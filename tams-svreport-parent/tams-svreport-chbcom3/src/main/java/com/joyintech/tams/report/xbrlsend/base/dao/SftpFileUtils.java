package com.joyintech.tams.report.xbrlsend.base.dao;


import com.jcraft.jsch.*;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class SftpFileUtils {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SftpFileUtils.class);

    private final Integer LOGIN_TYPE_NOPASSWORD = 2;
    /**
     * 日志工具类
     */
    public class SettleLogger implements com.jcraft.jsch.Logger {
        public boolean isEnabled(int level) {
            return true;
        }

        public void log(int level, String msg) {
            logger.info(msg);
        }
    }


    private SftpDto login() throws JSchException {
        String ip = CacheUtil.getSysParam("SYS_FS_SFTP_IP");
        String port = CacheUtil.getSysParam("SYS_FS_SFTP_PORT");
        Integer logintype = Integer.valueOf(CacheUtil.getSysParam("SYS_FS_SFTP_LOGINTYPE"));
        String password = CacheUtil.getSysParam("SYS_FS_SFTP_PASSWORD");
        String username = CacheUtil.getSysParam("SYS_FS_SFTP_USERNAME");
        SftpDto res = new SftpDto();
        Session sshSession = null;
        try {
            // 接口 SFTP 服务配置
            JSch jsch = new JSch();
            sshSession = jsch.getSession(username, ip, Integer.valueOf(port));
            com.jcraft.jsch.Logger jschLogger = new SettleLogger();
            JSch.setLogger(jschLogger);
            if (LOGIN_TYPE_NOPASSWORD == logintype) {
                jsch.addIdentity(password);
            } else {
                sshSession.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            res.setSshSession(sshSession);
            ChannelSftp sftp = (ChannelSftp) channel;
            res.setSftp(sftp);
        } catch (JSchException e) {
            logger.error("", e);
            throw new JSchException("登录文件服务器失败");
        }
        return res;
    }

    /***
     * 上传文件
     * @param uploadFile
     * @param filePath
     */
    public void upload(SysUploadFileInfo uploadFile,String filePath) throws Exception {
        String date = DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
        String remote = filePath  + date;
        SftpDto dto = null;
        dto = login();
        putFile(dto, uploadFile.getPath(), remote);
        close(dto);
    }

    /**
     * sftp将本地文件上传到远程服务器
     *
     * @param local 本地文件路径
     * @param remote 远程文件路径
     * @throws SftpException
     */
    public void putFile(SftpDto dto, String local, String remote) throws SftpException {
        try {
            dto.getSftp().ls(remote);
        }
        catch (SftpException e) {
            logger.info("服务器上没有该目录：" + remote);
            mkdir(dto, remote);
        }
        dto.getSftp().put(local, remote);
        logger.info("文件上传成功");
    }

    /**
     * 支持在SFTP 服务器上创建多级目录
     *
     * @param remote
     * @throws SftpException
     * @see
     * @since 1.0
     */
    private void mkdir(SftpDto dto, String remote) throws SftpException {
        String curdir = dto.getSftp().pwd();
        if (remote == null) {
            logger.error("路径不能为空！");
            return;
        }
        String[] remotes = remote.split("/");
        if (remote.startsWith("/")) {
            // 绝对路径从/下开始递归创建
            dto.getSftp().cd("/");
        }
        for (String dir : remotes) {
            if (StringUtil.isEmpty(dir)) {
                continue;
            }
            try {
                dto.getSftp().ls(dir);
            }
            catch (SftpException e) {
                logger.error("服务器上没有该目录：" + dir);
                dto.getSftp().mkdir(dir);
            }
            dto.getSftp().cd(dir);
        }
        dto.getSftp().cd(curdir);
    }


    /**
     * 关闭SFTP连接
     */
    private void close(SftpDto dto) {
        if (dto.getSftp().isConnected()) {
            dto.getSftp().disconnect();
            logger.info("成功关闭连接SFTP！");
        }
        if (dto.getSshSession() != null) {
            dto.getSshSession().disconnect();
            dto.setSshSession(null);
        }
    }

    /***
     * 下载文件
     * @param localDir
     * @return
     */
    public List<File> getFileListByPath(String localDir) {
        List<File> fileList = new ArrayList<>();
        SftpDto dto = null;
        try {
             dto = this.login();

            //ls命令获取文件名列表
            Vector vector = dto.getSftp().ls(localDir);
            Iterator iterator = vector.iterator();
            while (iterator.hasNext()) {
                ChannelSftp.LsEntry file = (ChannelSftp.LsEntry) iterator.next();
                //文件名称
                String fileName = file.getFilename();
                if(StringUtil.isNotEmpty(fileName)&&(!fileName.equals(".")&&!fileName.equals(".."))){
                    InputStream inputStream = dto.getSftp().get(localDir + fileName);
                    if (inputStream != null) {
                        File newFile = new File(fileName);
                        //将InputStream转File
                        this.copyFile(inputStream, newFile);
                        logger.info("文件名称:" + fileName);
//                        dto.getSftp().rm(fileName);
                        fileList.add(newFile);
                    }
                }

            }
        }catch (Exception e){
            logger.error("获取文件列表错误:",e);
        }finally {
            this.close(dto);
        }
        return fileList;
    }


    /**
     * Description: 从FTP服务器拷贝文件并重命名
     *
     * @return 文件复制后的信息
     */
    public void copyFile(InputStream inputStream, File file) throws IOException {
        OutputStream outstream = null;
        try{
            outstream = new FileOutputStream(file);
            byte b[] = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                outstream.write(b, 0, n);
            }
        }catch (Exception e){
            logger.error("复制文件错误",e);
        }finally {
            outstream.flush();
            outstream.close();
        }

    }



    private class SftpDto {
        private ChannelSftp sftp;
        private Session sshSession;

        public ChannelSftp getSftp() {
            return sftp;
        }

        public void setSftp(ChannelSftp sftp) {
            this.sftp = sftp;
        }

        public Session getSshSession() {
            return sshSession;
        }

        public void setSshSession(Session sshSession) {
            this.sshSession = sshSession;
        }
    }

}

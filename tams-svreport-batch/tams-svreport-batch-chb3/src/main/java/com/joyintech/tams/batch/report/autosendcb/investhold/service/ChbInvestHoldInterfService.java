package com.joyintech.tams.batch.report.autosendcb.investhold.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbFileReadMonitorInfoMapper;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;
import com.joyintech.tams.report.interf.common.util.InterfUtil;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusiDetails001Service;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.dao.ChbInvestHoldInfoTmpMpper;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.service.SvrChbInvestHoldFileImpService;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.submitmaintenance.proddeal.service.ProdDurationDeal001Service;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.service.ProdFactorInfo001Service;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


/**
 * 读取上游文件
 *
 * @author wm
 * @version 1.0 2017年11月9日
 * @see ChbInvestHoldInterfService
 * @since 1.0
 */
@Service("ChbInvestHoldInterfService")
public class ChbInvestHoldInterfService extends InterfService {

    private static final String[] XML_FILE_NAME = new String[] {"CHB_INVESTHOLD_INFO"};

    @Autowired
    private SvrChbInvestHoldFileImpService svrChbInvestHoldFileImpService;

    @Autowired
    private ChbFileReadMonitorInfoMapper chbFileReadMonitorInfoMapper;

    /**
     * 上游接口解析读取文件处理中，是否需要.ok传输完成标志文件（1：需要 0：不需要）
     */
    @Value("${application.reportInterfIsNeedOkFile}")
    private String reportInterfIsNeedOkFile;

    /**
     * 上游接口解析读取文件处理中，传输完成标志文件的扩展名（默认为ok）
     */
    @Value("${application.reportInterfOkFileExtension}")
    private String reportInterfOkFileExtension;

    /**
     * 读取上游接口
     *
     * @param currentDate 当前日期
     * @return map 返回map
     * @see
     * @since 1.0
     */
    public Map<String, InputStream> getUpInterfMessage(String currentDate,String saveFlag) {
        logger.info("开始处理读取上游接口推送的数据 " + currentDate);
        // 覆盖开关
        String isOpen = this.getParaVauleByparaCode(ReportGlobalConstant.DICT_INTERF_IMPORT_SWITCH);
        Map<String, InputStream> map = new HashMap<String, InputStream>();
        if (DictCodeList.是否_中债_是.getValue().equals(isOpen)) {
            // FTP_TYPE
            String ftpType = this.getParaVauleByparaCode(ReportGlobalConstant.SYS_PARA_FTP_TYPE);
            // IP
            String url = this.getParaVauleByparaCode(ReportGlobalConstant.SYS_PARA_SALES_FTP_IP);
            // PORT
            String port = this.getParaVauleByparaCode(ReportGlobalConstant.SYS_PARA_SALES_FTP_PROT);
            // ftp连接用户名
            String username = this.getParaVauleByparaCode(ReportGlobalConstant.SYS_PARA_SALES_FTP_USER);
            // ftp密码
            String password = this.getParaVauleByparaCode(
                    ReportGlobalConstant.SYS_PARA_SALES_FTP_PASSWORD);
            // 销售系统ftp路径
            String salesRemotePath="";
                salesRemotePath = this.getParaVauleByparaCode(
                        DictCodeListExt2.数据来源_销售系统.getValue());
            // 临时文件存放目录
            String localPath = this.getSysPara(null,
                    null, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                Map<String, String> mapPath = SysDataUtil.getSysDictDataMap(DictCode.数据来源);
                Iterator<Map.Entry<String, String>> entries = mapPath.entrySet().iterator();
                // 来源系统
                String type;
                // 配置读取路径
                String remotePath;
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    type = entry.getKey();
                    remotePath = this.getParaVauleByparaCode(entry.getKey());
                    if(DictCodeListExt2.数据来源_销售系统.getValue().equals(type)){
                        Date tempDate=DateUtil.parse(currentDate);
                        currentDate=DateUtil.format(tempDate, DateUtil.DEFAULT_OTHER_FORMAT);
                    }
                    try {
                        this.getInterfInputStream(ftpType, url, StringUtil.toInt(port), username, password,
                                remotePath + currentDate, localPath, type,currentDate,saveFlag);
                    } catch (FileNotFoundException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
        }
        logger.info("上游接口推送数据处理完成 " + currentDate);
        return map;
    }

    /**
     * 解析ftp中文件，并存入数据库
     *
     * @param ftpType ftp方式
     * @param url ftpIP
     * @param port ftp端口
     * @param username 用户名
     * @param password 密码
     * @param remotePath 路径
     * @param localPath 本地存放路径
     * @param type 来源系统
     * @param date 跑批日
     * @throws FileNotFoundException
     * @see
     * @since 1.0
     */
    public void getInterfInputStream(String ftpType, String url, int port, String username,
                                     String password, String remotePath, String localPath,
                                     String type,String date,String saveFlag) throws FileNotFoundException{
        // 删除临时文件夹下文件
        InterfUtil.deleteLocalTmpFile(localPath);

        List<File> xmlFiles = InterfUtil.downInterfFile(ftpType, url, port, username, password,
                remotePath, localPath, Arrays.asList(XML_FILE_NAME));
        if (StringUtil.isEmptyList(xmlFiles)) {
            return;
        }else{
            logger.info("共找到" + xmlFiles.size() + "个持有文件");
        }

        try {
            if (StringUtil.equals(ReportGlobalConstant.reportTinterfIsneedokfileYes,
                    reportInterfIsNeedOkFile)&&!DictCodeListExt2.数据来源_销售系统.getValue().equals(type)) {
                // .ok标志文件处理
                List<String> okFileList = InterfUtil.downInterfOKFile(ftpType, url, port, username,
                        password, remotePath, localPath, reportInterfOkFileExtension);
                if (StringUtil.isEmptyList(okFileList)) {
                    return;
                }

                for (int i = xmlFiles.size() - 1; i >= 0; i-- ) {
                    if (!okFileList.contains(StringUtil.splitToList(GlobalConstant.POINT,
                            xmlFiles.get(i).getName()).get(0))) {
                        xmlFiles.remove(i);
                    }
                }

                if (StringUtil.isEmptyList(xmlFiles)) {
                    return;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            return;
        }

        //进行排序 汇产品运作模式信息排在前，然后是投资者信息
        xmlFiles.sort(new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                int temp1 = 0;
                int temp2 = 0;
                if(o1.getName().indexOf("CHB_PRDRESIST_CODE") != -1){
                    temp1 = 1;
                }else if(o1.getName().indexOf("CHB_EQU_HOLD") != -1){
                    temp1 = 2;
                }else if(o1.getName().indexOf("CHB_INVESTHOLD_INFO") != -1){
                    temp1 = 3;
                }else{
                    temp1 = 4;
                }
                if(o2.getName().indexOf("CHB_PRDRESIST_CODE") != -1){
                    temp2 = 1;
                }else if(o2.getName().indexOf("CHB_EQU_HOLD") != -1){
                    temp2 = 2;
                }else if(o2.getName().indexOf("CHB_INVESTHOLD_INFO") != -1){
                    temp2 = 3;
                }else{
                    temp2 = 4;
                }
                return temp1 - temp2;
            }
        });

        // 遍历文件列表
        InputStream in = null;
        for (File file : xmlFiles) {
            try {
            String realName = file.getName();
            String okFileExtension = "." + reportInterfOkFileExtension;
            if (StringUtil.endsWith(realName, okFileExtension)) {
                    continue;
            }
            String suffix = FileNameUtil.getFileExtName(realName);
            try {
                in = new FileInputStream(file);

                // 解析处理开始前，登录监控信息表，状态处理中
                ChbFileReadMonitorInfo monitorInfoDto = new ChbFileReadMonitorInfo();
                monitorInfoDto.setHandleSeqno(
                        CommonUtil.getSequenceIndexData(DataIndexSequenceType.上游文件读取处理监控序列号));
                monitorInfoDto.setHandleFileName(realName);
                monitorInfoDto.setHandleFilePath(remotePath);
                monitorInfoDto.setFileReadStartTime(new Date());
                monitorInfoDto.setFileHandleStatus(DictCodeListExt2.上游接口文件读取处理状态_处理中.getValue());
                //投资者持有信息上游数据处理
                    String remoteDir = "fail";
                    try {
                        monitorInfoDto.setHandleFileType(DictCodeListExt2.上游接口文件类型_投资者持有信息.getValue());
                        chbFileReadMonitorInfoMapper.insert(monitorInfoDto);
                            remoteDir = svrChbInvestHoldFileImpService.getInvestorsHoldInfoByInterf(in, type,
                                    localPath, suffix, monitorInfoDto,saveFlag,realName);

                        this.handleSqlldrLogMsg(monitorInfoDto);

                        this.handleMonitorInfoAfterMainService(monitorInfoDto, remoteDir);
                    }
                    catch (Exception e) {
                        logger.error("上游接口数据处理失败：", e);

                        this.handleMonitorInfoAfterException(monitorInfoDto, e);
                    }
                    finally {
                        if (in != null){
                            try {
                                in.close();
                            } catch (IOException e) {
                                logger.error(e.getMessage(),e);
                            }
                        }
                        // ftp文件移动
                        if (StringUtil.isNotEmpty(remoteDir)) {
                            InterfUtil.moveFiles(ftpType, url, port, username, password, remotePath,
                                    realName, remoteDir);
                        }
                        // 删除临时文件
                        file.delete();
                    }
            }finally {
                if (in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw new BusinessException(e);
                    }
                }
            }

            }finally {
                if (in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
            }

        }
    }

    /**
     * 文件读取主处理后的处理，根据remoteDir判断文件是否成功处理
     * 设置到读取上游文件监控信息dto
     *
     * @param monitorInfoDto 读取上游文件监控信息dto
     * @param remoteDir remoteDir
     * @see
     * @since 1.0
     */
    private void handleMonitorInfoAfterMainService(ChbFileReadMonitorInfo monitorInfoDto,
                                                   String remoteDir) {
        monitorInfoDto.setFileReadEndTime(new Date());
        if (StringUtil.equals(remoteDir, "success")) {
            monitorInfoDto.setFileHandleStatus(DictCodeListExt2.上游接口文件读取处理状态_正常结束.getValue());
        }
        else {
            monitorInfoDto.setFileHandleStatus(DictCodeListExt2.上游接口文件读取处理状态_异常结束.getValue());
            if(monitorInfoDto.getFileHandleResultMsg() == null || "".equals(monitorInfoDto.getFileHandleResultMsg())){
                monitorInfoDto.setFileHandleResultMsg("数据文件有问题，已移动到fail文件夹下");
            }
        }

        chbFileReadMonitorInfoMapper.updateByPrimaryKeyWithBLOBs(monitorInfoDto);
    }

    /**
     * 解析sqlldr日志入库
     * @author liubin
     */
    public void handleSqlldrLogMsg(ChbFileReadMonitorInfo monitorInfoDto){
        String sqlldrLogFilePath = this.getParaVauleByparaCode(InterfConstant.SQLLDR_LOG_FILEPATH);
        StringBuffer info = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        InputStreamReader inreader = null;
        BufferedInputStream bfinput = null;
        FileInputStream in = null;
        File file = new File(sqlldrLogFilePath);
        if(file.isDirectory() && file.listFiles().length>0){
            //开始解析文件内容入库
            for(File logFile : file.listFiles()){
                try {
                    in = new FileInputStream(logFile);
                    bfinput = new BufferedInputStream(in);
                    inreader = new InputStreamReader(bfinput);
                    reader = new BufferedReader(inreader);
                    while((line=reader.readLine()) != null){
                        info.append(line).append("\n");
                    }
                    info.append("\n");
                } catch (Exception e) {
		            logger.error(e.getMessage(),e);
				}finally {
    			    if(in != null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    if(bfinput != null){
                        try {
                            bfinput.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    if(inreader != null){
                        try {
                            inreader.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    if(reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                }
            }
            //关闭流
            if(in!=null){
                try {
                    in.close();
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
            if(bfinput !=null){
                try {
                    bfinput.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            if(inreader != null){
                try {
                    inreader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            monitorInfoDto.setFileHandleResultMsg(info.toString());
            //解析完成删除sqlldr对应目录下的日志文件
            if(file.isDirectory() && file.listFiles().length>0){
                for(File logFile : file.listFiles()){
                    logFile.delete();
                }
            }
        }
    }

    /**
     * 文件读取主处理发生异常后的处理
     *
     * @param monitorInfoDto 读取上游文件监控信息dto
     * @param e 异常
     * @see
     * @since 1.0
     */
    private void handleMonitorInfoAfterException(ChbFileReadMonitorInfo monitorInfoDto,
                                                 Exception e) {
        monitorInfoDto.setFileReadEndTime(new Date());
        monitorInfoDto.setFileHandleStatus(DictCodeListExt2.上游接口文件读取处理状态_异常结束.getValue());
        monitorInfoDto.setFileHandleResultMsg("异常信息：" + e.getMessage());
        chbFileReadMonitorInfoMapper.updateByPrimaryKeyWithBLOBs(monitorInfoDto);
    }

}

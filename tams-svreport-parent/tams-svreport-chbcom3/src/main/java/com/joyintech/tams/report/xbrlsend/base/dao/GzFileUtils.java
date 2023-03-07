package com.joyintech.tams.report.xbrlsend.base.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzFileUtils {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(GzFileUtils.class);

    /**
     * 组装gz文件并且反馈文件路径
     * @param txtFile  文件路径
     * @param gzFilePath gz文件路径
     * @return
     * @throws IOException
     */
    public static String doGzipFile(String txtFile, String gzFilePath) throws IOException {
        BufferedOutputStream bos = null;
        GZIPOutputStream gzos = null;
        FileOutputStream fos = null;

        FileInputStream fis=new FileInputStream(txtFile);
        BufferedInputStream bis=new BufferedInputStream(fis);
        try {
            File txtGzFile=new File(gzFilePath);
            fos=new FileOutputStream(txtGzFile);
            bos=new BufferedOutputStream(fos);
            gzos = new GZIPOutputStream(bos);
            int len=0;
            byte[] buffer=new byte[1024];
            while ((len = bis.read(buffer)) != -1) {
                gzos.write(buffer, 0, len);
            }
            gzos.flush();
        } catch (Exception ex){
            logger.error(ex.toString());
        }finally {
            gzos.close();
            bis.close();
            bos.close();
            fos.close();
            fis.close();
        }
        return gzFilePath;
    }


    /**
     * 组装gz文件并且反馈文件路径
     * @param txtFile
     * @param txtGzFilePath
     * @return
     * @throws IOException
     */
    /**
     * 解压文件
     * @param sourcedir  文件目录及文件名字
     */
    public static void unGzipFile(String ouputfile,String sourcedir) throws IOException {
        FileOutputStream fout = null;
        GZIPInputStream gzin = null;
        FileInputStream fin = null;

        try {
            //建立gzip压缩文件输入流
            fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf('.'));
//            ouputfile = ouputfile.substring(0,ouputfile.lastIndexOf('.'));
            fout = new FileOutputStream(ouputfile);
            int num;
            byte[] buf=new byte[1024];

            while ((num = gzin.read(buf,0,buf.length)) != -1) {
                fout.write(buf,0,num);
            }

        } catch (Exception ex){
            logger.error(ex.toString());
        }finally {
            gzin.close();
            fout.close();
            fin.close();
        }
        return;
    }

}

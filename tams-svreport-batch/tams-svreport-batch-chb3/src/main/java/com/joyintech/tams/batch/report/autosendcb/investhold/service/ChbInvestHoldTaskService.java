package com.joyintech.tams.batch.report.autosendcb.investhold.service;

import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;

/**
 * 中债上游接口定时任务处理
 *
 * @author wyn
 * @version 1.0 20220520
 * @see ChbInvestHoldTaskService
 * @since 1.0
 */
@Service("ChbInvestHoldTaskService")
public class ChbInvestHoldTaskService extends InterfService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbInvestHoldTaskService.class);

    /**
     * 上游接口service
     */
    @Autowired
    private ChbInvestHoldInterfService chbInvestHoldInterfService;

    /**
     * 上游接口轮询任务
     *
     */
    @SuppressWarnings("resource")
    public void execute(Date batchDate,String saveFlag) {
        FileChannel channel = null;
        FileLock lock = null;
        try {
            String fileLockPath = this.getParaVauleByparaCode(InterfConstant.KEY_LOCK_FILEPATH);
            // 查看锁文件是否存在
            File lockPath = new File(fileLockPath);
            if (!lockPath.exists()) {
                lockPath.mkdirs();
            }
            File lockFile = new File(fileLockPath + File.separator + "up.lockfile");
            if (!lockFile.exists()) {
                lockFile.createNewFile();
            }
            channel = new FileOutputStream(lockFile).getChannel();
            // 获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
            lock = channel.tryLock();// 无参lock()为独占锁
            if (lock == null) {
                return;
            }
	        Date bdate=new Date();
	        Long bt=bdate.getTime();
	        logger.info("上游解析三期持有文件开始---------:"+ DateUtil.format(batchDate));
            try {
                // 获取消息（读取前一日文件夹信息）
                chbInvestHoldInterfService.getUpInterfMessage(DateUtil.format(batchDate),saveFlag);
            }
            catch (Exception e) {
            	 logger.error("error:",e);
                logger.error("读取前一日文件夹数据异常");
            }
        }
        catch (FileNotFoundException e) {
            logger.error("任务锁文件", e);
        }
        catch (IOException e) {
            logger.error("任务锁文件", e);
        }
        finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                }
                catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }

            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                }
                catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

}

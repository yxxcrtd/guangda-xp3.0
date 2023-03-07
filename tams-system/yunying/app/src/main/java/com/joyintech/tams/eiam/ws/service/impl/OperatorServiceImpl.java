package com.joyintech.tams.eiam.ws.service.impl;

import com.google.common.collect.Lists;
import com.joyintech.framework.security.User;
import com.joyintech.tams.eiam.dao.OperatorMapper;
import com.joyintech.tams.eiam.dto.OperatorDto;
import com.joyintech.tams.eiam.service.EiamConfig;
import com.joyintech.tams.eiam.service.EiamUserInfoSyncService;
import com.joyintech.tams.eiam.ws.service.OperatorService;
import com.joyintech.tams.eiam.ws.util.IAMDraftGenerateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@WebService(
        //对外发布的服务名
        serviceName = "OperatorService",
        // 与接口中的命名空间一致,一般是接口的包名倒,此处EIAM写死
        targetNamespace = "http://iam.cebbank.com",
        // 服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
        endpointInterface = "com.joyintech.tams.eiam.ws.service.OperatorService"
)
@Component
public class OperatorServiceImpl implements OperatorService {

    private static final Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private EiamUserInfoSyncService eiamUserInfoSyncService;

    @Override
    public String findUser(String accId) {
        logger.info("[findUser] accId {}", accId);

        // 执行
        User user = eiamUserInfoSyncService.findUser(accId);
        OperatorDto dto = null;
        if (user != null) {
            dto = new OperatorDto();
            dto.setAccId(user.getUid());
            dto.setName(user.getRealName());
        }

        // 生成报文
        String draftString = IAMDraftGenerateUtils.generateIF01Darft(dto);
        logger.info("[findUser] result {}", draftString);
        return draftString;
    }

    @Override
    public String addUserInfo(String userInfos) {
        logger.info("[addUserInfo] userInfos {}", userInfos);
        // 解析参数
        List<OperatorDto> optList = IAMDraftGenerateUtils.analyzeMsgToOperatorList(userInfos);

        // 执行
        List<String> successList = Lists.newArrayList();
        List<String> missList = Lists.newArrayList();
        boolean success = false;
        for (OperatorDto dto : optList) {
            try {
                success = eiamUserInfoSyncService.addUser(dto.getAccId(), dto.getName());

                if (success) {
                    successList.add(dto.getAccId());
                } else {
                    missList.add(dto.getAccId());
                }

            } catch (Exception e) {
                logger.error("add user {} fail. {}", dto, e);
                missList.add(dto.getAccId());
            }
        }

        // 生成报文
        String draftString = IAMDraftGenerateUtils.generateIF06Darft(successList, missList);
        logger.info("[addUserInfo] result {}", draftString);
        return draftString;
    }

    @Override
    public String delUser(String userIDs) {
        logger.info("[delUser] userIDs {}", userIDs);
        // 解析参数
        List<String> accIds = IAMDraftGenerateUtils.analyzeMsgToList(userIDs);

        // 执行
        List<String> successList = Lists.newArrayList();
        List<String> missList = Lists.newArrayList();
        for (String accId : accIds) {
            try {
                boolean success = eiamUserInfoSyncService.delUser(accId);

                if (success) {
                    successList.add(accId);
                } else {
                    missList.add(accId);
                }

            } catch (Exception e) {
                logger.error("del user {} fail. {}", accId, e);
                missList.add(accId);
            }
        }

        // 生成报文
        String draftString = IAMDraftGenerateUtils.generateIF08Darft(successList, missList);
        logger.info("[delUser] result {}", draftString);
        return draftString;
    }

    @Override
    public String modifyUserInfo(String userInfos) {
        logger.info("[modifyUserInfo] userInfos {}", userInfos);
        // 解析参数
        List<OperatorDto> optList = IAMDraftGenerateUtils.analyzeMsgToOperatorList(userInfos);

        // 执行
        List<String> successList = Lists.newArrayList();
        List<String> missList = Lists.newArrayList();
        for (OperatorDto dto : optList) {

            try {
                boolean success = eiamUserInfoSyncService.modifyUserInfo(dto.getAccId(), dto.getName());

                if (success) {
                    successList.add(dto.getAccId());
                } else {
                    missList.add(dto.getAccId());
                }

            } catch (Exception e) {
                logger.error("modify user {} fail. {}", dto, e);
                missList.add(dto.getAccId());
            }
        }

        // 生成报文
        String draftString = IAMDraftGenerateUtils.generateIF07Darft(successList, missList);
        logger.info("[modifyUserInfo] result {}", draftString);
        return draftString;
    }

    /**
     * 应急模式状态查询
     *
     * @return 正常状态返回字符串0 ，应急状态返回字符串1
     */
    @Override
    public String stateQuery() {

        String sysParamValue = operatorMapper.getSysParaValue(EiamConfig.SYSTEM_CODE, EiamConfig.PARA_CODE);

        return StringUtils.equals(sysParamValue, EiamConfig.EMERGENCY_MODE) ? EiamConfig.EMERGENCY_MODE : EiamConfig.NORMAL_MODE;
    }

    /**
     * 应急模式状态切换
     *
     * @param status 值为1 切换到应急状态，值为 0切换到正常状态
     * @return 返回应用切换后的状态，正常状态返回字符串 0 ，应急状态返回 字符串1
     */
    @Override
    public String setState(String status) {
        // 取配置表对象（其中包含应急模式标志位）
        operatorMapper.updateSysParaValue(EiamConfig.SYSTEM_CODE, EiamConfig.PARA_CODE, status);
        return status;
    }

}

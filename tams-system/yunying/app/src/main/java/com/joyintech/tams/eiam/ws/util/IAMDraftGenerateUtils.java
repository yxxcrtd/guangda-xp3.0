package com.joyintech.tams.eiam.ws.util;

import com.joyintech.tams.eiam.dto.OperatorDto;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 创建时间：2019.7.23
 * 类说明: IAM系统XML报文生成
 */
@SuppressWarnings("unchecked")
public class IAMDraftGenerateUtils {

    private static final Logger logger = LoggerFactory.getLogger(IAMDraftGenerateUtils.class);
    /**
     * 添加柜员账号成功代码
     */
    public static final String IF06_ADD_SUCC_RETURNCODE = "1300";
    /**
     * 添加柜员账号失败代码
     */
    public static final String IF06_ADD_MISS_RETURNCODE = "1301";
    /**
     * 修改柜员账号成功代码
     */
    public static final String IF07_EDIT_SUCC_RETURNCODE = "1302";
    /**
     * 修改柜员账号失败代码
     */
    public static final String IF07_EDIT_MISS_RETURNCODE = "1303";
    /**
     * 删除柜员账号成功代码
     */
    public static final String IF08_DEL_SUCC_RETURNCODE = "1304";
    /**
     * 删除柜员账号失败代码
     */
    public static final String IF08_DEL_MISS_RETURNCODE = "1305";

    /**
     * 柜员账号有效
     */
    public static final String OPERATOR_STATUS_VILID = "1";
    /**
     * 柜员账号无效
     */
    public static final String OPERATOR_STATUS_INVILID = "0";

    /**
     * 生成IF01根据从帐号ID获取从帐号信息接口报文
     *
     * @param user
     * @return
     */
    public static String generateIF01Darft(OperatorDto user) {
        String draftString = "";
        try {
            //生成xml文档
            Document sendDoc = DocumentHelper.createDocument();
            //生成根节点
            Element root = sendDoc.addElement("accounts");

            if (user != null && user.getAccId() != null) {
                Element second = root.addElement("account");

                second.addElement("accId").setText(user.getAccId() != null ? user.getAccId() : "");
                second.addElement("name").setText(user.getName() != null ? user.getName() : "");
                second.addElement("userPassword").setText("");
                second.addElement("employeeNumber").setText("");
                second.addElement("employeeType").setText("");
                second.addElement("idCardNumber").setText("");
                second.addElement("postOfficeBox").setText("");
                second.addElement("telephoneNumber").setText("");
                second.addElement("mobile").setText("");
                second.addElement("address").setText("");
                second.addElement("gender").setText("");
                second.addElement("workOrg").setText("");
                second.addElement("status").setText("");
                second.addElement("endTime").setText("");
                second.addElement("birthday").setText("");
                second.addElement("religion").setText("");
                second.addElement("nation").setText("");
                second.addElement("duty").setText("");
                second.addElement("supporterCorpName").setText("");

            } else {
                root.setText("");
            }
            draftString = sendDoc.asXML();
            logger.error("returnIF01DraftResult= " + draftString);
        } catch (Exception e) {
            logger.error("根据从帐号ID获取从帐号信息生成IF01报文失败 " + e.getMessage());
            e.printStackTrace();
        }
        return draftString;
    }

    /**
     * 统一生成返回信息
     *
     * @param succList 账号成功列表
     * @param missList 账号失败列表
     * @param busiCode 交易码
     * @return
     */
    public static String generateCommonReturnDarft(List<String> succList, List<String> missList, String busiCode) {
        String draftString = "";
        try {
            String succTemp = "";
            String missTemp = "";
            if ("IF06".equalsIgnoreCase(busiCode)) {
                succTemp = IF06_ADD_SUCC_RETURNCODE;
                missTemp = IF06_ADD_MISS_RETURNCODE;
            } else if ("IF07".equalsIgnoreCase(busiCode)) {
                succTemp = IF07_EDIT_SUCC_RETURNCODE;
                missTemp = IF07_EDIT_MISS_RETURNCODE;
            } else if ("IF08".equalsIgnoreCase(busiCode)) {
                succTemp = IF08_DEL_SUCC_RETURNCODE;
                missTemp = IF08_DEL_MISS_RETURNCODE;
            }

            //生成xml文档
            Document sendDoc = DocumentHelper.createDocument();
            //生成根节点
            Element root = sendDoc.addElement("results");

            Element second = root.addElement("result");
            //1300为添加柜员账号成功代码
            second.addAttribute("returncode", succTemp);
            if (succList != null && succList.size() > 0) {
                for (String string : succList) {
                    second.addElement("accId").setText(string != null ? string : "");
                }
            } else {
                second.setText("");
            }
            Element second2 = root.addElement("result");
            //1301为添加柜员账号失败代码
            second2.addAttribute("returncode", missTemp);
            if (missList != null && missList.size() > 0) {
                for (String string : missList) {
                    second2.addElement("accId").setText(string != null ? string : "");
                }
            } else {
                second2.setText("");
            }
            draftString = sendDoc.asXML();
            logger.error("returnDraftResult= " + draftString);
        } catch (Exception e) {
            logger.error("应用系统中生成返回报文失败 " + e.getMessage());
            e.printStackTrace();
        }
        return draftString;
    }

    /**
     * 生成在应用系统中添加帐号IF06接口报文返回数据
     *
     * @param succList 柜员账号添加成功代码列表
     * @param missList 柜员账号添加失败代码列表
     * @return
     */
    public static String generateIF06Darft(List<String> succList, List<String> missList) {

        String draftString = generateCommonReturnDarft(succList, missList, "IF06");

        return draftString;
    }

    /**
     * 生成在应用系统中删除帐号IF08接口报文返回数据
     *
     * @param succList 柜员账号删除成功代码列表
     * @param missList 柜员账号删除失败代码列表
     * @return
     */
    public static String generateIF08Darft(List<String> succList, List<String> missList) {
        String draftString = generateCommonReturnDarft(succList, missList, "IF08");

        return draftString;
    }

    /**
     * 生成在应用系统中修改帐号IF07接口报文返回数据
     *
     * @param succList 柜员账号修改成功代码列表
     * @param missList 柜员账号修改失败代码列表
     * @return
     */
    public static String generateIF07Darft(List<String> succList, List<String> missList) {
        String draftString = generateCommonReturnDarft(succList, missList, "IF07");

        return draftString;
    }

    /**
     * 解析IAM来报字符串为Document
     *
     * @param msgBody
     * @return
     */
    public static Document analyzeMsgToDocument(String msgBody) {
        Document document = null;
        try {
            InputStream in = new ByteArrayInputStream(msgBody.getBytes("utf-8"));
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(in);
        } catch (Exception e) {
            logger.error("接收的消息体格式不正确:" + msgBody);
            throw new RuntimeException("接收的消息体格式不正确");
        }
        return document;
    }

    /**
     * 解析IAM来报账号列表信息
     *
     * @param userInfos IAM来报
     * @return 账号列表信息
     */
    public static List<OperatorDto> analyzeMsgToOperatorList(String userInfos) {
        List<OperatorDto> optList = null;
        try {
            //字符串数据转换为文档
            Document document = DocumentHelper.parseText(userInfos);

            List list = document.selectNodes("//accounts/account");
            OperatorDto user = null;
            //xml节点数据转换为对象数据
            if (list != null && list.size() > 0) {
                optList = new ArrayList<OperatorDto>();
                for (int i = 0; i < list.size(); i++) {
                    Node node = (Node) list.get(i);
                    user = new OperatorDto();
                    String accId = node.selectSingleNode("//accounts/account/accId").getText();
                    String name = node.selectSingleNode("//accounts/account/name").getText();
                    String idCardNumber = node.selectSingleNode("//accounts/account/idCardNumber").getText();
                    user.setAccId(accId);
                    user.setName(name);
                    user.setIdCardNumber(idCardNumber);
                    optList.add(user);
                }
            }
        } catch (Exception e) {
            logger.error("字符串数据转换为文档失败");
            e.printStackTrace();
        }
        return optList;
    }


    /**
     * 解析IAM来报账号ID列表
     *
     * @param userIDs IAM来报
     * @return 账号列表信息
     */
    public static List<String> analyzeMsgToList(String userIDs) {
        List<String> userIDList = null;
        try {
            logger.error("IAMDraft=" + userIDs);
            //字符串数据转换为文档
            Document document = DocumentHelper.parseText(userIDs);
            List list = document.selectNodes("//accounts/accId");

            //xml节点数据转换为对象数据
            if (list != null && list.size() > 0) {
                userIDList = new ArrayList<String>();
                for (int i = 0; i < list.size(); i++) {
                    Node node = (Node) list.get(i);
                    String accId = node.getText();
                    userIDList.add(accId);
                }
            }
        } catch (Exception e) {
            logger.error("字符串数据转换为文档失败");
            e.printStackTrace();
        }
        return userIDList;
    }
}

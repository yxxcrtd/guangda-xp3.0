package com.joyintech.tams.eiam.dto;

import java.util.Date;

/**
 * @author chenwei
 * E-mail: chenwei5@hundsun.com
 * 创建时间：Aug 9, 2011 11:12:48 AM
 * 类说明： IAM柜员信息
 */
public class OperatorDto {
    private Long operId;
    /**
     * 账号ID
     */
    private String accId;
    /**
     * 柜员姓名
     */
    private String name;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 柜员是否有效 0 无效 1 有效
     */
    private String operFlag;
    /**
     * 柜员新增日期
     */
    private Date addDt;
    /**
     * 所属机构ID
     * <employeeNumber>员工编号</employeeNumber>
     * <userPassword>密码</userPassword>
     * <postOfficeBox>电子邮箱</postOfficeBox>
     * <mobile>手机号码</mobile>
     * <address>地址</address>
     * <gender>性别</gender>
     * <workOrg>成本中心编号</workOrg>
     * <employeeType>员工类型</employeeType>
     * <idCardNumber>证件号码</idCardNumber>（非空项）
     * <telephoneNumber>座机号码</telephoneNumber>
     * <status>用户状态</status>
     * <endTime>帐号到期时间</endTime>
     * <birthday>生日</birthday>
     * <religion>政治面貌</religion>
     * <nation>民族</nation>
     * <duty>岗位</duty>
     * <supporterCorpName>所在公司</supporterCorpName>
     */
    private String brchId;
    /**
     * 证件号码
     */
    private String idCardNumber;


    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperFlag() {
        return operFlag;
    }

    public void setOperFlag(String operFlag) {
        this.operFlag = operFlag;
    }

    public Date getAddDt() {
        return addDt;
    }

    public void setAddDt(Date addDt) {
        this.addDt = addDt;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getBrchId() {
        return brchId;
    }

    public void setBrchId(String brchId) {
        this.brchId = brchId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

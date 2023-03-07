package com.joyintech.tams.batch.svreport.pbc.zgdatfile.service;

import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataCollectDatFileDto;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.service.PbcZGDatFileHelper;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SvrPbcZGDatFileCommonSevice
 * @Description: 人行资管产品基本信息（ZG01）dat公用类
 * @Author yanya
 * @Date 2022-11-11
 * @Version V1.0
 **/

@Service(value = "svrPbcZGDatFileCommonSevice")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SvrPbcZGDatFileCommonSevice {

    @Autowired
    private SvrDBUtil dbUtil;

    /**
     * 处理选择的产品
     * @param prodList
     * @param batchExecuteDetail
     * @param prodListex
     */
    public void dealProdList(List<String> prodList, SysBatchgrpExecInfo batchExecuteDetail, List<String> prodListex ) {
        if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
            Map<String,String> prodMap = dbUtil.getAllProdMapWithNoSameId();
            if(!prodListex.isEmpty()) {
                for(String prodString : prodListex) {
                    //净值型产品过滤“F16_”前缀
                    prodString = prodString.replaceFirst("F16_", "");
                    if(prodMap.containsKey(prodString)){
                        prodList.add(prodMap.get(prodString));
                    }else{
                        prodList.add(prodString);
                    }
                }
            }
        }
    }

    /**
     * 生成UUID
     * @return
     */
    public String generateUUID() {
        return IDGenerator.generate(36);
    }

    /**
     * 构建ZG明细map
     * @param searchParam
     * @param batchDate
     * @param uuid
     */
    public void buildZGDetailMap(Map<String, Object> searchParam, Date batchDate, String uuid, String type) {
        searchParam.put("batchDate", batchDate);
        searchParam.put("termid", DateUtil.format(DateUtil.addDay(batchDate, -1), DateUtil.MONTH_OTHER_FORMAT));
        searchParam.put("termiden", DateUtil.format(DateUtil.addDay(batchDate, -1), DateUtil.MONTH_DATE_FORMAT_EN));
        searchParam.put("reportStatus", DictCodeListExt2.报送状态_未报送.getValue());
        //searchParam.put("status", DictCodeListExt2.数据状态_有效.getValue());
        searchParam.put("reportName", type);
        searchParam.put("sendStatusList", StringUtil.splitToList(",", DictCodeListExt2.报送状态_未报送.getValue()));
        searchParam.put("detailid", uuid);
        Date nowDate = new Date();
        searchParam.put("createUser", CommonUtil.getUserName());
        searchParam.put("cerateDept", CommonUtil.getDeptCode());
        searchParam.put("createTime", nowDate);
        searchParam.put("updateUser", CommonUtil.getUserName());
        searchParam.put("updateTime", nowDate);
    }
    /**
     * 构建采集dat文件主表dto
     * @param batchDate
     * @param type
     * @return
     */
    public PbcDataCollectDatFileDto buildPbcDataCollectDatFileDto(Date batchDate, String type) {
        String uuid = generateUUID();
        Date nowDate = new Date();
        //dat文件名不对修复
        String issuePartyId =  CommonUtil.selectPbcBankCode(null);
        String fileName="";
        if(StringUtil.equals(PbcZGDatFileHelper.DATAFILE_TYPE_ZG01,type)||
                StringUtil.equals(PbcZGDatFileHelper.DATAFILE_TYPE_ZG02,type)||
                StringUtil.equals(PbcZGDatFileHelper.DATAFILE_TYPE_ZG03,type)
        ){
            fileName = PbcZGDatFileHelper.createPbcFileName(issuePartyId, type, batchDate, PbcZGDatFileHelper.文件后缀);
        }else{
            Date batchDate2=DateUtil.parse(com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(batchDate));
            fileName = PbcZGDatFileHelper.createPbcFileName(issuePartyId, type, batchDate2, PbcZGDatFileHelper.文件后缀);
        }
        PbcDataCollectDatFileDto record = new PbcDataCollectDatFileDto();
        record.setUuid(uuid);//
        record.setDatFileName(fileName);//
        record.setDatFileNameMd5(DigestUtils.md5Hex(fileName));//
        record.setReportName(type);//
        record.setCreateDate(batchDate);//
        record.setReportStatus(DictCodeListExt2.报送状态_未报送.getValue());//主键
        record.setCreateUser(CommonUtil.getUserName());
        record.setCreateTime(nowDate);
        record.setCreateDept(CommonUtil.getDeptCode());
        record.setUpdateUser(CommonUtil.getUserName());
        record.setUpdateTime(nowDate);
        return record;
    }


}

package com.joyintech.tams.report.xbrlsend.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquSub;
import com.joyintech.tams.common.base.entity.ChbInfoReportXbrl;
import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.common.base.entity.ChbXbrlbackErrordata;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface XbrlSendBaseMapper {

    int insert(ChbInfoReportXbrl record);

    int updateByPrimaryKeySelective(ChbInfoReportXbrl record);

    ChbInfoReportXbrl selectByPrimaryKey(String reportNo);

    /**
     * 获取最新的记录信息.目前
     * @param submitType
     * @param sendTime
     * @param uploadResult
     * @return
     */
    ChbInfoReportXbrl getMaxXbrlDataByValueDate(@Param("submitType") String submitType, @Param("sendTime") List<Date> execDate, @Param("checkResult") String checkResult);

    /**
     * 获取最新的记录信息.目前
     * @param submitType
     * @param sendTime
     * @param uploadResult
     * @return
     */
    ChbInfoReportXbrl getErrorData(@Param("submitType") String submitType, @Param("sendTime") List<Date> execDate, @Param("dataList") List<EquityInfoRegist001Dto> dataList);


    /**
     *
     * @param fileId
     * @return
     */
    ChbInfoReportXbrl getChbInfoReportXbrlByFileId(@Param("fileId") String fileId);


    void batchInsertData(@Param("list") List<ChbXbrlbackErrordata> list);

    void updateChbXbrlBackBatch(@Param("list") List<ChbXbrlbackErrordata> list,@Param("updateType") String dataType);

    /**
     * 登录中债报送状态数据
     *
     * @param chbInfoReportList 中债报送状态数据
     * @see
     * @since 1.0
     */
    void insertChbSubmitStatusList(@Param("xbrl") ChbInfoReportXbrl xbrl,@Param("submitStatus") String submitStatus);
    /**
     * 登录中债投资者明细报送状态数据
     *
     * @param chbSubmitStatusList 中债报送状态数据
     * @see
     * @since 1.0
     */
    void insertChbEquDetSubmitStatusList(@Param("xbrl") ChbInfoReportXbrl xbrl,@Param("submitStatus") String submitStatus);

}

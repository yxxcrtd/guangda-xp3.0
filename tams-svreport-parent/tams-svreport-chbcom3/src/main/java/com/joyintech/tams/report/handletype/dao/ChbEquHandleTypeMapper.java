package com.joyintech.tams.report.handletype.dao;

import com.joyintech.tams.report.handletype.dto.EquityHolderTypeDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbEquHandleTypeMapper {

	/**
	 * 查询持有信息，返回高净值和私人银行客户
	 * @param
	 * @return
	 */
	List<EquityHolderTypeDto> selectInvestHoldData(@Param("excuteDate")String excuteDate);

	/***
	 * 删除临时表数据
	 */
	void  truncateTmp();

	/***
	 * 查询身份登记表数据中投资者类型变更的身份
	 * @return
	 */
	List<EquityInfo001Dto>   selectChangeHoldType();

	void  updateSubHandleInfoType(List<EquityInfo001Dto> list);


	void  updateSubHandleType(List<EquityInfo001Dto> list);


}
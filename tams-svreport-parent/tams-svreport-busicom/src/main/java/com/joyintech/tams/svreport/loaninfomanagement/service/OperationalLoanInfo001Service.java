package com.joyintech.tams.svreport.loaninfomanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbLoanDetailInfoMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 贷款明细信息维护 服务Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalLoanInfo001Service extends BaseService<OperationalLoanInfo001Dto>{

	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;
	
	@Autowired
	private ChbLoanDetailInfoMapper chbLoanDetailInfoMapper;
	
	/**
	 * 根据主键删除数据
	 * @param param
	 */
	public void doDelete(Map<String, Object> param){
		String assetUuid = param.get("assetUuid").toString();
		String assetCode = param.get("assetCode").toString();
		OperationalLoanInfo001Dto dto = new OperationalLoanInfo001Dto();
		dto.setAssetUuid(assetUuid);
		String status =DictCodeListExt1.维护数据状态_删除.getValue();
		dto.setStatus(status);
		//删除 删除所有状态的记录
		operationalLoanInfo001Mapper.selectStatusByAssetCode(assetCode);
		//再生成一条删除状态的记录
		OperationalLoanInfo001Dto statusDDto = operationalLoanInfo001Mapper.selectByPrimaryKey(assetUuid);
		OperationalLoanInfo001Dto newDto = new OperationalLoanInfo001Dto();
		BeanUtil.copyPropertiesIgnoreNull(newDto,statusDDto);
		newDto.setStatus("D");
		chbLoanDetailInfoMapper.updateByPrimaryKeySelective(newDto);
		//同时生成一条待处理数据，该数据字段中资产代码，
		//人行资产三级分类字段为该条删除数据的对应字段值，其他字段值置空
		if(StringUtil.equals(param.get("status").toString(),"E")){
			OperationalLoanInfo001Dto tempDto = new OperationalLoanInfo001Dto();
			tempDto.setAssetUuid(IDGenerator.generate(36));
			tempDto.setAssetCode(assetCode);
			tempDto.setAssetType(param.get("assetType").toString());
			tempDto.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
			chbLoanDetailInfoMapper.insertSelective(tempDto);
		}
	}
	
	/**
	 * 根据主键查询资产收益权贷款明细基本信息表数据
	 * @param param
	 * @return
	 */
	public OperationalLoanInfo001Dto selectByPrimaryKey(Map<String, Object> param){
		String assetUuid = param.get("assetUuid").toString();
		return operationalLoanInfo001Mapper.selectByPrimaryKey(assetUuid);
	}
	
	/**
	 * 查询产品资产代码
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> queryPbcAssetCode(Map<String, Object> param,int page,int length){
		return this.queryForPageForSelectReturn(page, length,()->operationalLoanInfo001Mapper.queryPbcAssetCode(param));
	}
	public String queryPbcAssetType(Map<String, Object> param){
		String assetCode = param.get("assetCode").toString();
		return operationalLoanInfo001Mapper.queryPbcAssetType(assetCode);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(OperationalLoanInfo001Dto dto){
		String pageModel = dto.getPageModel();
		String status =DictCodeListExt1.维护数据状态_有效.getValue();
		dto.setStatus(status);
		dto.setUpdateUser(CommonUtil.getUserName());
		dto.setUpdateTime(DateUtil.now());
		if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			dto.setAssetUuid(IDGenerator.generate(36));
			dto.setCreateUser(CommonUtil.getUserName());
			dto.setCreateTime(DateUtil.now());
			dto.setCreateDept(CommonUtil.getDeptCode());
			dto.setOrgCode(CommonUtil.getOrgCode());
			dto.setDeptCode(CommonUtil.getDeptCode());
			dto.setDataSource(DictCodeListExt2.数据来源_系统新建.getValue());
			
			//插入资产收益权贷款明细基本信息表
//			operationalLoanInfo001Mapper.insertLoanInfo(dto);
			chbLoanDetailInfoMapper.insert(dto);
		}else if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			dto.setStatus(DictCodeListExt1.维护数据状态_修改.getValue());
			//更新资产收益权贷款明细基本信息表
			chbLoanDetailInfoMapper.updateByPrimaryKeySelective(dto);
		}
	}
	
	public int checkAssetCodeExist(String assetCode){
		return operationalLoanInfo001Mapper.checkAssetCodeExist(assetCode);
	}
	
	/**
	 * 根据计划名称查询计划id以及待维护表ID
	 * @param pid
	 * @return
	 */
	public OperationalLoanInfo001Dto queryPlanInfo(String pid){
		return operationalLoanInfo001Mapper.queryPlanInfo(pid);
	}
	
	/**
     * 
     * 省市县联动
     * @param param
     * @return
     */
    @Transactional
    @DataSource(DataSourceType.OTHER)
    public Page<SelectOptionDto> getAddressSelectOption(Map<String, Object> param,int page,int length){
        return this.queryForPageForSelectReturn(page, length,()->operationalLoanInfo001Mapper.getAddressSelectOption(param));
    }

	/**
	 * 分页查询
	 *
	 * @param page 页编号
	 * @param length 页条数
	 * @param param 画面参数
	 * @return 查询结果
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	public Page<OperationalLoanInfo001Dto> findPage(Map<String, Object> param, int page, int length) {
		//01收益权,02贷款
		param.put("assetType08","08");
		return this.queryForPage(page, length,
				() -> operationalLoanInfo001Mapper.findPage(param));
	}
}

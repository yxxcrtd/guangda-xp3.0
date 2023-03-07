package com.joyintech.tams.svreport.loaninfomanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfoMService;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.loaninfomanagement.service.OperationalLoanInfoMService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 贷款明细信息维护 Controller
 * @author hpp
 *
 */
@Controller
@PageId("loanplan")
@RequestMapping("/common/loaninfomanagement/OperationalLoanInfo001")
public class OperationalLoanInfo001Controller extends BaseController{

	@Autowired
	private OperationalLoanInfoMService operationalLoanInfoMService;
	@Autowired
	private OperationalInfoMService operationalInfoMService;


	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model,DictCode.借款人类型, DictCode.中债数据状态,DictCode.新人行增信机构类型,DictCode.客户一级类型);
		return super.init(model);
	}

//	@RequestMapping("{pid}/" + PAGE_INIT)
//	public String init(Model model, @PathVariable("pid") String pid) {
//
//		//根据计划名称查询计划id以及待维护表ID
//		OperationalLoanInfo001Dto dto = operationalLoanInfoMService.queryPlanInfo(pid);
//		model.addAttribute("filterId", dto.getFilterid());
//		// 设置默认查询条件 "CHB_LOAN_DETAIL_INFO"
//        model.addAttribute("tableId", dto.getBasetableid());
//
//		List<SelectOptionDto> listTableName = new ArrayList<SelectOptionDto>();
//		List<BaseInformationDto> baseInfomationList = operationalInfoMService.queryAllTableInfo();
//		baseInfomationList.forEach(baseInformationDto -> {
//			SelectOptionDto selectOptionDto = new SelectOptionDto();
//            selectOptionDto.setId(baseInformationDto.getBaseTableId());
//            selectOptionDto.setText(baseInformationDto.getBaseTableName());
//            selectOptionDto.setCol("");
//            listTableName.add(selectOptionDto);
//		});
//        model.addAttribute("tableNames", JsonUtils.objectArray2Json(listTableName.toArray()));
//
//        return super.init(model);
//	}
	
	/**
	 * 分页查询
	 * @return 业务数据维护查询结果
	 */
	@ResponseBody
    @RequestMapping("findPage")
    public Page<OperationalLoanInfo001Dto> findPage() {
         return operationalLoanInfoMService.findPage(RequestUtil.getPageParam());
    }
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult doDelete(@RequestBody OperationalLoanInfo001Dto dto, BindingResult br)
			throws Exception {
		List<OperationalLoanInfo001Dto> selectedItem = dto.getSelectedItem();
		Map<String,Object> param = new HashMap<String,Object>();
		for(OperationalLoanInfo001Dto operationalLoanInfo001Dto : selectedItem){
			param = BeanUtil.object2Map(operationalLoanInfo001Dto);
			operationalLoanInfoMService.doDelete(param);
		}
		AjaxResult ar = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
		return ar;
	}
	
	/**
     * 导出excel
     *
     * @return 导出
     */
	@ResponseBody
	@RequestMapping("export")
    public ResponseEntity<byte[]> export() {
    	return operationalLoanInfoMService.export2(this.getPageId(), RequestUtil.getPageParam());
    }


    //导入模板下载
	@RequestMapping("export01")
	public ResponseEntity<byte[]> export01() throws Exception {
		Map<String, Object> params = RequestUtil.getPageParam();
		return operationalLoanInfoMService.export01(this.getPageId(), params);
	}

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName) throws Exception  {
        return operationalLoanInfoMService.checkConfirmMsgForImportData(fileName);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 修改条数
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public AjaxResult importData(String fileName) {
    	return operationalInfoMService.importData(fileName);
    }
	/**
	 * 导入（数据保存）
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/importFileSave")
	public List<com.joyintech.tams.systemtools.dto.ExcelValidDto> importFileSave(String fileName) throws Exception {
		return this.operationalLoanInfoMService.importFileSave(fileName);
	}
}

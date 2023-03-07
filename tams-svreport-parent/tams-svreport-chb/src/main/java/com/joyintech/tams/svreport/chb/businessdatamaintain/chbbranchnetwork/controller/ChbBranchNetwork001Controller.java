
package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.service.ChbBranchNetworkService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网点信息映射表管理
 * 
 * @author zhoubin
 * @version 1.0 2022年05月16日
 * @see ChbBranchNetwork001Controller
 */
@Controller
@PageId("ChbBranchNetwork001")
@RequestMapping("/chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001")
public class ChbBranchNetwork001Controller extends BaseController {
    /**
     * 网点信息映射表管理service
     */
    @Resource
    private ChbBranchNetworkService chbBranchNetworkService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //网点所属监管
        this.setSelectOptionToModel(model, DictCode.监管机构2);
        //网点所属地区
        this.setSelectOptionToModel(model, DictCode.销售区域_中债);
        //数据的状态
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }
    /**
     * 网点信息映射表管理查询
     * @return 返回查询结果
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChbBranchNetworkDto> selectAll(){
        Page<ChbBranchNetworkDto> page = chbBranchNetworkService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ChbBranchNetworkDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ChbBranchNetworkDto dto) throws Exception {
     chbBranchNetworkService.delete(dto);
    return new AjaxResult("操作成功");
    }

    /**
     * 导出信息excel
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){

        return chbBranchNetworkService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));

    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return chbBranchNetworkService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return chbBranchNetworkService.importData(fileName);

    }

}

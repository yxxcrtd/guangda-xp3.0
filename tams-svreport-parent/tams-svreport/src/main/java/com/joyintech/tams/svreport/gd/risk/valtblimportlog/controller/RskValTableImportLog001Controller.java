package com.joyintech.tams.svreport.gd.risk.valtblimportlog.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.gd.risk.valtblimportlog.dto.RskValTableImportLogDto;
import com.joyintech.tams.svreport.gd.risk.valtblimportlog.service.RskValTableImportLogService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *   估值表查询
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月27日
 * @see RskValTableImportLog001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValTableImportLog001")
@RequestMapping("/invest/risk/valtblimportlog/RskValTableImportLog001")
public class RskValTableImportLog001Controller extends BaseController {

    @Autowired
    private RskValTableImportLogService rskValTableImportLogService;
    @Override
    public String init(Model model){
        super.setSelectOptionToModel(model, DictCode.估值自动导入状态);
        return super.init(model);
    }


    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValTableImportLogDto> selectAll()
            throws Exception {
        Page<RskValTableImportLogDto> page = rskValTableImportLogService.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return rskValTableImportLogService.downLoadValTable(param);
    }

    @ResponseBody
    @RequestMapping(value="uploadData")
    public AjaxResult uploadData(String fileName) throws IOException, InterruptedException {
        AjaxResult webResult= new AjaxResult();
        String filePath = CacheUtil.getSysParam("VALTBL_GTP_PATH");
        if (!filePath.endsWith(File.separator)){
            filePath = filePath + File.separator;
        }
        String newFilePath = filePath + new File(fileName).getName();
        FileUtils.copyFile(new File(fileName),new File(newFilePath));
        return webResult;
    }

    @ResponseBody
    @RequestMapping(value="runAutoImport")
    public AjaxResult runAutoImport(){
        AjaxResult webResult= new AjaxResult();
        webResult = rskValTableImportLogService.runAutoImport();
        return webResult;
    }

    @ResponseBody
    @RequestMapping(value="checkBatchClash")
    public AjaxResult checkBatchClash(@RequestBody String taskId){
        AjaxResult ajaxResult= new AjaxResult(this.getMessage("RSK_G_Z_0073"));
        boolean flag = rskValTableImportLogService.checkBatchClash(taskId);
        if (!flag){
            ajaxResult.setMessage(this.getMessage("RSK_G_Z_0072"), MessageType.错误消息);
        }
        return ajaxResult;
    }
}

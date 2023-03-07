package com.joyintech.service.chb.gd.risk.valtblimportlog.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chb.gd.risk.valtblimportlog.dto.RskValTableImportLogDto;
import com.joyintech.service.chb.gd.risk.valtblimportlog.service.RskValTableImportLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("rskValTableImportLog_dtx")
@RequestMapping("/RskValTableImportLog")
public class RskValTableImportLogController {

    @Autowired
    private RskValTableImportLogService rskValTableImportLogService;

    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskValTableImportLogDto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValTableImportLogService.selectForPage(page, length, param);
    }

    @PostMapping("selectAllData")
    public List<RskValTableImportLogDto> selectAllData(@RequestBody Map<String, Object> param) throws Exception {
        return rskValTableImportLogService.selectAllData( param);
    }

    @PostMapping("updateBatch")
    public void updateBatch(@RequestBody List<RskValTableImportLogDto> list) throws Exception {
        rskValTableImportLogService.updateBatch(list);
    }

    @PostMapping("checkBatchClash")
    public boolean checkBatchClash(@RequestBody String taskId) throws Exception {
        return rskValTableImportLogService.checkBatchClash(taskId);
    }

}

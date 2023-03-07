package com.joyintech.service.chb.gd.risk.valtblimportlog.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.chb.gd.risk.valtblimportlog.dao.RskValTableImportLogMapper;
import com.joyintech.service.chb.gd.risk.valtblimportlog.dto.RskValTableImportLogDto;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("backRskValTableImportLogService")
public class RskValTableImportLogService extends BaseService<RskValTableImportLogDto> {

    @Autowired
    private RskValTableImportLogMapper rskValTableImportLogMapper;


    public Page<RskValTableImportLogDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        Page<RskValTableImportLogDto> pages;
        if(StringUtil.isNotEmpty(pageParam.get("ptlId"))){
            pageParam.put("ptlIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("ptlId")));
        }
        if(StringUtil.isNotEmpty(pageParam.get("status"))){
            pageParam.put("statusList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("status")));
        }

        pages = super.queryForPage(page, length, () -> rskValTableImportLogMapper.queryForPage(pageParam));
        dealDataList(pages.getRoot());
        return pages;
    }

    private void dealDataList(List<RskValTableImportLogDto> list) {
        list.forEach(item -> {
            item.setStatusName(SysDataUtil.getNameFromDataDict(
                    DictCode.估值自动导入状态 , item.getStatus()));

        });


    }

    public List<RskValTableImportLogDto> selectAllData( Map<String, Object> pageParam)
            throws Exception {
        List<RskValTableImportLogDto> list;
        if(StringUtil.isNotEmpty(pageParam.get("ptlId"))){
            pageParam.put("ptlIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("ptlId")));
        }
        if(StringUtil.isNotEmpty(pageParam.get("status"))){
            pageParam.put("statusList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("status")));
        }

        list = rskValTableImportLogMapper.queryForPage(pageParam);
        dealDataList(list);
        return list;
    }

    public void updateBatch(List<RskValTableImportLogDto> list){
        rskValTableImportLogMapper.updateBatch(list);
    }

    /**
     * 验证批处理是否正在运行
     * @param taskId
     * @return
     */
    public boolean checkBatchClash(String taskId) {
        int count =  rskValTableImportLogMapper.selectNoendCountByTaskBeanName(taskId);
        if (count > 0) {
            return false;
        }
        return true;
    }
}

package com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto;

import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;

/**
 * @ClassName PbcDataZG01DatDto
 * @Description: 采集平台人行dat文件生成主表
 * @Author yanya
 * @Date 2022-11-12
 * @Version V1.0
 **/
@TableName("PBC_COL_PRD_BASEINFO_DAT")
public class PbcDataZG01DatDto extends ZG04PbcProdRaiseSub {

    private String detailid;//主表的uuid

    public String getDetailid() {
        return detailid;
    }

    public void setDetailid(String detailid) {
        this.detailid = detailid;
    }
}

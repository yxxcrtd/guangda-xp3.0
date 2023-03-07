package com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.TableName;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@TableName("ZG10_PBC_COL_REPORT")
public class ZG10PbcColReportDto extends PbcColReport implements ITableRow {
    private DecimalFormat df2 = new DecimalFormat("0.00");
    /**
     * 产品品种
     */
    private  String pbcProdType;
    private  String pbcProdTypeStr;

    private  BigDecimal	h10000	 ;
    private  BigDecimal	h11000   ;
    private  BigDecimal	h11100;
    private  BigDecimal	h11200;
    private  BigDecimal	h11300   ;
    private  BigDecimal	h12000   ;
    private  BigDecimal	h12100;
    private  BigDecimal	h12200;
    private  BigDecimal	h12300   ;
    private  BigDecimal	h13000   ;
    private  BigDecimal	h13100;
    private  BigDecimal	h13200;
    private  BigDecimal	h13300   ;
    private  BigDecimal	h14000   ;
    private  BigDecimal	h14100;
    private  BigDecimal	h14200;
    private  BigDecimal	h14300   ;
    private  BigDecimal	h20000   ;
    private  BigDecimal	h21000   ;
    private  BigDecimal	h22000   ;
    private  BigDecimal	h23000   ;
    private  BigDecimal	h30000   ;
    private  BigDecimal	h31000   ;
    private  BigDecimal	h32000   ;
    private  BigDecimal	h33000   ;
    private  BigDecimal	h34000   ;
    private  BigDecimal	h40000   ;
    private  BigDecimal	h41000   ;
    private  BigDecimal	h42000   ;
    private  BigDecimal	h43000   ;
    private  BigDecimal	h44000   ;
    private  BigDecimal	h45000   ;
    private  BigDecimal	h46000   ;
    private  BigDecimal	h46100   ;
    private  BigDecimal	h47000   ;
    private  BigDecimal	h47100   ;
    private  BigDecimal	h47200   ;
    private  BigDecimal	h47300   ;
    private  BigDecimal	h47400   ;
    private  BigDecimal	h47500   ;
    private  BigDecimal	h47600   ;
    private  BigDecimal	h47700   ;
    private  BigDecimal	h47800   ;
    private  BigDecimal	h47900   ;
    private  BigDecimal	h47a00   ;
    private  BigDecimal	h48000   ;
    private  BigDecimal	h50000   ;
    private  BigDecimal	h51000   ;
    private  BigDecimal	h52000   ;
    private  BigDecimal	h53000   ;
    private  BigDecimal	h54000   ;
    private  BigDecimal	h55000   ;
    private  BigDecimal	h56000   ;
    private  BigDecimal	h57000   ;
    private  BigDecimal	h58000   ;
    private  BigDecimal	h59000   ;
    private  BigDecimal	h5a000   ;

    private String datuuid;

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    public String getPbcProdType() {
        return pbcProdType;
    }

    public void setPbcProdType(String pbcProdType) {
        this.pbcProdType = pbcProdType;
    }

    public BigDecimal getH10000() {
        return h10000;
    }

    public void setH10000(BigDecimal h10000) {
        this.h10000 = h10000;
    }

    public BigDecimal getH11000() {
        return h11000;
    }

    public void setH11000(BigDecimal h11000) {
        this.h11000 = h11000;
    }

    public BigDecimal getH11100() {
        return h11100;
    }

    public void setH11100(BigDecimal h11100) {
        this.h11100 = h11100;
    }

    public BigDecimal getH11200() {
        return h11200;
    }

    public void setH11200(BigDecimal h11200) {
        this.h11200 = h11200;
    }

    public BigDecimal getH11300() {
        return h11300;
    }

    public void setH11300(BigDecimal h11300) {
        this.h11300 = h11300;
    }

    public BigDecimal getH12000() {
        return h12000;
    }

    public void setH12000(BigDecimal h12000) {
        this.h12000 = h12000;
    }

    public BigDecimal getH12100() {
        return h12100;
    }

    public void setH12100(BigDecimal h12100) {
        this.h12100 = h12100;
    }

    public BigDecimal getH12200() {
        return h12200;
    }

    public void setH12200(BigDecimal h12200) {
        this.h12200 = h12200;
    }

    public BigDecimal getH12300() {
        return h12300;
    }

    public void setH12300(BigDecimal h12300) {
        this.h12300 = h12300;
    }

    public BigDecimal getH13000() {
        return h13000;
    }

    public void setH13000(BigDecimal h13000) {
        this.h13000 = h13000;
    }

    public BigDecimal getH13100() {
        return h13100;
    }

    public void setH13100(BigDecimal h13100) {
        this.h13100 = h13100;
    }

    public BigDecimal getH13200() {
        return h13200;
    }

    public void setH13200(BigDecimal h13200) {
        this.h13200 = h13200;
    }

    public BigDecimal getH13300() {
        return h13300;
    }

    public void setH13300(BigDecimal h13300) {
        this.h13300 = h13300;
    }

    public BigDecimal getH14000() {
        return h14000;
    }

    public void setH14000(BigDecimal h14000) {
        this.h14000 = h14000;
    }

    public BigDecimal getH14100() {
        return h14100;
    }

    public void setH14100(BigDecimal h14100) {
        this.h14100 = h14100;
    }

    public BigDecimal getH14200() {
        return h14200;
    }

    public void setH14200(BigDecimal h14200) {
        this.h14200 = h14200;
    }

    public BigDecimal getH14300() {
        return h14300;
    }

    public void setH14300(BigDecimal h14300) {
        this.h14300 = h14300;
    }

    public BigDecimal getH20000() {
        return h20000;
    }

    public void setH20000(BigDecimal h20000) {
        this.h20000 = h20000;
    }

    public BigDecimal getH21000() {
        return h21000;
    }

    public void setH21000(BigDecimal h21000) {
        this.h21000 = h21000;
    }

    public BigDecimal getH22000() {
        return h22000;
    }

    public void setH22000(BigDecimal h22000) {
        this.h22000 = h22000;
    }

    public BigDecimal getH23000() {
        return h23000;
    }

    public void setH23000(BigDecimal h23000) {
        this.h23000 = h23000;
    }

    public BigDecimal getH30000() {
        return h30000;
    }

    public void setH30000(BigDecimal h30000) {
        this.h30000 = h30000;
    }

    public BigDecimal getH31000() {
        return h31000;
    }

    public void setH31000(BigDecimal h31000) {
        this.h31000 = h31000;
    }

    public BigDecimal getH32000() {
        return h32000;
    }

    public void setH32000(BigDecimal h32000) {
        this.h32000 = h32000;
    }

    public BigDecimal getH33000() {
        return h33000;
    }

    public void setH33000(BigDecimal h33000) {
        this.h33000 = h33000;
    }

    public BigDecimal getH34000() {
        return h34000;
    }

    public void setH34000(BigDecimal h34000) {
        this.h34000 = h34000;
    }

    public BigDecimal getH40000() {
        return h40000;
    }

    public void setH40000(BigDecimal h40000) {
        this.h40000 = h40000;
    }

    public BigDecimal getH41000() {
        return h41000;
    }

    public void setH41000(BigDecimal h41000) {
        this.h41000 = h41000;
    }

    public BigDecimal getH42000() {
        return h42000;
    }

    public void setH42000(BigDecimal h42000) {
        this.h42000 = h42000;
    }

    public BigDecimal getH43000() {
        return h43000;
    }

    public void setH43000(BigDecimal h43000) {
        this.h43000 = h43000;
    }

    public BigDecimal getH44000() {
        return h44000;
    }

    public void setH44000(BigDecimal h44000) {
        this.h44000 = h44000;
    }

    public BigDecimal getH45000() {
        return h45000;
    }

    public void setH45000(BigDecimal h45000) {
        this.h45000 = h45000;
    }

    public BigDecimal getH46000() {
        return h46000;
    }

    public void setH46000(BigDecimal h46000) {
        this.h46000 = h46000;
    }

    public BigDecimal getH46100() {
        return h46100;
    }

    public void setH46100(BigDecimal h46100) {
        this.h46100 = h46100;
    }

    public BigDecimal getH47000() {
        return h47000;
    }

    public void setH47000(BigDecimal h47000) {
        this.h47000 = h47000;
    }

    public BigDecimal getH47100() {
        return h47100;
    }

    public void setH47100(BigDecimal h47100) {
        this.h47100 = h47100;
    }

    public BigDecimal getH47200() {
        return h47200;
    }

    public void setH47200(BigDecimal h47200) {
        this.h47200 = h47200;
    }

    public BigDecimal getH47300() {
        return h47300;
    }

    public void setH47300(BigDecimal h47300) {
        this.h47300 = h47300;
    }

    public BigDecimal getH47400() {
        return h47400;
    }

    public void setH47400(BigDecimal h47400) {
        this.h47400 = h47400;
    }

    public BigDecimal getH47500() {
        return h47500;
    }

    public void setH47500(BigDecimal h47500) {
        this.h47500 = h47500;
    }

    public BigDecimal getH47600() {
        return h47600;
    }

    public void setH47600(BigDecimal h47600) {
        this.h47600 = h47600;
    }

    public BigDecimal getH47700() {
        return h47700;
    }

    public void setH47700(BigDecimal h47700) {
        this.h47700 = h47700;
    }

    public BigDecimal getH47800() {
        return h47800;
    }

    public void setH47800(BigDecimal h47800) {
        this.h47800 = h47800;
    }

    public BigDecimal getH47900() {
        return h47900;
    }

    public void setH47900(BigDecimal h47900) {
        this.h47900 = h47900;
    }

    public BigDecimal getH47a00() {
        return h47a00;
    }

    public void setH47a00(BigDecimal h47a00) {
        this.h47a00 = h47a00;
    }

    public BigDecimal getH48000() {
        return h48000;
    }

    public void setH48000(BigDecimal h48000) {
        this.h48000 = h48000;
    }

    public BigDecimal getH50000() {
        return h50000;
    }

    public void setH50000(BigDecimal h50000) {
        this.h50000 = h50000;
    }

    public BigDecimal getH51000() {
        return h51000;
    }

    public void setH51000(BigDecimal h51000) {
        this.h51000 = h51000;
    }

    public BigDecimal getH52000() {
        return h52000;
    }

    public void setH52000(BigDecimal h52000) {
        this.h52000 = h52000;
    }

    public BigDecimal getH53000() {
        return h53000;
    }

    public void setH53000(BigDecimal h53000) {
        this.h53000 = h53000;
    }

    public BigDecimal getH54000() {
        return h54000;
    }

    public void setH54000(BigDecimal h54000) {
        this.h54000 = h54000;
    }

    public BigDecimal getH55000() {
        return h55000;
    }

    public void setH55000(BigDecimal h55000) {
        this.h55000 = h55000;
    }

    public BigDecimal getH56000() {
        return h56000;
    }

    public void setH56000(BigDecimal h56000) {
        this.h56000 = h56000;
    }

    public BigDecimal getH57000() {
        return h57000;
    }

    public void setH57000(BigDecimal h57000) {
        this.h57000 = h57000;
    }

    public BigDecimal getH58000() {
        return h58000;
    }

    public void setH58000(BigDecimal h58000) {
        this.h58000 = h58000;
    }

    public BigDecimal getH59000() {
        return h59000;
    }

    public void setH59000(BigDecimal h59000) {
        this.h59000 = h59000;
    }

    public BigDecimal getH5a000() {
        return h5a000;
    }

    public void setH5a000(BigDecimal h5a000) {
        this.h5a000 = h5a000;
    }

    public String geth10000Str()    {return h10000==null?"":df2.format(h10000);}
    public String geth11000Str()    {return h11000==null?"":df2.format(h11000);}
    public String geth11100Str() {return h11100==null?"":df2.format(h11100);}
    public String geth11200Str() {return h11200==null?"":df2.format(h11200);}
    public String geth11300Str()    {return h11300==null?"":df2.format(h11300);}
    public String geth12000Str()    {return h12000==null?"":df2.format(h12000);}
    public String geth12100Str() {return h12100==null?"":df2.format(h12100);}
    public String geth12200Str() {return h12200==null?"":df2.format(h12200);}
    public String geth12300Str()    {return h12300==null?"":df2.format(h12300);}
    public String geth13000Str()    {return h13000==null?"":df2.format(h13000);}
    public String geth13100Str() {return h13100==null?"":df2.format(h13100);}
    public String geth13200Str() {return h13200==null?"":df2.format(h13200);}
    public String geth13300Str()    {return h13300==null?"":df2.format(h13300);}
    public String geth14000Str()    {return h14000==null?"":df2.format(h14000);}
    public String geth14100Str() {return h14100==null?"":df2.format(h14100);}
    public String geth14200Str() {return h14200==null?"":df2.format(h14200);}
    public String geth14300Str()    {return h14300==null?"":df2.format(h14300);}
    public String geth20000Str()    {return h20000==null?"":df2.format(h20000);}
    public String geth21000Str()    {return h21000==null?"":df2.format(h21000);}
    public String geth22000Str()    {return h22000==null?"":df2.format(h22000);}
    public String geth23000Str()    {return h23000==null?"":df2.format(h23000);}
    public String geth30000Str()    {return h30000==null?"":df2.format(h30000);}
    public String geth31000Str()    {return h31000==null?"":df2.format(h31000);}
    public String geth32000Str()    {return h32000==null?"":df2.format(h32000);}
    public String geth33000Str()    {return h33000==null?"":df2.format(h33000);}
    public String geth34000Str()    {return h34000==null?"":df2.format(h34000);}
    public String geth40000Str()    {return h40000==null?"":df2.format(h40000);}
    public String geth41000Str()    {return h41000==null?"":df2.format(h41000);}
    public String geth42000Str()    {return h42000==null?"":df2.format(h42000);}
    public String geth43000Str()    {return h43000==null?"":df2.format(h43000);}
    public String geth44000Str()    {return h44000==null?"":df2.format(h44000);}
    public String geth45000Str()    {return h45000==null?"":df2.format(h45000);}
    public String geth46000Str()    {return h46000==null?"":df2.format(h46000);}
    public String geth46100Str()    {return h46100==null?"":df2.format(h46100);}
    public String geth47000Str()    {return h47000==null?"":df2.format(h47000);}
    public String geth47100Str()    {return h47100==null?"":df2.format(h47100);}
    public String geth47200Str()    {return h47200==null?"":df2.format(h47200);}
    public String geth47300Str()    {return h47300==null?"":df2.format(h47300);}
    public String geth47400Str()    {return h47400==null?"":df2.format(h47400);}
    public String geth47500Str()    {return h47500==null?"":df2.format(h47500);}
    public String geth47600Str()    {return h47600==null?"":df2.format(h47600);}
    public String geth47700Str()    {return h47700==null?"":df2.format(h47700);}
    public String geth47800Str()    {return h47800==null?"":df2.format(h47800);}
    public String geth47900Str()    {return h47900==null?"":df2.format(h47900);}
    public String geth47a00Str()    {return h47a00==null?"":df2.format(h47a00);}
    public String geth48000Str()    {return h48000==null?"":df2.format(h48000);}
    public String geth50000Str()    {return h50000==null?"":df2.format(h50000);}
    public String geth51000Str()    {return h51000==null?"":df2.format(h51000);}
    public String geth52000Str()    {return h52000==null?"":df2.format(h52000);}
    public String geth53000Str()    {return h53000==null?"":df2.format(h53000);}
    public String geth54000Str()    {return h54000==null?"":df2.format(h54000);}
    public String geth55000Str()    {return h55000==null?"":df2.format(h55000);}
    public String geth56000Str()    {return h56000==null?"":df2.format(h56000);}
    public String geth57000Str()    {return h57000==null?"":df2.format(h57000);}
    public String geth58000Str()    {return h58000==null?"":df2.format(h58000);}
    public String geth59000Str()    {return h59000==null?"":df2.format(h59000);}
    public String geth5a000Str()    {return h5a000==null?"":df2.format(h5a000);}
    public String getPbcProdTypeStr()    {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.人行_产品种类, this.getPbcProdType());
    }
    public void setPbcProdTypeStr(String pbcProdTypeStr)    {
        this.pbcProdTypeStr = pbcProdTypeStr;
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键*  发行机构代码;统计年月*/
    public String getDataKey(){
        return "发行机构代码:"+this.getIssuePartyId()+";统计年月:"+ this.getTermid();
    }
}

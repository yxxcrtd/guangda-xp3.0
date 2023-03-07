delete
from pbc_check_rule_info t
where t.rule_id in ('DB0001',
                    'DB0002',
                    'DB0003',
                    'DB0004',
                    'DB0005',
                    'DB0006',
                    'DB0007',
                    'DB0008',
                    'DB0009',
                    'DB0010',
                    'DB0011',
                    'DB0012',
                    'DB0013',
                    'DB0014',
                    'DB0015',
                    'DB0016',
                    'DB0017',
                    'DB0018',
                    'DB0019',
                    'DB0020',
                    'DB0021',
                    'DB0022',
                    'DB0023',
                    'DB0024',
                    'DB0025',
                    'DB0026',
                    'DB0027',
                    'DB0028',
                    'DB0029',
                    'DB0030',
                    'DB0031',
                    'DB0032',
                    'DB0033',
                    'DB0034',
                    'DB0035',
                    'DB0036',
                    'DB0037',
                    'DB0038',
                    'DB0039',
                    'DB0040',
                    'DB0041',
                    'DB0042',
                    'DB0043',
                    'DB0044',
                    'DB0045',
                    'DB0046',
                    'DB0047',
                    'DB0048',
                    'DB0049',
                    'DB0050',
                    'DB0051',
                    'DB0052',
                    'DB0053',
                    'DB0054',
                    'DB0055',
                    'DB0056',
                    'DB0057',
                    'DB0058',
                    'DB0059',
                    'DB0060',
                    'DB0061',
                    'DB0062',
                    'DB0063',
                    'DB0064',
                    'DB0065',
                    'DB0066',
                    'DB0067',
                    'DB0068',
                    'DB0069',
                    'DB0070',
                    'DB0071',
                    'DB0072',
                    'DB0073',
                    'DB0074',
                    'DB0075',
                    'DB0076',
                    'DB0077',
                    'DB0078',
                    'DB0079',
                    'DB0080',
                    'DB0081',
                    'DB0082',
                    'DB0083',
                    'DB0084',
                    'DB0085',
                    'DB0086',
                    'DB0087',
                    'DB0088',
                    'DB0089');


commit;

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0001','ZG0080','3','3','01','str_kit.isnotEqualssql(''0'',c1,pbcProdId,'''',buyPlace)','ZG02','币种','ccy','币种校验：资管产品初始募集信息 表中的“币种”除了BWB，其他必须于对应产品 资管产品基本信息 表中“募集资金币种”报送选项一致','币种与资管产品基本信息表中“募集资金币种”不一致！','01','12','select count(1)
from PBC_COL_PRD_BASEINFO_SUB t,pbc_col_init_prod_raising_sub i
where 1=1
     and t.pbc_prod_id = i.pbc_prod_id
     and i.ccy<>''BWB''
     and t.RAISE_CCY<>i.CCY
     and i.pbc_prod_id = ''#pbcProdId''
     and i.BUY_PLACE =  ''#buyplace''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0002','','3','3','01','str_kit.isEqualssql(c1,''0'',pbcProdId,'''',buyPlace)','ZG02','产品代码','pbcProdId','产品代码不存在！
备注：检查报送的产品代码是否属于ZG01中的“产品代码”','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0003','','3','3','01','str_kit.isnotEqualssql(c1,''0'',pbcProdId,'''',buyPlace)','ZG02','初始募集金额','initRaisingAmtsum','同币种（除BWB），全国“初始募集金额”数据≠中债募集总量登记对应币种的“认购金额”','同币种（除BWB），全国“初始募集金额”数据≠中债募集总量登记对应币种的“认购金额”！','01','12','select COUNT(1) from chb_prd_raisetot_sub t,pbc_col_init_prod_raising_sub i
 where 1=1
 AND I.PROD_ID = t.PROD_ID
 AND i.PBC_PROD_ID =''#pbcProdId''
 AND I.INIT_RAISING_AMTSUM<>nvl(t.TOTAL_AMT,0)
 AND I.CCY<>''BWB''
 and i.BUY_PLACE =  ''#buyplace''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0004','','3','3','01','str_kit.isnotEqualssql(c1,''0'',pbcProdId,'''',buyPlace)','ZG02','初始募集金额','initRaisingAmtsum','币种为BWB，全国“初始募集金额”数据≠中债募集总量登记“实际募集金额(元)”','币种为BWB，全国“初始募集金额”数据≠中债募集总量登记“实际募集金额(元)”！','01','12',' select COUNT(1) from chb_prd_raisetot_sub t,pbc_col_init_prod_raising_sub i
 where 1=1
 AND I.PROD_ID = t.PROD_ID
 AND i.PBC_PROD_ID =''#pbcProdId''
 AND I.INIT_RAISING_AMTSUM<>nvl(t.TOTAL_AMT,0)
 AND I.CCY=''BWB''
 and i.BUY_PLACE =  ''#buyplace''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0005','','3','3','01','str_kit.isnotEqualssql(c1,''0'',pbcProdId,'''',buyPlace)','ZG02','初始募集份额','initRaisingQty','币种为BWB，全国“初始募集份额”数据≠中债募集总量登记“募集总份额”','币种为BWB，全国“初始募集份额”数据≠中债募集总量登记“募集总份额”！','01','12','  select COUNT(1) from chb_prd_raisetot_sub t,pbc_col_init_prod_raising_sub i
 where 1=1
 AND I.PROD_ID = t.PROD_ID
 AND i.PBC_PROD_ID =''#pbcProdId''
 AND I.init_Raising_Qty<>nvl(t.RAISE_QTY,0)
 AND I.CCY=''BWB''
 and i.BUY_PLACE =  ''#buyplace''

',' ');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0006', '3', '01', 'str_kit.judgeValueByCond7sql(''1'',c1,''1'',tar1,pbcProdId,'''','''')', 'ZG02', '客户类型', 'customerType1', '12', '产品基本信息中管理方式为单独管理，产品起始募集信息中客户类型不能多于1类', '产品基本信息中管理方式为单独管理，产品起始募集信息中客户类型不能多于1类！', '01', 'ZG0096', '2', 'SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and MANAGE_TYPE =''2''', 'select count(*) from (
SELECT count(1)
FROM pbc_col_init_prod_raising_sub
WHERE PBC_PROD_ID = ''#pbcProdId''
group  by CUSTOMER_TYPE1) t', null, null, null, null, null);
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0007','ZG1604','3','3','01','str_kit.isnotEqualssql(''0'',c1,pbcProdId,'''','''')','ZG03','币种','ccy','币种校验：资管产品终止信息 表中的“币种”除了BWB，其他必须于对应产品 资管产品基本信息 表中“兑付收益币种”报送选项一致','币种与资管产品基本信息表中“兑付收益币种”不一致！','01','12','select count(1)
from PBC_COL_PRD_BASEINFO_SUB t,pbc_col_prd_baseinfo_end_sub i
where 1=1
     and t.pbc_prod_id = i.pbc_prod_id
     and i.ccy<>''BWB''
     and t.CASH_PROF_CCY<>i.CCY
     and i.pbc_prod_id = ''#pbcProdId'' ',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0008','ZG0100','3','2','05','str_kit.isEqualssql(c1,''0'',pbcProdId,'''','''')','ZG03','产品代码','pbcProdId','产品代码不存在！
备注：检查报送的产品代码是否属于ZG01中的“产品代码”','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0009','ZG0118','3','2','01','date_kit.dateCompareSmallsql(c1,mdate,pbcProdId,'''','''')','ZG03','产品实际终止日期','mdate','产品实际终止日期>=产品初始日期','产品实际终止日期不得早于等于产品初始日期！','01','12','select distinct vdate from PBC_COL_PRD_BASEINFO_SUB t where t.pbc_prod_id = ''#pbcProdId''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0010','ZG0117','3','3','01','date_kit.dateCompareTosql(mdate,c1,pbcProdId,'''','''')','ZG03','产品实际终止日期','mdate','封闭式产品客户赎回权标识为无且发行机构提前终止权标识为无，产品实际终止日期<产品预计终止日期！
备注：资管产品基本信息表中“运行方式”为“3：封闭式净值型”的产品为封闭式产品','封闭式产品客户赎回权标识为无且发行机构提前终止权标识为无，产品不可提前终止！','01','12','select MDATE from ( select (CASE WHEN CUS_REDEEM_FLAG=''1'' OR TERM_RIGHT_FLAG=''1'' THEN NULL ELSE MDATE END ) AS MDATE from pbc_col_prd_baseinfo_sub t where   OPERATE_MODE = ''3'' and  t.pbc_prod_id = ''#pbcProdId'' order by  UPDATE_TIME DESC ) WHERE rownum = 1 ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0011','ZG1606','3','2','04','date_kit.dateCompareTosql(mdate,c1,pbcProdId,'''','''')','ZG03','产品实际终止日期','mdate','当信息类型为变更资管产品基本信息时，产品实际终止日期>最新产品变更日期','当信息类型为变更资管产品基本信息时，产品实际终止日期不应早于最新产品变更日期!','01','12','select distinct cdate from PBC_COL_PRD_BASEINFO_SUB t where t.pbc_prod_id = ''#pbcProdId'' and t.INFO_TYPE=''2''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0012','','3','3','01','str_kit.isnotEqualssql(''0'',c1,pbcProdId,raisingDate,buyPlace)','ZG04','币种','ccy','币种校验：资管产品存续期募集信息 表中当“当期申购金额”不为0时“币种”除了BWB，其他必须于对应产品 资管产品基本信息 表中“募集资金币种”报送选项一致','币种与资管产品基本信息表中“募集资金币种”不一致！','01','12','select count(1)
from PBC_COL_PRD_BASEINFO_SUB t,pbc_col_prod_raising_sub i
where 1=1
     and t.pbc_prod_id = i.pbc_prod_id
     and i.ccy<>''BWB''
     and t.RAISE_CCY<>i.CCY
     and i.pbc_prod_id = ''#pbcProdId''
     and i.RAISING_AMTSUM<>0
     and i.RAISING_DATE =  ''#termid''
     and i.BUY_PLACE =  ''#buyplace''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0013','','3','3','01','str_kit.isnotEqualssql(''0'',c1,pbcProdId,termid,buyPlace)','ZG04','币种','ccy','币种校验：资管产品存续期募集信息 表中当“当期兑付/赎回金额”不为0时“币种”除了BWB，其他必须于对应产品 资管产品基本信息 表中“兑付本金币种”报送选项一致','币种与资管产品基本信息表中“兑付本金币种”不一致！','01','12','select count(1) from PBC_COL_PRD_BASEINFO_SUB t,pbc_col_prod_raising_sub i where 1=1
     and t.pbc_prod_id = i.pbc_prod_id
     and i.ccy<>''BWB''
     and t.CASH_PRIN_CCY<>i.CCY
     and i.pbc_prod_id = ''#pbcProdId''
     and i.REDAMT<>0   and i.RAISING_DATE =  ''#termid''
     and i.BUY_PLACE =  ''#buyplace''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0014','ZG0127','3','2','01','str_kit.isEqualssql(c1,''0'',pbcProdId,'''',buyPlace)','ZG04','产品代码','pbcProdId','产品代码不存在！
备注：检查报送的产品代码是否属于ZG01中的“产品代码”','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0015','ZG0170','3','2','04','big_decimal_kit.sqlGreaterThanEqualsql(raisingAmtsum,c1,pbcProdId,'''',buyPlace)','ZG04','当期申购金额','raisingAmtsum','当月成立产品的当期申购金额>=初始募集金额','当月成立产品的当期申购金额不能小于初始募集金额！','01','12','select  sum(nvl(INIT_RAISING_AMTSUM,0)) from pbc_col_init_prod_raising_sub t where t.CCY=''BWB'' AND  t.pbc_prod_id =''#pbcProdId''
    and t.BUY_PLACE =  ''#buyplace''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0016','ZG0171','3','2','04','big_decimal_kit.sqlGreaterThanEqualsql(raisingQty,c1,pbcProdId,'''',buyPlace)','ZG04','当期申购份额','raisingQty','当月成立产品的当期申购份额>=初始募集份额','当月成立产品的当期申购份额不能小于初始募集份额！','01','12','select  sum(nvl(INIT_RAISING_QTY,0)) from pbc_col_init_prod_raising_sub t where t.CCY=''BWB'' AND  t.pbc_prod_id = ''#pbcProdId''
    and t.BUY_PLACE =  ''#buyplace''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0017','ZG0180','3','3','数值比较校验','big_decimal_kit.compareYieldNotABSsqlOne(c1,tar1,''0.5'',pbcProdId,raisingDate,buyPlace)','ZG04','期末产品金额跨期校验','','详细公式：
（当期期末产品金额-（上期期末产品金额+当期申购金额-当期兑付金额））/(上期期末产品金额+当期申购金额-当期兑付金额)绝对值*100% 大于5%

或是
1、(当期期末产品金额-当前计算出来期末产品金额值）/当前计算出来期末产品金额值)绝对值*100% > 5%!
2、当前计算出来期末产品金额值 =  (上期期末产品金额+当期申购金额-当期兑付金额)','产品金额比对不符合校验公式（当期期末产品金额≈上期期末产品金额+当期申购金额-当期兑付金额）！','01','12','select nvl(sum(nvl(ENDAMTSUM,0)),0)
from PBC_COL_PROD_RAISING_SUB
where RAISING_DATE = str_to_date(date_format(''#termid'',''%Y%m'')-1,''%Y%m'')
  and PBC_PROD_ID = ''#pbcProdId''
  and BUY_PLACE =''#buyplace''','select concat(sum(nvl(ENDAMTSUM,0)),'';'',sum(nvl(RAISING_AMTSUM,0)),'';'',sum(nvl(REDAMT,0)))
from PBC_COL_PROD_RAISING_SUB
where RAISING_DATE = ''#termid''
  and PBC_PROD_ID =''#pbcProdId''
  and BUY_PLACE = ''#buyplace'' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0018','ZG0179','3','3','04','big_decimal_kit.compareCheckSql(c1,raisingQty,redqty,endqty,pbcProdId,termid,buyPlace)','ZG04','期末产品份额跨期校验','','上期末份额+当期申购份额-当期兑付/赎回份额-期末产品份额=0','上期末份额+当期申购份额-当期兑付/赎回份额-期末产品份额≠0！','01','12','select nvl(ENDQTY,0) from pbc_col_prod_raising_sub where RAISING_DATE= TO_CHAR(ADD_MONTHS(to_date(''#termid'',''YYYYMM''), -1),''YYYYMM'') and BUY_PLACE = ''#buyplace'' and pbc_prod_id = ''#pbcProdId''',' ');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0019', '3', '04', 'str_kit.isnotEqualssql(tar1,c1,pbcProdId,termid,buyPlace)', 'ZG04', '期末产品金额', 'endamtsum', '12', '所有存续产品币种为BWB全国“期末产品金额”数据合计=中债非保本理财业务月度统计表 1.3合计E列值', '所有存续产品币种为BWB全国“期末产品金额”数据合计≠ 中债非保本理财业务月度统计表 1.3合计E列值！', '01', '', '3', 'select sum(ENDAMTSUM)
from pbc_col_prod_raising_sub
where RAISING_DATE = ''#termid'' and CCY = ''BWB''
 and BUY_PLACE =  ''#buyplace''', 'select SUM(BALANCE)
from chb_month_report_sub_detail
where REPORTTYPE = ''PROD''
  AND PROPERTYCODE IN (''SMLCCP'', ''GMLCCP'')
  AND TERMID = ''#termid''', null, null, null, null, null);
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0020','ZG0128','3','2','01','str_kit.isEqualssql(''1'',c1,pbcProdId,raisingDate,buyPlace)','ZG04','产品代码','pbcProdId','当期在运行的产品未报送当月存续期募集数据！','当期在运行的产品未报送当月存续期募集数据！','01','31','select  count(1) from
     pbc_col_prod_raising_sub b,
     pbc_col_prd_baseinfo_sub c
     where 1=1
    and c.PBC_PROD_ID = b.PBC_PROD_ID and b.pbc_prod_id = ''#pbcProdId'' and b.RAISING_DATE = ''#termid''
     and c.SEND_STATUS =''1''
    and b.BUY_PLACE =  ''#buyplace''',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0021','ZG0129','3','2','01','str_kit.isEqualssql(''1'',c1,pbcProdId,raisingDate,buyPlace)','ZG04','产品代码','pbcProdId','当期终止的产品未报送当月存续期募集数据！','当期终止的产品未报送当月存续期募集数据！','01','31','select  count(1) from
     pbc_col_prod_raising_sub b,
    pbc_col_prd_baseinfo_end_sub c
     where 1=1
    and c.PBC_PROD_ID = b.PBC_PROD_ID and a.pbc_prod_id = ''#pbcProdId'' and b.RAISING_DATE = ''#termid''
     and c.SEND_STATUS = ''1''
     and b.BUY_PLACE =  ''#buyplace''
',' ');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DB0022', '3', '01', 'str_kit.isEqualssql(''1'',c1,pbcProdId,termId,buyPlace)', 'ZG04', '产品代码', 'pbcProdId', '12', '产品终止后不能报送当月存续期募集数据！', '产品终止后不能报送当月存续期募集数据！', '01', 'ZG0130', '2', 'select case when COUNTS > 1 then ''1'' else ''0'' end from ( select  count(1) as COUNTS  from
     pbc_col_prod_raising_sub b,
    pbc_col_prd_baseinfo_end_sub c
     where 1=1
     and c.PBC_PROD_ID = b.PBC_PROD_ID and b.pbc_prod_id = ''#pbcProdId'' and b.RAISING_DATE = ''#termid''
 and to_char(c.MDATE,''yyyyMM'') <''#termid''
and b.BUY_PLACE =  ''#buyplace'' )' , '', null, null, null, null, null);
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0023','ZG0131','3','3','01','str_kit.beEmptyAll(redamt,redamtCny,redqty)','ZG04','当期兑付/赎回金额当期兑付/赎回金额折人民币当期兑付/赎回份额','','封闭式产品客户赎回权标识为无非终止月份，当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额均应为空！','封闭式产品客户赎回权标识为无非终止月份不应报送当期兑付/赎回数据！','01',' ',' ',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0024','ZG0132','3','3','01','str_kit.beEmptyAll(raisingAmtsum,raisingAmtsumCny,raisingQty)','ZG04','当期申购金额 当期申购金额折人民币 当期申购份额','','封闭式产品非起始月份当期申购金额、当期申购金额折人民币、当期申购份额均应为空','封闭式产品非起始月份报送了当期申购数据，请核实是否符合实际情况！','01',' ',' ',' ');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DB0025', '3', '01', 'unique_kit.notMultiInList4bwb(pbcProdId,endamtsum,ccy,buyPlace,mapList,str_1,str_2,str_3,str_4)', 'ZG04', '期末产品金额', 'endamtsum', '21', '未终止产品，期末产品余额≠0', '期末产品余额为零，但产品尚未终止！', '01', 'ZG0133', '3', 'pbcProdId', 'endamtsum','ccy', 'buyPlace', null, null);


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0026','ZG0134','3','2','01','str_kit.isEqualssql(''0'',c1,pbcProdId,raisingDate,buyPlace)','ZG04','产品代码','pbcProdId','过了预期终止日期尚未终止的资管产品没有报送募集信息,可能漏报产品终止信息','过了预期终止日期尚未终止的资管产品没有报送募集信息,可能漏报产品终止信息！','01','30','',' ');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0027','ZG0140','3','1','01','str_kit.judgeValueByCond3sql(''1'',c1,''0'',tar1,pbcProdId,termid,buyPlace)','ZG04','净值型产品期末净值
净值型产品期末净值折人民币
净值型产品期末累计净值
净值型产品期末累计净值折人民币','','存续或当月终止的净值型产品，净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币 不应同时为空，至少应有1条报送数据！','净值型产品未报送期末净值，或累计净值！','01','12','select count(1)
from pbc_col_prd_baseinfo_sub b
where b.PBC_PROD_ID =  ''#pbcProdId''
  and b.OPERATE_MODE in (''3'', ''1'')','select count(1)
from pbc_col_prod_raising_sub a,
     pbc_col_prd_baseinfo_sub b
where a.PBC_PROD_ID = b.PBC_PROD_ID
     and a.RAISING_DATE = ''#termid''
     and a.PBC_PROD_ID = ''#pbcProdId''
     and b.OPERATE_MODE in (''3'',''1'')
     and a.BUY_PLACE is null
     and( (PROD_VALUE is not null and PROD_VALUE_CNY is not null) OR
      (UNIT_VALUE_TOTAL is not null and UNIT_VALUE_TOTAL_CNY is not null))');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0028','ZG0142','3','1','01','str_kit.judgeValueByCond4sql(''1'',c1,''0'',tar1,pbcProdId,termid,buyPlace)','ZG04','产品期末业绩表现','prodEndYield','存续或当月终止的现金管理类理财产品需报送1条期末净值信息。备注：产品基本信息中货基或现金管理类产品标识为是的产品为现金管理类产品','现金管理类理财产品未报送产品期末业绩表现！','01','12','select count(1)
from pbc_col_prd_baseinfo_sub b
where b.PBC_PROD_ID =  ''#pbcProdId''
  and b.CASH_MANA_CATE in (''1'')','select count(1)
from pbc_col_prod_raising_sub a,
     pbc_col_prd_baseinfo_sub b
where a.PBC_PROD_ID = b.PBC_PROD_ID
  and a.RAISING_DATE = ''#termid''
  and a.PBC_PROD_ID =  ''#pbcProdId''
  and b.CASH_MANA_CATE in (''1'')
  and a.BUY_PLACE is null
  and a.PROD_END_YIELD is not null');


INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0029', '3', '01', 'str_kit.isEqualssql(''1'',c1,pbcProdId,termId,buyPlace)', 'ZG04', '期末产品金额
期末产品金额折人民币', '', '12', '产品终止当月期末产品金额、期末产品金额折人民币应等于零', '产品终止当月期末产品金额不等于零！', '01', 'ZG0183', '2', '
select  case  when count(1)>0 then 1 else 0 end
from pbc_col_prd_baseinfo_end_sub a,
     pbc_col_prod_raising_sub b
where a.PBC_PROD_ID = b.PBC_PROD_ID
  and b.ENDAMTSUM <> 0 and b.ENDAMTSUM_CNY <> 0
   and b.pbc_prod_id = ''#pbcProdId''
        and date_format(a.mdate,''%Y%m'') <= b.RAISING_DATE
and b.BUY_PLACE =  ''#buyplace''', '', null, null, null, null, null);
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0030','ZG0184','3','3','04','big_decimal_kit.notEqualsZeroFour(pbcProdId,prodValue,prodValueCny,unitValueTotal,unitValueTotalCny,str_m)','ZG04','净值型产品期末净值','','净值型产品，净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币均不应为0。','净值型产品报送期末净值一般不为0！','01','16','SELECT a.OPERATE_MODE FROM pbc_col_prd_baseinfo_sub a LEFT JOIN( SELECT inside_prod_id, pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE a.PBC_PROD_ID = ''#pbcProdId''  or t2.pbc_code =  ''#pbcProdId''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0031','ZG1599','3','3','02','big_decimal_kit.compareYieldNotABSsql31(prodValue,c1,''0.2'',pbcProdId,raisingDate,ccy)','ZG04','净值型产品期末净值','prodValue','净值型产品同币种（（本期）产品期末净值-（上期）产品期末净值）)/（上期）产品期末净值）绝对值 > 20%','净值型产品报送期末净值跨期变动过大！','01','12','select nvl(sum(nvl(prod_Value,0)),0) from pbc_col_prod_raising_sub where RAISING_DATE= date_format(DATE_SUB(str_to_date(CONCAT(''#termid'',''01''),''%Y%m%d''),interval 1 month),''%Y%m'')
and pbc_prod_id = ''#pbcProdId'' and  CCY=''#ccy'' ','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0032','ZG0173','3','3','04','str_kit.isEqualssql(''0'',c1,pbcProdId,raisingDate,buyPlace)','ZG04','期末产品金额','endamtsum','货基或现金管理类产品为“是”的产品，期末产品金额应等于期末产品份额','货币基金和现金管理类标识为是，但是产品期末金额份额不相等！','01','12','select ENDAMTSUM<> ENDQTY  from pbc_col_prod_raising_sub   where  RAISING_DATE = ''#termid'' and PBC_PROD_ID in(
 select PBC_PROD_ID from  pbc_col_prd_baseinfo_sub   where CASH_MANA_CATE in(''1'')  and  PBC_PROD_ID=''#pbcProdId'' )and BUY_PLACE =  ''#buyplace''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0033','ZG0181','3','3','04','big_decimal_kit.beEqualsql2(endamtsum,c1,pbcProdId,raisingDate,buyPlace,ccy,customerType1)','ZG04','期末产品金额','endamtsum','净值型产品期末产品金额=期末产品份额*净值型产品期末净值','净值型产品期末金额不符合校验公式（期末产品金额=净值型产品期末净值*份额）！','01','12','
select ENDQTY*(
    select DISTINCT  PROD_VALUE
    from pbc_col_prod_raising_sub
    where  RAISING_DATE = ''#termid''
      and  PBC_PROD_ID=''#pbcProdId''
      and CCY=''#ccy''
     AND PROD_VALUE IS NOT NULL
    ) from pbc_col_prod_raising_sub
where  RAISING_DATE = ''#termid''
  and PBC_PROD_ID in(
    select PBC_PROD_ID from  pbc_col_prd_baseinfo_sub
    where OPERATE_MODE in(''1'',''3'')  and  PBC_PROD_ID=''#pbcProdId'' )
  and BUY_PLACE =''#buyplace''
  and CCY=''#ccy''
 and CUSTOMER_TYPE1 = ''#customerType1''
',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0034','ZG0188','3','2','03','str_kit.judgeValueByCondsql34(c1,''customerType1'',pbcProdId,mapList,''pbcProdId'')','ZG04','客户类型','customerType1','产品基本信息中管理方式为单独管理的产品，产品存续期募集信息中客户类型不应多于1类','产品基本信息中管理方式为单独管理，产品募集信息中客户类型不应多于1类！','01','12',' select count(1) from  pbc_col_prd_baseinfo_sub   where MANAGE_TYPE = ''2'' and  PBC_PROD_ID=''#pbcProdId'' ','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0035','ZG0189','3','2','03','str_kit.isnotEqualssql(''0'',c1,pbcProdId,termid,buyPlace)','ZG04','产品期末业绩表现','prodEndYield','货基或现金管理类产品为“否”的净值型产品不应报送产品期末业绩表现','货基或现金管理类产品为“否”的净值型产品不应报送产品期末业绩表现！','01','12','select count(1) from pbc_col_prod_raising_sub where PROD_END_YIELD is not null and PBC_PROD_ID in(select PBC_PROD_ID from  pbc_col_prd_baseinfo_sub  where CASH_MANA_CATE = ''2'' and  PBC_PROD_ID=''#pbcProdId'' ) and  RAISING_DATE = ''#termid''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0036','ZG1179','3','3','06','str_kit.isEqualsStrsql(c1,customerType1,pbcProdId,raisingDate,buyPlace)','ZG04','客户类型','customerType1','封闭式产品于产品成立日所在报数期，“资管产品初始募集信息-客户类型”和“资管产品存续期募集信息-客户类型”报送值应该一致','封闭式产品于产品成立日所在报数期，“资管产品初始募集信息-客户类型”和“资管产品存续期募集信息-客户类型”应该一致！','01','12','SELECT CUSTOMER_TYPE1 FROM pbc_col_init_prod_raising_sub WHERE termid = ''#termid''
and  PBC_PROD_ID = ''#pbcProdId'' and a.BUY_PLACE =  ''#buyplace''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0037','ZG0191','3','2','01','str_kit.isEqualssql(c1,''0'',pbcProdId,'''','''')','ZG05','产品代码','pbcProdId','产品代码不存在！','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0038','ZG0192','3','2','01','str_kit.isEqualssql(''0'',c1,pbcProdId,raisingDate,'''')','ZG05','产品代码','pbcProdId','过了预期终止日期、尚未终止的资管产品、没有报送资产负债信息,可能漏报产品终止信息！','过了预期终止日期尚未终止的资管产品没有报送资产负债信息,可能漏报产品终止信息！','01','30','',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0039','ZG0193','3','3','01','str_kit.isEqualssql(''0'',c1,pbcProdId,raisingDate,'''')','ZG05','产品代码','pbcProdId','过了预期终止日期3个工作日后、尚未终止的资管产品没有报送资产负债信息,可能漏报产品终止信息！','过了预期终止日期3个工作日后尚未终止的资管产品没有报送资产负债信息,可能漏报产品终止信息！','01','30','',' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
                                                                                                                                                                                                                                                    'DB0040','ZG0194','3','3','01','big_decimal_kit.notBeEndOfSql(str_m,pbcProdId,a0000)','ZG05','资产合计','','资产合计为零，但产品尚未终止！','资产合计为零，但产品尚未终止！','01','16','select count(1) from CHB_PRD_DEAL where PROD_ID in (select PROD_ID from pbc_col_prd_baseinfo_sub where PBC_PROD_ID=''#pbcProdId'') and TRADE_TYPE =''16''',' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0041','ZG0195','3','2','01','str_kit.isEqualssql(c1,pbcProdId,pbcProdId,termid,'''')','ZG05','产品代码','pbcProdId','资管产品报送了终止信息后仍报送资产负债信息！','资管产品报送了终止信息后仍报送资产负债信息！','01','12','SELECT distinct PBC_PROD_ID FROM pbc_col_prd_baseinfo_end_sub WHERE  PBC_PROD_ID = ''#pbcProdId'' and to_char(MDATE,''yyyyMM'') <= ''#termid''  ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0042','ZG0654','3','2','04','big_decimal_kit.beEqualsql42(c1,a6100,pbcProdId,termId,statusType,''1'')','ZG05','A6100_贷款收益权','a6100','同币种（数据类型=1时）A6100_贷款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6100_贷款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6100''  and CURRENCY_EPERIODCCY=''CNY''  and termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0043','ZG0655','3','2','04','big_decimal_kit.beEqualsql42(c1,a6200,pbcProdId,termId,statusType,''1'')','ZG05','A6200_债务证券收益权','a6200','同币种（数据类型=1时）A6200_债务证券收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6200_债务证券收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6200''  and CURRENCY_EPERIODCCY=''CNY''  and   termid = ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0044','ZG0656','3','2','04','big_decimal_kit.beEqualsql42(c1,a6300,pbcProdId,termId,statusType,''1'')','ZG05','A6300_应收账款收益权','a6300','同币种（数据类型=1时）A6300_应收账款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6300_应收账款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6300''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0045','ZG0657','3','2','04','big_decimal_kit.beEqualsql42(c1,a6400,pbcProdId,termId,statusType,''1'')','ZG05','A6400_其他债权收益权','a6400','同币种（数据类型=1时）A6400_其他债权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6400_其他债权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6400''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0046','ZG0658','3','2','04','big_decimal_kit.beEqualsql42(c1,a6500,pbcProdId,termId,statusType,''1'')','ZG05','A6500_股票收益权','a6500','同币种（数据类型=1时）A6500_股票收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6500_股票收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6500''  and CURRENCY_EPERIODCCY=''CNY''  and    termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0047','ZG0659','3','2','04','big_decimal_kit.beEqualsql42(c1,a6600,pbcProdId,termId,statusType,''1'')','ZG05','A6600_其他股权收益权','a6600','同币种（数据类型=1时）A6600_其他股权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6600_其他股权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6600''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0048','ZG0660','3','2','04','big_decimal_kit.beEqualsql42(c1,a6700,pbcProdId,termId,statusType,''1'')','ZG05','A6700_物权资产收益权','a6700','同币种（数据类型=1时）A6700_物权资产收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6700_物权资产收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6700''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0049','ZG0654','3','2','04','big_decimal_kit.beEqualsql42(c1,a6100,pbcProdId,termId,statusType,''1'')','ZG05','A6100_贷款收益权','a6100','币种BWBA6100_贷款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6100_贷款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6100''  and CURRENCY_EPERIODCCY=''CNY''  and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0050','ZG0655','3','2','04','big_decimal_kit.beEqualsql42(c1,a6200,pbcProdId,termId,statusType,''1'')','ZG05','A6200_债务证券收益权','a6200','币种BWBA6200_债务证券收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6200_债务证券收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub   where  ASSETS_LIABILITIES=''A6200''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0051','ZG0656','3','2','04','big_decimal_kit.beEqualsql42(c1,a6300,pbcProdId,termId,statusType,''1'')','ZG05','A6300_应收账款收益权','a6300','币种BWBA6300_应收账款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6300_应收账款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6300''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0052','ZG0657','3','2','04','big_decimal_kit.beEqualsql42(c1,a6400,pbcProdId,termId,statusType,''1'')','ZG05','A6400_其他债权收益权','a6400','币种BWBA6400_其他债权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6400_其他债权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6400''  and CURRENCY_EPERIODCCY=''CNY'' and termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0053','ZG0658','3','2','04','big_decimal_kit.beEqualsql42(c1,a6500,pbcProdId,termId,statusType,''1'')','ZG05','A6500_股票收益权','a6500','币种BWBA6500_股票收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6500_股票收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6500''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0054','ZG0659','3','2','04','big_decimal_kit.beEqualsql42(c1,a6600,pbcProdId,termId,statusType,''1'')','ZG05','A6600_其他股权收益权','a6600','币种BWBA6600_其他股权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6600_其他股权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6600''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0055','ZG0660','3','2','04','big_decimal_kit.beEqualsql42(c1,a6700,pbcProdId,termId,statusType,''1'')','ZG05','A6700_物权资产收益权','a6700','币种BWBA6700_物权资产收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6700_物权资产收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6700''  and CURRENCY_EPERIODCCY=''CNY''  and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0056','ZG0661','3','2','04','big_decimal_kit.beEqualsqlDb56(c1,a5100,pbcProdId,termid,b1100,ccy,''CNY'')','ZG05','除回购和拆借外贷款','','同币种（除BWB）（A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计=对应产品“除回购和拆借外贷款明细信息”报送的对应贷款余额币种的贷款余额合计值','同币种（除BWB）（A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计≠对应产品“除回购和拆借外贷款明细信息”报送的对应贷款余额币种的贷款余额合计值！','01','12','SELECT nvl(sum(nvl(CONTRACT_AMOUNT_CNY,0)),0) FROM pbc_col_loan_detail_sub WHERE termid = ''#termid''
 and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0057','ZG0661','3','2','04','big_decimal_kit.beEqualsqlDb56(c1,a5100,pbcProdId,termid,b1100,ccy,''BWB'')','ZG05','除回购和拆借外贷款','','币种为BWB （A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计=对应产品“除回购和拆借外贷款明细信息”报送的贷款余额折人民币合计值','币种BWB （A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计≠对应产品“除回购和拆借外贷款明细信息”报送的贷款余额折人民币合计值！','01','12','SELECT nvl(sum(nvl(CONTRACT_AMOUNT_CNY,0)),0) FROM pbc_col_loan_detail_sub WHERE termid = ''#termid''
 and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0058','','3','3','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”合计值=表ZG11企业债券分行业、企业规模情况信息“c00000_企业债券余额总计”值','所有产品币种为BWB“A4500_企业债券”合计值≠表ZG11企业债券分行业、企业规模情况信息“c00000_企业债券余额总计”值','01','12','SELECT nvl(sum(nvl(c00000,0)),0) FROM zg11_pbc_col_report WHERE termid = ''#termid'' '
,'SELECT  sum(nvl(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0059','ZG1170','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','G000A_资产方-债务工具-总计','g000a','所有产品币种为BWB （“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A8000_金融衍生品”+“A9000_应收账款”）值汇总=表ZG09资产负债剩余期限信息“G000A_资产方-债务工具-总计”值','所有产品币种为BWB （“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A8000_金融衍生品”+“A9000_应收账款”）值汇总≠表ZG09资产负债剩余期限信息“G000A_资产方-债务工具-总计”值','01','12','SELECT sum(nvl(G000A,0)) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ','SELECT sum(nvl(A1000,0))+sum(nvl(A2000,0))+sum(nvl(A3000,0))+sum(nvl(A4000,0))+sum(nvl(A5000,0))+sum(nvl(A6000,0))+sum(nvl(A8000,0))+sum(nvl(A9000,0))  FROM zg05_pbc_col_report WHERE termid = ''termid''
AND  ccy = ''BWB''');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0060', '3', '01', 'big_decimal_kit.beEqualsqlBWB(c1,tar1,pbcProdId,termid,'''',ccy)', 'ZG05', 'G000B_资产方-股权-总计', 'g000b', '12', '所有产品币种为BWB（“A7100_股票”+“A7300_其他股权”+“A5280_境外”）值汇总=表ZG09资产负债剩余期限信息“G000B_资产方-股权-总计”值', '所有产品币种为BWB（“A7100_股票”+“A7300_其他股权”+“A5280_境外”）值汇总≠表ZG09资产负债剩余期限信息“G000B_资产方-股权-总计”值', '01', 'ZG1171', '1', 'SELECT nvl(sum(nvl(G000B,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ', 'SELECT sum(nvl(A7100, 0)) +
           sum(nvl(A7300, 0)) + sum(nvl(A5280, 0)) FROM zg05_pbc_col_report WHERE termid = ''#termid'' AND ccy = ''BWB''', null, null, null, null, null);
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0061','ZG1172','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','A7200_特定目的载体份额','a7200','所有产品币种为BWB“A7200_特定目的载体份额”值汇总=表ZG09资产负债剩余期限信息“G000C_资产方-特定目的载体份额-总计”值','所有产品币种为BWB“A7200_特定目的载体份额”值汇总≠表ZG09资产负债剩余期限信息“G000C_资产方-特定目的载体份额-总计”值','01','12','SELECT nvl(sum(nvl(G000C,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A7200,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0062','ZG1173','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','负债方债务工具','','所有产品币种为BWB“B0000_负债合计”值汇总=表ZG09资产负债剩余期限信息“G000D_负债及权益方-债务工具-总计”值','所有产品币种为BWB“B0000_负债合计”值汇总≠表ZG09资产负债剩余期限信息“G000D_负债及权益方-债务工具-总计”值','01','12','SELECT nvl(sum(nvl(G000D,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(B0000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0063','ZG1174','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','实收本金','','所有产品币种为BWB“C1000_实收本金”值汇总=表ZG09资产负债剩余期限信息“G000E_负债及权益方-实收本金-总计”值','所有产品币种为BWB“C1000_实收本金”值汇总≠表ZG09资产负债剩余期限信息“G000E_负债及权益方-实收本金-总计”值','01','12','SELECT nvl(sum(nvl(G000E,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(C1000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0064','ZG1430','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','政策性金融债券','','所有产品币种为BWB“A4300_政策性金融债券”值汇总=表ZG10债券等资产配置情况信息“h11000_其中：政策性金融债券”','所有产品币种为BWB“A4300_政策性金融债券”值汇总≠表ZG10债券等资产配置情况信息“h11000_其中：政策性金融债券”','01','12','SELECT nvl(sum(nvl(h11000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A4300,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0065','ZG1431','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','其他金融债券','','所有产品币种为BWB“A4400_其他金融债券”值汇总=表ZG10债券等资产配置情况信息“h12000_其他金融债券”','所有产品币种为BWB“A4400_其他金融债券”值汇总≠表ZG10债券等资产配置情况信息“h12000_其他金融债券”','01','12','SELECT nvl(sum(nvl(h12000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A4400,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0066','ZG1432','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”值汇总=表ZG10债券等资产配置情况信息“h13000_企业债券”','所有产品币种为BWB“A4500_企业债券”值汇总≠表ZG10债券等资产配置情况信息“h13000_企业债券”','01','12','SELECT nvl(sum(nvl(h13000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
 'SELECT  sum(nvl(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0067','ZG1433','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','资产支持证券','','所有产品币种为BWB“A4700_资产支持证券”值汇总=表ZG10债券等资产配置情况信息“h14000_资产支持证券”','所有产品币种为BWB“A4700_资产支持证券”值汇总≠表ZG10债券等资产配置情况信息“h14000_资产支持证券”','01','12','SELECT nvl(sum(nvl(h14000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A4700,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0068','ZG1434','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”值汇总>=表ZG10债券等资产配置情况信息（“h21000_其中：企业债”+“h22000_公司债”+“h23000_非金融企业债务融资工具”）','所有产品币种为BWB“A4500_企业债券”值汇总<表ZG10债券等资产配置情况信息（“h21000_其中：企业债”+“h22000_公司债”+“h23000_非金融企业债务融资工具”）','01','12','SELECT sum(nvl(h21000,0)+nvl(h22000,0)+nvl(h23000,0)) FROM zg10_pbc_col_report WHERE termid = ''#termid''',
'SELECT  sum(nvl(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0069','ZG1437','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5200_回购和拆借','a5200','所有产品币种为BWB“A5200_回购和拆借”值汇总>=表ZG10债券等资产配置情况信息“h40000_四、债券逆回购”','所有产品币种为BWB“A5200_回购和拆借”值汇总<表ZG10债券等资产配置情况信息“h40000_四、债券逆回购”','01','12','SELECT nvl(sum(nvl(h40000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5200,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0070','ZG1438','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5210_住户','a5210','所有产品币种为BWB“A5210_住户”值汇总>=表ZG10债券等资产配置情况信息“h41000_住户” ','所有产品币种为BWB“A5210_住户”值汇总<表ZG10债券等资产配置情况信息“h41000_住户” ','01','12','SELECT nvl(sum(nvl(h41000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5210,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0071','ZG1439','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5220_广义政府','a5220','所有产品币种为BWB“A5220_广义政府”值汇总>=表ZG10债券等资产配置情况信息“h42000_广义政府” ','所有产品币种为BWB“A5220_广义政府”值汇总<表ZG10债券等资产配置情况信息“h42000_广义政府” ','01','12','SELECT nvl(sum(nvl(h42000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5220,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0072','ZG1440','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5230_非金融企业','a5230','所有产品币种为BWB“A5230_非金融企业”值汇总>=表ZG10债券等资产配置情况信息“h43000_非金融企业”','所有产品币种为BWB“A5230_非金融企业”值汇总<表ZG10债券等资产配置情况信息“h43000_非金融企业”','01','12','SELECT nvl(sum(nvl(h43000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5230,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0073','ZG1441','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5240_银行业存款类金融机构','a5240','所有产品币种为BWB“A5240_银行业存款类金融机构”值汇总>=表ZG10债券等资产配置情况信息 “h44000_银行业存款类金融机构”','所有产品币种为BWB“A5240_银行业存款类金融机构”值汇总<表ZG10债券等资产配置情况信息 “h44000_银行业存款类金融机构”','01','12','SELECT nvl(sum(nvl(h44000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5240,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0074','ZG1442','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5250_银行业非存款类金融机构','a5250','所有产品币种为BWB“A5250_银行业非存款类金融机构”值汇总>=表ZG10债券等资产配置情况信息“h45000_银行业非存款类金融机构”','所有产品币种为BWB“A5250_银行业非存款类金融机构”值汇总<表ZG10债券等资产配置情况信息“h45000_银行业非存款类金融机构”','01','12','SELECT nvl(sum(nvl(h45000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
'SELECT  sum(nvl(A5250,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0075','ZG1443','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5260_非银行业金融机构','a5260','所有产品币种为BWB“A5260_非银行业金融机构”值汇总>=表ZG10债券等资产配置情况信息“h46000_非银行业金融机构”','所有产品币种为BWB“A5260_非银行业金融机构”值汇总<表ZG10债券等资产配置情况信息“h46000_非银行业金融机构”','01','12','SELECT nvl(sum(nvl(h46000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5260,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0076','ZG1444','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5261_其中：中央交易对手方','a5261','所有产品币种为BWB“A5261_其中：中央交易对手方”值汇总>=表ZG10债券等资产配置情况信息 “h46100_其中：中央交易对手方” ','所有产品币种为BWB“A5261_其中：中央交易对手方”值汇总<表ZG10债券等资产配置情况信息 “h46100_其中：中央交易对手方” ','01','12','SELECT nvl(sum(nvl(h46100,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5261,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0077','ZG1445','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5270_特定目的载体','a5270','所有产品币种为BWB“A5270_特定目的载体”值汇总>=表ZG10债券等资产配置情况信息“h47000_特定目的载体”','所有产品币种为BWB“A5270_特定目的载体”值汇总<表ZG10债券等资产配置情况信息“h47000_特定目的载体”','01','12','SELECT nvl(sum(nvl(h47000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5270,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0078','ZG1446','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5271_银行非保本理财','a5271','所有产品币种为BWB“A5271_银行非保本理财”值汇总>=表ZG10债券等资产配置情况信息 “h47100_银行非保本理财” ','所有产品币种为BWB“A5271_银行非保本理财”值汇总<表ZG10债券等资产配置情况信息 “h47100_银行非保本理财” ','01','12','SELECT nvl(sum(nvl(h47100,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5271,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0079','ZG1447','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5272_信托公司资管产品','a5272','所有产品币种为BWB“A5272_信托公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47200_信托公司资管产品”','所有产品币种为BWB“A5272_信托公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47200_信托公司资管产品”','01','12','SELECT nvl(sum(nvl(h47200,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5272,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0080','ZG1448','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5273_证券公司及其子公司资管产品','a5273','所有产品币种为BWB“A5273_证券公司及其子公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47300_证券公司及其子公司资管产品” ','所有产品币种为BWB“A5273_证券公司及其子公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47300_证券公司及其子公司资管产品” ','01','12','SELECT nvl(sum(nvl(h47300,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5273,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0081','ZG1449','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5274_基金管理公司及其子公司专户','a5274','所有产品币种为BWB“A5274_基金管理公司及其子公司专户”值汇总>=表ZG10债券等资产配置情况信息“h47400_基金管理公司及其子公司专户” ','所有产品币种为BWB“A5274_基金管理公司及其子公司专户”值汇总<表ZG10债券等资产配置情况信息“h47400_基金管理公司及其子公司专户” ','01','12','SELECT nvl(sum(nvl(h47400,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5274,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0082','ZG1450','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5275_期货公司及其子公司资管产品','a5275','所有产品币种为BWB“A5275_期货公司及其子公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47500_期货公司及其子公司资管产品”','所有产品币种为BWB“A5275_期货公司及其子公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47500_期货公司及其子公司资管产品”','01','12','SELECT nvl(sum(nvl(h47500,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5275,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0083','ZG1451','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5276_保险资管产品','a5276','所有产品币种为BWB“A5276_保险资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47600_保险资管产品”','所有产品币种为BWB“A5276_保险资管产品”值汇总<表ZG10债券等资产配置情况信息“h47600_保险资管产品”','01','12','SELECT nvl(sum(nvl(h47600,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5276,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0084','ZG1452','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5277_金融资产投资公司资管产品','a5277','所有产品币种为BWB“A5277_金融资产投资公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47700_金融资产投资公司资管产品” ','所有产品币种为BWB“A5277_金融资产投资公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47700_金融资产投资公司资管产品” ','01','12','SELECT nvl(sum(nvl(h47700,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5277,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0085','ZG1453','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5278_公募基金','a5278','所有产品币种为BWB“A5278_公募基金”值汇总>=表ZG10债券等资产配置情况信息“h47800_公募基金”','所有产品币种为BWB“A5278_公募基金”值汇总<表ZG10债券等资产配置情况信息“h47800_公募基金”','01','12','SELECT nvl(sum(nvl(h47800,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5278,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0086','ZG1454','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5279_私募机构私募基金','a5279','所有产品币种为BWB“A5279_私募机构私募基金”值汇总>=表ZG10债券等资产配置情况信息“h47900_私募机构私募基金”','所有产品币种为BWB“A5279_私募机构私募基金”值汇总<表ZG10债券等资产配置情况信息“h47900_私募机构私募基金”','01','12','SELECT nvl(sum(nvl(h47900,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5279,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0087','ZG1455','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A527a_其他特定目的载体','a527a','所有产品币种为BWB“A527a_其他特定目的载体”值汇总>=表ZG10债券等资产配置情况信息“h47a00_其他特定目的载体” ','所有产品币种为BWB“A527a_其他特定目的载体”值汇总<表ZG10债券等资产配置情况信息“h47a00_其他特定目的载体” ','01','12','SELECT nvl(sum(nvl(h47a00,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A527a,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0088','ZG1456','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5280_境外','a5280','所有产品币种为BWB“A5280_境外”值汇总>=表ZG10债券等资产配置情况信息“h48000_境外” 。','所有产品币种为BWB“A5280_境外”值汇总<表ZG10债券等资产配置情况信息“h48000_境外” ！','01','12','SELECT nvl(sum(nvl(h48000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(A5280,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0089','ZG1457','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','AC000_资产减值准备（减）','ac000','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总=表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总≠表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ！','01','12','SELECT nvl(sum(nvl(h50000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(nvl(AC000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');
                                                                                                                                                                                                                                                    'DB0089','ZG1457','3','2','01','big_decimal_kit.greaterOrEqualsql(c1,ac000,pbcProdId,termid,'''')','ZG05','AC000_资产减值准备（减）','ac000','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总=表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总≠表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ！','01','12','SELECT nvl(sum(nvl(h50000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',' ');

-- QYRHPTSJ-153
delete from pbc_check_rule_info where rule_id='DB0056';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0056','ZG0661','3','2','04','big_decimal_kit.beEqualsqlDb56(c1,a5100,pbcProdId,termid,b1100,ccy,''CNY'')','ZG05','除回购和拆借外贷款','','同币种（除BWB）（A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计=对应产品“除回购和拆借外贷款明细信息”报送的对应贷款余额币种的贷款余额合计值','同币种（除BWB）（A5100_除回购和拆借外贷款+B1100_除回购和拆借外贷款）期末余额合计≠对应产品“除回购和拆借外贷款明细信息”报送的对应贷款余额币种的贷款余额合计值！','01','12','SELECT NVL(sum(NVL(CONTRACT_AMOUNT_CNY,0)),0) FROM pbc_col_loan_detail_sub WHERE termid = ''#termid''
 and PBC_PROD_ID = ''#pbcProdId'' ','');


delete from pbc_check_rule_info where rule_id='DB0058';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0058','','3','3','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”合计值=表ZG11企业债券分行业、企业规模情况信息“c00000_企业债券余额总计”值','所有产品币种为BWB“A4500_企业债券”合计值≠表ZG11企业债券分行业、企业规模情况信息“c00000_企业债券余额总计”值','01','12','SELECT NVL(sum(NVL(c00000,0)),0) FROM zg11_pbc_col_report WHERE termid = ''#termid'' '
       ,'SELECT  sum(NVL(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0061';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0061','ZG1172','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','A7200_特定目的载体份额','a7200','所有产品币种为BWB“A7200_特定目的载体份额”值汇总=表ZG11企业债券分行业、企业规模情况信息“G000C_资产方-特定目的载体份额-总计”值','所有产品币种为BWB“A7200_特定目的载体份额”值汇总≠表ZG11企业债券分行业、企业规模情况信息“G000C_资产方-特定目的载体份额-总计”值','01','12','SELECT NVL(sum(NVL(G000C,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A7200,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0062';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0062','ZG1173','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','负债方债务工具','','所有产品币种为BWB“B0000_负债合计”值汇总=表ZG11企业债券分行业、企业规模情况信息“G000D_负债及权益方-债务工具-总计”值','所有产品币种为BWB“B0000_负债合计”值汇总≠表ZG11企业债券分行业、企业规模情况信息“G000D_负债及权益方-债务工具-总计”值','01','12','SELECT NVL(sum(NVL(G000D,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(B0000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0063';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0063','ZG1174','3','1','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','实收本金','','所有产品币种为BWB“C1000_实收本金”值汇总=表ZG11企业债券分行业、企业规模情况信息“G000E_负债及权益方-实收本金-总计”值','所有产品币种为BWB“C1000_实收本金”值汇总≠表ZG11企业债券分行业、企业规模情况信息“G000E_负债及权益方-实收本金-总计”值','01','12','SELECT NVL(sum(NVL(G000E,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(C1000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0064';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0064','ZG1430','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','政策性金融债券','','所有产品币种为BWB“A4300_政策性金融债券”值汇总=表ZG10债券等资产配置情况信息“h11000_其中：政策性金融债券”','所有产品币种为BWB“A4300_政策性金融债券”值汇总≠表ZG10债券等资产配置情况信息“h11000_其中：政策性金融债券”','01','12','SELECT NVL(sum(NVL(h11000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A4300,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0065';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0065','ZG1431','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','其他金融债券','','所有产品币种为BWB“A4400_其他金融债券”值汇总=表ZG10债券等资产配置情况信息“h12000_其他金融债券”','所有产品币种为BWB“A4400_其他金融债券”值汇总≠表ZG10债券等资产配置情况信息“h12000_其他金融债券”','01','12','SELECT NVL(sum(NVL(h12000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A4400,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0066';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0066','ZG1432','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”值汇总=表ZG10债券等资产配置情况信息“h13000_企业债券”','所有产品币种为BWB“A4500_企业债券”值汇总≠表ZG10债券等资产配置情况信息“h13000_企业债券”','01','12','SELECT NVL(sum(NVL(h13000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0067';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0067','ZG1433','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','资产支持证券','','所有产品币种为BWB“A4700_资产支持证券”值汇总=表ZG10债券等资产配置情况信息“h14000_资产支持证券”','所有产品币种为BWB“A4700_资产支持证券”值汇总≠表ZG10债券等资产配置情况信息“h14000_资产支持证券”','01','12','SELECT NVL(sum(NVL(h14000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A4700,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB''');

delete from pbc_check_rule_info where rule_id='DB0068';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0068','ZG1434','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','企业债券','','所有产品币种为BWB“A4500_企业债券”值汇总>=表ZG10债券等资产配置情况信息（“h21000_其中：企业债”+“h22000_公司债”+“h23000_非金融企业债务融资工具”）','所有产品币种为BWB“A4500_企业债券”值汇总<表ZG10债券等资产配置情况信息（“h21000_其中：企业债”+“h22000_公司债”+“h23000_非金融企业债务融资工具”）','01','12','SELECT sum(NVL(h21000,0)+NVL(h22000,0)+NVL(h23000,0)) FROM zg10_pbc_col_report WHERE termid = ''#termid''',
        'SELECT  sum(NVL(A4500,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0069';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0069','ZG1437','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5200_回购和拆借','a5200','所有产品币种为BWB“A5200_回购和拆借”值汇总>=表ZG10债券等资产配置情况信息“h40000_四、债券逆回购”','所有产品币种为BWB“A5200_回购和拆借”值汇总<表ZG10债券等资产配置情况信息“h40000_四、债券逆回购”','01','12','SELECT NVL(sum(NVL(h40000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5200,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0070';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0070','ZG1438','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5210_住户','a5210','所有产品币种为BWB“A5210_住户”值汇总>=表ZG10债券等资产配置情况信息“h41000_住户” ','所有产品币种为BWB“A5210_住户”值汇总<表ZG10债券等资产配置情况信息“h41000_住户” ','01','12','SELECT NVL(sum(NVL(h41000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5210,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0071';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0071','ZG1439','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5220_广义政府','a5220','所有产品币种为BWB“A5220_广义政府”值汇总>=表ZG10债券等资产配置情况信息“h42000_广义政府” ','所有产品币种为BWB“A5220_广义政府”值汇总<表ZG10债券等资产配置情况信息“h42000_广义政府” ','01','12','SELECT NVL(sum(NVL(h42000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5220,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0072';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0072','ZG1440','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5230_非金融企业','a5230','所有产品币种为BWB“A5230_非金融企业”值汇总>=表ZG10债券等资产配置情况信息“h43000_非金融企业”','所有产品币种为BWB“A5230_非金融企业”值汇总<表ZG10债券等资产配置情况信息“h43000_非金融企业”','01','12','SELECT NVL(sum(NVL(h43000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5230,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0073';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0073','ZG1441','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5240_银行业存款类金融机构','a5240','所有产品币种为BWB“A5240_银行业存款类金融机构”值汇总>=表ZG10债券等资产配置情况信息 “h44000_银行业存款类金融机构”','所有产品币种为BWB“A5240_银行业存款类金融机构”值汇总<表ZG10债券等资产配置情况信息 “h44000_银行业存款类金融机构”','01','12','SELECT NVL(sum(NVL(h44000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5240,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0074';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0074','ZG1442','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5250_银行业非存款类金融机构','a5250','所有产品币种为BWB“A5250_银行业非存款类金融机构”值汇总>=表ZG10债券等资产配置情况信息“h45000_银行业非存款类金融机构”','所有产品币种为BWB“A5250_银行业非存款类金融机构”值汇总<表ZG10债券等资产配置情况信息“h45000_银行业非存款类金融机构”','01','12','SELECT NVL(sum(NVL(h45000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ',
        'SELECT  sum(NVL(A5250,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0075';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0075','ZG1443','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5260_非银行业金融机构','a5260','所有产品币种为BWB“A5260_非银行业金融机构”值汇总>=表ZG10债券等资产配置情况信息“h46000_非银行业金融机构”','所有产品币种为BWB“A5260_非银行业金融机构”值汇总<表ZG10债券等资产配置情况信息“h46000_非银行业金融机构”','01','12','SELECT NVL(sum(NVL(h46000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5260,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0076';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0076','ZG1444','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5261_其中：中央交易对手方','a5261','所有产品币种为BWB“A5261_其中：中央交易对手方”值汇总>=表ZG10债券等资产配置情况信息 “h46100_其中：中央交易对手方” ','所有产品币种为BWB“A5261_其中：中央交易对手方”值汇总<表ZG10债券等资产配置情况信息 “h46100_其中：中央交易对手方” ','01','12','SELECT NVL(sum(NVL(h46100,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5261,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0077';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0077','ZG1445','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5270_特定目的载体','a5270','所有产品币种为BWB“A5270_特定目的载体”值汇总>=表ZG10债券等资产配置情况信息“h47000_特定目的载体”','所有产品币种为BWB“A5270_特定目的载体”值汇总<表ZG10债券等资产配置情况信息“h47000_特定目的载体”','01','12','SELECT NVL(sum(NVL(h47000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5270,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0078';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0078','ZG1446','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5271_银行非保本理财','a5271','所有产品币种为BWB“A5271_银行非保本理财”值汇总>=表ZG10债券等资产配置情况信息 “h47100_银行非保本理财” ','所有产品币种为BWB“A5271_银行非保本理财”值汇总<表ZG10债券等资产配置情况信息 “h47100_银行非保本理财” ','01','12','SELECT NVL(sum(NVL(h47100,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5271,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0079';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0079','ZG1447','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5272_信托公司资管产品','a5272','所有产品币种为BWB“A5272_信托公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47200_信托公司资管产品”','所有产品币种为BWB“A5272_信托公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47200_信托公司资管产品”','01','12','SELECT NVL(sum(NVL(h47200,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5272,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0080';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0080','ZG1448','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5273_证券公司及其子公司资管产品','a5273','所有产品币种为BWB“A5273_证券公司及其子公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47300_证券公司及其子公司资管产品” ','所有产品币种为BWB“A5273_证券公司及其子公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47300_证券公司及其子公司资管产品” ','01','12','SELECT NVL(sum(NVL(h47300,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5273,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0081';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0081','ZG1449','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5274_基金管理公司及其子公司专户','a5274','所有产品币种为BWB“A5274_基金管理公司及其子公司专户”值汇总>=表ZG10债券等资产配置情况信息“h47400_基金管理公司及其子公司专户” ','所有产品币种为BWB“A5274_基金管理公司及其子公司专户”值汇总<表ZG10债券等资产配置情况信息“h47400_基金管理公司及其子公司专户” ','01','12','SELECT NVL(sum(NVL(h47400,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5274,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0082';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0082','ZG1450','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5275_期货公司及其子公司资管产品','a5275','所有产品币种为BWB“A5275_期货公司及其子公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47500_期货公司及其子公司资管产品”','所有产品币种为BWB“A5275_期货公司及其子公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47500_期货公司及其子公司资管产品”','01','12','SELECT NVL(sum(NVL(h47500,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5275,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0083';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0083','ZG1451','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5276_保险资管产品','a5276','所有产品币种为BWB“A5276_保险资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47600_保险资管产品”','所有产品币种为BWB“A5276_保险资管产品”值汇总<表ZG10债券等资产配置情况信息“h47600_保险资管产品”','01','12','SELECT NVL(sum(NVL(h47600,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5276,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0084';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0084','ZG1452','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5277_金融资产投资公司资管产品','a5277','所有产品币种为BWB“A5277_金融资产投资公司资管产品”值汇总>=表ZG10债券等资产配置情况信息“h47700_金融资产投资公司资管产品” ','所有产品币种为BWB“A5277_金融资产投资公司资管产品”值汇总<表ZG10债券等资产配置情况信息“h47700_金融资产投资公司资管产品” ','01','12','SELECT NVL(sum(NVL(h47700,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5277,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0085';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0085','ZG1453','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5278_公募基金','a5278','所有产品币种为BWB“A5278_公募基金”值汇总>=表ZG10债券等资产配置情况信息“h47800_公募基金”','所有产品币种为BWB“A5278_公募基金”值汇总<表ZG10债券等资产配置情况信息“h47800_公募基金”','01','12','SELECT NVL(sum(NVL(h47800,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5278,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0086';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0086','ZG1454','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5279_私募机构私募基金','a5279','所有产品币种为BWB“A5279_私募机构私募基金”值汇总>=表ZG10债券等资产配置情况信息“h47900_私募机构私募基金”','所有产品币种为BWB“A5279_私募机构私募基金”值汇总<表ZG10债券等资产配置情况信息“h47900_私募机构私募基金”','01','12','SELECT NVL(sum(NVL(h47900,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5279,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0087';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0087','ZG1455','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A527a_其他特定目的载体','a527a','所有产品币种为BWB“A527a_其他特定目的载体”值汇总>=表ZG10债券等资产配置情况信息“h47a00_其他特定目的载体” ','所有产品币种为BWB“A527a_其他特定目的载体”值汇总<表ZG10债券等资产配置情况信息“h47a00_其他特定目的载体” ','01','12','SELECT NVL(sum(NVL(h47a00,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A527a,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0088';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0088','ZG1456','3','2','01','big_decimal_kit.greaterOrEqualsql68(c1,tar1,ccy,termid,''BWB'')','ZG05','A5280_境外','a5280','所有产品币种为BWB“A5280_境外”值汇总>=表ZG10债券等资产配置情况信息“h48000_境外” 。','所有产品币种为BWB“A5280_境外”值汇总<表ZG10债券等资产配置情况信息“h48000_境外” ！','01','12','SELECT NVL(sum(NVL(h48000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(A5280,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');

delete from pbc_check_rule_info where rule_id='DB0089';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0089','ZG1457','3','2','01','big_decimal_kit.beEqualsqlDb58(c1,tar1,ccy,termid,''BWB'')','ZG05','AC000_资产减值准备（减）','ac000','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总=表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ','所有产品币种为BWB“AC000_资产减值准备（减）”值汇总≠表ZG10债券等资产配置情况信息“h50000_五、资产减值准备” ！','01','12','SELECT NVL(sum(NVL(h50000,0)),0) FROM zg10_pbc_col_report WHERE termid = ''#termid'' ','SELECT  sum(NVL(AC000,0)) FROM zg05_pbc_col_report WHERE TERMID=''#termid'' AND CCY=''BWB'' ');


-- QYRHPTSJ-151
delete from pbc_check_rule_info where rule_id in ('DB0042','DB0043','DB0044','DB0045','DB0046','DB0047','DB0048','DB0049','DB0050','DB0051','DB0052','DB0053','DB0054','DB0055');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0042','ZG0654','3','2','04','big_decimal_kit.beEqualsql(c1,a6100,pbcProdId,termid,'''')','ZG05','A6100_贷款收益权','a6100','同币种（数据类型=1时）A6100_贷款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6100_贷款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6100''  and CURRENCY_EPERIODCCY=''CNY''  and termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0043','ZG0655','3','2','04','big_decimal_kit.beEqualsql(c1,a6200,pbcProdId,termid,'''')','ZG05','A6200_债务证券收益权','a6200','同币种（数据类型=1时）A6200_债务证券收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6200_债务证券收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6200''  and CURRENCY_EPERIODCCY=''CNY''  and   termid = ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0044','ZG0656','3','2','04','big_decimal_kit.beEqualsql(c1,a6300,pbcProdId,termid,'''')','ZG05','A6300_应收账款收益权','a6300','同币种（数据类型=1时）A6300_应收账款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6300_应收账款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6300''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0045','ZG0657','3','2','04','big_decimal_kit.beEqualsql(c1,a6400,pbcProdId,termid,'''')','ZG05','A6400_其他债权收益权','a6400','同币种（数据类型=1时）A6400_其他债权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6400_其他债权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6400''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0046','ZG0658','3','2','04','big_decimal_kit.beEqualsql(c1,a6500,pbcProdId,termid,'''')','ZG05','A6500_股票收益权','a6500','同币种（数据类型=1时）A6500_股票收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6500_股票收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6500''  and CURRENCY_EPERIODCCY=''CNY''  and    termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0047','ZG0659','3','2','04','big_decimal_kit.beEqualsql(c1,a6600,pbcProdId,termid,'''')','ZG05','A6600_其他股权收益权','a6600','同币种（数据类型=1时）A6600_其他股权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6600_其他股权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where   ASSETS_LIABILITIES=''A6600''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0048','ZG0660','3','2','04','big_decimal_kit.beEqualsql(c1,a6700,pbcProdId,termid,'''')','ZG05','A6700_物权资产收益权','a6700','同币种（数据类型=1时）A6700_物权资产收益权 期末余额=对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值','同币种（数据类型=1时）A6700_物权资产收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应期末币种对应资产负债项目的基础资产期末余额合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6700''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0049','ZG0654','3','2','04','big_decimal_kit.beEqualsql(c1,a6100,pbcProdId,termid,'''')','ZG05','A6100_贷款收益权','a6100','币种BWBA6100_贷款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6100_贷款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6100''  and CURRENCY_EPERIODCCY=''CNY''  and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0050','ZG0655','3','2','04','big_decimal_kit.beEqualsql(c1,a6200,pbcProdId,termid,'''')','ZG05','A6200_债务证券收益权','a6200','币种BWBA6200_债务证券收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6200_债务证券收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub   where  ASSETS_LIABILITIES=''A6200''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0051','ZG0656','3','2','04','big_decimal_kit.beEqualsql(c1,a6300,pbcProdId,termid,'''')','ZG05','A6300_应收账款收益权','a6300','币种BWBA6300_应收账款收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6300_应收账款收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6300''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0052','ZG0657','3','2','04','big_decimal_kit.beEqualsql(c1,a6400,pbcProdId,termid,'''')','ZG05','A6400_其他债权收益权','a6400','币种BWBA6400_其他债权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6400_其他债权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6400''  and CURRENCY_EPERIODCCY=''CNY'' and termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0053','ZG0658','3','2','04','big_decimal_kit.beEqualsql(c1,a6500,pbcProdId,termid,'''')','ZG05','A6500_股票收益权','a6500','币种BWBA6500_股票收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6500_股票收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6500''  and CURRENCY_EPERIODCCY=''CNY'' and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0054','ZG0659','3','2','04','big_decimal_kit.beEqualsql(c1,a6600,pbcProdId,termid,'''')','ZG05','A6600_其他股权收益权','a6600','币种BWBA6600_其他股权收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6600_其他股权收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6600''  and CURRENCY_EPERIODCCY=''CNY'' and   termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0055','ZG0660','3','2','04','big_decimal_kit.beEqualsql(c1,a6700,pbcProdId,termid,'''')','ZG05','A6700_物权资产收益权','a6700','币种BWBA6700_物权资产收益权 期末余额=对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值','币种BWBA6700_物权资产收益权 期末余额≠对应产品“资产收益权明细信息”报送的对应资产负债项目基础资产期末余额折人民币合计值！','01','12','select nvl(sum(CURRENCY_AMT),0) from pbc_col_asset_usufruct_sub  where  ASSETS_LIABILITIES=''A6700''  and CURRENCY_EPERIODCCY=''CNY''  and  termid = ''#termid'' and PBC_PROD_ID = ''#pbcProdId'' ','');


delete from pbc_check_rule_info where rule_id='DB0060';
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0060', '3', '01', 'big_decimal_kit.beEqualsqlBWB(c1,tar1,pbcProdId,termid,'''',ccy)', 'ZG05', 'G000B_资产方-股权-总计', 'g000b', '12', '所有产品币种为BWB（“A7100_股票”+“A7300_其他股权”+“A5280_境外”）值汇总=表ZG11企业债券分行业、企业规模情况信息“G000B_资产方-股权-总计”值', '所有产品币种为BWB（“A7100_股票”+“A7300_其他股权”+“A5280_境外”）值汇总≠表ZG11企业债券分行业、企业规模情况信息“G000B_资产方-股权-总计”值', '01', 'ZG1171', '1', 'SELECT nvl(sum(nvl(G000B,0)),0) FROM zg09_pbc_col_report WHERE termid = ''#termid'' ', 'SELECT sum(nvl(A7100, 0)) +
           sum(nvl(A7300, 0)) + sum(nvl(A5280, 0)) FROM zg05_pbc_col_report WHERE termid = ''#termid'' AND ccy = ''BWB''', null, null, null, null, null);

-- QYRHPTSJ-163
delete from pbc_check_rule_info where rule_id='DB0012';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0012','','3','3','01','str_kit.isnotEqualssql(''0'',c1,pbcProdId,termid,buyPlace)','ZG04','币种','ccy','币种校验：资管产品存续期募集信息 表中当“当期申购金额”不为0时“币种”除了BWB，其他必须于对应产品 资管产品基本信息 表中“募集资金币种”报送选项一致','币种与资管产品基本信息表中“募集资金币种”不一致！','01','12','select count(1)
from PBC_COL_PRD_BASEINFO_SUB t,pbc_col_prod_raising_sub i
where 1=1
     and t.pbc_prod_id = i.pbc_prod_id
     and i.ccy<>''BWB''
     and t.RAISE_CCY<>i.CCY
     and i.pbc_prod_id = ''#pbcProdId''
     and i.RAISING_AMTSUM<>0
     and i.RAISING_DATE =  ''#termid''
     and i.BUY_PLACE =  ''#buyplace''','');

-- QYRHPTSJ-156
delete from pbc_check_rule_info where rule_id='DB0006';
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DB0006', '3', '01', 'str_kit.judgeValueByCond7sql(''1'',c1,''1'',tar1,pbcProdId,'''','''')', 'ZG02', '客户类型', 'customerType1', '12', '产品基本信息中管理方式为单独管理，产品起始募集信息中客户类型不能多于1类', '产品基本信息中管理方式为单独管理，产品起始募集信息中客户类型不能多于1类！', '01', 'ZG0096', '2', 'SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and MANAGE_TYPE =''2''', 'select count(*) from (
SELECT count(1)
FROM pbc_col_init_prod_raising_sub
WHERE PBC_PROD_ID = ''#pbcProdId''
group  by CUSTOMER_TYPE1) t', null, null, null, null, null);

-- QYRHPTSJ-164
delete from pbc_check_rule_info where rule_id='DB0031';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0031','ZG1599','3','3','02',
        'big_decimal_kit.compareYieldNotABSsql31(prodValue,c1,''0.2'',pbcProdId,termid,ccy)','ZG04','净值型产品期末净值','prodValue','净值型产品同币种（（本期）产品期末净值-（上期）产品期末净值）)/（上期）产品期末净值）绝对值 > 20%','净值型产品报送期末净值跨期变动过大！','01','12',
        'select nvl(sum(nvl(prod_Value,0)),0) from pbc_col_prod_raising_sub where RAISING_DATE = to_char(to_date(''#termid'', ''yyyyMM'') - 1, ''yyyyMM'')
        and pbc_prod_id = ''#pbcProdId'' and  CCY=''#ccy'' ','');

-- QYRHPTSJ-167
delete from pbc_check_rule_info where rule_id='DB0033';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0033','ZG0181','3','3','04','big_decimal_kit.beEqualsql2(endamtsum,c1,pbcProdId,raisingDate,buyPlace,ccy,customerType1)','ZG04','期末产品金额','endamtsum','净值型产品期末产品金额=期末产品份额*净值型产品期末净值','净值型产品期末金额不符合校验公式（期末产品金额=净值型产品期末净值*份额）！','01','12','
 select nvl ((select ENDQTY*(
    select DISTINCT  PROD_VALUE
    from pbc_col_prod_raising_sub
    where  RAISING_DATE = ''#termid''
      and  PBC_PROD_ID=''#pbcProdId''
      and CCY=''#ccy''
     AND PROD_VALUE IS NOT NULL
    ) from pbc_col_prod_raising_sub
where  RAISING_DATE = ''#termid''
  and PBC_PROD_ID in(
    select PBC_PROD_ID from  pbc_col_prd_baseinfo_sub
    where OPERATE_MODE in(''1'',''3'')  and  PBC_PROD_ID=''#pbcProdId'' )
  and BUY_PLACE =''#buyplace''
  and CCY=''#ccy''
 and CUSTOMER_TYPE1 = ''#customerType1'' ),0)from dual
','');

-- QYRHPTSJ-166
delete from pbc_check_rule_info where rule_id='DB0032';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0032','ZG0173','3','3','04','str_kit.isnotEqualssql(''0'',c1,pbcProdId,termId,buyPlace)','ZG04','期末产品金额','endamtsum','货基或现金管理类产品为“是”的产品，期末产品金额应等于期末产品份额','货币基金和现金管理类标识为是，但是产品期末金额份额不相等！','01','12','select case when ENDAMTSUM  = ENDQTY then ''0'' else ''1'' end  from pbc_col_prod_raising_sub   where  RAISING_DATE = ''#termid'' and PBC_PROD_ID in(
 select PBC_PROD_ID from  pbc_col_prd_baseinfo_sub   where CASH_MANA_CATE in(''1'')  and  PBC_PROD_ID=''#pbcProdId'' )and BUY_PLACE =  ''#buyplace''','');
commit;
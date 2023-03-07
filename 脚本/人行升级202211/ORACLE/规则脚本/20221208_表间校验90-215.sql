-- ZG05校验
delete from pbc_check_rule_info where rule_id in (
'DB0090','DB0091','DB0092','DB0093','DB0094','DB0095','DB0096','DB0097','DB0098','DB0099'
,'DB0100','DB0101','DB0102','DB0103','DB0104','DB0105','DB0106','DB0107','DB0108','DB0109'
,'DB0110','DB0111','DB0112','DB0113','DB0114','DB0115','DB0116','DB0117','DB0118','DB0119'
,'DB0120','DB0121','DB0122','DB0123','DB0124','DB0125','DB0126','DB0127','DB0128','DB0129'
,'DB0130','DB0131','DB0132','DB0133','DB0134','DB0135','DB0136','DB0137','DB0138','DB0139'
,'DB0140','DB0141','DB0142','DB0143','DB0144','DB0145','DB0146','DB0147','DB0148','DB0149'
,'DB0150','DB0151','DB0152','DB0153','DB0154','DB0155','DB0156','DB0157','DB0158','DB0159'
,'DB0160','DB0161','DB0162','DB0163','DB0164','DB0165','DB0166','DB0167','DB0168','DB0169'
,'DB0170','DB0171','DB0172','DB0173','DB0174','DB0175','DB0176','DB0177','DB0178','DB0179'
,'DB0180','DB0181','DB0182','DB0183','DB0184','DB0185','DB0186','DB0187','DB0188','DB0189'
,'DB0190','DB0191','DB0192','DB0193','DB0194','DB0195','DB0196','DB0197','DB0198','DB0199'
,'DB0200','DB0201','DB0202','DB0217','DB0218'
);
-- ZG06校验
delete from pbc_check_rule_info where rule_id in ('DB0203');
-- ZG07校验
delete from pbc_check_rule_info where rule_id in ('DB0204','DB0205','DB0206');
-- ZG08校验
delete from pbc_check_rule_info where rule_id in ('DB0207','DB0208');
-- ZG10校验
delete from pbc_check_rule_info where rule_id in ('DB0209','DB0210','DB0211');
-- ZG11校验
delete from pbc_check_rule_info where rule_id in ('DB0212','DB0213','DB0214','DB0215');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0090','ZG0662','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5271_银行非保本理财','a5271'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(A5271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0091','ZG0663','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5272_信托公司资管产','a5272'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(A5272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0092','ZG0664','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5273_证券公司及其子公司资管产品','a5273'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(A5273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0093','ZG0665','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5274_基金管理公司及其子公司专户','a5274'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(A5274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0094','ZG0666','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5275_期货公司及其子公司资管产品','a5275'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(A5275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0095','ZG0667','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5276_保险资管产品','a5276'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(A5276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0096','ZG0668','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5277_金融资产投资公司资管产品','a5277'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(A5277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0097','ZG0669','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A5278_公募基金','a5278'
,'同币种（数据类型=1时），资产方“回购和拆借”下“A5278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额之和！','同币种（数据类型=1时），资产方“回购和拆借”下“A5278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(A5278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0098','ZG0670','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1271_银行非保本理财','b1271'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(B1271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0099','ZG0671','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1272_信托公司资管产品','b1272'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(B1272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0100','ZG0672','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1273_证券公司及其子公司资管产品','b1273'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(B1273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0101','ZG0673','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1274_基金管理公司及其子公司专户','b1274'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(B1274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0102','ZG0674','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1275_期货公司及其子公司资管产品','b1275'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(B1275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0103','ZG0675','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1276_保险资管产品','b1276'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(B1276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0104','ZG0676','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1277_金融资产投资公司资管产品','b1277'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(B1277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0105','ZG0677','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','B1278_公募基金','b1278'
,'同币种（数据类型=1时），负债方“回购和拆借”下“B1278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额之和！','同币种（数据类型=1时），负债方“回购和拆借”下“B1278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(B1278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0106','ZG0678','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7210_银行非保本理财','a7210'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7210银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7210银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(A7210,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0107','ZG0679','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7220_信托公司资管产品','a7220'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7220信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7220信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(A7220,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0108','ZG0680','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7230_证券公司及其子公司资管产品','a7230'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7230证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7230证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(A7230,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0109','ZG0681','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7240_基金管理公司及其子公司专户','a7240'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7240基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7240基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(A7240,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0110','ZG0682','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7250_期货公司及其子公司资管产品','a7250'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7250期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7250期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(A7250,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0111','ZG0683','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7260_保险资管产品','a7260'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7260保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“6-保险资管产品”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7260保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“6-保险资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(A7260,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0112','ZG0684','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7270_金融资产投资公司资管产品','a7270'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7270金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7270金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(A7270,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0113','ZG0685','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','A7280_公募基金','a7280'
,'同币种（数据类型=1时），资产方“特定目的载体份额”下“A7280公募基金”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“8-公募基金”期末金额之和！','同币种（数据类型=1时），资产方“特定目的载体份额”下“A7280公募基金”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“8-公募基金”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(A7280,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0114','ZG0686','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1171_银行非保本理财','c1171'
,'同币种（数据类型=1时），实收本金“优先级”下“C1171银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1171银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(C1171,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0115','ZG0687','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1172_信托公司资管产品','c1172'
,'同币种（数据类型=1时），实收本金“优先级”下“C1172信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1172信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(C1172,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0116','ZG0688','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1173_证券公司及其子公司资管产品','c1173'
,'同币种（数据类型=1时），实收本金“优先级”下“C1173证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1173证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(C1173,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0117','ZG0689','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1174_基金管理公司及其子公司专户','c1174'
,'同币种（数据类型=1时），实收本金“优先级”下“C1174基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1174基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(C1174,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0118','ZG0690','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1175_期货公司及其子公司资管产品','c1175'
,'同币种（数据类型=1时），实收本金“优先级”下“C1175期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1175期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(C1175,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0119','ZG0691','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1176_保险资管产品','c1176'
,'同币种（数据类型=1时），实收本金“优先级”下“C1176保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1176保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(C1176,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0120','ZG0692','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1177_金融资产投资公司资管产品','c1177'
,'同币种（数据类型=1时），实收本金“优先级”下“C1177金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1177金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(C1177,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0121','ZG0693','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1178_公募基金','c1178'
,'同币种（数据类型=1时），实收本金“优先级”下“C1178公募基金”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“8-公募基金”期末金额之和！','同币种（数据类型=1时），实收本金“优先级”下“C1178公募基金”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“8-公募基金”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(C1178,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0122','ZG0694','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1271_银行非保本理财','c1271'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(C1271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0123','ZG0695','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1272_信托公司资管产品','c1272'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(C1272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0124','ZG0696','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1273_证券公司及其子公司资管产品','c1273'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy''and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(C1273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0125','ZG0697','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1273_证券公司及其子公司资管产品','c1274'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(C1274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0126','ZG0698','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1275_期货公司及其子公司资管产品','c1275'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(C1275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0127','ZG0699','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1276_保险资管产品','c1276'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(C1276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0128','ZG0700','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1277_金融资产投资公司资管产品','c1277'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(C1277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0129','ZG0701','3','3','04','big_decimal_kit.beEqualOfSqlByCcy(str_m,str_n,termid,ccy,statusType)','ZG05','C1278_公募基金','c1278'
,'同币种（数据类型=1时），实收本金“劣后级”下“C1278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“8-公募基金”期末金额之和！','同币种（数据类型=1时），实收本金“劣后级”下“C1278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“8-公募基金”期末金额之和！','01','16',
'SELECT nvl(sum(nvl(AMT,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and CCY = ''#ccy'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(C1278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''1''  and termid = ''#termid'' and CCY = ''#ccy''',null,null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0130','ZG0662','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5271_银行非保本理财','a5271',
'币种为BWB，资产方“回购和拆借”下“A5271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(A5271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0131','ZG0663','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5272_信托公司资管产','a5272',
'币种为BWB，资产方“回购和拆借”下“A5272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(A5272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0132','ZG0664','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5273_证券公司及其子公司资管产品','a5273',
'币种为BWB，资产方“回购和拆借”下“A5273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(A5273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0133','ZG0665','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5274_基金管理公司及其子公司专户','a5274',
'币种为BWB，资产方“回购和拆借”下“A5274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(A5274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0134','ZG0666','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5275_期货公司及其子公司资管产品','a5275',
'币种为BWB，资产方“回购和拆借”下“A5275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(A5275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0135','ZG0667','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5276_保险资管产品','a5276',
'币种为BWB，资产方“回购和拆借”下“A5276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(A5276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0136','ZG0668','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5277_金融资产投资公司资管产品','a5277',
'币种为BWB，资产方“回购和拆借”下“A5277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(A5277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0137','ZG0669','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A5278_公募基金','a5278',
'币种为BWB，资产方“回购和拆借”下“A5278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','币种为BWB，资产方“回购和拆借”下“A5278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“A5200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A5200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(A5278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0138','ZG0670','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1271_银行非保本理财','b1271',
'币种为BWB，负债方“回购和拆借”下“B1271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(B1271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0139','ZG0671','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1272_信托公司资管产品','b1272',
'币种为BWB，负债方“回购和拆借”下“B1272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(B1272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0140','ZG0672','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1273_证券公司及其子公司资管产品','b1273',
'币种为BWB，负债方“回购和拆借”下“B1273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(B1273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0141','ZG0673','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1274_基金管理公司及其子公司专户','b1274',
'币种为BWB，负债方“回购和拆借”下“B1274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(B1274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0142','ZG0674','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1275_期货公司及其子公司资管产品','b1275',
'币种为BWB，负债方“回购和拆借”下“B1275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(B1275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0143','ZG0675','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1276_保险资管产品','b1276',
'币种为BWB，负债方“回购和拆借”下“B1276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(B1276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0144','ZG0676','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1277_金融资产投资公司资管产品','b1277',
'币种为BWB，负债方“回购和拆借”下“B1277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(B1277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0145','ZG0677','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','B1278_公募基金','b1278',
'币种为BWB，负债方“回购和拆借”下“B1278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','币种为BWB，负债方“回购和拆借”下“B1278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“B1200-回购和拆借”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''B1200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(B1278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0146','ZG0678','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7210_银行非保本理财','a7210',
'币种为BWB，资产方“特定目的载体份额”下“A7210银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7210银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(A7210,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0147','ZG0679','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7220_信托公司资管产品','a7220',
'币种为BWB，资产方“特定目的载体份额”下“A7220信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7220信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(A7220,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0148','ZG0680','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7230_证券公司及其子公司资管产品','a7230',
'币种为BWB，资产方“特定目的载体份额”下“A7230证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7230证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(A7230,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0149','ZG0681','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7240_基金管理公司及其子公司专户','a7240',
'币种为BWB，资产方“特定目的载体份额”下“A7240基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7240基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(A7240,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0150','ZG0682','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7250_期货公司及其子公司资管产品','a7250',
'币种为BWB，资产方“特定目的载体份额”下“A7250期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7250期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(A7250,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0151','ZG0683','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7260_保险资管产品','a7260',
'币种为BWB，资产方“特定目的载体份额”下“A7260保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7260保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(A7260,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0152','ZG0684','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7270_金融资产投资公司资管产品','a7270',
'币种为BWB，资产方“特定目的载体份额”下“A7270金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7270金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(A7270,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0153','ZG0685','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','A7280_公募基金','a7280',
'币种为BWB，资产方“特定目的载体份额”下“A7280公募基金”余额，应等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','币种为BWB，资产方“特定目的载体份额”下“A7280公募基金”余额，不等于对应SPV明细信息中资产负债项目为“A7200-特定目的载体份额”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''A7200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(A7280,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0154','ZG0686','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1171_银行非保本理财','c1171',
'币种为BWB，实收本金“优先级”下“C1171银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1171银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(C1171,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0155','ZG0687','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1172_信托公司资管产品','c1172',
'币种为BWB，实收本金“优先级”下“C1172信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1172信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(C1172,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0156','ZG0688','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1173_证券公司及其子公司资管产品','c1173',
'币种为BWB，实收本金“优先级”下“C1173证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1173证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(C1173,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0157','ZG0689','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1174_基金管理公司及其子公司专户','c1174',
'币种为BWB，实收本金“优先级”下“C1174基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1174基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(C1174,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0158','ZG0690','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1175_期货公司及其子公司资管产品','c1175',
'币种为BWB，实收本金“优先级”下“C1175期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1175期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(C1175,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0159','ZG0691','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1176_保险资管产品','c1176',
'币种为BWB，实收本金“优先级”下“C1176保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1176保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(C1176,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0160','ZG0692','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1177_金融资产投资公司资管产品','c1177',
'币种为BWB，实收本金“优先级”下“C1177金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1177金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(C1177,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0161','ZG0693','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1178_公募基金','c1178',
'币种为BWB，实收本金“优先级”下“C1178公募基金”余额，应等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','币种为BWB，实收本金“优先级”下“C1178公募基金”余额，不等于对应SPV明细信息中资产负债项目为“C1100-优先级实收本金”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1100'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(C1178,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0162','ZG0694','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1271_银行非保本理财','c1271',
'币种为BWB，实收本金“劣后级”下“C1271银行非保本理财”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1271银行非保本理财”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“1-银行非保本理财”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''1'''
,'SELECT nvl(sum(nvl(C1271,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0163','ZG0695','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1272_信托公司资管产品','c1272',
'币种为BWB，实收本金“劣后级”下“C1272信托公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1272信托公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“2-信托公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''2'''
,'SELECT nvl(sum(nvl(C1272,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0164','ZG0696','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1273_证券公司及其子公司资管产品','c1273',
'币种为BWB，实收本金“劣后级”下“C1273证券公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1273证券公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“3-证券公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''3'''
,'SELECT nvl(sum(nvl(C1273,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0165','ZG0697','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1273_证券公司及其子公司资管产品','c1274',
'币种为BWB，实收本金“劣后级”下“C1274基金管理公司及其子公司专户”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1274基金管理公司及其子公司专户”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“4-基金管理公司及其子公司专户”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''4'''
,'SELECT nvl(sum(nvl(C1274,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0166','ZG0698','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1275_期货公司及其子公司资管产品','c1275',
'币种为BWB，实收本金“劣后级”下“C1275期货公司及其子公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1275期货公司及其子公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“5-期货公司及其子公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''5'''
,'SELECT nvl(sum(nvl(C1275,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0167','ZG0699','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1276_保险资管产品','c1276',
'币种为BWB，实收本金“劣后级”下“C1276保险资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1276保险资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“6-保险资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''6'''
,'SELECT nvl(sum(nvl(C1276,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0168','ZG0700','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1277_金融资产投资公司资管产品','c1277',
'币种为BWB，实收本金“劣后级”下“C1277金融资产投资公司资管产品”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1277金融资产投资公司资管产品”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“7-金融资产投资公司资管产品”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''7'''
,'SELECT nvl(sum(nvl(C1277,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0169','ZG0701','3','3','04','big_decimal_kit.beEqualOfSqlByBWBCcy(str_m,str_n,termid,ccy)','ZG05','C1278_公募基金','c1278',
'币种为BWB，实收本金“劣后级”下“C1278公募基金”余额，应等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','币种为BWB，实收本金“劣后级”下“C1278公募基金”余额，不等于对应SPV明细信息中资产负债项目为“C1200-劣后级实收本金”且交易对手产品种类为“8-公募基金”期末金额折人民币之和！','01','16',
'SELECT nvl(sum(nvl(AMT_CNY,0)),0) FROM PBC_COL_SPE_COUNTER_PARTY_SUB WHERE termid = ''#termid'' and ASSET_TYPE = ''C1200'' and PRRTY_PROD_TYPE =''8'''
,'SELECT nvl(sum(nvl(C1278,0)),0) FROM zg05_pbc_col_report WHERE STATUS_TYPE = ''3''  and termid = ''#termid''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0170','ZG0703','3','3','04','big_decimal_kit.greaterOrEqualsql170(c1,tar1,ccy,pbcProdId,''BWB'',a6000,b0000)','ZG05','A6000_资产收益权','a6000','资管产品基本信息中“收益权转让产品标识”为“是”的产品，当“B0000_负债合计”等于0或为空，资管产品存续期募集信息中币种为“BWB”的“期末产品金额”合计值应大于等于资产负债信息中币种为“BWB”的A6000_资产收益权 余额。','收益权转让产品在负债合计无数的情况下，产品募集余额应大于等于资产收益权！','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''',
        'select a.PBC_PROD_ID from
(select PBC_PROD_ID,TRANSFER_TYPE,
row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn
FROM PBC_COL_PRD_BASEINFO_SUB)  a WHERE a.rn=1 and a.TRANSFER_TYPE=''1'' and    a.PBC_PROD_ID = ''#pbcProdId''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0171','ZG0741','3','3','03','big_decimal_kit.compareBWBValueGreater0(ccy,param01,c0000,''1'',c4000,c3000,pbcProdId)','ZG05','C3000_未分配利润','c3000','当资管产品存续期募集信息中币种为“BWB”的“期末产品金额”合计值>资产负债信息表币种为“BWB”的C0000_权益合计余额，则币种为“BWB”的(C4000_其他综合收益+C3000_未分配利润)余额>0。','产品募集余额大于资产合计时，其他综合收益或未分配利润应有数!','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0172','ZG0742','3','3','03','big_decimal_kit.checkDB0172(ccy,pbcProdId,str_m,a1000,a2000,a3000,a4000,a5000,a6000,a9000)','ZG05','A4000、A3000、A2000、A5000','A4000、A3000、A2000、A5000',
'资管产品基本信息中“产品类型”为“固定收益类”的产品，资产负债信息中币种为“BWB”的（“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A9000_应收账款”）余额>0。','资管产品基本信息中“产品类型”为“固定收益类”的产品，资产负债信息中币种为“BWB”的（“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A9000_应收账款”）余额应大于0。','01','16',
'select IVSTORIENTATION from (SELECT IVSTORIENTATION,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0173','ZG0743','3','3','03','big_decimal_kit.notZeroOrNullOfsql173(pbcProdId,a7000,str_1,str_2,ccy,''BWB'')','ZG05','A7000_股权及特定目的载体份额','a7000',
'资管产品基本信息中“产品类型”为“权益类”的产品，资产负债信息中币种为“BWB”的A7000_股权及特定目的载体份额余额>0。',
'产品类型为权益类，资产池资负信息中资产方股权及特定目的载体份额项下应有数据！','01','22',
'select IVSTORIENTATION from (SELECT IVSTORIENTATION,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'2');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01)
VALUES ('DB0174','ZG0748','3','3','03','big_decimal_kit.notZero174(c1278,c1178)','ZG05','C1178_公募基金
C1278_公募基金','','C1178_公募基金、C1278_公募基金不应有值。备注：我们均为银行非保本理财产品','除公募基金产品外，实收本金中公募基金不应有数据！','01','11','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0175','ZG0749','3','3','03','big_decimal_kit.isZeroOrNullOfsql(pbcProdId,a7210,str_1,str_2)','ZG05','A7210_银行非保本理财','a7210',
'银行非保本理财产品，资产方股权特定目的载体份额银行A7210_银行非保本理财不应有数',
'银行非保本理财产品，资产方股权特定目的载体份额银行A7210_银行非保本理财不应有数！','01','22',
'select PBC_PROD_TYPE from (SELECT PBC_PROD_TYPE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'1');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0176','ZG0750','3','3','03','big_decimal_kit.isZeroOrNullOfsql(pbcProdId,a4600,str_1,str_2)','ZG05','A4600_票据','a4600',
'银行非保本理财产品，资产方A4600_票据不应有数',
'银行非保本理财产品，资产方A4600_票据不应有数！','01','22',
'select PBC_PROD_TYPE from (SELECT PBC_PROD_TYPE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'1');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0177','ZG0751','3','3','03','big_decimal_kit.notZeroOrNullOfsql(pbcProdId,a6000,str_1,str_2)','ZG05','A6000_资产收益权','a6000',
'资管产品基本信息中“收益权转让产品标识”为“是”的产品，A6000_资产收益权>0',
'资管产品基本信息中“收益权转让产品标识”为“是”的产品，收益权资产不应无数！','01','22',
'select TRANSFER_TYPE from (SELECT TRANSFER_TYPE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'1');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0178','ZG0752','3','3','03','big_decimal_kit.isZeroOrNullOfsql(pbcProdId,a6000,str_1,str_2)','ZG05','A6000_资产收益权','a6000',
'资管产品基本信息中“收益权转让产品标识”为“否”的产品，A6000_资产收益权不应有数',
'资管产品基本信息中“收益权转让产品标识”为“否”的产品，收益权资产不应有数！','01','22',
'select TRANSFER_TYPE from (SELECT TRANSFER_TYPE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'2');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0179','ZG0753','3','3','03','big_decimal_kit.notZeroOrNullOfsql(pbcProdId,c1100,str_1,str_2)','ZG05','C1100_优先级','c1100',
'资管产品基本信息中“分级产品标识”为“是”的产品，C1100_优先级>0',
'资管产品基本信息中“分级产品标识”为“是”的产品，C1100_优先级不应无数！','01','22',
'select IS_CONSTRACTE from (SELECT IS_CONSTRACTE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'1');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0180','ZG0754','3','3','03','big_decimal_kit.isZeroOrNullOfsql(pbcProdId,c1100,str_1,str_2)','ZG05','C1100_优先级','c1100',
'资管产品基本信息中“分级产品标识”为“否“的产品，C1100_优先级不应有数','资管产品基本信息中“分级产品标识”为“否“的产品，C1100_优先级不应有数！','01','22',
'select IS_CONSTRACTE from (SELECT IS_CONSTRACTE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'2');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0181','ZG0755','3','3','03','big_decimal_kit.checkDB0181ToDB0183(ccy,pbcProdId,str_1,valList_M,valList_N,str_4,a0000,b0000)','ZG05','A2000_存款','a2000',
'资管产品基本信息中“产品类型”为“固定收益类”的产品，资产负债信息中币种为“BWB”的（“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A9000_应收账款”-“B1000_贷款”-B1100_除回购和拆借外贷款-B1200_回购和拆借-B2000_资产支持证券-B4000_应付账款-B5000_其他负债）余额/（“A0000_资产合计”-“B0000_负债合计”）余额*100%>80%。','产品类型为固定收益类时，投资于存款、债券等债权类资产的比例应超过80%！','01','22',
'select IVSTORIENTATION from (SELECT IVSTORIENTATION,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'a1000,a2000,a3000,a4000,a5000,a6000,a9000,b1000,b1100,b1200,b2000,b4000,b5000','+,+,+,+,+,+,+,-,-,-,-,-,-','1');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0182','ZG0756','3','3','03','big_decimal_kit.checkDB0181ToDB0183(ccy,pbcProdId,str_1,valList_M,valList_N,str_4,a0000,b0000)','ZG05','A6000_资产收益权','a6000',
'资管产品基本信息中“产品类型”为“权益类”的产品，资产负债信息中币种为“BWB”的（“A7100_股票”+“A7300_其他股权”+“A5280_境外”-“B1280_境外”）余额/（“A0000_资产合计”-“B0000_负债合计”）余额*100%>80%。','产品类型为权益类时，投资于股票、未上市股权等权益类资产的比例应超过80%！','01','22',
'select IVSTORIENTATION from (SELECT IVSTORIENTATION,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'a7100,a7300,a5280,b1280','+,+,+,-','2');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03,PARAMS04) VALUES (
'DB0183','ZG0757','3','3','03','big_decimal_kit.checkDB0181ToDB0183(ccy,pbcProdId,str_1,valList_M,valList_N,str_4,a0000,b0000)','ZG05','A8000_金融衍生品','a8000',
'资管产品基本信息中“产品类型”为“商品及金融衍生品”的产品，资产负债信息中币种为“BWB”的（“A8000_金融衍生品”-“B3000_金融衍生品”）余额/（“A0000_资产合计”-“B0000_负债合计”）余额*100%>80%。','产品类型为商品及金融衍生品类时，投资于商品及金融衍生品的比例应超过80%！','01','22',
'select IVSTORIENTATION from (SELECT IVSTORIENTATION,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'a8000,b3000','+,-','3');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0184','ZG1648','3','3','04','big_decimal_kit.notBeEqualOfSql(str_1,termid,''a0000'',mapList)','ZG05','A0000_资产合计','a0000','资产合计当期数据和上期数据相同!','资产合计当期数据和上期数据相同!','01','17','SELECT nvl(sum(nvl(a0000,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = to_char(add_months(to_date(CONCAT(''#termid'',''01''),''yyyyMMdd''),-1),''yyyyMM'')','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0185','ZG1649','3','3','04','big_decimal_kit.notBeEqualOfSql(str_1,termid,''a7100'',mapList)','ZG05','A7100_股票','a7100','A7100_股票当期数据和上期数据相同!','A7100_股票当期数据和上期数据相同!','01','17','SELECT nvl(sum(nvl(a7100,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = to_char(add_months(to_date(CONCAT(''#termid'',''01''),''yyyyMMdd''),-1),''yyyyMM'')','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0186','ZG1650','3','3','04','big_decimal_kit.greaterOrEqualsql186(c1,tar1,ccy,pbcProdId,''BWB'',c1100)','ZG05','C1100_优先级','c1100','分级产品标识为“是”的产品，存续期募集信息中币种为“BWB”的“期末产品金额”合计值合计应大于等于该产品资产负债信息表中币种为“BWB”的C1100_优先级余额','分级产品标识为是，产品募集余额应大于等于实收本金优先级！','01','12','SELECT sum(nvl(ENDAMTSUM,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''',
'select a.PBC_PROD_ID from
    (select PBC_PROD_ID,IS_CONSTRACTE,
            row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn
     FROM PBC_COL_PRD_BASEINFO_SUB)  a WHERE a.rn=1 and a.IS_CONSTRACTE=''1'' and    a.PBC_PROD_ID = ''#pbcProdId''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0187','ZG1651','3','3','04','big_decimal_kit.checkDB0187(param01,''2'',ccy,param02,c1000,pbcProdId)','ZG05','C1000_实收本金','c1000','货基或现金管理类产品为“否”的产品，存续期募集信息中币种为“BWB”的“期末产品金额”合计值与该产品资产负债信息表币种为“BWB”的C1000_实收本金余额不应该相等','非货基或现金管理类产品的净值型产品，产品募集余额与实收本金相等！','01','12','SELECT nvl(max(t1.CASH_MANA_CATE),0) FROM PBC_COL_PRD_BASEINFO_SUB t1
		LEFT JOIN(
		SELECT
		inside_prod_id,
		pbc_code
		FROM
		pbc_prod_assetpool_code
		WHERE
		status=''E''
		AND code_type=''01''
		)t2 ON t2.inside_prod_id = t1.PROD_ID
        where  nvl(t2.pbc_code,replace(t1.PBC_PROD_ID,''INS'','''')) = ''#pbcProdId'' and t1.STATUS in (''01'')','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0188','ZG0636','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1120,c1220,'''',''0.05'',pbcProdId,termid)','ZG05','C1120_广义政府
C1220_广义政府','','1、（差值/实收本金广义政府)绝对值*100%大于>5%；
其中：
2、差值 = 期末产品份额广义政府-实收本金广义政府
3、期末产品份额广义政府 = 存续期募集信息中币种为“BWB”的客户类型为“广义政府”的“期末产品份额”，地区为“000000”
4、实收本金广义政府 = 资产负债信息产品币种为“BWB”的（“C1120_广义政府”+“C1220_广义政府”）合计值；','产品资金募集信息期末产品份额广义政府与资产负债信息实收本金广义政府差异较大！

','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''2'' and BUY_PLACE=''000000'' AND RAISING_DATE= ''#termid'' and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0189', '3', '04', 'big_decimal_kit.compareBWBYieldABSCond01(param01,''净值型'',ccy,c0000,param02,pbcProdId,'''',''0.05'',termid)', 'ZG05', 'C0000_权益合计', 'c0000', '12', '1、净值型产品 (差值/期末产品金额)绝对值*100%大于>5%；
其中：
2、差值 =  权益合计 - 期末产品金额
3、权益合计 = 资产负债信息中币种为“BWB”的C0000_权益合计；
4、期末产品金额 =  存续期募集信息中币种为“BWB”，地区为“000000”，所有客户类型的“期末产品金额”合计  ', '净值型产品资产负债信息中权益合计与产品资金募集信息中期末产品金额差异较大！', '01', 'ZG0641', '3', 'SELECT max(case when OPERATE_MODE = 1 then ''净值型'' when OPERATE_MODE = 3 then ''净值型'' else ''0'' end ) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')', 'SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and BUY_PLACE=''000000'' AND RAISING_DATE= ''#termid''   and PBC_PROD_ID = ''#pbcProdId''', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0190','ZG0704','3','3','04','big_decimal_kit.checkDB0190(param01,''1'',ccy,param02,c1000,pbcProdId)','ZG05','C1000_实收本金','c1000','净值型产品，存续期募集信息中币种为“BWB”,地区为“000000“，期末产品金额”合计与该产品币种为“BWB”的C1000_实收本金余额不应相等','净值型产品有数，期末募集资金余额与实收本金不应相等！','01','12','SELECT max(case when t1.OPERATE_MODE = 1 then ''1'' when t1.OPERATE_MODE = 3 then ''1'' else ''0'' end ) FROM PBC_COL_PRD_BASEINFO_SUB t1
        LEFT JOIN(
		SELECT
		inside_prod_id,
		pbc_code
		FROM
		pbc_prod_assetpool_code
		WHERE
		status=''E''
		AND code_type=''01''
		)t2 ON t2.inside_prod_id = t1.PROD_ID
        where nvl(t2.pbc_code,replace(t1.PBC_PROD_ID,''INS'','''')) = ''#pbcProdId'' and t1.STATUS in (''01'')','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and BUY_PLACE=''000000''  and PBC_PROD_ID = ''#pbcProdId''');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0191','ZG0635','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1110,c1210,'''',''0.05'',pbcProdId,termid)','ZG05','C1110_住户
C1210_住户','','1、（差值/资产负债信息实收本金住户）绝对值*100%>5%；
其中:
2、差值 = 产品资金募集信息期末产品份额-资产负债信息实收本金住户；
3、产品资金募集信息期末产品份额 = 存续期募集信息表，币种为“BWB”，客户类型为“住户”的“期末产品份额”，地区为“000000”；
4、资产负债信息实收本金住户 = 资产负债信息表,币种为“BWB”,“C1110_住户”+“C1210_住户”之和；','产品资金募集信息期末产品份额住户与资产负债信息实收本金住户差异较大！
','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''1'' and ccy = ''BWB'' and BUY_PLACE=''000000'' AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0192','ZG0637','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1130,c1230,'''',''0.05'',pbcProdId,termid)','ZG05','C1130_非金融企业
C1230_非金融企业','','1、（差值/资产负债信息实收本金非金融企业)绝对值*100%大于>5%；
其中：
2、差值 = 期末产品份额非金融企业-实收本金非金融企业
3、期末产品份额非金融企业 = 存续期募集信息表中，币种为“BWB”，客户类型为“非金融企业”的“期末产品份额”
4、实收本金非金融企业 = 资产负债信息表中，产品币种为“BWB”（“C1130_非金融企业”+“C1230_非金融企业”）合计值','产品资金募集信息期末产品份额非金融企业与资产负债信息实收本金非金融企业差异较大！','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''3'' and ccy = ''BWB'' and BUY_PLACE=''000000''  AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0193','ZG0638','3','3','04','big_decimal_kit.checkDB0193(ccy,param01,c1140,c1150,c1160,c1240,c1250,c1260,''0.05'',pbcProdId,termid)','ZG05','C1140_银行业存款类金融机构
C1150_银行业非存款类金融机构
C1160_非银行业金融机构
C1240_银行业存款类金融机构
C1250_银行业非存款类金融机构
C1260_非银行业金融机构','','1、（差值/实收本金A ）绝对值*100%大于>5%；
其中：
2、差值 = （期末产品份额金融机构-实收本金A）
3、期末产品份额金融机构 = 存续期募集信息中，币种为“BWB”，客户类型为“金融机构实体”的“期末产品份额”；
4、实收本金A = 资产负债信息表，产品币种为“BWB”（“C1140_银行业存款类金融机构”+“C1150_银行业非存款类金融机构”+“C1160_非银行业金融机构”+“C1240_银行业存款类金融机构”+“C1250_银行业非存款类金融机构”+“C1260_非银行业金融机构”）合计值；','产品资金募集信息期末产品份额金融机构与资产负债信息实收本金银行业存款类金融机构+银行业非存款类金融机构+非银行业金融机构差异较大！

','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''4'' and ccy = ''BWB'' and BUY_PLACE=''000000''  AND RAISING_DATE= ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0194','ZG0639','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1170,c1270,'''',''0.05'',pbcProdId,termid)','ZG05','C1170_特定目的载体
C1270_特定目的载体','','1、(差值)/实收本金特定目的载体绝对值*100% > 5%；
其中：
2、差值 = 期末产品份额特定目的载体-实收本金特定目的载体
3、期末产品份额特定目的载体 = 存续期募集信息中币种为“BWB”的客户类型为“特定目的载体”的“期末产品份额”；
4、实收本金特定目的载体绝对值 = 资产负债信息产品币种为“BWB”的（“C1170_特定目的载体”+“C1270_特定目的载体”合计值','产品资金募集信息期末产品份额特定目的载体与资产负债信息实收本金特定目的载体差异较大！
','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''5'' and ccy = ''BWB'' and BUY_PLACE=''000000'' AND RAISING_DATE= ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES (
'DB0195','ZG0640','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1180,c1280,'''',''0.05'',pbcProdId,termid)','ZG05','C1180_境外
C1280_境外','','1、（差值/实收本金境外）绝对值*100% > 5%
其中：
2、差值 = (期末产品份额境外-实收本金境外)
3、期末产品份额境外 = 存续期募集信息中币种为“BWB”的客户类型为“境外”的“期末产品份额”；
4、实收本金境外 = 资产负债信息产品币种为“BWB”的（“C1180_境外”+“C1280_境外”）合计值','产品资金募集信息期末产品份额境外与资产负债信息实收本金境外差异较大；
','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''6'' and ccy = ''BWB'' and BUY_PLACE=''000000'' AND RAISING_DATE= ''#termid''  and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0196','ZG0735','3','3','03','big_decimal_kit.judgeValueGreater0196(param01,c1110,c1210,statusType,pbcProdId,ccy)','ZG05','C1110_住户
C1210_住户','','存续期募集信息中客户类型为住户的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1110_住户、C1210_住户 余额应至少1个有数','客户类型为住户的产品募集余额与实收本金优先级与劣后级下住户应同时有数！','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''1'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0197','ZG0736','3','3','03','big_decimal_kit.judgeValueGreater0196(param01,c1120,c1220,statusType,pbcProdId,ccy)','ZG05','C1120_广义政府
C1220_广义政府','','存续期募集信息中客户类型为“广义政府”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1120_广义政府、C1220_广义政府 余额应至少1个有数','客户类型为广义政府的产品募集余额与实收本金优先级与劣后级下广义政府应同时有数','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''2'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0198','ZG0737','3','3','03','big_decimal_kit.judgeValueGreater0196(param01,c1130,c1230,statusType,pbcProdId,ccy)','ZG05','C1130_非金融企业
C1230_非金融企业','','存续期募集信息中客户类型为“非金融企业”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1130_非金融企业、C1230_非金融企业 余额应至少1个有数','客户类型为非金融性企业的产品募集余额与实收本金优先级与劣后级下非金融性企业应同时有数','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''3'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0199','ZG0738','3','3','03','big_decimal_kit.judgeValueGreater0199(param01,c1140,c1150,c1160,c1240,c1250,c1260,statusType,pbcProdId,ccy)','ZG05','C1140_银行业存款类金融机构
C1150_银行业非存款类金融机构
C1160_非银行业金融机构
C1240_银行业存款类金融机构
C1250_银行业非存款类金融机构
C1260_非银行业金融机构','','存续期募集信息中客户类型为“金融机构实体”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1140_银行业存款类金融机构、C1150_银行业非存款类金融机构、C1160_非银行业金融机构、C1240_银行业存款类金融机构、C1250_银行业非存款类金融机构、C1260_非银行业金融机构 余额应至少1个有数','客户类型为金融机构（实体）的产品募集余额与实收本金优先级与劣后级下金融机构（实体）应同时有数','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''4'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0200','ZG0739','3','3','03','big_decimal_kit.judgeValueGreater0196(param01,c1170,c1270,statusType,pbcProdId,ccy)','ZG05','C1170_特定目的载体
C1270_特定目的载体','','存续期募集信息中客户类型为“特定目的载体”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1170_特定目的载体、C1270_特定目的载体 余额应至少1个有数','客户类型为特定目的载体的产品募集余额与实收本金优先级与劣后级下特定目的载体应同时有数','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''5'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0201','ZG0740','3','3','03','big_decimal_kit.judgeValueGreater0196(param01,c1180,c1280,statusType,pbcProdId,ccy)','ZG05','C1180_境外
C1280_境外','','存续期募集信息中客户类型为“境外”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）C1180_境外、C1280_境外 余额应至少1个有数','客户类型为境外的产品募集余额与实收本金优先级与劣后级下境外应同时有数','01','12','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''6'' and ccy = ''#ccy'' and PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02,PARAMS03) VALUES (
'DB0202','ZG0705','3','3','04','big_decimal_kit.biggerThenZero(pbcProdId,c0000,valList_N,str_1,valList_M)','ZG05','C3000_未分配利润','c3000',
'净值型产品，当资产负债信息中C0000_权益合计大于0，C3000_未分配利润和C4000_其他综合收益也应大于0！',
'净值型产品资产负债信息中权益合计大于0，未分配利润和其他综合收益也应大于0！','01','22',
'select OPERATE_MODE from (SELECT OPERATE_MODE,row_number() over (partition by   PBC_PROD_ID   order  by  UPDATE_TIME desc) rn FROM PBC_COL_PRD_BASEINFO_SUB a LEFT JOIN( SELECT inside_prod_id,pbc_code FROM pbc_prod_assetpool_code WHERE status=''E'' AND code_type=''01'' )t2 ON t2.inside_prod_id=a.PROD_ID WHERE (a.PBC_PROD_ID = ''#pbcProdId'' or t2.pbc_code = ''#pbcProdId'') and a.STATUS in (''01'')) a where a.rn = ''1'''
,'c3000,c4000','1,3');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)VALUES (
'DB0203','ZG0858','3','2','05','big_decimal_kit.sqlGreaterTo(param01,pbcProdId,"0")','ZG06','产品代码','pbcProdId','产品代码不存在！','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0204','ZG0858','3','2','05','big_decimal_kit.sqlGreaterTo(param01,pbcProdId,"0")','ZG07','产品代码','pbcProdId','产品代码不存在！','产品代码不存在！','01','12','select  count(1)  from PBC_COL_PRD_BASEINFO_SUB t1
		LEFT JOIN(
		SELECT
		inside_prod_id,
		pbc_code
		FROM
		pbc_prod_assetpool_code
		WHERE
		status=''E''
		AND code_type=''01''
		)t2 ON t2.inside_prod_id=t1.PROD_ID
 where  nvl(t2.pbc_code,replace(t1.PBC_PROD_ID,''INS'',''''))= ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0205','ZG0935','3','2','03','str_kit.judgeValueByCond2sql(''1'',c1,''1'',loansType,pbcProdId,'''',buyPlace)','ZG07','贷款种类','loansType','产品基本信息中“产品品种”为“银行非保本理财”的产品，除回购和拆借外贷款明细信息中“贷款种类”不能选择“直接发放贷款”','非信托公司资管产品无直接发放贷款！','01','12','SELECT max(PBC_PROD_TYPE) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')','');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0206','ZG1000','3','3','04','date_kit.compareValueByCondSql(loanState,"FS09",loanMdate,param01,pbcProdId,termid,loanBasisId)','ZG07','贷款到期日期','loanMdate','贷款状态变更为缩期，贷款到期日期（本期）<贷款到期日期（上期）','贷款状态变更为缩期，贷款到期日期应早于上期；','01','12','SELECT to_char(max(LOAN_MDATE),''YYYY-MM-DD'') FROM pbc_col_loan_detail_sub WHERE PBC_PROD_ID = ''#pbcProdId'' and termid = TO_CHAR(ADD_MONTHS(to_date(''#termid'',''YYYYMM''), -1),''YYYYMM'') and LOAN_BASIS_ID = ''#loanBasisId'' and STATUS in (''01'')','');

-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0207','ZG1015','3','3','05','big_decimal_kit.sqlGreaterTo(param01,pbcProdId,"0")','ZG08','产品代码','pbcProdId','产品代码不存在！备注：检查报送的产品代码是否等于【人行产品资产池代码管理】模块“人行产品代码（外部）”或“人行产品代码（系统）”','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');
-- INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
-- VALUES ('DB0208', '3', '03', 'str_kit.judgeValueByCond02(param01,''1'',prrtyProdType,''1'',pbcProdId)', 'ZG08', '交易对手产品种类', 'prrtyProdType', '12', '产品基本信息中“产品品种”为“银行非保本理财”的产品，特定目的载体交易对手明细信息中“交易对手产品种类”一般不为银行非保本理财', '当前产品品种为01-银行非保本理财，交易对手一般不为银行非保本理财！', '01', 'ZG1042', '3', 'SELECT max(PBC_PROD_TYPE) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')', '', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0207','ZG1015','3','2','05','big_decimal_kit.sqlGreaterTo(param01,pbcProdId,"0")','ZG08','产品代码','pbcProdId','产品代码不存在！','产品代码不存在！','01','12','SELECT count(1) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId''','');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DB0208', '3', '03', 'str_kit.judgeValueByCond02(param01,''1'',prrtyProdType,''1'',pbcProdId)', 'ZG08', '交易对手产品种类', 'prrtyProdType', '12', '产品基本信息中“产品品种”为“银行非保本理财”的产品，特定目的载体交易对手明细信息中“交易对手产品种类”一般不为银行非保本理财', '当前产品品种为01-银行非保本理财，交易对手一般不为银行非保本理财！', '01', 'ZG1042', '3', 'SELECT max(PBC_PROD_TYPE) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')', '', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0209', '3', '03', 'big_decimal_kit.greaterOrEqualPlusSql(param01,h32000,h34000,termid)', 'ZG10', 'h32000_永续债（债性）
h34000_二级资本债', '', '12', '所有产品资产负债信息中币种为BWB的“A4400_其他金融债券”合计值>=债券等资产配置情况信息的“h32000_永续债（债性）”+“h34000_二级资本债”', '所有产品资产负债信息中币种为BWB的“A4400_其他金融债券”合计值<债券等资产配置情况信息的“h32000_永续债（债性）”+“h34000_二级资本债”!', '01', 'ZG1435', '2', 'SELECT nvl(sum(nvl(A4400,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = ''#termid'' and ccy = ''BWB''', '', null, null, null, null, '[发行机构代码;统计年月]');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0210', '3', '03', 'big_decimal_kit.greaterOrEqualPlusSql(param01,h33000,'''',termid)', 'ZG10', 'h33000_永续债（股性）', 'h33000', '12', '所有产品资产负债信息中币种为BWB的“A7320_金融机构股权”合计值 >=债券等资产配置情况信息的“h33000_永续债（股性）”', '所有产品资产负债信息中币种为BWB的“A7320_金融机构股权”合计值 <债券等资产配置情况信息的“h33000_永续债（股性）”!', '01', 'ZG1436', '2', 'SELECT nvl(sum(nvl(A7320,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = ''#termid'' and ccy = ''BWB''', '', null, null, null, null, '[发行机构代码;统计年月]');
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0211', '3', '03', 'big_decimal_kit.notZeroEqual(param01,h40000,termid)', 'ZG10', 'h40000_四、债券逆回购', 'h40000', '12', '资管产品资产负债信息的“A5200_回购和拆借”有数（非0），h40000_四、债券逆回购无数或为0，需核实', '资管产品资产负债信息的“A5200_回购和拆借”有数（非0），h40000_四、债券逆回购无数或为0，需核实!', '01', 'ZG1661', '3', 'SELECT nvl(sum(nvl(A5200,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = ''#termid'' and ccy = ''BWB''', '', null, null, null, null, '[发行机构代码;统计年月]');

-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0212','ZG1591','3','3','03','big_decimal_kit.beEqual(PARAMS01,c00000)','ZG11','c00000_企业债券余额总计','c00000','资管产品资产负债信息的“A4500_企业债券”本外币合计数据 = 企业债券分行业、企业规模情况信息的“c00000_企业债券余额总计”','资管产品资产负债信息的“A4500_企业债券”本外币合计数据 = 企业债券分行业、企业规模情况信息的“c00000_企业债券余额总计”','01','12','SELECT nvl(sum(nvl(A4500,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = ''#termid'' and ccy = ''BWB''','');
-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0213','ZG1662','3','3','02','big_decimal_kit.compareYieldNotABS(c04000,PARAMS01,''0.2'')','ZG11','c04000_企业债券余额总计-微型企业','c04000','(c04000_企业债券余额总计-微型企业(本期) - c04000_企业债券余额总计-微型企业(上期）)/c04000_企业债券余额总计-微型企业（上期）*100%>20%，需核实','c04000_企业债券余额总计-微型企业较上期增长20%以上，需核实!','01','12','select nvl(C04000,0) from ZG11_PBC_COL_REPORT where termid = date_format(DATE_SUB(str_to_date(CONCAT(''#termid'',''01''),''%Y%m%d''),interval 1 month),''%Y%m'')','');
-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0214','ZG1663','3','3','02','big_decimal_kit.zeroEqual(str_m,c04000,termId)','ZG11','c04000_企业债券余额总计-微型企业','c04000','c04000_企业债券余额总计-微型企业上期无数或为0，当期有数（非0），需核实','c04000_企业债券余额总计-微型企业上期无数或为0，当期有数（非0），需核实!','01','16','select nvl(C04000,0) from ZG11_PBC_COL_REPORT where termid = date_format(DATE_SUB(str_to_date(CONCAT(''#termid'',''01''),''%Y%m%d''),interval 1 month),''%Y%m'')',null);
-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0215','ZG1664','3','3','03','big_decimal_kit.notZero(cj0000)','ZG11','cj0000_公共管理、社会保障和社会组织','cj0000','cj0000_公共管理、社会保障和社会组织有数（非0），需核实','cj0000_公共管理、社会保障和社会组织有数（非0），需核实!','01','','','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0212','ZG1591','3','2','03','big_decimal_kit.sqlEqualsTo(param01,c00000,termid)','ZG11','c00000_企业债券余额总计','c00000','资管产品资产负债信息的“A4500_企业债券”本外币合计数据 = 企业债券分行业、企业规模情况信息的“c00000_企业债券余额总计”','资管产品资产负债信息的“A4500_企业债券”本外币合计数据 = 企业债券分行业、企业规模情况信息的“c00000_企业债券余额总计”','01','12','SELECT nvl(sum(nvl(A4500,0)),0) FROM ZG05_PBC_COL_REPORT WHERE termid = ''#termid'' and ccy = ''BWB''','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0213','ZG1662','3','3','02','big_decimal_kit.sqlCompareYieldNotABS(c04000,param01,termid,"0.2")','ZG11','c04000_企业债券余额总计-微型企业','c04000','(c04000_企业债券余额总计-微型企业(本期) - c04000_企业债券余额总计-微型企业(上期）)/c04000_企业债券余额总计-微型企业（上期）*100%>20%，需核实','c04000_企业债券余额总计-微型企业较上期增长20%以上，需核实!','01','12','select nvl(c04000,0) from ZG11_PBC_COL_REPORT where termid = TO_CHAR(ADD_MONTHS(to_date(''#termid'',''YYYYMM''), -1),''YYYYMM'')','');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0214','ZG1663','3','3','02','big_decimal_kit.zeroEqual(str_m,c04000,termid)','ZG11','c04000_企业债券余额总计-微型企业','c04000','c04000_企业债券余额总计-微型企业上期无数或为0，当期有数（非0），需核实','c04000_企业债券余额总计-微型企业上期无数或为0，当期有数（非0），需核实!','01','16','select nvl(C04000,0) from ZG11_PBC_COL_REPORT where termid = TO_CHAR(ADD_MONTHS(to_date(''#termid'',''YYYYMM''), -1),''YYYYMM'')',null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0215','ZG1664','3','3','03','big_decimal_kit.notZero2(cj0000)','ZG11','cj0000_公共管理、社会保障和社会组织','cj0000','cj0000_公共管理、社会保障和社会组织有数（非0），需核实','cj0000_公共管理、社会保障和社会组织有数（非0），需核实!','01','','','');


INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID) VALUES ('DB0217', '3', '04', 'big_decimal_kit.compareBWBYieldABSCond01(param01,''净值型'',ccy,c0000,param02,pbcProdId,'''',''0.05'')', 'ZG05', 'C0000_权益合计', 'c0000', '12', '1、净值型产品 (差值/期末产品金额)绝对值*100%大于>5%；
其中：
2、差值 =  权益合计 - 期末产品金额
3、权益合计 = 资产负债信息中币种为“BWB”的C0000_权益合计；
4、期末产品金额 =  存续期募集信息中币种为“BWB”，地区不为“000000”，所有客户类型的“期末产品金额”合计  ', '净值型产品资产负债信息中权益合计与产品资金募集信息中期末产品金额差异较大！', '01', 'ZG0641', '3', 'SELECT max(case when OPERATE_MODE = 1 then ''净值型'' when OPERATE_MODE = 3 then ''净值型'' else ''0'' end ) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')', 'SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and BUY_PLACE !=''000000''  and PBC_PROD_ID = ''#pbcProdId''', null, null, null, null, null);


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0218','ZG0704','3','3','04','big_decimal_kit.checkDB0218(param01,''1'',ccy,param02,c1000,pbcProdId)','ZG05','C1000_实收本金','c1000','净值型产品，存续期募集信息中币种为“BWB”,地区不为“000000“，期末产品金额”合计与该产品币种为“BWB”的C1000_实收本金余额不应相等','净值型产品有数，期末募集资金余额与实收本金不应相等！','01','12','SELECT max(case when t1.OPERATE_MODE = 1 then ''1'' when t1.OPERATE_MODE = 3 then ''1'' else ''0'' end ) FROM PBC_COL_PRD_BASEINFO_SUB t1
        LEFT JOIN(
		SELECT
		inside_prod_id,
		pbc_code
		FROM
		pbc_prod_assetpool_code
		WHERE
		status=''E''
		AND code_type=''01''
		)t2 ON t2.inside_prod_id = t1.PROD_ID
        where nvl(t2.pbc_code,replace(t1.PBC_PROD_ID,''INS'','''')) = ''#pbcProdId'' and t1.STATUS in (''01'')','SELECT nvl(sum(ENDAMTSUM),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and BUY_PLACE<>''000000''  and PBC_PROD_ID = ''#pbcProdId''');

delete from pbc_check_rule_info where rule_id='DB0216';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0216','ZG0636','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1120,c1220,'''',''0.05'',pbcProdId,termid)','ZG05','C1120_广义政府
C1220_广义政府','','1、（差值/实收本金广义政府)绝对值*100%大于>5%；
其中：
2、差值 = 期末产品份额广义政府-实收本金广义政府
3、期末产品份额广义政府 = 存续期募集信息中币种为“BWB”的客户类型为“广义政府”的“期末产品份额”，地区不为“000000”
4、实收本金广义政府 = 资产负债信息产品币种为“BWB”的（“C1120_广义政府”+“C1220_广义政府”）合计值；','产品资金募集信息期末产品份额广义政府与资产负债信息实收本金广义政府差异较大！
','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''2'' and BUY_PLACE !=''000000''  AND RAISING_DATE= ''#termid''  and ccy = ''BWB'' and PBC_PROD_ID = ''#pbcProdId''','');

delete from pbc_check_rule_info where rule_id='DB0217';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0217','ZG0641','3','3','04','big_decimal_kit.compareBWBYieldABSCond01(param01,''净值型'',ccy,c0000,param02,pbcProdId,'''',''0.05'',termid)','ZG05','C0000_权益合计','c0000','1、净值型产品 (差值/期末产品金额)绝对值*100%大于>5%；
其中：
2、差值 =  权益合计 - 期末产品金额
3、权益合计 = 资产负债信息中币种为“BWB”的C0000_权益合计；
4、期末产品金额 =  存续期募集信息中币种为“BWB”，地区不为“000000”所有客户类型的“期末产品金额”合计 ','净值型产品资产负债信息中权益合计与产品资金募集信息中期末产品金额差异较大！','01','12','SELECT max(case when OPERATE_MODE = 1 then ''净值型'' when OPERATE_MODE = 3 then''净值型'' else ''0'' end ) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')','SELECT sum(nvl(ENDAMTSUM,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'')  and ccy = ''BWB'' and BUY_PLACE !=''000000'' AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''');

delete from pbc_check_rule_info where rule_id='DB0219';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0219','ZG0635','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1110,c1210,'''',''0.05'',pbcProdId,termid)','ZG05','C1110_住户
C1210_住户','','1、（差值/资产负债信息实收本金住户）绝对值*100%>5%；
其中:
2、差值 = 产品资金募集信息期末产品份额-资产负债信息实收本金住户；
3、产品资金募集信息期末产品份额 = 存续期募集信息表，币种为“BWB”，客户类型为“住户”的“期末产品份额”，地区不为“000000”；
4、资产负债信息实收本金住户 = 资产负债信息表,币种为“BWB”,“C1110_住户”+“C1210_住户”之和；','产品资金募集信息期末产品份额住户与资产负债信息实收本金住户差异较大！
','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''1'' and ccy = ''BWB'' and BUY_PLACE !=''000000'' AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');

delete from pbc_check_rule_info where rule_id='DB0220';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0220','ZG0637','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1130,c1230,'''',''0.05'',pbcProdId,termid)','ZG05','C1130_非金融企业
C1230_非金融企业','','1、（差值/资产负债信息实收本金非金融企业)绝对值*100%大于>5%；
其中：
2、差值 = 期末产品份额非金融企业-实收本金非金融企业
3、期末产品份额非金融企业 = 存续期募集信息表中，币种为“BWB”，客户类型为“非金融企业”的“期末产品份额”，地区不为“000000”；
4、实收本金非金融企业 = 资产负债信息表中，产品币种为“BWB”（“C1130_非金融企业”+“C1230_非金融企业”）合计值','产品资金募集信息期末产品份额非金融企业与资产负债信息实收本金非金融企业差异较大！','01','12','SELECT nvl(sum(ENDQTY),0) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''3'' and ccy = ''BWB'' and BUY_PLACE !=''000000'' AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');

delete from pbc_check_rule_info where rule_id='DB0221';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0221','ZG0638','3','3','04','big_decimal_kit.checkDB0193(ccy,param01,c1140,c1150,c1160,c1240,c1250,c1260,''0.05'',pbcProdId,termid)','ZG05','C1140_银行业存款类金融机构
C1150_银行业非存款类金融机构
C1160_非银行业金融机构
C1240_银行业存款类金融机构
C1250_银行业非存款类金融机构
C1260_非银行业金融机构','','1、（差值/实收本金A ）绝对值*100%大于>5%；
其中：
2、差值 = （期末产品份额金融机构-实收本金A）
3、期末产品份额金融机构 = 存续期募集信息中，币种为“BWB”，客户类型为“金融机构实体”的“期末产品份额”，地区不为“000000”；
4、实收本金A = 资产负债信息表，产品币种为“BWB”（“C1140_银行业存款类金融机构”+“C1150_银行业非存款类金融机构”+“C1160_非银行业金融机构”+“C1240_银行业存款类金融机构”+“C1250_银行业非存款类金融机构”+“C1260_非银行业金融机构”）合计值；','产品资金募集信息期末产品份额金融机构与资产负债信息实收本金银行业存款类金融机构+银行业非存款类金融机构+非银行业金融机构差异较大！
','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''4'' and ccy = ''BWB'' and BUY_PLACE !=''000000''  AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');

delete from pbc_check_rule_info where rule_id='DB0222';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0222','ZG0639','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1170,c1270,'''',''0.05'',pbcProdId,termid)','ZG05','C1170_特定目的载体
C1270_特定目的载体','','1、(差值)/实收本金特定目的载体绝对值*100% > 5%；
其中：
2、差值 = 期末产品份额特定目的载体-实收本金特定目的载体
3、期末产品份额特定目的载体 = 存续期募集信息中币种为“BWB”的客户类型为“特定目的载体”的“期末产品份额”，地区不为“000000”；
4、实收本金特定目的载体绝对值 = 资产负债信息产品币种为“BWB”的（“C1170_特定目的载体”+“C1270_特定目的载体”合计值','产品资金募集信息期末产品份额特定目的载体与资产负债信息实收本金特定目的载体差异较大！
','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''5'' and ccy = ''BWB'' and BUY_PLACE !=''000000''  AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');

delete from pbc_check_rule_info where rule_id='DB0223';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02) VALUES ('DB0223','ZG0640','3','3','04','big_decimal_kit.compareBWBYieldABSPlus(ccy,param01,c1180,c1280,'''',''0.05'',pbcProdId,termid)','ZG05','C1180_境外
C1280_境外','','1、（差值/实收本金境外）绝对值*100% > 5%
其中：
2、差值 = (期末产品份额境外-实收本金境外)
3、期末产品份额境外 = 存续期募集信息中币种为“BWB”的客户类型为“境外”的“期末产品份额”，地区不为“000000”；
4、实收本金境外 = 资产负债信息产品币种为“BWB”的（“C1180_境外”+“C1280_境外”）合计值','产品资金募集信息期末产品份额境外与资产负债信息实收本金境外差异较大；
','01','12','SELECT sum(nvl(ENDQTY,0)) FROM  pbc_col_prod_raising_sub WHERE STATUS in (''01'') and CUSTOMER_TYPE1 = ''6'' and ccy = ''BWB'' and BUY_PLACE !=''000000''   AND RAISING_DATE= ''#termid'' and PBC_PROD_ID = ''#pbcProdId''','');

-- 新增规则
delete from pbc_check_rule_info where rule_id='DB0224';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0224','ZG1653','3','3','04','big_decimal_kit.checkDB0224(param01,termid,g00001)','ZG09','00001_附报-金融机构表内资产余额（本外币）','g00001','00001_附报-金融机构表内资产余额（本外币）（本期）/00001_附报-金融机构表内资产余额（本外币）（上期）>2
or 00001_附报-金融机构表内资产余额（本外币）（本期）= 00001_附报-金融机构表内资产余额（本外币）（上期）','00001_附报-金融机构表内资产余额（本外币）与上期数比值大于2或者等于1','01','12','select nvl(g00001,0) from ZG09_PBC_COL_REPORT WHERE TERMID = TO_CHAR(ADD_MONTHS(TO_DATE(CONCAT(''#termid'',''01''),''yyyyMMdd''),-1),''yyyyMM'')','');

delete from pbc_check_rule_info where rule_id='DB0225';
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
VALUES ('DB0225','ZG1654','3','3','04','big_decimal_kit.checkDB0225(param01,termid,g00002)','ZG09','00002_附报-金融机构表内金融资产余额（本外币）','g00002','00002_附报-金融机构表内金融资产余额（本外币）（本期）/00002_附报-金融机构表内金融资产余额（本外币）（上期）>2
or 00002_附报-金融机构表内金融资产余额（本外币）（本期）= 00002_附报-金融机构表内金融资产余额（本外币）（上期）','00002_附报-金融机构表内金融资产余额（本外币）与上期数比值大于2或者等于1','01','12','select nvl(g00002,0) from ZG09_PBC_COL_REPORT WHERE TERMID = TO_CHAR(ADD_MONTHS(TO_DATE(CONCAT(''#termid'',''01''),''yyyyMMdd''),-1),''yyyyMM'')','');


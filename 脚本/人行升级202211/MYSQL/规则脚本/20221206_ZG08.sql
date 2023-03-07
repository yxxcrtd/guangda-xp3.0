-- ZG08
DELETE FROM pbc_check_rule_info WHERE table_data_name = 'ZG08';
INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08001', 'ZG1014', '3', '2', '08', 'pbcProdId,assetType,prrtyProdId,ccy', 'ZG08', '产品代码,资产负债项目,交易对手产品代码,币种', 'pbcProdId', '10', '产品代码、资产负债项目、交易对手产品代码、币种组合应唯一', '产品代码+资产负债项目+交易对手产品代码+币种应唯一！', '01', null, null, '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08002', 'ZG1019', '1', '1', '07', 'str_kit.isNotEmpty(pbcProdId)', 'ZG08', '产品代码', 'pbcProdId', '静态校验', '【产品代码】不可为空！', '【产品代码】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08003', 'ZG1020', '1', '1', '07', 'str_kit.isNotEmpty(assetType)', 'ZG08', '资产负债项目', 'assetType', '静态校验', '【资产负债项目】不可为空！', '【资产负债项目】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08004', 'ZG1021', '1', '1', '07', 'str_kit.isNotEmpty(prrtyProdType)', 'ZG08', '交易对手产品种类', 'prrtyProdType', '静态校验', '【交易对手产品种类】不可为空！', '【交易对手产品种类】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08005', 'ZG1022', '3', '2', '07', 'str_kit.isNotEmpty(prrtyOrgCode)', 'ZG08', '交易对手机构编码', 'prrtyOrgCode', '静态校验', '【交易对手机构编码】不可为空！', '【交易对手机构编码】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08006', 'ZG1023', '3', '2', '07', 'str_kit.isNotEmpty(prrtyProdId)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '静态校验', '【交易对手产品代码】不可为空！', '【交易对手产品代码】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08007', 'ZG1024', '1', '1', '07', 'str_kit.isNotEmpty(ccy)', 'ZG08', '币种', 'ccy', '静态校验', '【币种】不可为空！', '【币种】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08008', 'ZG1025', '1', '1', '07', 'str_kit.isNotEmpty(amt)', 'ZG08', '期末金额', 'amt', '静态校验', '【期末金额】不可为空！', '【期末金额】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08009', 'ZG1026', '1', '1', '07', 'str_kit.isNotEmpty(amtCny)', 'ZG08', '期末金额折人民币', 'amtCny', '静态校验', '【期末金额折人民币】不可为空！', '【期末金额折人民币】不可为空！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08010', '1', '1', '1', '09', 'str_kit.compareEqualToLength(pbcProdId,15)', 'ZG08', '产品代码', 'pbcProdId', '静态校验', 'C15字符长度15', '【产品代码】数据长度错误,C15字符长度15!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08011', '2', '1', '1', '09', 'str_kit.compareEqualToLength(assetType,5)', 'ZG08', '资产负债项目', 'assetType', '静态校验', 'C5字符长度5', '【资产负债项目】数据长度错误,C5字符长度5!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08012', '3', '1', '1', '09', 'str_kit.compareEqualToLength(prrtyProdType,1)', 'ZG08', '交易对手产品种类', 'prrtyProdType', '静态校验', 'C1字符长度1', '【交易对手产品种类】数据长度错误,C1字符长度1!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08013', '4', '1', '1', '09', 'str_kit.compareEqualToLength(prrtyOrgCode,14)', 'ZG08', '交易对手机构编码', 'prrtyOrgCode', '静态校验', 'C14字符长度14', '【交易对手机构编码】数据长度错误,C14字符长度14!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08014', '5', '1', '1', '09', 'str_kit.compareEqualToLength(prrtyProdId,15)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '静态校验', 'C15字符长度15', '【交易对手产品代码】数据长度错误,C15字符长度15!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08015', '6', '1', '1', '09', 'str_kit.compareEqualToLength(ccy,3)', 'ZG08', '币种', 'ccy', '静态校验', 'C3字符长度3', '【币种】数据长度错误,C3字符长度3!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08016', '7', '1', '1', '09', 'big_decimal_kit.checkNumLenByParam(amt,20,2)', 'ZG08', '期末金额', 'amt', '静态校验', 'D20.2小数：字符最大长度20，保留2位小数', '【期末金额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08017', '8', '1', '1', '09', 'big_decimal_kit.checkNumLenByParam(amtCny,20,2)', 'ZG08', '期末金额折人民币', 'amtCny', '静态校验', 'D20.2小数：字符最大长度20，保留2位小数', '【期末金额折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08018', 'ZG1027', '1', '1', '10', 'enum_kit.isErrorDictCode("PBC_ASSET_LIAB_PROJ",assetType)', 'ZG08', '资产负债项目', 'assetType', '静态检验', '资产负债项目 值域范围为：A5200：回购和拆借A7200：特定目的载体份额B1200：回购和拆借C1100：优先级C1200：劣后级', '【资产负债项目】数据不在值域范围内!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08019', '3', '1', '1', '10', 'enum_kit.isErrorDictCode("PBC_PROD_TYPE",prrtyProdType)', 'ZG08', '交易对手产品种类', 'prrtyProdType', '静态检验', '1：银行非保本理财2：信托公司资管产品3：证券公司及其子公司资管产品4：基金管理公司及其子公司专户5：期货公司及其子公司资管产品6：保险资管产品7：金融资产投资公司资管产品8：公募基金', '【交易对手产品种类】数据不在值域范围内!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08020', 'ZG1028', '3', '2', '10', 'enum_kit.isErrorDictCode("CURRENCY_PBC",ccy)', 'ZG08', '币种', 'ccy', '静态检验', '采用《表示货币和资金的代码》(GB/T12406)最新标准中的三位字母型代码。具体代码见码值表“Z0003”。', '【币种】数据不在值域范围内!', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08021', 'ZG1029', '3', '3', '04', 'big_decimal_kit.greaterOrEqual(amt,0)', 'ZG08', '期末金额', 'amt', '静态检验', '期末金额>=0', '期末金额应不小于0！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08022', 'ZG1030', '3', '3', '04', 'big_decimal_kit.greaterOrEqual(amtCny,0)', 'ZG08', '期末金额折人民币', 'amtCny', '静态检验', '期末金额折人民币>=0', '期末金额折人民币应不小于0！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08023', 'ZG1031', '3', '3', '04', 'big_decimal_kit.conditionEqual(ccy,"CNY",amtCny,amt)', 'ZG08', '期末金额折人民币', 'amtCny', '静态检验', '币种为CNY时，期末金额折人民币=期末金额应', '币种为人民币时，期末金额折人民币与期末金额应相等！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08024', 'ZG1032', '3', '3', '04', 'big_decimal_kit.betweenOut(amtCny,0,10000000000)', 'ZG08', '期末金额折人民币', 'amtCny', '静态检验', '0<期末余额人民币一般应<100000000000', '期末余额人民币一般应大于0，小于100亿！', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08025', 'ZG1018', '3', '3', '06', 'unique_kit.isStrUniformity(prrtyOrgCode,prrtyProdId)', 'ZG08', '交易对手机构编码', 'prrtyOrgCode', '内部数据冲突类', '交易对手机构编码前6位应该与交易对手产品代码前6位一致', '交易对手机构编码与交易对手产品代码前6位不一致', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08026', 'ZG1033', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,1)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“1”的，交易对手产品种类应为“银行非保本理财”', '交易对手产品代码第7位为“1”的，交易对手产品种类应为“银行非保本理财”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08027', 'ZG1034', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,2)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“2”的，交易对手产品种类应为“信托公司资管产品”', '交易对手产品代码第7位为“2”的，交易对手产品种类应为“信托公司资管产品”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08028', 'ZG1035', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,3)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“3”的，交易对手产品种类应为“证券公司及其子公司资管产品”', '交易对手产品代码第7位为“3”的，交易对手产品种类应为“证券公司及其子公司资管产品”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08029', 'ZG1036', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,4)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“4”的，交易对手产品种类应为“基金管理公司及其子公司专户”','交易对手产品代码第7位为“4”的，交易对手产品种类应为“基金管理公司及其子公司专户”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08030', 'ZG1037', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,5)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“5”的，交易对手产品种类应为“期货公司及其子公司资管产品”', '交易对手产品代码第7位为“5”的，交易对手产品种类应为“期货公司及其子公司资管产品”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08031', 'ZG1038', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,6)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“6”的，交易对手产品种类应为“保险资管产品”', '交易对手产品代码第7位为“6”的，交易对手产品种类应为“保险资管产品”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08032', 'ZG1039', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,7)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“7”的，交易对手产品种类应为“金融资产投资公司资管产品”', '交易对手产品代码第7位为“7”的，交易对手产品种类应为“金融资产投资公司资管产品”', '01', '', '', '', '', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time, DATA_ID)
VALUES ('DS08033', 'ZG1040', '3', '3', '19', 'str_kit.judgePartyProdType(prrtyProdId,prrtyProdType,8)', 'ZG08', '交易对手产品代码', 'prrtyProdId', '业务逻辑类', '交易对手产品代码第7位为“8”的，交易对手产品种类应为“公募基金”', '交易对手产品代码第7位为“8”的，交易对手产品种类应为“公募基金”', '01', '', '', '', '', null, null, null);
-- 表间
-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0207','ZG1015','3','3','05','big_decimal_kit.sqlGreaterTo(param01,pbcProdId,"0")','ZG08','产品代码','pbcProdId','产品代码不存在！备注：检查报送的产品代码是否等于【人行产品资产池代码管理】模块“人行产品代码（外部）”或“人行产品代码（系统）”','产品代码不存在！','01','12','SELECT count(1) FROM PBC_PROD_ASSETPOOL_CODE WHERE PBC_CODE = ''#pbcProdId'' and status = ''E''','');

-- INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_content, rule_prompt, isStart,rule_method,PARAMS01,PARAMS02)
-- VALUES ('DB0208','ZG1042','3','3','03','str_kit.judgeValueByCond2(param01,''1'',prrtyProdType,''1'')','ZG08','交易对手产品种类','prrtyProdType','产品基本信息中“产品品种”为“银行非保本理财”的产品，特定目的载体交易对手明细信息中“交易对手产品种类”一般不为银行非保本理财','当前产品品种为01-银行非保本理财，交易对手一般不为银行非保本理财！','01','12','SELECT max(PBC_PROD_TYPE) FROM PBC_COL_PRD_BASEINFO_SUB WHERE PBC_PROD_ID = ''#pbcProdId'' and STATUS in (''01'')','');

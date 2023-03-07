DELETE FROM pbc_check_rule_info WHERE table_data_name = 'ZG02';

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02001','1','1','1','09','str_kit.compareEqualToLength(pbcProdId,15)','ZG02','产品代码','pbcProdId','静态校验','C15字符长度为15','【产品代码】数据长度错误,C15字符长度为15!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02002','2','1','1','09','str_kit.compareEqualToLength(ccy,3)','ZG02','币种','ccy','静态校验','C3字符长度为3','【币种】数据长度错误,C3字符长度为3!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02003','3','1','1','09','str_kit.compareEqualToLength(buyPlace,6)','ZG02','地区','buyPlace','静态校验','C6字符长度为6','【地区】数据长度错误,C6字符长度为6!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02004','4','1','1','09','str_kit.compareEqualToLength(customerType1,1)','ZG02','客户类型','customerType1','静态校验','C1字符长度为1','【客户类型】数据长度错误,C1字符长度为1!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02005','5','1','1','09','big_decimal_kit.checkNumLenByParam(initRaisingAmtsum,20,2)','ZG02','初始募集金额','initRaisingAmtsum','静态校验','D20.2小数：字符最大长度20，保留2位小数','【初始募集金额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02007','6','1','1','09','big_decimal_kit.checkNumLenByParam(initRaisingAmtsumcny,20,2)','ZG02','初始募集金额折人民币','initRaisingAmtsumcny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【初始募集金额折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02006','5','3','2','11','big_decimal_kit.ccyEqualSumOfPbcProdId(pbcProdId,ccy,buyPlace,str_1,str_2,str_3,mapList)','ZG02','初始募集金额','initRaisingAmtsum','20','同币种，全国（当【地区】为000000（全国）时），“初始募集金额”数据=各省级“初始募集金额”数据和','全国“初始募集金额”数据≠各省级“初始募集金额”数据和！','01', 'pbcProdId', 'ccy', 'initRaisingAmtsum', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02009','7','1','1','09','big_decimal_kit.checkNumLenByParam(initRaisingQty,20,2)','ZG02','初始募集份额','initRaisingQty','静态校验','D20.2小数：字符最大长度20，保留2位小数','【初始募集份额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02012','ZG0081','1','1','07','str_kit.isNotEmpty(pbcProdId)','ZG02','产品代码','pbcProdId','静态校验','【产品代码】不可为空！','【产品代码】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02013','ZG0082','1','1','07','str_kit.isNotEmpty(ccy)','ZG02','币种','ccy','静态校验','【币种】不可为空！','【币种】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02014','ZG0083','1','1','07','str_kit.isNotEmpty(buyPlace)','ZG02','地区','buyPlace','静态校验','【地区】不可为空！','【地区】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02015','ZG0084','1','1','07','str_kit.isNotEmpty(customerType1)','ZG02','客户类型','customerType1','静态校验','【客户类型】不可为空！','【客户类型】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02016','ZG0085','1','1','07','str_kit.isNotEmpty(initRaisingAmtsum)','ZG02','初始募集金额','initRaisingAmtsum','静态校验','【初始募集金额】不可为空！','【初始募集金额】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02017','ZG0086','1','1','07','str_kit.isNotEmpty(initRaisingAmtsumcny)','ZG02','初始募集金额折人民币','initRaisingAmtsumcny','静态校验','【初始募集金额折人民币】不可为空！','【初始募集金额折人民币】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02018','ZG0087','1','1','07','str_kit.isNotEmpty(initRaisingQty)','ZG02','初始募集份额','initRaisingQty','静态校验','【初始募集份额】不可为空！','【初始募集份额】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02019','ZG0088','3','2','10','enum_kit.isErrorDictCode(CURRENCY_PBC,ccy)','ZG02','币种','ccy','15','采用《表示货币和资金的代码》（GB/T12406）最新标准中的三位字母型代码。具体代码见码值表“Z0003”。如涉及多个币种，按币种进行拆分报送。除分币种数据外，每只资管产品均需同时报送本外币合计数据，币种填报“BWB”。如资管产品募集资金币种为人民币、美元，则需要按“CNY”、“USD”、“BWB”拆分报送。','【币种】数据不在值域范围内!','01', 'CURRENCY_PBC', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02020','ZG0089','3','2','10','enum_kit.isErrorDictCode(ADMINISTRATIVE_TYPE1,buyPlace)','ZG02','地区','buyPlace','15','境内地区采用《中华人民共和国行政区划代码》（GB/T2260）的省级数字码，具体代码见码值表“Z0013”。境外地区前3位用“000”填充，后3位采用《世界各国和地区名称代码》（GB/T2659）最新标准的3位国别阿拉伯数字代码（港澳台地区暂采用该标准编码），具体代码见码值表“Z0011”。如涉及多个地区，按地区进行拆分报送。除分地区数据外，每只资管产品均需同时报送全部地区（包括境内地区和境外地区）合计数据，地区填报“000000”。','【地区】数据不在值域范围内!','01', 'ADMINISTRATIVE_TYPE1', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02021','ZG0090','1','1','10','enum_kit.isErrorDictCode(SVR_PBC_COL_CUSTTYPE,customerType1)','ZG02','客户类型','customerType1','15','1：住户2：广义政府3：非金融企业4：金融机构（实体）5：特定目的载体6：境外如涉及多个客户类型，按客户类型进行拆分报送。','【客户类型】数据不在值域范围内!','01', 'SVR_PBC_COL_CUSTTYPE', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02022','ZG0091','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,initRaisingAmtsum,initRaisingAmtsumcny)','ZG02','初始募集金额','initRaisingAmtsum','静态检验','当币种为CNY时，“初始募集金额”数据=“初始募集金额折人民币”数据！','当币种为CNY时，“初始募集金额”数据≠“初始募集金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02023','ZG0091','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,initRaisingAmtsum,initRaisingAmtsumcny)','ZG02','初始募集金额','initRaisingAmtsum','静态检验','当币种为BWB时，“初始募集金额”数据=“初始募集金额折人民币”数据','当币种为BWB时，“初始募集金额”数据≠“初始募集金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02024','ZG0092','3','2','11','big_decimal_kit.greaterOrEqual(initRaisingAmtsum,0)','ZG02','初始募集金额','initRaisingAmtsum','静态检验','初始募集金额>=0！','初始募集金额不应为负值！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02025','ZG0093','3','2','11','big_decimal_kit.greaterOrEqual(initRaisingAmtsumcny,0)','ZG02','初始募集金额折人民币','initRaisingAmtsumcny','静态检验','初始募集金额折人民币>=0！','初始募集金额折人民币不应为负值！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02026','ZG0094','3','2','11','big_decimal_kit.greaterOrEqual(initRaisingQty,0)','ZG02','初始募集份额','initRaisingQty','静态检验','初始募集份额>=0！','初始募集份额不应为负值！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02031','ZG1190','1','1','03','str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)','ZG02','币种','ccy','17','每个产品代码下至少有一条币种为‘BWB’的数据','每个产品代码下至少有一条币种为‘BWB‘的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02032','ZG1191','3','2','03','str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)','ZG02','地区','buyPlace','17','每个产品代码下至少有一条地区为‘000000’的数据','每个产品代码下至少有一条地区为‘000000‘的数据!','01', 'pbcProdId', 'buyPlace', '000000', null);


-- 补充更新脚本 部分字段需要携带双引号
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",initRaisingAmtsum,initRaisingAmtsumcny)' where rule_id = 'DS02022';

update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",initRaisingAmtsum,initRaisingAmtsumcny)' where rule_id = 'DS02023';

-- 唯一性校验
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS02011','ZG0079','3','2','08','pbcProdId,ccy,buyPlace,customerType1','ZG02','产品代码、币种、地区、客户类型','pbcProdId','10','产品代码+币种+地区+客户类型应唯一！','产品代码+币种+地区+客户类型应唯一！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time) VALUES 
('DS02027', '3', '06', 'big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,initRaisingAmtsumcny)', 'ZG02', '初始募集金额折人民币', 'initRaisingAmtsumcny', '21', '相同的产品代码、地区和客户类型下，币种为BWB的初始募集金额折人民币应等于除BWB外的其他各币种初始募集金额折人民币之和!', '相同的产品代码、地区和客户类型下，币种为BWB的初始募集金额折人民币应等于除BWB外的其他各币种初始募集金额折人民币之和!', '01', 'ZG1186', '2', 'pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'initRaisingAmtsumcny', null, null);
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time) VALUES 
('DS02028', '3', '06', 'big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,initRaisingQty)', 'ZG02', '初始募集份额', 'initRaisingQty', '21', '相同的产品代码、地区和客户类型下，币种为BWB的初始募集份额应等于除BWB外的其他各币种初始募集份额之和', '相同的产品代码、地区和客户类型下，币种为BWB的初始募集份额应等于除BWB外的其他各币种初始募集份额之和!', '01', 'ZG1187', '2', 'pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'initRaisingQty', null, null);
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time) VALUES 
('DS02029', '3', '06', 'big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,initRaisingAmtsumcny)', 'ZG02', '初始募集金额折人民币', 'initRaisingAmtsumcny', '21', '相同的产品代码、币种和客户类型下，地区为000000的初始募集金额折人民币应等于除000000外的其他各地区的初始募集金额折人民币之和', '相同的产品代码、币种和客户类型下，地区为000000的初始募集金额折人民币应等于除000000外的其他各地区的初始募集金额折人民币之和!', '01', 'ZG1188', '2', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'initRaisingAmtsumcny', null, null);
INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time) VALUES 
('DS02030', '3', '06', 'big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,initRaisingQty)', 'ZG02', '初始募集份额', 'initRaisingQty', '21', '相同的产品代码、币种和客户类型下，地区为000000的初始募集份额应等于除000000外的其他各地区的初始募集份额之和', '相同的产品代码、币种和客户类型下，地区为000000的初始募集份额应等于除000000外的其他各地区的初始募集份额之和!', '01', 'ZG1189', '2', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'initRaisingQty', null, null);


-- 新增 需求
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02033','ZG1684','3','2','03','str_kit.listConditionExistsNotEquals(pbcProdId,str_1,str_2,str_3,mapList)','ZG02','币种','ccy','17','该报表每个产品代码下至少有一条币种不为''BWB''的数据','每个产品代码下至少有一条币种不为''BWB''的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS02034','ZG1685','3','2','03','str_kit.listConditionExistsNotEquals(pbcProdId,str_1,str_2,str_3,mapList)','ZG02','地区','buyPlace','17','该报表每个产品代码下至少有一条地区不为''000000''的数据','每个产品代码下至少有一条地区不为''000000''的数据!','01', 'pbcProdId', 'buyPlace', '000000', null);

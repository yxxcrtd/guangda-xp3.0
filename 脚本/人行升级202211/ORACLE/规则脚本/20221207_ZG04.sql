
DELETE FROM pbc_check_rule_info WHERE table_data_name = 'ZG04';

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04001','1','1','1','09','str_kit.compareEqualToLength(pbcProdId,15)','ZG04','产品代码','pbcProdId','静态校验','C15字符长度15','【产品代码】数据长度错误,C15字符长度15!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04002','2','1','1','09','str_kit.compareEqualToLength(buyPlace,6)','ZG04','地区','buyPlace','静态校验','C6字符长度6','【地区】数据长度错误,C6字符长度6!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04003','3','1','1','09','str_kit.compareEqualToLength(customerType1,1)','ZG04','客户类型','customerType1','静态校验','C1字符长度1','【客户类型】数据长度错误,C1字符长度1!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04004','4','1','1','09','str_kit.compareEqualToLength(ccy,3)','ZG04','币种','ccy','静态校验','C3字符长度3','【币种】数据长度错误,C3字符长度3!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04005','4','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,ccy)','ZG04','币种','ccy','静态检验','报送产品期末业绩表现时，本项为空！','当报送产品期末业绩表现时，币种为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04006','4','3','2','14','str_kit.isEmptyByCond3(prodEndYield,ccy)','ZG04','币种','ccy','静态检验','【产品期末业绩表现】为空时，【币种】必填！','【产品期末业绩表现】为空时，【币种】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04007','5','1','1','09','big_decimal_kit.checkNumLenByParam(raisingAmtsum,20,2)','ZG04','当期申购金额','raisingAmtsum','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期申购金额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04009','5','3','2','11','big_decimal_kit.notZero2(raisingAmtsum)','ZG04','当期申购金额','raisingAmtsum','静态检验','封闭式净值型产品，非成立月，不能报送当期申购金额！备注：资管产品基本信息表中“运行方式”为“3：封闭式净值型”的产品为封闭式净值型产品；','封闭式净值型产品报送了当期申购数据，请核实是否为特殊情况！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04010','6','1','1','09','big_decimal_kit.checkNumLenByParam(raisingAmtsumCny,20,2)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期申购金额折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04012','7','1','1','09','big_decimal_kit.checkNumLenByParam(raisingQty,20,2)','ZG04','当期申购份额','raisingQty','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期申购份额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04015','8','1','1','09','big_decimal_kit.checkNumLenByParam(redamt,20,2)','ZG04','当期兑付/赎回金额','redamt','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期兑付/赎回金额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04017','9','1','1','09','big_decimal_kit.checkNumLenByParam(redamtCny,20,2)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期兑付/赎回金额折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04019','10','1','1','09','big_decimal_kit.checkNumLenByParam(redqty,20,2)','ZG04','当期兑付/赎回份额','redqty','静态校验','D20.2小数：字符最大长度20，保留2位小数','【当期兑付/赎回份额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04021','11','1','1','09','big_decimal_kit.checkNumLenByParam(endamtsum,20,2)','ZG04','期末产品金额','endamtsum','静态校验','D20.2小数：字符最大长度20，保留2位小数','【期末产品金额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04023','11','3','2','11','big_decimal_kit.notZero2(endamtsum)','ZG04','期末产品金额','endamtsum','静态检验','产品终止当月期末产品金额=0','产品终止当月期末产品金额≠0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04025','12','1','1','09','big_decimal_kit.checkNumLenByParam(endamtsumCny,20,2)','ZG04','期末产品金额折人民币','endamtsumCny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【期末产品金额折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04027','13','1','1','09','big_decimal_kit.checkNumLenByParam(endqty,20,2)','ZG04','期末产品份额','endqty','静态校验','D20.2小数：字符最大长度20，保留2位小数','【期末产品份额】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04030', '1', '09', 'big_decimal_kit.checkNumLenByParam(prodValue,20,8)', 'ZG04', '净值型产品期末净值', 'prodValue', '静态校验', 'D20.2小数：字符最大长度20，保留8位小数', '【净值型产品期末净值】数据长度错误,D20.2小数：字符最大长度20，保留8位小数!', '01', '14', '1', null, null, null, null);


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04031','14','1','1','14','str_kit.isNotEmptyByThreeParam(prodValueCny,unitValueTotal,unitValueTotalCny,prodValue)','ZG04','净值型产品期末净值','prodValue','静态检验','【净值型产品期末净值折人民币】【净值型产品期末累计净值】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末净值】必填！','【净值型产品期末净值折人民币】【净值型产品期末累计净值】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末净值】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04032', '1', '09', 'big_decimal_kit.checkNumLenByParam(prodValueCny,20,8)', 'ZG04', '净值型产品期末净值折人民币', 'prodValueCny', '静态校验', 'D20.2小数：字符最大长度20，保留8位小数', '【净值型产品期末净值折人民币】数据长度错误,D20.2小数：字符最大长度20，保留8位小数!', '01', '15', '1', null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04033','15','1','1','14','str_kit.isNotEmptyByThreeParam(prodValue,unitValueTotal,unitValueTotalCny,prodValueCny)','ZG04','净值型产品期末净值折人民币','prodValueCny','静态检验','【净值型产品期末净值】【净值型产品期末累计净值】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末净值折人民币】必填！','【净值型产品期末净值】【净值型产品期末累计净值】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末净值折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04034', '1', '09', 'big_decimal_kit.checkNumLenByParam(unitValueTotal,20,8)', 'ZG04', '净值型产品期末累计净值', 'unitValueTotal', '静态校验', 'D20.2小数：字符最大长度20，保留8位小数', '【净值型产品期末累计净值】数据长度错误,D20.2小数：字符最大长度20，保留8位小数!', '01', '16', '1', null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04035','16','1','1','14','str_kit.isNotEmptyByThreeParam(prodValue,prodValueCny,unitValueTotalCny,unitValueTotal)','ZG04','净值型产品期末累计净值','unitValueTotal','静态检验','【净值型产品期末净值】【净值型产品期末净值折人民币】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末累计净值】必填！','【净值型产品期末净值】【净值型产品期末净值折人民币】【净值型产品期末累计净值折人民币】任何一个字段值非空时，【净值型产品期末累计净值】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04036', '1', '09', 'big_decimal_kit.checkNumLenByParam(unitValueTotalCny,20,8)', 'ZG04', '净值型产品期末累计净值折人民币', 'unitValueTotalCny', '静态校验', 'D20.2小数：字符最大长度20，保留8位小数', '【净值型产品期末累计净值折人民币】数据长度错误,D20.2小数：字符最大长度20，保留8位小数!', '01', '17', '1', null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04037','17','1','1','14','str_kit.isNotEmptyByThreeParam(prodValue,prodValueCny,unitValueTotal,unitValueTotalCny)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny','静态检验','【净值型产品期末净值】【净值型产品期末净值折人民币】【净值型产品期末累计净值】任何一个字段值非空时，【净值型产品期末累计净值折人民币】必填！','【净值型产品期末净值】【净值型产品期末净值折人民币】【净值型产品期末累计净值】任何一个字段值非空时，【净值型产品期末累计净值折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04038', '1', '09', 'big_decimal_kit.checkNumLenByParam(prodEndYield,20,5)', 'ZG04', '产品期末业绩表现', 'prodEndYield', '静态校验', 'D10.5小数：字符最大长度20，保留5位小数', '【产品期末业绩表现】数据长度错误,D10.5小数：字符最大长度20，保留5位小数!', '01', '18', '1', null, null, null, null);



INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04042','ZG0138','1','1','07','str_kit.isNotEmpty(pbcProdId)','ZG04','产品代码','pbcProdId','静态校验','【产品代码】不可为空！','【产品代码】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04043','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(raisingAmtsumCny,raisingQty,raisingAmtsum)','ZG04','当期申购金额','raisingAmtsum','静态检验','【当期申购金额折人民币】【当期申购份额】任何一个字段值非空时，【当期申购金额】必填！','【当期申购金额折人民币】【当期申购份额】任何一个值非空时，【当期申购金额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04044','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(raisingAmtsum,raisingQty,raisingAmtsumCny)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','【当期申购金额】【当期申购份额】任何一个字段值非空时，【当期申购金额折人民币】必填！','【当期申购金额】【当期申购份额】任何一个值非空时，【当期申购金额折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04045','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(raisingAmtsumCny,raisingAmtsum,raisingQty)','ZG04','当期申购份额','raisingQty','静态检验','【当期申购金额折人民币】【当期申购金额】任何一个字段值非空时，【当期申购份额】必填！','【当期申购金额折人民币】【当期申购金额】任何一个字段值非空时，【当期申购份额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04046','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(redqty,redamtCny,redamt)','ZG04','当期兑付/赎回金额','redamt','静态检验','【当期兑付/赎回份额】【当期兑付/赎回金额折人民币】任何一个字段值非空时，【当期兑付/赎回金额】必填！','【当期兑付/赎回金额】【当期兑付/赎回金额折人民币】任何一个字段值非空时，【当期兑付/赎回金额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04047','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(redamt,redqty,redamtCny)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','【当期兑付/赎回金额】【当期兑付/赎回份额】任何一个字段值非空时，【当期兑付/赎回金额折人民币】必填！','【当期兑付/赎回金额】【当期兑付/赎回份额】任何一个字段值非空时，【当期兑付/赎回金额折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04048','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(redamtCny,redamt,redqty)','ZG04','当期兑付/赎回份额','redqty','静态检验','【当期兑付/赎回金额】【当期兑付/赎回金额折人民币】任何一个字段值非空时，【当期兑付/赎回份额】必填！','【当期兑付/赎回金额】【当期兑付/赎回金额折人民币】任何一个字段值非空时，【当期兑付/赎回份额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04049','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(endamtsumCny,endqty,endamtsum)','ZG04','期末产品金额','endamtsum','静态检验','【期末产品金额折人民币】【期末产品份额】任何一个字段值非空时，【期末产品金额】必填！','【期末产品金额折人民币】【期末产品份额】任何一个字段值非空时，【期末产品金额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04050','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(endamtsum,endamtsumCny,endamtsumCny)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','【期末产品金额】【期末产品份额】任何一个字段值非空时，【期末产品金额折人民币】必填！','【期末产品金额】【期末产品份额】任何一个字段值非空时，【期末产品金额折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04051','ZG0139','1','1','14','str_kit.isNotEmptyByTwoParam(endamtsum,endamtsumCny,endqty)','ZG04','期末产品份额','endqty','静态检验','【期末产品金额】【期末产品金额折人民币】任何一个字段值非空时，【期末产品份额】必填！','【期末产品金额】【期末产品金额折人民币】任何一个字段值非空时，【期末产品份额】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04052','ZG0139','1','1','11','str_kit.isNotEmptyByCond2(raisingAmtsum,buyPlace)','ZG04','地区','buyPlace','静态检验','当“当期申购金额”有值时，“地区”不能为空','报送当期申购时，“地区”不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04053','ZG0139','1','1','11','str_kit.isNotEmptyByCond2(redamt,buyPlace)','ZG04','地区','buyPlace','静态检验','当“当期兑付/赎回金额”有值时，“地区”不能为空','报送当期兑付/赎回时，“地区”不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04054','ZG0139','1','1','11','str_kit.isNotEmptyByCond2(endamtsum,buyPlace)','ZG04','地区','buyPlace','静态检验','当“期末产品金额”有值时，“地区”不能为空','报送期末余额时，“地区”不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04055','ZG0145','1','1','10','enum_kit.permilIsValid(prodEndYield)','ZG04','产品期末业绩表现','prodEndYield','静态校验','产品期末业绩表现不能包含‰或%','产品期末业绩表现不能包含‰或%！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04056','ZG0146','3','2','10','enum_kit.isErrorDictCode(ADMINISTRATIVE_TYPE1,buyPlace)','ZG04','地区','buyPlace','15','境内地区采用《中华人民共和国行政区划代码》（GB/T2260）的省级数字码，具体代码见码值表“Z0013”。境外地区前3位用“000”填充，后3位采用《世界各国和地区名称代码》（GB/T2659）最新标准的3位国别阿拉伯数字代码（港澳台地区暂采用该标准编码），具体代码见码值表“Z0011”。','【地区】数据不在值域范围内!','01', 'ADMINISTRATIVE_TYPE1', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, pbc_asset_code, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04057', 'ZG0147', '1', '10', 'enum_kit.isErrorDictCode(SVR_PBC_COL_CUSTTYPE,customerType1)', 'ZG04', '客户类型', 'customerType1', '15', '1：住户2：广义政府3：非金融企业4：金融机构(实体)5：特定目的载体6：境外如涉及多个客户类型，按客户类型进行拆分报送', '【客户类型】数据不在值域范围内!', '01', '1', 'SVR_PBC_COL_CUSTTYPE', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04058','ZG0148','3','2','10','enum_kit.isErrorDictCode(CURRENCY_PBC,ccy)','ZG04','币种','ccy','15','采用《表示货币和资金的代码》(GB/T12406)最新标准中的三位字母型代码。具体代码见码值表“Z0003”','【币种】数据不在值域范围内!','01', 'CURRENCY_PBC', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04059','ZG0151','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,raisingAmtsumCny,raisingAmtsum)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','当币种为CNY时，“当期申购金额”数据=“当期申购金额折人民币”数据！','当币种为CNY时，“当期申购金额”数据≠“当期申购金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04060','ZG0151','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,raisingAmtsumCny,raisingAmtsum)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','当币种为BWB时，“当期申购金额”数据=“当期申购金额折人民币”数据！','当币种为BWB时，“当期申购金额”数据≠“当期申购金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04061','ZG0152','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,redamtCny,redamt)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','当币种为CNY时，“当期兑付/赎回金额”数据=“当期兑付/赎回金额折人民币”数据','当币种为CNY时，“当期兑付/赎回金额”数据≠“当期兑付/赎回金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04062','ZG0152','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,redamtCny,redamt)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','当币种为BWB时，“当期兑付/赎回金额”数据=“当期兑付/赎回金额折人民币”数据','当币种为BWB时，“当期兑付/赎回金额”数据≠“当期兑付/赎回金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04063','ZG0153','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,endamtsumCny,endamtsum)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','当币种为CNY时，“期末产品金额”数据=“期末产品金额折人民币”数据','当币种为CNY时，“期末产品金额”数据≠“期末产品金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04064','ZG0153','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,endamtsumCny,endamtsum)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','当币种为BWB时，“期末产品金额”数据=“期末产品金额折人民币”数据','当币种为BWB时，“期末产品金额”数据≠“期末产品金额折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04065','ZG0154','3','2','14','big_decimal_kit.conditionEqual(ccy,CNY,prodValueCny,prodValue)','ZG04','净值型产品期末净值折人民币','prodValueCny','静态检验','当币种为CNY时，“净值型产品期末净值”数据=“净值型产品期末净值折人民币”数据','当币种为CNY时，“净值型产品期末净值”数据≠“净值型产品期末净值折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04066','ZG0154','3','2','14','big_decimal_kit.conditionEqual(ccy,BWB,prodValueCny,prodValue)','ZG04','净值型产品期末净值折人民币','prodValueCny','静态检验','当币种为BWB时，“净值型产品期末净值”数据=“净值型产品期末净值折人民币”数据','当币种为BWB时，“净值型产品期末净值”数据≠“净值型产品期末净值折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04067','ZG0155','3','2','14','big_decimal_kit.conditionEqual(ccy,CNY,unitValueTotalCny,unitValueTotal)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny','静态检验','当币种为CNY时，“净值型产品期末累计净值”数据=“净值型产品期末累计净值折人民币”数据','当币种为CNY时，“净值型产品期末累计净值”数据=“净值型产品期末累计净值折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04068','ZG0155','3','2','14','big_decimal_kit.conditionEqual(ccy,BWB,unitValueTotalCny,unitValueTotal)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny','静态检验','当币种为BWB时，“净值型产品期末累计净值”数据=“净值型产品期末累计净值折人民币”数据','当币种为BWB时，“净值型产品期末累计净值”数据=“净值型产品期末累计净值折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04069','ZG0156','3','2','04','big_decimal_kit.greaterOrEqual(raisingAmtsum,0)','ZG04','当期申购金额','raisingAmtsum','静态检验','当期申购金额>=0','当期申购金额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04070','ZG0157','3','2','04','big_decimal_kit.greaterOrEqual(raisingAmtsumCny,0)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','当期申购金额折人民币>=0','当期申购金额折人民币<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04071','ZG0158','3','2','04','big_decimal_kit.greaterOrEqual(raisingQty,0)','ZG04','当期申购份额','raisingQty','静态检验','当期申购份额>=0','当期申购份额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04072','ZG0159','3','2','04','big_decimal_kit.greaterOrEqual(redamt,0)','ZG04','当期兑付/赎回金额','redamt','静态检验','当期兑付/赎回金额>=0','当期兑付/赎回金额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04073','ZG0160','3','2','04','big_decimal_kit.greaterOrEqual(redamtCny,0)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','当期兑付/赎回金额折人民币>=0','当期兑付/赎回金额折人民币<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04074','ZG0161','3','2','04','big_decimal_kit.greaterOrEqual(redqty,0)','ZG04','当期兑付/赎回份额','redqty','静态检验','当期兑付/赎回份额>=0','当期兑付/赎回份额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04075','ZG0162','3','2','04','big_decimal_kit.greaterOrEqual(endamtsum,0)','ZG04','期末产品金额','endamtsum','静态检验','期末产品金额>=0','期末产品金额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04076','ZG0163','3','2','04','big_decimal_kit.greaterOrEqual(endamtsumCny,0)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','期末产品金额折人民币>=0','期末产品金额折人民币<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04077','ZG0164','3','2','04','big_decimal_kit.greaterOrEqual(endqty,0)','ZG04','期末产品份额','endqty','静态检验','期末产品份额>=0','期末产品份额<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04078','ZG0165','3','2','04','big_decimal_kit.greaterTo(prodValue,0)','ZG04','净值型产品期末净值','prodValue',' ','净值型产品期末净值>0','净值型产品报送期末净值或累计净值一般不为0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04079','ZG0166','3','2','04','big_decimal_kit.greaterTo(prodValueCny,0)','ZG04','净值型产品期末净值折人民币','prodValueCny',' ','净值型产品期末净值折人民币>0','净值型产品期末净值折人民币<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04080','ZG0167','3','2','04','big_decimal_kit.greaterTo(unitValueTotal,0)','ZG04','净值型产品期末累计净值','unitValueTotal',' ','净值型产品期末累计净值>0','净值型产品期末累计净值<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04081','ZG0168','3','2','04','big_decimal_kit.greaterTo(unitValueTotalCny,0)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny',' ','净值型产品期末累计净值折人民币>0','净值型产品期末累计净值折人民币<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04082','ZG0169','3','2','04','big_decimal_kit.greaterOrEqual(prodEndYield,0)','ZG04','产品期末业绩表现','prodEndYield',' ','产品期末业绩表现>=0','产品期末业绩表现<0！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04083','ZG0172','3','3','04','big_decimal_kit.greaterOrEqual(unitValueTotal,prodValue)','ZG04','净值型产品期末净值
净值型产品期末累计净值','unitValueTotal',' ','净值型产品期末净值<=净值型产品期末累计净值','净值型产品报送期末净值不应大于累计净值！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04084','ZG0172','3','3','04','big_decimal_kit.greaterOrEqual(unitValueTotalCny,prodValueCny)','ZG04','净值型产品期末净值折人民币
净值型产品期末累计净值折人民币','unitValueTotalCny',' ','净值型产品期末净值折人民币<=净值型产品期末累计净值折人民币','净值型产品报送期末净值不应大于累计净值！','01');



INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04097','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,buyPlace)','ZG04','地区','buyPlace','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04098','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,customerType1)','ZG04','客户类型','customerType1','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04099','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,ccy)','ZG04','币种','ccy','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04100','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,raisingAmtsum)','ZG04','当期申购金额','raisingAmtsum','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04101','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,raisingAmtsumCny)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04102','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,raisingQty)','ZG04','当期申购份额','raisingQty','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04103','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,redamt)','ZG04','当期兑付/赎回金额','redamt','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04104','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,redamtCny)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04105','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,redqty)','ZG04','当期兑付/赎回份额','redqty','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04106','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,endamtsum)','ZG04','期末产品金额','endamtsum','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04107','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,endamtsumCny)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04108','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,endqty)','ZG04','期末产品份额','endqty','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04109','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,prodValue)','ZG04','净值型产品期末净值','prodValue','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04110','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,prodValueCny)','ZG04','净值型产品期末净值折人民币','prodValueCny','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04111','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,unitValueTotal)','ZG04','净值型产品期末累计净值','unitValueTotal','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04112','ZG0186','3','2','15','str_kit.isNotEmptyByCond3(prodEndYield,unitValueTotalCny)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny','静态检验','产品期末业绩表现有数时，本项为空！','产品期末业绩表现有数，地区、客户类型、币种、净值型产品期末净值与累计净值、当期申购、兑付与期末产品金额不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04130','ZG1207','1','1','03','str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','币种','ccy','17','每个产品代码下至少有一条币种为‘BWB’的数据','每个产品代码下至少有一条币种为‘BWB’的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04131','ZG1208','1','1','03','str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','地区','buyPlace','17','每个产品代码下至少有一条地区为’000000‘的数据','每个产品代码下至少有一条地区为‘000000’的数据!','01', 'pbcProdId', 'buyPlace', '000000', null);


-- 补充更新脚本
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",raisingAmtsumCny,raisingAmtsum)' where rule_id = 'DS04059';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",raisingAmtsumCny,raisingAmtsum)' where rule_id = 'DS04060';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",redamtCny,redamt)' where rule_id = 'DS04061';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",redamtCny,redamt)' where rule_id = 'DS04062';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",endamtsumCny,endamtsum)' where rule_id = 'DS04063';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",endamtsumCny,endamtsum)' where rule_id = 'DS04064';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",prodValueCny,prodValue)' where rule_id = 'DS04065';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",prodValueCny,prodValue)' where rule_id = 'DS04066';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",unitValueTotalCny,unitValueTotal)' where rule_id = 'DS04067';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",unitValueTotalCny,unitValueTotal)' where rule_id = 'DS04068';





INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04085','ZG0185','3','2','15','str_kit.conditionListValue2(buyPlace, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','地区','buyPlace','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04086','ZG0185','3','2','15','str_kit.conditionListValue2(customerType1, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','客户类型','customerType1','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04087','ZG0185','3','2','15','str_kit.conditionListValue2(raisingAmtsum, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期申购金额','raisingAmtsum','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04088','ZG0185','3','2','15','str_kit.conditionListValue2(raisingAmtsumCny, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期申购金额折人民币','raisingAmtsumCny','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04089','ZG0185','3','2','15','str_kit.conditionListValue2(raisingQty, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期申购份额','raisingQty','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04090','ZG0185','3','2','15','str_kit.conditionListValue2(redamt, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期兑付/赎回金额','redamt','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04091','ZG0185','3','2','15','str_kit.conditionListValue2(redamtCny, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期兑付/赎回金额折人民币','redamtCny','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04092','ZG0185','3','2','15','str_kit.conditionListValue2(redqty, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','当期兑付/赎回份额','redqty','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04093','ZG0185','3','2','15','str_kit.conditionListValue2(endamtsum, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','期末产品金额','endamtsum','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04094','ZG0185','3','2','15','str_kit.conditionListValue2(endamtsumCny, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','期末产品金额折人民币','endamtsumCny','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04095','ZG0185','3','2','15','str_kit.conditionListValue2(endqty, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','期末产品份额','endqty','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04096','ZG0185','3','2','15','str_kit.conditionListValue2(prodEndYield, prodValue, prodValueCny, unitValueTotal, unitValueTotalCny)','ZG04','产品期末业绩表现','prodEndYield','静态检验','净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有数时，本项为空！','净值型产品期末净值与累计净值有数，地区、客户类型、当期申购、兑付、期末产品金额及产品期末业绩表现不应有数！','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04113','ZG0187','3','2','15','str_kit.conditionListValue3(prodValue, raisingAmtsum, raisingAmtsumCny, raisingQty, redamt, redamtCny, redqty, endamtsum, endamtsumCny, endqty)','ZG04','净值型产品期末净值','prodValue','静态检验','当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值时，本项为空！','当期申购、兑付与期末产品金额有数，净值型产品期末净值与累计净值、产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04114','ZG0187','3','2','15','str_kit.conditionListValue3(prodValueCny, raisingAmtsum, raisingAmtsumCny, raisingQty, redamt, redamtCny, redqty, endamtsum, endamtsumCny, endqty)','ZG04','净值型产品期末净值折人民币','prodValueCny','静态检验','当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值时，本项为空！','当期申购、兑付与期末产品金额有数，净值型产品期末净值与累计净值、产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04115','ZG0187','3','2','15','str_kit.conditionListValue3(unitValueTotal, raisingAmtsum, raisingAmtsumCny, raisingQty, redamt, redamtCny, redqty, endamtsum, endamtsumCny, endqty)','ZG04','净值型产品期末累计净值','unitValueTotal','静态检验','当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值时，本项为空！','当期申购、兑付与期末产品金额有数，净值型产品期末净值与累计净值、产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04116','ZG0187','3','2','15','str_kit.conditionListValue3(unitValueTotalCny, raisingAmtsum, raisingAmtsumCny, raisingQty, redamt, redamtCny, redqty, endamtsum, endamtsumCny, endqty)','ZG04','净值型产品期末累计净值折人民币','unitValueTotalCny','静态检验','当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值时，本项为空！','当期申购、兑付与期末产品金额有数，净值型产品期末净值与累计净值、产品期末业绩表现不应有数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04117','ZG0187','3','2','15','str_kit.conditionListValue3(prodEndYield, raisingAmtsum, raisingAmtsumCny, raisingQty, redamt, redamtCny, redqty, endamtsum, endamtsumCny, endqty)','ZG04','产品期末业绩表现','prodEndYield','静态检验','当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值时，本项为空！','当期申购、兑付与期末产品金额有数，净值型产品期末净值与累计净值、产品期末业绩表现不应有数！','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04008','5','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期申购金额','raisingAmtsum','20','相同的产品代码、客户类型下，地区为“000000”的“当期申购金额”数据=各省级币种为“BWB”的“当期申购金额”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期申购金额”数据≠各省级币种为“BWB”的“当期申购金额”数据和！','01','raisingAmtsum','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04011','6','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期申购金额折人民币','raisingAmtsumCny','20','相同的产品代码、客户类型下，地区为“000000”的“当期申购金额折人民币”数据=各省级币种为“BWB”的“当期申购金额折人民币”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期申购金额折人民币”数据≠各省级币种为“BWB”的“当期申购金额折人民币”数据和！','01','raisingAmtsumCny','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04013','7','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期申购份额','raisingQty','20','相同的产品代码、客户类型下，地区为“000000”的“当期申购份额”数据=各省级币种为“BWB”的“当期申购份额”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期申购份额”数据≠各省级币种为“BWB”的“当期申购份额”数据和！','01','raisingQty','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04016','8','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期兑付/赎回金额','redamt','20','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回金额”数据=各省级币种为“BWB”的“当期兑付/赎回金额”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回金额”数据≠各省级币种为“BWB”的“当期兑付/赎回金额”数据和！','01','redamt','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04018','9','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期兑付/赎回金额折人民币','redamtCny','20','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回金额折人民币”数据=各省级币种为“BWB”的“当期兑付/赎回金额折人民币”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回金额折人民币”数据≠各省级币种为“BWB”的“当期兑付/赎回金额折人民币”数据和！','01','redamtCny','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04020','10','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','当期兑付/赎回份额','redqty','20','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回份额”数据=各省级币种为“BWB”的“当期兑付/赎回份额”数据和','相同的产品代码、客户类型下，地区为“000000”的“当期兑付/赎回份额”数据≠各省级币种为“BWB”的“当期兑付/赎回份额”数据和！','01','redqty','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04022','11','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','期末产品金额','endamtsum','20','相同的产品代码、客户类型下，地区为“000000”的“期末产品金额”数据=各省级币种为“BWB”的“期末产品金额”数据和','相同的产品代码、客户类型下，地区为“000000”的“期末产品金额”数据≠各省级币种为“BWB”的“期末产品金额”数据和！','01','endamtsum','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04026','12','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','期末产品金额折人民币','endamtsumCny','20','相同的产品代码、客户类型下，地区为“000000”的“期末产品金额折人民币”数据=各省级币种为“BWB”的“期末产品金额折人民币”数据和','相同的产品代码、客户类型下，地区为“000000”的“期末产品金额折人民币”数据≠各省级币种为“BWB”的“期末产品金额折人民币”数据和！','01','endamtsumCny','pbcProdId','customerType1');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02,PARAMS03)
VALUES ('DS04028','13','3','2','11','big_decimal_kit.BWBEqualOfPbcprodId3(pbcProdId,customerType1,str_1,str_2,str_3,mapList)','ZG04','期末产品份额','endqty','20','相同的产品代码、客户类型下，地区为“000000”的“期末产品份额”数据=各省级币种为“BWB”的“期末产品份额”数据和','相同的产品代码、客户类型下，地区为“000000”的“期末产品份额”数据≠各省级币种为“BWB”的“期末产品份额”数据和！','01','endqty','pbcProdId','customerType1');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04039','ZG0124','3','2','08','unique_kit.listConditionOneByTwoParam(pbcProdId,ccy,str_1,str_2,str_3,mapList)','ZG04','产品代码币种','pbcProdId','20','相同的产品代码、币种下，只可报送1条净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有值的数据！','净值型产品期末净值+净值型产品期末净值折人民币+净值型产品期末累计净值+净值型产品期末累计净值折人民币不应报送多条！','01', 'pbcProdId', 'ccy', 'prodValue,prodValueCny,unitValueTotal,unitValueTotalCny', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04040','ZG0125','3','2','08','unique_kit.listConditionOneByFourParam(pbcProdId,buyPlace,ccy,customerType1,str_1,str_2,mapList)','ZG04','产品代码地区币种客户类型','pbcProdId','20','相同的产品代码、地区、币种、客户类型下，只可报送1条当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值的数据！','当期申购金额+当期申购金额折人民币+当期申购份额+当期兑付/赎回金额+当期兑付/赎回金额折人民币+当期兑付/赎回份额+ 期末产品金额 +期末产品金额折人民币+期末产品份额不应报送多条！','01', 'pbcProdId,buyPlace,ccy,customerType1', 'raisingAmtsum,raisingAmtsumCny,raisingQty,redamt,redamtCny,redqty,endamtsum,endamtsumCny,endqty', ' ', ' ');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04041','ZG0126','3','2','08','unique_kit.listConditionOne(pbcProdId,prodEndYield,str_1,str_2,mapList)','ZG04','产品期末业绩表现','prodEndYield','20','相同的产品代码下，只可报送1条产品期末业绩表现数据！','产品期末业绩表现不应报送多条！','01', 'pbcProdId', 'prodEndYield', ' ', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS04014','7','3','2','11','big_decimal_kit.notZero2(raisingQty)','ZG04','当期申购份额','raisingQty','静态检验','封闭式净值型产品，非成立月，不能报送当期申购份额！备注：资管产品基本信息表中“运行方式”为“3：封闭式净值型”的产品为封闭式净值型产品；','封闭式净值型产品报送了当期申购份据，请核实是否为特殊情况！','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04118','ZG1195','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,raisingAmtsumCny)','ZG04','当期申购金额折人民币','raisingAmtsumCny','21','相同的产品代码、地区和客户类型下，币种为BWB的当期申购金额折人民币应等于除BWB外的其他各币种当期申购金额折人民币之和','相同的产品代码、地区和客户类型下，币种为BWB的当期申购金额折人民币应等于除BWB外的其他各币种当期申购金额折人民币之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'raisingAmtsumCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04119','ZG1196','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,raisingQty)','ZG04','当期申购份额','raisingQty','21','相同的产品代码、地区和客户类型下，币种为BWB的当期申购份额应等于除BWB外的其他各币种当期申购份额之和','相同的产品代码、地区和客户类型下，币种为BWB的当期申购份额应等于除BWB外的其他各币种当期申购份额之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'raisingQty');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04120','ZG1197','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,redamtCny)','ZG04','当期兑付/赎回金额折人民币','redamtCny','21','相同的产品代码、地区和客户类型下，币种为BWB的当期兑付/赎回金额折人民币应等于除BWB外的其他各币种当期兑付/赎回金额折人民币之和','相同的产品代码、地区和客户类型下，币种为BWB的当期兑付/赎回金额折人民币应等于除BWB外的其他各币种当期兑付/赎回金额折人民币之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'redamtCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04121','ZG1198','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,redqty)','ZG04','当期兑付/赎回份额','redqty','21','相同的产品代码、地区和客户类型下，币种为BWB的当期兑付/赎回份额应等于除BWB外的其他各币种当期兑付/赎回份额之和','相同的产品代码、地区和客户类型下，币种为BWB的当期兑付/赎回份额应等于除BWB外的其他各币种当期兑付/赎回份额之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'redqty');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04122','ZG1199','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,endamtsumCny)','ZG04','期末产品金额折人民币','endamtsumCny','21','相同的产品代码、地区和客户类型下，币种为BWB的期末产品金额折人民币应等于除BWB外的其他各币种期末产品金额折人民币之和','相同的产品代码、地区和客户类型下，币种为BWB的期末产品金额折人民币应等于除BWB外的其他各币种期末产品金额折人民币之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'endamtsumCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04123','ZG1200','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,endqty)','ZG04','期末产品份额','endqty','21','相同的产品代码、地区和客户类型下，币种为BWB的期末产品份额应等于除BWB外的其他各币种期末产品份额之和','相同的产品代码、地区和客户类型下，币种为BWB的期末产品份额应等于除BWB外的其他各币种期末产品份额之和!','01','pbcProdId,buyPlace,customerType1', 'ccy', 'BWB', 'endqty');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04124','ZG1201','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,raisingAmtsumCny)','ZG04','当期申购金额折人民币','raisingAmtsumCny','21','相同的产品代码、币种和客户类型下，地区为000000的当期申购金额折人民币应等于除000000外的其他各地区当期申购金额折人民币之和','相同的产品代码、币种和客户类型下，地区为000000的当期申购金额折人民币应等于除000000外的其他各地区当期申购金额折人民币之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'raisingAmtsumCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04125','ZG1202','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,raisingQty)','ZG04','当期申购份额','raisingQty','21','相同的产品代码、币种和客户类型下，地区为000000的当期申购份额应等于除000000外的其他各地区当期申购份额之和','相同的产品代码、币种和客户类型下，地区为000000的当期申购份额应等于除000000外的其他各地区当期申购份额之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'raisingQty');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04126','ZG1203','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,redamtCny)','ZG04','当期兑付/赎回金额折人民币','redamtCny','21','相同的产品代码、币种和客户类型下，地区为000000的当期兑付/赎回金额折人民币应等于除000000外的其他各地区当期兑付/赎回金额折人民币之和','相同的产品代码、币种和客户类型下，地区为000000的当期兑付/赎回金额折人民币应等于除000000外的其他各地区当期兑付/赎回金额折人民币之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'redamtCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04127','ZG1204','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,redqty)','ZG04','当期兑付/赎回份额','redqty','21','相同的产品代码、币种和客户类型下，地区为000000的当期兑付/赎回份额应等于除000000外的其他各地区当期兑付/赎回份额之和','相同的产品代码、币种和客户类型下，地区为000000的当期兑付/赎回份额应等于除000000外的其他各地区当期兑付/赎回份额之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'redqty');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04128','ZG1205','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,endamtsumCny)','ZG04','期末产品金额折人民币','endamtsumCny','21','相同的产品代码、币种和客户类型下，地区为000000的期末产品金额折人民币应等于除000000外的其他各地区期末产品金额折人民币之和','相同的产品代码、币种和客户类型下，地区为000000的期末产品金额折人民币应等于除000000外的其他各地区期末产品金额折人民币之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'endamtsumCny');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04129','ZG1206','3','2','06','big_decimal_kit.EqualParamsOfKey(TempMap,str_1,str_2,str_3,str_4,mapList,endqty)','ZG04','期末产品份额','endqty','21','相同的产品代码、币种和客户类型下，地区为000000的期末产品份额应等于除000000外的其他各地区期末产品份额之和','相同的产品代码、币种和客户类型下，地区为000000的期末产品份额应等于除000000外的其他各地区期末产品份额之和!','01', 'pbcProdId,ccy,customerType1', 'buyPlace', '000000', 'endqty');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS04024','11','3','2','11','unique_kit.notMultiInList4(pbcProdId,endamtsum,mapList,str_1,str_2)','ZG04','期末产品金额','endamtsum','20','存续产品，至少报送1条期末产品金额有数的数据！备注：存续产品指月末未出“资管产品终止信息”的产品','存续产品，未报送期末产品金额未报送！','01','pbcProdId','endamtsum');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS04029','13','3','2','11','unique_kit.notMultiInList4(pbcProdId,endqty,mapList,str_1,str_2)','ZG04','期末产品份额','endqty','20','存续产品，至少报送1条期末产品份额有数的数据！备注：存续产品指月末未出“资管产品终止信息”的产品','存续产品，未报送期末产品份额未报送！','01','pbcProdId','endqty');


-- 新增需求

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04134','ZG1687','3','2','03','str_kit.listConditionExistsNotEquals(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','币种','ccy','17','该报表每个产品代码下至少有一条币种不为''BWB''的数据','每个产品代码下至少有一条币种不为''BWB''的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04135','ZG1688','3','2','03','str_kit.listConditionExistsNotEquals(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','地区','buyPlace','17','该报表每个产品代码下至少有一条地区不为''000000''的数据','每个产品代码下至少有一条地区不为''000000''的数据!','01', 'pbcProdId', 'buyPlace', '000000', null);


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04132','ZG1682','3','2','03','unique_kit.listConditionTwoByNineParam(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','当期申购金额
当期申购金额折人民币
当期申购份额
当期兑付/赎回金额
当期兑付/赎回金额折人民币
当期兑付/赎回份额
期末产品金额
期末产品金额折人民币
期末产品份额','pbcProdId','20','当期申购金额、当期申购份额、当期兑付/赎回金额、当期兑付/赎回份额、期末产品金额、期末产品份额任何一个字段有数时，该报表每个产品代码下至少有2条“当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额”均不为空的数据','当期申购金额、当期申购份额、当期兑付/赎回金额、当期兑付/赎回份额、期末产品金额、期末产品份额任何一个字段有数时，每个产品代码下至少有2条“当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额”均不为空的数据!','01', 'pbcProdId', 'raisingAmtsum,raisingQty,redamt,redqty,endamtsum,endqty', 'raisingAmtsum,raisingAmtsumCny,raisingQty,redamt,redamtCny,redqty,endamtsum,endamtsumCny,endqty', '');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS04133','ZG1683','3','2','03','unique_kit.listConditionTwoByFourParam(pbcProdId,str_1,str_2,str_3,mapList)','ZG04','净值型产品期末净值
净值型产品期末净值折人民币
净值型产品期末累计净值
净值型产品期末累计净值折人民币','pbcProdId','20','净值型产品期末净值、净值型产品期末累计净值任意一个字段有数时，该报表每个净值型产品代码下至少有2条“净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币”均不为空的数据','净值型产品期末净值、净值型产品期末累计净值任意一个字段有数时，该报表每个净值型产品代码下至少有2条“净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币”均不为空的数据!','01', 'pbcProdId','prodValue,unitValueTotal', 'prodValue,prodValueCny,unitValueTotal,unitValueTotalCny', '');

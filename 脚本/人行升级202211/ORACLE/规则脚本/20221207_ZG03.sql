
DELETE FROM pbc_check_rule_info WHERE table_data_name = 'ZG03';

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03001','1','1','1','09','str_kit.compareEqualToLength(pbcProdId,15)','ZG03','产品代码','pbcProdId','静态校验','C15字符长度15','【产品代码】数据长度错误,C15字符长度15!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03002','2','1','1','09','str_kit.compareEqualToLength(mdate,10)','ZG03','产品实际终止日期','mdate','静态校验','C10字符长度10','【产品实际终止日期】数据长度错误,C10字符长度10!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03003','3','1','1','09','str_kit.compareEqualToLength(ccy,3)','ZG03','币种','ccy','静态校验','C3字符长度3','【币种】数据长度错误,C3字符长度3!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03004','4','1','1','09','big_decimal_kit.checkNumLenByParam(profit,20,2)','ZG03','发行机构实现收入','profit','静态校验','D20.2小数：字符最大长度20，保留2位小数','【发行机构实现收入】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03005','4','3','3','11','big_decimal_kit.greaterOrEqual(profit,0)','ZG03','发行机构实现收入','profit','静态检验','发行机构实现收入>=0','发行机构实现收入<0!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03006','5','1','1','09','big_decimal_kit.checkNumLenByParam(profitCny,20,2)','ZG03','发行机构实现收入折人民币','profitCny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【发行机构实现收入折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03007','5','3','3','11','big_decimal_kit.greaterOrEqual(profitCny,0)','ZG03','发行机构实现收入折人民币','profitCny','静态检验','发行机构实现收入折人民币>=0','发行机构实现收入折人民币<0!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03008','6','1','1','09','big_decimal_kit.checkNumLenByParam(customProfit,20,2)','ZG03','兑付客户收益','customProfit','静态校验','D20.2小数：字符最大长度20，保留2位小数','【兑付客户收益】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03009','7','1','1','09','big_decimal_kit.checkNumLenByParam(customProfitCny,20,2)','ZG03','兑付客户收益折人民币','customProfitCny','静态校验','D20.2小数：字符最大长度20，保留2位小数','【兑付客户收益折人民币】数据长度错误,D20.2小数：字符最大长度20，保留2位小数!','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03010','8','1','1','09','big_decimal_kit.checkNumLenByParam(cusYearYield,10,5)','ZG03','兑付客户收益率','cusYearYield','静态校验','D10.5小数：字符最大长度10，保留5位小数','【兑付客户收益率】数据长度错误,D10.5小数：字符最大长度10，保留5位小数!','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03013','ZG0102','1','1','07','str_kit.isNotEmpty(pbcProdId)','ZG03','产品代码','pbcProdId','静态校验','【产品代码】不可为空！','【产品代码】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03014','ZG0103','1','1','07','str_kit.isNotEmpty(mdate)','ZG03','产品实际终止日期','mdate','静态校验','【产品实际终止日期】不可为空！','【产品实际终止日期】不可为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03015','ZG0104','1','1','14','str_kit.isEmptyByCond3(cusYearYield,ccy)','ZG03','币种','ccy','静态校验','【兑付客户收益率】字段无值时【币种】必填！','【兑付客户收益率】字段无值时【币种】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03016','ZG0105','1','1','14','str_kit.isEmptyByCond3(cusYearYield,profit)','ZG03','发行机构实现收入','profit','静态校验','【兑付客户收益率】字段无值时【发行机构实现收入】必填！','【兑付客户收益率】字段无值时【发行机构实现收入】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03017','ZG0106','1','1','14','str_kit.isEmptyByCond3(cusYearYield,profitCny)','ZG03','发行机构实现收入折人民币','profitCny','静态校验','【兑付客户收益率】字段无值时【发行机构实现收入折人民币】必填！','【兑付客户收益率】字段无值时【发行机构实现收入折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03018','ZG0108','1','1','14','str_kit.isEmptyByCond3(cusYearYield,customProfit)','ZG03','兑付客户收益','customProfit','静态校验','【兑付客户收益率】字段无值时【兑付客户收益】必填！','【兑付客户收益率】字段无值时【兑付客户收益】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03019','ZG0109','1','1','14','str_kit.isEmptyByCond3(cusYearYield,customProfitCny)','ZG03','兑付客户收益折人民币','customProfitCny','静态校验','【兑付客户收益率】字段无值时【兑付客户收益折人民币】必填！','【兑付客户收益率】字段无值时【兑付客户收益折人民币】必填！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03021','ZG0111','1','1','13','date_kit.betweenDate(mdate)','ZG03','产品实际终止日期','mdate','静态校验','产品实际终止日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间','产品实际终止日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03022','ZG0112','1','1','10','enum_kit.permilIsValid(cusYearYield)','ZG03','兑付客户收益率','cusYearYield','静态校验','兑付客户收益率不能包含‰或%','兑付客户收益率不能包含‰或%！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS03023','ZG0113','3','2','10','enum_kit.isErrorDictCode(CURRENCY_PBC,ccy)','ZG03','币种','ccy','15','采用《表示货币和资金的代码》（GB/T12406）最新标准中的三位字母型代码。具体代码见码值表“Z0003”。如涉及多个币种，按币种进行拆分报送。除分币种数据外，每只资管产品均需同时报送本外币合计数据，币种填报“BWB”。如币种为人民币、美元，则需要按“CNY”、“USD”、“BWB”拆分报送。报送兑付客户收益率时，本项不填。','【币种】数据不在值域范围内!','01', 'CURRENCY_PBC', null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03024','ZG0115','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,profitCny,profit)','ZG03','发行机构实现收入折人民币','profitCny','静态检验','当币种为CNY时，“发行机构实现收入”数据=“发行机构实现收入折人民币”数据！','当币种为CNY时，“发行机构实现收入”数据≠“发行机构实现收入折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03025','ZG0115','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,profitCny,profit)','ZG03','发行机构实现收入折人民币','profitCny','静态检验','当币种为BWB时，“发行机构实现收入”数据=“发行机构实现收入折人民币”数据！','当币种为BWB时，“发行机构实现收入”数据≠“发行机构实现收入折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03026','ZG0116','3','2','11','big_decimal_kit.conditionEqual(ccy,CNY,customProfitCny,customProfit)','ZG03','兑付客户收益折人民币','customProfitCny','静态检验','当币种为CNY时，“兑付客户收益”数据=“兑付客户收益折人民币”数据！','当币种为CNY时，“兑付客户收益”数据≠“兑付客户收益折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03027','ZG0116','3','2','11','big_decimal_kit.conditionEqual(ccy,BWB,customProfitCny,customProfit)','ZG03','兑付客户收益折人民币','customProfitCny','静态检验','当币种为BWB时，“兑付客户收益”数据=“兑付客户收益折人民币”数据！','当币种为BWB时，“兑付客户收益”数据≠“兑付客户收益折人民币”数据！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03028','ZG0119','3','3','11','big_decimal_kit.greaterTo(customProfit,0)','ZG03','兑付客户收益','customProfit','静态检验','兑付客户收益>0！','兑付客户收益不能为零或负数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03029','ZG0119','3','3','11','big_decimal_kit.greaterTo(customProfitCny,0)','ZG03','兑付客户收益折人民币','customProfitCny','静态检验','兑付客户收益折人民币>0！','兑付客户收益折人民币不能为零或负数！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03030','ZG0120','3','3','11','big_decimal_kit.betweenIn(cusYearYield,1,10)','ZG03','兑付客户收益率','cusYearYield','静态检验','1%<=兑付客户收益率<=10%！','兑付客户收益率过高大于10%，或者过低小于1%！','01');

-- 发行机构实现收入、发行机构实现收入折人民币、兑付客户收益、兑付客户收益折人民币有数时，本项不填。
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03031','ZG0121','3','3','15','big_decimal_kit.paramNotNullTarget(profit,profitCny,customProfit,customProfitCny,cusYearYield)','ZG03','兑付客户收益率','cusYearYield','静态校验','发行机构实现收入、发行机构实现收入折人民币、兑付客户收益、兑付客户收益折人民币有数时，本项不填。','发行机构收入与兑付有数时，兑付客户收益率不应有数！','01');


INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03032','ZG0122','3','3','15','str_kit.isNotEmptyByCond3(cusYearYield,ccy)','ZG03','币种','ccy','静态校验','报送兑付客户收益率时，本项不填。','当兑付客户收益率有值，币种为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03033','ZG0122','3','3','15','str_kit.isNotEmptyByCond3(cusYearYield,profit)','ZG03','发行机构实现收入','profit','静态校验','报送兑付客户收益率时，本项不填。','当兑付客户收益率有值，发行机构实现收入为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03034','ZG0122','3','3','15','str_kit.isNotEmptyByCond3(cusYearYield,profitCny)','ZG03','发行机构实现收入折人民币','profitCny','静态校验','报送兑付客户收益率时，本项不填。','当兑付客户收益率有值，发行机构实现收入折人民币为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03035','ZG0122','3','3','15','str_kit.isNotEmptyByCond3(cusYearYield,customProfit)','ZG03','兑付客户收益','customProfit','静态校验','报送兑付客户收益率时，本项不填。','当兑付客户收益率有值，兑付客户收益为空！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS03036','ZG0122','3','3','15','str_kit.isNotEmptyByCond3(cusYearYield,customProfitCny)','ZG03','兑付客户收益折人民币','customProfitCny','静态校验','报送兑付客户收益率时，本项不填。','当兑付客户收益率有值，兑付客户收益折人民币为空！','01');

-- 兑付客户收益、兑付客户收益率符号不一致！
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS03037','ZG0123','3','3','11','big_decimal_kit.compareSymbol(pbcProdId,customProfit,c1,c2,mapList)','ZG03','兑付客户收益','customProfit','14','兑付客户收益、兑付客户收益率值,正负符号必须相同！','兑付客户收益、兑付客户收益率符号不一致！','01', 'cusYearYield','pbcProdId', null, null);

-- 每个产品代码下至少有一条币种为'BWB'的数据
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS03040','ZG1194','1','1','03','str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)','ZG03','币种','ccy','17','每个产品代码下至少有一条币种为‘BWB’的数据','每个产品代码下至少有一条币种为’BWB‘的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS03041','ZG1655','1','1','04','date_kit.compareWithSysdate(mdate,str_m)','ZG03','产品实际终止日期','mdate','16','产品实际终止日期应小于或等于系统日期','产品实际终止日期应小于或等于系统日期!','01', '<=', null, null, null);

-- 补充更新脚本 部分字段携带双引号
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",profitCny,profit)' where rule_id = 'DS03024';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",profitCny,profit)' where rule_id = 'DS03025';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"CNY",customProfitCny,customProfit)' where rule_id = 'DS03026';
update pbc_check_rule_info set rule_param = 'big_decimal_kit.conditionEqual(ccy,"BWB",customProfitCny,customProfit)' where rule_id = 'DS03027';

-- 补充复杂脚本
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS03038','ZG1192','3','2','06','big_decimal_kit.BWBEqualOfPbcprodId2(pbcProdId,c1,c2,mapList)','ZG03','发行机构实现收入折人民币','profitCny','14','相同的产品代码下，币种为BWB的发行机构实现收入折人民币应等于除BWB外的其他各币种发行机构实现收入折人民币之和','相同的产品代码下，币种为BWB的发行机构实现收入折人民币应等于除BWB外的其他各币种发行机构实现收入折人民币之和!','01','profitCny','pbcProdId');
INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS03039','ZG1193','3','2','06','big_decimal_kit.BWBEqualOfPbcprodId2(pbcProdId,c1,c2,mapList)','ZG03','兑付客户收益折人民币','customProfitCny','14','相同的产品代码下，币种为BWB的兑付客户收益折人民币应等于除BWB外的其他各币种兑付客户收益折人民币之和','相同的产品代码下，币种为BWB的兑付客户收益折人民币应等于除BWB外的其他各币种兑付客户收益折人民币之和!','01','customProfitCny','pbcProdId');



INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS03011','ZG0097','3','2','08','unique_kit.notMultiInList1(mapList,pbcProdId,cusYearYield,str_1,str_2)','ZG03','产品代码','pbcProdId','20','产品终止信息中同一产品收益率数据不能存在多条记录！','产品终止信息中同一产品收益率数据不能存在多条记录！','01','pbcProdId','cusYearYield');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01,PARAMS02)
VALUES ('DS03012','ZG0098','3','2','08','unique_kit.notMultiInList2(mapList, pbcProdId,str_1,str_2,ccy)','ZG03','产品代码币种','pbcProdId','21','产品终止信息中同一产品代码和币种不能存在多条记录！','产品终止信息中同一产品代码和币种不能存在多条记录！','01', 'pbcProdId','ccy');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart,PARAMS01)
VALUES ('DS03020','ZG0110','1','1','11','unique_kit.notMultiInList3(mapList,str_1)','ZG03','兑付客户收益率','cusYearYield','20','终止的产品必须报送1条兑付客户收益数据。','兑付客户收益率漏报！','01','cusYearYield');

-- 新增需求

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS03042','ZG1686','3','2','03','str_kit.listConditionExistsNotEquals(pbcProdId,str_1,str_2,str_3,mapList)','ZG03','币种','ccy','17','该报表每个产品代码下至少有一条币种不为''BWB''的数据','每个产品代码下至少有一条币种不为''BWB''的数据!','01', 'pbcProdId', 'ccy', 'BWB', null);

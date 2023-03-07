-- 增加 数据唯一标识
ALTER TABLE pbc_check_rule_info ADD DATA_ID varchar(200) DEFAULT NULL COMMENT '数据唯一标识';

update pbc_check_rule_info set DATA_ID = '[产品代码;信息类型;发行机构代码;起息日;数据状态]' WHERE table_data_name = 'ZG01';
update pbc_check_rule_info set DATA_ID = '[产品代码;币种;地区;客户类型;起息日]' WHERE table_data_name = 'ZG02';
update pbc_check_rule_info set DATA_ID = '[产品代码;币种;兑付客户收益率;到期日]' WHERE table_data_name = 'ZG03';
update pbc_check_rule_info set DATA_ID = '[产品代码;币种;地区;客户类型;统计年月]' WHERE table_data_name = 'ZG04';
update pbc_check_rule_info set DATA_ID = '[产品代码;币种;数据类型;统计年月]' WHERE table_data_name = 'ZG05';
update pbc_check_rule_info set DATA_ID = '[产品代码;资产负债项目;资产收益权内部编码;统计年月]' WHERE table_data_name = 'ZG06';
update pbc_check_rule_info set DATA_ID = '[产品代码;贷款借据编码;统计年月]' WHERE table_data_name = 'ZG07';
update pbc_check_rule_info set DATA_ID = '[产品代码;资产负债项目;交易对手产品种类交易对手产品代码;统计年月]' WHERE table_data_name = 'ZG08';
update pbc_check_rule_info set DATA_ID = '[发行机构代码;统计年月]' WHERE table_data_name = 'ZG09';
update pbc_check_rule_info set DATA_ID = '[发行机构代码;统计年月]' WHERE table_data_name = 'ZG10';
update pbc_check_rule_info set DATA_ID = '[发行机构代码;统计年月]' WHERE table_data_name = 'ZG11';
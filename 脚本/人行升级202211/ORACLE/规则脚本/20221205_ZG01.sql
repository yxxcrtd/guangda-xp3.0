DELETE FROM pbc_check_rule_info WHERE table_data_name = 'ZG01';

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01001', '1', '09', 'str_kit.compareEqualToLength(infoType,1)', 'ZG01', '信息类型', 'infoType', '静态校验', 'c1字符长度为1', '【信息类型】数据长度错误！c1字符长度为1!', '01', '1', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01002', '1', '09', 'str_kit.compareEqualToLength(pbcProdId,15)', 'ZG01', '产品代码', 'pbcProdId', '静态校验', 'C15字符长度为15', '【产品代码】数据长度错误！C15字符长度为15!', '01', '2', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01003', '1', '09', 'str_kit.compareLength(prodName,120)', 'ZG01', '产品名称', 'prodName', '静态校验', 'C..120字符最大长度为120', '【产品名称】数据长度错误！C..120字符最大长度为120!', '01', '3', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01004', '1', '09', 'str_kit.compareEqualToLength(issuePartyId,14)', 'ZG01', '发行机构代码', 'issuePartyId', '静态校验', 'C14字符长度为14', '【发行机构代码】数据长度错误！C14字符长度为14!', '01', '4', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01005', '1', '09', 'str_kit.compareLength(issuePartyName,200)', 'ZG01', '发行机构名称', 'issuePartyName', '静态校验', 'C..200字符最大长度为200', '【发行机构名称】数据长度错误！C..200字符最大长度为200!', '01', '5', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01006', '1', '09', 'str_kit.compareEqualToLength(pbcProdType,1)', 'ZG01', '产品品种', 'pbcProdType', '静态校验', 'c1字符长度为1', '【产品品种】数据长度错误！c1字符长度为1!', '01', '6', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01007', '1', '09', 'str_kit.compareEqualToLength(ivstorientation,1)', 'ZG01', '产品类型', 'ivstorientation', '静态校验', 'c1字符长度为1', '【产品类型】数据长度错误！c1字符长度为1!', '01', '7', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01008', '1', '09', 'str_kit.compareLength(prodBrand,120)', 'ZG01', '产品品牌', 'prodBrand', '静态校验', 'C..120字符最大长度为120', '【产品品牌】数据长度错误！C..120字符最大长度为120!', '01', '8', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01009', '1', '09', 'str_kit.compareLength(prodStage,4)', 'ZG01', '产品期次', 'prodStage', '静态校验', 'C..4字符最大长度为4', '【产品期次】数据长度错误！C..4字符最大长度为4!', '01', '9', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01010', '1', '09', 'str_kit.compareLength(prodId,60)', 'ZG01', '发行机构内部产品代码', 'prodId', '静态校验', 'C..60字符最大长度为60', '【发行机构内部产品代码】数据长度错误！C..60字符最大长度为60!', '01', '10', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01011', '1', '09', 'str_kit.compareLength(raiseCcy,100)', 'ZG01', '募集资金币种', 'raiseCcy', '静态校验', 'C..100字符最大长度为100', '【募集资金币种】数据长度错误！C..100字符最大长度为100!', '01', '11', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01012', '1', '09', 'str_kit.compareLength(cashPrinCcy,100)', 'ZG01', '兑付本金币种', 'cashPrinCcy', '静态校验', 'C..100字符最大长度为100', '【兑付本金币种】数据长度错误！C..100字符最大长度为100!', '01', '12', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01013', '1', '09', 'str_kit.compareLength(cashProfCcy,100)', 'ZG01', '兑付收益币种', 'cashProfCcy', '静态校验', 'C..100字符最大长度为100', '【兑付收益币种】数据长度错误！C..100字符最大长度为100!', '01', '13', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01014', '1', '09', 'str_kit.compareEqualToLength(raiseType,1)', 'ZG01', '募集方式', 'raiseType', '静态校验', 'c1字符长度为1', '【募集方式】数据长度错误！c1字符长度为1!', '01', '14', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01015', '1', '09', 'str_kit.compareEqualToLength(manageType,1)', 'ZG01', '管理方式', 'manageType', '静态校验', 'c1字符长度为1', '【管理方式】数据长度错误！c1字符长度为1!', '01', '15', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01016', '1', '09', 'str_kit.compareEqualToLength(operateMode,1)', 'ZG01', '运行方式', 'operateMode', '静态校验', 'c1字符长度为1', '【运行方式】数据长度错误！c1字符长度为1!', '01', '16', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01017', '1', '09', 'str_kit.compareEqualToLength(raiseVdate,10)', 'ZG01', '募集起始日期', 'raiseVdate', '静态校验', 'c10字符长度为10', '【募集起始日期】数据长度错误！c10字符长度为10!', '01', '17', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01018', '1', '09', 'str_kit.compareEqualToLength(raiseEnddate,10)', 'ZG01', '募集结束日期', 'raiseEnddate', '静态校验', 'c10字符长度为10', '【募集结束日期】数据长度错误！c10字符长度为10!', '01', '18', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01019', '1', '09', 'str_kit.compareEqualToLength(termRightFlag,1)', 'ZG01', '发行机构提前终止权标识', 'termRightFlag', '静态校验', 'c1字符长度为1', '【发行机构提前终止权标识】数据长度错误！c1字符长度为1!', '01', '19', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01020', '1', '09', 'str_kit.compareEqualToLength(cusRedeemFlag,1)', 'ZG01', '客户赎回权标识', 'cusRedeemFlag', '静态校验', 'c1字符长度为1', '【客户赎回权标识】数据长度错误！c1字符长度为1!', '01', '20', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01021', '1', '09', 'str_kit.compareEqualToLength(creditFlag,1)', 'ZG01', '产品增信标识', 'creditFlag', '静态校验', 'c1字符长度为1', '【产品增信标识】数据长度错误！c1字符长度为1!', '01', '21', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01022', '1', '09', 'str_kit.compareLength(creditType,30)', 'ZG01', '增信机构类型', 'creditType', '静态校验', 'C..30字符最大长度为30', '【增信机构类型】数据长度错误！C..30字符最大长度为30!', '01', '22', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01023', '1', '09', 'str_kit.compareLength(creditMode,30)', 'ZG01', '增信形式', 'creditMode', '静态校验', 'C..30字符最大长度为30', '【增信形式】数据长度错误！C..30字符最大长度为30!', '01', '23', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01024', '1', '09', 'str_kit.compareEqualToLength(inDepCode,14)', 'ZG01', '境内托管机构代码', 'inDepCode', '静态校验', 'c14字符长度为14', '【境内托管机构代码】数据长度错误！c14字符长度为14!', '01', '24', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01025', '1', '09', 'str_kit.compareEqualToLength(outDepCtry,6)', 'ZG01', '境外托管机构国别代码', 'outDepCtry', '静态校验', 'C6字符长度为6', '【境外托管机构国别代码】数据长度错误！C6字符长度为6!', '01', '25', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01026', '1', '09', 'str_kit.compareLength(outDepName,200)', 'ZG01', '境外托管机构名称', 'outDepName', '静态校验', 'C..200字符最大长度为200', '【境外托管机构名称】数据长度错误！C..200字符最大长度为200!', '01', '26', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01027', '1', '09', 'str_kit.compareEqualToLength(vdate,10)', 'ZG01', '产品起始日期', 'vdate', '静态校验', 'c10字符长度为10', '【产品起始日期】数据长度错误！c10字符长度为10!', '01', '27', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01028', '1', '09', 'str_kit.condtionLenth(infoType,2,cdate,10)', 'ZG01', '产品变更日期', 'cdate', '静态校验', 'c10字符长度为10', '【产品变更日期】数据长度错误！c10字符长度为10!', '01', '28', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01029', '1', '13', 'date_kit.betweenDate(cdate)', 'ZG01', '产品变更日期', 'cdate', '静态校验', '变更日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '变更日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '01', '28', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01030', '1', '09', 'str_kit.compareEqualToLength(mdate,10)', 'ZG01', '产品预计终止日期', 'mdate', '静态校验', 'c10字符长度为10', '【产品预计终止日期】数据长度错误！c10字符长度为10!', '01', '29', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01031', '1', '09', 'str_kit.compareEqualToLength(fiduciaryDuty,1)', 'ZG01', '受托职责', 'fiduciaryDuty', '静态校验', 'c1字符长度为1', '【受托职责】数据长度错误！c1字符长度为1!', '01', '30', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01032', '1', '09', 'str_kit.compareEqualToLength(isConstracte,1)', 'ZG01', '分级产品标识', 'isConstracte', '静态校验', 'c1字符长度为1', '【分级产品标识】数据长度错误！c1字符长度为1!', '01', '31', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01033', '1', '09', 'str_kit.compareEqualToLength(transferType,1)', 'ZG01', '收益权转让产品标识', 'transferType', '静态校验', 'c1字符长度为1', '【收益权转让产品标识】数据长度错误！c1字符长度为1!', '01', '32', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01034', '1', '09', 'str_kit.compareEqualToLength(cashManaCate,1)', 'ZG01', '货基或现金管理类产品标识', 'cashManaCate', '静态校验', 'c1字符长度为1', '【货基或现金管理类产品标识】数据长度错误！c1字符长度为1!', '01', '33', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01035', '1', '09', 'str_kit.compareEqualToLength(crossBorderFlag,1)', 'ZG01', '跨境理财通', 'crossBorderFlag', '静态校验', 'c1字符长度为1', '【跨境理财通】数据长度错误！c1字符长度为1!', '01', '34', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01036', '1', '09', 'str_kit.compareEqualToLength(isPropertyFlag,1)', 'ZG01', '以财产权信托名义开展的资金信托', 'isPropertyFlag', '静态校验', 'c1字符长度为1', '【以财产权信托名义开展的资金信托】数据长度错误！c1字符长度为1!', '01', '35', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01037', '1', '09', 'str_kit.compareEqualToLength(baseInfoSign,1)', 'ZG01', '基本信息公开标识', 'baseInfoSign', '静态校验', 'c1字符长度为1', '【基本信息公开标识】数据长度错误！c1字符长度为1!', '01', '36', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01038', '1', '09', 'str_kit.compareLength(changeReason,200)', 'ZG01', '变更原因', 'changeReason', '静态校验', 'C..200字符最大长度为200', '【变更原因】数据长度错误！C..200字符最大长度为200!', '01', '37', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01039', '1', '07', 'str_kit.isNotEmpty(infoType)', 'ZG01', '信息类型', 'infoType', '静态校验', '【信息类型】不可为空!', '【信息类型】不可为空!', '01', 'ZG0006', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01040', '1', '07', 'str_kit.isNotEmpty(pbcProdId)', 'ZG01', '产品代码', 'pbcProdId', '静态校验', '【产品代码】不可为空!', '【产品代码】不可为空!', '01', 'ZG0007', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01041', '1', '07', 'str_kit.isNotEmpty(prodName)', 'ZG01', '产品名称', 'prodName', '静态校验', '【产品名称】不可为空!', '【产品名称】不可为空!', '01', 'ZG0008', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01042', '1', '07', 'str_kit.isNotEmpty(issuePartyId)', 'ZG01', '发行机构代码', 'issuePartyId', '静态校验', '【发行机构代码】不可为空!', '【发行机构代码】不可为空!', '01', 'ZG0009', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01043', '1', '07', 'str_kit.isNotEmpty(issuePartyName)', 'ZG01', '发行机构名称', 'issuePartyName', '静态校验', '【发行机构名称】不可为空!', '【发行机构名称】不可为空!', '01', 'ZG0010', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01044', '1', '07', 'str_kit.isNotEmpty(pbcProdType)', 'ZG01', '产品品种', 'pbcProdType', '静态校验', '【产品品种】不可为空!', '【产品品种】不可为空!', '01', 'ZG0011', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01045', '1', '07', 'str_kit.isNotEmpty(ivstorientation)', 'ZG01', '产品类型', 'ivstorientation', '静态校验', '【产品类型】不可为空!', '【产品类型】不可为空!', '01', 'ZG0012', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01046', '1', '07', 'str_kit.isNotEmpty(raiseCcy)', 'ZG01', '募集资金币种', 'raiseCcy', '静态校验', '【募集资金币种】不可为空!', '【募集资金币种】不可为空!', '01', 'ZG0014', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01047', '1', '07', 'str_kit.isNotEmpty(cashPrinCcy)', 'ZG01', '兑付本金币种', 'cashPrinCcy', '静态校验', '【兑付本金币种】不可为空!', '【兑付本金币种】不可为空!', '01', 'ZG0015', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01048', '1', '07', 'str_kit.isNotEmpty(cashProfCcy)', 'ZG01', '兑付收益币种', 'cashProfCcy', '静态校验', '【兑付收益币种】不可为空!', '【兑付收益币种】不可为空!', '01', 'ZG0016', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01049', '1', '07', 'str_kit.isNotEmpty(raiseType)', 'ZG01', '募集方式', 'raiseType', '静态校验', '【募集方式】不可为空!', '【募集方式】不可为空!', '01', 'ZG0017', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01050', '1', '07', 'str_kit.isNotEmpty(manageType)', 'ZG01', '管理方式', 'manageType', '静态校验', '【管理方式】不可为空!', '【管理方式】不可为空!', '01', 'ZG0018', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01051', '1', '07', 'str_kit.isNotEmpty(operateMode)', 'ZG01', '运行方式', 'operateMode', '静态校验', '【运行方式】不可为空!', '【运行方式】不可为空!', '01', 'ZG0019', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01052', '1', '07', 'str_kit.isNotEmpty(raiseVdate)', 'ZG01', '募集起始日期', 'raiseVdate', '静态校验', '【募集起始日期】不可为空!', '【募集起始日期】不可为空!', '01', 'ZG0020', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01053', '1', '07', 'str_kit.isNotEmpty(raiseEnddate)', 'ZG01', '募集结束日期', 'raiseEnddate', '静态校验', '【募集结束日期】不可为空!', '【募集结束日期】不可为空!', '01', 'ZG0021', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01054', '1', '07', 'str_kit.isNotEmpty(termRightFlag)', 'ZG01', '发行机构提前终止权标识', 'termRightFlag', '静态校验', '【发行机构提前终止权标识】不可为空!', '【发行机构提前终止权标识】不可为空!', '01', 'ZG0022', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01055', '1', '07', 'str_kit.isNotEmpty(cusRedeemFlag)', 'ZG01', '客户赎回权标识', 'cusRedeemFlag', '静态校验', '【客户赎回权标识】不可为空!', '【客户赎回权标识】不可为空!', '01', 'ZG0023', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01056', '1', '07', 'str_kit.isNotEmpty(creditFlag)', 'ZG01', '产品增信标识', 'creditFlag', '静态校验', '【产品增信标识】不可为空!', '【产品增信标识】不可为空!', '01', 'ZG0024', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01057', '1', '14', 'str_kit.isNotEmptyByCond(creditFlag,1,creditType)', 'ZG01', '增信机构类型', 'creditType', '静态检验', '当产品增信标识为是，增信机构类型不可为空！', '当产品增信标识为是，增信机构类型不可为空！', '01', 'ZG0025', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01058', '1', '14', 'str_kit.isNotEmptyByCond(creditFlag,1,creditMode)', 'ZG01', '增信形式', 'creditMode', '静态检验', '当产品增信标识为是，增信形式不可为空！', '当产品增信标识为是，增信形式不可为空！', '01', 'ZG0026', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01059', '1', '07', 'str_kit.isNotEmpty(vdate)', 'ZG01', '产品起始日期', 'vdate', '静态校验', '【产品起始日期】不可为空!', '【产品起始日期】不可为空!', '01', 'ZG0027', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01060', '1', '07', 'str_kit.isNotEmpty(fiduciaryDuty)', 'ZG01', '受托职责', 'fiduciaryDuty', '静态校验', '【受托职责】不可为空!', '【受托职责】不可为空!', '01', 'ZG0028', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01061', '1', '07', 'str_kit.isNotEmpty(isConstracte)', 'ZG01', '分级产品标识', 'isConstracte', '静态校验', '【分级产品标识】不可为空!', '【分级产品标识】不可为空!', '01', 'ZG0029', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01062', '1', '07', 'str_kit.isNotEmpty(transferType)', 'ZG01', '收益权转让产品标识', 'transferType', '静态校验', '【收益权转让产品标识】不可为空!', '【收益权转让产品标识】不可为空!', '01', 'ZG0030', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01063', '1', '07', 'str_kit.isNotEmpty(cashManaCate)', 'ZG01', '货基或现金管理类产品标识', 'cashManaCate', '静态校验', '【货基或现金管理类产品标识】不可为空!', '【货基或现金管理类产品标识】不可为空!', '01', 'ZG0031', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01064', '1', '07', 'str_kit.isNotEmpty(baseInfoSign)', 'ZG01', '基本信息公开标识', 'baseInfoSign', '静态校验', '【基本信息公开标识】不可为空!', '【基本信息公开标识】不可为空!', '01', 'ZG0032', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01065', '1', '12', 'str_kit.isNotEmptyByCond(operateMode,3,mdate)', 'ZG01', '产品预计终止日期', 'mdate', '静态校验', '当产品运行方式为封闭式净值型，产品预计终止日期不可为空！', '封闭式产品，产品预计终止日期不可为空！', '01', 'ZG0033', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01066', '1', '14', 'str_kit.isNotEmptyByCond(infoType,2,changeReason)', 'ZG01', '变更原因', 'changeReason', '静态检验', '当信息类型为变更资管产品基本信息，变更原因不可为空！', '当信息类型为变更资管产品基本信息，变更原因不可为空！', '01', 'ZG0034', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01067', '1', '13', 'date_kit.betweenDate(raiseVdate)', 'ZG01', '募集起始日期', 'raiseVdate', '静态校验', '募集起始日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '募集起始日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间！', '01', 'ZG0035', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01068', '1', '10', 'date_kit.betweenDate(raiseEnddate)', 'ZG01', '募集结束日期', 'raiseEnddate', '静态校验', '募集结束日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '募集结束日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间！', '01', 'ZG0036', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01069', '1', '13', 'date_kit.betweenDate(vdate)', 'ZG01', '产品起始日期', 'vdate', '静态校验', '产品起始日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '产品起始日期字段必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间！', '01', 'ZG0037', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01070', '1', '10', 'date_kit.betweenDate(mdate)', 'ZG01', '产品预计终止日期', 'mdate', '静态校验', '产品预计终止日期必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间', '产品预计终止日期必须满足日期格式为：YYYY-MM-DD且日期范围在1800-01-01到2100-12-31之间！', '01', 'ZG0038', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01071', '1', '10', 'enum_kit.isErrorDictCode(str_m,pbcProdType)', 'ZG01', '产品品种', 'pbcProdType', '16', '1：银行非保本理财2：信托公司资管产品3：证券公司及其子公司资管产品4：基金管理公司及其子公司专户5：期货公司及其子公司资管产品6：保险资管产品7：金融资产投资公司资管产品8：公募基金', '【产品品种】数据不在值域范围内!', '01', 'ZG0039', '1', 'PBC_PROD_TYPE',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01072', '1', '10', 'enum_kit.isErrorDictCode(str_m,ivstorientation)', 'ZG01', '产品类型', 'ivstorientation', '16', '1：固定收益类2：权益类3：商品及金融衍生品类4：混合类', '【产品类型】数据不在值域范围内!', '01', 'ZG0040', '1', 'PBC_INVEST_TYPE', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01073', '3', '10', 'enum_kit.judgeErrorDictCode(str_m,raiseCcy)', 'ZG01', '募集资金币种', 'raiseCcy', '16', '按合同、说明书填写。采用《表示货币和资金的代码》(GB/T12406)最新标准中的三位字母型代码。具体代码见码值表“Z0003”。如一只资管产品对应多个币种，逐一列出，以英文逗号“,”隔开。', '【募集资金币种】数据不在值域范围内!', '01', 'ZG0041', '2', 'CURRENCY_PBC', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01074', '3', '10', 'enum_kit.judgeErrorDictCode(str_m,cashPrinCcy)', 'ZG01', '兑付本金币种', 'cashPrinCcy', '16', '按合同、说明书填写。采用《表示货币和资金的代码》(GB/T12406)最新标准中的三位字母型代码。具体代码见码值表“Z0003”。如一只资管产品对应多个币种，逐一列出，以英文逗号“,”隔开。', '【兑付本金币种】数据不在值域范围内!', '01', 'ZG0042', '2', 'CURRENCY_PBC', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01075', '3', '10', 'enum_kit.judgeErrorDictCode(str_m,cashProfCcy)', 'ZG01', '兑付收益币种', 'cashProfCcy', '16', '按合同、说明书填写。采用《表示货币和资金的代码》(GB/T12406)最新标准中的三位字母型代码。具体代码见码值表“Z0003”。如一只资管产品对应多个币种，逐一列出，以英文逗号“,”隔开。', '【兑付收益币种】数据不在值域范围内!', '01', 'ZG0043', '2', 'CURRENCY_PBC', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01076', '1', '10', 'enum_kit.isErrorDictCode(SVR_PBC_NEW_RAISE_TYPE,raiseType)', 'ZG01', '募集方式', 'raiseType', '15', '1：公开募集2：非公开募集', '【募集方式】数据不在值域范围内!', '01', 'ZG0044', '1', 'SVR_PBC_NEW_RAISE_TYPE', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01077', '1', '10', 'enum_kit.isErrorDictCode(str_m,manageType)', 'ZG01', '管理方式', 'manageType', '16', '1：集合管理2：单独管理', '【管理方式】数据不在值域范围内!', '01', 'ZG0045', '1', 'SVR_PBC_MANAGE_STYLE',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01078', '1', '10', 'enum_kit.isErrorDictCode(str_m,operateMode)', 'ZG01', '运行方式', 'operateMode', '16', '1：开放式净值型2：开放式非净值型3：封闭式净值型4：封闭式非净值型', '【运行方式】数据不在值域范围内!', '01', 'ZG0046', '1', 'SVR_PBC_NEW_OPERATEMODE', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01079', '1', '10', 'enum_kit.isErrorDictCode(str_m,termRightFlag)', 'ZG01', '发行机构提前终止权标识', 'termRightFlag', '16', '1：有2：无', '【发行机构提前终止权标识】数据不在值域范围内!', '01', 'ZG0047', '1', 'SVR_PBC_HASORNO',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01080', '1', '10', 'enum_kit.isErrorDictCode(str_m,cusRedeemFlag)', 'ZG01', '客户赎回权标识', 'cusRedeemFlag', '16', '1：有2：无', '【客户赎回权标识】数据不在值域范围内!', '01', 'ZG0048', '1', 'SVR_PBC_HASORNO',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01081', '1', '10', 'enum_kit.isErrorDictCode(str_m,creditFlag)', 'ZG01', '产品增信标识', 'creditFlag', '16', '1：是2：否', '【产品增信标识】数据不在值域范围内!', '01', 'ZG0049', '1', 'SVR_PBC_YESORNO',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01082', '1', '10', 'enum_kit.judgeErrorDictCodeobj(str_m,creditType,creditFlag,str_n)', 'ZG01', '增信机构类型', 'creditType', '16', '若“产品增信标识”选择“是”，则此项必填。如一只资管产品对应多个增信机构，逐一列出机构类型，以英文逗号“,”隔开。1：住户2：广义政府3：非金融性企业4：金融机构(实体)5：特定目的载体6：境外', '【增信机构类型】数据不在值域范围内!', '01', 'ZG0050', '1', 'SVR_PBC_CREDIT_TYPE', '1', null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01083', '1', '10', 'enum_kit.judgeErrorDictCodeobj(str_m,creditMode,creditFlag,str_n)', 'ZG01', '增信形式', 'creditMode', '16', '若“产品增信标识”选择“是”，则此项必填。如一只资管产品对应多个增信机构，逐一列出增信形式，顺序与字段“增信机构类型”保持一致，以英文逗号“,”隔开。1：内部增级2：外部增级', '【增信形式】数据不在值域范围内!', '01', 'ZG0051', '1', 'PBC_CREDIT_MODE', '1', null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01084', '1', '10', 'enum_kit.isErrorDictCode(str_m,fiduciaryDuty)', 'ZG01', '受托职责', 'fiduciaryDuty', '16', '1：主动管理2：被动管理', '【受托职责】数据不在值域范围内!', '01', 'ZG0053', '1', 'SVR_PBC_FIDUCIARY_DUTY_COL', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01085', '1', '10', 'enum_kit.isErrorDictCode(str_m,isConstracte)', 'ZG01', '分级产品标识', 'isConstracte', '16', '1：是2：否', '【分级产品标识】数据不在值域范围内!', '01', 'ZG0054', '1', 'SVR_PBC_YESORNO', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01086', '1', '10', 'enum_kit.isErrorDictCode(str_m,transferType)', 'ZG01', '收益权转让产品标识', 'transferType', '16', '1：是2：否', '【收益权转让产品标识】数据不在值域范围内!', '01', 'ZG0055', '1', 'SVR_PBC_YESORNO',null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01087', '1', '10', 'enum_kit.isErrorDictCode(str_m,cashManaCate)', 'ZG01', '货基或现金管理类产品标识', 'cashManaCate', '16', '1：是2：否', '【货基或现金管理类产品标识】数据不在值域范围内!', '01', 'ZG0056', '1', 'SVR_PBC_YESORNO', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01088', '1', '10', 'str_kit.judgeValueByCond3(raiseType,2,baseInfoSign,1)', 'ZG01', '基本信息公开标识', 'baseInfoSign', '静态检验', '1：是2：否募集方式为非公开募集的资管产品基本信息公开标识可填报“1：是”或“2：否”，如填报“2：否”，需同时提交说明。其他资管产品基本信息公开标识均应填报“1：是”。', '【基本信息公开标识】数据不在值域范围内!', '01', 'ZG0057', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01089', '1', '10', 'enum_kit.isErrorDictCode(PBC_INFO_TYPE,infoType)', 'ZG01', '信息类型', 'infoType', '15', '1：新增资管产品基本信息2：变更资管产品基本信息如报送修改产品基本信息，需经过当地人民银行同意后方可报送。', '【信息类型】数据不在值域范围内!', '01', 'ZG0058', '1', 'PBC_INFO_TYPE', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01090', '3', '11', 'date_kit.getBetweenDayNumber(vdate,mdate,92)', 'ZG01', '产品预计终止日期', 'mdate', '静态校验', '产品预计终止日期有值时，产品预计终止日期-产品起始日期>=92天', '资管产品期限不应小于3个月！', '01', 'ZG0059', '3', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01091', '3', '11', 'date_kit.dateCompare(raiseVdate,raiseEnddate)', 'ZG01', '募集结束日期', 'raiseEnddate', '静态校验', '产品起始日期<=募集结束日期', '募集结束日期不得早于募集起始日期！', '01', 'ZG0060', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01092', '3', '11', 'date_kit.dateCompareIfNotEmpty(vdate,mdate)', 'ZG01', '预计终止日期', 'mdate', '静态校验', '当产品预计终止日期有值时，产品起始日期<=产品预计终止日期', '预计终止日期不应小于产品起始日期！', '01', 'ZG0061', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01093', '3', '11', 'str_kit.judgeValueByCond2(raiseType,1,manageType,2)', 'ZG01', '管理方式', 'manageType', '静态校验', '产品募集方式为公开募集时，管理方式不应为单独管理', '产品募集方式为公开募集时，管理方式不应为单独管理！', '01', 'ZG0062', '3', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01094', '3', '11', 'str_kit.judgeValueByCond2(pbcProdType,1,fiduciaryDuty,2)', 'ZG01', '受托职责', 'fiduciaryDuty', '静态校验', '银行非保本理财产品的受托职责不能填写被动管理', '银行非保本理财产品的受托职责不能填写被动管理！', '01', 'ZG0073', '3', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01095', '3', '03', 'str_kit.isNotEmptyByCond(operateMode,1,mdate)', 'ZG01', '产品预计终止日期', 'mdate', '业务逻辑类', '产品运行方式为开放式净值型，未填写预计终止日', '开放式产品未填写预计终止日！', '01', 'ZG0074', '3', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01096', '1', '11', 'str_kit.judgeValueByCond2(raiseType,1,baseInfoSign,2)', 'ZG01', '基本信息公开标识', 'baseInfoSign', '静态校验', '募集方式为公开募集的产品，产品基本信息公开标识不能选择否！', '募集方式为公开募集的产品，产品基本信息公开标识不能选择否！', '01', 'ZG0076', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01097', '3', '11', 'str_kit.judgeValueByCond4(creditMode,1,creditType,4)', 'ZG01', '增信机构类型', 'creditType', '静态校验', '产品增信形式包含内部增级，增信机构类型必须包含产品发行机构类型！（理财子和银行资管的增信机构类型默认为金融机构(实体)）', '产品增信形式包含内部增级，增信机构类型不包含产品发行机构类型！', '01', 'ZG0077', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01098', '3', '11', 'str_kit.judgeValueByCond5(creditMode,1,creditType,4)', 'ZG01', '增信机构类型', 'creditType', '静态校验', '产品增信形式只有内部增级，增信机构类型不得包含产品发行机构类型以外的机构类型！（理财子和银行资管的增信机构类型默认为金融机构(实体)）', '产品增信形式只有内部增级，增信机构类型包含产品发行机构类型以外的机构类型！', '01', 'ZG0078', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01099', '1', '07', 'str_kit.isNotEmpty(crossBorderFlag)', 'ZG01', '跨境理财通', 'crossBorderFlag', '静态校验', '【跨境理财通】不可为空!', '【跨境理财通】不可为空!', '01', 'ZG1140', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01100', '1', '10', 'enum_kit.isErrorDictCode(SVR_PBC_YESORNO,crossBorderFlag)', 'ZG01', '跨境理财通', 'crossBorderFlag', '15', '1：是2：否', '【跨境理财通】数据不在值域范围内!', '01', 'ZG1141', '1', 'SVR_PBC_YESORNO', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01101', '1', '07', 'str_kit.isNotEmpty(isPropertyFlag)', 'ZG01', '以财产权信托名义开展的资金信托', 'isPropertyFlag', '静态校验', '【以财产权信托名义开展的资金信托】不可为空!', '【以财产权信托名义开展的资金信托】不可为空!', '01', 'ZG1142', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01102', '1', '10', 'enum_kit.isErrorDictCode(SVR_PBC_YESORNO,isPropertyFlag)', 'ZG01', '以财产权信托名义开展的资金信托', 'isPropertyFlag', '15', '1：是2：否', '【以财产权信托名义开展的资金信托】数据不在值域范围内!', '01', 'ZG1143', '1', 'SVR_PBC_YESORNO', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01103', '1', '14', 'str_kit.isNotEmptyByCond(infoType,2,cdate)', 'ZG01', '变更日期', 'cdate', '静态检验', '当信息类型为变更资管产品基本信息，变更日期不可为空！', '当信息类型为变更资管产品基本信息，变更日期不可为空！', '01', 'ZG1180', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01104', '3', '03', 'str_kit.beEmpty(backup1)', 'ZG01', '备用字段1', 'backup1', '静态校验', '预留字段必须为空！', '备用字段1，为预留字段必须为空！', '01', 'ZG1181', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01105', '3', '03', 'str_kit.beEmpty(backup2)', 'ZG01', '备用字段2', 'backup2', '静态校验', '预留字段必须为空！', '备用字段2，为预留字段必须为空！', '01', 'ZG1182', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01106', '3', '03', 'str_kit.beEmpty(backup3)', 'ZG01', '备用字段3', 'backup3', '静态校验', '预留字段必须为空！', '备用字段3，为预留字段必须为空！', '01', 'ZG1183', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01107', '3', '03', 'str_kit.beEmpty(backup4)', 'ZG01', '备用字段4', 'backup4', '静态校验', '预留字段必须为空！', '备用字段4，为预留字段必须为空！', '01', 'ZG1184', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01108', '3', '03', 'str_kit.beEmpty(backup5)', 'ZG01', '备用字段5', 'backup5', '静态校验', '预留字段必须为空！', '备用字段5，为预留字段必须为空！', '01', 'ZG1185', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01109', '3', '03', 'str_kit.judgeValueByCond2(raiseType,2,baseInfoSign,1)', 'ZG01', '基本信息公开标识', 'baseInfoSign', '静态检验', '募集方式为非公开募集的产品，公开标识选择否！', '募集方式为非公开募集的产品，公开标识选择否！', '01', 'ZG1595', '3', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01110', '3', '04', 'date_kit.dateCompareByParam2(infoType,2,cdate,vdate)', 'ZG01', '产品变更日期', 'cdate', '业务逻辑类', '当信息类型为变更资管产品基本信息时，产品变更日期>产品起始日期', '当信息类型为变更资管产品基本信息时，产品变更日期早于产品起始日期!', '01', 'ZG1607', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01111', '3', '03', 'ZG01,', 'ZG01', '其他业务逻辑校验', 'infoType', '19', '当信息类型为变更资管产品基本信息时，与上一期比对的产品基本信息表应至少有一个字段发生变化', '当信息类型为变更资管产品基本信息时，与上一期比对的产品基本信息表应至少有一个字段发生变化!', '01', 'ZG1608', '2', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01112', '3', '03', 'ZG01,', 'ZG01', '产品名称', 'prodName', '18', '产品代码相同，信息类型为变更资管产品基本信息中的产品名称与上期不一致', '产品代码相同，信息类型为变更资管产品基本信息中的产品名称与上期不一致!', '01', 'ZG1609', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01113', '3', '03', 'ZG01,', 'ZG01', '产品品种', 'pbcProdType', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品品种与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品品种与上期不一致!', '01', 'ZG1612', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01114', '3', '03', 'ZG01,', 'ZG01', '产品类型', 'ivstorientation', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品类型与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品类型与上期不一致!', '01', 'ZG1613', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01115', '3', '03', 'ZG01,', 'ZG01', '产品品牌', 'prodBrand', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品品牌与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品品牌与上期不一致!', '01', 'ZG1614', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01116', '3', '03', 'ZG01,', 'ZG01', '产品期次', 'prodStage', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品期次与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品期次与上期不一致!', '01', 'ZG1615', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01117', '3', '03', 'ZG01,', 'ZG01', '募集资金币种', 'raiseCcy', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的募集资金币种与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的募集资金币种与上期不一致!', '01', 'ZG1617', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01118', '3', '03', 'ZG01,', 'ZG01', '兑付本金币种', 'cashPrinCcy', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的兑付本金币种与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的兑付本金币种与上期不一致!', '01', 'ZG1618', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01119', '3', '03', 'ZG01,', 'ZG01', '兑付收益币种', 'cashProfCcy', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的兑付收益币种与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的兑付收益币种与上期不一致!', '01', 'ZG1619', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01120', '3', '03', 'ZG01,', 'ZG01', '募集方式', 'raiseType', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的募集方式与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的募集方式与上期不一致!', '01', 'ZG1620', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01121', '3', '03', 'ZG01,', 'ZG01', '管理方式', 'manageType', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的管理方式与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的管理方式与上期不一致!', '01', 'ZG1621', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01122', '3', '03', 'ZG01,', 'ZG01', '运行方式', 'operateMode', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的运行方式与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的运行方式与上期不一致!', '01', 'ZG1622', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01123', '3', '03', 'ZG01,', 'ZG01', '募集起始日期', 'raiseVdate', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的募集起始日期与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的募集起始日期与上期不一致!', '01', 'ZG1623', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01124', '3', '03', 'ZG01,', 'ZG01', '募集结束日期', 'raiseEnddate', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的募集结束日期与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的募集结束日期与上期不一致!', '01', 'ZG1624', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01125', '3', '03', 'ZG01,', 'ZG01', '发行机构提前终止权标识', 'termRightFlag', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的发行机构提前终止权标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的发行机构提前终止权标识与上期不一致!', '01', 'ZG1625', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01126', '3', '03', 'ZG01,', 'ZG01', '客户赎回权标识', 'cusRedeemFlag', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的客户赎回权标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的客户赎回权标识与上期不一致!', '01', 'ZG1626', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01127', '3', '03', 'ZG01,', 'ZG01', '产品增信标识', 'creditFlag', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品增信标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品增信标识与上期不一致!', '01', 'ZG1627', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01128', '3', '03', 'ZG01,', 'ZG01', '增信机构类型', 'creditType', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的增信机构类型与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的增信机构类型与上期不一致!', '01', 'ZG1628', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01129', '3', '03', 'ZG01,', 'ZG01', '增信形式', 'creditMode', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的增信形式与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的增信形式与上期不一致!', '01', 'ZG1629', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01130', '3', '03', 'ZG01,', 'ZG01', '境内托管机构代码', 'inDepCode', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的境内托管机构代码与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的境内托管机构代码与上期不一致!', '01', 'ZG1630', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01131', '3', '03', 'ZG01,', 'ZG01', '境外托管机构国别代码', 'outDepCtry', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的境外托管机构国别代码与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的境外托管机构国别代码与上期不一致!', '01', 'ZG1631', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01132', '3', '03', 'ZG01,', 'ZG01', '境外托管机构名称', 'outDepName', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的境外托管机构名称与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的境外托管机构名称与上期不一致!', '01', 'ZG1632', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01133', '3', '03', 'ZG01,', 'ZG01', '产品起始日期', 'vdate', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品起始日期与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品起始日期与上期不一致!', '01', 'ZG1633', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01134', '3', '03', 'ZG01,', 'ZG01', '产品预计终止日期', 'mdate', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的产品预计终止日期与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的产品预计终止日期与上期不一致!', '01', 'ZG1634', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01135', '3', '03', 'ZG01,', 'ZG01', '受托职责', 'fiduciaryDuty', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的受托职责与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的受托职责与上期不一致!', '01', 'ZG1635', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01136', '3', '03', 'ZG01,', 'ZG01', '分级产品标识', 'isConstracte', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的分级产品标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的分级产品标识与上期不一致!', '01', 'ZG1636', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01137', '3', '03', 'ZG01,', 'ZG01', '收益权转让产品标识', 'transferType', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的收益权转让产品标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的收益权转让产品标识与上期不一致!', '01', 'ZG1637', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01138', '3', '03', 'ZG01,', 'ZG01', '货基或现金管理类产品标识', 'cashManaCate', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的货基或现金管理类产品标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的货基或现金管理类产品标识与上期不一致!', '01', 'ZG1638', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01139', '3', '03', 'ZG01,', 'ZG01', '跨境理财通', 'crossBorderFlag', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的跨境理财通与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的跨境理财通与上期不一致!', '01', 'ZG1639', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01140', '3', '03', 'ZG01,', 'ZG01', '以财产权信托名义开展的资金信托', 'isPropertyFlag', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的以财产权信托名义开展的资金信托与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的以财产权信托名义开展的资金信托与上期不一致!', '01', 'ZG1640', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01141', '3', '03', 'ZG01,', 'ZG01', '基本信息公开标识', 'baseInfoSign', '18', '产品代码相同，当信息类型为变更资管产品基本信息中的基本信息公开标识与上期不一致', '产品代码相同，当信息类型为变更资管产品基本信息中的基本信息公开标识与上期不一致!', '01', 'ZG1641', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS01142','ZG1643','1','1','03','str_kit.judgeValueByCond(pbcProdType,str_1,isPropertyFlag,2)','ZG01','以财产权信托名义开展的资金信托','isPropertyFlag','20','产品品种不是信托公司资管产品，以财产权信托名义开展的资金信托标识应为否','产品品种不是信托公司资管产品，以财产权信托名义开展的资金信托标识应为否!','01', '1,3,4,5,6,7,8', '', '', '');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01143', '3', '03', 'str_kit.conditionValueCompare(isConstracte,1,operateMode,1,raiseType,1)', 'ZG01', '分级产品标识', 'isConstracte', '业务逻辑类', '分级产品标识为“是”的产品，运行方式不应该为“开放式净值型”，且募集方式不应该为“公开募集”', '银行发行的分级产品不应为开放式或公开募集!', '01', 'ZG1646', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01144', '3', '03', 'str_kit.judgeValueByCond2(isConstracte,1,manageType,2)', 'ZG01', '管理方式', 'manageType', '业务逻辑类', '分级产品标识为“是”的产品，管理方式一般不为单独管理', '分级产品的管理方式一般不为单独管理!', '01', 'ZG1647', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01145', '1', '04', 'date_kit.compareWithSysdate(vdate,str_m)', 'ZG01', '产品起始日期', 'vdate', '16', '产品起始日期<=当前系统日期', '产品起始日期应小于或等于当前系统日期!', '01', 'ZG1656', '1', '<=', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS01146','ZG1657','3','3','03','str_kit.judgeValueByCond6(infoType,"1")','ZG01','信息类型','infoType','业务逻辑类','信息类型选择变更资管产品基本信息，应经人民银行同意后报送。','信息类型选择变更资管产品基本信息，应经人民银行同意后报送！','01');

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01147', '3', '12', 'str_kit.isNotEmptyByCond2(outDepName,outDepCtry)', 'ZG01', '境外托管机构国别代码', 'outDepCtry', '业务逻辑类', '境外托管机构名称非空，则境外托管机构国别代码不应为空。', '境外托管机构名称非空，则境外托管机构国别代码不应为空!', '01', 'ZG1658', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01148', '1', '22', 'str_kit.compareEqualToLength(pbcProdId,15)', 'ZG01', '产品代码', 'pbcProdId', '违背编码规范类', '产品代码需符合《特定目的载体（SPV）编码标准（试行）》的编码规则要求', '产品代码应符合《特定目的载体（SPV）编码标准（试行）》的编码规则要求！', '01', 'ZG0001-1', '1', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01149', '3', '08', 'pbcProdId,infoType,issuePartyId,vdate,status', 'ZG01', '产品代码', 'pbcProdId', '10', '当信息类型选择新增资管产品基本信息，产品代码不可同系统中“ ZG01 资管产品基本信息”模块已有数据的产品代码重复。', '新增产品代码应唯一！', '01', 'ZG0003', '2', null, null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01150', '3', '05', 'ZG01,', 'ZG01', '产品代码', 'pbcProdId', '18', '当信息类型选择变更资管产品基本信息，产品代码必须同系统中“ ZG01 资管产品基本信息”模块数据状态为“03 手工处理”的任一条已有数据的产品代码相同。', '所报送产品变更信息中的产品代码不存在！', '01', 'ZG0004', '2', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01153', '3', '03', 'ZG01,', 'ZG01', '发行机构代码', 'issuePartyId', '18', '产品代码相同时，当信息类型选择变更资管产品基本信息，发行机构代码需与上期一致', '产品代码相同，信息类型2中的发行机构代码与上期不一致！', '01', 'ZG1610', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01154', '3', '03', 'ZG01,', 'ZG01', '发行机构名称', 'issuePartyName', '18', '产品代码相同时，当信息类型选择变更资管产品基本信息，发行机构名称需与上期一致', '产品代码相同，信息类型2中的发行机构名称与上期不一致！', '01', 'ZG1611', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01155', '3', '03', 'ZG01,', 'ZG01', '发行机构内部产品代码', 'prodId', '18', '产品代码相同时，当信息类型选择变更资管产品基本信息，发行机构内部产品代码需与上期一致', '产品代码相同，信息类型2中的发行机构内部产品代码与上期不一致！', '01', 'ZG1616', '3', 'pbcProdId', null, null, null, null, null);

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart)
VALUES ('DS01152','ZG1594','1','1','06','unique_kit.equalsSysParaValue(issuePartyName,"SVR_ISSUE_PARTY_NAME")','ZG01','发行机构名称','issuePartyName','业务逻辑类','发行机构代码必须同“机构登记银行代码管理”模块的“机构名称”相同（此机构代码的“机构类型”需为“机构管理”模块的“总行机构”）','发行机构名称应与报数机构名称保持一致！','01');

INSERT INTO pbc_check_rule_info (rule_id,pbc_asset_code, rule_classify,rule_identify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, PARAMS01, PARAMS02, PARAMS03, PARAMS04)
VALUES ('DS01151','ZG1166','1','1','06','unique_kit.equalsSysParaValue(issuePartyId,"PBC_ISSUE_PARTY_ID")','ZG01','发行机构代码','issuePartyId','业务逻辑类','发行机构代码必须同“机构登记银行代码管理”模块的“人行登记机构代码”相同（此机构代码的“机构类型”需为“机构管理”模块的“总行机构”）','发行机构代码应与报数机构代码保持一致！','01', null, null, null, null);


INSERT INTO pbc_check_rule_info (rule_id, rule_classify, rule_type, rule_param, table_data_name, checked_field_name, checked_filed_value, rule_method, rule_content, rule_prompt, isStart, pbc_asset_code, rule_identify, PARAMS01, PARAMS02, PARAMS03, PARAMS04, related_table, update_time)
VALUES ('DS01156', '1', '08', 'pbcProdId,infoType,issuePartyId', 'ZG01', '产品代码', 'pbcProdId', '10', '产品基本信息中产品代码加信息类型加发行机构代码在文件内唯一', '发行机构代码+产品代码+信息类型应唯一！', '01', 'ZJ01001', '1', null, null, null, null, null, null);

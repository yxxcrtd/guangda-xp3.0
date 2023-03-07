
-- 前置条件
DELETE FROM chb_datacheck_condition WHERE CHECKRULEID IN ( SELECT CHECKRULEID FROM chb_datacheck_rule WHERE DATATYPE = 'PBC_SPE_COUNTER_PARTY' );
DELETE FROM chb_datacheck_condition where CHECKCONDITIONID='PBC_SPE_COUNTER_PARTY_2022_11_01_0010_01';
INSERT INTO chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0010_01', 'PBC_SPE_COUNTER_PARTY_2022_11_01_0010', 'inArray', 'CUR_ASSET_TYPE', '==', 'A5200,A7200,B1200', '', 'D');

-- check
DELETE FROM chb_datacheck_rule WHERE DATATYPE = 'PBC_SPE_COUNTER_PARTY';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0001', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '资产代码不可为空。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0002', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_ORG_NAME', null, '121', 'Y', '交易对手机构名称最大长度120。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0003', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_PROD_NAME', null, '121', 'N', '交易对手产品名称不可为空且最大长度120。', 'E');
-- INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
-- VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0004', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_TYPE', null, '6', 'N', '资产负债项目不可为空且最大长度5。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0005', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PARTY_PROD_TYPE', null, null, 'N', '交易对手产品种类不可为空。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0006', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_ORG_CODE', null, '201', 'N', '交易对手机构编码不可为空且最大长度200。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0007', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'fixedLength', 'PARTY_PROD_ID', null, '15', 'N', '交易对手产品代码不可为空且固定长度15。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0008', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_PROD_TYPE', '==', 'PBC_PROD_TYPE', 'Y', '交易对手产品种类不在值域范围内。', 'E');
-- INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
-- VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0009', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'ASSET_TYPE', '==', 'A5200,A7200,B1200,C1100,C1200', 'Y', '资产负债项不在值域范围内。', 'E');
-- INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
-- VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0010', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PARTY_ORG_NAME', null, null, 'N', '交易对手机构名称不可为空。', 'D');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_SPE_COUNTER_PARTY_2022_11_01_0011', 'PBC_SPE_COUNTER_PARTY', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '51', 'Y', '资产代码最大长度50。', 'E');

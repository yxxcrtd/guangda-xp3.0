
-- 添加基本信息公开标识 字段
ALTER TABLE CHB_PRD_BASEINFO ADD BASE_INFO_SIGN VARCHAR(1) COMMENT '基本信息公开标识';

-- 变更原因 字段
ALTER TABLE CHB_PRD_BASEINFO ADD CHANGE_REASON VARCHAR(200) COMMENT '变更原因';

-- 变更原因长度校验
delete from chb_datacheck_rule where DATATYPE='CHB_PRD_BASEINFO' and CHECKRULEID='BASEINFO-6813-5A9E-E050-007F01000200';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('BASEINFO-6813-5A9E-E050-007F01000200', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHANGE_REASON', null, '201', 'Y', '变更原因（最大长）200。', 'E');

-- 基本信息公开标识不可为空
delete from chb_datacheck_rule where DATATYPE='CHB_PRD_BASEINFO' and CHECKRULEID='BASEINFO-6813-5A9E-E050-007F01000201';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('BASEINFO-6813-5A9E-E050-007F01000201', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BASE_INFO_SIGN', null, null, 'N', '基本信息公开标识不可为空。', 'E');

-- 前置校验条件
delete from chb_datacheck_condition where CHECKCONDITIONID='BASEINFO-6810-1A9E-E650-007F01000200';
INSERT INTO chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS) VALUES ('BASEINFO-6810-1A9E-E650-007F01000200', 'BASEINFO-6813-5A9E-E050-007F01000202', 'compareValue', 'RAISE_TYPE', '==', '01', '当“产品募集方式”为“01 公募”时', 'E');

-- 基本信息公开标识 公募只能选择 1:是
delete from chb_datacheck_rule where DATATYPE='CHB_PRD_BASEINFO' and CHECKRULEID='BASEINFO-6813-5A9E-E050-007F01000202';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('BASEINFO-6813-5A9E-E050-007F01000202', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'BASE_INFO_SIGN', '==', '1', 'Y', '基本信息公开标识只可选择“1:是”。', 'E');

-- 基本信息公开标识 1:是 2:否
delete from chb_datacheck_rule where DATATYPE='CHB_PRD_BASEINFO' and CHECKRULEID='BASEINFO-6813-5A9E-E050-007F01000203';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('BASEINFO-6813-5A9E-E050-007F01000203', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASE_INFO_SIGN', '==', 'SVR_PBC_YESORNO', 'Y', '基本信息公开标识不在值域范围内。', 'E');


DELETE FROM chb_datacheck_rule_inv WHERE PAGE_ID = 'CchbPrdBaseInfo002' AND CHECKRULEID = 'CHB_PRD_BASEINF_CPJBXX_2022_11_11_0001';
INSERT INTO chb_datacheck_rule_inv (`CHECKRULEID`, `PAGE_ID`, `CHECKMETHOD`, `SRCPROPERTY`, `COMPARETO`, `OBJVALUE`, `NULLABLE`, `REMARK`, `STATUS`, `CHECKCLASS`)
VALUES ('CHB_PRD_BASEINF_CPJBXX_2022_11_11_0001', 'CchbPrdBaseInfo002', 'lessThanCharLengthUTF8', 'changeReason', NULL, '201', 'Y', '变更原因（最大长）200。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');

-- 日期校验提示
delete from chb_datacheck_rule where CHECKRULEID='PBC_TARGET_INFO_0010';
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0010', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate3', 'HOLD_DATE', '==', 'yyyy-MM-dd', 'Y', '持有日期必须为日期格式（yyyy-MM-dd）且为正确的日期。', 'E');

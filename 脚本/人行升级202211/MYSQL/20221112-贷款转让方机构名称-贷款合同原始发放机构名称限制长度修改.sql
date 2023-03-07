delete from chb_datacheck_rule where CHECKRULEID ='9A48C2BF-0E5E-0CFD-E053-035AA8C0F562';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('9A48C2BF-0E5E-0CFD-E053-035AA8C0F562', 'CHB_LOAN_DETAIL_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'LOAN_CONTRACT_ORG_NAME', null, '121', 'Y', '贷款合同原始发放机构名称要素超长(最大长120字节)。', 'E');

delete from chb_datacheck_rule where CHECKRULEID='9A48C2BF-0E54-0CFD-E053-035AA8C0F562';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('9A48C2BF-0E54-0CFD-E053-035AA8C0F562', 'CHB_LOAN_DETAIL_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'LOAN_TRANSFEROR_NAME', null, '121', 'Y', '贷款转让方机构名称要素超长（最大长120字节）。', 'E');

delete from chb_datacheck_condition where CHECKCONDITIONID ='9A48C2BF-0E53-0CFD-E053-035AA8C0F562';
delete from chb_datacheck_rule where  CHECKRULEID='9A48C2BF-0E52-0CFD-E053-035AA8C0F562';
delete from chb_datacheck_condition where CHECKCONDITIONID ='9A48C2BF-0E5D-0CFD-E053-035AA8C0F562';
delete from chb_datacheck_rule where CHECKRULEID ='9A48C2BF-0E5C-0CFD-E053-035AA8C0F562';
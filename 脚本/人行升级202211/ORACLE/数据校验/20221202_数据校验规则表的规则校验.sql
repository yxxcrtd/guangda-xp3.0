DELETE FROM chb_datacheck_rule_inv WHERE PAGE_ID = 'PbcCheckRuleInfo002';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_01', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'ruleId', null , '13', 'N', '规则编码不可为空,且最大长度不超过12。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_02', 'PbcCheckRuleInfo002', 'isNotEmpty', 'ruleClassify', null , null, 'N', '规则大类不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_03', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'tableDataName', null , '30', 'N', '报表名称不可为空且最大长度不超过30。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_04', 'PbcCheckRuleInfo002', 'isNotEmpty', 'ruleType', null , null, 'N', '规则类型不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_05', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'checkedFieldName', null , '50', 'N', '检查字段名称不可为空且最大长度不超过50。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_06', 'PbcCheckRuleInfo002', 'isNotEmpty', 'isstart', null , null, 'N', '启动状态不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_07', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'ruleContent', null , '301', 'N', '规则内容不可为空且最大长度不超过300。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_08', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'rulePrompt', null , '301', 'N', '提示语不可为空且最大长度不超过300。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1202_09', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'pbcAssetCode', null , '13', 'Y', '人行要素编码长度不超过12。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1210_01', 'PbcCheckRuleInfo002', 'lessThanCharLength', 'checkedFiledValue', null , '50', 'N', '检查字段对应表名称不可为空且最大长度不超过50。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1210_02', 'PbcCheckRuleInfo002', 'isNotEmpty', 'ruleIdentify', null , null, 'N', '规则标识不能为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_CHECK_RULE_INFO_1210_03', 'PbcCheckRuleInfo002', 'isNotEmpty', 'ruleParam', null , null, 'N', '规则参数不能为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');

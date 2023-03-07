-- 特定目的载体持有信息（投资者）接口表 新增
DROP TABLE IF EXISTS `PBC_Target_INFO`;
CREATE TABLE `PBC_Target_INFO`  (
  `UUID` varchar(36)   NULL COMMENT '主键',
  `ACCOUNT` varchar(50)   not NULL COMMENT '内部产品代码',
  `INDENTIFY_MARK` varchar(30)  NOT NULL COMMENT '识别标识',
  `PBC_PROD_ID` varchar(100)    NULL COMMENT '人行产品代码',
  `HOLDER_NAME` varchar(200)  NULL DEFAULT NULL COMMENT '投资者名称',
  `HOLDER_TYPE` varchar(2)  NULL DEFAULT NULL COMMENT '投资者类别',
  `HOLD_DATE` date  NULL DEFAULT NULL COMMENT '持有日期',
  `CCY` varchar(10) NULL DEFAULT NULL COMMENT '币种',
  `QTY` DECIMAL(32,5) NULL DEFAULT NULL COMMENT '持有份额',
  `AMOUNT` DECIMAL(32,2)  NULL DEFAULT NULL COMMENT '持有金额',
  `CREATE_TIME` datetime  NULL DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` datetime  NULL DEFAULT NULL COMMENT '更新日期',
  `CREATE_USER` varchar(50) NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_DEPT` varchar(10) NULL DEFAULT NULL COMMENT '创建部门',
  `UPDATE_USER` varchar(10) NULL DEFAULT NULL COMMENT '更新人',
  `STATUS` varchar(50) NULL DEFAULT NULL COMMENT '数据状态',
   PRIMARY KEY (`ACCOUNT`,`INDENTIFY_MARK`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '特定目的载体持有信息（投资者）接口表' ROW_FORMAT = Dynamic;



-- 特定目的载体持有信息（投资者）接口
delete from sys_batch_task where TASK_ID='3119';
INSERT INTO sys_batch_task(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('3119', 'SVR', '特定目的载体持有信息（投资者）接口数据接入', null, 'svrProdTargetInterfTaskService', 3000, 'system', null, NOW(), 'system', NOW(), null);


-- 上游接口文件类型字典添加
delete from sys_datadict_list where DICT_CODE ='UPINTERF_FILE_TYPE' and ITEM_CODE='40';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('UPINTERF_FILE_TYPE', '40', '特定目的载体持有信息', 40, '0', 'system', null, now(), 'system', now(), null);

-- 接口规则配置
delete from chb_interf_config where INTERF_ID='PBC_TARGET_INFO';
INSERT INTO chb_interf_config(INTERF_ID, LAST_UPDATETIME, REMARK, INTERF_NAME, CONTENT_TYPE, CONTENT_SRC, REF_REPORT, SYS_SCOPE, STATUS, COVER_FLAG) VALUES ('PBC_TARGET_INFO', '2022-11-01 18:51:11', '特定目的载体持有信息接口数据', null, 'I', 'T', 'svrProdDurationRegTaskService;svrMonthReportTaskService;svrPbcReportTaskService;svrDataCheckCBService', '110', 'E', 'Y');


-- 接口校验规则
delete from chb_datacheck_rule where DATATYPE='PBC_TARGET_INFO';
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0001', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ACCOUNT', null, null, 'N', '内部产品代码要素不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0002', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ACCOUNT', null, '51', 'Y', '内部产品代码超长（最大长50）。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0003', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDENTIFY_MARK', null, null, 'N', '识别标识不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0004', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INDENTIFY_MARK', null, '31', 'Y', '识别标识（最大长30）。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0005', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'HOLDER_NAME', null, null, 'N', '投资者名称不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0006', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'HOLDER_NAME', null, '201', 'Y', '投资者名称（最大长30）。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0007', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'HOLDER_TYPE', null, null, 'N', '投资者类别不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0008', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'HOLDER_TYPE', '==', '17,18,19,20,21,22', 'Y', '投资者类别不在值域范围内', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0009', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'HOLD_DATE', null, null, 'N', '持有日期不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0010', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate3', 'HOLD_DATE', '==', 'yyyy-MM-dd', 'Y', '持有日期必须为日期格式（yyyyMMdd或yyyy-MM-dd）且为正确的日期。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0011', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CCY', '==', 'CURRENCY_CHB', 'Y', '币种不在值域范围内。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0012', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CCY', null, null, 'Y', '币种不可为空。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0013', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'QTY', null, null, 'Y', '持有份额不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0014', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'QTY', null, '18,5', 'Y', '持有份额必须为n（18,5）格式。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0015', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'QTY', '>=', '0', 'Y', '持有份额必须大于等于0。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0016', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMOUNT', null, null, 'Y', '持有金额不可为空。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0017', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMOUNT', null, '15,2', 'Y', '持有金额必须为n（15,2）格式。', 'E');
INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0018', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMOUNT', '>=', '0', 'Y', '持有金额必须大于等于0。', 'E');

INSERT INTO chb_datacheck_rule(CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS) VALUES ('PBC_TARGET_INFO_0019', 'PBC_TARGET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PBC_PROD_ID', null, '16', 'Y', '人行产品代码最大长度15。', 'E');
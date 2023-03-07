-- MySQL
DROP TABLE IF EXISTS `PBC_FIN_INSTITUTION_ASSET_INFO`;
CREATE TABLE `PBC_FIN_INSTITUTION_ASSET_INFO` (
   `PBC_UUID` varchar(64) DEFAULT NULL COMMENT 'UUID',
   `TERM_ID` varchar(20) NOT NULL COMMENT '统计年月',
   `FIN_ASSET_BALANCE` decimal(20,2) DEFAULT NULL COMMENT '金融机构表内资产余额（本外币）',
   `FIN_INS_ASSET_BALANCE` decimal(20,2) DEFAULT NULL COMMENT '金融机构表内金融资产余额（本外币）',
   `CREATE_USER` varchar(20) DEFAULT NULL COMMENT '创建人',
   `CREATE_TIME` timestamp(6) NULL DEFAULT NULL COMMENT '创建时间',
   `CREATE_DEPT` varchar(20) DEFAULT NULL COMMENT '创建部门',
   `UPDATE_USER` varchar(20) DEFAULT NULL COMMENT '更新人',
   `UPDATE_TIME` timestamp(6) NULL DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`TERM_ID`)
)COMMENT='表内资产数据维护表';

-- sys_menu
DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_001005011' AND PAGE_ID = 'PbcFinInstitutionAssetInfo001';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_001005011', 'SVR', null, 'REPORT_001005', 'PbcFinInstitutionAssetInfo001', 11, null, '表内资产数据维护', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00');

-- sys_page
DELETE FROM sys_page WHERE PAGE_ID IN ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo002');
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcFinInstitutionAssetInfo001', 'SVR', '/svreport/fininstitution/PbcFinInstitutionAssetInfo001', 'svreport/fininstitution/PbcFinInstitutionAssetInfo001', 'REPORT_001005011', null, null, '02', '01', '表内资产数据维护', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00');
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcFinInstitutionAssetInfo002', 'SVR', '/svreport/fininstitution/PbcFinInstitutionAssetInfo002', 'svreport/fininstitution/PbcFinInstitutionAssetInfo002', null, null, null, '02', '01', '表内资产数据维护新建', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00');

-- sys_page_element
DELETE FROM sys_page_element WHERE PAGE_ID = 'PbcFinInstitutionAssetInfo001' ;
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_add', '01', '01', null, '新建', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_export', '01', '01', null, '导出', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_import', '01', '01', null, '导入', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_reset', '01', '01', null, '重置', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);
INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo001_btn_search', '01', '01', null, '查询', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00', 'C', null);


-- check
DELETE FROM chb_datacheck_rule_inv WHERE PAGE_ID = 'PbcFinInstitutionAssetInfo002';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0001', 'PbcFinInstitutionAssetInfo002', 'isNotEmpty', 'termId', null , null, 'N', '统计年月不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0002', 'PbcFinInstitutionAssetInfo002', 'isNotEmpty', 'finAssetBalance', null, null, 'N', '金融机构表内资产余额（本外币）不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0003', 'PbcFinInstitutionAssetInfo002', 'isNotEmpty', 'finInsAssetBalance', null, null, 'N', '金融机构表内金融资产余额（本外币）不可为空.', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0004', 'PbcFinInstitutionAssetInfo002', 'isNumber2', 'finAssetBalance', '==', '20,2', 'Y', '金融机构表内资产余额（本外币）必须为(20,2)格式。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0005', 'PbcFinInstitutionAssetInfo002', 'isNumber2', 'finInsAssetBalance', '==', '20,2', 'Y', '金融机构表内金融资产余额（本外币）必须为(20,2)格式。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0006', 'PbcFinInstitutionAssetInfo002', 'isValidDate2', 'termId', '==', 'yyyy-MM', 'Y', '统计年月格式(yyyy-MM)。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0007', 'PbcFinInstitutionAssetInfo002', 'compareValueAsNumber', 'finAssetBalance', '>=', '0', 'Y', '金融机构表内资产余额（本外币）必须大于等于0。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0008', 'PbcFinInstitutionAssetInfo002', 'compareValueAsNumber', 'finInsAssetBalance', '>=', '0', 'Y', '金融机构表内金融资产余额（本外币）必须大于等于0。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0009', 'PbcFinInstitutionAssetInfo002', 'comparePropertyAsNumber', 'finAssetBalance', '>=', 'finInsAssetBalance', 'Y', '金融机构表内资产余额（本外币）必须大于等于金融机构表内金融资产余额（本外币）。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0010', 'PbcFinInstitutionAssetInfo002', 'comparePropertyAsNumber', 'finInsAssetBalance', '<=', 'finAssetBalance', 'Y', '金融机构表内金融资产余额（本外币）必须小于等于金融机构表内资产余额（本外币）。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');


-- check
DELETE FROM chb_datacheck_rule WHERE DATATYPE = 'PBC_FIN_INSTITUTION_ASSET_INFO';
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0001', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TERM_ID', null, null, 'N', '统计年月不可为空。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0002', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_ASSET_BALANCE', null, null, 'N', '金融机构表内资产余额（本外币）不可为空。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0003', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_INS_ASSET_BALANCE', null, null, 'N', '金融机构表内金融资产余额（本外币）不可为空', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0004', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'FIN_ASSET_BALANCE', '==', '20,2', 'Y', '金融机构表内资产余额（本外币）必须为(20,2)格式。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0005', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'FIN_INS_ASSET_BALANCE', '==', '20,2', 'Y', '金融机构表内金融资产余额（本外币）必须为(20,2)格式。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0006', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'TERM_ID', '==', 'yyyy-MM', 'Y', '统计年月格式(yyyy-MM)。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0007', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'FIN_ASSET_BALANCE', '>=', '0', 'Y', '金融机构表内资产余额（本外币）必须大于等于0。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0008', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'FIN_INS_ASSET_BALANCE', '>=', '0', 'Y', '金融机构表内金融资产余额（本外币）必须大于等于0。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0009', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'FIN_ASSET_BALANCE', '>=', 'FIN_INS_ASSET_BALANCE', 'Y', '金融机构表内资产余额（本外币）必须大于等于金融机构表内金融资产余额（本外币）。', 'E');
INSERT INTO chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
VALUES ('PBC_FIN_INSTITUTION_ASSET_INFO_11_23_0010', 'PBC_FIN_INSTITUTION_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'FIN_INS_ASSET_BALANCE', '<=', 'FIN_ASSET_BALANCE', 'Y', '金融机构表内金融资产余额（本外币）必须小于等于金融机构表内资产余额（本外币）。', 'E');

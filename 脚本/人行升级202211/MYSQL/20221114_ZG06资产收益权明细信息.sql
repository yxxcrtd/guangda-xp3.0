/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_019';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_019','SVR',null,'REPORT_REGIST_001_003','ZG06PbcAssetSusfructSub',19,'2','ZG06 资产收益权明细信息','system',null,now(),'system',now());
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG06PbcAssetSusfructSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG06PbcAssetSusfructSub','SVR','/pbc/zg06assetusufructsub/ZG06PbcAssetSusfructSub','report/pbc/classic/zg06assetusufructsub/ZG06PbcAssetSusfructSub','REPORT_REGIST_001_003_019',null,null,'02','02','ZG02 资管产品初始募集信息','system',null,now(),'system',now());

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG06PbcAssetSusfructSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

/*数据字典配置*/
DELETE from SYS_PAGE_DICTLIST where PAGE_ID = 'ZG06PbcAssetSusfructSub' and DICT_ID = 'SUBMIT_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ZG06PbcAssetSusfructSub','999999','SUBMIT_STATUS','01',null,null,'system',null,NOW(),'system',NOW(),null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ZG06PbcAssetSusfructSub','999999','SUBMIT_STATUS','03',null,null,'system',null,NOW(),'system',NOW(),null);


DROP TABLE IF EXISTS PBC_COL_ASSET_USUFRUCT_SUB;
CREATE TABLE `PBC_COL_ASSET_USUFRUCT_SUB` (
  `DETAILUUID` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '明细统计uuid',
  `PBC_PROD_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '人行产品代码',
  `TERMID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '期数',
  `ASSETS_LIABILITIES` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '资产负债项目',
  `ASSET_USUFRUCT_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '资产收益权内部编码',
  `ASSETS_TRANSFER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构名称',
  `ASSETS_TRANSFER_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构代码',
  `ASSETS_INVEST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构类型',
  `ASSETS_TRANSFER_IINDU` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构行业',
  `ASSETS_TRANSFER` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构注册地区',
  `ECONOMIC_COMPOSITION` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构经济成分',
  `ASSETS_TRANSFER_SCALE` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产出让机构规模',
  `TRANSFER_VDATE` date COMMENT '转让起始日期',
  `TRANSFER_ESTI_MDATE` date COMMENT '转让预计终止日期',
  `TRANSFER_EXTDUE_MDATE` date COMMENT '转让展期到期日期',
  `TRANSFER_ACTMDATE` date COMMENT '转让实际终止日期',
  `BASICASSET_TYPE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产类型',
  `ASSETS_ORIGCCY` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产原始协议币种',
  `ASSETS_ORIGAMT` decimal(20,2) COMMENT '基础资产原始协议金额',
  `ASSETS_ORIGAMT_CNY` decimal(20,2) COMMENT '基础资产原始协议金额折人民币',
  `CURRENCY_TRANSFERCCY` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产转让币种',
  `CURRENCY_TRANSFERAMT` decimal(20,2) COMMENT '基础资产转让金额',
  `CURRENCY_TRANSFERAMTCNY` decimal(20,2) COMMENT '基础资产转让金额折人民币',
  `LABEL_TRANSFEROR_MARK` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '出让机构出表标识',
  `REPURCHASE_MARK` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '出让机构回购标识',
  `RATE_FIXED_FLAG` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '利率是否固定',
  `ASSET_RATE_LEVEL` decimal(20,2) COMMENT '利率水平',
  `GUARANTEE_METHOD` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '担保方式',
  `ASSETS_INV_DEPA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产投向部门',
  `CURRENCY_EPERIODCCY` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '基础资产期末币种',
  `CURRENCY_AMT` decimal(20,2) COMMENT '基础资产期末余额 ',
  `CURRENCY_AMT_CNY` decimal(20,2) COMMENT '基础资产期末余额折人民币',
  `SUBMIT_TIME` datetime DEFAULT NULL COMMENT '报送日期',
  `PBC_SUBMIT_DEADLINE` datetime COMMENT '人行报送截止日',
  `SUBMIT_DEADLINE` datetime COMMENT '行内报送截止日',
  `STATUS` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '有效状态',
  `SEND_STATUS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '报送状态',
  `STATUS_DATE` datetime COMMENT '数据日期',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '创建人',
  `CREATE_TIME` datetime COMMENT '创建时间',
  `UPDATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '更新人',
  `UPDATE_TIME` datetime COMMENT '更新时间',
  `ORG_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构代码',
  `DEPT_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '部门代码',
  `CREATE_DEPT` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建部门',
  `PROD_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '内部产品代码',
  `ASSET_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资产代码',
  UNIQUE KEY `PBC_COL_SPE_COUNTER_PARTY_SUB` (`DETAILUUID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资管收益权明细登记表';


delete from sys_batch_task where TASK_ID='8061';
INSERT INTO sys_batch_task
(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES('8061', 'SVR', '采集平台资产收益权细信息(ZG06)生成批处理', NULL, 'ZG06PbcColAssetUsufructTaskService', 8061, 'system', NULL, now(), 'system', now(), NULL);

/**2022-11-18 RHPTSJ-366 ZG06利率水平字段存储为两位错误修复为五位**/
alter table PBC_COL_ASSET_USUFRUCT_SUB modify column ASSET_RATE_LEVEL decimal(10,5) COMMENT '利率水平';
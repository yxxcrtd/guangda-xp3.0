delete from sys_menu where MENU_ID='REPORT_REGIST_001_003_021';
INSERT INTO sys_menu
(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('REPORT_REGIST_001_003_021', 'SVR', NULL, 'REPORT_REGIST_001_003', 'ZG08SpeCounterQuity', 21, '2', 'ZG08 特定目的载体交易对手明细信息', 'system', NULL, SYSDATE, 'system', SYSDATE);


delete from sys_menu_split where MENU_ID='REPORT_REGIST_001_003_014';
INSERT INTO sys_menu_split
(MENU_ID, SPLIT_ORDER_NO, SPLIT_MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('REPORT_REGIST_001_003_014', 3, '大数据采集平台', 'admin', NULL, SYSDATE, 'admin', SYSDATE);

delete from sys_page where PAGE_ID='ZG08SpeCounterQuity';
INSERT INTO sys_page
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ZG08SpeCounterQuity', 'SVR', '/pbc/zg08specounterequity/ZG08SpeCounterQuity', 'report/pbc/classic/zg08specounterequity/ZG08SpeCounterQuity', 'REPORT_REGIST_001_003_021', NULL, NULL, '01', '01', 'ZG08 特定目的载体交易对手明细信息', 'system', NULL, SYSDATE, 'system', SYSDATE);

delete from sys_datadict where DICT_CODE='PBC_SUBMIT_STATUS';

INSERT INTO sys_datadict
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('PBC_SUBMIT_STATUS', 'PBC', '人行报送状态', NULL, '1', 'system', NULL, SYSDATE, 'system', SYSDATE);

delete from sys_datadict_list where DICT_CODE='PBC_SUBMIT_STATUS';

INSERT INTO sys_datadict_list
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES('PBC_SUBMIT_STATUS', '01', '未报送', 1, '0', 'system', NULL, SYSDATE, 'system', SYSDATE, NULL);

INSERT INTO sys_datadict_list
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES('PBC_SUBMIT_STATUS', '03', '手工处理', 2, '0', 'system', NULL, SYSDATE, 'system', SYSDATE, NULL);


-- Create table
create table pbc_col_spe_counter_party_sub
(
  DETAILUUID               char(36) not null,
  PBC_PROD_ID           VARCHAR2(50) ,
  TERMID             VARCHAR2(50),
  ASSET_TYPE              VARCHAR2(5),
  PRRTY_PROD_TYPE             char(1),
  PRRTY_ORG_CODE              VARCHAR2(14),
  PRRTY_PROD_ID           VARCHAR2(15) ,
  CCY           VARCHAR2(3),
  AMT           NUMBER(20,2) ,
  AMT_CNY           NUMBER(20,2) ,
  SUBMIT_TIME           TIMESTAMP(6),
  PBC_SUBMIT_DEADLINE           TIMESTAMP(6) ,
  SUBMIT_DEADLINE TIMESTAMP(6),
  STATUS             VARCHAR2(50),
  SEND_STATUS             VARCHAR2(50),
  STATUS_DATE             TIMESTAMP(6),
  CREATE_USER             VARCHAR2(50),
  CREATE_TIME             TIMESTAMP(6),
  UPDATE_USER             VARCHAR2(50),
  UPDATE_TIME             TIMESTAMP(6),
  ORG_CODE             VARCHAR2(50),
  DEPT_CODE             VARCHAR2(50),
  CREATE_DEPT             VARCHAR2(50),
  PROD_ID             VARCHAR2(50)
);

alter table pbc_col_spe_counter_party_sub
  add constraint PK_pbc_col_spe_counter primary key (DETAILUUID)
-- Add comments to the table 
comment on table pbc_col_spe_counter_party_sub
  is '特定目的载体交易对手明细信息(ZG08)';
-- Add comments to the columns 
comment on column pbc_col_spe_counter_party_sub.DETAILUUID is '明细统计uuid';
comment on column pbc_col_spe_counter_party_sub.PBC_PROD_ID is '人行产品代码';
comment on column pbc_col_spe_counter_party_sub.TERMID is '期数';
comment on column pbc_col_spe_counter_party_sub.ASSET_TYPE is '资产负债项目';
comment on column pbc_col_spe_counter_party_sub.PRRTY_PROD_TYPE is '交易对手产品种类';
comment on column pbc_col_spe_counter_party_sub.PRRTY_ORG_CODE is '交易对手机构编码';
comment on column pbc_col_spe_counter_party_sub.PRRTY_PROD_ID is '交易对手产品代码';
comment on column pbc_col_spe_counter_party_sub.CCY is '币种';
comment on column pbc_col_spe_counter_party_sub.AMT is '期末余额';
comment on column pbc_col_spe_counter_party_sub.AMT_CNY is '期末余额折人民币';
comment on column pbc_col_spe_counter_party_sub.SUBMIT_TIME is '报送日期';
comment on column pbc_col_spe_counter_party_sub.PBC_SUBMIT_DEADLINE is '人行报送截止日';
comment on column pbc_col_spe_counter_party_sub.SUBMIT_DEADLINE is '行内报送截止日';
comment on column pbc_col_spe_counter_party_sub.STATUS is '有效状态';
comment on column pbc_col_spe_counter_party_sub.SEND_STATUS is '报送状态';
comment on column pbc_col_spe_counter_party_sub.STATUS_DATE is '数据日期';
comment on column pbc_col_spe_counter_party_sub.CREATE_USER is '创建人';
comment on column pbc_col_spe_counter_party_sub.CREATE_TIME is '创建时间';
comment on column pbc_col_spe_counter_party_sub.UPDATE_USER is '更新人';
comment on column pbc_col_spe_counter_party_sub.UPDATE_TIME is '更新时间';
comment on column pbc_col_spe_counter_party_sub.ORG_CODE is '机构代码';
comment on column pbc_col_spe_counter_party_sub.DEPT_CODE is '部门代码';
comment on column pbc_col_spe_counter_party_sub.CREATE_DEPT is '创建部门';
comment on column pbc_col_spe_counter_party_sub.PROD_ID is '内部产品代码';




delete from sys_batch_task where TASK_ID='8001';
INSERT INTO sys_batch_task
(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES('8001', 'SVR', '采集平台特定目的载体交易对手明细信息(ZG08)生成批处理', NULL, 'ZG08SpeCounterQuityTaskService', 3000, 'system', NULL, SYSDATE, 'system', SYSDATE, NULL);

delete from sys_datadict_list where DICT_CODE='SVR_REPORT_CODE' and ITEM_CODE ='ZG08';
INSERT INTO sys_datadict_list
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES('SVR_REPORT_CODE', 'ZG08', '特定目的载体交易对手明细信息(ZG08)', 54, '0', 'system', NULL, NOW(), 'system', NOW(), NULL);

delete from chb_reg_time_points where SEQNO ='ZG08';
INSERT INTO chb_reg_time_points
(SEQNO, ORG_CODE, REPORT_CODE, TYPE_DETAIL, SUBMIT_TIMETYPE, SUBMIT_REQUIRE3, SUBMIT_REQUIRE2, SUBMIT_REQUIRE, SUBMIT_POINT_BOND, SUBMIT_POINT_BANK, UPDATE_SUBMIT_POINT, DELETE_SUBMING_POINT, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, CREATE_DEPT)
VALUES('ZG08', NULL, 'ZG08', NULL, NULL, NULL, '02', '数据有效后N个工作日', 5.00, 5.00, 12.00, 4.00, 'E', NULL, NULL, 'system', NOW(), 'system', '2021-09-17 11:25:29.0', NOW());


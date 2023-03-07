-- Create table
DROP TABLE PBC_COL_ASSET_USUFRUCT_SUB;
create table PBC_COL_ASSET_USUFRUCT_SUB
(
  detailuuid          CHAR(36) not null,
  pbc_prod_id         VARCHAR2(50),
  termid              VARCHAR2(50),
  ASSETS_LIABILITIES          VARCHAR2(30),
  ASSET_USUFRUCT_CODE         VARCHAR2(30),
  ASSETS_TRANSFER_NAME     VARCHAR2(200),
  ASSETS_TRANSFER_ID      VARCHAR2(100),
  ASSETS_INVEST       VARCHAR2(100),
  ASSETS_TRANSFER_IINDU                 VARCHAR2(2),
  ASSETS_TRANSFER                 VARCHAR2(6),
  ECONOMIC_COMPOSITION             VARCHAR2(3),
  ASSETS_TRANSFER_SCALE         VARCHAR2(4),
  TRANSFER_VDATE DATE,
  TRANSFER_ESTI_MDATE     DATE,
  TRANSFER_EXTDUE_MDATE              DATE,
  TRANSFER_ACTMDATE         DATE,
  BASICASSET_TYPE VARCHAR2(2),
  ASSETS_ORIGCCY VARCHAR2(3),
  ASSETS_ORIGAMT NUMBER(20,2),
  ASSETS_ORIGAMT_CNY NUMBER(20,2),
  CURRENCY_TRANSFERCCY VARCHAR2(3),
  CURRENCY_TRANSFERAMT NUMBER(20,2),
  CURRENCY_TRANSFERAMTCNY NUMBER(20,2),
  LABEL_TRANSFEROR_MARK VARCHAR2(1),
  REPURCHASE_MARK VARCHAR2(1),
  RATE_FIXED_FLAG VARCHAR2(4),
  ASSET_RATE_LEVEL NUMBER(20,2),
  GUARANTEE_METHOD VARCHAR2(3),
  ASSETS_INV_DEPA VARCHAR2(1),
  CURRENCY_EPERIODCCY VARCHAR2(3),
  CURRENCY_AMT NUMBER(20,2),
  CURRENCY_AMT_CNY NUMBER(20,2),
  submit_time         TIMESTAMP(6),
  pbc_submit_deadline TIMESTAMP(6),
  submit_deadline     TIMESTAMP(6),
  status              VARCHAR2(50),
  send_status         VARCHAR2(50),
  status_date         TIMESTAMP(6),
  create_user         VARCHAR2(50),
  create_time         TIMESTAMP(6),
  update_user         VARCHAR2(50),
  update_time         TIMESTAMP(6),
  org_code            VARCHAR2(50),
  dept_code           VARCHAR2(50),
  create_dept         VARCHAR2(50),
  prod_id             VARCHAR2(50),
  ASSET_CODE VARCHAR2(50)
);
-- Add comments to the table 
comment on table PBC_COL_ASSET_USUFRUCT_SUB
  is 'ZG06资产收益权明细信息';
-- Add comments to the columns 
comment on column PBC_COL_ASSET_USUFRUCT_SUB.detailuuid
  is '明细统计uuid';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.pbc_prod_id
  is '人行产品代码';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.termid
  is '期数';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_LIABILITIES
is '资产负债项目';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSET_USUFRUCT_CODE
is '资产收益权内部编码';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_TRANSFER_NAME
is '基础资产出让机构名称';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_TRANSFER_ID
is '基础资产出让机构代码';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_INVEST
is '基础资产出让机构类型';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_TRANSFER_IINDU
is '基础资产出让机构行业';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_TRANSFER
is '基础资产出让机构注册地区';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ECONOMIC_COMPOSITION
is '基础资产出让机构经济成分';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_TRANSFER_SCALE
is '基础资产出让机构规模';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.TRANSFER_VDATE
is '转让起始日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.TRANSFER_ESTI_MDATE
is '转让预计终止日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.TRANSFER_EXTDUE_MDATE
is '转让展期到期日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.TRANSFER_ACTMDATE
is '转让实际终止日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.BASICASSET_TYPE
is '基础资产类型';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_ORIGCCY
is '基础资产原始协议币种';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_ORIGAMT
is '基础资产原始协议金额';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_ORIGAMT_CNY
is '基础资产原始协议金额折人民币';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_TRANSFERCCY
is '基础资产转让币种';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_TRANSFERAMT
is '基础资产转让金额';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_TRANSFERAMTCNY
is '基础资产转让金额折人民币';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.LABEL_TRANSFEROR_MARK
is '出让机构出表标识';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.REPURCHASE_MARK
is '出让机构回购标识';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.RATE_FIXED_FLAG
is '利率是否固定';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSET_RATE_LEVEL
is '利率水平';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.GUARANTEE_METHOD
is '担保方式';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSETS_INV_DEPA
is '基础资产投向部门';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_EPERIODCCY
is '基础资产期末币种';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_AMT
is '基础资产期末余额';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.CURRENCY_AMT_CNY
is '基础资产期末余额折人民币';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.submit_time
  is '报送日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.submit_deadline
  is '行内报送截止日';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.status
  is '有效状态';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.send_status
  is '报送状态';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.status_date
  is '数据日期';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.create_user
  is '创建人';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.create_time
  is '创建时间';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.update_user
  is '更新人';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.update_time
  is '更新时间';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.org_code
  is '机构代码';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.dept_code
  is '部门代码';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.create_dept
  is '创建部门';
comment on column PBC_COL_ASSET_USUFRUCT_SUB.prod_id
  is '内部产品代码';
  comment on column PBC_COL_ASSET_USUFRUCT_SUB.ASSET_CODE
  is '资产代码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_COL_ASSET_USUFRUCT_SUB
  add constraint PK_ASSET_USUFRUCT_SUB primary key (DETAILUUID)
  using index ;



delete from sys_batch_task where TASK_ID='8061';
INSERT INTO sys_batch_task
(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES('8061', 'SVR', '采集平台资产收益权细信息(ZG06)生成批处理', NULL, 'ZG06PbcColAssetUsufructTaskService', 8061, 'system', NULL, SYSDATE, 'system', SYSDATE, NULL);


/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_019';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_019','SVR',null,'REPORT_REGIST_001_003','ZG06PbcAssetSusfructSub',19,'2','ZG06 资产收益权明细信息','system',null,sysdate,'system',sysdate);
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG06PbcAssetSusfructSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG06PbcAssetSusfructSub','SVR','/pbc/zg06assetusufructsub/ZG06PbcAssetSusfructSub','report/pbc/classic/zg06assetusufructsub/ZG06PbcAssetSusfructSub','REPORT_REGIST_001_003_019',null,null,'02','02','ZG02 资管产品初始募集信息','system',null,sysdate,'system',sysdate);

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG06PbcAssetSusfructSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_manual', '01', '01', null, '手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG06PbcAssetSusfructSub', 'ZG06PbcAssetSusfructSub_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'C', null);

/*数据字典配置*/
DELETE from SYS_PAGE_DICTLIST where PAGE_ID = 'ZG06PbcAssetSusfructSub' and DICT_ID = 'SUBMIT_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ZG06PbcAssetSusfructSub','999999','SUBMIT_STATUS','01',null,null,'system',null,NOW(),'system',NOW(),null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ZG06PbcAssetSusfructSub','999999','SUBMIT_STATUS','03',null,null,'system',null,NOW(),'system',NOW(),null);


/**2022-11-18 RHPTSJ-366 ZG06利率水平字段存储为两位错误修复为五位**/
alter table PBC_COL_ASSET_USUFRUCT_SUB modify (ASSET_RATE_LEVEL NUMBER(10,5));
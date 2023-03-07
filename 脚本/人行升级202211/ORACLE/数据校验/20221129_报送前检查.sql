-- MySQL
-- Submit before check
DROP TABLE PBC_SUBMIT_BEFORE_CHECK;
CREATE TABLE PBC_SUBMIT_BEFORE_CHECK (
  DATA_UUID varchar2(64) NOT NULL ,
  DATA_ID varchar2(200) DEFAULT NULL ,
  RULE_ID varchar2(120) DEFAULT NULL ,
  PBC_ELEMENT_CODE varchar2(15) DEFAULT NULL,
  REPORT_NAME varchar2(120) DEFAULT NULL ,
  FIELD_NAME varchar2(120) DEFAULT NULL ,
  RULE_TYPE varchar2(20) DEFAULT NULL ,
  RULE_CODE varchar2(50) DEFAULT NULL ,
  RULE_DETAILS varchar2(300) DEFAULT NULL,
  ERR_MSG CLOB DEFAULT NULL ,
  RELEVANCE_REPORT varchar2(400) DEFAULT NULL ,
  CHECK_DATE timestamp(6) DEFAULT NULL ,
  ORG_DATA CLOB DEFAULT NULL ,
  FLAG_UUID varchar2(64) DEFAULT NULL ,
  CREATE_USER varchar2(20) DEFAULT NULL ,
  CREATE_DEPT varchar2(20) DEFAULT NULL ,
  CREATE_TIME timestamp(6) DEFAULT NULL ,
  UPDATE_USER varchar2(20) DEFAULT NULL,
  UPDATE_TIME timestamp(6) DEFAULT NULL ,
  constraint PK_PBC_SUBMIT_BEFORE_CHECK PRIMARY KEY(DATA_UUID)
);
comment on table PBC_SUBMIT_BEFORE_CHECK is '报送前检查表';
comment on column PBC_SUBMIT_BEFORE_CHECK.DATA_UUID
is 'UUID';
comment on column PBC_SUBMIT_BEFORE_CHECK.DATA_ID
is '数据唯一标识';
comment on column PBC_SUBMIT_BEFORE_CHECK.RULE_ID
is '规则编码';
comment on column PBC_SUBMIT_BEFORE_CHECK.PBC_ELEMENT_CODE
is '人行要素编码';
comment on column PBC_SUBMIT_BEFORE_CHECK.REPORT_NAME
is '报表名称';
comment on column PBC_SUBMIT_BEFORE_CHECK.FIELD_NAME
is '字段名称';
comment on column PBC_SUBMIT_BEFORE_CHECK.RULE_TYPE
is '规则类型';
comment on column PBC_SUBMIT_BEFORE_CHECK.RULE_CODE
is '规则标识';
comment on column PBC_SUBMIT_BEFORE_CHECK.RULE_DETAILS
is '规则详细内容';
comment on column PBC_SUBMIT_BEFORE_CHECK.ORG_DATA
is '错误数据';
comment on column PBC_SUBMIT_BEFORE_CHECK.ERR_MSG
is '错误信息提示语';
comment on column PBC_SUBMIT_BEFORE_CHECK.FLAG_UUID
is '预览主键';
comment on column PBC_SUBMIT_BEFORE_CHECK.CHECK_DATE
is '检查时间';


-- sys_menu
DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_CHECK_001_004_002' AND PAGE_ID = 'PbcSubmitBeforeCheck001';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_CHECK_001_004_002', 'SVR', null, 'REPORT_CHECK_001_004', 'PbcSubmitBeforeCheck001', 2, 2, '报送前检查', 'system', null, sysdate, 'system', sysdate);

-- sys_page
DELETE FROM sys_page WHERE PAGE_ID = 'PbcSubmitBeforeCheck001';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcSubmitBeforeCheck001', 'SVR', '/svreport/submitbefore/PbcSubmitBeforeCheck001', 'svreport/submitbefore/PbcSubmitBeforeCheck001', 'REPORT_CHECK_001_004_002', null, null, '02', '02', '报送前检查', 'system', null, sysdate, 'system', sysdate);


/**SYS_MENU**/
DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_REGIST_001_003_017';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_REGIST_001_003_017', 'SVR', null, 'REPORT_REGIST_001_003', 'ZG04PbcPrdCashRaiseSub', 17, '2', 'ZG04 资管产品存续期募集信息', 'system', null, sysdate, 'system', sysdate);
/**SYS_PAGE**/
DELETE FROM sys_page WHERE PAGE_ID = 'ZG04PbcPrdCashRaiseSub';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ZG04PbcPrdCashRaiseSub', 'SVR', '/pbc/zg04prdcashraisesub/ZG04PbcPrdCashRaiseSub', 'report/pbc/classic/zg04prdcashraisesub/ZG04PbcPrdCashRaiseSub', 'REPORT_REGIST_001_003_017', null, null, '02', '02', 'ZG04 资管产品存续期募集信息', 'system', null, sysdate, 'system', sysdate);
/**建表登记表**/
DROP TABLE pbc_col_prod_raising_sub;
CREATE TABLE
    pbc_col_prod_raising_sub
(
    REGIST_SEQNO CHAR(36) NOT NULL ,
    RAISING_DATE VARCHAR2(50) ,
    PBC_PROD_ID VARCHAR2(50) ,
    BUY_PLACE VARCHAR2(50),
    CUSTOMER_TYPE1 VARCHAR2(50),
    CCY CHAR(3) ,
    RAISING_AMTSUM NUMBER(30,2),
    RAISING_AMTSUM_CNY NUMBER(30,2) ,
    RAISING_QTY NUMBER(30,5) ,
    REDAMT NUMBER(30,2) ,
    REDAMT_CNY NUMBER(30,2) ,
    REDQTY NUMBER(30,5) ,
    ENDAMTSUM NUMBER(30,2) ,
    ENDAMTSUM_CNY NUMBER(30,2) ,
    ENDQTY NUMBER(30,5) ,
    PROD_VALUE NUMBER(30,14),
    PROD_VALUE_CNY NUMBER(30,14),
    UNIT_VALUE_TOTAL NUMBER(30,14) ,
    UNIT_VALUE_TOTAL_CNY NUMBER(30,14),
    PROD_END_YIELD NUMBER(30,14),
    SEND_STATUS VARCHAR2(50),
    SUBMIT_TIME DATE,
    SUBMIT_FEEDBACK_STATUS VARCHAR2(50),
    SUMIT_SEQ VARCHAR2(20) ,
    STATUS_DATE DATE ,
    PBC_SUBMIT_DEADLINE DATE ,
    SUBMIT_DEADLINE DATE ,
    STATUS VARCHAR2(50),
    CREATE_USER VARCHAR2(20),
    ORG_CODE VARCHAR2(32),
    CREATE_DEPT VARCHAR2(32),
    CREATE_TIME DATE,
    UPDATE_USER VARCHAR2(20),
    UPDATE_TIME DATE ,
    constraint PK_pbc_col_prod_raising_sub PRIMARY KEY(REGIST_SEQNO)
);
comment on table pbc_col_prod_raising_sub is '报表生成检查表';
comment on column pbc_col_prod_raising_sub.RAISING_DATE
is '统计年月';
comment on column pbc_col_prod_raising_sub.PBC_PROD_ID
is '人行产品代码';
comment on column pbc_col_prod_raising_sub.BUY_PLACE
is '地区代码:销售区域代码';
comment on column pbc_col_prod_raising_sub.CUSTOMER_TYPE1
is '客户类型:客户一级类型，枚举值参见CUSTOMER_TYPE1';
comment on column pbc_col_prod_raising_sub.CCY
is '币种代码:币种';
comment on column pbc_col_prod_raising_sub.RAISING_AMTSUM
is '当期申购金额';
comment on column pbc_col_prod_raising_sub.RAISING_AMTSUM_CNY
is '当期申购金额折人民币';
comment on column pbc_col_prod_raising_sub.UNIT_VALUE_TOTAL
is '净值型产品期末累计净值';
comment on column pbc_col_prod_raising_sub.UNIT_VALUE_TOTAL_CNY
is '净值型产品期末累计净值折人民币';
comment on column pbc_col_prod_raising_sub.PROD_END_YIELD
is '产品期末业绩表现';
comment on column pbc_col_prod_raising_sub.PBC_SUBMIT_DEADLINE
is '人行报送截止日';
comment on column pbc_col_prod_raising_sub.SUBMIT_DEADLINE
is '行内报送截止日';
comment on column pbc_col_prod_raising_sub.ORG_CODE
is '机构代码';

/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG04PbcPrdCashRaiseSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG04PbcPrdCashRaiseSub', 'ZG04PbcPrdCashRaiseSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG04PbcPrdCashRaiseSub', 'ZG04PbcPrdCashRaiseSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG04PbcPrdCashRaiseSub', 'ZG04PbcPrdCashRaiseSub_btn_manual', '01', '01', null, '手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG04PbcPrdCashRaiseSub', 'ZG04PbcPrdCashRaiseSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG04PbcPrdCashRaiseSub', 'ZG04PbcPrdCashRaiseSub_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'C', null);
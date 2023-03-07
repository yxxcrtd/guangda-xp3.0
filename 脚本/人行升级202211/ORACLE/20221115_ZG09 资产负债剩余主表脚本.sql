create table ZG09_PBC_COL_REPORT
(
    REPORTUUID             char(36)     not null,
    TERMID                 varchar2(20),
    REPORTTYPE             varchar2(20),
    STARTDATE              date ,
    ENDDATE                date,
    ISSUE_PARTY_ID         varchar2(50) ,
    ISSUE_PARTY_NAME       varchar2(50) ,
    REPORT_DATE            date ,
    SUBMIT_TIME            date,
    CHB_SUBMIT_DEADLINE    date ,
    SUBMIT_DEADLINE        date,
    STATUS                 varchar2(50),
    SEND_STATUS            varchar2(20),
    STATUS_DATE            date,
    CREATE_USER            varchar2(20),
    CREATE_TIME            TIMESTAMP(6) ,
    UPDATE_USER            varchar2(20),
    UPDATE_TIME            TIMESTAMP(6) ,
    ORG_CODE               varchar2(20),
    DEPT_CODE              varchar2(20),
    CREATE_DEPT            varchar2(32),
    PBC_PROD_TYPE          char(1),
    G000A                  NUMBER(20,2) ,
	G000B                  NUMBER(20,2) ,
	G000C                  NUMBER(20,2) ,
	G000D                  NUMBER(20,2) ,
	G000E                  NUMBER(20,2) ,
	G100A                  NUMBER(20,2) ,
	G100B                  NUMBER(20,2) ,
	G100C                  NUMBER(20,2) ,
	G100D                  NUMBER(20,2) , 
	G100E                  NUMBER(20,2) , 
	G200A                  NUMBER(20,2) , 
	G200B                  NUMBER(20,2) , 
	G200C                  NUMBER(20,2) , 
	G200D                  NUMBER(20,2) , 
	G200E                  NUMBER(20,2) , 
	G300A                  NUMBER(20,2) ,  
	G300B                  NUMBER(20,2) ,  
	G300C                  NUMBER(20,2) , 
	G300D                  NUMBER(20,2) , 
	G300E                  NUMBER(20,2) , 
	G400A                  NUMBER(20,2) , 
	G400B                  NUMBER(20,2) , 
	G400C                  NUMBER(20,2) , 
	G400D                  NUMBER(20,2) , 
	G400E                  NUMBER(20,2) , 
	G500A                  NUMBER(20,2) , 
	G500B                  NUMBER(20,2) , 
	G500C                  NUMBER(20,2) , 
	G500D                  NUMBER(20,2) , 
	G500E                  NUMBER(20,2) , 
	G600A                  NUMBER(20,2) , 
	G600B                  NUMBER(20,2) , 
	G600C                  NUMBER(20,2) , 
	G600D                  NUMBER(20,2) , 
	G600E                  NUMBER(20,2) , 
	G700A                  NUMBER(20,2) , 
	G700B                  NUMBER(20,2) , 
	G700C                  NUMBER(20,2) , 
	G700D                  NUMBER(20,2) , 
	G700E                  NUMBER(20,2) , 
	G800A                  NUMBER(20,2) , 
	G800B                  NUMBER(20,2) , 
	G800C                  NUMBER(20,2) , 
	G800D                  NUMBER(20,2) , 
	G800E                  NUMBER(20,2) , 
	G00001                 NUMBER(20,2) , 
	G00002                 NUMBER(20,2) ,
	CONSTRAINT ZG09_PBC_COL_REPORT_PK PRIMARY KEY (REPORTUUID)
);
COMMENT ON table ZG09_PBC_COL_REPORT IS '资产负债剩余主表';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.REPORTUUID is '主键';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.TERMID is '期数';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.REPORTTYPE is '报表类型:';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.STARTDATE is '开始日期:';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.ENDDATE is '结束日期:';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.ISSUE_PARTY_ID is '发行机构代码';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.ISSUE_PARTY_NAME is '发行机构名称';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.REPORT_DATE is '制表日期';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.SUBMIT_TIME is '报送日期';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.CHB_SUBMIT_DEADLINE is '中债报送截止日';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.SUBMIT_DEADLINE is '行内报送截止日';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.STATUS is '有效状态';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.SEND_STATUS is '报送状态';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.STATUS_DATE is '数据日期';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.CREATE_USER is '创建人';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.CREATE_TIME is '创建时间';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.UPDATE_USER is '更新人';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.UPDATE_TIME is '更新时间';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.ORG_CODE is '机构代码';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.DEPT_CODE is '部门代码';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.CREATE_DEPT is '创建部门';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.PBC_PROD_TYPE is '产品品种';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G000A is 'G000A_资产方-债务工具-总计';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G000B is 'G000B_资产方-股权-总计';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G000C is 'G000C_资产方-特定目的载体份额-总计';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G000D is 'G000D_负债及权益方-债务工具-总计';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G000E is 'G000E_负债及权益方-实收本金-总计';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G100A is 'G100A_资产方-债务工具-1个月（含）以下';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G100B is 'G100B_资产方-股权-1个月（含）以下';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G100C is 'G100C_资产方-特定目的载体份额-1个月（含）以下';
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G100D is 'G100D_负债及权益方-债务工具-1个月（含）以下'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G100E is 'G100E_负债及权益方-实收本金-1个月（含）以下'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G200A is 'G200A_资产方-债务工具-1-3个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G200B is 'G200B_资产方-股权-1-3个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G200C is 'G200C_资产方-特定目的载体份额-1-3个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G200D is 'G200D_负债及权益方-债务工具-1-3个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G200E is 'G200E_负债及权益方-实收本金-1-3个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G300A is 'G300A_资产方-债务工具-3-6个月（含）';  
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G300B is 'G300B_资产方-股权-3-6个月（含）';  
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G300C is 'G300C_资产方-特定目的载体份额-3-6个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G300D is 'G300D_负债及权益方-债务工具-3-6个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G300E is 'G300E_负债及权益方-实收本金-3-6个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G400A is 'G400A_资产方-债务工具-6-12个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G400B is 'G400B_资产方-股权-6-12个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G400C is 'G400C_资产方-特定目的载体份额-6-12个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G400D is 'G400D_负债及权益方-债务工具-6-12个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G400E is 'G400E_负债及权益方-实收本金-6-12个月（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G500A is 'G500A_资产方-债务工具-1-2年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G500B is 'G500B_资产方-股权-1-2年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G500C is 'G500C_资产方-特定目的载体份额-1-2年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G500D is 'G500D_负债及权益方-债务工具-1-2年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G500E is 'G500E_负债及权益方-实收本金-1-2年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G600A is 'G600A_资产方-债务工具-2-3年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G600B is 'G600B_资产方-股权-2-3年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G600C is 'G600C_资产方-特定目的载体份额-2-3年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G600D is 'G600D_负债及权益方-债务工具-2-3年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G600E is 'G600E_负债及权益方-实收本金-2-3年（含）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G700A is 'G700A_资产方-债务工具-3年以上'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G700B is 'G700B_资产方-股权-3年以上'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G700C is 'G700C_资产方-特定目的载体份额-3年以上'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G700D is 'G700D_负债及权益方-债务工具-3年以上'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G700E is 'G700E_负债及权益方-实收本金-3年以上'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G800A is 'G800A_资产方-债务工具-无固定期限'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G800B is 'G800B_资产方-股权-无固定期限'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G800C is 'G800C_资产方-特定目的载体份额-无固定期限'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G800D is 'G800D_负债及权益方-债务工具-无固定期限'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G800E is 'G800E_负债及权益方-实收本金-无固定期限'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G00001 is 'G00001_附报-金融机构表内资产余额（本外币）'; 
COMMENT ON COLUMN  ZG09_PBC_COL_REPORT.G00002 is 'G00002_附报-金融机构表内金融资产余额（本外币）';


alter table pbc_month_asset_position drop column END_DAYS_PBC;
alter table pbc_month_asset_position add END_DAYS_PBC NUMBER(30,4);
comment on column pbc_month_asset_position.END_DAYS_PBC is '剩余期限，ZG09用';

delete from  sys_batch_task where TASK_ID='5009';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('5009', 'SVR', '采集平台资产负债剩余期限信息(ZG09)生成批处理', null, 'ZG09PbcAssetDebtTermTaskService', 3009, 'system', null, sysdate, 'system', sysdate, null);


/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_022';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_022','SVR',null,'REPORT_REGIST_001_003','ZG09PbcAssetDebtTermSub',22,'2','ZG09 资产负债剩余期限信息','system',null,sysdate,'system',sysdate);
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG09PbcAssetDebtTermSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG09PbcAssetDebtTermSub','SVR','/pbc/zg09assetdebtterm/ZG09PbcAssetDebtTermSub','report/pbc/classic/zg09assetdebtterm/ZG09PbcAssetDebtTermSub','REPORT_REGIST_001_003_022',null,null,'02','02','ZG10 债券等资产配置情况信息','system',null,sysdate,'system',sysdate);

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG09PbcAssetDebtTermSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_manual', '01', '01', null, '手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'C', null);

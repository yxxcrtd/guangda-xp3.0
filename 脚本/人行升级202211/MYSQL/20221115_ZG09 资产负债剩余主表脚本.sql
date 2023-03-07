DROP TABLE IF EXISTS  ZG09_PBC_COL_REPORT;
create table ZG09_PBC_COL_REPORT
(
    REPORTUUID             char(36)     not null comment '主键'
        primary key,
    TERMID                 varchar(20)  null comment '期数',
    REPORTTYPE             varchar(20)  null comment '报表类型:',
    STARTDATE              date         null  comment '开始日期:',
    ENDDATE                date         null  comment '结束日期:',
    ISSUE_PARTY_ID         varchar(50)  null comment '发行机构代码',
    ISSUE_PARTY_NAME       varchar(50)  null comment '发行机构名称',
    REPORT_DATE            date         null comment '制表日期',
    SUBMIT_TIME            date         null comment '报送日期',
    CHB_SUBMIT_DEADLINE    date         null comment '中债报送截止日',
    SUBMIT_DEADLINE        date         null comment '行内报送截止日',
    STATUS                 varchar(50)  null comment '有效状态',
    SEND_STATUS            varchar(20)  null comment '报送状态',
    STATUS_DATE            date         null comment '数据日期',
    CREATE_USER            varchar(20)  null comment '创建人',
    CREATE_TIME            datetime     null comment '创建时间',
    UPDATE_USER            varchar(20)  null comment '更新人',
    UPDATE_TIME            datetime     null comment '更新时间',
    ORG_CODE               varchar(20)  null comment '机构代码',
    DEPT_CODE              varchar(20)  null comment '部门代码',
    CREATE_DEPT            varchar(32)  null comment '创建部门',
    PBC_PROD_TYPE          char(1)  null comment '产品品种',
    G000A                  decimal(20,2) null comment 'G000A_资产方-债务工具-总计',
	G000B                  decimal(20,2) null comment 'G000B_资产方-股权-总计',
	G000C                  decimal(20,2) null comment 'G000C_资产方-特定目的载体份额-总计',
	G000D                  decimal(20,2) null comment 'G000D_负债及权益方-债务工具-总计',
	G000E                  decimal(20,2) null comment 'G000E_负债及权益方-实收本金-总计',
	G100A                  decimal(20,2) null comment 'G100A_资产方-债务工具-1个月（含）以下',
	G100B                  decimal(20,2) null comment 'G100B_资产方-股权-1个月（含）以下',
	G100C                  decimal(20,2) null comment 'G100C_资产方-特定目的载体份额-1个月（含）以下',
	G100D                  decimal(20,2) null comment 'G100D_负债及权益方-债务工具-1个月（含）以下', 
	G100E                  decimal(20,2) null comment 'G100E_负债及权益方-实收本金-1个月（含）以下', 
	G200A                  decimal(20,2) null comment 'G200A_资产方-债务工具-1-3个月（含）', 
	G200B                  decimal(20,2) null comment 'G200B_资产方-股权-1-3个月（含）', 
	G200C                  decimal(20,2) null comment 'G200C_资产方-特定目的载体份额-1-3个月（含）', 
	G200D                  decimal(20,2) null comment 'G200D_负债及权益方-债务工具-1-3个月（含）', 
	G200E                  decimal(20,2) null comment 'G200E_负债及权益方-实收本金-1-3个月（含）', 
	G300A                  decimal(20,2) null comment 'G300A_资产方-债务工具-3-6个月（含）',  
	G300B                  decimal(20,2) null comment 'G300B_资产方-股权-3-6个月（含）',  
	G300C                  decimal(20,2) null comment 'G300C_资产方-特定目的载体份额-3-6个月（含）', 
	G300D                  decimal(20,2) null comment 'G300D_负债及权益方-债务工具-3-6个月（含）', 
	G300E                  decimal(20,2) null comment 'G300E_负债及权益方-实收本金-3-6个月（含）', 
	G400A                  decimal(20,2) null comment 'G400A_资产方-债务工具-6-12个月（含）', 
	G400B                  decimal(20,2) null comment 'G400B_资产方-股权-6-12个月（含）', 
	G400C                  decimal(20,2) null comment 'G400C_资产方-特定目的载体份额-6-12个月（含）', 
	G400D                  decimal(20,2) null comment 'G400D_负债及权益方-债务工具-6-12个月（含）', 
	G400E                  decimal(20,2) null comment 'G400E_负债及权益方-实收本金-6-12个月（含）', 
	G500A                  decimal(20,2) null comment 'G500A_资产方-债务工具-1-2年（含）', 
	G500B                  decimal(20,2) null comment 'G500B_资产方-股权-1-2年（含）', 
	G500C                  decimal(20,2) null comment 'G500C_资产方-特定目的载体份额-1-2年（含）', 
	G500D                  decimal(20,2) null comment 'G500D_负债及权益方-债务工具-1-2年（含）', 
	G500E                  decimal(20,2) null comment 'G500E_负债及权益方-实收本金-1-2年（含）', 
	G600A                  decimal(20,2) null comment 'G600A_资产方-债务工具-2-3年（含）', 
	G600B                  decimal(20,2) null comment 'G600B_资产方-股权-2-3年（含）', 
	G600C                  decimal(20,2) null comment 'G600C_资产方-特定目的载体份额-2-3年（含）', 
	G600D                  decimal(20,2) null comment 'G600D_负债及权益方-债务工具-2-3年（含）', 
	G600E                  decimal(20,2) null comment 'G600E_负债及权益方-实收本金-2-3年（含）', 
	G700A                  decimal(20,2) null comment 'G700A_资产方-债务工具-3年以上', 
	G700B                  decimal(20,2) null comment 'G700B_资产方-股权-3年以上', 
	G700C                  decimal(20,2) null comment 'G700C_资产方-特定目的载体份额-3年以上', 
	G700D                  decimal(20,2) null comment 'G700D_负债及权益方-债务工具-3年以上', 
	G700E                  decimal(20,2) null comment 'G700E_负债及权益方-实收本金-3年以上', 
	G800A                  decimal(20,2) null comment 'G800A_资产方-债务工具-无固定期限', 
	G800B                  decimal(20,2) null comment 'G800B_资产方-股权-无固定期限', 
	G800C                  decimal(20,2) null comment 'G800C_资产方-特定目的载体份额-无固定期限', 
	G800D                  decimal(20,2) null comment 'G800D_负债及权益方-债务工具-无固定期限', 
	G800E                  decimal(20,2) null comment 'G800E_负债及权益方-实收本金-无固定期限', 
	G00001                 decimal(20,2) null comment 'G00001_附报-金融机构表内资产余额（本外币）', 
	G00002                 decimal(20,2) null comment 'G00002_附报-金融机构表内金融资产余额（本外币）'
) comment '资产负债剩余主表';


alter table pbc_month_asset_position drop column END_DAYS_PBC;
ALTER TABLE pbc_month_asset_position ADD COLUMN END_DAYS_PBC decimal(30,4) COMMENT '剩余期限，ZG09用';

delete from  sys_batch_task where TASK_ID='5009';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('5009', 'SVR', '采集平台资产负债剩余期限信息(ZG09)生成批处理', null, 'ZG09PbcAssetDebtTermTaskService', 3009, 'system', null, now(), 'system', now(), null);


/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_022';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_022','SVR',null,'REPORT_REGIST_001_003','ZG09PbcAssetDebtTermSub',22,'2','ZG09 资产负债剩余期限信息','system',null,now(),'system',now());
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG09PbcAssetDebtTermSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG09PbcAssetDebtTermSub','SVR','/pbc/zg09assetdebtterm/ZG09PbcAssetDebtTermSub','report/pbc/classic/zg09assetdebtterm/ZG09PbcAssetDebtTermSub','REPORT_REGIST_001_003_022',null,null,'02','02','ZG10 债券等资产配置情况信息','system',null,now(),'system',now());

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG09PbcAssetDebtTermSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG09PbcAssetDebtTermSub', 'ZG09PbcAssetDebtTermSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

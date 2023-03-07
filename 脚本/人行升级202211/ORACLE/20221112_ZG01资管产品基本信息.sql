-- 资管产品基本信息登记表 新建

declare
    num number;
begin
    select count(1) into num from user_tables where table_name = 'PBC_COL_PRD_BASEINFO_SUB';
    if num > 0 then
        execute immediate 'drop table PBC_COL_PRD_BASEINFO_SUB';
    end if;
end;
/
create table PBC_COL_PRD_BASEINFO_SUB
(
    REGIST_SEQNO           char(36)  not null primary key ,
    PBC_PROD_ID            varchar(50) null ,
    ISSUE_PARTY_ID         varchar(50) null,
    ISSUE_PARTY_NAME       varchar(200) null,
    PBC_PROD_TYPE          char null,
    PROD_NAME              varchar(200) null,
    IVSTORIENTATION        varchar(1) null,
    PROD_BRAND             varchar(180) null,
    PROD_STAGE             decimal(30, 4) null,
    PROD_ID                varchar(150) null,
    INFO_TYPE              char null,
    RAISE_CCY              varchar(50) null,
    CASH_PRIN_CCY          varchar(50) null,
    CASH_PROF_CCY          varchar(50) null,
    RAISE_TYPE             char null,
    MANAGE_TYPE            char null,
    FIDUCIARY_DUTY         varchar(50) null,
    OPERATE_MODE           varchar(50) null,
    RAISE_VDATE            date null,
    RAISE_ENDDATE          date null,
    TERM_RIGHT_FLAG        char null,
    CUS_REDEEM_FLAG        char null,
    CREDIT_FLAG            char null,
    CREDIT_TYPE            varchar(50) null,
    CREDIT_MODE            varchar(50) null,
    IN_DEP_NAME            varchar(50) null,
    IN_DEP_CODE            varchar(50) null,
    OUT_DEP_CTRY           varchar(1000) null,
    OUT_DEP_NAME           varchar(200) null,
    VDATE                  date null,
    CDATE                  date null,
    MDATE                  date null,
    IS_CONSTRACTE          char null,
    TRANSFER_TYPE          char null,
    CASH_MANA_CATE         varchar(32) null,
    CROSS_BORDER_FLAG      varchar(32) null,
    IS_PROPERTY_FLAG       varchar(2) null,
    BASE_INFO_SIGN         varchar(1) null,
    CHANGE_REASON          varchar(200) null,
    BACKUP1                varchar(200) null,
    BACKUP2                varchar(200) null,
    BACKUP3                varchar(200) null,
    BACKUP4                varchar(200) null,
    BACKUP5                varchar(200) null,
    SEND_STATUS            varchar(50) null,
    SUBMIT_TIME            DATE null,
    SUBMIT_FEEDBACK_STATUS varchar(50) null,
    SUMIT_SEQ              varchar(20) null,
    STATUS_DATE            date null,
    PBC_SUBMIT_DEADLINE    DATE null,
    SUBMIT_DEADLINE        DATE null,
    STATUS                 varchar(50) null,
    CREATE_USER            varchar(20) null,
    ORG_CODE               varchar(20) null,
    CREATE_DEPT            varchar(20) null,
    CREATE_TIME            DATE null,
    UPDATE_USER            varchar(20) null,
    UPDATE_TIME            DATE null

);

comment on table PBC_COL_PRD_BASEINFO_SUB is '资管产品基本信息登记表';
comment on column PBC_COL_PRD_BASEINFO_SUB.REGIST_SEQNO is '登记序列';
comment on column PBC_COL_PRD_BASEINFO_SUB.PBC_PROD_ID is '人行产品代码';
comment on column PBC_COL_PRD_BASEINFO_SUB.ISSUE_PARTY_ID is '理财产品的发行机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB.ISSUE_PARTY_NAME is '发行机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB.PBC_PROD_TYPE is '产品品种';
comment on column PBC_COL_PRD_BASEINFO_SUB.PROD_NAME is '产品名称';
comment on column PBC_COL_PRD_BASEINFO_SUB.IVSTORIENTATION is '产品类型，1固定收益类/2权益类/3商品及金融衍生品类/4混合类';
comment on column PBC_COL_PRD_BASEINFO_SUB.PROD_BRAND is '产品品牌';
comment on column PBC_COL_PRD_BASEINFO_SUB.PROD_STAGE is '产品期次';
comment on column PBC_COL_PRD_BASEINFO_SUB.PROD_ID is  '内部产品代码';
comment on column PBC_COL_PRD_BASEINFO_SUB.INFO_TYPE is  '信息类型，1-新增资管产品基本信息 2-变更资管产品基本信息';
comment on column PBC_COL_PRD_BASEINFO_SUB.RAISE_CCY is '募集币种';
comment on column PBC_COL_PRD_BASEINFO_SUB.CASH_PRIN_CCY is '兑付本金币种';
comment on column PBC_COL_PRD_BASEINFO_SUB.CASH_PROF_CCY is '兑付收益币种';
comment on column PBC_COL_PRD_BASEINFO_SUB.RAISE_TYPE is '募集方式，1-公募，2-私募';
comment on column PBC_COL_PRD_BASEINFO_SUB.MANAGE_TYPE is '管理方式，1-集合管理/2-单独管理';
comment on column PBC_COL_PRD_BASEINFO_SUB.FIDUCIARY_DUTY is '受托职责，1-主动管理/2-被动管理';
comment on column PBC_COL_PRD_BASEINFO_SUB.OPERATE_MODE is '运行方式，1-开放式净值型/2-开放式非净值型/3一封闭式净值型/4-封闭式非净值型';
comment on column PBC_COL_PRD_BASEINFO_SUB.RAISE_VDATE is '募集起始日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.RAISE_ENDDATE is '募集结束日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.TERM_RIGHT_FLAG is '发行机构提前终止权标识，1-有，2-无';
comment on column PBC_COL_PRD_BASEINFO_SUB.CUS_REDEEM_FLAG is '客户赎回权标识，1-有，2-无';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREDIT_FLAG is '产品增信标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREDIT_TYPE is '增信机构类型，1：住户2：广义政府3：非金融性企业4：金融机构（实体）5：特定目的载体6：境外';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREDIT_MODE is '增信形式，1-内部增级，2-外部增级';
comment on column PBC_COL_PRD_BASEINFO_SUB.IN_DEP_NAME is '境内托管机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB.IN_DEP_CODE is '境内托管机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB.OUT_DEP_CTRY is '境外托管机构国别';
comment on column PBC_COL_PRD_BASEINFO_SUB.OUT_DEP_NAME is '境外托管机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB.VDATE is '产品起始日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.CDATE is '产品变更日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.MDATE is '产品预计终止日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.IS_CONSTRACTE is '分级产品标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.TRANSFER_TYPE is '收益权转让产品标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.CASH_MANA_CATE is '货基或现金管理类产品标识,1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.CROSS_BORDER_FLAG is '跨境理财通，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.IS_PROPERTY_FLAG is '以财产权信托名义开展的资金信托标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.BASE_INFO_SIGN is '基本信息公开标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB.CHANGE_REASON is '变更原因';
comment on column PBC_COL_PRD_BASEINFO_SUB.BACKUP1 is '备用字段1';
comment on column PBC_COL_PRD_BASEINFO_SUB.BACKUP2 is '备用字段2';
comment on column PBC_COL_PRD_BASEINFO_SUB.BACKUP3 is '备用字段3';
comment on column PBC_COL_PRD_BASEINFO_SUB.BACKUP4 is '备用字段4';
comment on column PBC_COL_PRD_BASEINFO_SUB.BACKUP5 is '备用字段5';
comment on column PBC_COL_PRD_BASEINFO_SUB.SEND_STATUS is '报送状态';
comment on column PBC_COL_PRD_BASEINFO_SUB.SUBMIT_TIME is '报送日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.SUBMIT_FEEDBACK_STATUS is '报送反馈状态';
comment on column PBC_COL_PRD_BASEINFO_SUB.SUMIT_SEQ is '报送流水号';
comment on column PBC_COL_PRD_BASEINFO_SUB.STATUS_DATE is '数据日期';
comment on column PBC_COL_PRD_BASEINFO_SUB.PBC_SUBMIT_DEADLINE is '人行报送截止日';
comment on column PBC_COL_PRD_BASEINFO_SUB.SUBMIT_DEADLINE is '行内报送截止日';
comment on column PBC_COL_PRD_BASEINFO_SUB.STATUS is '数据状态';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREATE_USER is '创建人';
comment on column PBC_COL_PRD_BASEINFO_SUB.ORG_CODE is '机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREATE_DEPT is '创建部门';
comment on column PBC_COL_PRD_BASEINFO_SUB.CREATE_TIME is '创建时间';
comment on column PBC_COL_PRD_BASEINFO_SUB.UPDATE_USER is '更新人';
comment on column PBC_COL_PRD_BASEINFO_SUB.UPDATE_TIME is '更新时间';


-- 资管产品基本信息登记历史表 新建

declare
    num number;
begin
    select count(1) into num from user_tables where table_name = 'PBC_COL_PRD_BASEINFO_SUB_HIS';
    if num > 0 then
        execute immediate 'drop table PBC_COL_PRD_BASEINFO_SUB_HIS';
    end if;
end;
/
create table PBC_COL_PRD_BASEINFO_SUB_HIS
(
    REGIST_SEQNO           char(36)  not null primary key ,
    PBC_PROD_ID            varchar(50) null ,
    ISSUE_PARTY_ID         varchar(50) null,
    ISSUE_PARTY_NAME       varchar(200) null,
    PBC_PROD_TYPE          char null,
    PROD_NAME              varchar(200) null,
    IVSTORIENTATION        varchar(1) null,
    PROD_BRAND             varchar(180) null,
    PROD_STAGE             decimal(30, 4) null,
    PROD_ID                varchar(150) null,
    INFO_TYPE              char null,
    RAISE_CCY              varchar(50) null,
    CASH_PRIN_CCY          varchar(50) null,
    CASH_PROF_CCY          varchar(50) null,
    RAISE_TYPE             char null,
    MANAGE_TYPE            char null,
    FIDUCIARY_DUTY         varchar(50) null,
    OPERATE_MODE           varchar(50) null,
    RAISE_VDATE            date null,
    RAISE_ENDDATE          date null,
    TERM_RIGHT_FLAG        char null,
    CUS_REDEEM_FLAG        char null,
    CREDIT_FLAG            char null,
    CREDIT_TYPE            varchar(50) null,
    CREDIT_MODE            varchar(50) null,
    IN_DEP_NAME            varchar(50) null,
    IN_DEP_CODE            varchar(50) null,
    OUT_DEP_CTRY           varchar(1000) null,
    OUT_DEP_NAME           varchar(200) null,
    VDATE                  date null,
    CDATE                  date null,
    MDATE                  date null,
    IS_CONSTRACTE          char null,
    TRANSFER_TYPE          char null,
    CASH_MANA_CATE         varchar(32) null,
    CROSS_BORDER_FLAG      varchar(32) null,
    IS_PROPERTY_FLAG       varchar(2) null,
    BASE_INFO_SIGN         varchar(1) null,
    CHANGE_REASON          varchar(200) null,
    BACKUP1                varchar(200) null,
    BACKUP2                varchar(200) null,
    BACKUP3                varchar(200) null,
    BACKUP4                varchar(200) null,
    BACKUP5                varchar(200) null,
    SEND_STATUS            varchar(50) null,
    SUBMIT_TIME            DATE null,
    SUBMIT_FEEDBACK_STATUS varchar(50) null,
    SUMIT_SEQ              varchar(20) null,
    STATUS_DATE            date null,
    PBC_SUBMIT_DEADLINE    DATE null,
    SUBMIT_DEADLINE        DATE null,
    STATUS                 varchar(50) null,
    CREATE_USER            varchar(20) null,
    ORG_CODE               varchar(20) null,
    CREATE_DEPT            varchar(20) null,
    CREATE_TIME            DATE null,
    UPDATE_USER            varchar(20) null,
    UPDATE_TIME            DATE null

);

comment on table PBC_COL_PRD_BASEINFO_SUB_HIS is '资管产品基本信息登记表';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.REGIST_SEQNO is '登记序列';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PBC_PROD_ID is '人行产品代码';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.ISSUE_PARTY_ID is '理财产品的发行机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.ISSUE_PARTY_NAME is '发行机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PBC_PROD_TYPE is '产品品种';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PROD_NAME is '产品名称';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.IVSTORIENTATION is '产品类型，1固定收益类/2权益类/3商品及金融衍生品类/4混合类';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PROD_BRAND is '产品品牌';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PROD_STAGE is '产品期次';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PROD_ID is  '内部产品代码';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.INFO_TYPE is  '信息类型，1-新增资管产品基本信息 2-变更资管产品基本信息';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.RAISE_CCY is '募集币种';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CASH_PRIN_CCY is '兑付本金币种';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CASH_PROF_CCY is '兑付收益币种';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.RAISE_TYPE is '募集方式，1-公募，2-私募';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.MANAGE_TYPE is '管理方式，1-集合管理/2-单独管理';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.FIDUCIARY_DUTY is '受托职责，1-主动管理/2-被动管理';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.OPERATE_MODE is '运行方式，1-开放式净值型/2-开放式非净值型/3一封闭式净值型/4-封闭式非净值型';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.RAISE_VDATE is '募集起始日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.RAISE_ENDDATE is '募集结束日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.TERM_RIGHT_FLAG is '发行机构提前终止权标识，1-有，2-无';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CUS_REDEEM_FLAG is '客户赎回权标识，1-有，2-无';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREDIT_FLAG is '产品增信标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREDIT_TYPE is '增信机构类型，1：住户2：广义政府3：非金融性企业4：金融机构（实体）5：特定目的载体6：境外';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREDIT_MODE is '增信形式，1-内部增级，2-外部增级';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.IN_DEP_NAME is '境内托管机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.IN_DEP_CODE is '境内托管机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.OUT_DEP_CTRY is '境外托管机构国别';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.OUT_DEP_NAME is '境外托管机构名称';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.VDATE is '产品起始日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CDATE is '产品变更日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.MDATE is '产品预计终止日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.IS_CONSTRACTE is '分级产品标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.TRANSFER_TYPE is '收益权转让产品标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CASH_MANA_CATE is '货基或现金管理类产品标识,1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CROSS_BORDER_FLAG is '跨境理财通，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.IS_PROPERTY_FLAG is '以财产权信托名义开展的资金信托标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BASE_INFO_SIGN is '基本信息公开标识，1-是，2-否';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CHANGE_REASON is '变更原因';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BACKUP1 is '备用字段1';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BACKUP2 is '备用字段2';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BACKUP3 is '备用字段3';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BACKUP4 is '备用字段4';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.BACKUP5 is '备用字段5';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.SEND_STATUS is '报送状态';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.SUBMIT_TIME is '报送日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.SUBMIT_FEEDBACK_STATUS is '报送反馈状态';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.SUMIT_SEQ is '报送流水号';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.STATUS_DATE is '数据日期';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.PBC_SUBMIT_DEADLINE is '人行报送截止日';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.SUBMIT_DEADLINE is '行内报送截止日';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.STATUS is '数据状态';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREATE_USER is '创建人';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.ORG_CODE is '机构代码';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREATE_DEPT is '创建部门';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.CREATE_TIME is '创建时间';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.UPDATE_USER is '更新人';
comment on column PBC_COL_PRD_BASEINFO_SUB_HIS.UPDATE_TIME is '更新时间';



-- 资管产品基本信息(ZG01)生成批处理 新增
delete from sys_batch_task where TASK_ID='8200';
INSERT INTO sys_batch_task(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('8200', 'SVR', '采集平台资管产品基本信息(ZG01)生成批处理', null, 'zG01PbcPrdBaseinfoSubTaskService', 3000, 'system', null, sysdate, 'system', sysdate, null);

-- 资管产品基本信息 目录
delete from sys_menu where MENU_ID='REPORT_REGIST_001_003_014';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_003_014', 'SVR', null, 'REPORT_REGIST_001_003', 'ZG01PbcPrdBaseinfoSub', 14, '2', 'ZG01 资管产品基本信息', 'system', null, sysdate, 'system', sysdate);

-- Page
delete from sys_page where PAGE_ID='ZG01PbcPrdBaseinfoSub';
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG01PbcPrdBaseinfoSub', 'SVR', '/pbc/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'report/pbc/classic/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'REPORT_REGIST_001_003_014', null, null, '01', '01', 'ZG01 资管产品基本信息', 'system', null, sysdate, 'system', sysdate);

-- 信息类型数据字典添加
delete from sys_datadict where DICT_CODE='PBC_INFO_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_INFO_TYPE', 'PBC', '人行信息类型', null, '1', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='PBC_INFO_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INFO_TYPE', '1', '新增资管产品基本信息', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INFO_TYPE', '2', '变更资管产品基本信息', 2, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 产品类型数据字典添加
delete from sys_datadict where DICT_CODE='PBC_INVEST_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_INVEST_TYPE', 'COM', '投资性质', null, '02', 'system', null, sysdate, 'system',sysdate);
delete from sys_datadict_list where DICT_CODE='PBC_INVEST_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '1', '固定收益类', 1, '1', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '2', '权益类', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '3', '商品及金融衍生品类', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '4', '混合类', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 人行增信机构类型数据字典添加
delete from sys_datadict where DICT_CODE='SVR_PBC_CREDIT_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SVR_PBC_CREDIT_TYPE', 'PBC', '人行增信机构类型', null, '1', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='SVR_PBC_CREDIT_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '1', '住户', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '2', '广义政府', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '3', '非金融性企业 ', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '4', '金融机构（实体）', 4, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '5', '特定目的载体', 5, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '6', '境外', 6, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 产品品种数据字典添加
delete from sys_datadict where DICT_CODE='PBC_COL_PROD_VARIETY';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_COL_PROD_VARIETY', 'REP', '产品品种', null, '02', 'system', null, sysdate, 'system', sysdate);
delete from sys_datadict_list where DICT_CODE='PBC_COL_PROD_VARIETY';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_COL_PROD_VARIETY', '1', '银行非保本理财', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 配置权限
delete from SYS_PAGE_ELEMENT where PAGE_ID='ZG01PbcPrdBaseinfoSub';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_submit', '01', '01', null, '数据检查', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_manual', '01', '01', null, '手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_chbexportxls', '01', '01', null, '导出人行Excel', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 受托职责数据字典添加
delete from sys_datadict where DICT_CODE='SVR_PBC_FIDUCIARY_DUTY_COL';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', 'PBC', '人行受托职责', null, '1', 'system', null, now(), 'system', now());

delete from sys_datadict_list where DICT_CODE='SVR_PBC_FIDUCIARY_DUTY_COL';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', '1', '主动管理', 1, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', '2', '被动管理', 2, '0', 'system', null, now(), 'system', now(), null);


-- 境内托管机构代码 映射配置
delete  from CHB_TYPE_MAPPING_EX where MAPPINGCODE='MAPPINGCODE_DEPT_CODE_NEW';
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A3-5001-E050-007F01001DB2', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '01', null, null, null, null, 'F1', 'C10102', 'C1010211000012', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A4-5002-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '02', null, null, null, null, 'F1', 'C10103', 'C1010311000014', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A5-5003-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '03', null, null, null, null, 'F1', 'C10104', 'C1010411000013', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A6-5004-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '04', null, null, null, null, 'F1', 'C10105', 'C1010511003703', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A7-5005-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '05', null, null, null, null, 'F1', 'C10301', 'C1030131001168', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A8-5006-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '06', null, null, null, null, 'F1', 'C10304', 'C1030411000431', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A9-5007-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '07', null, null, null, null, 'F1', 'C10303', 'C1030311000455', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AA-5008-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '08', null, null, null, null, 'F1', 'C10308', 'C1030844001362', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AB-5009-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '09', null, null, null, null, 'F1', 'C10302', 'C1030211000389', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AC-5010-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '10', null, null, null, null, 'F1', 'C10305', 'C1030511000483', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AD-5011-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '11', null, null, null, null, 'F1', 'C10309', 'C1030935001347', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AE-5012-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '12', null, null, null, null, 'F1', 'C10310', 'C1031011000340', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AF-5013-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '13', null, null, null, null, 'F1', 'C10802', 'C1080211000028', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B0-5014-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '14', null, null, null, null, 'F1', 'C10307', 'C1030744001296', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B1-5015-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '15', null, null, null, null, 'F1', 'C10306', 'C1030644003515', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B2-5016-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '16', null, null, null, null, 'F1', 'C10403', 'C1040311005293', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B3-5017-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '17', null, null, null, null, 'F1', 'C10912', 'C1091231000013', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B4-5018-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '18', null, null, null, null, 'F1', 'C12112', 'C1211252000022', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B5-5019-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '19', null, null, null, null, 'F1', 'C10921', 'C1092133000016', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B6-5020-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '20', null, null, null, null, 'F1', 'C10316', 'C1031633000292', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B7-5021-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '21', null, null, null, null, 'F1', 'C10804', 'C1080434000027', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B8-5022-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '22', null, null, null, null, 'F1', 'C11312', 'C1131244000017', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B9-5023-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '23', null, null, null, null, 'F1', 'C10876', 'C1087615000019', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BA-5024-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '24', null, null, null, null, 'F1', 'C10315', 'C1031537000877', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BB-5025-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '25', null, null, null, null, 'F1', 'C10920', 'C1092033000015', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BC-5026-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '26', null, null, null, null, 'F1', 'C10869', 'C1086932000011', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BD-5027-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '27', null, null, null, null, 'F1', 'C10868', 'C1086832000010', null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);


-- 查询页面数据状态字典值
delete from  SYS_PAGE_DICTLIST where PAGE_ID='ZG01PbcPrdBaseinfoSub' and DICT_ID='SUBMITTED_STATUS';
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ZG01PbcPrdBaseinfoSub', '999999', 'SUBMITTED_STATUS', '01', '新建', null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ZG01PbcPrdBaseinfoSub', '999999', 'SUBMITTED_STATUS', '02', '修改', null, 'system', null, sysdate, 'system', sysdate, null);
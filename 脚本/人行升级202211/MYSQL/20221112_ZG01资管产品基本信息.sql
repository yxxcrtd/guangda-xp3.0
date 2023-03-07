-- 资管产品基本信息登记表 新建
DROP TABLE IF EXISTS PBC_COL_PRD_BASEINFO_SUB;
create table PBC_COL_PRD_BASEINFO_SUB
(
    REGIST_SEQNO           char(36)        not null comment '登记序列' primary key,
    PBC_PROD_ID            varchar(50)     null comment '人行产品代码',
    ISSUE_PARTY_ID         varchar(50)     null comment '理财产品的发行机构代码',
    ISSUE_PARTY_NAME         varchar(200)     null comment '发行机构名称',
    PBC_PROD_TYPE          char            null comment '产品品种',
    PROD_NAME              varchar(200)    null comment '产品名称',
    IVSTORIENTATION        varchar(1)    null comment '产品类型，1固定收益类/2权益类/3商品及金融衍生品类/4混合类',
    PROD_BRAND             varchar(180)    null comment '产品品牌',
    PROD_STAGE             decimal(30, 4)  null comment '产品期次',
    PROD_ID                varchar(150)    null comment '内部产品代码',
    INFO_TYPE              char    null comment '信息类型，1-新增资管产品基本信息 2-变更资管产品基本信息',
    RAISE_CCY              varchar(50)     null comment '募集币种',
    CASH_PRIN_CCY          varchar(50)     null comment '兑付本金币种',
    CASH_PROF_CCY          varchar(50)     null comment '兑付收益币种',
    RAISE_TYPE             char            null comment '募集方式，1-公募，2-私募',
    MANAGE_TYPE            char            null comment '管理方式，1-集合管理/2-单独管理',
    FIDUCIARY_DUTY         varchar(50)     null comment '受托职责，1-主动管理/2-被动管理',
    OPERATE_MODE           varchar(50)     null comment '运行方式，1-开放式净值型/2-开放式非净值型/3一封闭式净值型/4-封闭式非净值型',
    RAISE_VDATE            date            null comment '募集起始日期',
    RAISE_ENDDATE          date            null comment '募集结束日期',
    TERM_RIGHT_FLAG        char            null comment '发行机构提前终止权标识，1-有，2-无',
    CUS_REDEEM_FLAG        char            null comment '客户赎回权标识，1-有，2-无',
    CREDIT_FLAG            char            null comment '产品增信标识，1-是，2-否',
    CREDIT_TYPE            varchar(50)     null comment '增信机构类型，1：住户2：广义政府3：非金融性企业4：金融机构（实体）5：特定目的载体6：境外',
    CREDIT_MODE            varchar(50)     null comment '增信形式，1-内部增级，2-外部增级',
    IN_DEP_NAME            varchar(50)     null comment '境内托管机构名称',
    IN_DEP_CODE            varchar(50)     null comment '境内托管机构代码',
    OUT_DEP_CTRY           varchar(1000)   null comment '境外托管机构国别',
    OUT_DEP_NAME           varchar(200)    null comment '境外托管机构名称',
    VDATE                  date            null comment '产品起始日期',
    CDATE                  date            null comment '产品变更日期',
    MDATE                  date            null comment '产品预计终止日期',
    IS_CONSTRACTE          char            null comment '分级产品标识，1-是，2-否',
    TRANSFER_TYPE          char            null comment '收益权转让产品标识，1-是，2-否',
    CASH_MANA_CATE         varchar(32)     null comment '货基或现金管理类产品标识，1-是，2-否',
    CROSS_BORDER_FLAG      varchar(32)     null comment '跨境理财通，1-是，2-否',
    IS_PROPERTY_FLAG       varchar(2)      null comment '以财产权信托名义开展的资金信托标识，1-是，2-否',
    BASE_INFO_SIGN       varchar(1)      null comment '基本信息公开标识，1-是，2-否',
    CHANGE_REASON       varchar(401)      null comment '变更原因',
    BACKUP1       varchar(200)      null comment '备用字段1',
    BACKUP2       varchar(200)      null comment '备用字段2',
    BACKUP3       varchar(200)      null comment '备用字段3',
    BACKUP4       varchar(200)      null comment '备用字段4',
    BACKUP5       varchar(200)      null comment '备用字段5',
    SEND_STATUS            varchar(50)     null comment '报送状态',
    SUBMIT_TIME            datetime        null comment '报送日期',
    SUBMIT_FEEDBACK_STATUS varchar(50)     null comment '报送反馈状态',
    SUMIT_SEQ              varchar(20)     null comment '报送流水号',
    STATUS_DATE            datetime        null comment '数据日期',
    PBC_SUBMIT_DEADLINE    datetime        null comment '人行报送截止日',
    SUBMIT_DEADLINE        datetime        null comment '行内报送截止日',
    STATUS                 varchar(50)     null comment '数据状态',
    CREATE_USER            varchar(20)     null comment '创建人',
    ORG_CODE               varchar(20)     null comment '机构代码',
    CREATE_DEPT            varchar(20)     null comment '创建部门',
    CREATE_TIME            datetime        null comment '创建时间',
    UPDATE_USER            varchar(20)     null comment '更新人',
    UPDATE_TIME            datetime        null comment '更新时间'
)
    comment '资管产品基本信息登记表';

-- 资管产品基本信息登记历史表 新建
DROP TABLE IF EXISTS PBC_COL_PRD_BASEINFO_SUB_HIS;
create table PBC_COL_PRD_BASEINFO_SUB_HIS
(
    REGIST_SEQNO           char(36)        not null comment '登记序列' primary key,
    PBC_PROD_ID            varchar(50)     null comment '人行产品代码',
    ISSUE_PARTY_ID         varchar(50)     null comment '理财产品的发行机构代码',
    ISSUE_PARTY_NAME         varchar(200)     null comment '发行机构名称',
    PBC_PROD_TYPE          char            null comment '产品品种',
    PROD_NAME              varchar(200)    null comment '产品名称',
    IVSTORIENTATION        varchar(1)    null comment '产品类型，1固定收益类/2权益类/3商品及金融衍生品类/4混合类',
    PROD_BRAND             varchar(180)    null comment '产品品牌',
    PROD_STAGE             decimal(30, 4)  null comment '产品期次',
    PROD_ID                varchar(150)    null comment '内部产品代码',
    INFO_TYPE              char    null comment '信息类型，1-新增资管产品基本信息 2-变更资管产品基本信息',
    RAISE_CCY              varchar(50)     null comment '募集币种',
    CASH_PRIN_CCY          varchar(50)     null comment '兑付本金币种',
    CASH_PROF_CCY          varchar(50)     null comment '兑付收益币种',
    RAISE_TYPE             char            null comment '募集方式，1-公募，2-私募',
    MANAGE_TYPE            char            null comment '管理方式，1-集合管理/2-单独管理',
    FIDUCIARY_DUTY         varchar(50)     null comment '受托职责，1-主动管理/2-被动管理',
    OPERATE_MODE           varchar(50)     null comment '运行方式，1-开放式净值型/2-开放式非净值型/3一封闭式净值型/4-封闭式非净值型',
    RAISE_VDATE            date            null comment '募集起始日期',
    RAISE_ENDDATE          date            null comment '募集结束日期',
    TERM_RIGHT_FLAG        char            null comment '发行机构提前终止权标识，1-有，2-无',
    CUS_REDEEM_FLAG        char            null comment '客户赎回权标识，1-有，2-无',
    CREDIT_FLAG            char            null comment '产品增信标识，1-是，2-否',
    CREDIT_TYPE            varchar(50)     null comment '增信机构类型，1：住户2：广义政府3：非金融性企业4：金融机构（实体）5：特定目的载体6：境外',
    CREDIT_MODE            varchar(50)     null comment '增信形式，1-内部增级，2-外部增级',
    IN_DEP_NAME            varchar(50)     null comment '境内托管机构名称',
    IN_DEP_CODE            varchar(50)     null comment '境内托管机构代码',
    OUT_DEP_CTRY           varchar(1000)   null comment '境外托管机构国别',
    OUT_DEP_NAME           varchar(200)    null comment '境外托管机构名称',
    VDATE                  date            null comment '产品起始日期',
    CDATE                  date            null comment '产品变更日期',
    MDATE                  date            null comment '产品预计终止日期',
    IS_CONSTRACTE          char            null comment '分级产品标识，1-是，2-否',
    TRANSFER_TYPE          char            null comment '收益权转让产品标识，1-是，2-否',
    CASH_MANA_CATE         varchar(32)     null comment '货基或现金管理类产品标识，1-是，2-否',
    CROSS_BORDER_FLAG      varchar(32)     null comment '跨境理财通，1-是，2-否',
    IS_PROPERTY_FLAG       varchar(2)      null comment '以财产权信托名义开展的资金信托标识，1-是，2-否',
    BASE_INFO_SIGN       varchar(1)      null comment '基本信息公开标识，1-是，2-否',
    CHANGE_REASON       varchar(401)      null comment '变更原因',
    BACKUP1       varchar(200)      null comment '备用字段1',
    BACKUP2       varchar(200)      null comment '备用字段2',
    BACKUP3       varchar(200)      null comment '备用字段3',
    BACKUP4       varchar(200)      null comment '备用字段4',
    BACKUP5       varchar(200)      null comment '备用字段5',
    SEND_STATUS            varchar(50)     null comment '报送状态',
    SUBMIT_TIME            datetime        null comment '报送日期',
    SUBMIT_FEEDBACK_STATUS varchar(50)     null comment '报送反馈状态',
    SUMIT_SEQ              varchar(20)     null comment '报送流水号',
    STATUS_DATE            datetime        null comment '数据日期',
    PBC_SUBMIT_DEADLINE    datetime        null comment '人行报送截止日',
    SUBMIT_DEADLINE        datetime        null comment '行内报送截止日',
    STATUS                 varchar(50)     null comment '数据状态',
    CREATE_USER            varchar(20)     null comment '创建人',
    ORG_CODE               varchar(20)     null comment '机构代码',
    CREATE_DEPT            varchar(20)     null comment '创建部门',
    CREATE_TIME            datetime        null comment '创建时间',
    UPDATE_USER            varchar(20)     null comment '更新人',
    UPDATE_TIME            datetime        null comment '更新时间'
)
    comment '资管产品基本信息登记历史表';



-- 资管产品基本信息(ZG01)生成批处理 新增
delete from sys_batch_task where TASK_ID='8200';
INSERT INTO sys_batch_task(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('8200', 'SVR', '采集平台资管产品基本信息(ZG01)生成批处理', null, 'zG01PbcPrdBaseinfoSubTaskService', 3000, 'system', null, now(), 'system', now(), null);

-- 资管产品基本信息 目录
delete from sys_menu where MENU_ID='REPORT_REGIST_001_003_014';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_003_014', 'SVR', null, 'REPORT_REGIST_001_003', 'ZG01PbcPrdBaseinfoSub', 14, '2', 'ZG01 资管产品基本信息', 'system', null, now(), 'system', now());

-- Page
delete from sys_page where PAGE_ID='ZG01PbcPrdBaseinfoSub';
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG01PbcPrdBaseinfoSub', 'SVR', '/pbc/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'report/pbc/classic/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'REPORT_REGIST_001_003_014', null, null, '01', '01', 'ZG01 资管产品基本信息', 'system', null, now(), 'system', now());

-- 信息类型数据字典添加
delete from sys_datadict where DICT_CODE='PBC_INFO_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_INFO_TYPE', 'PBC', '人行信息类型', null, '1', 'system', null, now(), 'system', now());

delete from sys_datadict_list where DICT_CODE='PBC_INFO_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INFO_TYPE', '1', '新增资管产品基本信息', 1, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INFO_TYPE', '2', '变更资管产品基本信息', 2, '0', 'system', null, now(), 'system', now(), null);


-- 产品类型数据字典添加
delete from sys_datadict where DICT_CODE='PBC_INVEST_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_INVEST_TYPE', 'COM', '投资性质', null, '02', 'system', null, now(), 'system',now());
delete from sys_datadict_list where DICT_CODE='PBC_INVEST_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '1', '固定收益类', 1, '1', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '2', '权益类', 2, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '3', '商品及金融衍生品类', 3, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_INVEST_TYPE', '4', '混合类', 4, '0', 'system', null, now(), 'system', now(), null);

-- 人行增信机构类型数据字典添加
delete from sys_datadict where DICT_CODE='SVR_PBC_CREDIT_TYPE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SVR_PBC_CREDIT_TYPE', 'PBC', '人行增信机构类型', null, '1', 'system', null, now(), 'system', now());

delete from sys_datadict_list where DICT_CODE='SVR_PBC_CREDIT_TYPE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '1', '住户', 1, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '2', '广义政府', 2, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '3', '非金融性企业 ', 3, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '4', '金融机构（实体）', 4, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '5', '特定目的载体', 5, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_CREDIT_TYPE', '6', '境外', 6, '0', 'system', null, now(), 'system', now(), null);

-- 产品品种数据字典添加
delete from sys_datadict where DICT_CODE='PBC_COL_PROD_VARIETY';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_COL_PROD_VARIETY', 'REP', '产品品种', null, '02', 'system', null, now(), 'system', now());
delete from sys_datadict_list where DICT_CODE='PBC_COL_PROD_VARIETY';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_COL_PROD_VARIETY', '1', '银行非保本理财', 1, '0', 'system', null, now(), 'system', now(), null);

-- 受托职责数据字典添加
delete from sys_datadict where DICT_CODE='SVR_PBC_FIDUCIARY_DUTY_COL';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', 'PBC', '人行受托职责', null, '1', 'system', null, now(), 'system', now());

delete from sys_datadict_list where DICT_CODE='SVR_PBC_FIDUCIARY_DUTY_COL';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', '1', '主动管理', 1, '0', 'system', null, now(), 'system', now(), null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SVR_PBC_FIDUCIARY_DUTY_COL', '2', '被动管理', 2, '0', 'system', null, now(), 'system', now(), null);


-- 配置权限
delete from SYS_PAGE_ELEMENT where PAGE_ID='ZG01PbcPrdBaseinfoSub';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_submit', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_chbexportxls', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG01PbcPrdBaseinfoSub', 'ZG01PbcPrdBaseinfoSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

-- 境内托管机构代码 映射配置
delete  from CHB_TYPE_MAPPING_EX where MAPPINGCODE='MAPPINGCODE_DEPT_CODE_NEW';
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A3-5001-E050-007F01001DB2', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '01', null, null, null, null, 'F1', 'C10102', 'C1010211000012', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A4-5002-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '02', null, null, null, null, 'F1', 'C10103', 'C1010311000014', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A5-5003-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '03', null, null, null, null, 'F1', 'C10104', 'C1010411000013', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A6-5004-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '04', null, null, null, null, 'F1', 'C10105', 'C1010511003703', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A7-5005-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '05', null, null, null, null, 'F1', 'C10301', 'C1030131001168', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A8-5006-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '06', null, null, null, null, 'F1', 'C10304', 'C1030411000431', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53A9-5007-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '07', null, null, null, null, 'F1', 'C10303', 'C1030311000455', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AA-5008-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '08', null, null, null, null, 'F1', 'C10308', 'C1030844001362', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AB-5009-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '09', null, null, null, null, 'F1', 'C10302', 'C1030211000389', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AC-5010-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '10', null, null, null, null, 'F1', 'C10305', 'C1030511000483', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AD-5011-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '11', null, null, null, null, 'F1', 'C10309', 'C1030935001347', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AE-5012-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '12', null, null, null, null, 'F1', 'C10310', 'C1031011000340', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53AF-5013-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '13', null, null, null, null, 'F1', 'C10802', 'C1080211000028', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B0-5014-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '14', null, null, null, null, 'F1', 'C10307', 'C1030744001296', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B1-5015-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '15', null, null, null, null, 'F1', 'C10306', 'C1030644003515', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B2-5016-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '16', null, null, null, null, 'F1', 'C10403', 'C1040311005293', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B3-5017-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '17', null, null, null, null, 'F1', 'C10912', 'C1091231000013', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B4-5018-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '18', null, null, null, null, 'F1', 'C12112', 'C1211252000022', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B5-5019-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '19', null, null, null, null, 'F1', 'C10921', 'C1092133000016', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B6-5020-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '20', null, null, null, null, 'F1', 'C10316', 'C1031633000292', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B7-5021-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '21', null, null, null, null, 'F1', 'C10804', 'C1080434000027', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B8-5022-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '22', null, null, null, null, 'F1', 'C11312', 'C1131244000017', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53B9-5023-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '23', null, null, null, null, 'F1', 'C10876', 'C1087615000019', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BA-5024-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '24', null, null, null, null, 'F1', 'C10315', 'C1031537000877', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BB-5025-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '25', null, null, null, null, 'F1', 'C10920', 'C1092033000015', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BC-5026-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '26', null, null, null, null, 'F1', 'C10869', 'C1086932000011', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);
INSERT INTO CHB_TYPE_MAPPING_EX(MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD) VALUES ('906EBCF0-53BD-5027-E050-007F01001DE3', 'MAPPINGCODE_DEPT_CODE_NEW', '境内托管机构代码', '27', null, null, null, null, 'F1', 'C10868', 'C1086832000010', null, null, 'E', null, null, 'system', now(), 'system', now(), null, null);

-- 查询页面数据状态字典值
delete from  SYS_PAGE_DICTLIST where PAGE_ID='ZG01PbcPrdBaseinfoSub' and DICT_ID='SUBMITTED_STATUS';
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ZG01PbcPrdBaseinfoSub', '999999', 'SUBMITTED_STATUS', '01', '新建', null, 'system', null, now(), 'system', now(), null);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ZG01PbcPrdBaseinfoSub', '999999', 'SUBMITTED_STATUS', '02', '修改', null, 'system', null, now(), 'system', now(), null);
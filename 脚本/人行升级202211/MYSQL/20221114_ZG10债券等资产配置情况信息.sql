drop table if exists ZG10_PBC_COL_REPORT;
create table ZG10_PBC_COL_REPORT
(
    REPORTUUID             char(36)        not null comment '主键',
    TERMID                 varchar(20)     null  comment '期数',
    REPORTTYPE             varchar(20)     null  comment '报表类型:',
    STARTDATE              date            null  comment '开始日期:',
    ENDDATE                date            null  comment '结束日期:',
    ISSUE_PARTY_ID         varchar(50)     null  comment '发行机构代码',
    ISSUE_PARTY_NAME       varchar(50)     null  comment '发行机构名称',
    REPORT_DATE            date            null  comment '制表日期',
    SUBMIT_TIME            date            null  comment '报送日期',
    CHB_SUBMIT_DEADLINE    date            null  comment '中债报送截止日',
    SUBMIT_DEADLINE        date            null  comment '行内报送截止日',
    SUBMIT_FEEDBACK_STATUS varchar(50)     null  comment '返回信息',
    STATUS                 varchar(50)     null  comment '有效状态',
    SEND_STATUS            varchar(20)     null  comment '报送状态',
    STATUS_DATE            date            null  comment '数据日期',
    CREATE_USER            varchar(20)     null  comment '创建人',
    CREATE_TIME            datetime        null  comment '创建时间',
    UPDATE_USER            varchar(20)     null  comment '更新人',
    UPDATE_TIME            datetime        null  comment '更新时间',
    ORG_CODE               varchar(20)     null  comment '机构代码',
    DEPT_CODE              varchar(20)     null  comment '部门代码',
    CREATE_DEPT            varchar(32)     null  comment '创建部门',
    PBC_PROD_TYPE          char(1)         null  comment '产品品种',
    h10000				   decimal(20,2)   null  comment 'AMT_h10000_ZWZQ一、债务证券（按评级分类）',		
	h11000      		   decimal(20,2)   null  comment 'AMT_h11000_ZCXJRZQ政策性金融债券',
	h11100 		           decimal(20,2)   null  comment 'AMT_h11100_AAJYSAA+（含）以上',
	h11200   		       decimal(20,2)   null  comment 'AMT_h11200_AAJYXAA+以下',
	h11300      		   decimal(20,2)   null  comment 'AMT_h11300_WPJ无评级',
	h12000      		   decimal(20,2)   null  comment 'AMT_h12000_QTJRZQ其他金融债券',
	h12100   		       decimal(20,2)   null  comment 'AMT_h12100_AAJYSAA+（含）以上',
	h12200   		       decimal(20,2)   null  comment 'AMT_h12200_AAJYXAA+以下',
	h12300      		   decimal(20,2)   null  comment 'AMT_h12300_WPJ无评级',
	h13000      		   decimal(20,2)   null  comment 'AMT_h13000_QYZQ企业债券',
	h13100   		       decimal(20,2)   null  comment 'AMT_h13100_AAJYSAA+（含）以上',
	h13200   		       decimal(20,2)   null  comment 'AMT_h13200_AAJYXAA+以下',
	h13300      		   decimal(20,2)   null  comment 'AMT_h13300_WPJ无评级',
	h14000      		   decimal(20,2)   null  comment 'AMT_h14000_ZCZCZQ资产支持证券',
	h14100   		       decimal(20,2)   null  comment 'AMT_h14100_AAJYSAA+（含）以上',
	h14200   		       decimal(20,2)   null  comment 'AMT_h14200_AAJYXAA+以下',
	h14300      		   decimal(20,2)   null  comment 'AMT_h14300_WPJ无评级',
	h20000      		   decimal(20,2)   null  comment 'AMT_h20000_ZYZQAQZFL二、企业债券按券种分类',
	h21000      		   decimal(20,2)   null  comment 'AMT_h21000_QZQYZ其中：企业债',
	h22000      		   decimal(20,2)   null  comment 'AMT_h22000_GSZ公司债',
	h23000      		   decimal(20,2)   null  comment 'AMT_h23000_FJRQYZW非金融企业债务融资工具',
	h30000      		   decimal(20,2)   null  comment 'AMT_h30000_YHZBBCGJ三、银行资本补充工具合计',
	h31000      		   decimal(20,2)   null  comment 'AMT_h31000_QZYXG其中：优先股',
	h32000      		   decimal(20,2)   null  comment 'AMT_h32000_YXZZX永续债（债性）',
	h33000      		   decimal(20,2)   null  comment 'AMT_h33000_YXZGX永续债（股性）',
	h34000      		   decimal(20,2)   null  comment 'AMT_h34000_EJZBZ二级资本债',
	h40000      		   decimal(20,2)   null  comment 'AMT_h40000_ZQNHGHTYCJ债券逆回购和同业拆借',
	h41000      		   decimal(20,2)   null  comment 'AMT_h41000_ZH住户',
	h42000      		   decimal(20,2)   null  comment 'AMT_h42000_GYZF广义政府',
	h43000      		   decimal(20,2)   null  comment 'AMT_h43000_FJRQY非金融企业',
	h44000      		   decimal(20,2)   null  comment 'AMT_h44000_YHYCKLJRJG银行业存款类金融机构',
	h45000      		   decimal(20,2)   null  comment 'AMT_h45000_YHYFCKLJRJG银行业非存款类金融机构',
	h46000      		   decimal(20,2)   null  comment 'AMT_h46000_FYHYJRJG非银行业金融机构',
	h46100      		   decimal(20,2)   null  comment 'AMT_h46100_QZZYJYDSF其中：中央交易对手方',
	h47000      		   decimal(20,2)   null  comment 'AMT_h47000_TDMDZT特定目的载体',
	h47100      		   decimal(20,2)   null  comment 'AMT_h47100_YHFBBLC银行非保本理财',
	h47200      		   decimal(20,2)   null  comment 'AMT_h47200_XTGSZGCP信托公司资管产品',
	h47300      		   decimal(20,2)   null  comment 'AMT_h47300_ZQGSJQZGS证券公司及其子公司资管产品',
	h47400      		   decimal(20,2)   null  comment 'AMT_h47400_JJGLGSJQZGS基金管理公司及其子公司专户',
	h47500      		   decimal(20,2)   null  comment 'AMT_h47500_QHGLGSJQZGS期货公司及其子公司资管产品',
	h47600      		   decimal(20,2)   null  comment 'AMT_h47600_BXZGCP保险资管产品',
	h47700      		   decimal(20,2)   null  comment 'AMT_h47700_JRZCTZGSJQZGS金融资产投资公司资管产品',
	h47800      		   decimal(20,2)   null  comment 'AMT_h47800_GMJJ公募基金',
	h47900      		   decimal(20,2)   null  comment 'AMT_h47900_SMJGSMJJ私募机构私募基金',
	h47a00      		   decimal(20,2)   null  comment 'AMT_h47a00_QTTDMDZT其他特定目的载体',
	h48000      		   decimal(20,2)   null  comment 'AMT_h48000_JW境外',
	h50000      		   decimal(20,2)   null  comment 'AMT_h50000_ZCJZZB五、资产减值准备',
	h51000      		   decimal(20,2)   null  comment 'AMT_h51000_CK存款',
	h52000      		   decimal(20,2)   null  comment 'AMT_h52000_KZRCD可转让存单',
	h53000      		   decimal(20,2)   null  comment 'AMT_h53000_ZWZQ债务证券',
	h54000      		   decimal(20,2)   null  comment 'AMT_h54000_CHGHCJWDK除回购和拆借外贷款',
	h55000      		   decimal(20,2)   null  comment 'AMT_h55000_HGHCJ回购和拆借',
	h56000      		   decimal(20,2)   null  comment 'AMT_h56000_ZCSYQ资产收益权',
	h57000      		   decimal(20,2)   null  comment 'AMT_h57000_GQJTDMDDZTFE股权及特定目的载体份额',
	h58000      		   decimal(20,2)   null  comment 'AMT_h58000_JRYSP金融衍生品',
	h59000      		   decimal(20,2)   null  comment 'AMT_h59000_YSZK应收账款',
	h5a000      		   decimal(20,2)   null  comment 'AMT_h5a000_QT其他',
	PRIMARY KEY (REPORTUUID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ZG10债券等资产配置情况信息登记表';

ALTER TABLE  ZG10_PBC_COL_REPORT  change   CHB_SUBMIT_DEADLINE  PBC_SUBMIT_DEADLINE date     comment '人行报送截止日';

/*增加批处理配置*/
delete from  sys_batch_task where TASK_ID='8210';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES
('8210', 'SVR', '采集平台资管产品债券等资产配置情况信息(ZG10)生成批处理', null, 'ZG10PbcColReportTaskService', 3000, 'system', null, now(), 'system', now(), null);


/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_023';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_023','SVR',null,'REPORT_REGIST_001_003','ZG10PbcColAssetConfigurationSub',23,'2','ZG10 债券等资产配置情况信息','system',null,now(),'system',now());
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG10PbcColAssetConfigurationSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG10PbcColAssetConfigurationSub','SVR','/pbc/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub','report/pbc/classic/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub','REPORT_REGIST_001_003_023',null,null,'02','02','ZG10 债券等资产配置情况信息','system',null,now(),'system',now());

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG10PbcColAssetConfigurationSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

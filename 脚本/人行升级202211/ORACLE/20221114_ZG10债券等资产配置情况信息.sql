drop table ZG10_PBC_COL_REPORT;
create table ZG10_PBC_COL_REPORT
(
    REPORTUUID             char(36)    ,
    TERMID                 varchar2(20),
    REPORTTYPE             varchar2(20),
    STARTDATE              date        ,
    ENDDATE                date        ,
    ISSUE_PARTY_ID         varchar2(50),
    ISSUE_PARTY_NAME       varchar2(50),
    REPORT_DATE            date        ,
    SUBMIT_TIME            date        ,
    CHB_SUBMIT_DEADLINE    date        ,
    SUBMIT_DEADLINE        date        ,
    SUBMIT_FEEDBACK_STATUS varchar2(50),
    STATUS                 varchar2(50),
    SEND_STATUS            varchar2(20),
    STATUS_DATE            date        ,
    CREATE_USER            varchar2(20),
    CREATE_TIME            TIMESTAMP(6),
    UPDATE_USER            varchar2(20),
    UPDATE_TIME            TIMESTAMP(6),
    ORG_CODE               varchar2(20),
    DEPT_CODE              varchar2(20),
    CREATE_DEPT            varchar2(32),
    PBC_PROD_TYPE          char(1)     ,
    h10000				   NUMBER(20,2),
	h11000      		   NUMBER(20,2),
	h11100       		   NUMBER(20,2),
	h11200       		   NUMBER(20,2),
	h11300       		   NUMBER(20,2),
	h12000       		   NUMBER(20,2),
	h12100       		   NUMBER(20,2),
	h12200       		   NUMBER(20,2),
	h12300       		   NUMBER(20,2),
	h13000       		   NUMBER(20,2),
	h13100       		   NUMBER(20,2),
	h13200       		   NUMBER(20,2),
	h13300       		   NUMBER(20,2),
	h14000       		   NUMBER(20,2),
	h14100       		   NUMBER(20,2),
	h14200       		   NUMBER(20,2),
	h14300      		   NUMBER(20,2),
	h20000      		   NUMBER(20,2),
	h21000      		   NUMBER(20,2),
	h22000      		   NUMBER(20,2),
	h23000      		   NUMBER(20,2),
	h30000      		   NUMBER(20,2),
	h31000      		   NUMBER(20,2),
	h32000      		   NUMBER(20,2),
	h33000      		   NUMBER(20,2),
	h34000      		   NUMBER(20,2),
	h40000      		   NUMBER(20,2),
	h41000      		   NUMBER(20,2),
	h42000      		   NUMBER(20,2),
	h43000      		   NUMBER(20,2),
	h44000      		   NUMBER(20,2),
	h45000      		   NUMBER(20,2),
	h46000      		   NUMBER(20,2),
	h46100      		   NUMBER(20,2),
	h47000      		   NUMBER(20,2),
	h47100      		   NUMBER(20,2),
	h47200      		   NUMBER(20,2),
	h47300      		   NUMBER(20,2),
	h47400      		   NUMBER(20,2),
	h47500      		   NUMBER(20,2),
	h47600      		   NUMBER(20,2),
	h47700      		   NUMBER(20,2),
	h47800      		   NUMBER(20,2),
	h47900      		   NUMBER(20,2),
	h47a00      		   NUMBER(20,2),
	h48000      		   NUMBER(20,2),
	h50000      		   NUMBER(20,2),
	h51000      		   NUMBER(20,2),
	h52000      		   NUMBER(20,2),
	h53000      		   NUMBER(20,2),
	h54000      		   NUMBER(20,2),
	h55000      		   NUMBER(20,2),
	h56000      		   NUMBER(20,2),
	h57000      		   NUMBER(20,2),
	h58000      		   NUMBER(20,2),
	h59000      		   NUMBER(20,2),
	h5a000      		   NUMBER(20,2),
	 CONSTRAINT ZG05_PBC_COL_REPORT_PK PRIMARY KEY (REPORTUUID)
);
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT  IS 'ZG10债券等资产配置情况信息登记表';
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.REPORTUUID             IS '主键'                                             ; 
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.TERMID                 IS '期数'                                             ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.REPORTTYPE             IS '报表类型:'                                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.STARTDATE              IS '开始日期:'                                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.ENDDATE                IS '结束日期:'                                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.ISSUE_PARTY_ID         IS '发行机构代码'                                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.ISSUE_PARTY_NAME       IS '发行机构名称'                                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.REPORT_DATE            IS '制表日期'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.SUBMIT_TIME            IS '报送日期'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.CHB_SUBMIT_DEADLINE    IS '中债报送截止日'                                   ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.SUBMIT_DEADLINE        IS '行内报送截止日'                                   ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.SUBMIT_FEEDBACK_STATUS IS '返回信息'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.STATUS                 IS '有效状态'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.SEND_STATUS            IS '报送状态'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.STATUS_DATE            IS '数据日期'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.CREATE_USER            IS '创建人'                                           ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.CREATE_TIME            IS '创建时间'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.UPDATE_USER            IS '更新人'                                           ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.UPDATE_TIME            IS '更新时间'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.ORG_CODE               IS '机构代码'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.DEPT_CODE              IS '部门代码'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.CREATE_DEPT            IS '创建部门'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.PBC_PROD_TYPE          IS '产品品种'                                         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h10000				  IS 'AMT_h10000_ZWZQ一、债务证券（按评级分类）'		;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h11000      		  	  IS	'AMT_h11000_ZCXJRZQ政策性金融债券'              ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h11100      		  	  IS	'AMT_h11100_AAJYSAA+（含）以上'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h11200      		  	  IS	'AMT_h11200_AAJYXAA+以下'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h11300      		  	  IS	'AMT_h11300_WPJ无评级'                          ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h12000      		  	  IS	'AMT_h12000_QTJRZQ其他金融债券'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h12100      		  	  IS	'AMT_h12100_AAJYSAA+（含）以上'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h12200      		  	  IS	'AMT_h12200_AAJYXAA+以下'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h12300      		  	  IS	'AMT_h12300_WPJ无评级'                          ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h13000      		  	  IS	'AMT_h13000_QYZQ企业债券'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h13100      		  	  IS	'AMT_h13100_AAJYSAA+（含）以上'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h13200      		  	  IS	'AMT_h13200_AAJYXAA+以下'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h13300      		  	  IS	'AMT_h13300_WPJ无评级'                          ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h14000      		  	  IS	'AMT_h14000_ZCZCZQ资产支持证券'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h14100      		  	  IS	'AMT_h14100_AAJYSAA+（含）以上'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h14200      		  	  IS	'AMT_h14200_AAJYXAA+以下'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h14300      		  	  IS	'AMT_h14300_WPJ无评级'                          ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h20000      		  	  IS	'AMT_h20000_ZYZQAQZFL二、企业债券按券种分类'    ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h21000      		  	  IS	'AMT_h21000_QZQYZ其中：企业债'                  ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h22000      		  	  IS	'AMT_h22000_GSZ公司债'                          ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h23000      		  	  IS	'AMT_h23000_FJRQYZW非金融企业债务融资工具'      ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h30000      		  	  IS	'AMT_h30000_YHZBBCGJ三、银行资本补充工具合计'   ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h31000      		  	  IS	'AMT_h31000_QZYXG其中：优先股'                  ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h32000      		  	  IS	'AMT_h32000_YXZZX永续债（债性）'                ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h33000      		  	  IS	'AMT_h33000_YXZGX永续债（股性）'                ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h34000      		  	  IS	'AMT_h34000_EJZBZ二级资本债'                    ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h40000      		  	  IS	'AMT_h40000_ZQNHGHTYCJ债券逆回购和同业拆借'     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h41000      		  	  IS	'AMT_h41000_ZH住户'                             ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h42000      		  	  IS	'AMT_h42000_GYZF广义政府'                       ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h43000      		  	  IS	'AMT_h43000_FJRQY非金融企业'                    ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h44000      		  	  IS	'AMT_h44000_YHYCKLJRJG银行业存款类金融机构'     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h45000      		  	  IS	'AMT_h45000_YHYFCKLJRJG银行业非存款类金融机构'  ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h46000      		  	  IS	'AMT_h46000_FYHYJRJG非银行业金融机构'           ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h46100      		  	  IS	'AMT_h46100_QZZYJYDSF其中：中央交易对手方'      ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47000      		  	  IS	'AMT_h47000_TDMDZT特定目的载体'                 ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47100      		  	  IS	'AMT_h47100_YHFBBLC银行非保本理财'              ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47200      		  	  IS	'AMT_h47200_XTGSZGCP信托公司资管产品'           ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47300      		  	  IS	'AMT_h47300_ZQGSJQZGS证券公司及其子公司资管产品';
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47400      		  	  IS	'AMT_h47400_JJGLGSJQZGS基金管理公司及其子公司专户';
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47500      		  	  IS	'AMT_h47500_QHGLGSJQZGS期货公司及其子公司资管产品';
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47600      		  	  IS	'AMT_h47600_BXZGCP保险资管产品'					  ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47700      		  	  IS	'AMT_h47700_JRZCTZGSJQZGS金融资产投资公司资管产品';
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47800      		  	  IS	'AMT_h47800_GMJJ公募基金'                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47900      		  	  IS	'AMT_h47900_SMJGSMJJ私募机构私募基金'            ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h47a00      		  	  IS	'AMT_h47a00_QTTDMDZT其他特定目的载体'            ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h48000      		  	  IS	'AMT_h48000_JW境外'                              ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h50000      		  	  IS	'AMT_h50000_ZCJZZB五、资产减值准备'              ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h51000      		  	  IS	'AMT_h51000_CK存款'                              ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h52000      		  	  IS	'AMT_h52000_KZRCD可转让存单'                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h53000      		  	  IS	'AMT_h53000_ZWZQ债务证券'                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h54000      		  	  IS	'AMT_h54000_CHGHCJWDK除回购和拆借外贷款'         ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h55000      		  	  IS	'AMT_h55000_HGHCJ回购和拆借'                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h56000      		  	  IS	'AMT_h56000_ZCSYQ资产收益权'                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h57000      		  	  IS	'AMT_h57000_GQJTDMDDZTFE股权及特定目的载体份额'  ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h58000      		  	  IS	'AMT_h58000_JRYSP金融衍生品'                     ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h59000      		  	  IS	'AMT_h59000_YSZK应收账款'                        ;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.h5a000      		  	  IS	'AMT_h5a000_QT其他'                              ;
ALTER TABLE  ZG10_PBC_COL_REPORT  RENAME COLUMN    CHB_SUBMIT_DEADLINE TO  PBC_SUBMIT_DEADLINE;
COMMENT ON COLUMN  ZG10_PBC_COL_REPORT.PBC_SUBMIT_DEADLINE    IS '人行报送截止日'                                   ;
/*增加批处理配置*/
delete from  sys_batch_task where TASK_ID='8210';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES
('8210', 'SVR', '采集平台资管产品债券等资产配置情况信息(ZG10)生成批处理', null, 'ZG10PbcColReportTaskService', 3000, 'system', null, sysdate, 'system', sysdate, null);


/*SYS_MENU*/
delete from sys_menu where menu_id = 'REPORT_REGIST_001_003_023';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_REGIST_001_003_023','SVR',null,'REPORT_REGIST_001_003','ZG10PbcColAssetConfigurationSub',23,'2','ZG10 债券等资产配置情况信息','system',null,sysdate,'system',sysdate);
/*SYS_PAGE*/
delete from sys_page where page_id = 'ZG10PbcColAssetConfigurationSub';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ZG10PbcColAssetConfigurationSub','SVR','/pbc/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub','report/pbc/classic/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub','REPORT_REGIST_001_003_023',null,null,'02','02','ZG10 债券等资产配置情况信息','system',null,sysdate,'system',sysdate);

/*建表登记表*/


/**按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG10PbcColAssetConfigurationSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_manual', '01', '01', null, '手工处理', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, sysdate, 'system', sysdate, 'C', null);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) VALUES ('ZG10PbcColAssetConfigurationSub', 'ZG10PbcColAssetConfigurationSub_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'C', null);

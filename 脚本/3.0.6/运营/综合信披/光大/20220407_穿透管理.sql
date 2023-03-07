--页面菜单配置
delete from SYS_MENU where PAGE_ID in('RskValStructure001','RskValAssetCode001','RskValSumname001','RskValRelateImport001','RskValTableImport001'
,'RskValtblPrtBotasset001','RskValTableQuery001','RskValBotDetailReport001','RskValTableImportLog001','RskValAssetCheck001') ;
delete from SYS_MENU where menu_id ='REPORT_MAINTAIN_001_005';
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005','REP',null,'REPORT_MAINTAIN_001',null,5,2,'穿透管理','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_001','REP',null,'REPORT_MAINTAIN_001_005','RskValStructure001',1,'02','估值表指标结构映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_002','REP',null,'REPORT_MAINTAIN_001_005','RskValAssetCode001',2,'02','估值表资产科目映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_003','REP',null,'REPORT_MAINTAIN_001_005','RskValSumname001',3,'02','估值表汇总科目映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_004','REP',null,'REPORT_MAINTAIN_001_005','RskValRelateImport001',4,'02','估值表关联导入映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_005','REP',null,'REPORT_MAINTAIN_001_005','RskValTableImport001',5,'02','估值表导入','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_006','REP',null,'REPORT_MAINTAIN_001_005','RskValtblPrtBotasset001',6,'02','估值表底层资产映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_005_007','REP',null,'REPORT_MAINTAIN_001_005','RskValTableQuery001',7,'02','估值表查询','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where  PAGE_ID in('RskValStructure001','RskValAssetCode001','RskValSumname001','RskValRelateImport001','RskValTableImport001'
,'RskValtblPrtBotasset001','RskValTableQuery001','RskValBotDetailReport001','RskValTableImportLog001','RskValAssetCheck001') ;
INSERT INTO SYS_PAGE VALUES ('RskValStructure001','REP','/svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure001','svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure001','REPORT_MAINTAIN_001_005_001',null,null,'02','01','估值表指标结构映射管理','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValAssetCode001','REP','/svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001','svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001','REPORT_MAINTAIN_001_005_002',null,null,'02','01','估值表资产科目映射管理','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValSumname001','REP','/svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname001','svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname001','REPORT_MAINTAIN_001_005_003',null,null,'02','01','估值表组合汇总科目映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValRelateImport001','REP','/svreport/gd/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport001','svreport/gd/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport001','REPORT_MAINTAIN_001_005_004',null,null,'02','01','估值表组合关联导入映射查询','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValTableImport001','REP','/svreport/gd/risk/valtblimport/RskValTableImport001','svreport/gd/risk/valtblimport/RskValTableImport001','REPORT_MAINTAIN_001_005_005',null,null,'02','01','估值表导入','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValtblPrtBotasset001','REP','/svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset001','svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset001','REPORT_MAINTAIN_001_005_006',null,null,'02','01','估值表底层资产映射','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValTableQuery001','REP','/svreport/gd/risk/valtblimport/RskValTableQuery001','svreport/gd/risk/valtblimport/RskValTableQuery001','REPORT_MAINTAIN_001_005_007',null,null,'02','01','估值表查询','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValBotDetailReport001','REP','/svreport/gd/risk/rskbotdetailreport/RskValBotDetailReport001','svreport/gd/risk/rskbotdetailreport/RskValBotDetailReport001','REPORT_MAINTAIN_001_005_008',null,null,'02','01','穿透后底层明细表','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValTableImportLog001','REP','/svreport/gd/risk/valtblimportlog/RskValTableImportLog001','svreport/gd/risk/valtblimportlog/RskValTableImportLog001','REPORT_MAINTAIN_001_005_009',null,null,'02','01','估值表自动导入结果查询','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValAssetCheck001','REP','/svreport/gd/risk/valtblassetcheck/RskValAssetCheck001','svreport/gd/risk/valtblassetcheck/RskValAssetCheck001','REPORT_MAINTAIN_001_005_010',null,null,'02','01','穿透后资产校验','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from sys_page where page_id='RskValStructure002';
INSERT INTO SYS_PAGE VALUES ('RskValStructure002','REP','/svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure002','svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure002',null,null,null,'02','01','映射明细','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
delete from SYS_PAGE where PAGE_ID='RskValStructure003';
INSERT INTO SYS_PAGE VALUES ('RskValStructure003','REP','/svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure003','svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure003',null,null,null,'02','01','估值表资产科目映射新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where PAGE_ID in('RskValAssetCode002','RskValAssetCode003','RskValAssetCode004');
INSERT INTO SYS_PAGE VALUES ('RskValAssetCode002','REP','/svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002','svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002',null,null,null,'02','01','估值表资产科目映射新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValAssetCode003','REP','/svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode003','svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode003',null,null,null,'02','01','估值表资产科目映射明细管理','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValAssetCode004','REP','/svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004','svreport/gd/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004',null,null,null,'02','01','估值表资产科目映射明细新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where PAGE_ID in('RskValSumname002','RskValSumname003');
INSERT INTO SYS_PAGE VALUES ('RskValSumname002','REP','/svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname002','svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname002',null,null,null,'02','01','估值表组合汇总科目映射新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));
INSERT INTO SYS_PAGE VALUES ('RskValSumname003','REP','/svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname003','svreport/gd/risk/penetrationmanagement/rskvalsumname/RskValSumname003',null,null,null,'02','01','估值表组合汇总科目映射明细新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where PAGE_ID in('RskValRelateImport002');
INSERT INTO SYS_PAGE VALUES ('RskValRelateImport002','REP','/svreport/gd/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002','svreport/gd/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002',null,null,null,'02','01','估值表组合关联导入映射新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where PAGE_ID in('RskValTableImport002');
INSERT INTO SYS_PAGE VALUES ('RskValTableImport002','REP','/svreport/gd/risk/valtblimport/RskValTableImport002','svreport/gd/risk/valtblimport/RskValTableImport002',null,null,null,'02','01','估值表组合关联导入映射新建','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_PAGE where PAGE_ID in('RskValtblPrtBotasset002');
INSERT INTO SYS_PAGE VALUES ('RskValtblPrtBotasset002','REP','/svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002','svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002',null,null,null,'02','01','估值表底层资产映射配置','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

 delete from SYS_PAGE where PAGE_ID in('RskValtblPrtBotasset003');
INSERT INTO SYS_PAGE VALUES ('RskValtblPrtBotasset003','REP','/svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003','svreport/gd/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003',null,null,null,'02','01','估值表底层资产映射应用','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

---字典值配置
delete from SYS_DATADICT where dict_code='VALTBL_AUTO_IMPORT_STATUS';
INSERT INTO SYS_DATADICT values('VALTBL_AUTO_IMPORT_STATUS','SVR','估值自动导入状态',null,'02','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_DATADICT_LIST where dict_code='VALTBL_AUTO_IMPORT_STATUS';
insert into SYS_DATADICT_LIST values('VALTBL_AUTO_IMPORT_STATUS','01','导入成功',1,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('VALTBL_AUTO_IMPORT_STATUS','02','导入失败',2,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('VALTBL_AUTO_IMPORT_STATUS','03','手工处理',3,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('VALTBL_AUTO_IMPORT_STATUS','04','警告',4,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);

delete from SYS_DATADICT where dict_code='SUBJECT_CCY_TYPE';
INSERT INTO SYS_DATADICT values('SUBJECT_CCY_TYPE','SVR','估值自动导入状态',null,'02','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'));

delete from SYS_DATADICT_LIST where dict_code='SUBJECT_CCY_TYPE';
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','AUD','06',6,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','CAD','07',7,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','CNY','01',1,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','EUR','04',4,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','GBP','05',5,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','HKD','03',3,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','JPY','09',9,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','SGD','08',8,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('SUBJECT_CCY_TYPE','USD','02',2,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);

----sys_para
delete from sys_para where PARA_CODE='CHB_VAL_SPLIT_STR';
INSERT INTO sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
VALUES ('REP', 'CHB_VAL_SPLIT_STR', '-', null, null, '导入估值表中文件名称拆分分隔符', 'system', '', date_format('2022/3/30', '%Y%m%d%H%i%s'), 'zyf', date_format('2022/3/30', '%Y%m%d%H%i%s'), null, '01');


--建表
CREATE TABLE chb_sec_repay_info
(SEC_ID varchar(50)  NOT NULL COMMENT '债券代码',
SEC_NM varchar(200)  COMMENT '债券名称',
REPAY_DT datetime NOT NULL COMMENT '还本日期',
REPAY_RATE decimal(19,4) COMMENT '还本比例',
REPAY_NET decimal(19,4) COMMENT '还本后面值',
TRADE_MARKET varchar(2)  COMMENT '上市地点 上交所：SH,深交所:SZ,银行间：IB',
REPAY_CYCLE_DAY datetime COMMENT '还本周期计息截止日',
REPAY_MODE varchar(1)  COMMENT '还本方式 按面值：1,按数量：2',
PRIMARY KEY (SEC_ID, REPAY_DT)) ;
ALTER TABLE CHB_SEC_REPAY_INFO COMMENT '债券还本信息a';

CREATE TABLE rsk_valtbl_asset_sub_map
(ID varchar(32)  NOT NULL COMMENT '组合资产科目映射编号',
NAME varchar(200)  NOT NULL COMMENT '映射名称',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID)) ;
ALTER TABLE rsk_valtbl_asset_sub_map COMMENT '估值表资产科目映射';


CREATE TABLE rsk_valtbl_asset_sub_map_dtl
(ID varchar(32)  NOT NULL COMMENT '组合资产科目映射编号',
SRC_CODE varchar(50)  NOT NULL COMMENT '源科目代码',
TGT_CODE varchar(50)  NOT NULL COMMENT '目标科目代码',
ALLOW_SUM varchar(50)  NOT NULL COMMENT '是否允许汇总(Y:是,N:否)',
MEMO text  COMMENT '描述',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID, SRC_CODE)) ;
ALTER TABLE rsk_valtbl_asset_sub_map_dtl COMMENT '组合资产科目映射编号';


CREATE TABLE rsk_valtbl_auto_import_log
(id varchar(64)   NOT NULL COMMENT 'id',
file_name varchar(128)   COMMENT '文件名称',
ptl_id varchar(128)   COMMENT '资管计划',
val_date date COMMENT '估值日期',
status varchar(8)   COMMENT '导入状态',
message text   COMMENT '导入描述',
create_time datetime COMMENT '导入时间',
file_path varchar(256)   COMMENT '文件路径',
PRIMARY KEY (id),
INDEX file_name (file_name),
INDEX val_date (val_date),
INDEX status (status),
INDEX ptl_id (ptl_id)) ;
ALTER TABLE rsk_valtbl_auto_import_log COMMENT '估值表自动导入日志';


CREATE TABLE rsk_valtbl_prt_botasset_map
(PTL_ID varchar(32)  NOT NULL COMMENT '组合代码',
SUB_CODE varchar(32)  NOT NULL COMMENT '科目代码',
FINPROD_ID varchar(50)  COMMENT '金融产品代码',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
SUB_NAME varchar(100)  COMMENT '科目名称',
SUB_CLASS varchar(32)  COMMENT '科目类型',
ASSET_SOURCE varchar(32)  COMMENT '资产来源',
MARKET_ID char(2)  COMMENT '估值表债券市场代码',
PRIMARY KEY (PTL_ID, SUB_CODE)) ;
ALTER TABLE rsk_valtbl_prt_botasset_map COMMENT '估值表组合底层资产映射';


CREATE TABLE rsk_valtbl_prt_imp_map
(PTL_ID varchar(32)  NOT NULL COMMENT '组合代码',
START_ROW int(10) NOT NULL COMMENT '起始行',
SUBJECT_CODE_COL int(10) NOT NULL COMMENT '科目代码列',
STRUCT_MAP_ID varchar(32)  NOT NULL COMMENT '指标结构映射编号',
SUMSUB_MAP_ID varchar(32)  NOT NULL COMMENT '汇总科目映射编号',
ASSET_SUB_MAP_ID varchar(32)  NOT NULL COMMENT '资产科目映射编号',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
FILTER_ID varchar(50)  COMMENT '过滤器',
PRIMARY KEY (PTL_ID)) ;
ALTER TABLE rsk_valtbl_prt_imp_map COMMENT '估值表组合关联导入映射';


CREATE TABLE rsk_valtbl_struct_map
(ID varchar(32)  NOT NULL COMMENT '结构映射编号',
NAME varchar(200)  NOT NULL COMMENT '映射名称',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID)) ;
ALTER TABLE rsk_valtbl_struct_map COMMENT '估值表指标结构映射';


CREATE TABLE rsk_valtbl_struct_map_dtl
(ID varchar(32)  NOT NULL COMMENT '结构映射编号',
SRC_COL_INDEX varchar(20)  NOT NULL COMMENT '列索引',
SRC_COL_DATA_TYPE varchar(50)  NOT NULL COMMENT '列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)',
TGT_INDEX varchar(50)  NOT NULL COMMENT '指标',
MEMO text  COMMENT '描述',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID, SRC_COL_INDEX)) ;
ALTER TABLE rsk_valtbl_struct_map_dtl COMMENT '估值表指标结构映射明细';


CREATE TABLE rsk_valtbl_subject
(SUB_CODE varchar(32)  NOT NULL COMMENT '科目代码',
SUB_TYPE varchar(50)  NOT NULL COMMENT '科目类型',
SUB_NAME varchar(200)  NOT NULL COMMENT '科目名称',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (SUB_CODE, SUB_TYPE)) ;
ALTER TABLE rsk_valtbl_subject COMMENT '估值科目表';


CREATE TABLE rsk_valtbl_sumsub_map
(ID varchar(32)  NOT NULL COMMENT '组合汇总科目映射编号',
NAME varchar(200)  NOT NULL COMMENT '映射名称',
TRIM_CHARS varchar(50)  COMMENT '清理字符',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID)) ;
ALTER TABLE rsk_valtbl_sumsub_map COMMENT '估值表汇总科目映射';


CREATE TABLE rsk_valtbl_sumsub_map_dtl
(ID varchar(32)  NOT NULL COMMENT '汇总科目映射编号',
SUM_CODE varchar(50)  NOT NULL COMMENT '目标汇总科目代码',
SUM_NAME varchar(200)  NOT NULL COMMENT '源汇总科目名称',
HAS_NEXT_INDEX varchar(50)  NOT NULL COMMENT '是否有下一列指标(Y:是,N:否)',
NEXT_INDEX_TYPE varchar(50)  COMMENT '下一指标数据类型',
CREATE_USER varchar(20)  NOT NULL COMMENT '创建人',
CREATE_DEPT varchar(32)  COMMENT '创建部门',
CREATE_TIME datetime NOT NULL COMMENT '创建时间',
UPDATE_USER varchar(20)  NOT NULL COMMENT '更新人',
UPDATE_TIME datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (ID, SUM_CODE)) ;
ALTER TABLE rsk_valtbl_sumsub_map_dtl COMMENT '估值表汇总科目映射明细';



alter table RSK_VALTBL_ASSET_SUB add COLUMN SECURITY_COST_ORI decimal(30,14) COMMENT  '成本(原币)';
alter table rsk_valtbl_asset_sub add COLUMN SMV_ORI decimal(30,14) COMMENT '市值(原币)';
alter table rsk_valtbl_asset_sub add COLUMN VALUE_ADDED_ORI decimal(30,14) COMMENT  '估值增值(原币)';
alter table rsk_valtbl_asset_sub add COLUMN TRADE_PLACE VARCHAR2(50) COMMENT  '交易场所';
alter table rsk_valtbl_asset_sub add COLUMN CCY VARCHAR2(50) COMMENT  '币种';
alter table rsk_valtbl_asset_sub add COLUMN EXCHANGE_RATE decimal(30,14) COMMENT  '汇率';
alter table rsk_valtbl_asset_sub add COLUMN INVEST_TYPE VARCHAR2(50) COMMENT  '投资类型';
alter table rsk_valtbl_asset_sub add COLUMN ISSUE_CODE VARCHAR2(50) COMMENT  '发行代码';
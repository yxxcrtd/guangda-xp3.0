--DROP TABLE  pbc_data_collect_dat_file;
CREATE TABLE pbc_data_collect_dat_file(
 UUID VARCHAR2(36) NOT NULL  PRIMARY KEY ,
 dat_file_name VARCHAR2(200)  NOT NULL ,
 dat_file_name_md5 VARCHAR2(50) NOT NULL ,
 report_name VARCHAR2(10) NOT NULL ,
 create_date DATE NOT NULL ,
 report_date DATE  ,
 report_status VARCHAR2(10) ,
 CREATE_TIME TIMESTAMP(6)   DEFAULT NULL ,
 UPDATE_TIME TIMESTAMP(6)   DEFAULT NULL ,
 CREATE_USER VARCHAR2(50)  DEFAULT NULL ,
 CREATE_DEPT VARCHAR2(10)  DEFAULT NULL ,
 UPDATE_USER VARCHAR2(10)  DEFAULT NULL ,
  remark VARCHAR2(500) 
);

comment on table pbc_data_collect_dat_file is 'dat信息表（数据采集平台）';
comment on column pbc_data_collect_dat_file.UUID is '主键';
comment on column pbc_data_collect_dat_file.dat_file_name is 'Dat文件名称';
comment on column pbc_data_collect_dat_file.dat_file_name_md5 is '文件名称MD5码';
comment on column pbc_data_collect_dat_file.report_name is '报表名称';
comment on column pbc_data_collect_dat_file.create_date is '生成日期';
comment on column pbc_data_collect_dat_file.report_date is '报送日期';
comment on column pbc_data_collect_dat_file.report_status is '报送状态';
comment on column pbc_data_collect_dat_file.CREATE_TIME is '创建日期';
comment on column pbc_data_collect_dat_file.UPDATE_TIME is '更新日期';
comment on column pbc_data_collect_dat_file.CREATE_USER is '创建人';
comment on column pbc_data_collect_dat_file.CREATE_DEPT is '创建部门';
comment on column pbc_data_collect_dat_file.UPDATE_USER is '更新人';
comment on column pbc_data_collect_dat_file.remark is '备注';


/*ZG01 资管产品基本信息 dat明细表*/
DROP TABLE PBC_COL_PRD_BASEINFO_DAT;
CREATE TABLE PBC_COL_PRD_BASEINFO_DAT
AS (SELECT * FROM PBC_COL_PRD_BASEINFO_SUB WHERE 1=2);

ALTER TABLE PBC_COL_PRD_BASEINFO_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column PBC_COL_PRD_BASEINFO_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE PBC_COL_PRD_BASEINFO_SUB ADD  datuuid VARCHAR(36) null;
 COMMENT on column PBC_COL_PRD_BASEINFO_SUB.datuuid is 'pbc_data_collect_dat_file主键';
 
/*ZG02 资管产品初始募集信息*/
DROP TABLE pbc_col_init_prod_raising_DAT;
CREATE TABLE pbc_col_init_prod_raising_DAT
AS (SELECT * FROM pbc_col_init_prod_raising_sub WHERE 1=2);

ALTER TABLE pbc_col_init_prod_raising_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column pbc_col_init_prod_raising_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE pbc_col_init_prod_raising_sub ADD  datuuid VARCHAR(36) null;
 COMMENT on column pbc_col_init_prod_raising_sub.datuuid is 'pbc_data_collect_dat_file主键';
 
/*ZG03 资管产品终止信息*/
DROP TABLE pbc_col_prd_baseinfo_end_DAT;
CREATE TABLE pbc_col_prd_baseinfo_end_DAT
AS (SELECT * FROM pbc_col_prd_baseinfo_end_sub WHERE 1=2);
ALTER TABLE pbc_col_prd_baseinfo_end_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column pbc_col_prd_baseinfo_end_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE pbc_col_prd_baseinfo_end_sub ADD  datuuid VARCHAR(36) null;
 COMMENT on column pbc_col_prd_baseinfo_end_sub.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG04 资管产品存续期募集信息*/
DROP TABLE  pbc_col_prod_raising_DAT;
CREATE TABLE pbc_col_prod_raising_DAT
AS (SELECT * FROM pbc_col_prod_raising_sub WHERE 1=2);
ALTER TABLE pbc_col_prod_raising_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column pbc_col_prod_raising_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE pbc_col_prod_raising_sub ADD  datuuid VARCHAR(36) null;
 COMMENT on column pbc_col_prod_raising_sub.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG05 资管产品资产负债信息*/
DROP TABLE ZG05_PBC_COL_REPORT_DAT;
CREATE TABLE ZG05_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG05_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG05_PBC_COL_REPORT_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column ZG05_PBC_COL_REPORT_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE ZG05_PBC_COL_REPORT ADD  datuuid VARCHAR(36) null;
 COMMENT on column ZG05_PBC_COL_REPORT.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG06资产收益权明细信息*/
DROP TABLE PBC_COL_ASSET_USUFRUCT_DAT;
CREATE TABLE PBC_COL_ASSET_USUFRUCT_DAT
AS (SELECT * FROM PBC_COL_ASSET_USUFRUCT_SUB WHERE 1=2);
ALTER TABLE PBC_COL_ASSET_USUFRUCT_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column PBC_COL_ASSET_USUFRUCT_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE PBC_COL_ASSET_USUFRUCT_SUB ADD  datuuid VARCHAR(36) null;
 COMMENT on column PBC_COL_ASSET_USUFRUCT_SUB.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG07除回购和拆借外贷款明细信息*/
DROP TABLE PBC_COL_LOAN_DETAIL_DAT;
CREATE TABLE PBC_COL_LOAN_DETAIL_DAT
AS (SELECT * FROM PBC_COL_LOAN_DETAIL_SUB WHERE 1=2);
ALTER TABLE PBC_COL_LOAN_DETAIL_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column PBC_COL_LOAN_DETAIL_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE PBC_COL_LOAN_DETAIL_SUB ADD  datuuid VARCHAR(36) null;
 COMMENT on column PBC_COL_LOAN_DETAIL_SUB.datuuid is 'pbc_data_collect_dat_file主键';


/*ZG08 特定目的载体交易对手明细信息*/
DROP TABLE pbc_col_spe_counter_party_DAT;
CREATE TABLE pbc_col_spe_counter_party_DAT
AS (SELECT * FROM pbc_col_spe_counter_party_sub WHERE 1=2);
ALTER TABLE pbc_col_spe_counter_party_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column pbc_col_spe_counter_party_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE pbc_col_spe_counter_party_sub ADD  datuuid VARCHAR(36) null;
 COMMENT on column pbc_col_spe_counter_party_sub.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG09 资产负债剩余期限信息*/
DROP TABLE ZG09_PBC_COL_REPORT_DAT;
CREATE TABLE ZG09_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG09_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG09_PBC_COL_REPORT_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column ZG09_PBC_COL_REPORT_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE ZG09_PBC_COL_REPORT ADD  datuuid VARCHAR(36) null;
 COMMENT on column ZG09_PBC_COL_REPORT.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG10 债券等资产配置情况信息*/
DROP TABLE ZG10_PBC_COL_REPORT_DAT;
CREATE TABLE ZG10_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG10_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG10_PBC_COL_REPORT_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column ZG10_PBC_COL_REPORT_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE ZG10_PBC_COL_REPORT ADD  datuuid VARCHAR(36) null;
 COMMENT on column ZG10_PBC_COL_REPORT.datuuid is 'pbc_data_collect_dat_file主键';

/*ZG11 企业债券分行业、企业规模情况信息*/
DROP TABLE ZG11_PBC_COL_REPORT_DAT;
CREATE TABLE ZG11_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG11_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG11_PBC_COL_REPORT_DAT ADD  datuuid VARCHAR(36) NOT NULL;
 COMMENT on column ZG11_PBC_COL_REPORT_DAT.datuuid is 'pbc_data_collect_dat_file主键';
 
ALTER TABLE ZG11_PBC_COL_REPORT ADD  datuuid VARCHAR(36) null;
 COMMENT on column ZG11_PBC_COL_REPORT.datuuid is 'pbc_data_collect_dat_file主键';


DROP TABLE IF EXISTS pbc_data_collect_dat_file;
CREATE TABLE pbc_data_collect_dat_file(
 `UUID` VARCHAR(36) NOT NULL  PRIMARY KEY COMMENT '主键',
 dat_file_name VARCHAR(200)  NOT NULL COMMENT 'Dat文件名称',
 dat_file_name_md5 VARCHAR(50) NOT NULL COMMENT '文件名称MD5码',
 report_name VARCHAR(10) NOT NULL COMMENT '报表名称',
 create_date DATE NOT NULL COMMENT '生成日期',
 report_date DATE  COMMENT '报送日期',
 report_status VARCHAR(10) COMMENT '报送状态',
 `CREATE_TIME` DATETIME  NULL DEFAULT NULL COMMENT '创建日期',
 `UPDATE_TIME` DATETIME  NULL DEFAULT NULL COMMENT '更新日期',
 `CREATE_USER` VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人',
 `CREATE_DEPT` VARCHAR(10) NULL DEFAULT NULL COMMENT '创建部门',
 `UPDATE_USER` VARCHAR(10) NULL DEFAULT NULL COMMENT '更新人',
  `remark` VARCHAR(500) COMMENT '备注'
)ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'dat信息表（数据采集平台）' ROW_FORMAT = DYNAMIC;

/*ZG01 资管产品基本信息 dat明细表*/
DROP TABLE IF EXISTS PBC_COL_PRD_BASEINFO_DAT;
CREATE TABLE PBC_COL_PRD_BASEINFO_DAT
AS (SELECT * FROM PBC_COL_PRD_BASEINFO_SUB WHERE 1=2);
ALTER TABLE PBC_COL_PRD_BASEINFO_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE PBC_COL_PRD_BASEINFO_SUB ADD COLUMN datuuid VARCHAR(36) null COMMENT 'pbc_data_collect_dat_file主键';

/*ZG02 资管产品初始募集信息*/
DROP TABLE IF EXISTS pbc_col_init_prod_raising_DAT;
CREATE TABLE pbc_col_init_prod_raising_DAT
AS (SELECT * FROM pbc_col_init_prod_raising_sub WHERE 1=2);
ALTER TABLE pbc_col_init_prod_raising_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE pbc_col_init_prod_raising_sub ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG03 资管产品终止信息*/
DROP TABLE IF EXISTS pbc_col_prd_baseinfo_end_DAT;
CREATE TABLE pbc_col_prd_baseinfo_end_DAT
AS (SELECT * FROM pbc_col_prd_baseinfo_end_sub WHERE 1=2);
ALTER TABLE pbc_col_prd_baseinfo_end_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE pbc_col_prd_baseinfo_end_sub ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG04 资管产品存续期募集信息*/
DROP TABLE IF EXISTS pbc_col_prod_raising_DAT;
CREATE TABLE pbc_col_prod_raising_DAT
AS (SELECT * FROM pbc_col_prod_raising_sub WHERE 1=2);
ALTER TABLE pbc_col_prod_raising_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE pbc_col_prod_raising_sub ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG05 资管产品资产负债信息*/
DROP TABLE IF EXISTS ZG05_PBC_COL_REPORT_DAT;
CREATE TABLE ZG05_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG05_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG05_PBC_COL_REPORT_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE ZG05_PBC_COL_REPORT ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG06资产收益权明细信息*/
DROP TABLE IF EXISTS PBC_COL_ASSET_USUFRUCT_DAT;
CREATE TABLE PBC_COL_ASSET_USUFRUCT_DAT
AS (SELECT * FROM PBC_COL_ASSET_USUFRUCT_SUB WHERE 1=2);
ALTER TABLE PBC_COL_ASSET_USUFRUCT_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE PBC_COL_ASSET_USUFRUCT_SUB ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';


/*ZG07除回购和拆借外贷款明细信息*/
DROP TABLE IF EXISTS PBC_COL_LOAN_DETAIL_DAT;
CREATE TABLE PBC_COL_LOAN_DETAIL_DAT
AS (SELECT * FROM PBC_COL_LOAN_DETAIL_SUB WHERE 1=2);
ALTER TABLE PBC_COL_LOAN_DETAIL_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE PBC_COL_LOAN_DETAIL_SUB ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG08 特定目的载体交易对手明细信息*/
DROP TABLE IF EXISTS pbc_col_spe_counter_party_DAT;
CREATE TABLE pbc_col_spe_counter_party_DAT
AS (SELECT * FROM pbc_col_spe_counter_party_sub WHERE 1=2);
ALTER TABLE pbc_col_spe_counter_party_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE pbc_col_spe_counter_party_sub ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG09 资产负债剩余期限信息*/
DROP TABLE IF EXISTS ZG09_PBC_COL_REPORT_DAT;
CREATE TABLE ZG09_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG09_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG09_PBC_COL_REPORT_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE ZG09_PBC_COL_REPORT ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG10 债券等资产配置情况信息*/
DROP TABLE IF EXISTS ZG10_PBC_COL_REPORT_DAT;
CREATE TABLE ZG10_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG10_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG10_PBC_COL_REPORT_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE ZG10_PBC_COL_REPORT ADD COLUMN datuuid VARCHAR(36) COMMENT 'pbc_data_collect_dat_file主键';

/*ZG11 企业债券分行业、企业规模情况信息*/
DROP TABLE IF EXISTS ZG11_PBC_COL_REPORT_DAT;
CREATE TABLE ZG11_PBC_COL_REPORT_DAT
AS (SELECT * FROM ZG11_PBC_COL_REPORT WHERE 1=2);
ALTER TABLE ZG11_PBC_COL_REPORT_DAT ADD COLUMN datuuid VARCHAR(36) NOT NULL COMMENT 'pbc_data_collect_dat_file主键';
ALTER TABLE ZG11_PBC_COL_REPORT ADD COLUMN datuuid VARCHAR(36)  COMMENT 'pbc_data_collect_dat_file主键';

ALTER TABLE  pbc_report_generation_check ADD IN_FLAG VARCHAR(2) COMMENT  '导入方式';

ALTER TABLE pbc_report_generation_check ADD INDEX  PRZ_CHECK_INDEX(DATA_ID);
alter table pbc_month_asset_position drop column REC_INT_AMT;
ALTER TABLE pbc_month_asset_position ADD COLUMN REC_INT_AMT decimal(30,4) COMMENT '应收利息';

ALTER TABLE ZG09_PBC_COL_REPORT change CHB_SUBMIT_DEADLINE  PBC_SUBMIT_DEADLINE date comment '人行报送截止日';
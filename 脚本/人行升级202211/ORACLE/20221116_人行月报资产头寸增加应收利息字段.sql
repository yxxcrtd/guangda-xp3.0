alter table pbc_month_asset_position drop column REC_INT_AMT;
alter table pbc_month_asset_position add REC_INT_AMT NUMBER(30,4);
comment on column pbc_month_asset_position.REC_INT_AMT is '应收利息';


ALTER TABLE ZG09_PBC_COL_REPORT RENAME COLUMN CHB_SUBMIT_DEADLINE TO  PBC_SUBMIT_DEADLINE;
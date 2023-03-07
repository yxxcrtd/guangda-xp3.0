alter table chb_investhold_info_check drop primary key;
alter table chb_investhold_info_check add primary key(REGIST_BANK_CODE,INDENTIFY_MARK,HOLD_DATE,CCY,STATUS,PRODID,AGENCY_CODE,FUND_ACC_PLACE);
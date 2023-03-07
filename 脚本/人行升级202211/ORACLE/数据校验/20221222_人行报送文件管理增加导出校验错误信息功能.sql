ALTER TABLE  pbc_report_generation_check ADD IN_FLAG VARCHAR2(2);

create index PRZ_CHECK_INDEX on pbc_report_generation_check(DATA_ID);

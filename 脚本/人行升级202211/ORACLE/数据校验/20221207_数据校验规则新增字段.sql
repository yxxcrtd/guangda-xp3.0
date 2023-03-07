ALTER TABLE pbc_check_rule_info ADD related_table varchar2(100) NULL ;
ALTER TABLE pbc_check_rule_info ADD update_time DATE NULL ;
ALTER TABLE pbc_check_rule_info MODIFY update_time varchar2(30);


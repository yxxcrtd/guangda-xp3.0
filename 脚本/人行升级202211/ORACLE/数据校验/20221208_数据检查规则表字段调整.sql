ALTER TABLE pbc_check_rule_info MODIFY  checked_field_name varchar2(200);
ALTER TABLE pbc_check_rule_info MODIFY  checked_filed_value varchar2(200);
ALTER TABLE pbc_check_rule_info ADD PRIMARY KEY(rule_id);
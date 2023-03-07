ALTER TABLE pbc_check_rule_info MODIFY COLUMN checked_field_name varchar(200) NULL COMMENT '检查字段名称';
ALTER TABLE pbc_check_rule_info MODIFY COLUMN checked_filed_value varchar(200) NULL COMMENT '字段值';
ALTER TABLE pbc_check_rule_info ADD PRIMARY KEY(rule_id);
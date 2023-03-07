ALTER TABLE pbc_check_rule_info ADD related_table varchar(100) NULL COMMENT '关联报表';
ALTER TABLE pbc_check_rule_info ADD update_time DATE NULL COMMENT '更新时间';
ALTER TABLE pbc_check_rule_info MODIFY COLUMN update_time varchar(30) NULL COMMENT '更新时间';


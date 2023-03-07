-- gd_test_20220517_01_dxy.pbc_check_rule_info definition
drop table if exists `pbc_check_rule_info`;
CREATE TABLE `pbc_check_rule_info`
(
    `rule_id`             varchar(12)  NOT NULL COMMENT '规则编号',
    `rule_classify`       varchar(30)  NOT NULL COMMENT '规则大类',
    `rule_type`           varchar(40)  NOT NULL COMMENT '规则类型',
    `rule_param`          varchar(100)  DEFAULT NULL COMMENT '参数（规则类型对应的参数）',
    `table_data_name`     varchar(50)  NOT NULL COMMENT '报表名称',
    `checked_field_name`  varchar(30)  NOT NULL COMMENT '检查字段名称',
    `checked_filed_value` varchar(30)   DEFAULT NULL COMMENT '字段值',
    `rule_method`         varchar(30)   DEFAULT NULL COMMENT '规则方法',
    `rule_content`        varchar(1000) NOT NULL COMMENT '规则内容',
    `rule_prompt`         varchar(300) NOT NULL COMMENT '规则提示语',
    `isStart`             varchar(2)   NOT NULL COMMENT '是否启动：01启动，02禁用',
    `pbc_asset_code`      varchar(12)   DEFAULT NULL COMMENT '人行要素编码',
    `rule_identify`       varchar(20)   DEFAULT NULL COMMENT '规则标识',
    `PARAMS01`            varchar(1000) DEFAULT NULL,
    `PARAMS02`            varchar(1000) DEFAULT NULL,
    `PARAMS03`            varchar(1000) DEFAULT NULL,
    `PARAMS04`            varchar(1000) DEFAULT NULL
) ENGINE=InnoDB  COMMENT='数据检验规则详情表';
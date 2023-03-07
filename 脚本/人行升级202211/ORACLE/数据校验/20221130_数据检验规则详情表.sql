-- gd_test_20220517_01_dxy.pbc_check_rule_info definition
drop table pbc_check_rule_info ;
CREATE TABLE pbc_check_rule_info
(
    rule_id             varchar2(12)  NOT NULL ,
    rule_classify       varchar2(30)  NOT NULL ,
    rule_type           varchar2(40)  NOT NULL ,
    rule_param          varchar2(100) DEFAULT NULL ,
    table_data_name     varchar2(50)  NOT NULL ,
    checked_field_name  varchar2(30)  NOT NULL ,
    checked_filed_value varchar2(30)  NOT NULL ,
    rule_method         varchar2(30)  NOT NULL ,
    rule_content        varchar2(300) NOT NULL ,
    rule_prompt         varchar2(300) NOT NULL ,
    isStart             varchar2(2)   NOT NULL ,
    pbc_asset_code      varchar2(12)  DEFAULT NULL
);
comment on table pbc_check_rule_info is '数据检验规则详情表';
comment on column pbc_check_rule_info.rule_id
is '规则编号';
comment on column pbc_check_rule_info.rule_classify
is '规则大类';
comment on column pbc_check_rule_info.rule_type
is '规则类型';
comment on column pbc_check_rule_info.rule_param
is '参数';
comment on column pbc_check_rule_info.table_data_name
is '报表/数据名称';
comment on column pbc_check_rule_info.checked_field_name
is '字段名称';
comment on column pbc_check_rule_info.checked_filed_value
is '字段值';
comment on column pbc_check_rule_info.rule_method
is '规则方法';
comment on column pbc_check_rule_info.rule_content
is '规则内容';
comment on column pbc_check_rule_info.rule_prompt
is '规则提示语';
comment on column pbc_check_rule_info.isStart
is '是否启动：01启动，02禁用';
comment on column pbc_check_rule_info.pbc_asset_code
is '人行要素编码';


ALTER TABLE pbc_check_rule_info ADD rule_identify varchar2(20) NULL;
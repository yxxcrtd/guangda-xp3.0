/**新增导入方式字段*/
ALTER TABLE  pbc_report_generation_check ADD IN_FLAG VARCHAR2(2);
comment on column pbc_report_generation_check.IN_FLAG is '导入方式';
/**增加索引*/
create  INDEX PRZ_CHECK_INDEX on pbc_report_generation_check(DATA_ID);

drop table pbc_generat_b_checkdetail;
create table pbc_generat_b_checkdetail
(
    UUID         varchar2(36)   not null ,
    CHECK_UUID   varchar2(50) ,
    CHECK_RESULT varchar2(1000),
    CHECK_FLAG   varchar2(1),
    CHECK_DATA   varchar2(1000),
    RULE_ID      varchar2(100) ,
    CREATE_TIME  TIMESTAMP (6) ,
    UPDATE_TIME  TIMESTAMP (6) ,
    CREATE_USER  varchar2(50) ,
    CREATE_DEPT  varchar2(10) ,
    UPDATE_USER  varchar2(10) ,
    STATUS       varchar2(50) ,
     constraint PK_pbc_report_b_checkdetail PRIMARY KEY(UUID)
);
comment on table pbc_generat_b_checkdetail is '数据检查状态详情表';
comment on column pbc_generat_b_checkdetail.UUID is '主键';
comment on column pbc_generat_b_checkdetail.CHECK_UUID is '数据检查uuid';
comment on column pbc_generat_b_checkdetail.CHECK_RESULT is '结果错误信息';
comment on column pbc_generat_b_checkdetail.CHECK_FLAG is '检查状态- 0-成功,1-失败';
comment on column pbc_generat_b_checkdetail.CHECK_DATA is '错误数据-值';
comment on column pbc_generat_b_checkdetail.RULE_ID is '规则编号';
comment on column pbc_generat_b_checkdetail.CREATE_TIME is '创建日期';
comment on column pbc_generat_b_checkdetail.UPDATE_TIME is '更新日期';
comment on column pbc_generat_b_checkdetail.CREATE_USER is '创建人';
comment on column pbc_generat_b_checkdetail.CREATE_DEPT is '创建部门';
comment on column pbc_generat_b_checkdetail.UPDATE_USER is '更新人';
comment on column pbc_generat_b_checkdetail.STATUS is '数据状态';
create  INDEX PRG_CHECK_B_INDEX on pbc_generat_b_checkdetail(CHECK_UUID);
DROP TABLE pbc_report_generat_checkdetail;
create table pbc_report_generat_checkdetail
(
    UUID         varchar2(36)   not null,
    CHECK_UUID   varchar2(50)   null,
    CHECK_RESULT varchar2(1000) null,
    CHECK_FLAG   varchar2(1)    null,
    CHECK_DATA   varchar2(1000) null,
    RULE_ID      varchar2(100)  null,
    CREATE_TIME  date      null,
    UPDATE_TIME  date      null,
    CREATE_USER  varchar2(50)   null,
    CREATE_DEPT  varchar2(10)   null,
    UPDATE_USER  varchar2(10)   null,
    STATUS       varchar2(50)   null,
	 constraint PK_pbc_report_generat_che PRIMARY KEY(UUID)
);
comment on table pbc_report_generat_checkdetail is '数据检查状态详情表';
comment on column pbc_report_generat_checkdetail.CHECK_UUID
is '数据检查uuid';
comment on column pbc_report_generat_checkdetail.CHECK_RESULT
is '结果错误信息';
comment on column pbc_report_generat_checkdetail.CHECK_FLAG
is '检查状态- 0-成功,1-失败';
comment on column pbc_report_generat_checkdetail.CHECK_DATA
is '错误数据-值';
comment on column pbc_report_generat_checkdetail.RULE_ID
is '规则编号';

create index PRG_CHECK_INDEX
    on pbc_report_generat_checkdetail (CHECK_UUID);
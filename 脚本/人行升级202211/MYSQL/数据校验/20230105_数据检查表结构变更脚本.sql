/**新增导入方式字段*/
ALTER TABLE  pbc_report_generation_check ADD IN_FLAG VARCHAR(2) COMMENT  '导入方式';
/**增加索引*/
ALTER TABLE PBC_REPORT_GENERAT_CHECKDETAIL ADD INDEX  PRG_CHECK_INDEX(CHECK_UUID);
ALTER TABLE pbc_report_generation_check ADD INDEX  PRZ_CHECK_INDEX(DATA_ID);

DROP TABLE IF EXISTS pbc_generat_b_checkdetail;
create table pbc_generat_b_checkdetail
(
    UUID         varchar(36)   not null comment '主键'
        primary key,
    CHECK_UUID   varchar(50)   null comment '数据检查uuid',
    CHECK_RESULT varchar(1000) null comment '结果错误信息',
    CHECK_FLAG   varchar(1)    null comment '检查状态- 0-成功,1-失败',
    CHECK_DATA   varchar(1000) null comment '错误数据-值',
    RULE_ID      varchar(100)  null comment '规则编号',
    CREATE_TIME  datetime      null comment '创建日期',
    UPDATE_TIME  datetime      null comment '更新日期',
    CREATE_USER  varchar(50)   null comment '创建人',
    CREATE_DEPT  varchar(10)   null comment '创建部门',
    UPDATE_USER  varchar(10)   null comment '更新人',
    STATUS       varchar(50)   null comment '数据状态'
)
    comment '数据检查状态详情表';

create index PRG_CHECK_B_INDEX
    on pbc_generat_b_checkdetail (CHECK_UUID);
drop  table   if exists chb_equ_type_tmp;
create table chb_equ_type_tmp
(
    INDENTIFY_MARK            varchar(30)            not null comment '识别标识',
    HOLDER_TYPE               char(2)                null comment '权益人类别',
    STATUS                    varchar(10)            null comment '数据状态',
    UPDATE_USER               varchar(20)            null comment '更新人',
    UPDATE_TIME               datetime               null comment '更新时间',
    CREATE_USER               varchar(32)            null comment '创建人',
    CREATE_TIME               datetime               null comment '创建时间'
);


delete from sys_batch_task  where TASK_ID='3200';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES ('3200', 'VAL', '投资者类别转换轮巡批处理', null, 'equityHoldInfoHandleTypCBService', 3070, 'system', null, '2021-09-17 10:43:28', 'system', '2021-09-17 10:43:28', null);

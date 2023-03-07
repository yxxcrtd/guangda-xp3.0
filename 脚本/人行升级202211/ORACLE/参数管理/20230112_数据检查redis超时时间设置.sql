delete from sys_para  where PARA_CODE='redis_config_expire_second';
INSERT INTO sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
VALUES ('SVR', 'redis_config_expire_second', '300', null, null, '数据检查redis超时时间设置。默认的值为：300（5分钟）', 'admin', null, sysdate, 'admin', sysdate, null, '01');


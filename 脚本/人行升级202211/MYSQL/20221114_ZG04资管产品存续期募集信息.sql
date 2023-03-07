-- ZG04批处理
delete from sys_batch_task where TASK_ID='4206';
INSERT INTO sys_batch_task(TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('4206', 'SVR', '采集平台资管产品存续期募集信息(ZG04)生成批处理', null, 'zg04PrdCashRaiseTaskService', 3000, 'system', null, NOW(), 'system', NOW(), null);

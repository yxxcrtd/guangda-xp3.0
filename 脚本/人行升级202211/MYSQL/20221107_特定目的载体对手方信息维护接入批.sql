-- 特定目的载体对手方信息维护接入批
DELETE FROM sys_batch_task WHERE TASK_ID ='4002';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES ('4002', 'SVR', '采集平台特定目的载体对手方信息维护接入批', null, 'svrSpcCounterPartyInterfTaskService', 4002, 'system', null, '2022-11-07 00:00:00', 'system', '2022-11-07 00:00:00', null);

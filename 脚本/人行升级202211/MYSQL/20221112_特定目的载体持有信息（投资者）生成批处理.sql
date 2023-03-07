-- 特定目的载体持有信息（投资者）生成批处理
DELETE FROM sys_batch_task WHERE TASK_ID ='3999' AND TASK_NAME = '采集平台特定目的载体持有信息（投资者）生成批处理';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
VALUES ('3999', 'SVR', '采集平台特定目的载体持有信息（投资者）生成批处理', null, 'svrSpcInvestorsHoldTaskService', 3999, 'system', null, '2022-11-12 00:00:00', 'system', '2022-11-12 00:00:00', null);
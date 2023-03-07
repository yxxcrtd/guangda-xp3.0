/**修改采集平台人行接入批的优先级 3100*/
update  sys_batch_task  set TASK_PRI='3100' WHERE BEAN_NAME IN ('svrProdTargetInterfTaskService','svrSpcCounterPartyInterfTaskService','svrLoanDetailInfoInterfTaskService');
/**修改ZG01报表批处理优先级 4000*/
update  sys_batch_task  set TASK_PRI='4000' WHERE BEAN_NAME IN ('zG01PbcPrdBaseinfoSubTaskService');
/**修改ZG报表批处理优先级 4100*/
update  sys_batch_task  set TASK_PRI='4100' WHERE BEAN_NAME IN ('ZG02PbccolInitProdRaisingSubTaskService',
'zg03PrdBaseinfoEndSubTaskService','zg04PrdCashRaiseTaskService','ZG05PbcCodPrdAssetDebtTaskService',
'ZG06PbcColAssetUsufructTaskService','ZG07PbcColLoanDetailSubTaskService','ZG08SpeCounterQuityTaskService',
'ZG09PbcAssetDebtTermTaskService','ZG10PbcColReportTaskService','ZG11CompanyBondInfoSubTaskService');
/**修改ZG报表dat批处理优先级 4200*/
update  sys_batch_task  set TASK_PRI='4200'WHERE BEAN_NAME IN ('svrPbcZG01DatFileTask',
'svrPbcZG02DatFileTask','svrPbcZG03DatFileTask','svrPbcZG04DatFileTask',
'svrPbcZG05DatFileTask','svrPbcZG06DatFileTask','svrPbcZG07DatFileTask',
'svrPbcZG08DatFileTask','svrPbcZG09DatFileTask','svrPbcZG10DatFileTask','svrPbcZG11DatFileTask');
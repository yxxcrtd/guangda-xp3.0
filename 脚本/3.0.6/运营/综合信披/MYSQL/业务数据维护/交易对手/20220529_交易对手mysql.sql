ALTER TABLE chb_mst_counter_party ADD DATA_SOURCE VARCHAR(20) COMMENT '数据来源';

ALTER TABLE chb_mst_counter_party ADD ERR_MSG TEXT COMMENT '错误信息';

DELETE FROM CHB_INTERF_CONFIG WHERE INTERF_ID = 'CHB_MST_COUNTER_PARTY';
INSERT INTO CHB_INTERF_CONFIG VALUES ('CHB_MST_COUNTER_PARTY',CURRENT_DATE,'交易对手数据',NULL,'F','T','svrPbcReportTaskService;svrAssetInfoRegTaskService;svrAssetDealRegTaskService;svrDataCheckCBService','110','E','Y');
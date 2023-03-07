drop table  if exists CHB_INFO_REPORT_XBRL;
CREATE TABLE `CHB_INFO_REPORT_XBRL` (
  `REPORT_NO` varchar(20)  COMMENT '报送编码',
  `REP_MES_NO` varchar(20)  DEFAULT NULL COMMENT '报送报文编码',
  `UPLOAD_RESULT` varchar(15)  DEFAULT NULL COMMENT '报送结果码',
  `UPLOAD_RESULT_MSG` varchar(200)  DEFAULT NULL COMMENT '报送结果信息',
  `UPLOAD_FILE_ID` varchar(50)  DEFAULT NULL COMMENT '报送结果文件代码',
  `CHECK_FILE_ID` varchar(50)  DEFAULT NULL COMMENT '确认文件代码',
  `CHECK_RESULT` varchar(15)  DEFAULT NULL COMMENT '确认结果码',
  `CHECK_RESULT_MSG` varchar(200)  DEFAULT NULL COMMENT '确认结果消息',
  `CHECK_FILE_NAME` varchar(50)  DEFAULT NULL COMMENT '确认文件名(中债消息获取API返回值)',
  `CHECK_TOTAL_COUNT` decimal(65,30) DEFAULT NULL COMMENT '确认总件数',
  `CHECK_SUCCESS_COUNT` decimal(65,30) DEFAULT NULL COMMENT '确认成功件数',
  `CHECK_FAILED_COUNT` decimal(65,30) DEFAULT NULL COMMENT '确认失败件数',
  `INSIDE_FILE_ID` varchar(50)  DEFAULT NULL COMMENT '内部文件代码',
  `SEND_TIME` date DEFAULT NULL,
  `REPLY_TIME` datetime DEFAULT NULL COMMENT '确认时间',
  `WORKFLOW_ID` varchar(20)  DEFAULT NULL COMMENT '工作流代码',
  `CREATE_USER` varchar(32)  COMMENT '创建人',
  `CREATE_DEPT` varchar(32)  DEFAULT NULL COMMENT '创建部门',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(60)  COMMENT '更新人',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `SUBMIT_COUNT` decimal(65,30) DEFAULT NULL COMMENT '报送总数',
  `SUBMIT_TYPE` varchar(30)  DEFAULT NULL,
  `CHB_INT_CODE` varchar(2)  COMMENT '中债报送编号',
  `XBRL_INT_CODE` varchar(3)  COMMENT 'xbrl报送编号',
  PRIMARY KEY (`REPORT_NO`) USING BTREE
) COMMENT='中债直连报送';



drop table  if exists CHB_XBRLBACK_ERRORDATA;
CREATE TABLE CHB_XBRLBACK_ERRORDATA (
    `SEQNO` varchar(20)  COMMENT '报送编码',
     `REP_MES_NO` varchar(20)  DEFAULT NULL COMMENT '报送报文编码',
    `SUBMIT_NUM` varchar(20) DEFAULT NULL COMMENT '批次号',
    `SUBMIT_TYPE` varchar(30) DEFAULT NULL COMMENT '报送类型',
    `SUBMIT_FEEDBACK_STATUS` varchar(10) DEFAULT NULL COMMENT '报送反馈状态',
    `UPDATE_USER` varchar(20) DEFAULT NULL COMMENT '更新人',
    `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
    `CREATE_USER` varchar(20) DEFAULT NULL COMMENT '创建人',
    `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
    `REPORT_NO` varchar(20) DEFAULT NULL COMMENT '报送编码',
    `SUBMIT_FEEDBACK_MSG` varchar(600) DEFAULT NULL COMMENT '错误信息',
    `UNIQUE_IDENTI_1` varchar(300) COMMENT '业务主键1 - 身份/持有(识别标识)，明细(核心交易流水号)',
    `UNIQUE_IDENTI_2` varchar(300) COMMENT '业务主键2 - 身份(证件号码) ，持有/明细(产品登记编码)',
    `UNIQUE_IDENTI_3` DATE COMMENT '业务主键3 - 持有(持有日期)',
    PRIMARY KEY (SEQNO,REPORT_NO)
) COMMENT='中债直连报送-错误信息';

/***xbrl报送批处理**/
DELETE FROM SYS_PARA WHERE PARA_CODE IN ('SVR_XBRL_SEND_PATH','SVR_XBRL_FEEDBACK_PATH','SVR_XBRL_FEEDBACK_HIS_PATH');
INSERT INTO SYS_PARA(`SYSTEM_CODE`, `PARA_CODE`, `PARAM_VALUE`, `DATA_TYPE`, `DATA_SCOPE`, `REMARK`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `INSTITUTION_CODE`, `UPDATE_FLG`)
VALUES ('SVR', 'SVR_XBRL_SEND_PATH', '/home/svr/send/', '  ', ' ', 'XBRL推送文件地址', 'system', NULL, now(), 'system', now(), NULL, '01');
INSERT INTO SYS_PARA(`SYSTEM_CODE`, `PARA_CODE`, `PARAM_VALUE`, `DATA_TYPE`, `DATA_SCOPE`, `REMARK`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `INSTITUTION_CODE`, `UPDATE_FLG`)
VALUES ('SVR', 'SVR_XBRL_FEEDBACK_PATH', '/home/svr/feedback/', '  ', ' ', 'XBRL获取反馈文件地址', 'system', NULL, now(), 'system', now(), NULL, '01');
INSERT INTO SYS_PARA(`SYSTEM_CODE`, `PARA_CODE`, `PARAM_VALUE`, `DATA_TYPE`, `DATA_SCOPE`, `REMARK`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `INSTITUTION_CODE`, `UPDATE_FLG`)
VALUES ('SVR', 'SVR_XBRL_FEEDBACK_HIS_PATH', '/home/svr/feedbackhis/', '  ', ' ', 'XBRL获取反馈文件备份', 'system', NULL, now(), 'system', now(), NULL, '01');


/***xbrl报送批处理**/
DELETE FROM sys_batch_task WHERE TASK_ID IN ('4201','4202','4203','4204');
INSERT INTO `sys_batch_task`(`TASK_ID`, `SYSTEM_CODE`, `TASK_NAME`, `FTASK_ID`, `BEAN_NAME`, `TASK_PRI`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `IS_BATCH_START_TARGET`) VALUES
('4201', 'SVR', 'xbrl报送投资者身份信息', NULL, 'xbrlSendEquityInfoService', 1, 'system', NULL, now(), 'system', now(), null);
INSERT INTO `sys_batch_task`(`TASK_ID`, `SYSTEM_CODE`, `TASK_NAME`, `FTASK_ID`, `BEAN_NAME`, `TASK_PRI`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `IS_BATCH_START_TARGET`) VALUES
('4202', 'SVR', 'xbrl报送投资者持有信息', NULL, 'xbrlSendInvestorsHoldInfoRegistService', 2, 'system', NULL, now(), 'system', now(), null);
INSERT INTO `sys_batch_task`(`TASK_ID`, `SYSTEM_CODE`, `TASK_NAME`, `FTASK_ID`, `BEAN_NAME`, `TASK_PRI`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `IS_BATCH_START_TARGET`) VALUES
('4203', 'SVR', 'xbrl报送投资者明细信息', NULL, 'xbrlSendEquityBusiInfoService', 3, 'system', NULL, now(), 'system', now(), null);
INSERT INTO `sys_batch_task`(`TASK_ID`, `SYSTEM_CODE`, `TASK_NAME`, `FTASK_ID`, `BEAN_NAME`, `TASK_PRI`, `CREATE_USER`, `CREATE_DEPT`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`, `IS_BATCH_START_TARGET`) VALUES
('4204', 'SVR', 'xbrl反馈信息获取', NULL, 'ChbFeedBackXbrlRunner', 3, 'system', NULL, now(), 'system', now(), null);


DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_XBRL_BACKSTATUS';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_XBRL_BACKSTATUS', 'SVR', 'xbrl报送状态', null, '02', 'system', null, now(), 'system', now());
DELETE FROM sys_datadict_list WHERE DICT_CODE = 'SVR_XBRL_BACKSTATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '00', 'xbrl发送中', 0, '0', 'system', null, now(), 'system', now(), null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '01', 'xbrl通过', 1, '0', 'system', null, now(), 'system', now(), null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '02', 'xbrl校验失败', 2, '0', 'system', null, now(), 'system', now(), null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '03', '中债通过', 3, '0', 'system', null, now(), 'system', now(), null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '04', '中债退回', 4, '0', 'system', null, now(), 'system', now(), null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_XBRL_BACKSTATUS', '05', 'xbrl异常反馈', 6, '0', 'system', null, now(), 'system', now(), null);


--DROP TABLE pbc_prd_deal;
CREATE TABLE pbc_prd_deal (
  SEQNO char(36),
  TRADE_ID varchar2(50),
  FPROD_ID varchar2(50),
  PROD_ID varchar2(150),
  ISSUE_PARTY_ID varchar2(50),
  INDENTIFY_MARK varchar2(50),
  BUY_PLACE varchar2(50),
  TRADE_TYPE varchar2(50),
  SETTDATE_DATE date,
  PAY_DATE date,
  INTEREST_DATE date,
  INT_AMT NUMBER(30,2),
  TRADE_PRINCE NUMBER(30,2),
  UNIT_BONUS_AMOUNT NUMBER(30,2),
  REDEMPTION_FEE NUMBER(30,2),
  CCY varchar2(50),
  STATUS varchar2(50),
  ORG_CODE varchar2(20),
  DEPT_CODE varchar2(20),
  DATA_SOURCE varchar2(50),
  REMARK varchar2(600),
  REV_DATE date,
  UPDATE_USER varchar2(20),
  UPDATE_TIME TIMESTAMP,
  CREATE_USER varchar2(20),
  CREATE_TIME TIMESTAMP,
  INTEREST_CCY varchar2(50),
  CUSTOMER_TYPE varchar2(50),
  CUST_COUNTS NUMBER(10,0),
  TRADE_COST NUMBER(30,2),
  ISAGENCY char(2),
  TRADE_QTY NUMBER(31,5),
  constraint PK_pbc_prd_deal PRIMARY KEY(PROD_ID,SETTDATE_DATE)
);
  COMMENT ON COLUMN pbc_prd_deal.SEQNO IS '交易编号';
  COMMENT ON COLUMN pbc_prd_deal.TRADE_ID IS '上游系统交易编码';
  COMMENT ON COLUMN pbc_prd_deal.FPROD_ID IS '父产品代码';
  COMMENT ON COLUMN pbc_prd_deal.PROD_ID IS '产品代码';
  COMMENT ON COLUMN pbc_prd_deal.ISSUE_PARTY_ID IS '发行机构代码';
  COMMENT ON COLUMN pbc_prd_deal.INDENTIFY_MARK IS '识别标识';
  COMMENT ON COLUMN pbc_prd_deal.BUY_PLACE IS '销售区域';
  COMMENT ON COLUMN pbc_prd_deal.TRADE_TYPE IS '交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)';
  COMMENT ON COLUMN pbc_prd_deal.INT_AMT IS '利息金额';
  COMMENT ON COLUMN pbc_prd_deal.TRADE_PRINCE IS '交易本金(赎回时存净金额=交易本金-赎回费)';
  COMMENT ON COLUMN pbc_prd_deal.UNIT_BONUS_AMOUNT IS '单位分红金额';
  COMMENT ON COLUMN pbc_prd_deal.REDEMPTION_FEE IS  '赎回费';
  COMMENT ON COLUMN pbc_prd_deal.CCY IS '币种';
  COMMENT ON COLUMN pbc_prd_deal.STATUS IS '数据状态(新建、修改、删除)';
  COMMENT ON COLUMN pbc_prd_deal.ORG_CODE IS '机构代码';
  COMMENT ON COLUMN pbc_prd_deal.DEPT_CODE IS '部门代码';
  COMMENT ON COLUMN pbc_prd_deal.DATA_SOURCE IS '数据来源';
  COMMENT ON COLUMN pbc_prd_deal.UPDATE_USER IS '更新人';
  COMMENT ON COLUMN pbc_prd_deal.UPDATE_TIME IS '更新时间';
  COMMENT ON COLUMN pbc_prd_deal.CREATE_USER IS '创建人';
  COMMENT ON COLUMN pbc_prd_deal.CREATE_TIME IS '创建时间';
  COMMENT ON COLUMN pbc_prd_deal.INTEREST_CCY IS '收益币种';
  COMMENT ON COLUMN pbc_prd_deal.CUSTOMER_TYPE IS '客户类型';
  COMMENT ON COLUMN pbc_prd_deal.CUST_COUNTS IS '保有客户数';
  COMMENT ON COLUMN pbc_prd_deal.TRADE_COST IS '交易成本';
  COMMENT ON COLUMN pbc_prd_deal.ISAGENCY IS '是否代销';
  COMMENT ON COLUMN pbc_prd_deal.TRADE_QTY IS '交易份额';
  COMMENT ON TABLE pbc_prd_deal IS '人行产品交易维护表';


-- 增加批处理
DELETE FROM sys_batch_task WHERE TASK_ID = '3331';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES('3331', 'SVR', '产品交易信息接口数据接入', NULL, 'svrProdDealInterfTaskService2', 3000, 'system', NULL, sysdate, 'system', sysdate, NULL);

-- 接口配置数据
DELETE FROM chb_interf_config WHERE INTERF_ID = 'PBC_PRD_DEAL';
INSERT INTO chb_interf_config (INTERF_ID, LAST_UPDATETIME, REMARK, INTERF_NAME, CONTENT_TYPE, CONTENT_SRC, REF_REPORT, SYS_SCOPE, STATUS, COVER_FLAG) VALUES('PBC_PRD_DEAL', sysdate, '投资者交易信息接口数据', NULL, 'I', 'T', 'svrProdPositionTaskService;svrProdRaiseRegTaskService;svrProdEndRegTaskService;svrProdDurationRegTaskService;svrMonthReportTaskService;svrPbcReportTaskService;svrDataCheckCBService', '110', 'E', 'Y');

drop table if exists CHB_ASSETS_ELEMENTS_SUB;
create table CHB_ASSETS_ELEMENTS_SUB(
	REGIST_SEQNO VARCHAR(36) NOT NULL comment '主键uuid',
	STATUS_BATCH VARCHAR(2) NOT NULL comment '数据批次',
	ASSET_ID VARCHAR(50) NOT NULL comment '理财产品编号',
	ASSET_NAME VARCHAR(200)  comment '投资标的名称',
	INFORMATION_ASSETS_ID VARCHAR(200) comment '资管资产类型代码',
	ASSET_FLAG VARCHAR(2) comment '非标准债权资产标志',
	FINANCIER_NAME VARCHAR(200) comment '融资人名称',
	INDUSTRY_ID VARCHAR(50) comment '行业代码',
	CCY VARCHAR(50) comment '币种代码',
	HOLD_AMOUNT DECIMAL(30,8) comment '持仓金额',
	ON_PASSAGE_AMT DECIMAL(30,8) comment '在途资金金额',
	HOLD_QTY_AMOUNT DECIMAL(30,8) comment '折人民币持仓金额',
	BONDSMAN VARCHAR(200) comment '担保人名称',
	FUNDS_USE_REMARK VARCHAR(500) comment '资金用途描述',
	PAYMENT_REMARK VARCHAR(500) comment '还款来源描述',
	ORG_ID VARCHAR(20) comment '机构编号',
	BANK_MARK VARCHAR(20) comment '银行标识号',
	DATE_STATUS DATE comment '数据时点',
	GROUP_ID VARCHAR(10) comment '组别',
	CREATE_USER VARCHAR(20) comment '创建人',
	ORG_CODE VARCHAR(20) comment '机构代码',
	CREATE_DEPT VARCHAR(20) comment '创建部门',
	CREATE_TIME TIMESTAMP(6) comment '创建时间',
	UPDATE_USER VARCHAR(20) comment '更新人',
	UPDATE_TIME TIMESTAMP(6) comment '更新时间',
	PRIMARY KEY (REGIST_SEQNO)
)comment '理财产品资产要素登记表';


					




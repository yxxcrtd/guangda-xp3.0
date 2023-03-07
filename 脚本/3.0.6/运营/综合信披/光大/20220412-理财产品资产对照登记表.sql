drop table  if exists CHB_PRODUCTS_ASSET_COMPARE;
create table CHB_PRODUCTS_ASSET_COMPARE(
	REGIST_SEQNO VARCHAR(36) NOT NULL comment '主键uuid',
	STATUS_BATCH VARCHAR(2) NOT NULL comment '数据批次',
	ACCOUNT VARCHAR(50) NOT NULL comment '理财产品编号',
	ASSET_ID VARCHAR(50) NOT NULL comment '投资标的编号',
	HOLD_QTY_AMOUNT DECIMAL(30,8) comment '折人民币持仓金额',
	HOLD_SHARES DECIMAL(30,8) comment '持仓份额',
	BANK_MARK VARCHAR(20) comment '银行标识号',
	DATE_STATUS DATE comment '数据时点',
	GROUP_ID VARCHAR(10) comment '组别',
	STATUS VARCHAR(10) comment '数据状态',
	CREATE_USER VARCHAR(20) comment '创建人',
	ORG_CODE VARCHAR(20) comment '机构代码',
	CREATE_DEPT VARCHAR(20) comment '创建部门',
	CREATE_TIME TIMESTAMP(6) comment '创建时间',
	UPDATE_USER VARCHAR(20) comment '更新人',
	UPDATE_TIME TIMESTAMP(6) comment '更新时间',
	PRIMARY KEY (REGIST_SEQNO)
)comment '理财产品资产对照登记表';




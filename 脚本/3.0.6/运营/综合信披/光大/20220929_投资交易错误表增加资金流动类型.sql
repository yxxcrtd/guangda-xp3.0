ALTER TABLE `chb_invest_trade_errodata`
	ADD COLUMN `CUR_ASSET_TYPE` VARCHAR(50) NULL COMMENT '资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出' AFTER `ASSET_CODE`;

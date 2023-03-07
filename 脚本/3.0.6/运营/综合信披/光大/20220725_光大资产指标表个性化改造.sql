ALTER TABLE chb_assets_basis_data ADD UNIT_VAL_NET decimal(30,14) DEFAULT NULL COMMENT '单位净价';
ALTER TABLE chb_assets_basis_data ADD UNIT_VAL_TOTAL decimal(30,14) DEFAULT NULL COMMENT '单位全价';

/***汇率信息处理**/
UPDATE chb_datacheck_rule SET STATUS = 'D' WHERE SRCPROPERTY IN ('OPEN_PRICE','CLOSE_PRICE','HIGH_PRICE','LOW_PRICE');
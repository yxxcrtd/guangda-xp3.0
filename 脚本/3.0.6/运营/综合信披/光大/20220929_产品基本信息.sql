
-- 关闭(业绩比较基准说明必须填写白名单中的文字)
UPDATE `chb_datacheck_rule_inv` SET `STATUS`='D' WHERE `CHECKRULEID`='CHB_PRD_BASEINFO-CPJBXX-20220514-0001-51';

-- 其他规律开放周期(长度小于5)
UPDATE `chb_datacheck_rule_inv` SET `OBJVALUE`='5' WHERE `CHECKRULEID`='CHB_PRD_BASEINFO-CPJBXX-20220514-0001-28';


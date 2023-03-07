-- RHPTSJ-149
update chb_datacheck_rule set status ='D'
where CHECKRULEID in ('1A48C2BF-0EBE-0CFD-E053-035AA8C0F564', '9A48C2BF-0E7F-0CFD-E053-035AA8C0F562',
                      '1A48C2BF-0EBE-0CFD-E053-035AA8C0F565');

update chb_datacheck_rule set REMARK = '贷款产品类别只能为“F99-其他贷款”',NULLABLE = 'N'
where CHECKRULEID ='9A48C2BF-0EA9-0CFD-E053-035AA8C0F562';
update chb_datacheck_rule set REMARK = '贷款产品类别不能为“F99-其他贷款”'
where CHECKRULEID ='9A48C2BF-0EAB-0CFD-E053-035AA8C0F562';
-- HXRHPTSJ-32
update sys_datadict_list set ITEM_VALUE ='境外' where DICT_CODE='SVR_INDUSTRY_DOOR_CODE' and ITEM_CODE ='2';

-- HXRHPTSJ-60
update chb_datacheck_rule
set OBJVALUE ='CURRENCY_PBC'
where CHECKRULEID in ('9A48C2BF-0EDE-0CFD-E053-035AA8C0F562',
                      'CHB_LOAN_DETAIL_INFO-20221106-1106-50',
                      'CHB_LOAN_DETAIL_INFO-20221106-1106-59',
                      'CHB_LOAN_DETAIL_INFO-20221106-1106-86');
					  
-- HXRHPTSJ-76
update chb_datacheck_condition set STATUS ='D' where CHECKRULEID ='9A48C2BF-0EA3-0CFD-E053-035AA8C0F562';
update chb_datacheck_rule set STATUS ='D' where CHECKRULEID ='9A48C2BF-0EA3-0CFD-E053-035AA8C0F562';
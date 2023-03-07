-- 删除错误的基础资产出让机构注册地区(市)
delete from sys_datadict_list where DICT_CODE='AREA_TYPE2' and ITEM_CODE='120300';
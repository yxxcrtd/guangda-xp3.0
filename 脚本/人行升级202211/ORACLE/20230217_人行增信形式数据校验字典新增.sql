delete from sys_datadict where DICT_CODE='PBC_CREDIT_MODE';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PBC_CREDIT_MODE', 'PBC', '人行增信形式', null, '1', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='PBC_CREDIT_MODE';
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_CREDIT_MODE', '1', '内部增级', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PBC_CREDIT_MODE', '2', '外部增级', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
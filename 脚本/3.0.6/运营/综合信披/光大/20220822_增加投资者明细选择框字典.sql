delete from  sys_datadict where DICT_CODE='EQBUSI_SELECT_COLUMN';
INSERT INTO sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EQBUSI_SELECT_COLUMN', 'REP', '选择框（投资者明细）', null, '02', 'system', null, now(), 'system', now());

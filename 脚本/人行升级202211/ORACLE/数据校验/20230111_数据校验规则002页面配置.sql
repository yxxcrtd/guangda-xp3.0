--
delete from sys_page where PAGE_ID ='PbcCheckRuleInfo002';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcCheckRuleInfo002', 'SVR', '/svreport/checkRule/PbcCheckRuleInfo002', 'svreport/pbc/checkRuleInfo/PbcCheckRuleInfo002', null, null, null, '02', '02', '数据检查规则配置新增', 'system', null, TIMESTAMP '2022-11-29 12:00:00', 'system', TIMESTAMP '2022-11-29 12:00:00');

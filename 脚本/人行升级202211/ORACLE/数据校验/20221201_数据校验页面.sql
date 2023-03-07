delete from sys_menu where MENU_ID ='REPORT_CHECK_001_004_003';
INSERT INTO sys_menu
(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('REPORT_CHECK_001_004_003', 'SVR', NULL, 'REPORT_CHECK_001_004', 'PbcCheckRuleInfo001', 3, '2', '数据检查规则配置', 'system', NULL, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID ='PbcCheckRuleInfo001' and MENU_ID ='REPORT_CHECK_001_004_003';
INSERT INTO sys_page
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('PbcCheckRuleInfo001', 'SVR', '/svreport/checkRule/PbcCheckRuleInfo001', 'svreport/pbc/checkRuleInfo/PbcCheckRuleInfo001', 'REPORT_CHECK_001_004_003', NULL, NULL, '02', '02', '数据检查规则配置', 'system', NULL, sysdate, 'system', sysdate);

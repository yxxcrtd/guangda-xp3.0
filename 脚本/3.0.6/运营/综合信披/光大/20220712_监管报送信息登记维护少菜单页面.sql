
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbConsignorDetailSub001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbConsignorDetailSub001', 'SVR', '/svreport/gd/report/chbconsignordetailsub/ChbConsignorDetailSub001', 'svreport/gd/report/chbconsignordetailsub/ChbConsignorDetailSub001', 'REPORT_REGIST_001_007_001', null, null, '02', '01', '委托人明细数据登记表', 'system', null, NOW(), 'system', NOW());

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbProductsBalanceSub001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbProductsBalanceSub001', 'SVR', '/svreport/gd/report/chbproductsbalancesub/ChbProductsBalanceSub001', 'svreport/gd/report/chbproductsbalancesub/ChbProductsBalanceSub001', 'REPORT_REGIST_001_006_003', null, null, '02', '01', '理财产品账户余额登记表', 'system', null, NOW(), 'system', NOW());

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbProductsDealSub001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbProductsDealSub001', 'SVR', '/svreport/gd/report/chbproductsdealsub/ChbProductsDealSub001', 'svreport/gd/report/chbproductsdealsub/ChbProductsDealSub001', 'REPORT_REGIST_001_006_002', null, null, '02', '01', '理财产品交易明细登记表', 'system', null, NOW(), 'system', NOW());


DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_REGIST_001_007_001';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_REGIST_001_007_001', 'SVR', NULL, 'REPORT_REGIST_001_007', 'ChbConsignorDetailSub001', '1', '2', '委托人明细数据', 'system', NULL, NOW(), 'system', NOW());

DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_REGIST_001_006_002';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_REGIST_001_006_002', 'SVR', NULL, 'REPORT_REGIST_001_006', 'ChbProductsDealSub001', '2', '2', '理财产品交易明细表', 'system', NULL, NOW(), 'system', NOW());

DELETE FROM sys_menu WHERE MENU_ID = 'REPORT_REGIST_001_006_003';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_REGIST_001_006_003', 'SVR', NULL, 'REPORT_REGIST_001_006', 'ChbProductsBalanceSub001', '3', '2', '理财产品账户余额表', 'system', NULL, NOW(), 'system', NOW());
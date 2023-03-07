DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbFinanceProductsSub001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbFinanceProductsSub001', 'SVR', '/svreport/gd/report/chbfinanceproductssub/ChbFinanceProductsSub001', 'svreport/gd/report/chbfinanceproductssub/ChbFinanceProductsSub001', 'REPORT_REGIST_001_006_001', null, null, '02', '01', '理财产品登记表', 'system', null, '2022-03-29 14:00:00', 'system', '2022-03-29 14:00:00');

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbProductsAssetCompare001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbProductsAssetCompare001', 'SVR', '/svreport/gd/report/chbproductsassetcompare/ChbProductsAssetCompare001', 'svreport/gd/report/chbproductsassetcompare/ChbProductsAssetCompare001', 'REPORT_REGIST_001_006_004', null, null, '02', '01', '理财产品资产对照表', 'system', null, '2022-03-29 14:00:00', 'system', '2022-03-29 14:00:00');

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ChbAssetsElementsSub001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ChbAssetsElementsSub001', 'SVR', '/svreport/gd/report/chbassetselementssub/ChbAssetsElementsSub001', 'svreport/gd/report/chbassetselementssub/ChbAssetsElementsSub001', 'REPORT_REGIST_001_006_005', null, null, '02', '01', '理财产品资产要素登记表', 'system', null, '2022-03-29 14:00:00', 'system', '2022-03-29 14:00:00');

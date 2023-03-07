-- 人行报送文件管理（大数据采集平台）菜单调整
delete from sys_menu  where MENU_ID='REPORT_MANAGE_001_001_003';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_MANAGE_001_001_003', 'REP', null, 'REPORT_MANAGE_001_001', 'PbcDataCollectDatFile', 3, '2', '人行报送文件管理（大数据采集平台）', 'system', null, now(), 'system', now());

-- 表内资产数据维护
delete from sys_menu where PAGE_ID = 'PbcFinInstitutionAssetInfo001';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_MAINTAIN_001_002_012', 'REP', null, 'REPORT_MAINTAIN_001_002', 'PbcFinInstitutionAssetInfo001', 12, '2', '表内资产数据维护', 'system', null, '2022-11-03 18:00:00', 'system', '2022-11-03 18:00:00');


DELETE FROM sys_page WHERE PAGE_ID IN ('PbcFinInstitutionAssetInfo001', 'PbcFinInstitutionAssetInfo002');
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcFinInstitutionAssetInfo001', 'SVR', '/svreport/fininstitution/PbcFinInstitutionAssetInfo001', 'svreport/fininstitution/PbcFinInstitutionAssetInfo001', 'REPORT_MAINTAIN_001_002_012', null, null, '02', '01', '表内资产数据维护', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00');
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcFinInstitutionAssetInfo002', 'SVR', '/svreport/fininstitution/PbcFinInstitutionAssetInfo002', 'svreport/fininstitution/PbcFinInstitutionAssetInfo002', null, null, null, '02', '01', '表内资产数据维护新建', 'system', null, '2022-11-23 18:00:00', 'system', '2022-11-23 18:00:00');


-- 删除多余菜单
delete from sys_menu where sys_menu.MENU_ID in ('REPORT_REGIST_001_003_015','REPORT_REGIST_001_003_016','REPORT_REGIST_001_003_017',
                                               'REPORT_REGIST_001_003_018','REPORT_REGIST_001_003_019','REPORT_REGIST_001_003_020',
                                               'REPORT_REGIST_001_003_021','REPORT_REGIST_001_003_022','REPORT_REGIST_001_003_023');
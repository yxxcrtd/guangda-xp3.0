-- 菜单目录调整

delete from sys_menu_split where MENU_ID='REPORT_MANAGE_001_001_003' ;
delete from sys_menu_split where MENU_ID='REPORT_MANAGE_001_004_010' ;
INSERT INTO sys_menu_split(MENU_ID, SPLIT_ORDER_NO, SPLIT_MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_MANAGE_001_004_010', 3, '大数据采集平台', 'admin', null, '2022-11-17 09:58:02', 'admin', '2022-11-17 09:58:02');

delete from sys_menu where MENU_ID='REPORT_MANAGE_001_001_003';
delete from sys_menu where MENU_ID='REPORT_MANAGE_001_004_010';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_MANAGE_001_004_010', 'REP', null, 'REPORT_MANAGE_001_004', 'PbcDataCollectDatFile', 10, '2', '人行报送文件管理（大数据采集平台）', 'system', null, '2022-11-17 09:36:33', 'system', '2022-11-17 09:36:33');


delete from sys_page where PAGE_ID='PbcDataCollectDatFile';
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PbcDataCollectDatFile', 'REP', '/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile', 'report/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile', 'REPORT_MANAGE_001_004_010', null, null, '02', '02', '人行报送文件管理（大数据采集平台）', 'system', null, '2022-11-17 09:36:33', 'system', '2022-11-17 09:36:33');

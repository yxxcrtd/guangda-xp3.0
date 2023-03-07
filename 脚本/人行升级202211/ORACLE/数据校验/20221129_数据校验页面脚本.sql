DELETE FROM SYS_PAGE WHERE PAGE_ID = 'PbcDataCollectDatFile002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PbcDataCollectDatFile002', 'REP', '/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile002', 'report/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile002', '', NULL, NULL, '02', '02', '人行报送文件管理', 'system', NULL, sysdate, 'system', sysdate);

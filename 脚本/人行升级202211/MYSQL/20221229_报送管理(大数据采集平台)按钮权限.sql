-- 页面按钮权限
DELETE FROM sys_page_element WHERE PAGE_ID = 'PbcDataCollectDatFile';

INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcDataCollectDatFile', 'PbcDataCollectDatFile_btn_sendFileDownLoad', '01', '01', null, '下载DAT文件', 'system', null, '2022-12-29 18:00:00', 'system', '2022-12-29 18:00:00', 'COM', null);

INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcDataCollectDatFile', 'PbcDataCollectDatFile_btn_staticTemplateImport', '01', '01', null, '导入人行EXCEL', 'system', null, '2022-12-29 18:00:00', 'system', '2022-12-29 18:00:00', 'COM', null);

INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcDataCollectDatFile', 'PbcDataCollectDatFile_btn_search', '01', '01', null, '查询', 'system', null, '2022-12-29 18:00:00', 'system', '2022-12-29 18:00:00', 'COM', null);

INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcDataCollectDatFile', 'PbcDataCollectDatFile_btn_batch', '01', '01', null, '预览', 'system', null, '2022-12-29 18:00:00', 'system', '2022-12-29 18:00:00', 'COM', null);

INSERT INTO sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('PbcDataCollectDatFile', 'PbcDataCollectDatFile_btn_delete', '01', '01', null, '删除', 'system', null, '2022-12-29 18:00:00', 'system', '2022-12-29 18:00:00', 'COM', null);

/**ZG05按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG05PbcColPrdAssetDebtSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) 
VALUES 
('ZG05PbcColPrdAssetDebtSub', 'ZG05PbcColPrdAssetDebtSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG05PbcColPrdAssetDebtSub', 'ZG05PbcColPrdAssetDebtSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null),
('ZG05PbcColPrdAssetDebtSub', 'ZG05PbcColPrdAssetDebtSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG05PbcColPrdAssetDebtSub', 'ZG05PbcColPrdAssetDebtSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null),
('ZG05PbcColPrdAssetDebtSub', 'ZG05PbcColPrdAssetDebtSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

/**ZG07按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG07PbcColLoanDetailSub001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) 
VALUES 
('ZG07PbcColLoanDetailSub001', 'ZG07PbcColLoanDetailSub001_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG07PbcColLoanDetailSub001', 'ZG07PbcColLoanDetailSub001_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null),
('ZG07PbcColLoanDetailSub001', 'ZG07PbcColLoanDetailSub001_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG07PbcColLoanDetailSub001', 'ZG07PbcColLoanDetailSub001_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null),
('ZG07PbcColLoanDetailSub001', 'ZG07PbcColLoanDetailSub001_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

/**ZG11按钮配置**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ZG11CompanyBondInfoSub';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID) 
VALUES 
('ZG11CompanyBondInfoSub', 'ZG11CompanyBondInfoSub_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG11CompanyBondInfoSub', 'ZG11CompanyBondInfoSub_btn_checkData', '01', '01', null, '数据检查', 'system', null, now(), 'system', now(), 'C', null),
('ZG11CompanyBondInfoSub', 'ZG11CompanyBondInfoSub_btn_manual', '01', '01', null, '手工处理', 'system', null, now(), 'system', now(), 'C', null),
('ZG11CompanyBondInfoSub', 'ZG11CompanyBondInfoSub_btn_pbcexport', '01', '01', null, '导出人行Excel', 'system', null, now(), 'system', now(), 'C', null),
('ZG11CompanyBondInfoSub', 'ZG11CompanyBondInfoSub_btn_search', '01', '01', null, '查询', 'system', null, now(), 'system', now(), 'C', null);

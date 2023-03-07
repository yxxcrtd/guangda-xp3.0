-- 菜单目录
delete from sys_menu  where MENU_ID='REPORT_MANAGE_001_001_003';
update sys_menu
set MENU_ID='REPORT_MANAGE_001_001_003' , PARENT_MENU_ID='REPORT_MANAGE_001_001',ORDER_NO=3
where sys_menu.PAGE_ID='PbcDataCollectDatFile';

update sys_page
set MENU_ID='REPORT_MANAGE_001_001_003'
where sys_page.PAGE_ID='PbcDataCollectDatFile';

delete from sys_menu_split where MENU_ID='REPORT_MANAGE_001_004_009' ;
delete from sys_menu_split where MENU_ID='REPORT_MANAGE_001_001_003' ;
INSERT INTO sys_menu_split(MENU_ID, SPLIT_ORDER_NO, SPLIT_MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_MANAGE_001_001_003', 1, '大数据采集平台', 'admin', null, sysdate, 'admin', sysdate);



delete from sys_menu_split where MENU_ID='REPORT_REGIST_001_003_014';
delete from sys_menu_split where MENU_ID='REPORT_REGIST_001_008_001';
INSERT INTO sys_menu_split(MENU_ID, SPLIT_ORDER_NO, SPLIT_MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_001', 1, '大数据采集平台', 'admin', null, sysdate, 'admin', sysdate);

delete
from sys_menu
where sys_menu.PAGE_ID in
      ('ZG01PbcPrdBaseinfoSub', 'ZG02PbcPrdInitRaiseSub', 'ZG03PbcPrdBaseinfoEndSub', 'ZG04PbcPrdCashRaiseSub',
       'ZG05PbcColPrdAssetDebtSub', 'ZG06PbcAssetSusfructSub',
       'ZG07PbcColLoanDetailSub001', 'ZG08SpeCounterQuity', 'ZG09PbcAssetDebtTermSub',
       'ZG10PbcColAssetConfigurationSub', 'ZG11CompanyBondInfoSub');
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_001', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG01PbcPrdBaseinfoSub', 1, '2', 'ZG01 资管产品基本信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_002', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG02PbcPrdInitRaiseSub', 2, '2', 'ZG02 资管产品初始募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_003', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG03PbcPrdBaseinfoEndSub', 3, '2', 'ZG03 资管产品终止信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_004', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG04PbcPrdCashRaiseSub', 4, '2', 'ZG04 资管产品存续期募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_005', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG05PbcColPrdAssetDebtSub', 5, '2', 'ZG05 资管产品资产负债信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_006', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG06PbcAssetSusfructSub', 6, '2', 'ZG06 资产收益权明细信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_007', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG07PbcColLoanDetailSub001', 7, '2', 'ZG07 除回购和拆借外贷款明细信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_008', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG08SpeCounterQuity', 8, '2', 'ZG08 特定目的载体交易对手明细信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_009', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG09PbcAssetDebtTermSub', 9, '2', 'ZG09 资产负债剩余期限信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_010', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG10PbcColAssetConfigurationSub', 10, '2', 'ZG10 债券等资产配置情况信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008_011', 'SVR', null, 'REPORT_REGIST_001_008', 'ZG11CompanyBondInfoSub', 11, '2', 'ZG11 企业债券分行业、企业规模情况信息', 'system', null, sysdate, 'system', sysdate);


delete from sys_page where PAGE_ID in ('ZG01PbcPrdBaseinfoSub', 'ZG02PbcPrdInitRaiseSub', 'ZG03PbcPrdBaseinfoEndSub', 'ZG04PbcPrdCashRaiseSub',
       'ZG05PbcColPrdAssetDebtSub', 'ZG06PbcAssetSusfructSub',
       'ZG07PbcColLoanDetailSub001', 'ZG08SpeCounterQuity', 'ZG09PbcAssetDebtTermSub',
       'ZG10PbcColAssetConfigurationSub', 'ZG11CompanyBondInfoSub');

INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG01PbcPrdBaseinfoSub', 'SVR', '/pbc/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'report/pbc/classic/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub', 'REPORT_REGIST_001_008_001', null, null, '01', '01', 'ZG01 资管产品基本信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG02PbcPrdInitRaiseSub', 'SVR', '/pbc/zg02prdinitraisesub/ZG02PbcPrdInitRaiseSub', 'report/pbc/classic/zg02prdinitraisesub/ZG02PbcPrdInitRaiseSub', 'REPORT_REGIST_001_008_002', null, null, '02', '02', 'ZG02 资管产品初始募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG03PbcPrdBaseinfoEndSub', 'SVR', '/pbc/zg03prdinfoendsub/ZG03PbcPrdBaseinfoEndSub', 'report/pbc/classic/zg03prdinfoendsub/ZG03PbcPrdBaseinfoEndSub', 'REPORT_REGIST_001_008_003', null, null, '02', '02', 'ZG03 资管产品终止信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG04PbcPrdCashRaiseSub', 'SVR', '/pbc/zg04prdcashraisesub/ZG04PbcPrdCashRaiseSub', 'report/pbc/classic/zg04prdcashraisesub/ZG04PbcPrdCashRaiseSub', 'REPORT_REGIST_001_008_004', null, null, '02', '02', 'ZG04 资管产品存续期募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG05PbcColPrdAssetDebtSub', 'SVR', '/pbc/zg05prdassetdebt/ZG05PbcColPrdAssetDebtSub', 'report/pbc/classic/zg05prdassetdebt/ZG05PbcColPrdAssetDebtSub', 'REPORT_REGIST_001_008_005', null, null, '01', '01', 'ZG05 资管产品资产负债信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG06PbcAssetSusfructSub', 'SVR', '/pbc/zg06assetusufructsub/ZG06PbcAssetSusfructSub', 'report/pbc/classic/zg06assetusufructsub/ZG06PbcAssetSusfructSub', 'REPORT_REGIST_001_008_006', null, null, '02', '02', 'ZG02 资管产品初始募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG07PbcColLoanDetailSub001', 'SVR', '/pbc/zg07pbccolloandetailsub/ZG07PbcColLoanDetailSub001', 'report/pbc/classic/zg07pbccolloandetailsub/ZG07PbcColLoanDetailSub001', 'REPORT_REGIST_001_008_007', null, null, '01', '01', 'ZG07 除回购和拆借外贷款明细信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG08SpeCounterQuity', 'SVR', '/pbc/zg08specounterequity/ZG08SpeCounterQuity', 'report/pbc/classic/zg08specounterequity/ZG08SpeCounterQuity', 'REPORT_REGIST_001_008_008', null, null, '01', '01', 'ZG08 特定目的载体交易对手明细信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG09PbcAssetDebtTermSub', 'SVR', '/pbc/zg09assetdebtterm/ZG09PbcAssetDebtTermSub', 'report/pbc/classic/zg09assetdebtterm/ZG09PbcAssetDebtTermSub', 'REPORT_REGIST_001_008_009', null, null, '02', '02', 'ZG10 债券等资产配置情况信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG10PbcColAssetConfigurationSub', 'SVR', '/pbc/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub', 'report/pbc/classic/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub', 'REPORT_REGIST_001_008_010', null, null, '02', '02', 'ZG02 资管产品初始募集信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_page(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZG11CompanyBondInfoSub', 'SVR', '/pbc/zg11companybondinfosub/ZG11CompanyBondInfoSub', 'report/pbc/classic/zg11companybondinfosub/ZG11CompanyBondInfoSub', 'REPORT_REGIST_001_008_011', null, null, '01', '01', 'ZG11 企业债券分行业、企业规模情况信息', 'system', null, sysdate, 'system', sysdate);


delete from sys_menu where MENU_ID='REPORT_REGIST_001_008';
INSERT INTO sys_menu(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_REGIST_001_008', 'REP', null, 'REPORT_REGIST_001', null, 8, '2', '人行报送信息登记', 'system', null, sysdate, 'system', sysdate);
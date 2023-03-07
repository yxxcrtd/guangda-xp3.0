DROP TABLE IF EXISTS  ZG05_PBC_COL_REPORT;
create table ZG05_PBC_COL_REPORT
(
    REPORTUUID             char(36)     not null comment '主键'
        primary key,
    TERMID                 varchar(20)  null comment '期数',
    REPORTTYPE             varchar(20)  null comment '报表类型:',
    STARTDATE              date         null  comment '开始日期:',
    ENDDATE                date         null  comment '结束日期:',
    ISSUE_PARTY_ID         varchar(50)  null comment '发行机构代码',
    ISSUE_PARTY_NAME       varchar(50)  null comment '发行机构名称',
    REPORT_DATE            date         null comment '制表日期',
    SUBMIT_TIME            date         null comment '报送日期',
    CHB_SUBMIT_DEADLINE    date         null comment '中债报送截止日',
    SUBMIT_DEADLINE        date         null comment '行内报送截止日',
    SUBMIT_FEEDBACK_STATUS varchar(50)  null comment '返回信息',
    STATUS                 varchar(50)  null comment '有效状态',
    SEND_STATUS            varchar(20)  null comment '报送状态',
    STATUS_DATE            date         null comment '数据日期',
    CREATE_USER            varchar(20)  null comment '创建人',
    CREATE_TIME            datetime     null comment '创建时间',
    UPDATE_USER            varchar(20)  null comment '更新人',
    UPDATE_TIME            datetime     null comment '更新时间',
    ORG_CODE               varchar(20)  null comment '机构代码',
    DEPT_CODE              varchar(20)  null comment '部门代码',
    CREATE_DEPT            varchar(32)  null comment '创建部门',
    PBC_PROD_ID            varchar(50)  null comment '人行产品代码',
    PROD_ID                varchar(50)  null comment '内部产品代码',
    CCY                    varchar(20)  null comment '币种',
    STATUS_TYPE            varchar(20)  null comment '数据类型',
    A0000     decimal(20,2)  null  comment 'A0000_资产合计',

    A1000     decimal(20,2)  null  comment 'A1000_现金',

    A2000     decimal(20,2)  null  comment 'A2000_存款',

    A2100     decimal(20,2)  null  comment 'A2100_境内存款',

    A2110     decimal(20,2)  null  comment 'A2110_活期存款',

    A2120     decimal(20,2)  null  comment 'A2120_定期和其他存款',

    A2200     decimal(20,2)  null  comment 'A2200_境外存款',

    A2210     decimal(20,2)  null  comment 'A2210_活期存款',

    A2220     decimal(20,2)  null  comment 'A2220_定期和其他存款',

    A3000     decimal(20,2)  null  comment 'A3000_可转让存单',

    A3100     decimal(20,2)  null  comment 'A3100_同业存单',

    A3200     decimal(20,2)  null  comment 'A3200_大额存单',

    A4000     decimal(20,2)  null  comment 'A4000_债务证券',

    A4100     decimal(20,2)  null  comment 'A4100_政府债券',

    A4200     decimal(20,2)  null  comment 'A4200_中央银行债券',

    A4300     decimal(20,2)  null  comment 'A4300_政策性金融债券',

    A4400     decimal(20,2)  null  comment 'A4400_其他金融债券',

    A4500     decimal(20,2)  null  comment 'A4500_企业债券',

    A4600     decimal(20,2)  null  comment 'A4600_票据',

    A4700     decimal(20,2)  null  comment 'A4700_资产支持证券',

    A4800     decimal(20,2)  null  comment 'A4800_境外债券',

    A5000     decimal(20,2)  null  comment 'A5000_贷款',

    A5100     decimal(20,2)  null  comment 'A5100_除回购和拆借外贷款',

    A5200     decimal(20,2)  null  comment 'A5200_回购和拆借',

    A5210     decimal(20,2)  null  comment 'A5210_住户',

    A5220     decimal(20,2)  null  comment 'A5220_广义政府',

    A5230     decimal(20,2)  null  comment 'A5230_非金融企业',

    A5240     decimal(20,2)  null  comment 'A5240_银行业存款类金融机构',

    A5250     decimal(20,2)  null  comment 'A5250_银行业非存款类金融机构',

    A5260     decimal(20,2)  null  comment 'A5260_非银行业金融机构',

    A5261     decimal(20,2)  null  comment 'A5261_其中：中央交易对手方',

    A5270     decimal(20,2)  null  comment 'A5270_特定目的载体',

    A5271     decimal(20,2)  null  comment 'A5271_银行非保本理财',

    A5272     decimal(20,2)  null  comment 'A5272_信托公司资管产品',

    A5273     decimal(20,2)  null  comment 'A5273_证券公司及其子公司资管产品',

    A5274     decimal(20,2)  null  comment 'A5274_基金管理公司及其子公司专户',

    A5275     decimal(20,2)  null  comment 'A5275_期货公司及其子公司资管产品',

    A5276     decimal(20,2)  null  comment 'A5276_保险资管产品',

    A5277     decimal(20,2)  null  comment 'A5277_金融资产投资公司资管产品',

    A5278     decimal(20,2)  null  comment 'A5278_公募基金',

    A5279     decimal(20,2)  null  comment 'A5279_私募机构私募基金',

    A527a     decimal(20,2)  null  comment 'A527a_其他特定目的载体',

    A5280     decimal(20,2)  null  comment 'A5280_境外',

    A6000     decimal(20,2)  null  comment 'A6000_资产收益权',

    A6100     decimal(20,2)  null  comment 'A6100_贷款收益权',

    A6200     decimal(20,2)  null  comment 'A6200_债务证券收益权',

    A6300     decimal(20,2)  null  comment 'A6300_应收账款收益权',

    A6400     decimal(20,2)  null  comment 'A6400_其他债权收益权',

    A6500     decimal(20,2)  null  comment 'A6500_股票收益权',

    A6600     decimal(20,2)  null  comment 'A6600_其他股权收益权',

    A6700     decimal(20,2)  null  comment 'A6700_物权资产收益权',

    A7000     decimal(20,2)  null  comment 'A7000_股权及特定目的载体份额',

    A7100     decimal(20,2)  null  comment 'A7100_股票',

    A7110     decimal(20,2)  null  comment 'A7110_非金融企业股票',

    A7120     decimal(20,2)  null  comment 'A7120_金融机构股票',

    A7200     decimal(20,2)  null  comment 'A7200_特定目的载体份额',

    A7210     decimal(20,2)  null  comment 'A7210_银行非保本理财',

    A7220     decimal(20,2)  null  comment 'A7220_信托公司资管产品',

    A7230     decimal(20,2)  null  comment 'A7230_证券公司及其子公司资管产品',

    A7240     decimal(20,2)  null  comment 'A7240_基金管理公司及其子公司专户',

    A7250     decimal(20,2)  null  comment 'A7250_期货公司及其子公司资管产品',

    A7260     decimal(20,2)  null  comment 'A7260_保险资管产品',

    A7270     decimal(20,2)  null  comment 'A7270_金融资产投资公司资管产品',

    A7280     decimal(20,2)  null  comment 'A7280_公募基金',

    A7290     decimal(20,2)  null  comment 'A7290_私募机构私募基金',

    A72a0     decimal(20,2)  null  comment 'A72a0_其他特定目的载体',

    A7300     decimal(20,2)  null  comment 'A7300_其他股权',

    A7310     decimal(20,2)  null  comment 'A7310_非金融企业股权',

    A7320     decimal(20,2)  null  comment 'A7320_金融机构股权',

    A7400     decimal(20,2)  null  comment 'A7400_境外',

    A8000     decimal(20,2)  null  comment 'A8000_金融衍生品',

    A8100     decimal(20,2)  null  comment 'A8100_境内',

    A8200     decimal(20,2)  null  comment 'A8200_境外',

    A9000     decimal(20,2)  null  comment 'A9000_应收账款',

    AA000     decimal(20,2)  null  comment 'AA000_黄金',

    AB000     decimal(20,2)  null  comment 'AB000_非金融资产',

    AC000     decimal(20,2)  null  comment 'AC000_资产减值准备（减）',

    D0000     decimal(20,2)  null  comment 'D0000_负债及权益合计',

    B0000     decimal(20,2)  null  comment 'B0000_负债合计',

    B1000     decimal(20,2)  null  comment 'B1000_贷款',

    B1100     decimal(20,2)  null  comment 'B1100_除回购和拆借外贷款',

    B1200     decimal(20,2)  null  comment 'B1200_回购和拆借',

    B1210     decimal(20,2)  null  comment 'B1210_住户',

    B1220     decimal(20,2)  null  comment 'B1220_广义政府',

    B1230     decimal(20,2)  null  comment 'B1230_非金融企业',

    B1240     decimal(20,2)  null  comment 'B1240_银行业存款类金融机构',

    B1250     decimal(20,2)  null  comment 'B1250_银行业非存款类金融机构',

    B1260     decimal(20,2)  null  comment 'B1260_非银行业金融机构',

    B1261     decimal(20,2)  null  comment 'B1261_其中：中央交易对手方',

    B1270     decimal(20,2)  null  comment 'B1270_特定目的载体',

    B1271     decimal(20,2)  null  comment 'B1271_银行非保本理财',

    B1272     decimal(20,2)  null  comment 'B1272_信托公司资管产品',

    B1273     decimal(20,2)  null  comment 'B1273_证券公司及其子公司资管产品',

    B1274     decimal(20,2)  null  comment 'B1274_基金管理公司及其子公司专户',

    B1275     decimal(20,2)  null  comment 'B1275_期货公司及其子公司资管产品',

    B1276     decimal(20,2)  null  comment 'B1276_保险资管产品',

    B1277     decimal(20,2)  null  comment 'B1277_金融资产投资公司资管产品',

    B1278     decimal(20,2)  null  comment 'B1278_公募基金',

    B1279     decimal(20,2)  null  comment 'B1279_私募机构私募基金',

    B127a     decimal(20,2)  null  comment 'B127a_其他特定目的载体',

    B1280     decimal(20,2)  null  comment 'B1280_境外',

    B2000     decimal(20,2)  null  comment 'B2000_资产支持证券',

    B3000     decimal(20,2)  null  comment 'B3000_金融衍生品',

    B3100     decimal(20,2)  null  comment 'B3100_境内',

    B3200     decimal(20,2)  null  comment 'B3200_境外',

    B4000     decimal(20,2)  null  comment 'B4000_应付账款',

    B5000     decimal(20,2)  null  comment 'B5000_其他负债',

    C0000     decimal(20,2)  null  comment 'C0000_权益合计',

    C1000     decimal(20,2)  null  comment 'C1000_实收本金',

    C1100     decimal(20,2)  null  comment 'C1100_优先级',

    C1110     decimal(20,2)  null  comment 'C1110_住户',

    C1120     decimal(20,2)  null  comment 'C1120_广义政府',

    C1130     decimal(20,2)  null  comment 'C1130_非金融企业',

    C1140     decimal(20,2)  null  comment 'C1140_银行业存款类金融机构',

    C1150     decimal(20,2)  null  comment 'C1150_银行业非存款类金融机构',

    C1160     decimal(20,2)  null  comment 'C1160_非银行业金融机构',

    C1170     decimal(20,2)  null  comment 'C1170_特定目的载体',

    C1171     decimal(20,2)  null  comment 'C1171_银行非保本理财',

    C1172     decimal(20,2)  null  comment 'C1172_信托公司资管产品',

    C1173     decimal(20,2)  null  comment 'C1173_证券公司及其子公司资管产品',

    C1174     decimal(20,2)  null  comment 'C1174_基金管理公司及其子公司专户',

    C1175     decimal(20,2)  null  comment 'C1175_期货公司及其子公司资管产品',

    C1176     decimal(20,2)  null  comment 'C1176_保险资管产品',

    C1177     decimal(20,2)  null  comment 'C1177_金融资产投资公司资管产品',

    C1178     decimal(20,2)  null  comment 'C1178_公募基金',

    C1179     decimal(20,2)  null  comment 'C1179_私募机构私募基金',

    C117a     decimal(20,2)  null  comment 'C117a_其他特定目的载体',

    C1180     decimal(20,2)  null  comment 'C1180_境外',

    C1200     decimal(20,2)  null  comment 'C1200_劣后级',

    C1210     decimal(20,2)  null  comment 'C1210_住户',

    C1220     decimal(20,2)  null  comment 'C1220_广义政府',

    C1230     decimal(20,2)  null  comment 'C1230_非金融企业',

    C1240     decimal(20,2)  null  comment 'C1240_银行业存款类金融机构',

    C1250     decimal(20,2)  null  comment 'C1250_银行业非存款类金融机构',

    C1260     decimal(20,2)  null  comment 'C1260_非银行业金融机构',

    C1270     decimal(20,2)  null  comment 'C1270_特定目的载体',

    C1271     decimal(20,2)  null  comment 'C1271_银行非保本理财',

    C1272     decimal(20,2)  null  comment 'C1272_信托公司资管产品',

    C1273     decimal(20,2)  null  comment 'C1273_证券公司及其子公司资管产品',

    C1274     decimal(20,2)  null  comment 'C1274_基金管理公司及其子公司专户',

    C1275     decimal(20,2)  null  comment 'C1275_期货公司及其子公司资管产品',

    C1276     decimal(20,2)  null  comment 'C1276_保险资管产品',

    C1277     decimal(20,2)  null  comment 'C1277_金融资产投资公司资管产品',

    C1278     decimal(20,2)  null  comment 'C1278_公募基金',

    C1279     decimal(20,2)  null  comment 'C1279_私募机构私募基金',

    C127a     decimal(20,2)  null  comment 'C127a_其他特定目的载体',

    C1280    decimal(20,2)  null  comment 'C1280_境外',

    C3000     decimal(20,2)  null  comment 'C3000_未分配利润',

    C4000     decimal(20,2)  null  comment 'C4000_其他综合收益'
)
    comment '人行采集平台资产负债报表主表';

delete from  sys_batch_task where TASK_ID='5001';
INSERT INTO sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('5001', 'SVR', '采集平台资管产品资产负债信息(ZG05)生成批处理', null, 'ZG05PbcCodPrdAssetDebtTaskService', 3000, 'system', null, '2022-11-11 14:34:12', 'system', '2022-11-11 14:34:12', null);

	
delete from sys_menu  where MENU_ID='REPORT_REGIST_001_003_018';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_REGIST_001_003_018', 'SVR', null, 'REPORT_REGIST_001_003', 'ZG05PbcColPrdAssetDebtSub', 18, '2', 'ZG05 资管产品资产负债信息', 'system', null, '2022-11-04 16:30:16', 'system', '2022-11-04 16:30:16');

delete from  sys_page where PAGE_ID='ZG05PbcColPrdAssetDebtSub';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ZG05PbcColPrdAssetDebtSub', 'SVR', '/pbc/zg05prdassetdebt/ZG05PbcColPrdAssetDebtSub', 'report/pbc/classic/zg05prdassetdebt/ZG05PbcColPrdAssetDebtSub', 'REPORT_REGIST_001_003_018', null, null, '01', '01', 'ZG05 资管产品资产负债信息', 'system', null, '2022-11-04 16:30:22', 'system', '2022-11-04 16:30:22');


DELETE FROM  sys_datadict WHERE DICT_CODE='STATUS_TYPE_PBC';
INSERT INTO sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('STATUS_TYPE_PBC', 'REP', '数据类型', null, '02', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00');

DELETE FROM  sys_datadict_list WHERE DICT_CODE='STATUS_TYPE_PBC';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('STATUS_TYPE_PBC', '1', '原币种数据', 1, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('STATUS_TYPE_PBC', '2', '折人民币数据', 2, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('STATUS_TYPE_PBC', '3', '本外币合计数据', 3, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);

DELETE FROM  sys_datadict WHERE DICT_CODE='CURRENCY_PBC';
INSERT INTO sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CURRENCY_PBC', 'REP', '币种（人行）', null, '02', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00');

DELETE FROM  sys_datadict_list WHERE DICT_CODE='CURRENCY_PBC';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ADP', '安道尔比赛塔', 1, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AED', 'UAE迪拉姆', 2, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AFA', '阿富汗尼', 3, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ALL', '列克', 4, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AMD', '亚美尼亚达姆', 5, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ANG', '荷属安的列斯盾', 6, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AOA', '安哥拉宽扎', 199, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AON', '新克瓦查', 7, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ARS', '阿根廷比索', 8, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ASF', '记账瑞士法郎', 191, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ATS', '先令', 9, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AUD', '澳大利亚元', 10, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AWG', '阿鲁巴盾', 11, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'AZM', '阿塞拜疆马纳特', 12, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BAM', '可自由兑换标记', 200, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BBD', '巴巴多斯元', 13, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BDT', '塔卡', 14, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BEF', '比利时法郎', 15, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BGL', '列弗', 16, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BGN', '保加利亚列弗', 201, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BHD', '巴林第纳尔', 17, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BIF', '布隆迪法郎', 18, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BMD', '百慕大元', 19, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BND', '文莱元', 20, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BOB', '玻利维亚比索', 21, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BOV', 'Mvdol', 22, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BRL', '巴西瑞尔', 23, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BSD', '巴哈尔元', 24, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BTN', '努尔特鲁姆', 25, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BWB', '本位币', 221, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BWP', '普拉', 26, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BYB', '白俄罗斯卢布', 27, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BYR', '白俄罗斯卢布', 202, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'BZD', '伯利兹元', 28, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CAD', '加元', 29, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CDF', '刚果法郎', 203, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CHF', '瑞士法郎', 30, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CLF', '发展单位', 31, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CLP', '智利比索', 32, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CNY', '人民币元', 33, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'COP', '哥伦比亚比索', 34, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CRC', '哥斯达黎加科郎', 35, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CUP', '古巴比索', 36, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CVE', '佛得角埃斯库多', 37, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CYP', '塞浦路斯镑', 38, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'CZK', '捷克克郎', 39, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'DEM', '德国马克', 40, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'DJF', '吉布提法郎', 41, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'DKK', '丹麦克朗', 42, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'DOP', '多米尼加比索', 43, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'DZD', '阿尔及利亚第纳尔', 44, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ECS', '苏克雷', 45, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ECV', 'UVC', 46, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'EEK', '克罗姆', 47, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'EGP', '埃及镑', 48, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ERN', '厄立特里亚纳克法', 204, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ESP', '西班牙比赛塔', 49, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ETB', '埃塞俄比亚比尔', 50, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'EUR', '欧元　', 51, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'FIM', '马克', 52, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'FJD', '斐济元', 53, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'FKP', '福克兰群岛镑', 54, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'FRF', '法国法郎', 55, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GBP', '英镑', 56, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GEL', '格鲁吉亚拉里', 205, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GHC', '塞地', 57, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GIP', '直布罗陀镑', 58, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GMD', '达拉西', 59, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GNF', '几内亚法郎', 60, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GRD', '德拉克马', 61, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GTQ', '格查尔', 62, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GWP', '几内亚比绍比索', 63, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'GYD', '圭亚那元', 64, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'HKD', '香港元', 65, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'HNL', '伦皮拉', 66, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'HRK', '克罗地亚库纳', 67, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'HTG', '古德', 68, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'HUF', '福林', 69, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'IDR', '印度尼西亚卢比', 70, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'IEP', '爱尔兰镑', 71, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ILS', '锡克尔', 72, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'INR', '印度卢比', 73, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'IQD', '伊拉克第纳尔', 74, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'IRR', '伊朗里亚尔', 75, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ISK', '冰岛克郎', 76, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ITL', '意大利里拉', 77, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'JMD', '牙买加元', 78, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'JOD', '约旦第纳尔', 79, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'JPY', '日元', 80, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KES', '肯尼亚先令', 81, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KGS', '索姆', 82, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KHR', '瑞尔', 83, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KMF', '科摩罗法郎', 84, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KPW', '北朝鲜圆', 85, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KRW', '韩元', 86, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KWD', '科威特第纳尔', 87, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KYD', '开曼群岛元', 88, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'KZT', '坚戈', 89, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LAK', '基普', 90, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LBP', '黎巴嫩镑', 91, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LKR', '斯里兰卡卢比', 92, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LRD', '利比里亚元', 93, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LSL', '罗提', 94, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LTL', '立陶宛', 95, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LUF', '卢森堡法郎', 96, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LVL', '拉托维亚拉特', 97, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'LYD', '利比亚第纳尔', 98, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MAD', '摩洛哥迪拉姆', 99, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MDL', '摩尔瓦多列伊', 100, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MGA', '马达加斯加阿里亚里', 206, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MGF', '马尔加什法郎', 101, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MKD', '第纳尔', 102, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MMK', '缅元', 103, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MNT', '图格里克', 104, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MOP', '澳门元', 105, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MRO', '乌吉亚', 106, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MTL', '马尔他里拉', 107, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MUR', '毛里求斯卢比', 108, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MVR', '卢菲亚', 109, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MWK', '克瓦查', 110, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MXN', '墨西哥比索', 111, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MXV', '墨西哥发展单位', 207, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MYR', '马来西亚林吉特', 112, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'MZM', '麦梯卡尔', 113, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NAD', '纳米比亚元', 114, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NGN', '奈拉', 115, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NIO', '金科多巴', 116, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NLG', '荷兰盾', 117, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NOK', '挪威克朗', 118, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NPR', '尼泊尔卢比', 119, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'NZD', '新西兰元', 120, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'OMR', '阿曼里亚尔', 121, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PAB', '巴波亚', 122, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PEN', '索尔', 123, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PGK', '基那', 124, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PHP', '菲律宾比索', 125, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PKR', '巴基斯坦卢比', 126, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PLN', '兹罗提', 127, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PLZ', '兹罗提', 128, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PTE', '葡萄牙埃斯库多', 129, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'PYG', '瓜拉尼', 130, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'QAR', '卡塔尔里亚尔', 131, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ROL', '列伊', 132, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'RSD', '塞尔维亚第纳尔', 208, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'RUB', '俄罗斯卢布', 133, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'RUR', '俄罗斯卢布', 209, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'RWF', '卢旺达法郎', 134, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SAR', '沙特里亚尔', 135, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SBD', '所罗门群岛元', 136, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SCR', '塞舌尔卢比', 137, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SDD', '苏丹第纳尔', 138, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SDG', '新苏丹镑', 210, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SDP', '苏丹镑', 139, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SDR', '特别提款权（停用）', 211, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SEK', '瑞典克郎', 140, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SGD', '新加坡元', 141, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SHP', '圣赫勒拿镑', 142, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SIT', '托拉尔', 143, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SKK', '斯洛伐克克郎', 144, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SLL', '利昂', 145, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SOS', '索马里先令', 146, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SRG', '苏里南盾', 147, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SSP', '南苏丹镑', 212, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'STD', '多布拉', 148, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SVC', '萨尔瓦多科郎', 149, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SYP', '叙利亚镑', 150, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'SZL', '里兰吉尼', 151, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'THB', '铢', 152, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TJR', '塔吉克卢布', 153, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TJS', '塔吉克斯坦索莫尼', 213, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TMM', '马纳特', 154, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TND', '突尼斯第纳尔', 155, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TOP', '邦加', 156, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TPE', '东帝汶埃斯库多', 157, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TRL', '土耳其里拉', 158, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TRY', '新土耳其里拉', 214, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TTD', '特立尼达和多巴哥元', 159, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TWD', '新台湾元', 160, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'TZS', '坦桑尼亚先令', 161, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'UAH', '乌克兰格里夫纳', 215, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'UAK', '库邦', 162, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'UGX', '乌干达先令', 163, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'USD', '美元', 164, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'USN', '美元次日', 165, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'USS', '美元同日', 166, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'UYU', '乌拉圭比索', 167, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'UZS', '乌兹别克斯坦苏姆', 168, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'VEB', '博利瓦', 169, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'VEF', '委内瑞拉博利瓦', 216, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'VES', '玻利瓦尔苏别纳', 170, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'VND', '盾', 171, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'VUV', '瓦图', 172, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'WST', '塔拉', 173, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XAF', 'CFA法郎BEAC', 174, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XAG', '银', 175, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XAU', '黄金', 176, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XB5', '欧洲账户17单位（E.U.A.-17）', 180, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XBA', '欧洲混合单位（EURCO）', 177, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XBB', '欧洲货币（单位EMU.-6）', 178, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XBC', '欧洲账户9单位（E.U.A.-9）', 179, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XBD', '欧洲帐户17单位（E.U.A.-17）', 217, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XCD', '东加勒比元', 181, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XDR', '特别提款权', 182, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XEU', '欧洲货币单位（E.C.U.）', 183, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XFO', '黄金法郎', 184, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XFU', 'UIC法郎', 185, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XOF', 'CFA法郎BCEAO', 186, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XPD', '钯', 187, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XPF', 'CFP法郎', 188, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XPT', '铂白金', 189, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XTS', '记账美元', 190, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'XXX', '未包括的交易货币代码', 218, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'YER', '也门里亚尔', 192, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'YUM', '南斯拉夫第纳尔', 219, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'YUN', '南斯拉夫第纳尔', 193, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZAL', '（金融兰特）', 194, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZAR', '兰特', 195, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZMK', '克瓦查', 196, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZMW', '新赞比亚克瓦查', 220, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZRN', '新扎伊尔', 197, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CURRENCY_PBC', 'ZWD', '津巴布韦元', 198, '0', 'system', null, '2019-03-05 00:00:00', 'system', '2019-03-05 00:00:00', null);
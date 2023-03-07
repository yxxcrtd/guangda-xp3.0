-- Oracle
DELETE FROM sys_datadict WHERE DICT_CODE = 'PBC_LOAN_ACT_INVEST';
INSERT INTO sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PBC_LOAN_ACT_INVEST', 'PBC', '贷款实际投向', null, '1', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00');

DELETE FROM sys_datadict_list WHERE DICT_CODE = 'PBC_LOAN_ACT_INVEST';
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'A01', '农业','1', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'A02', '林业','2', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'A03', '畜牧业','3', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'A04', '渔业','4', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'A05', '农、林、牧、渔专业及辅助性活动','5', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B06', '煤炭开采和洗选业','6', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B07', '石油和天然气开采业','7', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B08', '黑色金属矿采选业','8', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B09', '有色金属矿采选业','9', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B10', '非金属矿采选业','10', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B11', '开采专业及辅助性活动','11', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'B12', '其他采矿业','12', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C13', '农副食品加工业','13', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C14', '食品制造业','14', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C15', '酒、饮料和精制茶制造业','15', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C16', '烟草制品业','16', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C17', '纺织业','17', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C18', '纺织服装、服饰业','18', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C19', '皮革、毛皮、羽毛及其制品和制鞋业','19', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C20', '木材加工和木、竹、藤、棕、草制品业','20', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C21', '家具制造业','21', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C22', '造纸和纸制品业','22', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C23', '印刷和记录媒介复制业','23', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C24', '文教、工美、体育和娱乐用品制造业','24', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C25', '石油、煤炭及其他燃料加工业','25', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C26', '化学原料和化学制品制造业','26', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C27', '医药制造业','27', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C28', '化学纤维制造业','28', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C29', '橡胶和塑料制品业','29', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C30', '非金属矿物制品业','30', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C31', '黑色金属冶炼和压延加工业','31', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C32', '有色金属冶炼和压延加工业','32', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C33', '金属制品业','33', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C34', '通用设备制造业','34', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C35', '专用设备制造业','35', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C36', '汽车制造业','36', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C37', '铁路、船舶、航空航天和其他运输设备制造业','37', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C38', '电气机械和器材制造业','38', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C39', '计算机、通信和其他电子设备制造业','39', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C40', '仪器仪表制造业','40', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C41', '其他制造业','41', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C42', '废弃资源综合利用业','42', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'C43', '金属制品、机械和设备修理业','43', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'D44', '电力、热力生产和供应业','44', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'D45', '燃气生产和供应业','45', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'D46', '水的生产和供应业','46', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'E47', '房屋建筑业','47', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'E48', '土木工程建筑业','48', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'E49', '建筑安装业','49', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'E50', '建筑装饰、装修和其他建筑业','50', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'F51', '批发业','51', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'F52', '零售业','52', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G53', '铁路运输业','53', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G54', '道路运输业','54', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G55', '水上运输业','55', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G56', '航空运输业','56', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G57', '管道运输业','57', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G58', '多式联运和运输代理业','58', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G59', '装卸搬运和仓储业','59', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'G60', '邮政业','60', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'H61', '住宿业','61', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'H62', '餐饮业','62', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'I63', '电信、广播电视和卫星传输服务','63', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'I64', '互联网和相关服务','64', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'I65', '软件和信息技术服务业','65', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'J66', '货币金融服务','66', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'J67', '资本市场服务','67', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'J68', '保险业','68', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'J69', '其他金融业','69', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'K70', '房地产业','70', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'L71', '租赁业','71', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'L72', '商务服务业','72', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'M73', '研究和试验发展','73', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'M74', '专业技术服务业','74', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'M75', '科技推广和应用服务业','75', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'N76', '水利管理业','76', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'N77', '生态保护和环境治理业','77', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'N78', '公共设施管理业','78', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'N79', '土地管理业','79', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'O80', '居民服务业','80', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'O81', '机动车、电子产品和日用产品修理业','81', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'O82', '其他服务业','82', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'P83', '教育','83', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'Q84', '卫生','84', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'Q85', '社会工作','85', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'R86', '新闻和出版业','86', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'R87', '广播、电视、电影和录音制作业','87', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'R88', '文化艺术业','88', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'R89', '体育','89', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'R90', '娱乐业','90', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S91', '中国共产党机关','91', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S92', '国家机构','92', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S93', '人民政协、民主党派','93', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S94', '社会保障','94', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S95', '群众团体、社会团体和其他成员组织','95', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'S96', '基层群众自治组织及其他组织','96', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', 'T97', '国际组织','97', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', '100', '个人','98', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_ACT_INVEST', '200', '境外','99', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);

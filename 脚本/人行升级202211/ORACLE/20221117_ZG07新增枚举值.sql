-- Oracle
-- Z0040
DELETE FROM sys_datadict WHERE DICT_CODE = 'PBC_LOAN_LOCATION';
INSERT INTO sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('PBC_LOAN_LOCATION', 'PBC', '人行贷款发放所在地', null, '1', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00');

DELETE FROM sys_datadict_list WHERE DICT_CODE = 'PBC_LOAN_LOCATION';
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110000', '北京市','1', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110100', '市辖区','2', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110101', '东城区','3', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110102', '西城区','4', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110105', '朝阳区','5', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110106', '丰台区','6', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110107', '石景山区','7', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110108', '海淀区','8', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110109', '门头沟区','9', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110111', '房山区','10', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110112', '通州区','11', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110113', '顺义区','12', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110114', '昌平区','13', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110115', '大兴区','14', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110116', '怀柔区','15', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110117', '平谷区','16', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110118', '密云区','17', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '110119', '延庆区','18', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120000', '天津市','19', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120100', '市辖区','20', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120101', '和平区','21', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120102', '河东区','22', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120103', '河西区','23', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120104', '南开区','24', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120105', '河北区','25', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120106', '红桥区','26', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120110', '东丽区','27', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120111', '西青区','28', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120112', '津南区','29', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120113', '北辰区','30', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120114', '武清区','31', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120115', '宝坻区','32', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120116', '滨海新区','33', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120117', '宁河区','34', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120118', '静海区','35', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '120119', '蓟州区','36', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130000', '河北省','37', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130101', '市辖区','38', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130100', '石家庄市','39', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130102', '长安区','40', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130104', '桥西区','41', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130105', '新华区','42', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130107', '井陉矿区','43', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130108', '裕华区','44', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130109', '藁城区','45', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130110', '鹿泉区','46', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130111', '栾城区','47', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130121', '井陉县','48', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130123', '正定县','49', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130125', '行唐县','50', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130126', '灵寿县','51', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130127', '高邑县','52', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130128', '深泽县','53', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130129', '赞皇县','54', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130130', '无极县','55', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130131', '平山县','56', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130132', '元氏县','57', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130133', '赵县','58', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130181', '辛集市','59', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130183', '晋州市','60', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130184', '新乐市','61', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130200', '唐山市','62', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130201', '市辖区','63', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130202', '路南区','64', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130203', '路北区','65', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130204', '古冶区','66', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130205', '开平区','67', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130207', '丰南区','68', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130208', '丰润区','69', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130209', '曹妃甸区','70', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130224', '滦南县','71', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130225', '乐亭县','72', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130227', '迁西县','73', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130229', '玉田县','74', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130281', '遵化市','75', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130283', '迁安市','76', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130284', '滦州市','77', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130300', '秦皇岛市','78', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130301', '市辖区','79', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130302', '海港区','80', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130303', '山海关区','81', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130304', '北戴河区','82', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130306', '抚宁区','83', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130321', '青龙满族自治县','84', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130322', '昌黎县','85', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130324', '卢龙县','86', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130400', '邯郸市','87', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130401', '市辖区','88', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130402', '邯山区','89', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130403', '丛台区','90', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130404', '复兴区','91', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130406', '峰峰矿区','92', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130407', '肥乡区','93', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130408', '永年区','94', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130423', '临漳县','95', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130424', '成安县','96', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130425', '大名县','97', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130426', '涉县','98', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130427', '磁县','99', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130430', '邱县','100', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130431', '鸡泽县','101', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130432', '广平县','102', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130433', '馆陶县','103', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130434', '魏县','104', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130435', '曲周县','105', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130481', '武安市','106', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130500', '邢台市','107', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130501', '市辖区','108', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130502', '桥东区','109', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130503', '桥西区','110', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130521', '邢台县','111', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130522', '临城县','112', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130523', '内丘县','113', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130524', '柏乡县','114', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130525', '隆尧县','115', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130526', '任县','116', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130527', '南和县','117', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130528', '宁晋县','118', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130529', '巨鹿县','119', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130530', '新河县','120', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130531', '广宗县','121', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130532', '平乡县','122', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130533', '威县','123', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130534', '清河县','124', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130535', '临西县','125', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130581', '南宫市','126', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130582', '沙河市','127', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130600', '保定市','128', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130601', '市辖区','129', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130602', '竞秀区','130', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130606', '莲池区','131', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130607', '满城区','132', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130608', '清苑区','133', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130609', '徐水区','134', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130623', '涞水县','135', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130624', '阜平县','136', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130626', '定兴县','137', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130627', '唐县','138', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130628', '高阳县','139', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130629', '容城县','140', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130630', '涞源县','141', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130631', '望都县','142', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130632', '安新县','143', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130633', '易县','144', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130634', '曲阳县','145', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130635', '蠡县','146', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130636', '顺平县','147', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130637', '博野县','148', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130638', '雄县','149', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130681', '涿州市','150', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130682', '定州市','151', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130683', '安国市','152', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130684', '高碑店市','153', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130700', '张家口市','154', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130701', '市辖区','155', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130702', '桥东区','156', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130703', '桥西区','157', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130705', '宣化区','158', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130706', '下花园区','159', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130708', '万全区','160', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130709', '崇礼区','161', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130722', '张北县','162', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130723', '康保县','163', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130724', '沽源县','164', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130725', '尚义县','165', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130726', '蔚县','166', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130727', '阳原县','167', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130728', '怀安县','168', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130730', '怀来县','169', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130731', '涿鹿县','170', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130732', '赤城县','171', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130800', '承德市','172', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130801', '市辖区','173', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130802', '双桥区','174', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130803', '双滦区','175', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130804', '鹰手营子矿区','176', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130821', '承德县','177', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130822', '兴隆县','178', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130824', '滦平县','179', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130825', '隆化县','180', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130826', '丰宁满族自治县','181', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130827', '宽城满族自治县','182', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130828', '围场满族蒙古族自治县','183', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130881', '平泉市','184', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130900', '沧州市','185', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130901', '市辖区','186', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130902', '新华区','187', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130903', '运河区','188', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130921', '沧县','189', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130922', '青县','190', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130923', '东光县','191', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130924', '海兴县','192', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130925', '盐山县','193', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130926', '肃宁县','194', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130927', '南皮县','195', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130928', '吴桥县','196', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130929', '献县','197', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130930', '孟村回族自治县','198', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130981', '泊头市','199', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130982', '任丘市','200', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130983', '黄骅市','201', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '130984', '河间市','202', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131000', '廊坊市','203', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131001', '市辖区','204', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131002', '安次区','205', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131003', '广阳区','206', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131022', '固安县','207', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131023', '永清县','208', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131024', '香河县','209', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131025', '大城县','210', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131026', '文安县','211', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131028', '大厂回族自治县','212', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131081', '霸州市','213', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131082', '三河市','214', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131100', '衡水市','215', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131101', '市辖区','216', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131102', '桃城区','217', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131103', '冀州区','218', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131121', '枣强县','219', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131122', '武邑县','220', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131123', '武强县','221', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131124', '饶阳县','222', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131125', '安平县','223', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131126', '故城县','224', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131127', '景县','225', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131128', '阜城县','226', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '131182', '深州市','227', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140000', '山西省','228', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140100', '太原市','229', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140101', '市辖区','230', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140105', '小店区','231', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140106', '迎泽区','232', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140107', '杏花岭区','233', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140108', '尖草坪区','234', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140109', '万柏林区','235', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140110', '晋源区','236', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140121', '清徐县','237', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140122', '阳曲县','238', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140123', '娄烦县','239', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140181', '古交市','240', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140200', '大同市','241', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140201', '市辖区','242', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140212', '新荣区','243', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140213', '平城区','244', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140214', '云冈区','245', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140215', '云州区','246', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140221', '阳高县','247', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140222', '天镇县','248', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140223', '广灵县','249', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140224', '灵丘县','250', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140225', '浑源县','251', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140226', '左云县','252', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140300', '阳泉市','253', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140301', '市辖区','254', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140302', '城区','255', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140303', '矿区','256', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140311', '郊区','257', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140321', '平定县','258', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140322', '盂县','259', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140400', '长治市','260', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140401', '市辖区','261', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140403', '潞州区','262', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140404', '上党区','263', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140405', '屯留区','264', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140406', '潞城区','265', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140423', '襄垣县','266', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140425', '平顺县','267', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140426', '黎城县','268', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140427', '壶关县','269', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140428', '长子县','270', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140429', '武乡县','271', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140430', '沁县','272', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140431', '沁源县','273', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140500', '晋城市','274', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140501', '市辖区','275', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140502', '城区','276', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140521', '沁水县','277', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140522', '阳城县','278', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140524', '陵川县','279', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140525', '泽州县','280', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140581', '高平市','281', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140600', '朔州市','282', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140601', '市辖区','283', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140602', '朔城区','284', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140603', '平鲁区','285', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140621', '山阴县','286', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140622', '应县','287', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140623', '右玉县','288', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140681', '怀仁市','289', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140700', '晋中市','290', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140701', '市辖区','291', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140702', '榆次区','292', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140703', '太谷区','293', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140721', '榆社县','294', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140722', '左权县','295', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140723', '和顺县','296', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140724', '昔阳县','297', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140725', '寿阳县','298', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140727', '祁县','299', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140728', '平遥县','300', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140729', '灵石县','301', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140781', '介休市','302', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140800', '运城市','303', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140801', '市辖区','304', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140802', '盐湖区','305', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140821', '临猗县','306', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140822', '万荣县','307', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140823', '闻喜县','308', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140824', '稷山县','309', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140825', '新绛县','310', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140826', '绛县','311', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140827', '垣曲县','312', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140828', '夏县','313', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140829', '平陆县','314', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140830', '芮城县','315', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140881', '永济市','316', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140882', '河津市','317', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140900', '忻州市','318', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140901', '市辖区','319', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140902', '忻府区','320', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140921', '定襄县','321', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140922', '五台县','322', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140923', '代县','323', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140924', '繁峙县','324', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140925', '宁武县','325', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140926', '静乐县','326', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140927', '神池县','327', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140928', '五寨县','328', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140929', '岢岚县','329', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140930', '河曲县','330', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140931', '保德县','331', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140932', '偏关县','332', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '140981', '原平市','333', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141000', '临汾市','334', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141001', '市辖区','335', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141002', '尧都区','336', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141021', '曲沃县','337', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141022', '翼城县','338', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141023', '襄汾县','339', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141024', '洪洞县','340', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141025', '古县','341', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141026', '安泽县','342', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141027', '浮山县','343', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141028', '吉县','344', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141029', '乡宁县','345', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141030', '大宁县','346', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141031', '隰县','347', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141032', '永和县','348', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141033', '蒲县','349', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141034', '汾西县','350', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141081', '侯马市','351', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141082', '霍州市','352', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141100', '吕梁市','353', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141101', '市辖区','354', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141102', '离石区','355', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141121', '文水县','356', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141122', '交城县','357', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141123', '兴县','358', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141124', '临县','359', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141125', '柳林县','360', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141126', '石楼县','361', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141127', '岚县','362', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141128', '方山县','363', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141129', '中阳县','364', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141130', '交口县','365', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141181', '孝义市','366', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '141182', '汾阳市','367', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150000', '内蒙古自治区','368', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150100', '呼和浩特市','369', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150101', '市辖区','370', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150102', '新城区','371', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150103', '回民区','372', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150104', '玉泉区','373', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150105', '赛罕区','374', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150121', '土默特左旗','375', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150122', '托克托县','376', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150123', '和林格尔县','377', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150124', '清水河县','378', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150125', '武川县','379', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150200', '包头市','380', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150201', '市辖区','381', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150202', '东河区','382', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150203', '昆都仑区','383', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150204', '青山区','384', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150205', '石拐区','385', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150206', '白云鄂博矿区','386', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150207', '九原区','387', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150221', '土默特右旗','388', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150222', '固阳县','389', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150223', '达尔罕茂明安联合旗','390', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150300', '乌海市','391', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150301', '市辖区','392', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150302', '海勃湾区','393', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150303', '海南区','394', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150304', '乌达区','395', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150400', '赤峰市','396', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150401', '市辖区','397', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150402', '红山区','398', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150403', '元宝山区','399', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150404', '松山区','400', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150421', '阿鲁科尔沁旗','401', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150422', '巴林左旗','402', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150423', '巴林右旗','403', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150424', '林西县','404', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150425', '克什克腾旗','405', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150426', '翁牛特旗','406', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150428', '喀喇沁旗','407', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150429', '宁城县','408', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150430', '敖汉旗','409', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150500', '通辽市','410', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150501', '市辖区','411', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150502', '科尔沁区','412', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150521', '科尔沁左翼中旗','413', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150522', '科尔沁左翼后旗','414', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150523', '开鲁县','415', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150524', '库伦旗','416', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150525', '奈曼旗','417', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150526', '扎鲁特旗','418', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150581', '霍林郭勒市','419', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150600', '鄂尔多斯市','420', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150601', '市辖区','421', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150602', '东胜区','422', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150603', '康巴什区','423', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150621', '达拉特旗','424', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150622', '准格尔旗','425', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150623', '鄂托克前旗','426', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150624', '鄂托克旗','427', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150625', '杭锦旗','428', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150626', '乌审旗','429', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150627', '伊金霍洛旗','430', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150700', '呼伦贝尔市','431', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150701', '市辖区','432', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150702', '海拉尔区','433', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150703', '扎赉诺尔区','434', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150721', '阿荣旗','435', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150722', '莫力达瓦达斡尔族自治旗','436', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150723', '鄂伦春自治旗','437', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150724', '鄂温克族自治旗','438', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150725', '陈巴尔虎旗','439', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150726', '新巴尔虎左旗','440', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150727', '新巴尔虎右旗','441', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150781', '满洲里市','442', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150782', '牙克石市','443', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150783', '扎兰屯市','444', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150784', '额尔古纳市','445', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150785', '根河市','446', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150800', '巴彦淖尔市','447', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150801', '市辖区','448', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150802', '临河区','449', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150821', '五原县','450', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150822', '磴口县','451', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150823', '乌拉特前旗','452', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150824', '乌拉特中旗','453', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150825', '乌拉特后旗','454', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150826', '杭锦后旗','455', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150900', '乌兰察布市','456', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150901', '市辖区','457', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150902', '集宁区','458', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150921', '卓资县','459', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150922', '化德县','460', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150923', '商都县','461', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150924', '兴和县','462', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150925', '凉城县','463', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150926', '察哈尔右翼前旗','464', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150927', '察哈尔右翼中旗','465', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150928', '察哈尔右翼后旗','466', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150929', '四子王旗','467', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '150981', '丰镇市','468', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152200', '兴安盟','469', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152201', '乌兰浩特市','470', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152202', '阿尔山市','471', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152221', '科尔沁右翼前旗','472', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152222', '科尔沁右翼中旗','473', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152223', '扎赉特旗','474', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152224', '突泉县','475', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152500', '锡林郭勒盟','476', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152501', '二连浩特市','477', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152502', '锡林浩特市','478', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152522', '阿巴嘎旗','479', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152523', '苏尼特左旗','480', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152524', '苏尼特右旗','481', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152525', '东乌珠穆沁旗','482', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152526', '西乌珠穆沁旗','483', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152527', '太仆寺旗','484', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152528', '镶黄旗','485', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152529', '正镶白旗','486', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152530', '正蓝旗','487', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152531', '多伦县','488', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152900', '阿拉善盟','489', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152921', '阿拉善左旗','490', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152922', '阿拉善右旗','491', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '152923', '额济纳旗','492', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210000', '辽宁省','493', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210100', '沈阳市','494', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210101', '市辖区','495', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210102', '和平区','496', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210103', '沈河区','497', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210104', '大东区','498', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210105', '皇姑区','499', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210106', '铁西区','500', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210111', '苏家屯区','501', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210112', '浑南区','502', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210113', '沈北新区','503', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210114', '于洪区','504', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210115', '辽中区','505', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210123', '康平县','506', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210124', '法库县','507', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210181', '新民市','508', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210200', '大连市','509', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210201', '市辖区','510', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210202', '中山区','511', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210203', '西岗区','512', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210204', '沙河口区','513', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210211', '甘井子区','514', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210212', '旅顺口区','515', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210213', '金州区','516', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210214', '普兰店区','517', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210224', '长海县','518', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210281', '瓦房店市','519', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210283', '庄河市','520', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210300', '鞍山市','521', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210301', '市辖区','522', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210302', '铁东区','523', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210303', '铁西区','524', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210304', '立山区','525', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210311', '千山区','526', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210321', '台安县','527', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210323', '岫岩满族自治县','528', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210381', '海城市','529', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210400', '抚顺市','530', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210401', '市辖区','531', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210402', '新抚区','532', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210403', '东洲区','533', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210404', '望花区','534', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210411', '顺城区','535', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210421', '抚顺县','536', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210422', '新宾满族自治县','537', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210423', '清原满族自治县','538', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210500', '本溪市','539', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210501', '市辖区','540', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210502', '平山区','541', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210503', '溪湖区','542', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210504', '明山区','543', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210505', '南芬区','544', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210521', '本溪满族自治县','545', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210522', '桓仁满族自治县','546', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210600', '丹东市','547', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210601', '市辖区','548', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210602', '元宝区','549', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210603', '振兴区','550', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210604', '振安区','551', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210624', '宽甸满族自治县','552', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210681', '东港市','553', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210682', '凤城市','554', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210700', '锦州市','555', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210701', '市辖区','556', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210702', '古塔区','557', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210703', '凌河区','558', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210711', '太和区','559', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210726', '黑山县','560', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210727', '义县','561', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210781', '凌海市','562', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210782', '北镇市','563', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210800', '营口市','564', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210801', '市辖区','565', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210802', '站前区','566', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210803', '西市区','567', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210804', '鲅鱼圈区','568', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210811', '老边区','569', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210881', '盖州市','570', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210882', '大石桥市','571', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210900', '阜新市','572', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210901', '市辖区','573', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210902', '海州区','574', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210903', '新邱区','575', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210904', '太平区','576', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210905', '清河门区','577', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210911', '细河区','578', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210921', '阜新蒙古族自治县','579', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '210922', '彰武县','580', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211000', '辽阳市','581', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211001', '市辖区','582', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211002', '白塔区','583', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211003', '文圣区','584', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211004', '宏伟区','585', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211005', '弓长岭区','586', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211011', '太子河区','587', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211021', '辽阳县','588', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211081', '灯塔市','589', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211100', '盘锦市','590', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211101', '市辖区','591', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211102', '双台子区','592', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211103', '兴隆台区','593', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211104', '大洼区','594', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211122', '盘山县','595', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211200', '铁岭市','596', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211201', '市辖区','597', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211202', '银州区','598', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211204', '清河区','599', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211221', '铁岭县','600', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211223', '西丰县','601', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211224', '昌图县','602', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211281', '调兵山市','603', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211282', '开原市','604', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211300', '朝阳市','605', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211301', '市辖区','606', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211302', '双塔区','607', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211303', '龙城区','608', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211321', '朝阳县','609', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211322', '建平县','610', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211324', '喀喇沁左翼蒙古族自治县','611', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211381', '北票市','612', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211382', '凌源市','613', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211400', '葫芦岛市','614', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211401', '市辖区','615', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211402', '连山区','616', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211403', '龙港区','617', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211404', '南票区','618', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211421', '绥中县','619', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211422', '建昌县','620', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '211481', '兴城市','621', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220000', '吉林省','622', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220100', '长春市','623', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220101', '市辖区','624', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220102', '南关区','625', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220103', '宽城区','626', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220104', '朝阳区','627', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220105', '二道区','628', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220106', '绿园区','629', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220112', '双阳区','630', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220113', '九台区','631', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220122', '农安县','632', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220182', '榆树市','633', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220183', '德惠市','634', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220200', '吉林市','635', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220201', '市辖区','636', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220202', '昌邑区','637', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220203', '龙潭区','638', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220204', '船营区','639', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220211', '丰满区','640', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220221', '永吉县','641', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220281', '蛟河市','642', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220282', '桦甸市','643', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220283', '舒兰市','644', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220284', '磐石市','645', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220300', '四平市','646', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220301', '市辖区','647', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220302', '铁西区','648', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220303', '铁东区','649', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220322', '梨树县','650', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220323', '伊通满族自治县','651', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220381', '公主岭市','652', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220382', '双辽市','653', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220400', '辽源市','654', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220401', '市辖区','655', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220402', '龙山区','656', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220403', '西安区','657', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220421', '东丰县','658', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220422', '东辽县','659', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220500', '通化市','660', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220501', '市辖区','661', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220502', '东昌区','662', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220503', '二道江区','663', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220521', '通化县','664', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220523', '辉南县','665', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220524', '柳河县','666', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220581', '梅河口市','667', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220582', '集安市','668', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220600', '白山市','669', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220601', '市辖区','670', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220602', '浑江区','671', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220605', '江源区','672', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220621', '抚松县','673', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220622', '靖宇县','674', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220623', '长白朝鲜族自治县','675', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220681', '临江市','676', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220700', '松原市','677', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220701', '市辖区','678', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220702', '宁江区','679', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220721', '前郭尔罗斯蒙古族自治县','680', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220722', '长岭县','681', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220723', '乾安县','682', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220781', '扶余市','683', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220800', '白城市','684', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220801', '市辖区','685', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220802', '洮北区','686', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220821', '镇赉县','687', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220822', '通榆县','688', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220881', '洮南市','689', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '220882', '大安市','690', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222400', '延边朝鲜族自治州','691', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222401', '延吉市','692', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222402', '图们市','693', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222403', '敦化市','694', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222404', '珲春市','695', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222405', '龙井市','696', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222406', '和龙市','697', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222424', '汪清县','698', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '222426', '安图县','699', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230000', '黑龙江省','700', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230100', '哈尔滨市','701', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230101', '市辖区','702', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230102', '道里区','703', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230103', '南岗区','704', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230104', '道外区','705', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230108', '平房区','706', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230109', '松北区','707', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230110', '香坊区','708', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230111', '呼兰区','709', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230112', '阿城区','710', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230113', '双城区','711', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230123', '依兰县','712', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230124', '方正县','713', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230125', '宾县','714', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230126', '巴彦县','715', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230127', '木兰县','716', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230128', '通河县','717', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230129', '延寿县','718', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230183', '尚志市','719', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230184', '五常市','720', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230200', '齐齐哈尔市','721', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230201', '市辖区','722', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230202', '龙沙区','723', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230203', '建华区','724', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230204', '铁锋区','725', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230205', '昂昂溪区','726', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230206', '富拉尔基区','727', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230207', '碾子山区','728', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230208', '梅里斯达斡尔族区','729', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230221', '龙江县','730', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230223', '依安县','731', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230224', '泰来县','732', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230225', '甘南县','733', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230227', '富裕县','734', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230229', '克山县','735', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230230', '克东县','736', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230231', '拜泉县','737', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230281', '讷河市','738', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230300', '鸡西市','739', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230301', '市辖区','740', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230302', '鸡冠区','741', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230303', '恒山区','742', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230304', '滴道区','743', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230305', '梨树区','744', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230306', '城子河区','745', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230307', '麻山区','746', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230321', '鸡东县','747', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230381', '虎林市','748', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230382', '密山市','749', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230400', '鹤岗市','750', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230401', '市辖区','751', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230402', '向阳区','752', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230403', '工农区','753', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230404', '南山区','754', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230405', '兴安区','755', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230406', '东山区','756', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230407', '兴山区','757', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230421', '萝北县','758', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230422', '绥滨县','759', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230500', '双鸭山市','760', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230501', '市辖区','761', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230502', '尖山区','762', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230503', '岭东区','763', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230505', '四方台区','764', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230506', '宝山区','765', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230521', '集贤县','766', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230522', '友谊县','767', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230523', '宝清县','768', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230524', '饶河县','769', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230600', '大庆市','770', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230601', '市辖区','771', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230602', '萨尔图区','772', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230603', '龙凤区','773', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230604', '让胡路区','774', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230605', '红岗区','775', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230606', '大同区','776', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230621', '肇州县','777', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230622', '肇源县','778', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230623', '林甸县','779', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230624', '杜尔伯特蒙古族自治县','780', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230700', '伊春市','781', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230701', '市辖区','782', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230717', '伊美区','783', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230718', '乌翠区','784', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230719', '友好区','785', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230722', '嘉荫县','786', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230723', '汤旺县','787', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230724', '丰林县','788', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230725', '大箐山县','789', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230726', '南岔县','790', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230751', '金林区','791', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230781', '铁力市','792', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230800', '佳木斯市','793', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230801', '市辖区','794', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230803', '向阳区','795', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230804', '前进区','796', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230805', '东风区','797', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230811', '郊区','798', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230822', '桦南县','799', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230826', '桦川县','800', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230828', '汤原县','801', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230881', '同江市','802', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230882', '富锦市','803', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230883', '抚远市','804', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230900', '七台河市','805', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230901', '市辖区','806', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230902', '新兴区','807', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230903', '桃山区','808', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230904', '茄子河区','809', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '230921', '勃利县','810', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231000', '牡丹江市','811', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231001', '市辖区','812', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231002', '东安区','813', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231003', '阳明区','814', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231004', '爱民区','815', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231005', '西安区','816', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231025', '林口县','817', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231081', '绥芬河市','818', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231083', '海林市','819', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231084', '宁安市','820', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231085', '穆棱市','821', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231086', '东宁市','822', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231100', '黑河市','823', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231101', '市辖区','824', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231102', '爱辉区','825', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231123', '逊克县','826', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231124', '孙吴县','827', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231181', '北安市','828', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231182', '五大连池市','829', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231183', '嫩江市','830', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231200', '绥化市','831', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231201', '市辖区','832', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231202', '北林区','833', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231221', '望奎县','834', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231222', '兰西县','835', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231223', '青冈县','836', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231224', '庆安县','837', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231225', '明水县','838', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231226', '绥棱县','839', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231281', '安达市','840', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231282', '肇东市','841', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '231283', '海伦市','842', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '232700', '大兴安岭地区','843', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '232701', '漠河市','844', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '232721', '呼玛县','845', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '232722', '塔河县','846', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310000', '上海市','847', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310100', '市辖区','848', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310101', '黄浦区','849', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310104', '徐汇区','850', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310105', '长宁区','851', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310106', '静安区','852', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310107', '普陀区','853', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310109', '虹口区','854', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310110', '杨浦区','855', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310112', '闵行区','856', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310113', '宝山区','857', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310114', '嘉定区','858', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310115', '浦东新区','859', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310116', '金山区','860', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310117', '松江区','861', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310118', '青浦区','862', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310120', '奉贤区','863', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '310151', '崇明区','864', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320000', '江苏省','865', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320100', '南京市','866', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320101', '市辖区','867', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320102', '玄武区','868', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320104', '秦淮区','869', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320105', '建邺区','870', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320106', '鼓楼区','871', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320111', '浦口区','872', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320113', '栖霞区','873', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320114', '雨花台区','874', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320115', '江宁区','875', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320116', '六合区','876', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320117', '溧水区','877', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320118', '高淳区','878', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320200', '无锡市','879', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320201', '市辖区','880', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320205', '锡山区','881', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320206', '惠山区','882', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320211', '滨湖区','883', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320213', '梁溪区','884', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320214', '新吴区','885', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320281', '江阴市','886', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320282', '宜兴市','887', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320300', '徐州市','888', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320301', '市辖区','889', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320302', '鼓楼区','890', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320303', '云龙区','891', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320305', '贾汪区','892', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320311', '泉山区','893', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320312', '铜山区','894', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320321', '丰县','895', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320322', '沛县','896', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320324', '睢宁县','897', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320381', '新沂市','898', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320382', '邳州市','899', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320400', '常州市','900', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320401', '市辖区','901', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320402', '天宁区','902', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320404', '钟楼区','903', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320411', '新北区','904', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320412', '武进区','905', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320413', '金坛区','906', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320481', '溧阳市','907', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320500', '苏州市','908', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320501', '市辖区','909', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320505', '虎丘区','910', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320506', '吴中区','911', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320507', '相城区','912', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320508', '姑苏区','913', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320509', '吴江区','914', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320581', '常熟市','915', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320582', '张家港市','916', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320583', '昆山市','917', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320585', '太仓市','918', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320600', '南通市','919', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320601', '市辖区','920', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320602', '崇川区','921', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320611', '港闸区','922', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320612', '通州区','923', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320623', '如东县','924', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320681', '启东市','925', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320682', '如皋市','926', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320684', '海门市','927', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320685', '海安市','928', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320700', '连云港市','929', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320701', '市辖区','930', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320703', '连云区','931', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320706', '海州区','932', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320707', '赣榆区','933', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320722', '东海县','934', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320723', '灌云县','935', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320724', '灌南县','936', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320800', '淮安市','937', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320801', '市辖区','938', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320803', '淮安区','939', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320804', '淮阴区','940', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320812', '清江浦区','941', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320813', '洪泽区','942', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320826', '涟水县','943', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320830', '盱眙县','944', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320831', '金湖县','945', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320900', '盐城市','946', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320901', '市辖区','947', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320902', '亭湖区','948', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320903', '盐都区','949', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320904', '大丰区','950', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320921', '响水县','951', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320922', '滨海县','952', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320923', '阜宁县','953', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320924', '射阳县','954', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320925', '建湖县','955', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '320981', '东台市','956', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321000', '扬州市','957', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321001', '市辖区','958', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321002', '广陵区','959', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321003', '邗江区','960', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321012', '江都区','961', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321023', '宝应县','962', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321081', '仪征市','963', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321084', '高邮市','964', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321100', '镇江市','965', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321101', '市辖区','966', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321102', '京口区','967', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321111', '润州区','968', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321112', '丹徒区','969', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321181', '丹阳市','970', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321182', '扬中市','971', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321183', '句容市','972', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321200', '泰州市','973', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321201', '市辖区','974', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321202', '海陵区','975', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321203', '高港区','976', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321204', '姜堰区','977', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321281', '兴化市','978', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321282', '靖江市','979', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321283', '泰兴市','980', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321300', '宿迁市','981', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321301', '市辖区','982', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321302', '宿城区','983', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321311', '宿豫区','984', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321322', '沭阳县','985', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321323', '泗阳县','986', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '321324', '泗洪县','987', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330000', '浙江省','988', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330100', '杭州市','989', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330101', '市辖区','990', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330102', '上城区','991', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330103', '下城区','992', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330104', '江干区','993', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330105', '拱墅区','994', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330106', '西湖区','995', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330108', '滨江区','996', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330109', '萧山区','997', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330110', '余杭区','998', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330111', '富阳区','999', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330112', '临安区','1000', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330122', '桐庐县','1001', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330127', '淳安县','1002', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330182', '建德市','1003', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330200', '宁波市','1004', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330201', '市辖区','1005', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330203', '海曙区','1006', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330205', '江北区','1007', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330206', '北仑区','1008', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330211', '镇海区','1009', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330212', '鄞州区','1010', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330213', '奉化区','1011', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330225', '象山县','1012', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330226', '宁海县','1013', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330281', '余姚市','1014', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330282', '慈溪市','1015', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330300', '温州市','1016', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330301', '市辖区','1017', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330302', '鹿城区','1018', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330303', '龙湾区','1019', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330304', '瓯海区','1020', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330305', '洞头区','1021', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330324', '永嘉县','1022', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330326', '平阳县','1023', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330327', '苍南县','1024', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330328', '文成县','1025', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330329', '泰顺县','1026', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330381', '瑞安市','1027', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330382', '乐清市','1028', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330383', '龙港市','1029', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330400', '嘉兴市','1030', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330401', '市辖区','1031', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330402', '南湖区','1032', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330411', '秀洲区','1033', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330421', '嘉善县','1034', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330424', '海盐县','1035', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330481', '海宁市','1036', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330482', '平湖市','1037', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330483', '桐乡市','1038', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330500', '湖州市','1039', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330501', '市辖区','1040', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330502', '吴兴区','1041', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330503', '南浔区','1042', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330521', '德清县','1043', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330522', '长兴县','1044', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330523', '安吉县','1045', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330600', '绍兴市','1046', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330601', '市辖区','1047', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330602', '越城区','1048', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330603', '柯桥区','1049', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330604', '上虞区','1050', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330624', '新昌县','1051', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330681', '诸暨市','1052', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330683', '嵊州市','1053', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330700', '金华市','1054', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330701', '市辖区','1055', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330702', '婺城区','1056', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330703', '金东区','1057', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330723', '武义县','1058', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330726', '浦江县','1059', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330727', '磐安县','1060', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330781', '兰溪市','1061', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330782', '义乌市','1062', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330783', '东阳市','1063', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330784', '永康市','1064', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330800', '衢州市','1065', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330801', '市辖区','1066', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330802', '柯城区','1067', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330803', '衢江区','1068', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330822', '常山县','1069', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330824', '开化县','1070', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330825', '龙游县','1071', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330881', '江山市','1072', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330900', '舟山市','1073', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330901', '市辖区','1074', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330902', '定海区','1075', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330903', '普陀区','1076', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330921', '岱山县','1077', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '330922', '嵊泗县','1078', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331000', '台州市','1079', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331001', '市辖区','1080', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331002', '椒江区','1081', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331003', '黄岩区','1082', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331004', '路桥区','1083', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331022', '三门县','1084', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331023', '天台县','1085', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331024', '仙居县','1086', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331081', '温岭市','1087', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331082', '临海市','1088', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331083', '玉环市','1089', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331100', '丽水市','1090', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331101', '市辖区','1091', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331102', '莲都区','1092', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331121', '青田县','1093', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331122', '缙云县','1094', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331123', '遂昌县','1095', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331124', '松阳县','1096', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331125', '云和县','1097', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331126', '庆元县','1098', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331127', '景宁畲族自治县','1099', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '331181', '龙泉市','1100', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340000', '安徽省','1101', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340100', '合肥市','1102', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340101', '市辖区','1103', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340102', '瑶海区','1104', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340103', '庐阳区','1105', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340104', '蜀山区','1106', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340111', '包河区','1107', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340121', '长丰县','1108', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340122', '肥东县','1109', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340123', '肥西县','1110', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340124', '庐江县','1111', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340181', '巢湖市','1112', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340200', '芜湖市','1113', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340201', '市辖区','1114', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340202', '镜湖区','1115', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340203', '弋江区','1116', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340207', '鸠江区','1117', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340208', '三山区','1118', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340221', '芜湖县','1119', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340222', '繁昌县','1120', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340223', '南陵县','1121', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340281', '无为市','1122', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340300', '蚌埠市','1123', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340301', '市辖区','1124', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340302', '龙子湖区','1125', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340303', '蚌山区','1126', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340304', '禹会区','1127', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340311', '淮上区','1128', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340321', '怀远县','1129', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340322', '五河县','1130', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340323', '固镇县','1131', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340400', '淮南市','1132', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340401', '市辖区','1133', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340402', '大通区','1134', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340403', '田家庵区','1135', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340404', '谢家集区','1136', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340405', '八公山区','1137', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340406', '潘集区','1138', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340421', '凤台县','1139', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340422', '寿县','1140', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340500', '马鞍山市','1141', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340501', '市辖区','1142', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340503', '花山区','1143', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340504', '雨山区','1144', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340506', '博望区','1145', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340521', '当涂县','1146', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340522', '含山县','1147', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340523', '和县','1148', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340600', '淮北市','1149', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340601', '市辖区','1150', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340602', '杜集区','1151', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340603', '相山区','1152', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340604', '烈山区','1153', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340621', '濉溪县','1154', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340700', '铜陵市','1155', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340701', '市辖区','1156', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340705', '铜官区','1157', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340706', '义安区','1158', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340711', '郊区','1159', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340722', '枞阳县','1160', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340800', '安庆市','1161', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340801', '市辖区','1162', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340802', '迎江区','1163', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340803', '大观区','1164', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340811', '宜秀区','1165', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340822', '怀宁县','1166', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340825', '太湖县','1167', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340826', '宿松县','1168', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340827', '望江县','1169', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340828', '岳西县','1170', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340881', '桐城市','1171', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '340882', '潜山市','1172', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341000', '黄山市','1173', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341001', '市辖区','1174', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341002', '屯溪区','1175', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341003', '黄山区','1176', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341004', '徽州区','1177', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341021', '歙县','1178', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341022', '休宁县','1179', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341023', '黟县','1180', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341024', '祁门县','1181', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341100', '滁州市','1182', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341101', '市辖区','1183', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341102', '琅琊区','1184', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341103', '南谯区','1185', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341122', '来安县','1186', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341124', '全椒县','1187', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341125', '定远县','1188', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341126', '凤阳县','1189', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341181', '天长市','1190', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341182', '明光市','1191', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341200', '阜阳市','1192', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341201', '市辖区','1193', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341202', '颍州区','1194', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341203', '颍东区','1195', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341204', '颍泉区','1196', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341221', '临泉县','1197', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341222', '太和县','1198', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341225', '阜南县','1199', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341226', '颍上县','1200', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341282', '界首市','1201', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341300', '宿州市','1202', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341301', '市辖区','1203', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341302', '埇桥区','1204', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341321', '砀山县','1205', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341322', '萧县','1206', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341323', '灵璧县','1207', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341324', '泗县','1208', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341500', '六安市','1209', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341501', '市辖区','1210', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341502', '金安区','1211', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341503', '裕安区','1212', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341504', '叶集区','1213', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341522', '霍邱县','1214', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341523', '舒城县','1215', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341524', '金寨县','1216', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341525', '霍山县','1217', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341600', '亳州市','1218', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341601', '市辖区','1219', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341602', '谯城区','1220', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341621', '涡阳县','1221', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341622', '蒙城县','1222', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341623', '利辛县','1223', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341700', '池州市','1224', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341701', '市辖区','1225', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341702', '贵池区','1226', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341721', '东至县','1227', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341722', '石台县','1228', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341723', '青阳县','1229', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341800', '宣城市','1230', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341801', '市辖区','1231', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341802', '宣州区','1232', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341821', '郎溪县','1233', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341823', '泾县','1234', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341824', '绩溪县','1235', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341825', '旌德县','1236', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341881', '宁国市','1237', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '341882', '广德市','1238', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350000', '福建省','1239', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350100', '福州市','1240', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350101', '市辖区','1241', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350102', '鼓楼区','1242', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350103', '台江区','1243', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350104', '仓山区','1244', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350105', '马尾区','1245', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350111', '晋安区','1246', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350112', '长乐区','1247', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350121', '闽侯县','1248', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350122', '连江县','1249', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350123', '罗源县','1250', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350124', '闽清县','1251', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350125', '永泰县','1252', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350128', '平潭县','1253', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350181', '福清市','1254', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350200', '厦门市','1255', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350201', '市辖区','1256', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350203', '思明区','1257', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350205', '海沧区','1258', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350206', '湖里区','1259', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350211', '集美区','1260', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350212', '同安区','1261', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350213', '翔安区','1262', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350300', '莆田市','1263', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350301', '市辖区','1264', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350302', '城厢区','1265', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350303', '涵江区','1266', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350304', '荔城区','1267', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350305', '秀屿区','1268', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350322', '仙游县','1269', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350400', '三明市','1270', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350401', '市辖区','1271', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350402', '梅列区','1272', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350403', '三元区','1273', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350421', '明溪县','1274', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350423', '清流县','1275', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350424', '宁化县','1276', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350425', '大田县','1277', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350426', '尤溪县','1278', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350427', '沙县','1279', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350428', '将乐县','1280', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350429', '泰宁县','1281', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350430', '建宁县','1282', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350481', '永安市','1283', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350500', '泉州市','1284', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350501', '市辖区','1285', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350502', '鲤城区','1286', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350503', '丰泽区','1287', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350504', '洛江区','1288', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350505', '泉港区','1289', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350521', '惠安县','1290', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350524', '安溪县','1291', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350525', '永春县','1292', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350526', '德化县','1293', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350527', '金门县','1294', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350581', '石狮市','1295', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350582', '晋江市','1296', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350583', '南安市','1297', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350600', '漳州市','1298', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350601', '市辖区','1299', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350602', '芗城区','1300', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350603', '龙文区','1301', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350622', '云霄县','1302', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350623', '漳浦县','1303', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350624', '诏安县','1304', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350625', '长泰县','1305', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350626', '东山县','1306', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350627', '南靖县','1307', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350628', '平和县','1308', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350629', '华安县','1309', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350681', '龙海市','1310', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350700', '南平市','1311', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350701', '市辖区','1312', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350702', '延平区','1313', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350703', '建阳区','1314', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350721', '顺昌县','1315', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350722', '浦城县','1316', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350723', '光泽县','1317', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350724', '松溪县','1318', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350725', '政和县','1319', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350781', '邵武市','1320', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350782', '武夷山市','1321', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350783', '建瓯市','1322', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350800', '龙岩市','1323', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350801', '市辖区','1324', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350802', '新罗区','1325', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350803', '永定区','1326', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350821', '长汀县','1327', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350823', '上杭县','1328', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350824', '武平县','1329', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350825', '连城县','1330', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350881', '漳平市','1331', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350900', '宁德市','1332', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350901', '市辖区','1333', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350902', '蕉城区','1334', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350921', '霞浦县','1335', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350922', '古田县','1336', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350923', '屏南县','1337', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350924', '寿宁县','1338', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350925', '周宁县','1339', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350926', '柘荣县','1340', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350981', '福安市','1341', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '350982', '福鼎市','1342', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360000', '江西省','1343', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360100', '南昌市','1344', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360101', '市辖区','1345', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360102', '东湖区','1346', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360103', '西湖区','1347', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360104', '青云谱区','1348', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360111', '青山湖区','1349', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360112', '新建区','1350', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360113', '红谷滩区','1351', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360121', '南昌县','1352', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360123', '安义县','1353', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360124', '进贤县','1354', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360200', '景德镇市','1355', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360201', '市辖区','1356', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360202', '昌江区','1357', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360203', '珠山区','1358', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360222', '浮梁县','1359', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360281', '乐平市','1360', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360300', '萍乡市','1361', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360301', '市辖区','1362', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360302', '安源区','1363', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360313', '湘东区','1364', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360321', '莲花县','1365', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360322', '上栗县','1366', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360323', '芦溪县','1367', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360400', '九江市','1368', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360401', '市辖区','1369', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360402', '濂溪区','1370', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360403', '浔阳区','1371', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360404', '柴桑区','1372', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360423', '武宁县','1373', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360424', '修水县','1374', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360425', '永修县','1375', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360426', '德安县','1376', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360428', '都昌县','1377', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360429', '湖口县','1378', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360430', '彭泽县','1379', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360481', '瑞昌市','1380', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360482', '共青城市','1381', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360483', '庐山市','1382', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360500', '新余市','1383', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360501', '市辖区','1384', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360502', '渝水区','1385', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360521', '分宜县','1386', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360600', '鹰潭市','1387', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360601', '市辖区','1388', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360602', '月湖区','1389', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360603', '余江区','1390', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360681', '贵溪市','1391', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360700', '赣州市','1392', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360701', '市辖区','1393', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360702', '章贡区','1394', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360703', '南康区','1395', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360704', '赣县区','1396', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360722', '信丰县','1397', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360723', '大余县','1398', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360724', '上犹县','1399', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360725', '崇义县','1400', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360726', '安远县','1401', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360727', '龙南县','1402', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360728', '定南县','1403', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360729', '全南县','1404', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360730', '宁都县','1405', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360731', '于都县','1406', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360732', '兴国县','1407', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360733', '会昌县','1408', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360734', '寻乌县','1409', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360735', '石城县','1410', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360781', '瑞金市','1411', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360800', '吉安市','1412', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360801', '市辖区','1413', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360802', '吉州区','1414', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360803', '青原区','1415', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360821', '吉安县','1416', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360822', '吉水县','1417', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360823', '峡江县','1418', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360824', '新干县','1419', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360825', '永丰县','1420', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360826', '泰和县','1421', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360827', '遂川县','1422', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360828', '万安县','1423', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360829', '安福县','1424', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360830', '永新县','1425', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360881', '井冈山市','1426', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360900', '宜春市','1427', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360901', '市辖区','1428', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360902', '袁州区','1429', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360921', '奉新县','1430', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360922', '万载县','1431', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360923', '上高县','1432', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360924', '宜丰县','1433', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360925', '靖安县','1434', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360926', '铜鼓县','1435', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360981', '丰城市','1436', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360982', '樟树市','1437', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '360983', '高安市','1438', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361000', '抚州市','1439', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361001', '市辖区','1440', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361002', '临川区','1441', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361003', '东乡区','1442', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361021', '南城县','1443', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361022', '黎川县','1444', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361023', '南丰县','1445', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361024', '崇仁县','1446', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361025', '乐安县','1447', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361026', '宜黄县','1448', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361027', '金溪县','1449', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361028', '资溪县','1450', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361030', '广昌县','1451', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361100', '上饶市','1452', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361101', '市辖区','1453', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361102', '信州区','1454', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361103', '广丰区','1455', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361104', '广信区','1456', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361123', '玉山县','1457', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361124', '铅山县','1458', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361125', '横峰县','1459', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361126', '弋阳县','1460', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361127', '余干县','1461', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361128', '鄱阳县','1462', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361129', '万年县','1463', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361130', '婺源县','1464', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '361181', '德兴市','1465', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370000', '山东省','1466', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370100', '济南市','1467', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370101', '市辖区','1468', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370102', '历下区','1469', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370103', '市中区','1470', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370104', '槐荫区','1471', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370105', '天桥区','1472', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370112', '历城区','1473', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370113', '长清区','1474', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370114', '章丘区','1475', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370115', '济阳区','1476', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370116', '莱芜区','1477', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370117', '钢城区','1478', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370124', '平阴县','1479', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370126', '商河县','1480', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370200', '青岛市','1481', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370201', '市辖区','1482', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370202', '市南区','1483', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370203', '市北区','1484', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370211', '黄岛区','1485', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370212', '崂山区','1486', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370213', '李沧区','1487', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370214', '城阳区','1488', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370215', '即墨区','1489', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370281', '胶州市','1490', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370283', '平度市','1491', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370285', '莱西市','1492', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370300', '淄博市','1493', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370301', '市辖区','1494', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370302', '淄川区','1495', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370303', '张店区','1496', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370304', '博山区','1497', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370305', '临淄区','1498', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370306', '周村区','1499', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370321', '桓台县','1500', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370322', '高青县','1501', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370323', '沂源县','1502', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370400', '枣庄市','1503', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370401', '市辖区','1504', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370402', '市中区','1505', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370403', '薛城区','1506', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370404', '峄城区','1507', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370405', '台儿庄区','1508', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370406', '山亭区','1509', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370481', '滕州市','1510', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370500', '东营市','1511', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370501', '市辖区','1512', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370502', '东营区','1513', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370503', '河口区','1514', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370505', '垦利区','1515', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370522', '利津县','1516', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370523', '广饶县','1517', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370600', '烟台市','1518', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370601', '市辖区','1519', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370602', '芝罘区','1520', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370611', '福山区','1521', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370612', '牟平区','1522', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370613', '莱山区','1523', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370634', '长岛县','1524', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370681', '龙口市','1525', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370682', '莱阳市','1526', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370683', '莱州市','1527', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370684', '蓬莱市','1528', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370685', '招远市','1529', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370686', '栖霞市','1530', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370687', '海阳市','1531', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370700', '潍坊市','1532', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370701', '市辖区','1533', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370702', '潍城区','1534', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370703', '寒亭区','1535', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370704', '坊子区','1536', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370705', '奎文区','1537', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370724', '临朐县','1538', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370725', '昌乐县','1539', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370781', '青州市','1540', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370782', '诸城市','1541', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370783', '寿光市','1542', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370784', '安丘市','1543', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370785', '高密市','1544', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370786', '昌邑市','1545', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370800', '济宁市','1546', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370801', '市辖区','1547', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370811', '任城区','1548', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370812', '兖州区','1549', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370826', '微山县','1550', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370827', '鱼台县','1551', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370828', '金乡县','1552', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370829', '嘉祥县','1553', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370830', '汶上县','1554', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370831', '泗水县','1555', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370832', '梁山县','1556', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370881', '曲阜市','1557', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370883', '邹城市','1558', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370900', '泰安市','1559', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370901', '市辖区','1560', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370902', '泰山区','1561', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370911', '岱岳区','1562', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370921', '宁阳县','1563', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370923', '东平县','1564', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370982', '新泰市','1565', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '370983', '肥城市','1566', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371000', '威海市','1567', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371001', '市辖区','1568', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371002', '环翠区','1569', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371003', '文登区','1570', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371082', '荣成市','1571', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371083', '乳山市','1572', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371100', '日照市','1573', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371101', '市辖区','1574', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371102', '东港区','1575', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371103', '岚山区','1576', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371121', '五莲县','1577', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371122', '莒县','1578', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371300', '临沂市','1579', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371301', '市辖区','1580', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371302', '兰山区','1581', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371311', '罗庄区','1582', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371312', '河东区','1583', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371321', '沂南县','1584', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371322', '郯城县','1585', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371323', '沂水县','1586', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371324', '兰陵县','1587', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371325', '费县','1588', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371326', '平邑县','1589', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371327', '莒南县','1590', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371328', '蒙阴县','1591', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371329', '临沭县','1592', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371400', '德州市','1593', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371401', '市辖区','1594', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371402', '德城区','1595', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371403', '陵城区','1596', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371422', '宁津县','1597', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371423', '庆云县','1598', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371424', '临邑县','1599', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371425', '齐河县','1600', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371426', '平原县','1601', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371427', '夏津县','1602', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371428', '武城县','1603', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371481', '乐陵市','1604', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371482', '禹城市','1605', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371500', '聊城市','1606', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371501', '市辖区','1607', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371502', '东昌府区','1608', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371503', '茌平区','1609', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371521', '阳谷县','1610', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371522', '莘县','1611', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371524', '东阿县','1612', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371525', '冠县','1613', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371526', '高唐县','1614', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371581', '临清市','1615', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371600', '滨州市','1616', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371601', '市辖区','1617', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371602', '滨城区','1618', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371603', '沾化区','1619', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371621', '惠民县','1620', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371622', '阳信县','1621', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371623', '无棣县','1622', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371625', '博兴县','1623', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371681', '邹平市','1624', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371700', '菏泽市','1625', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371701', '市辖区','1626', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371702', '牡丹区','1627', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371703', '定陶区','1628', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371721', '曹县','1629', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371722', '单县','1630', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371723', '成武县','1631', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371724', '巨野县','1632', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371725', '郓城县','1633', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371726', '鄄城县','1634', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '371728', '东明县','1635', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410000', '河南省','1636', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410100', '郑州市','1637', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410101', '市辖区','1638', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410102', '中原区','1639', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410103', '二七区','1640', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410104', '管城回族区','1641', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410105', '金水区','1642', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410106', '上街区','1643', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410108', '惠济区','1644', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410122', '中牟县','1645', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410181', '巩义市','1646', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410182', '荥阳市','1647', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410183', '新密市','1648', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410184', '新郑市','1649', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410185', '登封市','1650', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410200', '开封市','1651', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410201', '市辖区','1652', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410202', '龙亭区','1653', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410203', '顺河回族区','1654', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410204', '鼓楼区','1655', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410205', '禹王台区','1656', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410212', '祥符区','1657', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410221', '杞县','1658', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410222', '通许县','1659', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410223', '尉氏县','1660', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410225', '兰考县','1661', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410300', '洛阳市','1662', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410301', '市辖区','1663', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410302', '老城区','1664', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410303', '西工区','1665', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410304', '瀍河回族区','1666', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410305', '涧西区','1667', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410306', '吉利区','1668', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410311', '洛龙区','1669', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410322', '孟津县','1670', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410323', '新安县','1671', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410324', '栾川县','1672', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410325', '嵩县','1673', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410326', '汝阳县','1674', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410327', '宜阳县','1675', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410328', '洛宁县','1676', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410329', '伊川县','1677', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410381', '偃师市','1678', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410400', '平顶山市','1679', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410401', '市辖区','1680', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410402', '新华区','1681', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410403', '卫东区','1682', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410404', '石龙区','1683', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410411', '湛河区','1684', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410421', '宝丰县','1685', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410422', '叶县','1686', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410423', '鲁山县','1687', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410425', '郏县','1688', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410481', '舞钢市','1689', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410482', '汝州市','1690', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410500', '安阳市','1691', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410501', '市辖区','1692', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410502', '文峰区','1693', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410503', '北关区','1694', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410505', '殷都区','1695', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410506', '龙安区','1696', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410522', '安阳县','1697', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410523', '汤阴县','1698', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410526', '滑县','1699', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410527', '内黄县','1700', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410581', '林州市','1701', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410600', '鹤壁市','1702', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410601', '市辖区','1703', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410602', '鹤山区','1704', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410603', '山城区','1705', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410611', '淇滨区','1706', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410621', '浚县','1707', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410622', '淇县','1708', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410700', '新乡市','1709', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410701', '市辖区','1710', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410702', '红旗区','1711', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410703', '卫滨区','1712', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410704', '凤泉区','1713', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410711', '牧野区','1714', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410721', '新乡县','1715', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410724', '获嘉县','1716', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410725', '原阳县','1717', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410726', '延津县','1718', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410727', '封丘县','1719', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410781', '卫辉市','1720', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410782', '辉县市','1721', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410783', '长垣市','1722', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410800', '焦作市','1723', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410801', '市辖区','1724', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410802', '解放区','1725', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410803', '中站区','1726', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410804', '马村区','1727', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410811', '山阳区','1728', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410821', '修武县','1729', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410822', '博爱县','1730', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410823', '武陟县','1731', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410825', '温县','1732', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410882', '沁阳市','1733', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410883', '孟州市','1734', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410900', '濮阳市','1735', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410901', '市辖区','1736', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410902', '华龙区','1737', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410922', '清丰县','1738', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410923', '南乐县','1739', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410926', '范县','1740', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410927', '台前县','1741', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '410928', '濮阳县','1742', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411000', '许昌市','1743', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411001', '市辖区','1744', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411002', '魏都区','1745', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411003', '建安区','1746', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411024', '鄢陵县','1747', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411025', '襄城县','1748', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411081', '禹州市','1749', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411082', '长葛市','1750', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411100', '漯河市','1751', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411101', '市辖区','1752', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411102', '源汇区','1753', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411103', '郾城区','1754', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411104', '召陵区','1755', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411121', '舞阳县','1756', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411122', '临颍县','1757', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411200', '三门峡市','1758', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411201', '市辖区','1759', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411202', '湖滨区','1760', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411203', '陕州区','1761', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411221', '渑池县','1762', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411224', '卢氏县','1763', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411281', '义马市','1764', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411282', '灵宝市','1765', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411300', '南阳市','1766', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411301', '市辖区','1767', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411302', '宛城区','1768', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411303', '卧龙区','1769', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411321', '南召县','1770', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411322', '方城县','1771', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411323', '西峡县','1772', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411324', '镇平县','1773', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411325', '内乡县','1774', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411326', '淅川县','1775', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411327', '社旗县','1776', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411328', '唐河县','1777', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411329', '新野县','1778', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411330', '桐柏县','1779', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411381', '邓州市','1780', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411400', '商丘市','1781', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411401', '市辖区','1782', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411402', '梁园区','1783', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411403', '睢阳区','1784', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411421', '民权县','1785', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411422', '睢县','1786', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411423', '宁陵县','1787', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411424', '柘城县','1788', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411425', '虞城县','1789', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411426', '夏邑县','1790', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411481', '永城市','1791', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411500', '信阳市','1792', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411501', '市辖区','1793', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411502', '浉河区','1794', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411503', '平桥区','1795', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411521', '罗山县','1796', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411522', '光山县','1797', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411523', '新县','1798', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411524', '商城县','1799', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411525', '固始县','1800', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411526', '潢川县','1801', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411527', '淮滨县','1802', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411528', '息县','1803', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411600', '周口市','1804', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411601', '市辖区','1805', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411602', '川汇区','1806', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411603', '淮阳区','1807', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411621', '扶沟县','1808', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411622', '西华县','1809', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411623', '商水县','1810', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411624', '沈丘县','1811', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411625', '郸城县','1812', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411627', '太康县','1813', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411628', '鹿邑县','1814', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411681', '项城市','1815', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411700', '驻马店市','1816', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411701', '市辖区','1817', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411702', '驿城区','1818', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411721', '西平县','1819', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411722', '上蔡县','1820', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411723', '平舆县','1821', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411724', '正阳县','1822', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411725', '确山县','1823', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411726', '泌阳县','1824', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411727', '汝南县','1825', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411728', '遂平县','1826', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '411729', '新蔡县','1827', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '419001', '济源市','1828', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420000', '湖北省','1829', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420100', '武汉市','1830', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420101', '市辖区','1831', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420102', '江岸区','1832', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420103', '江汉区','1833', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420104', '硚口区','1834', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420105', '汉阳区','1835', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420106', '武昌区','1836', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420107', '青山区','1837', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420111', '洪山区','1838', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420112', '东西湖区','1839', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420113', '汉南区','1840', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420114', '蔡甸区','1841', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420115', '江夏区','1842', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420116', '黄陂区','1843', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420117', '新洲区','1844', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420200', '黄石市','1845', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420201', '市辖区','1846', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420202', '黄石港区','1847', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420203', '西塞山区','1848', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420204', '下陆区','1849', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420205', '铁山区','1850', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420222', '阳新县','1851', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420281', '大冶市','1852', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420300', '十堰市','1853', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420301', '市辖区','1854', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420302', '茅箭区','1855', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420303', '张湾区','1856', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420304', '郧阳区','1857', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420322', '郧西县','1858', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420323', '竹山县','1859', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420324', '竹溪县','1860', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420325', '房县','1861', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420381', '丹江口市','1862', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420500', '宜昌市','1863', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420501', '市辖区','1864', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420502', '西陵区','1865', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420503', '伍家岗区','1866', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420504', '点军区','1867', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420505', '猇亭区','1868', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420506', '夷陵区','1869', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420525', '远安县','1870', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420526', '兴山县','1871', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420527', '秭归县','1872', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420528', '长阳土家族自治县','1873', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420529', '五峰土家族自治县','1874', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420581', '宜都市','1875', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420582', '当阳市','1876', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420583', '枝江市','1877', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420600', '襄阳市','1878', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420601', '市辖区','1879', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420602', '襄城区','1880', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420606', '樊城区','1881', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420607', '襄州区','1882', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420624', '南漳县','1883', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420625', '谷城县','1884', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420626', '保康县','1885', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420682', '老河口市','1886', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420683', '枣阳市','1887', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420684', '宜城市','1888', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420700', '鄂州市','1889', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420701', '市辖区','1890', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420702', '梁子湖区','1891', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420703', '华容区','1892', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420704', '鄂城区','1893', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420800', '荆门市','1894', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420801', '市辖区','1895', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420802', '东宝区','1896', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420804', '掇刀区','1897', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420822', '沙洋县','1898', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420881', '钟祥市','1899', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420882', '京山市','1900', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420900', '孝感市','1901', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420901', '市辖区','1902', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420902', '孝南区','1903', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420921', '孝昌县','1904', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420922', '大悟县','1905', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420923', '云梦县','1906', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420981', '应城市','1907', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420982', '安陆市','1908', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '420984', '汉川市','1909', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421000', '荆州市','1910', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421001', '市辖区','1911', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421002', '沙市区','1912', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421003', '荆州区','1913', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421022', '公安县','1914', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421023', '监利县','1915', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421024', '江陵县','1916', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421081', '石首市','1917', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421083', '洪湖市','1918', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421087', '松滋市','1919', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421100', '黄冈市','1920', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421101', '市辖区','1921', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421102', '黄州区','1922', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421121', '团风县','1923', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421122', '红安县','1924', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421123', '罗田县','1925', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421124', '英山县','1926', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421125', '浠水县','1927', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421126', '蕲春县','1928', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421127', '黄梅县','1929', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421181', '麻城市','1930', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421182', '武穴市','1931', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421200', '咸宁市','1932', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421201', '市辖区','1933', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421202', '咸安区','1934', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421221', '嘉鱼县','1935', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421222', '通城县','1936', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421223', '崇阳县','1937', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421224', '通山县','1938', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421281', '赤壁市','1939', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421300', '随州市','1940', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421301', '市辖区','1941', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421303', '曾都区','1942', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421321', '随县','1943', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '421381', '广水市','1944', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422800', '恩施土家族苗族自治州','1945', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422801', '恩施市','1946', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422802', '利川市','1947', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422822', '建始县','1948', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422823', '巴东县','1949', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422825', '宣恩县','1950', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422826', '咸丰县','1951', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422827', '来凤县','1952', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '422828', '鹤峰县','1953', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '429004', '仙桃市','1954', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '429005', '潜江市','1955', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '429006', '天门市','1956', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '429021', '神农架林区','1957', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430000', '湖南省','1958', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430100', '长沙市','1959', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430101', '市辖区','1960', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430102', '芙蓉区','1961', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430103', '天心区','1962', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430104', '岳麓区','1963', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430105', '开福区','1964', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430111', '雨花区','1965', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430112', '望城区','1966', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430121', '长沙县','1967', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430181', '浏阳市','1968', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430182', '宁乡市','1969', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430200', '株洲市','1970', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430201', '市辖区','1971', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430202', '荷塘区','1972', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430203', '芦淞区','1973', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430204', '石峰区','1974', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430211', '天元区','1975', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430212', '渌口区','1976', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430223', '攸县','1977', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430224', '茶陵县','1978', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430225', '炎陵县','1979', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430281', '醴陵市','1980', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430300', '湘潭市','1981', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430301', '市辖区','1982', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430302', '雨湖区','1983', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430304', '岳塘区','1984', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430321', '湘潭县','1985', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430381', '湘乡市','1986', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430382', '韶山市','1987', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430400', '衡阳市','1988', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430401', '市辖区','1989', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430405', '珠晖区','1990', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430406', '雁峰区','1991', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430407', '石鼓区','1992', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430408', '蒸湘区','1993', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430412', '南岳区','1994', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430421', '衡阳县','1995', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430422', '衡南县','1996', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430423', '衡山县','1997', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430424', '衡东县','1998', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430426', '祁东县','1999', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430481', '耒阳市','2000', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430482', '常宁市','2001', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430500', '邵阳市','2002', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430501', '市辖区','2003', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430502', '双清区','2004', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430503', '大祥区','2005', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430511', '北塔区','2006', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430522', '新邵县','2007', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430523', '邵阳县','2008', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430524', '隆回县','2009', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430525', '洞口县','2010', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430527', '绥宁县','2011', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430528', '新宁县','2012', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430529', '城步苗族自治县','2013', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430581', '武冈市','2014', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430582', '邵东市','2015', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430600', '岳阳市','2016', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430601', '市辖区','2017', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430602', '岳阳楼区','2018', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430603', '云溪区','2019', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430611', '君山区','2020', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430621', '岳阳县','2021', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430623', '华容县','2022', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430624', '湘阴县','2023', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430626', '平江县','2024', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430681', '汨罗市','2025', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430682', '临湘市','2026', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430700', '常德市','2027', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430701', '市辖区','2028', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430702', '武陵区','2029', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430703', '鼎城区','2030', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430721', '安乡县','2031', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430722', '汉寿县','2032', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430723', '澧县','2033', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430724', '临澧县','2034', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430725', '桃源县','2035', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430726', '石门县','2036', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430781', '津市市','2037', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430800', '张家界市','2038', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430801', '市辖区','2039', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430802', '永定区','2040', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430811', '武陵源区','2041', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430821', '慈利县','2042', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430822', '桑植县','2043', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430900', '益阳市','2044', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430901', '市辖区','2045', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430902', '资阳区','2046', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430903', '赫山区','2047', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430921', '南县','2048', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430922', '桃江县','2049', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430923', '安化县','2050', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '430981', '沅江市','2051', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431000', '郴州市','2052', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431001', '市辖区','2053', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431002', '北湖区','2054', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431003', '苏仙区','2055', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431021', '桂阳县','2056', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431022', '宜章县','2057', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431023', '永兴县','2058', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431024', '嘉禾县','2059', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431025', '临武县','2060', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431026', '汝城县','2061', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431027', '桂东县','2062', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431028', '安仁县','2063', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431081', '资兴市','2064', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431100', '永州市','2065', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431101', '市辖区','2066', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431102', '零陵区','2067', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431103', '冷水滩区','2068', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431121', '祁阳县','2069', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431122', '东安县','2070', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431123', '双牌县','2071', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431124', '道县','2072', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431125', '江永县','2073', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431126', '宁远县','2074', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431127', '蓝山县','2075', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431128', '新田县','2076', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431129', '江华瑶族自治县','2077', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431200', '怀化市','2078', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431201', '市辖区','2079', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431202', '鹤城区','2080', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431221', '中方县','2081', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431222', '沅陵县','2082', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431223', '辰溪县','2083', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431224', '溆浦县','2084', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431225', '会同县','2085', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431226', '麻阳苗族自治县','2086', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431227', '新晃侗族自治县','2087', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431228', '芷江侗族自治县','2088', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431229', '靖州苗族侗族自治县','2089', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431230', '通道侗族自治县','2090', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431281', '洪江市','2091', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431300', '娄底市','2092', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431301', '市辖区','2093', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431302', '娄星区','2094', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431321', '双峰县','2095', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431322', '新化县','2096', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431381', '冷水江市','2097', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '431382', '涟源市','2098', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433100', '湘西土家族苗族自治州','2099', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433101', '吉首市','2100', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433122', '泸溪县','2101', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433123', '凤凰县','2102', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433124', '花垣县','2103', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433125', '保靖县','2104', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433126', '古丈县','2105', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433127', '永顺县','2106', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '433130', '龙山县','2107', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440000', '广东省','2108', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440100', '广州市','2109', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440101', '市辖区','2110', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440103', '荔湾区','2111', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440104', '越秀区','2112', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440105', '海珠区','2113', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440106', '天河区','2114', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440111', '白云区','2115', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440112', '黄埔区','2116', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440113', '番禺区','2117', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440114', '花都区','2118', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440115', '南沙区','2119', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440117', '从化区','2120', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440118', '增城区','2121', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440200', '韶关市','2122', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440201', '市辖区','2123', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440203', '武江区','2124', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440204', '浈江区','2125', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440205', '曲江区','2126', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440222', '始兴县','2127', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440224', '仁化县','2128', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440229', '翁源县','2129', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440232', '乳源瑶族自治县','2130', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440233', '新丰县','2131', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440281', '乐昌市','2132', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440282', '南雄市','2133', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440300', '深圳市','2134', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440301', '市辖区','2135', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440303', '罗湖区','2136', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440304', '福田区','2137', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440305', '南山区','2138', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440306', '宝安区','2139', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440307', '龙岗区','2140', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440308', '盐田区','2141', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440309', '龙华区','2142', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440310', '坪山区','2143', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440311', '光明区','2144', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440400', '珠海市','2145', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440401', '市辖区','2146', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440402', '香洲区','2147', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440403', '斗门区','2148', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440404', '金湾区','2149', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440500', '汕头市','2150', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440501', '市辖区','2151', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440507', '龙湖区','2152', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440511', '金平区','2153', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440512', '濠江区','2154', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440513', '潮阳区','2155', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440514', '潮南区','2156', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440515', '澄海区','2157', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440523', '南澳县','2158', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440600', '佛山市','2159', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440601', '市辖区','2160', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440604', '禅城区','2161', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440605', '南海区','2162', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440606', '顺德区','2163', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440607', '三水区','2164', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440608', '高明区','2165', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440700', '江门市','2166', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440701', '市辖区','2167', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440703', '蓬江区','2168', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440704', '江海区','2169', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440705', '新会区','2170', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440781', '台山市','2171', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440783', '开平市','2172', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440784', '鹤山市','2173', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440785', '恩平市','2174', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440800', '湛江市','2175', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440801', '市辖区','2176', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440802', '赤坎区','2177', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440803', '霞山区','2178', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440804', '坡头区','2179', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440811', '麻章区','2180', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440823', '遂溪县','2181', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440825', '徐闻县','2182', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440881', '廉江市','2183', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440882', '雷州市','2184', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440883', '吴川市','2185', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440900', '茂名市','2186', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440901', '市辖区','2187', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440902', '茂南区','2188', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440904', '电白区','2189', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440981', '高州市','2190', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440982', '化州市','2191', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '440983', '信宜市','2192', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441200', '肇庆市','2193', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441201', '市辖区','2194', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441202', '端州区','2195', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441203', '鼎湖区','2196', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441204', '高要区','2197', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441223', '广宁县','2198', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441224', '怀集县','2199', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441225', '封开县','2200', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441226', '德庆县','2201', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441284', '四会市','2202', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441300', '惠州市','2203', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441301', '市辖区','2204', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441302', '惠城区','2205', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441303', '惠阳区','2206', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441322', '博罗县','2207', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441323', '惠东县','2208', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441324', '龙门县','2209', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441400', '梅州市','2210', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441401', '市辖区','2211', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441402', '梅江区','2212', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441403', '梅县区','2213', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441422', '大埔县','2214', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441423', '丰顺县','2215', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441424', '五华县','2216', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441426', '平远县','2217', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441427', '蕉岭县','2218', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441481', '兴宁市','2219', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441500', '汕尾市','2220', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441501', '市辖区','2221', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441502', '城区','2222', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441521', '海丰县','2223', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441523', '陆河县','2224', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441581', '陆丰市','2225', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441600', '河源市','2226', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441601', '市辖区','2227', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441602', '源城区','2228', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441621', '紫金县','2229', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441622', '龙川县','2230', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441623', '连平县','2231', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441624', '和平县','2232', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441625', '东源县','2233', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441700', '阳江市','2234', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441701', '市辖区','2235', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441702', '江城区','2236', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441704', '阳东区','2237', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441721', '阳西县','2238', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441781', '阳春市','2239', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441800', '清远市','2240', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441801', '市辖区','2241', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441802', '清城区','2242', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441803', '清新区','2243', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441821', '佛冈县','2244', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441823', '阳山县','2245', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441825', '连山壮族瑶族自治县','2246', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441826', '连南瑶族自治县','2247', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441881', '英德市','2248', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441882', '连州市','2249', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '441900', '东莞市','2250', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '442000', '中山市','2251', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445100', '潮州市','2252', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445101', '市辖区','2253', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445102', '湘桥区','2254', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445103', '潮安区','2255', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445122', '饶平县','2256', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445200', '揭阳市','2257', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445201', '市辖区','2258', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445202', '榕城区','2259', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445203', '揭东区','2260', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445222', '揭西县','2261', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445224', '惠来县','2262', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445281', '普宁市','2263', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445300', '云浮市','2264', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445301', '市辖区','2265', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445302', '云城区','2266', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445303', '云安区','2267', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445321', '新兴县','2268', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445322', '郁南县','2269', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '445381', '罗定市','2270', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450000', '广西壮族自治区','2271', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450100', '南宁市','2272', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450101', '市辖区','2273', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450102', '兴宁区','2274', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450103', '青秀区','2275', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450105', '江南区','2276', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450107', '西乡塘区','2277', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450108', '良庆区','2278', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450109', '邕宁区','2279', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450110', '武鸣区','2280', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450123', '隆安县','2281', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450124', '马山县','2282', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450125', '上林县','2283', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450126', '宾阳县','2284', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450127', '横县','2285', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450200', '柳州市','2286', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450201', '市辖区','2287', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450202', '城中区','2288', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450203', '鱼峰区','2289', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450204', '柳南区','2290', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450205', '柳北区','2291', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450206', '柳江区','2292', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450222', '柳城县','2293', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450223', '鹿寨县','2294', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450224', '融安县','2295', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450225', '融水苗族自治县','2296', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450226', '三江侗族自治县','2297', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450300', '桂林市','2298', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450301', '市辖区','2299', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450302', '秀峰区','2300', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450303', '叠彩区','2301', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450304', '象山区','2302', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450305', '七星区','2303', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450311', '雁山区','2304', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450312', '临桂区','2305', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450321', '阳朔县','2306', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450323', '灵川县','2307', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450324', '全州县','2308', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450325', '兴安县','2309', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450326', '永福县','2310', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450327', '灌阳县','2311', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450328', '龙胜各族自治县','2312', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450329', '资源县','2313', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450330', '平乐县','2314', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450381', '荔浦市','2315', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450332', '恭城瑶族自治县','2316', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450400', '梧州市','2317', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450401', '市辖区','2318', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450403', '万秀区','2319', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450405', '长洲区','2320', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450406', '龙圩区','2321', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450421', '苍梧县','2322', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450422', '藤县','2323', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450423', '蒙山县','2324', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450481', '岑溪市','2325', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450500', '北海市','2326', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450501', '市辖区','2327', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450502', '海城区','2328', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450503', '银海区','2329', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450512', '铁山港区','2330', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450521', '合浦县','2331', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450600', '防城港市','2332', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450601', '市辖区','2333', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450602', '港口区','2334', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450603', '防城区','2335', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450621', '上思县','2336', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450681', '东兴市','2337', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450700', '钦州市','2338', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450701', '市辖区','2339', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450702', '钦南区','2340', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450703', '钦北区','2341', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450721', '灵山县','2342', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450722', '浦北县','2343', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450800', '贵港市','2344', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450801', '市辖区','2345', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450802', '港北区','2346', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450803', '港南区','2347', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450804', '覃塘区','2348', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450821', '平南县','2349', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450881', '桂平市','2350', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450900', '玉林市','2351', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450901', '市辖区','2352', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450902', '玉州区','2353', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450903', '福绵区','2354', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450921', '容县','2355', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450922', '陆川县','2356', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450923', '博白县','2357', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450924', '兴业县','2358', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '450981', '北流市','2359', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451000', '百色市','2360', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451001', '市辖区','2361', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451002', '右江区','2362', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451003', '田阳区','2363', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451022', '田东县','2364', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451024', '德保县','2365', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451026', '那坡县','2366', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451027', '凌云县','2367', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451028', '乐业县','2368', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451029', '田林县','2369', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451030', '西林县','2370', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451031', '隆林各族自治县','2371', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451081', '靖西市','2372', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451082', '平果市','2373', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451100', '贺州市','2374', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451101', '市辖区','2375', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451102', '八步区','2376', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451103', '平桂区','2377', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451121', '昭平县','2378', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451122', '钟山县','2379', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451123', '富川瑶族自治县','2380', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451200', '河池市','2381', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451201', '市辖区','2382', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451202', '金城江区','2383', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451203', '宜州区','2384', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451221', '南丹县','2385', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451222', '天峨县','2386', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451223', '凤山县','2387', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451224', '东兰县','2388', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451225', '罗城仫佬族自治县','2389', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451226', '环江毛南族自治县','2390', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451227', '巴马瑶族自治县','2391', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451228', '都安瑶族自治县','2392', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451229', '大化瑶族自治县','2393', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451300', '来宾市','2394', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451301', '市辖区','2395', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451302', '兴宾区','2396', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451321', '忻城县','2397', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451322', '象州县','2398', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451323', '武宣县','2399', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451324', '金秀瑶族自治县','2400', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451381', '合山市','2401', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451400', '崇左市','2402', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451401', '市辖区','2403', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451402', '江州区','2404', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451421', '扶绥县','2405', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451422', '宁明县','2406', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451423', '龙州县','2407', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451424', '大新县','2408', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451425', '天等县','2409', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '451481', '凭祥市','2410', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460000', '海南省','2411', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460100', '海口市','2412', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460101', '市辖区','2413', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460105', '秀英区','2414', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460106', '龙华区','2415', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460107', '琼山区','2416', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460108', '美兰区','2417', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460200', '三亚市','2418', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460201', '市辖区','2419', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460202', '海棠区','2420', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460203', '吉阳区','2421', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460204', '天涯区','2422', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460205', '崖州区','2423', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460300', '三沙市','2424', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '460400', '儋州市','2425', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469001', '五指山市','2426', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469002', '琼海市','2427', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469005', '文昌市','2428', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469006', '万宁市','2429', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469007', '东方市','2430', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469021', '定安县','2431', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469022', '屯昌县','2432', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469023', '澄迈县','2433', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469024', '临高县','2434', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469025', '白沙黎族自治县','2435', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469026', '昌江黎族自治县','2436', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469027', '乐东黎族自治县','2437', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469028', '陵水黎族自治县','2438', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469029', '保亭黎族苗族自治县','2439', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '469030', '琼中黎族苗族自治县','2440', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500000', '重庆市','2441', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500100', '市辖区','2442', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500101', '万州区','2443', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500102', '涪陵区','2444', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500103', '渝中区','2445', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500104', '大渡口区','2446', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500105', '江北区','2447', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500106', '沙坪坝区','2448', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500107', '九龙坡区','2449', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500108', '南岸区','2450', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500109', '北碚区','2451', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500110', '綦江区','2452', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500111', '大足区','2453', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500112', '渝北区','2454', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500113', '巴南区','2455', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500114', '黔江区','2456', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500115', '长寿区','2457', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500116', '江津区','2458', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500117', '合川区','2459', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500118', '永川区','2460', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500119', '南川区','2461', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500120', '璧山区','2462', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500151', '铜梁区','2463', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500152', '潼南区','2464', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500153', '荣昌区','2465', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500154', '开州区','2466', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500155', '梁平区','2467', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500156', '武隆区','2468', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500229', '城口县','2469', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500230', '丰都县','2470', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500231', '垫江县','2471', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500233', '忠县','2472', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500235', '云阳县','2473', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500236', '奉节县','2474', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500237', '巫山县','2475', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500238', '巫溪县','2476', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500240', '石柱土家族自治县','2477', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500241', '秀山土家族苗族自治县','2478', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500242', '酉阳土家族苗族自治县','2479', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '500243', '彭水苗族土家族自治县','2480', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510000', '四川省','2481', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510100', '成都市','2482', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510101', '市辖区','2483', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510104', '锦江区','2484', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510105', '青羊区','2485', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510106', '金牛区','2486', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510107', '武侯区','2487', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510108', '成华区','2488', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510112', '龙泉驿区','2489', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510113', '青白江区','2490', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510114', '新都区','2491', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510115', '温江区','2492', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510116', '双流区','2493', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510117', '郫都区','2494', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510121', '金堂县','2495', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510129', '大邑县','2496', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510131', '蒲江县','2497', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510132', '新津县','2498', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510181', '都江堰市','2499', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510182', '彭州市','2500', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510183', '邛崃市','2501', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510184', '崇州市','2502', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510185', '简阳市','2503', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510300', '自贡市','2504', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510301', '市辖区','2505', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510302', '自流井区','2506', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510303', '贡井区','2507', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510304', '大安区','2508', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510311', '沿滩区','2509', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510321', '荣县','2510', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510322', '富顺县','2511', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510400', '攀枝花市','2512', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510401', '市辖区','2513', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510402', '东区','2514', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510403', '西区','2515', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510411', '仁和区','2516', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510421', '米易县','2517', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510422', '盐边县','2518', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510500', '泸州市','2519', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510501', '市辖区','2520', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510502', '江阳区','2521', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510503', '纳溪区','2522', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510504', '龙马潭区','2523', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510521', '泸县','2524', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510522', '合江县','2525', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510524', '叙永县','2526', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510525', '古蔺县','2527', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510600', '德阳市','2528', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510601', '市辖区','2529', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510603', '旌阳区','2530', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510604', '罗江区','2531', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510623', '中江县','2532', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510681', '广汉市','2533', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510682', '什邡市','2534', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510683', '绵竹市','2535', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510700', '绵阳市','2536', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510701', '市辖区','2537', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510703', '涪城区','2538', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510704', '游仙区','2539', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510705', '安州区','2540', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510722', '三台县','2541', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510723', '盐亭县','2542', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510725', '梓潼县','2543', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510726', '北川羌族自治县','2544', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510727', '平武县','2545', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510781', '江油市','2546', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510800', '广元市','2547', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510801', '市辖区','2548', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510802', '利州区','2549', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510811', '昭化区','2550', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510812', '朝天区','2551', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510821', '旺苍县','2552', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510822', '青川县','2553', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510823', '剑阁县','2554', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510824', '苍溪县','2555', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510900', '遂宁市','2556', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510901', '市辖区','2557', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510903', '船山区','2558', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510904', '安居区','2559', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510921', '蓬溪县','2560', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510923', '大英县','2561', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '510981', '射洪市','2562', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511000', '内江市','2563', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511001', '市辖区','2564', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511002', '市中区','2565', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511011', '东兴区','2566', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511024', '威远县','2567', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511025', '资中县','2568', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511083', '隆昌市','2569', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511100', '乐山市','2570', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511101', '市辖区','2571', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511102', '市中区','2572', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511111', '沙湾区','2573', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511112', '五通桥区','2574', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511113', '金口河区','2575', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511123', '犍为县','2576', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511124', '井研县','2577', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511126', '夹江县','2578', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511129', '沐川县','2579', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511132', '峨边彝族自治县','2580', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511133', '马边彝族自治县','2581', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511181', '峨眉山市','2582', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511300', '南充市','2583', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511301', '市辖区','2584', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511302', '顺庆区','2585', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511303', '高坪区','2586', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511304', '嘉陵区','2587', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511321', '南部县','2588', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511322', '营山县','2589', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511323', '蓬安县','2590', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511324', '仪陇县','2591', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511325', '西充县','2592', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511381', '阆中市','2593', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511400', '眉山市','2594', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511401', '市辖区','2595', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511402', '东坡区','2596', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511403', '彭山区','2597', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511421', '仁寿县','2598', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511423', '洪雅县','2599', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511424', '丹棱县','2600', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511425', '青神县','2601', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511500', '宜宾市','2602', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511501', '市辖区','2603', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511502', '翠屏区','2604', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511503', '南溪区','2605', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511504', '叙州区','2606', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511523', '江安县','2607', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511524', '长宁县','2608', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511525', '高县','2609', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511526', '珙县','2610', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511527', '筠连县','2611', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511528', '兴文县','2612', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511529', '屏山县','2613', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511600', '广安市','2614', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511601', '市辖区','2615', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511602', '广安区','2616', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511603', '前锋区','2617', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511621', '岳池县','2618', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511622', '武胜县','2619', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511623', '邻水县','2620', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511681', '华蓥市','2621', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511700', '达州市','2622', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511701', '市辖区','2623', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511702', '通川区','2624', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511703', '达川区','2625', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511722', '宣汉县','2626', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511723', '开江县','2627', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511724', '大竹县','2628', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511725', '渠县','2629', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511781', '万源市','2630', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511800', '雅安市','2631', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511801', '市辖区','2632', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511802', '雨城区','2633', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511803', '名山区','2634', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511822', '荥经县','2635', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511823', '汉源县','2636', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511824', '石棉县','2637', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511825', '天全县','2638', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511826', '芦山县','2639', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511827', '宝兴县','2640', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511900', '巴中市','2641', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511901', '市辖区','2642', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511902', '巴州区','2643', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511903', '恩阳区','2644', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511921', '通江县','2645', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511922', '南江县','2646', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '511923', '平昌县','2647', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '512000', '资阳市','2648', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '512001', '市辖区','2649', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '512002', '雁江区','2650', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '512021', '安岳县','2651', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '512022', '乐至县','2652', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513200', '阿坝藏族羌族自治州','2653', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513201', '马尔康市','2654', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513221', '汶川县','2655', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513222', '理县','2656', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513223', '茂县','2657', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513224', '松潘县','2658', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513225', '九寨沟县','2659', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513226', '金川县','2660', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513227', '小金县','2661', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513228', '黑水县','2662', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513230', '壤塘县','2663', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513231', '阿坝县','2664', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513232', '若尔盖县','2665', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513233', '红原县','2666', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513300', '甘孜藏族自治州','2667', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513301', '康定市','2668', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513322', '泸定县','2669', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513323', '丹巴县','2670', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513324', '九龙县','2671', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513325', '雅江县','2672', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513326', '道孚县','2673', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513327', '炉霍县','2674', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513328', '甘孜县','2675', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513329', '新龙县','2676', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513330', '德格县','2677', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513331', '白玉县','2678', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513332', '石渠县','2679', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513333', '色达县','2680', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513334', '理塘县','2681', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513335', '巴塘县','2682', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513336', '乡城县','2683', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513337', '稻城县','2684', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513338', '得荣县','2685', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513400', '凉山彝族自治州','2686', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513401', '西昌市','2687', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513422', '木里藏族自治县','2688', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513423', '盐源县','2689', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513424', '德昌县','2690', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513425', '会理县','2691', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513426', '会东县','2692', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513427', '宁南县','2693', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513428', '普格县','2694', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513429', '布拖县','2695', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513430', '金阳县','2696', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513431', '昭觉县','2697', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513432', '喜德县','2698', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513433', '冕宁县','2699', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513434', '越西县','2700', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513435', '甘洛县','2701', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513436', '美姑县','2702', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '513437', '雷波县','2703', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520000', '贵州省','2704', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520100', '贵阳市','2705', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520101', '市辖区','2706', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520102', '南明区','2707', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520103', '云岩区','2708', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520111', '花溪区','2709', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520112', '乌当区','2710', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520113', '白云区','2711', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520115', '观山湖区','2712', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520121', '开阳县','2713', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520122', '息烽县','2714', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520123', '修文县','2715', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520181', '清镇市','2716', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520200', '六盘水市','2717', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520201', '钟山区','2718', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520203', '六枝特区','2719', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520221', '水城县','2720', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520281', '盘州市','2721', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520300', '遵义市','2722', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520301', '市辖区','2723', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520302', '红花岗区','2724', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520303', '汇川区','2725', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520304', '播州区','2726', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520322', '桐梓县','2727', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520323', '绥阳县','2728', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520324', '正安县','2729', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520325', '道真仡佬族苗族自治县','2730', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520326', '务川仡佬族苗族自治县','2731', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520327', '凤冈县','2732', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520328', '湄潭县','2733', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520329', '余庆县','2734', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520330', '习水县','2735', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520381', '赤水市','2736', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520382', '仁怀市','2737', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520400', '安顺市','2738', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520401', '市辖区','2739', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520402', '西秀区','2740', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520403', '平坝区','2741', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520422', '普定县','2742', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520423', '镇宁布依族苗族自治县','2743', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520424', '关岭布依族苗族自治县','2744', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520425', '紫云苗族布依族自治县','2745', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520500', '毕节市','2746', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520501', '市辖区','2747', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520502', '七星关区','2748', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520521', '大方县','2749', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520522', '黔西县','2750', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520523', '金沙县','2751', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520524', '织金县','2752', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520525', '纳雍县','2753', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520526', '威宁彝族回族苗族自治县','2754', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520527', '赫章县','2755', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520600', '铜仁市','2756', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520601', '市辖区','2757', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520602', '碧江区','2758', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520603', '万山区','2759', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520621', '江口县','2760', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520622', '玉屏侗族自治县','2761', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520623', '石阡县','2762', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520624', '思南县','2763', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520625', '印江土家族苗族自治县','2764', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520626', '德江县','2765', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520627', '沿河土家族自治县','2766', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '520628', '松桃苗族自治县','2767', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522300', '黔西南布依族苗族自治州','2768', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522301', '兴义市','2769', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522302', '兴仁市','2770', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522323', '普安县','2771', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522324', '晴隆县','2772', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522325', '贞丰县','2773', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522326', '望谟县','2774', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522327', '册亨县','2775', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522328', '安龙县','2776', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522600', '黔东南苗族侗族自治州','2777', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522601', '凯里市','2778', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522622', '黄平县','2779', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522623', '施秉县','2780', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522624', '三穗县','2781', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522625', '镇远县','2782', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522626', '岑巩县','2783', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522627', '天柱县','2784', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522628', '锦屏县','2785', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522629', '剑河县','2786', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522630', '台江县','2787', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522631', '黎平县','2788', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522632', '榕江县','2789', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522633', '从江县','2790', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522634', '雷山县','2791', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522635', '麻江县','2792', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522636', '丹寨县','2793', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522700', '黔南布依族苗族自治州','2794', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522701', '都匀市','2795', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522702', '福泉市','2796', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522722', '荔波县','2797', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522723', '贵定县','2798', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522725', '瓮安县','2799', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522726', '独山县','2800', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522727', '平塘县','2801', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522728', '罗甸县','2802', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522729', '长顺县','2803', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522730', '龙里县','2804', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522731', '惠水县','2805', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '522732', '三都水族自治县','2806', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530000', '云南省','2807', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530100', '昆明市','2808', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530101', '市辖区','2809', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530102', '五华区','2810', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530103', '盘龙区','2811', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530111', '官渡区','2812', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530112', '西山区','2813', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530113', '东川区','2814', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530114', '呈贡区','2815', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530115', '晋宁区','2816', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530124', '富民县','2817', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530125', '宜良县','2818', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530126', '石林彝族自治县','2819', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530127', '嵩明县','2820', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530128', '禄劝彝族苗族自治县','2821', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530129', '寻甸回族彝族自治县','2822', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530181', '安宁市','2823', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530300', '曲靖市','2824', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530301', '市辖区','2825', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530302', '麒麟区','2826', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530303', '沾益区','2827', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530304', '马龙区','2828', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530322', '陆良县','2829', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530323', '师宗县','2830', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530324', '罗平县','2831', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530325', '富源县','2832', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530326', '会泽县','2833', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530381', '宣威市','2834', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530400', '玉溪市','2835', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530401', '市辖区','2836', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530402', '红塔区','2837', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530403', '江川区','2838', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530423', '通海县','2839', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530424', '华宁县','2840', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530425', '易门县','2841', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530426', '峨山彝族自治县','2842', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530427', '新平彝族傣族自治县','2843', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530428', '元江哈尼族彝族傣族自治县','2844', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530481', '澄江市','2845', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530500', '保山市','2846', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530501', '市辖区','2847', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530502', '隆阳区','2848', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530521', '施甸县','2849', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530523', '龙陵县','2850', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530524', '昌宁县','2851', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530581', '腾冲市','2852', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530600', '昭通市','2853', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530601', '市辖区','2854', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530602', '昭阳区','2855', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530621', '鲁甸县','2856', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530622', '巧家县','2857', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530623', '盐津县','2858', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530624', '大关县','2859', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530625', '永善县','2860', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530626', '绥江县','2861', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530627', '镇雄县','2862', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530628', '彝良县','2863', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530629', '威信县','2864', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530681', '水富市','2865', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530700', '丽江市','2866', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530701', '市辖区','2867', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530702', '古城区','2868', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530721', '玉龙纳西族自治县','2869', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530722', '永胜县','2870', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530723', '华坪县','2871', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530724', '宁蒗彝族自治县','2872', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530800', '普洱市','2873', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530801', '市辖区','2874', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530802', '思茅区','2875', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530821', '宁洱哈尼族彝族自治县','2876', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530822', '墨江哈尼族自治县','2877', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530823', '景东彝族自治县','2878', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530824', '景谷傣族彝族自治县','2879', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530825', '镇沅彝族哈尼族拉祜族自治县','2880', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530826', '江城哈尼族彝族自治县','2881', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530827', '孟连傣族拉祜族佤族自治县','2882', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530828', '澜沧拉祜族自治县','2883', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530829', '西盟佤族自治县','2884', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530900', '临沧市','2885', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530901', '市辖区','2886', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530902', '临翔区','2887', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530921', '凤庆县','2888', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530922', '云县','2889', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530923', '永德县','2890', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530924', '镇康县','2891', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530925', '双江拉祜族佤族布朗族傣族自治县','2892', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530926', '耿马傣族佤族自治县','2893', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '530927', '沧源佤族自治县','2894', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532300', '楚雄彝族自治州','2895', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532301', '楚雄市','2896', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532322', '双柏县','2897', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532323', '牟定县','2898', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532324', '南华县','2899', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532325', '姚安县','2900', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532326', '大姚县','2901', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532327', '永仁县','2902', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532328', '元谋县','2903', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532329', '武定县','2904', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532331', '禄丰县','2905', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532500', '红河哈尼族彝族自治州','2906', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532501', '个旧市','2907', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532502', '开远市','2908', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532503', '蒙自市','2909', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532504', '弥勒市','2910', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532523', '屏边苗族自治县','2911', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532524', '建水县','2912', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532525', '石屏县','2913', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532527', '泸西县','2914', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532528', '元阳县','2915', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532529', '红河县','2916', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532530', '金平苗族瑶族傣族自治县','2917', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532531', '绿春县','2918', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532532', '河口瑶族自治县','2919', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532600', '文山壮族苗族自治州','2920', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532601', '文山市','2921', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532622', '砚山县','2922', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532623', '西畴县','2923', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532624', '麻栗坡县','2924', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532625', '马关县','2925', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532626', '丘北县','2926', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532627', '广南县','2927', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532628', '富宁县','2928', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532800', '西双版纳傣族自治州','2929', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532801', '景洪市','2930', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532822', '勐海县','2931', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532823', '勐腊县','2932', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532900', '大理白族自治州','2933', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532901', '大理市','2934', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532922', '漾濞彝族自治县','2935', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532923', '祥云县','2936', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532924', '宾川县','2937', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532925', '弥渡县','2938', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532926', '南涧彝族自治县','2939', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532927', '巍山彝族回族自治县','2940', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532928', '永平县','2941', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532929', '云龙县','2942', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532930', '洱源县','2943', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532931', '剑川县','2944', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '532932', '鹤庆县','2945', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533100', '德宏傣族景颇族自治州','2946', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533102', '瑞丽市','2947', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533103', '芒市','2948', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533122', '梁河县','2949', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533123', '盈江县','2950', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533124', '陇川县','2951', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533300', '怒江傈僳族自治州','2952', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533301', '泸水市','2953', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533323', '福贡县','2954', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533324', '贡山独龙族怒族自治县','2955', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533325', '兰坪白族普米族自治县','2956', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533400', '迪庆藏族自治州','2957', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533401', '香格里拉市','2958', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533422', '德钦县','2959', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '533423', '维西傈僳族自治县','2960', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540000', '西藏自治区','2961', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540100', '拉萨市','2962', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540101', '市辖区','2963', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540102', '城关区','2964', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540103', '堆龙德庆区','2965', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540104', '达孜区','2966', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540121', '林周县','2967', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540122', '当雄县','2968', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540123', '尼木县','2969', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540124', '曲水县','2970', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540127', '墨竹工卡县','2971', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540200', '日喀则市','2972', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540202', '桑珠孜区','2973', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540221', '南木林县','2974', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540222', '江孜县','2975', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540223', '定日县','2976', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540224', '萨迦县','2977', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540225', '拉孜县','2978', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540226', '昂仁县','2979', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540227', '谢通门县','2980', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540228', '白朗县','2981', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540229', '仁布县','2982', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540230', '康马县','2983', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540231', '定结县','2984', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540232', '仲巴县','2985', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540233', '亚东县','2986', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540234', '吉隆县','2987', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540235', '聂拉木县','2988', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540236', '萨嘎县','2989', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540237', '岗巴县','2990', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540300', '昌都市','2991', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540302', '卡若区','2992', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540321', '江达县','2993', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540322', '贡觉县','2994', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540323', '类乌齐县','2995', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540324', '丁青县','2996', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540325', '察雅县','2997', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540326', '八宿县','2998', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540327', '左贡县','2999', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540328', '芒康县','3000', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540329', '洛隆县','3001', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540330', '边坝县','3002', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540400', '林芝市','3003', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540402', '巴宜区','3004', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540421', '工布江达县','3005', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540422', '米林县','3006', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540423', '墨脱县','3007', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540424', '波密县','3008', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540425', '察隅县','3009', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540426', '朗县','3010', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540500', '山南市','3011', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540501', '市辖区','3012', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540502', '乃东区','3013', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540521', '扎囊县','3014', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540522', '贡嘎县','3015', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540523', '桑日县','3016', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540524', '琼结县','3017', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540525', '曲松县','3018', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540526', '措美县','3019', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540527', '洛扎县','3020', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540528', '加查县','3021', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540529', '隆子县','3022', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540530', '错那县','3023', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540531', '浪卡子县','3024', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540600', '那曲市','3025', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540602', '色尼区','3026', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540621', '嘉黎县','3027', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540622', '比如县','3028', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540623', '聂荣县','3029', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540624', '安多县','3030', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540625', '申扎县','3031', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540626', '索县','3032', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540627', '班戈县','3033', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540628', '巴青县','3034', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540629', '尼玛县','3035', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '540630', '双湖县','3036', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542500', '阿里地区','3037', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542521', '普兰县','3038', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542522', '札达县','3039', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542523', '噶尔县','3040', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542524', '日土县','3041', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542525', '革吉县','3042', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542526', '改则县','3043', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '542527', '措勤县','3044', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610000', '陕西省','3045', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610100', '西安市','3046', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610101', '市辖区','3047', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610102', '新城区','3048', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610103', '碑林区','3049', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610104', '莲湖区','3050', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610111', '灞桥区','3051', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610112', '未央区','3052', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610113', '雁塔区','3053', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610114', '阎良区','3054', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610115', '临潼区','3055', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610116', '长安区','3056', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610117', '高陵区','3057', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610118', '鄠邑区','3058', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610122', '蓝田县','3059', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610124', '周至县','3060', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610200', '铜川市','3061', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610201', '市辖区','3062', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610202', '王益区','3063', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610203', '印台区','3064', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610204', '耀州区','3065', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610222', '宜君县','3066', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610300', '宝鸡市','3067', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610301', '市辖区','3068', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610302', '渭滨区','3069', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610303', '金台区','3070', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610304', '陈仓区','3071', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610322', '凤翔县','3072', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610323', '岐山县','3073', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610324', '扶风县','3074', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610326', '眉县','3075', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610327', '陇县','3076', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610328', '千阳县','3077', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610329', '麟游县','3078', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610330', '凤县','3079', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610331', '太白县','3080', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610400', '咸阳市','3081', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610401', '市辖区','3082', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610402', '秦都区','3083', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610403', '杨陵区','3084', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610404', '渭城区','3085', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610422', '三原县','3086', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610423', '泾阳县','3087', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610424', '乾县','3088', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610425', '礼泉县','3089', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610426', '永寿县','3090', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610428', '长武县','3091', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610429', '旬邑县','3092', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610430', '淳化县','3093', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610431', '武功县','3094', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610481', '兴平市','3095', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610482', '彬州市','3096', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610500', '渭南市','3097', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610501', '市辖区','3098', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610502', '临渭区','3099', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610503', '华州区','3100', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610522', '潼关县','3101', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610523', '大荔县','3102', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610524', '合阳县','3103', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610525', '澄城县','3104', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610526', '蒲城县','3105', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610527', '白水县','3106', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610528', '富平县','3107', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610581', '韩城市','3108', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610582', '华阴市','3109', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610600', '延安市','3110', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610601', '市辖区','3111', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610602', '宝塔区','3112', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610603', '安塞区','3113', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610621', '延长县','3114', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610622', '延川县','3115', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610625', '志丹县','3116', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610626', '吴起县','3117', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610627', '甘泉县','3118', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610628', '富县','3119', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610629', '洛川县','3120', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610630', '宜川县','3121', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610631', '黄龙县','3122', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610632', '黄陵县','3123', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610681', '子长市','3124', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610700', '汉中市','3125', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610701', '市辖区','3126', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610702', '汉台区','3127', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610703', '南郑区','3128', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610722', '城固县','3129', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610723', '洋县','3130', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610724', '西乡县','3131', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610725', '勉县','3132', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610726', '宁强县','3133', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610727', '略阳县','3134', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610728', '镇巴县','3135', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610729', '留坝县','3136', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610730', '佛坪县','3137', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610800', '榆林市','3138', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610801', '市辖区','3139', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610802', '榆阳区','3140', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610803', '横山区','3141', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610822', '府谷县','3142', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610824', '靖边县','3143', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610825', '定边县','3144', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610826', '绥德县','3145', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610827', '米脂县','3146', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610828', '佳县','3147', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610829', '吴堡县','3148', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610830', '清涧县','3149', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610831', '子洲县','3150', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610881', '神木市','3151', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610900', '安康市','3152', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610901', '市辖区','3153', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610902', '汉滨区','3154', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610921', '汉阴县','3155', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610922', '石泉县','3156', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610923', '宁陕县','3157', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610924', '紫阳县','3158', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610925', '岚皋县','3159', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610926', '平利县','3160', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610927', '镇坪县','3161', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610928', '旬阳县','3162', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '610929', '白河县','3163', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611000', '商洛市','3164', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611001', '市辖区','3165', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611002', '商州区','3166', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611021', '洛南县','3167', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611022', '丹凤县','3168', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611023', '商南县','3169', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611024', '山阳县','3170', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611025', '镇安县','3171', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '611026', '柞水县','3172', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620000', '甘肃省','3173', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620100', '兰州市','3174', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620101', '市辖区','3175', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620102', '城关区','3176', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620103', '七里河区','3177', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620104', '西固区','3178', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620105', '安宁区','3179', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620111', '红古区','3180', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620121', '永登县','3181', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620122', '皋兰县','3182', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620123', '榆中县','3183', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620200', '嘉峪关市','3184', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620201', '市辖区','3185', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620300', '金昌市','3186', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620301', '市辖区','3187', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620302', '金川区','3188', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620321', '永昌县','3189', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620400', '白银市','3190', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620401', '市辖区','3191', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620402', '白银区','3192', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620403', '平川区','3193', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620421', '靖远县','3194', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620422', '会宁县','3195', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620423', '景泰县','3196', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620500', '天水市','3197', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620501', '市辖区','3198', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620502', '秦州区','3199', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620503', '麦积区','3200', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620521', '清水县','3201', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620522', '秦安县','3202', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620523', '甘谷县','3203', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620524', '武山县','3204', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620525', '张家川回族自治县','3205', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620600', '武威市','3206', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620601', '市辖区','3207', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620602', '凉州区','3208', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620621', '民勤县','3209', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620622', '古浪县','3210', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620623', '天祝藏族自治县','3211', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620700', '张掖市','3212', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620701', '市辖区','3213', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620702', '甘州区','3214', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620721', '肃南裕固族自治县','3215', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620722', '民乐县','3216', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620723', '临泽县','3217', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620724', '高台县','3218', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620725', '山丹县','3219', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620800', '平凉市','3220', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620801', '市辖区','3221', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620802', '崆峒区','3222', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620821', '泾川县','3223', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620822', '灵台县','3224', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620823', '崇信县','3225', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620825', '庄浪县','3226', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620826', '静宁县','3227', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620881', '华亭市','3228', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620900', '酒泉市','3229', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620901', '市辖区','3230', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620902', '肃州区','3231', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620921', '金塔县','3232', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620922', '瓜州县','3233', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620923', '肃北蒙古族自治县','3234', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620924', '阿克塞哈萨克族自治县','3235', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620981', '玉门市','3236', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '620982', '敦煌市','3237', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621000', '庆阳市','3238', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621001', '市辖区','3239', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621002', '西峰区','3240', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621021', '庆城县','3241', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621022', '环县','3242', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621023', '华池县','3243', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621024', '合水县','3244', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621025', '正宁县','3245', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621026', '宁县','3246', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621027', '镇原县','3247', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621100', '定西市','3248', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621101', '市辖区','3249', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621102', '安定区','3250', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621121', '通渭县','3251', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621122', '陇西县','3252', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621123', '渭源县','3253', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621124', '临洮县','3254', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621125', '漳县','3255', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621126', '岷县','3256', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621200', '陇南市','3257', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621201', '市辖区','3258', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621202', '武都区','3259', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621221', '成县','3260', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621222', '文县','3261', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621223', '宕昌县','3262', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621224', '康县','3263', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621225', '西和县','3264', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621226', '礼县','3265', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621227', '徽县','3266', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '621228', '两当县','3267', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622900', '临夏回族自治州','3268', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622901', '临夏市','3269', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622921', '临夏县','3270', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622922', '康乐县','3271', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622923', '永靖县','3272', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622924', '广河县','3273', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622925', '和政县','3274', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622926', '东乡族自治县','3275', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '622927', '积石山保安族东乡族撒拉族自治县','3276', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623000', '甘南藏族自治州','3277', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623001', '合作市','3278', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623021', '临潭县','3279', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623022', '卓尼县','3280', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623023', '舟曲县','3281', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623024', '迭部县','3282', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623025', '玛曲县','3283', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623026', '碌曲县','3284', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '623027', '夏河县','3285', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630000', '青海省','3286', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630100', '西宁市','3287', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630101', '市辖区','3288', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630102', '城东区','3289', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630103', '城中区','3290', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630104', '城西区','3291', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630105', '城北区','3292', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630106', '湟中区','3293', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630121', '大通回族土族自治县','3294', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630123', '湟源县','3295', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630200', '海东市','3296', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630202', '乐都区','3297', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630203', '平安区','3298', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630222', '民和回族土族自治县','3299', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630223', '互助土族自治县','3300', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630224', '化隆回族自治县','3301', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '630225', '循化撒拉族自治县','3302', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632200', '海北藏族自治州','3303', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632221', '门源回族自治县','3304', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632222', '祁连县','3305', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632223', '海晏县','3306', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632224', '刚察县','3307', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632300', '黄南藏族自治州','3308', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632321', '同仁县','3309', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632322', '尖扎县','3310', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632323', '泽库县','3311', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632324', '河南蒙古族自治县','3312', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632500', '海南藏族自治州','3313', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632521', '共和县','3314', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632522', '同德县','3315', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632523', '贵德县','3316', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632524', '兴海县','3317', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632525', '贵南县','3318', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632600', '果洛藏族自治州','3319', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632621', '玛沁县','3320', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632622', '班玛县','3321', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632623', '甘德县','3322', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632624', '达日县','3323', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632625', '久治县','3324', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632626', '玛多县','3325', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632700', '玉树藏族自治州','3326', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632701', '玉树市','3327', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632722', '杂多县','3328', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632723', '称多县','3329', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632724', '治多县','3330', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632725', '囊谦县','3331', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632726', '曲麻莱县','3332', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632800', '海西蒙古族藏族自治州','3333', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632801', '格尔木市','3334', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632802', '德令哈市','3335', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632803', '茫崖市','3336', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632821', '乌兰县','3337', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632822', '都兰县','3338', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '632823', '天峻县','3339', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640000', '宁夏回族自治区','3340', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640100', '银川市','3341', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640101', '市辖区','3342', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640104', '兴庆区','3343', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640105', '西夏区','3344', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640106', '金凤区','3345', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640121', '永宁县','3346', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640122', '贺兰县','3347', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640181', '灵武市','3348', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640200', '石嘴山市','3349', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640201', '市辖区','3350', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640202', '大武口区','3351', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640205', '惠农区','3352', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640221', '平罗县','3353', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640300', '吴忠市','3354', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640301', '市辖区','3355', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640302', '利通区','3356', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640303', '红寺堡区','3357', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640323', '盐池县','3358', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640324', '同心县','3359', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640381', '青铜峡市','3360', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640400', '固原市','3361', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640401', '市辖区','3362', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640402', '原州区','3363', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640422', '西吉县','3364', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640423', '隆德县','3365', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640424', '泾源县','3366', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640425', '彭阳县','3367', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640500', '中卫市','3368', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640501', '市辖区','3369', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640502', '沙坡头区','3370', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640521', '中宁县','3371', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '640522', '海原县','3372', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650000', '新疆维吾尔自治区','3373', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650100', '乌鲁木齐市','3374', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650101', '市辖区','3375', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650102', '天山区','3376', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650103', '沙依巴克区','3377', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650104', '新市区','3378', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650105', '水磨沟区','3379', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650106', '头屯河区','3380', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650107', '达坂城区','3381', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650109', '米东区','3382', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650121', '乌鲁木齐县','3383', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650200', '克拉玛依市','3384', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650201', '市辖区','3385', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650202', '独山子区','3386', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650203', '克拉玛依区','3387', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650204', '白碱滩区','3388', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650205', '乌尔禾区','3389', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650400', '吐鲁番市','3390', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650402', '高昌区','3391', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650421', '鄯善县','3392', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650422', '托克逊县','3393', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650500', '哈密市','3394', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650502', '伊州区','3395', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650521', '巴里坤哈萨克自治县','3396', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '650522', '伊吾县','3397', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652300', '昌吉回族自治州','3398', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652301', '昌吉市','3399', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652302', '阜康市','3400', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652323', '呼图壁县','3401', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652324', '玛纳斯县','3402', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652325', '奇台县','3403', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652327', '吉木萨尔县','3404', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652328', '木垒哈萨克自治县','3405', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652700', '博尔塔拉蒙古自治州','3406', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652701', '博乐市','3407', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652702', '阿拉山口市','3408', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652722', '精河县','3409', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652723', '温泉县','3410', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652800', '巴音郭楞蒙古自治州','3411', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652801', '库尔勒市','3412', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652822', '轮台县','3413', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652823', '尉犁县','3414', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652824', '若羌县','3415', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652825', '且末县','3416', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652826', '焉耆回族自治县','3417', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652827', '和静县','3418', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652828', '和硕县','3419', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652829', '博湖县','3420', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652900', '阿克苏地区','3421', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652901', '阿克苏市','3422', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652902', '库车市','3423', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652922', '温宿县','3424', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652924', '沙雅县','3425', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652925', '新和县','3426', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652926', '拜城县','3427', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652927', '乌什县','3428', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652928', '阿瓦提县','3429', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '652929', '柯坪县','3430', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653000', '克孜勒苏柯尔克孜自治州','3431', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653001', '阿图什市','3432', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653022', '阿克陶县','3433', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653023', '阿合奇县','3434', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653024', '乌恰县','3435', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653100', '喀什地区','3436', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653101', '喀什市','3437', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653121', '疏附县','3438', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653122', '疏勒县','3439', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653123', '英吉沙县','3440', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653124', '泽普县','3441', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653125', '莎车县','3442', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653126', '叶城县','3443', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653127', '麦盖提县','3444', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653128', '岳普湖县','3445', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653129', '伽师县','3446', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653130', '巴楚县','3447', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653131', '塔什库尔干塔吉克自治县','3448', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653200', '和田地区','3449', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653201', '和田市','3450', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653221', '和田县','3451', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653222', '墨玉县','3452', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653223', '皮山县','3453', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653224', '洛浦县','3454', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653225', '策勒县','3455', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653226', '于田县','3456', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '653227', '民丰县','3457', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654000', '伊犁哈萨克自治州','3458', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654002', '伊宁市','3459', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654003', '奎屯市','3460', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654004', '霍尔果斯市','3461', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654021', '伊宁县','3462', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654022', '察布查尔锡伯自治县','3463', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654023', '霍城县','3464', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654024', '巩留县','3465', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654025', '新源县','3466', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654026', '昭苏县','3467', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654027', '特克斯县','3468', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654028', '尼勒克县','3469', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654200', '塔城地区','3470', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654201', '塔城市','3471', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654202', '乌苏市','3472', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654221', '额敏县','3473', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654223', '沙湾县','3474', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654224', '托里县','3475', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654225', '裕民县','3476', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654226', '和布克赛尔蒙古自治县','3477', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654300', '阿勒泰地区','3478', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654301', '阿勒泰市','3479', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654321', '布尔津县','3480', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654322', '富蕴县','3481', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654323', '福海县','3482', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654324', '哈巴河县','3483', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654325', '青河县','3484', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '654326', '吉木乃县','3485', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659001', '石河子市','3486', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659002', '阿拉尔市','3487', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659003', '图木舒克市','3488', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659004', '五家渠市','3489', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659005', '北屯市','3490', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659006', '铁门关市','3491', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659007', '双河市','3492', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659008', '可克达拉市','3493', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659009', '昆玉市','3494', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);
INSERT INTO sys_datadict_list
    VALUES ('PBC_LOAN_LOCATION', '659010', '胡杨河市','3495', '0', 'system', null, TIMESTAMP '2022-11-17 00:00:00', 'system', TIMESTAMP '2022-11-17 00:00:00', null);

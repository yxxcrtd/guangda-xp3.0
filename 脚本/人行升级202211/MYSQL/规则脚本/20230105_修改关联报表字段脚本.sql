alter table pbc_check_rule_info modify related_table   varchar(200) comment '关联报表';

update pbc_check_rule_info SET related_table='ZG01 资管产品基本信息' where rule_id  in
('DB0001',
'DB0002',
'DB0006',
'DB0007',
'DB0008',
'DB0009',
'DB0010',
'DB0011',
'DB0012',
'DB0013',
'DB0014',
'DB0024',
'DB0030',
'DB0032',
'DB0033',
'DB0034',
'DB0035',
'DB0037',
'DB0172',
'DB0173',
'DB0174',
'DB0175',
'DB0176',
'DB0177',
'DB0178',
'DB0179',
'DB0180',
'DB0181',
'DB0182',
'DB0183',
'DB0202',
'DB0203',
'DB0204',
'DB0205',
'DB0207',
'DB0208');

update pbc_check_rule_info SET related_table='ZG01 资管产品基本信息,ZG04 资管产品存续期募集信息' where rule_id  in ('DB0190','DB0218','DB0031','DB0186','DB0187');

update pbc_check_rule_info SET related_table='ZG01 资管产品基本信息,ZG04 资管产品存续期募集信息,ZG05 资管产品资产负债信息' where rule_id  in ('DB0189','DB0217');

update pbc_check_rule_info SET related_table='ZG01 资管产品基本信息,ZG03 资管产品终止信息' where rule_id  in ('DB0038');

update pbc_check_rule_info SET related_table='ZG03 资管产品终止信息' where rule_id in(
'DB0021',
'DB0022',
'DB0025',
'DB0029',
'DB0040',
'DB0041');

update pbc_check_rule_info SET related_table='ZG02 资管产品初始募集信息' where rule_id  in ('DB0015','DB0016','DB0036');

update pbc_check_rule_info SET related_table='ZG01 资管产品基本信息,ZG03 资管产品终止信息' where rule_id  in (
'DB0020',
'DB0023',
'DB0026',
'DB0027',
'DB0028',
'DB0039');

update pbc_check_rule_info SET related_table='ZG04 资管产品存续期募集信息' where rule_id  in (
'DB0017',
'DB0018',
'DB0171',
'DB0188',
'DB0191',
'DB0192',
'DB0193',
'DB0194',
'DB0195',
'DB0196',
'DB0197',
'DB0198',
'DB0199',
'DB0200',
'DB0201',
'DB0216',
'DB0219',
'DB0220',
'DB0221',
'DB0222',
'DB0223');

update pbc_check_rule_info SET related_table='ZG05 资管产品资产负债信息' where rule_id  in (
'DB0184',
'DB0185',
'DB0209',
'DB0210',
'DB0211',
'DB0212');

update pbc_check_rule_info SET related_table='ZG06 资产收益权明细信息' where rule_id  in (
'DB0044',
'DB0045',
'DB0046',
'DB0047',
'DB0048',
'DB0049',
'DB0050',
'DB0051',
'DB0052',
'DB0053',
'DB0054',
'DB0055');

update pbc_check_rule_info SET related_table='ZG07 除回购和拆借外贷款明细信息' where rule_id  in ('DB0056','DB0057','DB0206');

update pbc_check_rule_info SET related_table='ZG08 特定目的载体交易对手明细信息' where rule_id  in (
'DB0093',
'DB0094',
'DB0095',
'DB0096',
'DB0097',
'DB0098',
'DB0099',
'DB0100',
'DB0101',
'DB0102',
'DB0103',
'DB0104',
'DB0105',
'DB0106',
'DB0107',
'DB0108',
'DB0109',
'DB0110',
'DB0111',
'DB0112',
'DB0113',
'DB0114',
'DB0115',
'DB0116',
'DB0117',
'DB0118',
'DB0119',
'DB0120',
'DB0121',
'DB0122',
'DB0123',
'DB0124',
'DB0125',
'DB0126',
'DB0127',
'DB0128',
'DB0129',
'DB0130',
'DB0131',
'DB0132',
'DB0133',
'DB0134',
'DB0135',
'DB0136',
'DB0137',
'DB0138',
'DB0139',
'DB0140',
'DB0141',
'DB0142',
'DB0143',
'DB0144',
'DB0145',
'DB0146',
'DB0147',
'DB0148',
'DB0149',
'DB0150',
'DB0151',
'DB0152',
'DB0153',
'DB0154',
'DB0155',
'DB0156',
'DB0157',
'DB0158',
'DB0159',
'DB0160',
'DB0161',
'DB0162',
'DB0163',
'DB0164',
'DB0165',
'DB0166',
'DB0167',
'DB0168',
'DB0169');


update pbc_check_rule_info SET related_table='ZG09 资产负债剩余期限信息' where rule_id  in (
'DB0059',
'DB0060',
'DB0061',
'DB0062',
'DB0063'
    );
	
update pbc_check_rule_info SET related_table='ZG10 债券等资产配置情况信息' where rule_id  in (
'DB0064',
'DB0065',
'DB0066',
'DB0067',
'DB0068',
'DB0069',
'DB0070',
'DB0071',
'DB0072',
'DB0073',
'DB0074',
'DB0075',
'DB0076',
'DB0077',
'DB0078',
'DB0079',
'DB0080',
'DB0081',
'DB0082',
'DB0083',
'DB0084',
'DB0085',
'DB0086',
'DB0087',
'DB0088',
'DB0089');

update pbc_check_rule_info SET related_table='ZG11 企业债券分行业、企业规模情况信息' where rule_id  in (
'DB0058',
'DB0213',
'DB0214',
'DB0215');

update pbc_check_rule_info SET related_table='募集总量登记' where rule_id  in (
'DB0003',
'DB0004',
'DB0005');

update pbc_check_rule_info SET related_table='银行非保本理财业务月度统计表' where rule_id  in ('DB0019');





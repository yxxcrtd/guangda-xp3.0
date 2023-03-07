delete from SYS_MENU where PAGE_ID ='ChbMstCounterParty001';
INSERT INTO SYS_MENU VALUES('REPORT_MAINTAIN_001_004_012','REP',null,'REPORT_MAINTAIN_001_004','ChbMstCounterParty001',12,'02','交易对手信息维护','system',null,now(),'system',now());

delete from SYS_PAGE where page_id  = 'ChbMstCounterParty001';
INSERT INTO SYS_PAGE  VALUES ('ChbMstCounterParty001','REP','/report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001','report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001','REPORT_MAINTAIN_001_004_012',null,null,'02','01','交易对手信息维护','system',null,now(),'system',now());
delete from SYS_PAGE where page_id  = 'ChbMstCounterParty002';
INSERT INTO SYS_PAGE  VALUES ('ChbMstCounterParty002','REP','/report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002','report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002','',null,null,'02','01','新建交易对手信息','system',null,now(),'system',now());

delete from SYS_DATADICT where dict_code='MST_COUNTER_TYPE';
INSERT INTO SYS_DATADICT values('MST_COUNTER_TYPE','SVR','交易对手类别',null,'02','system',null,now(),'system',now());

delete from SYS_DATADICT_LIST where dict_code='MST_COUNTER_TYPE';
insert into SYS_DATADICT_LIST values('MST_COUNTER_TYPE','01','市场机构',1,'0','system',null,now(),'system',now(),null);
insert into SYS_DATADICT_LIST values('MST_COUNTER_TYPE','02','资管产品',2,'0','system',null,now(),'system',now(),null);
insert into SYS_DATADICT_LIST values('MST_COUNTER_TYPE','05','内部交易对手',5,'0','system',null,now(),'system',now(),null);

drop table if exists CHB_MST_COUNTER_PARTY_ERRODATA;
 CREATE TABLE  CHB_MST_COUNTER_PARTY_ERRODATA
    (
        UUID CHAR(36) COMMENT 'uuid技术主键',
        COUNTER_ID VARCHAR(100) NOT NULL COMMENT '交易对手代码',
        COUNTER_TYPE VARCHAR(200) COMMENT '交易对手类型，市场机构、资管产品、内部交易对手',
        COUNTER_NAME VARCHAR(400) COMMENT '交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。',
		COUNTER_TYPE1 VARCHAR(100) COMMENT '交易对手一级类型',
        COUNTER_TYPE2 VARCHAR(100) COMMENT '交易对手二级类型',
        ORG_DATA longtext COMMENT '原始数据 ',
        ERR_MSG longtext COMMENT '错误信息 ',
        STATUS VARCHAR(2) COMMENT '数据状态：A待处理 D删除  E有效  M修改',
        CREATE_USER VARCHAR(20) COMMENT '创建人',
        CREATE_DEPT VARCHAR(32) COMMENT '创建部门',
        CREATE_TIME DATETIME COMMENT '创建时间',
        UPDATE_USER VARCHAR(20) COMMENT '更新人',
        UPDATE_TIME DATETIME COMMENT '更新时间',
        PRIMARY KEY (COUNTER_ID)
    );
    ALTER TABLE CHB_MST_COUNTER_PARTY_ERRODATA COMMENT '交易对手错误信息表';


delete from CHB_DATACHECK_RULE_INV where PAGE_ID='ChbMstCounterParty002';
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123000', 'ChbMstCounterParty002', 'isNotEmpty', 'counterId', null, null, 'N', '交易对手代码不可以为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123001', 'ChbMstCounterParty002', 'isNotEmpty', 'counterName', null, null, 'N', '交易对手名称不可以为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123002', 'ChbMstCounterParty002', 'isNotEmpty', 'counterType2', null, null, 'N', '交易对手类型不可以为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123003', 'ChbMstCounterParty002', 'isNotEmpty', 'counterType', null, null, 'N', '交易对手类别不可以为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
  INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123004', 'ChbMstCounterParty002', 'inArray', 'counterType2', '==', 'C0500', 'N', '交易对手类型为C0500银行非保本理财', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
 INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123005', 'ChbMstCounterParty002', 'inDatadict', 'counterType2', '==', 'SVR_CUSTOMER_TYPE2', 'Y', '交易对手类型不在值域范围内', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123006', 'ChbMstCounterParty002', 'inDatadict', 'counterType', '==', 'MST_COUNTER_TYPE', 'Y', '交易对手类别不在值域范围内', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123007', 'ChbMstCounterParty002', 'isCustomizedString', 'remark',null,
 '^[\\u4e00-\\u9fa5\\ue815-\\ue864\\uff01-\\uff5e\\uffe0-\\uffe5\\u3000-\\u3003\\u3005-\\u3017\\u301d-\\u301e\\u2010\\u2013-\\u2016\\u2018-\\u2019\\u201c-\\u201d\\u2025-\\u2026\\u2030\\u2032-\\u2033\\u2035\\u203b\\u00b7\\u0030-\\u0039\\u0041-\\u005a\\u0061-\\u007a\\u2160-\\u216b\\u0020-\\u0026\\u0028-\\u007B\\u007D\\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123008', 'ChbMstCounterParty002', 'isCustomizedString', 'counterName', null, '^[\\u4e00-\\u9fa5\\ue815-\\ue864\\uff01-\\uff5e\\uffe0-\\uffe5\\u3000-\\u3003\\u3005-\\u3017\\u301d-\\u301e\\u2010\\u2013-\\u2016\\u2018-\\u2019\\u201c-\\u201d\\u2025-\\u2026\\u2030\\u2032-\\u2033\\u2035\\u203b\\u00b7\\u0030-\\u0039\\u0041-\\u005a\\u0061-\\u007a\\u2160-\\u216b\\u0020-\\u0026\\u0028-\\u007B\\u007D\\u007E]{0,}$', 'Y', '交易对手名称必须填写白名单中的文字', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
INSERT INTO CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
 VALUES ('CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123009', 'ChbMstCounterParty002', 'isCustomizedString', 'counterId', null, '^[\\u4e00-\\u9fa5\\ue815-\\ue864\\uff01-\\uff5e\\uffe0-\\uffe5\\u3000-\\u3003\\u3005-\\u3017\\u301d-\\u301e\\u2010\\u2013-\\u2016\\u2018-\\u2019\\u201c-\\u201d\\u2025-\\u2026\\u2030\\u2032-\\u2033\\u2035\\u203b\\u00b7\\u0030-\\u0039\\u0041-\\u005a\\u0061-\\u007a\\u2160-\\u216b\\u0020-\\u0026\\u0028-\\u007B\\u007D\\u007E]{0,}$', 'Y', '交易对手代码必须填写白名单中的文字', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');


delete from CHB_DATACHECK_CONDITION_INV where CHECKCONDITIONID='CHB_MST_COUNTER_PARTY-20220519-0001-01';
insert into CHB_DATACHECK_CONDITION_INV (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('CHB_MST_COUNTER_PARTY-20220519-0001-01', 'CHB_MST_COUNTER_PARTY_0000-0000-0000-0000-0000-0000-0000-123004', 'inArray', 'counterType', '==', '3', '当交易对手类别为内部交易对手时', 'E');

delete from CHB_INFOMGR_CONFIG where BASETABLEID='CHB_EQUDET';
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','FIN_ACC','理财账号','T','N','');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','CUST_CONTRACT_NO','客户统一编号','T','N','');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','CUST_NAME','客户姓名','T','N','');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','IS_CONSIGN','是否代销','S','N','YES_OR_NO');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','AGENCY_CODE','代销机构代码','T','N','');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID,BASETABLEID,BASECOLUMNID,BASECOLUMNNAME,BASECOLUMNTYPE,BUSINESSPKFLAG,DICTCODE) values (f_guid(),'CHB_EQUDET','AGENCY_NAME','代销机构名称','T','N','');

delete from SYS_DATADICT_LIST where dict_code='EQBUSI_SELECT_COLUMN';
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','FIN_ACC','理财账号',1,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','CUST_CONTRACT_NO','客户统一编号',2,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','CUST_NAME','客户姓名',3,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','IS_CONSIGN','是否代销',4,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','AGENCY_CODE','代销机构代码',5,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);
insert into SYS_DATADICT_LIST values('EQBUSI_SELECT_COLUMN','AGENCY_NAME','代销机构名称',6,'0','system',null,date_format('2022/3/30', '%Y%m%d%H%i%s'),'system',date_format('2022/3/30', '%Y%m%d%H%i%s'),null);


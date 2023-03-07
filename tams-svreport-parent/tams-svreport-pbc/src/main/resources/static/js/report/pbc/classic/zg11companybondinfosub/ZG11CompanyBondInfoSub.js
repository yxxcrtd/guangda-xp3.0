$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			//结束日（统计年月）
			enddate : $('#enddate').val(),
			//报送状态
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			//数据日期
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			//报送日期
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			//报送截止日
			submitDeadLineEnd:$('#pbcSubmitDeadline').endDate(),
			submitDeadLineStart:$('#pbcSubmitDeadline').startDate(),
		};
	};
	// 定义数据表格
	var table=$("#dataTable").joyintable({
		ajax: {
			url: 'searchPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		rowno: false, // 显示行号
		searchOnInit: false, //初始化的时候就请求后台数据
		showDetail: true,//双击详情对比画面
		cols:[
			{ field: 'reportuuid',visible:false},
			{ title: "数据日期", field: 'statusDate',width:150,type: 'date',sortField:'STATUS_DATE'},
			{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'SEND_STATUS'},
			{ title: '报送日期',field:'submitTime',width:150,type: 'datetime',sortField:'SUBMIT_TIME'},
			{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'PBC_SUBMIT_DEADLINE'},
			{ title: "统计年月", field: 'termid',width:130,sortField:'termid'},
			{ title: "发行机构代码", field: 'issuePartyId',width:150,sortField:'ISSUE_PARTY_ID'},
			{ title: "产品品种", field: 'prodVarietyStr',width:150,sortField:'PROD_VARIETY'},
			{ title: "c00000_企业债券余额总计", field: 'c00000Str',width:200,sortField:'c00000'},
			{ title: "c01000_企业债券余额总计-大型企业", field: 'c01000Str',width:200,sortField:'c01000'},
			{ title: "c02000_企业债券余额总计-中型企业", field: 'c02000Str',width:200,sortField:'c02000'},
			{ title: "c03000_企业债券余额总计-小型企业", field: 'c03000Str',width:200,sortField:'c03000'},
			{ title: "c04000_企业债券余额总计-微型企业", field: 'c04000Str',width:200,sortField:'c04000'},
			{ title: "c10000_农、林、牧、渔业", field: 'c10000Str',width:200,sortField:'c10000'},
			{ title: "c11000_农、林、牧、渔业-大型企业", field: 'c11000Str',width:200,sortField:'c11000'},
			{ title: "c12000_农、林、牧、渔业-中型企业", field: 'c12000Str',width:200,sortField:'c12000'},
			{ title: "c13000_农、林、牧、渔业-小型企业", field: 'c13000Str',width:200,sortField:'c13000'},
			{ title: "c14000_农、林、牧、渔业-微型企业", field: 'c14000Str',width:200,sortField:'c14000'},
			{ title: "c20000_采矿业", field: 'c20000Str',width:200,sortField:'c20000'},
			{ title: "c21000_采矿业-大型企业", field: 'c21000Str',width:200,sortField:'c21000'},
			{ title: "c22000_采矿业-中型企业", field: 'c22000Str',width:200,sortField:'c22000'},
			{ title: "c23000_采矿业-小型企业", field: 'c23000Str',width:200,sortField:'c23000'},
			{ title: "c24000_采矿业-微型企业", field: 'c24000Str',width:200,sortField:'c24000'},
			{ title: "c30000_制造业", field: 'c30000Str',width:200,sortField:'c30000'},
			{ title: "c31000_制造业-大型企业", field: 'c31000Str',width:200,sortField:'c31000'},
			{ title: "c32000_制造业-中型企业", field: 'c32000Str',width:200,sortField:'c32000'},
			{ title: "c33000_制造业-小型企业", field: 'c33000Str',width:200,sortField:'c33000'},
			{ title: "c34000_制造业-微型企业", field: 'c34000Str',width:200,sortField:'c34000'},
			{ title: "c40000_电力、热力、燃气及水生产和供应业", field: 'c40000Str',width:200,sortField:'c40000'},
			{ title: "c41000_电力、热力、燃气及水生产和供应业-大型企业", field: 'c41000Str',width:200,sortField:'c41000'},
			{ title: "c42000_电力、热力、燃气及水生产和供应业-中型企业", field: 'c42000Str',width:200,sortField:'c42000'},
			{ title: "c43000_电力、热力、燃气及水生产和供应业-小型企业", field: 'c43000Str',width:200,sortField:'c43000'},
			{ title: "c44000_电力、热力、燃气及水生产和供应业-微型企业", field: 'c44000Str',width:200,sortField:'c44000'},
			{ title: "c50000_建筑业", field: 'c50000Str',width:200,sortField:'c50000'},
			{ title: "c51000_建筑业-大型企业", field: 'c51000Str',width:200,sortField:'c51000'},
			{ title: "c52000_建筑业-中型企业", field: 'c52000Str',width:200,sortField:'c52000'},
			{ title: "c53000_建筑业-小型企业", field: 'c53000Str',width:200,sortField:'c53000'},
			{ title: "c54000_建筑业-微型企业", field: 'c54000Str',width:200,sortField:'c54000'},
			{ title: "c60000_批发和零售业：中央交易对手方", field: 'c60000Str',width:200,sortField:'c60000'},
			{ title: "c61000_批发和零售业-大型企业", field: 'c61000Str',width:200,sortField:'c61000'},
			{ title: "c62000_批发和零售业-中型企业", field: 'c62000Str',width:200,sortField:'c62000'},
			{ title: "c63000_批发和零售业-小型企业", field: 'c63000Str',width:200,sortField:'c63000'},
			{ title: "c64000_批发和零售业-微型企业", field: 'c64000Str',width:200,sortField:'c64000'},
			{ title: "c70000_交通运输、仓储和邮政业", field: 'c70000Str',width:200,sortField:'c70000'},
			{ title: "c71000_交通运输、仓储和邮政业-大型企业", field: 'c71000Str',width:200,sortField:'c71000'},
			{ title: "c72000_交通运输、仓储和邮政业-中型企业", field: 'c72000Str',width:200,sortField:'c72000'},
			{ title: "c73000_交通运输、仓储和邮政业-小型企业", field: 'c73000Str',width:200,sortField:'c73000'},
			{ title: "c74000_交通运输、仓储和邮政业-微型企业", field: 'c74000Str',width:200,sortField:'c74000'},
			{ title: "c80000_住宿和餐饮业", field: 'c80000Str',width:200,sortField:'c80000'},
			{ title: "c81000_住宿和餐饮业-大型企业", field: 'c81000Str',width:200,sortField:'c81000'},
			{ title: "c82000_住宿和餐饮业-中型企业", field: 'c82000Str',width:200,sortField:'c82000'},
			{ title: "c83000_住宿和餐饮业-小型企业", field: 'c83000Str',width:200,sortField:'c83000'},
			{ title: "c84000_住宿和餐饮业-微型企业", field: 'c84000Str',width:200,sortField:'c84000'},
			{ title: "c90000_信息传输、软件和信息技术服务业", field: 'c90000Str',width:200,sortField:'c90000'},
			{ title: "c91000_信息传输、软件和信息技术服务业-大型企业", field: 'c91000Str',width:200,sortField:'c91000'},
			{ title: "c92000_信息传输、软件和信息技术服务业-中型企业", field: 'c92000Str',width:200,sortField:'c92000'},
			{ title: "c93000_信息传输、软件和信息技术服务业-小型企业", field: 'c93000Str',width:200,sortField:'c93000'},
			{ title: "c94000_信息传输、软件和信息技术服务业-微型企业", field: 'c94000Str',width:200,sortField:'c94000'},
			{ title: "ca0000_金融业", field: 'ca0000Str',width:200,sortField:'ca0000'},
			{ title: "ca1000_金融业-大型企业", field: 'ca1000Str',width:200,sortField:'ca1000'},
			{ title: "ca2000_金融业-中型企业", field: 'ca2000Str',width:200,sortField:'ca2000'},
			{ title: "ca3000_金融业-小型企业", field: 'ca3000Str',width:200,sortField:'ca3000'},
			{ title: "ca4000_金融业-微型企业", field: 'ca4000Str',width:200,sortField:'ca4000'},
			{ title: "cb0000_房地产业", field: 'cb0000Str',width:200,sortField:'cb0000'},
			{ title: "cb1000_房地产业-大型企业", field: 'cb1000Str',width:200,sortField:'cb1000'},
			{ title: "cb2000_房地产业-中型企业", field: 'cb2000Str',width:200,sortField:'cb2000'},
			{ title: "cb3000_房地产业-小型企业", field: 'cb3000Str',width:200,sortField:'cb3000'},
			{ title: "cb4000_房地产业-微型企业", field: 'cb4000Str',width:200,sortField:'cb4000'},
			{ title: "cc0000_租赁和商务服务业", field: 'cc0000Str',width:200,sortField:'cc0000'},
			{ title: "cc1000_租赁和商务服务业-大型企业", field: 'cc1000Str',width:200,sortField:'cc1000'},
			{ title: "cc2000_租赁和商务服务业-中型企业", field: 'cc2000Str',width:200,sortField:'cc2000'},
			{ title: "cc3000_租赁和商务服务业-小型企业", field: 'cc3000Str',width:200,sortField:'cc3000'},
			{ title: "cc4000_租赁和商务服务业-微型企业", field: 'cc4000Str',width:200,sortField:'cc4000'},
			{ title: "cd0000_科学研究和技术服务业", field: 'cd0000Str',width:200,sortField:'cd0000'},
			{ title: "cd1000_科学研究和技术服务业-大型企业", field: 'cd1000Str',width:200,sortField:'cd1000'},
			{ title: "cd2000_科学研究和技术服务业-中型企业", field: 'cd2000Str',width:200,sortField:'cd2000'},
			{ title: "cd3000_科学研究和技术服务业-小型企业", field: 'cd3000Str',width:200,sortField:'cd3000'},
			{ title: "cd4000_科学研究和技术服务业-微型企业", field: 'cd4000Str',width:200,sortField:'cd4000'},
			{ title: "ce0000_水利、环境和公共设施管理业", field: 'ce0000Str',width:200,sortField:'ce0000'},
			{ title: "ce1000_水利、环境和公共设施管理业-大型企业", field: 'ce1000Str',width:200,sortField:'ce1000'},
			{ title: "ce2000_水利、环境和公共设施管理业-中型企业", field: 'ce2000Str',width:200,sortField:'ce2000'},
			{ title: "ce3000_水利、环境和公共设施管理业-小型企业", field: 'ce3000Str',width:200,sortField:'ce3000'},
			{ title: "ce4000_水利、环境和公共设施管理业-微型企业", field: 'ce4000Str',width:200,sortField:'ce4000'},
			{ title: "cf0000_居民服务、修理和其他服务业", field: 'cf0000Str',width:200,sortField:'cf0000'},
			{ title: "cf1000_居民服务、修理和其他服务业-大型企业", field: 'cf1000Str',width:200,sortField:'cf1000'},
			{ title: "cf2000_居民服务、修理和其他服务业-中型企业", field: 'cf2000Str',width:200,sortField:'cf2000'},
			{ title: "cf3000_居民服务、修理和其他服务业-小型企业", field: 'cf3000Str',width:200,sortField:'cf3000'},
			{ title: "cf4000_居民服务、修理和其他服务业-微型企业", field: 'cf4000Str',width:200,sortField:'cf4000'},
			{ title: "cg0000_教育", field: 'cg0000Str',width:200,sortField:'cg0000'},
			{ title: "cg1000_教育-大型企业", field: 'cg1000Str',width:200,sortField:'cg1000'},
			{ title: "cg2000_教育-中型企业", field: 'cg2000Str',width:200,sortField:'cg2000'},
			{ title: "cg3000_教育-小型企业", field: 'cg3000Str',width:200,sortField:'cg3000'},
			{ title: "cg4000_教育-微型企业", field: 'cg4000Str',width:200,sortField:'cg4000'},
			{ title: "ch0000_卫生和社会工作", field: 'ch0000Str',width:200,sortField:'ch0000'},
			{ title: "ch1000_卫生和社会工作-大型企业", field: 'ch1000Str',width:200,sortField:'ch1000'},
			{ title: "ch2000_卫生和社会工作-中型企业", field: 'ch2000Str',width:200,sortField:'ch2000'},
			{ title: "ch3000_卫生和社会工作-小型企业", field: 'ch3000Str',width:200,sortField:'ch3000'},
			{ title: "ch4000_卫生和社会工作-微型企业", field: 'ch4000Str',width:200,sortField:'ch4000'},
			{ title: "ci0000_文化、体育和娱乐业", field: 'ci0000Str',width:200,sortField:'ci0000'},
			{ title: "ci1000_文化、体育和娱乐业-大型企业", field: 'ci1000Str',width:200,sortField:'ci1000'},
			{ title: "ci2000_文化、体育和娱乐业-中型企业", field: 'ci2000Str',width:200,sortField:'ci2000'},
			{ title: "ci3000_文化、体育和娱乐业-小型企业", field: 'ci3000Str',width:200,sortField:'ci3000'},
			{ title: "ci4000_文化、体育和娱乐业-微型企业", field: 'ci4000Str',width:200,sortField:'ci4000'},
			{ title: "cj0000_公共管理、社会保障和社会组织", field: 'cj0000Str',width:200,sortField:'cj0000'},
			{ title: "cj1000_公共管理、社会保障和社会组织-大型企业", field: 'cj1000Str',width:200,sortField:'cj1000'},
			{ title: "cj2000_公共管理、社会保障和社会组织-中型企业", field: 'cj2000Str',width:200,sortField:'cj2000'},
			{ title: "cj3000_公共管理、社会保障和社会组织-小型企业", field: 'cj3000Str',width:200,sortField:'cj3000'},
			{ title: "cj4000_公共管理、社会保障和社会组织-微型企业", field: 'cj4000Str',width:200,sortField:'cj4000'},
			{field:'sendStatus', visible:false},
			{field:'status', visible:false},
			{ field: 'datuuid', visible: false }
		]
	})
	// 查询按钮
	$('#ZG11CompanyBondInfoSub_btn_search').click(function(){
		table.load();
	})

	// 手工处理
			$('#ZG11CompanyBondInfoSub_btn_manual').click(function(){
				var rows = table.getAllCheckedRows();
				if(rows.length === 0) {
					var sendStatus = $("#sendStatus").val();
					if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '01')){
						return Joyin.error("当不选择数据进行手工处理时，报送状态必须选择未报送！");
					}
					var paramObj = getSearchParams();
					//paramObj.flag = '02';
					paramObj.AllData = 'Y';
					Joyin.ajax('searchPage',paramObj, function(result){
						var termid = '';
						for(var i = 0 ;i<result.root.length ; i++){
							if(termid==''||!termid){
								termid = result.root[i].termid;
							}else{
								if(termid!=result.root[i].termid){
									return Joyin.error("符合查询条件的报送数据包含多个报送月份数据，不支持跨月手工处理");
								}
							}
						}
						var param= {};
						param.flag = '01';
						param.infoList = result.root;
						Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
							layui.layer.open({type:3});
							Joyin.ajaxJson('doManul', param, function() {
								table.load();
								layui.layer.closeAll();
							});
						});
					},null);
				}else{
					var termid = '';
					for(var i=0;i<rows.length;i++){
						if(termid==''||!termid){
							termid = rows[i].termid;
						}else{
							if(termid!=rows[i].termid){
								return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
							}
						}
						if (!(rows[i].sendStatus == '01')) {
							Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
							return;
						}
					}
					var paramObj= {};
					paramObj.flag = '01';
					paramObj.infoList = rows;
					Joyin.confirm("请确认是否要手工处理选中当月的所有记录？", function() {
						layui.layer.open({type:3});
						Joyin.ajaxJson('doManul', paramObj, function() {
							table.load();
							layui.layer.closeAll();
						});
					});
				}

			})
	// 取消手工处理
		$('#ZG11CompanyBondInfoSub_btn_cancelManual').click(function(){
			var manArr=table.getAllCheckedRows();
			if(!manArr || manArr.length ==0){
				var sendStatus = $("#sendStatus").val();
				if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
					Joyin.error("只能选择手工处理的数据，请重新选择！");
					return;
				}
				var paramObj = getSearchParams();
				//paramObj.flag = '02';
				paramObj.AllData = 'Y';
				Joyin.ajax('searchPage',paramObj, function(result){
					var termid = '';
					for(var i = 0 ;i<result.root.length ; i++){
						if(termid==''||!termid){
							termid = result.root[i].termid;
						}else{
							if(termid!=result.root[i].termid){
								return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
							}
						}
					}
					var param= {};
					param.flag = '01';
					param.infoList = result.root;
					Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
						layui.layer.open({type:3});
						Joyin.ajaxJson('cancelManul', param, function() {
							table.load();
							layui.layer.closeAll();
						});
					});
				},null);
			}else{
				var termid = '';
				for(var i=0;i<manArr.length;i++){
					if(termid==''||!termid){
						termid = manArr[i].termid;
					}else{
						if(termid!=manArr[i].termid){
							return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
						}
					}
					if(manArr[i].sendStatus !="03"){
						Joyin.error("只能选择手工处理的数据，请重新选择！");
						return;
					}
				};
				var paramObj= {};
				paramObj.flag = '01';
				paramObj.infoList = manArr;
				Joyin.confirm("确认将选中当月的所有记录取消手工处理吗？", function() {
					layui.layer.open({type:3});
					Joyin.ajaxJson('cancelManul', paramObj, function() {
						table.load();
						layui.layer.closeAll();
					});
				});
			}
		});

	function init(){
		if(isMain=='01'){
			setTimeout(function() {
				if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
					$('#submitTime').val(searchDataStart+' - '+searchDataEnd);
				}else{
					$('#pbcSubmitDeadline').val(searchDataStart+' - '+searchDataEnd);
				}
				table.load();
			}, 500);
		}
	};


	$('#ZG11CompanyBondInfoSub_btn_checkData').click(dateCheckMethod);
	function dateCheckMethod(){
		var checkArr=table.getAllCheckedRows();
		if(checkArr.length === 0) {
			var submitStatus = $('#sendStatus').val();
			var status = $('#status').val();
//            if(submitStatus!='01'){
			if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '01')) {
				Joyin.error("请选择报送状态为未报送的数据进行数据检查或查询条件报送状态选择为未报送！");
				return;
			}
//            else if(status && status.includes('03')){
// 			else if(status && status.indexOf('03') > -1){
// 				Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
// 				return;
// 			}
			var paramObj = getSearchParams()
			if(!status){
				paramObj.status = '01,02';
			}
			Joyin.ajax('reportCheck',paramObj, function(result){
				window.checkResult = result;
				Joyin.checkData(dataTitle, checkUrl, getSearchParams());
			})
			return ;
		}
		var keyList = [];
		var keytermId='';

		for(var i=0;i<checkArr.length;i++){
			//modify by wangmin 修改取数顺序 start 2018-07-12
			keyList.push(checkArr[i].termid );
			keytermId=checkArr[i].termid;
			//modify by wangmin 修改取数顺序 end 2018-07-12
			if(checkArr[i].sendStatus !="01"){
				Joyin.error("数据检查只能检查未报送数据，请重新选择！");
				return;
			}
//            else if(checkArr[i].status && checkArr[i].status.includes('03')){
			else if(checkArr[i].status && checkArr[i].status.indexOf('03') > -1){
				Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
				return;
			}
		};
		var list=Array.from(new Set(keyList));
		if (list.length>1){
			return Joyin.error("所选报送数据包含多个报送月份数据，不允许数据检查");
		}
		Joyin.ajax('reportCheck',{checkKeys:keytermId}, function(result){
			window.checkResult = result;
			Joyin.checkData(dataTitle, checkUrl, {checkKeys: keytermId});
		})
	}

	// 导出人行Excel按钮
	$('#ZG11CompanyBondInfoSub_btn_chbexport').click(function(){
		var expArr=table.getAllCheckedRows();
		if(expArr != null && expArr.length >0){
			Joyin.confirm('确认导出选中信息？',function(){
				exportExcel(1);
			})
		}else{
			if ($('#enddate').val()==null||$('#enddate').val()=='') {
				return Joyin.error("当不选择数据导出时，统计年月必填");
			}
			Joyin.confirm('请确认是否导出人行excel？',function(){
				exportExcel(0);
			})
		}
	});

	/**
	 * 导出
	 * flag:0 全部导出，选中导出
	 */
	var exportExcel = function(flag) {
		var haveNoSendedData = false;
		if(flag == 0){
			var param = getSearchParams();
			var isHaveData = true;
			param.AllData = 'Y';
			Joyin.ajax('searchPage',param, function(result){
				if (!result || result.root.length == 0){
					isHaveData = false;
				}
				var termid = '';
				for(var i = 0 ;i<result.root.length ; i++){
					if(termid==''||!termid){
						termid = result.root[i].termid;
					}else{
						if(termid!=result.root[i].termid){
							return Joyin.error("查询报送数据包含多个报送月份数据，不允许人行Excel导出");
						}
					}
					if(result.root[i].sendStatus=='01'){
						haveNoSendedData = true;
					}
				}
				Joyin.exportExcel("export",param,function () {
					if (isHaveData&&haveNoSendedData){
						Joyin.confirm("导出成功，是否自动标识当前未报送数据的报送状态为“手工处理”？", function() {
							//页面添加滚动条
							layui.layer.open({type:3});
							param.flag = '02';
							Joyin.ajaxJson('doManul', param, function() {
								table.load();
								layui.layer.closeAll();
							});
						});
					}
				});
			},null);
		}else{
			var param = {};
			var expArr=table.getAllCheckedRows();
			var keyList = '';
			var arr = [];
			var statuFlag = false;
			var termId='';
			layui.each(expArr,function(index,data){
				if(index == expArr.length-1){
					keyList = keyList + data.reportuuid+'';
				}else{
					keyList = keyList + data.reportuuid+',';
				}
				if (data.sendStatus=='03'){
					statuFlag=true;
				}
				if (data.sendStatus=='01'){
					haveNoSendedData=true;
				}
				arr.push(data.termid);
				termId=data.termid;
			});
			var list=Array.from(new Set(arr));
			if (list.length>1){
				return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
			}

			param.keyList = keyList;
			if (statuFlag){
				Joyin.confirm("导出数据包含已报送数据，请确认是否导出！", function() {
					Joyin.exportExcel("export", param);
				});
				return;
			} else {
				Joyin.exportExcel("export", param,function () {
					if(!haveNoSendedData)return;
					Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
						//页面添加滚动条
						layui.layer.open({type:3});
						param.flag = '02';
						param.infoList = table.getAllCheckedRows();
						param.termId=termId;
						Joyin.ajaxJson('doManul', param, function() {
							table.load();
							layui.layer.closeAll();
						});
					});
				});
			}
		}
	};
	init();
});
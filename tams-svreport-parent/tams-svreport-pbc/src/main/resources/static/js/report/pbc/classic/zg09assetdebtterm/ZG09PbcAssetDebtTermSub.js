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
			{ title: "数据日期", field: 'statusDate',width:150,type: 'datetime',sortField:'statusDate'},
			{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'sendStatus'},
			{ title: '报送日期',field:'submitTime',width:150,type: 'datetime',sortField:'submitTime'},
			{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'pbcSubmitDeadline'},
			{ title: "统计年月", field: 'termid',width:130,sortField:'termid'},
			{ title: "发行机构代码", field: 'issuePartyId',width:150,sortField:'issuePartyId'},
			{ title: "产品品种", field: 'pbcProdTypeStr',width:150,sortField:'pbcProdType'},
			{ title: "G000A_资产方-债务工具-总计", field: 'g000a',type:'money',digit:2,width:200,sortField:'g000a'},
			{ title: "G000B_资产方-股权-总计", field: 'g000b',type:'money',digit:2,width:200,sortField:'g000b'},
			{ title: "G000C_资产方-特定目的载体份额-总计", field: 'g000c',type:'money',digit:2,width:200,sortField:'g000c'},
			{ title: "G000D_负债及权益方-债务工具-总计", field: 'g000d',type:'money',digit:2,width:200,sortField:'g000d'},
			{ title: "G000E_负债及权益方-实收本金-总计", field: 'g000e',type:'money',digit:2,width:200,sortField:'g000e'},
			{ title: "G100A_资产方-债务工具-1个月（含）以下", field: 'g100a',type:'money',digit:2,width:200,sortField:'g100a'},
			{ title: "G100B_资产方-股权-1个月（含）以下", field: 'g100b',type:'money',digit:2,width:200,sortField:'g100b'},
			{ title: "G100C_资产方-特定目的载体份额-1个月（含）以下", field: 'g100c',type:'money',digit:2,width:200,sortField:'g100c'},
			{ title: "G100D_负债及权益方-债务工具-1个月（含）以下", field: 'g100d',type:'money',digit:2,width:200,sortField:'g100d'},
			{ title: "G100E_负债及权益方-实收本金-1个月（含）以下", field: 'g100e',type:'money',digit:2,width:200,sortField:'g100e'},
			{ title: "G200A_资产方-债务工具-1-3个月（含）", field: 'g200a',type:'money',digit:2,width:200,sortField:'g200a'},
			{ title: "G200B_资产方-股权-1-3个月（含）", field: 'g200b',type:'money',digit:2,width:200,sortField:'g200b'},
			{ title: "G200C_资产方-特定目的载体份额-1-3个月（含）", field: 'g200c',type:'money',digit:2,width:200,sortField:'g200c'},
			{ title: "G200D_负债及权益方-债务工具-1-3个月（含）", field: 'g200d',type:'money',digit:2,width:200,sortField:'g200d'},
			{ title: "G200E_负债及权益方-实收本金-1-3个月（含）", field: 'g200e',type:'money',digit:2,width:200,sortField:'g200e'},
			{ title: "G300A_资产方-债务工具-3-6个月（含）", field: 'g300a',type:'money',digit:2,width:200,sortField:'g300a'},
			{ title: "G300B_资产方-股权-3-6个月（含）", field: 'g300b',type:'money',digit:2,width:200,sortField:'g300b'},
			{ title: "G300C_资产方-特定目的载体份额-3-6个月（含）", field: 'g300c',type:'money',digit:2,width:200,sortField:'g300c'},
			{ title: "G300D_负债及权益方-债务工具-3-6个月（含）", field: 'g300d',type:'money',digit:2,width:200,sortField:'g300d'},
			{ title: "G300E_负债及权益方-实收本金-3-6个月（含）", field: 'g300e',type:'money',digit:2,width:200,sortField:'g300e'},
			{ title: "G400A_资产方-债务工具-6-12个月（含）", field: 'g400a',type:'money',digit:2,width:200,sortField:'g400a'},
			{ title: "G400B_资产方-股权-6-12个月（含）", field: 'g400b',type:'money',digit:2,width:200,sortField:'g400b'},
			{ title: "G400C_资产方-特定目的载体份额-6-12个月（含）", field: 'g400c',type:'money',digit:2,width:200,sortField:'g400c'},
			{ title: "G400D_负债及权益方-债务工具-6-12个月（含）", field: 'g400d',type:'money',digit:2,width:200,sortField:'g400d'},
			{ title: "G400E_负债及权益方-实收本金-6-12个月（含）", field: 'g400e',type:'money',digit:2,width:200,sortField:'g400e'},
			{ title: "G500A_资产方-债务工具-1-2年（含）", field: 'g500a',type:'money',digit:2,width:200,sortField:'g500a'},
			{ title: "G500B_资产方-股权-1-2年（含）", field: 'g500b',type:'money',digit:2,width:200,sortField:'g500b'},
			{ title: "G500C_资产方-特定目的载体份额-1-2年（含）", field: 'g500c',type:'money',digit:2,width:200,sortField:'g500c'},
			{ title: "G500D_负债及权益方-债务工具-1-2年（含）", field: 'g500d',type:'money',digit:2,width:200,sortField:'g500d'},
			{ title: "G500E_负债及权益方-实收本金-1-2年（含）", field: 'g500e',type:'money',digit:2,width:200,sortField:'g500e'},
			{ title: "G600A_资产方-债务工具-2-3年（含）", field: 'g600a',type:'money',digit:2,width:200,sortField:'g600a'},
			{ title: "G600B_资产方-股权-2-3年（含）", field: 'g600b',type:'money',digit:2,width:200,sortField:'g600b'},
			{ title: "G600C_资产方-特定目的载体份额-2-3年（含）", field: 'g600c',type:'money',digit:2,width:200,sortField:'g600c'},
			{ title: "G600D_负债及权益方-债务工具-2-3年（含）：中央交易对手方", field: 'g600d',type:'money',digit:2,width:200,sortField:'g600d'},
			{ title: "G600E_负债及权益方-实收本金-2-3年（含）", field: 'g600e',type:'money',digit:2,width:200,sortField:'g600e'},
			{ title: "G700A_资产方-债务工具-3年以上", field: 'g700a',type:'money',digit:2,width:200,sortField:'g700a'},
			{ title: "G700B_资产方-股权-3年以上", field: 'g700b',type:'money',digit:2,width:200,sortField:'g700b'},
			{ title: "G700C_资产方-特定目的载体份额-3年以上", field: 'g700c',type:'money',digit:2,width:200,sortField:'g700c'},
			{ title: "G700D_负债及权益方-债务工具-3年以上", field: 'g700d',type:'money',digit:2,width:200,sortField:'g700d'},
			{ title: "G700E_负债及权益方-实收本金-3年以上", field: 'g700e',type:'money',digit:2,width:200,sortField:'g700e'},
			{ title: "G800A_资产方-债务工具-无固定期限", field: 'g800a',type:'money',digit:2,width:200,sortField:'g800a'},
			{ title: "G800B_资产方-股权-无固定期限", field: 'g800b',type:'money',digit:2,width:200,sortField:'g800b'},
			{ title: "G800C_资产方-特定目的载体份额-无固定期限", field: 'g800c',type:'money',digit:2,width:200,sortField:'g800c'},
			{ title: "G800D_负债及权益方-债务工具-无固定期限", field: 'g800d',type:'money',digit:2,width:200,sortField:'g800d'},
			{ title: "G800E_负债及权益方-实收本金-无固定期限", field: 'g800e',type:'money',digit:2,width:200,sortField:'g800e'},
			{ title: "00001_附报-金融机构表内资产余额（本外币）", field: 'g00001',type:'money',digit:2,width:200,sortField:'g00001'},
			{ title: "00002_附报-金融机构表内金融资产余额（本外币）", field: 'g00002',type:'money',digit:2,width:200,sortField:'g00002'},
			{field:'sendStatus', visible:false},
			{field:'status', visible:false},
			{ field: 'datuuid', visible: false }
		]
	})
	// 查询按钮
	$('#ZG09PbcAssetDebtTermSub_btn_search').click(function(){
		table.load();
	})

	// 手工处理
			$('#ZG09PbcAssetDebtTermSub_btn_manual').click(function(){
				var rows = table.getAllCheckedRows();
				if(rows.length === 0) {
					var sendStatus = $("#sendStatus").val();
					if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '01')){
						return Joyin.error("当不选择数据进行手工处理时，报送状态必须选择未报送！");
					}
					var paramObj = getSearchParams();
					paramObj.flag = '02';
					Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
						layui.layer.open({type:3});
						Joyin.ajaxJson('doManul', paramObj, function() {
							table.load();
							layui.layer.closeAll();
						});
					});
				}else{
					for(var i=0;i<rows.length;i++){
						if (!(rows[i].sendStatus == '01')) {
							Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
							return;
						}
					}
					var arr = [];
					$.each(rows, function (index, data) {
						arr.push(data.termid);
					});
					var list = Array.from(new Set(arr));
					if (list.length > 1) {
						return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
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
		$('#ZG09PbcAssetDebtTermSub_btn_cancelManual').click(function(){
			var manArr=table.getAllCheckedRows();
			if(!manArr || manArr.length ==0){
				var sendStatus = $("#sendStatus").val();
				if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
					Joyin.error("只能选择手工处理的数据，请重新选择！");
					return;
				}
				var paramObj = getSearchParams();
				paramObj.flag = '02';
				Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
					layui.layer.open({type:3});
					Joyin.ajaxJson('cancelManul', paramObj, function() {
						table.load();
						layui.layer.closeAll();
					});
				});
			}else{
				for(var i=0;i<manArr.length;i++){
					if(manArr[i].sendStatus !="03"){
						Joyin.error("只能选择手工处理的数据，请重新选择！");
						return;
					}
				};
				var arr = [];
				$.each(manArr, function (index, data) {
					arr.push(data.termid);
				});
				var list = Array.from(new Set(arr));
				if (list.length > 1) {
					return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
				}
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

		})

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

	// 导出人行Excel按钮
	$('#ZG09PbcAssetDebtTermSub_btn_chbexport').click(function(){
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

	$('#ZG09PbcAssetDebtTermSub_btn_checkData').click(dateCheckMethod);
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

	/**
	 * 导出
	 * flag:0 全部导出，选中导出
	 */
	var exportExcel = function(flag) {
		if(flag == 0){
			var param = getSearchParams();
			var isHaveData = true;
			Joyin.ajaxSync('searchPage',param, function(result){
				if (!result || result.root.length == 0){
					isHaveData = false;
					return;
				}
			});
			Joyin.exportExcel("export",param);
			if (isHaveData){
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
		}else{
			var param = {};
			var expArr=table.getAllCheckedRows();
			var keyList = '';
			var arr = [];
			var statuFlag = false;
			var termid='';
			layui.each(expArr,function(index,data){
				if(index == expArr.length-1){
					keyList = keyList + data.reportuuid+'';
				}else{
					keyList = keyList + data.reportuuid+',';
				}
				if (data.sendStatus=='03'){
					statuFlag=true;
				}
				arr.push(data.termid);
				termid=data.termid;
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
			} else {
				Joyin.exportExcel("export", param);
				Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
					//页面添加滚动条
					layui.layer.open({type:3});
					param.flag = '02';
					param.infoList = table.getAllCheckedRows();
					param.termid=termid;
					Joyin.ajaxJson('doManul', param, function() {
						table.load();
						layui.layer.closeAll();
					});
				});
			}


		}

	};



	init();

})
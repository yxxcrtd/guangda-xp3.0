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
			{ title: "数据日期", field: 'statusDate',width:120,type: 'datetime',sortField:'statusDate'},
			{ title: "报送状态", field: 'sendStatusStr',width:120,sortField:'sendStatus'},
			{ title: '报送日期',field:'submitTime',width:120,type: 'datetime',sortField:'submitTime'},
			{ title: "报送截止日", field: 'pbcSubmitDeadline',width:120, type: 'date',sortField:'pbcSubmitDeadline'},
			{ title: "统计年月", field: 'termid',width:120,sortField:'termid'},
			{ title: "发行机构代码", field: 'issuePartyId',width:150,sortField:'issuePartyId'},
			{ title: "产品品种", field: 'pbcProdTypeStr',width:150,sortField:'pbcProdType'},
			{ title: "h10000_一、债务证券（按评级分类）", field: 'h10000',type:'money',digit:2,width:200,sortField:'h10000'},
			{ title: "h11000_其中：政策性金融债券", field: 'h11000',type:'money',digit:2,width:200,sortField:'h11000'},
			{ title: "h11100_AA+（含）以上", field: 'h11100',type:'money',digit:2,width:200,sortField:'h11100'},
			{ title: "h11200_AA+以下", field: 'h11200',type:'money',digit:2,width:200,sortField:'h11200'},
			{ title: "h11300_无评级", field: 'h11300',type:'money',digit:2,width:200,sortField:'h11300'},
			{ title: "h12000_其他金融债券", field: 'h12000',type:'money',digit:2,width:200,sortField:'h12000'},
			{ title: "h12100_AA+（含）以上", field: 'h12100',type:'money',digit:2,width:200,sortField:'h12100'},
			{ title: "h12200_AA+以下", field: 'h12200',type:'money',digit:2,width:200,sortField:'h12200'},
			{ title: "h12300_无评级", field: 'h12300',type:'money',digit:2,width:200,sortField:'h12300'},
			{ title: "h13000_企业债券", field: 'h13000',type:'money',digit:2,width:200,sortField:'h13000'},
			{ title: "h13100_AA+（含）以上", field: 'h13100',type:'money',digit:2,width:200,sortField:'h13100'},
			{ title: "h13200_AA+以下", field: 'h13200',type:'money',digit:2,width:200,sortField:'h13200'},
			{ title: "h13300_无评级", field: 'h13300',type:'money',digit:2,width:200,sortField:'h13300'},
			{ title: "h14000_资产支持证券", field: 'h14000',type:'money',digit:2,width:200,sortField:'h14000'},
			{ title: "h14100_AA+（含）以上", field: 'h14100',type:'money',digit:2,width:200,sortField:'h14100'},
			{ title: "h14200_AA+以下", field: 'h14200',type:'money',digit:2,width:200,sortField:'h14200'},
			{ title: "h14300_无评级", field: 'h14300',type:'money',digit:2,width:200,sortField:'h14300'},
			{ title: "h20000_二、企业债券按券种分类", field: 'h20000',type:'money',digit:2,width:200,sortField:'h20000'},
			{ title: "h21000_其中：企业债", field: 'h21000',type:'money',digit:2,width:200,sortField:'h21000'},
			{ title: "h22000_公司债", field: 'h22000',type:'money',digit:2,width:200,sortField:'h22000'},
			{ title: "h23000_非金融企业债务融资工具", field: 'h23000',type:'money',digit:2,width:200,sortField:'h23000'},
			{ title: "h30000_三、银行资本补充工具合计", field: 'h30000',type:'money',digit:2,width:200,sortField:'h30000'},
			{ title: "h31000_其中：优先股", field: 'h31000',type:'money',digit:2,width:200,sortField:'h31000'},
			{ title: "h32000_永续债（债性）", field: 'h32000',type:'money',digit:2,width:200,sortField:'h32000'},
			{ title: "h33000_永续债（股性）", field: 'h33000',type:'money',digit:2,width:200,sortField:'h33000'},
			{ title: "h34000_二级资本债", field: 'h34000',type:'money',digit:2,width:200,sortField:'h34000'},
			{ title: "h40000_四、债券逆回购", field: 'h40000',type:'money',digit:2,width:200,sortField:'h40000'},
			{ title: "h41000_住户", field: 'h41000',type:'money',digit:2,width:200,sortField:'h41000'},
			{ title: "h42000_广义政府", field: 'h42000',type:'money',digit:2,width:200,sortField:'h42000'},
			{ title: "h43000_非金融企业", field: 'h43000',type:'money',digit:2,width:200,sortField:'h43000'},
			{ title: "h44000_银行业存款类金融机构", field: 'h44000',type:'money',digit:2,width:200,sortField:'h44000'},
			{ title: "h45000_银行业非存款类金融机构", field: 'h45000',type:'money',digit:2,width:200,sortField:'h45000'},
			{ title: "h46000_非银行业金融机构", field: 'h46000',type:'money',digit:2,width:200,sortField:'h46000'},
			{ title: "h46100_其中：中央交易对手方", field: 'h46100',type:'money',digit:2,width:200,sortField:'h46100'},
			{ title: "h47000_特定目的载体", field: 'h47000',type:'money',digit:2,width:200,sortField:'h47000'},
			{ title: "h47100_银行非保本理财", field: 'h47100',type:'money',digit:2,width:200,sortField:'h47100'},
			{ title: "h47200_信托公司资管产品", field: 'h47200',type:'money',digit:2,width:200,sortField:'h47200'},
			{ title: "h47300_证券公司及其子公司资管产品", field: 'h47300',type:'money',digit:2,width:200,sortField:'h47300'},
			{ title: "h47400_基金管理公司及其子公司专户", field: 'h47400',type:'money',digit:2,width:200,sortField:'h47400'},
			{ title: "h47500_期货公司及其子公司资管产品", field: 'h47500',type:'money',digit:2,width:200,sortField:'h47500'},
			{ title: "h47600_保险资管产品", field: 'h47600',type:'money',digit:2,width:200,sortField:'h47600'},
			{ title: "h47700_金融资产投资公司资管产品", field: 'h47700',type:'money',digit:2,width:200,sortField:'h47700'},
			{ title: "h47800_公募基金", field: 'h47800',type:'money',digit:2,width:200,sortField:'h47800'},
			{ title: "h47900_私募机构私募基金", field: 'h47900',type:'money',digit:2,width:200,sortField:'h47900'},
			{ title: "h47a00_其他特定目的载体", field: 'h47a00',type:'money',digit:2,width:200,sortField:'h47a00'},
			{ title: "h48000_境外", field: 'h48000',type:'money',digit:2,width:200,sortField:'h48000'},
			{ title: "h50000_五、资产减值准备", field: 'h50000',type:'money',digit:2,width:200,sortField:'h50000'},
			{ title: "h51000_存款", field: 'h51000',type:'money',digit:2,width:200,sortField:'h51000'},
			{ title: "h52000_可转让存单", field: 'h52000',type:'money',digit:2,width:200,sortField:'h52000'},
			{ title: "h53000_债务证券", field: 'h53000',type:'money',digit:2,width:200,sortField:'h53000'},
			{ title: "h54000_除回购和拆借外贷款", field: 'h54000',type:'money',digit:2,width:200,sortField:'h54000'},
			{ title: "h55000_回购和拆借", field: 'h55000',type:'money',digit:2,width:200,sortField:'h55000'},
			{ title: "h56000_资产收益权", field: 'h56000',type:'money',digit:2,width:200,sortField:'h56000'},
			{ title: "h57000_股权及特定目的载体份额", field: 'h57000',type:'money',digit:2,width:200,sortField:'h57000'},
			{ title: "h58000_金融衍生品", field: 'h58000',type:'money',digit:2,width:200,sortField:'h58000'},
			{ title: "h59000_应收账款", field: 'h59000',type:'money',digit:2,width:200,sortField:'h59000'},
			{ title: "h5a000_其他", field: 'h5a000',type:'money',digit:2,width:200,sortField:'h5a000'},
			{field:'sendStatus', visible:false},
			{field:'status', visible:false},
			{ field: 'datuuid', visible: false }
		]
	})
	// 查询按钮
	$('#ZG10PbcColAssetConfigurationSub_btn_search').click(function(){
		table.load();
	})

	// 手工处理
			$('#ZG10PbcColAssetConfigurationSub_btn_manual').click(function(){
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
					var arr = [];
					layui.each(rows,function(index,data){
						arr.push(data.termid);
					});
					var list=Array.from(new Set(arr));
					if (list.length>1){
						return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
					}
					for(var i=0;i<rows.length;i++){
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

	$('#ZG10PbcColAssetConfigurationSub_btn_checkData').click(dateCheckMethod);
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

	// 取消手工处理
		$('#ZG10PbcColAssetConfigurationSub_btn_cancelManual').click(function(){
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
				var arr = [];
				layui.each(manArr,function(index,data){
					arr.push(data.termid);
				});
				var list=Array.from(new Set(arr));
				if (list.length>1){
					return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
				}
				for(var i=0;i<manArr.length;i++){
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
	$('#ZG10PbcColAssetConfigurationSub_btn_chbexport').click(function(){
		var expArr=table.getAllCheckedRows();
		if(expArr != null && expArr.length >0){
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
                arr.push(data.termid);
                termId=data.termid;
            });
            var list=Array.from(new Set(arr));
            if (list.length>1){
                return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
            }
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
			} else {
				Joyin.exportExcel("export", param);
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
			}


		}

	};



	init();

})
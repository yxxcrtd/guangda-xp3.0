$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			pbcProdId:$('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(','),
			//结束日（统计年月）
			enddate : $('#enddate').val(),
            //内部产品代码
			prodId:$('#prodId').val()==null?null:$('#prodId').val().join(","),
			//报送状态
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			//数据日期
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			//报送日期
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			//数据类型
			statusType:$('#statusType').val()==null?null:$('#statusType').val().join(','),
			//报送截止日
			submitDeadLineEnd:$('#pbcSubmitDeadline').endDate(),
			submitDeadLineStart:$('#pbcSubmitDeadline').startDate(),
			//币种
			ccy:$('#ccy').val()==null?null:$('#ccy').val().join(','),

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
			{ title: "数据日期", field: 'statusDate',width:150,type: 'datetime',sortField:'STATUS_DATE'},
			{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'SEND_STATUS'},
			{ title: '报送日期',field:'submitTime',width:150,type: 'datetime',sortField:'SUBMIT_TIME'},
			{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'PBC_SUBMIT_DEADLINE'},
			{ title: "统计年月", field: 'termid',width:130,sortField:'termid'},
			{ title: "内部产品代码", field: 'prodId',width:150,sortField:'PROD_ID'},
			{ title: "产品代码", field: 'pbcProdId',width:150,sortField:'PBC_PROD_ID'},
			{ title: "币种", field: 'ccy',width:150,sortField:'ccy'},
			{ title: "数据类型", field: 'statusTypeStr',width:150,sortField:'STATUS_TYPE'},
			{ title: "A0000_资产合计", field: 'a0000',type:'money',digit:2,width:200,sortField:'a0000'},
			{ title: "A1000_现金", field: 'a1000',type:'money',digit:2,width:200,sortField:'a1000'},
			{ title: "A2000_存款", field: 'a2000',type:'money',digit:2,width:200,sortField:'a2000'},
			{ title: "A2100_境内存款", field: 'a2100',type:'money',digit:2,width:200,sortField:'a2100'},
			{ title: "A2110_活期存款", field: 'a2110',type:'money',digit:2,width:200,sortField:'a2110'},
			{ title: "A2120_定期和其他存款", field: 'a2120',type:'money',digit:2,width:200,sortField:'a2120'},
			{ title: "A2200_境外存款", field: 'a2200',type:'money',digit:2,width:200,sortField:'a2200'},
			{ title: "A2210_活期存款", field: 'a2210',type:'money',digit:2,width:200,sortField:'a2210'},
			{ title: "A2220_定期和其他存款", field: 'a2220',type:'money',digit:2,width:200,sortField:'a2220'},
			{ title: "A3000_可转让存单", field: 'a3000',type:'money',digit:2,width:200,sortField:'a3000'},
			{ title: "A3100_同业存单", field: 'a3100',type:'money',digit:2,width:200,sortField:'a3100'},
			{ title: "A3200_大额存单", field: 'a3200',type:'money',digit:2,width:200,sortField:'a3200'},
			{ title: "A4000_债务证券", field: 'a4000',type:'money',digit:2,width:200,sortField:'a4000'},
			{ title: "A4100_政府债券", field: 'a4100',type:'money',digit:2,width:200,sortField:'a4100'},
			{ title: "A4200_中央银行债券", field: 'a4200',type:'money',digit:2,width:200,sortField:'a4200'},
			{ title: "A4300_政策性金融债券", field: 'a4300',type:'money',digit:2,width:200,sortField:'a4300'},
			{ title: "A4400_其他金融债券", field: 'a4400',type:'money',digit:2,width:200,sortField:'a4400'},
			{ title: "A4500_企业债券", field: 'a4500',type:'money',digit:2,width:200,sortField:'a4500'},
			{ title: "A4600_票据", field: 'a4600',type:'money',digit:2,width:200,sortField:'a4600'},
			{ title: "A4700_资产支持证券", field: 'a4700',type:'money',digit:2,width:200,sortField:'a4700'},
			{ title: "A4800_境外债券", field: 'a4800',type:'money',digit:2,width:200,sortField:'a4800'},
			{ title: "A5000_贷款", field: 'a5000',type:'money',digit:2,width:200,sortField:'a5000'},
			{ title: "A5100_除回购和拆借外贷款", field: 'a5100',type:'money',digit:2,width:200,sortField:'a5100'},
			{ title: "A5200_回购和拆借", field: 'a5200',type:'money',digit:2,width:200,sortField:'a5200'},
			{ title: "A5210_住户", field: 'a5210',type:'money',digit:2,width:200,sortField:'a5210'},
			{ title: "A5220_广义政府", field: 'a5220',type:'money',digit:2,width:200,sortField:'a5220'},
			{ title: "A5230_非金融企业", field: 'a5230',type:'money',digit:2,width:200,sortField:'a5230'},
			{ title: "A5240_银行业存款类金融机构", field: 'a5240',type:'money',digit:2,width:200,sortField:'a5240'},
			{ title: "A5250_银行业非存款类金融机构", field: 'a5250',type:'money',digit:2,width:200,sortField:'a5250'},
			{ title: "A5260_非银行业金融机构", field: 'a5260',type:'money',digit:2,width:200,sortField:'a5260'},
			{ title: "A5261_其中：中央交易对手方", field: 'a5261',type:'money',digit:2,width:200,sortField:'a5261'},
			{ title: "A5270_特定目的载体", field: 'a5270',type:'money',digit:2,width:200,sortField:'a5270'},
			{ title: "A5271_银行非保本理财", field: 'a5271',type:'money',digit:2,width:200,sortField:'a5271'},
			{ title: "A5272_信托公司资管产品", field: 'a5272',type:'money',digit:2,width:200,sortField:'a5272'},
			{ title: "A5273_证券公司及其子公司资管产品", field: 'a5273',type:'money',digit:2,width:200,sortField:'a5273'},
			{ title: "A5274_基金管理公司及其子公司专户", field: 'a5274',type:'money',digit:2,width:200,sortField:'a5274'},
			{ title: "A5275_期货公司及其子公司资管产品", field: 'a5275',type:'money',digit:2,width:200,sortField:'a5275'},
			{ title: "A5276_保险资管产品", field: 'a5276',type:'money',digit:2,width:200,sortField:'a5276'},
			{ title: "A5277_金融资产投资公司资管产品", field: 'a5277',type:'money',digit:2,width:200,sortField:'a5277'},
			{ title: "A5278_公募基金", field: 'a5278',type:'money',digit:2,width:200,sortField:'a5278'},
			{ title: "A5279_私募机构私募基金", field: 'a5279',type:'money',digit:2,width:200,sortField:'a5279'},
			{ title: "A527a_其他特定目的载体", field: 'a527a',type:'money',digit:2,width:200,sortField:'a527a'},
			{ title: "A5280_境外", field: 'a5280',type:'money',digit:2,width:200,sortField:'a5280'},
			{ title: "A6000_资产收益权", field: 'a6000',type:'money',digit:2,width:200,sortField:'a6000'},
			{ title: "A6100_贷款收益权", field: 'a6100',type:'money',digit:2,width:200,sortField:'a6100'},
			{ title: "A6200_债务证券收益权", field: 'a6200',type:'money',digit:2,width:200,sortField:'a6200'},
			{ title: "A6300_应收账款收益权", field: 'a6300',type:'money',digit:2,width:200,sortField:'a6300'},
			{ title: "A6400_其他债权收益权", field: 'a6400',type:'money',digit:2,width:200,sortField:'a6400'},
			{ title: "A6500_股票收益权", field: 'a6500',type:'money',digit:2,width:200,sortField:'a6500'},
			{ title: "A6600_其他股权收益权", field: 'a6600',type:'money',digit:2,width:200,sortField:'a6600'},
			{ title: "A6700_物权资产收益权", field: 'a6700',type:'money',digit:2,width:200,sortField:'a6700'},
			{ title: "A7000_股权及特定目的载体份额", field: 'a7000',type:'money',digit:2,width:200,sortField:'a7000'},
			{ title: "A7100_股票", field: 'a7100',type:'money',digit:2,width:200,sortField:'a7100'},
			{ title: "A7110_非金融企业股票", field: 'a7110',type:'money',digit:2,width:200,sortField:'a7110'},
			{ title: "A7120_金融机构股票", field: 'a7120',type:'money',digit:2,width:200,sortField:'a7120'},
			{ title: "A7200_特定目的载体份额", field: 'a7200',type:'money',digit:2,width:200,sortField:'a7200'},
			{ title: "A7210_银行非保本理财", field: 'a7210',type:'money',digit:2,width:200,sortField:'a7210'},
			{ title: "A7220_信托公司资管产品", field: 'a7220',type:'money',digit:2,width:200,sortField:'a7220'},
			{ title: "A7230_证券公司及其子公司资管产品", field: 'a7230',type:'money',digit:2,width:200,sortField:'a7230'},
			{ title: "A7240_基金管理公司及其子公司专户", field: 'a7240',type:'money',digit:2,width:200,sortField:'a7240'},
			{ title: "A7250_期货公司及其子公司资管产品", field: 'a7250',type:'money',digit:2,width:200,sortField:'a7200'},
			{ title: "A7260_保险资管产品", field: 'a7260',type:'money',digit:2,width:200,sortField:'a7260'},
			{ title: "A7270_金融资产投资公司资管产品", field: 'a7270',type:'money',digit:2,width:200,sortField:'a7270'},
			{ title: "A7280_公募基金", field: 'a7280',type:'money',digit:2,width:200,sortField:'a7280'},
			{ title: "A7290_私募机构私募基金", field: 'a7290',type:'money',digit:2,width:200,sortField:'a7290'},
			{ title: "A72a0_其他特定目的载体", field: 'a72a0',type:'money',digit:2,width:200,sortField:'a72a0'},
			{ title: "A7300_其他股权", field: 'a7300',type:'money',digit:2,width:200,sortField:'a7300'},
			{ title: "A7310_非金融企业股权", field: 'a7310',type:'money',digit:2,width:200,sortField:'a7310'},
			{ title: "A7320_金融机构股权", field: 'a7320',type:'money',digit:2,width:200,sortField:'a7320'},
			{ title: "A7400_境外", field: 'a7400',type:'money',digit:2,width:200,sortField:'a7400'},
			{ title: "A8000_金融衍生品", field: 'a8000',type:'money',digit:2,width:200,sortField:'a8000'},
			{ title: "A8100_境内", field: 'a8100',type:'money',digit:2,width:200,sortField:'a8100'},
			{ title: "A8200_境外", field: 'a8200',type:'money',digit:2,width:200,sortField:'a8200'},
			{ title: "A9000_应收账款", field: 'a9000',type:'money',digit:2,width:200,sortField:'a9000'},
			{ title: "AA000_黄金", field: 'aa000',type:'money',digit:2,width:200,sortField:'aa000'},
			{ title: "AB000_非金融资产", field: 'ab000',type:'money',digit:2,width:200,sortField:'ab000'},
			{ title: "AC000_资产减值准备（减）", field: 'ac000',type:'money',digit:2,width:200,sortField:'ac000'},
			{ title: "D0000_负债及权益合计", field: 'd0000',type:'money',digit:2,width:200,sortField:'d0000'},
			{ title: "B0000_负债合计", field: 'b0000',type:'money',digit:2,width:200,sortField:'b0000'},
			{ title: "B1000_贷款", field: 'b1000',type:'money',digit:2,width:200,sortField:'b1000'},
			{ title: "B1100_除回购和拆借外贷款", field: 'b1100',type:'money',digit:2,width:200,sortField:'b1100'},
			{ title: "B1200_回购和拆借", field: 'b1200',type:'money',digit:2,width:200,sortField:'b1200'},
			{ title: "B1210_住户", field: 'b1210',type:'money',digit:2,width:200,sortField:'b1210'},
			{ title: "B1220_广义政府", field: 'b1220',type:'money',digit:2,width:200,sortField:'b1220'},
			{ title: "B1230_非金融企业", field: 'b1230',type:'money',digit:2,width:200,sortField:'b1230'},
			{ title: "B1240_银行业存款类金融机构", field: 'b1240',type:'money',digit:2,width:200,sortField:'b1240'},
			{ title: "B1250_银行业非存款类金融机构", field: 'b1250',type:'money',digit:2,width:200,sortField:'b1200'},
			{ title: "B1260_非银行业金融机构", field: 'b1260',type:'money',digit:2,width:200,sortField:'b1260'},
			{ title: "B1261_其中：中央交易对手方", field: 'b1261',type:'money',digit:2,width:200,sortField:'b1261'},
			{ title: "B1270_特定目的载体", field: 'b1270',type:'money',digit:2,width:200,sortField:'b1270'},
			{ title: "B1271_银行非保本理财", field: 'b1271',type:'money',digit:2,width:200,sortField:'b1271'},
			{ title: "B1272_信托公司资管产品", field: 'b1272',type:'money',digit:2,width:200,sortField:'b1272'},
			{ title: "B1273_证券公司及其子公司资管产品", field: 'b1273',type:'money',digit:2,width:200,sortField:'b1273'},
			{ title: "B1274_基金管理公司及其子公司专户", field: 'b1274',type:'money',digit:2,width:200,sortField:'b1274'},
			{ title: "B1275_期货公司及其子公司资管产品", field: 'b1275',type:'money',digit:2,width:200,sortField:'b1275'},
			{ title: "B1276_保险资管产品", field: 'b1276',type:'money',digit:2,width:200,sortField:'b1276'},
			{ title: "B1277_金融资产投资公司资管产品", field: 'b1277',type:'money',digit:2,width:200,sortField:'b1277'},
			{ title: "B1278_公募基金", field: 'b1278',type:'money',digit:2,width:200,sortField:'b1278'},
			{ title: "B1279_私募机构私募基金", field: 'b1279',type:'money',digit:2,width:200,sortField:'b1279'},
			{ title: "B127a_其他特定目的载体", field: 'b127a',type:'money',digit:2,width:200,sortField:'b127a'},
			{ title: "B1280_境外", field: 'b1280',type:'money',digit:2,width:200,sortField:'b1280'},
			{ title: "B2000_资产支持证券", field: 'b2000',type:'money',digit:2,width:200,sortField:'b2000'},
			{ title: "B3000_金融衍生品", field: 'b3000',type:'money',digit:2,width:200,sortField:'b3000'},
			{ title: "B3100_境内", field: 'b3100',type:'money',digit:2,width:200,sortField:'b3100'},
			{ title: "B3200_境外", field: 'b3200',type:'money',digit:2,width:200,sortField:'b3200'},
			{ title: "B4000_应付账款", field: 'b4000',type:'money',digit:2,width:200,sortField:'b4000'},
			{ title: "B5000_其他负债", field: 'b5000',type:'money',digit:2,width:200,sortField:'b5000'},
			{ title: "C0000_权益合计", field: 'c0000',type:'money',digit:2,width:200,sortField:'c0000'},
			{ title: "C1000_实收本金", field: 'c1000',type:'money',digit:2,width:200,sortField:'c1000'},
			{ title: "C1100_优先级", field: 'c1100',type:'money',digit:2,width:200,sortField:'c1100'},
			{ title: "C1110_住户", field: 'c1110',type:'money',digit:2,width:200,sortField:'c1110'},
			{ title: "C1120_广义政府", field: 'c1120',type:'money',digit:2,width:200,sortField:'c1120'},
			{ title: "C1130_非金融企业", field: 'c1130',type:'money',digit:2,width:200,sortField:'c1130'},
			{ title: "C1140_银行业存款类金融机构", field: 'c1140',type:'money',digit:2,width:200,sortField:'c1140'},
			{ title: "C1150_银行业非存款类金融机构", field: 'c1150',type:'money',digit:2,width:200,sortField:'c1150'},
			{ title: "C1160_非银行业金融机构", field: 'c1160',type:'money',digit:2,width:200,sortField:'c1160'},
			{ title: "C1170_特定目的载体", field: 'c1170',type:'money',digit:2,width:200,sortField:'c1170'},
			{ title: "C1171_银行非保本理财", field: 'c1171',type:'money',digit:2,width:200,sortField:'c1171'},
			{ title: "C1172_信托公司资管产品", field: 'c1172',type:'money',digit:2,width:200,sortField:'c1172'},
			{ title: "C1173_证券公司及其子公司资管产品", field: 'c1173',type:'money',digit:2,width:200,sortField:'c1173'},
			{ title: "C1174_基金管理公司及其子公司专户", field: 'c1174',type:'money',digit:2,width:200,sortField:'c1174'},
			{ title: "C1175_期货公司及其子公司资管产品", field: 'c1175',type:'money',digit:2,width:200,sortField:'c1175'},
			{ title: "C1176_保险资管产品", field: 'c1176',type:'money',digit:2,width:200,sortField:'c1176'},
			{ title: "C1177_金融资产投资公司资管产品", field: 'c1177',type:'money',digit:2,width:200,sortField:'c1177'},
			{ title: "C1178_公募基金", field: 'c1178',type:'money',digit:2,width:200,sortField:'c1178'},
			{ title: "C1179_私募机构私募基金", field: 'c1179',type:'money',digit:2,width:200,sortField:'c1179'},
			{ title: "C117a_其他特定目的载体", field: 'c117a',type:'money',digit:2,width:200,sortField:'c117a'},
			{ title: "C1180_境外", field: 'c1180',type:'money',digit:2,width:200,sortField:'c1180'},
			{ title: "C1200_劣后级", field: 'c1200',type:'money',digit:2,width:200,sortField:'c1200'},
			{ title: "C1210_住户", field: 'c1210',type:'money',digit:2,width:200,sortField:'c1210'},
			{ title: "C1220_广义政府", field: 'c1220',type:'money',digit:2,width:200,sortField:'c1220'},
			{ title: "C1230_非金融企业", field: 'c1230',type:'money',digit:2,width:200,sortField:'c1230'},
			{ title: "C1240_银行业存款类金融机构", field: 'c1240',type:'money',digit:2,width:200,sortField:'c1240'},
			{ title: "C1250_银行业非存款类金融机构", field: 'c1250',type:'money',digit:2,width:200,sortField:'c1200'},
			{ title: "C1260_非银行业金融机构", field: 'c1260',type:'money',digit:2,width:200,sortField:'c1260'},
			{ title: "C1270_特定目的载体", field: 'c1270',type:'money',digit:2,width:200,sortField:'c1270'},
			{ title: "C1271_银行非保本理财", field: 'c1271',type:'money',digit:2,width:200,sortField:'c1271'},
			{ title: "C1272_信托公司资管产品", field: 'c1272',type:'money',digit:2,width:200,sortField:'c1272'},
			{ title: "C1273_证券公司及其子公司资管产品", field: 'c1273',type:'money',digit:2,width:200,sortField:'c1273'},
			{ title: "C1274_基金管理公司及其子公司专户", field: 'c1274',type:'money',digit:2,width:200,sortField:'c1274'},
			{ title: "C1275_期货公司及其子公司资管产品", field: 'c1275',type:'money',digit:2,width:200,sortField:'c1275'},
			{ title: "C1276_保险资管产品", field: 'c1276',type:'money',digit:2,width:200,sortField:'c1276'},
			{ title: "C1277_金融资产投资公司资管产品", field: 'c1277',type:'money',digit:2,width:200,sortField:'c1277'},
			{ title: "C1278_公募基金", field: 'c1278',type:'money',digit:2,width:200,sortField:'c1278'},
			{ title: "C1279_私募机构私募基金", field: 'c1279',type:'money',digit:2,width:200,sortField:'c1279'},
			{ title: "C127a_其他特定目的载体", field: 'c127a',type:'money',digit:2,width:200,sortField:'c127a'},
			{ title: "C1280_境外", field: 'c1280',type:'money',digit:2,width:200,sortField:'c1280'},
			{ title: "C3000_未分配利润", field: 'c3000',type:'money',digit:2,width:200,sortField:'c3000'},
			{ title: "C4000_其他综合收益", field: 'c4000',type:'money',digit:2,width:200,sortField:'c4000'},
			{field:'sendStatus', visible:false},
			{field:'status', visible:false},
			{ field: 'datuuid', visible: false }
		]
	})
	// 查询按钮
	$('#ZG05PbcColPrdAssetDebtSub_btn_search').click(function(){
		table.load();
	})
	// 手工处理
	$('#ZG05PbcColPrdAssetDebtSub_btn_manual').click(function(){
		var rows = table.getAllCheckedRows();
		if(rows.length === 0) {
			return Joyin.error("请至少选择一条记录");
		}else{
			/**	RHPTSJ-122**/
			var arr = [];
			layui.each(rows,function(index,data){
				arr.push(data.termid);
			});
			var list=Array.from(new Set(arr));
			if (list.length>1){
				return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
			}
			/**	RHPTSJ-122**/
			for(var i=0;i<rows.length;i++){
				if (!(rows[i].sendStatus == '01')) {
					Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
					return;
				}
			}
			var paramObj= {};
			paramObj.flag = '01';
			paramObj.infoList = rows;
			Joyin.confirm("请确认是否要手工处理选中产品的所有记录？", function() {
				layui.layer.open({type:3});
				Joyin.ajaxJson('doManul', paramObj, function() {
					table.load();
					layui.layer.closeAll();
				});
			});
		}
	})

	//数据检查
	$('#ZG05PbcColPrdAssetDebtSub_btn_checkData').click(dateCheckMethod);
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
	$('#ZG05PbcColPrdAssetDebtSub_btn_cancelManual').click(function(){
		var manArr=table.getAllCheckedRows();
		if(!manArr || manArr.length ==0){
			return Joyin.error("请至少选择一条记录");
		}else{
			/**	RHPTSJ-122**/
			var arr = [];
			layui.each(manArr,function(index,data){
				arr.push(data.termid);
			});
			var list=Array.from(new Set(arr));
			if (list.length>1){
				return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
			}
			/**	RHPTSJ-122**/
			for(var i=0;i<manArr.length;i++){
				if(manArr[i].sendStatus !="03"){
					Joyin.error("只能选择手工处理的数据，请重新选择！");
					return;
				}
			};
			var paramObj= {};
			paramObj.flag = '01';
			paramObj.infoList = manArr;
			Joyin.confirm("确认将选中产品的所有记录取消手工处理吗？", function() {
				paramObj.doFlag = "0";
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
	$('#ZG05PbcColPrdAssetDebtSub_btn_chbexport').click(function(){
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
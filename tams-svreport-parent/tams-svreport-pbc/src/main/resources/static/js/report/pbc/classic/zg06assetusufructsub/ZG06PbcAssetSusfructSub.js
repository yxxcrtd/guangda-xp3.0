$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			pbcProdId:$('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(","),
			prodId:$('#prodId').val()==null?null:$('#prodId').val().join(","),
			assetType:$('#assetType').val()==null?null:$('#assetType').val().join(","),
			assetCode:$('#assetCode').val(),
			termId:$('#termId').val(),
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			pbcSubmitDeadlineDateEnd:$('#pbcSubmitDeadline').endDate(),
			pbcSubmitDeadlineDateStart:$('#pbcSubmitDeadline').startDate(),
			
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        showDetail: true,//双击详情对比画面
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[
				{ field: 'registSeqno',visible:false},
				{ title: "数据状态", field: 'statusStr',width:150,sortField:'status',visible:false},
                				{ title: "数据日期", field: 'statusDate',width:150,type:'datetime',sortField:'statusDate'},
                				{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'sendStatus'},
                				{ title: '报送日期',field:'submitTime',width:150,type:'datetime',sortField:'submitTime'},
                				{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'pbcSubmitDeadline'},
                				{ title: "统计年月", field: 'termId',width:130, sortField:'termId'},
                				{ field: 'detailuuid',visible:false},
                                { field: 'pbcProdId',visible:false},
                				{ title: "内部产品代码", field: 'prodId',width:150,sortField:'prodId'},
                				{ title: "资产代码", field: 'assetCode',width:150,sortField:'assetCode'},
                				{ title: "产品代码", field: 'pbcProdIdShow',width:150,sortField:'pbcProdIdShow'},
                				{ title: "资产负债项目", field: 'assetLiabilitiesStr',width:150,sortField:'assetLiabilities'},
                				{ title: "资产收益权内部编码", field: 'assetUsufructCode',width:150,sortField:'assetUsufructCode'},
                				{ title: "基础资产出让机构名称", field: 'assetTransferName',width:150,sortField:'assetTransferName'},
                				{ title: "基础资产出让机构代码", field: 'assetTransferId',width:150,sortField:'assetTransferId'},
                				{ title: "基础资产出让机构类型", field: 'assetInvestStr',width:150,sortField:'assetInvest'},
                				{ title: "基础资产出让机构行业", field: 'assetTransferIinduStr',width:150,sortField:'assetTransferIindu'},
                				{ title: "基础资产出让机构注册地区", field: 'assetTransferStr',width:150,sortField:'assetTransfer'},
                				{ title: "基础资产出让机构经济成分", field: 'economicCompositionStr',width:150,sortField:'economicComposition'},
                				{ title: "基础资产出让机构规模", field: 'assetTransferScaleStr',width:150,sortField:'assetTransferScale'},
                				{ title: "转让起始日期", field: 'transferVdateStr',width:150,sortField:'transferVdate'},
                				{ title: "转让预计终止日期", field: 'transferEstiMdateStr',width:150,sortField:'transferEstiMdate'},
                				{ title: "转让展期到期日期", field: 'transferExtdueMdateStr',width:150,sortField:'transferExtdueMdate'},
                				{ title: "转让实际终止日期", field: 'transferActMdateStr',width:150,sortField:'transferActMdate'},
                				{ title: "基础资产类型", field: 'basicAssetTypeStr',width:150,sortField:'basicAssetType'},
                				{ title: "基础资产原始协议币种", field: 'assetsOrigCcyStr',width:150,sortField:'assetsOrigCcy'},
                				{ title: "基础资产原始协议金额", field: 'assetsOrigAmt',width:150,type:'money',digit:2,sortField:'assetsOrigAmt'},
                				{ title: "基础资产原始协议金额折人民币", field: 'assetsOrigAmtCny',width:150,type:'money',digit:2,sortField:'assetsOrigAmtCny'},
                                { title: "基础资产转让币种", field: 'currencyTransferCcyStr',width:150,sortField:'currencyTransferCcy'},
                                { title: "基础资产转让金额", field: 'currencyTransferAmt',width:150,type:'money',digit:2,sortField:'currencyTransferAmt'},
                                { title: "基础资产转让金额折人民币", field: 'currencyTransferAmtCny',width:150,type:'money',digit:2,sortField:'currencyTransferAmtCny'},
                                { title: "出让机构出表标识", field: 'labelTransferorMarkStr',width:150,sortField:'labelTransferorMark'},
                                { title: "出让机构回购标识", field: 'repurchaseMarkStr',width:150,sortField:'repurchaseMark'},
                                { title: "利率是否固定", field: 'rateFixedFlagStr',width:150,sortField:'rateFixedFlag'},
                                { title: "利率水平", field: 'assetRateLevel',width:150,type:'money',digit:5,sortField:'assetRateLevel'},
                                { title: "担保方式", field: 'guaranteeMethodStr',width:150,sortField:'guaranteeMethod'},
                                { title: "基础资产投向部门", field: 'assetsInvDepaStr',width:150,sortField:'assetsInvDepa'},
                                { title: "基础资产期末币种", field: 'currencyEperiodCcyStr',width:150,sortField:'currencyEperiodCcy'},
                                { title: "基础资产期末余额", field: 'currencyAmt',width:150,type:'money',digit:2,sortField:'currencyAmt'},
                                { title: "基础资产期末余额折人民币", field: 'currencyAmtCny',width:150,type:'money',digit:2,sortField:'currencyAmtCny'},
                				{field:'sendStatus', visible:false},
                	            {field:'status', visible:false},
                	            {field:'submitFeedbackStatus', visible:false},
                                { field: 'datuuid', visible: false }
          ]
	});
	// 查询按钮
	$('#ZG06PbcAssetSusfructSub_btn_search').click(function(){
		table.load();
	});

	// 导出按钮
	$('#ZG06PbcAssetSusfructSub_btn_pbcexport').click(function(){
        var expArr=table.getAllCheckedRows();
        if(expArr != null && expArr.length >0){
            Joyin.confirm('确认导出选中信息？',function(){
                exportExcel(1);
            })
        }else{
            if ($('#termId').val()==null||$('#termId').val()=='') {
                return Joyin.error("当不选择数据导出时，统计年月必填");
            }
            Joyin.confirm('请确认是否导出人行excel？',function(){
                exportExcel(0);
            })
        }
	})


    /**
     * 导出
     * flag:0 全部导出，选中导出
     */
    var exportExcel = function (flag) {
        if (flag == 0) {
            var param = getSearchParams();
            var isHaveData = true;
            Joyin.ajaxSync('searchPage', param, function (result) {
                if (!result || result.root.length == 0) {
                    isHaveData = false;
                    return;
                }
            });
            Joyin.exportExcel("export", param);
            if (isHaveData) {
                Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function () {
                    //页面添加滚动条
                    layui.layer.open({ type: 3 });
                    param.flag = '02';
                    Joyin.ajaxJson('doManul', param, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }
        } else {
            var param = {};
            var expArr = table.getAllCheckedRows();
            var keyList = '';
            var arr = [];
            var statuFlag = false;
            var termid = '';
            layui.each(expArr, function (index, data) {
                if (index == expArr.length - 1) {
                    keyList = keyList + data.detailuuid + '';
                } else {
                    keyList = keyList + data.detailuuid + ',';
                }
                if (data.sendStatus == '03') {
                    statuFlag = true;
                }
                arr.push(data.termId);
                termid = data.termId;
            });
            var list = Array.from(new Set(arr));
            if (list.length > 1) {
                return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
            }

            param.keyList = keyList;
            param.termId = termid;
            if (statuFlag) {
                Joyin.confirm("导出数据包含已报送数据，请确认是否导出！", function () {
                    Joyin.exportExcel("export", param);
                    // Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function () {
                    //     //页面添加滚动条
                    //     layui.layer.open({ type: 3 });
                    //     param.flag = '02';
                    //     param.infoList = table.getAllCheckedRows();
                    //     param.termid = termid;
                    //     Joyin.ajaxJson('doManul', param, function () {
                    //         table.load();
                    //         layui.layer.closeAll();
                    //     });
                    // });
                });
            } else {
                Joyin.exportExcel("export", param);
                Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function () {
                    //页面添加滚动条
                    layui.layer.open({ type: 3 });
                    param.flag = '02';
                    param.infoList = table.getAllCheckedRows();
                    param.termid = termid;
                    Joyin.ajaxJson('doManul', param, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }

        }

    };

    //数据检查
    $('#ZG06PbcAssetSusfructSub_btn_checkData').click(dateCheckMethod);
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
//             else if(status && status.indexOf('03') > -1){
//                 Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
//                 return;
//             }
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
            keyList.push(checkArr[i].termId );
            keytermId=checkArr[i].termId;

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
    // 手工处理
    $('#ZG06PbcAssetSusfructSub_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
            return Joyin.error("请至少选择一条记录");
        	/*var sendStatus = $("#sendStatus").val();
        	var submitFeedbackStatus = $("#submitFeedbackStatus").val();
        	if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
        			|| (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
        		Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
        		return;
        	}
        	var paramObj = getSearchParams();
        	paramObj.flag = '02';
        	Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
        	    paramObj.doFlag = "0";
        		layui.layer.open({type:3});
                 Joyin.ajaxJson('doManul', paramObj, function() {
                     table.load();
                     layui.layer.closeAll();
                 });
             });*/
        }else{


            // var arr = [];
            // var termid = '';
            // layui.each(rows,function(index,data){
            //     arr.push(data.termid);
            // });
            // var list=Array.from(new Set(arr));
            // if (list.length>1){
            //     return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
            // }

            var arr = [];
            var termid = '';
            layui.each(rows, function (index, data) {
                arr.push(data.termId);
                termid = data.termId;
            });
            var list = Array.from(new Set(arr));
            if (list.length > 1) {
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
            Joyin.confirm("请确认是否要手工处理选中产品的所有记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('doManul', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }
    })
    // 取消手工处理
    $('#ZG06PbcAssetSusfructSub_btn_cancelManual').click(function(){
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            return Joyin.error("请至少选择一条记录");
     	   /*var sendStatus = $("#sendStatus").val();
        	   if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
        		 Joyin.error("只能选择手工处理的数据，请重新选择！");
                 return;
        	   }
        	   var paramObj = getSearchParams();
        	   paramObj.flag = '02';
        	   Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
        	     paramObj.doFlag = "0";
        		 layui.layer.open({type:3});
                 Joyin.ajaxJson('cancelManul', paramObj, function() {
                 table.load();
                 layui.layer.closeAll();
             	});
        	   });*/
        }else{
            var arr = [];
            var termid = '';
            layui.each(manArr, function (index, data) {
                arr.push(data.termId);
                termid = data.termId;
            });
            var list = Array.from(new Set(arr));
            if (list.length > 1) {
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
    init();
	
})
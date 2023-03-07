$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			pbcProdId:$('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(","),
			prodId:$('#prodId').val()==null?null:$('#prodId').val().join(","),
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			pbcSubmitDeadlineDateEnd:$('#pbcSubmitDeadline').endDate(),
			pbcSubmitDeadlineDateStart:$('#pbcSubmitDeadline').startDate(),
			mdateStart:$('#mdate').startDate(),
			mdateEnd:$('#mdate').endDate(),
			
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
				{ title: "数据日期", field: 'statusDate',width:150,type:'datetime',sortField:'statusDate'},
				{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'sendStatus'},
				{ title: '报送日期',field:'submitTime',width:150,type:'datetime',sortField:'submitTime'},
				{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'pbcSubmitDeadline'},
				{ title: "内部产品代码", field: 'prodId',width:150,sortField:'prodId'},
				{ title: "产品代码", field: 'pbcProdId',width:150,sortField:'pbcProdId'},
				{ title: "产品实际终止日期", field: 'mdate',width:150,type:'date',sortField:'mdate'},
				{ title: "币种", field: 'ccy',width:150,sortField:'ccy'},
				{ title: "发行机构实现收入", field: 'profit',width:150,type:'money',digit:2,sortField:'profit'},
				{ title: "发行机构实现收入折人民币", field: 'profitCny',width:150,type:'money',digit:2,sortField:'profitCny'},
				{ title: "兑付客户收益", field: 'customProfit',width:150,type:'money',digit:2,sortField:'customProfit'},
				{ title: "兑付客户收益折人民币", field: 'customProfitCny',width:150,type:'money',digit:2,sortField:'customProfitCny'},
				{ title: "兑付客户收益率", field: 'cusYearYield',width:150,type: 'number',digit:5,sortField:'cusYearYield'},
				{field:'sendStatus', visible:false},
	            {field:'status', visible:false},
	            {field:'submitFeedbackStatus', visible:false},
                { field: 'datuuid', visible: false }
          ]
	});
	// 查询按钮
	$('#ZG03PbcPrdBaseinfoEndSub_btn_search').click(function(){
		table.load();
	});

	// 导出按钮
	$('#ZG03PbcPrdBaseinfoEndSub_btn_pbcexport').click(function(){
		var expArr=table.getAllCheckedRows();
		if(expArr != null && expArr.length >0){
			Joyin.confirm('确认导出选中信息？',function(){
				exportExcel("01");
			})
		}else{
			Joyin.confirm('请确认是否导出人行excel？',function(){
				exportExcel("02");
			})
		}
	})


	 /**
     * 导出
     * flag:02 全部导出，01选中导出
     */
    var exportExcel = function(flag) {
		if(flag == "02"){
	    	 var param = getSearchParams();
             param.flag = flag;
             var isHaveData = true;
             Joyin.ajaxSync('searchPage',param, function(result){
                   if (!result || result.root.length == 0){
                       isHaveData = false;
                       return;
                   }
              })
	        Joyin.exportExcel("export",param);
            if(isHaveData){
              Joyin.confirm("导出成功，是否自动标识当前未报送数据的报送状态为“手工处理”？", function() {
                param.sendStatus="01";
                //页面添加滚动条
                layui.layer.open({type:3});
                Joyin.ajaxJson('doManul', param, function() {
                    table.load();
                    layui.layer.closeAll();
                });
              });
            }
		}else{
			var param = {};
			param.flag = flag;
			var expArr=table.getAllCheckedRows();
			var keyList = '';
            var statuFlag = false;
			layui.each(expArr,function(index,data){
				if(index == expArr.length-1){
					keyList = keyList + data.registSeqno+'';
				}else{
					keyList = keyList + data.registSeqno+',';
				}
                if (data.sendStatus=='03'){
                    statuFlag=true;
                }
			})
			param.keyList = keyList;
            if (statuFlag){
                Joyin.confirm("导出数据包含已报送数据，请确认是否导出！", function() {
                    Joyin.exportExcel("export", param);
                });
            }else {
                Joyin.exportExcel("export",param);
                //判断所选导出的数据是否已经报送送成功，若报送成功，则无需"手工处理"，只对报送失败的数据进行处理
                var isNotSendData = false;
                for(var j=expArr.length-1;j>=0;j--){
                    if(expArr[j].sendStatus=="01" || (expArr[j].sendStatus == '02' && (expArr[j].submitFeedbackStatus == '发送失败' || expArr[j].submitFeedbackStatus == '中债退回'))) {
                        isNotSendData = true;//有未报送成功的数据
                    }
                }
                if (isNotSendData){
                    Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
                        param.infoList = expArr;
                        param.sendStatus="01";
                        //页面添加滚动条
                        layui.layer.open({type:3});
                        Joyin.ajaxJson('doManul', param, function() {
                            table.load();
                            layui.layer.closeAll();
                        });
                    });
                }
            }
		}

    };

    //数据检查
    $('#ZG03PbcPrdBaseinfoEndSub_btn_checkData').click(dateCheckMethod);
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
        for(var i=0;i<checkArr.length;i++){
            //modify by wangmin 修改取数顺序 start 2018-07-12
            keyList.push(checkArr[i].pbcProdId );
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
        Joyin.ajax('reportCheck',{checkKeys:keyList.join(',')}, function(result){
            window.checkResult = result;
            Joyin.checkData(dataTitle, checkUrl, {checkKeys: keyList.join(',')});
        })

    }
    // 手工处理
    $('#ZG03PbcPrdBaseinfoEndSub_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
//        	var sendStatus = $("#sendStatus").val();
//        	var submitFeedbackStatus = $("#submitFeedbackStatus").val();
//        	if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
//        			|| (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
//        		Joyin.error("只能选择报送状态为未报送的数据进行手工处理");
//        		return;
//        	}
//        	var paramObj = getSearchParams();
//        	paramObj.flag = '02';
//        	Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
//        	    paramObj.doFlag = "0";
//        		layui.layer.open({type:3});
//                 Joyin.ajaxJson('doManul', paramObj, function() {
//                     table.load();
//                     layui.layer.closeAll();
//                 });
//             });
                Joyin.error("请至少选择一条记录");
        		return;
        }else{
        	for(var i=0;i<rows.length;i++){
              if (!(rows[i].sendStatus == '01'
                  || (rows[i].sendStatus == '02' && (rows[i].submitFeedbackStatus == '02' || rows[i].submitFeedbackStatus == '04')))) {
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
    $('#ZG03PbcPrdBaseinfoEndSub_btn_cancelManual').click(function(){
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
//     	   var sendStatus = $("#sendStatus").val();
//        	   if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
//        		 Joyin.error("只能选择手工处理的数据，请重新选择！");
//                 return;
//        	   }
//        	   var paramObj = getSearchParams();
//        	   paramObj.flag = '02';
//        	   Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
//        	     paramObj.doFlag = "0";
//        		 layui.layer.open({type:3});
//                 Joyin.ajaxJson('cancelManul', paramObj, function() {
//                 table.load();
//                 layui.layer.closeAll();
//             	});
//        	   });
                Joyin.error("请至少选择一条记录");
        		return;
        }else{
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
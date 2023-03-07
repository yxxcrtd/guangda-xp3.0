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
			raisingDate : $('#enddate').val()
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
        showDetail: true,//双击详情对比画面
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[
			  { field: 'registSeqno',visible:false},
              { title: "数据日期", field: 'statusDate',width:150,type: 'datetime',sortField:'statusDate'},
              { title: "报送状态", field: 'sendStatusStr',width:150,sortField:'sendStatus'},
              { title: '报送日期',field:'submitTime',width:150,type: 'datetime',sortField:'submitTime'},
              { title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'pbcSubmitDeadline'},
              { title: "统计年月", field: 'raisingDate',width:150,sortField:'raisingDate'},
              { title: "内部产品代码", field: 'prodId',width:150,sortField:'prodId'},
              { title: "产品代码", field: 'pbcProdId',width:150,sortField:'pbcProdId'},
              { title: "地区", field: 'buyPlace',width:150,sortField:'buyPlace'},
              { title: "客户类型", field: 'customerType1',width:150,sortField:'customerType1'},
              { title: "币种", field: 'ccy',width:150,sortField:'ccy'},
              { title: "当期申购金额", field: 'raisingAmtsum',width:150, type: 'money',sortField:'raisingAmtsum'},
              { title: "当期申购金额折人民币", field: 'raisingAmtsumCny',width:150, type: 'money',sortField:'raisingAmtsumCny'},
              { title: "当期申购份额", field: 'raisingQtyStr',width:150, type: 'money',sortField:'raisingQty'},
              { title: "当期兑付/赎回金额", field: 'redamt',width:150, type: 'money',sortField:'redamt'},
              { title: "当期兑付/赎回金额折人民币", field: 'redamtCny',width:150, type: 'money',sortField:'redamtCny'},
              { title: "当期兑付/赎回份额", field: 'redqtyStr',type: 'money',width:150,sortField:'redqty',digit:2},
              { title: "期末产品金额", field: 'endamtsum',width:150, type: 'money',sortField:'endamtsum'},
              { title: "期末产品金额折人民币", field: 'endamtsumCny',width:150, type: 'money',sortField:'endamtsumCny'},
              { title: "期末产品份额", field: 'endqtyStr',width:150,sortField:'endqty',type: 'money',digit:2},
              { title: "净值型产品期末净值", field: 'prodValue',width:150, type: 'number',digit:8,sortField:'prodValue'},
              { title: "净值型产品期末净值折人民币", field: 'prodValueCny',width:200, type: 'number',digit:8,sortField:'prodValueCny'},
              { title: "净值型产品期末累计净值", field: 'unitValueTotal',width:200, type: 'number',digit:8,sortField:'unitValueTotal'},
              { title: "净值型产品期末累计净值折人民币", field: 'unitValueTotalCny',width:200, type: 'number',digit:8,sortField:'unitValueTotalCny'},
              { title: "产品期末业绩表现", field: 'prodEndYield',width:150, type: 'number',digit:5,sortField:'prodEndYield'},
              {field:'sendStatus', visible:false},
              {field:'status', visible:false},
              {field:'submitFeedbackStatus', visible:false},
              { field: 'datuuid', visible: false }
              
          ]
	})
	// 查询按钮
	$('#ZG04PbcPrdCashRaiseSub_btn_search').click(function(){
		table.load();
	})

    //数据检查
    $('#ZG04PbcPrdCashRaiseSub_btn_checkData').click(dateCheckMethod);
    function dateCheckMethod(){
        var checkArr=table.getAllCheckedRows();
        if(checkArr.length === 0) {
            var submitStatus = $('#sendStatus').val();
            var status = $('#status').val();
//            if(submitStatus!='01'){
            if ($('#enddate').val()==null||$('#enddate').val()=='') {
                return Joyin.error("当不选择数据进行检查时，统计年月必填");
            }
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
            keyList.push(checkArr[i].raisingDate );
            keytermId=checkArr[i].raisingDate;
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
	
	// 导出人行Excel
	$('#ZG04PbcPrdCashRaiseSub_btn_pbcexport').click(function(){
		var expArr=table.getAllCheckedRows();
		if(expArr != null && expArr.length >0){
		    var arr = [];
		    var termId='';
		    layui.each(expArr,function(index,data){
				arr.push(data.raisingDate);
				termId=data.raisingDate;
			});
			var list=Array.from(new Set(arr));
			if (list.length>1){
				return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
			}
			Joyin.confirm('确认导出所在选中信息的统计年月中的所有数据？',function(){
				exportExcel("01");
			})
		}else{
			if ($('#enddate').val()==null||$('#enddate').val()=='') {
				return Joyin.error("当不选择数据导出时，统计年月必填");
			}
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
    	        Joyin.exportExcel("export",param);
                if(flag == "02"){
                  param.sendStatus = "01";
                }
                 Joyin.ajaxSync('searchPage',param, function(result){
                       if (!result || result.root.length == 0){
                           isHaveData = false;
                           return;
                       }
                  })
                if(isHaveData){
                  Joyin.confirm("导出成功，是否自动标识当前未报送数据的报送状态为“手工处理”？", function() {
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
    			layui.each(expArr,function(index,data){
    				if(index == expArr.length-1){
    					raisingDate = data.raisingDate;
    				}else{
    					raisingDate = data.raisingDate;
    				}
    			})
    			param.raisingDate = raisingDate;
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

        };
    // 手工处理
    $('#ZG04PbcPrdCashRaiseSub_btn_manual').click(function(){
               var rows = table.getAllCheckedRows();
               if(rows.length === 0) {
               	var sendStatus = $("#sendStatus").val();
               	var submitFeedbackStatus = $("#submitFeedbackStatus").val();
               	if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
               			|| (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
               		Joyin.error("请至少选择一条记录！");
               		return;
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
                     if (!(rows[i].sendStatus == '01'
                         || (rows[i].sendStatus == '02' && (rows[i].submitFeedbackStatus == '02' || rows[i].submitFeedbackStatus == '04')))) {
                         Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                         return;
                     }
                 }
               	var paramObj= {};
               	paramObj.flag = '01';
    			layui.each(rows,function(index,data){
    				if(index == rows.length-1){
    					raisingDate = data.raisingDate;
    				}else{
    					raisingDate = data.raisingDate;
    				}
    			})
    			paramObj.raisingDate = raisingDate;
               	var raisingDate = '';
                layui.each(rows,function(index,data){
				    if(index == rows.length-1){
					    raisingDate = raisingDate + data.raisingDate+'';
				    }else{
				     	raisingDate = raisingDate + data.raisingDate+',';
				    }
			    });
			    paramObj.raisingDate = raisingDate;
                 Joyin.confirm("请确认是否要手工处理选中数据当月的所有记录？", function() {
               	  layui.layer.open({type:3});
                     Joyin.ajaxJson('doManul', paramObj, function() {
                         table.load();
                         layui.layer.closeAll();
                     });
                 });
               }
    })
    // 取消手工处理
    $('#ZG04PbcPrdCashRaiseSub_btn_cancelManual').click(function(){
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
            var paramObj= {};
        	paramObj.flag = '01';
        	paramObj.infoList = manArr;
               	var raisingDate = '';
                layui.each(manArr,function(index,data){
				    if(index == manArr.length-1){
					    raisingDate = raisingDate + data.raisingDate+'';
				    }else{
				     	raisingDate = raisingDate + data.raisingDate+',';
				    }
			    });
			    paramObj.raisingDate = raisingDate;
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
    init();
})
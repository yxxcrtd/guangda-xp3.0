$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			pbcProdId:$('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(","),
			assetType:$('#assetType').val()==null?null:$('#assetType').val().join(","),
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			prrtyProdId:$('#prrtyProdId').val(),
			pbcSubmitDeadlineDateEnd:$('#pbcSubmitDeadline').endDate(),
			pbcSubmitDeadlineDateStart:$('#pbcSubmitDeadline').startDate(),
			termId:$('#termId').val()
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
		showDetail:true,
        cols:[
				{ field: 'detailuuid',visible:false},
				{ title: "数据日期", field: 'statusDate',width:150,type:'datetime',sortField:'STATUS_DATE'},
				{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'SEND_STATUS'},
				{ title: '报送日期',field:'submitTime',width:150,type:'datetime',sortField:'SUBMIT_TIME'},
				{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type: 'date',sortField:'PBC_SUBMIT_DEADLINE'},
				{ title: "产品代码", field: 'pbcProdId',width:150,sortField:'PBC_PROD_ID'},
				{ title: "统计年月", field: 'termid',width:130,sortField:'TERMID'},
				{ title: "资产负债项目", field: 'assetTypeStr',width:150,sortField:'ASSET_TYPE'},
				{ title: "交易对手产品种类", field: 'prrtyProdTypeStr',width:150,sortField:'PRRTY_PROD_TYPE'},
				{ title: "交易对手机构代码", field: 'prrtyOrgCode',width:150,sortField:'PRRTY_ORG_CODE'},
				{ title: "交易对手产品代码", field: 'prrtyProdId',width:150,sortField:'PRRTY_PROD_ID'},
				{ title: "币种", field: 'ccyStr',width:150,sortField:'ccy'},
				{ title: "期末金额", field: 'amt',width:150,type:'money',digit:2,sortField:'amt'},
				{ title: "期末金额折人民币", field: 'amtCny',type:'money',digit:2,width:150,sortField:'AMT_CNY'},
				{ field: 'sendStatus',width:150,sortField:'sendStatus',visible:false},
				{ field: 'status',width:150,sortField:'status',visible:false},
				{ title: "数据状态",field: 'statusStr',width:150,sortField:'status',visible:false},
				{ field: 'assetType',width:150,sortField:'assetType',visible:false},
				{ field: 'prrtyProdType',width:150,sortField:'prrtyProdType',visible:false},
				{ field: 'ccy',width:150,sortField:'ccy',visible:false},
				{ field: 'datuuid', visible: false }

          ]
	})
	// 查询按钮
	$('#ZG08SpeCounterQuity_btn_search').click(function(){
		table.load();
	})
	
	// 导出按钮
	$('#ZG08SpeCounterQuity_btn_export').click(function(){
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
					var paramExport = {};
					paramExport.termId=$('#termId').val();
					layui.layer.open({type:3});
					paramExport.flag = '02';
					Joyin.ajaxJson('doManul', paramExport, function() {
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
					keyList = keyList + data.detailuuid+'';
				}else{
					keyList = keyList + data.detailuuid+',';
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
					// Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
					// 	//页面添加滚动条
					// 	layui.layer.open({type:3});
					// 	param.flag = '02';
					// 	param.infoList = table.getAllCheckedRows();
					// 	param.termId=termId;
					// 	Joyin.ajaxJson('doManul', param, function() {
					// 		table.load();
					// 		layui.layer.closeAll();
					// 	});
					// });
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
    // 手工处理
    $('#ZG08SpeCounterQuity_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
			return Joyin.error("请至少选择一条记录");
			/*var sendStatus = $("#sendStatus").val();
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
             });*/
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
          Joyin.confirm("请确认是否要手工处理选中产品的所有记录？", function() {
        	  layui.layer.open({type:3});
              Joyin.ajaxJson('doManul', paramObj, function() {
                  table.load();
                  layui.layer.closeAll();
              });
          });
        }
        
    })


	$('#ZG08SpeCounterQuity_btn_checkData').click(dateCheckMethod);
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
			Joyin.checkData(dataTitle, checkUrl, {checkKeys:keytermId});
		})
	}
    // 取消手工处理
    $('#ZG08SpeCounterQuity_btn_cancelManual').click(function(){
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
        		layui.layer.open({type:3});
             Joyin.ajaxJson('cancelManul', paramObj, function() {
                 table.load();
                 layui.layer.closeAll();
             	});
        	   });*/
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
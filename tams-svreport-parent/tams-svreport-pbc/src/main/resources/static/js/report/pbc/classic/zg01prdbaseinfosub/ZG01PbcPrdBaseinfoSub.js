$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			pbcProdId:$('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(","),
			prodId:$('#prodId').val()==null?null:$('#prodId').val().join(","),
			infoType:$('#infoType').val()==null?null:$('#infoType').val().join(","),
			ivstorientation:$('#ivstorientation').val()==null?null:$('#ivstorientation').val().join(","),
			operateMode:$('#operateMode').val()==null?null:$('#operateMode').val().join(','),
			sendStatus:$('#sendStatus').val()==null?null:$('#sendStatus').val().join(','),
			status:$('#status').val()==null?null:$('#status').val().join(','),
			statusDateStart:$('#statusDate').startDate(),
			statusDateEnd:$('#statusDate').endDate(),
			submitTimeStart:$('#submitTime').startDate(),
			submitTimeEnd:$('#submitTime').endDate(),
			pbcSubmitDeadlineDateEnd:$('#pbcSubmitDeadline').endDate(),
			pbcSubmitDeadlineDateStart:$('#pbcSubmitDeadline').startDate(),
			vdateStart:$('#vdate').startDate(),
			vdateEnd:$('#vdate').endDate(),
			
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
				{ field: 'status',width:150,sortField:'status',visible:false},
				{ title: "数据状态",field: 'statusStr',width:150,sortField:'status'},
				{ title: "数据日期", field: 'statusDate',width:150,type:'datetime',sortField:'statusDate'},
				{ title: "报送状态", field: 'sendStatusStr',width:150,sortField:'sendStatus'},
				{ title: '报送日期',field:'submitTime',width:150,type:'datetime',sortField:'submitTime'},
				{ title: "报送截止日", field: 'pbcSubmitDeadline',width:150, type:'date',sortField:'pbcSubmitDeadline'},
				{ title: "内部产品代码", field: 'prodId',width:150,sortField:'prodId'},
				{ title: "信息类型", field: 'infoType',width:150,sortField:'infoType'},
				{ title: "产品代码", field: 'pbcProdId',width:150,sortField:'pbcProdId'},
				{ title: "产品名称", field: 'prodName',width:150,sortField:'prodName'},
				{ title: "发行机构代码", field: 'issuePartyId',width:150,sortField:'issuePartyId'},
				{ title: "发行机构名称", field: 'issuePartyName',width:150,sortField:'issuePartyName'},
				{ title: "产品品种", field: 'pbcProdType',width:150,sortField:'pbcProdType'},
				{ title: "产品类型", field: 'ivstorientation',width:150,sortField:'ivstorientation'},
				{ title: "产品品牌", field: 'prodBrand',width:150,sortField:'prodBrand'},
				{ title: "产品期次", field: 'prodStage',width:150,sortField:'prodStage'},
				{ title: "发行机构内部产品代码", field: 'prodId',width:150,sortField:'prodId'},
				{ title: "募集币种", field: 'raiseCcy',width:150,sortField:'raiseCcy'},
				{ title: "兑付本金币种", field: 'cashPrinCcy',width:150,sortField:'cashPrinCcy'},
				{ title: "兑付收益币种", field: 'cashProfCcy',width:150,sortField:'cashProfCcy'},
				{ title: "募集方式", field: 'raiseType',width:150,sortField:'raiseType'},
				{ title: "管理方式", field: 'manageType',width:150,sortField:'manageType'},
				{ title: "运行方式", field: 'operateMode',width:150,sortField:'operateMode'},
				{ title: "募集起始日期", field: 'raiseVdate',width:150,type:'date',sortField:'raiseVdate'},
				{ title: "募集结束日期", field: 'raiseEnddate',width:150,type:'date',sortField:'raiseEnddate'},
				{ title: "发行机构提前终止权标识", field: 'termRightFlag',width:150,sortField:'termRightFlag'},
				{ title: "客户赎回权标识", field: 'cusRedeemFlag',width:150,sortField:'cusRedeemFlag'},
				{ title: "产品增信标识", field: 'creditFlag',width:150,sortField:'creditFlag'},
				{ title: "增信机构类型", field: 'creditType',width:150,sortField:'creditType'},
				{ title: "增信形式", field: 'creditMode',width:150,sortField:'creditMode'},
				// { title: "境内托管机构名称", field: 'inDepName',width:150,sortField:'inDepName'},
				{ title: "境内托管机构代码", field: 'inDepCode',width:150,sortField:'inDepCode'},
				{ title: "境外托管机构国别代码", field: 'outDepCtry',width:150,sortField:'outDepCtry'},
				{ title: "境外托管机构名称", field: 'outDepName',width:150,sortField:'outDepName'},
				{ title: "产品起始日期", field: 'vdate',width:150,type:'date',sortField:'vdate'},
				{ title: "产品变更日期", field: 'cdate',width:150,type:'date',sortField:'cdate'},
				{ title: "产品预计终止日期", field: 'mdate',width:150,type:'date',sortField:'mdate'},
				{ title: "受托职责", field: 'fiduciaryDuty',width:150,sortField:'fiduciaryDuty'},
				{ title: "分级产品标识", field: 'isConstracte',width:150,sortField:'isConstracte'},
				{ title: "收益权转让产品标识", field: 'transferType',width:150,sortField:'transferType'},
				{ title: "货基或现金管理类产品标识", field: 'cashManaCate',width:150,sortField:'cashManaCate'},
                { title: "跨境理财通", field: 'crossBorderFlag',width:150,sortField:'crossBorderFlag'},
                { title: "以财产权信托名义开展的资金信托", field: 'isPropertyFlag',width:150,sortField:'isPropertyFlag'},
                { title: "基本信息公开标识", field: 'baseInfoSign',width:150,sortField:'baseInfoSign'},
                { title: "变更原因", field: 'changeReason',width:150,sortField:'changeReason'},
                { title: "备用字段1", field: 'backup1',width:150,sortField:'backup1'},
                { title: "备用字段2", field: 'backup1',width:150,sortField:'backup2'},
                { title: "备用字段3", field: 'backup1',width:150,sortField:'backup3'},
                { title: "备用字段4", field: 'backup1',width:150,sortField:'backup4'},
                { title: "备用字段5", field: 'backup1',width:150,sortField:'backup5'},
			    {field:'sendStatus', visible:false},
              	{field:'status', visible:false},
              	{field:'submitFeedbackStatus', visible:false},
				{ field: 'datuuid', visible: false }
        ]
	})
	// 查询按钮
	$('#ZG01PbcPrdBaseinfoSub_btn_search').click(function(){
		table.load();
	})
	
	// 导出按钮
	$('#PbcPrdBaseinfoSub_btn_chbexport').click(function(){
		var expArr=table.getAllCheckedRows();
		if(expArr != null && expArr.length >0){
			Joyin.confirm('确认导出选中信息？',function(){
				exportExcel(1);
			})
		}else{
			Joyin.confirm('请确认是否导出人行excel？',function(){
				exportExcel(0);
			})
		}
	})
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
			 var status = false;
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
				 if(data.status=='02'){
					 status=true;
				 }
				 arr.push(data.termid);
				 termId=data.termid;
			 });
			 var list=Array.from(new Set(arr));
			 if (list.length>1){
				 return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
			 }

			 param.keyList = keyList;
			 if (statuFlag || status){
				 Joyin.confirm("导出数据包含修改或已报送数据，请确认是否导出！", function() {
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
    //数据检查
    $('#ZG01PbcPrdBaseinfoSub_btn_submit').click(dateCheckMethod);
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
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
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
        	keyList.push(checkArr[i].registSeqno );
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
    $('#ZG01PbcPrdBaseinfoSub_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
           return Joyin.error("请至少选择一条记录");
        	//add by liubin
        	var sendStatus = $("#sendStatus").val();
        	if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
        			|| (sendStatus && sendStatus.indexOf('02')>-1 ))){
        		Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
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
                  || (rows[i].sendStatus == '02'))) {
                  Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
                  return;
              }

				if(rows[i].pbcProdId==null||rows[i].pbcProdId==''){
					Joyin.error("请先在【人行产品资产池代码管理】模块维护产品代码，再进行手工处理操作。");
					return;
				}
          	}
        	var paramObj= {};
        	paramObj.flag = '01';
        	paramObj.infoList = rows;
          Joyin.confirm("请确认是否要手工处理选中记录？", function() {
        	  layui.layer.open({type:3});
              Joyin.ajaxJson('doManul', paramObj, function() {
                  table.load();
                  layui.layer.closeAll();
              });
          });
        }
        
    })
    // 取消手工处理
    $('#ZG01PbcPrdBaseinfoSub_btn_cancelManual').click(function(){
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
             Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
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
    
    
    // 导出人行Excel按钮
    $('#ZG01PbcPrdBaseinfoSub_btn_chbexportxls').click(function(){
    	var expArr=table.getAllCheckedRows();
    	if(expArr != null && expArr.length >0){
    		Joyin.confirm('确认导出选中信息？',function(){
				exportExcel(1);
    		})
    	}else{
    		Joyin.confirm('请确认是否导出人行excel？',function(){
				exportExcel(0);
    		})
    	}
    })

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
				Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
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
			var status = false;
			var termId='';
			layui.each(expArr,function(index,data){
				if(index == expArr.length-1){
					keyList = keyList + data.registSeqno+'';
				}else{
					keyList = keyList + data.registSeqno+',';
				}
				if (data.sendStatus=='03'){
					statuFlag=true;
				}
				if(data.status=='02'){
					status=true;
				}
				arr.push(data.termid);
				termId=data.termid;
			});
			// var list=Array.from(new Set(arr));
			// if (list.length>1){
			// 	return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
			// }

			param.keyList = keyList;
			if (statuFlag || status){
				Joyin.confirm("导出数据包含修改或已报送数据，请确认是否导出！", function() {
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
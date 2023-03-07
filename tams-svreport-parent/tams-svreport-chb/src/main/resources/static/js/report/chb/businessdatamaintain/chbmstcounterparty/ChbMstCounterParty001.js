/**
 * 交易对手
 */
$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var counterType = $('#counterType').val();
        var counterType2 = $('#counterType2').val();
        var statusStr = $('#status').val();
        return {
            counterName: $('#counterName').val(),
            counterType:counterType ? counterType.join(","):'',
            status:statusStr,
            counterType2:counterType2 ? counterType2.join(","):''
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        dblclick: function (table, data) {
            Joyin.dialog("查看交易对手",
                basePath + "report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002/init",
                "99%", "99%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    uuid: data.uuid,
                    info:data.info
                });
        },
        cols:[
            { field: 'uuid',width:0,sortField:'',visible:false},
            {field:'statusValidStr', sortable:false, width:100, title: 'ChbMstCounterParty001.status'}
            ,{field:'info',visible: false,width:100,  title: 'info'}
           /* ,{field:'counterTypeStr', sortable:false,width:200,  title: 'ChbMstCounterParty001.counterType'}*/
            ,{field:'counterId',sortable:false,true:120,title: '交易对手识别标识'}
            ,{field:'counterName', sortable:false,true:200, title: '交易对手报送字段'}
            ,{field:'counterType2Str',sortable:false,true:200,title: 'ChbMstCounterParty001.counterType2'}
            ,{field:'relatedTradeStatusStr',sortable:false,true:200,title: 'ChbMstCounterParty001.relatedTradeStatus'}
            ,{field:'remark', sortable:false, width:240,title: 'ChbMstCounterParty001.remark'}
            ,{field:'errMsg', sortable:false, width:240,title: '错误消息'}
            ,{field:'updateTimeStr', sortable:false, width:180,title: 'ChbMstCounterParty001.updateTime'}
            ,{field:'updateUser', sortable:true, width:120,title: 'ChbMstCounterParty001.updateUser'}
            ,{field:'operation', width:100, title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType='edit'
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.confirm("请确认是否修改?", function(){
            Joyin.dialog(JoyinUtil.getLabel("ChbMstCounterParty002"),
                basePath + "report//chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002/init",
                "98%", "95%",data,
                function(){table.reload();});
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001/delete', object, "ChbMstCounterParty002_btn_delete", "ChbMstCounterParty002",function(){
                table.reload();
            });
        });
    });
    
    var exportExcel = function() {   
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    
    // var reset = function(conditionArea,table){
    //     $(conditionArea).find("input").val("");
    //     $(conditionArea).find("select").val("").trigger("change");// select清空
    // }
    /**
     * 画面加载完成
     */
    $(function() {
        //新建
        $('#ChbMstCounterParty001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.create")+JoyinUtil.getLabel("ChbMstCounterParty002"),
                basePath + "report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        // 绑定按钮事件
        $('#ChbMstCounterParty001_btn_search').click(function() {
            table.load();
            $('#ChbMstCounterParty001_btn_delete').show();
        });
        $('#ChbMstCounterParty001_btn_export').click(function() {
            exportExcel();
        });
        $('#ChbMstCounterParty001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("没有选中数据"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001/delete', object, "ChbMstCounterParty002_btn_delete", "ChbMstCounterParty002",function(){
                    table.reload();
                });
            });
        });
      
        /** 事件绑定: 置空按钮 */
        // $("#ChbMstCounterParty001_btn_reset").click(function(event){
        //     reset( '#selectArea',table);
        // });


        // 导入按钮
        $('#ChbMstCounterParty001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                var options = {};
                options.pageId = "ChbMstCounterParty002";
                options.exts = 'xls|xlsx';
                Joyin.uploadFile(options,function(fileName) {
                    Joyin.ajax(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001/checkConfirmMsgForImportData', {fileName: fileName,tableName:'CHB_MST_COUNTER_PARTY'}, function(result) {
                        if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                            var joyinMessages = "";
                            $.each(result.joyinMessages, function(i, joyinMessage){
                                joyinMessages += joyinMessage.message + "<br />"
                            });
                            Joyin.confirm(joyinMessages, function(){
                                Joyin.ajax(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001/importFileSave', {fileName: fileName,tableName:'CHB_MST_COUNTER_PARTY'},function () {
                                    table.reload();
                                });
                            });
                        }else{
                            Joyin.ajax(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001/importFileSave', {fileName: fileName,tableName:'CHB_MST_COUNTER_PARTY'},function () {
                                table.reload();
                            });
                        }
                    });
                },function() {
                    table.reload();
                });
            });
        });
    });
}());
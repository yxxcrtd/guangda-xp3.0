$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            branchNo: $('#branchNo').val(),
            branchName: $('#branchName').val(),
            branchFsa: $('#branchFsa').val(),
            networkNo: $('#networkNo').val(),
            status: $('#status').val()
        };
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002/init",
                    "40%", "70%", {pageModel:PAGE_MODEL_REFERENCE,
                    branchNo:data.branchNo,branchName:branchName,branchFsa:data.branchFsa,
                    networkNo:data.networkNo,status:data.statusValid});
        },            
        cols:[{field:'statusValidStr',width:120, title: '数据状态',sortable:false}
            ,{field:'branchNo',width:150,  title: '网点编号'}
            ,{field:'branchName',width:370,  title: '网点名称'}
            ,{field:'branchFsaStr',width:250,  title: '网点所属监管码值'}
            ,{field:'networkNoStr',width:200,  title: '网点地区码值'}
            ,{field:'updateTimeStr',width:200, title: '更新时间',sortable:false}
            ,{field:'updateUser',width:100, title: '更新人',sortable:false}
            ,{field:'branchFsa',visible: false}
            ,{field:'networkNo',visible: false}
            ,{field:'statusValid',visible: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog("网点信息映射表管理修改",
            basePath + "chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002/init",
            "40%", "70%", {pageModel:PAGE_MODEL_EDIT,
                branchNo:data.branchNo,branchName:branchName,branchFsa:data.branchFsa,
                networkNo:data.networkNo,status:data.statusValid},function() {
                table.reload();
            });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"DELETE"};
        Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/check', object, function(result) {
        	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/delete', object, function() {
                    table.reload();
                });
            });
        });
    });

    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ChbBranchNetwork001_btn_search').click(function() {
            table.load();
        });
        $('#ChbBranchNetwork001_btn_export').click(function() {
            exportExcel();
        });
        $('#ChbBranchNetwork001_btn_add').click(function() {
        
            Joyin.dialog("网点信息映射表管理新建",
                        basePath + "chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002/init",
                        "40%", "70%", {pageModel:PAGE_MODEL_NEW});
        }); 
        $('#ChbBranchNetwork001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for (var i=0;i<selArr.length;i++) {
              if (selArr[i].statusValid=='D'){
                  Joyin.error("存在已删除状态的数据");
                  return;
              }
            }
            var object = {selectedItem: selArr, operateType:"DELETE"};
            Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/delete', object, function() {
                        table.reload();
                    });
             });
           });
        });
        
        // 导入按钮点击事件
        $('#ChbBranchNetwork001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                var options = {};
                options.pageId = "ChbBranchNetwork001";
                options.exts = 'xls|xlsx';
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/checkConfirmMsgForImportData', {fileName: fileName}, function(result) {
                        if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                            var joyinMessages = "";
                            $.each(result.joyinMessages, function(i, joyinMessage){
                                joyinMessages += joyinMessage.message + "<br />"
                            });
                            Joyin.confirm(joyinMessages, function(){
                                Joyin.ajax(basePath + '/chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/importFile', {fileName: fileName},function () {
                                    table.reload();
                                });
                            });
                        }else{
                            Joyin.ajax(basePath + '/chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork001/importFile', {fileName: fileName},function () {
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
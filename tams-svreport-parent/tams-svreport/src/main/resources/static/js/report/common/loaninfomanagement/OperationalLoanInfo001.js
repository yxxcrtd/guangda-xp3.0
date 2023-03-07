/**
 *贷款及资产收益权明细信息维护
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            status:$('#status').val(),
            loanVdateStart: $('#loanVdate').startDate(),
            loanVdateEnd: $('#loanVdate').endDate(),
            assetInvest:$('#assetInvest').val(),
            assetCode:$('#assetCode').val()
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
        dblclick: function (table, data) {
            Joyin.dialog("查看贷款及资产收益权明细信息维护",
                basePath + "/svreport/loaninfomanagement/OperationalLoanInfo002/init",
                "99%", "99%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    assetUuid: data.assetUuid,
                });
        },
        cols:[
            {field:'assetUuid',visible:false}
            ,{field:'statusStr', sortable:false, width:250, title: '数据状态'}
            ,{field:'assetCode', sortable:false, width:350, title: '资产代码'}
            ,{field:'assetName',sortable:false,width:350,  title: '资产名称'}
            ,{field:'assetsInvestPageStr', sortable:false,width:350,  title: '基础资产投向分类'}
            ,{field:'assetType',visible:false}
            ,{field:'assetTypeEStr',sortable:false,width:350,title: '人行资产三级分类'}
            ,{field:'operation', width:100, title: 'SysCommon.operation',sortable:false, type:'action',
                render: function (data, type, row, meta) {
                    if (row.status != 'D'){
                        var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                        str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                        return str;
                    }
                }}
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType='edit'
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.confirm("请确认是否修改?", function(){
            Joyin.dialog("编辑贷款及资产收益权明细信息维护",
                basePath + "/svreport/loaninfomanagement/OperationalLoanInfo002/init",
                "98%", "95%",data,
                function(){});
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        console.log(data)
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/delete', object, "OperationalLoanInfo001_btn_delete", "OperationalLoanInfo001",function(){
                table.reload();
            });
        });
    });

    var exportExcel = function() {
        var paramObj = getSearchParams();
        // RHPTSJ-136 start
        if(table != null && table.getAllCheckedRows().length > 0){
            // 导出的时候有选中行
            var selectedData = JSON.stringify(table.getAllCheckedRows())
            Joyin.exportExcel('export', {
                selectedData : selectedData
            });
        }else {
            // 导出全部数据
            Joyin.exportExcel('export', paramObj);
        }
        // RHPTSJ-136 end

    };

    //下载导入模板
    var exportExcel01 = function() {
        var paramObj = getSearchParams();
        // 导出全部数据
        Joyin.exportExcel('export01', paramObj);

    };
    /**
     * 画面加载完成
     */
    $(function() {
        //新建
        $('#OperationalLoanInfo001_btn_add').click(function() {
            Joyin.dialog("新建贷款及资产收益权明细信息维护",
                basePath + "/svreport/loaninfomanagement/OperationalLoanInfo002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        // 绑定查询事件
        $('#loanplan_btn_search').click(function() {
            table.load();
        });
        $('#loanplan_btn_export').click(function() {
            exportExcel();
        });
        //绑定导出模板事件
        $('#OperationalLoanInfo001_btn_import01').click(function() {
            exportExcel01();
        });
        //删除按钮点击事件
        $('#OperationalLoanInfo001_btn_delete').click(function(){

            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i = 0;i<selArr.length;i++){
                if(selArr[i].status && selArr[i].status.startsWith("D") ){
                    Joyin.error("包含删除状态的数据，不可删除！");
                    return;
                }
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/delete', object, "OperationalLoanInfo001_btn_delete", "OperationalLoanInfo001", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });

        // 导入按钮
        $('#OperationalLoanInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                var options = {};
                options.pageId = "OperationalLoanInfo001";
                options.exts = 'xls|xlsx';
                Joyin.uploadFile(options,function(fileName) {
                    Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/checkConfirmMsgForImportData', {fileName: fileName,tableName:'CHB_LOAN_DETAIL_INFO'}, function(result) {
                        if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                            var joyinMessages = "";
                            $.each(result.joyinMessages, function(i, joyinMessage){
                                joyinMessages += joyinMessage.message + "<br />"
                            });
                            Joyin.confirm(joyinMessages, function(){
                                Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/importFileSave', {fileName: fileName,tableName:'CHB_LOAN_DETAIL_INFO'},function () {
                                    table.reload();
                                });
                            });
                        }else{
                            Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/importFileSave', {fileName: fileName,tableName:'CHB_LOAN_DETAIL_INFO'},function () {
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
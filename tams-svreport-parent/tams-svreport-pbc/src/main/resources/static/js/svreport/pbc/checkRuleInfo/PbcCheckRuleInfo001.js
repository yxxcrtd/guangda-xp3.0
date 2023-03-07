(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            // 规则大类
            ruleClassify: $("#ruleClassify").val(),
            // 报表/数据名称
            tableDataName: $("#tableDataName").val() == null ? null : $("#tableDataName").val().join(","),
            // 检查字段名称
            checkedFieldName: $("#checkedFieldName").val(),
            // 规则类型
            ruleType: $("#ruleType").val() == null ? null : $("#ruleType").val().join(","),
            // 规则标识
            ruleIdentify: $('#ruleIdentify').val(),
            // 数据状态
            isstart: $("#isstart").val(),
            // 规则编码
            ruleId: $("#ruleId").val(),
            // 人行要素编码
            pbcAssetCode: $("#pbcAssetCode").val(),
            // 规则内容
            ruleContent: $("#ruleContent").val(),
            // 规则提示语
            rulePrompt: $("#rulePrompt").val()
        };
    };
    // 表格的列
    var tabCols = [
        {field: 'ruleClassifyStr', title: '规则大类',sortField:'ruleClassify'}
        , {field: 'tableDataNameStr', title: '报表名称',sortField: 'tableDataName'}
        , {field: 'dataId', title: '数据唯一标识', sortable: false}
        , {field: 'relatedTableStr', title: '关联报表',sortField: 'relatedTable'}
        , {field: 'ruleId', title: '规则编码'}
        , {field: 'pbcAssetCode', title: '人行要素编码'}
        , {field: 'checkedFieldName', title: '检查字段名称'}
        , {field: 'ruleTypeStr', title: '规则类型',sortField:'ruleType'}
        , {field: 'ruleIdentifyStr', title: '规则标识',sortField: 'ruleIdentify'}
        , {field: 'ruleContent', title: '规则详细内容'}
        , {field: 'rulePrompt', title: '错误信息提示语'}
        , {field: 'isstartStr', title: '是否启用',sortField: 'isstart'}
        , {field: 'updateDataTime', title: '更新时间'}
        , {
            fixed: 'right',
            field: 'action',
            title: 'SysCommon.operation',
            type: 'action',
            width: 50,
            render: function (data, type, row, meta) {
                var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                return str;
            }
        }
    ];

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        dblclick: function (table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("数据详情页面"),
                JoyinUtil.getRealPath("svreport/checkRule/PbcCheckRuleInfo002/init"),
                "98%", "95%", data, function () {
                    table.reload();
                }
            );
        },
        cols: tabCols,
    });


    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.pageId = 'PbcCheckRuleInfo002';
        Joyin.confirm("请确认是否修改?", function () {
            Joyin.dialog(JoyinUtil.getLabel("规则配置修改界面"),
                basePath + "svreport/checkRule/PbcCheckRuleInfo002/init",
                "98%", "95%", data,
                function () {
                    table.reload();
                }
            );
        });
    });


    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "DELETE", ruleId: data.ruleId};
        object.pageId = 'PbcCheckRuleInfo002';
        Joyin.confirm("请确认是否删除?", function () {
            Joyin.ajaxJson(basePath + 'svreport/checkRule/PbcCheckRuleInfo001/delete', object,
                function () {
                    table.reload();
                }
            );
        });
    });

    /**
     * 导出
     */
    var exportExcel = function () {
        if (table != null && table != '') {
            // 是否有选中行
            var selectArr = table.getAllCheckedRows();
            var selectData = JSON.stringify(selectArr);
        }
        if (!selectArr || selectArr.length == 0) {
            // 所有表格数据
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                selectData: selectData
            });
        }
    };

    function effective(flag){
        var select = table.getAllCheckedRows();
        var selectData = [];
        for(var i in select){
            selectData.push(select[i].ruleId)
        }
        var message = "请选择需要停用的规则";
        var confirmMessage = "确认停用已选择的规则吗？"
        if(flag=='Y'){
            message = "请选择需要启用的规则"
            confirmMessage = "确认启用已选择的规则吗？"
        }
        if(selectData.length==0){
            Joyin.alert(message, 7,null, null);
        }else{
            Joyin.confirm(confirmMessage, function(){
                Joyin.ajaxJson('effitive', {flag:flag,selectData:selectData}, function(result) {
                    table.reload();
                });
            });
        }
    };


    // 002 页面
    var openNew = function () {
        Joyin.dialog(JoyinUtil.getLabel("规则配置新增界面"),
            JoyinUtil.getRealPath("svreport/checkRule/PbcCheckRuleInfo002/init"),
            "98%", "95%", {pageModel: PAGE_MODEL_NEW}, function () {
                table.reload();
            }
        );
    };

    /**
     * 删除
     */
    var deleteData = function () {
        if (!table.isSearched()) {
            return;
        }
        var selArr = table.getAllCheckedRows();
        if (!selArr || selArr.length == 0) {
            Joyin.error("请从列表中选择数据");
            return;
        }
        var object = {selectedItem: selArr, operateType: "DELETE"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("删除当前数据")), function () {
            Joyin.ajaxJson(basePath + 'svreport/checkRule/PbcCheckRuleInfo001/delete', object, function (result) {
                table.reload();
            });
        });
    };

    $(function () {

        $('#PbcCheckRuleInfo001_btn_search').click(function () {
            table.load();
        });

        $('#PbcCheckRuleInfo001_btn_export').click(function () {
            exportExcel();
        });

        $('#PbcCheckRuleInfo001_btn_add').click(function () {
            openNew();
        });

        // 删除按钮点击事件
        $("#PbcCheckRuleInfo001_btn_delete").click(function () {
            deleteData();
        });
        $('#PbcCheckRuleInfo001_btn_effective').click(function() {
            effective('Y')
        });
        $('#PbcCheckRuleInfo001_btn_neffective').click(function() {
            effective('N')
        });
    });
}());
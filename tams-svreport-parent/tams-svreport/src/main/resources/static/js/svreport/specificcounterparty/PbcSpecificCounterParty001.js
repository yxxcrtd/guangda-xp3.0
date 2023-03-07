(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            // 交易对手机构名称
            partyOrgName: $("#partyOrgName").val(),
            // 交易对手产品名称
            partyProdName: $("#partyProdName").val(),
            // 资产代码
            assetCode: $("#assetCode").val(),
            // 资产负债项目
            assetType: $("#assetType").val(),
            // 交易对手产品种类
            partyProdType: $('#partyProdType').val(),
            // 数据状态
            status: $("#status").val()
        };
    };
    // 表格的列
    var tabCols = [
        {field: 'pbcUuid', title: '主键UUID', visible: false}
        , {field: 'statusStr', title: '数据状态', sortField: 'status'}
        , {field: 'assetCode', title: '资产代码', sortField: 'assetCode'}
        , {field: 'partyOrgName', title: '交易对手机构名称', sortField: 'partyOrgName'}
        , {field: 'partyProdName', title: '交易对手产品名称', sortField: 'partyProdName'}
        , {field: 'assetTypeStr', title: '资产负债项目', sortField: 'assetType'}
        , {field: 'partyProdTypeStr', title: '交易对手产品种类', sortField: 'partyProdType'}
        , {field: 'partyOrgCode', title: '交易对手机构编码', sortField: 'partyOrgCode'}
        , {field: 'partyProdId', title: '交易对手产品代码', sortField: 'partyProdId'}
        , {
            fixed: 'right',
            field: 'action',
            title: 'SysCommon.operation',
            type: 'action',
            width: 50,
            render: function (data, type, row, meta) {
                if (row.status != 'D'){
                    var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                    str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                    return str;
                }
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
            Joyin.dialog(JoyinUtil.getLabel("PbcSpecificCounterParty001.reference"),
                JoyinUtil.getRealPath("svreport/specificcounterparty/PbcSpecificCounterParty002/init"),
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
        data.pageId = 'PbcSpecificCounterParty002';
        Joyin.confirm("请确认是否修改?", function () {
            Joyin.dialog(JoyinUtil.getLabel("PbcSpecificCounterParty001.edit"),
                basePath + "svreport/specificcounterparty/PbcSpecificCounterParty002/init",
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
        var object = {selectedItem: [data], operateType: "DELETE", pbcUuid: data.pbcUuid};
        object.pageId = 'PbcSpecificCounterParty002';
        Joyin.confirm("请确认是否删除?", function () {
            Joyin.ajaxJson(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty001/delete', object,
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


    // 002 页面
    var openNew = function () {
        Joyin.dialog(JoyinUtil.getLabel("PbcSpecificCounterParty001.new"),
            JoyinUtil.getRealPath("svreport/specificcounterparty/PbcSpecificCounterParty002/init"),
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
        var deleteFlag = selArr[0].status;
        for (var i = 0; i < selArr.length; i++) {
            if (selArr[i].status == 'D') {
                return Joyin.error("只允许对“待处理”或“有效”状态的数据进行删除操作");
            }
        }
        var object = {selectedItem: selArr, operateType: "DELETE"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("PbcSpecificCounterParty001.delete")), function () {
            Joyin.ajaxJson(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty001/delete', object, function (result) {
                table.reload();
            });
        });
    };

    /**
     * 导入
     */
    var importData = function () {
        Joyin.confirm("请确认是否要导入数据？", function () {
            var options = {};
            options.pageId = "PbcSpecificCounterParty001";
            options.exts = 'xls|xlsx';
            Joyin.uploadFile(options, function (fileName) {
                Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty001/checkConfirmMsgForImportData', {fileName: fileName}, function (result) {
                    if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                        var joyinMessages = "";
                        $.each(result.joyinMessages, function (i, joyinMessage) {
                            joyinMessages += joyinMessage.message + "<br />"
                        });
                        Joyin.confirm(joyinMessages, function () {
                            Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty001/importData', {fileName: fileName}, function () {
                                table.reload();
                            });
                        });
                    } else {
                        Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty001/importData', {fileName: fileName}, function () {
                            table.reload();
                        });
                    }
                });
            }, function () {
                table.reload();
            });
        });
    };

    $(function () {

        $('#PbcSpecificCounterParty001_btn_search').click(function () {
            table.load();
        });

        $('#PbcSpecificCounterParty001_btn_export').click(function () {
            exportExcel();
        });

        $('#PbcSpecificCounterParty001_btn_add').click(function () {
            openNew();
        });

        // 删除按钮点击事件
        $("#PbcSpecificCounterParty001_btn_delete").click(function () {
            deleteData();
        });

        // 导入
        $('#PbcSpecificCounterParty001_btn_import').click(function () {
            importData();
        });

    });
}());
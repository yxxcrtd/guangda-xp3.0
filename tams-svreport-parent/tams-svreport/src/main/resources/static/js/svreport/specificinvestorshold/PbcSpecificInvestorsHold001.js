(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            // 数据状态
            status: $("#status").val(),
            // 内部产品代码
            account: $("#account").val(),
            // 人行产品代码
            pbcProdId: $("#pbcProdId").val(),
            // 识别标识
            indentifyMark: $("#indentifyMark").val(),
            // 投资者名称
            holderName: $("#holderName").val(),
            // 持有日期
            holdDateStart: $("#holdDate").startDate(),
            // 持有日期
            holdDateEnd: $("#holdDate").endDate()
        };
    };
    // 表格的列
    var tabCols = [
        {field: 'uuid', title: '主键UUID', visible: false}
        , {field: 'statusStr', title: '数据状态', sortField: 'status', width: 120}
        , {field: 'account', title: '内部产品代码', sortField: 'account', width: 150}
        , {field: 'pbcProdId', title: '人行产品代码', sortField: 'pbcProdId', width: 150}
        , {field: 'indentifyMark', title: '识别标识', sortField: 'indentifyMark', width: 180}
        , {field: 'holderName', title: '投资者名称', sortField: 'holderName', width: 150}
        , {field: 'holderTypeStr', title: '投资者类别', sortField: 'holderType', width: 200}
        , {field: 'holdDateStr', title: '持有日期', sortField: 'holdDate', width: 150}
        , {field: 'ccyStr', title: '币种', sortField: 'ccy', width: 150}
        , {field: 'qty', title: '持有份额', type: 'money', digit: 2, sortField: 'qty', width: 230}
        , {field: 'amount', title: '持有金额', type: 'money', digit: 2, sortField: 'amount', width: 245}
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
            Joyin.dialog(JoyinUtil.getLabel("PbcSpecificInvestorsHold001.reference"),
                JoyinUtil.getRealPath("svreport/specificinvestorshold/PbcSpecificInvestorsHold002/init"),
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
        data.pageId = 'PbcSpecificInvestorsHold002';
        Joyin.confirm("请确认是否修改?", function () {
            Joyin.dialog(JoyinUtil.getLabel("PbcSpecificInvestorsHold001.edit"),
                basePath + "svreport/specificinvestorshold/PbcSpecificInvestorsHold002/init",
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
        data.pageId = 'PbcSpecificInvestorsHold002';
        Joyin.confirm("请确认是否删除?", function () {
            Joyin.ajaxJson(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold001/delete', object,
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
        Joyin.dialog(JoyinUtil.getLabel("PbcSpecificInvestorsHold001.new"),
            JoyinUtil.getRealPath("svreport/specificinvestorshold/PbcSpecificInvestorsHold002/init"),
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
            if (selArr[i].status === 'D') {
                return Joyin.error("只能删除非删除数据状态的数据，请重新选择");
            }

            if (selArr[i].status !== deleteFlag) {
                return Joyin.error("只能删除一种数据状态的数据，请重新选择");
            }
        }
        var object = {selectedItem: selArr, operateType: "DELETE"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("PbcSpecificInvestorsHold001.delete")), function () {
            Joyin.ajaxJson(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold001/delete', object, function (result) {
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
            options.pageId = "PbcSpecificInvestorsHold001";
            options.exts = 'xls|xlsx';
            Joyin.uploadFile(options, function (fileName) {
                Joyin.ajax(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold001/checkConfirmMsgForImportData', {fileName: fileName}, function (result) {
                    if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                        var joyinMessages = "";
                        $.each(result.joyinMessages, function (i, joyinMessage) {
                            joyinMessages += joyinMessage.message + "<br />"
                        });
                        Joyin.confirm(joyinMessages, function () {
                            Joyin.ajax(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold001/importData', {fileName: fileName}, function () {
                                table.reload();
                            });
                        });
                    } else {
                        Joyin.ajax(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold001/importData', {fileName: fileName}, function () {
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

        $('#PbcSpecificInvestorsHold001_btn_search').click(function () {
            table.load();
        });

        $('#PbcSpecificInvestorsHold001_btn_export').click(function () {
            exportExcel();
        });

        $('#PbcSpecificInvestorsHold001_btn_add').click(function () {
            openNew();
        });

        // 删除按钮点击事件
        $("#PbcSpecificInvestorsHold001_btn_delete").click(function () {
            deleteData();
        });

        // 导入
        $('#PbcSpecificInvestorsHold001_btn_import').click(function () {
            importData();
        });

    });
}());
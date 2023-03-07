(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            // 统计年月
            termId: $("#termId").val(),
        };
    };
    // 表格的列
    var tabCols = [
        {field: 'pbcUuid', title: '主键UUID', visible: false}
        , {field: 'termId', title: '统计年月', sortField: 'termId', width: 120}
        , {field: 'finAssetBalance', title: '金融机构表内资产余额（本外币）', type: 'money', digit: 2, sortField: 'finAssetBalance', width: 280}
        , {field: 'finInsAssetBalance', title: '金融机构表内金融资产余额（本外币）', type: 'money', digit: 2, sortField: 'finInsAssetBalance', width: 320}
        , {
            title : '操作',
            field : 'operation',
            type : 'action',
            width : 100,
            buttons : [
                {text: '&#xe642;',clas: 'edit',title: '编辑'},
                {text: '&#xe640;',clas: 'delt',title: '删除'}
            ]
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
            Joyin.dialog("表内资产数据维护查看",
                JoyinUtil.getRealPath("svreport/fininstitution/PbcFinInstitutionAssetInfo002/init"),
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
        data.pageId = 'PbcFinInstitutionAssetInfo002';
        Joyin.confirm("请确认是否修改?", function () {
            Joyin.dialog("表内资产数据维护编辑",
                basePath + "svreport/fininstitution/PbcFinInstitutionAssetInfo002/init",
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
        var object = { selectedItem: [data], operateType: "DELETE", pbcUuid: data.pbcUuid};
        data.pageId = 'PbcFinInstitutionAssetInfo002';
        Joyin.confirm("请确认是否删除?", function () {
            Joyin.ajaxJson(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo001/delete', object,
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
        Joyin.dialog("表内资产数据维护新建",
            JoyinUtil.getRealPath("svreport/fininstitution/PbcFinInstitutionAssetInfo002/init"),
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
            if (selArr[i].status != deleteFlag) {
                return Joyin.error("只能删除一种数据状态的数据，请重新选择");
            }
        }
        var object = { selectedItem: selArr, operateType: "DELETE"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", "删除"), function () {
            Joyin.ajaxJson(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo001/delete', object, function (result) {
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
            options.pageId = "PbcFinInstitutionAssetInfo001";
            options.exts = 'xls|xlsx';
            Joyin.uploadFile(options, function (fileName) {
                Joyin.ajax(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function (result) {
                    if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                        var joyinMessages = "";
                        $.each(result.joyinMessages, function (i, joyinMessage) {
                            joyinMessages += joyinMessage.message + "<br />"
                        });
                        Joyin.confirm(joyinMessages, function () {
                            Joyin.ajax(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo001/importData', {fileName: fileName}, function () {
                                table.reload();
                            });
                        });
                    } else {
                        Joyin.ajax(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo001/importData', {fileName: fileName}, function () {
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

        $('#PbcFinInstitutionAssetInfo001_btn_search').click(function () {
            table.load();
        });

        $('#PbcFinInstitutionAssetInfo001_btn_export').click(function () {
            exportExcel();
        });

        $('#PbcFinInstitutionAssetInfo001_btn_add').click(function () {
            openNew();
        });

        // 删除按钮点击事件
        $("#PbcFinInstitutionAssetInfo001_btn_delete").click(function () {
            deleteData();
        });

        // 导入
        $('#PbcFinInstitutionAssetInfo001_btn_import').click(function () {
            importData();
        });

    });
}());
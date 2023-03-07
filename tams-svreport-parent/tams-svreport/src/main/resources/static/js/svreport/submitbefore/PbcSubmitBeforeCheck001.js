(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            // 检查时间
            checkDateStart: $("#checkDate").startDate(),
            // 检查时间
            checkDateEnd: $("#checkDate").endDate(),
            // 报表名称
            reportName: $("#reportName").val() == null ? null : $("#reportName").val().join(","),
            // 字段名称
            fieldName: $("#fieldName").val(),
            // 规则标识
            ruleCode: $("#ruleCode").val(),
            // 关联报表
            relevanceReport: $("#relevanceReport").val() == null ? null : $("#relevanceReport").val().join(","),
            // 规则编码
            ruleId: $("#ruleId").val(),
            //校验状态
            checkFlag:$("#checkFlag").val()
        };
    };

    // 表格的列
    var tabCols = [
        {field: 'dataUuid', title: 'uuid', visible: false}
        , {field: 'reportNameStr', title: '报表名称', sortField: 'reportName'}
        , {field: 'dataId', title: '数据唯一标识', sortField: 'dataId'}
        , {field: 'ruleId', title: '规则编码', sortField: 'ruleId'}
        , {field: 'checkFlagStr', title: '校验结果', sortField: 'checkFlag'}
        , {field: 'pbcElementCode', title: '人行要素编码', sortField: 'pbcElementCode'}
        , {field: 'fieldName', title: '字段名称', sortField: 'fieldName'}
        , {field: 'ruleTypeStr', title: '规则类型', sortField: 'ruleType'}
        , {field: 'ruleCodeStr', title: '规则标识', sortField: 'ruleCode'}
        , {field: 'ruleDetails', title: '规则详细内容', sortable: false}
        , {field: 'checkResult', title: '校验结果提示语', sortable: false}
        , {field: 'relevanceReport', title: '关联报表', sortable: false}
        , {field: 'checkDateStr', title: '检查时间', sortField: 'checkDate'}
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
        cols: tabCols
    });


    /**
     * 导出
     * 0 错误信息 / 1 报表
     */
    var exportExcel = function (flag) {
        debugger
        if (flag == '' || flag == undefined || flag == null) {
            return;
        }
        if (flag == '1'){
            if (table != null && table != '') {
                // 是否有选中行
                var selectArr = table.getAllCheckedRows();
                var selectData = JSON.stringify(selectArr);
            }
            // 根据查询导出
            if (!selectArr || selectArr.length == 0) {
                // 所有表格数据
                var paramObj = getSearchParams();
                if (paramObj.reportName == '' || paramObj.reportName == null ){
                    Joyin.error("未选中数据时，报表名称不可为空");
                    return;
                }
                var reportArr = paramObj.reportName.split(",");
                if (reportArr.length != 1){
                    Joyin.error("不能跨报表导出");
                    return;
                }
                paramObj.flag = "1";
                Joyin.exportExcel('export', paramObj);
            } else {
                // 报表去重
                var arr = [];
                layui.each(selectArr,function(index,data){
                    arr.push(data.reportName);
                });
                var list=Array.from(new Set(arr));
                if (list.length > 1){
                    return Joyin.error("不能跨报表导出");
                }
                // 选中导出
                var reportFlag = selectArr[0].reportName;
                var paramObj = {};
                paramObj.flag = "1";
                // 报表类型
                paramObj.reportName = reportFlag;
                // 选中导出
                paramObj.datas = true;
                paramObj.selectData = table.getAllCheckedRows();
                Joyin.exportExcel('export', paramObj);
            }
        } else {
            if (table != null && table != '') {
            } else {
                return ;
            }
            // 是否有选中行
            var selectArr = table.getAllCheckedRows();
            var selectData = JSON.stringify(selectArr);
            if (!selectArr || selectArr.length == 0) {
                // 所有表格数据
                var paramObj = getSearchParams();
                paramObj.flag = "0";
                Joyin.exportExcel('export', paramObj);
            } else {
                Joyin.exportExcel('export', {
                    selectData: selectData,
                    flag: "0"
                });
            }
        }

        var searchParams = getSearchParams();


    };



    $(function () {

        $('#PbcSubmitBeforeCheck001_btn_search').click(function () {
            table.load();
        });

        $('#PbcSubmitBeforeCheck001_btn_export').click(function () {
            // 导出错误明细信息
            exportExcel("0");
        });

        $('#PbcSubmitBeforeCheck001_btn_exportReport').click(function () {
            // 导出报表
            exportExcel("1");
        });

    });
}());
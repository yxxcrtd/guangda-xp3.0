(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        // 月份
        if(param.month){
            param.month = param.month;
         }
        // 产品代码
        if(param.prodId){
            param.prodId = param.prodId;
        }
        // 投资人类型
        if(param.holderType){
            param.holderType = param.holderType;
        }
        // 募集方式
        if(param.raiseType){
            param.raiseType = param.raiseType;
        }
        // 数据日期
        param.statusDateStart = $('#statusDate').startDate();
        param.statusDateEnd = $('#statusDate').endDate();

        return param;
    };
    // 表格的列
    var tabCols = [
         {field:'statusDateStr', title: '数据日期', type: 'date',  width:120}
        ,{field:'lastHoldDayStr', title: '最后持仓日期', width:130}
        ,{field:'prodId', title: '产品代码', width:100}
        ,{field:'prodName', title: '产品名称', width:150}
        ,{field:'manager', title: '管理人',width:150}
        ,{field:'prodType', title: '产品类型', width:130}
        ,{field:'prodInvestType', title: '产品投资性质', width:130}
        ,{field:'raiseType', title: '募集方式', width:100}
        ,{field:'termid', title: '期限（年）', width:100}
        ,{field:'prodScale', title: '产品规模（元）', type:'money', width:160,digit: 2}
        ,{field:'inbankBondmarkHoldDenomina', title: '银行间债券市场持仓面额', type: 'number', width:130}
        ,{field:'inbankBondmarkHoldRatio', title: '银行间债券市场持仓占比', type: 'number', width:130}
        ,{field:'holderName', title: '投资人名称',width:130}
        ,{field:'holderType', title: '投资人类型',width:130}
        ,{field:'investAmount', title: '投资金额（元）', type:'money', width:130,digit: 2}
        ,{field:'investRatio', title: '投资比例', type:'money', width:130,digit: 2}
        ,{field:'twoHolderName', title: '二级投资人名称',width:130}
        ,{field:'twoHolderType', title: '二级投资人类型',width:130}
        ,{field:'twoInvestAmount', title: '二级投资人投资金额（元）', type:'money', width:130}
        ,{field:'twoInvestRatio', title: '二级投资人投资比例', type:'number', width:130}
        ,{field:'prodShare', title: '产品份额', type:'money', width:150,digit: 2}
        ,{field:'holderShare', title: '投资人份额', type:'money', width:150,digit: 2}
        ,{field:'remark', title: '备注', width:130}
    ];

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols: tabCols,
    });


    /**
     * 导出报送模板
     */
    var exportSubmitExcel = function(){
        if (table != null && table != '') {
            // 是否有选中行
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        let paramObj = getSearchParams();
        if (!selArr || selArr.length == 0) {
            // 所有表格数据
            Joyin.exportExcel('export', paramObj);
        } else {
            paramObj['keys'] = datas;
            Joyin.exportExcel('export', paramObj);
        }
    };

    /**
     * 导出
     */
    var exportExcel = function(){
        if (table != null && table != '') {
            // 是否有选中行
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        let paramObj = getSearchParams();
        paramObj['export'] = true;
        if (!selArr || selArr.length == 0) {
            // 所有表格数据
            Joyin.exportExcel('export', paramObj);
        } else {
            paramObj['keys'] = datas;
            Joyin.exportExcel('export', paramObj);
        }
    };

            /**
             * 批导出
             */
            var exportBatchExcel = function(){
                // 后台批处理导出
                let param = getSearchParams();
                Joyin.ajax('batchExport',
                    {
                        key: JSON.stringify(param),
                        month: $('#month').val(),
                        selectedBatchGroup: '3096',// 批处理id
                        selectedProductGroup: '',
                        startDate: new Date(),
                        endDate: new Date(),
                        batchGroupType: '1',
                        prodGroupType: '0',
                        detailName: '委托人明细数据表批导出'
                    },
                    function(result) {
                        if(result.data){
                            Joyin.alert("委托人明细数据表批导出启动成功");
                        } else {
                            Joyin.alert(result.message);
                        }
                    }
                );
            };

    $(function() {
        $('#ChbConsignorDetailSub001_btn_search').click(function() {
            let monthStr = $.trim($('#month').val());
            if (monthStr == '' || monthStr == null) {
                Joyin.alert("请输入月份", 2);
            } else {
                table.load();
            }
        });
        $('#ChbConsignorDetailSub001_btn_export').click(function() {
                         // 导出
                         exportExcel();
        });
        $('#ChbConsignorDetailSub001_btn_exportSubmit').click(function() {
            // 导出前检查
            let param = getSearchParams();
            Joyin.ajaxJson('checkExport', param, function (result) {
                 console.log(result);
                 if (result.data) {
                     // 批导出
                      exportBatchExcel();
                 } else {
                          exportSubmitExcel();
                 }
            });
        });
    });
}());
$(function() {

    /**
     * 获取查询参数
     */

    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.dateStatus){
            param.dateStatus = $('#dateStatus').val();
        }
        return param;
    };
    var laydate = layui.laydate;
    laydate.render({
        elem: '#dateStatus',
        type: 'month',
        format: 'yyyy-MM'
    });

    	   var init = function(){
            	//隐藏查询按钮
            	$('#ChbProductsAssetCompare001_btn_search').attr("style","display:none;");
            }

    /**
     * 导出
     */
    var exportExcel = function(){
        // 所有表格数据
        var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
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
                    dateStatus: $('#dateStatus').val(),
                    selectedBatchGroup: '3099',// 批处理id
                    selectedProductGroup: '',
                    startDate: new Date(),
                    endDate: new Date(),
                    batchGroupType: '1',
                    prodGroupType: '0',
                    detailName: '理财产品资产对照表批导出'
                },
                function(result) {
                    if(result.data){
                        Joyin.alert("理财产品资产对照表批导出启动成功");
                    } else {
                        Joyin.alert(result.message);
                    }
                }
            );
        };

    /**
     * 画面加载完成
     */
    $(function() {
        init();
       //导出事件
        $('#ChbProductsAssetCompare001_btn_export').click(function() {
            // 导出前检查
            let param = getSearchParams();
            Joyin.ajaxJson(basePath + 'svreport/gd/report/chbproductsassetcompare/ChbProductsAssetCompare001/Check', param, function(result) {
            Joyin.ajaxJson('checkExport', param, function (result) {
                console.log(result);
                if (result.data) {
                    // 批导出
                    exportBatchExcel();
                } else {
                    exportExcel();
                }
            });
            });
        });

    });
}());
$(function() {

    /**
     * 获取查询参数
     */

    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        //查询日期
        param.dateStatus = $('#dateStatus').val();
        return param;
    };
    var laydate = layui.laydate;
    laydate.render({
        elem: '#dateStatus',
        type: 'month',
        format: 'yyyy-MM'
    });


    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取查询参数
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
                    selectedBatchGroup: '3097',// 批处理id
                    selectedProductGroup: '',
                    startDate: new Date(),
                    endDate: new Date(),
                    batchGroupType: '1',
                    prodGroupType: '0',
                    detailName: '理财产品资产要素登记表批导出'
                },
                function(result) {
                    if(result.data){
                        Joyin.alert("理财产品资产要素登记表批导出启动成功");
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
        $('#ChbAssetsElementsSub001_btn_search').attr("style","display:none;");
       //导出事件
        $('#ChbAssetsElementsSub001_btn_export').click(function() {
            // 导出前检查
            let param = getSearchParams();
            Joyin.ajaxJson(basePath + 'svreport/gd/report/chbassetselementssub/ChbAssetsElementsSub001/Check', param, function(result) {
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
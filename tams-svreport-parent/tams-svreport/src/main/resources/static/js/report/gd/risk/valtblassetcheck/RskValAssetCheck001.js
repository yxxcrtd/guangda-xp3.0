$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var result = $.serializeToObject('#submitForm');
        if($("#ptlId").val() == null || $("#ptlId").val() == ''){
            result.ptlId = $("#ptlId").val();
        }
        else{
            result.ptlId = $("#ptlId").val().join(',');
        }
        result.vdate = $('#valDate').startDate();
        result.mdate = $('#valDate').endDate();
        result.ptlName = $('#ptlName').val();
        return result;
    };
    var dataTable=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
                 {field:'id',visible:false}//id
                ,{field:'ptlId', width:150, title: '资管计划代码' ,sortField:'ptlId'}
                ,{field:'ptlName', width:300, title: '资管计划名称' ,sortField:'ptlName'}
                ,{field:'valDate', width:200, title: '持仓日期' ,type:'date' ,sortField:'valDate'}
                ,{field:'smv', width:200, title: '穿透前净资产',type:'number', sortField:'smv'}
                ,{field:'assetAmt', width:200,title: '穿透后总资产',type:'number',sortField:'assetAmt'}
                ,{field:'odAmt', width:200,title: '穿透后总负债',type:'number',sortField:'odAmt'}
                ,{field:'checkAmt', width:200,title: '检查结果',type:'number',sortField:'checkAmt'}
        ]
    });

    var exportExcel = function() {
            var paramObj = getPageParams();
            var rows = dataTable.getAllCheckedRows();
            if(rows.length > 0) {
                var keyList = [];
                paramObj.selectData =  JSON.stringify(rows);
            }
            Joyin.exportExcel('export', paramObj);
        };

    $(function () {

            $("#RskValAssetCheck001_btn_search").click(function () {
                dataTable.load();
             });

            $('#RskValAssetCheck001_btn_export').click(function() {
                exportExcel();
             });

        });

});

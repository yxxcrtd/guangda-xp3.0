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
        result.status = $('#status').val()==null?null:$('#status').val().join();
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
            ,{field:'fileName', width:400 , title: '文件名称' ,sortField:'file_name'}
            ,{field:'ptlName', width:200 , title: '资产名称' ,sortField:'ptl_Id'}
            ,{field:'valDate', width:200, title: '估值日期' ,type:'date' ,sortField:'val_date'}
            ,{field:'message', width:400 , title: '导入描述'}
            ,{field:'createTime', width:200 ,title: '导入时间' ,type:'datetime',sortField:'createTime'}
            ,{field:'statusName', width:200 ,title: '导入状态' ,sortField:'status'}
        ]
    });

    $(function () {
        $("#RskValTableImportLog001_btn_search").click(function () {
            dataTable.load();
        });

        //绑定到处按钮
        $('#RskValTableImportLog001_btn_export').click(function() {
            var param ={};
            var rows = dataTable.getAllCheckedRows();
            if(rows.length > 0) {
                param.dtoList = JSON.stringify(rows);
            }
            else{
                //有勾选优先勾选数据
                param = getPageParams();
            }
            Joyin.confirm("确定导出估值表并自动标识导出估值表的状态为手工处理？", function() {
                Joyin.exportExcel('export', param);
                dataTable.reload();
             });
        });

        $('#RskValTableImportLog001_btn_import').click(
            				function() {
                                Joyin.uploadFile({
                                    hideTpl : true
                                   }, function(fileName) {
                                    Joyin.ajax('uploadData', {fileName : fileName}, function(result) {

                                    });
                                }, function() {
                                    table.reload();
                                });
            				});

        $('#RskValTableImportLog001_btn_run').click(function() {
                    Joyin.confirm("确定运行估值导入批处理任务？", function() {
                        Joyin.ajax('checkBatchClash', {taskId : "3299"}, function(result) {
                            Joyin.ajax('runAutoImport',
                                    {selectedBatchGroup: '3299',
                                    selectedProductGroup: '',
                                    startDate: new Date(),
                                    endDate: new Date(),
                                    batchGroupType: '1',
                                    prodGroupType: '0',
                                    detailName: '估值表自动导入'},
                            function(result) {

                            });
                        });
                        dataTable.reload();
                    });
        });
    })
});

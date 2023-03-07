$(function() {
  //跳转的页码
    var pageCount = 1;

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //报送类型
            reportName : $('#reportName').val(),
            //生成日期
            // 查询起始日期
            createDateStart:$('#createDateStart').val(),
            // 查询结束日期
            createDateEnd:$('#createDateEnd').val(),
            //报送状态
            reportStatus:$('#reportStatus').val(),
            keyList:$('#keyList').val()
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox:false,
        cols:[
            { title: "标识码", field: 'validKey',sortable:false},
            { title: "检查结果", field: 'checkResult',sortable:false},
            { title: "错误信息项", field: 'errorInfo',sortable:false},
            { title: "警示信息", field: 'warnInfo',sortable:false}
        ]
    });



}());
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            svrAssetCode: $('#svrAssetCode').val() == null ? null : $(
            '#svrAssetCode').val().join(","),
            sadasAssetCode: $('#sadasAssetCode').val()
        };
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002/init",
                    "40%", "70%", {pageModel:PAGE_MODEL_REFERENCE,
                    uuid:data.uuid});
        },            
        cols:[{field:'uuid',visible:false},
            {field:'svrAssetCode',width:150,  title: '报送系统资产代码'}
            ,{field:'assetName',width:370,  title: '报送系统资产名称'}
            ,{field:'sadasAssetCode',width:250,  title: '策略专户系统代码'}
            ,{field:'updateTimeStr',width:200, title: '更新时间',sortable:false}
            ,{field:'updateUser',width:100, title: '更新人',sortable:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog("资产代码匹配映射管理修改",
            basePath + "chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002/init",
            "40%", "70%", {pageModel:PAGE_MODEL_EDIT,
                uuid:data.uuid},function() {
                table.reload();
            });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
            var object = data;
            Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbassetmapping/ChbAssetMapping001/check', object, function (result) {
                Joyin.confirm("请确认是否要删除该数据.删除后底层持仓数据映射的 策略专户系统代码 将恢复报送系统资产代码", function () {
                    Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbassetmapping/ChbAssetMapping001/delete', object, function () {
                        table.reload();
                    });
                });
            });
    });


    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ChbAssetMapping001_btn_search').click(function() {
            table.load();
        });
        $('#ChbAssetMapping001_btn_export').click(function() {
            exportExcel();
        });
        $('#ChbAssetMapping001_btn_add').click(function() {
        
            Joyin.dialog("资产代码匹配映射管理新建",
                        basePath + "chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002/init",
                        "40%", "70%", {pageModel:PAGE_MODEL_NEW});
        });
     });
}());
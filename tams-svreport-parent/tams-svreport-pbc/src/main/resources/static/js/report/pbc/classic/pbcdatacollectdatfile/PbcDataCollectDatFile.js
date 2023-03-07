$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //报送类型
            reportName : $('#reportName').val()==null?null:$('#reportName').val().join(","),
            //生成日期
            // 查询起始日期
            createDateStart:$('#createDate').startDate(),
            // 查询结束日期
            createDateEnd:$('#createDate').endDate(),
            //报送状态
            reportStatus:$('#reportStatus').val()
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
        cols:[
            { title: "uuid", field: 'uuid',width:0,visible:false,sortable:false},
            { title: "Dat文件名称", field: 'datFileName',sortable:false},
            { title: "报表名称", field: 'reportName',visible:false,sortable:false},
            { title: "报表名称", field: 'reportNameStr',sortable:false},
            { title: "生成日期", field: 'createDate', type: 'datetime',sortable:false},
           // { title: "DAT明细数", field: 'pibDetailCnt',width:100, sortable:false},
            { title: "报送状态", field: 'reportStatus',visible:false,sortable:false},
            { title: "报送状态", field: 'reportStatusStr',sortable:false},
            { title: "报送日期", field: 'reportDate', type: 'datetime',sortable:false},
             {
                 title : '操作',
                     field : 'operation',
                 type : 'action',
                 style:'text-align: left;',
                 buttons : [
                 {
                     text : '&#xe615;',
                     clas : 'batch',
                     title : '预览'
                 }, {
                         text : '&#xe640;',
                         clas : 'delt',
                         title : '删除'

                     }
                ]
             }
        ]
    });

    table.bind('click', '.batch', function(data, index) {
        var height = '500px';
        var btns = {};
        var keyList = [];
        var dialogIndex = -1;
        var path = basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/preView';
        if(data.status=='03'){
            Joyin.error("删除状态数据不支持预览");
            return;
        }

        var param={uuid:data.uuid,reportName:data.reportName};

        Joyin.ajax('preView', param, function(result) {
            if ("fail" == result){
                Joyin.error("所选数据不存在，请重新查询！");
                table.load();
            } else {
                dialogIndex = Joyin.dialog('预览', path, '1200px', height, param, function (params) {
                }, btns);
            }
        });

    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var param={uuid:data.uuid,reportName:data.reportName};
           if(data.reportStatus !='01'){
               Joyin.error("已报送状态的数据，不可删除！");
               return;
           }
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajax(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/delete', param,
                    function(){table.reload();});
            });

    });

       $(function(){
           // 绑定按钮事件
           $('#PbcDataCollectDatFile_btn_search').click(function() {
               table.load();
           });
           /**
            * 重置
            */
       });
    $('#PbcDataCollectDatFile_btn_reset').click(function() {
        $('#reportName').val('');
        $('#createDate').val('');
        table.load();
    });
    // 导入Excel
    $('#PbcDataCollectDatFile_btn_staticTemplateImport').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
           Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/checkExcel', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/importExcel', {fileName: fileName},function(result) {
                           //校验通过
                            if(result.length==0){
                                setTimeout(function() {
                                    Joyin.confirm('校验通过！导入后将覆盖登记和Dat中未报送数据，请确认是否入库？',function(){
                                        Joyin.ajax(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/importData', {fileName: fileName},function () {
                                            table.reload();
                                        });
                                    });
                                },500);
                           }
                            table.reload();
                        });
                });
            },function() {
                table.reload();
            });
        });
    });

    //下载zip文件
    // 导出按钮
    $('#PbcDataCollectDatFile_btn_sendFileDownLoad').click(function(){
        var selArr=table.getAllCheckedRows();
        var arr=new Array();
        for(var i in selArr){
            arr.push(selArr[i].uuid);
        }
        var data = JSON.stringify(arr);
        var param = getSearchParams();
        param.keyList = data;

        var btn = {"下载dat文件":downLoadDatFile, "下载excel文件":downLoadExcelFile}

        if(selArr.length === 0){
            Joyin.ajaxJson(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/checkCount', param, function(result) {
                if(result==false){
                    Joyin.error("下载的dat文件量过多,请重新选择！");
                    return;
                }else {
                    Joyin.dialog("dat数据检查",
                        basePath + "pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile002/init",
                        "60%", "80%", param, function () {
                            table.reload();
                        }, btn);
                }

            });
        }else{
            if(selArr.length>20){
                Joyin.error("下载的dat文件量过多,请重新选择！");
                return;
            }
            Joyin.dialog("dat数据检查",
                basePath + "pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile002/init",
                "60%", "80%", param, function () {
                    table.reload();
                }, btn);
        }

    })

    var downLoadDatFile = function(){
        var selArr=table.getAllCheckedRows();
        Joyin.confirm("是否确认生成报送文件？", function() {
            var arr=new Array();
            for(var i in selArr){
                arr.push(selArr[i].uuid);
            }
            var data = JSON.stringify(arr);
            var param = getSearchParams();
            param.keyList = data;
            Joyin.exportExcel(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/download', param);
            table.reload();
        });
    }


    var downLoadExcelFile = function(){
        var selArr=table.getAllCheckedRows();
        Joyin.confirm("是否下载Excel文件？", function() {
            var arr=new Array();
            for(var i in selArr){
                arr.push(selArr[i].uuid);
            }
            var data = JSON.stringify(arr);
            var param = getSearchParams();
            param.keyList = data;
            Joyin.exportExcel(basePath + 'pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile/downloadExcel', param);
            table.reload();
        });
    }


}());
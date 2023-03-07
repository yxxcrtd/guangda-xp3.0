(function() {
    /**
     * 初始化
     */

    if(pageModel==PAGE_MODEL_EDIT){
        $("#counterId").attr("disabled",true);
    }
        //  layui.form.render();
    //};



    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002/check', object, function(result) {
           //提交工作流
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002/save', object, "ChbMstCounterParty002_btn_submit");
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定按钮事件
        $('#ChbMstCounterParty002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });

    });
}());
(function () {
    /**
     * 初始化
     */
    var init = function () {
        if (pageModel === PAGE_MODEL_EDIT) {
            // 统计年月
            $("#termId").attr("disabled", true);
        }
    };



    /**
     * 提交
     * @param operateType 操作类型
     */
    var saveOrSubmit = function (operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo002/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit")), function () {
                Joyin.ajaxJson(basePath + 'svreport/fininstitution/PbcFinInstitutionAssetInfo002/save', object, function (result) {
                    Joyin.closeTopWindow();
                });
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {

        init();

        // 绑定按钮事件
        $('#PbcFinInstitutionAssetInfo002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });

    });
}());
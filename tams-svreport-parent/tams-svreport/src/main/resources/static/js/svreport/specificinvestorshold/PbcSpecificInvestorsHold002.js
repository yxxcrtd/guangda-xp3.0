(function () {
    /**
     * 初始化
     */
    var init = function () {
        if (pageModel === PAGE_MODEL_EDIT) {
            // 内部产品代码
            $("#account").attr("disabled", true);
            // 人行产品代码
            $("#pbcProdId").attr("disabled", true);
            // 识别标识
            $("#indentifyMark").attr("disabled", true);
            // 投资者名称
            $("#holderName").attr("disabled", true);
            // 持有日期
            $("#holdDate").attr("disabled", true);
        }
    };

    if (pageModel === PAGE_MODEL_NEW) {
        // 通过 内部产品代码 获取 人行产品代码
        $("#account").change(function () {
            let account = $("#account").val();
            if ("" == account || account == null || account == undefined) {
                $('#pbcProdId').val('');
                return;
            } else {
                // ajaxJson 非表单提交 工具类无法获取参数
                Joyin.ajax(basePath + "svreport/specificinvestorshold/PbcSpecificInvestorsHold002/getPbcProdIdByAccount", {search: account},
                    function (result) {
                        if (result['pbcProdId'] != '' && result['pbcProdId'] != null) {
                            $('#pbcProdId').val(result['pbcProdId']);
                        } else {
                            $('#pbcProdId').val('');
                        }
                    }
                );
            }
        });

        // 通过 内部产品代码 获取产代码
        $("#indentifyMark").change(function () {
            let indentifyMark = $("#indentifyMark").val();
            if ("" == indentifyMark || indentifyMark == null || indentifyMark == undefined) {
                return;
            } else {
                // ajaxJson 非表单提交 工具类无法获取参数
                Joyin.ajax(basePath + "svreport/specificinvestorshold/PbcSpecificInvestorsHold002/getHolderTypeByIndentifyMark", {search: indentifyMark},
                    function (result) {
                        if (result['holderType'] != '' && result['holderType'] != null) {
                            $('#holderType').val(result['holderType']);
                            $('#holderName').val(result['holderName']);
                        } else {
                            ;
                        }
                    },
                    function (result) {
                    }
                );
            }
        });

    }

    // 校验持有日期
    var holdDateCompare = function (holdDate) {
        let toDay = new Date();
        let hold = new Date(holdDate);
        return hold > toDay;
    };

    // 识别标识与投资者名称 产品代码 不可同时与已有数据重复
    var checkIndentifyMarkAndHoldName = function () {
        let indentifyMark = $("#indentifyMark").val();
        let holderName = $("#holderName").val();
        let account = $("#account").val();
        let holdDate = $("#holdDate").val();
        if (indentifyMark == "" || indentifyMark == null || indentifyMark == undefined) {
            return;
        }
        if (holderName == "" || holderName == null || holderName == undefined) {
            return;
        }
        if (account == "" || account == null || account == undefined) {
            return;
        }
        if (holdDate == "" || holdDate == null || holdDate == undefined) {
            return;
        }
        var param = {};
        param.indentifyMark = indentifyMark;
        param.holderName = holderName;
        param.account = account;
        param.holdDate = holdDate;
        Joyin.ajax(basePath + "svreport/specificinvestorshold/PbcSpecificInvestorsHold002/getIndentifyMarkAndHoldName", param,
            function (result) {
                if (result > 0) {
                    layer.msg('识别标识、投资者名称、内部产品代码、持有日期不可同时与已有数据重复');
                }
            }
        );

    };

    // 失去焦点
    $('#indentifyMark').blur(function () {
        checkIndentifyMarkAndHoldName();
    });
    $('#holderName').blur(function () {
        checkIndentifyMarkAndHoldName();
    });
    // 失去焦点
    $('#holdDate').blur(function () {
        checkIndentifyMarkAndHoldName();
    });


    /**
     * 提交
     * @param operateType 操作类型
     */
    var saveOrSubmit = function (operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold002/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit")), function () {
                Joyin.ajaxJson(basePath + 'svreport/specificinvestorshold/PbcSpecificInvestorsHold002/save', object, function (result) {
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
        $('#PbcSpecificInvestorsHold002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });

        // 选择框回带属性 默认值不展示
        if (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_REFERENCE) {
            let accountInit = $("#account").attr('initvalue');
            $("#account").val(accountInit);

            let pbcProdIdInit = $("#pbcProdId").attr('initvalue');
            $("#pbcProdId").val(pbcProdIdInit);
        }

    });
}());
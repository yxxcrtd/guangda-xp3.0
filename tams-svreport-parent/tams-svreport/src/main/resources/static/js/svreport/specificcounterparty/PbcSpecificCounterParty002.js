(function () {
    /**
     * 初始化
     */
    var init = function () {
        if (pageModel === PAGE_MODEL_EDIT) {
            // 资产代码
            $("#assetCode").attr("disabled", true);
            $("#assetCode1").attr("disabled", true);
            // 交易对手机构名称
            // $("#partyOrgName").attr("disabled", true);
            // 交易对手产品名称
            // $("#partyProdName").attr("disabled", true);
            // 资产负债项目
            $("#assetType").attr("disabled", true);
            // 交易对手产品种类
            $("#partyProdType").attr("disabled", true);
        }
        // 新建模式
        if (pageModel === PAGE_MODEL_NEW){
            $("#typeMark").hide();
            // 交易对手产品种类
            $("#partyProdType").attr("disabled", true);
        }

    };

    var assetTypeArr = ['A5200', 'A7200', 'B1200'];
    var markTypeArr = ['C1100', 'C1200'];


    // 值来源
    $("#assetType").change(function () {
        let assetTypeFlag = false;
        let assetType = $('#assetType').val();
        if ('' == assetType || null == assetType) {
            return ;
        } else {
            let dataIndex = $.inArray(assetType, assetTypeArr);
            if (dataIndex >= 0) {
                // 隐藏
                $("#typeMark").hide();
                // 清空
                $('#assetCode1').val('');
                $("#typeAsset").show();
                // 可用
                $("#partyOrgName").attr("disabled", false);
                assetTypeFlag = true;
            }
            if (!assetTypeFlag) {
                // 显示
                $("#typeMark").show();
                // 隐藏
                $("#typeAsset").hide();
                // 清空
                $('#assetCode').val('');
                $('#partyOrgName').val('');
                // 禁用
                $("#partyOrgName").attr("disabled", true);
                assetTypeFlag = false;
            }
        }
    });

    // 资产代码 资产类
    $("#assetCode").change(function () {
        let assetTypeFlag = false;
        // 清空 交易对手产品名称
        // $('#partyProdName').val('');
        let assetCode = $('#assetCode').val();
        if ('' == assetCode || null == assetCode || assetCode == undefined) {
            return ;
        }
        let assetType = $('#assetType').val();
        if ('' == assetType || null == assetType || assetType == undefined) {
            layer.msg('请先选择资产负债项目类别');
            return ;
        } else {
            let assetCode = $("#assetCode").val();
            Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/getPartyOrgNameByAssetCode', { search : assetCode },
                function (result) {
                    if (result['partyOrgName']) {
                        $("#partyOrgName").val(result['partyOrgName']);
                    } else {
                        $("#partyOrgName").val('');
                    }
                }
            );
        }
        // 获取 交易对手产品名称
        if ('' == assetCode || null == assetCode || assetCode == undefined) {
            return ;
        } else {
            let assetCode = $("#assetCode").val();
            let param = {};
            param.flag = 'asset';
            param.search = assetCode;
            Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/getPartyProdNameByAssetCode', param,
                function (result) {
                    if (result['partyProdName']) {
                        $("#partyProdName").val(result['partyProdName']);
                    } else {
                        $("#partyProdName").val('');
                    }
                }
            );
        }

        // 获取 交易对手产品种类
        if ('' == assetCode || null == assetCode || assetCode == undefined) {
            return ;
        } else {
            let assetCode = $("#assetCode").val();
            let param = {};
            param.flag = 'asset';
            param.search = assetCode;
            Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/getPartyProdTypeByAssetCode', param,
                function (result) {
                    if (result['partyProdType']) {
                        $("#partyProdType").val(result['partyProdType']);
                    } else {
                        $("#partyProdType").val('');
                    }
                }
            );
        }
    });

    // 获取 交易对手产品名称 非资产类
    $("#assetCode1").change(function () {
        let assetType = $('#assetType').val();
        if ('' == assetType || null == assetType || assetType == undefined) {
            layer.msg('请先选择资产负债项目类别');
            return;
        }
        let assetTypeFlag = false;
        // 清空资产类 交易对手产品名称
        // $('#partyProdName').val('');
        let assetCode1 = $('#assetCode1').val();
        if ('' == assetCode1 || null == assetCode1 || assetCode1 == undefined) {
            return ;
        } else {
            let assetCode1 = $("#assetCode1").val();
            let param = {};
            param.flag = 'mark';
            param.search = assetCode1;
            Joyin.ajax(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/getPartyProdNameByAssetCode', param,
                function (result) {
                    if (result['partyProdName']) {
                        $("#partyProdName").val(result['partyProdName']);
                    } else {
                        $("#partyProdName").val('');
                    }
                }
            );
        }
    });



    /**
     * 提交
     * @param operateType 操作类型
     */
    var saveOrSubmit = function (operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        let assetTypeFlag = false;
        let assetType = $("#assetType").val();
        let dataIndex = $.inArray(assetType, assetTypeArr);
        // 资产类
        if (dataIndex >= 0) {
            object.assetCode = $('#assetCode').val();
            assetTypeFlag = true;
        }
        // 已填充
        dataIndex = $.inArray(assetType, markTypeArr);
        if (!assetTypeFlag && dataIndex >= 0) {
            let dataIndex = $.inArray(assetType, markTypeArr);
            object.assetCode = $('#assetCode1').val();
            assetTypeFlag = false;
        }
        if (!object.assetCode) {
            Joyin.alert("资产代码不可为空", 2);
            return;
        }
        Joyin.ajaxJson(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit")), function () {
                Joyin.ajaxJson(basePath + 'svreport/specificcounterparty/PbcSpecificCounterParty002/save', object, function (result) {
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
        $('#PbcSpecificCounterParty002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });

        // 选择框回带属性 默认值不展示
        if (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_REFERENCE) {
            let partyOrgNameInit = $("#partyOrgName").attr('initvalue');
            $("#partyOrgName").val(partyOrgNameInit);
        }

    });
}());
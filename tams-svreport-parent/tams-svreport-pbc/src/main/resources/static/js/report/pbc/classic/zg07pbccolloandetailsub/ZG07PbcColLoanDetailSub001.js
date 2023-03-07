$(function () {
    // 获取查询参数
    var getSearchParams = function () {
        return {
            // 产品代码
            pbcProdId: $("#pbcProdId").val(),
            // 资产代码
            assetCode: $("#assetCode").val(),
            // 贷款种类
            loansType: $("#loansType").val(),
            // 统计年月
            termid: $("#termid").val(),
            // 报送状态
            sendStatus: $("#sendStatus").val(),
            // 报送日期
            submitTimeStart: $("#submitTime").startDate(),
            submitTimeEnd: $("#submitTime").endDate(),
            // 报送截止日
            pbcSubmitDeadlineStart: $("#pbcSubmitDeadline").startDate(),
            pbcSubmitDeadlineEnd: $("#pbcSubmitDeadline").endDate(),
            // 数据日期
            statusDateStart: $("#statusDate").startDate(),
            statusDateEnd: $("#statusDate").endDate()
        };
    };

    // 定义数据表格
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        showDetail: true,
        cols: [
            { field: 'detailuuid', visible: false },
            { title: "数据日期", field: 'statusDate', width: 150, type: 'datetime', sortField: 'STATUS_DATE' },
            { title: "报送状态", field: 'sendStatusStr', width: 150, sortField: 'sendStatus' },
            { title: '报送日期', field: 'submitTime', width: 150, type: 'datetime', sortField: 'submitTime' },
            { title: "报送截止日", field: 'pbcSubmitDeadline', width: 150, type: 'date', sortField: 'pbcSubmitDeadline' },
            { title: "统计年月", field: 'termid', width: 150, sortField: 'termid' },
            { title: "产品代码", field: 'pbcProdId', width: 150, sortField: 'pbcProdId' },
            { title: "资产代码", field: 'assetCode', width: 150, sortField: 'assetCode' },
            { title: "贷款种类", field: 'loansTypeStr', width: 150, sortField: 'loansType' },
            { title: "贷款转让方机构代码", field: 'loanTransferorId', width: 150, sortField: 'loanTransferorId' },
            { title: "贷款合同原始发放机构代码", field: 'loanContractOrgId', width: 150, sortField: 'loanContractOrgId' },
            { title: "贷款合同原始发放机构所在地代码", field: 'loanContractOrgCodeStr', width: 150, sortField: 'loanContractOrgCode' },
            { title: "借款人类型", field: 'borrowerTypeStr', width: 150, sortField: 'borrowerType' },
            { title: "地区代码", field: 'borrowerCountyStr', width: 150, sortField: 'borrowerCounty' },
            { title: "借款人代码", field: 'borrowerId', width: 150, sortField: 'borrowerId' },
            { title: "行业信息", field: 'industryInfoStr', width: 150, sortField: 'industryInfo' },
            { title: "企业出资人经济成分", field: 'enterpriseInvestorTypeStr', width: 150, sortField: 'enterpriseInvestorType' },
            { title: "企业规模", field: 'enterpriseScaleStr', width: 150, sortField: 'enterpriseScale' },
            { title: "贷款借据编码", field: 'loanBasisId', width: 150, sortField: 'loanBasisId' },
            { title: "贷款产品类别", field: 'loanPrdTypeStr', width: 150, sortField: 'loanPrdType' },
            { title: "贷款实际投向", field: 'loanActualInvestStr', width: 150, sortField: 'loanActualInvest' },
            { title: "贷款发放日期", field: 'loanVdate', type:'date', format:'yyyy-MM-dd', width: 150, sortField: 'loanVdate' },
            { title: "贷款到期日期", field: 'loanMdate', type:'date', format:'yyyy-MM-dd', width: 150, sortField: 'loanMdate' },
            { title: "贷款展期到期日期", field: 'loanExtensionDueDate', type:'date', format:'yyyy-MM-dd', width: 150, sortField: 'loanExtensionDueDate' },
            { title: "利率是否固定", field: 'rateFixedStr', width: 150, sortField: 'rateFixed' },
            { title: "利率水平", field: 'rateLevel', type: 'money', digit: 5, width: 150, sortField: 'rateLevel' },
            { title: "贷款担保方式", field: 'loanGuaranteeModeStr', width: 150, sortField: 'loanGuaranteeMode' },
            { title: "贷款质量", field: 'loanQualityStr', width: 150, sortField: 'loanQuality' },
            { title: "贷款状态", field: 'loanStateStr', width: 150, sortField: 'loanState' },
            { title: "贷款转让折扣率", field: 'loanTransferorRate', type: 'money', digit: 5, width: 150, sortField: 'loanTransferorRate' },
            { title: "原始合同币种", field: 'origCcyStr', width: 150, sortField: 'origCcy' },
            { title: "原始合同金额", field: 'origContractAmount', type: 'money', digit: 2, width: 150, sortField: 'origContractAmount' },
            { title: "原始合同金额折人民币", field: 'origContractAmountCny', type: 'money', digit: 2, width: 150, sortField: 'origContractAmountCny' },
            { title: "贷款余额币种", field: 'amtCcyStr', width: 150, sortField: 'amtCcy' },
            { title: "贷款余额", field: 'contractAmount', type: 'money', digit: 2, width: 150, sortField: 'contractAmount' },
            { title: "贷款余额折人民币", field: 'contractAmountCny', type: 'money', digit: 2, width: 150, sortField: 'contractAmountCny' },
            { field: 'sendStatus', width: 150, sortField: 'sendStatus', visible: false },
            { field: 'status', width: 150, sortField: 'status', visible: false },
            { field: 'datuuid', visible: false }
        ]
    });


    /**
     * 导出
     * flag:0 全部导出，选中导出
     */
    var exportExcel = function (flag) {
        if (flag == 0) {
            var param = getSearchParams();
            var isHaveData = true;
            Joyin.ajaxSync('searchPage', param, function (result) {
                if (!result || result.root.length == 0) {
                    isHaveData = false;
                    return;
                }
            });
            Joyin.exportExcel("export", param);
            if (isHaveData) {
                Joyin.confirm("导出成功，是否自动标识当前未报送数据的报送状态为“手工处理”？", function () {
                    //页面添加滚动条
                    layui.layer.open({ type: 3 });
                    param.flag = '02';
                    Joyin.ajaxJson('doManual', param, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }
        } else {
            var param = {};
            var expArr = table.getAllCheckedRows();
            var keyList = '';
            var arr = [];
            var statuFlag = false;
            var termid = '';
            layui.each(expArr, function (index, data) {
                if (index == expArr.length - 1) {
                    keyList = keyList + data.detailuuid + '';
                } else {
                    keyList = keyList + data.detailuuid + ',';
                }
                if (data.sendStatus == '03') {
                    statuFlag = true;
                }
                arr.push(data.termid);
                termid = data.termid;
            });
            var list = Array.from(new Set(arr));
            if (list.length > 1) {
                return Joyin.error("所选报送数据包含多个报送月份数据，不允许人行Excel导出");
            }

            param.keyList = keyList;
            if (statuFlag) {
                Joyin.confirm("导出数据包含已报送数据，请确认是否导出！", function () {
                    Joyin.exportExcel("export", param);
                    // Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function () {
                    //     //页面添加滚动条
                    //     layui.layer.open({ type: 3 });
                    //     param.flag = '02';
                    //     param.infoList = table.getAllCheckedRows();
                    //     param.termid = termid;
                    //     Joyin.ajaxJson('doManual', param, function () {
                    //         table.load();
                    //         layui.layer.closeAll();
                    //     });
                    // });
                });
            } else {
                Joyin.exportExcel("export", param);
                Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function () {
                    //页面添加滚动条
                    layui.layer.open({ type: 3 });
                    param.flag = '02';
                    param.infoList = table.getAllCheckedRows();
                    param.termid = termid;
                    Joyin.ajaxJson('doManual', param, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }

        }

    };

    function init(){
        if(isMain=='01'){
            setTimeout(function() {
                if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
                    $('#submitTime').val(searchDataStart+' - '+searchDataEnd);
                }else{
                    $('#pbcSubmitDeadline').val(searchDataStart+' - '+searchDataEnd);
                }
                table.load();
            }, 500);
        }
    };
    init();
    $(function () {

        $('#ZG07PbcColLoanDetailSub001_btn_search').click(function () {
            table.load();
        });

        // 导出按钮
        $('#ZG07PbcColLoanDetailSub001_btn_export').click(function () {
            var expArr = table.getAllCheckedRows();
            if (expArr != null && expArr.length > 0) {
                Joyin.confirm('确认导出选中信息？', function () {
                    exportExcel(1);
                })
            } else {
                if ($('#termid').val() == null || $('#termid').val() == '') {
                    return Joyin.error("当不选择数据导出时，统计年月必填");
                }
                Joyin.confirm('请确认是否导出人行excel？', function () {
                    exportExcel(0);
                });
            }
        });

        //数据检查
        $('#ZG07PbcColLoanDetailSub001_btn_checkData').click(dateCheckMethod);
        function dateCheckMethod(){
            var checkArr=table.getAllCheckedRows();
            if(checkArr.length === 0) {
                var submitStatus = $('#sendStatus').val();
                var status = $('#status').val();
//            if(submitStatus!='01'){
                if (!(submitStatus && submitStatus.length == 2 && submitStatus == '01')) {
                    Joyin.error("请选择报送状态为未报送的数据进行数据检查或查询条件报送状态选择为未报送！");
                    return;
                }
//            else if(status && status.includes('03')){
//                 else if(status && status.indexOf('03') > -1){
//                     Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
//                     return;
//                 }
                var paramObj = getSearchParams()
                if(!status){
                    paramObj.status = '01,02';
                }
                Joyin.ajax('reportCheck',paramObj, function(result){
                    window.checkResult = result;
                    Joyin.checkData(dataTitle, checkUrl, getSearchParams());
                })
                return ;
            }
            var keyList = [];
            var keytermId='';

            for(var i=0;i<checkArr.length;i++){
                //modify by wangmin 修改取数顺序 start 2018-07-12
                keyList.push(checkArr[i].termid );
                keytermId=checkArr[i].termid;

                //modify by wangmin 修改取数顺序 end 2018-07-12
                if(checkArr[i].sendStatus !="01"){
                    Joyin.error("数据检查只能检查未报送数据，请重新选择！");
                    return;
                }
//            else if(checkArr[i].status && checkArr[i].status.includes('03')){
                else if(checkArr[i].status && checkArr[i].status.indexOf('03') > -1){
                    Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
                    return;
                }
            };
            var list=Array.from(new Set(keyList));
            if (list.length>1){
                return Joyin.error("所选报送数据包含多个报送月份数据，不允许数据检查");
            }
            Joyin.ajax('reportCheck',{checkKeys:keytermId}, function(result){
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, {checkKeys: keytermId});
            })
        }


        // 手工处理
        $('#ZG07PbcColLoanDetailSub001_btn_manual').click(function () {
            var rows = table.getAllCheckedRows();
            if (rows.length === 0) {
                return Joyin.error("请至少选择一条记录");
            } else {
                var arr = [];
                layui.each(rows,function(index,data){
                    arr.push(data.termid);
                });
                var list=Array.from(new Set(arr));
                if (list.length>1){
                    return Joyin.error("所选报送数据包含多个报送月份数据，不允许手工处理");
                }
                for (var i = 0; i < rows.length; i++) {
                    if (!(rows[i].sendStatus == '01')) {
                        Joyin.error("只能选择报送状态为未报送的数据进行手工处理，请重新选择！");
                        return;
                    }
                }
                var paramObj = {};
                paramObj.flag = '01';
                paramObj.infoList = rows;
                Joyin.confirm("请确认是否要手工处理选中产品的所有记录？", function () {
                    layui.layer.open({ type: 3 });
                    Joyin.ajaxJson('doManual', paramObj, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }

        });

        // 取消手工处理
        $('#ZG07PbcColLoanDetailSub001_btn_cancelManual').click(function () {
            var manArr = table.getAllCheckedRows();
            if (!manArr || manArr.length == 0) {
                return Joyin.error("请至少选择一条记录");
            } else {
                var arr = [];
                layui.each(manArr,function(index,data){
                    arr.push(data.termid);
                });
                var list=Array.from(new Set(arr));
                if (list.length>1){
                    return Joyin.error("所选报送数据包含多个报送月份数据，不允许取消手工处理");
                }
                for (var i = 0; i < manArr.length; i++) {
                    if (manArr[i].sendStatus != "03") {
                        Joyin.error("只能选择手工处理的数据，请重新选择！");
                        return;
                    }
                }

                var paramObj = {};
                paramObj.flag = '01';
                paramObj.infoList = manArr;
                Joyin.confirm("确认将选中产品的所有记录取消手工处理吗？", function () {
                    layui.layer.open({ type: 3 });
                    Joyin.ajaxJson('cancelManual', paramObj, function () {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }

        });

    });

    function init() {
        if (isMain == '01') {
            setTimeout(function () {
                if ($('#sendStatus').val() == '02' || $('#sendStatus').val() == '03') {
                    $('#submitTime').val(searchDataStart + ' - ' + searchDataEnd);
                } else {
                    $('#pbcSubmitDeadline').val(searchDataStart + ' - ' + searchDataEnd);
                }
                table.load();
            }, 500);
        }
    };

    init();

})
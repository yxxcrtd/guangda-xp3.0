$(function() {
    function submit() {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002/submit', object, function(){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        });
    };
  
    //画面初始化
    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#branchNo').attr("disabled",true);
        }

    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始化画面
        init();
        // 绑定按钮事件
        $('#ChbBranchNetwork002_btn_submit').click(function() {
            submit('submit');
        });
    });

}());
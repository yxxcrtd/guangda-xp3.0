$(function() {
    function submit() {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002/submit', object, function(){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        });
    };
  
    //画面初始化
    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#sadasAssetCode').attr("disabled",true);
        }

    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始化画面
        init();
        // 绑定按钮事件
        $('#ChbAssetMapping002_btn_submit').click(function() {
            submit('submit');
        });
    });

}());
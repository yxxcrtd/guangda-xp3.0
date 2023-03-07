/**
 * 非标资产(票据或信用)中债报送组件JS
 * @auth zhangnian
 */
(function(){

    /**
     * 添加必填标志
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    var getElementById = function(id) {
        return $('#' + entityName_BussRepAssetAstBillInfo + '_' + id);
    };
    
    var repAssetTypeChange = function(){
        var repAssetType = getElementById('repAssetType').val();
        if(repAssetType){
            getElementById('content_one_div').nextAll().show();
            getElementById('repAssetType_div').nextAll().show();
        }else{
            getElementById('content_one_div').nextAll().hide();
            getElementById('repAssetType_div').nextAll().hide();
        }
        if(repAssetType==CHB_ASSET_TYPE_PROJECT_1201||repAssetType==CHB_ASSET_TYPE_PROJECT_1210){
            $('#bussRepAssetAstBillInfoDto_repAstBillType_div').show();
        }else{
            getElementById('repAstBillType').val('').trigger('change');
            $('#bussRepAssetAstBillInfoDto_repAstBillType_div').hide();
        }
    }
    $(function(){
        //资产/负债类别change
        /*getElementById('repAssetType').change(repAssetTypeChange).trigger('change');*/
        $('#bussRepAssetAstBillInfoDto_isBzhpj').change(function() {
            if ($('#bussRepAssetAstBillInfoDto_isBzhpj').val()=="01") {
                 $("#bussRepAssetAstBillInfoDto_repBillCode").prop("required",true).trigger('change');
                 $("#bussRepAssetAstBillInfoDto_repBillCode").attr("disabled",false).trigger('change');
            }else {
                 $("#bussRepAssetAstBillInfoDto_repBillCode").prop("required",false).trigger('change');
                 $("#bussRepAssetAstBillInfoDto_repBillCode").val('').attr("disabled",true).trigger('change');
            }
            star();
        })
    });
    
}());
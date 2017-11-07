

$(function(){
    var isCurrentWorking;
    var currentSelectId;
    $('.data-panel>table').on("click","tbody>tr",function(e){
        isCurrentWorking=$(this).find(":contains(WORKING)").length>0;
        currentSelectId=$(this).attr("id");
        $option.show();
        if (isCurrentWorking){
            $option.html("暂停");
        }else{
            $option.html("恢复");
        }
    });
    var toggleStatus=function(){
        $.post(isCurrentWorking?"pause":'resume',{id:currentSelectId},function(res){
            window.submitQuery()
        },'JSON');
    };
    var $option=$('<li id="optionToggleStatus"></li>')
        .hide()
        .click(toggleStatus);
    $('#tools').prepend($option);
});
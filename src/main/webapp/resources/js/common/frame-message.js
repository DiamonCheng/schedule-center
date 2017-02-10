/**
 * Created by DeffersonCheng on 2017/1/6.
 * 用于消息展示JS
 */
(function(){
    if (!$){ console.error("JQuery没有加载，消息控件加载失败！"); return;}
    var frame=window.frame;
    if (!window.frame) frame=window.frame={};
    frame.toast=function(str,callback){
        var $oldMessage=$('.frame-message');
        if ($oldMessage.length!=0)$oldMessage.remove();
        var $m=$("<div/>").addClass("frame-message").text(str);
        if (typeof callback =="function") $m.click(callback);
        $m.prependTo($("body"));
    };
    frame.toastError=function(str){
        var $oldMessage=$('.frame-message');
        if ($oldMessage.length!=0)$oldMessage.remove();
        var $m=$("<div/>").addClass("frame-message").addClass("error").text(str);
        if (typeof callback =="function") $m.click(callback);
        $m.prependTo($("body"));
    };
    $(document).ready(function(){
        $(document).on("hover","div.frame-message",function(){
            console.log(this);
            $(this).css({"animationDelay":"-0.9s;"})
        });
        // $(document).on("click","div.frame-message",function(){
        //     $(this).remove();
        // });
    });
})();
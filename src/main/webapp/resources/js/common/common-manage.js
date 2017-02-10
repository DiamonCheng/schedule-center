/**
 * Created by dcpho on 2017/1/8.
 */
(function () {
    if (window.frame==null)window.frame={};
    window.frame.manage={
        saveManage:function(){
            var $inputs=$('.main-panel input[name],.main-panel select[name],.main-panel textarea[name]');
            var param={};
            $inputs.each(function () {
                var $this=$(this);
                var val=$this.val();
                if ($this.attr("isentity")!=null){
                    var obj={};
                    if (val==null||val.length==0) obj=null;
                    else obj[$this.attr("valuefield")]=val;
                    param[$this.attr("name")]=obj;
                }else{
                    param[$this.attr("name")]=val;
                }
            });
            if (frame.manage.beforeSave(param)){
                $.post("",{paramString:JSON.stringify(param)},function(data){
                    frame.toast("操作成功（点我返回列表）",frame.manage.goBack);
                },"JSON");
            }
        },goBack:function(){
            window.location.href=window.location.href.substr(0,window.location.href.lastIndexOf("/")+1);
        },beforeSave:function(obj){
            return true;
        }
    };
    $(document).ready(function () {
        $('.datebox').addClass("Wdate");
        $('.datebox').on("focus",function(){
            WdatePicker(this);
        });
    });
})();
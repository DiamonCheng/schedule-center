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
                if ($this.is(":checkbox")){
                	if ($this.is(":checked")){
                		var name=$this.attr("name");
                		var val;
                		if ($this.attr("isentity")!=null){
                			val={};
                			val[$this.attr("valuefield")]=$this.val();
                		}else{
                			val=$this.val();
                		}
                		if (param[name]==null){
                			param[name]=[val];
                		}else{
                			param[name].push(val);
                		}
                	}
                }else{
	                var val=$this.val();
	                if ($this.attr("isentity")!=null){
	                    var obj={};
	                    if (val==null||val.length==0) obj=null;
	                    else obj[$this.attr("valuefield")]=val;
	                    param[$this.attr("name")]=obj;
	                }else{
	                    param[$this.attr("name")]=val;
	                }
                }
            });
            if (frame.manage.beforeSave(param)){
                $.post("",{paramString:JSON.stringify(param)},function(data){
                	if (frame.manage.afterSave()) frame.manage.goBack();
                },"JSON");
            }
        },goBack:function(){
            window.location.href=window.location.href.substr(0,window.location.href.lastIndexOf("/")+1);
        },beforeSave:function(obj){
            return true;
        },afterSave:function(){return true;}
    };
    $(document).ready(function () {
        $('.datebox').addClass("Wdate");
        $('.datebox').on("focus",function(){
            WdatePicker(this);
        });
    });
})();
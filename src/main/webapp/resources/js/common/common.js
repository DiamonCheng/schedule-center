/**
 * 
 */
(function(){
	String.prototype.format = function(args) {
		if (arguments.length > 0) {
			var result = this;
			if (arguments.length == 1 && typeof (args) == "object") {
				for ( var key in args) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			} else {
				for (var i = 0; i < arguments.length; i++) {
					if (arguments[i] == undefined) {
						return "";
					} else {
						var reg = new RegExp("({[" + i + "]})", "g");
						result = result.replace(reg, arguments[i]);
					}
				}
			}
			return result;
		} else {
			return this;
		}
	} 
	window.ctx=(function(){
	    var contextPath =  window.location.pathname.split("/")[1];
	    var basePath =  window.location.protocol+"//"+ window.location.host/*+":"+local.port*/+"/"+contextPath;
	    return basePath;
	})();
	window.escapeHTML=function(str){
		return $("div").text(str).html();
	};
    window.unescapeHTML=function(str){
        return $("div").html(str).text();
    };
	window.submitParams=function(url,method,data){
		var form='<form id="{id}" action="{url}" method="{method}">{inputs}</form>';
		if (method=="get"||method=="GET"||method=="Get")
			for(var e in data){
				data[e]=encodeURIComponent(data[e]);
			}
		else{
            for(var e in data){
                data[e]=data[e].replace(/"/g,"&quot;");
            }
		}
		var inputsTemplate="<input type=\"hidden\" name=\"{name}\" value=\"{value}\"/>";
		var inputs="";
		if (!!data) for(e in data){
			inputs+=(inputsTemplate.format({name:e,value:data[e]}));
		}
		var id=encrypt(new Date());
		form=form.format({id:id,url:url,method:method,inputs:inputs});
        $(form).appendTo($("body"));
		document.getElementById(id).submit();
	};
	$.ajaxSetup({
	    contentType : "application/x-www-form-urlencoded;charset=utf-8",
	    dataType:'JSON',
	    complete : function(XMLHttpRequest, textStatus) {
	        var sessionstatus = XMLHttpRequest.getResponseHeader("SESSION_STATUS"); // 通过XMLHttpRequest取得响应头，sessionstatus，
	        if (sessionstatus == "TIME_OUT") {
	            // 如果超时就处理 ，指定要跳转的页面
	            window.location.href=ctx+"/login";
	        }
        },error:function(xhr, status, e){
			if (xhr.status==500){
				var e= JSON.parse(xhr.responseText);
				console.error(e);
				frame.toastError("操作失败 "+e.message);
			}else{
				console.error("ajax请求出现异常:",xhr);
				console.error("status:",status);
				console.error(e);
				frame.toastError("出现异常，F12查看错误信息。");
			}
		}
	});
})();


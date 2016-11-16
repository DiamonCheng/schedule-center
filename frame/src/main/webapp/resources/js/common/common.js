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
		var local = window.location;  //http://www.w3school.com.cn/js/js_window_location.asp
	    var contextPath = local.pathname.split("/")[1];  // /js/js_window_location.asp
	    var basePath = local.protocol+"//"+local.host/*+":"+local.port*/+"/"+contextPath;
	    return basePath;
	})();
	window.ek="IwSe70Ts2IY9Xc+i";
	window.encrypt=function(word){
		var key = CryptoJS.enc.Utf8.parse(ek);   
        var srcs = CryptoJS.enc.Utf8.parse(word);  
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});  
        return encrypted.toString();  
	};
	window.decrypt=function(word){  
        var key = CryptoJS.enc.Utf8.parse(ek);   
        var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});  
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();  
	};
	window.submitParams=function(url,method,data){
		var form='<form id="{id}" action="{url}" method="{method}">{inputs}</form>';
		if (method=="get"||method=="GET"||method=="Get")
			for(e in data){
				data[e]=encodeURIComponent(data[e]);
			}
		var inputsTemplate="<input type=\"hidden\" name=\"{name}\" value=\"{value}\"/>";
		var inputs="";
		for(e in data){
			inputs+=(inputsTemplate.format({name:e,value:data[e]}));
		}
		var id=encrypt(new Date());
		form=form.format({id:id,url:url,method:method,inputs:inputs});
		document.body.innerHTML+=(form);
		document.getElementById(id).submit();
	};
	window.frame=angular.module("frame",['ngAnimate']);
	frame.controller("decorator",function($scope){
		
	});
})();


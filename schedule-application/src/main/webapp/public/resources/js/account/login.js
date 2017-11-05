/**
 * 
 */
(function(){
	var messageDictionary={
			'':'',
			1:'用户名或密码错误',
			2:'请输入用户名',
			3:'请输入密码',
			4:'用户名过长',
			5:'密码过长'
	};
	var validate=function(){
		var u=$('#userLoginVerification').val();
		if (u.length==0) {
			$('#loginMessage').text(messageDictionary[2]);
			return false;
		}
		if (u.length>31){
			$('#loginMessage').text(messageDictionary[4]);
			return false;
		}
		var p=$('#userPassword').val();
		if (p.length==0) {
			$('#loginMessage').text(messageDictionary[3]);
			return false;
		}
		if (p.length>31) {
			$('#loginMessage').text(messageDictionary[5]);
			return false;
		}
		$('#loginMessage').text('');
		return true;
	};
	var submit=function(){
		if(validate()){
			var u=$('#userLoginVerification').val();
			var p=u+$('#userPassword').val();
			submitParams("","POST",{
				userLoginVerification:u,
				userPassword:encrypt(p)
			});
		}
	};
	$(document).ready(function(){
		var $div=$('#loginMessage');
		$div.text(messageDictionary[$div.attr("message")]);
		$('#userLoginVerification').on("keyup",function(){
//			validate();
		}).on("keydown",function(e){
			var keycode = window.event?e.keyCode:e.which;
			if(keycode==13){
				submit();
			}
		});
		$('#userPassword').on("keyup",function(){
//			validate();
		}).on("keydown",function(e){
			var keycode = window.event?e.keyCode:e.which;
			if(keycode==13){
				submit();
			}
		});
		$('#loginSubmit').on('click',submit);
	});
})();
var submitBtn = function() {
	var nickname = $('#nickname').val();
	var password = "";
	if ($('#password').val() != "")
		password = $('#userName').text() + $('#password').val();
	$.ajax({
		url : '/front/acc/update',
		mothod : 'post',
		dataType : 'json',
		data : {
			nickName : nickname,
			userPassword : encrypt(password)
		},
		success : function(data) {
			if (data.code = "-1") {
				layer.msg(data.message);
			}
			layer.msg(data.message);
			window.location.reload();
		},
		error : function(jqxhr, textStatus, errorThorwn) {
			console.log(jqxhr, textStatus, errorThorwn);
		}
	})
}
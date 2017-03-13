var messageDictionary = {
	'' : '',
	1 : '用户名或密码错误',
	2 : '请输入用户名',
	3 : '请输入密码',
	4 : '用户名过长',
	5 : '密码过长',
	6 : '请输入确认密码',
	7 : '确认密码过长',
	8 : '两次输入的密码不一致'
};

var validate = function() {
	var u = $('#userLoginVerification').val();
	if (u.length == 0) {
		layer.msg(messageDictionary[2]);
		return false;
	}
	if (u.length > 31) {
		layer.msg(messageDictionary[4]);
		return false;
	}
	var p = $('#userPassword').val();
	if (p.length == 0) {
		layer.msg(messageDictionary[3]);
		return false;
	}
	if (p.length > 31) {
		layer.msg(messageDictionary[5]);
		return false;
	}
	$('#loginMessage').text('');
	return true;
};
// 注册校验
var validation = function() {
	var u = $('#userRegisterVerification').val();
	if (u.length == 0) {
		layer.msg(messageDictionary[2]);
		return false;
	}
	if (u.length > 31) {
		layer.msg(messageDictionary[4]);
		return false;
	}
	var p = $('#userRegisterPassword').val();
	if (p.length == 0) {
		layer.msg(messageDictionary[3]);
		return false;
	}
	if (p.length > 31) {
		layer.msg(messageDictionary[5]);
		return false;
	}
	var c = $('#userConfirmRegisterPassword').val();
	if (c.length == 0) {
		layer.msg(messageDictionary[6]);
		return false;
	}
	if (c.length > 31) {
		layer.msg(messageDictionary[7]);
		return false;
	}
	if (p != c) {
		layer.msg(messageDictionary[8])
		return false;
	}
	$('#loginMessage').text('');
	return true;
}
var registSubmit = function() {
	if (validation()) {
		var u = $('#userRegisterVerification').val();
		var p = u + $('#userRegisterPassword').val();
		var c = u + $('#userConfirmRegisterPassword').val();
		$.ajax({
			url : ''+ctx+'/register',
			method : 'post',
			dataType : 'json',
			data : {
				name : u,
				pwd : encrypt(p),
				checkPwd : encrypt(c)
			},
			success : function(result) {
				if (result.detailMessage)
					layer.msg(result.detailMessage);
				if(result.code ==  "00"){
					document.getElementById('register-from').reset();
					$('#formRegister').fadeOut(1);
					$('#formOpen').fadeIn(1000);
					layer.msg("注册成功！请登录")
				}
				console.log(result);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(jqXHR.readyState);
				console.log(jqXHR.statusText);
				console.log(jqXHR.responseText);
			}
		})
		/*
		 * submitParams("/register", "POST", { name : u, pwd : encrypt(p),
		 * checkPwd : encrypt(c) });
		 */
	}
};
var submit = function() {
	if (validate()) {
		var u = $('#userLoginVerification').val();
		var p = u + $('#userPassword').val();
		submitParams("", "POST", {
			userLoginVerification : u,
			userPassword : encrypt(p)
		});
	}
};

// 时间显示注册
function showTime() {
	// 创建Date对象
	var today = new Date();
	// 分别取出年、月、日、时、分、秒
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	// 如果是单个数，则前面补0
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	hours = hours < 10 ? "0" + hours : hours;
	minutes = minutes < 10 ? "0" + minutes : minutes;
	seconds = seconds < 10 ? "0" + seconds : seconds;
	$("#hours").text(hours);
	var temp = $("#seperator").text(":");
	$("#minutes").text(minutes);
	// 构建要输出的字符串
	var str = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes + ":" + seconds;

	// 延时器
	window.setTimeout(showTime, 1000);
}
var flag = false;
function seperator() {
	if (flag) {
		$("#seperator").fadeOut(1000);
		flag = false;
	} else {
		$("#seperator").fadeIn(1000);
		flag = true;
	}
	window.setTimeout(seperator, 1000);
}

// layer注册
$(document).ready(function() {

	var msg = $('#loginMessage').text();
	// layer.msg('23333');
	// message(msg);
	// 表单隐藏
	$('#formOpen').attr("style", "display:none");
	// 时间框隐藏
	$('#timeBox').attr("class", "time");
	showTime();
	seperator();
	// var $div = $('#loginMessage');
	// $div.text(messageDictionary[$div.attr("message")]);
	$('#userLoginVerification').on("keyup", function() {
		// validate();
	}).on("keydown", function(e) {
		var keycode = window.event ? e.keyCode : e.which;
		if (keycode == 13) {
			submit();
		}
	});
	$('#userPassword').on("keyup", function() {
		validate();
	}).on("keydown", function(e) {
		var keycode = window.event ? e.keyCode : e.which;
		if (keycode == 13) {
			submit();
		}
	});
	$('#loginSubmit').on('click', submit);
	// 鼠标经过图片 事件注册
	$('#bgBt1').on('mouseover', function() {
		layer.tips('点击置换背景图片', this, {
			tips : 1
		});
	})
	// 点击图片注册
	$('#bgBt1').on('click', function() {
		layer.photos({
			photos : '#imgContent',
			anim : 4,
			tab : function(pic, layero) {
				$('#bg').attr('style', 'background-image:url(' + '"' + pic.src + '"' + ');background-size:100% 100%;');
				layer.tips('当前图片已设为背景', layero);
			}
		});
	})
	$('#btnOPenSubmit').on('click', function() {
		$('#formOpen').fadeIn(1000, function() {
		});
		$('#timeBox').attr("class", "");
	});
	// 登录->注册事件注册
	$('#userloginRegist').on('click', function() {
		$('#formOpen').fadeOut(1);
		$('#formRegister').fadeIn(1000);
	});
	// 注册->登录事件注册
	$('#registerSubmit').on('click', function() {
		$('#formRegister').fadeOut(1);
		$('#formOpen').fadeIn(1000);
	});
	$('#userRegist').on('click', function() {
		registSubmit();
	});
	$('#userRegist').on('keyup', function() {
	}).on("keydown", function(e) {
		var keycode = window.event ? e.keyCode : e.which;
		if (keycode == 13) {
			registSubmit();
		}
	});
});

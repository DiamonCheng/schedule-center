var delRow = function(){
	var id =$('.selected ').find('td:eq(1)').text();
	$.ajax({
		url:'/admin/menu/img/group/xhr/delete',
		method:'post',
		dataType:'json',
		data:{
			id:id
		},
		success:function(data){
			layer.msg(data.message);
			setTimeout("location.reload()",500);
		},
		error:function(xhr,textStatus,errorThrown){
			layer.msg("系统出现异常!F12查看");
			console.log(xhr);
			console.log(textStatus);
			console.log(errorThrown);
		}
		
	})
}

$(document).ready(function(){
	 //onclick 事件重新注册    替换框架默认删除方法
	// $('ul.tools li:eq(2)').attr("onclick","delRow()");
})

var delRow = function(){
	var id =$('.selected ').find('td:eq(1)').text();
	$.ajax({
		url:'/admin/img/imgUpload/deleteInfo',
		method:'post',
		dataType:'json',
		data:{
			id:id
		},
		success:function(data){
			layer.msg(data.message);
			setTimeout("location.reload()",1000);
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
	var location = (window.location+'').split('/'); // 分割当前路径
	var basePath = location[0]+'//'+location[2];     // http: + // + ip:xx
	// 遍历图片路径 并转换成可访问路径 TO 显示
	 $('table tbody tr').each(function(index){
		 var savePath= $(this).find('td:eq(5)').text();
		 var fileName= $(this).find('td:eq(3)').text();
		 var imgPath = basePath+'/' +savePath+fileName;
		 $(this).find('td:eq(5)').html('<a href='+'"'+imgPath+'"'+'data-fancybox='+'"gallery"'+'>'+'<img src='+'"'+imgPath+'"'+'style='+'"cursor:pointer;height:50px;"'+'/><a/>');
		 //$(this).find('td:eq(4)').html('<img src='+'"'+imgPath+'"'+'data-action='+'"zoom"'+'style='+'"openbig"'+'/>');
	 })
	 // onclick 事件重新注册 替换框架默认删除方法
	 $('ul.tools li:eq(1)').attr("onclick","delRow()");
})
$(document).ready(function() {
	var location = (window.location+'').split('/')
	var basePath = location[0]+'//'+location[2];     // http: + // + ip:xx
	$('table tbody tr').each(function(index) {
		var relativePath = $(this).find('td:eq(3)').text();
		var title= $(this).find('td:eq(2)').text();
		var name= $(this).find('td:eq(4)').text();
		var path = basePath+relativePath+name; 
		$(this).find('td:eq(3)').html('<a class =' + '"video-a"' + 'href=' + '"#"' + 'onclick=' + '""' + '>' + path + '<a/>');
		// $(this).find('td:eq(4)').html('<img
		// src='+'"'+imgPath+'"'+'data-action='+'"zoom"'+'style='+'"openbig"'+'/>');
	})
	$('.video-a').each(function(){
		
	}).on('click',function(){
		var url=$(this).text();
		layer.open({
			type : 2,
			title : 'looking',
			content : url,
			closeBtn : 1,
			maxmin : true,
			area: ['600px', '350px']
		});
	})
})

$(document).ready(function() {
	$('table tbody tr').each(function(index) {
		var v = $(this).find('td:eq(3)').text();
		var t= $(this).find('td:eq(2)').text();
		$(this).find('td:eq(3)').html('<a class =' + '"video-a"' + 'href=' + '"#"' + 'onclick=' + '""' + '>' + v + '<a/>');
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
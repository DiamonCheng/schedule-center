$(document).ready(function(){
	$('.box-body-img').each(function(){
		
	}).on('click',function(){
		var id = $(this).find('input').val();
		window.location.href='/front/album/pic?id='+id;
	})
	
})
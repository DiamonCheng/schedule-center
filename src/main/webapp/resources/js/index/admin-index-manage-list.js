$(document).ready(function(){
	$('table tbody tr').each(function(){
		var tdfour=$(this).find('td:eq(4)').text();
		if(tdfour == '0') $(this).find('td:eq(4)').html('<span style="color:red">禁用</span>');
		if(tdfour == '1') $(this).find('td:eq(4)').html('<span style="color:blue">启用</span>');
	})
	
})
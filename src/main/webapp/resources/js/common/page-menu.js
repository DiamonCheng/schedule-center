/**
 * page-menu
 */
(function(){
	$(document).ready(function(){
		$('#menu ul ul').hide();
		$('#menu ul:has(.current),#menu ul:has(.open)').show();
		$('#menu .menuItem').on('click',function(){
			var $next=$(this).next();
			if ($next.children().length>0)
				if ($next.is(':hidden')){
					$next.slideDown();
				}else{
					$next.slideUp();
				}
			var url=$(this).attr("url");
			if (!!url&&url.length>0){
				submitParams(ctx+url,"GET");
			}
				
		});
	});
})();
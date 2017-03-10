/**
 * page-head
 */
(function(){
	$(document).ready(function(){
		$('#navigations>li').on("click",function(){
			var url=$(this).attr('url');
			if (""!=url) submitParams(ctx+url,"GET");
		});
	});
})();
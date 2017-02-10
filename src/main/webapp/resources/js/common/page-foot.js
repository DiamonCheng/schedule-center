/**
 * page-foot
 */
(function(){
	$(document).ready(function(){
		var $foot=$('#anchorfoot');
		function anchor(e){
			e.removeClass("anchored-foot");
			if (window.innerHeight>=(e.offset().top+e.height())){
				e.addClass("anchored-foot");
	    	}else{
	    		e.removeClass("anchored-foot");
	    	}
		}
		anchor($foot);
		$(window).resize(function(){
			anchor($foot);
		})
	});
})();
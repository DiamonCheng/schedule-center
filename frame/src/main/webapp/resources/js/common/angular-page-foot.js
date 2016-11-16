/**
 * page-foot
 */
(function(){
	function anchorFoot($window,element,$scope){
		var $foot=angular.element(element);
		$foot.removeClass("anchored-foot");
//		console.log($window.innerHeight,element.offsetTop+element.offsetHeight);
		if ($window.innerHeight>=(element.offsetTop+element.offsetHeight)){
			$foot.addClass("anchored-foot");
    	}else{
    		$foot.removeClass("anchored-foot");
    	}
	}
	frame.directive("anchorfoot", function($window) {
		return {
			restrict: 'A',
			controller:"footController",
	        link: function($scope, element) {
	        	anchorFoot($window,element[0]);
	        	angular.element($window).bind('resize', function () {
	        		anchorFoot($window,element[0]);
	        	});
	        }
		};
	});
	frame.controller("footController",function($scope,$window){
		
	});
})();
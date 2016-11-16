/**
 * page-menu
 */
(function(){
	var menuTree={children:[
		{
			id:1,
			name:'菜单一',
			children:[
				{
					id:2,
					name:'功能1',
					link:'#'
				},{
					id:3,
					name:'功能2',
					link:'#'
				}
			]
		},{
			id:4,
			name:'菜单二',
			children:[
				{id:5,name:'功能1'},{id:6,name:'功能2'}
			]
		},{
			id:7,
			name:'菜单三',
			children:[
				{id:8,name:'功能1'},{id:9,name:'功能2'}
			]
		}
	]};
	frame.value("menuTree",menuTree);
	frame.directive('menutree', function () {
		return {
		    restrict:'E',
		    controller:'menuController',
		    template:document.getElementById('menutreetemplate').innerHTML,
		    replace:true,
		    scope: {
		    	currentnode:'=',
		    	current:'=',
		    }
		};
	});
	frame.controller("menuController",function($scope,$rootScope,menuTree,$window){
		$scope.menuTree=menuTree;
		menuTree.opened=true;
		$scope.currentMenuNode={id:-1};
		$scope.click=function(node){
			if (!!node.children){
				node.opened=!node.opened;
			}else{
				$scope.currentnode.id=window.frame.currentMenuId=node.id;
			}
//			console.log(node);
		}
	});
})();
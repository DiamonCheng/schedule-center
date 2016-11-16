/**
 * page-head
 */
(function(){
	console.log("page-head init");
	var navigation=[{text:"首页1",link:'#'},{text:"标签1",link:'#'},{text:"标签2",link:'#'}];
	navigation.click=function(i,$scope){
		$scope.options[0].text='操作2';
		console.log("navigation click "+i);
	}
	var options=[
		{
			click:function($scope){
				console.log(this.text);
			},text:'操作1'
		}
	];
	frame.value("navigation",navigation);
	frame.value("options",options);
	frame.controller("pageHead",function($scope,navigation,options){
		navigation.click_=navigation.click;
		navigation.click=function(i){
			navigation.click_(i,$scope);
		}
		options.click=function(i){
			options[i].click($scope);
		}
		$scope.navigation=navigation;
		$scope.options=options;
	});
})();
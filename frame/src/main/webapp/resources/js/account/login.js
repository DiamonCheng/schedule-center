/**
 * 
 */
(function(){
	var messageDictionary={
			'':'',
			1:'用户名或密码错误',
			2:'请输入用户名',
			3:'请输入密码'
	}
	frame.controller("loginController",function($scope){
		$scope.messageDictionary=messageDictionary;
		$scope.validate=function(){
			if ($scope.loginForm.userLoginVerification.$error.required){
				$scope.message="2";
			}else if($scope.loginForm.userPassword.$error.required){
				$scope.message="3";
			}else{
				$scope.message="";
			}
		}
		$scope.submit=function(){
			if(!$scope.loginForm.userLoginVerification.$error.required&&!$scope.loginForm.userPassword.$error.required){
				$scope.user.userPassword=encrypt($scope.user.userLoginVerification+$scope.user.userPassword);
				submitParams("","POST",$scope.user);
			}
		}
		$scope.regist=function(){
			console.log("REGIST");
		}
		$scope.keydown = function(e){
            var keycode = window.event?e.keyCode:e.which;
            console.log("keydown");
            if(keycode==13){
            	$scope.submit();
            }
        };
//		user.userLoginVerification
//		user.userPassword
	});
})();
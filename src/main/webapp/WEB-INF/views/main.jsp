<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.content{
	height: 500px;
	overflow: hidden;
	/* background-color: #fff; */
	text-align: right;
}
.words{
	display: inline-block;
	font-style: italic;
	position: absolute;
	right: 20px;
	bottom: 0;
	font-size: 24px;
	opacity: 0.8;
	color:#000; 
}
.words>p{

}
.content_cover {
    background-color: #fff;
    opacity: 0.9; 
    width: 100%;
    height: 100%;
    position: absolute;
    /* filter: blur(5px); */
    z-index: -1;
    overflow: hidden;
}

</style>
</head>
<body>
<%-- <img src="${ctx }/resources/img/index2.jpg" style="width: 100%;"/> --%>
<div class="content_cover">
</div>
<div class="content">
	<div class="words">
		<p>Loading...</p>
	</div>
</div>	
</body>
</html>
//提交回复
var submitMsg = function() {
	var content = $('#msgContent').val();
	var albumId = $('#albumId').val();
	if (content == "")
		return false;
	$.ajax({
		url : '/front/msg/save',
		dataType : 'json',
		method : 'post',
		data : {
			content : content,
			albumId : albumId
		},
		success : function(data) {
			if (data.mssage = "成功") {
				layer.msg("留言成功")
				$('#msgContent').val("");
			}
			losdMsg();
			console.log(data);
		}
	})
}
//加载留言 及回复
var losdMsg = function(page) {
	var albumId = $('#albumId').val();
	$('#msgComment').empty();
	$.ajax({
		url : '/front/msg/ansc',
		dataType : 'json',
		method : 'post',
		data : {
			albumId : albumId,
			page : page
		},
		success : function(data) {
			if (data.list.length > 0)
				for (var i = 0; i < data.list.length; i++) {
					$('#msgComment').append('<div style="border:1px solid #ddd;margin-top:40px;"></div>')
					$('#msgComment').append(
							'<blockquote><p>' + data.list[i].content + '</p><footer><cite title="Source Title">'+data.list[i].userName+'&nbsp&nbsp&nbsp&nbsp' + data.list[i].createTime + '</cite></footer></blockquote><div class="panel-article">'+
							'<a  style="cursor: pointer;"class="btn btn-default btn-sm pull-right active"role="button" onclick="clickMsg(' + data.list[i].messageId + ')">回复</a></div>');
					// reply
					for (var j = 0; j < data.list[i].replyVO.length; j++) {
						$('#msgComment').append('<div class="panel-article reply-p"><p class=""style="border-bottom: 1px solid #e3e3e3; ">'+(j+1)+'楼:&nbsp' + data.list[i].replyVO[j].replyContent + '</p><p class="text-left "><strong>reply to :</strong>' + data.list[i].replyVO[j].replyUserName + '-- ' + data.list[i].replyVO[j].replyTime + '</p>');
					}
				}
			loadPage(data.page, data.totalPage, data.totalCount);
			console.log(data);
		}
	})
}
//js分页操作  这里是分页的逻辑所在
var loadPage = function(page, totalPage, totalCount) {
	//加载分页框前 ，先清空分页码。防止分页码重复
	$('.pagenumber').each(function() {
		$(this).remove();
	})
	var albumId = $('#albumId').val();
	$('#totalPage').text(totalPage);
	$('#totalCount').text(totalCount);
	$('#pageNumber').val(page);
	//应显示的页码框数
	var pagingSize = 7;
	var startPage;
	var endPage;
	// 加载首页 尾页框信息
	$('#first a').attr('href', 'javascript:losdMsg(' + 1 + ')');
	$('#last a').attr('href', 'javascript:losdMsg(' + totalPage + ')');
	//上一页，当前页为首页时隐藏按钮
	if (page == "1")
		$('#previouspage').hide();
	else {
		$('#previouspage').show();
		$('#previouspage a').attr('href', 'javascript:losdMsg(' + (page - 1) + ')');

	}
	//下一页，当前页为尾页时隐藏按钮
	if (totalPage == page)
		$('#next').hide();
	else {
		$('#next').show();
		$('#next a').attr('href', 'javascript:losdMsg(' + (page + 1) + ')');
	}
	//当总页数大于应显示的页码框数，显示的页码数的始末位置
	if (totalPage > pagingSize) {
		startPage = page - (pagingSize / 2);
		startPage = startPage < 1 ? 1 : Math.floor(startPage);
		endPage = startPage + pagingSize - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
			startPage = totalPage - pagingSize + 1;
		}
	} else {
		startPage = 1;
		endPage = totalPage;
	}
	//显示的页码数的起始位置不在首页时，显示...
	if (totalPage > pagingSize && startPage != 1) {
		$('#predisabled').show();
	} else {
		$('#predisabled').hide();

	}
	//显示的页码数的终点位置不在尾页时，显示...
	if ((totalPage > pagingSize && endPage != totalPage)) {
		$('#nextdisabled').show();
	} else {
		$('#nextdisabled').hide();

	}
	//渲染页码框，因为是after 所以要倒序渲染
	for (var i = endPage; i > startPage - 1; i--) {
		if (i == page) {
			$('#predisabled').after('<li class=" pagenumber current active"><a href="javascript:void(0)">' + i + '</a></li>');
			continue;
		}
		$('#predisabled').after('<li class="pagenumber" data-page-no="' + i + '"><a href="javascript:losdMsg(' + i + ')">' + i + '</a></li>')
	}

}
var clickMsg = function(messageId) {
	$('#messageId').val(messageId);
	$('#myModal').modal();
}
var submitReply = function() {
	var replyContent = $('#replyContent').val();
	var messageId = $('#messageId').val();
	var userId = $('#userId').val();
	if (replyContent == "")
		return false;
	$.ajax({
		url : '/front/msg/saveReply',
		dataType : 'json',
		method : 'post',
		data : {
			content : replyContent,
			messageId : messageId,
			replyUserId : userId
		},
		success : function(data) {
			if (data.mssage = "成功") {
				layer.msg("回复成功")
				$('#replyContent').val("");
				$('#myModal').modal('hide')
			}
			losdMsg();
			console.log(data);
		}
	})

}
$(document).ready(function() {
	losdMsg();
})
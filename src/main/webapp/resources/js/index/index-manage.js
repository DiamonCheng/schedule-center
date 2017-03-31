function saveInfo() {
	/* 获取编辑器内容 */
	var html = um.getContent();
	var txt = um.getContentTxt();
	var title = $('#edtitle').val();
	var edid = $('#edid').val();
	var edstatus = $('#edstatus').val();
	if (html == "" || title == "")
		return false;
	/* 设置编辑器内容 */
	// um.setContent('2256256264.');
	$.ajax({
		url : '/admin/index/mana/custsaveOrUpdate',
		mothod : 'post',
		dataType : 'json',
		data : {
			'title' : title,
			'content' : html,
			'edid' : edid,
			'edstatus' : edstatus
		},
		success : function(data) {
			if (data.code == "-1") {
				layer.msg(data.message);
			} else {
				layer.msg(data.message);
				window.history.back();
			}
		}/*,
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
			
		}*/

	})
}
$(document).ready(
		function() {
			$('#edcontent').attr('style', 'display:none');
			$('#edid').attr('type', 'hidden');
			// 声明富文本框的初始加载内容
			$('#edcontent').after('<script id="container" name="content" type="text/plain" style="width:800px;height:250px;"> 在这里编辑 内容</script>');
			$('.tools li:first-child').attr('onclick', 'saveInfo()');
			window.um = UM.getEditor('container', {
				/* 传入配置参数,可配参数列表看umeditor.config.js */
				toolbar : [ 'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |', 'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize',
						'| justifyleft justifycenter justifyright justifyjustify |', 'link unlink ', '| horizontal print preview fullscreen', 'drafts', 'formula' ],
			});
			//
			var content = $('#edcontent').val();
			if (content != "") {
				um.setContent(content);
			}

		})
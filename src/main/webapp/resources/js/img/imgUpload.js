	// .after('<input id='+'"imgName"'+'type='+'"hidden"');

	//dropzone 上传组件注册
	
	Dropzone.options.myAwesomeDropzone = {
		init : function() {
			this.on("addedfile", function(file) {
				// alert("Added file." + file);
			});
			this.on("maxfilesreached", function(file) {
				$('#tips').remove();
				$('#my-awesome-dropzone').after('<span id="tips"style="color:red;font-size:14px;float:right"></span>');
				$('#tips').html('一次最多上传20个 文件,超过的部分将不会上传');
				return false;
			});
		},
		maxFiles : 20,
		maxFilesize : 31,
		acceptedFiles : '.jpg,.png,.gif',
		dictDefaultMessage : '点击或者拖动图片到此处!一次最多上传20张',
		method : 'post',
		canceled : function() { // 文件取消上传时调用
			layer.msg("cancel file");
		},
		addRemoveLinks : true,
		/*
		 * dictFileToolBar:function(){ layer.msg('请上传小于10M的图片'); },
		 */
		dictFileTooBig:'图片大小限制为20m,请remove重新上传',
		 dictResponseError:'图片上传失败', 
		dictMaxFilesExceeded :"一次最多上传20个 文件,超过的部分将不会上传",
		/*
		 * sending: function(file, xhr, formData) { // 在每个文件发送时触发
		 * formData.append("filesize", file.size); },
		 */
		success : function(file, data, e) { // 文件已经成功上传触发。file为第一个参数，获取服务器响应作为第二个参数。(这一事件在finished之前触发
			console.log(file);
			console.log(data);
			console.log(e);
			if (data.code == "00") {
				layer.msg("上传成功！");
				var imgOriginalName = $('#imgOriginalName').val();
				var imgName = $('#imgName').val();
				$('#imgName').val(imgName+data.data.newFileName+'|');
				$('#imgOriginalName').val(imgOriginalName+data.data.fileName+'|');
			}else{
				layer.msg(data.message);
			}

		},
		error : function(e1, e2, e3) {
			console.log(e1);
			console.log(e2);
			console.log(e3);
			layer.msg(e2);
		}
	};
	var saveInfo =function(){
		var imgGroupKey= $("select[name='imgGroupKey']").val();
		var imgName = $("#imgName").val();
		var imgOriginalName = $("#imgOriginalName").val();
		if(imgGroupKey == ''){
			layer.msg("请选择一个相册保存！！")
			return false;
		}
		$.ajax({
			url:'/admin/img/imgUpload/addInfo',
			mothod:'post',
			dataType:'json',
			data:{
				imgGroupKey:imgGroupKey,
				imgOriginalName:imgOriginalName,
				imgName:imgName
			},
			success:function(data){
				console.log(data);
				window.history.back();
				layer.msg("保存成功！");
			}
		})
	}
	$(document).ready(function() {
		$('.tools li:first-child').attr('onclick', 'saveInfo()');// 重置保存onclick事件
		$('#imgOriginalName').attr("type", "hidden");
		$('#imgName').attr("type", "hidden");
		$('#imgupload').attr("type", "hidden").after('<form id=' + '"my-awesome-dropzone"' + 'action=' + '"/admin/img/imgUpload/target"' + 'class=' + '"dropzone needsclick dz-clickable">' + '</form>');
	})

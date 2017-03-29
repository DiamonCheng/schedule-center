Dropzone.options.myAwesomeDropzone = {
	init : function() {
		this.on("addedfile", function(file) {
			// alert("Added file." + file);
		});
		this.on("maxfilesreached", function(file) {
			$('#tips').remove();
			$('#my-awesome-dropzone').after('<span id="tips"style="color:red;font-size:14px;float:right"></span>');
			$('#tips').html('一次最多上传1个 .mp4文件,超过的部分将不会上传');
			return false;
		});
	},
	maxFiles : 1,
	maxFilesize : 1000,
	acceptedFiles : '.mp4,.MP4',
	dictDefaultMessage : '点击或者拖动.mp4文件到此处',
	method : 'post',
	canceled : function() { // 文件取消上传时调用
		layer.msg("cancel file");
	},
	addRemoveLinks : true,
	dictFileTooBig : '视频大小限制为1000m,请remove重新上传',
	dictResponseError : '视频上传失败',
	dictMaxFilesExceeded : "一次最多上传1个 .mp4文件,超过的部分将不会上传",
	/*
	 * sending: function(file, xhr, formData) { // 在每个文件发送时触发
	 * formData.append("filesize", file.size); },
	 */
	success : function(file, data, e) { // 文件已经成功上传触发。file为第一个参数，获取服务器响应作为第二个参数。(这一事件在finished之前触发
		console.log(file);
		console.log(data);
		console.log(e);
		if (data.code == "00") {
			layer.msg("上传成功！去点保存");
			$('#videoName').val(data.data.fileName);
			$('#videoNewName').val(data.data.newFileName);
		
		} else {
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
var saveInfo = function() {
	var videoName = $('#videoName').val();
	var videoNewName = $('#videoNewName').val();
	if(videoNewName==""){
		layer.msg("请上传一个文件!")
		return false;
	}
	var description = $('#description').val();
	$.ajax({
		url : '/admin/video/uploads/addInfo',
		method : 'post',
		dataType : 'json',
		data : {
			videoName : videoName,
			videoNewName : videoNewName,
			description : description
		},
		success:function(data){
			console.log(data);
			window.history.back();
			layer.msg("保存成功！");
		}
	})
}
$(document).ready(function() {
	if($('#videoName').val()==""){
		$('.tools li:first-child').attr('onclick', 'saveInfo()');// 重置保存onclick事件
		$('#videoName').attr('readonly', 'true');
		$('#videoNewName').attr('type', 'hidden');
		$('#description').after('<form id="my-awesome-dropzone"action="/admin/video/uploads/target" class="dropzone needsclick dz-clickable"></form>');
	}else{
		$('#videoNewName').attr('type', 'hidden');
	}
	
})
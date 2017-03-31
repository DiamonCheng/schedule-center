$(function() {
	window.um = UM.getEditor('container', {
		/* 传入配置参数,可配参数列表看umeditor.config.js */
		toolbar : [ 'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
				'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize', '| justifyleft justifycenter justifyright justifyjustify |',
				'link unlink | emotion image   | map', '| horizontal print preview fullscreen', 'drafts', 'formula' ],
	});
});
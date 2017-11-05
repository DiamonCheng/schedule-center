/**
 * @author Defferson.Cheng
 */
(function(){
	var zNodes;
	$(document).ready(function(){
		var id=$('input[name=id]').val();
		$.post(ctx+"/role/loadMenuTree",{id:id},function(res){
			zNodes=res;
			$('div.foot-block').before('<div class="row"><label class="form-label">菜单选项</label><div class="ztree-field-wrapper"><div id="roleMenuTree" class="ztree"></div></div></div>');
			window.menuTree=$.fn.zTree.init($("#roleMenuTree"), {
				check:{enable:true,chkboxType:{"Y":"ps","N":"s"}},
				data:{key:{name:"displayName",title:"description",children:'children2'}},
			}, zNodes);
		},"json");
	});
	window.frame.manage.afterSave=function(id){
		$.ajax(ctx+"/role/saveMenuTree",{
			dataType:"json",
			method:"POST",
			success:function(){
				frame.manage.goBack();
			},data:{
				menuList:JSON.stringify(menuTree.getCheckedNodes()),
				id:id
			}
		});
		return false;
	}
})();
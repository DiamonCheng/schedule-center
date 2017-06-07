/**
 * @author Defferson.Cheng
 */
(function(){
	window.submitQuery=function(){
		var param={
			page:$('#page').val(),
			pageSize:$('#pageSize').val(),
            sortEntries:[],
            conditions:[]
		};
		var $sortableTh=$('th[sortIndex]');
		$sortableTh.each(function(){
			param.sortEntries.push({
                field:$(this).attr("field"),
            	fromAlias:$(this).attr("fromAlias"),
            	order:$(this).attr("sortOrder"),
				index:$(this).attr("sortIndex")
			});
		});
		var $conditions=$('.query-panel input,.query-panel select');
        $conditions.each(function(){
            param.conditions.push({
                field:$(this).attr("field"),
                alias:$(this).attr("alias"),
                type:$(this).attr("fieldType"),
                operator:$(this).attr("operator"),
				value:$(this).val()
            });
        });
        param.sortEntries.sort(function(e1,e2){
        	return e1.index-e2.index;
		});
		submitParams("","POST",{paramString:JSON.stringify(param)});
	};
	window.deleteRow=function(){
		var selectedId=$(".data-panel>table>tbody>tr.selected").attr("id");
		//TODO 封装？
		if (!!selectedId){
			$.ajax("delete",{
				data:{id:selectedId},
				method:"POST",
				success:function(data){
					submitQuery();
				}
			});
		}
	};
	window.addRow=function(){
		submitParams("add","GET",{});
	};
	window.manageRow=function(){
        var selectedId=$(".data-panel>table>tbody>tr.selected").attr("id");
        //TODO 封装？
        if (!!selectedId){
            submitParams("edit","GET",{id:selectedId});
        }
	}
})();
$(document).ready(function(){
	$('.data-panel>table').on("click","tbody>tr",function(e){
		$(this).siblings().removeClass("selected");
		$(this).addClass("selected");
	});
	$('gofrist:not(.disabled)').click(function(){
		$('#page').val(1);
		submitQuery();
	});
	$('goprev:not(.disabled)').click(function(){
		$('#page').val($('#page').val()-1);
		submitQuery();
	});
	$('input#jump').change(function(){
		var page=$(this).val();
		page=parseInt(page);
		if (page>0&&page<=parseInt($('#totalPageCount').val())){
			$('#page').val($(this).val());
			submitQuery();
		}else{
			$(this).val($('#page').val());
		}
	});
	$('gonext:not(.disabled)').click(function(){
		$('#page').val(parseInt($('#page').val())+1);
		submitQuery();
	});
	$('golast:not(.disabled)').click(function(){
		$('#page').val($('#totalPageCount').val());
		submitQuery();
	});
	$('th[sortable]').click(function(){
		var sortOrder=$(this).attr("sortOrder");
		if (!sortOrder){
			$(this).attr("sortIndex",-1);
			$(this).attr("sortOrder","DESC");
		}else if (sortOrder=="ASC"){
            $(this).removeAttr("sortOrder");
            $(this).removeAttr("sortIndex");
		}else if (sortOrder=="DESC"){
            $(this).attr("sortIndex",-1);
            $(this).attr("sortOrder","ASC");
		}
		submitQuery();
	});
	$('#submitQuery').on('click',function(){
		$('#page').val(1);
        submitQuery();
	});
	$("#clearQuery").on("click",function(){
		$(".query-panel input,.query-panel select").val("");
        submitQuery();
	});
    $('div.query-panel').on("keydown",function(event){
        if(event.keyCode == 13){
            submitQuery();
        }
    });
    $('.datebox').addClass("Wdate");
    $('.datebox').on("focus",function(){
    	WdatePicker(this);
	});
});

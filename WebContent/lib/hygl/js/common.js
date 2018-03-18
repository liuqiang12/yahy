$(function(){
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FEF6EB");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#f9dfb8");
		}, function(){
			$(this).css("background-color", "#FEF6EB");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#f9dfb8");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 

	/*ie6,7下拉框美化*/
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    }


 
});
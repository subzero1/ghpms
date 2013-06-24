<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript">
function callQuote(){
	quoteRepository('${repository.id }');
	navTab.closeCurrentTab();
}
function tf(o)
{
	var s=$(o).attr("title");
	//alert(s);
	if($.trim($(o).next().text())!=null&&$.trim($(o).next().text())!="")
	{if(s=="+")
	{
		$(o).find("img").eq(0).attr("src","Images/minus.gif");
		$(o).find("img").eq(1).attr("src","Images/folderOpen.gif");
	$(o).next().css("display","inline");

	$(o).next().find("table tr:even").css("display","inline");
	$(o).attr("title","-");
	}
	else 
		{
		$(o).find("img").eq(0).attr("src","Images/plus.gif");
		$(o).next().css("display","none");
		$(o).find("img").eq(1).attr("src","Images/folderClosed.gif");
		$(o).attr("title","+");

		}
	}
}
$(function(){
	$("#test").css("display","none");
	$("#test").find("table").andSelf().attr("border","0");
$("#test td:empty").each(function(i,o){

$(o).parent().prev().find("img").eq(0).attr("src","Images/minus.gif");
$(o).parent().prev().find("img").eq(1).attr("src","Images/file.gif");
$(o).parent().prev().attr("title","-");

});
});
</script>
<style>
.soltest div { overflow:hidden; height:auto;}
.panelFooter{position:absolute;width:94%;}
.panelFooterContent{position:absolute;bottom:10px;width:94%;}
</style>
<div style="float:left; display:block; margin:0px;height:auto;line-height:21px; background:#FFF;width:100%;overflow:auto;">
	<div class=" soltest"  style="width:94%;float:left;margin:10px;overflow-x:visible;overflow-y:auto;" layouth="28">
	<div class="panel">
		<h1>在线帮助</h1>
		<div style="overflow-x:visible;">
			<div class="repository-solution">
				<b>标题：</b>
				<span id="tz06_title">${tz06.title}</span>
			</div>
			<br/>
			<div>
				${tz06.content}
			</div>
		</div>
</div>
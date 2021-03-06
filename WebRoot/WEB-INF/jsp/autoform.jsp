<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<style>
.scroll-follow{width:180px; height:250px; border:0px solid #ccc; position:relative; margin-left:790px; margin-top:0px; margin-right:30px;} 

/* Firefox*/
@-moz-document url-prefix()
{
	#attachBody {position:absolute;float:right;right:15px;}
	#mainBody{position:absolute;width:100%;right:0px;}
}


/*Opera*/
@media all and (min-width: 0px)
{
 #attachBody{position:absolute;float:right;right:15px;}
 #mainBody{position:absolute;width:100%;right:0px;}
} 
</style>

<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h3>&nbsp;</h3>
			<span id="gcmc" style="font-size:15;font-weight:bold;"></span>
			<!-- 辅助操作 -->
			<p style="float: right;text-align:right;">
				<c:forEach var="action" items="${actions}"><a href="${action.url}" >【${action.name}】</a>&nbsp;&nbsp;</c:forEach>
			</p>
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar" id="_flowButton">
			 <c:forEach var="button" items="${buttons}">
			 	<li>
					<a class="${button.picUri }"	href="${button.url}"><span>${button.name}</span></a>
			 	</li>
			<li class="line">line</li>
			 </c:forEach>
		 	<li class="line f-right">line</li>
		 	<c:forEach var="nodeMap" items="${nodeMaps}">
			    <li>
					<a class="edit"	href="${nodeMap.editURL}&project_id=${param.project_id}" title="${nodeMap.title}" target="dialog" rel="record"><span>${nodeMap.node_name}</span></a>
			 	</li>
			<li class="line">line</li>
			 </c:forEach>
		</ul>
	</div>
	<!-- 参数传递共享区 -->
	<div style="display:none" id="_sharestore"></div>
	
	<div id="autoform_body" class="pageContent" layoutH="0">
		
		<!-- 表单主体 -->
		<div id="mainBody" class="pageFormContent" layouth="60">
			<form id="auto_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		    <input type="hidden" name="project_id" id="project_id" value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="<c:out value="${fn:substring(module.form_table,32,fn:length(module.form_table))}"/>.PROJECT_ID"  value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="module_id" id="module_id" value="${param.module_id}"/>
		    <input type="hidden" name="node_id" id="node_id" value="${param.node_id}"/>
		    <input type="hidden" name="doc_id" id="doc_id" value="${param.doc_id}"/>
		    <input type="hidden" name="user_id" id="user_id" value="${user.id}"/>
		    <input type="hidden" name="user_name" id="user_name" value="${user.name}"/>
		    <input type="hidden" name="preOpernode_id" value="<c:out value="${param.preOpernode_id}" default="-1"/>"/>
		    <input type="hidden" name="opernode_id" id="opernode_id" value="<c:out value="${param.opernode_id}" default="-1"/>"/>
			<input type="hidden" name="validate" value="${validate}"/> 
			<input type="hidden" name="effacement" value="${param.effacement}"/>

			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="数据保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="flowForm.do" keep="true"/>
			<jsp:include page="${module.form_url}" flush="true"></jsp:include>
			</form>
				<div class="divider"></div>
		</div>		
	</div>
</div>

<script language="javascript">
	//按照屏幕分配表单右侧信息
	var max_w = $("#autoform_body",navTab.getCurrentPanel()).width();
	var attach_w = navTab._panelBox.width() - 800;
	var attach_h = navTab._panelBox.height() - 63;
	if(max_w <1080){
		//默认展开表单
		var bar = $("#sidebar");
		if(bar.is(":hidden") == false)	$(".toggleCollapse div", bar).click();
		attach_w = attach_w+175;
	}
	$("#attachBody",navTab.getCurrentPanel()).css({"width":attach_w+"px", "position":"relative"});
	$("#attachBody",navTab.getCurrentPanel()).css("margin","-" + attach_h + " 30 30 780");	
	
	$(".panel div",navTab.getCurrentPanel()).height(parseInt((attach_h-150)*0.5));
	$(".panel div",navTab.getCurrentPanel()).first().height(parseInt((attach_h-150)*0.5));
	
	
	//修改表单的tabid
	var  module_id = $("#module_id",navTab.getCurrentPanel()).val();
	var  doc_id = $("#doc_id",navTab.getCurrentPanel()).val();
	
	if(doc_id != ""){
		var tabid = navTab._getTabs().eq(navTab._currentIndex).attr("tabid");
		if(tabid == "autoform"){
			navTab._getTabs().eq(navTab._currentIndex).attr("tabid","autoform"+module_id+doc_id);
		}
	}
	$(function(){
	var gcmc =$("input[name$='GCMC']",navTab.getCurrentPanel());
	var ghbh =$("input[name$='GHBH']",navTab.getCurrentPanel());
	var skbh =$("input[name$='SKBH']",navTab.getCurrentPanel());
	var gcmc_span=$("#gcmc",navTab.getCurrentPanel());
	if(gcmc.val()!=""&&gcmc.size()>0)
	gcmc_span.append("工程名称：【"+gcmc.val()+"】      ");
	if(ghbh.val()!=""&&ghbh.size()>0)
	gcmc_span.append("歌华工程编号：【"+ghbh.val()+"】      ");
	if(skbh.val()!=""&&skbh.size()>0)
	gcmc_span.append("视宽编号：【"+skbh.val()+"】");
});
</script>

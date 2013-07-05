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
			<h1>${module.name}<c:if test="${not empty param.project_id}">【${param.project_id}】</c:if><c:if test="${not empty node}">(${node.name})</c:if></h1>
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
			<c:if test="${not empty param.doc_id && param.module_id != 114}"> 
				<li><a class="edit"	href="jlgt/jlgtView.do?module_id=${param.module_id }&doc_id=${param.doc_id}"  title="交流反馈" target="navTab"><span>反 馈</span></a></li>
				<li class="line">line</li>
			</c:if>
		 	<li class="f-right">
		 		<c:if test="${param.module_id != 101 && param.module_id != 102 && param.module_id != 114}">
					<a class="autonode"	href="showTree.do?project_id=${param.project_id}&doc_id=${param.doc_id}&module_id=${param.module_id}"  title="流程" target="navTab"><span>流程</span></a>
				</c:if>
		 	</li>
		 	<li class="line f-right">line</li>
		 	<c:forEach var="nodeMap" items="${nodeMaps}">
			    <li>
					<a class="edit"	href="${nodeMap.editURL}&project_id=${param.project_id}" title="${nodeMap.title}" target="dialog" rel="record"><span>${nodeMap.title}</span></a>
			 	</li>
			<li class="line">line</li>
			 </c:forEach>
		</ul>
	</div>
	<!-- 参数传递共享区 -->
	<div style="display:none" id="_sharestore"></div>
	
	<div id="autoform_body" class="pageContent" layoutH="0">
		
		<!-- 表单主体 -->
		<div id="mainBody" class="pageFormContent" layouth="80">
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
		</div>		
	
		<!-- 表单附件 -->	
		<div id="attachBody" layoutH="68">
			<c:if test="${param.module_id != 101 && param.module_id != 102}">
			<div class="panel">
				<h1>审批信息 [${fn:length(approve)}]</h1>
				<div  defH="150" style="background-color:#fff;">
					<c:forEach var="item" items="${approve}">
						<p class="approveList">
							<b>${item.user_name }</b> [${item.station}][<fmt:formatDate value="${item.oper_time }" pattern="yyyy-MM-dd HH:mm"/>]&nbsp;&nbsp;
							<font style="color:#666;" <c:if test="${item.check_result != 4}"> color="blue" </c:if> ><b>[${item.result_str }]</b></font>
							<font style="color:#666;">${item.check_idea }</font>
						</p>
					</c:forEach>
				</div>
			</div>
			</c:if>
			<div class="panel">
				<h1>表单附件 [${fn:length(formslave)+fn:length(extslave)+fn:length(uploadslave)}]</h1>
				<div id="slaveDiv" defH="150" style="background-color:#fff;">
					<c:set var="slaves" scope="page" value="0"/>
					<c:forEach var="obj" items="${formslave}">
						<p class="slaveList"><a href="${obj.formurl}">${obj.slave_name}</a></p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
					
					<c:forEach var="obj" items="${extslave}">
						<p class="slaveList"><a href="javascript:return false;" onclick="${obj.formurl}">${obj.slave_name}</a></p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
					
					<c:forEach var="obj" items="${uploadslave}">
						<p class="slaveList">
							<a href="download.do?slave_id=${obj.slave_id}">${obj.slave_name}</a>
							<c:if test="${fn:contains(obj.slave_name, 'xls') || fn:contains(obj.slave_name, 'doc') || fn:contains(obj.slave_name, 'pdf')}">
							<a href="show_slave.do?slave_id=${obj.slave_id}" target="dialog" width="1000" height="600" title="在线预览"><b>在线预览</b></a>
							</c:if>
							<c:if test="${obj.rw == 'w'}"><a href="javascript:del_slave('${obj.slave_id}','${slaves }');"> <img src="Images/icon10.gif" alt="删除" align="absmiddle"/></a></c:if>
						</p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
				</div>
			</div>
			<!--
			<div id="jlfkTitle" class="panel">
				<h1>交流反馈 [${fn:length(jlfk)}]</h1>
				<div id="jlfkdiv" defH="150" style="background-color:#fff;">
					<c:forEach var="jlfk" items="${jlfk}">
						<p class="jlfkList">
							<b>${jlfk.name}</b> [<fmt:formatDate value="${jlfk.time}" pattern="yyyy-MM-dd HH:mm"/>] <c:if test="${jlfk.slave_id != null}"><a href="download.do?slave_id=${jlfk.slave_id}"><img src="Images/slave.gif" alt="下载附件"/></a></c:if><br/>
							${jlfk.yj}&nbsp;<c:if test="${jlfk.rw == 'w'}"><a href="javascript:del_send('${jlfk.project_id}','${param.project_id}','${jlfk.slave_id}','${param.module_id }','${param.user_id }','${param.doc_id }');"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
						</p>
					</c:forEach>
				</div>
			</div>
			 -->
		</div>
		<div  style="clear:both"></div>  
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
</script>

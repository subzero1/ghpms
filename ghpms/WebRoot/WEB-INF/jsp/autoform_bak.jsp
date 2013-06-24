<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

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
			 	<li>
					<a class="edit"	href="dispath.do?url=form/jlfk.jsp?project_id=${param.project_id }&module_id=${param.module_id }&user_id=${param.user_id }&doc_id=${param.doc_id }" rel="jlfk" title="交流反馈" target="dialog"><span>反 馈</span></a>
			 	</li>
			<li class="line">line</li>
		</ul>
	</div>
	<!-- 参数传递共享区 -->
	<div style="display:none" id="_sharestore"></div>
	
	<div class="pageContent" layoutH="0">
		
		<!-- 表单主体 -->
		<div class="pageFormContent" layouth="270" style="text-align:center;">
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
		<div class="accordion" style="width:100%;float:left;margin:0px;">
			<div class="accordionHeader">
				<h2><span>icon</span>审批信息 [${fn:length(approve)}]</h2>
			</div>
			<div class="accordionContent" style="height:100px;">
				<c:forEach var="item" items="${approve}">
					<p class="approveList">
						<b>${item.user_name }</b> [${item.station}][<fmt:formatDate value="${item.oper_time }" pattern="yyyy-MM-dd HH:mm"/>]&nbsp;&nbsp;
						<font style="color:#666;" <c:if test="${item.check_result != 4}"> color="blue" </c:if> ><b>[${item.result_str }]</b></font>
						<font style="color:#666;">${item.check_idea }</font>
					</p>
				</c:forEach>
			</div>
			<div class="accordionHeader">
				<h2><span>icon</span>表单附件 [${fn:length(formslave)+fn:length(extslave)+fn:length(uploadslave)}]</h2>
			</div>
			<div class="accordionContent" id="slaveDiv" style="height:100px;">
				<c:set var="slaves" scope="page" value="0"/>
				<c:forEach var="obj" items="${formslave}">
					<p class="slaveList"><a href="${obj.formurl}">${obj.slave_name}</a></p>
					<c:set var="slaves" scope="page" value="${slaves+1 }"/>
				</c:forEach>
				
				<c:forEach var="obj" items="${extslave}">
					<p class="slaveList"><a href="#" onclick="${obj.formurl}">${obj.slave_name}</a></p>
					<c:set var="slaves" scope="page" value="${slaves+1 }"/>
				</c:forEach>
				
				<c:forEach var="obj" items="${uploadslave}">
					<p class="slaveList">
						<a href="download.do?slave_id=${obj.slave_id}">${obj.slave_name}</a>
						<a href="show_slave.do?slave_id=${obj.slave_id}" target="dialog" width="1000" height="600" title="在线预览">在线预览</a>
						<c:if test="${not empty obj.slave_remark}"><span style="color:#888">&nbsp;(${obj.slave_remark})</span></c:if>
						<c:if test="${obj.rw == 'w'}"><a href="javascript:del_slave('${obj.slave_id}','${slaves }');"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
					</p>
					<c:set var="slaves" scope="page" value="${slaves+1 }"/>
				</c:forEach>
			</div>
			<div id="jlfkTitle" class="accordionHeader">
				<h2><span>icon</span>交流反馈 [${fn:length(jlfk)}]</h2>
			</div>
			<div class="accordionContent" id="jlfkdiv" style="height:100px;">
				<c:forEach var="jlfk" items="${jlfk}">
					<p class="jlfkList">
						<b>${jlfk.name}</b> [<fmt:formatDate value="${jlfk.time}" pattern="yyyy-MM-dd HH:mm"/>] <c:if test="${jlfk.slave_id != null}"><a href="download.do?slave_id=${jlfk.slave_id}"><img src="Images/slave.gif" alt="下载附件"/></a></c:if><br/>
						${jlfk.yj}&nbsp;<c:if test="${jlfk.rw == 'w'}"><a href="javascript:del_send('${jlfk.project_id}','${param.project_id}','${jlfk.slave_id}','${param.module_id }','${param.user_id }','${param.doc_id }');"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
					</p>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script language="javascript">
	//默认展开表单
	var bar = $("#sidebar");
	if(bar.is(":hidden") == false)	$(".toggleCollapse div", bar).click();
	
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

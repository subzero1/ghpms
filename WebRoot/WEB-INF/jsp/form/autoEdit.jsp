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
	<div id="autoform_body" class="pageContent" layoutH="0">
		
		<!-- 表单主体 -->
		<div id="mainBody" class="pageFormContent" layouth="80">
			<form id="auto_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		    <input type="hidden" name="project_id" id="project_id" value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="<c:out value="${fn:substring(module.form_table,32,fn:length(module.form_table))}"/>.PROJECT_ID"  value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="module_id" id="module_id" value="${param.module_id}"/>
		    <input type="hidden" name="node_id" id="node_id" value="${param.node_id}"/>
		    <input type="hidden" name="user_id" id="user_id" value="${user.id}"/>
		    <input type="hidden" name="user_name" id="user_name" value="${user.name}"/>
		    <input type="hidden" name="opernode_id" id="opernode_id" value="<c:out value="${param.opernode_id}" default="-1"/>"/>

			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="数据保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="flowForm.do" keep="true"/>
			
			<jsp:include page="${param.node_id}.jsp" flush="true"></jsp:include>
			
			</form>
		</div>	 
		 
	</div>
</div> 
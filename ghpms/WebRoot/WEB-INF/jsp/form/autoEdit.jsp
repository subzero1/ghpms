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
<script type="text/javascript">
 function recordCallBack(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		setTimeout(function(){$.pdialog.closeCurrent();}, 100);
		var $project_id=$("#project_id",$.pdialog.getCurrent());
		var $module_id=$("#module_id",$.pdialog.getCurrent());
		var $user_id=$("#user_id",$.pdialog.getCurrent());
		var $node_id=$("#node_id",$.pdialog.getCurrent());
		if($project_id.val()!=""){ 
		   docReload();
		}else{
		
		//回调函数,取得表的最大ID
		 $.ajax({
			type: 'POST',
			url:'gcsj/getProjectID.do',
			data:{module_id:$module_id.val()},
			dataType:"html",
			cache: false,
			success: function openNavTab(data){
				navTab.openTab('gcsj', 'gh/openForm.do?project_id='+data+'&module_id='+$module_id.val()+'&user_id='+$user_id.val()+'&node_id='+$node_id.val(), {title:'工程设计信息'});
				},
			error: DWZ.ajaxError
		});
		}
		
	}
}


</script>
<div class="page">
	<div id="autoform_body" class="pageContent" layoutH="0">
		
		<!-- 表单主体 -->
		<form id="auto_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, recordCallBack);">
		<div id="mainBody2" class="pageFormContent" layouth="80">
		    <input type="hidden" name="project_id" id="project_id" value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="<c:out value="${fn:substring(module.form_table,32,fn:length(module.form_table))}"/>.PROJECT_ID"  value="<c:out value="${param.project_id}" default="-1"/>"/>
		    <input type="hidden" name="module_id" id="module_id" value="${param.module_id}"/>
		    <input type="hidden" name="node_id" id="node_id" value="${param.node_id}"/>
		    <input type="hidden" name="user_id" id="user_id" value="${user.id}"/>
		    <input type="hidden" name="user_name" id="user_name" value="${user.name}"/>

			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="数据保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="closeCurrent" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="" keep="true"/>
			<jsp:include page="${param.node_id}.jsp" flush="true"></jsp:include>
		</div>	 
		<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		 </form>
	</div>
</div> 

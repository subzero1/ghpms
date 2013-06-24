<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" media="screen,projection" type="text/css" href="css/show_tree.css" />

<script language="javascript">
function do_click(node_id,opernode_id,project_id,module_id,doc_id,node_status){
	if(node_status != -1){
		$.pdialog.open('nodeInfo.do?node_id=' + opernode_id,'','节点信息',{mask:true, width:740,height:350});
	}
}
function showtree(value){
	navTab.reload($('#treeform').attr("action")+value);
}
</script>

<div class="page">
	<div class="panelBar">
		<ul class="toolBar">	
		 <c:if test="${not empty moduleList}">
		 	<li>
				<a class="edit"	href="showFormTree.do?project_id=${param.project_id }" target="navTab" rel="showFormTree" title="工程主流程"><span>主流程</span></a>
		 	</li>
			<li class="line">line</li>
		 	<li>
				<a class="edit"	href="tree/spyj.do?project_id=${param.project_id }&module_id=${param.module_id }" target="dialog" width="700" height="400" title="查看表单审批明细"><span>审批明细</span></a>
		 	</li>
			<li class="line">line</li>			 
		 </c:if>
		 <c:if test="${not empty flowList}">
		 	<li>
				<a class="edit"	href="showFormTree.do?flow_id=${param.flow_id }" target="navTab" rel="showFormTree" title="主流程"><span>主流程</span></a>
		 	</li>
			<li class="line">line</li>
		 </c:if>
		 	<li><a class="helponline"	href="javascript:enterHelp('xtlc')"><span>在线帮助</span></a></li>
			<li class="line">line</li>
		 </ul>
  	</div>	
	<div class="pageContent" style="width:100%" layoutH="28">
	
		<div id="tree-title">
	  		<br/><h1 style="font-size:14px;">${title}</h1>
	  	</div>
		<div id="tree">
			<c:forEach var="obj" items="${line_list}">
				<div style='width:1px;height:1px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;background-color:${obj.color };'>
				</div>
			</c:forEach>
			<c:forEach var="obj" items="${word_list}">
				<div style='width:10px;height:10px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }'>
					${obj.name }
				</div>
			</c:forEach>
			<c:forEach var="obj" items="${node_list}">
				<div style='width:${obj.width }px;height:${obj.height }px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }' onclick='javascript:do_click(${obj.param })'>
					${obj.name }
				</div>
			</c:forEach>
		</div>
		<div id="form-select">
			<form id="treeform" action="showTree.do" method="post">
				<!-- 表单选择 对于项目的,列出当前项目的所有表单,对于系统流程显示的,列出系统中配置的所有表单流程 -->
				
				<c:if test="${not empty moduleList}">
					<select name="" onchange="showtree(this.value)">
						<c:forEach var="obj" items="${moduleList}">
							<option value="${obj.value }"
							<c:if test="${obj.module_id == param.module_id && obj.doc_id == param.doc_id }">
								selected
							</c:if>
							>${obj.text }</option>
						</c:forEach>
					</select>	
				</c:if>
				<p>&nbsp;</p>
				<c:if test="${not empty moduleList}">
					<c:forEach var="obj" items="${moduleList}">
						<c:if test="${obj.doc_id == param.doc_id }">
							<p><input type="button" name="" value="打开表单" onclick="javascript:navTab.openTab('autoform', 'flowForm.do${obj.value}', {title:'表单'});" class="red-message1"/></p>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${not empty flowList}">			
					<select name="flow_id" onchange="javascript:document.form1.submit();">
						<c:forEach var="obj" items="${flowList}">
							<option value="${obj.id }"
							<c:if test="${obj.id == param.flow_id }">
								selected
							</c:if>
							>${obj.name }</option>
						</c:forEach>
					</select>
				</c:if>
				</form>	
		</div>
		<div id="color" style="float:left;">  
			节点颜色标识：
			<ul>
				<li> *.未走</li>
				<li> 0.待办</li>
				<li> 1.新建</li>
				<li> 2.在办</li>
				<li> 3.待复</li>
				<li> 4.回复同意</li>
				<li> 5.回复修改</li>
				<li> 6.回复不同意</li>
				<li> 7.回复不发表意见</li>
				<li> 8.办结</li>
			</ul>
		</div>
		
	</div>
</div>


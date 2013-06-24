<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
//计算页面上待办列表允许高度
$(function(){
	var bo = $(".pageContent", $("#needWorkList_ui"));
	if(bo){
		//计算列表高度
		var temp_h = navTab._panelBox.height() - 306;
		bo.height(temp_h);
	}
	
});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<h1 style="line-height:26px;">待办列表</h1>
			</li>
			<li class="line">line</li>
			<c:forEach items="${newFormList}" var="formItem">
				<c:if test="${fn:contains(formItem.url,'module_id=109')}">
				<li style="float:right;">
					<a class="add"	href="${formItem.url }" target="navTab" rel="autoform" title="新建需求"><span>新建需求</span></a>&nbsp;&nbsp;&nbsp;
				</li>
				</c:if>
	     	</c:forEach> 
		</ul>
	</div>
	<div class="pageContent"  style="overflow-y:auto;overflow-x:hidden;height:300px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th width="25"></th>
					<th width="35"></th>
					<th width="100">表单类别</th>
					<th width="">名称</th>
					<th width="140">处理时间</th>
					<th width="80">节点</th>
					<th width="45">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="0" end ="${numPerPage-1}">
					<tr>
						<td>
							<c:if test="${docList[i][0] != null}">	
								<a href="javascript:openFlowForm('{project_id:${docList[i][0].project_id},doc_id:${docList[i][0].doc_id},module_id:${docList[i][0].module_id},opernode_id:${docList[i][0].opernode_id},node_id:${docList[i][0].node_id},user_id:${docList[i][0].user_id}}');" title="表单"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>
							<c:if test="${docList[i][0] != null}">	
								<a href="showTree.do?project_id=${docList[i][0].project_id}&doc_id=${docList[i][0].doc_id}&module_id=${docList[i][0].module_id}" target="navTab" rel="showTree" title="流程图"><img border="0" src="Images/node.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>${docList[i][0].module_name}</td>
						<td>
							<a href="javascript:openFlowForm('{project_id:${docList[i][0].project_id},doc_id:${docList[i][0].doc_id},module_id:${docList[i][0].module_id},opernode_id:${docList[i][0].opernode_id},node_id:${docList[i][0].node_id},user_id:${docList[i][0].user_id}}');" title="${docList[i][1].xmmc}">${docList[i][1].xmmc}</a>
						</td>
						<td>${docList[i][0].oper_time}</td>
						<td>${docList[i][0].near_user}</td>
						<td>${docList[i][0].near_status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>共${totalCount}条 </span>
			</div>
		</div>
		
	</div>
</div>
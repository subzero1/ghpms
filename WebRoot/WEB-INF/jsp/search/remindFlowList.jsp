<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" action="" method="post">
			<input type="hidden" name="pageNum" value="${pageNum}" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" name="orderField" value="${orderField}" />
			<input type="hidden" name="orderDirection" value="${orderDirection}" />
			<div class="searchBar">
				<div class="subBar">
				</div>
			</div>
		</form>	
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th width="20" align=center></th>
					<th width="40" align=center></th>
					<th width="120" align=center>表单编号</th>
					<th width="120" align=center>表单名称</th>
					<th width="280" align=center>项目名称</th>
					<th width="180" align=center>计划完成时间</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cswd_list}" var="o">
				<tr>
					<td><a href="javascript:openFlowForm('{project_id:${o.project_id},doc_id:${o.doc_id},module_id:${o.module_id},opernode_id:${o.opernode_id},node_id:${o.node_id},user_id:${o.user_id}}');" title="表单"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a></td>
					<td style="text-align:center;"><a href="showTree.do?project_id=${o.project_id}&doc_id=${o.doc_id}&module_id=${o.module_id}" target="navTab" rel="showTree" title="流程图"><img border="0" src="Images/node.gif" style="cursor:pointer"/></a></td>
					<td><a href="javascript:openFlowForm('{project_id:${o.project_id},doc_id:${o.doc_id},module_id:${o.module_id},opernode_id:${o.opernode_id},node_id:${o.node_id},user_id:${o.user_id}}');" title="表单">${o.bdbh }</a></td>
					<td>${o.bdmc }</td>
					<td style="text-align: left"><a href="javascript:openFlowForm('{project_id:${o.project_id},doc_id:${o.doc_id},module_id:${o.module_id},opernode_id:${o.opernode_id},node_id:${o.node_id},user_id:${o.user_id}}');" title="表单">${o.xmmc }</a></td>
					<td><fmt:formatDate value="${o.jhwcsj }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>
			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>
		</div>
	</div>
</div>


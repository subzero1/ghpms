<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="search/userLogin.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
	 
	</div>
	<div class="pageContent">
		 
		<table class="table" width="100%" layouth="75" >
			<thead>
				<tr> 
				<th style="width:20px;"></th>
					<th style="width: 100px;">表单类别</th> 
					<th style="width: 100px;">歌华编号</th>
					<th style="width: 100px;">视宽编号</th>
					<th style="width: 255px;">工程名称</th>
					<th style="width: 100px;">计划完成时间</th>
					<th style="width: 100px;">实际完成时间</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${outDateMapList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
					<td>
										<a href="gh/openForm.do?project_id=${obj.project.id}&module_id=${obj.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.project.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer;margin:4px 1px;"/></a>
									</td>
						<td>${obj.module_name }</td>
						<td>${obj.project.ghbh}</td>
						<td>${obj.project.skbh}</td>
						<td>${obj.project.gcmc}</td>
						<td>${obj.project.jhwcsj}</td>
						<td>${obj.project.sjwcsj}</td>
						<td></td>
					</tr>
				</c:forEach>
				<c:if test="${offset<20}">
				<c:forEach begin="${offset}" end="20">
				<tr>
					<c:forEach begin="1" end="8">
				      <td></td>
				    </c:forEach>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages"></div>
		</div>
	</div>
</div>
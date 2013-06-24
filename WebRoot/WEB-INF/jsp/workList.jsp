<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
$(function(){
	$("#keyword",navTab.getCurrentPanel()).keyup(function(e){
		if (e.which == 13){
			$("#searchButton",navTab.getCurrentPanel()).click();
		}
	});
});
</script>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="workState" value="${param.workState}">
	<input type="hidden" name="module_id" value="${param.module_id}">
	<input type="hidden" name="keyWord" value="${param.keyWord}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form	action="workList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<input type="hidden" name="workState" value="${param.workState}">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>${param.orderDirection}选择表单：
						<select name="module_id" >
							<option value="-1">
								---请选择表单---
							</option>
							<c:forEach var="module" items="${moduleList}" >
								<c:choose>
									<c:when test="${param.module_id == module[0] }">
										<option selected value="${module[0] }">${module[1] }</option>
									</c:when>
									<c:otherwise>
										<option value="${module[0] }">${module[1] }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
							</td>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyWord" value="${param.keyWord}" type="text" size="25" /></td>
						<td>
							<c:if test="${param.workState == 5}">
								<netsky:htmlSelect name="year" objectForOption="yearList"  value="${showYear}" htmlClass="td-select"/>
							</c:if>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'workList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:forEach items="${newFormList}" var="formItem">
					<c:if test="${fn:contains(formItem.url,'module_id=109')}">
					<li>
						<a class="add" href="${formItem.url }" target="navTab" rel="autoform" title="新建需求"><span>新建需求</span></a>
					</li>
					<li class="line">line</li>
					</c:if>
		     	</c:forEach> 
		     	<!--
				<li>
					<a class="batchmodify"	href="dispath.do?url=form/batchUpdateProject.jsp" target="dialog" rel="batchUpdateProject" width="400" height="200"><span>批量修改</span></a>
				</li>
				<li class="line">line</li>
				 -->
				<li>
					<a class="setting"	href="workListCfg.do" target="dialog" rel="workListConfig" width="500" height="370"><span>设置</span></a>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th style="width:20px;"></th>
					<th style="width:20px;"></th>
					<!-- 初始化标题名称 -->
					<c:forEach var="col" items="${docColsList}">
						<th  <c:if test="${col.name != 'jfmc'}"> style="width:${col.width}px;"</c:if> orderField="${col.object_name}.${col.name}">${col.comments}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>				
				<c:forEach var="doc" items="${docList}">
					<tr>
						<td>
							<c:if test="${doc[cols] != null }">
								<a href="javascript:openFlowForm('{project_id:${doc[cols].project_id},doc_id:${doc[cols].doc_id},module_id:${doc[cols].module_id},opernode_id:${doc[cols].opernode_id},node_id:${doc[cols].node_id},user_id:${doc[cols].user_id}}');" title="表单[${doc[cols].project_id}]"><img border="0" src="Images/form.gif" style="cursor:pointer;margin:4px 1px;"/></a>
							</c:if>				
						</td>
						<td>
							<c:if test="${doc[cols] != null}">								
								<a href="showTree.do?project_id=${doc[cols].project_id}&doc_id=${doc[cols].doc_id}&module_id=${doc[cols].module_id}" target="navTab" rel="showTree" title="流程图[${doc[cols].project_id}]"><img border="0" src="Images/autonode.png" style="cursor:pointer;margin:4px 1px;"/></a>
							</c:if>		
						</td>
						
						<c:forEach var="j" begin="0" end ="${cols-1}">
							<c:choose>
								<c:when test="${'名称' == docColsList[j].comments}">
									<td style="text-align:${docColsList[j].align};"><a href="javascript:openFlowForm('{project_id:${doc[cols].project_id},doc_id:${doc[cols].doc_id},module_id:${doc[cols].module_id},opernode_id:${doc[cols].opernode_id},node_id:${doc[cols].node_id},user_id:${doc[cols].user_id}}');">${doc[j]}</a></td>
								</c:when>
								<c:otherwise><td style="text-align:${docColsList[j].align};">${doc[j]}</td></c:otherwise>
							</c:choose>
						</c:forEach>
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

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>
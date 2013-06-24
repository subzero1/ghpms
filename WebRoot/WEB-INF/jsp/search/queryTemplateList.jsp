<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
 	var $keyword=$("#keyword",navTab.getCurrentPanel());
 	$keyword.keydown(function(e){
 		if(e.which==13)
 		   $(this).closest("form").submit();
 	});
</script>
<form id="pagerForm" method="post" action="search/queryTemplate.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="search/queryTemplate.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'search/queryTemplate.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		 
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr> 
					<th style="width: 30px;" orderField="name">模板名称</th>
					<th style="width: 30px;" orderField="type">类型</th>
					<th style="width: 30px;" orderField="user_name">创建人</th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${objectList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="xm_id" rel="${obj[0].id}"> 
						<td>
						<a href="search/condition.do?module_id=${obj[0].module_id }&template_id=${obj[0].id }<c:if test="${obj[0].type==1 }">&type=report</c:if>"
						 target="dialog" width="810" height="360" title="${fn:substring(obj[1],0,(fn:length(obj[1])-1)) }<c:if test="${obj[0].type==1}">统计</c:if><c:if test="${obj[0].type==2}">查询</c:if>:${obj[0].name }">${obj[0].name }&nbsp;</td>
						<td>${fn:substring(obj[1],0,(fn:length(obj[1])-1)) }<c:if test="${obj[0].type==1}">统计</c:if><c:if test="${obj[0].type==2}">查询</c:if></td>
						<td>${obj[0].user_name }</td> 
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
						<td></td> 
					</tr>
				</c:forEach>
				</c:if>
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
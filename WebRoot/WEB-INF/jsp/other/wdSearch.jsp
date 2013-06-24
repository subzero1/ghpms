<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%
	request.setAttribute("replace", "<span style='font-weight:bolder'>"+request.getParameter("keyword")+"</span>");
%>
<script type="text/javascript">
	$(function(){
		$("#keyword").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="other/ggList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'other/wdSearch.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="helponline"	href="javascript:enterHelp('xtgg')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 200px;" orderField="file_name">文档名称</th>
					<th style="width: 200px;" orderField="remark">关键字</th>
					<th style="width: 200px;" orderField="contents">文件目录</th>
					<th style="width: 80px;" orderField="user_name">上传人</th>
					<th style="width: 120px;" orderField="ftp_date">上传时间</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${wdList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td title="${obj.file_name }"><a href="download.do?slave_id=${obj.id }">${fn:replace(obj.file_name,param.keyword,replace) }</a></td>
						<td title="${obj.remark }">${fn:replace(obj.remark,param.keyword,replace) }</td>
						<td title="${obj.contents }">${obj.contents }</td>
						<td>${obj.user_name }</td>
						<td><fmt:formatDate value="${obj.ftp_date}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
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

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>

		</div>
	</div>
</div>
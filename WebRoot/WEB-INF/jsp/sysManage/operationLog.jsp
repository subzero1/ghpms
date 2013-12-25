<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
</script>
<form id="pagerForm" method="post" action="sysManage/operationLog.do">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<div style="font-size:14px;font-weight:bold;">${message_title }</div>
				
				<div class="subBar">
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layouth="88" style="width: 1366px;">
			<thead>
			<tr>
				<th width="30"  align="center">序号</th>
				<th width="80" orderField="start_time" align="center">起始时间</th>
				<th width="90" orderField="end_time" align="center">结束时间</th>	
				<th width="90" orderField="tatal_time" align="right">总时间</th>	
				<th width="150" orderField="request_url">请求地址</th>
				<th width="90" orderField="user_name">用户名</th>
				<th width="90" orderField="user_address">IP地址</th>
				<th width="120" orderField="session_id">会话标识</th>
			</tr>
			</thead>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="obj" items="${list}">
				<tr target="form_param" rel="${message_list['te04.id']}">
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
					<td>${offset}</td>
					<td><fmt:formatDate value="${obj.start_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${obj.end_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${obj.tatal_time}</td>
					<td>${obj.request_url}</td>
					<td>${obj.user_name}</td>
					<td>${obj.user_address}</td>
					<td>${obj.session_id}</td>
				</tr>
			</c:forEach>
			<c:forEach begin="1" end="${numPerPage-offset}">
				<tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
					<td>&nbsp;</td>	
					<td>&nbsp;</td>
					<td>&nbsp;</td>	
					<td>&nbsp;</td>	
					<td>&nbsp;</td>
				</tr>	
			</c:forEach>
		</table>	
		<div class="panelBar">
			<div class="pages">
				<span></span>
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
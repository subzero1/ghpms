<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" action="" method="post">
			<input type="hidden" name="keywords" value="${param.keywords}" />
			<input type="hidden" name="pageNum" value="${pageNum}" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" name="orderField" value="${orderField}" />
			<input type="hidden" name="orderDirection" value="${orderDirection}" />
			<input type="hidden" name="slave_type" value="${type }" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td><span>登录日期：</span><input type="text" name="startdate" value="${startdate}" class="date" format="yyyy-MM-dd" yearstart="-50" yearend="50" size="12"/>
							至&nbsp;<input type="text" name="enddate" value="${enddate}" class="date" format="yyyy-MM-dd" yearstart="-50" yearend="50" size="12"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'search/LoginLog.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>	
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="exportexcel" href="search/LoginLog.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th width="40">序号</th>
					<th width="180">登录时间</th>
					<th width="150">IP地址</th>
					<th width="180">登出时间</th>
					<th width="150">系统信息</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" scope="page" value="0"></c:set>
				<c:forEach items="${tz03_list}" var="tz03">
				<c:set var="offset" scope="page" value="${offset+1}"></c:set>
				<tr>
					<td style="text-align:center;">${offset }</td>
					<td><fmt:formatDate value="${tz03.login_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${tz03.login_ip }</td>
					<td><fmt:formatDate value="${tz03.logout_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${tz03.systeminfo }</td>
					<td>&nbsp;</td>
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


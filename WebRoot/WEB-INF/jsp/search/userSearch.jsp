<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<div class="pageHeader">
	<form id="pagerForm" action="search/userSearch.do" method="post">
		<input type="hidden" name="keywords" value="${param.keywords}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
		<input type="hidden" name="slave_type" value="${type }" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>姓名/工号：
					<input type="text" name="search_name" size="10"
						value="${search_name}"
						onkeypress="javascript:if(event.keyCode==13){searchs();}"
						class="td-input-nowidth" />
					<select name="search_dept" id="search_dept">
						<option value="">
							--部门--
						</option>
						<c:forEach var="dept_list" items="${dept_list}">
							<option value="${dept_list.id}"
								<c:if test="${search_dept == dept_list.id }">selected</c:if>>
								(${dept_list.area_name})${dept_list.name}
							</option>
						</c:forEach>
					</select></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'search/userSearch.do',navTabSearch);">检 索</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>	
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
					<li>
						<a class="exportexcel"	href="search/userSearch.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a>
					</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
						<th width="30" >序号</th>
						<th width="80" orderField='login_id'>工号</th>
						<th width="80" orderField='ta03.name'>姓名</th>
						<th width="120" orderField='fix_tel'>办公电话</th>
						<th width="120" orderField='mobile_tel'>移动电话</th>
						<th orderField='email'>邮箱</th>
						<th width="40" orderField='sex'>性别</th>
						<th width="200" orderField='ta01.name'>所属部门</th>
					</tr>
					</thead><tbody>
					<c:set var="offset" scope="page" value="0" />
					<c:forEach var="user_list" items="${user_list}">
						<tr>
							<c:set var="offset" scope="page" value="${offset + 1}" />
							<td style="text-align: center">${offset }</td>
							<td style="text-align: center">${user_list.login_id}</td>
							<td style="text-align: left" title="查看权限">
								<a href="search/user_tail.do?user_id=${user.id}" target="dialog" width="680" height="350" title="权限查看">${user_list.name}</a>
							</td>
							<td style="text-align: left">${user_list.tel}</td>
							<td style="text-align: left">${user_list.mobile}</td>
							<td style="text-align: left">${user_list.email}</td>
							<td style="text-align: center">${user_list.sex}</td>
							<td style="text-align: left">${user_list.deptname}</td>
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



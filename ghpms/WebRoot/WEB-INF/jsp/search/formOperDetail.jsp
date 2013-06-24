<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<form id="pagerForm" action="" method="post">
		<input type="hidden" name="keywords" value="${param.keywords}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
		<input type="hidden" name="slave_type" value="${type }" />
		<div class="searchBar">
		</div>
	</form>	
	<div class="pageContent">
		<div class="panelBar">
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
			<th width="30">序号</th>
			<th width="80" style="cursor:hand">工号</th>
			<th width="80" style="cursor:hand">姓名</th>
			<th style="cursor:hand">文档名称</th>
			<th width="80" style="cursor:hand">待处理</th>
			<th width="80" style="cursor:hand">未处理</th>
			<th width="80" style="cursor:hand">已处理</th>
			<th width="80">总计</th>
			<th width="80">完成率</th>
			<th >&nbsp;</th>
				</tr>
			</thead>
			<tbody>				
				<c:set var="offset" scope="page" value="0"/>
		<c:forEach var="obj" items="${oper_list}">
			<tr>
			    <c:set var="offset" scope="page" value="${offset + 1}"/>
			    <td style="text-align:center">${offset}</td>
				<td style="text-align:left">${obj.login_id }</td>
				<td style="text-align:left">${obj.user_name}</td>
				<td style="text-align:left">${obj.module_name }</td>
				<td style="text-align:center">${obj.dbs }</td>
				<td style="text-align:center">${obj.zbs }</td>
				<td style="text-align:center">${obj.bjs }</td>
				<td style="text-align:center">${obj.hj }</td>
				<td class="t-right"><fmt:formatNumber value="${obj.bjs/obj.hj*100 }" pattern="##0.00"/>%</td>
				<td>&nbsp;</td>
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

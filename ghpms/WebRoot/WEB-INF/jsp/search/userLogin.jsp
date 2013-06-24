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
		<form action="search/userLogin.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						登录时间：<input id="dlsj1" class="date" pattern="yyyy-MM-dd" name="dlsj1" value="${param.dlsj1}" type="text" size="10" />  至  <input id="dlsj2" name="dlsj2" class="date" pattern="yyyy-MM-dd" value="${param.dlsj2}" type="text" size="10" />&nbsp;&nbsp;&nbsp;&nbsp;
						<netsky:htmlSelect id="tjlb" name="tjlb" objectForOption="tjlbList"  valueForOption="" showForOption="" value="${param.tjlb}" htmlClass="td-select"/></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'search/userLogin.do',navTabSearch);">检 索</button></div>
						<!-- 
						<div class="buttonContent"><button type="button" onClick="javascript:alert('开发中')">Excel导出</button></div></div></li>
						 -->
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		 
		<table class="table" width="100%" layouth="116">
			<thead>
				<tr> 
					<th style="width: 40px;">序号</th>
					<th style="width: 250px;">名称</th>
					<th style="width:60px;">登录数</th> 
					<th></th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${dlList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
						<td style="text-align:center">${offset }</td>
						<td>${obj[0] }</td>
						<td><a href="aux/userLoginDetail.do?login_name=${obj[0] }&tjlb=${param.tjlb}" target="navTab" ref="userLoginDetail" title="${obj[0] }[${obj[1] }]">${obj[1] }</a></td> 
						<td>&nbsp;</td> 
					</tr>
				</c:forEach>
				
				<c:forEach var="obj2" items="${dlList2}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
						<td style="text-align:center">${offset }</td>
						<td>${obj2 }</td>
						<td>0</td> 
						<td>&nbsp;</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages"></div>
		</div>
	</div>
</div>
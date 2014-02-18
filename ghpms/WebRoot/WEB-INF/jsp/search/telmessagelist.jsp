<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page">
<form id="pagerForm"
			action="telmessagelist.do" method="post">
	<div class="pageHeader">
		
			<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
			<div class="searchBar">
				<table class="searchContent">
				<tr>
					<td>发送时间：</td>
					<td><input type="text" size="10" name="start_date" id="start_date"	value="${start_date}" class="date"  yearstart="-50" yearend="50"/>
						&nbsp;至&nbsp;<input type="text" size="10" name="end_date" id="end_date"	value="${end_date}" class="date" yearstart="-50" yearend="50"/></td>
					<td>发送人：</td>
					<td><input type="text" name="fsr" size="20"	value="${fsr}" class="td-input-nowidth"/></td>
					<td>接收人：</td>
					<td><input type="text" name="jsr" size="20"	value="${jsr}" class="td-input-nowidth"/></td>
				</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button id="testbutton" type="button" onClick="javascript:searchOrExcelExport(this,'',navTabSearch);">搜 索</button></div></div></li>
					</ul>
				</div>
			</div>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="delete" href="deltelmessage.do?id={message_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<!-- 初始化标题名称 -->
				<th width="90" orderField="jsr" style="cursor:hand">收件人</th>
				<th width="70" orderField="title" style="cursor:hand">主题</th>
				<th width="90" orderField="fsr" style="cursor:hand">发件人</th>
				<th width="120" orderField="fssj" style="cursor:hand">发送时间</th>
				<th orderField="content" style="cursor:hand">内容</th>
				<th width="50">发送状态</th>
				</tr>
			</thead>
			<tbody>			
			<c:set var="offset" scope="page" value="0"/>	
				<c:forEach var="message_list" items="${telmessage_list}">
				<tr target="message_id" rel="${message_list['id']}">
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
					<td class="t-center">${message_list.jsr}</td>
					<td class="t-left">${message_list["title"]}</td>
					<td class="t-center">${message_list["fsr"]}</td>
					<td class="t-center"><fmt:formatDate value="${message_list['fssj']}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td class="t-left" title="${message_list['content']}">${message_list["content"]}</td>
					<td class="t-center">${message_list["state"] }</td>
				</tr>
			</c:forEach>
			<c:forEach begin="1" end="${numPerPage-offset}">
				<tr>	
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
	</form>
</div>



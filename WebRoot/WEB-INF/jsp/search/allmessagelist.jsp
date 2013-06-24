<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page">
	<div class="pageHeader">
	<form id="pagerForm" action="" method="post">
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>发送时间：</td>
					<td>
						<input type="text" name="startdate" size="15" value="${param.startdate}" class="date" format="yyyy-MM-dd HH:mm:ss" yearstart="-50" yearend="50"/>
						~<input type="text" name="enddate" size="15" value="${param.enddate}" class="date" format="yyyy-MM-dd HH:mm:ss" yearstart="-50" yearend="50"/>
					</td>
					<td>关键字：</td>
					<td><input type="text" name="keyWord" value="${param.keyWord }"/></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'allmessagelist.do',navTabSearch);">检 索</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>	
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="edit"	href="MessageRead.do?message_id={message_id}&messageState=${param.messageState}" target="dialog" rel="messageRead" title="查看内部邮件" width="500" height="300"><span>查看</span></a>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
			<tr>
				<th width="30"><img src='Images/message/email.png'></img></th>
				<th width="80">收件人</th>
				<th>主题</th>
				<th width="60">发件人</th>
				<th width="60">工号</th>
				<th width="90">手机</th>	
				<th width="140">发送时间</th>
				<th width="40">附件</th>
			</tr></thead><tbody>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="message_list" items="${message_list}">
				<tr target="message_id" rel="${message_list['te04.id']}">
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
					<td>
						<c:if test="${message_list['te04.read_flag']==0}"><img src='Images/message/email.png' title="未读邮件"/></c:if>
						<c:if test="${message_list['te04.read_flag']=='1'&&message_list['te04.repeat_flag']==1}"><img src='Images/message/forwarded.gif' title="需要回复"/></c:if>
						<c:if test="${message_list['te04.read_flag']=='1'&&message_list['te04.repeat_flag']==2}"><img src='Images/message/replied.gif' title="已经回复"/></c:if>
						<c:if test="${message_list['te04.read_flag']==1&&message_list['te04.repeat_flag']==0}"><img src='Images/message/email_open.png' title="已读邮件"/></c:if>
					</td>
					<td>${message_list["te04.reader_name"]}</td>
					<td>${message_list["te04.title"]}</td>
					<td>${message_list["ta03.name"]}</td>
					<td>${message_list["ta03.login_id"]}</td>
					<td>${message_list["ta03.mobile_tel"]}</td>
					<td><fmt:formatDate value="${message_list['te04.send_date']}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><c:if test="${message_list['te04.fujian_flag']>0}"><img src='Images/message/icon09.gif' title="有${message_list['te04.fujian_flag']}个附件"></img></c:if></td>		
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
			</c:forEach></tbody>
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

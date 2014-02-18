<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	function goRead(id){
		var url = 'MessageRead.do?message_id='+id+'&messageState=${param.messageState}';
		$.pdialog.open(url,'xtdxx', '内部邮件',{mask:true, width:600, height:320});
	}

</script>
<form id="pagerForm" method="post" action="MessageList.do?messageState=${param.messageState}">
	<input type="hidden" name="keyWord" value="${param.keyWord}" />
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
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'MessageList.do?messageState=2',navTabSearch);">草稿箱</button></div></div></li>
					</ul>
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'MessageList.do?messageState=3',navTabSearch);">发件箱</button></div></div></li>
					</ul>
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'MessageList.do?messageState=1',navTabSearch);">收件箱</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
				<li>
					<a class="add"	href="MessageWrite3.do?messageState=${param.messageState}" target="navTab"  rel="messageWrite" title="撰写内部邮件"><span>新建</span></a>
				</li>
				<li class="line">line</li>
				<li>
					<a class="delete" href="ajaxMessageDelete.do?messageState=${param.messageState}&message_id={form_param}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a>
				</li>
				<c:if test="${param.messageState==4}">
				<li class="line">line</li>
				<li>
					<a class="delete" href="emptyRecycleBin.do" target="ajaxTodo" title="确认清空吗？"><span>清空</span></a>
				</li>
				</c:if>
				<li class="line">line</li> 
				<!-- 
				<li class="line">line</li>
				<li><a class="helponline"	href="javascript:enterHelp('xtxx')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
				 -->
			</ul>
		</div>
		<table class="table" width="100%" layouth="128">
			<thead>
			<tr>
				<th width="30" title="未读邮件" orderField="te04.read_flag"><img src='Images/message/email.png' style="margin:4px;"></img></th>
				<c:choose>
					<c:when test="${param.messageState==3||param.messageState==2}">
					<th width="60" orderField="te04.reader_name" style="cursor:hand">收件人</th>
					</c:when>
					<c:otherwise>
					<th width="60" orderField="ta03.name" style="cursor:hand">发件人</th>
					</c:otherwise>
				</c:choose>
				<th width="60" orderField="ta03.login_id">工号</th>
				<th width="90" orderField="ta03.mobile_tel">手机号</th>	
				<th orderField="te04.title">主题</th>
				<th width="40" orderField="te04.fujian_flag">附件</th>
				<th width="120" orderField="te04.send_date">时间</th>
			</tr>
			</thead>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="message_list" items="${message_list}">
				<tr target="form_param" rel="${message_list['te04.id']}">
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
					<td class="t-center">
						<c:if test="${param.messageState==1||param.messageState==4}">
							<c:if test="${message_list['te11.read_flag']==0}"><img src='Images/message/email.png' title="未读邮件"/></c:if>
							<c:if test="${message_list['te11.read_flag']=='1'&&message_list['te04.repeat_flag']==1}"><img src='Images/message/forwarded.gif' title="需要回复"/></c:if>
							<c:if test="${message_list['te11.read_flag']=='1'&&message_list['te04.repeat_flag']==2}"><img src='Images/message/replied.gif' title="已经回复"/></c:if>
							<c:if test="${message_list['te11.read_flag']==1&&message_list['te04.repeat_flag']==0}"><img src='Images/message/email_open.png' title="已读邮件"/></c:if>
					     </c:if>
					     <c:if test="${param.messageState==3}">
					     	<c:if test="${message_list['te11.read_flag']==0}"><img src="Images/online_time.gif" title="尚未读"/></c:if>
							<c:if test="${message_list['te11.read_flag']==1}"><img src="Images/online_ok.gif" title="已接收"/></c:if>
					     </c:if>
					</td>
					<td>${message_list["ta03.name"]}</td>
					<td>${message_list["ta03.login_id"]}</td>
					<td>${message_list["ta03.mobile_tel"]}</td>
					<td title="${message_list['te04.title']}"><a class="edit"	href="MessageRead.do?message_id=${message_list['te04.id']}&messageState=${param.messageState}" target="dialog" rel="messageRead" title="查看内部邮件" width="600" height="371">${message_list["te04.title"]}</a></td>
					<td><c:if test="${message_list['te04.fujian_flag']>0}"><img src='Images/message/icon09.gif' title="有${message_list['te04.fujian_flag']}个附件"></img></c:if></td>
					<td><fmt:formatDate value="${message_list['te04.send_date']}" pattern="yyyy-MM-dd HH:mm"/></td>
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
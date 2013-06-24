<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	request.setAttribute("x_n", "\n");
%>

<script type="text/javascript">
	function delMessage(){
		var msg = showMsgBox('确定要删除吗？','ok',null,2);
		Event.observe(msg.buttOK, 'click', function(){
			messageRead.action = '/ghpms/MessageDelete.do';
			messageRead.submit();
			parent.location.href = parent.location.href;	
		});
		Event.observe(msg.buttCL, 'click', function(){
			return;
		});
	}function setrepeat(another){
		var url='RepeatMessage.do?message_id=${message_id }&goanother='+another;
		navTab.openTab('messageWrite',url, {title:'内部邮件'});
		$.pdialog.close("messageRead");
		
	}
</script>



<div class="page" style="overflow-x: hidden">
	<div class="pageContent" >
		<form name="messageRead" id="messageRead" action="" method="post">
		<input type="hidden" id="messageState" name="messageState" value="${messageState }"/>
		<input type="hidden" id="message_id" name="message_id" value="${message_id }"/>
			<table width="550" id="read" layoutH="38" class="read" style="border-collapse:collapse;" border="0" cellspacing="0" cellpadding="0">
				<c:choose>
					<c:when test="${messageState == 3}">
						<tr>
							<th width="70">发件人：</th>
							<td width="100" class="t-left">${user.name}</td>
							<th width="80">电&nbsp;&nbsp;&nbsp;&nbsp;话：</th>
							<td >${user.mobile_tel }</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th width="70">发件人：</th>
							<td width="100">${singlemessage["ta03.name"]}</td>
							<th width="80">电&nbsp;&nbsp;&nbsp;&nbsp;话：</th>
							<td>${singlemessage["ta03.mobile_tel"]}&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${singlemessage['te04.repeat_flag']==1}"><span style="color:red">【需要回复】</span></c:if>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
						<tr>
							<th>主&nbsp;&nbsp;&nbsp;&nbsp;题：</th>
							<td colspan="3">${singlemessage["te04.title"]}</td>
						</tr>
				<c:choose>
					<c:when test="${messageState == 3}">
						<tr>
							<th>收件人：</th>
							<td >${singlemessage["te04.reader_name"]}</td>
							<th >发送时间：</th>
							<td class="t-left"><fmt:formatDate value="${singlemessage['te04.send_date']}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>收件人：</th>
							<td class="t-left">${singlemessage["te04.reader_name"]}</td>
							<th >发送时间：</th>
							<td class="t-left"><fmt:formatDate value="${singlemessage['te04.send_date']}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:otherwise>
				</c:choose>
						<tr >
							<th rowsplan="${rowsnum}">附&nbsp;&nbsp;&nbsp;&nbsp;件：</th>
							
							<td colspan="3" style="line-height:150%">
							<c:forEach var="fj_list" items="${fj_list}">
								<a href="show_slave.do?slave_id=${fj_list.id}" target="dialog" width="1000" height="600" title="在线预览">[<font color="red">${fj_list["file_name"] }</font></a>  
								<a href="download.do?slave_id=${fj_list.id}"><b>下载</b>]</a>&nbsp;<br/>
							</c:forEach>
							</td>
						</tr>
						<tr>
						<th>内&nbsp;&nbsp;&nbsp;&nbsp;容：</th>
							<td colspan="3" style="vertical-align:top;height:120px;line-height:150%">
								<span style="line-height:18px;">${fn:replace(singlemessage["te04.content"], x_n, '<br />')}</span>
							</td>
						</tr>
			</table>
			<div class="formBar">
				<ul>
					<c:if test="${messageState != 3}">
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:setrepeat('zhuanfa');">转 发</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:setrepeat('huifu');">回 复</button></div></div></li>
					</c:if>	
					<li><div class="button"><div class="buttonContent"><button type="button" class="close">关 闭</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>
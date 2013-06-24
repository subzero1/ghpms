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
						<tr >
							<td colspan="3" style="line-height:150%">
							<c:forEach var="a" items="${messageReaderList}">
							<c:if test="${a[1]==1&&message_list['te04.repeat_flag']==0}"><img src='Images/message/email_open.png' title="已读邮件"/></c:if>
					     	<c:if test="${a[1]==0}"><img src="Images/online_time.gif" title="尚未读"/></c:if>
							<c:if test="${a[1]==1}"><img src="Images/online_ok.gif" title="已接收"/></c:if>
								
								${a[0] }&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
							</c:forEach>
							</td>
						</tr>
						<tr> 
			</table>
		</form>
	</div>

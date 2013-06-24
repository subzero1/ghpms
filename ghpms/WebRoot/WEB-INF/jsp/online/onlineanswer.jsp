<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />

<%
	request.setAttribute("x_n", "\n");
%>

<c:choose>
<c:when test="${online.role_id=='15'}">
	<c:set var="web_title" scope="page" value="问题查看"/>
	<c:set var="new_desc" scope="page" value="回复"/>
</c:when>
<c:when test="${online.role_id=='17'}">
	<c:set var="web_title" scope="page" value="系统权限申请"/>
	<c:set var="new_desc" scope="page" value="回复"/>
</c:when>
<c:when test="${online.role_id=='601'}">
	<c:set var="web_title" scope="page" value="系统公告"/>
	<c:set var="new_desc" scope="page" value="评论"/>
</c:when>
<c:when test="${online.role_id=='304'}">
	<c:set var="web_title" scope="page" value="考核培训"/>
	<c:set var="new_desc" scope="page" value="回复"/>
</c:when>
<c:otherwise>
	<c:set var="web_title" scope="page" value="在线信息"/>
	<c:set var="new_desc" scope="page" value="评论"/>
</c:otherwise>
</c:choose>

<script type="text/javascript">
	var n=2;
	function zj(){
		n++;
		var file_id="the_file"+n;
		var oRow = ${"bd"}.insertRow(); 
 				oCell = oRow.insertCell(); 
				oRow.insertCell(0).innerHTML = "<th width='15%'>&nbsp;</th>";
		oRow.insertCell(1).innerHTML = "<td width='85%'>\
										<input type='file' name='"+file_id+"' id='"+file_id+"' size='30'/>\
										<input type='hidden' name='Te01_slave.ID' value=''/>\
										<input type='hidden' name='Te01_slave.MODULE_ID' value='9001'/>\
										<input type='hidden' name='Te01_slave.USER_ID' value='${user.id}'/>\
										<input type='hidden' name='Te01_slave.USER_NAME' value='${user.name}'/>\
										<input type='hidden' name='Te01_slave.FTP_DATE' value='<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>'/>\
										<input type='hidden' name='Te01_slave.FILE_NAME' value='1'/>\
										<input type='hidden' name='Te01_slave.EXT_NAME' value='1'/>\
										<input type='hidden' name='Te01_slave.FTP_URL' value='1'/>\
										<input type='hidden' name='Te01_slave.SLAVE_TYPE' value='其他附件' />\
									</td>"
	}
	
		
		    $(function(){
		    	$("#submitbutton").click(function(){
			    	$.ajax({
			    		type:'POST',
			    		async: false,
						url:'onlinechuliajax.do',
						data:'id=${online.id}&chuli=${xhf}',
						success: function(msg){
						if (!$.trim(msg)=="true"){
							alertMsg.info('<font color=red><b>〖 错误 〗</b></font> <br/>&nbsp;回复失败！请重新回复或联系管理员');
							} else {
								$("#form1").submit();
							}
						}
			    	})
		    	})
		    })
</script>
<body>
<div id="online">
	<form method="post" id="form1" action="onlineAjaxSave.do" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Te03_online"/>
		<input type="hidden" name="slaveTable" value="com.netsky.base.dataObjects.Te01_slave"/>
		<input type="hidden" name="slaveType" value="ftp"/>
		<input type="hidden" name="slaveFatherTables" value="Te03_online,ID,DOC_ID/Te03_online,ID,project_id"/>
		<input type="hidden" name="Te03_online.ID" value=""/>
		<input type="hidden" name="save_act" value="true"/>
		<input type="hidden" name="Te03_online.STATUS" value="${xhf }"/>
		<input type="hidden" name="role_id" id="role_id" value="${online.role_id }"/>
		<input type="hidden" name="_navTabId" value="_current"/>
		<input type="hidden" name="_forwardUrl" value="OnLineanswer.do?aq_id=${param.aq_id }"/>
		<input type="hidden" name="_callbackType" value="forward"/>
		<div style="width:730px;" layoutH="205">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div class="online-title">${online.title }</div>
						<div style="height:30px;color:#888;">发表人：${online.aq_name }&nbsp;[${online.login_id}/${online.aq_tel }]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						发表时间：<fmt:formatDate value="${online.aq_date }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						阅读次数：${online.ydcs }</div>						
						<div>						
							<div style="line-height:18px;">${fn:replace(online.content, x_n, '<br />')}</div>
							<br/>
							<c:if test="${not empty list_fj}">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="online-fj">
								<tr>
									<%int n=1; %>
									<c:forEach var="fj_list" items="${list_fj}">
										<c:if test="${fj_list.doc_id==online.id}">
											<%if(n==1){%><td width="50" valign="top" class="online-msg">附 件：</td><%n++;} else{%>&nbsp;<%} %>
										</c:if>
									</c:forEach>
									<td class="t-left">
										<c:forEach var="fj_list" items="${list_fj}">
											<c:if test="${fj_list.doc_id==online.id}">
												<a href="download.do?slave_id=${fj_list.id}">${fj_list.file_name}</a>
											</c:if>
										</c:forEach>
									</td>
								</tr>
							</table>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
			<table id="hf" width="97%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="hf-table">
				<caption>以下共有【${hfs }】人${new_desc }：</caption>
				<c:set var="offset" scope="page" value="0"/>
				<c:forEach var="list_hf" items="${list_hf}">
					<tr><c:set var="offset" scope="page" value="${offset + 3}"/>
						<td class="hf-msg"> ${list_hf.aq_name} &nbsp;[${list_hf.login_id }] <fmt:formatDate value="${list_hf.aq_date }" pattern="yyyy-MM-dd HH:mm"/>
						<c:if test="${((online.role_id==15 || online.role_id==17) && delrole=='yes') || (online.role_id==601 && ggrole=='yes')}">
						<a href="javascript:ajaxTodo('onlineanswerdelajax.do?up_id=${online.id }&id=${list_hf.id }',dialogAjaxDone);"><img src="Images/icon10.gif" alt="删除"/></a>
						</c:if>
						</td>
					</tr>
					<tr>
						<td>${fn:replace(list_hf.content, x_n, '<br />')}</td>
					</tr>
					<tr>
						<th>
							<%int n1=1; %>
							<c:forEach var="fj_list" items="${list_fj}">
								<c:if test="${fj_list.doc_id==list_hf.id}">
									<%if(n1==1){%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件：<% n1++;} %>
									<a href="download.do?slave_id=${fj_list.id}">${fj_list.file_name}</a>
								</c:if>
							</c:forEach>
						</th>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div style="width:720px;height:165px;float:left;padding:5px !important;display:inline; overflow-x:hidden;overflow-y:auto;">
			<table id="bd" name="bd" width="97%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="post-table">
				<caption>发表${new_desc}<a name="post" href=""/></caption>
				<tr>
					<th colspan="2">
						<textarea class="td-textarea" style="width:99%;height:80px;" type="text" name="Te03_online.CONTENT" id="Te03_online.CONTENT"></textarea>
						<input type="hidden" name="Te03_online.UP_ID" id="Te03_online.UP_ID" value="${online.id }"/>
						<input type="hidden" name="Te03_online.LOGIN_ID" id="Te03_online.LOGIN_ID" value="${user.login_id }"/>
						<input type="hidden" name="Te03_online.AQ_NAME" id="Te03_online.AQ_NAME" value="${user.name }"/>
						<input type="hidden" name="Te03_online.TITLE" id="Te03_online.TITLE" value="${new_desc}: ${online.title }"/>
						<input type="hidden" name="Te03_online.ROLE_ID" id="Te03_online.ROLE_ID" value="${online.role_id }"/>
						<input type="hidden" name="Te03_online.AQ_DATE" id="Te03_online.AQ_DATE" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/>"/>
						<input type="hidden" name="Te03_online.AQ_IP" id="Te03_online.AQ_IP" value="${ip}"/>
						<input type="hidden" name="Te03_online.FLAG" id="Te03_online.FLAG" value="0"/>
					</th>
				</tr>
				<tr>
					<th width="60">附 件<img src="Images/add.png" onclick="javascript:zj();" style="cursor:pointer" title="添加附件" />：</th>
					<td><input type="file" name="the_file1" id="the_file1" size="30"/>
						<input type="hidden" name="Te01_slave.ID" value=""/>
						<input type="hidden" name="Te01_slave.MODULE_ID" value="9001"/>
						<input type="hidden" name="Te01_slave.USER_ID" value="${user.id}"/>
						<input type="hidden" name="Te01_slave.USER_NAME" value="${user.name}"/>
						<input type="hidden" name="Te01_slave.FTP_DATE" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
						<input type="hidden" name="Te01_slave.FILE_NAME" value="1"/>
						<input type="hidden" name="Te01_slave.EXT_NAME" value="1"/>
						<input type="hidden" name="Te01_slave.FTP_URL" value="1"/>
						<input type="hidden" name="Te01_slave.SLAVE_TYPE" value="其他附件" />
					</td>		
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button"  id="submitbutton">提交${new_desc}</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>




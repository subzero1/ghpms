<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<c:choose>
	<c:when test="${wtlx=='15'}">
		<c:set var="web_title" scope="page" value="在线提问" />
		<c:set var="name_desc" scope="page" value="提问人" />
	</c:when>
	<c:when test="${wtlx=='17'}">
		<c:set var="web_title" scope="page" value="系统权限申请" />
		<c:set var="name_desc" scope="page" value="申请人" />
	</c:when>
	<c:when test="${wtlx=='601'}">
		<c:set var="web_title" scope="page" value="系统公告发布" />
		<c:set var="name_desc" scope="page" value="发布人" />
	</c:when>
	<c:when test="${wtlx=='304'}">
		<c:set var="web_title" scope="page" value="考核培训信息发布" />
		<c:set var="new_desc" scope="page" value="发布人" />
	</c:when>
	<c:otherwise>
		<c:set var="web_title" scope="page" value="在线信息" />
		<c:set var="name_desc" scope="page" value="姓 名" />
	</c:otherwise>
</c:choose>


<script type="text/javascript">
	function appenRow(){
		var rowi = $("#slave_table").find("tr").length + 1;
		var appenRowStr = "<tr><td width='90'> </td><td>\
						<input type='file' name='the_file"+rowi+"' id='the_file"+rowi+"' size='30'/>\
						<input type='hidden' name='Te01_slave.ID' value=''/>\
						<input type='hidden' name='Te01_slave.MODULE_ID' value='9001'/>\
						<input type='hidden' name='Te01_slave.USER_ID' value='${user.id}'/>\
						<input type='hidden' name='Te01_slave.USER_NAME' value='${user.name}'/>\
						<input type='hidden' name='Te01_slave.FTP_DATE' value='<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>'/>\
						<input type='hidden' name='Te01_slave.FILE_NAME' value='1'/>\
						<input type='hidden' name='Te01_slave.EXT_NAME' value='1'/>\
						<input type='hidden' name='Te01_slave.FTP_URL' value='1'/>\
						<input type='hidden' name='Te01_slave.SLAVE_TYPE' value='其他附件' />\
					</td></tr>";
		$("#slave_table").append(appenRowStr);
		
	}

	$(function(){
    	$("#submitbutton").click(function(){
    		if ($("#dxtz").attr("checked")==true) {
    		var data = "title="+$("#title").val();
	    	$.ajax({
	    		type:'POST',
	    		async: false,
				url:'dxtz.do',
				data:data,
				success: function(msg){
				if (!$.trim(msg)=="success"){
					alertMsg.info('<font color=red><b>〖 错误 〗</b></font> <br/>&nbsp;短信通知管理员失败');
					} 
				$("#form1").submit();
				setTimeout("navTab.openTab('onlineList', 'OnLineList.do?wtlx=15', {title:'在线提问'})",1000);
				}
	    	})
	    	}else {
	    		$("#form1").submit();
	    		setTimeout("navTab.openTab('onlineList', 'OnLineList.do?wtlx=15', {title:'在线提问'})",1000);
	    	}
    	})
    })
	
</script>

<div class="page">
	<div class="pageContent">

	<form method="post" id="form1" action="onlineAjaxSave.do" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<input type="hidden" name="Te03_online.ID" value="${online_id }" />
			<input type="hidden" name="Te03_online.YDCS" value="<c:choose><c:when test="${empty ydcs}">0</c:when><c:otherwise>${ydcs}</c:otherwise></c:choose>" />
			<input type="hidden" name="Te03_online.AQ_ID" id="Te03_online.AQ_ID" value="${user.id }" />
			<input type="hidden" name="Te03_online.AQ_TEL" id="Te03_online.AQ_TEL" value="${user.mobile_tel}" />
			<input type="hidden" name="Te03_online.ROLE_ID" id="Te03_online.ROLE_ID" value="${wtlx }" />
			<input type="hidden" name="Te03_online.UP_ID" id="Te03_online.UP_ID" value="" />
			<input type="hidden" name="Te03_online.LOGIN_ID" id="Te03_online.LOGIN_ID" value="${user.login_id }" />
			<input type="hidden" name="Te03_online.AQ_DATE" id="Te03_online.AQ_DATE"
				value="<c:choose><c:when test="${empty param.online_id}"><fmt:formatDate value="${now}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></c:when><c:otherwise>${aq_date}</c:otherwise></c:choose>" />
			<input type="hidden" name="Te03_online.AQ_IP" id="Te03_online.AQ_IP" value="${ip }" />
			
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>${name_desc}：</label>
					<c:choose>
						<c:when test="${user!=null}">
							<input type="text" class="td-input-nowidth" style="width:300px;" name="aq_name" id="aq_name" value="${user.name }" readonly style="width:100;" />
							<input type="hidden" name="Te03_online.AQ_NAME" id="Te03_online.AQ_NAME" value="${user.name}" />
						</c:when>
						<c:otherwise>
							<input type="text" class="td-input-nowidth" style="width:300px;" name="Te03_online.AQ_NAME" id="Te03_online.AQ_NAME" value="" style="width:100;" />
						</c:otherwise>
					</c:choose>
				</p>
				<c:if test="${wtlx==15}">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="dxtz" id="dxtz" value="yes"/>
						&nbsp;短信通知管理员
					</p>
				</c:if>
				<div style="height:0px;"></div>
				<p>
					<label>标&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
					<input type="text"  class="required" class="td-input-nowidth" style="width:600px;" name="Te03_online.TITLE" id="title" value="${title }" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>内&nbsp;&nbsp;&nbsp;&nbsp;容：</label>
					<textarea class="editor required" id="Te03_online.CONTENT" name="Te03_online.CONTENT" rows="17"
							cols="83" tools="simple" upLinkUrl="upload.do"
							upLinkExt="zip,rar,txt" upImgUrl="ajaxXhEditorUpload.do"
							upImgExt="jpg,jpeg,gif,png" upFlashUrl="upload.do"
							upFlashExt="swf" upMediaUrl="upload.do"upMediaExt:"avi">${content}</textarea>
					<c:choose>
						<c:when test="${wtlx=='601'}">
							<input type="hidden" name="Te03_online.STATUS" id="Te03_online.STATUS" value="已处理" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="Te03_online.STATUS" id="Te03_online.STATUS" value="未处理" />
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="Te03_online.FLAG" id="Te03_online.FLAG" value="1" />
				</p>
				<p>
					<label>附&nbsp;&nbsp;&nbsp;&nbsp;件：</label>
					<span onclick="javascript:appenRow();" class="add" title="追加附件" style="cursor:pointer;">[+追加]</span>
				</p>
				<div>
					<table name="slave_table" id="slave_table">
						<tr>
							<td width="90"> </td>
							<td>
								<input type="file" name="the_file1" id="the_file1" size="30" />
								<input type="hidden" name="Te01_slave.ID" value="" />
								<input type="hidden" name="Te01_slave.MODULE_ID" value="9001" />
								<input type="hidden" name="Te01_slave.USER_ID"
									value="${user.id}" />
								<input type="hidden" name="Te01_slave.USER_NAME"
									value="${user.name}" />
								<input type="hidden" name="Te01_slave.FTP_DATE"
									value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
								<input type="hidden" name="Te01_slave.FILE_NAME" value="1" />
								<input type="hidden" name="Te01_slave.EXT_NAME" value="1" />
								<input type="hidden" name="Te01_slave.FTP_URL" value="1" />
								<input type="hidden" name="Te01_slave.SLAVE_TYPE" value="其他附件" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="button">提 交</button></div></div></li>	
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>

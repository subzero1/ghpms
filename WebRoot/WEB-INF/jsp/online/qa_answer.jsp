<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />
<%
	request.setAttribute("x_n", "\n");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/appCommon.js"></script>
<script type="text/javascript">
	var save_act="${param.save_act }";
	if(save_act=="true"){
		parent.location.reload();
		parent.closeCustomWin();
	}
	
	var n=2;
	function wxdw_sx(){
		save('form1');
		//parent.location.reload();
		//parent.closeCustomWin();
	}
</script>
</head>
<body onLoad="javascript:initFormValidation();">
<div id="online">
	<form name="form1" id="form1" action="" enctype="multipart/form-data" method="post">
	<div style="height:300px;" class="scroll-body">
		<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
						<h2 class="online-title">${Te07_qa.ord}.${Te07_qa.question }</h2>
						<h4 class="online-msg"><span class="f-right">点击数：${Te07_qa.dj }&nbsp;&nbsp;</span>发表时间：<fmt:formatDate value="${Te07_qa.cjrq }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp; </h4>						
						<div class="online-content">${fn:replace(Te07_qa.answer, x_n, '<br />')}</div></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>




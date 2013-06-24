<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>[${sta_name}] 配置人员列表</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>

</head>
	<body onLoad="javascript:initTable();">
	  <div style="height:200px;" class="scroll-body">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table" style="border-collapse:collapse;" id="list" >
			<tr>
				<th style="text-align:center">姓名</th>
				<th style="text-align:center">工号</th>
				<th style="text-align:center">电话</th>
				<th style="text-align:center">手机</th>
			</tr>
			<c:forEach var="user_list" items="${user_list}">
			<tr >
				<td style="text-align:left"  valign="top" >&nbsp;&nbsp;${user_list.name}</td>
				<td style="text-align:left" valign="top"  >${user_list.login_id}</td>
			    <td style="text-align:left" valign="top" >&nbsp;&nbsp;${user_list.tel}</td>
			    <td style="text-align:left" valign="top" >&nbsp;&nbsp;${user_list.mobile}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</body>
</html>

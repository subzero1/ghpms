<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>[${names}] 配置信息</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>

</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
      <tr style="height:22px;">
      	<th width="60%" style="text-align:center">岗 位</th>
      	<th width="40%" style="text-align:center">人员</th>
      </tr>
      
      <tr>
      	<td align="left" valign="top">
      	 	 <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
		     <div class="contentc ">
		     <div class="scroll-body" style="height:150px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="data-table2">
		      		<c:forEach var="list_sta" items="${list_sta}">
		      		<tr>
		      			<td onMouseOver="this.className='l-check'" onMouseOut="this.className=''">
		      			${list_sta.name}
		      			</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</div>
			<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
		</td>
      	<td align="left" valign="top">
      		 <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
		     <div class="contentc ">
		     <div class="scroll-body" style="height:150px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="data-table2">
		      		<c:forEach var="list_user" items="${list_user}">
      				<tr>
		      			<td onMouseOver="this.className='l-check'" onMouseOut="this.className=''">
		      			[${list_user.login_id }]${list_user.name}
		      			</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</div>
			<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
      	</td>
	   </tr>	
     </table>
</body>
</html>

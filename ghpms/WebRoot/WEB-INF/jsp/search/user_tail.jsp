<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限配置信息</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="../css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
</head>

<body >
<div id="page">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
      <tr style="height:25px;">
      	<th width="180" style="text-align:center">岗 位</th>
      	<th width="180" style="text-align:center">角 色</th>
      	<th width="250" style="text-align:center">节 点</th>
      </tr>
      
      <tr>
      	<td align="left" valign="top">
      	 	 <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
		     <div class="contentc ">
		     <div class="scroll-body" style="height:240px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="data-table2">
		      		<c:forEach var="list_sta" items="${list_sta}">
		      		<tr>
		      			<td onMouseOver="this.className='l-check'" onMouseOut="this.className=''">
							<a href="search/station_user.do?login_id=${userlist.login_id}&sta_id=${list_sta.id}&sta_name=${list_sta.name}" rel="sta_user" target="dialog" width="600" height="380" title="[${list_sta.name}]配置人员列表">${list_sta.name}</a>
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
		     <div class="scroll-body" style="height:240px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="data-table2">
		      		<c:forEach var="list_role" items="${list_role}">
      				<tr>
		      			<td onMouseOver="this.className='l-check'" onMouseOut="this.className=''">
							<a href="search/role_node.do?role_id=${list_role.id}&role_name=${list_role.name}&type=role" target="dialog" rel="list_role" width="420" height="320" title="[${list_role.name}]配置信息">${list_role.name}</a><br/>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</div>
			<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
		
      	</td>
      	<td align="left" valign="top" width="219px">
      		 <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
		     <div class="contentc ">
		     <div class="scroll-body" style="height:240px;">
				<table border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:100%;" class="data-table2">
		      		<c:forEach var="list_node" items="${list_node}">
      					<tr>
		      				<td onMouseOver="this.className='l-check'" onMouseOut="this.className=''">
		      					<a href="search/role_node.do?node_id=${list_node.id}&node_name=${list_node.name}&type=node" target="dialog" rel="list_node" width="420" height="320" title="[${list_node.name}]配置信息">${list_node.name}</a><br/>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div></div>
				<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
	      	</td>
	      </tr>	
     </table>
<br/>
	<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
	<div id="win-box">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
			<tr style="height:25px;">
				 <td>
				 	&nbsp;&nbsp;&nbsp;姓名：${userlist.name}
				 	&nbsp;&nbsp;&nbsp;工号：${userlist.login_id }
				 	&nbsp;&nbsp;&nbsp;电话：${userlist.tel }
				 	&nbsp;&nbsp;&nbsp;手机：${userlist.mobile}
				 	&nbsp;&nbsp;&nbsp;部门：${userlist.dept_name }
				 </td>
			 </tr>
		</table>
	</div>
	<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b><b class="r0"></b><b class="r00"></b></b>
</div>
</body>
</html>

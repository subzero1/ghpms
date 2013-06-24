<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
/**
 * @author wangflan 
 * @create 2010-04-07
 * 主界面UI_1 我的提问列表
 **/
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>我的提问</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK" />
	<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
	<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="../css/main-msie.css" /><![endif]-->
	<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
	<script type="text/javascript" src="js/prototype.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</head>
<body id="main-body" onload="javascript:initTable();">
	<!-- Content -->
	<div style="width:100%;">
	    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;" id="list">
			<caption>
			   <b class="ptop"><b class="p1"></b><b class="p2"></b><b class="p3"></b><b class="p4"></b></b>						
			</caption>
			<tr>
				<th width="30">状态</th>
				<th>主题</th>
				<th width="40">回复数</th>
				
			</tr>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="list_qa" items="${list_qa}">
				<tr>
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
				    <td style="text-align:center">
						<c:if test="${list_qa.status=='已处理'}"><img src="images/online_ok.gif" style="cursor:pointer"/></c:if>
						<c:if test="${list_qa.status=='未处理'}"><img src="images/online_time.gif"  style="cursor:pointer"/></c:if>
					</td>
					<td><a href="javascript:parent.openCustomWin('OnLineanswer.do?aq_id=${list_qa.id}',700,400,'auto');" style="cursor:pointer" title="${list_qa.title}">${list_qa.title_jx}</a></td>
					<td style="text-align:center">${list_qa.hfs }</td>
				</tr>
			</c:forEach>
			<c:forEach begin="1" end="${pageRowSize-offset}">
				<tr>	
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>	
			</c:forEach>
		</table>
		<div style="height:4px;width:100%;"></div>
		<div style="text-align:right">
			<input type="button" class="button-online" value="我要提问" onclick="javascript:parent.openCustomWin('OnLinequestion.do?wtlx=15',450,300,'no');"/>
			<input type="button" class="button-online" value="权限申请" onclick="javascript:parent.openCustomWin('OnLinequestion.do?wtlx=17',450,300,'no');"/>
			<input type="button" class="button-online-down" value="权限申请表" onclick="javascript:window.open('download.do?slave_id=951','','');"/>&nbsp;
		</div>
		</div>
<!-- /Content -->
	</body>
</html>

					
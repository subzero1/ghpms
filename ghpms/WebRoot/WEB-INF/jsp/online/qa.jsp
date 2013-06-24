<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>问与答</title>
		<meta http-equiv="content-type" content="text/html; charset=GBK" />
		<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
		<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
		<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
		<script type="text/javascript" src="js/prototype.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/appCommon.js"></script>
		<script type="text/javascript">
			//刷新父页面，关闭本窗口
			if(<%="yes".equals(request.getParameter("sx"))%>){
			   parent.location.reload();
			   parent.closeCustomWin();
			}
			
			function exeSave(){
				var src_ord = $("src_ord");
				var cur_ord = $("Te07_qa.ORD");
				if(src_ord.value != cur_ord.value){
					$("order").value = "true";
				}
				save('form1');
			}
			
		</script>
	</head>
	<body onLoad="javascript:initFormValidation();">
		<form name="form1" id="form1" action="" method="post">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Te07_qa"/>
			<input type="hidden" name="dispatchStr" id="dispatchStr" value="/QA.do?sx=yes"/>
			<input type="hidden" name="Te07_qa.ID" value="${Te07_qa.id }"/>
			<input type="hidden" name="perproty" value="qa_id,Te07_qa,ID" />
			<input type="hidden" name="validate" value="问题:Te07_qa.QUESTION:VARCHAR2:200:N;回答:Te07_qa.ANSWER:VARCHAR2:1000:N;顺序号:Te07_qa.ORD:NUMBER:3:N;" />
			<input type="hidden" name="ServiceName" value="qaOrderService"/>
			<input type="hidden" name="ServiceFunction" value="saveOrder"/>
			<input type="hidden" name="ServicePerproty" value="order/Te07_qa,id/Te07_qa,ord"/> 
			<input type="hidden" name="src_ord" id="src_ord" value="<c:choose><c:when test="${not empty Te07_qa.ord }">${Te07_qa.ord }</c:when><c:otherwise>${new_ord }</c:otherwise></c:choose>"/>
			<input type="hidden" name="order" id="order" value="false"/>
			<br/>
			<div style="height:260px;" class="scroll-body">
			<table id="bd" border="0" width="100%" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="alert-table">
				<tr>
					<th width="60">问&nbsp;&nbsp;题：</th>
					<td colspan="3">
						<input type="text" class="td-input-nowidth" style="width:99%" name="Te07_qa.QUESTION" id="Te07_qa.QUESTION"  value="${Te07_qa.question }"/>
						<input type="hidden" class="td-input-nowidth"  name="Te07_qa.CJRQ" id="Te07_qa.CJRQ"  value="<c:choose><c:when test="${empty Te07_qa.cjrq}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${Te07_qa.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
					</td>
				</tr>
				<tr>
					<th>回&nbsp;&nbsp;答：</th>
					<td colspan="3">
						<textarea class="td-textarea-fk" style="height:160px;" type="text" name="Te07_qa.ANSWER" id="Te07_qa.ANSWER" >${Te07_qa.answer }</textarea>
					</td>
				</tr>
				<tr>
					<th>顺序号：</th>
					<td colspan="3">
						<input type="text" class="td-input-nowidth" name="Te07_qa.ORD" id="Te07_qa.ORD" value="<c:choose><c:when test="${not empty Te07_qa.ord }">${Te07_qa.ord }</c:when><c:otherwise>${new_ord }</c:otherwise></c:choose>" size="4"/>
					</td>
				</tr>
			</table>
		</div>
			<div id="button-div">
				<input class="button-alertl f-right" type="button" value="写好了,提交" onclick="javascript:exeSave();"/>
				<input class="button-alert f-left" type="button" value="取 消" onclick="javascript:parent.closeCustomWin();"/>
			</div>

			</form>
	</body>
</html>

<script type="text/javascript">
<!--
//-->
</script>


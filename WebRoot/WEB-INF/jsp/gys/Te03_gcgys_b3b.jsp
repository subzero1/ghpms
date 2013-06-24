<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>建筑安装工程施工机械使用费概预算表（表三）丙</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>
<script type="text/javascript" src="../js/prototype-date-extensions.js"></script>
<script type="text/javascript" src="../js/datepicker.js"></script>
</head>
<body>
 <center>
<table border="0" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-title">
  	<tr>
 		<th colspan="8" class="t-center"><h2>建筑安装工程施工机械使用费概预算表（表三）丙</h2></th>
 	</tr>
 	<!-- 
    <tr>
        <th width="92">单项工程名称：</th>
        <td width="180">${dxgcmc }</td>
        <th width="92">建设单位名称：</th>
	    <td width="180">${jsdw }</td>
	    <th width="80">表格编号：</th>
	    <td width="294">${bgbh }</td>
	    <th width="60">第全页</th>
    </tr>
     -->
  </table>
    
   <table border="1" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-box">
    <tr>
      <th height="19" width="30" rowspan="2">序号</th>
      <th height="19" width="80" rowspan="2">定额编号</th>
      <th height="19" width="258" rowspan="2">项目名称</th>
      <th height="19" width="120" rowspan="2">仪表名称</th>
      <th height="19" width="60" rowspan="2">单位</th>
      <th height="19" width="60" rowspan="2">数量</th>
      <th height="8" width="140" colspan="2">单位定额值</th>
      <th height="8" width="140" colspan="2">概、预算值</th>
      <th height="38" width="90" rowspan="3">备注</th>
    </tr>
    <tr>
      <th height="11" width="70">数量(台班)</th>
      <th height="11" width="70">单价(元)</th>
      <th height="11" width="70">数量(台班)</th>
      <th height="11" width="70">合价(元)</th>
    </tr>
    <tr>
      <th height="19">1</th>
      <th height="19">2</th>
      <th height="19">3</th>
      <th height="19">4</th>
      <th height="19">5</th>
      <th height="19">6</th>
      <th height="19">7</th>
      <th height="19">8</th>
      <th height="19">9</th>
      <th height="19">10</th>
    </tr>
    <c:set var="offset" scope="page" value="0"/>
     <c:forEach var="obj" items="${gysList}">
     <tr>
        <c:set var="offset" scope="page" value="${offset + 1}"/>
     	<td class="t-center">${obj.xh }</td>
     	<td>${obj.debh }</td>
     	<td>${obj.xmmc }</td>
     	<td>${obj.ybmc }</td>
     	<td class="t-center">${obj.dw }</td>
     	<td class="t-right">${obj.sl }</td>
     	<td class="t-right">${obj.dwsl }</td>
     	<td class="t-right">${obj.dj }</td>
     	<td class="t-right">${obj.slhj }</td>
     	<td class="t-right">${obj.jehj }</td>
     	<td>${obj.bz }</td>
     </tr>
     </c:forEach>
     <c:forEach begin="1" end="${15-(offset>15?15:offset)}">
      <tr>
     	<td class="t-center">&nbsp;</td>
     	<td>&nbsp;</td>
     	<td>&nbsp;</td>
     	<td class="t-center">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     </tr>
     </c:forEach>
 </table>
 </center>
</body>

</html>
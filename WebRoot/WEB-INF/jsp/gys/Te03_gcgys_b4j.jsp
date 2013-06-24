<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title }</title>
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
 		<th colspan="8" class="t-center"><h2>${title }</h2></th>
 	</tr>
 	<!-- 
    <tr>
        <th width="92">建设项目名称：</th>
        <td width="180">${jsxmmc }</td>
        <th width="92">单项工程名称：</th>
	    <td width="180">${dxgcmc }</td>
	    <th width="92">建设单位名称：</th>
	    <td width="180">${jsdw }</td>
	    <th width="72">表格编号：</th>
	    <td width="90">${bgbh }</td>
    </tr>
     -->
  </table>
   <table border="1" cellpadding="0" cellspacing="0" width="1186" style="border-collapse:collapse;" class="black-box">
    <tr>
      <th height="19" width="30">序号</th>
      <th height="19" width="230">名称</th>  
      <th height="19" width="208">产品规格</th>      
      <th height="19" width="208">产品型号</th>        
      <th height="19" width="90">单位</th>  
      <th height="19" width="90">数量</th>  
      <th height="19" width="90">单价</th>  
      <th height="19" width="90">合计</th>  
      <th height="19" width="150">备注</th>  
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
    </tr> 
     <c:set var="offset" scope="page" value="0"/>
     <c:forEach var="obj" items="${gysList}">
     <tr>
        <c:set var="offset" scope="page" value="${offset + 1}"/>
     	<td class="t-center">${obj.xh }</td>
     	<td>${obj.mc }</td>
     	<td>${obj.xhgg }</td>     	
     	<td>${obj.wpxh }</td>     	
     	<td class="t-center">${obj.dw }</td>
     	<td class="t-right">${obj.sl }</td>
     	<td class="t-right">${obj.dj }</td>
     	<td class="t-right">${obj.hj }</td>
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
     </tr>
     </c:forEach>
  </table>
 </center>
</body>

</html>
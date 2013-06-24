<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message_title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/appCommon.js"></script>

</head>
<body>
<script type="text/javascript">
Event.observe(msg.buttOK, 'click', function(){
	document.location.href = '/ghpms/MessageWrite.do?type=${param.type}';	
});
Event.observe(msg.buttCL, 'click', function(){
	parent.closeCustomWin();
});
</script>
</body>
</html>

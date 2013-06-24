<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	var urlStr = '${url}'
	var part1 = urlStr.substring(0,urlStr.indexOf("?"));
	var part2 = urlStr.substring(urlStr.indexOf("?"),urlStr.length);
	part1 = part1.replace(/[1-9]+.[0-9]+.[0-9]+.[0-9]+/g,'localhost');
	
	// Load XML 
	var xml = new ActiveXObject("Microsoft.XMLDOM");
	xml.async = false;
	xml.load(part1+part2);
	
	// Load XSL
	var xsl = new ActiveXObject("Microsoft.XMLDOM");
	xsl.async = false;
	xsl.load("css/printCss.xsl");
	
	// Transform
	document.write(xml.transformNode(xsl));
	
</script>


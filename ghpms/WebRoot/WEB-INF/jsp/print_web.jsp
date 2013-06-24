<?xml version="1.0" encoding="GBK"?>

<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page>
	<page-header font="Simhei" font-size="8pt" color="black"></page-header>
	<page-footer font="Simhei" font-size="8pt" align="right"></page-footer>
	<total-pages></total-pages>

	<c:forEach var="print" items="${module_map}">
	
		<c:if test="${print.module_id == 101}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td01_xmxx" value="${print.td01_xmxx}" scope="request"/>
				<jsp:include page="print/p_xmxxd.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
		
		<c:if test="${print.module_id == 102}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td00_gcxx" value="${print.td00_gcxx}" scope="request"/>
				<jsp:include page="print/p_gcxxd.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
		
		<c:if test="${print.module_id == 103}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td02_xmbgd" value="${print.td02_xmbgd}" scope="request"/>
				<c:set var="td01_xmxx" value="${print.td01_xmxx}" scope="request"/>
				<jsp:include page="print/p_xmbgd.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
		
		<c:if test="${print.module_id == 104}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td04_xmysd" value="${print.td04_xmysd}" scope="request"/>
				<c:set var="td01_xmxx" value="${print.td01_xmxx}" scope="request"/>
				<jsp:include page="print/p_xmysd.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
		
		<c:if test="${print.module_id == 105}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td03_jlzj" value="${print.td03_jlzj}" scope="request"/>
				<c:set var="td01_xmxx" value="${print.td01_xmxx}" scope="request"/>
				<jsp:include page="print/p_jlzj.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
	</c:forEach>
	<cover show="false"></cover>
</page>

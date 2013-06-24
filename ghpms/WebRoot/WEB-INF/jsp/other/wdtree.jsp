<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach var="te10Map" items="${te10List}">
	<li>
		<a href="other/${param.limit == 1 ? 'wdEdit.do' : 'wdView.do' }?id=${te10Map['te10'].id }"
			target="loadFileArea" rel="wd">${te10Map['te10'].mc }</a>
		<c:if test="${not empty te10Map['te10List'] }">
			<ul>
				<c:set var="te10List" value="${te10Map['te10List']}" scope="request" />
				<c:import url="wdtree.jsp" charEncoding="UTF-8"/>
			</ul>		
		</c:if>
	</li>
</c:forEach>

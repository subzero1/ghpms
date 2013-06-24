<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page">
	<div class="pageContent">
		<table class="table" width="100%" layouth="38">
			<thead>
				<tr>
					<th width="30"></th>
					<c:forEach var="obj" items="${fieldList}">
						<th width="${obj.width }">${obj.comments }</th>
					</c:forEach>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" scope="page" value="0"/>
				<c:forEach var="tdList" items="${resultList}">
					<tr>				
						<c:set var="offset" scope="page" value="${offset + 1}"/>
						<c:set var="offset_td" scope="page" value="0"/>			
						<c:forEach var="td" items="${tdList}">
							<c:set var="offset_td" scope="page" value="${offset_td + 1}"/>
							<c:choose>
								<c:when test="${offset_td == 1}">
									<td>
										<c:if test="${param.module_id == 102}">
										<a href="business/enterpriseInfo.do?tax_code=${td.value }" target="navTab" rel="enterpriseInfo" title="查看企业信息"><img border="0" src="Images/form.gif"/></a>
										</c:if>
									</td>
								</c:when>
								<c:otherwise>
									<td align="${td.align}" width="${td.width}">${td.value }</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
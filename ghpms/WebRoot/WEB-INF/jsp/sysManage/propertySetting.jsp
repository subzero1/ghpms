<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="contentTitle">基础属性维护</h2>
<div style=" float:left; display:block; margin:10px; overflow:auto; width:25%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree">

		<c:forEach var="property" items="${propertyList}">
			<li>
				<a href="#">${property}</a>
				<ul>
					<c:forEach var="nodeElement" items="${propertyListMap[property]}">
						<li>
							<a href="sysManage/propertyEdit.do?id=${nodeElement.id}"
								target="loadFileArea" rel="propertyEdit">${nodeElement.name}</a>
						</li>
					</c:forEach>
				</ul>
		</c:forEach>
</ul>
</div>
<div id="propertyEdit" class="loadFileArea" loadfile="sysManage/propertyEdit.do?id=${param.property_id}" style=" float:left; display:block; margin:10px; overflow:auto; width:65%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	
</div>
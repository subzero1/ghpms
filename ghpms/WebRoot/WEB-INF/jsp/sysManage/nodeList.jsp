<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="contentTitle">节点属性维护</h2>
<div style=" float:left; display:block; margin:10px; overflow:auto; width:25%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree">

		<c:forEach var="flow" items="${flowList}">
			<li>
				<a href="#">${flow.name}</a>
				<ul>
					<c:forEach var="nodeElement" items="${nodeMap[flow.name]}">
						<li>
							<a href="sysManage/nodeEdit.do?id=${nodeElement.id}"
								target="loadFileArea" rel="nodeEdit">${nodeElement.name}</a>
						</li>
					</c:forEach>
				</ul>
		</c:forEach>
</ul>
</div>
<div id="nodeEdit" class="loadFileArea" loadfile="sysManage/nodeEdit.do?id=${param.node_id}" style=" float:left; display:block; margin:10px; overflow:auto; width:65%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	
</div>
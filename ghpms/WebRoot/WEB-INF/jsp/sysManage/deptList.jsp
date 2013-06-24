<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="contentTitle">部门维护</h2>
<div style=" float:left; display:block; margin:10px; overflow:auto; width:25%; height:320px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree expand">
	<li><a href="">部门列表</a>
	 <ul>
		<c:forEach var="menu" items="${areaList}">
			<li>
				<a href="#">${menu.name}</a>
				<ul>
					<c:forEach var="nodeElement" items="${deptListMap[menu.name]}">
						<li>
							<a href="sysManage/deptEdit.do?id=${nodeElement.id}"
								target="loadFileArea" rel="deptEdit">${nodeElement.name}</a>
						</li>
					</c:forEach>
				</ul>
		</c:forEach>
	 </ul>
	</li>
</ul>
</div>
<div id="deptEdit" class="loadFileArea" loadfile="sysManage/deptEdit.do?id=${param.dept_id}" style=" float:left; display:block; margin:10px; overflow:auto; width:65%; height:320px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	
</div>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="contentTitle">群组维护</h2>
<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:22%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree expand">
	<li><a href="">群组列表</a>
		<ul id="node_li">
		<c:forEach var="group" items="${groupList}">
		   <li><a href="sysManage/groupEdit.do?id=${group.id}" target="loadFileArea" rel="groupEdit">${group.name }</a></li>
		</c:forEach>
		</ul>
	</li>
</ul>
</div>
<div id="groupEdit" class="loadFileArea sysmanage_right" loadfile="sysManage/groupEdit.do?id=<c:out value="${param.group_id}" default="${groupList[0].id}"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:68%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
</div>
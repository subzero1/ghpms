<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">岗位维护</h2>
<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:18%; height:390px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree expand">
	<li><a href="">系统岗位</a>
	 <ul>
		<c:forEach var="sta" items="${staList}">
		   <li><a href="sysManage/staEdit.do?id=${sta.id}" target="loadFileArea" rel="staEdit">${sta.name }</a></li>
		</c:forEach>
	 </ul>
	</li>
</ul>
</div>
<div id="staEdit" class="loadFileArea sysmanage_right" loadfile="sysManage/staEdit.do?id=<c:out value="${param.sta_id}" default="1"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:75%; height:390px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
</div>
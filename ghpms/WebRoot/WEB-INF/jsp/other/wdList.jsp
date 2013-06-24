<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2 class="contentTitle">文档${param.limit == 1 ? "管理" : "查阅" }<a href="other/wdSearch.do" style="color:blue;margin-left:10px;" target="navTab" rel="wdSearch" title="公司内部文档搜索"><img src="Images/fangdajing.png" title="搜索" alt="搜索" style="width:15px;height:15px;"/></a></h2>
	<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:22%; height:500px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	<ul class="tree">
		<li class="node_li"><a href="other/${param.limit == '1' ? 'wdEdit' : 'wdView' }.do?id=1" target="loadFileArea" rel="wd">文档目录</a>
		 <c:if test="${not empty te10List}">
		 <ul>
			<c:import url="wdtree.jsp" charEncoding="UTF-8"/>
		 </ul>
		 </c:if>
		</li>
	</ul>
	</div>
	<div id="wd" class="loadFileArea sysmanage_right" loadfile="other/${param.limit == '1' ? 'wdEdit' : 'wdView' }.do?id=<c:out value="${param.te10_id}" default="1"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:72%; height:500px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	</div>

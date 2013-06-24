<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script language="javascript">
$(function(){
	initSysManageWeb();
});

</script>

<div class="panel sysmanage_max" style="width: 96%; float: left; margin: 10px; overflow-y: hidden">
	<h1>
		<c:forEach items="${contents}" var="content">
			<a title="文档查看" href="other/wdList.do?limit=0&te10_id=${content.id }" target="navTab" rel="wdckList" style="color:#666;">${content.mc }</a>\
		</c:forEach>
		${te10.mc }
		<span style="color:#666; font-weight:normal">（查阅范围：${te10.cyfw }）</span>
	</h1>
	<div style="overflow-y: hidden;height:300px;padding:0px;">
				<c:forEach var="obj" items="${uploadslave}">
					<div class="fileDiv" style="border-bottom:1px #ccc dashed;margin:0px;">
					<!-- line1 -->
					<div style="text-align: right;margin:10px 10px 2px 10px;">
						<span style="float:left">
						<b>[ ${obj.file_name} ]</b>&nbsp;&nbsp;
						<a href="show_slave.do?slave_id=${obj.id}" target="dialog" width="1000" height="600" title="查看"><span style="vertical-align:bottom;"><img alt="查看" src="Images/form.gif"/></span>&nbsp;<font>查看</font></a>
						<a href="download.do?slave_id=${obj.id}" title="下载"><span style="vertical-align:bottom;"><img alt="下载" src="Images/download.png"/></span>&nbsp;<font color="red">下载</font></a>
						</span>
						<span>[创建人：${obj.user_name }&nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${obj.ftp_date}"/> ]</span>
					</div>
					<!-- line2 -->
					<div style="margin:10px;">
						<span style="color:#666;">文档描述：${obj.remark }</span>
					</div>
					</div>
				</c:forEach>

	</div>
</div>

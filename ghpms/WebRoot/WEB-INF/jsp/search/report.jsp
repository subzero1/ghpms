<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="crht"%>

<script language="javascript">
//查看统计明细
function showDetail(str){
	$("#str").val(str);
	$.pdialog.open("search/reportDetail.do", "reportDetail", "报表明细", {mask:true, width:800, height:500, data: $('#detailForm').serializeArray()});
}
//导出excel，从主界面隐藏报表条件中触发excel导出按钮的click事件
function mainReportExport(){
	var module_id = "${param.module_id }";
	var butt = $("#" + module_id + " button[name=reportToExcel]" , $("#mainReportCondition"));
	var y_way = $("#" + module_id + " #y_way tr" , $("#mainReportCondition"));
	var x_way = $("#" + module_id + " #x_way tr" , $("#mainReportCondition"));
	if(butt.size() > 0 && (y_way.size()>2 || x_way.size()>1))
		butt[0].click();
	else
		alertMsg.warn("没有可输出信息!");
}

</script>

<div class="page">
	<div class="pageHeader">
		<form action="" method="post">
		<div class="searchBar">
			<div class="subBar">
				<ul>
					<li><a class="button" href="search/condition.do?type=report&module_id=${param.module_id }" target="dialog" width="800" height="350" rel="reportCondition" title="设置统计条件"><span>条件过滤</span></a></li>
					<li><a class="button" href="javascript:mainReportExport();" title="EXCEL导出"><span>EXCEL导出</span></a></li>
					<li><a class="button" href="dispath.do?url=search/reportCharts.jsp;" title="统计图" target="navTab" rel="cacu"><span>统计图</span></a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent" layoutH="58">
		<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<c:forEach var="tdList" items="${resultList}">
				<tr>		
					<c:forEach var="td" items="${tdList}">
						<c:if test="${td.show == true}">
							<c:choose>
								<c:when test="${td.title == true}"><th <c:if test="${empty td.colspan }">style="width:90px;"</c:if> rowspan="${td.rowspan }" colspan="${td.colspan }" align="${td.align }">${td.value }</th></c:when>
								<c:otherwise>
									<td <c:if test="${empty td.colspan }">style="width:90px;"</c:if> rowspan="${td.rowspan }" colspan="${td.colspan }" style="text-align:center;">
										<a href="javascript:showDetail('${td.param }')">${td.value }</a>	&nbsp;								
									</td>
								</c:otherwise>
							</c:choose>
						</c:if>				
					</c:forEach>
				</tr>
			</c:forEach>						
		</table>
		<c:if test="${not empty searchStr }">
			<table border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
				<tr>
					<td>查询条件：${searchStr }</td>   
				</tr>
			</table>
		</c:if>
		
		<form id="detailForm" method="post" action="reportDetail.do">
			<input type="hidden" name="sqlWhere" value="${sqlWhere }"/>
			<input type="hidden" name="module_id" value="${param.module_id }"/>
			<input type="hidden" id="str" name="str" value=""/>
			<c:forEach var="obj" items="${YList}">
				<input type="hidden" name="ids" value="${obj.id }"/>
			</c:forEach>
			<c:forEach var="obj" items="${XList}">
				<input type="hidden" name="ids" value="${obj.id }"/>
			</c:forEach>
			<c:forEach var="obj" items="${sum}">
				<input type="hidden" name="ids" value="${obj }"/>
			</c:forEach>
		</form>
	</div>
</div>

<%
 List resultList=(List)request.getAttribute("resultList");
request.getSession().setAttribute("_resultList",resultList);  
%>
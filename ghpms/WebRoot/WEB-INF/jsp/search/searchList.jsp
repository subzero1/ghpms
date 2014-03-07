<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
 <%@ taglib uri="NetSkyTagLibs" prefix="netsky"%> 
<script language="javascript">
function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	if($form.find("input").size() == 4){
		alertMsg.warn("没有可输出信息!");
		return;
	}
	$form.attr("action","search/searchListExport.do");
	$form.submit();
	$form.attr("action","");
}
function searchList(param1){
	var module_name=$("select[name='module_name']",navTab.getCurrentPanel()).val();
	var url="search/condition.do?module_id="+module_name+"&navtab="+param1;
	$.pdialog.open(url, '', '设置查询条件', {mask:true,width:815,height:350});
	
}
</script>

<form id="pagerForm" method="post" action=""> 
	<input type="hidden" name="pageNum" value="${page}" />
	<input type="hidden" name="numPerPage" value="${pageRowSize}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" id="module_id" name="module_id" value="${param.module_id}"/>
	<c:forEach var="obj" items="${searchField}">
		<input type="hidden" name="ids" value="${obj[0]}" />
		<input type="hidden" name="${obj[1] }" value="${obj[2]}" />
	</c:forEach>
	<c:forEach var="obj" items="${fields_select}">
		<input type="hidden" name="fields_select" value="${obj}" />
	</c:forEach>
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="sysManage/userList.do" method="post">
		<div class="searchBar">
		<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						选择表单： <netsky:htmlSelect name="module_name"  objectForOption="modules" style="width:128px;" valueForOption="id" showForOption="name" extend="-----------,101" extendPrefix="true" value="${param.module_id}"  htmlClass="td-select"/></td>
					</tr>
				</table>
			<div class="subBar">
				<ul>
					<li><a class="button" href="javascript:searchList('${param.navtab }')" title="设置查询条件"><span>条件过滤</span></a></li>
					<li><a class="button" href="javascript:searchListExport();" title="EXCEL导出"><span>EXCEL导出</span></a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layouth="115" style="width: ${tablewidth }px;">
			<thead>
				<tr>
				<th width="30"></th>
					<th width="30"></th>
					<c:forEach var="obj" items="${fieldList}">
						<th width="${obj.width }">${obj.comments }</th>
					</c:forEach>
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
								<c:when test="${offset_td == 1 }">
									<td> </td>
									<td>
										<a href="gh/openForm.do?project_id=${td.value}&module_id=${param.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${doc[cols].project_id}${td.value}]"  title="项目信息"><img border="0" src="Images/project.png" /></a>
									</td>
								</c:when>
								<c:otherwise>
									<td style="text-align: ${td.align};"  width="${td.width}">${td.value }</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
				<c:forEach begin="1" end="${pageRowSize-offset}">
					<tr>
						<td>&nbsp;</td>
						<c:forEach var="obj" items="${fieldList}">
						<td>&nbsp;</td>
						</c:forEach>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalRows}条 </span>
			</div>
			
			<div class="pagination" formId="searchPageForm" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageRowSize}" currentPage="${page}"></div>

		</div>
	</div>
</div>
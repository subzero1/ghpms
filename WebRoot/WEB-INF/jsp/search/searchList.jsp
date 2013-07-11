<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="subBar">
				<ul>
					<li><a class="button" href="search/condition.do?module_id=${param.module_id }&navtab=${param.navtab }" target="dialog" width="815" height="350" rel="searchCondition" title="设置查询条件"><span>条件过滤</span></a></li>
					<li><a class="button" href="javascript:searchListExport();" title="EXCEL导出"><span>EXCEL导出</span></a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layouth="85">
			<thead>
				<tr>
					<c:if test="${param.module_id == 101}">
									<th width="30"></th>
								</c:if>
								<c:if test="${param.module_id == 102}">
									<th width="30"></th>
								</c:if>
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
								
								
								
								<c:when test="${offset_td == 1 && param.module_id == 1}">
									<td>
										<a href="mbk/mbkEdit.do?id=${td.value }" target="navTab" rel="mbk" title="目标库信息"><img border="0" src="Images/project.png" /></a>
									</td>
								</c:when>
								<c:when test="${offset_td == 1 && param.module_id == 101}">
								<c:if test="${param.module_id == 101}">
									<td>
								<a href="javascript:navTab.openTab('xmsgjd', 'wxdw/xmsgjd.do?id=${td.value}', {title:'项目施工进度'});" title="项目施工进度" ><img border="0" src="Images/station.png" /></a> 
										</td>
								</c:if>
									<td>
										<a href="javascript:openFlowForm('{project_id:${td.value},doc_id:${td.value},module_id:101,opernode_id:-1,node_id:-1,user_id:-1}');" title="表单[${doc[cols].project_id}${td.value}]"  title="项目信息"><img border="0" src="Images/project.png" /></a>
									</td>
								</c:when>
								<c:when test="${offset_td == 1 && param.module_id == 102}">
								<c:if test="${param.module_id == 102}">
									<td>
								<a href="javascript:navTab.openTab('gcsgjd', 'wxdw/gcsgjd.do?id=${td.value}', {title:'工程施工进度'});" title="工程施工进度" ><img border="0" src="Images/station.png" /></a> 
										</td>
								</c:if>	
									<td>
										<a href="javascript:openFlowForm('{project_id:${td.value},doc_id:${td.value},module_id:102,opernode_id:-1,node_id:-1,user_id:-1}');" title="表单[${doc[cols].project_id}${td.value}]"  title="工程信息"><img border="0" src="Images/project.png" /></a>
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
				<c:forEach begin="1" end="${pageRowSize-offset}">
					<tr>
						<c:if test="${param.module_id == 101}">
									<td width="30"></td>
								</c:if>
								<c:if test="${param.module_id == 102}">
									<td width="30"></td>
								</c:if>
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
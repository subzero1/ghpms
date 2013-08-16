<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
$(function(){
	navTab.getCurrentPanel().data("reloadFlag", 1);
	
	$("#keyword",navTab.getCurrentPanel()).keyup(function(e){
		if (e.which == 13){
			$("#searchButton",navTab.getCurrentPanel()).click();
		}
	});
});
function searchListExport(param1){
	var $form=$("#pagerForm",navTab.getCurrentPanel());
	$form.attr("action","form/gcsjList.do?"+param1);
	$form.removeAttr("onsubmit");
 	$form.submit();
 	$form.attr("onsubmit","return navTabSearch(this)");
 	$form.attr("action","form/gcsjList.do");
}
function navTabSearch(form){
//alert( $(form).serializeArray()[0].name);
var keyword=$("#keyword",$.pdialog.getCurrent()).val();
navTab.reload("form/gcsjList.do?module_id=${param.module_id}&keyword="+keyword, $(form).serializeArray());
return false;
}
</script>
<form id="pagerForm" method="post" action="form/gcsjList.do?module_id=${param.module_id}" onsubmit="return navTabSearch(this)">
	<input type="hidden" name="module_id" value="${param.module_id}" />
	<input type="hidden" name="keyword" value="${keyword}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form	action="form/gcsjList.do?module_id=${param.module_id}" method="post">
			<input type="hidden" id="selectedId_demo" />
			<input type="hidden" name="workState" value="${param.workState}">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${keyword}" type="text" size="25" title="工程名,视宽编号,或工程编号"/>
						<input type="hidden" name="module_id" value="${param.module_id}"></td>
						<td>
							<c:if test="${param.workState == 5}">
							年度
								<netsky:htmlSelect name="year" objectForOption="yearList"  value="${showYear}" htmlClass="td-select"/>
							</c:if>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'form/gcsjList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:forEach items="${newFormList}" var="formItem">
					<c:if test="${fn:contains(formItem.url,'module_id=109')}">
					<li>
						<a class="add" href="${formItem.url }" target="navTab" rel="autoform" title="新建需求"><span>新建需求</span></a>
					</li>
					<li class="line">line</li>
					</c:if>
		     	</c:forEach> 
		     	<!--
				<li>
					<a class="batchmodify"	href="dispath.do?url=form/batchUpdateProject.jsp" target="dialog" rel="batchUpdateProject" width="400" height="200"><span>批量修改</span></a>
				</li>
				<li class="line">line</li>
				 -->
				<li>
					<a class="setting"	href="gcsjListCfg.do?module_id=${param.module_id }" target="dialog" rel="gcsjListConfig" width="500" height="370"><span>设置</span></a>
				</li>
				<li class="line">line</li>
					<li>
						<a class="add"
							href="gh/openForm.do?module_id=${param.module_id }&user_id=${user.id }&node_id=${node_id }"
							target="navTab" rel="gcsj" title=""><span>添加</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="delete" href="gcsj/ajaxGcsjDel.do?id={project_id}&module_id=${param.module_id }"
							target="ajaxTodo" title="确认删除吗？"><span>删除</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="exportexcel"
							href="javascript:searchListExport('toExcel=yes');" ><span>导出</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="exportexcel"
							href="dispath.do?url=form/basicImport.jsp?module_id=${param.module_id }" target="dialog" width="400" height="200"><span>导入</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
			</ul>
		</div>
		<table class="table" layouth="138" style="width: ${docTabWitdh }">
			<thead>
				<tr> 
					<th style="width:20px;"></th>
					<!-- 初始化标题名称 -->
					<c:forEach var="col" items="${docColsList}">
						<th   style="width:${col.width}px;" orderField="${col.object_name}.${col.name}">${col.comments}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>	
				<c:set var="offset" value="0"/>			
				<c:forEach var="doc" items="${docs}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="project_id" rel="${doc[cols].id}">
						<td>
						<c:if test="${doc[cols] != null }">
							<a href="gh/openForm.do?project_id=${doc[cols].id}&module_id=${param.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${doc[cols].gcmc}]"><img border="0" src="Images/form.gif" style="cursor:pointer;margin:4px 1px;"/></a>
						</c:if>
						</td>
						
						<c:forEach var="j" begin="0" end ="${cols-1>0?cols-1:0}">
							<c:choose>
								<c:when test="${'gcmc' == docColsList[j].name}">
									<td style="text-align:${docColsList[j].align};"><a href="gh/openForm.do?project_id=${doc[cols].id}&module_id=${param.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${doc[cols].gcmc}]">${doc[j]}</a></td>
								</c:when>
								<c:otherwise><td style="text-align:${docColsList[j].align};">${doc[j]}</td></c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
					<c:forEach var="j" begin="1" end ="${cols-1>0?cols-1:0}">
						<td></td>
					</c:forEach>
					</tr>
				</c:forEach>
				</c:if>
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
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>
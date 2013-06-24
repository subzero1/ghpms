<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="crht"%>
<script type="text/javascript">
	function _sel(help_id,module_name){
		
		$.ajax({
			type: "POST",
			url: "help/ajaxSetHelpMap.do?help_id="+help_id+"&module_name="+module_name,
			dataType:"json",
			success: function(json){
				if(json.statusCode == DWZ.statusCode.ok){
					alertMsg.correct(json.message);
					$.pdialog.closeCurrent();
					return false;
				}
			},
			error: DWZ.ajaxError
		});
	}
</script>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="showPart" value="${param.showPart}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form id="form1" onsubmit="return dialogSearch(this);"
			action="help/helpList.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>标题：</td>
						<td><input name="keywords" value="${param.keywords}" type="text" /><input name="showPart" value="${param.showPart}" type="hidden" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
	<form id="helpDelForm" onsubmit="return navTabCheckbox(this);" action="help/ajaxHelpDelAll.do" method="post">
		<div class="panelBar">
			<ul class="toolBar"></ul>
		</div>
		<table class="table" width="100%" layouth="140">
			<thead>
				<tr>
					<th orderField="title">
						标题
					</th>
					<th style="width:50px;">&nbsp;</th>
				</tr>
			</thead>
			<tbody id="help_tbody">
				<c:forEach var="i" begin="0" end="${numPerPage}">
					<tr target="help_id" rel="${helpList[i].id}">
						<td>
							${helpList[i].title }
						</td>
						<td>
							<c:if test="${not empty helpList[i].title}"><a href="javascript:_sel(${helpList[i].id },'${param.module_name}')"><b>选择</b></a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" change="navTabPageBreak"
					param="numPerPage" selectValue="${numPerPage}">
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
						100
					</option>
					<option value="200">
						200
					</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" numPerPage="${numPerPage}"
				orderField="${orderField }"
				currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>
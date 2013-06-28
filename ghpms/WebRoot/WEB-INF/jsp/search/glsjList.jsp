<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
 	function searchSubmit(){
 		var $keyword=$("#keyword",navTab.getCurrentPanel());
 		navTab.openTab('xmxxList', 'search/xmxxList.do?keyword='+$keyword.val(), {title:'项目信息列表'});
 	}
 	$(function(){
 		$("#keyword",navTab.getCurrentPanel()).keyup(function(e){
 			if(e.which==13)
 				searchSubmit();
 		});
 		
 	});
</script>
<form id="pagerForm" method="post" action="form/glsjList.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" /> 
</form>

<div class="page">
	<div class="pageHeader">
		<form action="form/glsjList.do" method="post"
			onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input type="text" style="display: none" />
							关键字：
							<input id="keyword" name="keyword" value="${keyword}" type="text" size="25" />
						</td>

					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button"
										onClick="javascript:searchOrExcelExport(this,'form/glsjList.do',navTabSearch);">
										检 索
									</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${node_id == '10101'}">
					<li>
						<a class="add"
							href="flowForm.do?module_id=101&node_id=10101&flow_id=101"
							target="navTab" rel="xmxx" title="项目信息单"><span>添加</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="delete" href="form/ajaxXmxxDel.do?id={xm_id}"
							target="ajaxTodo" title="确认删除吗？"><span>删除</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<!--<li>
					<a class="batchmodify"	href="dispath.do?url=form/batchUpdateProject.jsp" target="dialog" rel="batchUpdateProject" width="400" height="200"><span>批量修改</span></a>
					</li>
					<li class="line">line</li>-->
					<li>
						<a class="exportexcel"
							href="dispath.do?url=form/xlsImport.jsp?config=batch_update_xm"
							target="dialog" width="400" height="200"><span>导入</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
				</c:if>
				<!-- 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
					
					<li><a class="helponline"	href="javascript:enterHelp('xmxx')"><span>在线2帮助</span></a></li>
					<li class="line">line</li>
					-->
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 60px;"></th>
					<th style="width: 60px;" orderField="jdmc">
						节点名称
					</th>
					<th orderField="skbh">
						视宽编号
					</th>
					<th style="width: 115px;" orderField="ghrwh">
						歌华任务号
					</th>
					<th style="width: 75px;" orderField="qy">
						区域
					</th>
					<th style="width: 60px;" orderField="rwwcqk">
						任务完成情况
					</th>
					<th style="width: 200px;" orderField="ghsxyq">
						歌华时限要求
					</th>
					<th style="width: 75px;" orderField="xmfzr">
						项目负责人
					</th>
					<th style="width: 70px;" orderField="rwly">
						任务来源
					</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0" />
				<c:forEach var="obj" items="${glsjList}">
					<c:set var="offset" value="${offset+1}" />
					<tr target="xm_id" rel="${obj.id}">
						<td style="text-align: center;">
							<a class="add"
								href="javascript:navTab.openTab('xmsgjd', 'wxdw/xmsgjd.do?id=${obj.id }', {title:'项目施工进度'});"
								rel="gcsgjd"><img border="0" src="Images/chart_bar.png"
									style="cursor: pointer" />
							</a>&nbsp;&nbsp;
							<a class="add"
								href="jlgt/jlgtView.do?module_id=101&doc_id=${obj.id }"
								target="navTab" rel="jlgtView"><img border="0"
									src="Images/track_record.png" style="cursor: pointer" />
							</a>
						</td>
						<td>
							<a
								href="gh/openForm.do?project_id=${obj.id }&module_id=101&doc_id=${obj.id }&user_id=${user.id }&limit=${limit }&node_id=${node_id }"
								target="navTab" rel="glsj" title="${obj.jdmc }">${obj.jdmc }</a>
						</td>
						<td>
							${obj.skbh }&nbsp;
						</td>
						<td>
							${obj.ghrwh}
						</td>
						<td>
							${obj.qy }
						</td>
						<td>
							${obj.rwwcqk }
						</td>
						<td>
							<fmt:formatDate value="${obj.ghsxyq }" pattern="yyyy-MM-dd" />
						</td>
						<td>
							${obj.xmfzr }
						</td>
						<td>
							${obj.rwly }
						</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
					<c:forEach begin="${offset}" end="${numPerPage-1}">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td> 
							<td></td> 
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})"
					selectValue="${numPerPage}">
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
				currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>
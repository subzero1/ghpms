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
<form id="pagerForm" method="post" action="aux/userLoginDetail.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
	<input type="hidden" name="login_date1" value="${param.login_date1 }"/>
	<input type="hidden" name="login_date2" value="${param.login_date2 }"/>
	<input type="hidden" name="login_name" value="${param.login_name }"/>
	<input type="hidden" name="tjlb" value="${param.tjlb }"/> 
</form>

<div class="page">
	<div class="pageHeader">
		<form action="aux/userLoginDetail.do" method="post"
			onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input type="text" style="display: none" />
							<input type="hidden" name="login_name" value="${param.login_name }"/>
							<input type="hidden" name="tjlb" value="${param.tjlb }"/> 
							登录时间：
							<input id="login_date1" name="login_date1" value="${param.login_date1}"
								type="text"  class="date" size="10"/>
							至
							<input id="login_date2" name="login_date2" value="${param.login_date2 }" class="date" size="10"/>
						</td>

					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button"
										onClick="javascript:searchOrExcelExport(this,'aux/userLoginDetail.do',navTabSearch);">
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
					<th style="width: 64px;" orderField="a.user_name">用户</th>
					<th orderField="b.mobile_tel">
						手机号码
					</th>
					<th style="width: 64px;"  >
						部门
					</th>
					<th style="width: 64px;" orderField="b.area_name">
						所属地区
					</th>
					<th style="width: 64px;" orderField="a.login_ip">
						登录IP
					</th>
					<th style="width: 64px;" orderField="a.login_date">
						登录时间
					</th>
					<th style="width: 64px;" orderField="a.logout_date">
						登出时间
					</th>
					<th style="width: 64px;" orderField="a.systeminfo">
						系统信息
					</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0" />
				<c:forEach var="obj" items="${userLoginList}">
					<c:set var="offset" value="${offset+1}" />
					<tr target="xm_id" rel="">
						<td>
							${obj[0]}
						</td>
						<td>
							${obj[1]}
						</td>
						<td>
							${obj[2]}
						</td>
						<td>
							${obj[3]}
						</td>
						<td>
							${obj[4]}
						</td>
						<td>
							<fmt:formatDate value="${obj[5]}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${obj[6]}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</td>
						<td>
							${obj[7]}
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
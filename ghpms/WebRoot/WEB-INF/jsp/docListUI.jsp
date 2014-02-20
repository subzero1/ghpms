<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
//计算页面上待办列表允许高度
$(function(){
	var bo = $(".pageContent", $("#needWorkList_ui"));
	if(bo){
		//计算列表高度
		var temp_h = navTab._panelBox.height() - 320;
		bo.height(temp_h);
	}
	
});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<h1 style="line-height:26px;">超期列表</h1>
			</li>
			<li class="line">line</li>
		</ul>
	</div>
	<div class="pageContent"  style="overflow-y:auto;overflow-x:hidden;height:300px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th width="25"></th>
					<th width="100">表单类别</th>
					<th width="">名称</th>
					<th width="128">歌华编号</th>
					<th width="100">视宽编号</th>
					<th width="100">计划时间</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/> 
				<c:forEach var="obj" items="${outDateList }" end="5">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>
							<c:if test="${obj != null}">	
								<a href="gh/openForm.do?project_id=${obj.project.id}&module_id=${obj.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.project.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>${obj.module_name}</td>
						<td>
							<a href="gh/openForm.do?project_id=${obj.project.id}&module_id=${obj.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.project.gcmc}]"  title="项目信息">${obj.project.gcmc}</a>
						</td>
						<td>${obj.project.skbh}</td>
						<td>${obj.project.ghbh}</td>
						<td style="color: red;"><fmt:formatDate value="${obj.project.jhwcsj}" pattern="yyyy-MM-dd"/> </td>
					</tr>
				</c:forEach>
				<c:if test="${!empty lendOutDateList }">
				<tr><td></td><td></td><td colspan="3"></td><td>借料时间</td></tr>
				</c:if>
				<!-- 借料反馈 -->
				<c:forEach var="obj" items="${lendOutDateList }">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>
							<c:if test="${obj != null}">	
								<a href="gh/openForm.do?project_id=${obj.project.id}&module_id=${obj.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.project.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>${obj.module_name}[借料超期]</td>	
						<td>
							<a href="gh/openForm.do?project_id=${obj.project.id}&module_id=${obj.module_id }&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.project.gcmc}]"  title="项目信息">${obj.project.gcmc}</a>
						</td>
						<td>${obj.project.skbh}</td>
						<td>${obj.project.ghbh}</td>
						<td style="color: red;"><fmt:formatDate value="${obj.project.jlsj}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					</tr>
				</c:forEach> 
				<c:if test="${!empty lendOutDateList }">
				<tr><td></td><td></td><td colspan="3"></td><td>反馈时间</td></tr>
				</c:if>
				<!-- 反馈调度中心超期 -->
				<c:forEach var="obj" items="${fkddList }">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>
							<c:if test="${obj != null}">	
								<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>光缆维护工程[反馈调度中心]</td>	
						<td>
							<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息">${obj.gcmc}</a>
						</td>
						<td>${obj.skbh}</td>
						<td>${obj.ghbh}</td>
						<td style="color: red;"><fmt:formatDate value="${obj.fksj1}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					</tr>
				</c:forEach> 
				<!-- 反馈工单超期 -->
				<c:forEach var="obj" items="${fkgdList }">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>
							<c:if test="${obj != null}">	
								<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>光缆维护工程[反馈工单]</td>	
						<td>
							<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息">${obj.gcmc}</a>
						</td>
						<td>${obj.skbh}</td>
						<td>${obj.ghbh}</td>
						<td style="color: red;"><fmt:formatDate value="${obj.fksj2}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					</tr>
				</c:forEach> 
				<!-- 超期紧急 -->
				<c:forEach var="obj" items="${jjList }">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>
							<c:if test="${obj != null}">	
								<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>					
							</c:if>	
						</td>
						<td>光缆维护工程[紧急]</td>	
						<td>
							<a href="gh/openForm.do?project_id=${obj.id}&module_id=401&user_id=${user.id }&node_id=${node_id }" target="navTab" rel="gcsj" title="表单[${obj.gcmc}]"  title="项目信息">${obj.gcmc}</a>
						</td>
						<td>${obj.skbh}</td>
						<td>${obj.ghbh}</td>
						<td style="color: red;"><fmt:formatDate value="${obj.fksj3}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					</tr>
				</c:forEach> 
				<c:if test="${offset<20}">
				<c:forEach begin="${offset}" end="15">
				<tr>
					<c:forEach begin="1" end="6">
				      <td></td>
				    </c:forEach>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>共<c:if test="${empty totalCount}">0</c:if><c:if test="${!empty totalCount}">0</c:if>条 </span>
			</div>
		</div>
	</div>
	
</div>
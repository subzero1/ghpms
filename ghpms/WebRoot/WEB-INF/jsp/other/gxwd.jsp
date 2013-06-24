<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<form id="pagerForm" action="" method="post">
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
		<input type="hidden" name="slave_type" value="${type }" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>文档名称：</td>
					<td>
					<input name="keywords" value="${param.keywords}" type="text" size="25" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'other/Gxwd.do',navTabSearch);">检 索</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="dispath.do?url=uploadFile.jsp?module_id=${module_id }&project_id=-1&doc_id=-1&slave_type=1&navTabId=gxwdList" target="dialog" width="400" height="260" title="共享文档上传"><span>上传</span></a></li>
				<li class="line">line</li>
				<c:if test="${ user.id==1}">
					<li><a class="delete" href="delfile.do?slave_id={gxwd_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
				</c:if>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
				<th width="30">序号</th>
				<th width="350" >文件名称</th>
				<th width="200" >文件描述</th>
				<th width="100" >上传人</th>
				<th width="150" >上传时间</th>
				<th></th>
				</tr>
			</thead>
			<tbody>				
				<c:set var="offset" scope="page" value="0"/>
				<c:forEach var="obj" items="${te01_list}">
					<tr target="gxwd_id" rel="${obj.id}">
					    <c:set var="offset" scope="page" value="${offset + 1}"/>
					    <td style="text-align:center">${offset}</td>
						<td title="点击下载"><a href="download.do?slave_id=${obj.id}">${obj.file_name}</a></td>
						<td style="text-align:left">${obj.remark}</td>
						<td style="text-align:left">${obj.user_name}</td>
						<td style="text-align:left"><fmt:formatDate value="${obj.ftp_date}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
				<c:forEach begin="1" end="${numPerPage-offset}">
					<tr>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>
	                    <td>&nbsp;</td>
						<td>&nbsp;</td>	
						<td>&nbsp;</td>
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
				<span>共${totalCount}条 </span>
			</div>
			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>
		</div>
	</div>
</div>

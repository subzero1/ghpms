<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
$(function(){
	$("#ztgjz",navTab.getCurrentPanel()).keyup(function(e){
		if (e.which == 13){
			$("#searchButton",navTab.getCurrentPanel()).click();
		}
	});
});

function phoneMsg(tel,name){
	$.pdialog.open('MessageWrite.do?type=phone&phonenum='+tel+'&name='+encodeURI(encodeURI(name)), 'messageWrite', '手机短信',{data:{}, mask:true, width:670, height:350})
}

</script>
<c:choose>
	<c:when test="${wtlx=='15'}">
		<c:set var="web_title" scope="page" value="在线提问" />
		<c:set var="new_desc" scope="page" value="我要提问" />
	</c:when>
	<c:when test="${wtlx=='17'}">
		<c:set var="web_title" scope="page" value="系统权限申请" />
		<c:set var="new_desc" scope="page" value="权限申请" />
	</c:when>
	<c:when test="${wtlx=='601'}">
		<c:set var="web_title" scope="page" value="系统公告" />
		<c:set var="new_desc" scope="page" value="公告发布" />
	</c:when>
	<c:otherwise>
		<c:set var="web_title" scope="page" value="在线信息" />
		<c:set var="new_desc" scope="page" value="新 建" />
	</c:otherwise>
</c:choose>

<form id="pagerForm" method="post" action="OnLineList.do">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="wttcr" value="${param.wttcr}" />
	<input type="hidden" name="wtzt" value="${param.wtzt}" />
	<input type="hidden" name="ztgjz" value="${param.ztgjz}" />
	<input type="hidden" name="wtlx" value="${wtlx}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form name="form1" action="OnLineList.do" method="post">
			<input type="hidden" name="pageNum" value="${pageNum }" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" name="orderField" value="${param.orderField}" />
			<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<input type="hidden" name="wtlx" id="wtlx" value="${wtlx }" />
						<c:if test="${wtlx == '15' || wtlx == '17'}">
							<td>状态：</td>	
							<td><select name="wtzt" id="wtzt">
								<option value="">全部</option>
								<option value="0" <c:if test="${wtzt=='0' }">selected</c:if>>未处理</option>
								<option value="1" <c:if test="${wtzt=='1' }">selected</c:if>>已处理</option>
							</select></td>
						</c:if>
						<c:if test="${wtlx=='15'}">
							<td><select name="wttcr" id="wttcr">
								<option value="0" <c:if test="${wttcr=='0' }">selected</c:if>>全部问题</option>
								<option value="1" <c:if test="${wttcr=='1' }">selected</c:if>>我提出的问题</option>
							</select></td>	
						</c:if>
						<td>关键字：</td>
						<td>
						    <input type="text" style="display:none"/>
							<input type="text" name="ztgjz" id="ztgjz" value="${ztgjz }" size="30" class="td-input-nowidth"/>
						</td>
					</tr>						
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button id="searchButton" type="button" onClick="javascript:searchOrExcelExport(this,'OnLineList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
		</div>
	</form>	
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${wtlx==15 || wtlx==17 || (wtlx==601 && ggrole=='yes')}">
					<li>
						<a class="add"	href="OnLinequestion.do?wtlx=${wtlx}" target="dialog" width="750" height="500" rel="onlinequestion" title="${web_title }"><span>${new_desc}</span></a>
					</li>
					<li class="line">line</li>
				</c:if>
				<c:if test="${delrole=='yes' && (wtlx=='15' || wtlx=='17' || wtlx=='601')}">
					<li>
						<a class="delete"	href="onlinedelajax.do?wtlx=${wtlx}&id={qa_id}" target="ajaxTodo" title="确认删除吗?"><span>删除</span></a>
					</li>
					<li class="line">line</li>
				</c:if>
				<c:if test="${wtlx=='601'}">
					<li>
						<a class="edit"	href="OnLinequestion.do?wtlx=${wtlx}&online_id={qa_id}" target="dialog" width="750" height="500" title="${web_title }修改"><span>修改</span></a>
					</li>
					<li class="line">line</li>
				</c:if>
				<c:if test="${ggrole=='yes' && (wtlx=='15' || wtlx=='17' || wtlx=='601')}">
					<li>
						<a class="stickandre" href="onlinezdajax.do?wtlx=${wtlx}&id={qa_id}" target="ajaxTodo" title="确认进行反向置顶设置吗?' " ><span>置顶反设</span></a>
					</li>
					<li class="line">line</li>
				</c:if>
				
				<li>
					<a class="lookover"	href="OnLineanswer.do?wtlx=${wtlx}&aq_id={qa_id}" target="dialog" width="750" height="500" title="${web_title }"><span>查看</span></a>
				</li>
				<li class="line">line</li>
				
				<li>
					<a class="helponline"	href="javascript:enterHelp('xtgg')" ><span>在线帮助</span></a>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<c:if test="${wtlx=='15' || wtlx=='17'}">
						<th width="30">状态</th>
					</c:if>
					<th orderField="status asc,flag asc,title">主题</th>
					<th width="100" orderField="status asc,flag asc,aq_name">创建人</th>
					<th width="120" orderField="status asc,flag asc,aq_tel">创建人电话</th>
					<th width="120" orderField="status asc,flag asc,aq_date">创建时间</th>
					<th width="50">回复人</th>
					<th width="120">回复时间</th>
					<th width="35">附件</th>
					<th width="35">回复</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="qa" items="${list_qa}">
				<tr target="qa_id" rel="${qa.id }">
					<c:if test="${wtlx=='15' || wtlx=='17'}">
						<td style="text-align:center">
							<c:if test="${qa.status=='已处理'}"><img src="Images/online_ok.gif" title="已处理" /></c:if>
							<c:if test="${qa.status=='未处理'}"><img src="Images/online_time.gif" title="未处理"/></c:if>
						</td>
					</c:if>
					<td title="${qa.title}">
						<c:if test="${qa.flag==0}">
							<font style="color: red;">${qa.title_jx}</font>
						</c:if>
						<c:if test="${qa.flag!=0}">${qa.title_jx}</c:if>
					</td>
					<td style="text-align: right">
						<c:if test="${qa != null}">
							${qa.aq_name}&nbsp;&nbsp;&nbsp;
							<a	href="javascript:phoneMsg('${qa.aq_tel}','${qa.aq_name}');">
								<img src="Images/phone.png" border="0" title="手机短信" />
							</a>
						</c:if>
					</td>
					<td>${qa.aq_tel}</td>
					<td><fmt:formatDate value="${qa.aq_date}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td>${qa.hfr }</td>
					<td><fmt:formatDate value="${qa.hf_date }" pattern="yyyy-MM-dd HH:mm" /></td>
					<td style="text-align:center;">${qa.fjs }</td>
					<td style="text-align:center;">${qa.hfs }</td>
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
	
		

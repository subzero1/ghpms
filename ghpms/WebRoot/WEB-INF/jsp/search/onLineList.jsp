<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="page">
	<form id="pagerForm" action="other/Gxwd.do" method="post">
		<div class="searchBar">
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:navTab.reload('search/OnLineList.do','zxry','在线人员列表');">刷  新</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>	
	<div class="pageContent">
	<div class="panelBar">
		</div>
	<ul class="toolBar">
		 <table width="100%" class="table" id="list" layouth="138">
		 <thead>
			<tr>
				<th width="5%">序号</th>
				<th width="10%">工号</th>
				<th width="10%">姓名</th>
				<th width="15%">手机</th>
				<th width="10%">部门</th>
				<th width="10%">地区</th>				
				<th width="10%">IP地址</th>
				<th width="15%">登录人系统信息</th>
				<th width="10%">登录时间</th>
				<th width="5%">短信</th>
			</tr>
			</thead>
			<tbody>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="user_list" items="${user_list}">
				<tr>
				    <c:set var="offset" scope="page" value="${offset + 1}"/>
				    <td style="text-align:center">${offset }</td>
					<td style="text-align:left">${user_list[0]}</td>
					<td style="text-align:left">${user_list[1]}</td>
					<td style="text-align:left">${user_list[7]}</td>
					<td style="text-align:left">${user_list[5]}</td>
					<td style="text-align:left">${user_list[6]}</td>					
					<td style="text-align:left">${user_list[3]}</td>
					<td style="text-align:left">${user_list[4]}</td>
					<td style="text-align:center"><fmt:formatDate value="${user_list[2]}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td style="text-align:center"><a
											href="javascript:$.pdialog.open('MessageWrite.do?type=phone&phonenum=${user_list[10]}', 'onlinequestion', '手机短信',{data:{name:'${user_list[1]}'}, mask:true, width:590, height:330});"><img
												src="Images/phone.png" border="0" title="手机短信" /></td>
				</tr>
			</c:forEach>
			<c:if test="${offset<18}">
				<c:forEach begin="${offset}" end="18">
					<tr>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
	                    <td>&nbsp;</td>
						<td>&nbsp;</td>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>	
						<td>&nbsp;</td>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>						
					</tr>	
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
</div>


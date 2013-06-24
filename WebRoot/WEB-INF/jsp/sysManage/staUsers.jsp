<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveStaUsers.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_user');"> 
			<input type="hidden" name="station_id" value="${param.id}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_user" name="s_user" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_user','t_user');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_users}">
								<option title="${obj.remark}"  value="${obj.id}">${obj.name}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_user','t_user');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_user','s_user');">
						</td>
						<td>
							<select id="t_user" name="t_user" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_user','s_user');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_users}">
								<option title="${obj.remark}"  value="${obj.id}">${obj.name}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveUserGroups.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_group');"> 
			<input type="hidden" name="user_id" value="${param.id}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_group" name="s_group" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_group','t_group');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_groups}">
								<option title="${obj.dis}"  value="${obj.id}">${obj.name}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_group','t_group');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_group','s_group');">
						</td>
						<td>
							<select id="t_group" name="t_group" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_group','s_group');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_groups}">
								<option title="${obj.dis}"  value="${obj.id}">${obj.name}</option>
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

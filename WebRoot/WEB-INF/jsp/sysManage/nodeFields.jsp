<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveNodeFields.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_field');"> 
			<input type="hidden" name="node_id" value="${param.id}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_field" name="s_field" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_field','t_field');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_fields}">
								<option title="${obj.comments}"  value="${obj.id}">${obj.comments}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_field','t_field');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_field','s_field');">
						</td>
						<td>
							<select id="t_field" name="t_field" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_field','s_field');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_fields}">
								<option title="${obj.comments}"  value="${obj.id}">${obj.comments}</option>
							</c:forEach>
							</select>
							<input type="hidden" id="fields" name="fields"/>
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

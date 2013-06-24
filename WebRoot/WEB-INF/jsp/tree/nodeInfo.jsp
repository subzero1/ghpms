<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/**
 * @author Chiang 2010-02-10
 * 流程节点信息显示
 * @param List<NodeInfo> list
 */
%>

<script language="javascript">
function update(){
	$("#node_action").val("update");
	$("#node_form").submit();
}
function del(){
	alertMsg.confirm('是否确认删除！',{
		okCall:function(){
			$("#node_action").val("del");
		 	$("#node_form").submit();
		}
	});
}
function modifyNodeAjaxDone(json){
	if (json.isRoot != null && json.isRoot == 'true'){
		setTimeout(function(){
			navTab.closeCurrentTab();
			$.pdialog.closeCurrent();
			navTab.reloadFlag("workList");
		}, 100);
	}else{
		dialogAjaxDone(json);
	}
}
</script>

<div class="page">
	<div class="pageContent">
		<table class="table" width="100%" layouth="58">
			<thead>
				<tr>
					<th>操作人</th>
					<th>受理时间</th>
					<th>操作时间</th>
					<th>节点开始时间</th>
					<th>节点结束时间</th>
					<th>处理状态</th>
					<th>节点状态</th>
				</tr>
			</thead>
			<tbody>		
				<c:forEach var="obj" items="${list}">
					<tr>
						<td>${obj.oper_user }</td>
						<td>${obj.accept_time }</td>
						<td>${obj.oper_time }</td>
						<td>${obj.start_time }</td>
						<td>${obj.end_time }</td>
						<td>${obj.approve_result }</td>
						<td>${obj.node_status }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form id="node_form" name="node_form" action="modifyNode.do" method="post" onsubmit="return validateCallback(this, modifyNodeAjaxDone);">
			<input type="hidden" id="node_action" name="action" value=""/>
			<input type="hidden" name="node_id" value="${param.node_id }"/>
			
			<div class="formBar">
				<div style="float:left;">
					<c:if test="${admin == true}">
					<table>
						<tr>
							<td width="90">当前节点状态：</td>
							<td width="100" align="left">
								<select name="status">
									<option value="0" <c:if test="${status == 0}">selected</c:if>>待办</option>
									<option value="1" <c:if test="${status == 1}">selected</c:if>>新建</option>
									<option value="2" <c:if test="${status == 2}">selected</c:if>>在办</option>
									<option value="3" <c:if test="${status == 3}">selected</c:if>>发送</option>
									<option value="4" <c:if test="${status == 4}">selected</c:if>>回复同意</option>
									<option value="5" <c:if test="${status == 5}">selected</c:if>>回复修改</option>
									<option value="6" <c:if test="${status == 6}">selected</c:if>>回复不同意</option>
									<option value="7" <c:if test="${status == 7}">selected</c:if>>回复不发表意见</option>
									<option value="8" <c:if test="${status == 8}">selected</c:if>>办结</option>
								</select>
							</td>
							<td width="80" align="right">审批结果：</td>
							<td width="100">
								<select name="approve_result">
								<option value="" <c:if test="${empty approve_result}">selected</c:if>></option>
								<option value="4" <c:if test="${approve_result == 4}">selected</c:if>>同意</option>
								<option value="5" <c:if test="${approve_result == 5}">selected</c:if>>修改</option>
								<option value="6" <c:if test="${approve_result == 6}">selected</c:if>>不同意</option>
								<option value="7" <c:if test="${approve_result == 7}">selected</c:if>>不发表意见</option>
								</select>
							</td>
						</tr>
					</table>
					</c:if>
				</div>
				<ul>
					<c:if test="${admin == true}">
						<li><div class="buttonActive"><div class="buttonContent"><button type="button"  onclick="javascript:update();">修 改</button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button"  onclick="javascript:del();">删除节点</button></div></div></li>
					</c:if>
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
			<c:if test="${admin != true}">
				<script language="javascript">
					var obj = document.getElementsByTagName("SELECT");
					for(var i = 0; i < obj.length; i++){
						obj[i].disabled = true;
					}
					obj = document.getElementsByTagName("INPUT");
					for(var i = 0; i < obj.length; i++){
						obj[i].disabled = true;
					}
				</script>
			</c:if>
		</form>
	</div>
</div>

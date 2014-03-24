<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
function changeFlow(obj){
	var station_id = "${param.id}";
	var flow_id = $(obj).val();
	$.pdialog.reload("sysManage/staNodes.do",{data:{"flow_id":flow_id, "id":station_id}});
}
</script>
<script language="javascript">
function changeFlow(obj){
	var station_id = "${param.id}";
	var flow_id = $(obj).val();
	var node_type=$("#node_type",$.pdialog.getCurrent()).val();
	$.pdialog.reload("sysManage/staNodes.do",{data:{"flow_id":flow_id, "id":station_id,"node_type":node_type}});
}
</script>
<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveStaNodes.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_node');"> 
			<input type="hidden" name="station_id" value="${param.id}"> 
			<input type="hidden" id="node_type" name="node_type" value="${node_type}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<netsky:htmlSelect name="flow_id" objectForOption="flowList" valueForOption="id" showForOption="name" value="${flow_id}" onChange="javascript:changeFlow(this);"/>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select id="s_node" name="s_node" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_node','t_node');" style="width:195px;height:210px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_nodes}">
								<option value="${obj.node.id}" 
								title="${obj.node.remark }" >${obj.node.name}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_node','t_node');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_node','s_node');">
						</td>
						<td>
							<select id="t_node" name="t_node" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_node','s_node');" style="width:195px;height:210px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_nodes}">
								<option value="${obj.node.id}" title="${obj.node.remark }"
								  >${obj.node.name}&nbsp;[${obj.module.name}]</option>
							</c:forEach>
							</select>
							<input type="hidden" id="nodes" name="nodes"/>
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

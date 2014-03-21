<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="dms"%>
<script type="text/javascript">
 function recordCallBack(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		var $node_id=$("#node_id",navTab.getCurrentPanel());
		var $node_type=$("#node_type",navTab.getCurrentPanel());
		if($node_id.val()==""){ 
		//回调函数,取得表的最大ID
		 $.ajax({
			type: 'POST',
			url:'gcsj/getMaxNodeID.do',
			data:{tableName:'Tb02_node'},
			dataType:"html",
			cache: false,
			success: function openNavTab(data){
				navTab.openTab('nodeList', 'sysManage/nodeList.do?node_id='+data+'&node_type='+$node_type.val(), {title:'节点维护信息'});
				},
			error: DWZ.ajaxError
		});
		}
		
	}
}


</script>

<div class="panel"  style="width:96%;float:left;margin:10px">
		<h1>基本属性</h1>
		<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, recordCallBack);">
			<input type="hidden" id="tableInfomation" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Tb02_node" keep="true"/>
			<input type="hidden" id="node_id" name="Tb02_node.ID" value="${tb02_node.id}" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="sysManage/nodeList.do?node_id=${tb02_node.id}&node_type=<c:out value='${tb02_node.node_type }' default="${param.node_type }"></c:out>" keep="true"/>
			<input type="hidden" name="_navTabId" value="propertySetting" keep="true"/>
			<input type="hidden" id="node_type" name="Tb02_node.NODE_TYPE" value="<c:out value='${tb02_node.node_type }' default="${param.node_type }"></c:out>" />
			<div class="pageFormContent" style="height:200px">
				<p>
					<label>节点名称：</label>
					<input type="text" name="Tb02_node.NAME" value="${tb02_node.name}" class="required" style="width:146px;"/>
				</p> 
				<p>
					<label>属性分类：</label>
					<dms:htmlSelect name="Tb02_node.FLOW_ID" objectForOption="flowList" showForOption="name" valueForOption="id"  value="${!empty tb02_node.flow_id?tb02_node.flow_id:param.flow_id}" style="width:128px;"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>节点注释：</label>
					<input type="text" name="Tb02_node.REMARK" value="${tb02_node.remark}"  style="width:146px;"/>
				</p>
				<div class="divider"></div>
				<div class="remark" style="color:#888;height:80px;">
				【注】：节点属性说明；<br>
			</div>
			<div class="formBar">
				<ul>
				   <c:if test="${empty property_type.flag || fn:indexOf(property_type.flag,'[r]') == -1}">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="divFileReload" type="Button" loadfile="sysManage/nodeEdit.do?flow_id=${!empty tb02_node.flow_id?tb02_node.flow_id:param.flow_id}&node_type=<c:out value='${tb02_node.node_type }' default="${param.node_type }"></c:out>">添 加</button></div></div></li>
					<c:if test="${not empty tb02_node.id}">
						<li><div class="button"><div class="buttonContent"><button class="formDataClear" type="Button">删 除</button></div></div></li>
					</c:if>
				   </c:if>
				</ul>
			</div>
		</form>
		</div>
	</div>
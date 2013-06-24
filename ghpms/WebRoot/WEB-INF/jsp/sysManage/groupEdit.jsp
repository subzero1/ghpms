<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();
　　$("#delGroup").click(
		function(){
		 var node="${nodes}";
		 var user="${users}";
		 if(node.length<=2 && user.length<=2){
			 alertMsg.confirm("确认删除吗", {
		        okCall: function(){
		            $("#delGroupForm").submit();
	             }});
          }else{
           alertMsg.warn("群组存在关联信息，不能删除");
          }
		}
	);
});
</script>
<div class="panel sysmanage_max" defH="158" style="width: 97%; float: left; margin: 5px">
	<h1>
		群组信息
	</h1>
	<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta05_group" />
			<input type="hidden" name="Ta05_group.ID" value="${group.id}" />
			<input type="hidden" name="perproty" value="group_id,Ta05_group,id">
			<input type="hidden" name="_callbackType" value="forward" />
			<input type="hidden" name="_forwardUrl" value="sysManage/groupList.do" />
			<input type="hidden" name="_navTabId" value="groupList" />
			<div class="pageFormContent">
				<p>
					<label>
						群组名称：
					</label>
					<input type="text" name="Ta05_group.NAME" value="${group.name}"
						class="required" style="width: 300px;" maxlength="15" />
				</p>
				<!-- 
				<div style="height: 0px;"></div>
				<p>
					<label>
						群组级别：
					</label>
					<input type="text" name="Ta05_group.GRADE" value="${group.grade}"
						class="required digits" style="width: 300px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						上一层标识：
					</label>
					<input type="text" name="Ta05_group.UP_ID" value="${group.up_id}"
						class="required" style="width: 300px;" maxlength="15" />
				</p>
				-->
				<div style="height: 0px;"></div>
				<p style="height: 60px;">
					<label>
						群组描述：
					</label>
					<textarea name="Ta05_group.DIS" style="width: 300px; height: 50px;">${group.dis}</textarea>
				</p>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/groupEdit.do">新建群组</button></div></div>
				</div>
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
					</li>
					<c:if test="${not empty group}">
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" id="delGroup">删 除</button></div></div>
					</li>
					</c:if>
				</ul>
			</div>
		</form>
		<form action="sysManage/ajaxGroupDel.do?id=${group.id}"
			id="delGroupForm"
			onsubmit="return validateCallback(this, navTabAjaxDone);"
			method="post" />
	</div>
</div>
<c:if test="${not empty group.id}">
	<div class="panel sysmanage_min" defH="110"
		style="width: 48%; float: left; margin: 5px">
		<h1>
			相关人员&nbsp;
			<a href="sysManage/groupUsers.do?id=${group.id}" target="dialog"
				width="450" height="340" title="群组人员配置">[配置]</a>
		</h1>
		<div>
			<ul>
			<c:forEach var="user" items="${users}">
				<li>
					${user.name}
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110"
		style="width: 48%; float: left; margin: 5px">
		<h1>
			相关节点&nbsp;
			<a href="sysManage/groupNodes.do?id=${group.id}" target="dialog"
				width="450" height="340" title="群组节点配置">[配置]</a>
		</h1>
		<div>
			<ul>
				<c:forEach var="node" items="${nodes}">
					<li>
						${node.remark}
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

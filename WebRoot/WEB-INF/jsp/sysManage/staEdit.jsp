<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();

	$("#delSta").click(
		function(){
		 var role="${role}";
		 var user="${users}";
		 if(role.length<=2 && user.length<=2){
			 alertMsg.confirm("确认删除吗", {
		        okCall: function(){
		            $("#delStaForm").submit();
	             }});
          }else{
           alertMsg.warn("岗位存在关联信息，不能删除");
          }
		}
	);
});
</script>
<div class="panel sysmanage_max" defH="160" style="width: 97%; float: left; margin: 5px">
	<h1>
		岗位信息
	</h1>
	<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta02_station" />
			<input type="hidden" name="Ta02_station.ID" value="${sta.id}" />
			<input type="hidden" name="perproty" value="sta_id,Ta02_station,id">
			<input type="hidden" name="_callbackType" value="forward" />
			<input type="hidden" name="_forwardUrl" value="sysManage/staList.do" />
			<input type="hidden" name="_navTabId" value="staList" />
			<div class="pageFormContent">
				<p>
					<label>
						岗位名称：
					</label>
					<input type="text" name="Ta02_station.NAME" value="${sta.name}"
						class="required" style="width: 300px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p style="height: 60px;">
					<label>
						岗位描述：
					</label>
					<textarea name="Ta02_station.REMARK"
						style="width: 300px; height: 50px;">${sta.remark}</textarea>
				</p>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/staEdit.do">新建岗位</button></div></div>
				</div>
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保 存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" id="delSta">
									删 除
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
		<form action="sysManage/ajaxStaDel.do?id=${sta.id}" id="delStaForm"
			onsubmit="return validateCallback(this, navTabAjaxDone);"
			method="post" />
	</div>
</div>
<c:if test="${not empty sta.id }">
	<div class="panel sysmanage_min" defH="110" style="width: 32%; float: left; margin: 5px">
		<h1>
			相关角色&nbsp;
			<a href="sysManage/staRoles.do?id=${sta.id}" target="dialog" width="450" height="340" title="岗位角色配置">[配置]</a>
		</h1>
		<div>
			<ul>
				<c:forEach var="role" items="${role}">
					<li>
						${role.name}
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110" style="width: 31%; float: left; margin: 5px">
		<h1>
			相关人员&nbsp;
			<a href="sysManage/staUsers.do?id=${sta.id}" target="dialog" width="450" height="340" title="岗位人员配置">[配置]</a>
		</h1>
		<div>
			<ul>
			<c:forEach var="user" items="${users}">
				<li>
					<a >${user.name}</a>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110" style="width: 32%; float: left; margin: 5px">
		<h1>
			相关节点&nbsp;
			<a href="sysManage/staNodes.do?id=${sta.id}" target="dialog" width="450" height="340" title="岗位节点配置">[配置]</a>
		</h1>
		<div>
			<ul>
			<c:forEach var="node" items="${nodes}">
				<li>
					<a href="sysManage/nodeFields.do?id=${node.id }" target="dialog"
				width="450" height="340" title="节点字段配置">${node.remark}</a>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	</c:if>
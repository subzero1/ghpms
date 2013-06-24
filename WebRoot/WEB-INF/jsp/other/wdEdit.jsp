<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script language="javascript">

$(function(){

	initSysManageWeb();
	
	　$("#del",navTab.getCurrentPanel()).click(
		function(){
			alertMsg.confirm("确认删除吗？", {
               okCall: function(){
					$("#delForm",navTab.getCurrentPanel()).submit();
               }});
		}
	);
	$("#upload",navTab.getCurrentPanel()).click(function(){
		var url = "dispath.do?url=other/uploadFile.jsp?project_id=${te10.id }&doc_id=${te10.id }&module_id=92&node_id=-1&opernode_id=-1&lb=${param.lb}";
		$.pdialog.open(url,'_upload_slave_form','附件上传',{width:400,height:220});
	});
	$(".delFile",navTab.getCurrentPanel()).click(function(){
		var slave_id = $(this).attr("slave_id");
		alertMsg.confirm("确认删除吗?", {
		okCall: function(){
			ajaxTodo("delfile.do", function delFileAjaxDone(json){
				DWZ.ajaxDone(json);
					if (json.statusCode == DWZ.statusCode.ok){
						navTab.reload("other/wdList.do?limit=1&te10_id=${te10.id }", {navTabId: 'wdList'});
					}
			}, {'slave_id':slave_id})
		}
	});});

});
//根目录不允许修改
if ("${te10.id == 1}"=="true"){
	$("#wdForm",navTab.getCurrentPanel()).find(":input").attr("readonly","readonly");
}
</script>
<div class="tabs"  <c:if test="${param.new_dir==1 }">currentIndex="0"</c:if><c:if test="${param.new_dir!=1 }">currentIndex="1"</c:if> eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>文档管理</span></a></li>
					<li><a href="javascript:;"><span>文件列表[${fn:length(uploadslave)}]</span></a></li>
				</ul>
			</div>
		</div>
<div class="tabsContent" style="height:512px;">
	<div style="overflow-y: hidden">
		<form id="wdForm" method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Te10_wdml" />
			<input type="hidden" name="Te10_wdml.ID" value="${te10.id}" />
			<input type="hidden" name="perproty" value="te10_id,Te10_wdml,id">
			<input type="hidden" name="_callbackType" value="forward" />
			<input type="hidden" name="_forwardUrl" value="other/wdList.do?limit=1&lb=${param.lb }" />
			<input type="hidden" name="_navTabId" value="" />
			<input type="hidden" name="Te10_wdml.UP_ID" value="${parent_te10.id}" />
			<input type="hidden" name="Te10_wdml.CJSJ" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${empty te10.cjsj ? now : te10.cjsj}"/>"/>
			<div class="pageFormContent" style="height: 172px;">
				<p>
					<label>目录名称${param.new_dir }：</label>
					<input type="text" name="Te10_wdml.MC" value="${te10.mc }" style="width:366px" class="required" />
				</p>
				<div style="height:0px;width:100%"></div>
				<p>
					<label>上级目录：</label>
					<input type="text" readonly="readonly" value="${parent_te10.mc }" style="width:366px" />
				</p>
				<div style="height:0px;width:100%"></div>
				<p>
					<label>查阅范围：</label>
					<input type="text" name="Te10_wdml.CYFW" class="required" id="cyfwOrg.cyfw" value="${empty te10.cyfw ? parent_te10.cyfw : te10.cyfw }" readonly="readonly" style="width:346px"/>
					<input type="hidden" name="Te10_wdml.CYLB" id="cyfwOrg.cylb" value="${empty te10.cylb ? parent_te10.cylb : te10.cylb }"/>
					<a id="btnLook" class="btnLook" href="other/getCyfw.do" data="{parentCylb:'${parent_te10.cylb }',parentCyfw:'${parent_te10.cyfw }'}" lookupGroup="cyfwOrg" width="600" height="395">选择查阅范围</a>
				</p>
				<div style="height:0px;width:100%"></div>
				<p>
					<label>创建部门：</label>
					<netsky:htmlSelect name="Te10_wdml.CJBM" objectForOption="deptList" style="width:126px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${empty te10 ? user.dept_name : te10.cjbm }" htmlClass="required"/>
				</p>
				<p>
					<label>创建人：</label>
					<input readonly="readonly" type="text" name="Te10_wdml.CJR" style="width:120px;" value="${empty te10.cjr ? user.name : te10.cjr}"/>
				</p>
				<div style="height:0px;width:100%"></div>
				<p>
					<label>备注：</label>
					<textarea name="Te10_wdml.BZ" style="width:366px;height:30px;">${te10.bz }</textarea>
				</p>
			</div>
			<div class="formBar">
				<c:if test="${not empty te10 }">
					<div  style="float:left;">
						<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="other/wdEdit.do?lb=${param.lb }&up_id=${te10.id }&new_dir=1">新建子目录</button></div></div>
					</div>
				</c:if>
				<ul>
					<c:if test="${te10.id != 1 && te10.id != 2}">
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
						</li>
					</c:if>
					<c:if test="${not empty te10 && te10.id != 1 && te10.id != 2 }">
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="Button" id="upload">上 传</button></div></div>
						</li>
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="Button" id="del">删 除</button></div></div>
						</li>
					</c:if>
				</ul>
			</div>

		</form>
		<form action="other/wdmlDel.do?id=${te10.id}" id="delForm" onsubmit="return validateCallback(this, navTabAjaxDone);" method="post">
			<input type="hidden" name="_forwardUrl" value="other/wdList.do?limit=1&lb=${param.lb }&te10_id=${te10.up_id }" />
		</form>
	</div> 
<c:if test="${not empty te10 }">
		<div >
		<c:forEach var="obj" items="${uploadslave}">
			<p class="slaveList" title="${obj.remark }">
				${obj.file_name}&nbsp;&nbsp;
				<a href="show_slave.do?slave_id=${obj.id}" target="dialog" width="1000" height="600" title="查看"><font color=blue>查看</font></a>
				<a href="download.do?slave_id=${obj.id}" title="下载"><font color="red">下载</font></a>
				<c:if test="${empty te05.fbsj}"><a href="#" class="delFile" slave_id="${obj.id }"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
			</p>
		</c:forEach>
		</div> 
</c:if>
</div>
</div>
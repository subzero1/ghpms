<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
<!--
	$(document).ready(function(){
		　　$("#delDept").click(
				function(){
					alertMsg.confirm("删除后，数据将不可恢复，确认删除？", {
	                okCall: function(){
	                               $("#delDeptForm").submit();
	                }});
				}
			);
	});
//-->
</script>
<div class="panel" defH="260" style="width:96%;float:left;margin:10px">
		<h1>部门信息</h1>
		<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta01_dept"/>
			<input type="hidden" name="Ta01_dept.ID" value="${deptObj.id}"/>
			<input type="hidden" name="perproty" value="dept_id,Ta01_dept,id">
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_forwardUrl" value="sysManage/deptList.do"/>
			<input type="hidden" name="_navTabId" value="deptList"/>
			<div class="pageFormContent" style="height:203px;">
				<p>
					<label>部门名称：</label>
					<input type="text" name="Ta01_dept.NAME"  value="${deptObj.name}" class="required" style="width:120px;" />
				</p>
				<p>
					<label>所属区域：</label>
					<netsky:htmlSelect name="Ta01_dept.AREA_NAME" objectForOption="areaList" valueForOption="name" showForOption="name" value="${deptObj.area_name}" style="width:110px;"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>部门描述：</label>
					<textarea name="Ta01_dept.REMARK" style="width:330px;height:60px;"> ${deptObj.remark } </textarea>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>是否有效：</label>
					<input type="radio" name="Ta01_dept.USEFLAG" <c:if test="${deptObj.useflag==1||empty deptObj }">checked</c:if> value="1"/>有效
					<input type="radio" name="Ta01_dept.USEFLAG" <c:if test="${not empty deptObj && deptObj.useflag!=1 }">checked</c:if> value="0"/>无效
				</p>
				<div class="divider"></div>
				<div class="remark">&nbsp;</div>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/deptEdit.do">新建部门</button></div></div>
				</div>
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<c:if test="${not empty deptObj}">
					<li><div class="buttonActive"><div class="buttonContent" ><button type="Button" id="delDept" >删 除</button></div></div></li>
					</c:if>
				</ul>
			</div> 
			
		</form>
		<form action="sysManage/ajaxDeptDel.do?id=${deptObj.id}" id="delDeptForm" onsubmit="return validateCallback(this, navTabAjaxDone);" method="post"/>
		</div>
	</div>	
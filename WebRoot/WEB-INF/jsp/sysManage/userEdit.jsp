<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<script language="javascript">

$(function(){

	initSysManageWeb();
	
	　$("#delUser").click(
		function(){
			alertMsg.confirm("删除后，数据将不可恢复，确认删除？", {
               okCall: function(){
                              $("#delUserForm").submit();
               }});
		}
	);
	$("#AREA_NAME").change(function(){
		jilian('DEPT_ID','Ta01_dept.area_name',$("#AREA_NAME").val(),'id','name','name');
	})
	
});

</script>

<div class="panel sysmanage_max" defH="230" style="width: 96%; float: left; margin: 10px; overflow-y: hidden">
	<h1>
		用户信息
	</h1>
	<div style="overflow-y: hidden">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta03_user" />
			<input type="hidden" name="Ta03_user.ID" value="${userObj.id}" />
			<input type="hidden" name="perproty" value="user_id,Ta03_user,id">
			<input type="hidden" name="_callbackType" value="forward" />
			<input type="hidden" name="_forwardUrl" value="sysManage/userList.do" />
			<input type="hidden" name="_navTabId" value="userList" />
			<div class="pageFormContent" style="height: 172px;">
				<p>
					<label>登录名：</label>
					<input  type="text" name="Ta03_user.LOGIN_ID" value="${userObj.login_id }" style="width:90px;" class="required" />
				</p>
				<p>
					<label>密 码：</label>
					<input type="password" name="Ta03_user.PASSWD" value="${userObj.passwd }" style="width:110px;" class="required" />
				</p>
				<p>
					<label>姓 名：</label>
					<input  type="text" name="Ta03_user.NAME" value="${userObj.name }" style="width:90px;" class="required" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>所属地区：</label>
					<netsky:htmlSelect id="AREA_NAME" style="width:96px;"
						name="Ta03_user.AREA_NAME" objectForOption="areaList"
						valueForOption="name" showForOption="name"
						value="${userObj.area_name}" extend="" extendPrefix="true" htmlClass="required"/>
				</p>
				<p>
					<label>所属部门：</label>
					<netsky:htmlSelect id="DEPT_ID" style="width:116px;"
						name="Ta03_user.DEPT_ID" objectForOption="deptList"
						valueForOption="id" showForOption="name"
						value="${userObj.dept_id}" extend="" extendPrefix="true"  htmlClass="required"/>
				</p>
				<p>
					<label>是否可用：</label>
					<input type="radio" name="Ta03_user.USEFLAG" value="1" <c:if test="${userObj.useflag=='1'||empty dept }">checked</c:if> />
					是
					<input type="radio" name="Ta03_user.USEFLAG" value="0" <c:if test="${userObj.useflag=='0' }">checked</c:if> />
					否
				</p>
				<!-- 
				<p>
					<label>集中派工：</label>
					<netsky:htmlSelect name="Ta03_user.SEND_HTGLY" style="width:90px;"
						objectForOption="sendHtglyList"  value="${userObj.send_htgly}"
						extendPrefix="true" extend="" />
				</p>
				 -->
				<div style="height:0px;"></div>
				<p>
					<label>移动电话：</label>
					<input  type="text" name="Ta03_user.MOBILE_TEL" value="${userObj.mobile_tel }" style="width:90px;" class="required" />
				</p>
				<p>
					<label>固定电话：</label>
					<input type="text" name="Ta03_user.FIX_TEL" value="${userObj.fix_tel }"  style="width:110px;"/>
				</p>
				<p>
					<label>性别：</label>
					<input type="radio" name="Ta03_user.SEX" value="男" <c:if test="${userObj.sex=='男'||empty dept }">checked</c:if> />
					男
					<input type="radio" name="Ta03_user.SEX" value="女" <c:if test="${userObj.sex=='女' }">checked</c:if> />
					女
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>电子邮件：</label>
					<input  type="text" name="Ta03_user.EMAIL" value="${userObj.email }"  style="width:495px;"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备 注：</label>
					<textarea name="Ta03_user.REMARK" style="width: 495px; height: 30px;">${userObj.remark }</textarea>
				</p>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/userEdit.do">新建用户</button></div></div>
				</div>
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
					</li>
					<c:if test="${not empty userObj}">
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="Button" id="delUser">删 除</button></div></div>
						</li>
					</c:if>
				</ul>
			</div>

		</form>
		<form action="sysManage/ajaxUserDel.do?id=${userObj.id}" id="delUserForm" onsubmit="return validateCallback(this, navTabAjaxDone);" method="post" />
	</div>
</div>
<c:if test="${not empty userObj.id }">

	<div class="panel sysmanage_min" defH="110"
		style="width: 47.5%; float: left; margin: 5px; margin-left: 10px">
		<h1>
			相关岗位&nbsp;
			<a href="sysManage/userStas.do?id=${userObj.id}" target="dialog" height="340" width="455"
				title="用户岗位配置">[配置]</a>
		</h1>
		<div>
			<ul>
				<c:forEach var="sta" items="${stas}">
					<li>
						${sta.name}
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110"
		style="width: 47.5%; float: left; margin: 5px;">
		<h1>
			相关群组&nbsp;
			<a href="sysManage/userGroups.do?id=${userObj.id}" target="dialog" height="340" width="455"
				title="用户群组配置">[配置]</a>
		</h1>
		<div>
			<ul>
				<c:forEach var="group" items="${groups}">
					<li>
						${group.name}
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
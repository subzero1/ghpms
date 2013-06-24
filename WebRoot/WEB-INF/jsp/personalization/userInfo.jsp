<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<style>
.width160 {
	width: 130px
}
</style>
<script type="text/javascript">
	$(function(){
		//var originalpwd = '${user.passwd}';
		$("#AREA_NAME").change(function(){
				jilian('DEPT_ID','Ta01_dept.area_name',$("#AREA_NAME").val(),'id','name','name');
			})
			/*
		$("#submitbutton").click(function(){
				if ($("#pwd").val()!=""){
					if ($("#pwd").val()!=$("#repwd").val()){
						$("#pwd").val("");
						$("#repwd").val("");
						alertMsg.info('对不起,两次输入的密码不一致!');
						return false;
					} else {
						$("#pwddate").val('<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>');
					}
				}else {
					$("#pwd").val(originalpwd);
				}
			})
			*/
	})
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="userInfoAjaxSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,loadFileAreaAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta03_user" />
			<input type="hidden" id="pwddate" name="Ta03_user.LAST_PWD_DATE" value="${user.last_pwd_date }"/>
			<input type="hidden" name="Ta03_user.ID" value="${user.id}" />
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
					<input  type="text" name="Ta03_user.LOGIN_ID" style="width:120px;" value="${user.login_id }" readonly="readonly"/>
				</p>
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="Ta03_user.NAME" style="width:120px;" value="${user.name }" class="required" />
				</p>
				<!-- 
				<p>
					<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					<input type="password" id="pwd" name="Ta03_user.PASSWD" style="width:120px;" value="" />
				</p>
				<p>
					<label>确认密码：</label>
					<input type="password" id="repwd" style="width:120px;" value="" />
				</p>
				 -->
				<p>
					<label>移动电话：</label>
					<input type="text" name="Ta03_user.MOBILE_TEL" style="width:120px;" value="${user.mobile_tel }" class="required" />
				</p>				
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input style="width: 30px" type="radio" name="Ta03_user.SEX" value="男" <c:if test="${user.sex=='男'||empty dept }">checked</c:if> />
					男
					<input style="width: 30px" type="radio" name="Ta03_user.SEX" value="女" <c:if test="${user.sex=='女' }">checked</c:if> />
					女
				</p>
				<p>
					<label>固定电话：</label>
					<input type="text" name="Ta03_user.FIX_TEL" style="width:120px;" value="${user.fix_tel }" />
				</p>
				<p>
					<label>电子邮件：</label>
					<input type="text" name="Ta03_user.EMAIL" style="width:120px;" value="${user.email }" />
				</p>
				<p>
					<label>所属地区：</label>
					<netsky:htmlSelect style="width:125" id="AREA_NAME"
						name="Ta03_user.AREA_NAME" objectForOption="areaList"
						valueForOption="name" showForOption="name"
						value="${user.area_name}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>所属部门：</label>
					<netsky:htmlSelect style="width:125" id="DEPT_ID"
						name="Ta03_user.DEPT_ID" objectForOption="deptList"
						valueForOption="id" showForOption="name"
						value="${user.dept_id}" extend="" extendPrefix="true" htmlClass="required"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea name="Ta03_user.REMARK" style="width:350px; height:40px;">${user.remark }</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">保存设置</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	
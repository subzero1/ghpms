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
		var originalpwd = '${user.passwd}';
		$("#AREA_NAME").change(function(){
				jilian('DEPT_ID','Ta01_dept.area_name',$("#AREA_NAME").val(),'id','name','name');
			})
		$("#submitbutton").click(function(){
				if ($("#newpwd").val()!=""){
					if ($("#newpwd").val()!=$("#repwd").val()){
						$("#newpwd").val("");
						$("#newrepwd").val("");
						alertMsg.info('对不起,两次输入的密码不一致!');
						return false;
					} else {
						$("#pwddate").val('<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>');
					}
				}else if($("#newpwd").val()==""){ 
					alertMsg.info('请您输入新密码！');
					return false;
				} if(/(\s)+/.test($("#newpwd").val())==true){
					alertMsg.info('密码不可以包含空格');
					return false;
				}
			})
	})
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="pwdSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,loadFileAreaAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta03_user" />
			<input type="hidden" id="pwddate" name="Ta03_user.LAST_PWD_DATE" value="${user.last_pwd_date }"/>
			<input type="hidden" name="Ta03_user.ID" value="${user.id}" />
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
					<input  type="text" name="Ta03_user.LOGIN_ID" style="width:120px;" value="${user.login_id }" readonly="readonly"/>
				</p>
				 
				<p>
					<label>原&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>
					<input type="password" id="pwd" name="Ta03_user.PASSWD" style="width:120px;" value="" />
				</p>
				<p>
					<label>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>
					<input type="password" id="newpwd" name="newpwd" style="width:120px;" value="" />
				</p>
				<p>
					<label>确认密码：</label>
					<input type="password" id="repwd" style="width:120px;" value="" />
				</p>
								
			   
				<div style="height:0px;"></div>
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
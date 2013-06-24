<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">

		<title>南京电信工程建设目标库管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<link rel="stylesheet" media="screen,projection" type="text/css" href="Images/index-images/style.css" />
		<script src="js/jquery/jquery-1.7.1.js" type="text/javascript"></script>
		<script src="js/jquery/jquery.cookie.js" type="text/javascript"></script>

		<!--居中显示start-->
		<script type="text/javascript" src="js/center-plugin.js"></script>
		<script>
		function logchk_onsubmit(){
		    if ($("#login_id").val()==""){
				alert("请输入用户名！");
			 	$("#login_id").focus();
			 	return false;
			}	
			 if ($("#passwd").val()==""){
				alert("请输入密码！");
			 	$("#passwd").focus();
			 	return false;
			}
			
			if($("#remember").attr("checked")){
				$.cookie("login_id", $("#login_id").val());
				$.cookie("passwd", $("#passwd").val());
			}else{
				$.cookie("login_id", null);
				$.cookie("passwd", null);
			}
			$.ajax({
				type: "POST",
				url: "login.do",
				data: "login_id=" + $("#login_id").val() + "&passwd=" + $("#passwd").val(),
				success: function(msg){
					if(msg != 'ok'){
						alert(msg);
					}else{
						$(window).attr("location","main.do");
					}
				}
			});
		}
		$(document).ready(function(){ 
			checkie();
			
			$('.login_main').center();	
			$("#login_id").focus();
			
			if($.cookie("login_id")!=null){
				$("#login_id").val($.cookie("login_id"));
				$("#passwd").val($.cookie("passwd"));
				$("#remember").attr("checked",true);
			}
		});
		
		function checkie(){
			var sys = navigator.appVersion;
			if(sys.split(';')[1]==' MSIE 6.0'){
				var msg = confirm('您的浏览器版本是6.0，是否升级？');
				if(msg){
					window.open('download.do?slave_id=1','','');
				}
			}
		}
		
		function getPwd(){
			var login_id = $.trim($("#login_id").val());
			if (login_id!=""){
				$("#wait").show();
				$.ajax({
					type: "post",
				  	url: "getPassowrd.do?login_id="+login_id,
				  	success: function(msg){
				  		$("#wait").hide();
				  		alert($.trim(msg));
					}
				})
				
			}else{
					alert("请输入您的用户名!");
					$("#login_id").focus();
			}
		}
		var count_etc = 0;
		setInterval("etc()",500);
		function etc(){
			if (count_etc == 4){
				count_etc = 0;
			}
			var s = "";
			for (var i=0;i<count_etc;i++){
				s += "."; 
			}
			count_etc++;
			$("#etc").html(s);
		}
	</script>
		<!--居中显示end-->

	</head>
	<body>
		<div class="login_main">
			<div class="login_top">
				<div class="login_title"></div>
			</div>
			<div class="login_middle">
				<div class="login_middleleft"></div>
				<div class="login_middlecenter">
					<form method="post" action="" class="login_form" onsubmit="javascript:logchk_onsubmit();return false;">
						<div class="login_user">
							用户名&nbsp;&nbsp;<input type="text" id="login_id" name="login_id"
								value="${param.login_id}" />
						</div>
						<div class="login_pass">
							密   码&nbsp;&nbsp;<input type="password" id="passwd" name="passwd" />
						</div>
						<div class="clear"></div>
						<div class="login_button">
							<div class="login_button_right">
								<input type="checkbox" id="remember" name="remember" value="yes">
								记住我 &nbsp;&nbsp;<a href="javascript:getPwd();">找回密码</a>
								<div style="display:none;color: grey" id="wait">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在处理,请稍候<label id="etc"></label></div>
							</div>
							<div class="login_button_left">
								<input type="submit" value="" onfocus="this.blur()"
									class="input" />
							</div>
							<div class="clear"></div>
						</div>
					</form>
				</div>
				<div class="login_middleright"></div>
				<div class="clear"></div>
			</div>
			<div class="login_bottom">
				<div class="login_copyright">
					研发单位：网天信息技术有限公司 | 服务热线：022-83946861/62  <a href="download.do?slave_id=1" target="_blank" style="color:#c00;font-weight:bold;">[ 下载 IE7 安装包 ]</a>
				</div>
			</div>
		</div>
	</body>
</html>

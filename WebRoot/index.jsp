<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>歌华有线工程管理系统_HGPMS</title>
		<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
		<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
		<link rel="stylesheet" media="screen,projection" type="text/css" href="Images/index-images/style.css" />
		<script src="js/jquery/speedup.js" type="text/javascript"></script>
		<script src="js/jquery/jquery-1.7.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/center-plugin.js"></script>
		<script src="js/jquery/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
		<script src="js/jquery/jquery.bgiframe.js" type="text/javascript"></script>
		<script src="js/jquery/jquery-ui-1.8.15.custom.min.js" type="text/javascript"></script>
				
		<script src="js/dwz.src/dwz.core.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.util.date.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.validate.method.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.regional.zh.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.barDrag.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.drag.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.tree.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.accordion.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.ui.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.theme.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.switchEnv.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.alertMsg.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.contextmenu.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.navTab.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.tab.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.resize.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.dialog.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.dialogDrag.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.sortDrag.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.cssTable.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.stable.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.taskBar.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.ajax.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.pagination.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.database.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.datepicker.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.effects.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.panel.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.checkbox.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.history.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.combox.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.print.js" type="text/javascript"></script>
		
		<script src="js/dwz.src/dwz.regional.zh.js" type="text/javascript"></script>
		
		
		<script src="js/jquery/jquery.jqprint.0.3.js" type="text/javascript"></script>
		
		<script src="js/src/netsky.ui.js" type="text/javascript"></script>
		<script src="js/src/netsky.buttonControl.js" type="text/javascript"></script>
		<script src="js/src/netsky.js" type="text/javascript"></script>
		<script src="js/src/netsky.searchReport.js" type="text/javascript"></script>
		<script src="js/src/netsky.formValidator.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/CutPic.js"></script>
		<script type="text/javascript" src="js/src/netsky.cascade.js"></script>
		<script type="text/javascript" src="js/chart/highcharts.js"></script>
		<script type="text/javascript" src="js/Jcrop/jquery.Jcrop.min.js"></script>
		<script>
		function logchk_onsubmit(){
		    if ($("#login_id").val()==""){
				alertMsg.info("请输入用户名！");
			 	$("#login_id").focus();
			 	return false;
			}	
			 if ($("#passwd").val()==""){
				 alertMsg.info("请输入密码！");
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
					msg = $.trim(msg);
					if (msg == '请修改原始密码！' || msg == '密码已过期！'){
						var login_id = $("#login_id").val();
						alertMsg.confirm(msg,{
							okCall:function(){
								$.pdialog.open("dispath.do?url=personalization/pwdSetting.jsp", "", "密码修改", {mask:true,minable:false,width:320,height:240,data:{"login_id":login_id}});
							}
						});
					}else if(msg != 'ok'){
						alertMsg.error(msg);
					}else{
						$(window).attr("location","main.do");
					}
				}
			});
		}
		$(document).ready(function(){ 
		DWZ.init("dwz.frag.xml", {
		//loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"index.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
				$("body").append(DWZ.frag["dwzFrag"]);

	if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
		try {
			document.execCommand("BackgroundImageCache", false, true);
		}catch(e){}
	}
	//清理浏览器内存,只对IE起效
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}

	$(window).resize(function(){
		initLayout();
		$(this).trigger(DWZ.eventType.resizeGrid);
	});

	var ajaxbg = $("#background,#progressBar");
	ajaxbg.hide();
	$(document).ajaxStart(function(){
		ajaxbg.show();
	}).ajaxStop(function(){
		ajaxbg.hide();
	});
	
	$("#leftside").jBar({minW:150, maxW:700});
	
	if ($.taskBar) $.taskBar.init();
	if ($.fn.switchEnv) $("#switchEnvBox").switchEnv();
	if ($.fn.navMenu) $("#navMenu").navMenu();
		
		}
	});
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
					window.open('download.do?slave_id=827','','');
				}
			}
		}
		
	</script>
		<!--居中显示end-->
	<style>#splitBar{display:none;}</style>
	</head>
	<body>
		<div class="login_top">
			&nbsp;
		</div>
		<div class="login_main"><div class="top_title"></div>
			<div class="login_middle">
					<form method="post" action="" class="login_form" onsubmit="javascript:logchk_onsubmit();return false;">
						<div class="login_user">
							<input type="text" id="login_id" name="login_id" value="${param.login_id}" />
						</div>
						<div class="login_pass">
							<input type="password" id="passwd" name="passwd" />
						</div>
						<div class="clear"></div>
						<div class="login_button">
							<div class="login_button_right">
								<input type="checkbox" id="remember" name="remember" value="yes">
								记住我
							</div>
							<div class="login_button_left">
								<input type="submit" value="" onfocus="this.blur()" class="input" />
							</div>
							<div class="clear"></div>
						</div>
					</form>
				<div class="clear"></div>
				<div class="login_copyright">
					研发单位：网天信息技术有限公司 | 服务热线：022-83946861/83946862
			</div>
		</div>
			<div class="login_bottom">
			</div>
	</body>
</html>

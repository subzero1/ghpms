<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>辅助工具</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.leftButton{
	    cursor:hand;
		margin:5px;
		float:left;
		width:150px;
		height:140px;
		border-color:Black;
		
	}
	.rightButton{
	    cursor:hand;
		margin:5px;
		float:left;
		width:150px;
		height:140px;
		border-color:Black;
	}
	#bottomComment{
		padding:10px;
	    margin:10px;
	    width:450px;
	    height:92px;
	    background-color:antiquewhite;
	    font:bold 13px 宋体;
	    line-height:20px;
	}
	</style>
  </head>
  <script src="js/jquery/jquery.corner.js" type="text/javascript"></script>
  <script type="">
	function setComment(flag){
			var obj = $("#bottomComment");
			var content = "";
			if(flag == '1'){
				content = "批量生成持久化对象。生成条件：<br/>1、tz07_dataobject_cfg.status=未处理";
			}
			else if(flag == '2'){
				content = "构造字段表[TA07]。生成条件：<br>1、对应的表单已经画完<br>2、ta06_module.extflag 包含[字段] <br> 3、ta06.extdesc 不包含[字段成功]";
			}
			else if(flag == '3'){
				content = "生成WORKLIST字段[TA06、TA07，MODULE_ID=0]。生成条件：<br>1、存在视图PROJECTINF";
			}
			else if(flag == '4'){
				content = "构造查询统计[TA08]。生成条件：<br>1、ta06_module.extflag 包含[统计] <br> 2、ta06.extdesc 不包含[统计成功]<br>3、\"复选框\"需被统计表字段注释包含[选项]<br>4、\"人员\"需要被统计表字段注释包含[人员]";
			}
			else if(flag == '5'){
				content = "构造表单保存配置文件。生成条件：<br>1、配置环境变量eclipse_workspace=xxx <br> 2、ta06_module.extflag 包含[配置保存] <br> 3、ta06.extdesc 不包含[配置保存成功]";
			}
			else if(flag == '6'){
				content = "根据系统表构造字段表[TA07]。生成条件：生成条件：<br>1、对应的表单已经画完<br>2、ta06_module.extflag 包含[字段二] <br> "
							+"3、ta06.extdesc 不包含[字段二成功]4、\"复选框\"需被统计表字段注释包含[选项],如果选项只包含是,否,则再加[XOR]<br>"
							+"5、\"人员\"需要被统计表字段注释包含[人员]";
			}
			else if(flag == ''){
				content = "";
			}
			obj.html(content);
			obj.corner();
		}

	function execAction(flag){
		var _url = "";
		if(flag == 1){
			_url = "batchAutoCreateJavaXml.do";
		}
		else if(flag == 2){
			_url = "generalFormField.do";
		}
		else if(flag == 3){
			_url = "generalListField.do";
		}
		else if(flag == 4){
			_url = "generalReport.do";
		}
		else if(flag == 5){
			_url = "generalSaveCfg.do";
		}
		else if(flag == 6){
			_url="generalFormFieldBySys.do";
		}
		alertMsg.confirm("继续操作有可能会更改系统设置，导致系统瘫痪，确认继续吗？", {
			okCall: function(){
				$.ajax({
					type: "POST",
					url: _url,
					dataType:"json",
					success: function(json){
						if(json.statusCode == 200){
							alertMsg.correct(json.message);
						}
						else{
							alertMsg.error(json.message);
						}
						return false;
					},
					error: DWZ.ajaxError
				});
			}
		});
	}
  </script>
  <body onLoad="setCorner()">
		<div style="padding:10px; border-width:5px;  width:490px;height:400px;">
			<div class="leftButton" onmouseover="setComment(1)" onclick="execAction(1)"  style="background-image:url(Images/generalDo.png);"></div>
			<div class="leftButton" onmouseover="setComment(2)" onclick="execAction(2)" style="background-image:url(Images/generalField.png);"></div>
			<div class="leftButton" onmouseover="setComment(3)" onclick="execAction(3)" style="background-image:url(Images/generalListField.png);"></div>
			<div class="leftButton" onmouseover="setComment(4)" onclick="execAction(4)" style="background-image:url(Images/generalReport.png);"></div>
			<div class="leftButton" onmouseover="setComment(5)" onclick="execAction(5)" style="background-image:url(Images/generalSaveCfg.png);"></div>
			<div class="leftButton" onmouseover="setComment(6)" onclick="execAction(6)" style="background-image:url(Images/generalOther.png);"></div>
			<div id="bottomComment" > 说明</div>
		</div>
  </body>
</html>
<script type="">
		$(".leftButton").corner();
		$(".rightButton").corner();
		$("#bottomComment").corner();
</script>
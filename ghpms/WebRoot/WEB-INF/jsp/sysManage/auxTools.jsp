<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>��������</title>
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
	    font:bold 13px ����;
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
				content = "�������ɳ־û���������������<br/>1��tz07_dataobject_cfg.status=δ����";
			}
			else if(flag == '2'){
				content = "�����ֶα�[TA07]������������<br>1����Ӧ�ı��Ѿ�����<br>2��ta06_module.extflag ����[�ֶ�] <br> 3��ta06.extdesc ������[�ֶγɹ�]";
			}
			else if(flag == '3'){
				content = "����WORKLIST�ֶ�[TA06��TA07��MODULE_ID=0]������������<br>1��������ͼPROJECTINF";
			}
			else if(flag == '4'){
				content = "�����ѯͳ��[TA08]������������<br>1��ta06_module.extflag ����[ͳ��] <br> 2��ta06.extdesc ������[ͳ�Ƴɹ�]<br>3��\"��ѡ��\"�豻ͳ�Ʊ��ֶ�ע�Ͱ���[ѡ��]<br>4��\"��Ա\"��Ҫ��ͳ�Ʊ��ֶ�ע�Ͱ���[��Ա]";
			}
			else if(flag == '5'){
				content = "��������������ļ�������������<br>1�����û�������eclipse_workspace=xxx <br> 2��ta06_module.extflag ����[���ñ���] <br> 3��ta06.extdesc ������[���ñ���ɹ�]";
			}
			else if(flag == '6'){
				content = "����ϵͳ�����ֶα�[TA07]����������������������<br>1����Ӧ�ı��Ѿ�����<br>2��ta06_module.extflag ����[�ֶζ�] <br> "
							+"3��ta06.extdesc ������[�ֶζ��ɹ�]4��\"��ѡ��\"�豻ͳ�Ʊ��ֶ�ע�Ͱ���[ѡ��],���ѡ��ֻ������,��,���ټ�[XOR]<br>"
							+"5��\"��Ա\"��Ҫ��ͳ�Ʊ��ֶ�ע�Ͱ���[��Ա]";
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
		alertMsg.confirm("���������п��ܻ����ϵͳ���ã�����ϵͳ̱����ȷ�ϼ�����", {
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
			<div id="bottomComment" > ˵��</div>
		</div>
  </body>
</html>
<script type="">
		$(".leftButton").corner();
		$(".rightButton").corner();
		$("#bottomComment").corner();
</script>
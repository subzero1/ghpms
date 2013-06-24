<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="css/upload.css" type="text/css" rel="Stylesheet" />
<script type="text/javascript">
	//回调函数
	function callback(url, width, height) {
		var imgD = document.getElementById('ImageDrag');
		var imgI = document.getElementById('ImageIcon');
		imgD.src = "upload/"+url + "?" + Math.round(Math.random() * 10000);
		imgI.src = "upload/"+url + "?" + Math.round(Math.random() * 10000);
		if(width>CANVAS_WIDTH){imgD.src.style="width="+CANVAS_WIDTH;imgI.src.style="width="+CANVAS_WIDTH;}
		if(height>CANVAS_HEIGHT){imgD.src.style="height="+CANVAS_HEIGHT;imgI.src.style="height="+CANVAS_HEIGHT;}
		$("#CurruntPicContainer").hide();
		document.getElementById('Step2Container').style.display = '';
		document.getElementById('save').style.display = '';
		document.getElementById("picture").value = url;
		setTimeout('initImage()',1000);
	}
	//校验上传文件
		$(function(){
			$("#picForm").submit(function() {
			//alert($.trim(location) == "");
			var location = document.getElementById('fuPhoto').value;
		if ($.trim(location) == "") {
			alertMsg.info('请选择上传的图片');
			return false;
		}
		var point = location.lastIndexOf(".");
		var type = location.substr(point);
		if (type == ".jpg" || type == ".JPG" || type == ".jpeg" || type == ".JPEG") {
			img = document.createElement("img");
			img.src = location;
			if (img.fileSize > 4194304) {
				alertMsg.info('上传的图片不能大于4M');
				return false;
			} else{
				return true;
			}
		} else {
			alertMsg.info('请上传JPG/JEPG格式的图片文件');
			return false;
		}
		return true;
		return false;
	})
		})
   </script>
</head>
<body>

<div>
	<div class="left">
		<!--当前照片-->
		<div id="CurruntPicContainer" >
			<div class="title">
				<b>当前照片</b>
			</div>
			<div class="photocontainer">
				<img id="imgphoto" style="border-width: 0px;" <c:choose>
				<c:when test="${fj.ftp_url != null}">
				src="download.do?slave_id=${fj.id}&random=<%=new Random().nextInt() %>"
				</c:when>
				<c:otherwise>
				src="Images/head.jpg" 
				</c:otherwise>
				</c:choose>
			 	/>
			</div>
		</div>
		<!--Step 2-->
		<div id="Step2Container" style="display:none;">
			<div class="title">
				<b>您可以拖动照片以裁剪满意的头像</b>
			</div>
			<div id="Canvas" class="uploaddiv">
				<div id="ImageDragContainer">
					<img id="ImageDrag" class="imagePhoto" src="" style="border-width: 0px;" />
				</div>
				<div id="IconContainer">
					<img id="ImageIcon" class="imagePhoto" src="" style="border-width: 0px;" />
				</div>
			</div>
			<div class="uploaddiv">
				<table>
					<tr>
						<td id="Min">
							<img alt="缩小" src="Images/_c.gif" onmouseover="this.src='Images/_c.gif';" onmouseout="this.src='Images/_h.gif';" id="moresmall" class="smallbig" />
						</td>
						<td>
							<div id="bar">
								<div class="child"></div>
							</div>
						</td>
						<td id="Max">
							<img alt="放大" src="Images/c.gif" onmouseover="this.src='Images/c.gif';" onmouseout="this.src='Images/h.gif';" id="morebig" class="smallbig" />
						</td>
					</tr>
				</table>
			</div>
			<form action="cutImage.do" method="post" id="saveHead" name="saveHead" onsubmit="return iframeCallback(this, loadFileAreaAjaxDone);">
				<input type="hidden" name="picture" id="picture" value="" />
				<div style="display:none;">
					图片实际宽度：<input name="txt_width" type="hidden" value="1" id="txt_width"/><br/>
					图片实际高度：<input name="txt_height" type="hidden" value="1" id="txt_height"/><br/>
					距离顶部：<input name="txt_top" type="hidden" value="82" id="txt_top"/><br/>
					距离左边：<input name="txt_left" type="hidden" value="73" id="txt_left"/><br/>
					截取框的宽：<input name="txt_DropWidth" type="hidden" value="120" id="txt_DropWidth"/>	<br/>
					截取框的高：<input name="txt_DropHeight" type="hidden" value="120" id="txt_DropHeight"/><br/>
					放大倍数：<input name="txt_Zoom" type="hidden" id="txt_Zoom" />
				</div>
			</form>
		</div>
	</div>
	
	<div class="right">
	<form name="picForm" id="picForm" action="faceUpload.do" method="post" enctype="multipart/form-data" target="hidden_frame">
		<!--Step 1-->
		<div id="Step1Container">
			<div class="title">
				<b>更换照片</b>
			</div>
			<div id="uploadcontainer">
				<div class="uploadtooltip">
					文件大小不能超过4MB，格式为 <b>JPG</b> 或 <b>JPEG</b>。
				</div>
				<div class="uploaddiv">
					<input type="file" name="fuPhoto" id="fuPhoto" title="选择照片" />
				</div>
				<div class="uploaddiv">
					<input type="submit" name="btnUpload" value=" 上 传 " id="btnUpload" />
					<iframe name='hidden_frame'	id="hidden_frame" style='display:none'></iframe>
				</div>
						<div class="uploaddiv" id="save" style="display: none;">
							<input type="button" value=" 保存头像 " onclick="javascript:$('#saveHead').submit();"/>
							<input type="button" value=" 取 消 " onclick="javascript:$.pdialog.closeCurrent();" />
						</div>
					</div>
		</div>
	</form>
	</div>
	
</div>

</body>
</html>
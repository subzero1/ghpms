<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function del(obj){
	 document.messagewrite.reader_name.value='';
	 document.messagewrite.reader_tel.value='';
	 document.messagewrite.reader_name1.value='';
	 document.messagewrite.additionTels.value='';
	}
	function selectToUser(){
		var selectO = $("#user_list option:selected");
		selectO.each(function(){
		if($("#reader_name").val()==""){
			$("#reader_name").val($(this).text());
			$("#reader_name1").val($(this).text());
			$("#reader_tel").val($(this).val());
			for(var i = 1 ; i < $(this).val().split(",").length ; i++){
			$("#reader_name").val($("#reader_name").val()+ "；" +$(this).text());
			}
		}else{
			$("#reader_name1").val($("#reader_name1").val()+"；"+$(this).text());
			$("#reader_name").val($("#reader_name").val() + "；" + $(this).text());
			$("#reader_tel").val($("#reader_tel").val() + "," + $(this).val());
			for(var i = 1 ; i < $(this).val().split(",").length ; i++){
			$("#reader_name").val($("#reader_name").val()+ "；" +$(this).text());
			}
		}
		});
	}
	$(function(){
		//及连菜单
		$("#area").change(function(){
			jilian('dept','Ta01_dept.area_name',$("#area").val(),'id','name','name');
			$("#dept").change();
		})
		$("#dept").change(function(){
			jilian('user_list','Ta03_user.dept_id',$("#dept").val(),'mobile_tel','name','name');
		})
		
		$("#submitbutton").click(function(){
			if(($("#reader_tel").val()==""||$("#reader_tel").val()==null)&&($("#additionTels").val()==""||$("#additionTels").val()==null)){
				alertMsg.info('收件人不能为空!');
				return;
			}
			$("#send_flag").val("1");
			$("#messagewrite").submit();
		})	
	})
</script>


<div class="page">
	<div class="pageContent">
		<form name="messagewrite" id="messagewrite" action="MessageToMobile.do" class="pageForm required-validate" method="post" onsubmit="return validateCallback(messagewrite, dialogAjaxDone);">
			<input type="hidden" id="send_flag" name="send_flag"/>
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_navTabId" value=""/>
			<input type="hidden" name="_forwardUrl" value=""/>
			<!-- left -->
			<div style="width:420px;float:left;padding:5px !important;display:inline; overflow-x:hidden;overflow-y:auto;" layoutH="50">
				<table width="400" class="read" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" id="send_message">
					<tr>
						<th width="80px">发件人：</th>
						<td>${user.name}&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th>收件人：</th>
						<td>
						<input type="hidden" style="width:85%" id="reader_name" name="reader_name" readOnly value="${reader_name }"/><input type="text" style="width:85%" id="reader_name1" name="reader_name1" readOnly value="${reader_name1 }"/><input type="hidden" id="reader_tel" name="reader_tel" value="${reader_tel }"/>&nbsp;<img src="Images/trash.gif" onclick="javascript:del(this);" style="cursor:pointer" title="清空内容" />
						</td>
					</tr>
					
					<c:if test="${empty reader_name}">
					<tr><th>电 话：</th>
						<td><input type="text" id="additionTels" name="additionTels" style="width:170px;"/>请用空格,逗号或分号隔开</td>
						</tr></c:if>
					
					<tr>
						<th>内&nbsp;&nbsp;容：</th>
						<td><textarea name="content" id="content" style='width:96%;height:160px' class="required" comments="内容">${content }</textarea></td>
					</tr>
				</table>
			</div>
			<!-- right -->
			<div style="width:200px;text-align:right;padding:5px; 5px;">
					 <select name="area" id="area" style="width:80px;">
				    	<c:forEach var="area" items="${areaList}">
							<c:choose>
								<c:when test="${user.area_name == area.name}">
									<option selected value="${area.name}">${area.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${area.name}">${area.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				    </select>
				    
				    <select name="dept" id="dept" style="width:115px;">
				    	<c:forEach var="dept" items="${user_dept_list}">
							<c:choose>
								<c:when test="${user_dept_id == dept[0]}">
									<option selected value="${dept[0]}">${dept[1]}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept[0]}">${dept[1]}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				    </select>
				    <select id="user_list" name="user_list" multiple="1" type="select-multiple" style="width:198px;height:225px;" ondblclick="javascript:selectToUser();">
					<c:forEach var="user" items="${user_list}">
						<option value="${user.mobile_tel }">${user.name }</option>
					</c:forEach>
					</select>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submitbutton">发 送</button></div></div></li>	
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>
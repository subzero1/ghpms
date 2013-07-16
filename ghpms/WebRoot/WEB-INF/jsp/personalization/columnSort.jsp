<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<script type="text/javascript">
var width=0;
function moveTop(){
    move("t_sta", "top")
}

function moveBottom(){
    move("t_sta", "bottom")
}

function moveUp(){
    move("t_sta", "up")
}

function moveDown(){
 	move("t_sta", "down")
}
$(function(){
	var selectO = $("#t_sta option");
	selectO.each(function(){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width += tmp;
	});
	$("#configW").val(width);
	$("#screenW").val(document.body.clientWidth-200);
	$("#clearbutton").click(function(){
		$("#clear").val("yes");
		$("#form1").submit();
	})
})
function changeWidth(obj,obj1){
	var selectO = $("#"+obj+" option:selected");
	selectO.each(function(){
		if (obj == "s_sta"){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width += tmp;
		} else if (obj == "t_sta"){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width -= tmp;
		}
	});
	if (width>=parseInt($("#screenW").val())){
		$("#configW").css("color","red");
	}else {
		$("#configW").css("color","black");
	}
	$("#configW").val(width);
	moveAct(obj,obj1);
}
function changeModule(_this){ 
 $.pdialog.open('sysManage/columnSort.do?module_id='+$(_this).val(),'column', '字段排序',{mask:true, width:460, height:345});
}
</script>
<div class="page">
	<div class="pageContent">
		<form id="form1" action="sysManage/saveColumnSort.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_sta');"> 
			<input type="hidden" name="module_id" value="${param.module_id }"/>
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td colspan="2">
						 <netsky:htmlSelect name="module_name"  objectForOption="modules" style="width:128px;" valueForOption="id" showForOption="name" extend="-----------,101" extendPrefix="true" onChange="changeModule(this)" value="${param.module_id}"  htmlClass="td-select"/></td>
					</tr>
					<tr>  
						<td>
							<select id="t_sta" name="t_sta" multiple=1 type=select-multiple   ondblclick="javascript:changeWidth('t_sta','s_sta')" style="width:320px;height:240px;">
							<option style="background-color:#ccc;" disabled >-------------------------</option>
							<c:forEach var="obj" items="${configList}">
								<option value="${obj.id }">${obj.comments }</option>
							</c:forEach>
							</select>
						</td>
						<td align="right">
							<input type="button" onclick="javascript:moveTop()" value="行首"/><br/>
							<input type="button" onclick="javascript:moveBottom()" value="行尾"/><br/>
							<input type="button" onclick="javascript:moveUp()" value="上移"/><br/>
							<input type="button" onclick="javascript:moveDown()" value="下移"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>

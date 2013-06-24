<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	function addComments(){
		$("#mainbody").append("<tr><td><input type='hidden' name='Ta22_user_idea.USER_ID' value='${user.id}'/>\
				<input type='hidden' name='Ta22_user_idea.ID' value='' />\
				<select name='Ta22_user_idea.CHECK_RESULT' style='width:98%;'>\
					<option value='4'>同意</option>\
					<option value='5' >修改后再报</option>\
					<option value='6' >不同意</option>\
				</select></td>\
				<td><input type='text'  class='required' comments='审批意见' name='Ta22_user_idea.CHECK_IDEA' style='width:98%'/></td>\
				<td><a href='#' onclick='javascript:delComments(this);'  class='btnDel'><span>删除</span></a></td></tr>");
	}
	function delComments(obj){
		var $tr = $(obj).closest("tr");
		if($tr){
			var valid = false;
			$tr.find(":input").each(function(){
				if(this.name != "Ta22_user_idea.ID"){
					$(this).val("");
				}else{
					if($(this).val()!="")
						valid = true;
				}
			});
			if(valid)
				$tr.hide();
			else
				$tr.remove();
		}
	}
	function saveComments(){
		$("#form1").submit();
	}
	//点DEL DEL 隐 ROLLBACK现 JQUERY 选择器 实现???
</script>

<div class="page">
	<div class="pageContent">
		<form id="form1" action="save.do" method="post" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta22_user_idea"/>
	
		<div class="pageFormContent" layouth="58">
			<table class="table" width="100%">
				<thead>
					<tr>
						<th style='width:90px;'>审批结果</th>
						<th style='width:580px;'>审批意见</th>
						<th style='width:30px;'>&nbsp;</th>
					</tr>
				</thead>
				<tbody id="mainbody">
					<c:forEach var="ta22" items="${ta22List}">
					<tr>
						<td>
							<input type="hidden" name="Ta22_user_idea.USER_ID" value="${ta22.user_id}"/>
							<input type="hidden" name="Ta22_user_idea.ID" value="${ta22.id}" />
							<select name="Ta22_user_idea.CHECK_RESULT" style="width:98%">
								<option value="4" <c:if test="${ta22.check_result==4 }">selected</c:if>>同意</option>
								<option value="5" <c:if test="${ta22.check_result==5 }">selected</c:if>>修改后再报</option>
								<option value="6" <c:if test="${ta22.check_result==6 }">selected</c:if>>不同意</option>
							</select>
						</td>
						<td>
							<input type="text" style="width:98%"  class="required" name="Ta22_user_idea.CHECK_IDEA" value="${ta22.check_idea }" comments="审批意见"/>
						</td>
						<td><a href="#" onclick="javascript:delComments(this);"  class="btnDel"><span>删除</span></a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<div class="button" style="float:left;"><div class="buttonContent"><button type="Button" onclick="javascript:addComments();">增 加</button></div></div>
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>



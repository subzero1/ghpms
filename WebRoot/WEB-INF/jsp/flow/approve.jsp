<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script language="javascript">
function selIdea(sel){
	var $idea = $("option:selected",$(sel));
	if($idea.val() != ""){
		$(":radio[name=check_result]").each(function(){
			if(this.value ==  $idea.val()){
				$(this).trigger("click");
			}
		});
		$(":input[name=check_idea]").val($idea.text());
	}
}
function selResult(idea_str){
	$(":input[name=check_idea]").val(idea_str);
}

function saveTemplate(butt){
	var $form = $(butt).closest("form");
	if($("#check_idea",$form).val().trim() == ""){
		alertMsg.info("审批意见不能为空!");
		return;
	}
	
	
	$.ajax({
		type: 'POST',
		url:'ajaxSaveCommentsTemplate.do',
		data:$form.serializeArray(),
		dataType:"html",
		cache: false,
		success: saveTemplateCallback,
		error: DWZ.ajaxError
	});
	
}
function saveTemplateCallback(html){
	if(html == ""){
		alertMsg.info("保存模板失败,请联系系统管理员!");
	}else{
		alertMsg.correct("保存模板成功!");
		$("#yjxz").append(html);
	}
	
}

function approveCallBack(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		setTimeout(function(){$.pdialog.closeCurrent();}, 100);
		docReload();
	}
}
//alert($('#auto_form #doc_id',navTab.getCurrentPanel()).attr("value"));
//alert("abc,12,34".search("12"));
$.get("approve.do");
</script>

<div class="page">
	<div class="pageContent">
		<form action="approve.do" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this,approveCallBack);">
			<input type="hidden" name="project_id" value="${param.project_id}"/>
			<input type="hidden" name="node_id" value="${param.node_id }"/>
			<input type="hidden" name="doc_id" value="${param.doc_id }"/>
			<input type="hidden" name="module_id" value="${param.module_id}"/>
			<input type="hidden" name="user_id" value="${param.user_id}"/>
			<input type="hidden" name="opernode_id" value="${param.opernode_id}"/>
			<input type="hidden" name="_navTabId" value="autoform${param.module_id}${param.doc_id}"/>
			
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>审 批 人：</label>
					<input type="text" name="useraa" value="${user.name}" disabled style="width:60px;" class="required" comments="审批人"/>
				</p>
				<p>
					<label>审批时间：</label>
					<input type="text" name="oper_time"  value="<fmt:formatDate value="${curDate}" pattern="yyyy-MM-dd HH:mm"/>"  disabled style="width:100px;"/>
				</p>
				<p>
					<label>审批结果：</label>
					<c:forEach var="item" items="${checkTypeList}">
						<input name="check_result" type="radio" value="${item}" onclick="javascript:selResult('${checkType[item]}')" <c:if test="${tb17.check_result == item||item==4}">checked</c:if> />${checkType[item]}
					</c:forEach>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>审批意见：</label>
					<textarea type="text" id="check_idea" name="check_idea" style="width:265px;height:70px;" class="required" comments="审批意见"><c:out value="${tb17.check_idea}" default="${default_idear}"></c:out> </textarea>
				</p>
				<p>
					<label>意见选择：</label>
					<select name="yjxz" id="yjxz" onchange="javascript:selIdea(this);" style="width:270px;">
						<option value=""  selected>----------常用意见选择----------</option>
						<c:forEach var="idea" items="${ideaList}">
						<option value="${idea.check_result}">${idea.check_idea}</option>
						</c:forEach>
					</select>
				</p>
			</div>
			<div class="formBar">
				<div class="button" style="float:left;"><div class="buttonContent"><button type="Button" onclick="javascript:saveTemplate(this);">保存到模板</button></div></div>
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交审批</button></div></div></li>	
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>
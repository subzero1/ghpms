<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script language="javascript">
function check(){
	var str = "";
	$("input:checked",$("#keySelect")).each(function(){
		if(str=="")
			str = $(this).val();
		else
			str = str + "," + $(this).val();
	});	
	setVal(str);
}

function setVal(str){
	var source_input = $("input[name=${param.name}]");
	if(source_input.length>0){
		source_input.val(str);
		source_input.focus();
	}
	setTimeout(function(){$.pdialog.closeCurrent();}, 100);
}

</script>

<div class="page">
	<div id="keySelect" class="pageContent">
		<br/><input type="checkbox" group="keys" value="" class="checkboxCtrl"/><span style="color:red;">全选</span>
			<c:set var="t_rows" value="${fn:length(result)}"/>
			<table border="0" width="100%" layouth="90">
			<c:forEach var="i" begin="0" end="${t_rows}" step="2">
			<tr>
				<td>
					<c:if test="${not empty result[i] }">
						<input type="checkbox" name="keys" value="${result[i] }"/>${result[i]}
					</c:if>
				</td>
				<td>
					<c:if test="${not empty result[i+1] }">
						<input type="checkbox" name="keys" value="${result[i+1] }"/>${result[i+1]}
					</c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
		
		<br/>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:check();">确 定</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:setVal('');">清 空</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</div>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
function setCheckResult(){
	if ($("#all",$.pdialog.getCurrent()).attr("checked")=="checked"){
			alertMsg.info("查阅范围为全部，该操作无效。");
		}
	var json = arguments[0], result="";
	//alert(json.checked);
	$(json.items).each(function(i){
		if (this.name==undefined){
			return;
		}
		var cyfw = $("#cyfw",$.pdialog.getCurrent()).val();
		if (json.checked){
			if (cyfw.indexOf(this.value)==-1){
				if (cyfw!=""){
					cyfw+="，"; 
				}
				cyfw+=this.value;
			}
		}else{
			if (cyfw.indexOf(this.value)!=-1){
				cyfw = cyfw.replaceAll(this.value,"");
				cyfw = cyfw.replaceAll("，，","，");
				if (cyfw.indexOf("，")==0){
					cyfw=cyfw.substring(1);
				}
				if (cyfw.lastIndexOf("，")==cyfw.length-1){
					cyfw=cyfw.substring(0,cyfw.length-1);
				}
			}
		}
		$("#cyfw",$.pdialog.getCurrent()).val(cyfw);
	});
}
function setCylb(cylb){
	//设置查阅类别，清空查阅范围，清空所有CHECKBOX
	$("#cylb",$.pdialog.getCurrent()).val(cylb);
	$("#cyfw",$.pdialog.getCurrent()).val("");
	$(".chk",$.pdialog.getCurrent()).attr("checked",false);
}
$(function(){
	$(".chk",$.pdialog.getCurrent()).change(function(){
		if ($("#all",$.pdialog.getCurrent()).attr("checked")=="checked"){
			alertMsg.info("查阅范围为全部，该操作无效。");
		}
		var cyfw = $("#cyfw",$.pdialog.getCurrent()).val();
		if ($(this).attr("checked")=="checked"){
			if (cyfw.indexOf($(this).val())==-1){
				if (cyfw!=""){
					cyfw+="，"; 
				}
				cyfw+=$(this).val();
			}
		}else{
			if (cyfw.indexOf($(this).val())!=-1){
				cyfw = cyfw.replaceAll($(this).val(),"");
				cyfw = cyfw.replaceAll("，，","，");
				if (cyfw.indexOf("，")==0){
					cyfw=cyfw.substring(1);
				}
				if (cyfw.lastIndexOf("，")==cyfw.length-1){
					cyfw=cyfw.substring(0,cyfw.length-1);
				}
			}
		}
		$("#cyfw",$.pdialog.getCurrent()).val(cyfw);
	});
	$("#allspan",$.pdialog.getCurrent()).click(function(){
		$("#all",$.pdialog.getCurrent()).click();
	});
	$("#submitButton",$.pdialog.getCurrent()).click(function(){
		if ($("#all",$.pdialog.getCurrent()).attr("checked")=="checked"){
			$("#cyfw").val("全部");
			$("#cylb").val("0");
		}
		$.bringBack({'cyfw':$("#cyfw").val(),'cylb':$("#cylb").val()});
	});
});
</script>
<input type="hidden" id="cylb" name="cylb" value="3" />
<input type="hidden" id="cyfw" name="cyfw" value="" />
<div>
<input type="checkbox" id="all"/><span id="allspan" style="cursor:pointer;color:blue">全部</span>
</div>
<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:setCylb('3');"><span>人 员</span> </a></li>
					<c:if test="${empty param.parentCylb || param.parentCylb == '1' || param.parentCylb == '0'}"><li><a href="javascript:setCylb('1');"><span>部 门</span> </a></li></c:if>
					<c:if test="${empty param.parentCylb || param.parentCylb == '2' || param.parentCylb == '0'}"><li><a href="javascript:setCylb('2');"><span>岗 位</span> </a></li></c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:260px;">
			<div id="ry_tab">
				<ul class="tree treeCheck collapse" oncheck="setCheckResult">
					<c:forEach items="${ta01AllList}" var="ta01">
						<c:if test="${not empty deptUserMap[ta01.id]}">
							<li><a>${ta01.name }</a>
							<ul>
							<c:forEach items="${deptUserMap[ta01.id]}" var="ta03">
							<li><a tname="ry_chk" tvalue="${ta03.name }">${ta03.name }</a></li>
							</c:forEach>
							</ul>
						</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<c:if test="${empty param.parentCylb || param.parentCylb == '1' || param.parentCylb == '0'}">
			<div id="bm_tab">
				<ul>
					<c:forEach items="${ta01List}" var="ta01">
						<li><input class="chk" type="checkbox" name="bm_chk" value="${ta01.name }"/>${ta01.name }</li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
			<c:if test="${empty param.parentCylb || param.parentCylb == '2' || param.parentCylb == '0'}">
			<div id="gw_tab">
				<ul>
					<c:forEach items="${ta02List}" var="ta02">
						<li><input class="chk" type="checkbox" name="gw_chk" value="${ta02.name }"/>${ta02.name }</li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submitButton">确 认</button></div></div></li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div>
			</li>
		</ul>
	</div>
</div>



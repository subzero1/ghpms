<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
<!--

function helpSave(butt){
	
	//光标置入列表输入域(解决含有xheditor编辑器的navtab关闭后,失掉焦点问题)
	$input = $("#tz06_content", navTab.getCurrentPanel());
	$input.focus();

	//form提交
	$form = $(butt).closest("form");
	$form.submit();
}

//-->
</script>
<style>
<!--
.pageFormContent div{clear:none;}
.unit{clear:both;}
-->
</style>
<div
	style="display: block; overflow: hidden; padding: 0 10px; line-height: 21px;">

	<div class="panel" style="width: 96%;; float: left; margin: 10px">
		<h1>
			在线帮助维护
		</h1>
		<div>
			<form method="post" action="help/ajaxHelpSave.do"
				class="pageForm required-validate"
				onsubmit="return validateCallback(this,navTabAjaxDone)">
				<input type="hidden" id="tableInfomation" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Tz06_help" keep="true" />
				<input type="hidden" name="Tz06_help.ID" value="<c:out value="${tz06.id}" default="${param.id}"/>" keep="true" />
				<input type="hidden" name="_callbackType" value="forward" keep="true" />
				<input type="hidden" name="_forwardUrl" value="help/helpList.do?help_id=${tz06.id}" keep="true" />
				<input type="hidden" name="_navTabId" value="templateList" keep="true" />
				<input type="hidden" name="Tz06_help.RECORDOR" id="Tz06_help.RECORDOR" value="<c:out value="${tz06.recordor }" default="${user.name }"/>" />
				<input type="hidden" name="Tz06_help.RECORD_DATE" id="Tz06_help.RECORD_DATE" value="<c:choose><c:when test="${empty tz06.record_date}">${now}</c:when><c:otherwise><fmt:formatDate value="${tz06.record_date}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>" />
				<div class="pageFormContent" layoutH="138">
					<div class="unit">
						<label>
							标题：
						</label>
						<input id="Tz06_help.TITLE" type="text" name="Tz06_help.TITLE" value="${tz06.title}" class="required" size="125" />
					</div>
					<div class="unit">
						<label>
							关键字：
						</label>
						<input id="Tz06_help.KEYS" type="text" name="Tz06_help.KEYS" value="${tz06.keys}"  size="125" />
					</div>
					<div style="clear:none">
						<label>
							解决方案：
						</label>
						<div class="unit">
							<textarea class="editor" id="tz06_content" name="Tz06_help.CONTENT" rows="17"
							cols="80" tools="simple" upLinkUrl="upload.do"
							upLinkExt="zip,rar,txt" upImgUrl="ajaxXhEditorUpload.do"
							upImgExt="jpg,jpeg,gif,png" upFlashUrl="upload.do"
							upFlashExt="swf" upMediaUrl="upload.do" upMediaExt:"avi">${tz06.content}</textarea>
						</div>
					</div>
					<div class="divider"></div>
				</div>
				<div class="formBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button" onclick="javascript:helpSave(this);">
										提 交
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="javascript:navTab.openTab('helpDisp', 'help/helpDisp.do?act=view&id=${tz06.id}', { title:'在线帮助编辑预览', flesh:false, data:{} });">
										预 览
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="javascript:navTab.closeCurrentTab();">
										取 消
									</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="form1" name="form1" action="getPassowrd.do" method="post" onsubmit="return validateCallback(this, dialogAjaxDone)">
<div style="width:260px;">
 <div class="scroll-body" style="width:250px;height:100px;">
	<table width="250px" border="1" cellspacing="0" cellpadding="0" style="border-color:#000000;;border-collapse:collapse;">
		<tr>
			<td height="25" align="center" class="required">请输入您的工号：</td>
		</tr>
		<tr>
			<td height="40" valign="bottom"><input type="text" name="login_id" id="login_id" value = ""></td>
		</tr>
	</table>
</div>

<div id='button-div' style="margin-top:18px;">
	<input class='button-alertl f-right' type='submit' value='找回密码' />
	<input class="button-alert f-left" type="button" value="取 消" onclick="javascript:$.pdialog.closeCurrent();"/>
</div>
</div>
</form>

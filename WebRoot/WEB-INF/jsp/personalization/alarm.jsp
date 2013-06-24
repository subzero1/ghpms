<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
function click_check(obj,i){
    var chk=false;
    var value_t;
    if (obj.checked){
	  chk = true;
	  value_t=1;
	}
    else{
    	 value_t=0;   		
    }
    
    document.getElementsByName("Ta27_user_remind.MOBILE_FLAG")[i].value=value_t;
}

function click_check1(obj,i){
    var chk=false;
    var value_t;
    if (obj.checked){
	  chk = true;
	  value_t=1;
	}
    else{
    	 value_t=0;   		
    }
    
    document.getElementsByName("Ta27_user_remind.MESSAGE_FLAG")[i].value=value_t;
}

</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="alarm.do?save=yes" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta27_user_remind"/>
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta23_personalization"/>
		<input type="hidden" name="dispatchStr" value="/alarm.do"/>
		<input type="hidden" name="validate" value=""/>
	
		<div class="pageFormContent" layoutH="53">
			<table width="440" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
				<tr>
				    <td width="100%" colspan="2" class="t-left"></td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="t-left">1、预警、超时文档提醒：&nbsp;&nbsp;
									<input type="checkbox" name="mobile_flag1" id="mobile_flag1" onclick="javascript:click_check(this,0);" <c:if test="${mobile_flag1==1}">checked</c:if> value="1" />&nbsp;短信提醒&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="Ta27_user_remind.ID" value=""/>
									<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
									<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="1"/>
									<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag1 }"/>
									<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag1 }"/>
									
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="t-left">2、待办、回复文档提醒：&nbsp;&nbsp;
									<input type="checkbox" name="mobile_flag2" id="mobile_flag2" onclick="javascript:click_check(this,1);" <c:if test="${mobile_flag2==1}">checked</c:if> value="1" />&nbsp;短信提醒&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="Ta27_user_remind.ID" value=""/>
									<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
									<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="2"/>
									<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag2 }"/>
									<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag2 }"/>
									
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="t-left">
						<ul>
							<c:forEach var="alarm_list" items="${alarm_list}">
								<li style="display:inline;width:105px;float:left;font-size:12px;">
									<input type="checkbox" name="po_id" <c:if test="${fn:contains(obj_new,alarm_list.id)}">checked</c:if> value="${alarm_list.id}"/>${alarm_list.name}
				 				</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="t-left" height="30px;">3、在线提问、权限申请处理完成提醒：&nbsp;&nbsp;
									<input type="checkbox" name="mobile_flag3" id="mobile_flag3" onclick="javascript:click_check(this,2);" <c:if test="${mobile_flag3==1}">checked</c:if> value="1" />&nbsp;短信提醒&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="Ta27_user_remind.ID" value=""/>
									<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
									<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="3"/>
									<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag3 }"/>
									<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag3 }"/>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="t-left"></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">设置完成,提交</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>	

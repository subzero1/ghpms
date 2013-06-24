<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript">
	//保存节点常用人员配制
	function saveConfig(obj){
	    //设置发送可选对像
		$("#_selectuser #select-options").html($("#_sendUserTemplate #_userids").html());
		//ajax调用保存
		$form = $(obj).parents("form");
		$.ajax({
			type: $form.attr("method") || 'POST',
			url:$form.attr("action"),
			data:$form.serializeArray(),
			dataType:"text",
			cache: false,
			success: _saveConfig,
			error: DWZ.ajaxError
		});
		
		//显示div 切换
		$("#_sendUserTemplate").hide();$("#_selectuser").show();
	}
	function _saveConfig(data){
	    //设置发送可选对像
		if(data =='保存成功'){
			//显示div 切换
			$("#_sendUserTemplate").hide();$("#_selectuser").show();
		}
		alertMsg.info(data);
	}

//保存节点常用人员配制
	function ok_send(obj){
		$form = $(obj).closest("form");
		//设置隐藏域 从autoForm copy 过来
		$("#_selectuser #hidden_div").html("");
		$("#_sharestore INPUT[type='hidden']",navTab.getCurrentPanel()).each(function(){
				$("#_selectuser #hidden_div").prepend(this);
			}
		);
		var ids = "";
		$("#select-options",$.pdialog.getCurrent()).children(":selected").each(function(){
				ids += $(this).val()+",";
			}
		);
		if (ids.length!=0){
			ids = ids.substring(0,ids.length-1);
		}
		$("#select-options1",$.pdialog.getCurrent()).val(ids);
		//alert($form.serializeArray());
		$.ajax({
			type: $form.attr("method") || 'POST',
			url:$form.attr("action"),
			data:$form.serializeArray(),
			dataType:"xml",
			cache: false,
			success: _ok_send,
			error: DWZ.ajaxError
		});
	}
	
	function _ok_send(data){
		_docSend(data);
		$.pdialog.closeCurrent();
	}
	
//选择全部
	function set_allUsers(){
		$('#_selectuser #select-options').html($('#_sharestore #optionlist',navTab.getCurrentPanel()).html());
	}
	
	//常用人员——选择
	function moveAct(target) {
	   //原列表
	   $s_list = $("#_sendUserTemplate #s_item");
	   //目标列表
	   $to_list = $("#_sendUserTemplate #_userids");
	   
	   if(target=="target"){
		   $s_list.find("option[selected]").each(function(){
		   		$to_list.append(this);
		   });   
	   }else {
		   $to_list.find("option[selected]").each(function(){
		   		$s_list.append(this);
		   });      	
	   }
	
	}
	
	//页面初始化设置
	$(function(){
		
		if("select-multiple"=="${param.selecttype}"){
			$("#_selectuser #seltype_remark").html("提示：同时按Ctrl或Shift键可多选");
			$("#_selectuser #select-options").attr("multiple","true");
		}
		
		//初始化发送可选择对像  如果配置列表为空取全部
		if($("#_selectuser #select-options option").length == 0 ){
			$("#_selectuser #select-options").html($("#_sharestore #optionlist",navTab.getCurrentPanel()).html());
		}
		
		//默认选中第一个人
		$("#_selectuser #select-options option").first().attr("selected",true);
		
		//初始化节点配置人员选择框
		var userids = ",";
		$("#_sendUserTemplate #_userids option").each(function(){
			userids += $(this).val() +",";
		});
		
		var s_item = $("#_sendUserTemplate #s_item");
		if(userids == ","){
			$("#_sendUserTemplate #s_item").html($("#_sharestore #optionlist",navTab.getCurrentPanel()).html());
		} else {
			s_item.html("");
			$("#_sharestore #optionlist option",navTab.getCurrentPanel()).each(function(){
			    if(userids.indexOf("," + $(this).val() + ",") == -1){
					s_item.append("<option value=\""+$(this).val()+"\">" + $(this).text() + "</option>");
				}
			});
		}
		
		//设置div 显示、隐藏
		$("#_sendUserTemplate").hide();$("#_selectuser").show();
		
		var t_formAction = '${param.formAction}';
		if(t_formAction == null || $.trim(t_formAction)==''){
			t_formAction = "send.do";
		}
		$("#_su").attr("action",t_formAction);
	});

</script>

<div class="page">
	<div class="pageContent">
		<div id="_selectuser">
			<form id="_su" name="_selectuser" action="send.do" method="post" >				
				<div class="pageFormContent" layoutH="56">
					<div style="display=none" id="hidden_div"></div> 
					<input type="hidden" name="opernode_id" id="opernode_id" value="<c:out value="${param.opernode_id}" default="-1"/>"/>
					<input type="hidden" name="project_id" id="project_id" value="<c:out value="${param.project_id}" default="-1"/>"/>
					<input type="hidden" id="select-options1" name="${param.return_flag }"/>
					<select id='select-options' type='select-multiple' size="6" align='center' style='width:390px;height:170px;'>
						<c:forEach var="user" items="${userList}">
							<option  value="${user.id}">[${user.login_id }]${user.name }</option>
						</c:forEach>		
					</select>
					<br/>
					<div id='seltype_remark' style='color:#888;font-size:12px;'>提示：只能选择一个目标对象</div>
				</div>
				<div class="formBar">
					<div class="button" style="float:left;"><div class="buttonContent"><button type="Button" onclick="javascript:set_allUsers();">全部</button></div></div>
					<div class="button" style="float:left;"><div class="buttonContent"><button type="Button" onclick="javascript:$('#_sendUserTemplate').show();$('#_selectuser').hide();">常用人员选择</button></div></div>
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button"  onclick="javascript:ok_send(this);">选好了,发送</button></div></div></li>	
						<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
					</ul>
				</div>
			</form>
		</div>
		
		<div id="_sendUserTemplate">
			<form id="_sut" name="_sendUserTemplate" action="flow/saveConfig.do" method="post" > 
				<input type="hidden" name="node_id"  value="${param.node_id}">
				<input type="hidden" name="user_id"  value="${param.user_id}">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" id="table1" layoutH="38">
					<tr>
						<td width="170">
							<select size="12" id="s_item" name="s_item" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('target')" style="width:170;overflow-x: scroll;">
									
							</select>
						</td>
						<td width="30" align="center">
							<input type="button" value="&gt;" name="B3" class="input-border" onclick="javascript:moveAct('target');"/><p>
							<input type="button" value="&lt;" name="B4" class="input-border" onclick="javascript:moveAct('source');"/>
						</td>
						<td width="170">
							<select size="12" id="_userids" name="_userids" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('source')" style="width:170;overflow-x: scroll;">
								<c:forEach var="user" items="${userList}">
									<option  value="${user.id}">[${user.login_id }]${user.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:saveConfig(this);" >保 存</button></div></div></li>	
						<li><div class="button"><div class="buttonContent"><button type="Button"  onclick="javascript:$('#_sendUserTemplate').hide();$('#_selectuser').show();">返 回</button></div></div></li>
					</ul>
				</div>
			</form> 
		</div>
	</div>
</div>
		

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.mail_read td{border:solid 0px #eee;height:22px;line-heiht:22px;text-align: left;}
.mail_read th{border:solid 0px #eee;text-align:center; color:#888;height:22px;line-heiht:22px;text-align: left;padding-left: 7px;}
</style>
<script>
	var arrayLength=0;
	function setdept(obj){
		if(obj.value!=""){
			var url = 'MessageAjax.do';
	   		var pars = "area_id="+obj.value;
			var myAjax = new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: showdeptResponse});
		}
	}function showdeptResponse(originalRequest){
		var result = originalRequest.responseText;
		var doc = new ActiveXObject("MSxml2.DOMDocument")
        doc.loadXML(result);
        var options = doc.getElementsByTagName("option");
		var dept = $("dept");
		dept.options.length=0;
		if(options!=null){
			var option = document.createElement("option");
			option.innerText = "选择部门";
			option.value = "";
			dept.appendChild(option);
			for(var i=0; i<options.length; i++){
				var option = document.createElement("option");
				option.innerText = options[i].childNodes[1].childNodes[0].nodeValue;
				option.value = options[i].childNodes[0].childNodes[0].nodeValue;
				dept.appendChild(option);
			}
		}
	}function setuser(obj){
		if(obj.value!=""){
			var url = 'MessageAjax.do';
	   		var pars = "dept_id="+obj.value;
			var myAjax = new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: showUserResponse});
		}
	}function showUserResponse(originalRequest){
		var result = originalRequest.responseText;
		var doc = new ActiveXObject("MSxml2.DOMDocument")
        doc.loadXML(result);
        var users = doc.getElementsByTagName("user");
		var user_list = $("user_list");
		var addText="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;\" class=\"data-table2\">";		
		var id = "";
		var name = "";
		var login_id = "";
		if(users!=null){
			for(var i=0; i<users.length; i++){
				id = users[i].childNodes[0].childNodes[0].nodeValue;
				name = users[i].childNodes[1].childNodes[0].nodeValue;
				login_id = users[i].childNodes[3].childNodes[0].nodeValue;
				addText += "<tr>"
				addText += "<td style=\"height:20px;\" onclick=\"javascript:chooseUser("+id+",'"+name+"')\">&nbsp;"+login_id+"--"+name+"</td>";
				addText += "</tr>"
			}
		}
		addText += "</table>";
		user_list.innerHTML = addText;
		initTable();
	}function addFj(tableId){
		var addtr = document.getElementById(tableId).insertRow(4);
		var rownum = document.getElementById(tableId).rows.length;
		addtr.insertCell(0).innerHTML = ""; 
		var Text = "<input type=\"file\" name=\"the_file1" + rownum + "\" style=\"float:left;\" size=\"35\"/> ";
		Text+="<a href=\"#\" onclick=\"javascript:delAttachment(this);\" class=\"btnDel\" ><span>删除</span> </a>";
		addtr.insertCell(1).innerHTML = Text;
	}
	function delAttachment(_this){
		var $tr = $(_this).closest("tr");
		$tr.remove();
	}
	
	function delrow(obj){
		var num = obj.parentNode.parentNode.rowIndex;
		document.getElementById("send_message").deleteRow(num);
	}	
	function del(obj){
	 document.messagewrite.reader_id.value='';
	 document.messagewrite.reader_name.value='';
	 $(".read_div").html(""); 
	}
	function selectToUser(){
		var selectO = $("#user_list option:selected");
		var $read_div=$(".read_div");
		selectO.each(function(){
		if($("#reader_id").val()==""){
			$("#reader_id").val($(this).val());
			$("#reader_name").val($(this).text());
		}else{ 
			$("#reader_id").val($("#reader_id").val() + "," + $(this).val());
			$("#reader_name").val($("#reader_name").val() + "," + $(this).text());
		}
		$read_div.append("<span name='"+$(this).text()+"' id='"+$(this).val()+"' onclick='delRead(this)' onmouseout='mouseout_css(this)' onmouseover='hover_css(this)'>"+$(this).text()+";&nbsp;</span>");
		$(this).remove();
		});
	} 
	function hover_css(_this){
		$(_this).css("background","#3399ff");
		$(_this).css("cursor","default");
	}
	function mouseout_css(_this){
		$(_this).css("background","white");
	}
	function delRead(_this){
		var read_id=$(_this).attr("id");
		var reader_ids=$("#reader_id").val();
		var reader_ids_array=reader_ids.split(",");
		
		var read_name=$(_this).attr("name");
		var reader_names=$("#reader_name").val();
		var reader_names_array=reader_names.split(",");
		
		arrayLength=reader_ids_array.length; 
		for(var i=0;i<arrayLength;i++){
			if(read_id==reader_ids_array[i]){
				for(var j=i;j<arrayLength-1;j++){
					reader_ids_array[j]=reader_ids_array[j+1];
					reader_names_array[j]=reader_names_array[j+1];
				}
				arrayLength--;
			} 
		}
		$("#reader_id").val("");
		$("#reader_name").val("");
		$("#reader_id").val(reader_ids_array[0]);
		$("#reader_name").val(reader_names_array[0]);
		for(var i=1;i<arrayLength;i++){
			$("#reader_id").val($("#reader_id").val()+","+reader_ids_array[i]);
			$("#reader_name").val($("#reader_name").val()+","+reader_names_array[i]);
		}
		if(arrayLength==0){
			$("#reader_id").val(""); 
			reader_ids_array=null;
			$("#reader_name").val(""); 
			reader_names_array=null;
		}  
		$(_this).remove();
	}
	$(function(){
	
	$("#submitbutton").click(function(){
		if($("#reader_id").val()==""||$("#reader_id").val()==null){
			alertMsg.info('收件人不能为空!');
			return;
		}if($("#title").val()==""||$("#title").val()==null){
			alertMsg.info('主题不能为空!');
			return;
		}
		$("#send_flag").val("1");
		$("#messagewrite").submit();
	})	
	$("#caogaobutton").click(function(){
		$("#send_flag").val("0");
		$("#messagewrite").submit();
		
	});
	//处理全选按钮
	var $span=$(".select_all",navTab.getCurrentPanel());
	var $span_a=$span.closest("a");
	$span.hide();
	$span_a.mouseout(function(){
		$(".select_all",$(this)).hide();
	});
	$span_a.mouseover(function(){
		$(".select_all",$(this)).show();
	}); 
	
	//处理宽度
	var $pageDiv=$(".page div",navTab.getCurrentPanel());
	var $leftDiv=$("#left_div",navTab.getCurrentPanel());
	var $rightDiv=$("#right_div",navTab.getCurrentPanel());
	var pageDivWidth=$pageDiv.width();
	var leftDivWidth=$leftDiv.width();
	var rightDivWidth=$rightDiv.width();
	$leftDiv.width(pageDivWidth-rightDivWidth-32); 
	
	//处理层高度
	var $formBar=$(".formBar div",navTab.getCurrentPanel());
	var $pageContentDiv=$(".pageContent div",navTab.getCurrentPanel()); 
	$leftDiv.height(navTab._panelBox.height()-$formBar.height()-63);
	$rightDiv.height(navTab._panelBox.height()-$formBar.height()-63); 
	
	//设置收信人
	var reader_id=$("#reader_id").val();
	var reader_name=$("#reader_name").val();
	if(reader_id!=""&&reader_name!=""){
		selectToUser3(reader_name,reader_id);
	}
	}) ;
 
	
	function selectToUser2(_this){
		var $read_div=$(".read_div"); 
		if($("#reader_id").val()==""){ 
			$("#reader_id").val($(_this).attr("name"));
			$("#reader_name").val($(_this).text());
		}else{ 
			$("#reader_id").val($("#reader_id").val() + "," +$(_this).attr("name"));
			$("#reader_name").val($("#reader_name").val() + "," + $(_this).text());
		}
		$read_div.append("<span name='"+$(_this).text()+"' id='"+$(_this).attr("name")+"' onclick='delRead(this)' onmouseout='mouseout_css(this)' onmouseover='hover_css(this)'>"+$(_this).text()+";&nbsp;</span>");
	}
	function selectToUser3(param1,param2){
		var $read_div=$(".read_div"); 
		$read_div.append("<span name='"+param1+"' id='"+param2+"' onclick='delRead(this)' onmouseout='mouseout_css(this)' onmouseover='hover_css(this)'>"+param1+";&nbsp;</span>");
	}
	function selectAll(_this){
		var $li=$(_this).closest("li");
		var $u=$(".user_list",$li);
		var $read_div=$(".read_div");
		$u.each(function(){ 
			checkReaderRepeat(this); 
		}); 
	}
	//检测收件人的重复性
	function checkReaderRepeat(_this){
		var reader_ids=$("#reader_id").val();
		var reader_ids_array=reader_ids.split(",");
		var addFlag=true;
		for(var i=0;i<reader_ids_array.length;i++){
			if($(_this).attr("name")==reader_ids_array[i]){
				addFlag=false;
				break;
			};
		} 
		if(addFlag)
		selectToUser2(_this);
		
	}
</script>


<div class="page">
	<div class="pageContent" style="overflow: hidden;">
		<form name="messagewrite" id="messagewrite" action="MessageSend.do"
			enctype="multipart/form-data" method="post"
			onsubmit="return iframeCallback(messagewrite, dialogAjaxDone);">
			<input type="hidden" id="send_flag" name="send_flag" />
			<input type="hidden" name="_callbackType" value="" />
			<input type="hidden" name="_navTabId" value="" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="messageState"
				value="${param.messageState }" />
			<input type="hidden" name="sendType" value="${param.goanother }"/>
			<input type="hidden" name="message_id" value="${param.message_id }"/>
			<!-- left --> 
			<div
				style=" float: left; padding: 5px ;overflow: auto;"
				 id="left_div">
				<table width="95%" height="96%" class="mail_read" border="0" cellspacing="0"
					cellpadding="0" style="border-collapse: collapse;"
					id="send_message">
					<tr >
						<th width="80px">
							发件人：
						</th>
						<td>
							${user.name}&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" value="1" id="repeat_flag"
								name="repeat_flag" />
							&nbsp;需要回复
						</td>
					</tr>
					<tr>
						<th>
							收件人：
						</th>
						<td>
							<div style="width: 100%; height: auto;" class='read_div'></div>
						</td>
						<td>
							<input type="hidden" id="reader_id" name="reader_id"
								value="${reader_id }" />
							<input type="hidden" id="reader_name" name="reader_name"
								value="${reader_name }" />
							<img src="Images/trash.gif" onclick="javascript:del(this);"
								style="cursor: pointer;" title="清空内容" />
						</td>
					</tr>
					<tr>
						<th align="left">
							主&nbsp;&nbsp;题：
						</th>
						<td>
							<input type="text" id="title" name="title" value="${title }"
								style="width: 98%" class="required"/>
						</td>
					</tr>
					<tr>
						<th>
							附&nbsp;&nbsp;件：
							<img src="Images/add.png"
								onclick="javascript:addFj('send_message')"
								style="cursor: pointer" title="添加附件" />
						</th>
						<td >
							<input type="file" name="the_file1" size="35" style="float:left;"/>
						</td>
					</tr>
					<c:forEach var="fj_list" items="${fj_list}">
						<tr>
							<td rowsplan="${rowsnum}">
								<input type="hidden" id="zffjid" name="zffjid"
									value="${fj_list.id }">
							</td>
							<td>
								<a
									href="download.do?formurl=${fj_list.ftp_url}&ext_name=${fj_list.ext_name}&slave_name=${fj_list.file_name}">${fj_list["file_name"]
									}</a>
								<a
									href="download.do?formurl=${fj_list.ftp_url}&ext_name=${fj_list.ext_name}&slave_name=${fj_list.file_name}"><b>下载</b>
								</a>
								<a href="#" onclick="javascript:delrow(this)"><b>移除</b> </a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<th>
							内&nbsp;&nbsp;容：
						</th>
						<td>
							<textarea name="content" id="content"
								style='width: 98%; height: 193px;'>${content }</textarea>
						</td>
					</tr>
				</table> 
			</div>
			<!-- right --> 
			<div
				style="float: right; display: block; margin: 10px; overflow: auto; width: 30%;  border: solid 1px #CCC; line-height: 21px; background: #FFF;" id="right_div">
						<div style="width: 122px;"><img src="Images/email.GIF" width="24" height="24" style="margin-bottom:-7px;"><span style="font-size:12px;font-style:normal;">联系人列表</span></div>
						<ul class="tree collapse">
							<c:forEach var="menu" items="${dept_list}">
								<li>
									<a href="#">${menu.name} &nbsp;&nbsp;&nbsp;<font  onclick="selectAll(this)" class="select_all">全选</font> </a>
									<ul>
										<c:forEach var="nodeElement" items="${user_list}">
											<c:if test="${menu.id==nodeElement.dept_id}">
												<li>
																	<a href="#" name="${nodeElement.id}" onclick="checkReaderRepeat(this)" class="user_list">${nodeElement.name }</a>
												</li>
											</c:if>  
										</c:forEach> 
									</ul>
									</li>
							</c:forEach>
						</ul> 
			</div>
			<div class="formBar">
				<div class="button" style="float: left;">
					<div class="buttonContent">
						<button type="Button" id="caogaobutton">
							存草稿
						</button>
					</div>
				</div>
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" id="submitbutton">
									发 送
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" class="close">
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
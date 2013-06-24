/*=======================================================================================*/
/*****************************表单按钮处理,基于juery 1.5.0*******************************/
/*=======================================================================================*/


/**********************************************
* 表单保存
* 参数: 	module		number		表单模块标识
***********************************************/
function docSave() {
	var $auto_form = $("#auto_form",navTab.getCurrentPanel());
	$auto_form.attr("action","save.do");
	$auto_form.trigger("submit");
}

/**********************************************
* 表单保存
* 参数: 	module		number		表单模块标识
***********************************************/
function docNew(url) {
	navTab.reload(url);
}

/**********************************************
* 受理或取消受理按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docAccept(url){
	navTab.reload(url);
}

/**********************************************
* 上传附件
* 参数: 	url		处理的url路径
***********************************************/
function docSlave(url){
	url = url.replace("slave.do","dispath.do?url=uploadFile.jsp")
	if(url.indexOf("slave_type")==-1){
		url=url + "&slave_type=1";
	}
	//url="dispath.do?url=uploadFile.jsp?project_id=141&doc_id=143&module_id=101&node_id=110101&opernode_id=49";
	$.pdialog.open(url,'_upload_slave_form','附件上传',{width:400,height:220});
}

/**********************************************
* 审结按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docApproveComplete(url){
   $.ajax({
        dataType : 'text'
        ,context: document.body
        ,type : 'POST'
        ,url : url
        ,success : function(data){
        	//关闭
        	if(data=='closeWindow'){
        		docClose();
        	//刷新
        	} else if(data=='reloadWindow'){
        		docReload();
        	//回退
        	} else if(data=='reloadWindow'){
        		docTurnback('turnback.'+url.substring(url.indexOf('.do')));
        	//其它
        	}else {
        		alertMsg.error(data);
        	}
        }
        ,error:function(){alertMsg.error('错误！请重试'); }
        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
    });	
}

/**********************************************
* 发送按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docSend(url){
	   $.ajax({
        dataType : 'xml'
        ,context: document.body
        ,type : 'POST'
        ,url : url
        ,success : _docSend
        ,error:function(){alertMsg.error('错误！请重试'); }
        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
    });	
}

//发送选择类
function _docSend(data){
	
   back_flag = $(data).find("back_flag").text();
   //选择发送人员
   if("3" == back_flag){

	   //初始化发送所有人员列表
	   var userids = "";
	   var buttonHtml = "";
	   	buttonHtml = "<select id=\"optionlist\" name=\"optionlist\">";
	    $(data).find("option").each(function(){
	   		buttonHtml += "<option value=\""+ $(this).find("id").text() + "\">" + $(this).find("name").text() + "</option>";
	   		userids += $(this).find("id").text() + ",";
	   });
	   userids +="-1";
	   buttonHtml += "</select>";
	   $("#_sharestore",navTab.getCurrentPanel()).html(buttonHtml);
	   	
	   //初始化发送隐藏参数 $("#_sharestore #optionlist",navTab.getCurrentPanel()).html()
	   $(data).find("hidden-inputs item").each(function(){
	   		buttonHtml = "<input type=\"hidden\" name=\""+ $(this).find("item_name").text() + "\"  value=\"" + $(this).find("item_value").text() + "\"/>";
	   		$("#_sharestore",navTab.getCurrentPanel()).append(buttonHtml);
	   });
	   	   	
	   //实始化打开对话框参数
	   	var selecttype = $(data).find("select_type").text();
	   	var return_flag = "to_user";
	   	if( $(data).find("to_user").length == 0 ){
	   		return_flag = "to_group";
	   	}
	   	var user_id = 	$(".pageFormContent #user_id",navTab.getCurrentPanel()).val();
	   	var node_id = 	$(".pageFormContent #node_id",navTab.getCurrentPanel()).val();
	   //打开对话框选择人员
   		$.pdialog.open("flow/selectSendTarget.do?user_id=" + user_id +"&node_id="+node_id +"&return_flag="+return_flag +"&selecttype="+selecttype + "&userids=" + userids,'_docSendSelectTarget','文档发送',{width:420,height:280,mask:false});
   		
   //发送成功	
   } else if("2" == back_flag){
   		var _todo = $(data).find("todo").text();
   		alertMsg.info($(data).find("show_msg").text());
   		
   		if(_todo == 'closewindow'){
   			docClose();
   		//列示按扭
   		} else if(_todo == 'listButton'){
			buttonHtml = "<ul>";
			$(data).find("buttonList button").each(function(){
			    buttonHtml = buttonHtml + "<li><a class=\"edit\"	href=\""+ $(this).find("button_url").text() +"\" title=\""  
			    	+$(this).find("button_comment").text() + "\"><span>" +  $(this).find("button_name").text() +"</span></a></li>"
						+ "<li class=\"line\">line</li>";
			});
			
			buttonHtml = buttonHtml + "</ul>";
			$("#_flowButton",navTab.getCurrentPanel()).html(buttonHtml);
   		}
   //发送失败
   } else {
   		alertMsg.error($(data).find("show_msg").text());
   }

   navTab.reloadFlag("workList");
}
/**********************************************
* 收回按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docCallback(url){
	navTab.reload(url);
	navTab.reloadFlag("workList");
}

/**********************************************
* 回退按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docTurnback(url){
	   $.ajax({
        dataType : 'xml'
        ,context: document.body
        ,type : 'POST'
        ,url : url
        ,success : _docTurnback
        ,error:function(){alertMsg.error('错误！请重试'); }
        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
    });	
}

//回退选择类
function _docTurnback(data){
   back_flag = $(data).find("back_flag").text();
   //选择回退节点
   if("4" == back_flag){
	   //初始化发送所有人员列表
	   var buttonHtml = "";
	   	buttonHtml ="<input type=\"hidden\" name=\"formAction\" value=\"" + $(data).find("formAction").text() +"\">";
	   	$("#_sharestore",navTab.getCurrentPanel()).html(buttonHtml); 
		
	   //初始化发送隐藏参数 $("#_sharestore #optionlist",navTab.getCurrentPanel()).html()
	   $(data).find("hidden-inputs item").each(function(){
	   		buttonHtml = "<input type=\"hidden\" name=\""+ $(this).find("item_name").text() + "\"  value=\"" + $(this).find("item_value").text() + "\"/>";
	   		$("#_sharestore",navTab.getCurrentPanel()).append(buttonHtml);
	   });
				
	   //实始化打开对话框参数
	   	var rollto_nodes = $(data).find("rollto_nodes").text();
	   	var flow_id = rollto_nodes.substring(0,4);
	   	
	   //打开对话框选择人员
   		$.pdialog.open('turnBackTree.do?flow_id='+flow_id+'&nodes='+rollto_nodes ,'_docSendSelectTarget','表单回退',{width:600,height:450,mask:false});
		
   //回退成功	
   } else if("2" == back_flag || "3" == back_flag){
   		var _todo = $(data).find("todo").text();
   		alertMsg.info($(data).find("show_msg").text());
   		docClose();
   //回退失败
   } else {
   		alertMsg.error($(data).find("show_msg").text());
   }
   
   navTab.reloadFlag("workList");
   //navTab.reload();
}
/**********************************************
* 转发按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docHandOver(url){
	   $.ajax({
        dataType : 'xml'
        ,context: document.body
        ,type : 'POST'
        ,url : url
        ,success : _docHandOver
        ,error:function(){alertMsg.error('错误！请重试'); }
        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
    });	
}

//转发处理类
function _docHandOver(data){

 back_flag = $(data).find("back_flag").text();
   //选择发送人员
   if("3" == back_flag){

	   //初始化发送所有人员列表
	   var userids = "";
	   var buttonHtml = "";
	   	buttonHtml = "<select id=\"optionlist\" name=\"optionlist\">";
	    $(data).find("option").each(function(){
	   		buttonHtml += "<option value=\""+ $(this).find("option_id").text() + "\">" + $(this).find("option_name").text() + "</option>";
	   		userids += $(this).find("option_id").text() + ",";
	   });
	   userids +="-1";
	   buttonHtml += "</select>";
	   $("#_sharestore",navTab.getCurrentPanel()).html(buttonHtml);
	   	
	   //初始化发送隐藏参数 $("#_sharestore #optionlist",navTab.getCurrentPanel()).html()
	   $(data).find("hidden-inputs item").each(function(){
	   		buttonHtml = "<input type=\"hidden\" name=\""+ $(this).find("item_name").text() + "\"  value=\"" + $(this).find("item_value").text() + "\"/>";
	   		$("#_sharestore",navTab.getCurrentPanel()).append(buttonHtml);
	   });
	   	   	
	   //初始化打开对话框参数
	   	var selecttype = $(data).find("select_type").text();
	   	var return_flag = "to_user";
	   	if( $(data).find("to_user").length == 0 ){
	   		return_flag = "to_group";
	   	}
	   	var user_id = $(".pageFormContent #user_id",navTab.getCurrentPanel()).val();
	   	var node_id = $(".pageFormContent #node_id",navTab.getCurrentPanel()).val();
	   	var opernode_id = $(".pageFormContent #opernode_id",navTab.getCurrentPanel()).val();
	   	var project_id = $(".pageFormContent #project_id",navTab.getCurrentPanel()).val();
	    var formAction =  $(data).find("formAction").text();
	   //打开对话框选择人员
   		$.pdialog.open("flow/selectSendTarget.do?project_id=" + project_id + "&opernode_id=" + opernode_id + "&user_id=" + user_id +"&node_id="+node_id +"&return_flag="+return_flag +"&selecttype="+selecttype + "&userids=" + userids + "&formAction=" + formAction,'_docSendSelectTarget','文档发送',{width:420,height:280,mask:false});
   		
   //发送成功	
   } else if("2" == back_flag){
   		var _todo = $(data).find("todo").text();
   		alertMsg.info($(data).find("show_msg").text());
   		
   		if(_todo == 'closewindow'){
   			docClose();
   		//列示按扭
   		} else if(_todo == 'listButton'){
			buttonHtml = "<ul>";
			$(data).find("buttonList button").each(function(){
			    buttonHtml = buttonHtml + "<li><a class=\"edit\"	href=\""+ $(this).find("button_url").text() +"\" title=\""  
			    	+$(this).find("button_comment").text() + "\"><span>" +  $(this).find("button_name").text() +"</span></a></li>"
						+ "<li class=\"line\">line</li>";
			});
			
			buttonHtml = buttonHtml + "</ul>";
			$("#_flowButton",navTab.getCurrentPanel()).html(buttonHtml);
   		}
   //发送失败
   } else {
   		alertMsg.error($(data).find("show_msg").text());
   }

   navTab.reloadFlag("workList");
        	
}
/**********************************************
* 归档按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docAchive(url){
	alertMsg.confirm("确认是否办结当前表单吗？", {
        okCall: function(){
             $.ajax({
		        dataType : 'text'
		        ,context: document.body
		        ,type : 'POST'
		        ,url : url
		        ,data : {p_var1: 'bb',p_var2: 'a'}
		        ,success : function(data){
					//关闭
		        	if(data=='closeWindow'){
		        		docClose();
		        	//刷新
		        	} else if(data=='reloadWindow'){
		        		docReload();
		        	//回退
		        	}
		        }
		        ,error:function(){alertMsg.error('错误！请重试'); }
		        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
		    });	
        }
	});
	
	navTab.reloadFlag("workList");
}

/**********************************************
* 完工或中止按纽的处理
* 参数: 	url		处理的url路径
***********************************************/
function docComplete(url){
	
}

/**********************************************
* 关闭按钮
* 参数: 
***********************************************/
function docClose(){
	navTab.closeCurrentTab();
}

/**********************************************
* 表单删除
* 参数: 	url		处理的url路径
***********************************************/
function docDelete(url){
	alertMsg.confirm("确认是否删除当前表单吗？", {
        okCall: function(){
             $.ajax({
		        dataType : 'text'
		        ,context: document.body
		        ,type : 'POST'
		        ,url : url
		        ,data : {p_var1: 'bb',p_var2: 'a'}
		        ,success : function(data){
		        	//删除成功
		        	if(data=='closeWindow'){
		        		alertMsg.info('表单删除成功！');
		        		docClose();
		        	//删除失败
		        	}else {
		        		alertMsg.error('表单删除失败：' + data);
		        	}
		        }
		        ,error:function(){alertMsg.error('错误！请重试'); }
		        ,timeout:function(){alertMsg.error('处理超时！请重试'); }
		    });	
        }
	});
	
	navTab.reloadFlag("workList");
}

/**********************************************
* 审批按钮
* 参数: 	url		处理的url路径
***********************************************/
function docApprove(url){
	$.pdialog.open(url,'_docApprove_form','表单审批',{width:420,height:280,mask:false});
}

/**********************************************
* 文档打印
* 参数: 	
***********************************************/
function docPrint(){
	var $form = $("#auto_form",navTab.getCurrentPanel());
	
	var project_id = $form.find("#project_id").val();
	var module_id = $form.find("#module_id").val();
	var doc_id = $form.find("#doc_id").val();	
	var pars = "project_id="+project_id+"&module_id="+module_id+"&doc_id="+doc_id;
	var url = "print.do?"+ pars;
	var pop = window.open('','print','width=700,height=470,scrollbars=1');
	pop.moveTo((screen.width-700)/2,(screen.height-470)/2);
	pop.location.href = url;
	pop.focus();
	
	//$.pdialog.open('print.do', 'formPrint', '表单打印', {width:'700', height:'500', data: $form.serializeArray()});
}

/**********************************************
* 删除附件
* 参数: 	
***********************************************/
function del_slave(slave_id, slave_index){
	ajaxTodo("delfile.do", delSalveAjaxDone, {'slave_id':slave_id, 'slave_index':slave_index})
}
function delSalveAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if(json.slaveIndex){
			var slavep = $("#slaveDiv", navTab.getCurrentPanel()).find("p");
			if(slavep.size()>0){
				$(slavep[json.slaveIndex]).remove();
				$.pdialog.reload();
			}
		}
	}
}
/**********************************************
* 删除附件2
* 参数: 	
***********************************************/

function del_send(jlfk_project_id,project_id,slave_id,module_id,user_id,doc_id){
	
	alertMsg.confirm("确认删除吗?", {
	okCall: function(){
			$.ajax({
			type: "post",
			dataType:"json",
			data: 'project_id='+jlfk_project_id+'&slave_id='+slave_id+'&random='+Math.random(),
			url: "delsend.do",
			success: function(msg){
				DWZ.ajaxDone(msg);
				navTab.reload();
	 		}
			});
	}
	});
}

/**********************************************
* 打开表单
* 参数: 	param	Stirng	参数串
		rel		String	指向的目标id
***********************************************/
function openFlowForm(paramJson){
	var param_json = DWZ.jsonEval(paramJson);
	var module_id = param_json.module_id, doc_id = param_json.doc_id;
	var param = "project_id="+param_json.project_id+"&module_id="+param_json.module_id+"&doc_id="+param_json.doc_id+"&opernode_id="+param_json.opernode_id+"&node_id="+param_json.node_id+"&user_id="+param_json.user_id;
	
	navTab.openTab('autoform'+module_id+doc_id, 'openForm.do?'+param, {title:'表单'});
}

/**********************************************
* 表单刷新
***********************************************/
function docReload(){
	/*
	var $auto_form = $("#auto_form",navTab.getCurrentPanel());
	
	var para = {'doc_id':$auto_form.find("#doc_id").val(),
				'module_id':$auto_form.find("#module_id").val(),
				'node_id':$auto_form.find("#node_id").val(),
				'project_id':$auto_form.find("#project_id").val(),
				'user_id':$auto_form.find("#user_id").val()};
				
	navTab.reload('openForm.do',{data:para});
	*/
	navTab.reload();
}
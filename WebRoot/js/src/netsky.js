
/**
 * 普通ajax表单提交（不含校验）
 * @param {Object} form
 * @param {Object} callback
 */
function formCommitCallback(form, callback) {
	var $form = $(form);
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: callback || DWZ.ajaxDone,
		error: DWZ.ajaxError
	});
	return false;
}
/**
 * 需用户双向确认的ajax表单提交
 * @param {Object} form
 * @param {Object} callback
 */
function businessConfirm(form,callback){
	var $form = $(form);	
	if (!$form.valid()) {
		return false;
	}
	var input_info = "<p><label>用户名：</label><input type=\"text\" id=\"input_login_id\" name=\"input_login_id\"  class=\"required\" style=\"width:100px;\" />\
						</p><p><label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label><input type=\"password\" id=\"input_password\" name=\"input_password\"  class=\"required\" style=\"width:100px;\" /></p>";
	alertMsg.confirm(input_info, {
		okCall: function(){
			if($("#check_login_id").size() == 0){
				var check_info = "<input type=\"hidden\" id=\"check_login_id\" name=\"check_login_id\" size=\"20\" />\
							<input type=\"hidden\" id=\"check_password\" name=\"check_password\" size=\"20\" />";
				$(check_info).appendTo($form);
			}			
			$("#check_login_id").val($("#input_login_id").val());
			$("#check_password").val($("#input_password").val());
			
			$.ajax({
				type: form.method || 'POST',
				url:$form.attr("action"),
				data:$form.serializeArray(),
				dataType:"json",
				cache: false,
				success: callback || DWZ.ajaxDone,
				error: DWZ.ajaxError
			});
		}
	});
	return false;
}

/**
 * 将子对象从原始父对象转移到目标父对象中
 * @param {String} s_obj
 * @param {String} t_obj
 */
function moveAct(s_obj,t_obj) {
	$("#"+s_obj+" option:selected").each(function(){
		$("#"+t_obj).append(this);
	});
}

/**
 * 使Select中的Option移动
 * @param {String} s_obj
 * @param {String} act
 */
function move(s_obj, act){
	var selectO = $("#"+s_obj+" option:selected");	
	if(act=='up'){
		selectO.each(function(){
			$(this).insertBefore($(this).prev());
		});
	}else if(act=='down'){
		var preSelectNum = selectO.length;
		selectO.each(function(){
			var nextOption = $(this);
			for(i=0; i<preSelectNum; i++){
				nextOption.end();
				nextOption = nextOption.next();
				
			}
			$(this).insertAfter(nextOption);
		});
	}else if(act=='top'){
		selectO.prependTo($("#"+s_obj));
	}else if(act=='bottom'){
		selectO.appendTo($("#"+s_obj));
	}else{
		alert("action error!");
	}
	
	var disabledO = $("#"+s_obj+" option:disabled");
	disabledO.prependTo($("#"+s_obj));
}

/**
 * 左右结构的多选项结果ajax提交
 * @param {Object} form
 * @param {Object} callback
 * @param {String} t_item
 */
function selectSubmit(form,callback,t_item) {
	var $form = $(form);
  	if(t_item!="")selectAllOption(t_item);
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: callback || DWZ.ajaxDone,
		error: DWZ.ajaxError
	});
	
    return false;
}

/**
 * 表单中select对象中所有option都选中
 * @param {String} items （多个select.id用“,”分隔）
 */
 function selectAllOption(items){
 	var t_item =  $(items.split(","));	
 	t_item.each(function(){
 		var ops = $("#"+this+ " option");
 		ops.each(function(){
			$(this).attr("selected",true);
		});
 	});
 }

/**
 * 查询和excel导出扩展函数
 * 在excel导出后修改form提交信息,实现navTab下ajax提交
 * @param {Object} buttObj
 * @param {String} url	(表单action)
 * @param {Function} submitCall (表单onsubmit时调用)
 * @param {String} callParam	(提交时调用函数字符串参数)
 */
function searchOrExcelExport(buttObj, url, submitCall, callParam){
	var $form = $(buttObj).closest("form");
	var temp_url = $form.attr('action');
	$form.attr('action',url);
	$form.submit( function () {
	  if(submitCall){
	  	  if(callParam) 	 return submitCall(this,callParam);
	  	  else		return submitCall(this);
	  }else	{return true;}
	});
	$form.submit();
	$form.attr('action',temp_url);
}


/**
 * serializeArray对象序列化json对象
 */
$.fn.serializeObject = function() {  
     var o = {};  
     var a = this.serializeArray();  
     $.each(a, function() {  
         if (o[this.name]) {  
             if (!o[this.name].push) {  
                 o[this.name] = [ o[this.name] ];  
             }
             o[this.name].push(this.value || '');  
         } else {
             o[this.name] = this.value || '';
         } 
         //alert(this.name + "=" + o[this.name]);
     }); 
     return o; 
 } 
 
 /**
 * 设置uploadify插件中上传对象的scriptdata属性，并执行上传
 * @param {Object} form
 * @param {Object} callback
 * @param {String} t_item
 */
 function setAndUpload(butt){
 	var script_data = $(butt).closest("form").serializeObject();
 	$('#file_upload').uploadifySettings('scriptData',script_data); 
 	$('#file_upload').uploadifyUpload();
 }
 
 
/**
 * ajax回调函数． 
 * 实现页面loadFileArea局部刷新 
 * callbackType如果是closeCurrent就会关闭当前tab
 * ajaxchuli后返回json数据结构statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因. 
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * {"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"","forwardId":"","forwardTitle":"","callbackType":"closeCurrent"}
 * {"statusCode":"300", "message":"操作失败"}
 * {"statusCode":"301", "message":"会话超时"}
 * 
 */
function loadFileAreaAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if (json.navTabId){ //重新载入
			var $load_area = $("#" + json.navTabId);//为通用，此处命名沿用navTabId，其实指具体的loadFileArea
			if($load_area.size()>0){
				var $p = $(document);
				var url = unescape($load_area.attr("loadfile")).replaceTmById($p);
				DWZ.debug(url);
				if (!url.isFinishedTm()) {
					alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
					return false;
				}
			
				$load_area.loadUrl(url);
			}
		} else { //重新载入当前navTab页面
			navTabPageBreak();
		}
		
		
		if ("closeCurrentTab" == json.callbackType) {
			setTimeout(function(){navTab.closeCurrentTab();}, 100);
		}else if("closeCurrentDialog" == json.callbackType){
			setTimeout(function(){$.pdialog.closeCurrent();}, 100);
		} else if ("forward" == json.callbackType) {
			if(""==json.forwardId){
				navTab.reload(json.forwardUrl);
			}else{
				setTimeout(function(){
							navTab.closeCurrentTab();
							navTab.openTab(json.forwardId, json.forwardUrl, { title:json.forwardTitle, flesh:false, data:{keywords:'${param.tax_code }'} });
							}, 100);
			}
		}
		
	}
}


/**
 * loadFileDiv上翻页
 * @param {String} form_id
 * @param {String} loadFileArea_id
 */
function prevRecord(form_id,loadFileArea_id){
	var $form = $("#"+form_id);
	var pageNum = $("#"+form_id+ " input[name=pageNum]");
	pageNum.val(parseInt(pageNum.val())-1);
	$("#"+loadFileArea_id).loadUrl($form.attr("action"), $form.serializeArray());
}

function nextRecord(form_id,loadFileArea_id){
	var $form = $("#"+form_id);
	var pageNum = $("#"+form_id+ " input[name=pageNum]");
	pageNum.val(parseInt(pageNum.val())+1);
	$("#"+loadFileArea_id).loadUrl($form.attr("action"), $form.serializeArray());
}

/**
 * 级联菜单
 * ver0.00
 * @param var0 填充的SELECT的ID(例:被填充的SELECT<select id="test"> 则var0='test')
 * @param var1 查询的类名.条件的方法名(例:'Ta03_user.id')
 * @param var2 查询条件的的值(例:'22')
 * @param var3 <option>标签的VALUE属性值所对应的持久化类的字段名 (例:<option>的VALUE为Ta03_user类中的id字段 则var3='id')
 * @param var4 <option>标签显示的内容(例:<option>显示的值为Ta03_user类中的name字段 则var3='name')
 * 例:select标签A代表地区 select标签B代表用户 用户下拉列表依地区的变化而变化 其中A下拉列表ID为selectA B下拉列表ID为selectB 其中option的VALUE由TA03中的ID填充 option显示的值由TA03中的NAME填充
 * 应如下调用该函数:jilian('selectB','Ta03_user.area_name','$('#selectA').val()','id','name'); 
 * 该函数只能用于selectA的option的value恰巧等于selectB所代表的持久化类中的某一字段 如上例中 selectA的option的value为areaname 而Ta03_user中也有areaname字段
 * 如果是selectA的option的value是一个id而非一个name 则暂不能用该函数解决 有待继续开发
 * ver0.01 允许多级菜单级联(例:参见messagewrite.jsp) 实现方式:async:false
 */
function jilian(var0, var1, var2, var3, var4, var5) {
	var params = "var1=" + var1 + "&var2=" + var2 + "&var3=" + var3 + "&var4=" + var4 + "&var5=" + var5;
	$.ajax({type:"post",async:false,url:"sysManage/ajaxjiliancaidan.do", data:params, success:function (msg) {
		$("#" + var0 + "").empty();
		$("#" + var0 + "").append(msg);
	}});
}

/**
*点流程按钮时的提示信息
*流程中的函数名和参数是固定的
*后三个参数此处无意义
*/
function showMsgBox(_msg,a,b,c){
	alertMsg.info(_msg);
}

/**
*概预算显示
*/
function djbck(project_id){
	navTab.openTab('gysShow', 'form/gysShow.do?project_id='+project_id, {title:'概预算显示'});
}

/**
*设计文档上传
*/
function designDocUpload(project_id,doc_id,module_id,node_id,opernode_id,slave_type){
	url="dispath.do?url=uploadFile.jsp?project_id="+project_id+"&doc_id="+doc_id+"&module_id="+module_id+"&node_id="+node_id+"&opernode_id="+opernode_id+"&slave_type="+slave_type;
	$.pdialog.open(url,'_upload_slave_form','附件上传',{width:400,height:220});
}

/**
*工程附件显示【设计文档、竣工资料】
*/
function projectSlaveShow(project_id,module_id,doc_id,canDel,lb){
	var url = 'form/designDocShow.do?project_id='+project_id+'&module_id='+module_id+'&doc_id='+doc_id+'&canDel='+canDel+'&lb='+lb;
	$.pdialog.open(url,'_projectSlaveShow','附件显示',{width:600,height:400});
}

/**
*工程材料出入库情况查看
*/
function projectClmx(project_id){
	var url = 'wxdw/projectClmx.do?project_id='+project_id;
	//$.pdialog.open(url,'_projectSlaveShow','材料出入库情况',{width:600,height:400});
	navTab.openTab('_projectClmxShow', url, {title:'材料出入库情况'});
}

/**
*功能中打开在线提问
*module_name [tz08_help_map.module_name]
*/
function enterHelp(module_name)
{
    $.ajax({
			type: "POST",
			url: "help/ajaxGetHelp.do?module_name="+module_name,
			dataType:"json",
			success: function(json){
				if(json.statusCode == DWZ.statusCode.ok){
					if(json.adminRole == 'true'){
						var input_info = "<p><input type=\"checkbox\" id=\"_reSel\"><label>重新选择</label></p>";
						alertMsg.confirm(input_info, {			
							okCall: function(){
								if($("#_reSel").attr('checked') == undefined){
									if(json.help_id != -1){
										navTab.openTab('helpDisp', 'help/helpDisp.do?id='+json.help_id, {title:'在线帮助'});
									}
									else{
										alertMsg.warn('暂无帮助，请与管理员联系');
										return false;
									}
								}
								else{
									$.pdialog.open('help/helpList.do?showPart=yes&module_name='+module_name, '', '重新选择', {mask:true,width:480,height:350});
								}
							}
						});
					}
					else{
						if(json.help_id != -1){
							navTab.openTab('helpDisp', 'help/helpDisp.do?id='+json.help_id, {title:'在线帮助'});
						}
						else{
							alertMsg.warn('暂无帮助，请与管理员联系');
							return false;
						}
					}
				}
			},
			error: DWZ.ajaxError
		});
}

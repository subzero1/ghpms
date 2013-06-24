/**
 * 查询统计条件tab切换
 * @param {Object} clickObj
 * @param {String} contentIndex
 */
function tabSearchCondition(clickObj,contentIndex){
	var index = $(clickObj).index();
	
	var content = $("#content_div div");
	content.hide();
	$(content.get(contentIndex)).show();
	
	var tab = $("#tab_table td");	
	tab.css("background-color","#dee0e1");
	$(tab.get(index)).css("background-color","");
}

/**
 * 从左侧select中选择字段到X、Y坐标
 * @param {Object} selectObj
 * @param {String} way
 */
function selectStatistic(selectObj,way){
	if($(way=="y_way" && "#y_way tr").length >= 5){
		alertMsg.error("Y坐标选项不能超过3个");
		return;
	}
	$(selectObj).find("option:selected").each(function(){
		var new_tr = $("#model_tr").clone(true);
		$(new_tr.find("td").get(0)).text($(this).text());
		$(new_tr.find("input").get(0)).val($(this).val());
		new_tr.appendTo($("#"+way));
		if(way == "x_way")$(new_tr.find("option")[1]).attr("selected",true);
		new_tr.show();
		$(this).remove();
	});	
}

/**
 * 移动坐标行
 * @param {Object} clickObj
 * @param {String} act
 */
function moveRow(clickObj,act){
	var rowObj = $(clickObj).closest("tr");
	if(act == "up"){
		rowObj.insertBefore(rowObj.prev());
	}else{
		rowObj.insertAfter(rowObj.next());
	}
}

/**
 * 删除坐标行
 * @param {Object} clickObj
 */
function delRow(clickObj){
	var rowObj = $(clickObj).closest("tr");
	var op_str = "<option value='"+ $(rowObj.find("input").get(0)).val() +"'>"+ $(rowObj.find("td").get(0)).text() +"</option>";
	$(op_str).appendTo($("#statisticList"));
	rowObj.remove();
}

/**
 * 变更坐标位置
 * @param {Object} changeSeletor
 */
function changeWay(changeSeletor){
	if($(changeSeletor).val() == "X"){
		if($("#x_way tr").length >= 4)
			alertMsg.error("X坐标选项不能超过3个");
		else
			$(changeSeletor).closest("tr").appendTo("#x_way");
	}else{
		if($("#y_way tr").length >= 5)
			alertMsg.error("Y坐标选项不能超过3个");
		else
			$(changeSeletor).closest("tr").appendTo("#y_way");
	}
}

/**
 * 处理dialog弹出层上查询, 重新载入当前navTab
 * 载入后关闭dialog弹出层
 * @param {Object} form
 * @param {String} param (格式:navTabId|t_item)
 * navTabId:查询结果显示的目标位置;
 * t_item:多个select.id用“,”分隔
 */
function dialogToNavTabSearch(form, param){
	var temp = param.split("|");
	var navTabId = temp.length>0?temp[0]:"";
	var t_item = temp.length>1?temp[1]:"";
  	if(t_item!="")selectAllOption(t_item);
	
	if (form[DWZ.pageInfo.pageNum]) form[DWZ.pageInfo.pageNum].value = 1;
	navTab.reload(form.action, {data: $(form).serializeArray(), navTabId:navTabId});
	setTimeout(function(){$.pdialog.closeCurrent();}, 100);
	return false;
}

/**
 * 保存模板
 * @param {Object} clickObj
 * @param {Integer} type (1:查询;2:统计报表)
 */
function saveTemplate(clickObj,type){
	$form = $(clickObj).closest("form");
	if(type == 1){
		selectAllOption("fields_select");
  	}
	$.ajax({
		type: 'POST',
		url: 'search/saveTemplate.do',
		data: $form.serializeArray(),
		dataType:"json",
		cache: false,
		success: saveTempCallback,
		error: DWZ.ajaxError
	});
}

/**
 * 删除模板
 * @param {Object} clickObj
 */
function delTemplate(clickObj){
	var template_id = $(clickObj).siblings("#template_sel").find("option:selected").val();
	if(template_id != ""){
		$.ajax({
			type: 'post',
			url: 'search/delTemplate.do',
			data: {'template_id':template_id},
			dataType:"json",
			cache: false,
			success: saveTempCallback,
			error: DWZ.ajaxError
		});
	}else{
		alertMsg.warn("请选择已经存储的模板!");
	}
}

/* 模板保存后的回调函数
 * json: {"statusCode":"200", "message":"保存成功", "act":"insert/update", "template_name":"模板名称", "template_id":"模板id"}
 * json: {"statusCode":"300", "message":"操作失败"}
 * json: {"statusCode":"301", "message":"会话超时"}
*/
function saveTempCallback(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
	
		if ("insert" == json.act){
			//插入模板
			var op_str = "<option value='" + json.template_id +"'>" + json.template_name +"</option>";
			$(op_str).attr("selected",true);
			$(op_str).appendTo($("#template_sel")); 
			
		} else if("update" == json.act) {
			//更新模板
			$("#template_sel option[value="+ json.template_id +"]").text(json.template_name);
			
		}else{
			//删除模板
			$("#template_sel option:selected").remove();
			$("#template_name").val("");
		}
	}
}

/**
 * 切换选择模板
 * @param {Object} callback
 */
function changeTemplate(callback){
	var template_id = $("#template_sel option:selected");
	$("#template_name").val(template_id.text());
	if(template_id.val() != ""){
		$.ajax({
			type: 'post',
			url: 'search/getTemplate.do',
			data: {'template_id':template_id.val()},
			dataType:'xml',
			cache: false,
			success: callback,
			error: DWZ.ajaxError
		});
	}else{
		$("#template_sel option:selected").text("");
	}
}

/**
 * 统计报表切换选择模板后回调函数
* @param {xml} xml
 */
function reportCtCallback(xml){
	if($(xml).text() == ""){
		alertMsg.error("返回模板数据错误!");
		return;
	}
	
	//初始化数据
	$("#reportConditionDiv input:checked").attr("checked",false);
	
	//初始化x、y坐标选择项目
	$("#y_way img[title=删除]").each(function(){
		if($(this).closest("tr").css('display')!="none")delRow($(this));
	});
	$("#x_way img[title=删除]").each(function(){
		if($(this).closest("tr").css('display')!="none")delRow($(this));
	});
	
	//设置模板信息
	$(xml).find("template").each(function(){
		var field_type = $(this).find("type").text();
		
		if(field_type == "yway"){
			//y坐标
			var temp_v = $(this).find("value").text().split(",");
			$(temp_v).each(function(){
				$("#statisticList option[value="+this+"]").attr("selected",true);
				selectStatistic($("#statisticList"),"y_way");
			});
			
		}else if(field_type == "xway"){
			//x坐标
			var temp_v = $(this).find("value").text().split(",");
			$(temp_v).each(function(){
				$("#statisticList option[value="+this+"]").attr("selected",true);
				selectStatistic($("#statisticList"),"x_way");
			});
		
		}else if(field_type == "sum_field"){
			//统计项
			var temp_v = $(this).find("value").text().split(",");
			$(temp_v).each(function(){
				$("#reportConditionDiv input[value="+this+"]").attr("checked",true);
			});			
			
		}else if(field_type == "interval"){
			//数值或日期区间
			var temp_k = $(this).find("key").text();
			var temp_v = $(this).find("value").text();
			if(temp_v == ""){
				$("#reportConditionDiv input[name="+temp_k+"_low]").val(temp_v);
				$("#reportConditionDiv input[name="+temp_k+"_high]").val(temp_v);
			}else{
				$(temp_v.split(",")).each(function(){
					var temp_i = this.split("=");
					if(temp_i[0] == "low")
						$("#reportConditionDiv input[name="+temp_k+"_low]").val(temp_i[1]);
					else
						$("#reportConditionDiv input[name="+temp_k+"_high]").val(temp_i[1]);
				});
			}
			
		}else if(field_type == "key"){
			//文本关键字
			var temp_k = $(this).find("key").text();
			var temp_v = $(this).find("value").text();
			$("#reportConditionDiv input[name="+temp_k+"]").val(temp_v);
			
		}else{
			alertMsg.error("不可识别的数据类型!");
		}
	});
}

/**
 * 查询切换选择模板后回调函数
* @param {xml} xml
 */
function searchCtCallback(xml){
	if($(xml).text() == ""){
		alertMsg.error("返回模板数据错误!");
		return;
	}
	
	//初始化数据
	$("#conditionForm #fields_select option").each(function(){$(this).attr("selected",true);});
	moveAct('fields_select','fields');
	$("#conditionForm #fields option").each(function(){$(this).attr("selected",false);});
		
	//设置模板信息
	$(xml).find("template").each(function(){
		var field_type = $(this).find("type").text();
		
		if(field_type == "interval"){
			//数值或日期区间
			var temp_k = $(this).find("key").text();
			var temp_v = $(this).find("value").text();
			if(temp_v == ""){
				$("#conditionForm input[name="+temp_k+"_low]").val(temp_v);
				$("#conditionForm input[name="+temp_k+"_high]").val(temp_v);
			}else{
				$(temp_v.split(",")).each(function(){
					var temp_i = this.split("=");
					if(temp_i[0] == "low")
						$("#conditionForm input[name="+temp_k+"_low]").val(temp_i[1]);
					else
						$("#conditionForm input[name="+temp_k+"_high]").val(temp_i[1]);
				});
			}
			
		}else if(field_type == "key"){
			//文本关键字
			var temp_k = $(this).find("key").text();
			var temp_v = $(this).find("value").text();
			$("#conditionForm input[name="+temp_k+"]").val(temp_v);
			
		}else if(field_type == "select_field"){
			//选中查询字段
			var temp_k = $(this).find("key").text();
			if($(this).find("value").text() != ""){
				var temp_v = $(this).find("value").text().split(",");
				$(temp_v).each(function(){
					$("#conditionForm #fields option[value="+this+"]").attr("selected",true);
					moveAct('fields','fields_select');
				});
			}
			
		}else{
			alertMsg.error("不可识别的数据类型!"+field_type);
		}
	});
}
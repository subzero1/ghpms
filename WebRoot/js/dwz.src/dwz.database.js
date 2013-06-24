/**
 * @author ZhangHuihua@msn.com
 */
(function($){
	var _lookup = {currentGroup:"", suffix:"", $target:null, pk:"id"};
	var _util = {
		_lookupPrefix: function(key){
			var strDot = _lookup.currentGroup ? "." : "";
			return _lookup.currentGroup + strDot + key + _lookup.suffix;
		},
		lookupPk: function(key){
			return this._lookupPrefix(key);
		},
		lookupField: function(key){
			return this.lookupPk(key);
		}
	};
	
	$.extend({
		bringBackSuggest: function(args){
			var $box = _lookup['$target'].parents(".unitBox:first");
			$box.find(":input").each(function(){
				var $input = $(this), inputName = $input.attr("name"), inputId=$input.attr("id");
				
				for (var key in args) {
					var name = (_lookup.pk == key) ? _util.lookupPk(key) : _util.lookupField(key);

					if (name == inputId) {
						$input.val(args[key]);
						$input.change(); //modify:2012-7-6 ZY 查找带回时,调用输入域的onchange事件
						break;
					}
				}
			});
		},
		bringBack: function(args){
			$.bringBackSuggest(args);
			$.pdialog.closeCurrent();
		}
	});
	
	$.fn.extend({
		lookup: function(){
			return this.each(function(){
				var $this = $(this), options = {mask:true, 
					width:$this.attr('width')||820, height:$this.attr('height')||400,
					maxable:eval($this.attr("maxable") || "true"),
					resizable:eval($this.attr("resizable") || "true"),
					data:DWZ.jsonEval($this.attr("data")) || {} //modify:zy 2013-1-8 查找带回增加data属性以传参数
				};
				$this.click(function(event){
					_lookup = $.extend(_lookup, {
						currentGroup: $this.attr("lookupGroup") || "",
						suffix: $this.attr("suffix") || "",
						$target: $this,
						pk: $this.attr("lookupPk") || "id"
					});
					
					var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
					if (!url.isFinishedTm()) {
						alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
						return false;
					}
					
					$.pdialog.open(url, "_blank", $this.attr("title") || $this.text(), options);
					return false;
				});
			});
		},
		multLookup: function(){
			return this.each(function(){
				var $this = $(this), args={};
				$this.click(function(event){
					var $unitBox = $this.parents(".unitBox:first");
					$unitBox.find("[name='"+$this.attr("multLookup")+"']").filter(":checked").each(function(){
						var _args = DWZ.jsonEval($(this).val());
						for (var key in _args) {
							var value = args[key] ? args[key]+"," : "";
							args[key] = value + _args[key];
						}
					});

					if ($.isEmptyObject(args)) {
						alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
						return false;
					}
					$.bringBack(args);
				});
			});
		},
		suggest: function(){
			var op = {suggest$:"#suggest", suggestShadow$: "#suggestShadow"};
			var selectedIndex = -1;
			return this.each(function(){
				var $input = $(this).attr('autocomplete', 'off').keydown(function(event){
					if (event.keyCode == DWZ.keyCode.ENTER && $(op.suggest$).is(':visible')) return false; //屏蔽回车提交
				});
				
				var suggestFields=$input.attr('suggestFields').split(",");
				
				function _show(event){
					if($input.val()=="") return;	//输入域的值为空时不显示 modify at 2011-12-16
					
					var offset = $input.offset();
					var iTop = offset.top+$input.height();	//this.offsetHeight; modify at 2011-12-16
					var $suggest = $(op.suggest$);
					if ($suggest.size() == 0) $suggest = $('<div id="suggest"></div>').appendTo($('body'));

					$suggest.css({
						left:offset.left+'px',
						top:iTop+'px'
					}).show();
					
					_lookup = $.extend(_lookup, {
						currentGroup: $input.attr("lookupGroup") || "",
						suffix: $input.attr("suffix") || "",
						$target: $input,
						pk: $input.attr("lookupPk") || "id"
					});

					var url = unescape($input.attr("suggestUrl")).replaceTmById($(event.target).parents(".unitBox:first"));
					if (!url.isFinishedTm()) {
						alertMsg.error($input.attr("warn") || DWZ.msg("alertSelectMsg"));
						return false;
					}
					
					var postData = {};
					postData[$input.attr("postField")||"inputValue"] = $input.val();

					$.ajax({
						type:'POST', dataType:"json", url:url, cache: false,
						data: postData,
						success: function(response){
							if (!response) return;
							var html = '';

							$.each(response, function(i){
								var liAttr = '', liLabel = '';
								
								for (var i=0; i<suggestFields.length; i++){
									var str = this[suggestFields[i]];
									if (str) {
										if (liLabel) liLabel += '-';
										liLabel += str;
									}
								}
								for (var key in this) {
									if (liAttr) liAttr += ',';
									liAttr += "'"+key+"':'"+this[key]+"'";	//modify at 2013-1-11 修复json关键字没加引号错误
								}
								html += '<li lookupAttrs="'+liAttr+'">' + liLabel + '</li>';
							});
							
							var $lis = $suggest.html('<ul>'+html+'</ul>').find("li");
							$lis.hoverClass("selected").click(function(){
								_select($(this));
							});
							if ($lis.size() == 1 && event.keyCode != DWZ.keyCode.BACKSPACE) {
								_select($lis.eq(0));
							} else if ($lis.size() == 0){
								var jsonStr = "";
								for (var i=0; i<suggestFields.length; i++){
									if (_util.lookupField(suggestFields[i]) == event.target.name) {
										break;
									}
									if (jsonStr) jsonStr += ',';
									jsonStr += suggestFields[i]+":''";
								}
								jsonStr = "{"+_lookup.pk+":''," + jsonStr +"}";
								$.bringBackSuggest(DWZ.jsonEval(jsonStr));
							}
						},
						error: function(){
							$suggest.html('');
						}
					});

					$(document).bind("click", _close);
					return false;
				}
				function _select($item){
					var jsonStr = "{"+ $item.attr('lookupAttrs') +"}";
					
					$.bringBackSuggest(DWZ.jsonEval(jsonStr));
				}
				function _close(){
					$(op.suggest$).html('').hide();
					selectedIndex = -1;
					$(document).unbind("click", _close);
				}
				
				//$input.focus(_show).click(false).keyup(function(event){ //modify at 2011-12-16
				$input.click(false).keyup(function(event){	
					var $items = $(op.suggest$).find("li");
					switch(event.keyCode){
						case DWZ.keyCode.ESC:
						case DWZ.keyCode.TAB:
						case DWZ.keyCode.SHIFT:
						case DWZ.keyCode.HOME:
						case DWZ.keyCode.END:
						case DWZ.keyCode.LEFT:
						case DWZ.keyCode.RIGHT:
							break;
						case DWZ.keyCode.ENTER:
							_close();
							break;
						case DWZ.keyCode.DOWN:
							if (selectedIndex >= $items.size()-1) selectedIndex = -1;
							else selectedIndex++;
							break;
						case DWZ.keyCode.UP:
							if (selectedIndex < 0) selectedIndex = $items.size()-1;
							else selectedIndex--;
							break;
						default:
							_show(event);
					}
					$items.removeClass("selected");
					if (selectedIndex>=0) {
						var $item = $items.eq(selectedIndex).addClass("selected");
						_select($item);
					}
				});
			});
		},
		
		itemDetail: function(){
			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
				var fields=[];

				$table.find("tr:first th[type]").each(function(i){
					var $th = $(this);
					var field = {
						type: $th.attr("type") || "text",
						patternDate: $th.attr("format") || "yyyy-MM-dd",
						name: $th.attr("name") || "",
						id: $th.attr("id") || "",					//modify at 2011-08-10 表单主从结构中增加id属性
						defaultVal: $th.attr("defaultVal") || "",
						hideName: $th.attr("hideName") || "",		//modify at 2011-08-08 表单主从结构中增加隐藏域
						hideValue: $th.attr("hideValue") || "",		//modify at 2011-08-08 表单主从结构中增加隐藏域的值
						hideId: $th.attr("hideId") || "",			
						size: $th.attr("size") || "12",
						width: $th.width() || "",					//modify at 2011-09-21 增加宽度
						enumUrl: $th.attr("enumUrl") || "",
						enumData: $th.attr("enumData") || "",		//modify at 2011-08-09 扩展选项url参数,解决中文转码问题
						lookupGroup: $th.attr("lookupGroup") || "",
						lookupUrl: $th.attr("lookupUrl") || "",
						lookupPk: $th.attr("lookupPk") || "id",
						suggestUrl: $th.attr("suggestUrl"),
						suggestFields: $th.attr("suggestFields"),
						postField: $th.attr("postField") || "",
						fieldClass: $th.attr("fieldClass") || "",
						fieldAttrs: $th.attr("fieldAttrs") || "",
						comments: $th.text() || ""					//modify at 2011-09-19 列标题作为输入域的注释
					};
					fields.push(field);
				});
				
				$tbody.find("a.btnDel").click(function(){
					var $btnDel = $(this);
					
					/* modify at 2012-07-12 jquery 1.7报错，暂不用
					if ($btnDel.is("[href^=javascript:]")){
						$btnDel.parents("tr:first").remove();
						initSuffix($tbody);
						return false;
					}
					*/
					
					function delDbData(){
						$.ajax({
							type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
							success: function(){
								$btnDel.parents("tr:first").remove();
								initSuffix($tbody);
							},
							error: DWZ.ajaxError
						});
					}
					
					if($btnDel.hasClass("emptyInput")){	//modify at 2011-08-09 扩展emptyInput类,清空字段不执行后台删除,配合保存实现
						var $tr = $btnDel.parents("tr:first");
												
						$tr.find(":input").not($("[name$='.ID']")).each(function(){
							$(this).val("");
							$(this).removeClass();
						});
						
						$tr.hide();
						
					}else{	
						if ($btnDel.attr("title")){
							alertMsg.confirm($btnDel.attr("title"), {okCall: delDbData});
						} else {
							delDbData();
						}
					}
					
					return false;
				});

				var addButTxt = $table.attr('addButton') || "追加行";
				if (addButTxt) {
					var $addBut = $('<div class="button"><div class="buttonContent"><button type="button">'+addButTxt+'</button></div></div>').insertBefore($table).find("button");
					var $rowNum = $('<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>').insertBefore($table);
					
					var trTm = "";
					$addBut.click(function(){
						if (! trTm) trTm = trHtml(fields);
						var rowNum = 1;
						try{rowNum = parseInt($rowNum.val())} catch(e){}

						for (var i=0; i<rowNum; i++){
							var $tr = $(trTm);
							$tr.appendTo($tbody).initUI().find("a.btnDel").click(function(){
								$(this).parents("tr:first").remove();
								initSuffix($tbody);
								return false;
							});
							$tr.find(">td").each(function(){$(this).initDetailWidth();});	//modify at 2011-09-21 初始化宽度
						}
						initSuffix($tbody);
						
					});
				}
			});
			
			/**
			 * 删除时重新初始化下标
			 */
			function initSuffix($tbody) {
				$tbody.find('>tr').each(function(i){
					$(':input, a.btnLook', this).each(function(){
						var $this = $(this), name = $this.attr('name'), id = $this.attr('id'), val = $this.val();

						if (name) $this.attr('name', name.replaceSuffix(i));
						if (id) $this.attr('id', id.replaceSuffix(i));	//	modify at 2011-01-17 
						
						var lookupGroup = $this.attr('lookupGroup');
						if (lookupGroup) {$this.attr('lookupGroup', lookupGroup.replaceSuffix(i));}
						
						var suffix = $this.attr("suffix");
						if (suffix) {$this.attr('suffix', suffix.replaceSuffix(i));}
						
						if (val && val.indexOf("#index#") >= 0) $this.val(val.replace('#index#',i+1));
					});
				});
			}
			
			function tdHtml(field){
				var html = '', suffix = '';
				
				if (field.id.endsWith("[#index#]")) suffix = "[#index#]";	//modify at 2013-1-17 field.name 改为field.id
				else if (field.id.endsWith("[]")) suffix = "[]";
				
				var suffixFrag = suffix ? ' suffix="' + suffix + '" ' : '';
				
				var attrFrag = '';
				if (field.fieldAttrs){
					var attrs = DWZ.jsonEval(field.fieldAttrs);
					for (var key in attrs) {
						attrFrag += key+'="'+attrs[key]+'"';
					}
				}
				switch(field.type){
					case 'del':
						html = '<a href="javascript:void(0)" class="btnDel '+ field.fieldClass + '">删除</a>';
						break;
					case 'lookup':
						var suggestFrag = '';
						if (field.suggestFields) {
							suggestFrag = 'autocomplete="off" lookupGroup="'+field.lookupGroup+'"'+suffixFrag+' suggestUrl="'+field.suggestUrl+'" suggestFields="'+field.suggestFields+'"' + ' postField="'+field.postField+'"';
						}

						html = '<input type="hidden" id="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
							+ '<input type="text" name="'+field.name+'" id="'+field.id+'"'+suggestFrag+' lookupPk="'+field.lookupPk+'" size="'+field.size+'" class="'+field.fieldClass+ '" style="width:0px;" comments="' + field.comments + '"/>'
							+ '<a class="btnLook" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" title="查找带回">查找带回</a>';
						break;
					case 'attach':
						html = '<input type="hidden" id="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
							+ '<input type="text" id="'+field.id+'" name="'+field.name+'" size="'+field.size+'" readonly="readonly" class="'+field.fieldClass+ '" style="width:0px;" comments="' + field.comments + '"/>'
							+ '<a class="btnAttach" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" width="560" height="300" title="查找带回">查找带回</a>';
						break;
					case 'enum':
						var options = DWZ.jsonEval(field.enumData);
						var enum_data = $.extend({enumName:field.enumName, inputName:field.name}, options); //modify at 2011-08-09 扩展选项url参数,解决中文转码问题
						$.ajax({
							type:"POST", dataType:"html", async: false,
							url:field.enumUrl, 
							data:enum_data,		//{inputName:field.name}, 
							success:function(response){
								html = response;
							}
						});
						break;
					case 'date':
						html = '<input type="text" id="'+field.id+'" name="'+field.name+'" value="'+field.defaultVal+'" class="date '+field.fieldClass+'" format="'+field.patternDate+'" size="'+field.size+' style="width:0px;" comments="' + field.comments + '"/>'
							+'<a class="inputDateButton" href="javascript:void(0)">选择</a>';
						break;
					case 'textarea':
						html = '<textarea  id="'+field.id+'" name="'+fieldName+'" value="'+field.defaultVal+'" size="'+field.size+'" style="border-width:0;width:0px;overflow-y:visible;" class="' + field.fieldClass + '" comments="' + field.comments + '"> '+field.value+' </textarea>';
						break;	
					default:
						html = '<input type="text" id="'+field.id+'" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" style="width:0px;" comments="' + field.comments + '" '+attrFrag+'/>';
						break;
				}
				if(field.hideName!="" || field.hideId!="" ) html = '<input type="hidden" id="' + field.hideId + '" name="' + field.hideName + '" value="'+field.hideValue+'"/>' + html;	//modify at 2011-08-08 表单主从结构中增加隐藏域
				return '<td>'+html+'</td>';
			}
			function trHtml(fields){
				var html = '';
				$(fields).each(function(){
					html += tdHtml(this);
				});
				return '<tr class="unitBox">'+html+'</tr>';
			}
		},
		
		selectedTodo: function(){
			
			function _getIds(selectedIds, targetType){
				var ids = "";
				var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
				$box.find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){
					var val = $(this).val();
					ids += i==0 ? val : ","+val;
				});
				return ids;
			}
			return this.each(function(){
				var $this = $(this);
				var selectedIds = $this.attr("rel") || "ids";
				var postType = $this.attr("postType") || "map";

				$this.click(function(){
					var ids = _getIds(selectedIds, $this.attr("targetType"));
					if (!ids) {
						alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
						return false;
					}
					function _doPost(){
						$.ajax({
							type:'POST', url:$this.attr('href'), dataType:'json', cache: false,
							data: function(){
								if (postType == 'map'){
									return $.map(ids.split(','), function(val, i) {
										return {name: selectedIds, value: val};
									})
								} else {
									var _data = {};
									_data[selectedIds] = ids;
									return _data;
								}
							}(),
							success: navTabAjaxDone,
							error: DWZ.ajaxError
						});
					}
					var title = $this.attr("title");
					if (title) {
						alertMsg.confirm(title, {okCall: _doPost});
					} else {
						_doPost();
					}
					return false;
				});
				
			});
		}
	});
	
	//modify at 2011-09-20 增加初始化明细输入域宽度
	$.fn.extend({
		initDetailWidth: function(){
			var $input = $(this).find(":input").not("[type=hidden]");
			var $width = $(this).attr("width") || $(this).width();
			$(this).css("width",$width);
			if($(this).find(".btnLook").size()>0){
				$input.css("width", $width-42+"px");
			}else if($input.hasClass("date")){
				$input.css("width", $width-26+"px");
			}else{
				$input.css("width", $width-6+"px");
			}	
		}
	});
})(jQuery);


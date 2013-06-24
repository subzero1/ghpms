/**
 * 流程表单校验
 * 
 */
(function($){
	$.fn.extend({
		initValidateFields: function(){
			var $this = $(this);
			var validate = $("input[name=validate]", $this).val();			
			if(validate == "") return;
			var validatorOp = DWZ.jsonEval(validate);
			$(":input", $this).not("[type='hidden']").not(":button").each(function(){				
				var $input=$(this), fieldName=$(this).attr("name");				
				if(fieldName && fieldName != ""){					
					var nameMode = fieldName.split(".");						
					if(nameMode.length > 1 && eval("validatorOp." + nameMode[0]) == null){
						//没有输入权限
						$input.attr("disabled",true);
						$input.addClass("readonly").focus(function(){$(this).blur();});	
					}else{
						var field_op = eval("validatorOp." + fieldName);										
						if(field_op){							
							$input.attr("comments",field_op.comments);							
							if(field_op.nullable.toLowerCase()=="n")$input.addClass("required");							
							var field_type = field_op.datatype.toLowerCase();
							
							switch(field_type){							
								case "date":
									$input.addClass("date");
									$input.attr("format","yyyy-MM-dd");
									break;
									
								case "datetime":
									$input.addClass("date");
									$input.attr("format","yyyy-MM-dd HH:mm");
									break;
								
								case "number":
									var dataL = field_op.datalength.split(",");
									if(dataL.length==1 || (dataL.length>1 && dataL[1]=="0"))
										$input.addClass("digits");
									else
										$input.addClass("number");
									$input.attr("maxlength",field_op.datalength);
									break;
									
								default:
									$input.addClass(field_op.datatype);
									$input.attr("maxlength",parseInt(field_op.datalength/2));
									break;
							}
						}else{
							//没有输入权限
							$input.attr("disabled",true);
							$input.addClass("readonly").focus(function(){$(this).blur();});	
						}
					}	
				}else{
					//没有输入权限
					$input.attr("disabled",true);
					$input.addClass("readonly").focus(function(){$(this).blur();});	
				}
				
			});
			
			//针对明细表自动加行生成的输入域进行设置
			$("table[itemdetail]", $this).find("th").each(function(){
				var $th=$(this), thName=$(this).attr("name");				
				if(thName && thName != ""){					
					var nameMode = thName.split(".");
					if(nameMode.length > 1 && eval("validatorOp." + nameMode[0]) == null){
						//没有输入权限
						$th.attr("disabled",true);					
					}else{
						var field_op = eval("validatorOp." + thName);										
						if(field_op){			
							if(field_op.nullable.toLowerCase()=="n")$th.attr("fieldClass",formatStr($th.attr("fieldClass"))+" required");
							
							var field_type = field_op.datatype.toLowerCase();
							
							switch(field_type){
							
								case "date":
									$th.attr("fieldClass","date");
									$th.attr("format","yyyy-MM-dd");
									break;
									
								case "datetime":
									$th.attr("fieldClass","date");
									$th.attr("format","yyyy-MM-dd HH:mm");
									break;
								
								case "number":
									var dataL = field_op.datalength.split(".");
									if(dataL.length==1 || (dataL.length>1 && dataL[1]=="0"))
										$th.attr("fieldClass","digits");
									else
										$th.attr("fieldClass","number");
									$th.attr("maxlength",field_op.datalength);
									break;
									
								default:
									$th.attr("fieldClass",formatStr($th.attr("fieldClass"))+" "+field_op.datatype);
									$th.attr("maxlength",parseInt(field_op.datalength/2));
									break;
							}
						}
					}	
				}
			});
			
		}
	});
	
	
	function formatStr(str){
		if(str == null)	return "";
		else return str.trim();
	}

})(jQuery);

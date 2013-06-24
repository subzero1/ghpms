/*
* Cascade
* version: Alpha 0.03 (07/04/2012)
*
* usage as:
*			$("#parentSelect").cascade({
*				childSelect:$("#childSelect"),
*				tableName:'Ta03_user ta03,Ta01_dept dept',
*				conditionColumn:'ta03.dept_id',
*				valueForOption:'ta03.id',
*				whereClause:'ta03.dept_id=dept.id',
*				orderBy:'ta03.name',
				key:'',
*				extendColumns:{
*					login_id:'ta03.login_id',
*					passwd:'ta03.passwd',
*					title:'ta03.name'
*				},
*				showForOption:{
*					pattern:'[name]\[[login_id]\],[testname]',
*					name:'ta03.name',
*					login_id:'ta03.login_id',
*					testname:'dept.name'
*				}
*			});	
*     
* Copyright 2012 ZhangYi[zhang_yi0627@hotmail.com]
*/
(function ($) {
	$.fn.cascade = function (options) {
		var defaults = {orderBy:'',
						extendColumns:{},
						val:null,
						whereClause:'',
						key:null
		};
		var o = $.extend(defaults,options);
		var extendColumns = o.extendColumns;
		var showForOption = o.showForOption;
		var data = {tableName:o.tableName,
					conditionColumn:o.conditionColumn,
					valueForOption:o.valueForOption,
					orderBy:o.orderBy,
					whereClause:o.whereClause
		};
		$.each(extendColumns ,function(i){
                data["extendColumn_"+i] = extendColumns[i];
        });
        $.each(showForOption ,function(i){
        		data["showForOption_"+i] = showForOption[i];
        });
		return this.each(function () {
			var $this = $(this);
			$this.change(function(){
				var value = $this.val();
				if (o.key && $this.find("option:selected").attr("valueforextend")){
				//var value = (window.JSON && window.JSON.parse ?
				//	window.JSON.parse( $this.find("option:selected").attr("valueforextend") ) :
				//	(new Function("return " + $this.find("option:selected").attr("valueforextend")))())[o.key];
				var value = DWZ.jsonEval($this.find("option:selected").attr("valueforextend"))[o.key];
				}
				data["conditionValue"] = value;//o.val == null ? $this.val() : o.val;
				$.ajax({
					url:'cascade.do',
					data:data,
					type:'post',
					success:function(msg){
						o.childSelect.empty();
						o.childSelect.append(msg);
					}
				});
			});
		});
	};
})(jQuery);


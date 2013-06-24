
/**
 * 初始化页面
 * @param 
 */
function netskyInitWeb(_box){
	var $p = $(_box || document);
	
	/**
	 * 在页面div区域引入其它文件
	 * loadFileArea
	*/
	$(".loadFileArea", $p).each(function(){
		var $this = $(this);
		if($this.attr("loadfile")=="") return;
		var url = unescape($this.attr("loadfile")).replaceTmById($p);
		DWZ.debug(url);
		if (!url.isFinishedTm()) {
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		}
		$this.loadUrl(url);
	});
	
	
	 
	// loadFileArea局部刷新
	$("a[target=loadFileArea]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var divid = $this.attr("rel") || "_blank";
			var url = unescape($this.attr("href")).replaceTmById($p);
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			$("#"+divid,$p).loadUrl(url);//modify zy 2013-1-10 $p
			event.preventDefault();
		});
	});
	
	
	// loadFileArea局部刷新
	$("button[class=divFileReload]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var _divFileReload = $this.closest("div .loadFileArea");
			if(_divFileReload){
				var url = unescape($this.attr("loadfile")).replaceTmById($p);
				
				DWZ.debug(url);
				if (!url.isFinishedTm()) {
					alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
					return false;
				}
				_divFileReload.loadUrl(url);
				
			}
		});
	});
	
	// 表单数据删除前清除
	$("button[class=formDataClear]", $p).each(function(){
		$(this).click(function(event){
		    /**
		    	获得删除按钮
		    */
			var $this = $(this);
			
		    alertMsg.confirm("确定删除吗？", {
			  okCall: function(){
				var v_form = $this.closest("form");
				if(v_form){
				   v_form.attr("id","clear_form");
					var v_input = $("#clear_form :input");
					v_input.each(function(){
						if($(this).attr('keep')!='true' ){
					        /**
					 		  给组件重命名
					 		*/
					 		var v_name = $(this).attr("name");
					 		var v_id = $(this).attr("id");
					 		$(this).attr("name",v_name+"_tmp");
					 		$(this).attr("id",v_id+"_tmp");
						}
					});
					v_form.action = "save.do";
					v_form.submit();
				}
			 }
		  });
	   });
	});
	
	//表单明细输入域宽度自适应
	$("table.itemDetail td",$p).each(function(){
		$(this).initDetailWidth();
	});	
}


/**
 * 系统管理
 * 初始化页面各操作块高度定义
 * @param 
 */
function initSysManageWeb(){
	var temp_h = navTab._panelBox.height() - 60;
	var $pnl =  navTab.getCurrentPanel();
	$(".sysmanage_left", $pnl).height(temp_h+"px");
	$(".sysmanage_right", $pnl).height(temp_h+"px");
	$(".sysmanage_min", $pnl).each(function(){
		$(this).attr("defH",temp_h - $(".sysmanage_max",$pnl).height()-100);
	});
}
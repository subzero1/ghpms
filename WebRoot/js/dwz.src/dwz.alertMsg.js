/**
 * @author ZhangHuihua@msn.com
 */
$.setRegional("alertMsg", {
	title:{error:"Error", info:"Information", warn:"Warning", correct:"Successful", confirm:"Confirmation", input:"Input"},
	butMsg:{ok:"OK", yes:"Yes", no:"No", cancel:"Cancel"}
});
var alertMsg = {
	_boxId: "#alertMsgBox",
	_bgId: "#alertBackground",
	_closeTimer: null,

	_types: {error:"error", info:"info", warn:"warn", correct:"correct", confirm:"confirm", input:"input"},

	_getTitle: function(key){
		return $.regional.alertMsg.title[key];
	},

	_keydownOk: function(event){
		if (event.keyCode == DWZ.keyCode.ENTER) event.data.target.trigger("click");
		//return false; modify at2012-12-19 注销返回
	},
	_keydownEsc: function(event){
		if (event.keyCode == DWZ.keyCode.ESC) event.data.target.trigger("click");
	},
	/**
	 * 
	 * @param {Object} type
	 * @param {Object} msg
	 * @param {Object} buttons [button1, button2]
	 */
	_open: function(type, msg, buttons){
		$(this._boxId).remove();
		var butsHtml = "";
		if (buttons) {
			for (var i = 0; i < buttons.length; i++) {
				var sRel = buttons[i].call ? "callback" : "";
				butsHtml += DWZ.frag["alertButFrag"].replace("#butMsg#", buttons[i].name).replace("#callback#", sRel);
			}
		}
		var boxHtml = DWZ.frag["alertBoxFrag"].replace("#type#", type).replace("#title#", this._getTitle(type)).replace("#message#", msg).replace("#butFragment#", butsHtml);
		
		/*移动msg到页面中间 modify at 2011/05/17*/
		var _top = 0;
		if($.browser.msie){
			_top = parseInt((document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight)/2-40,10);
		}else {
			_top = parseInt((self.innerHeight)/2-40,10);
		}
		
		$(boxHtml).appendTo("body").css({top:-$(this._boxId).height()+"px"}).animate({top:_top+"px"}, 500);
				
		if (this._closeTimer) {
			clearTimeout(this._closeTimer);
			this._closeTimer = null;
		}
		if (this._types.info == type || this._types.correct == type){
			this._closeTimer = setTimeout(function(){alertMsg.close()}, 3500);
		} else {
			$(this._bgId).show();
		}
		
		//用于屏蔽点击回车，触发click事件，再次打开alert
		$('<input type="text" style="width:0;height:0;" name="_alertFocusCtr"/>').appendTo(this._boxId).focus().hide();
		
			
		var jButs = $(this._boxId).find("a.button");
		var jCallButs = jButs.filter("[rel=callback]");
		var jDoc = $(document);
		
		for (var i = 0; i < buttons.length; i++) {
			if (buttons[i].call) jCallButs.eq(i).click(buttons[i].call);
			if (buttons[i].keyCode == DWZ.keyCode.ENTER) {
				if($(boxHtml).find("textarea").size()==0) //modify at2012-12-19 不允许通过回车执行，和textarea需要回车的情况冲突		
				jDoc.bind("keydown",{target:jButs.eq(i)}, this._keydownOk); 		
			}
			if (buttons[i].keyCode == DWZ.keyCode.ESC) {
				jDoc.bind("keydown",{target:jButs.eq(i)}, this._keydownEsc);
			}
		}
		
	},
	close: function(){
		$(document).unbind("keydown", this._keydownOk).unbind("keydown", this._keydownEsc);
		$(this._boxId).animate({top:-$(this._boxId).height()}, 500, function(){
			$(this).remove();
		});
		$(this._bgId).hide();
	},
	error: function(msg, options) {
		this._alert(this._types.error, msg, options);
	},
	info: function(msg, options) {
		this._alert(this._types.info, msg, options);
	},
	warn: function(msg, options) {
		this._alert(this._types.warn, msg, options);
	},
	correct: function(msg, options) {
		this._alert(this._types.correct, msg, options);
	},
	_alert: function(type, msg, options) {
		var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:null};
		$.extend(op, options);
		var buttons = [
			{name:op.okName, call: op.okCall, keyCode:DWZ.keyCode.ENTER}
		];
		this._open(type, msg, buttons);
	},	
	/**
	 * 
	 * @param {Object} msg
	 * @param {Object} options {okName, okCal, cancelName, cancelCall}
	 */
	confirm: function(msg, options) {
		var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:null, cancelName:$.regional.alertMsg.butMsg.cancel, cancelCall:null};
		$.extend(op, options);
		var buttons = [
			{name:op.okName, call: op.okCall, keyCode:DWZ.keyCode.ENTER},
			{name:op.cancelName, call: op.cancelCall, keyCode:DWZ.keyCode.ESC}
		];
		
		this._open(this._types.confirm, msg, buttons);
	},	
	/**
	 * modify at 2012-12-27 弹出消息录入数据确认
	 * @param {Object} msg
	 * @param {Object} options {okName, okCal, cancelName, cancelCall}
	 */
	input: function(msg, options) {
		var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:null, cancelName:$.regional.alertMsg.butMsg.cancel, cancelCall:null};
		$.extend(op, options);
		var buttons = [
			{name:op.okName, call: op.okCall, keyCode:DWZ.keyCode.ENTER},
			{name:op.cancelName, call: op.cancelCall, keyCode:DWZ.keyCode.ESC}
		];
		if(options.title!=null)
			$.regional.alertMsg.title.input = options.title;
		else
			$.regional.alertMsg.title.input = "";
		
		this._open(this._types.input, msg, buttons);
	}
};


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var width=0;
function moveTop(){
    move("t_sta", "top")
}

function moveBottom(){
    move("t_sta", "bottom")
}

function moveUp(){
    move("t_sta", "up")
}

function moveDown(){
 	move("t_sta", "down")
}
$(function(){
	var selectO = $("#t_sta option");
	selectO.each(function(){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width += tmp;
	});
	$("#configW").val(width);
	$("#screenW").val(document.body.clientWidth-200);
	$("#clearbutton").click(function(){
		$("#clear").val("yes");
		$("#form1").submit();
	})
})
function changeWidth(obj,obj1){
	var selectO = $("#"+obj+" option:selected");
	selectO.each(function(){
		if (obj == "s_sta"){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width += tmp;
		} else if (obj == "t_sta"){
		var tmp = parseInt($(this).val().split("(")[1]);
		if(!isNaN(tmp))
		width -= tmp;
		}
	});
	if (width>=parseInt($("#screenW").val())){
		$("#configW").css("color","red");
	}else {
		$("#configW").css("color","black");
	}
	$("#configW").val(width);
	moveAct(obj,obj1);
}

	/*
	 *点击搜索框的时候将‘录入后按回车进行搜索’去掉。
	 */
	$("#_keyWord").click(function(){
		var keyWord = $(this).val();
		if(keyWord != '' && keyWord != null && keyWord.indexOf('回车') != -1){
			$(this).val('');
		}
	})
	/* 
	 *回车按关键字进行搜索。
	 */
	$("#_keyWord").keypress(function(e){
	    
	    var counter = 0;
		var key = e.keyCode; //获取回车键
		
        if(key.toString() == "13"){
            var s_var = $(this).val(); //获取搜索关键字
        	$("#s_sta").children().each(function(k,v){ //k,v 隐含参数 k:当前OPTION位置序号 v:当前OPTION对象。 
        		var gcmc = $(v).html();  
        		$(v).removeAttr('selected');             //将选中的OPTION取消选中。
        		if(gcmc.indexOf(s_var) != -1){
        			$(v).attr('selected','true');        //将符合条件的OPTION选中。
        			counter ++;
        		}
        	});
        	move('s_sta', 'bottom'); 
        	if(counter == 0){ 
        		alertMsg.warn('未找到符合条件的选项');
        	}
            return false;
        }
	})
</script>
<div class="page">
	<div class="pageContent">
		<form id="form1" action="saveGcsjListConfig.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_sta');"> 
			<input type="hidden" name="module_id" value="${param.module_id }"/>
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_sta" name="s_sta" multiple=1 type=select-multiple  ondblclick="javascript:changeWidth('s_sta','t_sta')" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${fieldList}">
								<option value="${obj.id }(${obj.width }">${obj.comments }</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:changeWidth('s_sta','t_sta')"><br/>
							<input type="button" value="&lt;" onclick="javascript:changeWidth('t_sta','s_sta')">
						</td>
						<td>
							<select id="t_sta" name="t_sta" multiple=1 type=select-multiple   ondblclick="javascript:changeWidth('t_sta','s_sta')" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${configList}">
								<option value="${obj.id }(${obj.width }">${obj.comments }</option>
							</c:forEach>
							</select>
						</td>
						<td align="right">
							<input type="button" onclick="javascript:moveTop()" value="行首"/><br/>
							<input type="button" onclick="javascript:moveBottom()" value="行尾"/><br/>
							<input type="button" onclick="javascript:moveUp()" value="上移"/><br/>
							<input type="button" onclick="javascript:moveDown()" value="下移"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"><label>屏幕允许宽度：</label><input type="text" style="width:40px;text-align:center;" id="screenW" readonly value=""/> &nbsp;px</td>
						<td colspan="2"><label>配置列表宽度：</label><input type="text" style="width:40px;text-align:center;" id="configW" readonly value=""/> &nbsp;px</td>
					</tr>
					
												<tr>
						<td colspan="2"><input type="text" id="_keyWord" style="width:200" value="录入后按回车进行搜索" />
						<input type="text" name="" style="display:none;"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<input type="hidden" name="clear" id="clear"/>
						<div class="button"><div class="buttonContent"><button type="Button" id="clearbutton">恢复默认</button></div></div>
					</li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>

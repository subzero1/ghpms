<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
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
        	$("#s_field").children().each(function(k,v){ //k,v 隐含参数 k:当前OPTION位置序号 v:当前OPTION对象。 
        		var gcmc = $(v).html();  
        		$(v).removeAttr('selected');             //将选中的OPTION取消选中。
        		if(gcmc.indexOf(s_var) != -1){
        			$(v).attr('selected','true');        //将符合条件的OPTION选中。
        			counter ++;
        		}
        	});
        	move('s_field', 'bottom'); 
        	if(counter == 0){ 
        		alertMsg.warn('未找到符合条件的选项');
        	}
            return false;
        }
	})
</script>
<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveNodeFields.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_field');"> 
			<input type="hidden" name="node_id" value="${param.id}">
			<input type="hidden" name="node_type" value="${param.node_type}">
			<div class="pageFormContent" layoutH="56">
				<table style="width:100%;height:100%;min-height:270px;">
					<tr>
						<td style="width:40%;height:90%;">
							<select id="s_field" name="s_field" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_field','t_field');" style="width:100%;height:100%;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_fields}">
								<option title="${obj.comments}"  value="${obj.id}">${obj.comments}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:7%;max-width:50px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_field','t_field');"><br/><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_field','s_field');">
						</td>
						<td style="width:40%;height:90%;"">
							<select id="t_field" name="t_field" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_field','s_field');" style="width:100%;height:100%;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_fields}">
								<option title="${obj.comments}"  value="${obj.id}">${obj.comments}</option>
							</c:forEach>
							</select>
							<input type="hidden" id="fields" name="fields"/>
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" valign="top" style="padding-top:5px;"><input type="text" id="_keyWord" style="width:200" value="录入后按回车进行搜索" />
						<input type="text" name="" style="display:none;"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>

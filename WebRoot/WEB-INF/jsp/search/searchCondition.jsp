<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">

//从列表中获取已经设置的查询条件
$(function(){
	
	//避免冲突,删除统计表的条件缓存;
	$("#mainReportCondition").empty();
	
	$p = $(navTab._getPanel("${param.navtab }") || document);
	var src_o = $("#pagerForm input", $p);
	var targ_o = $("#conditionForm input");
	src_o.each(function(){
		var src_input = this;
		if($(src_input).attr("name")=="fields_select"){
			$("#conditionForm #fields option[value="+$(src_input).val()+"]").attr("selected",true);
			moveAct('fields','fields_select');
		}
		if($(src_input).attr("name")!="fields_select" && $(src_input).attr("name")!="ids"){
			targ_o.each(function(){
				var targ_input = this;
				if($(src_input).attr("name") == $(targ_input).attr("name"))
					$(targ_input).val($(src_input).val());
			});
		}
	});
	
	$("#template_sel",$.pdialog.getCurrent()).change();
});
function changeTemp(obj){
	changeTemplate(obj);
	var admin="${admin}";
	if(admin!='true'){
	var $option=$("#template_sel option[ selected='selected']");
	var $saveTemp=$("input[value='保存模板']");
	var $deleTemp=$("input[value='删除模板']");
	if($option.attr('class')==1){
		 $saveTemp.attr('disabled','disabled');
		 $deleTemp.attr('disabled','disabled');
	}else{
		$saveTemp.removeAttr('disabled');
		$deleTemp.removeAttr('disabled');
	}
	}
}
function setNull(_this){
	$(_this).val("null")//
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
        	$("#fields").children().each(function(k,v){ //k,v 隐含参数 k:当前OPTION位置序号 v:当前OPTION对象。 
        		var gcmc = $(v).html();  
        		$(v).removeAttr('selected');             //将选中的OPTION取消选中。
        		if(gcmc.indexOf(s_var) != -1){
        			$(v).attr('selected','true');        //将符合条件的OPTION选中。
        			counter ++;
        		}
        	});
        	move('fields', 'bottom'); 
        	if(counter == 0){ 
        		alertMsg.warn('未找到符合条件的选项');
        	}
            return false;
        }
	})
</script>

<div class="page">
	<div class="pageContent">
		<form id="conditionForm" action="" method="post" >
			<input type="hidden" id="page" name="page" value="${page}"/>
			<input type="hidden" id="module_id" name="module_id" value="${param.module_id}"/>
		  
			<div class="pageFormContent" layoutH="56">
				<div style="width:370px;position:absolute;top:0px;left:10px;">
					<div class="title01">
						<h3>字段选择：</h3>
					</div>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc">
						<table width="350" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
							<tr>
								<td colspan="4">
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" width="145">
									<select name="fields" id="fields" style="width:145px;height:218px;" ondblclick="moveAct('fields', 'fields_select')" multiple=1 type=select-multiple>
										<c:forEach var="obj" items="${fieldList}">
											<option value="${obj.id }">${obj.comments }</option>
										</c:forEach>
									</select>
								</td>
								<td align="center" width="20">
								    <br/><br/>
									<input type="button" class="button-td" style="width:20px;height:20px;" onclick="javascript:moveAct('fields', 'fields_select');" value="=&gt;" /><br/>
									<br/><br/>
									<input type="button" class="button-td" style="width:20px;height:20px;" onclick="javascript:moveAct('fields_select', 'fields');" value="&lt;=" /><br/>
								<br /><br /><br /></td>
								<td valign="top" width="155">
									<select id="fields_select" name="fields_select" style="width:145px;height:218px;" multiple ondblclick="moveAct('fields_select','fields')"></select>
								</td>
								<td width="40">
									 <input type="button" class="button-td" style="height:20px;" onclick="javascript:move('fields_select', 'top');" value="行首" /><br/>
								    <input type="button" class="button-td" style="height:20px;" onclick="javascript:move('fields_select', 'up');" value="上移" /><br/>
								    <input type="button" class="button-td" style="height:20px;" onclick="javascript:move('fields_select', 'down');" value="下移" /><br/>
									<input type="button" class="button-td" style="height:20px;" onclick="javascript:move('fields_select', 'bottom');" value="行尾" />
																
								</td>
							</tr>
							<tr>
						<td colspan="2"><input type="text" id="_keyWord" style="width:200" value="录入后按回车进行搜索" />
						<input type="text" name="" style="display:none;"/>
						</td>
					</tr>
						</table>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>
				<div style="float:left;width:10px;">&nbsp;
				</div>
				<!-- 右侧条件选择 -->
				<div style="width:390px;position:absolute;top:0px;left:390px;">
			   				
					<div class="title01">
						<h3>查询条件：</h3>
					</div>
			   		<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc" style="height:158px;">
						<table width="390" border="0" cellspacing="0" cellpadding="0">
		 		        	<tr>
		 		          	  <td>
		 		          		<table id="tab_table">	
		 		          			<tr><c:if test="${fn:length(keyList) > 0}">
		 		          				<td width="70" style="text-align:center;cursor:pointer;height:20px;" onclick="javascript:tabSearchCondition(this,0);">关键字</td>
		 		          				</c:if>
		 		          				<c:if test="${fn:length(selectList) > 0}">
		 		          				<td width="70" style="text-align:center;cursor:pointer;height:20px;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,1);">复选框</td>
		 		          				</c:if>
		 		          				<c:if test="${fn:length(numberList) > 0}">
		 		          				<td width="70" style="text-align:center;cursor:pointer;height:20px;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,2);">数值</td>
		 		          				</c:if>
		 		          				<c:if test="${fn:length(userList) > 0}">
		 		          				<td width="70" style="text-align:center;cursor:pointer;height:20px;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,3);">关键人</td>
		 		          				</c:if>
								        <c:if test="${fn:length(dateList) > 0}">
								        <td style="text-align:center;cursor:pointer;height:20px;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,4);">关键时间点</td>
								        </c:if>
								     </tr>
								</table>
							 </td>
							</tr>
							<tr>
								<td>
								 <div id="content_div" style="width:410px;height:135px;overflow-y:auto;line-height:12px;" class="scroll-body">
									<div style="display:block;">
										<table width="400" border="0" cellspacing="2" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
											<c:forEach var="obj" items="${keyList}">
											<tr>
												<td width="100" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
												<td width="300"><input name="${obj.id }" value="" style="width:280px;"/></td>			
											</tr>
											</c:forEach>
										</table>
									</div>
									<div style="display:none;">
										<table width="400" border="0" cellspacing="2" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
											<c:forEach var="obj" items="${selectList}">
											<tr>
												<td	width="90" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
												<td width="310"><input name="${obj.id }" fieldName="${obj.name }"  style="width:280px;"  title="双击选择${obj.comments }" ondblclick="javascript:$.pdialog.open('${obj.selecturl }&name='+this.name+'&module_id='+${obj.module_id }, 'sel_key', '选择${obj.comments}', {mask:true,width:500,height:400});"/></td>			
											</tr>
											</c:forEach>
										</table>
									</div>
									<div style="display:none;">
										<table width="400" border="0" cellspacing="2" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
											<c:forEach var="obj" items="${numberList}">
												<tr>
													<td width="100" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
													<td style="width:130px"><input name="${obj.id }_low" value="" style="width:120px"/></td>
													<td style="width:30px;text-align:center;">≤</td>
													<td style="width:140px"><input name="${obj.id }_high" value="" style="width:120px"/></td>			
												</tr>
											</c:forEach>
										</table>
									</div>
									<div style="display:none;">
										<table width="400" border="0"  cellspacing="2" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
											<c:forEach var="obj" items="${userList}">
												<tr>
													<td width="100" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
													<td width="300"><input name="${obj.id }" value="" style="width:280px;"/></td>			
												</tr>
											</c:forEach>
										</table>
									</div>
									<div style="display:none;">
										<table width="400" border="0" cellspacing="2" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
											<c:forEach var="obj" items="${dateList}">
												<tr><td style="width:140px" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }" /></td>
													<td style="width:120px;text-align:center;"><input name="${obj.id }_low" value="" style="width:100px;" class="date" ondblclick="javascript:setNull(this);"/></td>			
													<td style="width:20px;">--</td>			
													<td style="width:120px;"><input name="${obj.id }_high" value="" style="width:100px;" class="date" ondblclick="javascript:setNull(this);"/></td>			
												</tr>
											</c:forEach>
										</table>
									</div>
									</div>									
								</td>
							</tr>
		  			   </table>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					
					
					<div class="title01">
						<h3>模板设置${param.template_id }：</h3>
					</div>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc">
						<div style="height:24px;">
							<table width="400" height="23" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
								<tr>
									<td>
										<div style="position:absolute;" >
										<input type="hidden" name="type" value="2"/>
										<input id="template_name" name="template_name" value="" style="width:136px;"/>
										</div>
										<div>
										<select id="template_sel" name="template_id" style="width:160px" onchange="javascript:changeTemp(searchCtCallback);" value="${param.template_id }">
											<option value="" >新建模板</option>
											<c:forEach var="obj" items="${templateList}">
												<option class="${obj.user_id }" value="${obj.id }" <c:if test="${obj.id==param.template_id }">selected</c:if> >${obj.name }</option>
											</c:forEach>
										</select>&nbsp;<input type="button" value="保存模板" onclick="javascript:saveTemplate(this,1);"/>
											<input type="button" value="删除模板" onclick="javascript:delTemplate(this);"/>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:searchOrExcelExport(this, 'search/searchList.do?module_id=${param.module_id}&navtab=${param.navtab}', dialogToNavTabSearch, '${param.navtab }|fields_select');">查 询</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:searchOrExcelExport(this, 'search/searchListExport.do');">EXCEL导出</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>


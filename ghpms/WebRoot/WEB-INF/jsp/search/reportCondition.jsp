<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
//从主界面中获取已经设置的查询条件
$(function(){	
	var module_id = "${param.module_id }";
	var condition = $("#" + module_id , $("#mainReportCondition"));
	if(condition.length>0 && condition.html()!="") $("#reportConditionDiv form").replaceWith(condition.find("form"));
	else	$("#mainReportCondition").empty();
	$("#template_sel",$.pdialog.getCurrent()).change();
});

//报表输出前检测
function reportDoCheck(butt,excel){
	var sum_item = $("input[name=sum]:checked");
	
	var y_way = $("#reportConditionDiv #y_way tr");
	var x_way = $("#reportConditionDiv #x_way tr");
	if(y_way.size()==2 && x_way.size()==1){
		alertMsg.warn("坐标必选!");
		return;
	}


	if(excel == 0){
		creatHiddenCondition($(butt).closest('form'));
		searchOrExcelExport(butt, 'search/report.do', dialogToNavTabSearch, 'report');
	}else{
		searchOrExcelExport(butt, 'search/reportExport.do');
	}
}

//在主界面创建隐藏的条件
function creatHiddenCondition(c_form){
	var module_id = "${param.module_id }";
	var mainDiv = $("#mainReportCondition");
	var condi = $("#"+module_id , mainDiv);
	if(condi.length==0){
		//删除原条件缓存
		mainDiv.empty();
		//创建新的条件缓存
		var condi_html = "<div id='" + module_id + "' style='display: none;'></div>";
		$(condi_html).appendTo(mainDiv);
		condi = $("#"+module_id , mainDiv);
	}
	//c_form.clone().appendTo(condi);
}

</script>

<div class="page">
	<div id="reportConditionDiv" class="pageContent">
		<form action="" method="post">
			<input type="hidden" id="module_id" name="module_id" value="${param.module_id}"/>
			<input type="hidden" id="page" name="page" value="${page}"/>
		  
			<div class="pageFormContent" layoutH="56">
				<div style="width:100%;">
					<!-- 左侧统计项设置 -->
					<div style="width:390px;position:absolute;top:0px;left:10px;">
						<div class="title01">
							<h3>坐标设置：</h3>
						</div>
						<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="contentc">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" >
								<tr>
									<td width="100">
										<!-- 坐标项 -->
										<table border="0" style="width:100px;border-collapse:collapse;display:inline;" border="0" cellspacing="0" cellpadding="0" >
											<tr>
												<td valign="top" width="100">
													<select multiple name="statisticList" id="statisticList" style="width:95px;height:150px;" size="${fn:length(statisticList)}" ondblclick="selectStatistic(this,'y_way')">
														<c:forEach var="obj" items="${statisticList}">
															<option value="${obj.id }">${obj.comments }</option>
														</c:forEach>
													</select>
												</td>
											</tr>
										</table>
									</td>
									<td width="360" valign="top" align="center">
										<div style="scroll-body" style="width:360px;height:90px;">
										<table width="100%" border="0"  cellspacing="0" cellpadding="0">
											<tr>
												<td style="width:140px;vertical-align:top;">
												<!-- Y坐标 -->
												<table id="y_way" style="width:140px;border-collapse:collapse;display:inline;" border="0" cellspacing="0" cellpadding="0" >
													<tr>
														<th colspan="3" class="t-center">Y坐标</th>
													</tr>
													<tr style="display:none;" id="model_tr">
														<td width="60"></td>
														<td width="35">
															<input type="hidden" name="statistic"/>
															<select style="width:35px;" name="way" onchange="javascript:changeWay(this);">
																<option value="Y">Y</option>
																<option value="X">X</option>
															</select>
														</td>
														<td width="45">
															<img src="Images/icon2.gif" onclick="javascript:moveRow(this,'up');" style="cursor:pointer" title="向上移动" />
															<img src="Images/icon.gif" onclick="javascript:moveRow(this,'down');" style="cursor:pointer" title="向下移动" />
															<img src="Images/icon3.gif" onclick="javascript:delRow(this);" style="cursor:pointer" title="删除" />												
														</td>
													</tr>
												</table>
												</td>
												<td style="vertical-align:top;">
												<!-- X坐标 -->
												<table id="x_way" style="width:140px;border-collapse:collapse;display:inline" border="0" cellspacing="0" cellpadding="0" >
													<tr>
														<th colspan="3" class="t-center">X坐标</th>
													</tr>
												</table>
												</td>
											</tr>
										</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
						
						<div class="title01">
							<h3>统计项选择：</h3>
						</div>
						<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="contentc">
							<div style="height:30px;">
							<c:forEach var="obj" items="${numberList}">
								<input type="checkbox" name="sum" value="${obj.id }" checked/>${obj.comments }
							</c:forEach>
							</div>
						</div>
						<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					
					</div>
					
					<!-- 右侧条件选择 -->
					<div style="width:350px;position:absolute;top:0px;left:410px;">
				   				
						<div class="title01">
							<h3>统计条件：</h3>
						</div>
				   		<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="contentc" style="height:150px;">
					 		<table width="340" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;border:1px solid #d1d6d9;">
					 			<tr>
					 		    	<td>
					 		        	<table id="tab_table" width="100%" height="26">	
					 		          		<tr><c:if test="${not empty keyList}">
						          				<td width="55px" style="text-align:center;cursor:pointer;" onclick="javascript:tabSearchCondition(this,0);">关键字</td>
						          				</c:if>
						          				<c:if test="${not empty selectList}">
						          				<td width="70px" style="text-align:center;cursor:pointer;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,1);">复选条件</td>
						          				</c:if>
						          				<c:if test="${not empty numberList}">
						          				<td width="55px" style="text-align:center;cursor:pointer;height:15px;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,2);">金额</td>
						          				</c:if>
						          				<c:if test="${not empty userList}">
						          				<td width="55px" style="text-align:center;cursor:pointer;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,3);">关键人</td>
						          				</c:if>
										        <c:if test="${not empty dateList}">
										        <td style="text-align:center;cursor:pointer;background-color:#dee0e1" onclick="javascript:tabSearchCondition(this,4);">关键时间点</td>
										        </c:if>
									    	</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<div  id="content_div"style="width:340px;height:120px;overflow-y:auto;" class="scroll-body">
											<div style="display:block;">
												<table width="300" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
													<c:forEach var="obj" items="${keyList}">
													<tr>
														<td width="100" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
														<td width="200"><input name="${obj.id }" class="td-input" value="" style="width:100%"/></td>			
													</tr>
													</c:forEach>
												</table>
											</div>
											<div style="display:none;">
												<table width="300" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
													<c:forEach var="obj" items="${selectList}">
													<tr>
														<td	width="90" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
														<td width="210"><input name="${obj.id }" class="td-input" <c:if test="${obj.comments=='管理部门'&&user.login_id!='admin' }">value="${xqbms }"</c:if>  style="width:220px;" <c:if test="${(user.login_id=='admin'&&obj.comments=='管理部门')||obj.comments!='管理部门' }"> title="双击选择${obj.comments }" ondblclick="javascript:$.pdialog.open('${obj.selecturl }&name='+this.name, 'sel_key', '选择${obj.comments }', {mask:true,width:500,height:400});</c:if>"/></td>			
													</tr>
													</c:forEach>
												</table>
											</div>
											<div style="display:none;">
												<table width="300" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
													<c:forEach var="obj" items="${numberList}">
														<tr>
															<td class="t-right" style="width:90px">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
															<td style="width:90px"><input name="${obj.id }_low" class="td-input" value="" style="width:100px;"/></td>
															<td class="t-center" style="width:10px">≤</td>
															<td style="width:90px"><input name="${obj.id }_high" class="td-input" value="" style="width:100px;"/></td>			
														</tr>
													</c:forEach>
												</table>
											</div>
											<div style="display:none;">
												<table width="300" border="0"  cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
													<c:forEach var="obj" items="${userList}">
														<tr>
															<td width="100" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }"/></td>
															<td width="190"><input name="${obj.id }" class="td-input" value="" style="width:210px;"/></td>			
														</tr>
													</c:forEach>
												</table>
											</div>
											<div style="display:none;">
												<table width="300" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
													<c:forEach var="obj" items="${dateList}">
														<tr><td style="width:120px" class="t-right">${obj.comments }：<input type="hidden" name="ids" value="${obj.id }" /></td>
															<td style="width:85px"><input name="${obj.id }_low" value="" style="width:85px;" class="date"/></td>			
															<td style="width:10px;text-align:center;">-</td>
															<td style="width:85px;"><input name="${obj.id }_high" value="" style="width:85px;" class="date"/></td>			
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
							<h3>模板设置${param.id}：</h3>
						</div>
						<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="contentc">
							<div style="height:30px;;">
								<table>
									<tr>
										<td>
											<div style="position:absolute;"><input id="template_name" name="template_name" value="" style="width:140px"/></div>
											<div>
											<select id="template_sel" name="template_id" style="width:160px" onchange="javascript:changeTemplate(reportCtCallback);">
												<option value="">新建模板</option>
												<c:forEach var="obj" items="${templateList}">
													<option value="${obj.id }" <c:if test="${obj.id==param.template_id }">selected</c:if>>${obj.name }</option>
												</c:forEach>
											</select>&nbsp;<input type="button" value="保存模板" onclick="javascript:saveTemplate(this,2);"/>
											<c:if  test="${admin == true}">
												<input type="button" value="删除模板" onclick="javascript:delTemplate(this);"/>
											</c:if>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					</div>
					</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:reportDoCheck(this,0);">统 计</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" name="reportToExcel" onclick="javascript:reportDoCheck(this,1);">EXCEL导出</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close" onclick="javascript:creatHiddenCondition($(this).closest('form'));">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>


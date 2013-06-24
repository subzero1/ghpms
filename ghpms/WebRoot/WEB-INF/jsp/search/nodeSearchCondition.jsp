<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="pss"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">

//从列表中获取已经设置的查询条件
$(function(){
	//避免冲突,删除统计表的条件缓存;
	$("#mainReportCondition").empty();
	/*
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
	
	$("input[name='doc_status']").val("0,1,2,4");
	*/
});
 /*
 function test(){ 
 	$("input[name='toperson']").val("yes");
 	var x=$("input[name='toperson']").val(); 
 }
 */
</script>

<div class="page" style="overflow-x: hidden">
	<div class="pageContent" style="overflow-x: hidden">
		<form id="conditionForm" action="" method="post">
			<input type="hidden" id="page" name="page" value="${page}" />
			<input type="hidden" id="module_id" name="module_id"
				value="${param.module_id}" />

			<div class="pageFormContent" layoutH="56">


				<!-- 右侧条件选择 -->
				<div style="width: 400px; position: absolute; top: 0px; left: 10px;">

					<div class="title01">
						<h3>
							查询条件：
						</h3>
					</div>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc" style="height: 158px;">
						<table width="400" border="0" cellspacing="0" cellpadding="0"
							style="border-collapse: collapse;">
							<tr>
								<td>
									<table id="tab_table">
										<tr>
											<c:if test="${fn:length(selectList) > 0}">

											</c:if>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<div id="content_div"
										style="width: 410px; height: 135px; overflow-y: auto;"
										class="scroll-body">
										<div style="display: block;">
											<table width="400" cellspacing="0" cellpadding="0" border="0"
												class="data-table2" style="border-collapse: collapse;">
											</table>

										</div>
										<div style="display: block;">
											<table width="400" border="0" cellspacing="0" cellpadding="0"
												class="data-table2" style="border-collapse: collapse;">
												<tr>
													<td class="t-right">
														表单名称：
													</td>
													<td>
														<pss:htmlSelect style="width:286px"
															objectForOption="bdmcList" showForOption="name"
															valueForOption="id" name="bdmc_id" value="" />
															<input type="hidden" name="doc_status" value="0,1,2,4">
													</td>
												</tr>
												 
											 
											</table>
										</div>

									</div>
								</td>
							</tr>
						</table>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc">
						<div style="height: 24px;">
							<table width="400" height="23" border="0" cellspacing="0"
								cellpadding="0" style="border-collapse: collapse;">
								<tr>
									<td>
										<div style="position: absolute;">
											<input type="hidden" name="type" value="2" />
											是否具体到人：
											<input type="checkbox" name="toperson" id="toperson" value="yes" /> 
										</div>
										<div>

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
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button"
									onclick="javascript:searchOrExcelExport(this, 'search/jdcltj.do?module_id=${param.module_id}&navtab=${param.navtab}', dialogToNavTabSearch, '${param.navtab }|fields_select');">
									查 询
								</button>
							</div>
						</div>
					</li>
				 
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" class="close">
									取 消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>


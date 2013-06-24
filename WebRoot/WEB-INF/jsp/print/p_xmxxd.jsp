<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				项目信息单			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">投资切块</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.qkdl}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">切块细项</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.qkxl}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">所属区域</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ssdq}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black" colspan="6" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">预算费用（费用单位：元）</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">总投资</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_je}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">建安费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_jaf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">仪表费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_ybf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">人工费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_rgf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">材料费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_clf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">其他费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_qtf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">技工工日</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_jggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设备费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_sbf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_sjf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">普工工日</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_pggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">机械费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_jxf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_jlf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">立项金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.lxje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">立项时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.lxsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">需求部门</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xqbm}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计单位</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sjdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sjpgsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工单位</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sgdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sgpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理单位</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jldw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目管理员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmgly}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工管理员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sgfzr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理工程师</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlgcs}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计人员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sjry}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">验收人员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ysry}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">立项管理员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.lxgly}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">开工时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sjkgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">竣工时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sjjgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">验收时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.yssj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">预算类型</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.yslx}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目阶段</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmjd}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目状态</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmzt}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目说明</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmsm}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black" colspan="6" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">合同信息</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计合同编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sjhtbh}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sjhtje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">签订日期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sjhtqdrq}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工合同编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sghtbh}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.sghtje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">签订日期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.sghtqdrq}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理合同编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlhtbh}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlhtje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">签订日期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.jlhtqdrq}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="9mm">
			<td border="1px solid black" border-top="0px" colspan="6"><p font="Simsun" font-size="10pt" color="black" align="center">结算 (费用单位为：元)</p></td>
		</tr>
		<tr height="9mm">
			<td border="1px solid black" width="25mm"><p font="Simsun" font-size="10pt" color="black" align="center">费　用</p></td>						
		 	<td border="1px solid black" width="28mm"><p font="Simsun" font-size="10pt" color="black" align="center">结算</p></td>					
		 	<td border="1px solid black" width="29mm"><p font="Simsun" font-size="10pt" color="black" align="center">初审</p></td>
		 	<td border="1px solid black" width="28mm"><p font="Simsun" font-size="10pt" color="black" align="center">审计</p></td>
		 	<td border="1px solid black" width="28mm"><p font="Simsun" font-size="10pt" color="black" align="center">核减额</p></td>
		 	<td border="1px solid black" width="28mm"><p font="Simsun" font-size="10pt" color="black" align="center">核减率</p></td>
		</tr>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">技工工日</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jggr!=0&&td01_xmxx.ss_jggr!=null}"><fmt:formatNumber value="${td01_xmxx.ss_jggr}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_jggr!=0&&td01_xmxx.cs_jggr!=null}"><fmt:formatNumber value="${td01_xmxx.cs_jggr}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_jggr!=0&&td01_xmxx.sd_jggr!=null}"><fmt:formatNumber value="${td01_xmxx.sd_jggr}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jggr!=0&&td01_xmxx.ss_jggr!=null}"><fmt:formatNumber value="${td01_xmxx.ss_jggr-td01_xmxx.sd_jggr}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jggr!=0&&td01_xmxx.ss_jggr!=null}"><fmt:formatNumber value="${(td01_xmxx.ss_jggr-td01_xmxx.sd_jggr)/td01_xmxx.ss_jggr*100}" pattern="#,##0.00"/></c:if></p></td>
		</tr>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">普工工日</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_pggr!=0&&td01_xmxx.ss_pggr!=null}"><fmt:formatNumber value="${td01_xmxx.ss_pggr}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_pggr!=0&&td01_xmxx.cs_pggr!=null}"><fmt:formatNumber value="${td01_xmxx.cs_pggr}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_pggr!=0&&td01_xmxx.sd_pggr!=null}"><fmt:formatNumber value="${td01_xmxx.sd_pggr}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_pggr!=0&&td01_xmxx.ss_pggr!=null}"><fmt:formatNumber value="${td01_xmxx.ss_pggr-td01_xmxx.sd_pggr}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_pggr!=0&&td01_xmxx.ss_pggr!=null}"><fmt:formatNumber value="${(td01_xmxx.ss_pggr-td01_xmxx.sd_pggr)/td01_xmxx.ss_pggr*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">材料费</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_clf!=0&&td01_xmxx.ss_clf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_clf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_clf!=0&&td01_xmxx.cs_clf!=null}"><fmt:formatNumber value="${td01_xmxx.cs_clf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_clf!=0&&td01_xmxx.sd_clf!=null}"><fmt:formatNumber value="${td01_xmxx.sd_clf}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_clf!=0&&td01_xmxx.ss_clf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_clf-td01_xmxx.sd_clf}" pattern="#,##0.00"/></c:if></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_clf!=0&&td01_xmxx.ss_clf!=null}"><fmt:formatNumber value="${(td01_xmxx.ss_clf-td01_xmxx.sd_clf)/td01_xmxx.ss_clf*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<c:set var="ss_jxyb" value="${td01_xmxx.ss_jxf+td01_xmxx.ss_ybf}" scope="page"/>
		<c:set var="cs_jxyb" value="${td01_xmxx.cs_jxf+td01_xmxx.cs_ybf}" scope="page"/>
		<c:set var="sd_jxyb" value="${td01_xmxx.sd_jxf+td01_xmxx.sd_ybf}" scope="page"/>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">机械仪表费</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${ss_jxyb!=0}"><fmt:formatNumber value="${ss_jxyb}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${cs_jxyb!=0}"><fmt:formatNumber value="${cs_jxyb}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${sd_jxyb!=0}"><fmt:formatNumber value="${sd_jxyb}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${ss_jxyb!=0}"><fmt:formatNumber value="${ss_jxyb-sd_jxyb}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${ss_jxyb!=0}"><fmt:formatNumber value="${(ss_jxyb-sd_jxyb)/ss_jxyb*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">其它费</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_qtf!=0&&td01_xmxx.ss_qtf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_qtf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_qtf!=0&&td01_xmxx.cs_qtf!=null}"><fmt:formatNumber value="${td01_xmxx.cs_qtf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_qtf!=0&&td01_xmxx.sd_qtf!=null}"><fmt:formatNumber value="${td01_xmxx.sd_qtf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_qtf!=0&&td01_xmxx.ss_qtf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_qtf-td01_xmxx.sd_qtf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_qtf!=0&&td01_xmxx.ss_qtf!=null}"><fmt:formatNumber value="${(td01_xmxx.ss_qtf-td01_xmxx.sd_qtf)/td01_xmxx.ss_qtf*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<c:set var="ss_zfy" value="${td01_xmxx.ss_rgf+td01_xmxx.ss_clf+td01_xmxx.ss_jxf+td01_xmxx.ss_ybf+td01_xmxx.ss_qtf }" scope="page"/>
		<c:set var="cs_zfy" value="${td01_xmxx.cs_rgf+td01_xmxx.cs_clf+td01_xmxx.cs_jxf+td01_xmxx.cs_ybf+td01_xmxx.cs_qtf }" scope="page"/>
		<c:set var="sd_zfy" value="${td01_xmxx.sd_rgf+td01_xmxx.sd_clf+td01_xmxx.sd_jxf+td01_xmxx.sd_ybf+td01_xmxx.sd_qtf }" scope="page"/>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工总费用</p></td>						
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_sgf!=0}"><fmt:formatNumber value="${td01_xmxx.ss_sgf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_sgf!=0}"><fmt:formatNumber value="${td01_xmxx.cs_sgf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_sgf!=0}"><fmt:formatNumber value="${td01_xmxx.sd_sgf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_sgf!=0}"><fmt:formatNumber value="${td01_xmxx.ss_sgf-td01_xmxx.sd_sgf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_sgf!=0}"><fmt:formatNumber value="${(td01_xmxx.ss_sgf-td01_xmxx.sd_sgf)/td01_xmxx.ss_sgf*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理费</p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jlf!=0&&td01_xmxx.ss_jlf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_jlf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_jlf!=0&&td01_xmxx.cs_jlf!=null}"><fmt:formatNumber value="${td01_xmxx.cs_jlf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_jlf!=0&&td01_xmxx.sd_jlf!=null}"><fmt:formatNumber value="${td01_xmxx.sd_jlf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jlf!=0&&td01_xmxx.ss_jlf!=null}"><fmt:formatNumber value="${td01_xmxx.ss_jlf-td01_xmxx.sd_jlf}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_jlf!=0&&td01_xmxx.ss_jlf!=null}"><fmt:formatNumber value="${(td01_xmxx.ss_jlf-td01_xmxx.sd_jlf)/td01_xmxx.ss_jlf*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
		<c:set var="ss_jszfy" scope="page" value="${ss_zfy+td01_xmxx.ss_jlf}"/>
		<c:set var="cs_jszfy" scope="page" value="${cs_zfy+td01_xmxx.cs_jlf}"/>
		<c:set var="sd_jszfy" scope="page" value="${sd_zfy+td01_xmxx.sd_jlf}"/>
		<tr height="9mm">
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">结算总费用</p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_je!=0}"><fmt:formatNumber value="${td01_xmxx.ss_je}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.cs_je!=0}"><fmt:formatNumber value="${td01_xmxx.cs_je}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.sd_je!=0}"><fmt:formatNumber value="${td01_xmxx.sd_je}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_je!=0}"><fmt:formatNumber value="${td01_xmxx.ss_je-td01_xmxx.sd_je}" pattern="#,##0.00"/></c:if></p></td>
		 	<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><c:if test="${td01_xmxx.ss_je!=0}"><fmt:formatNumber value="${(td01_xmxx.ss_je-td01_xmxx.sd_je)/td01_xmxx.ss_je*100}" pattern="#,##0.00" />%</c:if></p></td>
		</tr>
	 </table>
</pages>
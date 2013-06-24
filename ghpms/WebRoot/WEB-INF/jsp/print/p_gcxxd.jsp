<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				工程信息单			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">需求部门</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.xqbm}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">需求提出人</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.cjr}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">提出日期</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${Td02_xmbgd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">工程名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">工程编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">所属区域</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ssdq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">要求完成时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.yqwcsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">工程类别</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gclb}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">工程专业</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.zydl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">专业细项</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.zyxx}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">需求说明</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcsm}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计单位</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">勘察反馈时限</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.kcfkzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计时限</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjsx}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工单位</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">进度填报周期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgjdtbzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">计划竣工时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理单位</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jldw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">日志填报周期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jlrjtbzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjpgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sgpfsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理派发时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.jlpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计人员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjry}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工管理员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgfzr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理工程师</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jlgcs}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目管理员</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.xmgly}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">实际开工时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjkgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">实际竣工时间</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black" colspan="6" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">预算费用（费用单位：元）</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">总投资</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_je}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">建安费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jaf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">仪表费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_ybf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">人工费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_rgf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">材料费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_clf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">其他费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_qtf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">技工工日</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设备费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_sbf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_sjf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">普工工日</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_pggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">机械费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jxf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理费</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jlf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">要求</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">反馈</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sjyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sjfk}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">施工</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sgyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sgfk}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.jlyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.jlfk}</p></td>
		</tr>
	</table>	
</pages>
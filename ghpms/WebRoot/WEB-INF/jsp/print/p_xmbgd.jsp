<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				项目变更单			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">发文部门</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">表单编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bdbh}</p></td>
		</tr>
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">起 草 人</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">电话</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">提出日期</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td02_xmbgd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">变更类别</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.gclb}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">变更种类</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgzl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目等级</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmdj}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设计金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_je}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">立项金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.lxje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">变更金额</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgje}</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border-left="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">原设计工作内容</p></td>
			<td colspan="3" border-left="1px solid black" border-right="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">变更后工作内容</p></td>
		</tr>
		<tr height="10mm">
		    <td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.ysjnr}</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bghnr}</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">原设计工作量</p></td>
			<td colspan="3" border="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">变更后工作量</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border="1px solid black" ><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.ysjgzl}</p></td>
			<td colspan="3" border="1px solid black" ><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bghgzl}</p></td>
		</tr>
		<tr height="20mm">
			<td border="1px solid black" border-top="0px solid black" width="23mm"><p font="Simsun" font-size="10pt" color="black" align="center">变更原因</p></td>
			<td border="1px solid black" border-top="0px solid black" colspan="5" width="142mm"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgyysm}</p></td>
		</tr>
	</table>	
</pages>
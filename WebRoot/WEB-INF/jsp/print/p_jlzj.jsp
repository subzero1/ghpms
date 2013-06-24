<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				监理总结			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">发文部门</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">表单编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.bdbh}</p></td>
		</tr>
		<tr height="10mm">
	    	<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">起 草 人</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">电话</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">提出日期</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td03_jlzj.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理单位</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jldw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">委托日期</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理工程师</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlgcs}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">联系电话</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlgcsdh}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">监理总结</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.jlzj}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">备　注</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.bz}</p></td>
		</tr>
	</table>	
</pages>
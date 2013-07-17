<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
 <%@ taglib uri="NetSkyTagLibs" prefix="netsky"%> 
 <jsp:include page="basicEdit.jsp"  flush="true"></jsp:include> 
 <input type="hidden" name="tableInfomation" value="noFatherTable:com.ghpms.dataObjects.form.Td01_glsj" /> 
<input type="hidden" name="Td01_glsj.ID" value="${param.project_id}"> <p> 
<label> 区域：</label> 
<input type="text"  name="Td01_glsj.QY" value="${td01_glsj.qy}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 视宽编号：</label> 
<input type="text"  name="Td01_glsj.SKBH" value="${td01_glsj.skbh}" style="width:256px;" />
 </p> 
 <p> 
<label> 歌华任务号：</label> 
<input type="text"  name="Td01_glsj.GHBH" value="${td01_glsj.ghbh}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 工程名称：</label> 
<input type="text"  name="Td01_glsj.GCMC" value="${td01_glsj.gcmc}" style="width:256px;" />
 </p> 
 <p> 
<label> 专项工程：</label> 
<netsky:htmlSelect name="Td01_glsj.ZXGC"  objectForOption="ZXGC" style="width:256px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td01_glsj.zxgc}"  htmlClass="td-select"/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 架空[揽长]：</label> 
<input type="text"  name="Td01_glsj.JK_LC" value="${td01_glsj.jk_lc}" style="width:256px;" />
 </p> 
 <p> 
<label> 架空[路由]：</label> 
<input type="text"  name="Td01_glsj.JK_LY" value="${td01_glsj.jk_ly}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 节点名称[地址]：</label> 
<input type="text"  name="Td01_glsj.JDMC" value="${td01_glsj.jdmc}" style="width:256px;" />
 </p> 
 <p> 
<label> 结算长度：</label> 
<input type="text"  name="Td01_glsj.JSCD" value="${td01_glsj.jscd}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 结算金额：</label> 
<input type="text"  name="Td01_glsj.JSJE" value="${td01_glsj.jsje}" style="width:256px;" />
 </p> 
 <p> 
<label> 勘察单位：</label> 
<input type="text"  name="Td01_glsj.KCDW" value="${td01_glsj.kcdw}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 勘察费估算：</label> 
<input type="text"  name="Td01_glsj.KCFGS" value="${td01_glsj.kcfgs}" style="width:256px;" />
 </p> 
 <p> 
<label> 报图日期：</label> 
<input type="text"  name="Td01_glsj.BTRQ" value="<fmt:formatDate value="${td01_glsj.btrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 标识：</label> 
<input type="text"  name="Td01_glsj.ID" value="${td01_glsj.id}" style="width:256px;" />
 </p> 
 <p> 
<label> 拆除[揽长]：</label> 
<input type="text"  name="Td01_glsj.CC_LC" value="${td01_glsj.cc_lc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 拆除[路由]：</label> 
<input type="text"  name="Td01_glsj.CC_LY" value="${td01_glsj.cc_ly}" style="width:256px;" />
 </p> 
 <p> 
<label> 道路长：</label> 
<input type="text"  name="Td01_glsj.DLC" value="${td01_glsj.dlc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 电子版：</label> 
<input type="text"  name="Td01_glsj.DZB" value="${td01_glsj.dzb}" style="width:256px;" />
 </p> 
 <p> 
<label> 杆公里：</label> 
<input type="text"  name="Td01_glsj.GGL" value="${td01_glsj.ggl}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 歌华时限要求：</label> 
<input type="text"  name="Td01_glsj.GHSXYQ" value="<fmt:formatDate value="${td01_glsj.ghsxyq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 工程编号：</label> 
<input type="text"  name="Td01_glsj.GCBH" value="${td01_glsj.gcbh}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 沟公里：</label> 
<input type="text"  name="Td01_glsj.GOUGL" value="${td01_glsj.gougl}" style="width:256px;" />
 </p> 
 <p> 
<label> 管道[揽长]：</label> 
<input type="text"  name="Td01_glsj.GD_LC" value="${td01_glsj.gd_lc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 管道[路由]：</label> 
<input type="text"  name="Td01_glsj.GD_LY" value="${td01_glsj.gd_ly}" style="width:256px;" />
 </p> 
 <p> 
<label> 管道需求长：</label> 
<input type="text"  name="Td01_glsj.GDXQC" value="${td01_glsj.gdxqc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 汇款日期：</label> 
<input type="text"  name="Td01_glsj.HKRQ" value="<fmt:formatDate value="${td01_glsj.hkrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 勘察类型：</label> 
<netsky:htmlSelect name="Td01_glsj.KCLX"  objectForOption="KCLX" style="width:256px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td01_glsj.kclx}"  htmlClass="td-select"/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 勘察任务书接受日期：</label> 
<input type="text"  name="Td01_glsj.KCRWSJSRQ" value="<fmt:formatDate value="${td01_glsj.kcrwsjsrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 揽长和：</label> 
<input type="text"  name="Td01_glsj.LCH" value="${td01_glsj.lch}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 路由和：</label> 
<input type="text"  name="Td01_glsj.LYH" value="${td01_glsj.lyh}" style="width:256px;" />
 </p> 
 <p> 
<label> 情况说明：</label> 
<textarea  name="Td01_glsj.QKSM" style="width:256px;height:70px;" >${td01_glsj.qksm} </textarea>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 情况说明上报日期：</label> 
<input type="text"  name="Td01_glsj.QKSMSBRQ" value="<fmt:formatDate value="${td01_glsj.qksmsbrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 任务来源：</label> 
<input type="text"  name="Td01_glsj.RWLY" value="${td01_glsj.rwly}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 任务来源2：</label> 
<input type="text"  name="Td01_glsj.RWLY_2" value="${td01_glsj.rwly_2}" style="width:256px;" />
 </p> 
 <p> 
<label> 任务完成情况：</label> 
<netsky:htmlSelect name="Td01_glsj.RWWCQK"  objectForOption="RWWCQK" style="width:256px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td01_glsj.rwwcqk}"  htmlClass="td-select"/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 视宽时限要求：</label> 
<input type="text"  name="Td01_glsj.SKSXYQ" value="<fmt:formatDate value="${td01_glsj.sksxyq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 视频协议编号：</label> 
<input type="text"  name="Td01_glsj.SPXYBH" value="${td01_glsj.spxybh}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 提交歌华日期：</label> 
<input type="text"  name="Td01_glsj.TJGHRQ" value="<fmt:formatDate value="${td01_glsj.tjghrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 项目负责人：</label> 
<input type="text"  name="Td01_glsj.XMFZR" value="${td01_glsj.xmfzr}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 备注：</label> 
<textarea  name="Td01_glsj.BZ" style="width:256px;height:70px;" >${td01_glsj.bz} </textarea>
 </p> 

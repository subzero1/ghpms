<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <jsp:include page="basicEdit.jsp"  flush="true"></jsp:include> 
 <input type="hidden" name="tableInfomation" value="noFatherTable:com.ghpms.dataObjects.form.Td01_glsj" /> 
<input type="hidden" name="Td01_glsj.ID" value="${param.project_id}"> <p> 
<label> 勘察任务书接受日期:</label> 
<input type="text"  name="Td01_glsj.KCRWSJSRQ" value="<fmt:formatDate value="${td01_glsj.kcrwsjsrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 管道(揽长):</label> 
<input type="text"  name="Td01_glsj.GD_LC" value="${td01_glsj.gd_lc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 管道(路由):</label> 
<input type="text"  name="Td01_glsj.GD_LY" value="${td01_glsj.gd_ly}" style="width:256px;" />
 </p> 
 <p> 
<label> 道路长:</label> 
<input type="text"  name="Td01_glsj.DLC" value="${td01_glsj.dlc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 管道需求长:</label> 
<input type="text"  name="Td01_glsj.GDXQC" value="${td01_glsj.gdxqc}" style="width:256px;" />
 </p> 
 <p> 
<label> 电子版:</label> 
<input type="text"  name="Td01_glsj.DZB" value="${td01_glsj.dzb}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 工程编号:</label> 
<input type="text"  name="Td01_glsj.GCBH" value="${td01_glsj.gcbh}" style="width:256px;" />
 </p> 
 <p> 
<label> 情况说明上报日期:</label> 
<input type="text"  name="Td01_glsj.QKSMSBRQ" value="<fmt:formatDate value="${td01_glsj.qksmsbrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 汇款日期:</label> 
<input type="text"  name="Td01_glsj.HKRQ" value="<fmt:formatDate value="${td01_glsj.hkrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
 <p> 
<label> 杆公里:</label> 
<input type="text"  name="Td01_glsj.GGL" value="${td01_glsj.ggl}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
<div class="formBar"> 
 <ul> 
<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li><li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li> 
 </ul> 
 </div>
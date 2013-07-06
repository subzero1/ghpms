<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
 <%@ taglib uri="NetSkyTagLibs" prefix="netsky"%> 
 <jsp:include page="basicEdit.jsp"  flush="true"></jsp:include> 
 <input type="hidden" name="tableInfomation" value="noFatherTable:com.ghpms.dataObjects.form.Td01_glsj" /> 
<input type="hidden" name="Td01_glsj.ID" value="${param.project_id}"> <p> 
<label> 歌华任务号:</label> 
<input type="text"  name="Td01_glsj.GHBH" value="${td01_glsj.ghbh}" style="width:256px;" />
 </p> 
 <p> 
<label> 勘察类型:</label> 
<netsky:htmlSelect name="Td01_glsj.KCLX"  objectForOption="objs" style="width:256px;" valueForOption="" showForOption="" extend="" extendPrefix="true" value="${td01_glsj.kclx}"  htmlClass="td-select"/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 专项工程:</label> 
<input type="text"  name="Td01_glsj.ZXGC" value="${td01_glsj.zxgc}" style="width:256px;" />
 </p> 
 <p> 
<label> 区域:</label> 
<input type="text"  name="Td01_glsj.QY" value="${td01_glsj.qy}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 节点名称(地址):</label> 
<input type="text"  name="Td01_glsj.JDMC" value="${td01_glsj.jdmc}" style="width:256px;" />
 </p> 
 <p> 
<label> 歌华时限要求:</label> 
<input type="text"  name="Td01_glsj.GHSXYQ" value="<fmt:formatDate value="${td01_glsj.ghsxyq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 提交歌华日期:</label> 
<input type="text"  name="Td01_glsj.TJGHRQ" value="<fmt:formatDate value="${td01_glsj.tjghrq}"  pattern="yyyy-MM-dd "/>" class="date" format="yyyy-MM-dd " yearstart="-50" yearend="50"style="width:256px;" />
 </p> 
<div class="formBar"> 
 <ul> 
<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li><li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li> 
 </ul> 
 </div>
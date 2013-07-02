<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <jsp:include page="basicEdit.jsp"  flush="true"></jsp:include> 
 <input type="hidden" name="tableInfomation" value="noFatherTable:com.ghpms.dataObjects.form.Td01_glsj" /><input type="hidden" name="Td01_glsj.ID" value="${param.project_id}"> <p> 
<label> 视宽时限要求:</label> 
<input type="text"  name="Td01_glsj.SKSXYQ" value="${td01_glsj.sksxyq}" style="width:256px;" />
 </p> 
 <p> 
<label> 报图日期:</label> 
<input type="text"  name="Td01_glsj.BTRQ" value="${td01_glsj.btrq}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 项目负责人:</label> 
<input type="text"  name="Td01_glsj.XMFZR" value="${td01_glsj.xmfzr}" style="width:256px;" />
 </p> 
 <p> 
<label> 拆除(揽长):</label> 
<input type="text"  name="Td01_glsj.CC_LC" value="${td01_glsj.cc_lc}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 管道需求长:</label> 
<input type="text"  name="Td01_glsj.GDXQC" value="${td01_glsj.gdxqc}" style="width:256px;" />
 </p> 
 <p> 
<label> 备注:</label> 
<input type="text"  name="Td01_glsj.BZ" value="${td01_glsj.bz}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 提交歌华日期:</label> 
<input type="text"  name="Td01_glsj.TJGHRQ" value="${td01_glsj.tjghrq}" style="width:256px;" />
 </p> 
 <p> 
<label> 视频协议编号:</label> 
<input type="text"  name="Td01_glsj.SPXYBH" value="${td01_glsj.spxybh}" style="width:256px;" />
 </p> 
<div style="height:0px;"></div> 
<div class="formBar"> 
 <ul> 
<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li><li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li> 
 </ul> 
 </div>
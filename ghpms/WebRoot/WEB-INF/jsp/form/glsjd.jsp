<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <jsp:include page="basicForm.jsp"  flush="true"></jsp:include> 
  <p> 
<label> 视宽编号:</label> 
<input type="text"  value="${td01_glsj.skbh}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 歌华任务号:</label> 
<input type="text"  value="${td01_glsj.ghrwh}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 勘察类型:</label> 
<input type="text"  value="${td01_glsj.kclx}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 专项工程:</label> 
<input type="text"  value="${td01_glsj.zxgc}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 区域:</label> 
<input type="text"  value="${td01_glsj.qy}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 节点名称(地址):</label> 
<input type="text"  value="${td01_glsj.jdmc}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 任务完成情况:</label> 
<input type="text"  value="${td01_glsj.rwwcqk}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 勘察任务书接受日期:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.kcrwsjsrq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 歌华时限要求:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.ghsxyq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 视宽时限要求:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.sksxyq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 报图日期:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.btrq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 勘察单位:</label> 
<input type="text"  value="${td01_glsj.kcdw}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 项目负责人:</label> 
<input type="text"  value="${td01_glsj.xmfzr}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 任务来源:</label> 
<input type="text"  value="${td01_glsj.rwly}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 架空(揽长):</label> 
<input type="text"  value="${td01_glsj.jk_lc}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 架空(路由):</label> 
<input type="text"  value="${td01_glsj.jk_ly}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 管道(揽长):</label> 
<input type="text"  value="${td01_glsj.gd_lc}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 管道(路由):</label> 
<input type="text"  value="${td01_glsj.gd_ly}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 拆除(揽长):</label> 
<input type="text"  value="${td01_glsj.cc_lc}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 拆除(路由):</label> 
<input type="text"  value="${td01_glsj.cc_ly}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 道路长:</label> 
<input type="text"  value="${td01_glsj.dlc}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 管道需求长:</label> 
<input type="text"  value="${td01_glsj.gdxqc}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 揽长和:</label> 
<input type="text"  value="${td01_glsj.lch}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 路由和:</label> 
<input type="text"  value="${td01_glsj.lyh}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 电子版:</label> 
<input type="text"  value="${td01_glsj.dzb}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 工程编号:</label> 
<input type="text"  value="${td01_glsj.gcbh}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 情况说明:</label> 
<input type="text"  value="${td01_glsj.qksm}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 情况说明上报日期:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.qksmsbrq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p>               value="<fmt:formatDate value="${curDate}"            pattern="yyyy-MM-dd HH:mm"/>"
<div style="height:0px;"></div> 
 <p> 
<label> 备注:</label> 
<input type="text"  value="${td01_glsj.bz}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 提交歌华日期:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.tjghrq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 结算长度:</label> 
<input type="text"  value="${td01_glsj.jscd}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 结算金额:</label> 
<input type="text"  value="${td01_glsj.jsje}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 汇款日期:</label> 
<input type="text"  value="<fmt:formatDate value="${td01_glsj.hkrq}" pattern="yyyy-MM-dd HH:mm"/>" style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 杆公里:</label> 
<input type="text"  value="${td01_glsj.ggl}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 沟公里:</label> 
<input type="text"  value="${td01_glsj.gougl}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 勘察费估算:</label> 
<input type="text"  value="${td01_glsj.kcfgs}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 
 <p> 
<label> 视频协议编号:</label> 
<input type="text"  value="${td01_glsj.spxybh}"style="width:256px;" readonly/>
 </p> 
 <p> 
<label> 任务来源2:</label> 
<input type="text"  value="${td01_glsj.rwly_2}"style="width:256px;" readonly/>
 </p> 
<div style="height:0px;"></div> 

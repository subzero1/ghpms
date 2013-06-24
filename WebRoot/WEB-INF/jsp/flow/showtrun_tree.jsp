<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/**
 * @author Chiang 2010-02-10
 * 流程显示
 * @param List<String> node_list
 * @param List<String> line_list
 * @param List<Ta06_module> moduleList
 * @param List<Tb01_flow> flowList
 * @param String title
 */
%>
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/show_tree.css" />
<script language="javascript">

function do_click(tb02_id ,tb12_id,project_id,module_id,doc_id,node_status){

	var vproject_id = $("#_sharestore INPUT[name='project_id']",navTab.getCurrentPanel()).val();
	var vmodule_id = $("#_sharestore INPUT[name='module_id']",navTab.getCurrentPanel()).val();
	var vdoc_id = $("#_sharestore INPUT[name='doc_id']",navTab.getCurrentPanel()).val();
	var vopernode_id = $("#_sharestore INPUT[name='opernode_id']",navTab.getCurrentPanel()).val();
	var	url = $("#_sharestore INPUT[name='formAction']",navTab.getCurrentPanel()).val();

 	if(node_status==1){
		alertMsg.confirm("确定回退到该节点么？", {
	        okCall: function(){
	        	var pars = 'opernode_id='+vopernode_id+'&doc_id='+vdoc_id+'&project_id='+vproject_id+'&module_id='+vmodule_id+'&rollto_nodes='+tb02_id+'&random='+Math.random();//参数
	        	
	             $.ajax({
			        dataType : 'text'
			        ,context: document.body
			        ,type : 'POST'
			        ,url : url + "?" +pars
			        ,data : {aaa: 'bb',p_var2: 'a'}
			        ,success : function(data){
						//关闭
						alertMsg.correct('回退成功！');
						$.pdialog.closeCurrent();
			        	docClose();
			        }
			        ,error:function(){alertMsg.info('错误！请重试'); }
			        ,timeout:function(){alertMsg.info('处理超时！请重试'); }
			    });	
			    
	        }
		});
	}
	
}

</script>
<div class="page">
	<div class="panelBar">
		<form name="form1" action="showTree.do" method="post">
			<input type="hidden" name="rollto_nodes" id="rollto_nodes" value=""/>
			<ul class="toolBar">
			<c:if test="${flowList != null}">
				<li>
				    <select name="flow_id" onchange="javascript:document.form1.action='turnBackTree.do';document.form1.submit();">
				    	<c:forEach var="obj" items="${flowList}">
				    		<option value="${obj[0]}"   <c:if test="${flow_id == obj[0]}">selected</c:if>>${obj[1]}</option>
				    	</c:forEach>
				    	<input type="hidden" name="nodes" id="nodes" value="${nodes}"/>
				    	<input type="hidden" name="fromType" id="fromType" value="onChange"/>
				    </select>
				</li>
			</c:if>
				<li style="color:red" >提示：点击选择非灰色节点回退</li>
			</ul>
		</form>
	</div>
	<div class="pageContent" layoutH="28">
		<div id="tree">
			<c:forEach var="obj" items="${line_list}">
				<div style='width:1px;height:1px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;background-color:${obj.color };'>
				</div>
			</c:forEach>
			<c:forEach var="obj" items="${word_list}">
				<div style='width:10px;height:10px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }'>
					${obj.name }
				</div>
			</c:forEach>
			<c:forEach var="obj" items="${node_list}">
				<div style='width:${obj.width }px;height:${obj.height }px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }' onclick='javascript:do_click(${obj.param })'>
					${obj.name }
				</div>
			</c:forEach>
		</div>
	</div>
</div>

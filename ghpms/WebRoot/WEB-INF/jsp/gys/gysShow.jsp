<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	
	$("#gys_ui").loadUrl("form/gysShow.do?project_id=${param.project_id}&bgmc=Te03_gcgys_b1");
	$("#select_table").change(function(){
	    var bgmc = $(this).val();
		$("#gys_ui").loadUrl("form/gysShow.do?project_id=${param.project_id}&bgmc="+bgmc);
	})
</script>
<style>
	.black-title{text-align:left;}
	.black-title th{text-align:right;height:18px;}
	.black-title td{height:18px;}
	
	.black-box {border:solid 1px #000;padding:0px;margin:0px;}
	.black-box th{border:solid 1px #000;text-align:center;font-weight:normal;line-height:140%;}
	.black-box td{border:solid 1px #000;height:25px;padding:0 5px;text-align:left;}

</style>
<div class="page">
	<div class="pageContent">
			<div class="pageFormContent" layoutH="56">
				<p>
				  	<b>项目名称  <c:out value="${xmxx.xmmc }" default="${gcxx.gcmc }"/></b>
				  
					<select id="select_table">
						<option value="Te03_gcgys_b1" >概预算总表（表一）</option>
						<option value="Te03_gcgys_b2" >建筑安装工程费用概预算表（表二）</option>
						<option value="Te03_gcgys_b3j" >建筑安装工程量概预算表（表三）甲</option>
						<option value="Te03_gcgys_b3y" >建筑安装工程施工机械使用费概预算表（表三）乙</option>
						<option value="Te03_gcgys_b3b" >建筑安装工程施工机械使用费概预算表（表三）丙</option>
						<option value="Te03_gcgys_b4j&bgbh=XA" >国内需要安装设备表（表四）甲</option>
						<option value="Te03_gcgys_b4j&bgbh=ZC" >国内主要材料表（表四）甲</option>
						<option value="Te03_gcgys_b5j" >工程建设其他费概预算表（表五）甲</option>
					</select>
				 </p>
				<div id="gys_ui">
					
				</div>  
			</div>
	</div>
</div>

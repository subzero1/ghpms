<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
/**
 * @author liuxu 2010-07-14
 * 疑难解答列表
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>疑难解答列表</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK" />
	<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
	<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="../css/main-msie.css" /><![endif]-->
	<link rel="stylesheet" media="print" type="text/css" href="css/print.css" />
	<script type="text/javascript" src="js/prototype.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/appCommon.js"></script>
	<script type="text/javascript">
     function del_check(){
		var isDel=false;
		var objs=document.getElementsByName('del_id');
		for(var i=0;i<objs.length;i++) { 
			if(objs[i].checked==true){ 
				isDel=true; 
				break; 
			} 
		}
		if(isDel==true){
			var msg = showMsgBox('<font color=red><b>〖 提醒 〗</b></font> <br/>&nbsp;确认删除所选项？','warning',null,2);
			Event.observe(msg.buttOK, 'click', function(){
				document.form1.action="QAList.do?del=yes&role_id=${role_id}";
				document.form1.submit();
			});
		}else{
		    var msg = showMsgBox('<font color=red><b>〖 错误 〗</b></font> <br/>&nbsp;请选择要删除的项！','error',null,1);
			Event.observe(msg.buttOK, 'click', function(){
			});
		}
	}
	
	function openAnswerBox(a){	
		var jxs = document.getElementsByName("answer_jx");
		alert(jxs);
		for(var j=0; j<jxs.length; j++){
			var trs2 = jxs[i];}
				
		var t=${pageRowSize }-1;
		for(var i=1;i<t;i++){
		var aqi= "answer_"+ i;
		var aql= document.getElementById(aqi);
			if(i==a && aql.style.display != null){aql.style.display= "";}else{aql.style.display= "none";}
		}
	}
	</script>
</head>
<body id="main-body" onload="javascript:initTable();"> 
	<form name="form1" id="form1" action="" method="post">
	<input type="hidden" name="pageRowSize" value="${pageRowSize }"/>	
	<!-- Content -->
	<div id="main-in">
	 <c:if test="${role_del=='yes'}">
		<div class="in-l in-m"><a href="javascript:openCustomWin('QA.do',550,310,'no');">新 建</a></div><div class="in-r"></div>
		<div class="in-l in-m"><a href="javascript:del_check();" >删 除</a></div><div class="in-r"></div>
	</c:if>
		<div class="in-l in-m" onclick="javascript:doClose();" >关 闭</div><div class="in-r"></div>
  	</div>
	<div id="listpage" class="box"> 
		<div class="title01">
			<h1 class="f-left">问与答列表</h1>
			<h2 class="f-right">
			<div id="search-area" class="f-right">
				问 题：
				<input type="text" name="ques" id="ques" value="${ques }" size="30" class="td-input-nowidth"></input>
	           	<input type="button" value="搜 索" onclick="javascript:document.form1.action='QAList.do?role_id=${role_id}';document.form1.submit();" class="red-search"/>
				<input type="button" value="刷新列表" onclick="javascript:window.location.reload();" class="button-config"/>
			</div>
			</h2>
	    </div>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;" id="list">
			<caption>
			   <b class="ptop"><b class="p1"></b><b class="p2"></b><b class="p3"></b><b class="p4"></b></b>						
			</caption>
			<tr>
			    <th width="20"></th>
			    <th width="40" onclick="javascript:backSortForSubmit('ord');" style="cursor:hand">序号</th>
				<th width="500" onclick="javascript:backSortForSubmit('question');" style="cursor:hand">问题</th>
				<th width="120" onclick="javascript:backSortForSubmit('cjrq');" style="cursor:hand">创建日期</th>
				<th width="40" onclick="javascript:backSortForSubmit('dj');" style="cursor:hand">点击数</th>
				<th></th>
			</tr>
			<c:forEach begin="0" var="i" end="${pageRowSize-1}">
				<tr>
				    <td width="20" style="text-align:center"><c:if  test="${list_qa[i] != null}"><input type="checkbox" name="del_id" value="${list_qa[i].id}" /></c:if></td>
				    <td class="t-center">
				    	${list_qa[i].ord}
				    </td>
				    <td>
				    	<c:if  test="${list_qa[i] != null}"> 
				    	<a title="${list_qa[i].answer}" href="javascript:openCustomWin('qa_answer.do?aq_id=${list_qa[i].id}',500,310,'no');">${list_qa[i].question_jx}</a>
				    	<div style="display:none;" class="qa-answer" name="answer_jx" id="answer_${list_qa[i].id}"><h2>${list_qa[i].question_jx}</h2><div class="answer-jx">${list_qa[i].answer_jx} </div></div>
				    	</c:if>
				    </td>
				    <td class="t-center"><fmt:formatDate value="${list_qa[i].cjrq }" pattern="yyyy-MM-dd HH:mm"/></td>
				    <td class="t-center">${list_qa[i].dj }</td>
				    <td>
					    <c:if  test="${list_qa[i] != null&&role_del=='yes'}"><a href="javascript:openCustomWin('QA.do?aq_id=${list_qa[i].id}',500,310,'no');" style="cursor:pointer;<c:if test="${empty list_qa[i].answer}">color:red;</c:if>" title="${list_qa[i].question}">[编辑]</a>
					    </c:if>
				    </td>
				</tr>
			</c:forEach>
		</table>
		</div>
<!-- /Content -->
	</form>
	<form name="form2" id="form2" action="" method="post">
		<div id="listpage" class="box"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;width:${tablewidth }px;" id="list">
				<caption>						
				</caption>
				<tr>
					<th align="right">
						共${totalRows}条记录&nbsp;&nbsp;&nbsp;&nbsp;第${page}/${totalPages}页
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:lastPage();">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:nextPage();">下一页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="goPage" name="goPage" size="4" value=""/>&nbsp;<a href="javascript:go();">go</a>
						<input type="hidden" id="page" name="page" value="${page }"/>						
						<input type="hidden" id="totalPages" name="totalPages" value="${totalPages }"/>
						<input type="hidden" name="pageRowSize" value="${pageRowSize }"/>
						<input type="hidden" id="sortField" name="sortField" value="${param.sortField}"/>
						<input type="hidden" id="sortType" name="sortType" value="${param.sortType}"/>			
					</th>
				</tr>
			</table>
		</div>
	</form>	</body>
</html>		
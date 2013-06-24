<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script language="javascript">

$(function(){
	//计算待办列表默认行数 
	var temp_h = navTab._panelBox.height() - 310;
	var numPerPage = parseInt((temp_h-40)/22,10);
	if(numPerPage <= 0){
		numPerPage = 5;
	}
	var url = "docListUI.do?workState=1&numPerPage=" + numPerPage;
	$("#needWorkList_ui").attr("loadfile",url);
	$("#needWorkList_ui").loadUrl(url);
});

function openOnLineList(){
	navTab.openTab('zxrs', 'search/OnLineList.do', {title:'在线人员列表'});
}
</script>

<div id="page">
   	<!-- Catalog -->
        <div id="col-l">
       	<div class="title01">
           	<div id="jnkc"></div><h1>系统桌面</h1>
               <script>setInterval("$('#jnkc').html('当前时间：' + new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay()) + '，在线人数：<a href='+'javascript:openOnLineList();'+'><font>${zxrs}</font></a>人');",1000);</script>
        </div>
           <div id="win-boxdes">
         	<!-- 个人信息  -->              
			<div id="win-boxl">
					<div id="personal">
						<img id="personal_head" <c:choose>
					<c:when test="${fj.id!= null}">
						src="download.do?slave_id=${fj.id}&radom=<%=new Random().nextInt()%>"
					</c:when>
					<c:otherwise>
						src="Images/head.jpg" 
					</c:otherwise>
					</c:choose> title="${user.name}" alt="${user.login_id}"/>
	 					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="personal_info">
						<tr>
							<th width="60">工号</th>
							<td>${user.login_id}</td>
							<td>
 								<div id="see">
 								<a href="search/user_tail.do?user_id=${user.id}" target="dialog" width="680" height="350" title="权限查看"><img src="Images/station.png"/></a>
	 					 		<a href="userInfo.do" target="dialog" width="520" height="320" title="用户个人信息设置"><img src="Images/setting.png"/></a>
		 					 	<a href="personalHead.do" target="dialog" width="600" height="380" title="头像修改"><img src="Images/headedit.png"/></a>
		 					 	<!-- <a href="setGetPassowrdInfo.do" target="dialog" width="300" height="200" title="找回密码"><img src="Images/icon11.png"/></a> -->														
								</div>									
							</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td colspan="2">${user.name}</td>
						</tr>
						<tr>
							<th>部门</th>
							<td colspan="2">${user.dept_name}</td>
						</tr>
						<tr>
							<th>地区</th>
							<td colspan="2">${user.area_name}</td>
						</tr>
					</table>
					</div>
			</div>             
            <!-- /个人信息 -->
		  		  
			<!-- 超时提醒 -->              
		  	<div id="win-boxr">
		  		<div ><b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b></div>
		  		<div class="contentc" style="height:225px;">
					<table border="0" cellspacing="6" cellpadding="0" style="border-collapse:collapse;" id="remind">
						<tr>
							<c:choose>
								<c:when test="${not empty rolesMap['110101']}">
							    	<td class="gxwd" onclick="javascript:navTab.openTab('gxwdList', 'other/wdList.do?limit=1', {title:'文档管理'});"><br/>共享文档</td>
							    </c:when>
							    <c:otherwise>
							    	<td class="gxwd" onclick="javascript:navTab.openTab('gxwdList', 'other/wdList.do?limit=0', {title:'文档查阅'});"><br/>共享文档</td>
							    </c:otherwise>
						    </c:choose>
							<td class="zxTws" onclick="javascript:navTab.openTab('onlineList', 'OnLineList.do?wtlx=15', {title:'在线提问'});"><font>${csMap.zxWdfs}</font><br/>在线提问(${csMap.zxTws})</td>
							<td class="jjcs" onclick="javascript:navTab.openTab('remind', 'search/remindFlowList.do?remindType=jcs&user_id=${user.id }', {title:'超时提醒'});"><font> ${jcss}</font><br/>即将超时</td>
							<td class="yjcs" onclick="javascript:navTab.openTab('remind', 'search/remindFlowList.do?remindType=cs&user_id=${user.id }', {title:'超时提醒'});"><font> ${css}</font><br/>已经超时</td>
							<td></td>
						</tr>
						<tr>
							<td class="dbWds" onclick="javascript:navTab.openTab('workList', 'workList.do?workState=1', {title:'待办文档'});"><font>${csMap.dbWds}</font><br/>待办文档</td>
							<td class="zbWds" onclick="javascript:navTab.openTab('workList', 'workList.do?workState=2', {title:'在办文档'});"><font>${csMap.zbWds}</font><br/>在办文档</td>
							<td class="dfWds" onclick="javascript:navTab.openTab('workList', 'workList.do?workState=3', {title:'待复文档'});"><font>${csMap.dfWds}</font><br/>待复文档</td>
							<td class="hfWds" onclick="javascript:navTab.openTab('workList', 'workList.do?workState=4', {title:'回复文档'});"><font>${csMap.hfWds}</font><br/>回复文档</td>
							<td></td>
						</tr>
						<tr>
							<th colspan="4">最后登录时间： <fmt:formatDate value="${csMap.zhdl}" pattern="yyyy-MM-dd HH:mm"/> 共 <a href="javascript:navTab.openTab('dljl', 'search/LoginLog.do', {title:'登录记录'});"><font color="#FF0000">${csMap.dlcs}</font></a> 次  <span style="cursor:hand" title="文档处理情况" onclick ="javascript:navTab.openTab('wdclqk', 'search/FormOperDetail.do?type=per', {title:'文档处理情况'});">	</span> </th>
						</tr>
						<c:if test="${not empty rolesMap['40107']}">	
							<tr>
								<th colspan="4"><a href="search/queryForBoss.do" target="navTab" rel="queryForBoss"><b><font style="color:blue">系统使用情况查询</font></b></a>  </th>
							</tr>		
						</c:if>				
					</table>
				</div>
		   		<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>									
			</div>
		  	<!-- /超时提醒 -->
		</div>
		
        <!-- /box -->             
	</div>
       <!-- /col-l -->
            
       <!-- col-r -->
       <div id="col-r">
		<div class="title01">
              <h3><a href="javascript:navTab.openTab('OnLineList','OnLineList.do?wtlx=601',{title:'系统公告'});">系统公告</a></h3>
           </div>
        <b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
		<div class="win-boxp" style="height:104px;">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"  style="border-collapse:collapse;" id="reporttable">
				<c:forEach var="i" begin="0" end="3">
					<c:if test="${online_list[i] != null}">
						<tr>
							<td style="width:25px;" class="t-right"><img src='Images/report.png' title="系统公告"/></td>
							<td style="width:45px;" class="t-center">
								<c:if test="${online_list[i].dif >= 0}">
									[<fmt:formatDate value="${online_list[i].aq_date }" pattern="MM/dd"/>]
								</c:if>
								<c:if test="${online_list[i].dif < 0}">
									[<fmt:formatDate value="${online_list[i].aq_date }" pattern="MM/dd"/>]
								</c:if>
							</td>
							<td><a href="OnLineanswer.do?aq_id=${online_list[i].id}" title="系统公告" target="dialog" width="750" height="500">${online_list[i].title}</a></td>
						</tr>
					</c:if>	
					<c:if test="${online_list[i] == null}">
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>	
					</c:if>							
				</c:forEach>
			</table>
		</div>
		<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b><b class="r0"></b><b class="r00"></b></b>
          <div class="title01">
				<span style="float:right;"><input class="red-message" type="button" onclick="javascript:navTab.openTab('messageList','MessageList.do?messageState=1',{title:'内部邮件'});" value="${wcldxx}条未读"/></span>
               <h3><a href="javascript:navTab.openTab('messageList','MessageList.do?messageState=1',{title:'内部邮件'});">内部邮件</a></h3>
           </div>
		<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
		<div class="win-boxp" style="height:93px;">
			<table width="97%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" id="messagetable">
				<c:forEach  var="i" begin="0" end="3">
					<c:if test="${message_list[i] != null}">
						<tr>
							<td style="width:25px;" class="t-right">
								<c:if test="${message_list[i].read_flag==0}"><img src='Images/message/email.png' title="未读邮件"/></c:if>
								<c:if test="${message_list[i].read_flag=='1'&&message_list[i].repeat_flag==1}"><img src='Images/message/forwarded.gif' title="需要回复"/></c:if>
								<c:if test="${message_list[i].read_flag=='1'&&message_list[i].repeat_flag==2}"><img src='Images/message/replied.gif' title="已经回复"/></c:if>
								<c:if test="${message_list[i].read_flag==1&&message_list[i].repeat_flag==0}"><img src='Images/message/email_open.png' title="已读邮件"/></c:if>
							</td>
							<td style="width:45px;" class="t-center">
									<c:if test="${message_list[i].dif>=0}">[<fmt:formatDate value="${message_list[i].send_date }" pattern="MM/dd"/>]</c:if>
									<c:if test="${message_list[i].dif<0}">[<fmt:formatDate value="${message_list[i].send_date }" pattern="MM/dd"/>]</c:if>
							</td>
							<td><a 	href="MessageRead.do?message_id=${message_list[i].id}&messageState=1" target="dialog" rel="messageRead" title="查看内部邮件" width="600" height="370">${message_list[i].title}</a></td>
						</tr>	
					</c:if>
					<c:if test="${message_list[i] == null}">
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>	
					</c:if>						
				</c:forEach>					
			</table>
		</div>	
		<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b><b class="r0"></b><b class="r00"></b></b>           		  
	</div>
</div>
<div id="needWorkList_ui">
	
</div>     

	
	

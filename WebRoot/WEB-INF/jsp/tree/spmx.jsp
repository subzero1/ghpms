<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page">
	<div class="pageContent">
	    <table class="table no" nowrapTD="false" width="100%" layouth="38">
			<thead>
				<tr>
			   		<th width="110">表单名称</th>
			   		<th width="70">审批人</th>
			   		<th width="120">审批时间</th>
			   		<th width="70">审批结果</th>
			   		<th>审批意见</th>
			   	</tr>
			</thead>
			<tbody>		
			   	<c:forEach var="obj" items="${list}">
					<tr>
						<c:if test="${obj.show_flag == true}">
							<td rowspan="${obj.rowspan }">${obj.module_name }</td>
						</c:if>
			    		<td>${obj.user }</td>
			    		<td>${obj.oper_time }</td>
			    		<td>${obj.check_result }</td>
			    		<td>${obj.check_idea }</td>
			    	</tr>
				</c:forEach>
			</tbody>
	    </table>
    </div>
</div>

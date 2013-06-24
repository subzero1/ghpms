<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'test.jsp' starting page</title>
		<script type="text/javascript" src="js/jquery/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="js/src/netsky.cascade.js"></script>
		<script type="text/javascript">
    	$(function(){
    		$("#parentSelect").cascade({
				childSelect:$("#childSelect"),
				tableName:'Ta03_user',
				conditionColumn:'dept_id',
				valueForOption:'id',
				key:'id',
				orderBy:'name',
				extendColumns:{
								login_id:'login_id',
								passwd:'passwd',
								title:'name'
				},
				showForOption:{
								pattern:'[name]\[[login_id]\]',
								name:'name',
								login_id:'login_id'
				}
			});	
    	});
    </script>
	</head>

	<body>
	多表：<select id="parentSelect">
			<option></option>
			<option value="4" optionforextend="{'id':'4' }">
				工程建设中心
			</option>
		</select>
		<select id="childSelect">
		</select>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="">
	var str = "|";
	var re_str = "/\\"+str+"/g";
	var reg = new RegExp((re_str));
	p_input.text(op.options.replace(reg,"\r"));
</script>
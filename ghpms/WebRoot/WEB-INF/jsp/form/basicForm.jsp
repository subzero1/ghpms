<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
var $input=$(":input");
 	$input.each(function(){
 	    var $p=$(this).closest("p");
 		if($(this).val()==''){
 			$p.css("color","black");
 		}else{
 			$p.css("color","#3498e4");
 		}
 	});
});
</script>

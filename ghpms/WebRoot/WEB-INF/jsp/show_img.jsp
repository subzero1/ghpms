<%@ page language="java"  pageEncoding="utf-8"%>
<body onload="javascript:abc();">
<img id="_img" name="_img" src="../download.do?slave_id=${slave_id}"/>
</body>
<script type="">
	function abc(){	
		var w = document.getElementById('_img').width;
		if(w > 940)
			document.getElementById('_img').width=940;
	}
</script>
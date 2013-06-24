<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
function uploadSlave(butt){
	var up_form = $(butt).closest("form");
	if($("#the_file",up_form).val()==""){
		alertMsg.info("请选择文件");
	}else{
		up_form.submit();
	}
}
</script>

<div class="page">
	<div class="pageContent">
		<form enctype="multipart/form-data" action="save.do" method="post" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<input type="hidden" name="slaveTable" value="com.netsky.base.dataObjects.Te01_slave"/>
			<input type="hidden" name="slaveType" value="ftp"/>
			<input type="hidden" name="_navTabId" value="wdList"/>
			<input type="hidden" name="_forwardUrl" value="other/wdList.do?limit=1&te10_id=${param.doc_id  }"/>
			<input type="hidden" name="_message" value="文件上传" />
							
			<div class="pageFormContent" layoutH="56">
				<div id="fileQueue" class="fileQueue"></div>
				<p>
					<label>文件选择：</label>
					<input type="file" name="the_file" id="the_file" size="23"/> 
					<input type="hidden" name="Te01_slave.ID" value="${param.slave_id}"/>
					<input type="hidden" name="Te01_slave.PROJECT_ID" value="${param.project_id}"/>
					<input type="hidden" name="Te01_slave.DOC_ID" value="${param.doc_id }"/>
					<input type="hidden" name="Te01_slave.MODULE_ID" value="${param.module_id}"/>
					<input type="hidden" name="Te01_slave.USER_ID" value="${user.id}"/>
					<input type="hidden" name="Te01_slave.USER_NAME" value="${user.name}"/>
					<input type="hidden" name="Te01_slave.FTP_DATE" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<input type="hidden" name="Te01_slave.FILE_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.EXT_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.FTP_URL" value="1"/>
					<input type="hidden" name="Te01_slave.SLAVE_TYPE" value="文档" />
					
				</p>
				<p>
					<label>关键字：</label>
					<textarea class="td-textarea" style="width:240px;height:60px;" type="text" name="Te01_slave.REMARK">${slave_type}</textarea>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:uploadSlave(this);">上传文档</button>
				</div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
	</div>
</div>	
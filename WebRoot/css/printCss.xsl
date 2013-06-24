<?xml version="1.0" encoding="GBK"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='GBK' indent='yes'/>


<xsl:template match="/">
<html>
<head>
<title>表单打印</title>
<style type="text/css">
    .PageNext{page-break-after: always;}<!--控制分页-->
</style>
<script language="javascript">
	function printpage(){
		if (window.print){
			if(confirm('本页将被自动打印. \n\n  确定打印吗?')){
				window.print();
			}else{
				//window.close();
			}
		}else{
			alert("请先安装打印机，操作失败!");
		}
	}
</script>
</head>
<body onload="javaScript:printpage();">
<!-- 
处理目录页面
当cover标签中属性show="true"时显示目录				
 -->
<xsl:for-each select="/page/cover">
	<xsl:if test="@show">
		<xsl:choose>
			<xsl:when test="@show='true'">
				<!-- 循环处理目录页 -->
				<xsl:for-each select="/page/cover/pages">
					<xsl:for-each select="table">
						<table align="center" border="0" cellspacing="0" cellpadding="0">
							<xsl:attribute name="style">
								<xsl:if test="@width">
										border-collapse: collapse;width:<xsl:value-of select="@width"/>;
								</xsl:if>
							</xsl:attribute>
							<xsl:for-each select="tr">
								<tr>
									<xsl:attribute name="style">
										<xsl:if test="@height">
											height:<xsl:value-of select="@height"/>;
										</xsl:if>
									</xsl:attribute>
									<xsl:for-each select="td">
										<td>
											<xsl:if test="@width">
												<xsl:attribute name="width">
													<xsl:value-of select="@width"/>
												</xsl:attribute>
											</xsl:if>
											<xsl:attribute name="style">
												<xsl:if test="@border">
														border:<xsl:value-of select="@border"/>;
												</xsl:if>
												<xsl:if test="@border-bottom">
														border-bottom:<xsl:value-of select="@border-bottom"/>;
												</xsl:if>
												<xsl:if test="@border-top">
														border-top:<xsl:value-of select="@border-top"/>;
												</xsl:if>
												<xsl:if test="@border-left">
														border-left:<xsl:value-of select="@border-left"/>;
												</xsl:if>
												<xsl:if test="@border-right">
														border-right:<xsl:value-of select="@border-right"/>;																
												</xsl:if>
											</xsl:attribute>
											<xsl:if test="@colspan">
												<xsl:attribute name="colspan">
													<xsl:value-of select="@colspan" />
												</xsl:attribute>
											</xsl:if>
											<xsl:if test="@rowspan">
												<xsl:attribute name="rowspan">
													<xsl:value-of select="@rowspan" />
												</xsl:attribute>
											</xsl:if>
											<xsl:if test="@align">
												<xsl:attribute name="align">
													<xsl:value-of select="@align"/>
												</xsl:attribute>
											</xsl:if>
											<xsl:if test="@valign">
												<xsl:attribute name="valign">
													<xsl:value-of select="@valign"/>
												</xsl:attribute>
											</xsl:if>
											<xsl:for-each select="p">
												<p>
													<xsl:attribute name="style">
														<xsl:if test="@font">
															font-family:
															<xsl:choose>
																<xsl:when test="@font='Simsun'">宋体</xsl:when>
																<xsl:when test="@font='Simhei'">黑体</xsl:when>
															</xsl:choose>;
														</xsl:if>
														<xsl:if test="@font-size">
																font-size:<xsl:value-of select="@font-size"/>;
														</xsl:if>
														<xsl:if test="@color">
																color:<xsl:value-of select="@color"/>;																		
														</xsl:if>
														<xsl:if test="@align">
																text-align:<xsl:value-of select="@align"/>;
														</xsl:if>
													</xsl:attribute>
													<xsl:value-of select="."/>
												</p>
											</xsl:for-each>										
										</td>
									</xsl:for-each>
								</tr>
							</xsl:for-each>
						</table>
					</xsl:for-each>
					<!-- 分页标记 -->
					<p class="PageNext"></p>
				</xsl:for-each>
			</xsl:when>
		</xsl:choose>
	</xsl:if>
</xsl:for-each>
<xsl:for-each select="/page/pages">
	<!-- 处理正文 -->
	<xsl:for-each select="table">
		<table align="center" border="0" cellspacing="0" cellpadding="0">
			<xsl:attribute name="style">
				<xsl:if test="@width">
						border-collapse: collapse;width:<xsl:value-of select="@width"/>;
				</xsl:if>
			</xsl:attribute>
			<xsl:for-each select="tr">
				<tr>
					<xsl:attribute name="style">
						<xsl:if test="@height">
							height:<xsl:value-of select="@height"/>;
						</xsl:if>
					</xsl:attribute>
					<xsl:for-each select="td">
						<td>
							<xsl:attribute name="style">
								<xsl:if test="@border">
									border:<xsl:value-of select="@border"/>;
								</xsl:if>
								<xsl:if test="@border-bottom">
									border-bottom:<xsl:value-of select="@border-bottom"/>;
								</xsl:if>
								<xsl:if test="@border-top">
									border-top:<xsl:value-of select="@border-top"/>;
								</xsl:if>
								<xsl:if test="@border-left">
									border-left:<xsl:value-of select="@border-left"/>;
								</xsl:if>
								<xsl:if test="@border-right">
									border-right:<xsl:value-of select="@border-right"/>;																
								</xsl:if>
								<xsl:if test="@width">
									width:<xsl:value-of select="@width"/>;
								</xsl:if>
							</xsl:attribute>
							<xsl:if test="@colspan">
								<xsl:attribute name="colspan">
									<xsl:value-of select="@colspan"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:if test="@rowspan">
								<xsl:attribute name="rowspan">
									<xsl:value-of select="@rowspan"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:if test="@align">
								<xsl:attribute name="align">
									<xsl:value-of select="@align"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:if test="@valign">
								<xsl:attribute name="valign">
									<xsl:value-of select="@valign"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:for-each select="p">
								<p>
									<xsl:attribute name="style">
										<xsl:if test="@font">
											font-family:
											<xsl:choose>
												<xsl:when test="@font='Simsun'">宋体</xsl:when>
												<xsl:when test="@font='Simhei'">黑体</xsl:when>
											</xsl:choose>;
										</xsl:if>
										<xsl:if test="@font-size">
												font-size:<xsl:value-of select="@font-size"/>;
										</xsl:if>
										<xsl:if test="@color">
												color:<xsl:value-of select="@color"/>;																		
										</xsl:if>
										<xsl:if test="@align">
												text-align:<xsl:value-of select="@align"/>;
										</xsl:if>
										<xsl:if test="@padding">
												padding:<xsl:value-of select="@padding"/>;
										</xsl:if>
										<xsl:if test="@margin-top">
												margin-top:<xsl:value-of select="@margin-top"/>;
										</xsl:if>
										<xsl:if test="@padding-left">
												padding-left:<xsl:value-of select="@padding-left"/>;
										</xsl:if>
										<xsl:if test="@padding-right">
												padding-right:<xsl:value-of select="@padding-right"/>;
										</xsl:if>
										<xsl:if test="@line-height">
												line-height:<xsl:value-of select="@line-height"/>;
										</xsl:if>
									</xsl:attribute>
									<xsl:value-of select="."/>
								</p>
							</xsl:for-each>										
						</td>
					</xsl:for-each>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:for-each>
	<!-- 分页标记 -->					
	<div class="PageNext">　</div>
</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

/*
 * @file_name： LoadFormListService.java
 * @author lixiangyu 2010-1-21
 * @description：  
 * @copyright： 网天信息技术有限公司
*/
package com.netsky.base.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * 读取表单中列表数据
 * @class name:<br>com.ghpms.service.form.LoadFormListService
 * @author lee.xiangyu 2010-01-21
 */

public interface LoadFormListService {
	
	public void load(HttpServletRequest request, Map paraMap) throws Exception;
}

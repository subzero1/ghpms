package com.netsky.base.imagecut;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

@Controller("/cutImage.do")
public class CutImageServlet implements org.springframework.web.servlet.mvc.Controller {

	/**
	 * 查询服务接口
	 */
	@Autowired
	private QueryService queryService;
	
	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private ExceptionService exceptionService;
	
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("GBK");
		
		PrintWriter out = null;
		Long module_id = null;
		Long doc_id = null;
		String navTabId = null;
		String json = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			module_id = convertUtil.toLong(request.getParameter("module_id"),0L);
			doc_id = convertUtil.toLong(request.getParameter("doc_id"),user.getId());
			navTabId = convertUtil.toString(request.getParameter("navTabId"),"desktop");
			String file_separator = System.getProperty("file.separator");
			int width=150;
			int height=113;
			//获取缩放和剪切参数
			int imageWidth = Integer.parseInt(request.getParameter("txt_width"));//图片实际宽度
		    int imageHeight = Integer.parseInt(request.getParameter("txt_height"));//图片实际高度
		    int cutTop = Integer.parseInt(request.getParameter("txt_top"));//距离顶部
		    int cutLeft = Integer.parseInt(request.getParameter("txt_left"));//距离左边
		    int dropWidth = width;//截取框的宽
		    int dropHeight = height;//截取框的高
		    float imageZoom = Float.parseFloat(request.getParameter("txt_Zoom"));//放大倍数
		    String picture = request.getParameter("picture");
		    String picture_o = picture.replace("a", "b");
		    
		    Rectangle rec = new Rectangle(cutLeft, cutTop, dropWidth, dropHeight);
		    String url = request.getSession().getServletContext().getRealPath("/");
		    File user_image_a = new File(url+ file_separator +"upload"+ file_separator +picture);
		    File user_image_b = new File(url+ file_separator +"upload"+ file_separator +picture_o);
		    
		    ImageHepler.cut(user_image_a, user_image_b, imageWidth, imageHeight, rec);
			        
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
			queryBuilder.eq("doc_id", doc_id);
			queryBuilder.eq("module_id", module_id);
			ResultObject ro_te01 = queryService.search(queryBuilder);
			Te01_slave te01 = new Te01_slave();
			if(ro_te01.next()){
				te01 = (Te01_slave)ro_te01.get(Te01_slave.class.getName());
			}
			FtpService ftp = new FtpService();

		    String ftpUrl = ftp.FileFtpUpload(request, url+ file_separator + "upload" + file_separator +picture_o ,te01.getId()+"Slave"+te01.getExt_name(),te01.getSlave_type());
		    json = "{\"statusCode\":\"200\", \"message\":\"头像修改成功\", \"navTabId\":\""+navTabId+"\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}";
		    if(!"failed".equals(ftpUrl)){
		    	te01.setFtp_url(ftpUrl);
		    	te01.setFtp_date(new Date());
		    	saveService.save(te01);
		    }else{
		    	json = "{\"statusCode\":\"300\", \"message\":\"头像上传失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		    	return	exceptionService.exceptionControl(this.getClass().getName(), "头像上传失败",new Exception("头像上传失败")); 
		    }
		    user_image_a.delete();
		    user_image_b.delete();
			
		} catch (Exception e) {
			System.out.println(e);
			json = "{\"statusCode\":\"300\", \"message\":\"剪切位置错误\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		out.print(json);
		return null;
	}
}

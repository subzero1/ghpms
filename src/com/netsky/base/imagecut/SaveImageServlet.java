package com.netsky.base.imagecut;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jspsmart.upload.SmartUpload;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.dataObjects.Tz01_exception;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

public class SaveImageServlet extends HttpServlet {
	/**
	 * 查询服务
	 */
	private QueryService queryService;
	
	/**
	 * 保存服务
	 */
	private SaveService saveService;
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String str = "";
		Long module_id = null;
		Long doc_id = null;
		Long project_id = null;
		try {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			module_id = convertUtil.toLong(request.getParameter("module_id"),0L);
			doc_id = convertUtil.toLong(request.getParameter("doc_id"),user.getId());
			project_id = convertUtil.toLong(request.getParameter("project_id"),user.getId());
			
			String file_separator = System.getProperty("file.separator");
			ApplicationContext actx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			queryService =(QueryService) actx.getBean("queryService");
			saveService = (SaveService) actx.getBean("saveService");
			SmartUpload mySmartUpload = new SmartUpload();
			mySmartUpload.setAllowedFilesList("jpg,jpeg,JPEG,JPG");
			mySmartUpload.setMaxFileSize(4194304);
			ServletConfig ctx = this.getServletConfig();
			mySmartUpload.initialize(ctx, request, response);
			mySmartUpload.upload();

			com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);
			if (!myFile.isMissing()) {
				String FileType = myFile.getFileExt();
				FileType = FileType.toLowerCase(); // 将扩展名转换成小写
				String personal_head = user.getId().toString()+"a."+FileType;// 根据个人ID给上传的文件取名
				
				String newFileName = request.getSession().getServletContext().getRealPath("/") + file_separator +"upload" + file_separator +personal_head;
				str = newFileName;
				myFile.saveAs(file_separator + "upload" + file_separator +personal_head,1);
				
				// 保存te01表数据
				QueryBuilder queryBuilder2 = new HibernateQueryBuilder(Te01_slave.class);
				queryBuilder2.eq("doc_id", doc_id);
				//queryBuilder2.eq("user_id", user.getId());
				queryBuilder2.eq("module_id",module_id);
				ResultObject ro_te01 = queryService.search(queryBuilder2);
				Te01_slave te01 = new Te01_slave();
				if(ro_te01.next()){
					te01 = (Te01_slave)ro_te01.get(Te01_slave.class.getName());
				}else{
					te01.setProject_id(project_id);
					te01.setDoc_id(doc_id);
					te01.setSlave_type("用户头像");
					te01.setFile_name(user.getName()+"."+FileType);
					te01.setFtp_url(user.getName()+"."+FileType);
					te01.setUser_name(user.getName());
					te01.setFtp_date(new Date());
					te01.setExt_name("."+FileType);
					te01.setUser_id(user.getId());	
					te01.setModule_id(module_id);
					saveService.save(te01);
				}
				BufferedImage image = ImageIO.read(new File(newFileName));
				int width = image.getWidth();
				int height = image.getHeight();
				response.sendRedirect("dispath.do?url=personalization/upload_ok.html?url="+personal_head+","+width+","+height);
			}
		} catch (Exception e) {
			Tz01_exception tz01 = new Tz01_exception();
			tz01.setOpclass("SaveImageServlet");
			tz01.setOptime(new Date());
			tz01.setName(e + ";"+str);
			OutputStream o = new ByteArrayOutputStream();
			PrintStream p = new PrintStream(o);
			e.printStackTrace(p);
			byte[] b = new byte[2000];
			p.write(b);
			tz01.setStacktrace(new String(b));
			saveService.save(tz01);
		}
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}


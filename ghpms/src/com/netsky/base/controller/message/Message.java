package com.netsky.base.controller.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.dataObjects.base.Tc02_area;
import com.ghpms.service.MessageToPhoneService;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.dataObjects.Te04_message;
import com.netsky.base.dataObjects.Te11_message_receiver;
import com.netsky.base.dataObjects.Te08_message;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.imagecut.FtpService;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.PHSService;
import com.netsky.base.utils.StringFormatUtil;

@Controller
public class Message {

	/**
	 * 异常捕捉
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;
	
	/**
	 * 短信服務
	 */
	@Autowired
	private MessageToPhoneService messageToPhoneService;
	
	private Logger log = Logger.getLogger(this.getClass());

	
	/**
	 * 短消息列表查看
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageList.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		Integer messageState = convertUtil.toInteger(request.getParameter("messageState"));
		String orderField = StringFormatUtil.format(request.getParameter("orderField"), "");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"), "desc");
		StringBuffer hsql = new StringBuffer();
		String view = null;

		int totalPages = 1;
		int totalCount = 1;

		ModelMap modelMap = new ModelMap();

		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);

		try {
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			Long user_id = user.getId();
			// 构造hsql 设置信箱状态
			String message_title = "邮箱";

			switch (messageState) {
			case 1:
				hsql.append("from Te04_message te04,Ta03_user ta03,Te11_message_receiver te11 ");
				hsql.append(" where te04.id = te11.msg_id ");
				hsql.append(" and te04.sender_id=ta03.id ");
				hsql.append(" and te04.send_flag<>0 ");
				hsql.append(" and te11.delete_flag is null ");
				hsql.append(" and te11.reader_id='" + user_id + "' ");
				if(orderField.equals("")){
					orderField = " te11.read_flag,te04.id ";
				}
				message_title = "收件箱";
				break;
			case 2:
				hsql.append("from Te04_message te04 ");
				hsql.append(" where send_flag=0 ");
				hsql.append(" and delete_flag is null ");
				hsql.append(" and sender_id=");
				hsql.append(user_id);
				if(orderField.equals("")){
					orderField = " te04.id ";
				}
				message_title = "草稿箱";
				break;
			case 3:
				hsql.append("from Te04_message te04,V_message_receiver a ");
				hsql.append(" where send_flag=1 ");
				hsql.append(" and delete_flag is null ");
				hsql.append(" and sender_id=" + user_id + " ");
				hsql.append(" and a.id=te04.id ");
				if(orderField.equals("")){
					orderField = " te04.id ";
				}
				message_title = "发件箱";
				break;
			case 4:
				hsql.append("from Te04_message te04,Ta03_user ta03,Te11_message_receiver te11 ");
				hsql.append(" where te04.id = te11.msg_id ");
				hsql.append(" and te04.sender_id=ta03.id ");
				hsql.append(" and te11.delete_flag = 0 and te04.reader_id='" + user_id + "'");
				if(orderField.equals("")){
					orderField = " te11.read_flag,te04.id ";
				}
				message_title = "垃圾箱";
				break;
			default:
				hsql.append("from Te04_message te04,Ta03_user ta03,Te11_message_receiver te11 ");
				hsql.append(" where te04.id = te11.msg_id ");
				hsql.append(" and te04.sender_id=ta03.id ");
				hsql.append(" and te04.send_flag<>0 ");
				hsql.append(" and te11.delete_flag is null ");
				hsql.append(" and te11.reader_id='" + user_id + "' ");
				if(orderField.equals("")){
					orderField = " te11.read_flag,te04.id ";
				}
				message_title = "收件箱";
				messageState = 1;
			}
			// 设置排序
			StringBuffer order = new StringBuffer();
			order.append(" order by ");
			order.append(orderField + " ");
			order.append(orderDirection);
			hsql.append(order.toString());
			modelMap.put("message_title", message_title);
			modelMap.put("messageState", messageState);

			// 获得记录条数
			ResultObject rs = queryService.search("select 'X' " + hsql.toString());
			totalCount = rs.getLength();
			if (totalCount > 0) {
				totalPages = totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1;
			} else {
				totalPages = 0;
			}
			if (pageNum < 1 || pageNum > totalPages) {
				pageNum = 1;
			}
			modelMap.put("totalCount", totalCount);
			modelMap.put("totalPages", totalPages);
			modelMap.put("page", pageNum);
			// 取列表数据
			String tmp_sql = null;
			if(hsql.indexOf("ta03") != -1){
				tmp_sql = "select te04.id,ta03.name,ta03.login_id,ta03.mobile_tel,te04.title,te04.send_date,"
					    + "te11.read_flag,te04.fujian_flag,te04.repeat_flag,te11.reader_name "+hsql.toString();
				view = "messagelist.jsp";
			}else if(hsql.indexOf("V_message_receiver")!=-1){
				tmp_sql = "select te04.id,te04.title,te04.send_date,te04.fujian_flag,te04.repeat_flag,a.nums "
				    + hsql.toString();
				view="messageListReceiver.jsp";
			}else{
				tmp_sql = "select te04.id,te04.title,te04.send_date,te04.fujian_flag,te04.repeat_flag "
					    + hsql.toString();
				view = "messagelistNoUser.jsp";
			}
			rs = queryService.searchByPage(tmp_sql,pageNum, numPerPage);
			List message_list = new ArrayList();
			
			StringBuffer hql=new StringBuffer();
			Long nums;
			while (rs.next()) {
				Map<String, Object> mo = rs.getMap();
				hql.delete(0, hql.length());
				hql.append("select a.reader_name,a.read_flag from Te11_message_receiver a where a.msg_id=");
				hql.append(mo.get("te04.id"));
				nums=convertUtil.toLong(mo.get("a.nums"));
				List receiver = null;
				if (nums==1) {
					receiver=queryService.searchList(hql.toString());
					mo.put("receiver", receiver);
				}
				message_list.add(mo);
			}
			modelMap.put("message_list", message_list);

			return new ModelAndView("/WEB-INF/jsp/message/"+view, modelMap);

		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息列表查看", e);
		}
	}

	/**
	 * 短消系单条查看
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageRead.do")
	public ModelAndView readmessage(HttpServletRequest request, HttpServletResponse reponse) {
		Long message_id = new Long(StringFormatUtil.format(request.getParameter("message_id"), "-1"));
		Integer messageState = convertUtil.toInteger(request.getParameter("messageState"));

		StringBuffer HSql = new StringBuffer();
		ModelMap modelMap = new ModelMap();

		try {
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			if (user == null) {
				return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception(
						"用户未登录或登录超时"));
			}
			HSql.append(" select te04.id,ta03.name,ta03.login_id,ta03.mobile_tel,te04.title,te04.repeat_flag, ");
			HSql.append(" te04.send_date,te04.content,te04.fujian_flag ");
			HSql.append(" from Te04_message te04,Ta03_user ta03 ");
			HSql.append(" where ta03.id = te04.sender_id ");
			HSql.append(" and te04.id=");
			HSql.append(message_id);
			ResultObject rs = queryService.search(HSql.toString());
			if (rs.next()) {
				Map<String, Object> mo = rs.getMap();
				modelMap.put("singlemessage", mo);
				if (Integer.parseInt(mo.get("te04.fujian_flag").toString()) > 0) {
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
					queryBuilder.eq("module_id", new Long(9002));
					queryBuilder.eq("doc_id", new Long(mo.get("te04.id").toString()));
					List<?> fj_list = queryService.searchList(queryBuilder);
					modelMap.put("fj_list", fj_list);
					modelMap.put("rowsnum", Integer.parseInt(mo.get("te04.fujian_flag").toString()));
				}
			}
			saveService.updateByHSql("update Te11_message_receiver set read_flag=1 where reader_id = "+user.getId()+" and msg_id="+message_id);
			
			modelMap.put("message_id", message_id);
			modelMap.put("messageState", messageState);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消系单条查看", e);
		}
		return new ModelAndView("/WEB-INF/jsp/message/messageread.jsp", modelMap);
	}

	/**
	 * 处理转发和回复
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/RepeatMessage.do")
	public ModelAndView repeatmessage(HttpServletRequest request, HttpServletResponse reponse) {
		Long message_id = new Long(StringFormatUtil.format(request.getParameter("message_id"), "-1"));
		String goanother = request.getParameter("goanother");

		StringBuffer HSql = new StringBuffer();
		ModelMap modelMap = new ModelMap();

		try {
			HSql.append("select te04.id,ta03.name,te04.sender_id,te04.content,te04.fujian_flag,te04.title,te04.send_date,te11.reader_name ");
			HSql.append("from Te04_message te04,Ta03_user ta03,Te11_message_receiver te11 ");
			HSql.append("where ta03.id = te04.sender_id ");
			HSql.append("and te04.id = te11.msg_id ");
			HSql.append("and te04.id=");
			HSql.append(message_id);
			ResultObject rs = queryService.search(HSql.toString());
			if (rs.next()) {
				Map<String, Object> mo = rs.getMap();
				if (goanother.equals("huifu")) {
					modelMap.put("reader_name", mo.get("ta03.name").toString());
					modelMap.put("reader_id", mo.get("te04.sender_id").toString());
					modelMap.put("title", "回复：" + mo.get("te04.title"));
					modelMap.put("mssage_id", mo.get("te04.id"));
					// 已回复
					saveService.updateByHSql("update Te04_message set repeat_flag=2 where id=" + message_id);
				}
				if (goanother.equals("zhuanfa")) {
					modelMap.put("title", "转发：" + mo.get("te04.title"));
					if (Integer.parseInt(mo.get("te04.fujian_flag").toString()) > 0) {
						QueryBuilder queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
						queryBuilder.eq("module_id", new Long(9002));
						queryBuilder.eq("doc_id", new Long(mo.get("te04.id").toString()));
						List<?> fj_list = queryService.searchList(queryBuilder);
						modelMap.put("fj_list", fj_list);
						modelMap.put("rowsnum", Integer.parseInt(mo.get("te04.fujian_flag").toString()));
					}
					//转发内容拼接
					StringBuffer content=new StringBuffer();
					content.append("\n \n \n \n \n");
					content.append("----原始邮件----\n");
					content.append("发件人:"+mo.get("ta03.name")+"\n");
					content.append("发送时间:"+DateFormatUtil.Format(DateFormatUtil.FormatTimeString(mo.get("te04.send_date").toString()), "yyyy年MM月dd日 HH:mm")+"\n");
					content.append("收件人:"+mo.get("te11.reader_name").toString()+"\n");
					content.append("主题:"+mo.get("te04.title")+"\n");
					content.append(mo.get("te04.content"));
					modelMap.put("content", content.toString());
				}
			}
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息－处理转发和回复", e);
		}
		return new ModelAndView("/MessageWrite2.do", modelMap);
	}

	/**
	 * 短消息发送中的初始化人员、地区、部门
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageWrite.do")
	public ModelAndView wirtemessage(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		QueryBuilder queryBuilder = null;
		ModelMap modelMap = new ModelMap();
		String type = StringFormatUtil.format(request.getParameter("type"), "");
		String url = null;
		try {
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			// user_dept_id 用户所在部门ID
			Long user_dept_id = user.getDept_id();
			modelMap.put("user_dept_id", user_dept_id);

			// 查询用户所在部门信息
			StringBuffer sql = new StringBuffer();

			sql.append("select t1.id,t1.name,t1.area_name ");
			sql.append("from Ta01_dept t1 ");
			sql.append("where t1.area_name=(select area_name from Ta01_dept where id=");
			sql.append(user_dept_id+") and t1.showflag is null ");
			List<?> user_dept_list = queryService.searchList(sql.toString());
			modelMap.put("user_dept_list", user_dept_list);

			// 查询地区
			queryBuilder = new HibernateQueryBuilder(Tc02_area.class);
			queryBuilder.like("type", "[3]", MatchMode.ANYWHERE);
			List<?> areaList = queryService.searchList(queryBuilder);
			modelMap.put("areaList", areaList);

			// 查询部门下所有的人
			queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
			queryBuilder.eq("dept_id", user_dept_id);
			queryBuilder.addOrderBy(Order.asc("login_id"));
			List<?> user_list = queryService.searchList(queryBuilder);
			modelMap.put("user_list", user_list);

		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息发送中的初始化人员、地区、部门  －错误", e);
		}
		if ("message".equals(type) || "".equals(type)) {
			url = "/WEB-INF/jsp/message/messagewrite.jsp";
		}
		if ("phone".equals(type)) {
			String phonenum = "";
			String onlinename = "";
			if (request.getParameter("name")!=null){
				onlinename = StringFormatUtil.format(java.net.URLDecoder.decode(request.getParameter("name"),"UTF-8"), "");
				phonenum = StringFormatUtil.format(request.getParameter("phonenum"), "");
				
			}
			// phonenum = phonenum.replaceAll(";", ",");
			if (!"".equals(onlinename) && !"".equals(phonenum)) {
				String tmp = onlinename;
				for(int i = 1 ; i < phonenum.split(",").length ; i++){
					onlinename += "；"+tmp;
				}
				modelMap.put("reader_name1", tmp);
				modelMap.put("reader_name", onlinename);
				modelMap.put("reader_tel", phonenum);
			}
			url = "/WEB-INF/jsp/message/phonemessage.jsp";
		}
		return new ModelAndView(url, modelMap);
	}

	/**
	 * 短消息发送到系统邮箱
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageSend3.do")
	public void messagesend3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		QueryBuilder queryBuilder = null;
		String title = null;
		String content = null;
		String reader_id = null;
		String reader_name = null;
		String send_flag = null;

		String zffjid[] = request.getParameterValues("zffjid");
		String msg = "";
		String repeat_flag = StringFormatUtil.format(request.getParameter("repeat_flag"), "0");
		try {
			// 设置当前时间
			Date time = new Date();
			Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");
			HttpServletRequest Crequest = (MultipartHttpServletRequest) request;

			title = request.getParameter("title");
			content = request.getParameter("content");
			reader_id = request.getParameter("reader_id");
			reader_name = request.getParameter("reader_name");
			send_flag = request.getParameter("send_flag");

			// 保存数据

			String[] readers_id = reader_id.split(",");
			String[] readers_name = reader_name.split(",");
			for (int i = 0; i < readers_id.length; i++) {
				int fj_num = 0;
				Te04_message te04 = new Te04_message();
				te04.setTitle(title); // 标题
				te04.setContent(content); // 内容
				te04.setSend_date(time); // 发送时间
				te04.setSender_id(ta03.getId()); // 发件人id
				te04.setRead_flag("0"); // 阅读标示 0 未读；1 已读；
				te04.setReader_id(readers_id[i]); // 收件人id
				te04.setReader_name(readers_name[i]); // 收件人姓名
				te04.setRepeat_flag(new Long(repeat_flag)); // 是否需要回复 0 不需要；1
				// 需要；
				te04.setSend_flag(new Long(send_flag)); // 直接发送还是存草稿
				saveService.save(te04);
				queryBuilder = new HibernateQueryBuilder(Te04_message.class);

				MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
				Iterator<?> it = Mrequest.getFileNames();
				while (it.hasNext()) {
					String fileDispath = (String) it.next();
					MultipartFile file = Mrequest.getFile(fileDispath);
					if (file.getName() != null && !file.getName().equals("") && file.getInputStream().available() > 0) {
						String fileName = new String(file.getOriginalFilename());
						String extends_name = "";
						if (file.getOriginalFilename().indexOf(".") != -1) {
							extends_name = file.getOriginalFilename().substring(
									file.getOriginalFilename().lastIndexOf("."));
						}
						Te01_slave te01 = new Te01_slave();
						te01.setProject_id(te04.getId());
						te01.setDoc_id(te04.getId());
						te01.setSlave_type("其他附件");
						te01.setFile_name(fileName);
						te01.setExt_name(extends_name);
						te01.setFtp_url(fileName);
						te01.setUser_name(ta03.getName());
						te01.setUser_id(ta03.getId());
						te01.setFtp_date(time);
						te01.setModule_id(new Long(9002));
						saveService.save(te01);
						queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
						StringBuffer ftp_url = new StringBuffer();
						ftp_url.append(te01.getId());
						ftp_url.append("Slave");
						ftp_url.append(te01.getExt_name());

						// ftp 上传
						FtpService ftp = new FtpService();
						String save_url = ftp.FileFtpUpload(request, file, ftp_url.toString(), te01.getSlave_type());
						te01.setFtp_url(save_url);
						saveService.save(te01);
						fj_num++;
					}
				}
				if (zffjid != null && !"".equals(zffjid)) {
					for (int j = 0; j < zffjid.length; j++) {
						queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
						queryBuilder.eq("id", new Long(zffjid[j]));
						ResultObject ro = queryService.search(queryBuilder);
						if (ro.next()) {
							Te01_slave te01 = (Te01_slave) ro.get(Te01_slave.class.getName());
							te01.setDoc_id(te04.getId());
							te01.setProject_id(te04.getId());
							te01.setId(null);
							saveService.save(te01);
							queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
							String copyname = te01.getId().toString() + "Slave" + te01.getExt_name();
							te01.setFtp_url(copyname);
							saveService.save(te01);
							fj_num++;
						}
					}
				}
				te04.setFujian_flag(new Long(fj_num));
				saveService.save(te04);
			}
		} catch (Exception e) {
			msg = "{\"statusCode\":\"301\", \"message\":\"操作失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		// return null;
		String messageState = convertUtil.toString(request.getParameter("messageState"),"1");
		msg = "{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"_current\", \"forwardUrl\":\"MessageList.do?messageState="+messageState+"\", \"callbackType\":\"\"}";
		response.getWriter().print(msg);
	}
	
	/**
	 * 短消息发送到系统邮箱
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageSend.do")
	public void messagesend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		QueryBuilder queryBuilder = null;
		String title = null;
		String content = null;
		String reader_id = null;
		String reader_name = null;
		String send_flag = null;
		Long msg_id = null;
		Long message_id=null;
		String sendType=null;

		String zffjid[] = request.getParameterValues("zffjid");
		String msg = "";
		String repeat_flag = StringFormatUtil.format(request.getParameter("repeat_flag"), "0");
		try {
			// 设置当前时间
			Date time = new Date();
			Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");
			HttpServletRequest Crequest = (MultipartHttpServletRequest) request;

			title = request.getParameter("title");
			content = request.getParameter("content");
			reader_id = request.getParameter("reader_id");
			reader_name = request.getParameter("reader_name");
			send_flag = request.getParameter("send_flag");
			message_id=convertUtil.toLong(request.getParameter("message_id"));
			sendType=convertUtil.toString(request.getParameter("sendType"));

			// 保存数据
			String[] readers_id = reader_id.split(",");
			String[] readers_name = reader_name.split(",");
			
			int fj_num = 0;
			Te04_message te04 = new Te04_message();
			te04.setTitle(title); // 标题
			te04.setContent(content); // 内容
			te04.setSend_date(time); // 发送时间
			te04.setSender_id(ta03.getId()); // 发件人id
			//te04.setRead_flag("0"); // 阅读标示 0 未读；1 已读；
			//te04.setReader_id(readers_id[i]); // 收件人id
			//te04.setReader_name(readers_name[i]); // 收件人姓名
			te04.setRepeat_flag(new Long(repeat_flag)); // 是否需要回复 0 不需要；1
			// 需要；
			te04.setSend_flag(new Long(send_flag)); // 直接发送还是存草稿
			if (sendType.equals("huifu")) {
				te04.setParent_id(message_id);
			}
			
			saveService.save(te04);
			
			queryBuilder = new HibernateQueryBuilder(Te04_message.class);
			MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
			Iterator<?> it = Mrequest.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = Mrequest.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("") && file.getInputStream().available() > 0) {
					String fileName = new String(file.getOriginalFilename());
					String extends_name = "";
					if (file.getOriginalFilename().indexOf(".") != -1) {
						extends_name = file.getOriginalFilename().substring(
								file.getOriginalFilename().lastIndexOf("."));
					}
					Te01_slave te01 = new Te01_slave();
					te01.setProject_id(te04.getId());
					te01.setDoc_id(te04.getId());
					te01.setSlave_type("其他附件");
					te01.setFile_name(fileName);
					te01.setExt_name(extends_name);
					te01.setFtp_url(fileName);
					te01.setUser_name(ta03.getName());
					te01.setUser_id(ta03.getId());
					te01.setFtp_date(time);
					te01.setModule_id(new Long(9002));
					saveService.save(te01);
					queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
					StringBuffer ftp_url = new StringBuffer();
					ftp_url.append(te01.getId());
					ftp_url.append("Slave");
					ftp_url.append(te01.getExt_name());

					// ftp 上传
					FtpService ftp = new FtpService();
					String save_url = ftp.FileFtpUpload(request, file, ftp_url.toString(), te01.getSlave_type());
					te01.setFtp_url(save_url);
					saveService.save(te01);
					fj_num++;
				}
			}
			if (zffjid != null && !"".equals(zffjid)) {
				for (int j = 0; j < zffjid.length; j++) {
					queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
					queryBuilder.eq("id", new Long(zffjid[j]));
					ResultObject ro = queryService.search(queryBuilder);
					if (ro.next()) {
						Te01_slave te01 = (Te01_slave) ro.get(Te01_slave.class.getName());
						te01.setDoc_id(te04.getId());
						te01.setProject_id(te04.getId());
						te01.setId(null);
						saveService.save(te01);
						queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
						//String copyname = te01.getId().toString() + "Slave" + te01.getExt_name();
						te01.setFtp_url(te01.getFtp_url());
						saveService.save(te01);
						fj_num++;
					}
				}
			}
			te04.setFujian_flag(new Long(fj_num));
			saveService.save(te04);
			msg_id = te04.getId();
		
			for (int i = 0; i < readers_id.length; i++) {
				Te11_message_receiver te11 = new Te11_message_receiver();
				te11.setMsg_id(msg_id);
				te11.setReader_id(convertUtil.toLong(readers_id[i]));
				te11.setReader_name(readers_name[i]);
				te11.setRead_flag(0L);
				//te11.setRepeat_flag(0L);
				saveService.save(te11);
			}
		} catch (Exception e) {
			msg = "{\"statusCode\":\"301\", \"message\":\"操作失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		// return null;
		String messageState = convertUtil.toString(request.getParameter("messageState"),"1");
		msg = "{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"_current\", \"forwardUrl\":\"MessageList.do?messageState="+messageState+"\", \"callbackType\":\"\"}";
		response.getWriter().print(msg);
	}

	/**
	 * 成功页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/success.do")
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) throws IOException {

		return new ModelAndView("/WEB-INF/jsp/message/success.jsp");
	}

	/**
	 * 短消发送到手机
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageToPhone.do")
	public void messagetophone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = "";
		response.setCharacterEncoding("UTF-8");
		String content = null;
		String reader_name = null;
		String reader_tel = null;
		StringBuffer message_phone = new StringBuffer();
		try {
			// 设置当前时间

			Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");

			content = request.getParameter("content");
			reader_name =request.getParameter("reader_name");
			reader_tel = request.getParameter("reader_tel");
			String fsr = ta03.getName() + "";
			message_phone.append("发自：" + fsr + "[ghpms系统]");
			message_phone.append("\n");
			message_phone.append("内容：");
			message_phone.append(content);
			String failed = "";
			if (!"".equals(reader_tel)) {
				String[] reader_names = reader_name.split("；");
				String[] readers = reader_tel.split(",");
				for (int i = 0; i < readers.length; i++) {

					if (NumberFormatUtil.isNumeric(readers[i]) && readers[i].length() == 11) {
						PHSService phs = new PHSService();
						phs.setSaveService(saveService);
						phs.setRecvNum(readers[i]);
						phs.setMsg(message_phone.toString());
						String state = phs.sendSMS();
						
						phs.dxjl(fsr, reader_names[i], "手机短信", content, state);// 短信发送记录
						if (!"success".equals(state)){
							failed += reader_names[i]+";";
						}
					} else {
						failed += reader_names[i]+"(非11位数字);";
					}
				}
			}
			if (request.getParameter("additionTels")!=null && request.getParameter("additionTels").length()!=0){
				String tmp = request.getParameter("additionTels");
				tmp = tmp.replaceAll(",", ";");
				tmp = tmp.replaceAll("，", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll("；", ";");
				String[] additionTels = tmp.split(";");
				for (String string : additionTels) {
					if(!"".equals(string)){
						if (NumberFormatUtil.isNumeric(string) && string.length() == 11) {
							PHSService phs = new PHSService();
							phs.setSaveService(saveService);
							phs.setRecvNum(string);
							phs.setMsg(message_phone.toString());
							String state = phs.sendSMS();
							
							phs.dxjl(fsr, string, "手机短信", content, state);// 短信发送记录
							if (!"success".equals(state)){
								failed += string+";";
							}
						} else {
							failed += string+"(非11位数字);";
						}
					}
				}
			}
			if (failed.length()!=0){
				failed = failed.substring(0,failed.length()-1);
			}
			json = "{\"statusCode\":\"200\", \"message\":\"发送完毕!" +
					(failed.length()==0?"":"其中"+failed+"没有发送成功") +
					"\", \"navTabId\":\"_current\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		} catch (Exception e) {
			e.printStackTrace();
			json = "{\"statusCode\":\"300\", \"message\":\"操作失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		response.getWriter().print(json);
	}
	
	
	/**
	 * 短消发送到移動設備 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageToMobile.do")
	public void messagetomobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = "";
		response.setCharacterEncoding("UTF-8");
		String content = null;
		String reader_name = null;
		String reader_tel = null;
		try {
			// 设置当前时间

			Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");

			content = request.getParameter("content");
			reader_name =request.getParameter("reader_name");
			reader_tel = request.getParameter("reader_tel");
			String fsr = ta03.getName() + "";
			
			String failed = "";
			failed=messageToPhoneService.sendMessageToPhone(content, fsr, request.getParameter("additionTels"), reader_tel, reader_name);
			if (failed.length()!=0){
				failed = failed.substring(0,failed.length()-1);
			}
			json = "{\"statusCode\":\"200\", \"message\":\"发送完毕!" +
					(failed.length()==0?"":"其中"+failed+"没有发送成功") +
					"\", \"navTabId\":\"_current\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			
			json = "{\"statusCode\":\"300\", \"message\":\"操作失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		response.getWriter().print(json);
	}

	/**
	 * 短消息删除
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajaxMessageDelete.do")
	public void ajaxMessageDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = response.getWriter();
		ResultObject rs = null;

		String message_ids[] = request.getParameterValues("message_id");
		Integer messageState = convertUtil.toInteger(request.getParameter("messageState"),1);

		StringBuffer HSql = new StringBuffer();

		Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");

		try {
			// 收件箱中删除
			if (messageState == 1) {
				for (int i = 0; i < message_ids.length; i++) {
					HSql.delete(0, HSql.length());
					HSql.append("update Te11_message_receiver set delete_flag=1 where reader_id = "+ta03.getId()+" and msg_id= ");
					HSql.append(message_ids[i]);
					saveService.updateByHSql(HSql.toString());
				}
			}
			// 草稿箱中删除
			if (messageState == 2) {
				for (int i = 0; i < message_ids.length; i++) {
					
					saveService.updateByHSql("update Te04_message set delete_flag = 1 where id=" + message_ids[i]);
					// 删附件
//					rs = queryService.search(" select id,ftp_url from Te01_slave where project_id=" + message_ids[i]
//							+ " and module_id=9002");
//					FtpService ftp = new FtpService();
//					while (rs.next()) {
//						String id = rs.get("id").toString();
//						String formurl = rs.get("ftp_url").toString();
//						ftp.FtpFileDel(request, formurl);
//						saveService.updateByHSql("delete Te01_slave where id=" + id);
//					}
				}
			}
			// 已发送中删除
			if (messageState == 3) {
				for (int i = 0; i < message_ids.length; i++) {
//					HSql.delete(0, HSql.length());
//					HSql.append("update Te04_message set send_flag=3 where id=");
//					HSql.append(message_ids[i]);
//					saveService.updateByHSql(HSql.toString());
					
					saveService.updateByHSql("update Te04_message set delete_flag = 1 where id=" + message_ids[i]);
				}
			}
			// 垃圾箱中删除
			if (messageState == 4) {
				for (int i = 0; i < message_ids.length; i++) {
					HSql.delete(0, HSql.length());
					// 删记录
					saveService.updateByHSql("delete Te04_message where id=" + message_ids[i]);
					// 删附件
					rs = queryService.search(" select id,ftp_url from Te01_slave where project_id=" + message_ids[i]
							+ " and module_id=9002");
					FtpService ftp = new FtpService();
					while (rs.next()) {
						String id = rs.get("id").toString();
						String formurl = rs.get("ftp_url").toString();
						ftp.FtpFileDel(request, formurl);
						saveService.updateByHSql("delete Te01_slave where id=" + id);
					}
				}
			}
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"" + ""
					+ "\", \"forwardUrl\":\"" + "MessageList.do?messageState=" + messageState + "\", \"callbackType\":\"" + "forward" + "\"}");
		} catch (Exception e) {
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败" + e
					+ "\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
	}

	/**
	 * 短消息发送中的人员选择 AJAX实现
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageAjax.do")
	public ModelAndView messageAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		Long area_id = new Long(StringFormatUtil.format(request.getParameter("area_id"), "-1"));
		Long dept_id = new Long(StringFormatUtil.format(request.getParameter("dept_id"), "-1"));
		Long flow_id = new Long(StringFormatUtil.format(request.getParameter("flow_id"), "-1"));
		Long form_id = new Long(StringFormatUtil.format(request.getParameter("form_id"), "-1"));

		QueryBuilder queryBuilder = null;

		try {
			if (area_id != -1) {
				queryBuilder = new HibernateQueryBuilder(Ta01_dept.class);
				queryBuilder.eq("area_id", area_id);
				ResultObject rs = queryService.search(queryBuilder);
				out.println("<response>");
				while (rs.next()) {
					Ta01_dept ta01 = (Ta01_dept) rs.get(Ta01_dept.class.getName());
					out.println("<option>");
					out.println("<id>" + ta01.getId() + "</id>");
					out.println("<name>" + ta01.getName() + "</name>");
					out.println("</option>");
				}
				out.println("</response>");
				out.flush();
				out.close();
			}
			if (dept_id != -1) {
				queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
				queryBuilder.eq("dept_id", dept_id);
				queryBuilder.addOrderBy(Order.asc("login_id"));
				ResultObject rs = queryService.search(queryBuilder);
				out.println("<response>");
				while (rs.next()) {
					Ta03_user ta03 = (Ta03_user) rs.get(Ta03_user.class.getName());
					out.println("<user>");
					out.println("<id>" + ta03.getId() + "</id>");
					out.println("<name>" + ta03.getName() + "</name>");
					out.println("<mobile>" + ta03.getMobile_tel() + "</mobile>");
					out.println("<loginid>" + ta03.getLogin_id() + "</loginid>");
					out.println("</user>");
				}
				out.println("</response>");
				out.flush();
				out.close();
			}
			if (flow_id != -1) {
				ResultObject rs = queryService
						.search("select tb04.cflow_id as cflow_id,ta06.name as name from Tb04_flowgroup tb04,"
								+ "Ta06_module ta06 where tb04.module_id = ta06.id and pflow_id =" + flow_id
								+ " order by tb04.cflow_id");
				out.println("<response>");
				while (rs.next()) {
					out.println("<form>");
					out.println("<id>" + rs.get("cflow_id") + "</id>");
					out.println("<name>" + rs.get("name") + "</name>");
					out.println("</form>");
				}
				out.println("</response>");
				out.flush();
				out.close();
			}
			if (form_id != -1) {
				ResultObject rs = queryService.search("select id,name from Tb02_node where flow_id =" + form_id);
				out.println("<response>");
				while (rs.next()) {
					out.println("<node>");
					out.println("<id>" + rs.get("id") + "</id>");
					out.println("<name>" + rs.get("name") + "</name>");
					out.println("</node>");
				}
				out.println("</response>");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息发送中的人员选择 AJAX实现－错误", e);
		}
		return null;
	}

	/**
	 * 管理员查询系统短信
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/allmessagelist.do")
	public ModelAndView allmessagelist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		ResultObject rs = null;

		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"));
		String orderField = StringFormatUtil.format(request.getParameter("orderField"), "te04.send_date");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"), "desc");

		String keyWord = StringFormatUtil.format(request.getParameter("keyWord"), "");
		String startdate = StringFormatUtil.format(request.getParameter("startdate"), DateGetUtil.getCurDate()
				.substring(0, 8)
				+ "01");
		String enddate = StringFormatUtil.format(request.getParameter("enddate"), DateGetUtil.getCurDate());
		if (startdate.compareTo(enddate) > 0) {
			String temp = startdate;
			startdate = enddate;
			enddate = temp;
		}
		StringBuffer HSql = new StringBuffer();
		int totalPages = 1;
		int totalCount = 1;
		try {
			HSql.append(" select te04.id,ta03.name,ta03.login_id,ta03.mobile_tel,te04.title,te04.send_date, ");
			HSql.append(" te04.read_flag,te04.fujian_flag,te04.repeat_flag,te04.reader_name ");
			HSql.append(" from Te04_message te04,Ta03_user ta03 ");
			HSql.append(" where te04.sender_id=ta03.id and te04.send_flag<>0 ");
			HSql.append(" and te04.send_date>=to_date('" + startdate
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss') and te04.send_date<=to_date('" + enddate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
			if (!"".equals(keyWord)) {
				HSql.append("and (te04.title like '%" + keyWord + "%' or ta03.name like '%" + keyWord + "%') ");
			}
			StringBuffer order = new StringBuffer();
			order.append(" order by ");
			order.append(orderField + " ");
			order.append(orderDirection);
			HSql.append(order.toString());
			// 取列表数据
			rs = queryService.searchByPage(HSql.toString(), pageNum, numPerPage);
			totalCount = rs.getTotalPages();
			totalCount = rs.getTotalRows();
			if (pageNum < 1 || pageNum > totalPages) {
				pageNum = 1;
			}
			modelMap.put("numPerPage", numPerPage);
			modelMap.put("totalCount", totalCount);
			modelMap.put("totalPages", totalPages);
			modelMap.put("pageNum", pageNum);
			List message_list = new ArrayList();
			while (rs.next()) {
				Map<String, Object> mo = rs.getMap();
				message_list.add(mo);
			}
			modelMap.put("message_list", message_list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/WEB-INF/jsp/search/allmessagelist.jsp", modelMap);
	}

	/**
	 * 查询手机短信
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/telmessagelist.do")
	public ModelAndView telmessagelist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		// 删除手机短信
		String del = request.getParameter("del");
		if (del != null && "yes".equals(del)) {
			String[] del_id = request.getParameterValues("del_id");
			for (int i = 0; i < del_id.length; i++) {
				saveService.updateByHSql("delete Te08_message where id=" + del_id[i]);
			}
		}

		ModelMap modelMap = new ModelMap();
		ResultObject rs = null;
		
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		String orderField = StringFormatUtil.format(request.getParameter("orderField"),"fssj");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"),"desc");

		String fsr = StringFormatUtil.format(request.getParameter("fsr"), "");
		String jsr = StringFormatUtil.format(request.getParameter("jsr"), "");
		String startdate = StringFormatUtil.format(request.getParameter("start_date"), DateGetUtil.getCurDate()
				.substring(0, 8)
				+ "01");
		String enddate = StringFormatUtil.format(request.getParameter("end_date"), DateGetUtil.getCurDate());
		modelMap.put("start_date", startdate);
		modelMap.put("end_date", enddate);
		StringBuffer HSql = new StringBuffer();
		int totalPages = 1;
		int totalCount = 1;
		try {
			HSql.append(" select id,fsr,jsr,fssj,title,content,state from Te08_message ");
			HSql.append(" where fssj>=to_date('" + startdate
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss') and fssj<=to_date('" + enddate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
			if (!"".equals(fsr)) {
				HSql.append(" and fsr like '%" + fsr + "%'");
				request.setAttribute("fsr", fsr);
			}
			if (!"".equals(jsr)) {
				HSql.append(" and jsr like '%" + jsr + "%'");
				request.setAttribute("jsr", jsr);
			}
			StringBuffer order = new StringBuffer();
			order.append(" order by ");
			order.append(orderField+" ");
			order.append(orderDirection);
			HSql.append(order.toString());
			// 取列表数据
			rs = queryService.searchByPage(HSql.toString(), pageNum, numPerPage);
			totalPages = rs.getTotalPages();
			totalCount = rs.getTotalRows();
			if (pageNum < 1 || pageNum > totalPages) {
				pageNum = 1;
			}
			modelMap.put("numPerPage", numPerPage);
			modelMap.put("totalCount", totalCount);
			modelMap.put("totalPages", totalPages);
			modelMap.put("pageNum", pageNum);
			List telmessage_list = new ArrayList();
			while (rs.next()) {
				Map<String, Object> mo = rs.getMap();
				telmessage_list.add(mo);
			}
			request.setAttribute("telmessage_list", telmessage_list);

		} catch (Exception e) {

		}
		return new ModelAndView("/WEB-INF/jsp/search/telmessagelist.jsp", modelMap);
	}

	@RequestMapping("/deltelmessage.do")
	public void deltelmessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = response.getWriter();
		try {
			Long id = convertUtil.toLong(request.getParameter("id"), -1L);
			if (id != -1L) {
				saveService.removeObject(Te08_message.class, id);
			}
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"" + "sjdxList"
					+ "\", \"forwardUrl\":\"" + "" + "\", \"callbackType\":\"" + "" + "\"}");
		} catch (Exception e) {
			e.printStackTrace();
			out
					.print("{\"statusCode\":\"300\", \"message\":\"删除失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
	}
	
	@RequestMapping("/dxtz.do")
	public void dxtz(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "success";
		response.setCharacterEncoding("UTF-8");
		String reader_name = null;
		String reader_tel = null;
		String title = null;
		StringBuffer message_phone = new StringBuffer();
		try {
			// 设置当前时间

			Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");
			Ta03_user admin = (Ta03_user) queryService.searchById(Ta03_user.class, 1L);
			reader_name = admin.getName();
			reader_tel = admin.getMobile_tel();
			title = request.getParameter("title");
			String fsr = ta03.getName() + "";
			message_phone.append("发自：" + fsr + "[r-ms系统]");
			message_phone.append("\n");
			message_phone.append("标题：");
			message_phone.append(title);
			

			if (!"".equals(reader_tel)) {
				String[] reader_names = reader_name.split("；");
				String[] readers = reader_tel.split(",");
				for (int i = 0; i < readers.length; i++) {

					// QueryBuilder queryBuilder = new
					// HibernateQueryBuilder(Ta03_user.class);
					// queryBuilder.eq("id", new Long(readers[i]));
					// ResultObject rs = queryService.search(queryBuilder);
					// if(rs.next()){
					// Object o_form = rs.get(Ta03_user.class.getName());
					// String mobile_tel = PropertyInject.getProperty(o_form,
					// "mobile_tel").toString();

					if (NumberFormatUtil.isNumeric(readers[i]) && readers[i].length() == 11) {
						PHSService phs = new PHSService();
						phs.setSaveService(saveService);
						phs.setRecvNum(readers[i]);
						phs.setMsg(message_phone.toString());
						String state = phs.sendSMS();
						phs.dxjl(fsr, reader_names[i], "手机短信", "短信通知:"+title, state);// 短信发送记录
					}
					// }
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "fail";
		}
		response.getWriter().print(msg);
	}
	/**
	 * 清空垃圾箱
	 */
	@RequestMapping("/emptyRecycleBin.do")
	public void emptyRecycleBin(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		String json = "{\"statusCode\":\"200\", \"message\":\"清除成功\", \"navTabId\":\""
				+ "\", \"forwardUrl\":\"MessageList.do?messageState=4"
				+ "\", \"callbackType\":\"forward\"}";
		try {
			Ta03_user user = (Ta03_user) (request.getSession()
					.getAttribute("user"));
			Long user_id = user.getId();
			String hsql = "select te04.id from Te04_message te04 where te04.receive_flag is not null and te04.reader_id='"
					+ user_id + "'";
			List ids = queryService.searchList(hsql);
			String te04_id_str = "";
			if (ids.size() != 0) {
				for (Object object : ids) {
					te04_id_str += object + ",";
				}
				te04_id_str = te04_id_str
						.substring(0, te04_id_str.length() - 1);
				hsql = "select id,ftp_url from Te01_slave where project_id in ("
						+ te04_id_str + ")";
				List list = queryService.searchList(hsql);
				String te01_id_str = "";
				List<String> ftp_url_list = new ArrayList<String>();
				if (list.size() != 0) {
					for (Object object2 : list) {
						Object[] o = (Object[]) object2;
						te01_id_str += o[0] + ",";
						if (o[1] != null && ((String) o[1]).length() != 0) {
							ftp_url_list.add((String) o[1]);
						}
					}
					te01_id_str = te01_id_str.substring(0,
							te01_id_str.length() - 1);
				}
				FtpService ftp = new FtpService();
				for (String string : ftp_url_list) {
					ftp.FtpFileDel(request, string);
				}
				hsql = "delete from Te01_slave where id in (" + te01_id_str
						+ ")";
				if (te01_id_str !=null && te01_id_str.length()!=0)
				saveService.updateByHSql(hsql);
				hsql = "delete from Te04_message where id in (" + te04_id_str
						+ ")";
				if (te04_id_str !=null && te04_id_str.length()!=0)
				saveService.updateByHSql(hsql);
			}
			response.setCharacterEncoding(request.getCharacterEncoding());
			out = response.getWriter();
		} catch (IOException e) {
			json = "{\"statusCode\":\"300\", \"message\":\"清除失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
			e.printStackTrace();
		}
		out.print(json);

	}
	
	/**
	 * 短消息发送中的初始化人员、地区、部门
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageWrite2.do")
	public ModelAndView wirtemessage2(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		QueryBuilder queryBuilder = null;
		ModelMap modelMap = new ModelMap();
		String type = StringFormatUtil.format(request.getParameter("type"), "");
		String url = null;
		try {
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			// user_dept_id 用户所在部门ID
			Long user_dept_id = user.getDept_id();
			modelMap.put("user_dept_id", user_dept_id);

			// 查询用户所在部门信息
			StringBuffer sql = new StringBuffer();

			sql.append("select t1.id,t1.name,t1.area_name ");
			sql.append("from Ta01_dept t1 ");
			sql.append("where t1.area_name=(select area_name from Ta01_dept where id=");
			sql.append(user_dept_id+") and t1.showflag is null ");
			List<?> user_dept_list = queryService.searchList(sql.toString());
			modelMap.put("user_dept_list", user_dept_list);

			// 查询地区
			queryBuilder = new HibernateQueryBuilder(Tc02_area.class);
			queryBuilder.like("type", "[3]", MatchMode.ANYWHERE);
			List<?> areaList = queryService.searchList(queryBuilder);
			modelMap.put("areaList", areaList);

			// 查询部门下所有的人
			queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
			queryBuilder.addOrderBy(Order.asc("login_id"));
			List<?> user_list = queryService.searchList(queryBuilder);
			modelMap.put("user_list", user_list);
			 
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息发送中的初始化人员、地区、部门  －错误", e);
		}
		if ("message".equals(type) || "".equals(type)) {
			url = "/WEB-INF/jsp/message/messagewrite2.jsp";
		}
		if ("phone".equals(type)) {
			String phonenum = "";
			String onlinename = "";
			if (request.getParameter("name")!=null){
				onlinename = StringFormatUtil.format(java.net.URLDecoder.decode(request.getParameter("name"),"UTF-8"), "");
				phonenum = StringFormatUtil.format(request.getParameter("phonenum"), "");
				
			}
			// phonenum = phonenum.replaceAll(";", ",");
			if (!"".equals(onlinename) && !"".equals(phonenum)) {
				String tmp = onlinename;
				for(int i = 1 ; i < phonenum.split(",").length ; i++){
					onlinename += "；"+tmp;
				}
				modelMap.put("reader_name1", tmp);
				modelMap.put("reader_name", onlinename);
				modelMap.put("reader_tel", phonenum);
			}
			url = "/WEB-INF/jsp/message/phonemessage.jsp";
		}
		return new ModelAndView(url, modelMap);
	}
	
	@RequestMapping("/message/messageReaderList.do")
	public ModelAndView messageReaderList(HttpServletRequest request,HttpServletResponse response) {
		ModelMap modelMap=new ModelMap();
		String view="/WEB-INF/jsp/message/messageReaderList.jsp";
		StringBuffer hql=new StringBuffer();
		Long msg_id=convertUtil.toLong(request.getParameter("msg_id"));
		hql.append("select a.reader_name,a.read_flag from Te11_message_receiver a where a.msg_id=");
		hql.append(msg_id);
		List messageReaderList=queryService.searchList(hql.toString());
		modelMap.put("messageReaderList", messageReaderList);
		return new ModelAndView(view,modelMap);
	}
	
	public void saveMessage(String fsr,String jsr,String title,String content,String state){
		Te08_message te08=new Te08_message();
		te08.setFsr(fsr);
		te08.setJsr(jsr);
		te08.setFssj(new Date());
		te08.setTitle(title);
		te08.setContent(content);
		te08.setState(state);
		saveService.save(te08);
	}
	
	/**
	 * 短消息发送中的初始化人员、地区、部门
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/MessageWrite3.do")
	public ModelAndView wirtemessage3(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		QueryBuilder queryBuilder = null;
		ModelMap modelMap = new ModelMap();
		String type = StringFormatUtil.format(request.getParameter("type"), "");
		String url = null;
		try {
			// 查询用户所在部门信息
			StringBuffer sql = new StringBuffer();

			//获得部门列表
			List dept_List=queryService.searchList(Ta01_dept.class);
			modelMap.put("dept_list", dept_List);
			// 查询部门下所有的人
			queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
			queryBuilder.addOrderBy(Order.asc("login_id"));
			List<?> user_list = queryService.searchList(queryBuilder);
			modelMap.put("user_list", user_list);
			 
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "短消息发送中的初始化人员、地区、部门  －错误", e);
		}
		if ("message".equals(type) || "".equals(type)) {
			url = "/WEB-INF/jsp/message/messagewrite3.jsp";
		}
		if ("phone".equals(type)) {
			String phonenum = "";
			String onlinename = "";
			if (request.getParameter("name")!=null){
				onlinename = StringFormatUtil.format(java.net.URLDecoder.decode(request.getParameter("name"),"UTF-8"), "");
				phonenum = StringFormatUtil.format(request.getParameter("phonenum"), "");
				
			}
			// phonenum = phonenum.replaceAll(";", ",");
			if (!"".equals(onlinename) && !"".equals(phonenum)) {
				String tmp = onlinename;
				for(int i = 1 ; i < phonenum.split(",").length ; i++){
					onlinename += "；"+tmp;
				}
				modelMap.put("reader_name1", tmp);
				modelMap.put("reader_name", onlinename);
				modelMap.put("reader_tel", phonenum);
			}
			url = "/WEB-INF/jsp/message/phonemessage.jsp";
		}
		return new ModelAndView(url, modelMap);
	}
}

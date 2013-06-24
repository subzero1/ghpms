package com.ghpms.controller.online;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.controller.OperFile;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te03_online;
import com.netsky.base.dataObjects.Te04_message;
import com.netsky.base.dataObjects.Te07_qa;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.others.QaOrderService;
import com.netsky.base.utils.PHSService;
import com.netsky.base.utils.RegExp;
import com.netsky.base.utils.StringFormatUtil;

import com.netsky.base.dataObjects.interfaces.SlaveObject;
/**
 * @description:
 * 
 * @class name:com.ghpms.controller.OnLine
 * 
 * @author liuxu Feb 25, 2010
 */
@Controller
public class OnLine {

	@Autowired
	Dao dao;
	@Autowired
	private QueryService queryService;
	@Autowired
	private SaveService saveService;
	@Autowired
	private QaOrderService qaOrderService;
	@Autowired
	private ExceptionService exceptionService;
	/**
	 * 默认ftp配置文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";

	/**
	 * 截取指定文章内容
	 * 
	 * @param str
	 * @param n
	 * @return String
	 */
	public static String subContent(String str, int n) {
		// 格式化字符串长度，超出部分显示省略号,区分汉字跟字母。汉字2个字节，字母数字一个字节
		String temp = "";
		if (str.length() < n) {// 如果长度比需要的长度n小,返回原字符串
			return str;
		} else {
			int t = 0;
			char[] tempChar = str.toCharArray();
			for (int i = 0; i < tempChar.length && t < n * 2; i++) {
				if ((int) tempChar[i] >= 0x4E00 && (int) tempChar[i] <= 0x9FA5)// 是否汉字
				{
					temp += tempChar[i];
					t += 2;
				} else {
					temp += tempChar[i];
					t++;
				}
			}
			return (temp + "...");
		}
	}

	// 在线问答列表
	@SuppressWarnings("unchecked")
	@RequestMapping("/OnLineList.do")
	public ModelAndView handleRequest1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// response.setCharacterEncoding("GBK");
		// request.setCharacterEncoding("GBK");

		String orderField = StringFormatUtil.format(request
				.getParameter("orderField"), "status,flag,aq_date");
		String orderDirection = StringFormatUtil.format(request
				.getParameter("orderDirection"), "desc");

		// 权限控制
		Map rolesMap = (Map) request.getSession().getAttribute("rolesMap");
		if (rolesMap != null) {
			if (rolesMap.containsKey("10301")) {
				request.setAttribute("delrole", "yes");
			} else {
				request.setAttribute("delrole", "no");
			}
			if (rolesMap.containsKey("10302")) {
				request.setAttribute("ggrole", "yes");
			} else {
				request.setAttribute("ggrole", "no");
			}
		}

		// 取出当前用户
		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");

		// 在线提问条件显示
		String wtzt = request.getParameter("wtzt");
		String ztgjz = request.getParameter("ztgjz");
		String wtlx = request.getParameter("wtlx");
		String temp = "1";
		List<?> sta_user_list = queryService
				.searchList("select user_id from Ta11_sta_user where station_id=1");
		if (sta_user_list.contains(user.getId())) {
			temp = "";
		}
		if ("601".equals(wtlx)) {
			temp = "";
		}
		String wttcr = StringFormatUtil.format(request.getParameter("wttcr"),
				temp);
		String sql_qa = "select login_id,id,aq_name,title,content,role_id,aq_date,status,aq_tel,flag from Te03_online where ";
		String sql_qa_num = "select count(*) from Te03_online where ";

		if (wtlx != null && !"".equals(wtlx)) {
			sql_qa = sql_qa + " role_id='" + wtlx + "' and ";
			sql_qa_num = sql_qa_num + " role_id='" + wtlx + "' and ";
			request.setAttribute("wtlx", wtlx);
		}
		if (wtzt != null && !"".equals(wtzt)) {
			String zt = ("1".equals(wtzt)) ? "已处理" : "未处理";
			sql_qa = sql_qa + " status='" + zt + "' and ";
			sql_qa_num = sql_qa_num + " status='" + zt + "' and ";
			request.setAttribute("wtzt", wtzt);
		}
		if ("1".equals(wttcr)) {
			sql_qa = sql_qa + " aq_name='" + user.getName() + "' and ";
			sql_qa_num = sql_qa_num + " aq_name='" + user.getName() + "' and ";
			request.setAttribute("wttcr", wttcr);
		}
		if (ztgjz != null && !"".equals(ztgjz)) {
			sql_qa = sql_qa + " (title like '%" + ztgjz
					+ "%' or aq_name like '%" + ztgjz + "%') and ";
			sql_qa_num = sql_qa_num + " (title like '%" + ztgjz
					+ "%' or aq_name like '%" + ztgjz + "%') and ";
			request.setAttribute("ztgjz", ztgjz);
		}

		sql_qa = sql_qa + "up_id is null";
		sql_qa_num = sql_qa_num + "up_id is null";

		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"));
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"));
		numPerPage = numPerPage < 0 ? 20 : numPerPage;
		int totalPages = 1;
		int totalCount = 1;
		request.setAttribute("numPerPage", numPerPage);

		// 获得记录条数
		totalCount = convertUtil.toInteger(dao.search(sql_qa_num).get(0));

		if (totalCount > 0) {
			totalPages = totalCount / (numPerPage + 1) + 1;
		}
		if (pageNum < 1 || pageNum > totalPages) {
			pageNum = 1;
		}
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageNum", pageNum);

		// 设置排序
		StringBuffer order = new StringBuffer();
		order.append(" order by ");
		order.append(orderField + " ");
		order.append(orderDirection);

		// 取列表数据
		ResultObject ro = queryService.searchByPage(sql_qa + order.toString(),
				pageNum, numPerPage);

		List list_qa = new ArrayList();
		String content_jx = "";
		String title_jx = "";
		while (ro.next()) {
			Map<String, Object> mo = ro.getMap();
			// 截取字符
			String str = convertUtil.toString(ro.get("content"));
			content_jx = subContent(str, 29);
			String str1 = convertUtil.toString(ro.get("title"));
			title_jx = subContent(str1, 23);
			mo.put("content_jx", content_jx);
			mo.put("title_jx", title_jx);

			// 取出最后回复人员和最后回复时间
			String sql_zhhf = "select aq_name,aq_date from Te03_online where up_id="
					+ mo.get("id")
					+ " and up_id is not null order by aq_date desc";
			ResultObject ro_zhhf = queryService.search(sql_zhhf);
			if (ro_zhhf.next()) {
				if (!ro_zhhf.get("aq_name").equals("管理员")) {
					mo.put("hfr", "<font color='red' >"
							+ ro_zhhf.get("aq_name") + "</font>");
				} else {
					mo.put("hfr", ro_zhhf.get("aq_name"));
				}
				mo.put("hf_date", ro_zhhf.get("aq_date"));
			} else {
				mo.put("hfr", "");
				mo.put("hf_date", "");
			}

			// 取出主题的回复数
			String sql_hf = "select up_id,count(*) as hfs from Te03_online where up_id="
					+ mo.get("id") + " and up_id is not null group by up_id";
			ResultObject ro_hf = queryService.search(sql_hf);
			if (ro_hf.next()) {
				mo.put("hfs", ro_hf.get("hfs"));
			} else {
				mo.put("hfs", "0");
			}

			// 取出附件数
			String sql_fj = "select count(*) as fjs from Te01_slave where doc_id="
					+ mo.get("id") + " and module_id=9001";
			ResultObject ro_fj = queryService.search(sql_fj);
			if (ro_fj.next()) {
				mo.put("fjs", ro_fj.get("fjs"));
			} else {
				mo.put("fjs", "0");
			}

			list_qa.add(mo);
		}
		request.setAttribute("list_qa", list_qa);

		return new ModelAndView("/WEB-INF/jsp/online/onlinelist.jsp");
	}

	// 在线问答提问
	@RequestMapping("/OnLinequestion.do")
	public ModelAndView handleRequest2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		// 取出问题信息
		String online_id = request.getParameter("online_id");
		String title = "";
		String content = "";
		String aq_date = "";
		String aq_name = "";
		String role_id = "";
		String ydcs = "";
		ResultObject ro_online = null;
		String sql_online = "select aq_name,title,content,aq_date,role_id,ydcs from Te03_online where id="
				+ online_id;
		ro_online = queryService.search(sql_online);
		if (ro_online.next()) {
			if (ro_online.get("title") != null) {
				title = ro_online.get("title").toString();
			}
			if (ro_online.get("content") != null) {
				content = ro_online.get("content").toString();
			}
			if (ro_online.get("aq_date") != null) {
				SimpleDateFormat sf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				aq_date = sf.format(ro_online.get("aq_date"));
			}
			if (ro_online.get("aq_name") != null) {
				aq_name = ro_online.get("aq_name").toString();
			}
			if (ro_online.get("role_id") != null) {
				role_id = ro_online.get("role_id").toString();
			}
			if (ro_online.get("ydcs") != null) {
				ydcs = ro_online.get("ydcs").toString();
			}
		}

		request.setAttribute("online_id", online_id);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("wtlx", role_id);
		request.setAttribute("ydcs", ydcs);
		request.setAttribute("aq_date", aq_date);

		// 判断是否发短信给管理员
		String dxtz = request.getParameter("dxtz");
		if (dxtz != null && "yes".equals(dxtz)) {

			// 取出管理员手机号
			String admin_sjh = "";
			ResultObject ro_sjh = null;
			String sql_sjh = "select mobile_tel from Ta03_user where id=1";
			ro_sjh = queryService.search(sql_sjh);
			while (ro_sjh.next()) {
				if (ro_sjh.get("mobile_tel") != null) {
					admin_sjh = ro_sjh.get("mobile_tel").toString();
				}
			}

			String phonenums[] = admin_sjh.split(";");
			StringBuffer message_phone = new StringBuffer();
			message_phone.append("发自：" + "ghpms在线提问");
			message_phone.append("\n");
			message_phone.append("用户名：" + aq_name);
			message_phone.append("\n");
			// message_phone.append("时间："+aq_date);
			// message_phone.append("\n");
			message_phone.append("标题：" + title);
			message_phone.append("\n");
			message_phone.append("内容：" + content);

			for (int i = 0; i < phonenums.length; i++) {
				PHSService phs = new PHSService();
				phs.setSaveService(saveService);
				// 手机号码
				phs.setRecvNum(phonenums[i]);
				phs.setMsg(message_phone.toString());
				String state = phs.sendSMS();
				phs.dxjl(aq_name, "管理员", "在线提问", content, state);// 保存短信记录
			}
		}

		// 取出IP地址
		String ip = request.getRemoteAddr();
		request.setAttribute("ip", ip);

		// 问题类型
		String wtlx = request.getParameter("wtlx");

		if (wtlx != null && !"".equals(wtlx)) {
			request.setAttribute("wtlx", wtlx);
		}
		return new ModelAndView("/WEB-INF/jsp/online/onlinequestion.jsp");
	}

	// 桌面我的提问列表
	@SuppressWarnings("unchecked")
	@RequestMapping("/OnLineUIList.do")
	public ModelAndView handleRequest4(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/xml");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("GBK");

		// 问题处理完毕
		String status = request.getParameter("status");
		if (status != null && "yes".equals(status)) {
			String aq_id = request.getParameter("aq_id");
			if (aq_id != null) {
				saveService
						.updateByHSql("update Te03_online set status='已处理' where id="
								+ aq_id);
			}
		}

		// 取出当前用户
		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		request.setAttribute("user", user);

		String wtlx = request.getParameter("wtlx");

		String sql_qa = "select id,aq_name,title,content,role_id,aq_date,status from Te03_online where login_id='"
				+ user.getLogin_id() + "'";
		String sql_qa_num = "select count(*) from Te03_online where login_id='"
				+ user.getLogin_id() + "'";

		if (wtlx != null && !"".equals(wtlx)) {
			sql_qa = sql_qa + " and role_id='" + wtlx + "' and ";
			sql_qa_num = sql_qa_num + " and role_id='" + wtlx + "' and ";
			request.setAttribute("wtlx", wtlx);
		}
		sql_qa = sql_qa + "up_id is null order by aq_date desc";
		sql_qa_num = sql_qa_num + "up_id is null";

		// System.out.println("sql_qa="+sql_qa);

		Integer page = convertUtil.toInteger(request.getParameter("page"));
		Integer pageRowSize = convertUtil.toInteger(request
				.getParameter("pageRowSize"));
		pageRowSize = pageRowSize < 3 ? 6 : pageRowSize - 2;
		int totalPages = 1;
		int totalRows = 1;
		request.setAttribute("page", page);
		request.setAttribute("pageRowSize", pageRowSize);

		// 获得记录条数
		totalRows = convertUtil.toInteger(dao.search(sql_qa_num).get(0));
		if (totalRows > 0) {
			totalPages = totalRows / (pageRowSize + 1) + 1;
		}
		if (page < 1 || page > totalPages) {
			page = 1;
		}
		request.setAttribute("totalRows", totalRows);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("page", page);
		// 取列表数据
		ResultObject ro = queryService.searchByPage(sql_qa, page, pageRowSize);
		List list_qa = new ArrayList();
		// List<Object[]> docList = new LinkedList<Object[]>();
		String content_jx = "";
		String title_jx = "";
		while (ro.next()) {
			Map<String, Object> mo = ro.getMap();
			// 截取字符
			String str = convertUtil.toString(ro.get("content"));
			content_jx = subContent(str, 29);
			String str1 = convertUtil.toString(ro.get("title"));
			title_jx = subContent(str1, 14);
			mo.put("content_jx", content_jx);
			mo.put("title_jx", title_jx);
			// 取出主题的回复数
			String sql_hf = "select up_id,count(*) as hfs from Te03_online where up_id="
					+ mo.get("id") + " and up_id is not null group by up_id";
			ResultObject ro_hf = queryService.search(sql_hf);
			if (ro_hf.next()) {
				mo.put("hfs", ro_hf.get("hfs"));
			} else {
				mo.put("hfs", "0");
			}
			list_qa.add(mo);
		}
		request.setAttribute("list_qa", list_qa);

		return new ModelAndView("/WEB-INF/jsp/online/onlineUI.jsp");
	}

	// 在线问答解答
	@SuppressWarnings("unchecked")
	@RequestMapping("/OnLineanswer.do")
	public ModelAndView handleRequest3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		
		// 新回复置状态：管理员=已处理；非管理员=未处理

		// String xhf = request.getParameter("xhf");
		// if (xhf != null && !"1".equals(xhf)) {
		// String aq_id = request.getParameter("aq_id");
		// if (aq_id != null) {
		// saveService
		// .updateByHSql("update Te03_online set status='未处理' where id="
		// + aq_id);
		// }
		// }
		// if (xhf != null && "1".equals(xhf)) {
		// String aq_id = request.getParameter("aq_id");
		// if (aq_id != null) {
		// saveService
		// .updateByHSql("update Te03_online set status='已处理' where id="
		// + aq_id);
		// }
		// }
		// 权限判断
		String role_id = request.getParameter("role_id");
		if (role_id != null) {
			request.setAttribute("role_id", role_id);
		}

		Map rolesMap = (Map) request.getSession().getAttribute("rolesMap");

		if (rolesMap != null) {
			if (rolesMap.containsKey("10301")) {
				request.setAttribute("delrole", "yes");
			} else {
				request.setAttribute("delrole", "no");
			}

			if (rolesMap.containsKey("10302")) {
				request.setAttribute("ggrole", "yes");
			} else {
				request.setAttribute("ggrole", "no");
			}
		} else {
			request.setAttribute("delrole", "no");
			request.setAttribute("ggrole", "no");
		}
		// 更新阅读次数
		saveService
				.updateByHSql("update Te03_online set ydcs = ydcs + 1 where id="
						+ convertUtil.toLong(request.getParameter("aq_id")));

		// 取出当前用户作为默认发布人
		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		request.setAttribute("user", user);
		if (user.getId() == 1) {
			request.setAttribute("xhf", "1");
		} else {
			request.setAttribute("xhf", "0");
		}

		// 取出IP地址
		String ip = request.getRemoteAddr();
		request.setAttribute("ip", ip);

		String aq_id = request.getParameter("aq_id");
		// 取出问题内容
		String twr_id = "-1";
		String twr_name = "";
		String twr_tel = "";
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Te03_online.class);
		if (aq_id != null && aq_id.length() > 0) {
			queryBuilder.eq("id", new Long(aq_id));
			ResultObject ro = null;
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				Te03_online online = (Te03_online) ro.get(Te03_online.class
						.getName());
				if (online.getAq_id() != null) {
					twr_id = online.getAq_id().toString();
					twr_name = online.getAq_name();
					twr_tel = online.getAq_tel();
				}

				request.setAttribute("online", online);

				// 取出回复数
				String sql_hf = "select up_id,count(*) as hfs from Te03_online where up_id="
						+ online.getId()
						+ " and up_id is not null group by up_id";
				ResultObject ro_hf = queryService.search(sql_hf);
				if (ro_hf.next()) {
					request.setAttribute("hfs", ro_hf.get("hfs"));
				} else {
					request.setAttribute("hfs", "0");
				}

				// 取附件
				List list_fj = new ArrayList();
				String sql_salve = "select id,doc_id,file_name,ext_name,ftp_url from Te01_slave where (doc_id in(select id from Te03_online where up_id="
						+ online.getId()
						+ ") or doc_id="
						+ online.getId()
						+ ") and module_id=9001";
				ResultObject ro_salve = queryService.search(sql_salve);
				while (ro_salve.next()) {
					Map<String, Object> mo = ro_salve.getMap();
					list_fj.add(mo);
				}
				request.setAttribute("list_fj", list_fj);
			}
		}

		// 判断是否有回复
		String online_id = request.getParameter("online_id");
		String title_hf = "";
		String content_hf = "";
		String login_id = "";
		if (online_id != null) {
			// 取出回复内容
			String sql_hf = "select login_id,title,content from Te03_online where id="
					+ online_id;
			ResultObject ro_hf = queryService.search(sql_hf);
			if (ro_hf.next()) {
				title_hf = ro_hf.get("title").toString();
				content_hf = ro_hf.get("content").toString();
				login_id = ro_hf.get("login_id").toString();
			}
			if ("admin".equals(login_id)) {
				String sql_tx = "select mobile_flag,message_flag from Ta27_user_remind where remind_type=3 and user_id="
						+ twr_id;
				ResultObject ro_tx = queryService.search(sql_tx);
				if (ro_tx.next()) {
					if ("1".equals(ro_tx.get("mobile_flag").toString())) {
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

						PHSService phs = new PHSService();
						phs.setSaveService(saveService);
						StringBuffer message_phone = new StringBuffer();
						message_phone.append("发自：" + "r-ms在线提问");
						message_phone.append("\n");
						message_phone.append("用户名：" + user.getName());
						message_phone.append("\n");
						// message_phone.append("时间："+sf.format(new Date()));
						// message_phone.append("\n");
						message_phone.append("标题：" + title_hf);
						message_phone.append("\n");
						message_phone.append("内容：" + content_hf);

						// 手机号码
						phs.setRecvNum(twr_tel);
						phs.setMsg(message_phone.toString());
						String state = phs.sendSMS();
						phs.dxjl(user.getName(), twr_name, "在线提问", content_hf,
								state);// 保存短信记录
					}
					if ("1".equals(ro_tx.get("message_flag").toString())) {
						// 保存数据（短消息）
						Te04_message te04 = new Te04_message();
						te04.setTitle(title_hf);
						te04.setContent(content_hf);
						te04.setReader_id(twr_id);
						te04.setReader_name(twr_name);
						te04.setSend_date(new Date());
						te04.setRead_flag("0");
						te04.setRepeat_flag(new Long(0));
						te04.setSend_flag(new Long(1));
						te04.setSender_id(user.getId());
						te04.setFujian_flag(new Long(0));
						saveService.save(te04);
					}
				}
			}
		}
		// 取出回复信息
		List list_hf = new ArrayList();
		String sql_hf = "select id,aq_name,title,content,role_id,aq_date,login_id from Te03_online where up_id="
				+ aq_id + " order by aq_date asc";
		ResultObject ro_hf = queryService.search(sql_hf);
		while (ro_hf.next()) {
			Map<String, Object> mo = ro_hf.getMap();
			list_hf.add(mo);
		}
		request.setAttribute("list_hf", list_hf);

		return new ModelAndView("/WEB-INF/jsp/online/onlineanswer.jsp");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/OnLineanswer_001.do")
	public void getWxdw(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();

		Map rolesMap = (Map) request.getSession().getAttribute("rolesMap");

		if (rolesMap != null) {
			if (rolesMap.containsKey("10301")) {
				request.setAttribute("delrole", "yes");
			} else {
				request.setAttribute("delrole", "no");
			}
			if (rolesMap.containsKey("10302")) {
				request.setAttribute("ggrole", "yes");
			} else {
				request.setAttribute("ggrole", "no");
			}
		}
		// 获得当前人员所在的地区
		if (request.getParameter("del") != null
				&& !request.getParameter("del").equals("")) {
			saveService.updateByHSql("delete Te03_online where id="
					+ request.getParameter("hf_id"));
		}
		response.setContentType("text/xml");
		out.print("yes");
		out.flush();
		out.close();
	}

	// 疑难解答列表
	@SuppressWarnings("unchecked")
	@RequestMapping("/QAList.do")
	public ModelAndView handleRequest5(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");

		String sortField = StringFormatUtil.format(request
				.getParameter("sortField"), "id");
		String sortType = StringFormatUtil.format(request
				.getParameter("sortType"), "desc");
		// 权限判断
		String role_id = request.getParameter("role_id");
		if (role_id != null) {
			request.setAttribute("role_id", role_id);
		}

		Map rolesMap = (Map) request.getSession().getAttribute("rolesMap");
		if (rolesMap.containsKey("900204")) {
			request.setAttribute("role_del", "yes");
		} else {
			request.setAttribute("role_del", "no");
		}

		// 取当前登录者信息
		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		request.setAttribute("user_id", user.getLogin_id());

		// 删除信息
		String del = request.getParameter("del");
		if (del != null && "yes".equals(del)) {
			String[] del_id = request.getParameterValues("del_id");
			if (del_id != null) {
				for (int i = 0; i < del_id.length; i++) {
					saveService.updateByHSql("delete Te07_qa where id="
							+ del_id[i]);
				}
				// 删除后重新排序
				qaOrderService.delOrder();
			}
		}
		String ques = request.getParameter("ques");
		String sql_qa = "select question,answer,id,ord,cjrq,dj from Te07_qa";
		String sql_qa_num = "select count(*) from Te07_qa";

		if (ques != null && !"".equals(ques)) {
			sql_qa = sql_qa + " where question like '%" + ques + "%'";
			sql_qa_num = sql_qa_num + " where question like '%" + ques + "%'";
			request.setAttribute("ques", ques);
		}
		sql_qa = sql_qa + " order by ord asc";
		sql_qa_num = sql_qa_num + " order by ord asc";

		Integer page = convertUtil.toInteger(request.getParameter("page"));
		Integer pageRowSize = convertUtil.toInteger(request
				.getParameter("pageRowSize"));
		pageRowSize = pageRowSize < 0 ? 12 : pageRowSize;
		int totalPages = 1;
		int totalRows = 1;
		request.setAttribute("page", page);
		request.setAttribute("pageRowSize", pageRowSize);

		// 设置排序
		StringBuffer order = new StringBuffer();
		order.append(",");
		order.append(sortField + " ");
		order.append(sortType);

		// 获得记录条数
		totalRows = convertUtil.toInteger(dao.search(sql_qa_num).get(0));

		if (totalRows > 0) {
			totalPages = totalRows / (pageRowSize + 1) + 1;
		}
		if (page < 1 || page > totalPages) {
			page = 1;
		}
		request.setAttribute("totalRows", totalRows);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("page", page);

		// 取列表数据
		ResultObject ro = queryService.searchByPage(sql_qa + order.toString(),
				page, pageRowSize);
		String question_jx;
		String answer_jx;
		List list_qa = new ArrayList();
		// List<Object[]> docList = new LinkedList<Object[]>();
		while (ro.next()) {
			Map<String, Object> mo = ro.getMap();
			// 截取字符
			String str = convertUtil.toString(ro.get("question"));
			question_jx = subContent(str, 19);
			String str1 = convertUtil.toString(ro.get("answer"));
			answer_jx = subContent(str1, 23);
			mo.put("question_jx", question_jx);
			mo.put("answer_jx", answer_jx);
			list_qa.add(mo);
		}
		request.setAttribute("list_qa", list_qa);

		return new ModelAndView("/WEB-INF/jsp/online/qalist.jsp");
	}

	// 疑难解答列表
	@RequestMapping("/QA.do")
	public ModelAndView handleRequest6(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		String aq_id = request.getParameter("aq_id");
		ResultObject ro = null;
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Te07_qa.class);
		if (aq_id != null && aq_id.length() > 0) {
			queryBuilder.eq("id", new Long(aq_id));
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				Te07_qa te07 = (Te07_qa) ro.get(Te07_qa.class.getName());
				request.setAttribute("Te07_qa", te07);
			}
		} else {
			String sql_qa = "select max(ord)+1 as new_ord from Te07_qa";
			ro = queryService.search(sql_qa);
			if (ro.next()) {
				request.setAttribute("new_ord", ro.get("new_ord"));
			}
		}

		return new ModelAndView("/WEB-INF/jsp/online/qa.jsp");
	}

	// 疑难解答查阅列表
	@RequestMapping("/qa_answer.do")
	public ModelAndView handleRequest7(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		String aq_id = request.getParameter("aq_id");
		// 更新阅读次数
		saveService.updateByHSql("update Te07_qa set dj = dj + 1 where id="
				+ convertUtil.toLong(request.getParameter("aq_id")));

		ResultObject ro = null;
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Te07_qa.class);
		if (aq_id != null && aq_id.length() > 0) {
			queryBuilder.eq("id", new Long(aq_id));
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				Te07_qa te07 = (Te07_qa) ro.get(Te07_qa.class.getName());
				request.setAttribute("Te07_qa", te07);
			}
		}

		return new ModelAndView("/WEB-INF/jsp/online/qa_answer.jsp");
	}

	/**
	 * 在线提问删除ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/onlinedelajax.do")
	public void onlinedelajax(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		String returnurl = "{\"statusCode\":\"200\", \"message\":\"在线提问删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"OnLineList.do?wtlx="+convertUtil.toString(request.getParameter("wtlx"))+"\", \"callbackType\":\"forward\"}";
		// 获取用户对象
		try {
			out = response.getWriter();
			String hsql = "from Te03_online where up_id=" + id;
			List<Te03_online> list = (List<Te03_online>) queryService
					.searchList(hsql);
			list.add((Te03_online) queryService.searchById(Te03_online.class,
					id));
			// 删除
			OperFile of = new OperFile();
			of.setQueryService(queryService);
			of.setSaveService(saveService);

			// String sql = "select id from D20_slave where doc_id = "+id;
			//			
			// while(ro.next()){
			// request.setAttribute("slave_id", ro.get("id"));
			// of.delfile(request, response);
			// dao.update("delete from D20_slave where doc_id =
			// "+(Long)ro.get("id"));
			// }
			for (Te03_online te03_online : list) {
				String sql = "select id,doc_id,file_name,ext_name,ftp_url from Te01_slave where (doc_id in(select id from Te03_online where up_id="
						+ te03_online.getId()
						+ ") or doc_id="
						+ te03_online.getId() + ") and module_id=9001";
				;
				ResultObject ro = queryService.search(sql);
				while (ro.next()) {
					request.setAttribute("slave_id", ro.get("id"));
					of.delfile(request, response);
					dao.update("delete from Te01_slave where doc_id = "
							+ te03_online.getId());
				}
				dao.removeObject(Te03_online.class, te03_online.getId());
			}

			out.print(returnurl);
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.netsky.controller.business.Online", "在线提问删除失败", e);
		}
	}

	/**
	 * 更改父记录是否已处理AJAX
	 * 
	 * 
	 */
	@RequestMapping("/onlinechuliajax.do")
	public void onlinechuliajax(HttpServletRequest request,	HttpServletResponse response) {
		String id = request.getParameter("id");
		String chuli = request.getParameter("chuli");
		String msg = "false";
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String status = "";
			if (chuli.equals("1")) {
				status = "已处理";
			} else if (chuli.equals("0")) {
				status = "未处理";
			}
			if (status.length() != 0 && id.length() != 0) {
				saveService.updateByHSql("update Te03_online set status='"
						+ status + "' where id=" + id);
				msg = "true";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		} finally {
			out.write(msg);
			out.flush();
			out.close();
		}
	}

	/**
	 * 在线提问置顶ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/onlinezdajax.do")
	public void onlinezdajax(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		// 获取用户对象
		try {
			out = response.getWriter();
			Te03_online te03_online = (Te03_online) queryService.searchById(
					Te03_online.class, id);
			Long flag = te03_online.getFlag();
			flag = flag == 0L ? 1L : 0L;
			saveService.updateByHSql("update Te03_online set flag=" + flag
					+ " where id=" + id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"置顶修改成功\", \"navTabId\":\"\", \"forwardUrl\":\"OnLineList.do?wtlx="+convertUtil.toString(request.getParameter("wtlx"))+"\", \"callbackType\":\"forward\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.netsky.controller.business.Online", "置顶修改失败", e);
		}
	}

	/**
	 * 在线提问删除回复AJAX
	 * 
	 * 
	 */

	@RequestMapping("/onlineanswerdelajax.do")
	public void onlineanswerdelajax(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		//response.setContentType("text/xml");
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		Long up_id = convertUtil.toLong(request.getParameter("up_id"), -1L);
		String returnurl = "{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"OnLineanswer.do?aq_id="+up_id+"\", \"callbackType\":\"forward\"}";
		try {
			out = response.getWriter();
			// 删除
			OperFile of = new OperFile();
			of.setQueryService(queryService);
			of.setSaveService(saveService);

			String sql = "select id,doc_id,file_name,ext_name,ftp_url from Te01_slave where doc_id ="
					+ id + " and module_id=9001";
			ResultObject ro = queryService.search(sql);
			while (ro.next()) {
				request.setAttribute("slave_id", ro.get("id"));
				of.delfile(request, response);
				dao.update("delete from Te01_slave where doc_id = " + id);
			}
			dao.removeObject(Te03_online.class, id);

			out.print(returnurl);
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.netsky.controller.business.Online", "删除失败", e);
		}
	}
	

	/**
	 * （在线提问、系统公告）保存ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/onlineAjaxSave.do")
	public void onlineAjaxSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		out = response.getWriter();
		StringBuffer hsql = new StringBuffer("");

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String fromEncode = "GBK";
		String targetEncode = "GBK";

		try {
			if (user != null) {
				Long id = convertUtil.toLong(request.getParameter("Te03_online.ID"), -1L);
				Long up_id = convertUtil.toLong(request.getParameter("Te03_online.UP_ID"), -1L);
				if(up_id == -1){
					up_id = null;
				}
				String title = convertUtil.toString(request.getParameter("Te03_online.TITLE"));
				String content = convertUtil.toString(request.getParameter("Te03_online.CONTENT"));
				String status = convertUtil.toString(request.getParameter("Te03_online.STATUS"));
				Long flag = convertUtil.toLong(request.getParameter("Te03_online.FLAG"));
				
				String login_id = convertUtil.toString(request.getParameter("Te03_online.LOGIN_ID"));
				String aq_name = convertUtil.toString(request.getParameter("Te03_online.AQ_NAME"));
				String aq_tel = convertUtil.toString(request.getParameter("Te03_online.AQ_TEL"));
				Long role_id = convertUtil.toLong(request.getParameter("Te03_online.ROLE_ID"));
				Long aq_id = convertUtil.toLong(request.getParameter("Te03_online.AQ_ID"));
				String aq_ip = convertUtil.toString(request.getParameter("Te03_online.AQ_IP"));
				Long ydcs = convertUtil.toLong(request.getParameter("Te03_online.YDCS"));
				/*
				title = new String(title.getBytes(fromEncode),targetEncode);
				content = new String(content.getBytes(fromEncode),targetEncode);
				status = new String(status.getBytes(fromEncode),targetEncode);
				aq_name = new String(aq_name.getBytes(fromEncode),targetEncode);
				 */
				/**
				 * 保存知识库
				 */
				Te03_online te03 = (Te03_online) dao.getObject(Te03_online.class, id);
				if (te03 == null) {
					te03 = new Te03_online();
				}

				/*
				 * 处理特殊字符
				 */
				content = content.replaceAll("</p><p\\s*[^>]*>", "<br>");// 回车
				content = content.replaceAll("</p>", "");// 分段
				content = content.replaceAll("<p\\s*[^>]*>", "");// 分段
				content = content.replaceAll("<\\s*a\\s+[^>]*>", "");// 链接
				content = content.replaceAll("<a>", "");// 链接
				content = content.replaceAll("</a>", "");// 链接

				te03.setUp_id(up_id);
				te03.setTitle(title);
				te03.setContent(content);
				te03.setStatus(status);
				te03.setFlag(flag);
				te03.setLogin_id(login_id);
				te03.setAq_name(aq_name);
				te03.setAq_id(aq_id);
				te03.setAq_ip(aq_ip);
				te03.setAq_tel(aq_tel);
				te03.setYdcs(ydcs);
				te03.setRole_id(role_id);
				if(id == -1){
					te03.setAq_date(new Date());
				}
				dao.saveObject(te03);

				/**
				 * 保存知识库附件
				 */
				RegExp regExp = new RegExp();
				Vector v_slave_id = regExp
						.pickupAll(
								"<img(\\s+[a-z]+=\"[^\"]*\")*\\s+src=\"/[a-zA-Z]+_[a-zA-Z]+/download.do\\?slave_id=(\\d+)\"(\\s+[a-z]+=\"[^\"]*\")*\\s+/>",
								content, 2);
				if (v_slave_id != null && v_slave_id.size() > 0) {
					for (int i = 0; i < v_slave_id.size(); i++) {
						dao.update("update Te01_slave set doc_id = "
								+ te03.getId() + " where id = "
								+ v_slave_id.get(i));
					}
				}
				
				/**
				 * 处理附件
				 */
				MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
				Map<String, String> ftpConfig = this.getFtpConfig(Mrequest);
				String tableClass = "com.netsky.base.dataObjects.Te01_slave";
				String tableName = null;
				tableName = tableClass.substring(tableClass.lastIndexOf(".") + 1);

				Object o[] = new Object[Mrequest.getParameterValues(tableName + ".ID").length];
				for (int i = 0; i < o.length; i++) {
					o[i] = Class.forName(tableClass).newInstance();
					if (Mrequest.getParameterValues(tableName + ".ID")[i] != null
							&& !"".equals(Mrequest.getParameterValues(tableName + ".ID")[i])) {
						o[i] = queryService.searchById(o[i].getClass(), new Long(Mrequest.getParameterValues(tableName
								+ ".ID")[i]));
					}
					PropertyInject.inject(request, o[i], i, fromEncode, targetEncode);
					/**
					 * 处理主从表信息
					 */
				
					PropertyInject.injectNexus(te03, "ID",o[i], "DOC_ID");
					PropertyInject.injectNexus(te03, "ID",o[i], "PROJECT_ID");
				}
				
					Map<String, String> ftpFolder = this.getFtpFolder(Mrequest);
					Iterator<?> it = Mrequest.getFileNames();
					int i = 0;
					while (it.hasNext() && i < o.length) {
						String fileDispath = (String) it.next();
						MultipartFile file = Mrequest.getFile(fileDispath);
						if (file.getName() != null && !file.getName().equals("")
								&& file.getInputStream().available() > 0) {
							dao.saveObject(o[i]);
							SlaveObject so = (SlaveObject) o[i];
							String fileName = so.getId() + so.getSlaveIdentifier();
							String extends_name = "";
							String folder;
							if (ftpFolder.get(so.getType()) != null) {
								folder = ftpFolder.get(so.getType());
							} else {
								folder = ftpConfig.get("folder");
							}
							if (file.getOriginalFilename().indexOf(".") != -1) {
								extends_name = file.getOriginalFilename().substring(
										file.getOriginalFilename().lastIndexOf("."));
								if (extends_name.length() == extends_name.getBytes().length)
									fileName += extends_name;
							}
							FTPClient ftp = new FTPClient();
							ftp.connect(ftpConfig.get("address"));
							ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
							ftp.enterLocalPassiveMode();
							ftp.setFileType(FTP.BINARY_FILE_TYPE);
							folder += "/" + new SimpleDateFormat("yyyyMM").format(new Date());
							String folders[] = folder.split("/");
							for (int j = 0; j < folders.length; j++) {
								if (!ftp.changeWorkingDirectory(folders[j])) {
									if (!ftp.makeDirectory(folders[j])) {
										throw new Exception("创建目录失败");
									}
									ftp.changeWorkingDirectory(folders[j]);
								}
							}
							ftp.storeFile(fileName, file.getInputStream());
							file.getInputStream().close();

							ftp.disconnect();
							so.setFileName(new String(file.getOriginalFilename().getBytes(fromEncode), targetEncode));
							so.setExt_name(extends_name);
							so.setFilePatch(folder + "/" + fileName);
							dao.saveObject(so);
						}
						i++;
					}

				out.print("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\"OnLineList\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
			} else {
				out.print("{\"statusCode\":\"301\",\"message\":\"会话超时，重新登录\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"statusCode\":\"300\",\"message\":\"保存失败\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
		} 
	}
	

	private Map<String, String> getFtpConfig(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		/**
		 * 通过xml文件获取配置信息
		 */
		String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + ftpConfigFile;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("配置文件不存在，路径：" + filePath);
		}
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			result.put("folder", root.elementText("folder"));
			result.put("address", root.elementText("address"));
			result.put("username", root.elementText("username"));
			result.put("password", root.elementText("password"));
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		return result;
	}

	private Map<String, String> getFtpFolder(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		/**
		 * 通过xml文件获取配置信息
		 */
		String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + ftpConfigFile;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("配置文件不存在，路径：" + filePath);
		}
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			Iterator<?> j;
			for (j = root.elementIterator("typeFolder"); j != null && j.hasNext();) {
				Element element = (Element) j.next();
				result.put(element.elementText("type"), element.elementText("folder"));
			}
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		return result;
	}
}

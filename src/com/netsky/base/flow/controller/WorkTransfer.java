package com.netsky.base.flow.controller;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 岗位交接
 * @class name:com.netsky.base.flow.controller.WorkTransfer
 * @author Administrator Apr 18, 2010
 */
@Controller
public class WorkTransfer {
	
	/**
	 * 用户对象
	 */
	Ta03_user user;
	
	/**
	 * 数据库操作
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;
	
	/**
	 * 选择交接人员
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/flow/workTransfer.do")
	public ModelAndView transferHandle(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String viewName ="/WEB-INF/jsp/flow/gwjj.jsp";
		try{
			request.setCharacterEncoding("GBK");
			response.setCharacterEncoding("GBK");
			
			String login_id = request.getParameter("login_id");
			
			//如果Login_id 为空，说明是直接打开交接页面。
			if(login_id == null){
				return new ModelAndView(viewName);
			}
			
			/**
			 * 如果login_id 非空，则通过login_id 获取以一下信息
			 * 1、对应人员 from_user
			 * 2、from_user 对应的顶级群组
			 * 3、from_user 拥有的岗位
			 */
			ModelMap modelMap = new ModelMap();
			StringBuffer hsql = new StringBuffer();
			Ta03_user from_user = null;
			List<Ta02_station> sta_list = new LinkedList();
			
			Long group_id = null;
			hsql.append("from Ta03_user where login_id = ? ");
			List tmpList = queryService.searchList(hsql.toString(), new Object[]{login_id});
			if(tmpList.size() == 1){
				from_user = (Ta03_user)tmpList.get(0);
				tmpList.clear();
				//获得当前交接人员所在顶级群组
				hsql.delete(0,hsql.length());
				hsql.append("select ta05.id from Ta05_group ta05,Ta14_group_user ta14");
				hsql.append(" where ta05.id = ta14.group_id and ta05.up_id = 0 and ta14.user_id = ?");
				tmpList = queryService.searchList(hsql.toString(), new Object[]{from_user.getId()});
				if(tmpList.size() ==1){
					group_id = (Long)tmpList.get(0);
					tmpList.clear();
				} 
				
				//获得人员岗位列表
				hsql.delete(0,hsql.length());
				hsql.append("select ta02 from Ta02_station ta02,Ta11_sta_user ta11");
				hsql.append(" where ta02.id = ta11.station_id and ta11.user_id = ? order by ta02.seq");
				sta_list = (List<Ta02_station>)queryService.searchList(hsql.toString(), new Object[]{from_user.getId()});
			}
			
			if(from_user == null ||sta_list == null||sta_list.size() == 0){
				String warnMessage ="没有找到人员或找到的人员没有岗位，请重新录入!";
				modelMap.put("warnMessage", warnMessage);
			} else {
				modelMap.put("from_user", from_user);
				modelMap.put("group_id", group_id);
				modelMap.put("sta_list", sta_list);
			}
			return new ModelAndView(viewName,modelMap);
		} catch(Exception e){
			e.printStackTrace();
			return exceptionService.exceptionControl(this.getClass().getName(), "工作岗位交接出错", e);
		}
	}
	
	/**
	 * 选择目标人员
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/flow/workTransfer_getUsers.do")
	public void getUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		try{
			String  sta_ids = request.getParameter("sta_ids");
			String group_id = request.getParameter("group_id");
			String from_id = request.getParameter("from_id");
			
			StringBuffer hsql = new StringBuffer();
			List<Ta03_user> user_list = new LinkedList();
			if(sta_ids != null && !"".equals(sta_ids)){
				if(group_id != null && !"".equals(group_id)){
					hsql.append("select ta03 from Ta03_user ta03,Ta11_sta_user ta11,Ta14_group_user ta14");
					hsql.append(" where ta03.id = ta11.user_id and ta03.id = ta14.user_id");
					hsql.append(" and ta14.group_id = ");
					hsql.append(group_id);
					hsql.append(" and ta11.station_id in (");
					hsql.append(sta_ids);
					hsql.append(")");
					hsql.append(" and ta03.id != ");
					hsql.append(from_id);					
				} else {
					hsql.append("select ta03 from Ta03_user ta03,Ta11_sta_user ta11");
					hsql.append(" where ta03.id = ta11.user_id ");
					hsql.append(" and ta11.station_id in (");
					hsql.append(sta_ids);
					hsql.append(")");
					hsql.append(" and ta03.id != ");
					hsql.append(from_id);	
				}

				
				user_list = (List<Ta03_user>)queryService.searchList(hsql.toString());					
			}
			//
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			PrintWriter out = response.getWriter();
			out.println("<response>");
			for(Ta03_user user :user_list){
				out.println("<user>");
				out.println("<user_id>" + user.getId() +"</user_id>");
				out.println("<user_name>[" + user.getLogin_id() +"]" + user.getName() +"</user_name>");			
				out.println("</user>");	
			}
			out.println("</response>");
			out.flush();
			out.close();
			
			
		} catch(Exception e){
			exceptionService.exceptionControl(this.getClass().getName(), "工作岗位交接出错", e);
		}
	}
	
	/**
	 * 工作交接
	 * @param request
	 * @param response
	 * @param session void
	 */
	@RequestMapping("/flow/workTransferDo.do")
	public ModelAndView transDo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		/**
		 * 工程交接，岗位删除
		 */
		try {
			String[] sta_ids = request.getParameterValues("sta_ids");
			String from_id = request.getParameter("from_id");
			String to_id = request.getParameter("to_id");

			StringBuffer hsql = new StringBuffer();
			ModelMap modelMap =new ModelMap();
			if (sta_ids != null && from_id != null && to_id != null) {
				String tmpSta_ids = "";
				for (String id : sta_ids) {
					tmpSta_ids += "," + id;
				}
				tmpSta_ids = tmpSta_ids.substring(1);
				
				//办结文档交接
				hsql.append("update Tb15_docflow tb15 set huser_id = ");
				hsql.append(to_id);
				hsql.append(" where tb15.user_id  = ");
				hsql.append(from_id);
				hsql.append(" and tb15.node_id in(");
				hsql.append("select node_id from Ta13_sta_node ta13 where station_id in (");
				hsql.append(tmpSta_ids);
				hsql.append("))");
				saveService.updateByHSql(hsql.toString());
				
				//未办结文档
				hsql.delete(0,hsql.length());
				hsql.append("update Tb15_docflow tb15 set huser_id = ");
				hsql.append(to_id);
				hsql.append(",user_id=");
				hsql.append(to_id);
				hsql.append(" where tb15.user_id  = ");
				hsql.append(from_id);
				hsql.append(" and tb15.node_id in(");
				hsql.append("select node_id from Ta13_sta_node ta13 where station_id in (");
				hsql.append(tmpSta_ids);
				hsql.append("))");
				saveService.updateByHSql(hsql.toString());
				
				//删除岗位
				hsql.delete(0,hsql.length());
				hsql.append("delete from Ta11_sta_user ta11 ");
				hsql.append(" where user_id = ");
				hsql.append(from_id);
				hsql.append(" and station_id in(");
				hsql.append(tmpSta_ids);
				hsql.append(")");
				saveService.updateByHSql(hsql.toString());
				String warnMessage ="岗位交接成功！";
				modelMap.put("warnMessage", warnMessage);
				
				//记录日志
				String logMessage = request.getParameter("stat_names")+" 岗位上" + request.getParameter("to_name") +"的工作交接予" + request.getParameter("to_name");
				
				
			}
			return new ModelAndView("workTransfer.do",modelMap);

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionService.exceptionControl(this.getClass().getName(), "工作岗位交接出错", e);
		}
	}
}

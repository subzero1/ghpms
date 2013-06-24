package com.netsky.base.flow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta05_group;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.component.Accept;
import com.netsky.base.flow.component.Archive;
import com.netsky.base.flow.component.Approve;
import com.netsky.base.flow.component.ApproveComplete;
import com.netsky.base.flow.component.Callback;
import com.netsky.base.flow.component.CompleteFlow;
import com.netsky.base.flow.component.DocDelete;
import com.netsky.base.flow.component.FlowBussines;
import com.netsky.base.flow.component.HandOver;
import com.netsky.base.flow.component.InterruptMeeting;
import com.netsky.base.flow.component.MakeCopy;
import com.netsky.base.flow.component.OpenForm;
import com.netsky.base.flow.component.Send;
import com.netsky.base.flow.component.Turnback;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.utils.convertUtil;

/**
 * @description:
 * 流程对应的controller 处理所有流程相关的请求
 * @class name:com.netsky.base.flow.controller.FlowController
 * @author wind Jan 13, 2010
 */
@Controller
public class FlowController {
	/**
	 * 受理，取消受理
	 */
	@Autowired
	Accept accept;
	
	/**
	 * 办结
	 */	
	@Autowired
	Archive archive;
	
	/**
	 * 审批 
	 */		
	@Autowired
	Approve approve;
	
	/**
	 * 审结
	 */
	@Autowired
	ApproveComplete approveComplete;
	
	/**
	 * 收回文档
	 */
	@Autowired
	Callback callback;
	
	/**
	 * 完工
	 */
	@Autowired
	CompleteFlow completeFlow;
	
	/**
	 * 打开表单
	 */
	@Autowired
	OpenForm openForm;
	
	/**
	 * 发送
	 */
	@Autowired
	Send send;
	
	/**
	 * 抄送
	 */
	@Autowired
	MakeCopy makeCopy;
	
	/**
	 * 回退
	 */
	@Autowired
	Turnback turnback;
	
	/**
	 * 回退
	 */
	@Autowired
	HandOver handOver;	
	
	/**
	 * 取消会审，结束会审
	 */
	@Autowired
	InterruptMeeting interruptMeeting;		
	
	/**
	 * 流程业务类
	 */
	@Autowired
	FlowBussines flowBussines;
	
	/**
	 * 表单删除
	 */
	@Autowired
	DocDelete docDelete;
	
	/**
	 * 异常处理
	 */
	@Autowired
	ExceptionService exceptionService;
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	 
	
	private Map<String, Object> paraMap = new HashMap();
	
	/**
	 * 文档受理
	 * method:acceptHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/accept.do")
	public String acceptHandler(HttpServletRequest request, HttpServletResponse response){
		try {
			paraMap.clear();
			MapUtil.load(paraMap, request);
			return accept.handleRequest(paraMap);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:文档受理，取消受理失败", e).getViewName();
		}
	}
	
	/**
	 * 文档办结
	 * method:archiveHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/archive.do")
	public void archiveHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			paraMap.clear();
			MapUtil.load(paraMap, request);
			if (archive.handleRequest(paraMap).equals(FlowConstant.VIEW_CLOSE)) {
				response.getOutputStream().print("closeWindow");
			} else {
				response.getOutputStream().print("reloadWindow");
			}
		} catch (Exception e) {
			exceptionService.exceptionControl(this.getClass().getName(), "流程:文档审结失败", e);
		}
	}
	
	
	/**
	 * 文档审结
	 * method:approveCompleteHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/approveComplete.do")
	public void approveCompleteHandler(HttpServletRequest request, HttpServletResponse response) {
		try{
			paraMap.clear();
			MapUtil.load(paraMap,request);
			//return approveComplete.handleRequest(paraMap);
			String return_view = approveComplete.handleRequest(paraMap);
			if(FlowConstant.VIEW_CLOSE.equals(return_view)){
				response.getOutputStream().print("closeWindow");
			}else if(FlowConstant.VIEW_TRUNBACK.equals(return_view)){
				response.getOutputStream().print("reloadWindow");
			}else{
				response.getOutputStream().print("reloadWindow");
			}		
		} catch (Exception e){
			 exceptionService.exceptionControl(this.getClass().getName(), "流程:文档审结失败", e);
		}

	}


	/**
	 * 文档审批
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping("/approve.do")
	public ModelAndView approveHandler(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		try {
			paraMap.clear();
			MapUtil.load(paraMap,request);
			ModelAndView mv = approve.handleRequest(paraMap);
			if("success".equals(mv.getModelMap().get("approve_return"))){
				this.printJson(request, response, "200", "审批操作成功");
			} else {
				return mv;
			}
			
		} catch(Exception e){
			log.error(e);
			printJson(request, response, "301", "审批操作失败");
		}	
		return  null;
	}

	/**
	 * 收回文档
	 * method:callbackHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/callback.do")
	public ModelAndView callbackHandler(HttpServletRequest request, HttpServletResponse response){
		try {
			paraMap.clear();
			MapUtil.load(paraMap,request);
			return callback.handleRequest(paraMap);
		} catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:收回文档失败", e);
		}
	}
	
	/**
	 * 终止会审
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/closeMeeting.do")
	public ModelAndView closeMeetingHandler(HttpServletRequest request, HttpServletResponse response) {
		try {
			paraMap.clear();
			MapUtil.load(paraMap,request);
			return interruptMeeting.closeHandle(paraMap);
		} catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:终止会审失败", e);
		}		
	}	
	
	/**
	 * 取消会审
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/interruptMeeting.do")
	public ModelAndView interruptMeetingHandler(HttpServletRequest request, HttpServletResponse response) {

		try {
			paraMap.clear();
			MapUtil.load(paraMap,request);
			return interruptMeeting.interruputHandle(paraMap);
		} catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:取消会审失败", e);
		}			
	}	
	
	/**
	 * 完工，或中止
	 * method:completeFlowHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/completeFlow.do")
	public ModelAndView completeFlowHandler(HttpServletRequest request, HttpServletResponse response) {
		try {
			paraMap.clear();
			MapUtil.load(paraMap,request);
			return completeFlow.handleRequest(paraMap);
		} catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:完工，或中止失败", e);
		}			
	}
	
	/**
	 * 文档发送
	 * method:sendHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/send.do")
	public void sendHandler(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			ModelAndView modelAndView = null;
			PrintWriter out = response.getWriter();   
			paraMap.clear();
			MapUtil.load(paraMap,request);
			modelAndView = send.handleRequest(paraMap);
			response.setContentType("text/xml");
			out.println("<response>");
			//发送错误
			if(modelAndView == null){
				out.println("<back_flag>1</back_flag>");
				out.println("<show_msg>");
				out.println("发送失败，请与开发人员联系!");
				out.println("</show_msg>");
			//目标节点一人，直接发送成功
			}else if(modelAndView.getViewName().equals(FlowConstant.CTR_OPENFORM)){
				out.println("<back_flag>2</back_flag>");
				out.println("<show_msg>");
				out.println(modelAndView.getModelMap().get("warnMessage"));
				out.println("</show_msg>");
				
				//按扭列表
				List<Button> buttonList = flowBussines.listButtons(paraMap);
				if(buttonList.size() > 0){
					Button m_button = (Button)buttonList.get(0);
//					if("收回文档".equals(m_button.getName())){
//						out.println("<todo>closewindow</todo>");	
//					}else {
						out.println("<todo>listButton</todo>");
						out.println("<buttonList>");
						for(Button button :buttonList){
							out.println("<button>");
							out.println("<button_name>"+button.getName()+"</button_name>");	
							out.println("<button_comment><![CDATA["+button.getComment()+"]]></button_comment>");	
							out.println("<button_url><![CDATA["+button.getUrl()+"]]></button_url>");					
							out.println("</button>");	
						}	
						out.println("</buttonList>");
//					}
				}
			//目标节点多个人需，选择发送
			} else if(modelAndView.getViewName().equals(FlowConstant.VIEW_SEND)){
				ModelMap modelMap = modelAndView.getModelMap();
				//**********************************发送用的隐藏域*********************/
				out.println("<back_flag>3</back_flag>");
				out.println("<show_msg>");
				out.println(modelAndView.getModelMap().get("warnMessage"));
				out.println("</show_msg>");
				
				out.println("<hidden-inputs>");			
				out.println("<item>");
				out.println("<item_name>relation_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "relation_id")+"</item_value>");		
				out.println("</item>");
				
				out.println("<item>");
				out.println("<item_name>doc_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "doc_id")+"</item_value>");			
				out.println("</item>");
				
				out.println("<item>");
				out.println("<item_name>project_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "project_id")+"</item_value>");			
				out.println("</item>");		
				
				out.println("<item>");
				out.println("<item_name>module_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "module_id")+"</item_value>");			
				out.println("</item>");	
				
				out.println("<item>");
				out.println("<item_name>node_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "node_id")+"</item_value>");			
				out.println("</item>");		
				
				
				out.println("<item>");
				out.println("<item_name>opernode_id</item_name>");
				out.println("<item_value>"+MapUtil.getLong(paraMap, "opernode_id")+"</item_value>");			
				out.println("</item>");				
				out.println("</hidden-inputs>");	
				
				//**********************************发送  人员或群组*********************/
				if(modelMap.containsKey("to_user")){
					List<Ta03_user> userList =(List<Ta03_user>)modelMap.get("to_user");
					out.println("<to_user>");
					
					for(Ta03_user user :userList){
						out.println("<option>");
						out.println("<id>"+user.getId()+"</id>");
						
						String remark = "";
						if(user.getRemark() != null ){
							for ( int i = 3 - user.getName().length() ;i >-1;i--){
								remark += "  ";
							}
							remark += "[" +user.getRemark() +"]"; ;
						}
						
						//out.println("<name>"+user.getLogin_id()+" " + user.getName()  + remark + "</name>");		
						out.println("<name>"+ user.getName()  + remark + "</name>");
						out.println("</option>");	
				
					}
					
					out.println("</to_user>");	
				}else {
					List<Ta05_group> groupList = (List<Ta05_group>)modelMap.get("to_group");
					out.println("<to_group>");
					
					for(Ta05_group group :groupList){
						out.println("<option>");
						out.println("<id>"+group.getId()+"</id>");	
						out.println("<name>"+group.getName()+"</name>");							
						out.println("</option>");	
				
					}
					out.println("</to_group>");	
				}
				//**************************人员是否可多选**************************//
				out.println("<select_type>"+modelMap.get("selectType")+"</select_type>");
				
				out.println("<formAct>");
				out.println("<formAction>send.do</formAction>");	
				out.println("</formAct>");
			}
			out.println("</response>");
			out.flush();
			out.close();
		} catch(Exception e){
			exceptionService.exceptionControl(this.getClass().getName(), "流程:文档发送失败", e);
		}	
	}
	
	/**
	 * 文档修改
	 * method:ModifyHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/modify.do")
	public ModelAndView modifyHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		paraMap.clear();
		MapUtil.load(paraMap,request);
		return null;
	}	
	
	/**
	 * 回退文档
	 * method:trunbackHandler
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/turnback.do")
	public void turnbackHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			ModelAndView modelAndView = null;
			PrintWriter out = response.getWriter();
			paraMap.clear();
			MapUtil.load(paraMap, request);
			modelAndView = turnback.handleRequest(paraMap);
			response.setContentType("text/xml");
			out.println("<response>");
			if (modelAndView == null) {
				out.println("<back_flag>1</back_flag>");
				out.println("<show_msg>");
				out.println("发送失败，请与开发人员联系!");
				out.println("</show_msg>");
			} else if (modelAndView.getViewName().equals(FlowConstant.CTR_OPENFORM)) {
				out.println("<back_flag>2</back_flag>");
				out.println("<show_msg>");
				out.println(modelAndView.getModelMap().get("warnMessage"));
				out.println("</show_msg>");

				// 按扭列表
				List<Button> buttonList = flowBussines.listButtons(paraMap);
				if (buttonList.size() > 0) {
					out.println("<buttonList>");
					for (Button button : buttonList) {
						out.println("<button>");
						out.println("<button_name>" + button.getName() + "</button_name>");
						out.println("<button_comment><![CDATA[" + button.getComment() + "]]></button_comment>");
						out.println("<button_url><![CDATA[" + button.getUrl() + "]]></button_url>");
						out.println("</button>");
					}
					out.println("</buttonList>");
				}

			} else if (modelAndView.getViewName().equals(FlowConstant.VIEW_CLOSE)) {
				out.println("<back_flag>3</back_flag>");
				out.println("<show_msg>");
				out.println(modelAndView.getModelMap().get("warnMessage"));
				out.println("</show_msg>");
				
				if (modelAndView.getModelMap().get("warnMessage") == null) {
					out.println("<todo>");
					out.print("closeWindow");
					out.println("</todo>");
				}
			} else if (modelAndView.getViewName().equals(FlowConstant.VIEW_TRUNBACK)) {
				ModelMap modelMap = modelAndView.getModelMap();
				// **********************************回退用的隐藏域*********************/
				out.println("<back_flag>4</back_flag>");

				out.println("<hidden-inputs>");

				out.println("<item>");
				out.println("<item_name>opernode_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "opernode_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>doc_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "doc_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>project_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "project_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>module_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "module_id") + "</item_value>");
				out.println("</item>");
				out.println("<item>");
				out.println("<item_name>flow_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(modelMap, "flow_id") + "</item_value>");
				out.println("</item>");

				out.println("</hidden-inputs>");
				// **************************回退的节点**************************//
				out.println("<nodes>");
				out.println("<rollto_nodes>" + MapUtil.getString(modelMap, "rollto_nodes") + "</rollto_nodes>");
				out.println("</nodes>");

				out.println("<formAct>");
				out.println("<formAction>turnback.do</formAction>");
				out.println("</formAct>");
			}
			out.println("</response>");
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("xx8");
			 exceptionService.exceptionControl(this.getClass().getName(), "流程:回退文档失败", e);
		}
	}
	
	/**
	 * 转交文档
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping("/handOver.do")
	public void handOverHandler(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			ModelAndView modelAndView = null;
			PrintWriter out = response.getWriter();
			paraMap.clear();
			MapUtil.load(paraMap, request);
			modelAndView = handOver.handleRequest(paraMap);
			response.setContentType("text/xml");
			out.println("<response>");
			if (modelAndView == null) {
				out.println("<back_flag>1</back_flag>");
				out.println("<show_msg>");
				out.println("发送失败，请与开发人员联系");
				out.println("</show_msg>");

			} else if (modelAndView.getViewName().equals(FlowConstant.CTR_OPENFORM)) {
				out.println("<back_flag>2</back_flag>");
				out.println("<show_msg>");
				out.println(modelAndView.getModelMap().get("warnMessage") );
				out.println("</show_msg>");

				// 按扭列表
				List<Button> buttonList = flowBussines.listButtons(paraMap);
				if (buttonList.size() > 0) {
					out.println("<buttonList>");
					for (Button button : buttonList) {
						out.println("<button>");
						out.println("<button_name>" + button.getName() + "</button_name>");
						out.println("<button_comment><![CDATA[" + button.getComment() + "]]></button_comment>");
						out.println("<button_url><![CDATA[" + button.getUrl() + "]]></button_url>");
						out.println("</button>");
					}
					out.println("</buttonList>");
				}

			} else if (modelAndView.getViewName().equals(FlowConstant.VIEW_SEND)) {
				ModelMap modelMap = modelAndView.getModelMap();
				out.println("<back_flag>3</back_flag>");
				// **********************************发送用的隐藏域*********************/
				out.println("<hidden-inputs>");

				out.println("<item>");
				out.println("<item_name>relation_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "relation_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>doc_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "doc_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>project_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "project_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>module_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "module_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>node_id</item_name>");
				out.println("<item_value>" + MapUtil.getLong(paraMap, "node_id") + "</item_value>");
				out.println("</item>");

				out.println("<item>");
				out.println("<item_name>opernode_id</item_name>");
				out.println("<value>" + MapUtil.getLong(paraMap, "opernode_id") + "</value>");
				out.println("</item>");

				out.println("</hidden-inputs>");

				// **********************************移交 人员*********************/
				if (modelMap.containsKey("to_user")) {
					List<Ta03_user> userList = (List<Ta03_user>) modelMap.get("to_user");
					out.println("<to_user>");

					for (Ta03_user user : userList) {
						out.println("<option>");
						out.println("<option_id>" + user.getId() + "</option_id>");
						;
						out.println("<option_name>" + user.getName() + "</option_name>");
						out.println("</option>");
					}
					out.println("</to_user>");
				}
				// **************************人员是否可多选**************************//
				out.println("<select_type>select-one</select_type>");

				out.println("<formAct>");
				out.println("<formAction>handOver.do</formAction>");
				out.println("</formAct>");
			}
			out.println("</response>");
			out.flush();
			out.close();
		} catch (Exception e) {
			exceptionService.exceptionControl(this.getClass().getName(), "流程:文档发送失败", e);
		}
	}
	
	/**
	 * 打开表单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 */

	@RequestMapping("/openForm.do")
	public String openFormHandler(HttpServletRequest request, HttpServletResponse response) {
		try {
			paraMap.clear();
			MapUtil.load(paraMap, request);
			return openForm.handleRequest(paraMap);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "流程:打开表单失败", e).getViewName();
		}
	}
	
	/**
	 * 删除表单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping("/modifyFlow.do")
	public void modifyFlowHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text");
		java.io.PrintWriter out = response.getWriter();
		try{
			paraMap.clear();
			MapUtil.load(paraMap,request);
			//return approveComplete.handleRequest(paraMap);
			String return_view = flowBussines.modifyFlow(paraMap);
			out.print(return_view);
		} catch (Exception e){
			exceptionService.exceptionControl(this.getClass().getName(), "流程:删除表单失败", e);
			out.print("流程调整失败");
		}		
		out.flush();
		out.close();
	}	
	
	@RequestMapping("/deleteForm.do")
	public void deleteFormHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			paraMap.clear();
			MapUtil.load(paraMap,request);
			//return approveComplete.handleRequest(paraMap);
			String return_view = docDelete.handleRequest(paraMap);
			if(FlowConstant.VIEW_CLOSE.equals(return_view)){
				response.getOutputStream().print("closeWindow");
			}else {
				response.getOutputStream().print("reloadWindow");
			}
		} catch (Exception e){
			exceptionService.exceptionControl(this.getClass().getName(), "流程:删除表单失败", e);
			response.getOutputStream().print("reloadWindow");
		}		
	}
	
	private void printJson(HttpServletRequest request, HttpServletResponse response, String statusCode, String _message)
			throws IOException {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String message = convertUtil.toString(request.getParameter("_message"));

		if (!"".equals(convertUtil.toString(_message))) {
			message = _message;
		} else {
			if ("200".equals(statusCode)) {
				message = message + "成功";
			} else if ("301".equals(statusCode)) {
				message = message + "失败";
			} else {
				message = message + "失败";
			}
		}
		String navTabId = convertUtil.toString(request.getParameter("_navTabId"));
		String forwardUrl = convertUtil.toString(request.getParameter("_forwardUrl"));
		String callbackType = convertUtil.toString(request.getParameter("_callbackType"));
		response.getWriter().print(
				"{\"statusCode\":\"" + statusCode + "\", \"message\":\"" + message + "\", \"navTabId\":\"" + navTabId
						+ "\", \"forwardUrl\":\"" + forwardUrl + "\", \"callbackType\":\"" + callbackType + "\"}");
	}
}

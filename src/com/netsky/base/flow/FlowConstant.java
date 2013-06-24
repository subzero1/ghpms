package com.netsky.base.flow;

public class FlowConstant {
	//***************************视图***********************/
	/**
	 * 关闭窗口对应的View
	 */
	public final static String VIEW_CLOSE = "WEB-INF/jsp/flow/close.html";
	
	/**
	 * 审批VIEW
	 */
	public final static String VIEW_APPROVE = "WEB-INF/jsp/flow/approve.jsp";
	
	/**
	 * 发送选择人员、群组选择VIEW
	 */
	public final static String VIEW_SEND = "view_send";
	
	/**
	 * 回退选择回退节点 view
	 */
	public final static String VIEW_TRUNBACK = "view_trunback";
	
	//***************************控制器***********************/
	/**
	 * 发送选择打开表单前置 CONTROLLER
	 */
	public final static String CTR_OPENFORM_PRE = "openForm.do";
	
	/**
	 * 发送选择打开表单 CONTROLLER
	 */
	public final static String CTR_OPENFORM = "flowForm.do";
	
	
	//***************************审批意见 tb15.approve_result,tb17.check_result***********************/
	/**
	 * 审批：4 同意。
	 */
	public final static Integer CHECK_RESULT_OK = 4;
	
	/**
	 * 审批：5 修改（审批意见，或回退意见）
	 */
	public final static Integer CHECK_RESULT_MODIFY = 5;
	
	/**
	 * 审批：6 不同意。
	 */
	public final static Integer CHECK_RESULT_NO = 6;
	
	/**
	 * 审批： 7 暂缓
	 */
	public final static Integer CHECK_RESULT_DEFER = 7;	
	
	/**
	 * 审批： 9 不具条件
	 */
	public final static Integer CHECK_RESULT_BJTJ = 9;		
	
	
	//***************************节点类型tb02.node_type***********************/
	/**
	 * 审批型：1
	 */
	public final static Integer NODE_TYPE_CHECK = 1;
	/**
	 * 不需要审批型：2
	 */
	public final static Integer NODE_TYPE_NO_CHECK = 2;
	/**
	 * 多人会签型：3
	 */
	public final static Integer NODE_TYPE_MORE_CHECK =3;
	
	//***************************节点、节点文档状态tb12.node_status,tb15.doc_status***********************/
	/**
	 * 节点文档状态：待办 0
	 */
	public final static Integer NODE_STATUS_NEED = 0;
	/**
	 * 节点文档状态：新建 1
	 */
	public final static Integer NODE_STATUS_NEW = 1;
	/**
	 * 节点文档状态：在办 2
	 */
	public final static Integer NODE_STATUS_WORK = 2;
	/**
	 * 节点文档状态：发送 3
	 */
	public final static Integer NODE_STATUS_SEND = 3;
	/**
	 * 节点文档状态：回复同意 4
	 */
	public final static Integer NODE_STATUS_RETURN_OK = 4;
	/**
	 * 节点文档状态：回复修改 5
	 */
	public final static Integer NODE_STATUS_RETURN_MODIFY = 5;
	/**
	 * 节点文档状态：回复接收 6
	 */
	public final static Integer NODE_STATUS_RETURN_ACCEPT = 6;
	/**
	 * 节点文档状态：回复暂缓 7
	 */
	public final static Integer NODE_STATUS_RETURN_DEFER = 7;
	/**
	 * 节点文档状态：办结 8
	 */
	public final static Integer NODE_STATUS_OFF = 8;
	
	/**
	 *节点文档状态：不具条件 9
	 */
	public final static Integer NODE_STATUS_BJTJ = 9;
	
	//**************************节点扩展定义********************************************/
	
	/**
	 * 节点扩展定义：不具条件
	 * 节点中包含该串时，显示，不具条件或已具条件。 
	 */
	public final static String NODE_EXT_BJTJ ="[不具条件]";
	
	/**
	 * 节点扩展定义：不同意
	 * 节点中包含该串时，显示，"不同意"。 
	 */
	public final static String NODE_EXT_SUSPEND  ="[挂起]";
	
	/**
	 * 节点扩展定义：传阅
	 * 节点中包含该串时，显示，"已阅"。 
	 */
	public final static String NODE_EXT_CY  ="[传阅]";
	
	//***************************发送接收人员范围 tb03.targetScope***********************/
	/**
	 * 所有有权限的人员
	 */
	public final static String SEND_SCOPE_ALL = "1";
	
	/**
	 * 群组下有权限的人员
	 */
	public final static String SEND_SCOPE_GROUP = "2";
	
	/**
	 * 当前群组下有权限的人员
	 */
	public final static String SEND_SCOPE_CUR_GROUP = "3";
	
	//***************************发送关系类别***********************************/
	/**
	 * 发送类别：上报
	 */
	public final static String SEND_TYPE_REPORT ="report";
	
	/**
	 * 发送类别：发送 
	 */
	public final static String SEND_TYPE_SEND = "send";
	
	/**
	 * 发送类别：通知
	 */
	public final static String SEND_TYPE_INFO = "info";
	
	//***************************流程状态 tb11.status***********************/
	/**
	 * 正常运行
	 */
	public final static String FLOW_STATUS_RUN = "1";
	
	/**
	 * 关闭的流程。
	 */
	public final static String FLOW_STATUS_SHUTDOWN = "2";
	
	/**
	 * 终止流程
	 */
	public final static String FLOW_STATUS_SUSPEND  = "3";
	
	//***************************流程分类 tb01.type***********************/
	/**
	 * 流转进程中只能出现一次，且办结
	 */
	public final static Integer FLOW_TYPE_ONLY_ARCHIVE = 1;
	
	/**
	 * 流转进程中只能出现一次，且不办结
	 */
	public final static Integer FLOW_TYPE_ONLY = 2;	
	/**
	 * 流转进程中可以出现多次,同一时期，只能存在一个子流程运行
	 */
	public final static Integer FLOW_TYPE_ASYN_MORE = 3;
	
	/**
	 * 流转进程中可以出现多次,同一时期，可以存在多个子流程一起运行
	 */
	public final static Integer FLOW_TYPE_SYN_MORE = 4;		
	
	//***************************tb14事件类别***********************/
	/**
	 * tb14事件类别-新建:1
	 */
	public final static Integer EVEN_TYPE_NEW = 1;
	
	/**
	 * tb14事件类别-发送，收回:2
	 */
	public final static Integer EVEN_TYPE_SEND = 2;	
	
	/**
	 * tb14事件类别-受理、取消受理：3
	 */
	public final static Integer EVEN_TYPE_ACCEPT = 3;	
	
	/**
	 * tb14事件类别-审批：7
	 */
	public final static Integer EVEN_TYPE_APPROVE = 7;		
	
	/**
	 * tb14事件类别-审结：4
	 */
	public final static Integer EVEN_TYPE_APPROVECOMPLETE = 4;	
	
	/**
	 * tb14事件类别-办结：5
	 */
	public final static Integer EVEN_TYPE_ARCHIVE = 5;	
	
	
	/**
	 * tb14事件类别-其它：6
	 * <br>在此主要指show_button,show_action
	 */
	public final static Integer EVEN_TYPE_OTHER = 6;
	
}

package com.netsky.base.controller.personalization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.PHSService;
/**
 * 个人信息修改
 * @author lee.xiangyu
 * @create 2010-04-12
 */
/**
 * @author lixiangyu
 *
 */
/**
 * @author lixiangyu
 *
 */
@Controller
public class GetPassword{
		/**
		 * 查询服务
		 */
		@Autowired
		private QueryService queryService;
		
		/**
		 * 更新服务
		 */
		@Autowired
		private SaveService saveService;
		
		
		@RequestMapping("/setGetPassowrdInfo.do")
		public String setGetPassowrdInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
			
			return "/WEB-INF/jsp/personalization/getPassword.jsp";
		}
		
		@RequestMapping("/getPassowrd.do")
		public void getPassowrd(HttpServletRequest request,HttpServletResponse response) throws Exception {
 			response.setCharacterEncoding(request.getCharacterEncoding());
			QueryBuilder queryBuilder = null;
			String login_id = null;
			ResultObject ro = null;
			StringBuffer info = new StringBuffer("");
			try{
				login_id = convertUtil.toString(request.getParameter("login_id"),"xxxx");
				Class clazz = Ta03_user.class;
				queryBuilder = new HibernateQueryBuilder(clazz);
				queryBuilder.eq("login_id", login_id);
				ro = queryService.search(queryBuilder);
				if(ro.next()){
					/**
					 * 获得用户信息
					 */
					Ta03_user ta03 = (Ta03_user)ro.get(Ta03_user.class.getName());
					String name = ta03.getName();
					String passwd = ta03.getPasswd();
					Long useflag = ta03.getUseflag();
					String tel = ta03.getMobile_tel();
					
					if(tel == null || tel.length() < 11){
						info.append("电话登记有误，无法获得密码，请与管理员联系！");
					}
					else{
						/**
						 * 设置短信内容，发送短信
						 */
						String phonenums[] = tel.split(";");
						StringBuffer message_phone = new StringBuffer();
						message_phone.append("发自："+"r-ms【找回密码】");
						message_phone.append("\n"); 
						message_phone.append("用户名：");
						message_phone.append(name);
						message_phone.append("[");
						message_phone.append(login_id);
						message_phone.append("]");
						message_phone.append("\n"); 
						message_phone.append("密码：");
						message_phone.append(passwd);
						message_phone.append("\n"); 
						message_phone.append("状态：");
						if(useflag != null && useflag == 1){
							message_phone.append("有效");
						}
						else{
							message_phone.append("无效");
						}
						message_phone.append("\n");
						message_phone.append("收到密码后请及时删除短信。");

						String state = null;
						for(int i=0;i<phonenums.length;i++){
							PHSService phs = new PHSService();
							phs.setSaveService(saveService);
							//手机号码
							phs.setRecvNum(phonenums[i]);
							phs.setMsg(message_phone.toString());
							state=phs.sendSMS();
							phs.dxjl("管理员",name,"找回密码","找回密码",state);//保存短信记录
						}
						if(state != null && state.equals("success"));
						else{
							info.append("找回密码失败，可能是网络问题，也可能是系统登记的电话有误！");
						}
					}
				}
				else{
					info.append("输入的用户名有误，请重新输入！");
				}
			}
			catch(Exception e){
				System.out.println(e);
				info.append("找回密码失败，可能是网络问题，也可能是系统登记的电话有误！");
			}
			
			if(info.length() == 0){
				info.append("密码已发送至您的手机,请注意查收");
			}
			response.getWriter().print(info.toString());
		}
	}


package com.ghpms.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.ghpms.service.MessageToPhoneService;
import com.netsky.base.dataObjects.Te08_message;
import com.netsky.base.dataObjects.interfaces.ThreadServiceInterface;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.MobileMessage;
import com.netsky.base.utils.MobileMessageImpl;
import com.netsky.base.utils.NumberFormatUtil;

/**
 * @description:
 * 
 * @class name:com.ghpms.service.impl.MessageToPhoneServiceImpl
 * @author net Jun 14, 2013
 */
@Service("messageToPhoneService")
public class MessageToPhoneServiceImpl implements MessageToPhoneService,ThreadServiceInterface {
	
	@Autowired
	private SaveService saveService;
	
	private MobileMessage message;
	
	private Logger log = Logger.getLogger(this.getClass());

	public void dxjl(String fsr, String jsr, String title, String content,
			String state) {
		Te08_message te08=new Te08_message();
		te08.setFsr(fsr);
		te08.setJsr(jsr);
		te08.setFssj(new Date());
		te08.setTitle(title);
		te08.setContent(content);
		te08.setState(state);
		this.saveService.save(te08);
	}

	public String sendMessageToPhone(String content, String sender_name,
			String additionTel, String reader_tel, String reader_name) {
		String failed = "";
		StringBuffer message_phone = new StringBuffer();
		try {
			// 设置当前时间

			String fsr = sender_name;
			message_phone.append("发自：" + fsr + "[r-ms系统]");
			message_phone.append("\n");
			message_phone.append("内容：");
			message_phone.append(content);
			this.message=new MobileMessageImpl();
			if (!"".equals(reader_tel)) {
				String[] reader_names = reader_name.split("；");
				String[] readers = reader_tel.split(",");
				for (int i = 0; i < readers.length; i++) {

					if (NumberFormatUtil.isNumeric(readers[i]) && readers[i].length() == 11) {
						String state=message.sendMsg(message_phone.toString(), reader_tel);
						this.dxjl(fsr, reader_names[i], "手机短信", content, state);// 短信发送记录
						if (state.equals("16")){
							failed += reader_names[i]+";";
						}
					} else {
						failed += reader_names[i] + "(非11位数字);";
					}
				}
			}
			if (additionTel != null && additionTel.length() != 0) {
				String tmp = additionTel;
				tmp = tmp.replaceAll(",", ";");
				tmp = tmp.replaceAll("，", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll("；", ";");
				String[] additionTels = tmp.split(";");
				for (String string : additionTels) {
					if (!"".equals(string)) {
						if (NumberFormatUtil.isNumeric(string) && string.length() == 11) {
							String state=message.sendMsg(message_phone.toString(), string);
							this.dxjl(fsr, string, "手机短信", content, state);// 短信发送记录
							if (state.equals("16")){
								failed += string+";";
							}
						} else {
							failed += string + "(非11位数字);";
						}
					}
				}
			}
			this.message.close();
			if (failed.length() != 0) {
				failed = failed.substring(0, failed.length() - 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.message.close();
			log.error("短信服务器已断开连接,错误信息:"+e.getMessage());
		}
		return failed;
	}

	public void handle(Map<String ,String> paramMap) throws Exception{
		
		
		String sender_name=(String) paramMap.get("sender_name");
		String content=(String)paramMap.get("content");
		String reader_tel=(String)paramMap.get("reader_tel");
		String reader_name=(String)paramMap.get("reader_name");
		String additionTel=(String)paramMap.get("additionTel");
		String failed = "";
		StringBuffer message_phone = new StringBuffer();
		try {
			// 设置当前时间

			String fsr = sender_name;
			message_phone.append("发自：" + fsr + "[r-ms系统]");
			message_phone.append("\n");
			message_phone.append("内容：");
			message_phone.append(content);
			this.message=new MobileMessageImpl();
			if (!"".equals(reader_tel)) {
				String[] reader_names = reader_name.split("；");
				String[] readers = reader_tel.split(",");
				for (int i = 0; i < readers.length; i++) {

					if (NumberFormatUtil.isNumeric(readers[i]) && readers[i].length() == 11) {
						String state=message.sendMsg(message_phone.toString(), reader_tel);
						this.dxjl(fsr, reader_names[i], "手机短信", content, state);// 短信发送记录
						if (state.equals("16")){
							failed += reader_names[i]+";";
						}
					} else {
						failed += reader_names[i] + "(非11位数字);";
					}
				}
			}
			if (additionTel != null && additionTel.length() != 0) {
				String tmp = additionTel;
				tmp = tmp.replaceAll(",", ";");
				tmp = tmp.replaceAll("，", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll(" ", ";");
				tmp = tmp.replaceAll("；", ";");
				String[] additionTels = tmp.split(";");
				for (String string : additionTels) {
					if (!"".equals(string)) {
						if (NumberFormatUtil.isNumeric(string) && string.length() == 11) {
							String state=message.sendMsg(message_phone.toString(), string);
							this.dxjl(fsr, string, "手机短信", content, state);// 短信发送记录
							if (state.equals("16")){
								failed += string+";";
							}
						} else {
							failed += string + "(非11位数字);";
						}
					}
				}
			}
			this.message.close();
			if (failed.length() != 0) {
				failed = failed.substring(0, failed.length() - 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.message.close();
		}
	
	}

}

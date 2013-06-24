package com.netsky.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.service.SaveService;
import com.netsky.base.service.SendMessageService;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.PHSService;

@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {
	@Autowired
	private SaveService saveService;

	public String sendMessage(String content, String sender_name, String additionTel, String reader_tel,
			String reader_name) {
		String failed = "";
		StringBuffer message_phone = new StringBuffer();
		try {
			// 设置当前时间

			String fsr = sender_name;
			message_phone.append("发自：" + fsr + "[ghpms系统]");
			message_phone.append("\n");
			message_phone.append("内容：");
			message_phone.append(content);
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
						if (!"success".equals(state)) {
							failed += reader_names[i] + ";";
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
							PHSService phs = new PHSService();
							phs.setSaveService(saveService);
							phs.setRecvNum(string);
							phs.setMsg(message_phone.toString());
							String state = phs.sendSMS();

							phs.dxjl(fsr, string, "手机短信", content, state);// 短信发送记录
							if (!"success".equals(state)) {
								failed += string + ";";
							}
						} else {
							failed += string + "(非11位数字);";
						}
					}
				}
			}
			if (failed.length() != 0) {
				failed = failed.substring(0, failed.length() - 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return failed;
	}
}

package com.ghpms.service;

import java.util.Map;

public interface MessageToPhoneService  {
	/**
	 * 
	 * @param content 短信內容
	 * @param sender_name 發件人姓名
	 * @param additionTel  額外收信人姓名
	 * @param reader_tel   收信人電話
	 * @param reader_name   收信人姓名
	 * @return String     返回值
	 */
	public String sendMessageToPhone(String content,String sender_name,String additionTel, String reader_tel,String reader_name);
	/**
	 * 
	 * @param fsr     發送人
	 * @param jsr      接收人
	 * @param title     信息類型
	 * @param content   發送內容
	 * @param state void
	 */
	public void dxjl(String fsr,String jsr,String title,String content,String state);
	
	/**
	 * 
	 * @param paramMap 參數列表
	 * @return String
	 */
	public void handle(Map<String, String> paramMap) throws Exception;
}

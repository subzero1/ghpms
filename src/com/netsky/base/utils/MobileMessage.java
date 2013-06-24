package com.netsky.base.utils;

public interface MobileMessage {
	/**
	 *  void
	 */
	public void connect();
	/**
	 *  void
	 */
	public void close();
	/**
	 * 
	 * @param content  短信內容
	 * @param reader_tel  收信人電話
	 * @return String    
	 */
	public String sendMsg(String content,String reader_tel);
}

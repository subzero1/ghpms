package com.netsky.base.utils;


import org.apache.log4j.Logger;

import com.linkage.netmsg.NetMsgclient;
import com.linkage.netmsg.server.ReceiveMsg;
public class MobileMessageImpl implements MobileMessage{

	private ReceiveMsg receiveMsg;
	private NetMsgclient client;
	private String url;
	private String username;
	private String password;
	private int portNum;
	
	private Logger log = Logger.getLogger(this.getClass());

 
	
	public void close() {
		client.closeConn();
	}

	public MobileMessageImpl(){
		super();
		this.client =new NetMsgclient();
		this.receiveMsg=new ReceiveMsgImp(); 
		this.connect();
	}
	public MobileMessageImpl(ReceiveMsg receiveMsg,NetMsgclient client){
		super();
		this.client =client;
		this.receiveMsg=receiveMsg;
	}
	public void connect() {

		client=client.initParameters("202.102.41.101", 9005, "025C00301969", "gc5977", receiveMsg);
		 try {
	            /*登录认证*/
	            boolean isLogin = client.anthenMsg(client);
	            if(isLogin) {
	            	System.out.println("login sucess");
	            } 
	        } catch (Exception e1) {
	            e1.printStackTrace();
	            log.error(e1.getMessage());
	        }
	
	}
 
	public String sendMsg(String content, String reader_tel) {
		return client.sendMsg(client, 0, reader_tel,content, 1);
	}
 

}

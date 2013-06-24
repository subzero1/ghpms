package com.netsky.base.utils;

import com.linkage.netmsg.server.AnswerBean;
import com.linkage.netmsg.server.ReceiveMsg;
import com.linkage.netmsg.server.ReturnMsgBean;
import com.linkage.netmsg.server.UpMsgBean;

public class ReceiveMsgImp extends ReceiveMsg{
	/*获取下行短信返回状态和短信ID的方法*/
	public ReceiveMsgImp(){
		super();
	}
	
	public void getAnswer(AnswerBean answerBean) {
		super.getAnswer(answerBean);
	}

	/*接收上行短信的方法*/
	public void getUpMsg(UpMsgBean upMsgBean) {
		super.getUpMsg(upMsgBean);
	}

	/* 获取下行短信回执的方法 */
	public void getReturnMsg(ReturnMsgBean returnMsgBean) {
		super.getReturnMsg(returnMsgBean);
	}


}

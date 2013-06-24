package com.netsky.base.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Te08_message;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

public class PHSService {

	private String recvNum = "";
	private String msg = "";
	
	@Autowired
	Dao dao;

	private QueryService queryService;

	private SaveService saveService;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	
	/**
	 * @return the saveService
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService
	 *            the saveService to set
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}
	
	
	//保存短信记录--wxl
	public void dxjl(String fsr,String jsr,String title,String content,String state){
		
		Te08_message te08=new Te08_message();
		te08.setFsr(fsr);
		te08.setJsr(jsr);
		te08.setFssj(new Date());
		te08.setTitle(title);
		te08.setContent(content);
		te08.setState(state);
		saveService.save(te08);
	}

	public String parseResult(String resStr){
		if(resStr.equals("ERROR"))
			return "failed";
		else{
			resStr = resStr.substring(resStr.indexOf("status")+8, resStr.indexOf("message")-2);
			if(Integer.parseInt(resStr)==0)
				return "success";
			else
				return "failed";
		}
	}

	public String sendSMS(){
		String result = "success";
		Transaction tx = saveService.getHiberbateSession().beginTransaction();
		Connection connection = saveService.getHiberbateSession().connection();
		CallableStatement st = null;
		try {
			st = connection.prepareCall("call p_send@msgSend('"+recvNum+"','"+msg+"')");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "failed";
		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		tx.commit();
		return result;
	}
	

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setRecvNum(String recvNum) {
		this.recvNum = recvNum;
	}

	
	public static void main(String[] args) {
		PHSService phs = new PHSService();
		phs.setRecvNum("13821717829");
		phs.setMsg("小灵通短信发送测试-网天");
		System.out.println(phs.sendSMS());

	}
}

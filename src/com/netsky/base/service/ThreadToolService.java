package com.netsky.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Tz05_thread_queue;
import com.netsky.base.dataObjects.interfaces.ThreadServiceInterface;


@Service("threadToolService")
public class ThreadToolService extends Thread implements ServletContextAware {
	// 线程休眠的时长
	private static Long SLEEP_SECONDS = 5L;

	private ServletContext servletContext;
	@Autowired
	private Dao dao;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void run(){
		while (true) {
			Session session = dao.getHibernateSession();
			try {
//				Long nextval = ((BigDecimal) (session.createSQLQuery("select batch_num.nextval from dual")
//						.uniqueResult())).longValue();
				
				Long nextval = new Long(Math.round(Math.random() * 1000000));
				
				if (session
						.createQuery(
								"update Tz05_thread_queue set batchno='" + nextval
										+ "' where status='未处理' and batchno is null").executeUpdate() > 0) {
					List<Tz05_thread_queue> threadList = (List<Tz05_thread_queue>) dao
							.search("from Tz05_thread_queue where status='未处理' and batchno='" + nextval + "'");

					Date now = new Date();

					for (Tz05_thread_queue thread : threadList) {
						try {
							thread.setHandletime(now);
							ApplicationContext ctx = WebApplicationContextUtils
									.getWebApplicationContext(servletContext);
							Object o = ctx.getBean(thread.getServicename());
							String status = "处理成功";
							if (!(o instanceof ThreadServiceInterface)) {
								status = "处理失败:这个SERVICE没有实现ThreadService接口";
							} else {
								ThreadServiceInterface threadService = (ThreadServiceInterface) o;
								Map<String, String> paramMap = new HashMap<String, String>();
								JSONObject jo = new JSONObject(thread.getParameters());
								for (String key : JSONObject.getNames(jo)) {
									paramMap.put(key, jo.getString(key));
								}
								try {
									threadService.handle(paramMap);
								} catch (Exception e) {
									status = "处理失败:" + e.getMessage();
								}
							}
							thread.setHandletime(now);
							thread.setStatus(status);
							dao.saveObject(thread);
						} catch (Exception e) {
							log.error("error in service:" + e);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("error in thread:" + e);
			} finally {
				session.close();
			}
			try {
				Thread.sleep(1000 * SLEEP_SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

}

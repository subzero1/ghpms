package com.netsky.base.service.threadDemo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Tz05_thread_queue;

@Controller
public class TestInsertController {
	@Autowired
	private Dao dao;

	@RequestMapping("/testInsert.do")
	public void testInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Tz05_thread_queue thread = new Tz05_thread_queue();
		thread.setInserttime(new Date());
		thread.setServicename("testservice");
		JSONObject jo = new JSONObject();
		jo.put("s", "成功");
		thread.setRemark("测试备注");
		thread.setType("测试类别");
		thread.setParameters(jo.toString());
		dao.saveObject(thread);
	}
}

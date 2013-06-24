package com.ghpms.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.utils.XMLTableWrite;

@Controller
public class XMLTableController {
	@RequestMapping("/autoTab.do")
	public ModelAndView	 autoGenerateTabXML(HttpServletRequest request,HttpServletResponse response) {
		String view="/WEB-INF/jsp/form/autoTab.jsp";
		return new ModelAndView(view);
	}
	@RequestMapping("/form/autoGenerateTab.do")
	public void autoGenerateTab(HttpServletRequest request,HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		String tableName=convertUtil.toString(request.getParameter("tableName"));
		StringBuffer outs=new StringBuffer();
		XMLTableWrite write=new XMLTableWrite();
		PrintWriter out=response.getWriter();
		response.setCharacterEncoding("utf-8");
		outs.append("文件已生成,文件名为:");
		outs.append(tableName.toLowerCase());
		outs.append(".xml");
		try {
			write.autoGenerateTableXML(tableName.toString());
			out.print(outs.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}

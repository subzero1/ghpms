package com.netsky.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller("/dispath.do")
public class DispathController implements org.springframework.web.servlet.mvc.Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/WEB-INF/jsp/" + request.getParameter("url"));
		
	}
}  
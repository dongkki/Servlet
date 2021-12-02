package com.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsAjax.do")
public class JavascriptAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("jsAjax.do 호출됨!");
		try {
			req.setCharacterEncoding("UTF-8");
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			
			System.out.println("name : " + name +", " + "age : " + age );
			
//			resp.setHeader("Access-Control-Allow-Credentials", "true");
//			resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//			resp.setHeader("Connection", "close");
			resp.setContentType("text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().append("서버에서 전송한 값<br>").append("name : " + name +", " + "age : " + age );
		} catch (Exception e) {}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}	
	
}

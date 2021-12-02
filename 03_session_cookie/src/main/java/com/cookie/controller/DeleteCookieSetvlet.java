package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletecookie.do")
public class DeleteCookieSetvlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 쿠키 삭제하는 법 : Client에 삭제 요청을 해야하며 setMaxAge(0)를 통해 쿠키 유효기간을 삭제!
		try {
			Cookie[] cookies = req.getCookies();
			for (Cookie c : cookies) {
				String name = c.getName();
				Cookie newCookie = new Cookie(name, "");	// 이렇게만 해도 쿠키 내용은 없어짐!
				newCookie.setMaxAge(0);
				resp.addCookie(newCookie);
			}
		} catch (Exception e) {
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append("<script> alert('쿠키가 삭제되었습니다.');" + "location.href='"+req.getContextPath()+"' </script>");
		
//		resp.sendRedirect(req.getContextPath());
	}
}

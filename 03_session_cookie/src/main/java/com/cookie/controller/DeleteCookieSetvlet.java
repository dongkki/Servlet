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
		// ��Ű �����ϴ� �� : Client�� ���� ��û�� �ؾ��ϸ� setMaxAge(0)�� ���� ��Ű ��ȿ�Ⱓ�� ����!
		try {
			Cookie[] cookies = req.getCookies();
			for (Cookie c : cookies) {
				String name = c.getName();
				Cookie newCookie = new Cookie(name, "");	// �̷��Ը� �ص� ��Ű ������ ������!
				newCookie.setMaxAge(0);
				resp.addCookie(newCookie);
			}
		} catch (Exception e) {
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append("<script> alert('��Ű�� �����Ǿ����ϴ�.');" + "location.href='"+req.getContextPath()+"' </script>");
		
//		resp.sendRedirect(req.getContextPath());
	}
}

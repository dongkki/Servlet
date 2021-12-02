package com.cookie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkcookie.do")
public class CheckCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��Ű �д� ���
		// 1. request ��ü���� ��Ű �о����
		Cookie[] cookies = req.getCookies();
		Map<String, String> cookiesMap = new HashMap<String, String>();

		for (Cookie c : cookies) {
			System.out.println("key : " + c.getName() + ", value : " + c.getValue());
			cookiesMap.put(c.getName(), c.getValue());
		}

		String userID = cookiesMap.get(CookieCreateServlet.COOKIE_NAME_ID);
		String userName = cookiesMap.get(CookieCreateServlet.COOKIE_NAME_USER_NAME);

		// 2. ���� ������ �ۼ�
		StringBuffer sb = new StringBuffer();

		sb.append("User ID : " + userID + "<br>");
		sb.append("User Name : " + userName + "<br>");
		sb.append("<a href='deletecookie.do'>���� ������ �̵�</a><br>");
		sb.append("<a href='" + req.getContextPath() + "'>���� ������ �̵�</a>");

		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(sb.toString());
	}

}

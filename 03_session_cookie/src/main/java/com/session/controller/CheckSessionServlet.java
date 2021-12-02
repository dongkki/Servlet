package com.session.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checksession.do")
public class CheckSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session�� ������ Ȯ�ι� / ���� ����
		// getSession(true) : session�� ������ ������ �������� ������ �����ؼ� �־���
		// getSession(false) : session�� ������ ������ �������� ������ null�� ��ȯ

		HttpSession session = req.getSession(false);

		if(session == null) {
			System.out.println("������ �����ϴ�.");
			return;
		}
		
		String userID = (String)session.getAttribute(SessionCreateServlet.SESSION_USER_ID);
		String userName = (String)session.getAttribute(SessionCreateServlet.SESSION_USER_NAME);
		
		// 2. ���� ������ �ۼ�
		StringBuffer sb = new StringBuffer();

		sb.append("User ID : " + userID + "<br>");
		sb.append("User Name : " + userName + "<br>");
		sb.append("<a href='" + req.getContextPath() + "'>���� ������ �̵�</a>");

		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(sb.toString());
	}
}

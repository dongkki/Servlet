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
		// session이 있을때 확인법 / 주의 사항
		// getSession(true) : session이 있으면 세션을 가져오고 없으면 생성해서 넣어줌
		// getSession(false) : session이 있으면 세션을 가져오고 없으면 null로 반환

		HttpSession session = req.getSession(false);

		if(session == null) {
			System.out.println("세션이 없습니다.");
			return;
		}
		
		String userID = (String)session.getAttribute(SessionCreateServlet.SESSION_USER_ID);
		String userName = (String)session.getAttribute(SessionCreateServlet.SESSION_USER_NAME);
		
		// 2. 응답 페이지 작성
		StringBuffer sb = new StringBuffer();

		sb.append("User ID : " + userID + "<br>");
		sb.append("User Name : " + userName + "<br>");
		sb.append("<a href='" + req.getContextPath() + "'>메인 페이지 이동</a>");

		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(sb.toString());
	}
}

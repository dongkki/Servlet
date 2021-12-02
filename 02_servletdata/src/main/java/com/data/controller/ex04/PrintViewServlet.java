package com.data.controller.ex04;

import static com.data.controller.ex04.Common.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/printmain.do")
public class PrintViewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute(SESSION_USER_ID);
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>");
		sb.append("<body>");
		if(userID != null) {
			// 로그인 성공
			sb.append(String.format("<h2>%s님 로그인을 환영합니다.</h2>", userID));
			String path = req.getContextPath() +"/sessiondel.do";
			sb.append(String.format("<button onclick='location.replace(\"%s\")'>로그아웃</button> ", path));
		}else {
			// 로그인 실패
			sb.append(String.format("<h2>로그인에 실패하였습니다. admin/1234, input=%s</h2>", userID));
			String path = req.getContextPath() +"/view/login.html";
			sb.append(String.format("<button onclick='location.replace(\"%s\")'>로그인</button> ", path));
		}
		sb.append("</html>");
		sb.append("</body>");
		
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().append(sb.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

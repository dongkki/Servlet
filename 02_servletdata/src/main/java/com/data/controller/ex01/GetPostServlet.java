package com.data.controller.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class GetPostServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// 참고, 로그인은 get 방식을 생성하지 않는다. -> 보안 이슈
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get으로 호출됨");
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 전달시에는 항상 한글이 깨질수 있음을 주의해야 한다. UTF-8로 다시 인코딩 필요
		req.setCharacterEncoding("UTF-8");
		System.out.println("post으로 호출됨");
		String id = req.getParameter("userid");
		String pw = req.getParameter("pw");
		System.out.println("아이디 : " + id + ", 비밀번호 : " + pw);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String loginHTML = makeLoginHTML(id, pw);
		PrintWriter out = resp.getWriter();
		out.println(loginHTML);
	}
	
	private String makeLoginHTML(String id, String pw) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<h3>");
		sb.append("사용자의 ID : " + id + " 입니다.<br>");
		sb.append("사용자의 PW : " + pw.substring(0,2) + "**** 입니다.<br>");
		sb.append("</h3>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
}

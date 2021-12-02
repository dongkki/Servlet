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

	// ����, �α����� get ����� �������� �ʴ´�. -> ���� �̽�
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get���� ȣ���");
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post ���޽ÿ��� �׻� �ѱ��� ������ ������ �����ؾ� �Ѵ�. UTF-8�� �ٽ� ���ڵ� �ʿ�
		req.setCharacterEncoding("UTF-8");
		System.out.println("post���� ȣ���");
		String id = req.getParameter("userid");
		String pw = req.getParameter("pw");
		System.out.println("���̵� : " + id + ", ��й�ȣ : " + pw);
		
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
		sb.append("������� ID : " + id + " �Դϴ�.<br>");
		sb.append("������� PW : " + pw.substring(0,2) + "**** �Դϴ�.<br>");
		sb.append("</h3>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
}

package com.data.controller.ex03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessiondel.do")
public class SessionDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// session��ü�� �����ڰ� ������ ���� �����Ҷ� ���� �����������.
		// session��ü�� �����ϴ� ��� : session.invalidate()�żҵ带 ȣ��
		HttpSession session = request.getSession();
		session.invalidate();// ������ �����ϴ� ���

		RequestDispatcher rd = request.getRequestDispatcher("userData.do");
		rd.forward(request, response);

	}

}

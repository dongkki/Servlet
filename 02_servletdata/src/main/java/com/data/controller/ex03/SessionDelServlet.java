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
		// session객체는 개발자가 로직에 의해 삭제할때 까지 사라지지않음.
		// session객체를 삭제하는 방법 : session.invalidate()매소드를 호출
		HttpSession session = request.getSession();
		session.invalidate();// 세션을 삭제하는 기능

		RequestDispatcher rd = request.getRequestDispatcher("userData.do");
		rd.forward(request, response);

	}

}

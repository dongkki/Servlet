package com.data.controller.ex04;

import static com.data.controller.ex04.Common.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Login에 관련된 Controller 역할
 * -> id, pw 유효성을 확인하고 DB로 부터 id와 pw 정보를 확인하여 로그인 합불판단만 하고
 * -> 실제 login 결과는 view에서 작성하도록 유도
 *
 */
@WebServlet("/logincheck.do")
public class LoginCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("userId");
		String pw = req.getParameter("password");
		// id = admin, pw = 1234
		
		if(id.equals("admin") == true && pw.equals("1234") == true) {
			HttpSession session = req.getSession();
			session.setAttribute(SESSION_USER_ID, id);
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패!!");
		}
		
		// 화명 출력 서블릿으로 이동, printmain.do
		RequestDispatcher rd = req.getRequestDispatcher("printmain.do");
		rd.forward(req, resp);
	}

}

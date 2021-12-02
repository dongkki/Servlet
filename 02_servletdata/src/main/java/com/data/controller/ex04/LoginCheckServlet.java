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
 * Login�� ���õ� Controller ����
 * -> id, pw ��ȿ���� Ȯ���ϰ� DB�� ���� id�� pw ������ Ȯ���Ͽ� �α��� �պ��Ǵܸ� �ϰ�
 * -> ���� login ����� view���� �ۼ��ϵ��� ����
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
			System.out.println("�α��� ����");
		}else {
			System.out.println("�α��� ����!!");
		}
		
		// ȭ�� ��� �������� �̵�, printmain.do
		RequestDispatcher rd = req.getRequestDispatcher("printmain.do");
		rd.forward(req, resp);
	}

}

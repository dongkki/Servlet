package com.data.controller.ex03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet�� Ȥ�� ���� �������� �����͸� �����ϴ� ���
 * 
 * 1. ServletContext�� Ȱ���ϴ� �� - 
 * 		Context��? 
 * 		- Tomcat���� �������ְ� ������ ���α׷��� ����Ǵ� ���� ����
 * 		- �����ֱ⸦ ���� ȯ�� ��ü ���� ������ Servlet ��ü ���� �� ȯ�濡 ���õ� ��� �ڿ��� �����ϴ� ��ü - �ش� ��ü��
 * 		- Attribute �Լ��� Ȱ���Ͽ� �����͸� ���� - ���������� �ش��ϴ� ���� ������. ȣ�� Ƚ��, ������ �ֿ���� ���� ������ �̽���. -
 * 		- ���峻�� ��ȿ�Ⱓ : ���� runtime���� ���� shutdown ���� �����
 * 
 * 2. HttpSession
 * 		- ����� ���� �����Ǵ� ������ ����� ��ü
 * 		- timeout�� ������ �� �ְ�, ������ �ð��� session�� ���� ��
 * 		- ���� �������� ����ں��� ������ Ȯ�� ������ ��ü
 * 		- ������ ���� �Ⱓ : timeout �ð� or ����ڰ� invalidate() �Լ��� ȣ���� �� -> logout ��ư ���� ��
 * 		- ex) ����� �α��� ����, ��������, ����ڿ� ���õ� ���� ���� ����.
 * 
 * 3. HttpServletRequest - setAttribute
 * 		- ���� ��, ���� - jsp �� �̵��� �� �� ��ü�� ��Ƽ� ������ �뵵
 * 		- ex) Servlet(Controller) -> JSP(View)
 * 			������ ���� DB ���� ȹ��, ��üȭ �Ͽ� JSP�� ������ JSP ���빰 ���
 * 		- redirect ���޽� ������ ���� X
 */
@WebServlet("/share.do")
public class ShareDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONTEXT_ID1 = "CONTEXT_ID1";
	public static final String CONTEXT_ID2 = "CONTEXT_ID2";

	public static final String SESSION_ID = "SESSION_ID";
	public static final String MSG_ID = "MSG_ID";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("share.do���� ȣ���");
		// 1. ServletContext ���� �����뵵
		ServletContext context1 = req.getServletContext(); // ��ü ȣ��, context�� �����ϴ�.
		ServletContext context2 = getServletContext();
		context1.setAttribute(CONTEXT_ID1, "test context1"); // ������ �����ϴ� ���
		context2.setAttribute(CONTEXT_ID2, "test context2");

		String msg1 = (String) context1.getAttribute(CONTEXT_ID2); // ������ �������� ���
		String msg2 = (String) context2.getAttribute(CONTEXT_ID1);
		System.out.println(msg1);
		System.out.println(msg2);

		// 2. HttpSession - ����� ���� ������ timeout �Ⱓ ���� ������ ����Ǵ� �ӽ� ��ü, login/���� ������ ����
		HttpSession session = req.getSession(); // �ش� Client�� ���� ������ �������� ���
		session.setAttribute(SESSION_ID, "seesion data! : " + req.getLocalAddr());

		String msg = (String) session.getAttribute(SESSION_ID);
		System.out.println(msg);

		// 3. HttpServletRequest��ü�� setAttribute�� Ȱ���� ������ ����, ������-������ �� ������ ����
		// Servlet(Controller) -> JSP(View)
		req.setAttribute(MSG_ID, "request�� ���� �޼��� ����");
		String msg3 = (String) req.getAttribute(MSG_ID);
		System.out.println(msg3);

		// ������ �̵��ؼ� ���� Ȯ���ϱ�
		// 1. forward Ȱ��
		RequestDispatcher rd = req.getRequestDispatcher("userData.do");
		rd.forward(req, resp);

		// 2. redirect Ȱ��
//		resp.sendRedirect("userData.do");
	}

}

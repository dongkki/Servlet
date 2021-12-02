package com.data.controller.ex05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * �Ķ���͸� web.xml�� �����Ͽ� ���긴�� ���� �����͸� �����ϴ� ��� 
 *
 */
public class InitParamServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		resp.getWriter().append("<http><body>");
		resp.getWriter().append(email + " / ");
		resp.getWriter().append(tel);
		resp.getWriter().append("</body></html>");
	}
}

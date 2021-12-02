package com.data.controller.ex05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 파라미터를 web.xml에 정의하여 서브릿에 별도 데이터를 전달하는 방법 
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

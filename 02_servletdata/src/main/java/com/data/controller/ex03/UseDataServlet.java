package com.data.controller.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userData.do")
public class UseDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = req.getSession();

		String contextMSG = (String) context.getAttribute(ShareDataServlet.CONTEXT_ID1);
		String sessionMSG = (String) session.getAttribute(ShareDataServlet.SESSION_ID);
		String requestMSG = (String) req.getAttribute(ShareDataServlet.MSG_ID);

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String html = "<html>";
		html += "<body>";
		html += "<ul>";
		html += "<li>contextData : " + contextMSG + "</li>";
		html += "<li>sessionData : " + sessionMSG + "</li>";
		html += "<li>requestData : " + requestMSG + "</li>";
		html += "</ul>";
		html += "<button onclick='location.assign(\"/02_servletdata/sessiondel.do\");'>sessionªË¡¶</button>";
		html += "</body>";
		html += "</html>";
		out.append(html);

	}
}

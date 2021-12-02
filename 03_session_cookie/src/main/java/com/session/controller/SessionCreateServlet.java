package com.session.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �����̶�?
 * - HTTP �������ݷ� �ϳ��� �������� Session ID�� ���� ����� ������ �����ϴ� �뵵�� Ȱ��
 * - ���� �Ⱓ : Ŭ���̾�Ʈ�� �������� �����Ͽ� �������� ����(������ ���� ����)�Ǹ� ���� ������ ����
 * - ���� ��ġ : ������ �޸�/��ũ ����(�ӽ������� ����)
 * - Ư¡ : ��Ű���� ���������� �����ϴ� -> �����Ͱ� �������� �����Ǳ⶧��
 * - ���� : ���ͳ� ������ �ҿ����ϰų� �������� ����Ǵ� ��� ���� ������ ���� ����
 * 		-> �ֱ� ����Ʈ�� ȯ�濡�� ������ �����ϱ� ���� cookie + session ������ �����Ͽ� �α��� ���� ����
 */
@WebServlet("/sessiontest.do")
public class SessionCreateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_USER_ID = "USER_ID";
	public static final String SESSION_USER_NAME = "USER_NAME";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� ����
		// 1. req�κ��� ���� ��ü ��������
		HttpSession session = req.getSession();
		
		// 2. ���� ������ �ֱ� �� �ð� ����
		session.setAttribute(SESSION_USER_ID, "testid_12");
		session.setAttribute(SESSION_USER_NAME, "�浿��");
		session.setMaxInactiveInterval(10);
		
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>������ �����Ǿ����ϴ�.</p>");
		sb.append("<a href = 'checksession.do'>����� ���� Ȯ��</a>");
		sb.append("</body>");
		sb.append("</html>");

		resp.getWriter().append(sb.toString());
	}
}

package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��Ű��? - HTTP�� ��ϼ��� �������� ����ڰ� �� ����Ʈ�� �湮�ϸ�, ����Ʈ���� ����ϴ� ������ ������ �� �ִ� ����� ���� - ��Ű
 * ǥ�� : �ִ� ũ�� 4kb, ���尳�� : 3000�� - ��� ��� : �� ���������� ������ path�� ���� - Ư¡ : ���ȿ� ����ϴٰ�
 * �˷��� -> �����ڰ� �����ϰ� ����� �� �ִ� ����� ���� �ʿ�
 * 
 *
 */
@WebServlet("/cookietest.do")
public class CookieCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String COOKIE_NAME_ID = "USER_ID";
	public static final String COOKIE_NAME_USER_NAME = "USER_NAME";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��Ű �����ϴ� ��
		// 1. ��Ű�� ��ü�� �����ϰ�, Key-Value ���� �־���
		Cookie cookie1 = new Cookie(COOKIE_NAME_ID, "testid");
		Cookie cookie2 = new Cookie(COOKIE_NAME_USER_NAME, "ȫ�浿");

		// 2. ��Ű�� �⺻ ������ ����
		// setMaxAge : ��Ű ���� �ð� seconds ����
		cookie1.setMaxAge(60 * 60 * 24); // 24�ð� ��/��/��
		cookie2.setMaxAge(60 * 60 * 24); // 24�ð� ��/��/��

		// 3. ���信 ��Ű �߰��ϱ� -> Client�� ���޵�
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);

		// 4. ���� ������ �ۼ��ϱ�
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>��Ű�� �����߽��ϴ�.</p>");
		sb.append("<a href = 'checkcookie.do'>����� ��Ű Ȯ��</a>");
		sb.append("</body>");
		sb.append("</html>");

		resp.getWriter().append(sb.toString());
	}
}

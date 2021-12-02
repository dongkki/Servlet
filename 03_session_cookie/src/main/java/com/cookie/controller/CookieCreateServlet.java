package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 쿠키란? - HTTP의 기록서의 일종으로 사용자가 웹 사이트를 방문하면, 사이트에서 사용하는 정보로 저장할 수 있는 기능을 제공 - 쿠키
 * 표준 : 최대 크기 4kb, 저장개수 : 3000개 - 기록 장소 : 웹 브라우저에서 지정한 path에 저장 - 특징 : 보안에 취약하다고
 * 알려짐 -> 개발자가 안전하게 사용할 수 있는 기술적 보완 필요
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
		// 쿠키 생성하는 법
		// 1. 쿠키의 객체를 생성하고, Key-Value 값을 넣어줌
		Cookie cookie1 = new Cookie(COOKIE_NAME_ID, "testid");
		Cookie cookie2 = new Cookie(COOKIE_NAME_USER_NAME, "홍길동");

		// 2. 쿠키의 기본 설정을 수행
		// setMaxAge : 쿠키 보관 시간 seconds 기준
		cookie1.setMaxAge(60 * 60 * 24); // 24시간 초/분/시
		cookie2.setMaxAge(60 * 60 * 24); // 24시간 초/분/시

		// 3. 응답에 쿠키 추가하기 -> Client에 전달됨
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);

		// 4. 응답 페이지 작성하기
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>쿠키를 생성했습니다.</p>");
		sb.append("<a href = 'checkcookie.do'>저장된 쿠키 확인</a>");
		sb.append("</body>");
		sb.append("</html>");

		resp.getWriter().append(sb.toString());
	}
}

package com.session.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 세션이란?
 * - HTTP 프로토콜로 하나의 서버에서 Session ID를 통해 사용자 정보를 저장하는 용도로 활용
 * - 저장 기간 : 클라이언트와 서버간의 접속하여 브라우저가 종료(비정상 종료 포함)되면 세션 정보가 해지
 * - 저장 위치 : 서버의 메모리/디스크 공간(임시적으로 생성)
 * - 특징 : 쿠키보다 보안적으로 안전하다 -> 데이터가 서버에서 관리되기때문
 * - 단점 : 인터넷 연결이 불완전하거나 브라우저가 종료되는 경우 세션 유지가 되지 않음
 * 		-> 최근 스마트폰 환경에서 단점을 보완하기 위해 cookie + session 정보를 결합하여 로그인 정보 유지
 */
@WebServlet("/sessiontest.do")
public class SessionCreateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_USER_ID = "USER_ID";
	public static final String SESSION_USER_NAME = "USER_NAME";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션 생성
		// 1. req로부터 세션 객체 가져오기
		HttpSession session = req.getSession();
		
		// 2. 세션 데이터 넣기 및 시간 설정
		session.setAttribute(SESSION_USER_ID, "testid_12");
		session.setAttribute(SESSION_USER_NAME, "길동이");
		session.setMaxInactiveInterval(10);
		
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>세션이 생성되었습니다.</p>");
		sb.append("<a href = 'checksession.do'>저장된 세션 확인</a>");
		sb.append("</body>");
		sb.append("</html>");

		resp.getWriter().append(sb.toString());
	}
}

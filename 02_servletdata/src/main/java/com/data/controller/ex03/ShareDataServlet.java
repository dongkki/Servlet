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
 * Servlet간 혹은 서버 레벨에서 데이터를 공유하는 방법
 * 
 * 1. ServletContext를 활용하는 법 - 
 * 		Context란? 
 * 		- Tomcat에서 생성해주고 서버의 프로그램이 실행되는 동안 같은
 * 		- 생명주기를 갖는 환경 객체 원래 역할은 Servlet 객체 관리 및 환경에 관련된 모든 자원을 관리하는 주체 - 해당 객체에
 * 		- Attribute 함수를 활용하여 데이터를 공유 - 전역변수에 해당하는 값을 저장함. 호출 횟수, 서버의 주요상태 등의 굵직한 이슈들. -
 * 		- 저장내용 유효기간 : 서버 runtime부터 서버 shutdown 까지 저장됨
 * 
 * 2. HttpSession
 * 		- 사용자 별로 생성되는 데이터 저장용 객체
 * 		- timeout을 지정할 수 있고, 지정된 시간만 session이 유지 됨
 * 		- 서블릿 전역에서 사용자별로 데이터 확인 가능한 객체
 * 		- 데이터 저장 기간 : timeout 시간 or 사용자가 invalidate() 함수를 호출할 때 -> logout 버튼 누를 때
 * 		- ex) 사용자 로그인 정보, 권한정보, 사용자에 관련된 고유 정보 저장.
 * 
 * 3. HttpServletRequest - setAttribute
 * 		- 서블릿 간, 서블릿 - jsp 간 이동이 될 때 객체를 담아서 보내는 용도
 * 		- ex) Servlet(Controller) -> JSP(View)
 * 			서블릿을 통한 DB 정보 획득, 객체화 하여 JSP로 보내고 JSP 내용물 출력
 * 		- redirect 전달시 데이터 전달 X
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
		System.out.println("share.do에서 호출됨");
		// 1. ServletContext 서버 전역용도
		ServletContext context1 = req.getServletContext(); // 객체 호출, context는 동일하다.
		ServletContext context2 = getServletContext();
		context1.setAttribute(CONTEXT_ID1, "test context1"); // 데이터 셋팅하는 방법
		context2.setAttribute(CONTEXT_ID2, "test context2");

		String msg1 = (String) context1.getAttribute(CONTEXT_ID2); // 데이터 가져오는 방법
		String msg2 = (String) context2.getAttribute(CONTEXT_ID1);
		System.out.println(msg1);
		System.out.println(msg2);

		// 2. HttpSession - 사용자 별로 지정한 timeout 기간 동안 서버에 저장되는 임시 객체, login/권한 정보를 담음
		HttpSession session = req.getSession(); // 해당 Client의 고유 세션을 가져오는 방법
		session.setAttribute(SESSION_ID, "seesion data! : " + req.getLocalAddr());

		String msg = (String) session.getAttribute(SESSION_ID);
		System.out.println(msg);

		// 3. HttpServletRequest객체에 setAttribute를 활용한 데이터 공유, 페이지-페이지 간 데이터 공유
		// Servlet(Controller) -> JSP(View)
		req.setAttribute(MSG_ID, "request를 통한 메세지 전달");
		String msg3 = (String) req.getAttribute(MSG_ID);
		System.out.println(msg3);

		// 페이지 이동해서 내용 확인하기
		// 1. forward 활용
		RequestDispatcher rd = req.getRequestDispatcher("userData.do");
		rd.forward(req, resp);

		// 2. redirect 활용
//		resp.sendRedirect("userData.do");
	}

}

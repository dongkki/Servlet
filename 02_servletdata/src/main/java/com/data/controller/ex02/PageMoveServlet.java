package com.data.controller.ex02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * pagemove.do 요청이 오면 testperson.do로 이동하는 코드 작성 해당 서블릿에서 특별한 인자를 넣고,
 * testperson.do 인자를 받는 형식으로 코딩되어 있음
 * RequestDispatcher : 페이지 이동을 위한 url을 셋팅하는 객체
 * forward() : 실제 페이지를 이동시키는 코드로 pagemove.do -> testperson.do로 전환되지만 url은 변하지 않음
 * include() : 페이지를 포함하는 코드로 pagemove.do -> testperson.do -> pagemove.do 로 이동되어 페이지 처리가 됨
 */

@WebServlet("/pagemove.do")
public class PageMoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String MSG_ID = "MSG";
	public static final String MSG_DATE = "DATE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request에 인자를 넣어 다른페이지로 전달하는 법

		// 1. request에 인자를 넣기 setAttribute(String, Object)
		req.setAttribute(MSG_ID, "안녕하세요?");
		req.setAttribute(MSG_DATE, new Date());
		// 만약 id값이 같은 경우 메세지 덮어 쓰기가 됨
		req.setAttribute(MSG_ID, "저는 홍길동 입니다.");

		// 2. request 인자 읽는 방법
		try {
			String msg = (String) req.getAttribute(MSG_ID);
			Date date = (Date) req.getAttribute(MSG_DATE);
			System.out.println(msg);
			System.out.println(date.toString());
			// 해당 코드의 위험성 : null point, type cast
		} catch (Exception e) {
		}

		// 3. 페이지 전달하는 방법
		// getRequestDispatcher 생성 및 forward()를 통해 이동
		// getRequestDisapatcher("서블릿맵칭주소||jsp주소");

		RequestDispatcher rd = req.getRequestDispatcher("testperson.do");
		rd.forward(req, resp); // 실제 페이지가 이동 되는 코드!!
	}

//	// 모든 페이지를 일부로 이어주진 않는다!!
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPost(req, resp);
//	}

}

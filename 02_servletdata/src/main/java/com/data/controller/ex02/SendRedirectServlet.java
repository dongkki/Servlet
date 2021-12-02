package com.data.controller.ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * sendRedirect란?
 * 지정된 URL을 특정 페이지로 재전송하는 기능으로 기존의 Post 파라메터가 리셋되고
 * 방식도 get으로 고정되어 전달되는 기능.
 * 기존의 요청(request) 객체, 응답(response) 객체 모두 초기화 된 상태로 전달되어
 * 정보가 유지되지 않는다!!
 * 
 * sendRedirect vs forward
 * sendRedirect : 순수 페이지 이동이 필요할때 사용
 * 				  ex) 잘못된 인자 전달된 경우 -> 404 페이지로 이동하는 단순한 경우
 * forward : 기존의 정보를 포함한 더 넓은 기능으로 사용될 때
 * 			 ex) login -> admin(vip)일 경우 추가적인 인자를 처리 할 때
 * 
 */

@WebServlet("/sendredirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sendredirect.do 호출");
		req.setAttribute(PageMoveServlet.MSG_ID, "redirect에서 보낸 메세지입니당");

		resp.sendRedirect("testperson.do");
	}

}

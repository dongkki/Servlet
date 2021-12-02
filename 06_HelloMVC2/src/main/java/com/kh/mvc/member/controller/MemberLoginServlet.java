package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name="login", urlPatterns = "/login")
public class MemberLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private MemberService service = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userID = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		String saveID = req.getParameter("saveId");
	
		System.out.println(userID + ", "+userPwd+", " + saveID);
		
		Member loginMember = service.login(userID, userPwd);
		
		// 쿠키 기능 구현
		if(saveID != null) { // 아이디 저장할때 = 쿠키 남겨야함
			Cookie cookie = new Cookie("saveId", userID);
    		cookie.setMaxAge(60*60); // 60초 * 60분 = 1시간
    		resp.addCookie(cookie); 
		}else { // 아이디 저장 안할때! = 쿠키 삭제해야함
			Cookie cookie = new Cookie("saveId", "");
    		cookie.setMaxAge(0);
    		resp.addCookie(cookie); 
		}
		
		// 로그인 기능 구현
		if(loginMember != null ) { // 로그인 성공!!
			// 로그인 성공하면, 세션에 로그인 결과를 남기고, 브라우저가 종료될때까지 로그인 상태 유지
			HttpSession session =  req.getSession();
			session.setAttribute("loginMember", loginMember);

			// redirect로 이동시 post 로그인 정보가 사라짐!
			resp.sendRedirect(req.getContextPath()+"/"); // 메인 페이지로 이동
		}else { // 로그인 실패!!
			// 로그인 실패하면, 사용자에게 실패를 알리는 페이지로 이동한다.
			
			// 실패 상황을 알려주는 메세지와 이동할 경로를 작성
			req.setAttribute("msg", "사용자 아이디나 비밀번호가 맞지 않습니다!");
			req.setAttribute("location", "/");
			
			// 공통 메세지 창에 메세지를 띄우고, 다음 이동할 페이지를 알려줌 
			//    -> 이때 로그인 정보와 Attribute 정보를 유지해야함으로 forward 이동
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		}
	}
}







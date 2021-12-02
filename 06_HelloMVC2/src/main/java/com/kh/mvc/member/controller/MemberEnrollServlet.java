package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name="enroll" ,urlPatterns = "/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/views/member/enroll.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = new Member();
	
		// 예외처리 부가 생략된 코드.
		// 현업에서는 반드시 보안 예외사항 또는 null이 있는지 확인해줘야한다.
		try { 
			member.setId(req.getParameter("userId"));
			member.setPassword(req.getParameter("userPwd"));
			member.setName(req.getParameter("userName"));
			member.setPhone(req.getParameter("phone"));
			member.setEmail(req.getParameter("email"));
	    	member.setAddress(req.getParameter("address"));
			member.setHobby(String.join(",", req.getParameterValues("hobby")));
			
			System.out.println(member.toString());
			
		} catch (Exception e) {
			req.setAttribute("msg", "회원가입 실패!");
			req.setAttribute("location", "/member/enroll");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
    		return;
		}
		
		int result = service.save(member);
		
		if(result > 0) {
			req.setAttribute("msg", "회원가입 성공!");
			req.setAttribute("location", "/");
		}else {
			req.setAttribute("msg", "회원가입 실패!");
			req.setAttribute("location", "/member/enroll");
		}
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}

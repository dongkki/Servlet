package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			
			Member modifyMember = new Member();
			modifyMember.setNo(loginMember.getNo());
			modifyMember.setId(req.getParameter("userId"));
			modifyMember.setName(req.getParameter("userName"));
			modifyMember.setPhone(req.getParameter("phone"));
			modifyMember.setEmail(req.getParameter("email"));
			modifyMember.setAddress(req.getParameter("address"));
			modifyMember.setHobby(String.join(",", req.getParameterValues("hobby")));
			
			if(loginMember.getId().equals(modifyMember.getId()) == false) {
				sendCommonPage("잘못된 아이디 입니다.", "/", req, resp);
				return;
			}
			
			int result = service.save(modifyMember);
			
			if(result <= 0) {
				sendCommonPage("회원정보를 수정할 수 없습니다.", "/member/view", req, resp);
				return;
			}
			sendCommonPage("회원정보가 수정되었습니다.", "/member/view", req, resp);
			
		} catch (Exception e) {
			sendCommonPage("잘못된 접근입니다.", "/", req, resp);
		}
	}
	
	void sendCommonPage(String msg, String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", path);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}

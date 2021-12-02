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

@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getId();

			Member member = service.findMemberById(memberId);
			member.getId();	// member가 없으면 null point error 발생!
			
			req.setAttribute("member", member);
			req.getRequestDispatcher("/views/member/view.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}
}

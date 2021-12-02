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

@WebServlet(name = "updatePwd", urlPatterns = "/member/updatePwd")
public class MemberUpdatePwdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/updatePwd.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String newPassword = req.getParameter("userPwd");
			
			if(loginMember == null) {
				sendCommonPage("잘못된 접근입니다.", "/", req, resp);
				return;
			}
			
			int result = service.updatePassword(loginMember.getNo(), newPassword);
			
			if(result <= 0) {
				sendCommonPage("비밀번호를 수정할 수 없습니다.", "/member/view", req, resp);
				return;
			}
			
			sendCommonPage("비밀번호가 수정되었습니다.", null, req, resp);
			
		} catch (Exception e) {
			sendCommonPage("잘못된 접근입니다.", "/", req, resp);
		}
	}
	
	void sendCommonPage(String msg, String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", path);
		req.setAttribute("script", "self.close()");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}

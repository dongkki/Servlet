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

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService service = new MemberService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMemeber = (Member) session.getAttribute("loginMember");

			if (loginMemeber == null) {
				sendCommonPage("잘못된 접근입니다.", "/", req, resp);
				return;
			}

			int result = service.delete(loginMemeber.getNo());

			if (result <= 0) {
				sendCommonPage("현재 탈퇴 할 수 없습니다. 문의 바랍니다.", "/", req, resp);
				return;
			}

			sendCommonPage("정상적으로 탈퇴 되었습니다.", "/logout", req, resp);
		} catch (Exception e) {
			sendCommonPage("잘못된 접근입니다.", "/", req, resp);
		}
	}

	public void sendCommonPage(String msg, String path, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", path);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}

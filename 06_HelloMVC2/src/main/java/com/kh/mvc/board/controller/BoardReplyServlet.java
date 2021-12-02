package com.kh.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/board/reply")
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService service = new BoardService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			String writer = req.getParameter("writer");
			String content = req.getParameter("content");
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");

			if (member.getId().equals(writer) == true) {
				Reply reply = new Reply();
				reply.setBoardNo(boardNo);
				reply.setWriterId(writer);
				reply.setWriterNo(member.getNo());
				reply.setContent(content);
				int result = service.saveReply(reply);
				if (result <= 0) {
					sendCommonPage("댓글 등록에 실패했습니다!", ("/board/view?boardNo=" + boardNo), req, resp);
					return;
				}
			} else {
				sendCommonPage("로그인 된 아이디와 다릅니다.", "/", req, resp);
				return;
			}
			req.setAttribute("msg", "댓글 등록 성공!");
			req.setAttribute("location", "/board/view?boardNo=" + boardNo);
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		} catch (Exception e) {
			sendCommonPage("잘못된 접근 입니다.", "/", req, resp);
			sendCommonPage("잘못된 접근 입니다.", "/", req, resp);
			return;
		}
	}

	void sendCommonPage(String msg, String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", path);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}

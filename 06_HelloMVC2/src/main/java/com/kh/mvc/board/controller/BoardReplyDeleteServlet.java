package com.kh.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;

@WebServlet("/board/replydel")
public class BoardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int replyNo = Integer.parseInt(req.getParameter("replyNo"));
			System.out.println("리플 삭제 번호 : " + replyNo);
			int result = service.deleteReply(replyNo);

			if (result > 0) {
				req.setAttribute("msg", "리플 삭제 성공");
			} else {
				req.setAttribute("msg", "리플 삭제 실패");
			}
		} catch (Exception e) {
			req.setAttribute("msg", "리플 삭제 실패");
		}
		req.setAttribute("location", "/board/list");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}

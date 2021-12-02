package com.kh.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.member.model.vo.Member;

// 파일첨부 없는 순수 게시판 기능
//@WebServlet("/board/write")
public class BoardWriteServlet_Origin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			Member member = (Member) session.getAttribute("loginMember");
			if(member != null) {
				req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {}
		
		// 비정상적 흐름
		req.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
		req.setAttribute("location", "/");
 		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String title = req.getParameter("title");
			String writer = req.getParameter("writer");
			String content = req.getParameter("content");
			
			HttpSession session = req.getSession(false);
			Member member = (Member) session.getAttribute("loginMember");
			
			if(member.getId().equals(writer) == false) { // 세션으로 다시 사용자 확인
				sendErrorPage(req, resp);
				return;
			}
			
			System.out.println(member);
			Board board = new Board();
			board.setTitle(title);
			board.setWriterId(writer);
			board.setWriterNo(member.getNo());
			board.setContent(content);
			System.out.println(board);
			
			int result = service.save(board);
			
			if(result <= 0) {
				sendErrorPage(req, resp);
				return;
			}
			req.setAttribute("msg", "게시글이 등록 되었습니다.");
			req.setAttribute("location", "/board/list");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorPage(req, resp);
		}
	}
	
	private void sendErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", "게시글 등록에 실패 했습니다.");
		req.setAttribute("location", "/board/list");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}

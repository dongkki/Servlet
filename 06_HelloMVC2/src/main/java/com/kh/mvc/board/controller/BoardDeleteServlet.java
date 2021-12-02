package com.kh.mvc.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			Board board = service.findBoardByNo(boardNo, false);
			int result = service.delete(boardNo);
			
			if(result > 0) {
				delete(board);
				req.setAttribute("msg", "게시글 삭제 성공");
			}else {
				req.setAttribute("msg", "게시글 삭제 실패");
			}
		} catch (Exception e) {	
			req.setAttribute("msg", "게시글 삭제 실패");
		}
		req.setAttribute("location", "/board/list");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
	
	private void delete(Board board) {
		try {
			String path = getServletContext().getRealPath("/resources/upload/board");
			if(path != null && path.length() > 0) {
				File deletefile = new File(path, board.getRenamedFileName());
				deletefile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

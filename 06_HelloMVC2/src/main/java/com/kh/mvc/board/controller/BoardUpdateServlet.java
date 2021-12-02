package com.kh.mvc.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyFileRenamePolicy;
import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		Board board = service.findBoardByNo(boardNo, true);
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/views/board/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = getServletContext().getRealPath("/resources/upload/board");
			int maxSize = 104857600; // 100MB
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			
			
			// 새로 업데이트 할 파일이 아니기 때문에 파라미터
			int boardNo = Integer.parseInt(mr.getParameter("boardNo"));
			String title = mr.getParameter("title");
			String writer = mr.getParameter("writer");
			String content = mr.getParameter("content");
			String originFileName = mr.getParameter("originalFileName");
			String renamedFileName = mr.getParameter("renameFileName");
			
			// 업데이트하기 때문에 이렇게
			String originReloadFile = mr.getOriginalFileName("reloadFile");
			String renamedReloadFileName = mr.getFilesystemName("reloadFile");

			if(originReloadFile != null && originReloadFile.length() > 0) {
				try {
					File deleteOldfile = new File(path, renamedFileName);
					deleteOldfile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			HttpSession session = req.getSession(false);
			Member member = (Member) session.getAttribute("loginMember");
			
			if(member.getId().equals(writer) == false) { // 세션으로 다시 사용자 확인
				sendErrorPage(req, resp);
				return;
			}
			
			System.out.println(member);
			
			Board board = new Board();
			board.setNo(boardNo);
			board.setTitle(title);
			board.setWriterId(writer);
			board.setWriterNo(member.getNo());
			board.setContent(content);
			
			if(originFileName != null) {
				board.setOriginalFileName(originFileName);
				board.setRenamedFileName(renamedFileName);
			}else {
				board.setOriginalFileName("");
				board.setRenamedFileName("");
			}
			
			System.out.println(board);
			
			int result = service.save(board);
			
			if(result <= 0) {
				sendErrorPage(req, resp);
				return;
			}
			req.setAttribute("msg", "게시글이 수정 되었습니다.");
			req.setAttribute("location", "/board/view?boardNo=" + board.getNo());
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorPage(req, resp);
		}
	}
	
	private void sendErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", "게시글 수정에 실패 했습니다.");
		req.setAttribute("location", "/board/list");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}

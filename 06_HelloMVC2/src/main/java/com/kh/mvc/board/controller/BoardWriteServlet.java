package com.kh.mvc.board.controller;

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

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet{
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
			// 멀티 파라메터 처리 및 파일 받아오는 객체 생성
			
			// 1. 저장 경로 지정
			String path = getServletContext().getRealPath("/resources/upload/board");
			// 절대 경로 셋팅하는 법
//			String path = "c:\\download";
			
			// 2. 파일 사이즈 지정
			int maxSize = 104857600; // 100MB
			// 3. 문자열 인코딩 설정
			String encoding = "UTF-8";
			
			//DefaultFileRenamePolicy : 중복된 이름 뒤에 0~9999 번호를 붙인다.
//			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new DefaultFileRenamePolicy());
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			// 멀티 파라메터 처리부 끝
			
			String title = mr.getParameter("title");
			String writer = mr.getParameter("writer");
			String content = mr.getParameter("content");
			String originFileName = mr.getOriginalFileName("upfile");
			String renamedFileName = mr.getFilesystemName("upfile");
			
			
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

package com.kh.mvc.board.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.board.model.dao.BoardDAO;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	public int getBoardCount() {
		Connection connection = getConnection();
		int count = dao.getBoardCount(connection);
		close(connection);
		return count;
	}

	public List<Board> getBoardList(PageInfo pageInfo) {
		Connection connection = getConnection();
		List<Board> list = dao.findAll(connection, pageInfo);
		close(connection);
		return list;
	}

	public int save(Board board) {
		Connection connection = getConnection();
		int result = 0;
		
		if(board.getNo() != 0) {
			result = dao.updateBoard(connection, board);
		}else {
			result = dao.insertBoard(connection, board);
		}
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}

		close(connection);
		
		return result;
	}

	public Board findBoardByNo(int boardNo, boolean hasRead) {
		Connection connection = getConnection();
		Board board = dao.findBoardByNo(connection, boardNo);
		
		// 조회수 증가 로직 추가
		if(hasRead == true && board !=null) {
			int result = dao.updateReadCount(connection, board);
			if(result > 0 ) {
				commit(connection);
			}else {
				rollback(connection);
			}
		}
		
		close(connection);
		return board;
	}

	public int delete(int boardNo) {
		int result = 0;
		Connection connection = getConnection();

		result = dao.updateStatus(connection, boardNo, "N");
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		return result;
	}
	
	public int deleteReply(int replyNo) {
		int result = 0;
		Connection connection = getConnection();
		
		result = dao.deleteReply(connection, replyNo, "N");
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		return result;
	}

	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();

		result = dao.insertReply(connection, reply);
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		return result;
	}
}

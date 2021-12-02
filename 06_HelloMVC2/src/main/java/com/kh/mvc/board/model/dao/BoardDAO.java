package com.kh.mvc.board.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.jdbc.JDBCTemplate;
import com.kh.common.util.PageInfo;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;

public class BoardDAO {
	
	// 게시물의 갯수를 가져오는 쿼리문
	public int getBoardCount(Connection connection) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM BOARD WHERE STATUS='Y'";
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next() == true) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	// 게시물 정보를 가져오는 객체
	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
				+ "FROM ("
				+ "    SELECT ROWNUM AS RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
				+ "    FROM ("
				+ "        SELECT  B.NO, B.TITLE, M.ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS"
				+ "        FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "        WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
				+ "    )"
				+ ")"
				+ "WHERE RNUM BETWEEN ? and ?";
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				Board board = new Board();
				board.setNo(rs.getInt("NO"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 글쓰기 기능
	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, board.getWriterNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	// 게시글을 가져오는 객체
	public Board findBoardByNo(Connection connection, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT  B.NO, B.TITLE, M.ID, B.READCOUNT, B.ORIGINAL_FILENAME, B.RENAMED_FILENAME, B.CONTENT, B.CREATE_DATE, B.MODIFY_DATE "
				+ "FROM BOARD B "
				+ "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "WHERE B.STATUS = 'Y' AND B.NO=? ";
		Board board = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				board = new Board();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
				board.setReplies(getRepliesByNo(connection, boardNo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return board;
	}
	
	
	// 게시글 조회수 올려주는 쿼리
	public int updateReadCount(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE BOARD SET READCOUNT=? WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			board.setReadCount(board.getReadCount() + 1);
			
			pstmt.setInt(1, board.getReadCount());
			pstmt.setInt(2, board.getNo());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}
	
	// 게시글 삭제(실제로는 안보여주기 기능) 를 위한 쿼리
	public int updateStatus(Connection connection, int boardNo, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE BOARD SET STATUS=? WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE BOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 리플을 가져오는 메소드
	public List<Reply> getRepliesByNo(Connection connection, int boardNo) {
		List<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT R.NO, R.BOARD_NO, R.CONTENT, M.ID, R.CREATE_DATE, R.MODIFY_DATE "
				+ "FROM REPLY R "
				+ "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
				+ "WHERE R.STATUS='Y' AND BOARD_NO= ? "
				+ "ORDER BY R.NO DESC";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setNo(rs.getInt("NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setWriterId(rs.getString("ID"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setModifyDate(rs.getDate("MODIFY_DATE"));
				list.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 리플 쓰기 기능
	public int insertReply(Connection connection, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());
			
			result = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteReply(Connection connection, int replyNo, String string) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "DELETE REPLY WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Connection connection = JDBCTemplate.getConnection();
		BoardDAO dao = new BoardDAO();
		
		Board board = new Board(0, 0, 1, "admin", "test", "내용물", "", "", 0, null, null, null, null);
		int result = dao.insertBoard(connection, board);
		commit(connection);
		System.out.println("게시물 입력결과 : " + result);
		
		int count = dao.getBoardCount(connection);
		System.out.println("게시물 갯수 : " + count);
		PageInfo pageinfo = new PageInfo(1, 10, count, 10);
		List<Board> list = dao.findAll(connection, pageinfo);
		for(Board item : list) {
			System.out.println(item);
		}
		System.out.println("--------------------------------------------------");
		
		board = dao.findBoardByNo(connection, 50);
		System.out.println(board);
	}

}





















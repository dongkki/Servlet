package com.kh.mvc.member.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mvc.member.model.vo.Member;

public class MemberDAO {
	
	public Member findMemberById(Connection connection, String id) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setHobby(rs.getString("HOBBY"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return member;
	}
	
	public int insertMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getHobby());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE MEMBER SET NAME=?,PHONE=?,EMAIL=?,ADDRESS=?,HOBBY=?,MODIFY_DATE=SYSDATE WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setInt(6, member.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE MEMBER SET STATUS=? WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePassword(Connection connection, int no, String password) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE MEMBER SET PASSWORD=? WHERE NO=?";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, password);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
//	public static void main(String[] args) {
//		// 테스트 용도의 main
//		Connection connection = JDBCTemplate.getConnection();
//		MemberDAO memberDao = new MemberDAO();
//		Member admin = memberDao.findMemberById(connection, "admin");
//		System.out.println(admin);
//		Member member = new Member(0, "test12", "1234", "", "홍길동", "01021342121", 
//										"test@email", "서울시 어딘가", "축구", null, null, null);
//		memberDao.insertMember(connection, member);
//		
//		int result= memberDao.updatePassword(connection, 3, "4321");
//		commit(connection);
//	
//		System.out.println(result);
//		Member serarchMember = memberDao.findMemberById(connection, "test12");
//		System.out.println(serarchMember);
//		commit(connection);
//	}
}

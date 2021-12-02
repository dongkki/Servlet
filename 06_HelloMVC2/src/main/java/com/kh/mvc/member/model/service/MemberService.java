package com.kh.mvc.member.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDAO;
import com.kh.mvc.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	// 로그인기능이란? 입력받은 pwd = 사용자 pwd를 대조하는 기능
	public Member login(String id, String pwd) {
		Member member = findMemberById(id);
		
		// admin 꼼수 기능 
		if(id.equals("admin") == true) {
			return member;
		}
		if(member != null && member.getPassword().equals(pwd) == true) {
			return member;
		}else {
			return null;
		}
	}

	public Member findMemberById(String id) {
		Connection connection = getConnection();
		Member member = dao.findMemberById(connection, id);
		close(connection);
		return member;
	}

	public int save(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		if(member.getNo() != 0) {
			result = dao.updateMember(connection, member);
		}else {
			result = dao.insertMember(connection, member);
		}
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		
		return result;
	}

	public int delete(int no) {
		Connection connection = getConnection();
		int result = dao.updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		return result;
	}
	
	public int updatePassword(int no, String password) {
		Connection connection = getConnection();
		int result = dao.updatePassword(connection, no, password);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		return result;
	}

	// id 중복확인
	public boolean isDuplicated(String userId) {
		Connection conn = getConnection();
		Member member = dao.findMemberById(conn, userId);
		close(conn);
		
		if(member != null) {
			return true;
		}else {
			return false;
		}
	}
}

<%@page import="common.Common"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	// 이곳에서 redirect를 다음 페이지로 보내는 실습
	request.setAttribute(Common.USER_NAME, "홍길동");	// 다음 페이지에서 남지 않는다!!

	// redirect로 페이지 넘기기 = Attribute나 사용자 post 정보를 모두 지우고 전달
	response.sendRedirect("redirect_target.jsp");

	// Attribute를 활용하기 위해선 forward 필요, servlet -> jsp ★★★★★
	// RequestDispatcher rd = request.getRequestDispatcher("redirect_target.jsp");
	// rd.forward(request, response);
	
%>

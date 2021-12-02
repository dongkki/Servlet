<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		application.setAttribute("name", "고길동");
		session.setAttribute("name", "징징이");
		request.setAttribute("name", "스폰지밥");
		pageContext.setAttribute("name", "뚱이");
		
		pageContext.forward("scopeTest3.jsp");
	%>

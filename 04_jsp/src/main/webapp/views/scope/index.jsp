<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Scope 객체간 생존 주기 실습</h3>
	
	<%
		application.setAttribute("name", "고길동");
		session.setAttribute("name", "징징이");
		request.setAttribute("name", "스폰지밥");
		pageContext.setAttribute("name", "뚱이");
	%>
	
	application = <%= application.getAttribute("name") %> <br>
	session = <%= session.getAttribute("name") %> <br>
	request = <%= request.getAttribute("name") %> <br>
	pageContext = <%= pageContext.getAttribute("name") %> <br>
	
	<a href="scopeTest.jsp">다음 페이지</a>

</body>
</html>
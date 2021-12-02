<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Scope 객체간 생존 주기 실습 Round 1, 페이지 이동 1번</h3>
	<p> 결과 : a태그 활용 시 redirect와 같은 효과로 request, pageContext 초기화 됨</p><br>
	
	application = <%= application.getAttribute("name") %> <br>
	session = <%= session.getAttribute("name") %> <br>
	request = <%= request.getAttribute("name") %> <br>
	pageContext = <%= pageContext.getAttribute("name") %> <br>
	
	<a href="scopeTest2.jsp">다음 페이지</a>
	
</body>
</html>
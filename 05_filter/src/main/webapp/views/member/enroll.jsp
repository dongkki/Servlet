<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// context 값 넣기
		application.setAttribute("test", "testApp");
		session.setAttribute("test", "testSession");
		request.setAttribute("test", "testRequest");
		session.setMaxInactiveInterval(100);
		out.println("session is new : " + session.isNew() + "<br>");
		
		out.println(application.getAttribute("test") + "<br>");
		out.println(session.getAttribute("test") + "<br>");
		out.println(request.getAttribute("test") + "<br>");
	%>
	<h2>회원가입</h2>
	<fieldset>
		<legend>회원가입</legend>
		<form action="enrollResult.jsp" method="post">
		<label>아이디 : <input type="text" name="id"></label> <br>
		<label>패스워드 : <input type="password" name="password"></label> <br>
		<label>이름 : <input type="text" name="name"></label> <br>
		<label>생년월일 : <input type="text" name="day"></label> <br>
		<div>
			<input type="submit" value="회원가입">
			<input type="reset" value="리셋">
		</div>
		</form>
	</fieldset>
</body>
</html>
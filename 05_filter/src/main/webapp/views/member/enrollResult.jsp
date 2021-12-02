<%@page import="com.kh.util.AES256Cipher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <h2>회원가입 결과</h2>
	 <hr><br>
	 
	 <div>이름 : <%= request.getParameter("name") %> </div>
	 <div>아이디 : <%= request.getParameter("id") %> </div>
	 <div>패스워드 : <%= request.getParameter("password") %> </div>
	 <div>생년월일 : <%= request.getParameter("day") %> </div>
	 <%
	 	String enDay = request.getParameter("day");
	 	String deDay = AES256Cipher.AES_Decode(enDay);
	 %>
	 <div>생년월일(해석된) : <%=deDay %></div>
</body>
</html>
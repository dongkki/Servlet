<%@page import="common.Common"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Redirect 홈페이지</h3>
	- redirect된 페이지 입니다.<br>
	<% String name = (String)request.getAttribute(Common.USER_NAME); %>
	<%= name %>
</body>
</html>
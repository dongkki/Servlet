<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%	// 로컬 변수 영역
	String userName ="고길동";
	String loginDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
%>

<header>
	<h5 align="left"> <%= userName %> 님 로그인 환영합니다!, 로그인 시간 : <%= loginDate %></h5>
	<h1>My JSP Page</h1>
	<div align="left">
		<a href="#">메뉴1</a>
		<a href="#">메뉴2</a>
		<a href="#">메뉴3</a>
	</div>
</header>
<hr>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP 내장 객체</h2>
	<hr>
	
	<h3>1. request 객체</h3>
	<h4>1.1 헤더와 관련된 메소드</h4>
	- 주요한 사용처 : IP를 통한 사용자 접속 차단.<br>
	<%
		Enumeration<String> headerNames = request.getHeaderNames();
	
		while(headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			out.append("key : " + headerName + ", value : " + request.getHeader(headerName) + "<br>");
		}
	%>
	<hr><br>
	
	<h4>1.2 URL/URI, 요청 방식과 관련된 메소드</h4>
	
	서버 도메인명★ :  <%= request.getServerName() %><br>
	서버 포트번호★ : <%= request.getServerPort() %><br>
	웹 애플리케이션 경로(Context Path)★ : <%= request.getContextPath() %><br>
	서버 URL : <%= request.getRequestURL() %><br>
	서버 URI : <%= request.getRequestURI() %><br>
	프로토콜 : <%= request.getProtocol() %><br>
	요청방식(GET, POST)★ : <%= request.getMethod() %><br>
	요청 쿼리 <%= request.getQueryString() %><br>
	클라이언트 호스트명 : <%= request.getRemoteHost() %><br>
	클라이언트 IP 주소 : <%= request.getRemoteAddr() %><br>
	<hr><br>
	
	<h3>2. response 객체</h3>
	- 서블릿에서 사용하던 response 역활과 같은데, JSP에는 주로 redirect 용도로 활용.
	- cookie 활용시에도 사용.
	
	- redirect란? 응답 객체를 새 객체로 만들어 전달함으로 요청/응답 정보가 남지 않는다!<br>
	- forward란? 
	<a href="redirect.jsp"> redirect test</a>
	
</body>
</html>
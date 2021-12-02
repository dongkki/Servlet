<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		td{
			text-align: center;
		}
	</style>
</head>
<body>
	<!-- HTML 주석 : 사용자가 볼 수 있음 -->
	<%--JSP 주석 : JSP로만 볼 수 있음 -> 사용자에게 노출 안됨 --%>

	<h1>JSP 스크립트 요소(Elements) 실습</h1>
	<%-- 선언부 --%>
	<%!	// 멤버변수나 메소드 선언하는 분
		public static final String TEST_ID = "testID";
		public String userName = "홍길동";
		
		String getName(){
			return this.userName;
		}

	%>
	<hr><br>
	
	<%-- 출력부 기본꼴 (추천) --%>
	<%= userName %>
	<br>
	
	<%= // 출력하는 부 = Tip : 복잡하게 쓰지 말것!
		getName() + TEST_ID
		// = out.print(getName() + TEST_ID); 와 같은 문법
	%>
	<br>
	
	<%	// 일반 자바문법 = 추천하지 않는 문법!
		out.print(getName() + TEST_ID);
	%>
	<hr><br>
	
	<%-- 일반 문법 --%>
	<h3>일반 문법 실습</h3>	
	<%	// 일반 Java 식
		if(userName.equals("홍길동") == true){
			out.print("홍길동 입니다.");
		}else{
			out.print("홍길동이 아닙니다.");
		}
	%>
	<hr><br>
	
	<%	// JSP식 - 1
		if(userName.equals("홍길동") == true){
	%>
		<p>저는 홍길동 입니다!!</p>
	<%
		} else {
	%>
		<p>저는 홍길동이 아닙니다!!</p>
	<%
		}
	%>
	
	<%	// JSP식 - 2
		if(userName.equals("홍길동") == true){
	%>
		<p>저는 <%= userName %> 입니다!!</p>
	<%
		} else {
	%>
		<p>저는 홍길동이 아닙니다!!</p>
		<p>저는 <%= userName %> 입니다!!</p>
	<%
		}
	%>
	<hr><br>
	
	<%-- 게시판 실습부 --%>
	<h3>게시판 실습부(=반복문 실습)</h3>
	
	<table border="1">
	<tr>
		<th width="40px">번호</th>
		<th width="200px">게시글 이름</th>
		<th width="80px">작성자</th>
		<th width="80px">작성일</th>
	</tr>
	<% for(int i = 0; i < 10; i++) { %>
		<tr>
			<td><%= (i+1) %></td>
			<td><a href=#><%= "게시글 이름 " + (i+1) %></a></td>
			<td><%= "홍길동 " + (i+1) %></td>
			<td><%= "21/11/" + (i+10) %></td>
		</tr>
	<% } %>
	</table>
	
</body>
</html>
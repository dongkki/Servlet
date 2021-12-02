<%@page import="com.kh.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
	String saveID = "";
	Member loginMember = (Member)session.getAttribute("loginMember");
	Cookie[] cookies = request.getCookies();

	if(cookies != null){
		for(Cookie c : cookies){
			if(c.getName().equals("saveId") == true){
				saveID = c.getValue();
				break;
			}
		}
	}
%>    
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KH 게시판</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/main.css" >
	<script src="<%= request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>

<body>

	<header>
		<h2>Hello MVC2</h2>
		<div class="login-container">
		<%if(loginMember == null){%>
		<%--일반 로그인 폼 영역 시작--%>
			<form id="loginFrm" action="<%=request.getContextPath() %>/login" method="post">
				<table>
					<tr>
						<td>
							<input type="text" name="userId" id="userId" value="<%= saveID %>" placeholder="아이디" required="required">
						</td>
					</tr>				
					<tr>
						<td>
							<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required="required">
						</td>
						<td>
							<input type="submit" value="로그인">
						</td>
					</tr>			
					<tr>
					<td colspan="2">
						<label><input type="checkbox" name="saveId" <%= saveID.length() == 0 ? "" : "checked"  %>>아이디 저장</label>
						<input type="button" value="회원가입" onclick="location.href='<%= request.getContextPath()%>/member/enroll';"/>
					</td>
					</tr>	
				</table>
			</form>
		<%--일반 로그인 폼 영역 종료--%>
		<%	}else{ %>
		<%--로그인 된 사용자 정보 보여주는 폼 영역 시작--%>
			<table>
				<tr>
					<td colspan="2">
						<%= loginMember.getName() %> 님 안녕하세요?
					</td>
				</tr>
				<tr>
					<td>	
						<button onclick="location.href = '<%=request.getContextPath()%>/member/view';">내정보</button>
					</td>
					<td>
						<button onclick="location.replace('<%= request.getContextPath() %>/logout')">로그아웃</button>
					</td>
				</tr>
			</table>
		<%--로그인 된 사용자 정보 보여주는 폼 영역 종료--%>
		<% 	}%>
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>		
				<li class="notice"><a>공지사항</a></li>
				<li class="board"><a href="<%=request.getContextPath()%>/board/list">게시판</a></li>
				<% 
					if(loginMember != null && loginMember.getRole().equals("ROLE_ADMIN")){
				%>						
						<li class="admin-member">
							<a href="<%=request.getContextPath()%>/admin/member"> 회원관리</a>
						</li>	
				<%
					}
				%>
			</ul>
		</nav>
	</header>
		

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
		request.setCharacterEncoding("UTF-8");
		String 	 name   = request.getParameter("name");
		String 	 age    = request.getParameter("age");
		String 	 gender = request.getParameter("gender");
		String 	 height = request.getParameter("height");
		String[] foods  = request.getParameterValues("food");
	%>

	<h3>사용자 입력 정보 출력</h3>
	 - 이름 : <%=name %><br>
	 - 나이 : <%=age %><br>
	 - 성별 : <%=gender %><br>
	 - 신장 : <%=height %><br>
	 - 좋아하는 음식 : <br>
	 <% for(int i = 0; i < foods.length; i++) {%>
	 	<%= "" + foods[i] + "<br>" %>
	 <%} %>
</body>
</html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%

	Member member = (Member) request.getAttribute("member");
	List<String> hobbies = null;
	try{
		hobbies = Arrays.asList(member.getHobby().split(","));
		
	}catch(Exception e){
		hobbies = new ArrayList<String>();
	}

%>

<style>
	section #view-container {
		text-align:center;
	}
	
	section #view-container input {
		margin:3px;
	}
	
	section #view-container table {
		margin:0 auto;
	}
	
	section #view-container table th {
		padding:0 10px; 
		text-align:right;
	}
	
	section #view-container table td {
		padding:0 10px; 
		text-align:left;
	}
</style>

<section id="content">
	<h2 align="center">회원정보수정</h2>
	<div id="view-container">
		<form id="memberFrm" action="<%= request.getContextPath() %>/member/update" method="POST">
			<table>
				<tr>
	                <th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" 
							value="<%= member.getId() %>" readonly required >
					</td> 	
	            </tr>
	            <tr>
	                <th>이름</th>
					<td>
						<input type="text" name="userName" id="userName" 
							value="<%= member.getName() %>" required>				
					</td> 	
	            </tr>
      	        <tr>
	                <th>휴대폰</th>
	                <td>
	                    <input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" 
	                    	maxlength="11" value="<%= member.getPhone() %>">
	                </td>
	            </tr>
	            <tr>
	                <th>이메일</th>
					<td>
						<input type="email" placeholder="abc@abc.com" name="email" id="email"
							 value="<%= member.getEmail() %>">												
					</td> 	
	            </tr>
	            <tr>
	                <th>주소</th>
						<td>
							<input type="text" name="address" id="address" 
								value="<%= member.getAddress() %>">
						</td> 	
	            </tr>
	            <tr>
	               	<th>취미</th>
					<td>
						<label><input type="checkbox" name="hobby" id="hobby0" value="운동" <%= hobbies.contains("운동") ? "checked" : "" %>>운동</label>
						<label><input type="checkbox" name="hobby" id="hobby1" value="등산" <%= hobbies.contains("등산") ? "checked" : "" %>>등산</label>
						<label><input type="checkbox" name="hobby" id="hobby2" value="독서" <%= hobbies.contains("독서") ? "checked" : "" %>>독서</label>
						<label><input type="checkbox" name="hobby" id="hobby3" value="게임" <%= hobbies.contains("게임") ? "checked" : "" %>>게임</label>
						<label><input type="checkbox" name="hobby" id="hobby4" value="여행" <%= hobbies.contains("여행") ? "checked" : "" %>>여행</label>
					</td> 		
	            </tr>
	        </table>
	        <input type="button" id="updatePwd" value="비밀번호변경" />
	        <input type="submit" value="정보수정" />
	        <input type="button" id="deleteMember" value="탈퇴" />
	 	</form>
 	</div>
</section>
<script>
	$(document).ready(() => {
		$("#deleteMember").on("click", (e) => {
			if(confirm("정말로 탈퇴하시겠습니까?!")) {
				location.replace('<%= request.getContextPath() %>/member/delete');
			}
		});
		
		$("#updatePwd").on("click", (e) => {
			// 비밀번호 변경 창을 띄우기
			const url = "<%= request.getContextPath() %>/member/updatePwd";
			const status = "left=500px,top=200px,width=400px,height=210px"
			
			open(url, "", status);
		});
	});
</script>

<%@ include file="/views/common/footer.jsp" %>
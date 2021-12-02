<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error/error.jsp"%>
    
<!-- 
	include 전략
	1. HTML 헤더나 끝 부분을 공용으로 사용하게끔 설계(현재 코드)
	2. JSP 페이지에서 HTML 헤더를 가지고 있고 메뉴바나 헤더 코드부를 별도로
 -->

<!-- 헤더 내용을 변경하지 않고 모두 include 할때는 헤더 내용을 헤더에 기입 -->
<%@ include file="/views/common/header.jsp" %>

<% //int value = 10/0;  %>

<h1>지시어(directive) 실습</h1>

<section>
	<h4>개인 정보 입력</h4>
	
	<p>데이터 입력 후 확인 버튼을 눌러주세요.</p>
	<form action="userFormResult.jsp" method="POST">
		<label>이름 : <input type="text" name="name" size="10"/></label><br>
		<label>나이 : <input type="text" name="age" size="10"/></label><br>
		<label>성별 : </label> 
		<label><input type="radio" name="gender" value="남자"/>남자</label>
		<label><input type="radio" name="gender" value="여자"/>여자</label><br>
		<label>키 : <input type="range" name="height" min="120" max="300" step="1"/></label><br>
		
		<label>좋아하는 음식 : </label>
		<label><input type="checkbox" name="food" value="한식"/>한식</label>
		<label><input type="checkbox" name="food" value="분식"/>분식</label>
		<label><input type="checkbox" name="food" value="중식"/>중식</label>
		<label><input type="checkbox" name="food" value="양식"/>양식</label>
		<label><input type="checkbox" name="food" value="일식"/>일식</label>
		
		<br><br>
		
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</form>
</section>

<!-- footer의 끝을 body로 하지 않고 직접 footer를 관리 할 때 사용 -->
<%@ include file="/views/common/footer.html" %>
	
</body>
</html>
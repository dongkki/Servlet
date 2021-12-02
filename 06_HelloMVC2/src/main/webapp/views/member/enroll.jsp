<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
 		<style>
            section #enroll-container {
                text-align: center;
            }
            
            section #enroll-container input {
                margin: 3px;
            }
            
            section #enroll-container table {
                margin: 0 auto;
            }
            
            section #enroll-container table th {
                padding: 0 10px;
                text-align: right;
            }
            
            section #enroll-container table td {
                padding: 0 10px;
                text-align: left;
            }
        </style>
        
        
        <section id="content">
            <h3 align="center">회원 가입 정보</h3>

            <div id="enroll-container">
                <form name="memberEnrollFrm" action="<%= request.getContextPath() %>/member/enroll" method="POST">
                    <table>
                        <tr>
                            <th>아이디</th>
                            <td>
                            	<input type="text" name="userId" id="newId" placeholder="아이디(4글자이상)" required /> 
                            	<input type="button" id="checkDuplicate1" value="중복검사1" />
                             	<input type="button" id="checkDuplicate2" value="중복검사2" />
                             </td>
                        </tr>
                        <tr>
                            <th>패스워드</th>
                            <td><input type="password" name="userPwd" id="pass1" required />
                            </td>
                        </tr>
                        <tr>
                            <th>패스워드 확인</th>
                            <td><input type="password" id="pass2" /></td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td><input type="text" name="userName" required /></td>
                        </tr>
                        <tr>
                            <th>휴대폰</th>
                            <td><input type="tel" name="phone" maxlength="11" placeholder="(-없이)01012345678"></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td><input type="email" name="email" placeholder="abc@abc.com" />
                            </td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td><input type="text" name="address" /></td>
                        </tr>
                        <tr>
                            <th>취미</th>
                            <td><label><input type="checkbox" name="hobby"
							value="운동" />운동</label> <label><input type="checkbox"
							name="hobby" value="등산" />등산</label> <label><input
							type="checkbox" name="hobby" value="독서" />독서</label> <label><input
							type="checkbox" name="hobby" value="게임" />게임</label> <label><input
							type="checkbox" name="hobby" value="여행" />여행</label></td>
                        </tr>
                    </table>

                    <input type="submit" id="enrollSubmit" value="가입" /> <input type="reset" value="취소" />
                </form>
                <form name="checkIdForm">
                    <input type="hidden" name="userId">
                </form>
            </div>
        </section>
        
<script>

	// ES6에서 도입된 함수형 언어 문법, Arrow Function 기법
	//  -> ES6 주요 문법 : class(한번 해봄), 상속, 생성자 + 함수형 언어 문법 
	//       기존                      Arrow Function
	// (function(){ .... })     |      () => {....}    // 인자 없을때
	// (function(val){ .... })  |   (val) => {....}    // 인자 1개
	// (function(x, y){ .... }) |  (x, y) => {....}    // 인자 2개...
	//  리턴값이 있을때
	// (function(x, y){ return x+y; }) |  (x, y) => { return x+y;}    // 인자 2개...
	// (function(x, y){ return x+y; }) |  (x, y) => x + y;    // 인자 2개...

	
	$("#checkDuplicate1").click(()=>{
		let id = $("#newId").val().trim();
		if(id.length < 4){
			alert("아이디는 최소 4글자 입니다.");
			return;
		}
		// 동기식 확인 방법
		
		// 새창 띄우는 방법
		let url = "<%=request.getContextPath()%>/member/checkId";
		let title = "duplicate";
		let status = "left=500px,top=100px,width=500px,height=300px";
		
		open("",title,status);
		
		// 미리 선언 된 form 데이터 채우는 법
		checkIdForm.target = title;
		checkIdForm.action = url;
		checkIdForm.method = "post";
		checkIdForm.userId.value = id;
		checkIdForm.submit();
	});
	
	
	$("#checkDuplicate2").click(()=>{
		let id = $("#newId").val().trim();
		if(id.length < 4){
			alert("아이디는 최소 4글자 입니다.");
			return;
		}
		// AJAX를 통해 비동기식 확인하기!
		
		const xhr = new XMLHttpRequest();
		xhr.open("get", "${pageContext.request.contextPath}/member/checkId2?userId=" + id);
		xhr.onreadystatechange = () => {
			if(xhr.readyState == 4 && xhr.status == 200){
				if(xhr.responseText == 'used'){
					alert("중복된 아이디 입니다!");
				}else{
					alert("입력하신 아이디는 사용 가능합니다.");
				}
			}
		};
		xhr.send();
	});

	/*
	$("#pass2").blur((event) => {
	        let pass1 = $("#pass1").val();
	        let pass2 = $(event.target).val();
	
	        if (pass1.trim() != pass2.trim()) {
	            alert("비밀번호가 일치하지 않습니다.");
	
	            $("#pass1").val("");
	            $(event.target).val("");
	            $("#pass1").focus();
	        }
	    }
	);
*/
	
</script>

<%@ include file="/views/common/footer.jsp"%>
        
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="jquery-3.6.0.min.js"></script>
    <style>
        div {
            height: 200px;
            width: 100%;
            border: 1px solid black;
            margin-top: 3px;
        }
    </style>
</head>

<body>
    <h2>AJAX 실습</h2>
    <hr><br>
    <h3>JavaScript를 활용한 AJAX 실습</h3>
    
    
    <h3>1. Get 방식으로 서버 데이터 송수신 방법</h3>
    <button onclick="ajaxTest1()">Get 방식 전송</button><br>
    <div id="div1"></div>
    <script>
        function ajaxTest1() {
            // 1. xhr 객체 생성
            let xhr = new XMLHttpRequest();

            // 2. onreadystatechange 이벤트 리스너 함수 생성 - 전송이 되고 메세지가 바뀐경우 동작하는 함수
            xhr.onreadystatechange = function() {
                // 서버로 부터 요청이 온 경우 시작되는 부
                console.log("readystate : " + xhr.readyState);
                console.log("status : " + xhr.status);
                document.getElementById("div1").innerHTML = xhr.status;
                if (xhr.readyState == 4) { // 페이지 전달 성공
                    if (xhr.status == 200) { // 200 : 성공, 404 : Not found, 500 : 서버 에러, 403 : Forbidden
                        let str = xhr.responseText;
                        document.getElementById("div1").innerHTML = str;
                    }else{
                        document.getElementById("div1").innerHTML = "통신 실패!";
                    }
                }else{
                    document.getElementById("div1").innerHTML = "전송 실패!";
                }
            }

            // 3. xhr open할 객체 셋팅
            let url = "<%=request.getContextPath()%>/jsAjax.do";
            let getParameter = "?name=홍길동&age=23";
            // open(방식, URL, 비동기식 전송 여부)
            xhr.open("GET", url + getParameter, true);

            // 4. xhr 전송
            xhr.send();
        }
    </script>


	<h3>2. Post 방식으로 서버 데이터 송수신 방법</h3>
    <button onclick="ajaxTest2()">post 방식 전송</button><br>
    <div id="div2"></div>
    <script>
        function ajaxTest2() {
            // 1. xhr 생성
        	let xhr = new XMLHttpRequest();

            // 2. onreadystatechange 이벤트 리스너 등록
            xhr.onreadystatechange = function() {
                console.log("readystate : " + xhr.readyState);
                console.log("status : " + xhr.status);
                document.getElementById("div2").innerHTML = xhr.status;
                if (xhr.readyState == 4 && xhr.status == 200) { // 페이지 전달 성공
                    let str = xhr.responseText;
                    document.getElementById("div2").innerHTML = str;
                }
            };

            let url = "<%=request.getContextPath()%>/jsAjax.do";
            let parameter = "name=최길동&age=43";
            
            // 3. xhr open
            xhr.open("POST", url, true);
            
            // 4. Post header 설정 - POST 방식의 파라메터가 있음을 알리는 MIME 셋팅
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            
            // 5. xhr 전송
            xhr.send(parameter);
        }
    </script>
	<hr><br>
		
	<h2>JQuery 방식의 AJAX 실습</h2>

	<h3>1. GET 방식으로 서버 데이터 송수신 방법</h3>
    <button id="ajaxSend1">get 방식 전송</button><br>
	입력 : <input type="text" id="input1"> <br>
    <div id="div3">결과값</div>
    <script>
    	$("#ajaxSend1").click(function(){
    		let inputVal = $("#input1").val();
    		
    	    let url = "<%=request.getContextPath()%>/jqAjax1.do";
    	    
    		$.ajax({ // key - value
    			// 1. type 설정 - get, post
    			type : "get",
    			// 2. url
    			url : url,
    			// 3. data(파라미터)
    			data : {
    				inputVal
    			},
    			// 4. success : AJAX 성공시의 콜백함수(이벤트 리스너)를 등록
    			success : function (result) {
					$("#div3").html(result);
				},
    			// 5. error : AJAX 실패시의 콜백함수(이벤트 리스너)를 등록
    			error : function (e) {
					$("#div3").html("에러<br>" + e);
				},
    		});
    		
    	});
    </script>
    
	<h3>2. POST 방식으로 서버 데이터 송수신 방법</h3>
    <button id="ajaxSend2">post 방식 전송</button><br>
	이름 : <input type="text" id="input2-1"> <br>
	나이 : <input type="text" id="input2-2"> <br>
    <div id="div4">결과값</div>
    <script>
    	$("#ajaxSend2").click(function(){
    		let name = $("#input2-1").val();
    		let age = $("#input2-2").val();
    		
    		$.ajax({ // key - value
    			// 1. type 설정 - get, post
    			type : "post",
    			// 2. url
    			url : "jqAjax2.do",
    			// 3. data(파라미터)
    			data : {
    				name,
    				age
    			},
    			// 4. success : AJAX 성공시의 콜백함수(이벤트 리스너)를 등록
    			success : function (result) {
					$("#div4").html(result);
				},
    			// 5. error : AJAX 실패시의 콜백함수(이벤트 리스너)를 등록
    			error : function (e) {
					$("#div4").html("에러<br>" + e);
				},
    		});
    		
    	});
    </script>

	<h3>3. JSON 객체로 서버로 부터 응답 받기</h3>
    <button id="ajaxSend3">객체 응답 받기</button><br>
    <div id="div5">결과값</div>
    <script>
    	$("#ajaxSend3").click(function(){
    		var userNo = 10;
    		$.ajax({ 
    			type : "get",
    			url : "jqAjax3.do",
    			dataType : "json",	// 응답 데이터 형식
    			data : {
    				userNo
    			},
    			success : function (result) {
					$("#div5").html(result);
					str = "회원 번호 : " + result.no;
					str += ", 이름 : " + result.name;
					str += ", 나이 : " + result.age;
					str += ", 성별 : " + result.gender;
					$("#div5").html(result + "<br>" + str);
				},
    			error : function (e) {
					$("#div5").html("에러<br>" + e);
				},
    		});
    		
    	});
    </script>
    

	<h3>4. JSON 객체로 서버로 부터 응답 받기 - List 이부분 다시</h3>
    <button id="btn4">객체 응답 받기</button><br>
    <div id="div7">결과값</div>
    <script>
    	$('#btn4').click(function () {
			$.ajax({
				type : "get",
				url : "jqAjax4.do",
				dataType : "json",
				success : function (list) {
					let result = "";
					
					$.each(list, function(i, obj){
						result +=
							"회원 번호 : " + obj.no + " / " +
							"이름 : " + obj.name + " / " +
							"나이 : " + obj.age + " / " + 
							"성별 : " + obj.gender + "<br>";
					});
					$("#div7").html(result);
				},
				error : function (e) {
					$("#div7").html("에러 " + e);
				}
			});
		});
    </script>
    
    
    
    <h3>5. GSON을 활용한 List 형태 받기</h3>
    <button id="btn5">조회</button><br>
    <div id="div8"></div>
    <script>
    	$('#btn5').click(function () {
			$.ajax({
				type : "get",
				url : "jqAjax5.do",
				dataType : "json",
				success : function (list) {
					let result = "";
					
					$.each(list, function(i, obj){
						result +=
							"회원 번호 : " + obj.no + " / " +
							"이름 : " + obj.name + " / " +
							"나이 : " + obj.age + " / " + 
							"성별 : " + obj.gender + "<br>";
					});
					$("#div8").html(result);
				},
				error : function (e) {
					$("#div8").html("에러 " + e);
				}
			});
		});
    </script>

</body>
</html>




























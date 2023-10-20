<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
session = request.getSession();
Boolean isLogon = (Boolean) session.getAttribute("isLogon");
%>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<style>
* {
	font-size: 20px;
}

body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

form {
	text-align: center;
	background-color: beige;
	border: 1px solid black;
	width: 400px;
	padding: 20px;
	/* 내용물과 폼 요소들 사이에 간격 추가 */
}
</style>
</head>

<body>
	<%
	if (isLogon == null) {
		// 사용자가 로그인하지 않은 경우
	%>
	<div>
		<form name="frmLogin" method="post" action="login" encType="UTF-8">
			환영합니다<br> 철도 좌석 예약 시스템<br> 
			아이디 : <input type="text" name="user_id"><br> 
			비밀번호: <input type="password" name="user_pw"><br> 
			<input type="submit" value="로그인">	<input type="reset" value="다시입력"><br>
			<input type="button" value="회원가입"  style="WIDTH: 100px; HEIGHT: 100px" onclick="signUp()">
		</form>
	</div>
	<%
	} else if (isLogon && isLogon != null) {
	// 사용자가 로그인한 경우
	%>잘못된 접근입니다. 이미 로그인 하셨습니다.
	<hr>
	<a href="menu.jsp">메뉴로 돌아가기</a>
	<%
	}
	%>
	<script>
		function signUp(){
			window.open("/seat_web/signUpForm.jsp");
		}
	</script>
</body>

</html>

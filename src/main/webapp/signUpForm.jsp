<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="seat_web.members.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="addMember.do">
		<h1 style="text-align: center">회원 가입창</h1>
		<table align="center">
			<tr>
				<td width="200">
					<p align="right">아이디
				</td>
				<td width="400"><input type="text" name="user_id"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">비밀번호
				</td>
				<td width="400"><input id="password" type="password" name="user_pw" required></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">비밀번호 확인
				</td>
				<td width="400"><input id="confirm_password" type="password" name="user_pw"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">이름
				</td>
				<td width="400">
					<p>
						<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">핸드폰 번호('-'제외 11자리)
				</td>
				<td width="400">
					<p>
						<input type="text" name="phoneNum" id="phoneNum" onChange="fn_validatePN()">
				</td>
			</tr>
			<tr>
				<td colspan="2" height="22"><div id="msg" /></td>
			</tr>
			<tr>
				<td width="200">
					<p>&nbsp;</p>
				</td>
				<td width="400"><input type="submit" value="가입하기"> <input type="reset" value="다시입력"></td>
			</tr>
		</table>
	</form>
	<script>
		function fn_validatePN() {
			let validMsg = "";
			let phoneNum = document.getElementById("phoneNum");
			let msg = document.getElementById("msg");

			if (phoneNum.value.length != 11) {
				msg.style.color = "red";
				validMsg = "휴대전화번호는 '-'를 제외하고 11자리 입력 바랍니다.";
			} else {
				msg.style.color = "green";
				validMsg = "유효한 입력입니다.";
			}
			msg.innerText = validMsg;
		}

		var password = document.getElementById("password"), 
		confirm_password = document.getElementById("confirm_password");

		function validatePassword() {
			if (password.value != confirm_password.value) { // 만일 두 인풋 필드값이 같지 않을 경우
				// setCustomValidity의 값을 지정해 무조건 경고 표시가 나게 하고
				confirm_password.setCustomValidity("Passwords Don't Match");
			} else { // 만일 두 인풋 필드값이 같을 경우
				// 오류가 없으면 메시지를 빈 문자열로 설정해야한다. 오류 메시지가 비어 있지 않은 한 양식은 유효성 검사를 통과하지 않고 제출되지 않는다.
				// 따라서 빈값을 주어 submit 처리되게 한다
				confirm_password.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>

</html>
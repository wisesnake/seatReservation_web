<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%! String checkMsg="" ; %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
			<link rel="stylesheet" href="./CSS/reservation.css" />
		</head>

		<body>
			<form name="reservation" method="get" action="cancel">
				취소 희망 좌석번호 : <input type="text" name="seatNo"><br>
				전화번호 뒷자리 : <input type="text" name="reservationNum" id="phoneNum" onChange="fnValidate()"> <span
					id="msg" color="red">
					<%=checkMsg %>
				</span><br>
				<input type="submit" value="확인"> <input type="reset" value="다시입력">
				<input type="button" onClick="fnValidate()" value="유효성검사확인">
			</form>

			<script  type="text/javascript">
				function fnValidate() {
					let phoneNum = document.getElementById("phoneNum");
					let msg = document.getElementById("msg");

					if (phoneNum.value.length > 4) {
						checkMsg = "전화번호 뒷자리는 4자리 미만으로 작성하십시오.";
					} else if (phoneNum.value.length < 1) {
						checkMsg = "전화번호 뒷자리는 예약 확인을 위해 필수 입력 사항입니다.";
					} else {
						msg.style.color = "green";
						checkMsg = "유효한 입력입니다.";
					}
				}
			</script>
		</body>




		</html>
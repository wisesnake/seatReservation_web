<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
session = request.getSession();
String user = (String) session.getAttribute("user_id");
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./CSS/menu.css" />
<title>Document</title>
</head>

<body>
	<table class="menu-table">
		<tr>
			<td id="title" colspan="2" height="160px" bgcolor="cyan"><%=session.getAttribute("user_id")%>님! 환영합니다<br> 그린 버스 예약시스템</td>
		</tr>
		<c:choose>
			<c:when test='${user_id == "admin"}'>
				<tr class="menubtn">
					<td colspan="2" id="adminMembers">회원 관리</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr class="menubtn">
					<td id="modUserInfo">회원정보 수정</td>
					<td id="delUserInfo">회원 탈퇴</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr class="menubtn">
			<td id="seatStatus">1. 좌석 현황</td>
			<td id="seatReservation">2. 예약</td>
		</tr>
		<tr class="menubtn">
			<td id="cancel">3. 조회 / 취소</td>
			<td id="logout">4. 로그아웃</td>
		</tr>
	</table>

	<script>
		// 1번메뉴
		document
				.getElementById("seatStatus")
				.addEventListener(
						"click",
						function() {
							window
									.open("/seat_web/showseat", "popup",
											"width=500,height=620,history=no,status=no,scrollbars=yes,menubar=no");
						});
		//2번메뉴
		document.getElementById("seatReservation").addEventListener("click",
				function() {
					window.open("/seat_web/reservation.jsp");
				});
		//3번메뉴
		document.getElementById("cancel").addEventListener("click", function() {
			window.open("/seat_web/cancel.jsp");
		});
		//4번메뉴
		document.getElementById("logout").addEventListener("click", function() {
			var logOutFlag = confirm('정말로 로그아웃 하시겠습니까?');
			if (logOutFlag) {
				window.open("/seat_web/logout.jsp");
			}
		});
		//관리자_정보수정 및 삭제
		document.getElementById("adminMembers").addEventListener("click", function() {
			window.open("${contextPath}/seat_web/member");
		});
	</script>
</body>

</html>
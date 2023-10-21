<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

table {
	width: 400px;
	height: 300px;
	border-collapse: collapse;
}

td {
	background: beige;
}

.attr {
	text-align: right;
}
</style>
</head>
<body>
	<table>
		<form name="formBox" method="post" action="${contextPath}/member/modMember.do">
			<tr>
				<td class="attr">아이디 :</td>
				<td><input type="text" name="user_id" value="${userInfo.user_id }"></td>
			</tr>
		<tr>
			<td rowspan=3 class="attr">비밀번호 변경:</td>
			<td><input id="currPW" type="password" placeholder="현재 비밀번호" onchange="validatePW()"></td>
		</tr>
		<tr>
			<td><input name="user_pw" type="password" placeholder="바꿀 비밀번호"></td>
		</tr>
		<tr>
			<td><div style="font-size: 11px"></div></td>
		</tr>
		<tr>
			<td class="attr">이름 :</td>
			<td><input type="text" name="name" value="${userInfo.name }"></td>
		</tr>
		<tr>
			<td class="attr">휴대전화 번호 :</td>
			<td><input type="text" name="phoneNum" value="${userInfo.phoneNum }"></td>
		</tr>
		<tr>
			<td class="attr">가입일 :</td>
			<td><input type="text" name="joinDate" value="${userInfo.joinDate }" disabled></td>
		</tr>
		<tr>
			<td colspan=2 style="text-align: right"><input type="submit" value="수정" /> <input type="reset" value="다시 입력" /></td>
		</tr>
		</form>
	</table>

</body>
</html>

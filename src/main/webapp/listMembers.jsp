<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<c:choose>
	<c:when test='${msg=="modified" }'>
		<script>
			window.onload = function() {
				alert("회원 정보를 수정했습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg=="deleted" }'>
		<script>
			window.onload = function() {
				alert("회원 정보를 삭제했습니다.");
			}
		</script>
	</c:when>
</c:choose>

<meta charset="UTF-8">
<title>회원 정보 출력창</title>
<style>
.cls1 {
	font-size: 40px;
	text-align: center;
}

.cls2 {
	font-size: 20px;
	text-align: center;
}
</style>

</head>
<body>
	<p class="cls1">회원정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>회원번호</b></td>
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>핸드폰번호</b></td>
			<td width="7%"><b>가입일</b></td>
			<td width="7%"><b>수정</b></td>
			<td width="7%"><b>삭제</b></td>

		</tr>

		<c:choose>
			<c:when test="${empty  membersList}">
				<tr>
					<td colspan=5><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${!empty membersList}">
				<c:forEach var="mem" items="${membersList }">
					<tr align="center">
						<td>${mem.userNo }</td>
						<td>${mem.user_id }</td>
						<td>${mem.user_pw }</td>
						<td>${mem.name }</td>
						<td>${mem.phoneNum }</td>
						<td>${mem.joinDate}</td>
						<td><a href="${contextPath}/member/modMemberForm.do?user_id=${mem.user_id }">수정</a></td>
						<td><a href="${contextPath}/member/adminDelMember.do?user_id=${mem.user_id }">삭제</a></td>
						<!-- 수정 혹은 삭제 요청 시, 파라미터에 id 에 첫번째 td의 값 즉, 해당 유저의 id 태워서 보내서 회원정보찾기 메소드에 씀. -->

					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a href="${contextPath}/menu.jsp"><p class="cls2">메뉴로 돌아가기</p></a>
</body>
</html>

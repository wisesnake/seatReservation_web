<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session = request.getSession();
	session.invalidate();
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	로그아웃이 완료되었습니다.<br>
	<a href="login.jsp">로그인 화면으로 돌아가기</a>
</body>
</html>
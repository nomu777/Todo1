<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TodoUser"%>
<% TodoUser loginUser = (TodoUser) session.getAttribute("loginUser"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
<title>ログイン画面</title>
</head>
<body>
<div class="container">
<!-- <form method="post" action="${fn:escapeXml('j_security_check')}">  -->
	<form method="post" action="./servlet/todoLogin">
		<table>
			<tr>
				<td>ユーザーID</td>
				<!-- <td><input type="text" name="j_username"></td>  -->
				<td><input class="form-control mr-sm-2" type="text" name="name">
			</tr>
			<tr>
			 	<td>パスワード</td>
			 	<!-- <td><input type="password" name="j_password"></td>  -->
			 	<td><input class="form-control mr-sm-2" type="password" name="pass"></td>
			 </tr>
		</table>
		<br>
		<input class="btn btn-success" type="submit" value="ログイン" name="submit">
		<a href="./login.jsp" class="btn btn-primary">リセット</a>
	</form>
</div>
</body>
</html>
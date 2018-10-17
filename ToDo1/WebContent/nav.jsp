<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TodoUser"%>
<% TodoUser loginUser = (TodoUser) session.getAttribute("loginUser"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
</head>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container" id="linkButton">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><%= loginUser.getName() %>のTODO管理</a>
		</div>
		<div id="registerTask">
			<a href="./input">タスクの新規登録　</a>
		</div>
		<div id="showList">
			<a href="./search">一覧に戻る</a>
		</div>
		<div id="search">
			<form class="form-inline my-2 my-lg-0" action="./searchList">
	      		<input class="form-control mr-sm-2" type="text" placeholder="Search" name="text">
	      		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    	</form>
    	</div>
    	<div id="logout">
			<a href="./todoLogout">ログアウト</a>
		</div>
    	<div id="navbar" class="navbar-collapse collapse"></div>
		<!-- /.navbar-collapse -->
	</div>
</nav>
</html>
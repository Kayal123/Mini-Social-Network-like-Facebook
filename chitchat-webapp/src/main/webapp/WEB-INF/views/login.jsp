<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChitChat Login Page</title>
<style type="text/css">
.msgBlock {
	color: red;
	font-weight: bold;
	font-style: italic;
	padding: 1em;
	border: 1px dotted red;
}
</style>
</head>
<body onload="document.form.j_username.focus();">
	<div
		style="text-align: center; padding: 30px; border: 1px solid green; width: 250px;">
		<h1>ChitChat Login Page</h1>
		<c:if test="${not empty error}">
			<div class=".msgBlock">
				<p>Failure to login:
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
			</div>
		</c:if>
		<c:if test="${not empty logout}">
			<div class=".msgBlock">User logged out successfully.</div>
		</c:if>
		<form action="j_spring_security_check" name="form" method="post">
			<table>
				<tr>
					<td>User:</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td colspan=2><input type="submit" name="Submit"
						value="Submit" /></td>
				</tr>

			</table>
		</form>
		<div> <p><a href="register.html"> Sign up for an account here.</a> </p></div>
	</div>
</body>
</html>
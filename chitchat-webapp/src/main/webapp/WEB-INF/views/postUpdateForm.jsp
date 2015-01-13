<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<pre>
	<form action="/chitchat/post/submitPostUpdate/${post.id}" method="post">
		<table>
			<tr><textarea name="content" cols=40 rows=6></textarea></tr>
		</table>
		<input type="submit" name="Post">
	</form>
</pre>
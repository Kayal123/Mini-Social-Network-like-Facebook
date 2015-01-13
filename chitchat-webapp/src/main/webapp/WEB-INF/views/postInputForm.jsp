<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<pre>
	<form role="form" action="/chitchat/post/newPost" method="post">
		<div class="form-group">
			<textarea class="form-control" name="content" rows=6></textarea>
		</div>
		<div class="form-group">
			<label class="radio-inline">
				<input type="radio" name="privacy" value="private"> private
			</label>
			<label class="radio-inline">
				<input type="radio" name="privacy" value="public"> public
			</label>		
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-default" name="Post">
		</div>
	</form>
</pre>
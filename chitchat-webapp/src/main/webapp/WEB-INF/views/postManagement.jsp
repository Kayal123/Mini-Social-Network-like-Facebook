<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<div class="container" id="newPost">
	<form role="form" method="post">
		<div class="form-group">
			<textarea class="form-control" name="content" rows=6></textarea>
		</div>
		<div class="row">
		<div class="col-md-8">
			<div class="form-group">
				<label class="radio-inline">
					<input type="radio" name="privacy" value="private" checked> private
				</label>
				<label class="radio-inline">
					<input type="radio" name="privacy" value="public"> public
				</label>		
			</div> <!-- /form-group -->
		</div> <!-- /col-md-8 -->
		<div class="col-md-4">
			<input type="submit" class="btn pull-right" name="Post" value="Post">
		</div> <!-- /col-md-4 -->
		</div> <!-- /row -->
	</form>
</div>
<div class="container" id="listPost">
	<div class="col-md-4">
		<table class="table table-bordered table-striped table-hover">
			<tr><th>My Posts</th></tr>
            <c:forEach items="${userposts}" var="post">
			<tr><td>${post.content}</td></tr>
            </c:forEach>
		</table>
	</div>
	<div class="col-md-4">
		<table class="table table-bordered table-striped table-hover">
			<tr><th>Friends</th>
				<th>Posts</th></tr>
            <c:forEach items="${userfriendsposts}" var="friendpost">
			<tr>
				<td>${friendpost.author.firstname}</td>
				<td>${friendpost.content}</td>
			</tr>
            </c:forEach>
		</table>
	</div>
	<div class="col-md-4">
		<table class="table table-bordered table-striped table-hover">
			<tr><th>Author</th>
				<th>Posts</th></tr>
            <c:forEach items="${publicposts}" var="publicpost">
			<tr>
				<td>${publicpost.author.firstname}</td>
				<td>${publicpost.content}</td>
			</tr>
            </c:forEach>
		</table>
	</div>
</div>
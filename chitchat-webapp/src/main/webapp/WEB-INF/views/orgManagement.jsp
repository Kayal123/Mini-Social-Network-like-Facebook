<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<div class="container" id="listOrg">
	<div class="row"><h2>Organizations</h2></div>
		<c:forEach items="${allorgs}" var="allorg">
		<div class="row">
		<div class="btn-group dropup">
			<button type="button" class="btn btn-default btn-sm">${allorg.name}</button>
			<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="/chitchat/org/join/${allorg.id}">Join</a></li>
			</ul>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Members</h3>
			</div>
			<div class="panel-body">
				<div class="list-group">
		            <c:forEach items="${allorg.members}" var="member">
						<a href="#" class="list-group-item">${member.firstname} ${member.lastname}</a>
		            </c:forEach>
				</div>
			</div>
		</div>
		
		
		</div>
		</c:forEach>
	<div class="row"><h2>My Memberships</h2></div>
		<c:forEach items="${mymemberships}" var="mymembership">
		<div class="row">
		<div class="btn-group dropup">
			<button type="button" class="btn btn-default btn-sm">${mymembership.name}</button>
			<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="/chitchat/org/leave/${mymembership.id}">Leave</a></li>
			</ul>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Members</h3>
			</div>
			<div class="panel-body">
				<div class="list-group">
		            <c:forEach items="${mymembership.members}" var="member">
					<a href="#" class="list-group-item">${member.firstname} ${member.lastname}</a>
		            </c:forEach>
				</div>
			</div>
		</div>
		
		
		</div>
		</c:forEach>
</div>
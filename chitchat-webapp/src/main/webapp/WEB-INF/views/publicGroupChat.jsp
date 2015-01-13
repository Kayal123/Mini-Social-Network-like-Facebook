<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var wsUri = getServerRootUri() + "/chatserver/grpchat";
	var currentUsername = "${profile.email}";
	var chatWebsocket;

	function getServerRootUri() {
		return "ws://"
				+ (document.location.hostname == "" ? "localhost"
						: document.location.hostname)
				+ ":"
				+ (document.location.port == "" ? "8080"
						: document.location.port);
	};

	function init() {
		output = document.getElementById("output");
		users = document.getElementById("users");

		chatWebsocket = new WebSocket(wsUri);
		chatWebsocket.onopen = function(evt) {
			chatWebsocket.send(currentUsername);
		};
		chatWebsocket.onmessage = function(evt) {
			if ((evt.data).search("\\[") == 0) {
				displayUsers(evt.data);
			} else {
				displayOutput(evt.data);
			}
		};
		chatWebsocket.onerror = function(evt) {
			console.log("error " + evt + " " + evt.data);
		};
	}

	function displayUsers(userList) {
		users.innerHTML = "";
		var userarray = (userList.substring(1, userList.length - 1))
				.split(", ");
		for ( var i in userarray) {
			var node = document.createElement("p");
			node.innerHTML = userarray[i].toString();
			users.appendChild(node);
		}
	}

	function displayOutput(message) {
		var node = document.createElement("p");
		node.style.wordWrap = "break-word";
		node.innerHTML = message;
		output.appendChild(node);
	}

	function send_msg() {
		chatWebsocket.send(textID.value);
		document.getElementById('textID').value = '';
		return false;
	}

	window.onload = init;
</script>
<h2>Public Group Chat Area</h2>

<h4>Users Online</h4>
<div id="users"></div>

<hr>
<div id="output"></div>
<div>
	<form action="" onsubmit="return send_msg()">
		<input id="textID" name="message" type="text" value=""> <br>
		<input value="Send" type="submit">
	</form>
</div>



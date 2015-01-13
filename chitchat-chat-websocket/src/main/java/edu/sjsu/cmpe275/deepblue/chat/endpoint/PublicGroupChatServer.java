package edu.sjsu.cmpe275.deepblue.chat.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/grpchat")
public class PublicGroupChatServer {
	private static final String USERNAME_KEY = "uname";

	private static Set<String> usernames = Collections
			.synchronizedSet(new HashSet<String>());

	@OnOpen
	public void init(Session session, EndpointConfig endpointCfg) {
	}

	@OnMessage
	public void handleRequests(Session session, String message) {
		if (session.getUserProperties().get(USERNAME_KEY) == null) {
			session.getUserProperties().put(USERNAME_KEY, message);
			usernames.add(message);
			for (Session current : session.getOpenSessions()) {
				try {
					current.getBasicRemote().sendText(usernames.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String msg = " *** " + message + " has entered the group chat *** ";
			for (Session current : session.getOpenSessions()) {
				try {
					current.getBasicRemote().sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			String username = (String) session.getUserProperties().get(
					USERNAME_KEY);
			String msg = username + " : " + message;
			for (Session current : session.getOpenSessions()) {
				try {
					current.getBasicRemote().sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println(t.getLocalizedMessage());
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		String username = (String) session.getUserProperties()
				.get(USERNAME_KEY);
		usernames.remove(username);
		String msg = " --- " + username + " has left the group chat --- ";
		for (Session current : session.getOpenSessions()) {
			try {
				current.getBasicRemote().sendText(msg);
				current.getBasicRemote().sendText(usernames.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

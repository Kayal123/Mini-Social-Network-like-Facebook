package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Friendship;

public interface FriendshipService {

	String requestFriendshipByEmail(long id, String friendEmail);

	String removeFriendshipByEmail(long id, String friendEmail);
	
	String removeFriendshipById(long id, long friendId);

	String acceptFriendRequest(long id, long friendId);

	String rejectFriendRequest(long id, long friendId);

	List<Friendship> findAllFriendsOfById(long id);
}
package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.datasource.ChitChatRestClient;

@Service("friendshipService")
public class FriendshipServiceImpl implements FriendshipService {

	@Inject
	private ChitChatRestClient rc;

	@Override
	public String requestFriendshipByEmail(long id, String friendEmail) {
		Person friend = null;
		try {
			friend = rc.doGetForFetchPerson(friendEmail);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(friend == null) {
			// Invalid friend email
			return null;
		}
		
		try {
			return rc.doPostForFriendRequest(id, friend.getId());
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String removeFriendshipByEmail(long id, String friendEmail) {
		Person friend = null;
		try {
			friend = rc.doGetForFetchPerson(friendEmail);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(friend == null) {
			// Invalid friend email
			return null;
		}
		
		try {
			return rc.doDeleteForFriendship(id, friend.getId());
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String removeFriendshipById(long id, long friendId) {
		Person friend = null;
		try {
			friend = rc.doGetForFetchPerson(friendId);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(friend == null) {
			// Invalid friend email
			return null;
		}
		
		try {
			return rc.doDeleteForFriendship(id, friend.getId());
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String acceptFriendRequest(long id, long friendId) {
		try {
			return rc.doPutForFriendshipAccept(id, friendId);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String rejectFriendRequest(long id, long friendId) {
		try {
			return rc.doPutForFriendshipReject(id, friendId);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Friendship> findAllFriendsOfById(long id) {
		try {
			return rc.doGetForAllFriendships(id);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;
import edu.sjsu.cmpe275.deepblue.webapp.datasource.ChitChatRestClient;

@Service("postService")
public class PostServiceImpl implements PostService {
	// TODO: Need to add validation using aop
	// Check valid Person object
	// Check valid Post object

	@Inject
	private ChitChatRestClient rc;

	@Override
	public Post createPost(Person user, Post post) {
		post.setAuthor(user);
		try {
			return rc.doPostCreateNewPost(post);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Post updatePost(Person user, Post post) {
		// TODO: check if user's id matches id on post object
		try {
				return rc.doPutForExistingPost(post);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Post deletePost(Person user, Post post) {
		// TODO: check if user's id matches id on post object
		try {
			return rc.doDeleteForPost(post);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAllUserPosts(Person user) {
		return getAllUserPosts(user.getId());
	}

	@Override
	public List<Post> getAllUserPosts(long userId) {
		try {
			return rc.doGetForAllUserPost(userId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAllUserFriendsPosts(Person user) {
		return getAllUserFriendsPosts(user.getId());
	}

	@Override
	public List<Post> getAllUserFriendsPosts(long userId) {
		try {
			return rc.doGetForAllUserFriendPosts(userId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAUserFriendPost(Person user, Person friend) {
		return getAUserFriendPost(user.getId(), friend.getId());
	}

	@Override
	public List<Post> getAUserFriendPost(long userId, long friendId) {
		try {
			return rc.doGetForAUserFriendPosts(userId, friendId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAllPublicPost() {
		try {
			return rc.doGetForAllPublicPost();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Post getPost(long id) {
		try {
			return rc.doGetForAUserPost(id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Post getPost(Post post) {
		return getPost(post.getId());
	}
}

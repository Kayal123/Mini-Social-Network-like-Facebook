package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;

public interface PostService {

	/**
	 * Create a new user post
	 * 
	 * @param user
	 * @param post
	 * @return
	 */
	Post createPost(Person user, Post post);

	/**
	 * Update an existing user post
	 * 
	 * @param user
	 * @param post
	 * @return
	 */
	Post updatePost(Person user, Post post);

	/**
	 * Get an existing post by id
	 * 
	 * @param id
	 * @return
	 */
	Post getPost(long id);
	Post getPost(Post post);

	/**
	 * Remove an existing user post
	 * 
	 * @param user
	 * @param post
	 * @return
	 */
	Post deletePost(Person user, Post post);

	/**
	 * Retrieve all posts by current user
	 * 
	 * @param user
	 * @return
	 */
	List<Post> getAllUserPosts(Person user);

	List<Post> getAllUserPosts(long userId);

	/**
	 * Retrieve all posts of all friends by current user (private only)
	 * 
	 * @param user
	 * @return
	 */
	List<Post> getAllUserFriendsPosts(Person user);

	List<Post> getAllUserFriendsPosts(long userId);

	/**
	 * Retrieve all posts by a single friend of a user (private only)
	 * 
	 * @param user
	 * @return
	 */
	List<Post> getAUserFriendPost(Person user, Person friend);

	List<Post> getAUserFriendPost(long userId, long friendId);

	/**
	 * Retrieve all public posts
	 * 
	 * @return
	 */
	List<Post> getAllPublicPost();
}

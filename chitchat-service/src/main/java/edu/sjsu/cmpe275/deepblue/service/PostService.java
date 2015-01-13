package edu.sjsu.cmpe275.deepblue.service;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Post;

public interface PostService {

	/**
	 * Creates a new post.
	 * 
	 * @param post
	 * @return Post entity with properties with id that was saved.
	 */
	Post createPost(Post post);

	/**
	 * Updates post content.
	 * 
	 * @param 
	 * @return Updated post entity.
	 */
	Post updatePost(Post post);

	/**
	 * Retrieve post information.
	 * 
	 * @param id
	 * @return Post information.
	 */
	Post getPost(long id);
	Post getPost(Post post);
	
	/**
	 * Retrieve all posts from an author.
	 * 
	 * @param id
	 * @return 
	 */
	List<Post> getAllPostByAuthor(long id);
	
	/**
	 * Retrieve all posts from all of author's friends.
	 * 
	 * @param id
	 * @return 
	 */
	List<Post> getAllPostsByAuthorAllFriends(long id);
	
	/**
	 * Retrieve all posts from an author's particular friend.
	 * 
	 * @param id, fid
	 * @return 
	 */
	List<Post> getAllPostsByAuthorFriend(long id, long fid);
	
	/**
	 * Retrieve all public posts.
	 * 
	 * @param id, fid
	 * @return 
	 */
	List<Post> getAllPublicPosts();

	/**
	 * Remove post by the specified id.
	 * 
	 * @param id
	 * @return Post entity that was removed.
	 */
	Post deletePost(long id);
}

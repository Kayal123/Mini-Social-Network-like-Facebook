/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.restapi.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.deepblue.model.Post;
import edu.sjsu.cmpe275.deepblue.service.PostService;

/**
 * @author deepblue
 *
 */
@Controller
@RequestMapping(value="/post")
public class PostApi {

	@Inject
	private PostService postService;
	
	/**
	 * Create new post
	 */
	@RequestMapping(method = {RequestMethod.POST})
	@ResponseBody
	Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}
	
	/**
	 * Update an existing post
	 */
	@RequestMapping(method = {RequestMethod.PUT})
	@ResponseBody
	Post updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}
	
//	/**
//	 * Get existing post by id
//	 */
//	@RequestMapping(value = { "/getPost/{id}" }, method = {RequestMethod.GET})
//	@ResponseBody
//	Post getPost(@RequestBody Post post) {
//		return postService.getPost(post);
//	}
//	
	/**
	 * Get existing post by id
	 */
	@RequestMapping(value = { "/getPost/{id}" }, method = {RequestMethod.GET})
	@ResponseBody
	Post getPost(@PathVariable(value="id") String id) {
		return postService.getPost(Long.parseLong(id));
	}
	
	/**
	 * Remove an existing post
	 */
	@RequestMapping(method = {RequestMethod.DELETE})
	@ResponseBody
	Post removePost(@RequestBody Post post) {
		return postService.deletePost(post.getId());
	}
	
	/**
	 * Display all of this user posts
	 */
	@RequestMapping(value = {"/{id}"}, method = {RequestMethod.GET})
	@ResponseBody
	List<Post> displayAllPostByPerson(@PathVariable(value="id") String id) {
		return postService.getAllPostByAuthor(Long.parseLong(id));
	}
	
	/**
	 * Display all of this user's friends post (that are private)
	 */
	@RequestMapping(value = {"/{id}/friends"}, method = {RequestMethod.GET})
	@ResponseBody
	List<Post> displayAllPostByFriends(@PathVariable(value="id") String id) {
		return postService.getAllPostsByAuthorAllFriends(Long.parseLong(id));
	}
	
	/**
	 * Display post of a single friend (that are private)
	 */
	@RequestMapping(value = {"/{id}/friends/{fid}"}, method = {RequestMethod.GET})
	@ResponseBody
	List<Post> displayAllPostByAFriend(@PathVariable(value="id") String id, @PathVariable(value="fid") String friendId) {
		return postService.getAllPostsByAuthorFriend(Long.parseLong(id), Long.parseLong(friendId));
	}
	
	/**
	 * Display all public posts
	 */
	@RequestMapping(value = {"/public"}, method = {RequestMethod.GET})
	@ResponseBody
	List<Post> displayAllPublicPost() {
		return postService.getAllPublicPosts();
	}
	
	// MAYBE EXPOSE API THAT CAN QUERY BY DATE
}

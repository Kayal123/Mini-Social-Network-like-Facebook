/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.webapp.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;
import edu.sjsu.cmpe275.deepblue.webapp.service.FriendshipService;
import edu.sjsu.cmpe275.deepblue.webapp.service.PostService;
import edu.sjsu.cmpe275.deepblue.webapp.service.ProfileService;

/**
 * @author DeepBlue
 *
 */
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Inject
	private PostService postService;
	@Inject
	private ProfileService profileService;


	@RequestMapping(method = { RequestMethod.GET })	
	public String listPosts(Model model) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute("userposts", postService.getAllUserPosts(currentUser));
		model.addAttribute("userfriendsposts", postService.getAllUserFriendsPosts(currentUser));
		model.addAttribute("publicposts", postService.getAllPublicPost());
		return "postManagement";
		
	}
	
	@RequestMapping(method = { RequestMethod.POST }, params = "Post")
	public String newPost(Model model, @RequestParam(value = "content") String content,
			@RequestParam(value = "privacy") String privacy) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		Post post = new Post();
		post.setAuthor(currentUser);
		post.setContent(content);
		post.setPrivacy(privacy);
		System.out.println(privacy);
		model.addAttribute("newpost", postService.createPost(currentUser, post));
		model.addAttribute("userposts", postService.getAllUserPosts(currentUser));
		model.addAttribute("userfriendsposts", postService.getAllUserFriendsPosts(currentUser));
		model.addAttribute("publicposts", postService.getAllPublicPost());
		return "postManagement";
	}

//	@RequestMapping(value = { "/{postId}" })
//	public ModelAndView getPost(@PathVariable String postId) {
//		return new ModelAndView("getPost", "post", postService.getPost(Long.parseLong(postId)));
//	}
//
//	@RequestMapping(value = { "/getUserPosts" })
//	public ModelAndView getAllUserPosts() {
//		//TODO remove hard-coded id and handle identity with authorization schemes
//		Person author = new Person();
//		author.setId(1);
//		return new ModelAndView("getUserPosts", "posts", postService.getAllUserPosts(author));
//	}
//
//	@RequestMapping(value = { "/getUserFriendsPosts" })
//	public ModelAndView getAllUserFriendsPosts() {
//		//TODO remove hard-coded id and handle identity with authorization schemes
//		Person author = new Person();
//		author.setId(1);
//		return new ModelAndView("getUserPosts", "posts", postService.getAllUserFriendsPosts(author));
//	}
//
//	@RequestMapping(value = { "/getPublicPosts" })
//	public ModelAndView getAllPublicPosts() {
//		return new ModelAndView("getUserPosts", "posts", postService.getAllPublicPost());
//	}

//	@RequestMapping(value = { "/{postId}" })
//	public ModelAndView deletePost(@PathVariable String postId) {
//		//TODO remove hard-coded id and handle identity with authorization schemes
////		Person author = new Person();
////		author.setId(1);
////		Post post = new Post();
////		post.setId(Long.parseLong(postId));
////		post.setAuthor(author);
//		return new ModelAndView("getPost", "post", postService.getPost(Long.parseLong(postId)));
//
//	}
//
//	@RequestMapping(value= {"/updatePost/{postId}"})	
//	public ModelAndView launchUpdateForm(@PathVariable("postId") String postId) {
//		//TODO remove hard-coded id and handle identity with authorization schemes
//		Person author = new Person();
//		author.setId(1);
//		postId="1";
//		Post post = new Post();
//		post.setId(Long.parseLong(postId));
//		post.setAuthor(author);
//		ModelAndView model = new ModelAndView( "postUpdateForm","post",post);	
//		return model;
//		
//	}
//
//	@RequestMapping(value= {"/submitPostUpdate/{postId}"})
//	public ModelAndView updatePost(
//			@PathVariable("postId") String postId,
//			@RequestParam(value = "content") String content) {
//
//		//TODO remove hard-coded id and handle identity with authorization schemes
//		Person author = new Person();
//		author.setId(1);
//		Post post = postService.getPost(Long.parseLong(postId));
////		Person author = post.getAuthor();
//		Post updatedPost = postService.updatePost(author, post);
//		return new ModelAndView("updatedProfileSuccess", "post", updatedPost);
//	}

}

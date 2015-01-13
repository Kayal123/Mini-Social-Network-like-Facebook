package edu.sjsu.cmpe275.deepblue.webapp.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.service.FriendshipService;
import edu.sjsu.cmpe275.deepblue.webapp.service.ProfileService;

/**
 * @author deepblue
 * 
 */
@Controller
@RequestMapping("/friendship")
public class FriendshipController {

	private static final String FRIENDSHIP_VIEW = "friendshipManagement";

	@Inject
	private ProfileService profileService;
	@Inject
	private FriendshipService friendshipService;

	/**
	 * Retrieve latest friends list and status
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public String listFriendship(Model model) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));

		return FRIENDSHIP_VIEW;
	}

	/**
	 * Make a request for friendship
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "request")
	public String requestFriendshipByEmail(Model model,
			@RequestParam(value = "friendEmail") String friendEmail) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute("message", friendshipService
				.requestFriendshipByEmail(currentUser.getId(), friendEmail));
		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));
		return FRIENDSHIP_VIEW;
	}

	/**
	 * Remove friendship. Status does not matter.
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "remove")
	public String removeFriendshipByEmail(Model model,
			@RequestParam(value = "friendEmail") String friendEmail) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute("message", friendshipService
				.removeFriendshipByEmail(currentUser.getId(), friendEmail));
		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));
		return FRIENDSHIP_VIEW;
	}

	@RequestMapping(value = { "/accept/{friendId}" }, method = { RequestMethod.POST })
	public String acceptFriendship(Model model,
			@PathVariable(value = "friendId") String friendId) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute(
				"message",
				friendshipService.acceptFriendRequest(currentUser.getId(),
						Long.parseLong(friendId)));
		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));
		return FRIENDSHIP_VIEW;
	}

	@RequestMapping(value = { "/reject/{friendId}" }, method = { RequestMethod.POST })
	public String rejectFriendship(Model model,
			@PathVariable(value = "friendId") String friendId) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute(
				"message",
				friendshipService.rejectFriendRequest(currentUser.getId(),
						Long.parseLong(friendId)));
		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));
		return FRIENDSHIP_VIEW;
	}

	@RequestMapping(value = { "/remove/{friendId}" }, method = { RequestMethod.POST })
	public String removeFriendship(Model model,
			@PathVariable(value = "friendId") String friendId) {

		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");

		model.addAttribute("message", friendshipService.removeFriendshipById(
				currentUser.getId(), Long.parseLong(friendId)));
		model.addAttribute("friendships",
				friendshipService.findAllFriendsOfById(currentUser.getId()));
		return FRIENDSHIP_VIEW;
	}
}
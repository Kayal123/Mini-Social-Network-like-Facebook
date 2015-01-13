package edu.sjsu.cmpe275.deepblue.restapi.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.service.ProfileService;

@Controller
@RequestMapping("/friends")
public class FriendshipApi {

	@Inject
	private ProfileService profileService;

	/**
	 * Make a friend request
	 * 
	 */
	@RequestMapping(value = { "/{id1}/{id2}" }, method = { RequestMethod.POST })
	@ResponseBody
	String requestFriendship(@PathVariable("id1") String id1,
			@PathVariable("id2") String id2) {
		return profileService.addFriendRequest(Long.parseLong(id1),
				Long.parseLong(id2));
	}

	/**
	 * Update friendship status
	 * 
	 */
	@RequestMapping(value = { "/{id1}/{id2}" }, method = { RequestMethod.PUT })
	@ResponseBody
	String acceptFriendship(@PathVariable("id1") String id1,
			@PathVariable("id2") String id2,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equalsIgnoreCase("accept")) {
			return profileService.addFriendAccept(Long.parseLong(id1),
					Long.parseLong(id2));
		} else {
			return profileService.addFriendReject(Long.parseLong(id1),
					Long.parseLong(id2));
		}
	}

	/**
	 * Remove friendship between two person
	 * 
	 */
	@RequestMapping(value = { "/{id1}/{id2}" }, method = { RequestMethod.DELETE })
	@ResponseBody
	String removeFriend(@PathVariable("id1") String id1,
			@PathVariable("id2") String id2) {
		return profileService.removeFriend(Long.parseLong(id1),
				Long.parseLong(id2));
	}

	/**
	 * Get a list of all friendships
	 * 
	 */
	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	List<Friendship> getAllFriendship(@PathVariable("id") String id) {
		return profileService.getAllFriends(Long.parseLong(id));
	}
}
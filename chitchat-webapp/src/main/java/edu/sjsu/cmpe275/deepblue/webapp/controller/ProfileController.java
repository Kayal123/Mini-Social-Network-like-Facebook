/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.service.ProfileServiceImpl;

/**
 * @author DeepBlue
 * 
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Inject
	private ProfileServiceImpl profileService;

	@Inject
	private UserDetailsManager udm;

	/*
	 * Entry point after successful login
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public String viewProfile(Model model) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);
		}
		return "getProfile";
	}

	/*
	 * Create new profile
	 */
	@RequestMapping(value = { "/submitData" }, method = {RequestMethod.POST})
	public String newProfile(
			@RequestParam(value = "firstname") String firstname,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "org", required = false) String orgId,
			@RequestParam(value = "pw") String password) {

		Person person = new Person.Builder(firstname, lastname, email)
				.description(description).street(street).city(city)
				.state(state).zip(zip).organization(orgId).build();

		Person newProfile = profileService.createProfile(person);

		// If profile was created ... add to UserDetailsService
		if (newProfile != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
					"ROLE_USER");
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(authority);
			User user = new User(person.getEmail(), password, authorities);

			udm.createUser(user);
			if (udm.userExists(person.getEmail())) {
				System.out.println(person.getEmail()
						+ " added to UserDetailsService successful...");
			} else {
				// if adding new user to UDS fails remove user profile just
				// created
				profileService.deleteProfile(newProfile.getId());
			}
		}
		return "redirect:/login.html";
	}

	
	
// TODO: Need to re-eval below code	
	
	
	
	/*
	 * Just displays update profile page ... TODO should prepopulate existing user info
	 */
	@RequestMapping(value = { "/profileUpdate" })
	public String launchUpdateForm(Model model) {
		
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		
		return "profileUpdateForm";
	}
	
	/*
	 * Update profile
	 */
	@RequestMapping(value = { "/submitData/{id}" })
	public ModelAndView updateProfile(
			@PathVariable("id") String id,
			@RequestParam(value = "firstname") String firstname,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "org", required = false) String orgId,
			@RequestParam(value = "pw", required = false) String password) {
		
		Person person = new Person.Builder(firstname, lastname, email)
				.description(description).street(street).city(city)
				.state(state).zip(zip).id(Long.parseLong(id))
				.organization(orgId).build();

		Person updatedProfile = profileService.updateProfile(person);

		if (password != null) {
			// TODO: Need strategy to update password
		}

		return new ModelAndView("updatedProfileSuccess", "profile",
				updatedProfile);
	}
	
	/*
	 * Mapping for chat
	 */
	@RequestMapping(value = {"/grpchat"})
	public String publicGrpChat(Model model) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);
		}
		return "publicGroupChat";
	}
}

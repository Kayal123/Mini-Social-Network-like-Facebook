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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.service.OrganizationServiceImpl;
import edu.sjsu.cmpe275.deepblue.webapp.service.ProfileService;

/**
 * @author layya_000
 *
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {
	
	@Inject
	private OrganizationServiceImpl orgService;
	@Inject
	private ProfileService profileService;

	/**
	 * Launch create org form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/orgUpdate"})
	public String launchCreateForm(Model model) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		return "orgInputForm";
	}
	
	/**
	 * Create new organization
	 * 
	 * @param model
	 * @param name
	 * @param description
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "CreateOrg")
	public String newOrg(Model model,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		Person c = profileService.getProfile(currentUser.getEmail());
		Organization organization = new Organization.Builder(name)
				.description(description).street(street).city(city)
				.state(state).zip(zip).build();
		model.addAttribute("neworg", orgService.createOrg(organization));
//		model.addAttribute("myorgs", orgService.getAllOrgByOwner(c.getId()));
		model.addAttribute("mymemberships", orgService.getAllOrgByMember(c.getId()));
		model.addAttribute("allorgs", orgService.getAllOrgs());
		return "orgManagement";
	}

	/**
	 * List My Organizations, My Memberships, and All Organizations
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public String listOrgs(Model model) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		Person c = profileService.getProfile(currentUser.getEmail());
//		model.addAttribute("myorgs", orgService.getAllOrgByOwner(c.getId()));
		model.addAttribute("mymemberships", orgService.getAllOrgByMember(c.getId()));
		model.addAttribute("allorgs", orgService.getAllOrgs());
		return "orgManagement";
	}

	/**
	 * Join an organization
	 * 
	 * @param model
	 * @param org_id
	 * @return
	 */
	@RequestMapping(value = { "/join/{id}" })
	public String joinOrg(Model model, @PathVariable("id") String org_id) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		Person c = profileService.getProfile(currentUser.getEmail());
		orgService.joinOrg(Long.parseLong(org_id), c);
//		model.addAttribute("myorgs", orgService.getAllOrgByOwner(c.getId()));
		model.addAttribute("mymemberships", orgService.getAllOrgByMember(c.getId()));
		model.addAttribute("allorgs", orgService.getAllOrgs());
		return "orgManagement";
	}

	/**
	 * Leave an organization
	 * 
	 * @param model
	 * @param org_id
	 * @return
	 */
	@RequestMapping(value = { "/leave/{id}" })
	public String leaveOrg(Model model, @PathVariable("id") String org_id) {
		if (!model.containsAttribute("profile")) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String email = auth.getName();
			Person person = profileService.getProfile(email);
			model.addAttribute("profile", person);

		}
		Person currentUser = (Person) model.asMap().get("profile");
		Person c = profileService.getProfile(currentUser.getEmail());
		orgService.leaveOrg(Long.parseLong(org_id), c);
//		model.addAttribute("myorgs", orgService.getAllOrgByOwner(c.getId()));
		model.addAttribute("mymemberships", orgService.getAllOrgByMember(c.getId()));
		model.addAttribute("allorgs", orgService.getAllOrgs());
		return "orgManagement";
	}
}

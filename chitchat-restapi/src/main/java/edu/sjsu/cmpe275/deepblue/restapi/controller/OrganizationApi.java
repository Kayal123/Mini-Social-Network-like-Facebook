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

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.service.ProfileService;

@Controller
@RequestMapping("/org")
public class OrganizationApi {

	@Inject
	private ProfileService profileService;

	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	Organization createOrganization(
//			@RequestParam(value = "city", required = false) String city,
//			@RequestParam(value = "description", required = false) String description,
//			@RequestParam(value = "name") String name,
//			@RequestParam(value = "state", required = false) String state,
//			@RequestParam(value = "street", required = false) String street,
//			@RequestParam(value = "zip", required = false) String zip) {
//
//		Organization organization = new Organization.Builder(name)
//				.description(description).street(street).city(city)
//				.state(state).zip(zip).build();
			@RequestBody Organization organization) {
		return profileService.createOrganization(organization);
	}

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	Organization getOrganization(@PathVariable("id") String id) {
		long value = Long.parseLong(id);
		return profileService.getOrganization(value);
	}

//	@RequestMapping(value = { "/owner/{id}" }, method = { RequestMethod.GET })
//	@ResponseBody
//	List<Organization> getAllOwnerOrg(@PathVariable("id") String id) {
//		long value = Long.parseLong(id);
//		return profileService.getAllOrgByOwner(value);
//	}

	@RequestMapping(value = { "/member/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	List<Organization> getAllMemberOrg(@PathVariable("id") String id) {
		long value = Long.parseLong(id);
		return profileService.getAllOrgByMember(value);
	}

	@RequestMapping(value = { "/all" }, method = { RequestMethod.GET })
	@ResponseBody
	List<Organization> getAllOrg() {
		return profileService.getAllOrgs();
	}

//	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.POST })
	@RequestMapping(method = { RequestMethod.PUT })
	@ResponseBody
	Organization updateOrganization(
//			@PathVariable("id") String id,
//			@RequestParam(value = "name") String name,
//			@RequestParam(value = "description", required = false) String description,
//			@RequestParam(value = "street", required = false) String street,
//			@RequestParam(value = "city", required = false) String city,
//			@RequestParam(value = "state", required = false) String state,
//			@RequestParam(value = "zip", required = false) String zip) {
//
//		Organization org = profileService.getOrganization(Long.parseLong(id));
//		org.setName(name);
//		org.setDescription(description);
//		org.setStreet(street);
//		org.setCity(city);
//		org.setState(state);
//		org.setZip(zip);
			@RequestBody Organization organization) {

		return profileService.updateOrganization(organization);
	}

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
	@ResponseBody
	Organization deleteOrganization(@PathVariable("id") String id) {
		Organization org = profileService.getOrganization(Long.parseLong(id));
		return profileService.deleteOrganization(org);
	}
	
}

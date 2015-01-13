package edu.sjsu.cmpe275.deepblue.restapi.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.service.ProfileService;

@Controller
@RequestMapping("/person")
public class PersonApi {

	@Inject
	private ProfileService profileService;

	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	Person createPerson(
			@RequestParam(value = "firstname") String firstname,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "org", required = false) String orgId) {

		Person person = new Person.Builder(firstname, lastname, email)
				.description(description).street(street).city(city)
				.state(state).zip(zip).organization(orgId).build();

		return profileService.createPerson(person);
	}

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	Person getPerson(@PathVariable("id") String id, @RequestParam(value="email", required = false) String email) {
		if(email != null) {
			return profileService.getPerson(email);
		}
		else {
			return profileService.getPerson(Long.parseLong(id));
		}
	}

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.POST })
	@ResponseBody
	Person updatePerson(
			@PathVariable("id") String id,
			@RequestParam(value = "firstname") String firstname,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "org", required = false) String orgId) {

		Person person = new Person.Builder(firstname, lastname, email)
				.description(description).street(street).city(city)
				.state(state).zip(zip).id(Long.parseLong(id))
				.organization(orgId).build();

		return profileService.updatePerson(person);
	}

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
	@ResponseBody
	Person deletePerson(@PathVariable("id") String id) {
		return profileService.deletePerson(Long.parseLong(id));
	}
}

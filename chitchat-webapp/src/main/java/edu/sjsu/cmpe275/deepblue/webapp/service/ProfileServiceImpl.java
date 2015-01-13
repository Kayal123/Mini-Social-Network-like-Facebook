package edu.sjsu.cmpe275.deepblue.webapp.service;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.datasource.ChitChatRestClient;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{
	@Inject
	private ChitChatRestClient restClient;

	@Override
	public Person createProfile(Person person) {
		try {
			return restClient.doPostForCreatePerson(person);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Person getProfile(String email) {
		try {
			return restClient.doGetForFetchPerson(email);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Person getProfile(long id) {
		try {
			return restClient.doGetForFetchPerson(id);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		};
		return null;
	}

	@Override
	public Person updateProfile(Person person) {
		try {
			return restClient.doPutForUpdatePerson(person);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Person deleteProfile(long id) {
		try {
			return restClient.doDeleteForDeletePerson(id);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}

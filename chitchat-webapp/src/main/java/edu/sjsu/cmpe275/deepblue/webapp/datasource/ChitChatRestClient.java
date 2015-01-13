package edu.sjsu.cmpe275.deepblue.webapp.datasource;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;

@Named("chitchatRestClient")
public class ChitChatRestClient {
	private static final String SCHEME = "http";
	private static final String HOST = "localhost:8080";
	private static final String RESOURCE_PATH = "/restapi";
	
//	private static final String HOST = "chitchat-restapi.uc01.clc.af.cm";
//	private static final String RESOURCE_PATH = "/";
	
	private static final String URL_SEPARATOR = "/";

	private enum RestapiType {
		PERSON("person"), FRIENDS("friends"), ORGANIZATION("org"), POST("post");

		private final String value;
		private RestapiType(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}

	@Inject
	private RestTemplate restTemplate;

	
	/**************************************************************************
	 * ORGANIZATION API
	 **************************************************************************/


	public Organization doPostForCreateOrg(Organization org)
			throws RestClientException, URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder()
				.setScheme(SCHEME)
				.setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION);
		
//		uriBuilder.setParameter("name", org.getName());
//		uriBuilder.setParameter("description", org.getDescription());
//		uriBuilder.setParameter("street", org.getStreet());
//		uriBuilder.setParameter("city", org.getCity());
//		uriBuilder.setParameter("state", org.getState());
//		uriBuilder.setParameter("zip", org.getZip());
//		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());

//		return restTemplate.postForObject(uriBuilder.build(), null, Organization.class);
		return restTemplate.postForObject(uriBuilder.build(), org, Organization.class);
	}

	public Organization doGetForFetchOrg(long id) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.getForObject(uriBuilder.build(), Organization.class);
	}
	
//	public List<Organization> doGetForFetchOwnerOrg(long id) 
//			throws RestClientException, URISyntaxException {
//		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
//				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + "owner" + URL_SEPARATOR + id);
//		
//		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
//		
//		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Organization[].class));
//	}
	
	public List<Organization> doGetForFetchMemberOrg(long id) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + "member" + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Organization[].class));
	}
	
	public List<Organization> doGetForFetchAllOrg() 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + "all");
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Organization[].class));
	}
	
	public Organization doPostForUpdateOrg(Organization org)
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME)
				.setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION);
//				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + org.getId());
//		uriBuilder.setParameter("name", org.getName());
//		uriBuilder.setParameter("description", org.getDescription());
//		uriBuilder.setParameter("street", org.getStreet());
//		uriBuilder.setParameter("city", org.getCity());
//		uriBuilder.setParameter("state", org.getState());
//		uriBuilder.setParameter("zip", org.getZip());
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());

//		return restTemplate.postForObject(uriBuilder.build(), null, Organization.class);
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.PUT, new HttpEntity<Organization>(org), Organization.class).getBody();
	}

	public Organization doDeleteForDeleteOrg(long id) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.ORGANIZATION + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.DELETE, null, Organization.class).getBody();
	}
	
	
	/**************************************************************************
	 * PERSON API
	 **************************************************************************/

	
	public Person doPostForCreatePerson(Person person)
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME)
				.setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.PERSON);
		uriBuilder.setParameter("firstname", person.getFirstname());
		uriBuilder.setParameter("lastname", person.getLastname());
		uriBuilder.setParameter("email", person.getEmail());
		uriBuilder.setParameter("description", person.getDescription());
		uriBuilder.setParameter("street", person.getStreet());
		uriBuilder.setParameter("city", person.getCity());
		uriBuilder.setParameter("state", person.getState());
		uriBuilder.setParameter("zip", person.getZip());
		if(person.getOrgId() > 0) {
			uriBuilder.setParameter("org", Long.toString(person.getOrgId()));
		}
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());

		return restTemplate.postForObject(uriBuilder.build(), null, Person.class);
	}
	
	public Person doPutForUpdatePerson(Person person)
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME)
				.setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.PERSON + URL_SEPARATOR + person.getId());
		uriBuilder.setParameter("firstname", person.getFirstname());
		uriBuilder.setParameter("lastname", person.getLastname());
		uriBuilder.setParameter("email", person.getEmail());
		uriBuilder.setParameter("description", person.getDescription());
		uriBuilder.setParameter("street", person.getStreet());
		uriBuilder.setParameter("city", person.getCity());
		uriBuilder.setParameter("state", person.getState());
		uriBuilder.setParameter("zip", person.getZip());
		if(person.getOrgId() > 0) {
			uriBuilder.setParameter("org", Long.toString(person.getOrgId()));
		}
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());

		return restTemplate.postForObject(uriBuilder.build(), null, Person.class);
	}

	public Person doGetForFetchPerson(long id) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.PERSON + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.getForObject(uriBuilder.build(), Person.class);
	}
	
	public Person doGetForFetchPerson(String email) 
			throws RestClientException, URISyntaxException {
		// zero in the uri is just a negible placeholder
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.PERSON + URL_SEPARATOR + "0");
		uriBuilder.setParameter("email", email);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.getForObject(uriBuilder.build(), Person.class);
	}
	
	public Person doDeleteForDeletePerson(long id) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.PERSON + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.DELETE, null, Person.class).getBody();
	}
	
	/**************************************************************************
	 * FRIENDSHIP API
	 **************************************************************************/

	public String doPostForFriendRequest(long personId, long friendId) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.FRIENDS + URL_SEPARATOR + personId + URL_SEPARATOR + friendId);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.postForObject(uriBuilder.build(), null, String.class);
	}
	
	public String doPutForFriendshipAccept(long personId, long friendId) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.FRIENDS + URL_SEPARATOR + personId + URL_SEPARATOR + friendId);
		uriBuilder.setParameter("action", "accept");
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.PUT, null, String.class).getBody();
	}
	
	public String doPutForFriendshipReject(long personId, long friendId) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.FRIENDS + URL_SEPARATOR + personId + URL_SEPARATOR + friendId);
		uriBuilder.setParameter("action", "");
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.PUT, null, String.class).getBody();
	}
	
	public String doDeleteForFriendship(long personId, long friendId) 
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.FRIENDS + URL_SEPARATOR + personId + URL_SEPARATOR + friendId);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.DELETE, null, String.class).getBody();
	}
	
	public List<Friendship> doGetForAllFriendships(long personId)
			throws RestClientException, URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.FRIENDS + URL_SEPARATOR + personId);

		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return new ArrayList<Friendship>(Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Friendship[].class)));
	}
	
	/**************************************************************************
	 * POST API
	 **************************************************************************/
	
	public Post doPostCreateNewPost(Post post) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.postForObject(uriBuilder.build(), post, Post.class);
	}
	
	public Post doPutForExistingPost(Post post) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.PUT, new HttpEntity<Post>(post), Post.class).getBody();
	}
	
	public Post doDeleteForPost(Post post) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.exchange(uriBuilder.build(), HttpMethod.DELETE, new HttpEntity<Post>(post), Post.class).getBody();
	}
	
	public Post doGetForAUserPost(long id) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST + URL_SEPARATOR + "getPost" + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return restTemplate.getForObject(uriBuilder.build(), Post.class);
	}
	
	public List<Post> doGetForAllUserPost(long id) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST + URL_SEPARATOR + id);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Post[].class));
	}
	
	public List<Post> doGetForAllUserFriendPosts(long id) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST + URL_SEPARATOR + id + URL_SEPARATOR + "friends");
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Post[].class));
	}
	
	public List<Post> doGetForAUserFriendPosts(long id, long fid) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST + URL_SEPARATOR + id + URL_SEPARATOR + "friends" + URL_SEPARATOR + fid);
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Post[].class));
	}
	
	public List<Post> doGetForAllPublicPost() throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST)
				.setPath(RESOURCE_PATH + URL_SEPARATOR + RestapiType.POST + URL_SEPARATOR + "public");
		
		System.out.println("REST CLIENT: Target Uri --> " + uriBuilder.build().toString());
		
		return Arrays.asList(restTemplate.getForObject(uriBuilder.build(), Post[].class));
	}
	
	
}
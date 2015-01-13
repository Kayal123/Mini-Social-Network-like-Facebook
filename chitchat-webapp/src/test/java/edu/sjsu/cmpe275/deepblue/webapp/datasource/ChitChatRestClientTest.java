package edu.sjsu.cmpe275.deepblue.webapp.datasource;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:webapp-servlet-test.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChitChatRestClientTest {

	@Inject
	private ChitChatRestClient restClient;

	@Test
	public void test01() throws RestClientException, URISyntaxException {
		assertTrue(true);

		// Create test object model
		Organization org = new Organization.Builder("San Jose State University")
				.description("Best school ever!!")
				.street("1 Washington Square").city("San Jose").state("CA")
				.zip("95192").build();

		Person person = new Person.Builder("Tom", "Hank", "thank"
				+ System.currentTimeMillis() + "@sjsu.edu")
				.description("I love to act.").street("222 Hollywood Street")
				.city("Los Angelos").state("CA").zip("93842").build();

		Person friend = new Person.Builder("Joe", "Don", "jdon"
				+ System.currentTimeMillis() + "@sjsu.edu")
				.description("I love to fly.").street("111 Hollywood Street")
				.city("Los Angelos").state("CA").zip("93842").build();

		// Make a call to restapi to create organization and new user
		restClient.doPostForCreateOrg(org);
		restClient.doPostForCreatePerson(person);
		restClient.doPostForCreatePerson(friend);
	}

//	@Test
//	public void test02() throws RestClientException, URISyntaxException {
//		List<Friendship> friendships = restClient.doPostForFriendRequest(2, 3);
//		
//		for (Friendship friend : friendships) {
//			System.out.println("Show from test 02 \n" + friend + " "
//					+ friend.getStatus());
//		}
//		
//		assertTrue(true);
//	}
//
//	@Test
//	public void test03() throws RestClientException, URISyntaxException {
//		List<Friendship> friendships = restClient.doGetForAllFriendships(1);
//		for (Friendship friend : friendships) {
//			System.out.println("Show from test 03 \n" + friend + " "
//					+ friend.getStatus());
//		}
//		assertTrue(true);
//	}
}

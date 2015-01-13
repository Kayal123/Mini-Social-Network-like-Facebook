/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-service-test.xml" })
public class ProfileServiceTest {

	@Inject
	private ProfileService profileService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void getPersonByEmail() {
		Person person = new Person.Builder("Mary", "Beth", "mbeth@deepblue.com")
				.description("I love games.").street("111 Battery Street")
				.city("San Francisco").state("CA").zip("94111").build();

		assertTrue(profileService.createPerson(person) != null);

		Person fetchPerson = profileService.getPerson("mbeth@deepblue.com");
		assertTrue("Unable to get person by email", fetchPerson != null);
	}

}

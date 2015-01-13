/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.Status;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-persist-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PersonDaoTest {

	@Inject
	private PersonDao personDao;

	@Test
	public void createPersonTest() {
		Person person = new Person.Builder("John", "Doe", "jdoe@deepblue.com")
				.description("I love classical music.")
				.street("123 Battery Street").city("San Francisco").state("CA")
				.zip("94111").build();

		Person newPerson = personDao.save(person);

		System.out.println(newPerson.toString());

		assertTrue("Method save() return a null value", newPerson != null);
		assertTrue("Invalid id for new person " + newPerson.getId(),
				newPerson.getId() > 0);
		assertTrue(
				"Unexpected values set for new person " + newPerson.toString(),
				newPerson.getFirstname().equals("John")
						&& newPerson.getLastname().equals("Doe")
						&& newPerson.getDescription().equals(
								"I love classical music.")
						&& newPerson.getStreet().equals("123 Battery Street")
						&& newPerson.getCity().equals("San Francisco")
						&& newPerson.getState().equals("CA")
						&& newPerson.getZip().equals("94111")
						&& newPerson.getEmail().equals("jdoe@deepblue.com"));
		// TODO: Add any additional assertions here
	}

	@Test
	public void findByEmailTest() {
		Person person = new Person.Builder("Jack", "Black",
				"jblack1@deepblue.com").description("I love comedy.")
				.street("321 Battery Street").city("San Francisco").state("CA")
				.zip("94111").build();

		assertTrue(personDao.save(person) != null);

		Person fetchedPerson = personDao.findByEmail("jblack1@deepblue.com");
		assertTrue("Unable to retreive person by email", fetchedPerson != null
				&& fetchedPerson.getEmail().equals("jblack1@deepblue.com"));
	}

	@Test
	public void findByIdTest() {
		Person person = new Person.Builder("Jack", "Black",
				"jblack2@deepblue.com").description("I love comedy.")
				.street("321 Battery Street").city("San Francisco").state("CA")
				.zip("94111").build();

		long id = personDao.save(person).getId();
		assertTrue(id > 0);

		Person fetchedPerson = personDao.findById(id);
		assertTrue("Unable to retreive person by id", fetchedPerson != null
				&& fetchedPerson.getEmail().equals("jblack2@deepblue.com"));
	}

	@Test
	public void updatePerson() {

	}

	@Test
	public void deletePerson() {

	}

	@Test
	public void addRelationshipTest() {
		Person person1 = new Person.Builder("Naruto", "Uzumaki",
				"nuzumaki@deepblue.com").description("I love ramen.")
				.street("222 Battery Street").city("San Mateo").state("CA")
				.zip("94121").build();

		Person person2 = new Person.Builder("Sasuke", "Uchia",
				"suchia@deepblue.com").description("I dislike Naruto.")
				.street("333 Battery Street").city("San Jose").state("CA")
				.zip("94131").build();

		Person p1 = personDao.save(person1);
		Person p2 = personDao.save(person2);

		// Create friendship entity
		Friendship friendship = new Friendship();
		// Set value
		friendship.setPerson(p1);
		friendship.setFriend(p2);
		friendship.setStatus(Status.WAITING);
		// add to person list
		p1.getFriendships().add(friendship);
		personDao.save(p1);
		

		Friendship friendship2 = new Friendship();
		friendship2.setPerson(p2);
		friendship2.setFriend(p1);
		friendship2.setStatus(Status.PENDING);
		p2.getFriendships().add(friendship2);
		personDao.save(p2);
		
		System.out.println(p1);
	}
	
	

	@Test
	public void displayPersonWithFriends() {

	}
}

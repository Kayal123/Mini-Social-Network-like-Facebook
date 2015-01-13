/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.FriendshipId;
import edu.sjsu.cmpe275.deepblue.model.Friendship.Status;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-persist-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendshipDaoTest {

	@Inject
	private PersonDao personDao;
	@Inject
	private FriendshipDao friendshipDao;

	@Test
	public void test1_addFriendship() {
		Person person1 = new Person.Builder("Diane", "Han", "dhan@deepblue.com")
				.description("I love classical music.")
				.street("7 Battery Street").city("San Francisco").state("CA")
				.zip("94111").build();
		Person person2 = new Person.Builder("Mary", "Han", "mhan@deepblue.com")
				.description("I love classical music.").street("10 Bay Street")
				.city("San Clara").state("CA").zip("94122").build();
		personDao.save(person1);
		personDao.save(person2);
		Person p1 = personDao.findByEmail("dhan@deepblue.com");
		Person p2 = personDao.findByEmail("mhan@deepblue.com");

		// Below code can be ommitted when adding friendship because of cascade
		// effect set on person
		Friendship friendship1 = new Friendship(p1, p2, Status.WAITING);
		Friendship friendship2 = new Friendship(p2, p1, Status.PENDING);
		friendshipDao.save(friendship1);
		friendshipDao.save(friendship2);

		// Below code is required to add friend successfully. This method can
		// also
		// cascade to the FRIENDSHIP table
		p1.getFriendships().add(friendship1);
		p2.getFriendships().add(friendship2);
		personDao.save(p1);
		personDao.save(p2);

		// Check Person object
		assertTrue("Unexpected friend inserted.", p1.getFriendships().get(0)
				.getFriend().getEmail().equalsIgnoreCase("mhan@deepblue.com"));
		assertTrue("Unexpected person inserted into friend's friend", p2
				.getFriendships().get(0).getFriend().getEmail()
				.equalsIgnoreCase("dhan@deepblue.com"));

		assertTrue("Unexpected status for friendship1",
				friendship1.getStatus() == Status.WAITING);
		assertTrue("Unexpected status for friendship1",
				friendship2.getStatus() == Status.PENDING);

		System.out.println("TEST 1 STATE");
		System.out.println("Person 1 ...");
		System.out.println(p1);
		System.out.println(friendship1);
		System.out.println("Person 2 ...");
		System.out.println(p2);
		System.out.println(friendship2);
	}

	@Test
	public void test2_getFriendship() {
		Person p1 = personDao.findByEmail("dhan@deepblue.com");
		Person p2 = personDao.findByEmail("mhan@deepblue.com");

		FriendshipId fid = new Friendship.FriendshipId();
		fid.setPerson(p1);
		fid.setFriend(p2);

		Friendship fetchFriendship = friendshipDao.findById(fid);

		assertTrue("Unable to find friendship by id", fetchFriendship != null);
		assertTrue("Unexpected status value (" + fetchFriendship.getStatus()
				+ ").", fetchFriendship.getStatus() == Status.WAITING);

		System.out.println("TEST 2 STATE");
		System.out.println("Fetch friendship\n"
				+ fetchFriendship);
	}

	@Test
	public void test3_updateFriendship() {
		Person p1 = personDao.findByEmail("dhan@deepblue.com");
		Person p2 = personDao.findByEmail("mhan@deepblue.com");

		FriendshipId fid = new Friendship.FriendshipId();
		fid.setPerson(p1);
		fid.setFriend(p2);

		Friendship fetchFriendship = friendshipDao.findById(fid);

		fetchFriendship.setStatus(Status.ACCEPTED);

		Friendship updatedFriendship = friendshipDao
				.updateStatus(fetchFriendship);
		assertTrue("Unexpected updated status for friendship.",
				updatedFriendship.getStatus() == Status.ACCEPTED);

		System.out.println("TEST 3 STATE");
		System.out.println(updatedFriendship);
	}

	@Test
	public void test4_removeFriendship() {
		Person p1 = personDao.findByEmail("dhan@deepblue.com");
		Person p2 = personDao.findByEmail("mhan@deepblue.com");
		
		System.out.println("TEST 4 Before state");
		System.out.println(p1);
		System.out.println(p2);

		FriendshipId fid1 = new Friendship.FriendshipId();
		fid1.setPerson(p1);
		fid1.setFriend(p2);
		
		FriendshipId fid2 = new Friendship.FriendshipId();
		fid2.setPerson(p2);
		fid2.setFriend(p1);
		
		Friendship fetchFriendship1 = friendshipDao.findById(fid1);
		Friendship fetchFriendship2 = friendshipDao.findById(fid2);
		
		friendshipDao.remove(fetchFriendship1);
		friendshipDao.remove(fetchFriendship2);
		
		p1.getFriendships().clear();
		p2.getFriendships().clear();
		personDao.update(p1);
		personDao.update(p2);

		System.out.println("TEST 4 After state");
		System.out.println(p1);
		System.out.println(p2);
	}
}

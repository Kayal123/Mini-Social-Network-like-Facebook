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

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-persist-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenceTest {
	@Inject
	private PersonDao personDao;
	@Inject
	private OrganizationDao orgDao;
	@Inject
	private FriendshipDao friendshipDao;

	// Store state between tests
	private static long idOfOrgToFetch;
	private static long idOfOrgToUpdate;
	private static long idOfOrgToDelete;

	private static long idOfPersonToFetch;
	private static long idOfPersonToUpdate;
	private static long idOfPersonToDelete;

	@Test
	public void test002_create_person() {
		Person person1 = new Person.Builder("Tom", "Hank", "thank@sjsu.edu")
				.description("I love to act.").street("222 Hollywood Street")
				.city("Los Angelos").state("CA").zip("93842").build();
		Person p1 = personDao.save(person1);
		assertTrue("1.Error persisting new person",
				p1.getEmail().equals("thank@sjsu.edu"));
		idOfPersonToFetch = p1.getId();

		Person person2 = new Person.Builder("A", "Bmin", "abmin@sjsu.edu")
				.build();
		Person p2 = personDao.save(person2);
		assertTrue("2.Error persisting new person",
				p2.getEmail().equals("abmin@sjsu.edu"));

		Person person3 = new Person.Builder("A", "B", "abupdate@sjsu.edu")
				.build();
		Person p3 = personDao.save(person3);
		assertTrue("3.Error persisting new person",
				p3.getEmail().equals("abupdate@sjsu.edu"));
		idOfPersonToUpdate = p1.getId();

		Person person4 = new Person.Builder("A", "B", "abdelete@sjsu.edu")
				.build();
		Person p4 = personDao.save(person4);
		assertTrue("4.Error persisting new person",
				p4.getEmail().equals("abdelete@sjsu.edu"));
		idOfPersonToDelete = p1.getId();
	}

	@Test
	public void test001_create_organization() {
		// Insert organization with all params
		Person p1 = personDao.findById(idOfPersonToFetch);
		Organization org1 = new Organization.Builder(
				"San Jose State University").description("Best school ever!!")
				.street("1 Washington Square").city("San Jose").state("CA")
				.zip("95192").build();

		Organization o1 = orgDao.save(org1);
		assertTrue("1.Error persisting new organization.", o1.getName()
				.equalsIgnoreCase("San Jose State University"));
		idOfOrgToFetch = o1.getId();

		// Insert organization with minimal
		Organization org2 = new Organization.Builder("Test Org").build();
		Organization o2 = orgDao.save(org2);
		assertTrue("2.Error persisting new organization.", o2.getName()
				.equalsIgnoreCase("Test Org"));

		// Insert organization to be updated
		Organization org3 = new Organization.Builder("Test Org to be updated")
				.build();
		Organization o3 = orgDao.save(org3);
		assertTrue("3.Error persisting new organization.", o3.getName()
				.equalsIgnoreCase("Test Org to be updated"));
		idOfOrgToUpdate = o3.getId();

		// Insert organization with minimal
		Organization org4 = new Organization.Builder("Test Org to be deleted")
				.build();
		Organization o4 = orgDao.save(org4);
		assertTrue("4.Error persisting new organization.", o4.getName()
				.equalsIgnoreCase("Test Org to be deleted"));
		idOfOrgToDelete = o4.getId();
	}

	@Test
	public void test101_create_person_with_org() {
		// Create person
		Person person = new Person.Builder("Joe", "Don", "jd@sjsu.edu")
				.description("Person with org id")
				.organization(Long.toString(idOfOrgToFetch)).build();

		// Set the org object on person
		Organization org = orgDao.findOrgById(idOfOrgToFetch);
		org.addMember(person);
//		person.setOrg(org);

		// Persist
		Person p = personDao.save(person);
		assertTrue("Error persisting new person with org",
				p.getEmail().equals("jd@sjsu.edu"));
		
//		assertTrue("Person object contains incorrect value for field org", p.getOrg().getId() == idOfOrgToFetch);
		assertTrue("Organization object does not contain member", org.getMembers().contains(person));
		
		System.out.println("Let's see the output for person and organization.");
		System.out.println(p);
		System.out.println(org);
	}
}

/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.service.aspect;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.deepblue.dao.FriendshipDao;
import edu.sjsu.cmpe275.deepblue.dao.OrganizationDao;
import edu.sjsu.cmpe275.deepblue.dao.PersonDao;
import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.FriendshipId;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.service.exception.InvalidIdentifierException;

/**
 * @author deepblue
 * 
 */
@Component
@Aspect
public class ValidationAspect {

	@Inject
	private PersonDao personDao;
	@Inject
	private FriendshipDao friendshipDao;
	@Inject
	private OrganizationDao orgDao;

	// TODO: Need validation for findByEmail. Currently return null with status
	// code 200.
	@Before("(execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.updatePerson(long)) || "
			+ "execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.getPerson(long)) || "
			+ "execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.deletePerson(long)) ) ")
	public void checkInvalidIdentifierPerson(JoinPoint jp)
			throws InvalidIdentifierException {
		Object[] args = jp.getArgs();
		long id = args[0] instanceof Person ? ((Person) args[0]).getId()
				: (Long) args[0];
		if (personDao.findById(id) == null) {
			throw new InvalidIdentifierException("Person id " + id
					+ " does not exist for method call "
					+ jp.getSignature().getName());
		}
	}

	@Before("(execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.createPerson(..)) )")
	public void validateCreatePerson(JoinPoint jp) {
		Person person = (Person) jp.getArgs()[0];
		// TODO: Need to also check if person email already exists. The
		// following exception is uncaught
		// 'org.hibernate.exception.ConstraintViolationException' with a
		// response status code of 500

		long orgid = person.getOrgId();
		if (orgid > 0 && orgDao.findOrgById(orgid) == null) {
			throw new InvalidIdentifierException("Organization id " + orgid
					+ " does not exist. Cannot create new person profile.");
		}
	}

	@Before("(execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.addFriend(..)) || "
			+ "execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.removeFriend(..)) ) ")
	@Order(1)
	public void checkInvalidIdentifierFriendship(JoinPoint jp)
			throws InvalidIdentifierException {
		Object[] args = jp.getArgs();
		for (Object id : args) {
			if (personDao.findById((Long) id) == null) {
				throw new InvalidIdentifierException("Person id " + id
						+ " does not exist for method call "
						+ jp.getSignature().getName());
			}
		}
	}

	@Before("(execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.removeFriend(..)))")
	@Order(2)
	public void checkInvalidStateFriendship(JoinPoint jp)
			throws IllegalStateException {
		Object[] args = jp.getArgs();

		Person user1 = personDao.findById((Long) args[0]);
		Person user2 = personDao.findById((Long) args[1]);
		
// TODO: Need to update this method 
//		if (!user1.getFriends().contains(user2)) {
//			throw new IllegalStateException("Person id " + user1.getId()
//					+ " and " + user2.getId()
//					+ " are not friends. Method call "
//					+ jp.getSignature().getName() + " failed!");
//		}
	}

	@Before("(execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.getOrganization(..)) )")
	public void checkInvalidIdentifierOrganization(JoinPoint jp)
			throws InvalidIdentifierException {
		Object[] args = jp.getArgs();
		long id = args[0] instanceof Organization ? ((Organization) args[0])
				.getId() : (Long) args[0];
		if (orgDao.findOrgById(id) == null) {
			throw new InvalidIdentifierException("Organization id " + id
					+ " does not exist for method call "
					+ jp.getSignature().getName());
		}
	}

	@Before("execution(* edu.sjsu.cmpe275.deepblue.service.ProfileService.deleteOrganization(..)))")
	public void validateDeleteOrg(JoinPoint jp) throws IllegalStateException {
		Object[] args = jp.getArgs();
		long id = args[0] instanceof Organization ? ((Organization) args[0])
				.getId() : (Long) args[0];
		if (!personDao.listByOrg(id).isEmpty()) {
			throw new IllegalStateException("Organization id " + id
					+ " still has members. ");
		}

	}
}

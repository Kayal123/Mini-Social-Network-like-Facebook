/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.deepblue.dao.FriendshipDao;
import edu.sjsu.cmpe275.deepblue.dao.OrganizationDao;
import edu.sjsu.cmpe275.deepblue.dao.PersonDao;
import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.FriendshipId;
import edu.sjsu.cmpe275.deepblue.model.Friendship.Status;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Inject
	private PersonDao personDao;
	@Inject
	private FriendshipDao friendshipDao;
	@Inject
	private OrganizationDao orgDao;

	@Override
	@Transactional
	public Person createPerson(Person person) {
		if (person.getOrgId() > 0) {
			Organization org = orgDao.findOrgById(person.getOrgId());
//			person.setOrg(org);
		}
		return personDao.save(person);
	}

	@Override
	@Transactional
	public Person updatePerson(Person person) {
		Person existingPerson = personDao.findById(person.getId());
		person.setFriendships(existingPerson.getFriendships());
		return personDao.update(person);
	}

	@Override
	public Person getPerson(long id) {
		return personDao.findById(id);
	}

	@Override
	public Person getPerson(String email) {
		return personDao.findByEmail(email);
	}

	@Override
	@Transactional
	public Person deletePerson(long id) {
		friendshipDao.deleteAllById(id);
		return personDao.removeById(id);
	}

	@Override
	public List<Friendship> getAllFriends(long id) {
		return friendshipDao.findAllByPersonId(id);
	}

	@Override
	@Transactional
	public String addFriendRequest(long id1, long id2) {
		Person user1 = personDao.findById(id1);
		Person user2 = personDao.findById(id2);

		Friendship friendwaiting = new Friendship(user1, user2, Status.WAITING);
		Friendship friendpending = new Friendship(user2, user1, Status.PENDING);

		user1.getFriendships().add(friendwaiting);
		user2.getFriendships().add(friendpending);

		personDao.save(user1);
		personDao.save(user2);

		return "Friend request successful to " + user2.getEmail();
	}

	@Override
	@Transactional
	public String addFriendAccept(long id1, long id2) {
		Person user1 = personDao.findById(id1);
		Person user2 = personDao.findById(id2);

		FriendshipId fid1 = new Friendship.FriendshipId();
		fid1.setPerson(user1);
		fid1.setFriend(user2);
		FriendshipId fid2 = new Friendship.FriendshipId();
		fid2.setPerson(user2);
		fid2.setFriend(user1);

		Friendship fetchFriendship1 = friendshipDao.findById(fid1);
		Friendship fetchFriendship2 = friendshipDao.findById(fid2);
		if (fetchFriendship1 != null && fetchFriendship2 != null) {
			fetchFriendship1.setStatus(Status.ACCEPTED);
			fetchFriendship2.setStatus(Status.ACCEPTED);
			friendshipDao.updateStatus(fetchFriendship1);
			friendshipDao.updateStatus(fetchFriendship2);
			return user2.getFirstname() + " " + user2.getLastname()
					+ " friendship invitation has been accepted.";
		}
		return "Error accepting friendship invitation.";
	}

	@Override
	@Transactional
	public String addFriendReject(long id1, long id2) {
		Person user1 = personDao.findById(id1);
		Person user2 = personDao.findById(id2);

		FriendshipId fid1 = new Friendship.FriendshipId();
		fid1.setPerson(user1);
		fid1.setFriend(user2);
		FriendshipId fid2 = new Friendship.FriendshipId();
		fid2.setPerson(user2);
		fid2.setFriend(user1);

		Friendship fetchFriendship1 = friendshipDao.findById(fid1);
		Friendship fetchFriendship2 = friendshipDao.findById(fid2);
		if (fetchFriendship1 != null && fetchFriendship2 != null) {
			fetchFriendship1.setStatus(Status.REJECTED);
			fetchFriendship2.setStatus(Status.REJECTED);
			friendshipDao.updateStatus(fetchFriendship1);
			friendshipDao.updateStatus(fetchFriendship2);
			return user2.getFirstname() + " " + user2.getLastname()
					+ " friendship invitation has been rejected.";
		}
		return "Error rejecting friendship invitation.";
	}

	@Override
	@Transactional
	public String removeFriend(long id1, long id2) {
		Person user1 = personDao.findById(id1);
		Person user2 = personDao.findById(id2);

		FriendshipId fid1 = new Friendship.FriendshipId();
		fid1.setPerson(user1);
		fid1.setFriend(user2);

		FriendshipId fid2 = new Friendship.FriendshipId();
		fid2.setPerson(user2);
		fid2.setFriend(user1);

		Friendship fetchFriendship1 = friendshipDao.findById(fid1);
		Friendship fetchFriendship2 = friendshipDao.findById(fid2);

		friendshipDao.remove(fetchFriendship1);
		friendshipDao.remove(fetchFriendship2);

		user1.getFriendships().remove(fetchFriendship1);
		user2.getFriendships().remove(fetchFriendship2);

		personDao.update(user1);
		personDao.update(user2);

		return "The user " + user2.getEmail()
				+ " have been removed from friend list.";
	}

	@Override
	@Transactional
	public Organization createOrganization(Organization organization) {
		return orgDao.save(organization);
	}

	@Override
	public Organization getOrganization(long org_id) {
		return orgDao.findOrgById(org_id);
	}

	@Override
	@Transactional
	public Organization updateOrganization(Organization organization) {
		return orgDao.update(organization);
	}

	@Override
	@Transactional
	public Organization deleteOrganization(Organization organization) {
		return orgDao.deleteById(organization.getId());
	}

//	@Override
//	public List<Organization> getAllOrgByOwner(long org_id) {
//		return orgDao.findAllOrgById(org_id);
//	}

	@Override
	public List<Organization> getAllOrgs() {
		return orgDao.findAllOrg();
	}

	@Override
	public List<Organization> getAllOrgByMember(long member_id) {
		List<Organization> allOrgs = getAllOrgs();
		List<Organization> memberOrgs = new ArrayList<Organization>();
		for (Organization o : allOrgs) {
			List<Person> members = o.getMembers();
			for (Person member : members) {
				if (member.getId() == member_id) memberOrgs.add(o);
			}
		}
		return memberOrgs;
	}

}

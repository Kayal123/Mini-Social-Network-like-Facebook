package edu.sjsu.cmpe275.deepblue.service;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

public interface ProfileService {

	/**
	 * Creates a new user with a unique email address.
	 * 
	 * @param person
	 * @return Person entity with properties with id that was saved.
	 */
	Person createPerson(Person person);

	/**
	 * Updates person information without modifying friends list.
	 * 
	 * @param person
	 * @return Updated person entity.
	 */
	Person updatePerson(Person person);

	/**
	 * Retrieve person information including the person friends information (excluding thier list of friends).
	 * 
	 * @param id
	 * @return Person information including friends list.
	 */
	Person getPerson(long id);
	
	/**
	 * Retrieve person information including the person friends information (excluding thier list of friends).
	 * 
	 * @param email
	 * @return Person information including friends list.
	 */
	Person getPerson(String email);

	/**
	 * Remove person by the specified id.
	 * 
	 * @param id
	 * @return Person entity that was removed.
	 */
	Person deletePerson(long id);

	/**
	 * Return a list of friendship (friends and status)
	 * 
	 * @param id The person friends list
	 * @return
	 */
	List<Friendship> getAllFriends(long id);

	/**
	 * Create a new friendship request
	 * 
	 * @param id1 Person making request and waiting
	 * @param id2 Person being befriended, pending response
	 * @return
	 */
	String addFriendRequest(long id1, long id2);
	
	/**
	 * Accept an existing friendship request
	 * 
	 * @param id1 Person accepting
	 * @param id2 Person that made request
	 * @return
	 */
	String addFriendAccept(long id1, long id2);
	
	/**
	 * Reject an existing friendship request
	 * 
	 * @param id1 Person rejecting
	 * @param id2 Person being rejected
	 * @return
	 */
	String addFriendReject(long id1, long id2);

	/**
	 * Remove an existing relationship between two person by id.
	 * 
	 * @param id1
	 * @param id2
	 * @return Response in text when a relationship successfully removed.
	 */
	String removeFriend(long id1, long id2);

	/**
	 * Creates a new organization with a organization id auto generated and name is required.
	 * 
	 * @param organization
	 * @return Organization entity with properties with id and name that was saved.
	 */
	Organization createOrganization(Organization organization);

	/**
	 * Retrieve organization information with all the properties.
	 * 
	 * @param id
	 * @return Organization information.
	 */
	Organization getOrganization(long org_id);

	Organization updateOrganization(Organization organization);

	Organization deleteOrganization(Organization organization);
	
//	List<Organization> getAllOrgByOwner(long org_id);
	
	List<Organization> getAllOrgs();
	
	List<Organization> getAllOrgByMember(long member_id);
	
}

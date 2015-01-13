package edu.sjsu.cmpe275.deepblue.dao;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.FriendshipId;
import edu.sjsu.cmpe275.deepblue.model.Person;

public interface FriendshipDao {
	/**
	 * Add an entry to friendship that does not cascade to Person table.
	 * Friendship can be cascaded when persisting Person entity that has added
	 * an entry to friendship.
	 * 
	 * @param friendship
	 * @return Entity that was persisted to Friendship.
	 */
	Friendship save(Friendship friendship);

	/**
	 * Retrieve an entry from Friendship by using an EmbeddedId object.
	 * 
	 * @param fid
	 * @return Entity that was found or null.
	 */
	Friendship findById(FriendshipId fid);

	/**
	 * Remove friendship entity. WARNING: Need to handle removing friendship
	 * entry from corresponding Person entity.
	 * 
	 * @param friendship
	 * @return Entity that was deleted.
	 */
	Friendship remove(Friendship friendship);

	/**
	 * Update updatable attribute of the entity Friendship. Only Status can be
	 * updated.
	 * 
	 * @param friendship
	 * @return Entity that was updated successfully.
	 */
	Friendship updateStatus(Friendship friendship);

	/**
	 * All Friendship entity that contains the id as a Person_Id.
	 * 
	 * @param id
	 * @return
	 */
	List<Friendship> findAllByPersonId(long id);

	/**
	 * All Friendship entity that contains the id as a Friend_Person_Id.
	 * 
	 * @param id
	 * @return
	 */
	List<Friendship> findAllByFriendId(long id);

	/**
	 * All Friendship entity that contains the id as a Person_Id.
	 * 
	 * @param person
	 * @return
	 */
	List<Friendship> findAllByPersonId(Person person);

	/**
	 * All Friendship entity that contains the id as a Friend_Person_Id.
	 * 
	 * @param person
	 * @return
	 */
	List<Friendship> findAllByFriendId(Person person);
	
	/**
	 * Delete all Friendship entity that contains the id as a Person_Id or
	 * Friend_Person_Id.
	 * 
	 * @param id
	 */
	void deleteAllById(long id);
	
	/**
	 * Delete all Friendship entity that contains the id as a Person_Id or
	 * Friend_Person_Id.
	 * 
	 * @param person
	 */
	void deleteAllById(Person person);
}
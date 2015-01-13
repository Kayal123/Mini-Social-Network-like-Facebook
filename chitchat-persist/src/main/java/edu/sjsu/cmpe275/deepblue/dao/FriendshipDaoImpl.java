/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.FriendshipId;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@Repository
public class FriendshipDaoImpl implements FriendshipDao {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Add an entry to friendship that does not cascade to Person table.
	 * Friendship can be cascaded when persisting Person entity that has added
	 * an entry to friendship.
	 * 
	 * @param friendship
	 * @return Entity that was persisted to Friendship.
	 */
	public Friendship save(Friendship friendship) {
		em.persist(friendship);
		em.flush();
		return friendship;
	}

	/**
	 * Retrieve an entry from Friendship by using an EmbeddedId object.
	 * 
	 * @param fid
	 * @return Entity that was found or null.
	 */
	public Friendship findById(FriendshipId fid) {
		return em.find(Friendship.class, fid);
	}

	/**
	 * Remove friendship entity. WARNING: Need to handle removing friendship
	 * entry from corresponding Person entity.
	 * 
	 * @param friendship
	 * @return Entity that was deleted.
	 */
	public Friendship remove(Friendship friendship) {
		em.remove(friendship);
		return friendship;
	}

	/**
	 * Update updatable attribute of the entity Friendship. Only Status can be
	 * updated.
	 * 
	 * @param friendship
	 * @return Entity that was updated successfully.
	 */
	public Friendship updateStatus(Friendship friendship) {
		return em.merge(friendship);
	}

	/**
	 * All Friendship entity that contains the id as a Person_Id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Friendship> findAllByPersonId(long id) {
		TypedQuery<Friendship> query = em
				.createQuery(
						"SELECT f FROM Friendship f WHERE f.friendshipId.person.id = :id",
						Friendship.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * All Friendship entity that contains the id as a Friend_Person_Id.
	 * 
	 * @param id
	 * @return
	 */
	public List<Friendship> findAllByFriendId(long id) {
		TypedQuery<Friendship> query = em
				.createQuery(
						"SELECT f FROM Friendship f WHERE f.friendshipId.friend.id = :id",
						Friendship.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * All Friendship entity that contains the id as a Person_Id.
	 * 
	 * @param person
	 * @return
	 */
	public List<Friendship> findAllByPersonId(Person person) {
		return findAllByPersonId(person.getId());
	}

	/**
	 * All Friendship entity that contains the id as a Friend_Person_Id.
	 * 
	 * @param person
	 * @return
	 */
	public List<Friendship> findAllByFriendId(Person person) {
		return findAllByFriendId(person.getId());
	}

	/**
	 * Delete all Friendship entity that contains the id as a Person_Id or
	 * Friend_Person_Id.
	 * 
	 * @param id
	 */
	public void deleteAllById(long id) {
		Query query = em
				.createQuery("DELETE FROM Friendship f WHERE f.friendshipId.person.id = :id OR f.friendshipId.friend.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	/**
	 * Delete all Friendship entity that contains the id as a Person_Id or
	 * Friend_Person_Id.
	 * 
	 * @param person
	 */
	public void deleteAllById(Person person) {
		deleteAllById(person.getId());
	}
}

/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;

/**
 * @author deepblue
 *
 */
@Repository
public class PostDaoImpl implements PostDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Post save(Post post) {
		em.persist(post);
		em.flush();
		return post;
	}

	@Override
	public Post findById(long id) {
		return em.find(Post.class, id);
	}

	@Override
	public List<Post> findAllByPersonId(long id) {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE author_id = :id",
						Post.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<Post> findAllByPersonId(Person person) {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE author_id = :id",
						Post.class);
		query.setParameter("id", person.getId());
		return query.getResultList();
	}

	@Override
	public Post remove(Post post) {
		em.remove(post);
		return post;
	}

	@Override
	public void deleteAllByPersonId(long id) {
		Query query = em
				.createQuery("DELETE FROM Post p WHERE author_id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void deleteAllByPersonId(Person person) {
		Query query = em
				.createQuery("DELETE FROM Post p WHERE author_id = :id");
		query.setParameter("id", person.getId());
		query.executeUpdate();
	}

	@Override
	public Post update(Post post) {
		return em.merge(post);
	}

	@Override
	public List<Post> findAllByDate(Date date) {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE dateCreated = :date",
						Post.class);
		query.setParameter("date", date, TemporalType.DATE);
		return query.getResultList();
	}

	@Override
	public List<Post> findAllPrivate(Person person) {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE author_id = :id AND isPrivate = :private",
						Post.class);
		query.setParameter("id", person.getId());
		query.setParameter("private", true);
		return query.getResultList();
	}

	@Override
	public List<Post> findAllByDate(Date start, Date end) {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE dateCreated BETWEEN :start AND :end",
						Post.class);
		query.setParameter("date", start, TemporalType.DATE);
		query.setParameter("date", end, TemporalType.DATE);
		return query.getResultList();
	}

	@Override
	public List<Post> findAllPublic() {
		TypedQuery<Post> query = em
				.createQuery(
						"SELECT p FROM Post p WHERE isPrivate = :private",
						Post.class);
		query.setParameter("private", false);
		return query.getResultList();
	}

}

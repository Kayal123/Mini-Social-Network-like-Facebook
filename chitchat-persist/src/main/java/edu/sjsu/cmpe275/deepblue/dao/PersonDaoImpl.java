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

import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Person save(Person person) {
		em.persist(person);
		em.flush();
		return person;
	}

	@Override
	public Person findByEmail(String email) {
		TypedQuery<Person> query = em.createQuery(
				"SELECT p FROM Person p WHERE p.email = :email", Person.class);
		query.setParameter("email", email);
		List<Person> rs = query.getResultList();
		return rs.size() == 1 ? rs.get(0) : null;
	}

	@Override
	public Person findById(long id) {
		return em.find(Person.class, id);
	}

	@Override
	public Person removeById(long id) {
		Person person = em.find(Person.class, id);
		em.remove(person);
		return person;
	}

	@Override
	public Person update(Person person) {
		return em.merge(person);
	}

	@Override
	public List<Person> listByOrg(long org_id) {
		Query membersQuery = em
				.createNativeQuery("SELECT * FROM person WHERE org_id= ?");
		membersQuery.setParameter(1, org_id);

		return membersQuery.getResultList();
	}

}

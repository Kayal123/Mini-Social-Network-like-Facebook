/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Post;

/**
 * @author deepblue
 * 
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Organization save(Organization organization) {
		em.persist(organization);
		em.flush();
		return organization;
	}

	@Override
	public Organization findOrgById(long org_id) {
		return em.find(Organization.class, org_id);
	}

	@Override
	public Organization update(Organization organization) {
		return em.merge(organization);
	}

	@Override
	public Organization deleteById(long id) {
		Organization org = em.find(Organization.class, id);
		em.remove(org);
		return org;
	}

//	@Override
//	public List<Organization> findAllOrgById(long org_id) {
//		TypedQuery<Organization> query = em
//				.createQuery(
//						"SELECT o FROM Organization o WHERE org_id = :id",
//						Organization.class);
//		query.setParameter("id", org_id);
//		return query.getResultList();
//	}

	@Override
	public List<Organization> findAllOrg() {
		TypedQuery<Organization> query = em
				.createQuery(
						"SELECT o FROM Organization o",
						Organization.class);
		return query.getResultList();
	}

}

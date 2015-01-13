/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Organization;

/**
 * @author deepblue
 *
 */
public interface OrganizationDao {

	Organization save(Organization organization);

	Organization findOrgById(long org_id);
	
//	List<Organization> findAllOrgById(long org_id);
	
	List<Organization> findAllOrg();

	Organization update(Organization organization);

	Organization deleteById(long id);

}

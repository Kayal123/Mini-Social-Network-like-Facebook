/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author layya_000
 *
 */
public interface OrganizationService {

	Organization createOrg(Organization org);
	Organization getOrg(long id);
	Organization updateOrg(Organization org);
	Organization deleteOrg(long id);
//	List<Organization> getAllOrgByOwner(long id);
	List<Organization> getAllOrgs();
	List<Organization> getAllOrgByMember(long id);
	void joinOrg(long org_id, Person applicant);
	void leaveOrg(long org_id, Person applicant);
}

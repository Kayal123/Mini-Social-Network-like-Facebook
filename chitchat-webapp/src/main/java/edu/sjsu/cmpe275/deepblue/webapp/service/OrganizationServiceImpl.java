/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.webapp.service;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.webapp.datasource.ChitChatRestClient;

/**
 * @author layya_000
 *
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService{
	@Inject
	private ChitChatRestClient restClient;

	@Override
	public Organization createOrg(Organization org) {
		// TODO Auto-generated method stub
		try {
			return restClient.doPostForCreateOrg(org);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Organization getOrg(long id) {
		// TODO Auto-generated method stub
		try {
			return restClient.doGetForFetchOrg(id);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Organization updateOrg(Organization org) {
		// TODO Auto-generated method stub
		try {
			return restClient.doPostForUpdateOrg(org);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Organization deleteOrg(long id) {
		// TODO Auto-generated method stub
		try {
			Organization o = getOrg(id);
			if(o.getMembers().isEmpty()) return restClient.doDeleteForDeleteOrg(id);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public List<Organization> getAllOrgByOwner(long id) {
//		// TODO Auto-generated method stub
//		try {
//			return restClient.doGetForFetchOwnerOrg(id);
//		} catch (RestClientException | URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public List<Organization> getAllOrgs() {
		// TODO Auto-generated method stub
		try {
			return restClient.doGetForFetchAllOrg();
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Organization> getAllOrgByMember(long id) {
		// TODO Auto-generated method stub
		try {
			return restClient.doGetForFetchMemberOrg(id);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void joinOrg(long org_id, Person applicant) {
		try {
			Organization o = getOrg(org_id);
			List<Person> members = o.getMembers();
			int i = 0;
			while(i < (members.size()-1) && members.get(i).getId() != applicant.getId()) i++;
			if (i>=members.size()) o.addMember(applicant);
			updateOrg(o);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void leaveOrg(long org_id, Person applicant) {
		try {
			Organization o = getOrg(org_id);
			List<Person> members = o.getMembers();
			int i = 0;
			while(i < (members.size()-1) && members.get(i).getId() != applicant.getId()) i++;
			if (i<members.size()) o.removeMember(applicant);
			updateOrg(o);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

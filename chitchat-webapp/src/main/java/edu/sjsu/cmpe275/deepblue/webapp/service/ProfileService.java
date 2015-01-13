/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.webapp.service;

import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author layya_000
 *
 */
public interface ProfileService {

	Person createProfile(Person person);
	Person getProfile(String email);
	Person getProfile(long id);
	Person updateProfile(Person person);
	Person deleteProfile(long id);
	
}

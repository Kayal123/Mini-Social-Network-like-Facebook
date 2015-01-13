package edu.sjsu.cmpe275.deepblue.dao;

import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Person;

public interface PersonDao {

	Person save(Person person);

	Person findByEmail(String email);

	Person findById(long id);

	Person removeById(long id);

	Person update(Person person);

	List<Person> listByOrg(long org_id);

}
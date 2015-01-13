package edu.sjsu.cmpe275.deepblue.dao;

import java.util.Date;
import java.util.List;

import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;

public interface PostDao {
	/**
	 * Add an entry to Post table.
	 * 
	 * @param post
	 * @return Entity that was persisted to Post.
	 */
	Post save(Post post);

	/**
	 * Add an entry to Post table.
	 * 
	 * @param post
	 * @return Entity that was persisted to Post.
	 */
	Post update(Post post);

	/**
	 * Retrieve an entry from Post by post ID.
	 * 
	 * @param id
	 * @return Entity that was found or null.
	 */
	Post findById(long id);

	/**
	 * Retrieve all entries from Post table by author ID.
	 * 
	 * @param id
	 * @return 
	 */	
	List<Post> findAllByPersonId(long id);

	/**
	 * Retrieve all entries from Post table by author ID.
	 * 
	 * @param person
	 * @return 
	 */		
	List<Post> findAllByPersonId(Person person);

	/**
	 * Retrieve all entries from Post table by date.
	 * 
	 * @param person
	 * @return 
	 */		
	List<Post> findAllByDate(Date date);

	/**
	 * Retrieve all entries from Post table by date.
	 * 
	 * @param person
	 * @return 
	 */		
	List<Post> findAllByDate(Date start, Date end);

	/**
	 * Retrieve all entries from author's friends.
	 * 
	 * @param person
	 * @return 
	 */		
	List<Post> findAllPrivate(Person person);

	/**
	 * Retrieve all entries from author's friends.
	 * 
	 * @param person
	 * @return 
	 */		
	List<Post> findAllPublic();

	/**
	 * Remove entry from Post table by post ID
	 * 
	 * @param id
	 * @return Entity that was deleted.
	 */	
	Post remove(Post post);

	/**
	 * Delete all entries from Post table by author ID
	 * 
	 * @param id
	 */		
	void deleteAllByPersonId(long id);

	/**
	 * Delete all entries from Post table by author ID
	 * 
	 * @param person
	 */			
	void deleteAllByPersonId(Person person);
}
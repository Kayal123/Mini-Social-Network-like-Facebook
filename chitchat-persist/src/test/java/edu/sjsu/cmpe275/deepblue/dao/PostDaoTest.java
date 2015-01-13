/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.dao;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.sjsu.cmpe275.deepblue.model.Post;
import edu.sjsu.cmpe275.deepblue.model.Person;

/**
 * @author deepblue
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-persist-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostDaoTest {

	@Inject
	private PersonDao personDao;
	@Inject
	private PostDao postDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_addPost() {
		Person p1 = personDao.findById(1);
		System.out.println(p1.toString());
		String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		Post post = new Post();
		post.setAuthor(p1);
		post.setContent(content);
		post.setPrivacy("private");
		Post newPost = postDao.save(post);
		System.out.println(newPost.toString());
		Date dateNow = new Date();
		System.out.println(dateNow.toString());
		// Check Post object
		assertTrue("Method save() return a null value", newPost != null);
	}

	@Test
	public void test_findById() {
		Post post = postDao.findById(1);
		System.out.println(post.toString());

		// Check Post object
		assertTrue("Method findById() return a null value", post.getContent().startsWith("Lorem"));
	}

	@Test
	public void test_findAllByPersonId() {
		List<Post> posts = postDao.findAllByPersonId(1);

		// Check Post object
		assertTrue("Method findAllByPersonId() return a null value", posts.isEmpty() == false);
	}
	
	@Test
	public void test_findAllPrivate() {
		Person p1 = personDao.findById(1);
		String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		Post post = new Post();
		post.setAuthor(p1);
		post.setContent(content);
		post.setPrivacy("public");
		postDao.save(post);
		List<Post> posts = postDao.findAllPrivate(p1);
		System.out.println("total posts : " + posts.size());
		
		assertTrue("Method findAllPrivate() return a null value", posts.isEmpty() == false);	
	}
//	@SuppressWarnings("deprecation")
//	@Test
//	public void test_findAllByDate() {
//		Person p1 = personDao.findById(1);
//		String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//		Post post = new Post();
//		post.setAuthor(p1);
//		post.setContent(content);
//		post.setPrivate(true);
//		postDao.save(post);
//		
//		
//		Date date = new Date();
//		Date nextDay = new Date();
//		nextDay.setDate(date.getDate()+1);
//        
//		List<Post> posts = postDao.findAllByDate(date, nextDay);
//		System.out.println("total posts : " + posts.size());
//		
//		//assertTrue("Method findAllByDate() return a null value", posts.isEmpty() == false);
//	}
}

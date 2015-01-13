/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.deepblue.dao.FriendshipDao;
import edu.sjsu.cmpe275.deepblue.dao.PostDao;
import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Friendship.Status;
import edu.sjsu.cmpe275.deepblue.model.Person;
import edu.sjsu.cmpe275.deepblue.model.Post;

/**
 * @author layya_000
 *
 */
@Service("postService")
public class PostServiceImpl implements PostService {

	@Inject
	private PostDao postDao;

	@Inject
	private FriendshipDao friendshipDao;
	
	@Override
	@Transactional
	public Post createPost(Post post) {
		if (post.getContent().isEmpty()) {
			//TODO throw exception
		}
		return postDao.save(post);
	}

	@Override
	@Transactional
	public Post updatePost(Post post) {
		if (post.getContent().isEmpty()) {
			//TODO throw exception
		}
		return postDao.update(post);
	}

	@Override
	public Post getPost(long id) {
		return postDao.findById(id);
	}

	@Override
	@Transactional
	public Post deletePost(long id) {
		Post post = postDao.findById(id);		
		return postDao.remove(post);
	}

	@Override
	public List<Post> getAllPostByAuthor(long id) {
		return postDao.findAllByPersonId(id);
	}

	@Override
	public List<Post> getAllPostsByAuthorAllFriends(long id) {
		List<Post> posts = new ArrayList<Post>();
		List<Friendship> friends = friendshipDao.findAllByPersonId(id);
		for (Friendship friend : friends) {
			if(friend.getStatus() == Status.ACCEPTED)
			{
				Person f = friend.getFriend();
				List<Post> p = postDao.findAllByPersonId(f.getId());
				posts.addAll(p);
			}
		}
		return posts;
	}

	@Override
	public List<Post> getAllPostsByAuthorFriend(long id, long fid) {
		List<Friendship> friends = friendshipDao.findAllByPersonId(id);
		for (Friendship friend : friends) {
			Person f = friend.getFriend();
			if (f.getId() == fid) {
				List<Post> p = postDao.findAllByPersonId(fid);
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Post> getAllPublicPosts() {
		return postDao.findAllPublic();
	}

	@Override
	public Post getPost(Post post) {
		Post existing = postDao.findById(post.getId());
//		if (post.getAuthor().getId() == existing.getAuthor().getId()) {
			return existing;
//		}
		
//		return null;
	}

}

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Post;
import model.Tweetuser;
import postTools.DBUtil;

public class PostDB {

	public static List<Post> select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT p FROM Post p ORDER BY p.postDate DESC";
		TypedQuery<Post> q = em.createQuery(query, Post.class);
		try {
			List<Post> postList = q.getResultList();
			return postList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Post> selectByKeyword(String keyword) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT p FROM Post p WHERE p.content LIKE '%" + keyword + "%' ORDER BY p.postDate DESC";
		TypedQuery<Post> q = em.createQuery(query, Post.class);
		try {
			List<Post> postList = q.getResultList();
			return postList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Post> selectByUser(String user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		long userId = UserDB.selectByName(user).getId();
		String query = "SELECT p FROM Post p WHERE p.tweetuser.id = " + userId + " ORDER BY p.postDate DESC";
		TypedQuery<Post> q = em.createQuery(query, Post.class);
		try {
			List<Post> postList = q.getResultList();
			return postList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Post> selectByUserKey(String user, String keyword) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		long userId = UserDB.selectByName(user).getId();
		String query = "SELECT p FROM Post p WHERE p.content LIKE '%" + keyword + "%' AND p.tweetuser.id = " + userId + " ORDER BY p.postDate DESC";
		TypedQuery<Post> q = em.createQuery(query, Post.class);
		try {
			List<Post> postList = q.getResultList();
			return postList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Post post) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(post);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Post post) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(post);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Post post) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(post));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
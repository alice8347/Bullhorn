import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Tweetuser;
import postTools.DBUtil;

public class UserDB {

	public static List<Tweetuser> select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT u FROM Tweetuser u";
		TypedQuery<Tweetuser> q = em.createQuery(query, Tweetuser.class);
		try {
			List<Tweetuser> userList = q.getResultList();
			return userList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Tweetuser selectByName(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT u FROM Tweetuser u WHERE u.name = '" + userName + "'";
		TypedQuery<Tweetuser> q = em.createQuery(query, Tweetuser.class);
		try {
			Tweetuser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Tweetuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Tweetuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Tweetuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
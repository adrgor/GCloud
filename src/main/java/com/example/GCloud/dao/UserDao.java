package com.example.GCloud.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.example.GCloud.model.User;

@Repository
public class UserDao {
	private Session session;
	public UserDao(){
		Configuration config = new Configuration().configure().addAnnotatedClass(User.class);
        SessionFactory sf = config.buildSessionFactory();
        this.session = sf.openSession();
	}
	
	public User getUserById(int id) {
		User user = null;
		session.beginTransaction();
			user = session.get(User.class, id);
		session.getTransaction().commit();
		return user;
	}
	
	public User getUserByLoginAndPassword(String login, String password) {
		User user = null;
		System.out.println(login);
		System.out.println(password);
		session.beginTransaction();
			user = (User) session.createQuery("from User u WHERE u.loginName=:lName AND u.password=:pass")
					.setParameter("lName", login)
					.setParameter("pass", password)
					.getSingleResult();
		session.getTransaction().commit();
		return user;
	}
	
	public void deleteUserById(int id) {
		session.beginTransaction();
			session.delete(getUserById(id));
		session.getTransaction().commit();
	}
	
	public void addUser(User user) {
		session.beginTransaction();
			session.save(user);
		session.getTransaction().commit();
	}
}

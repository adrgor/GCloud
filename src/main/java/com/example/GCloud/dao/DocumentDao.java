package com.example.GCloud.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.GCloud.model.Document;
import com.example.GCloud.model.User;

@Repository
public class DocumentDao {
	private Session session;
	public DocumentDao(){
		Configuration config = new Configuration().configure().addAnnotatedClass(Document.class);
        SessionFactory sf = config.buildSessionFactory();
        this.session = sf.openSession();
	}
	
	public void save(Document document) {
		session.beginTransaction();
		session.save(document);
		session.getTransaction().commit();
	}
	
	public List<Document> getUsersDocuments(int userId){
		List<Document> userDocuments;
		session.beginTransaction();
			Query query = session.createQuery("from Document where userId = :id");
			query.setParameter("id", userId);
			userDocuments = query.getResultList();
		session.getTransaction().commit();
		return userDocuments;
	}
}

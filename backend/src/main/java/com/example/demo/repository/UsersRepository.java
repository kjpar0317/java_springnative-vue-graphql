package com.example.demo.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public class UsersRepository {
	private EntityManager em;
	
	public UsersRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Users> getUserByLoginInfo(String userId, String passwd) {
		Query query = em.createNativeQuery("""
				SELECT
					id, name, password, role
				FROM USERS
				WHERE id=? AND password=?
				""", 
				"UsersMapping");
		
		query.setParameter(1, userId);
		query.setParameter(2, passwd);
		
		return query.getResultList().stream().findFirst();
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Users> getUserByLoginId(String id) {
		Query query = em.createNativeQuery("""
				SELECT
					id, name, password, role
				FROM USERS
				WHERE id=?
				""", 
				"UsersMapping");

		query.setParameter(1, id);
		
		return query.getResultList().stream().findFirst();
    }
}

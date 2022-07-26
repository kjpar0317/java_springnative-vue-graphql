package com.example.demo.repository;

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
	public Users getUserByLoginInfo(String userId, String passwd) {
		Query query = em.createNativeQuery("""
				SELECT
					id, name, password, role
				FROM USERS
				WHERE id=? AND password=?
				""", 
				"UsersMapping");
		
		query.setParameter(1, userId);
		query.setParameter(2, passwd);
		
		return (Users) query.getResultList().stream().findFirst().orElse(null);
	}
	
    @SuppressWarnings("unchecked")
	public Users getUserByLoginId(String id) {
		Query query = em.createNativeQuery("""
				SELECT
					id, name, password, role
				FROM USERS
				WHERE id=?
				""", 
				"UsersMapping");

		query.setParameter(1, id);
		
		return (Users) query.getResultList().stream().findFirst().orElse(null);
    }
}

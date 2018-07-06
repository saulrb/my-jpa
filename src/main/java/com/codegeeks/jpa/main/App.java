package com.codegeeks.jpa.main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import javax.persistence.criteria.CriteriaQuery;

import com.codegeeks.jpa.entity.Geek;
import com.codegeeks.jpa.entity.Person;

/**
 * App JPA Example
 *
 */
public class App 
{
	private static final Logger LOGGER = Logger.getLogger("App");
	
    public static void main( String[] args )
    {
    	App main = new App();
    	main.run();
    }
    
    public void run() {
    	EntityManagerFactory factory = null;
    	EntityManager entityManager = null;
    	
    	try {
    		factory = Persistence.createEntityManagerFactory("PersistenceUnit");
    		entityManager = factory.createEntityManager();
    		persistPerson(entityManager);
    		persistGeek(entityManager);
    		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    		CriteriaQuery<Person> query = builder.createQuery(Person.class);
    		Root<Person> personRoot = query.from(Person.class);
    		query.where(builder.equal(personRoot.get("firstName"), "Homer"));
    		List<Person> resultList = entityManager.createQuery(query).getResultList();
    	}catch(IllegalStateException e) {
    		LOGGER.log(Level.SEVERE, e.getMessage(), e);
    	}finally {
    		if (entityManager != null) {
    			entityManager.close();
    		}
    		if (factory != null) {
    			factory.close();
    		}
    	}
    	
    }
    
	private void persistPerson(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Person person = new Person();
			person.setFirstName("Homer");
			person.setLastName("Simpson");
			entityManager.persist(person);
			transaction.commit();

		} catch (EntityExistsException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	private void persistGeek(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Geek geek = new Geek();
			geek.setFirstName("Gavin");
			geek.setLastName("Coffee");
			geek.setFaboriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			geek = new Geek();
			geek.setFirstName("Thomas");
			geek.setLastName("Micro");
			geek.setFaboriteProgrammingLanguage("C#");
			entityManager.persist(geek);
			geek = new Geek();
			geek.setFirstName("Christian");
			geek.setLastName("Cup");
			geek.setFaboriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			transaction.commit();
		} catch(EntityExistsException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
}

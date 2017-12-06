package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableApplicationTests {
	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void query() {
		List<Person> list = em.createQuery("select p from Person p ", Person.class).getResultList();
		System.out.println(list);
	}

}

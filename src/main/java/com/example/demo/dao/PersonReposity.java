package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;

public interface PersonReposity extends JpaRepository<Person, Serializable> {
	public Person findById(String id);
}

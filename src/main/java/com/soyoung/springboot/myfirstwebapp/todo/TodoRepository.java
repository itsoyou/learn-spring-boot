package com.soyoung.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/** Repository supports Database interactions for the Entity */
public interface TodoRepository extends JpaRepository<Todo, Integer>{

	public List<Todo> findByUsername(String username);

}

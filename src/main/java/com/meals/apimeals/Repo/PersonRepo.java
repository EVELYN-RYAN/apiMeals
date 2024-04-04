package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meals.apimeals.Models.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{
    
    Person findByPersonId(long personId);
}

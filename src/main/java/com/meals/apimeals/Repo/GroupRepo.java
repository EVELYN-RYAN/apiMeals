package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meals.apimeals.Models.Group;


public interface GroupRepo extends JpaRepository<Group, Long> {

    Group findByGroupId(long groupId);    
}

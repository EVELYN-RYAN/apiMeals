package com.meals.apimeals.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meals.apimeals.Models.Family;
import com.meals.apimeals.Models.Group;


public interface FamilyRepo extends JpaRepository<Family, Long>{
    Family findByFamilyId(long familyId);

    List<Family> findByGroup(Group group);
}

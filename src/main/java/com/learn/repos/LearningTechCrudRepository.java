package com.learn.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.LearningTech;

@Repository
public interface LearningTechCrudRepository extends CrudRepository<LearningTech, Integer>{

}

package com.learn.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.learn.data.LearningTech;

@Repository
public interface LearningTechMongoRepository extends MongoRepository<LearningTech, Integer> {

	//db.learningTech.find({$and:[{_id:{$gte:?0}},{_id:{$lte:?1}}]})
	@Query(value="{$and:[{_id:{$gte:?0}},{_id:{$lte:?1}}]}")
	List<LearningTech> searchRanged(int fromId, int toId);
	
}

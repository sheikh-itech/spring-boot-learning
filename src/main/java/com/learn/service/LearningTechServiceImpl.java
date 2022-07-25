package com.learn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.learn.data.LearningTech;
import com.learn.excp.DuplicateLearningTech;
import com.learn.excp.LearningTechNotPresent;
import com.learn.repos.LearningTechCrudRepository;
import com.learn.repos.LearningTechMongoRepository;

@Service
public class LearningTechServiceImpl implements LearningTechService {
	
	private static final Logger logger = LoggerFactory.getLogger(LearningTechServiceImpl.class);
	@Autowired
	private LearningTechMongoRepository mongoRepository;
	@Autowired
	private LearningTechCrudRepository crudRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public LearningTech addNewTech(LearningTech tech) throws DuplicateLearningTech {
		try {
			
			mongoRepository.insert(tech);
			logger.info("LearningTech object saved to database");
		} catch(DuplicateKeyException ex) {
			throw new DuplicateLearningTech();
		} catch(Exception ex) {
			logger.info(ex.getMessage());
		}
		return tech;
	}

	public LearningTech updateTech(LearningTech tech) throws LearningTechNotPresent {
			
		if(mongoRepository.existsById(tech.getId())) {
			tech = mongoRepository.save(tech);
		} else {
			throw new LearningTechNotPresent();
		}
		logger.info("LearningTech object updated to database");
		return tech;
	}

	public LearningTech deleteTech(int id) throws LearningTechNotPresent {
	
		Optional<LearningTech> tech = mongoRepository.findById(id);
		if(tech.isPresent()) {
			mongoRepository.deleteById(id);
		} else {
			throw new LearningTechNotPresent();
		}
		logger.info("LearningTech object deleted from database");
		return tech.get();
	}

	public List<LearningTech> getAllTech() {

		return mongoRepository.findAll();
	}

	public LearningTech searchTech(int id) throws LearningTechNotPresent {

		Optional<LearningTech> tech = mongoRepository.findById(id);
		
		if(tech.isPresent())
			return tech.get();
		
		throw new LearningTechNotPresent();
	}

	public List<LearningTech> searchRangedTech(int fromId, int toId) throws LearningTechNotPresent {
		
		List<LearningTech> search = null;
		try {
			search = mongoRepository.searchRanged(fromId, toId);
			if(null==search)
				new LearningTechNotPresent();
			
		} catch(Exception ex) {
			logger.info(ex.getMessage());
		}
		return search;
	}
	
	public List<LearningTech> findByQuery(int from, int to) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").gte(from).lte(to));
		List<LearningTech> search = null;
		try {
			search = mongoTemplate.find(query, LearningTech.class);
			if(null==search)
				new LearningTechNotPresent();
			
		} catch(Exception ex) {
			logger.info(ex.getMessage());
		}
		return search;
	}
}

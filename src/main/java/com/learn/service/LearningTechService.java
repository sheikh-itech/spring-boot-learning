package com.learn.service;

import java.util.List;

import com.learn.data.LearningTech;
import com.learn.excp.DuplicateLearningTech;
import com.learn.excp.LearningTechNotPresent;

public interface LearningTechService {

	LearningTech addNewTech(LearningTech tech) throws DuplicateLearningTech;
	LearningTech updateTech(LearningTech tech) throws LearningTechNotPresent;
	LearningTech searchTech(int id) throws LearningTechNotPresent;
	List<LearningTech> getAllTech();
	List<LearningTech> searchRangedTech(int fromId, int toId) throws LearningTechNotPresent;
	LearningTech deleteTech(int id) throws LearningTechNotPresent;
	public List<LearningTech> findByQuery(int from, int to);
}

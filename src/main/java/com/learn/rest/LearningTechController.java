package com.learn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.LearningTech;
import com.learn.excp.DuplicateLearningTech;
import com.learn.excp.LearningTechNotPresent;
import com.learn.service.LearningTechService;

@RestController
@RequestMapping(value="tech")
public class LearningTechController {

	@Autowired
	private LearningTechService service;
	
	@RequestMapping(value="list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public LearningTech searchTech(@RequestParam("id") int id) throws LearningTechNotPresent {

		return service.searchTech(id);
	}

	@RequestMapping(value="listAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LearningTech>> getAllTech() {
		
		return new ResponseEntity<List<LearningTech>>(service.getAllTech(), HttpStatus.OK);
	}

	@RequestMapping(value="listRanged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LearningTech>> searchRangedTech(@RequestParam("from") int from, @RequestParam("to") int to) throws LearningTechNotPresent {
		service.findByQuery(from, to);
		return new ResponseEntity<List<LearningTech>>(service.searchRangedTech(from, to), HttpStatus.OK);
	}

	@RequestMapping(value="add", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LearningTech> addNewTech(@RequestBody LearningTech tech) throws DuplicateLearningTech {
		
		return new ResponseEntity<LearningTech>(service.addNewTech(tech), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="updateTech", method = RequestMethod.POST)
	public ResponseEntity<LearningTech> updateTech(@RequestBody LearningTech tech) throws LearningTechNotPresent {

		return new ResponseEntity<LearningTech>(service.updateTech(tech), HttpStatus.CREATED);
	}

	@RequestMapping(value="deleteTech", method = RequestMethod.GET)
	public ResponseEntity<LearningTech> deleteTech(@RequestParam("id") int id) throws LearningTechNotPresent {

		return new ResponseEntity<LearningTech>(service.deleteTech(id), HttpStatus.OK);
	}
}

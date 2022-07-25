package com.learn.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Service
public class NextSequence {

	@Autowired
	private MongoOperations mongo;
	
	public int getNextSequence() {
		
		CustomSequence counter = mongo.findAndModify(query(where("_id").is("CustomSequence")),
	            new Update().inc("seq",1),
	            options().returnNew(true).upsert(true),
	            CustomSequence.class);
		
		return counter.getSeq();
	}
}

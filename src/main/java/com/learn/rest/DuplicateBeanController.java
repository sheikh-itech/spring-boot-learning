package com.learn.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.bean1.DuplicateBean;

/**	We can mark bean @Primary
 * 	Or can qualify using @Qualifier
 */
@Component
@RequestMapping("duplicate")
public class DuplicateBeanController {

	@Autowired @Qualifier(value="DuplicateBean1")
	private DuplicateBean duplicate1;
	@Autowired @Qualifier(value="DuplicateBean2")
	private com.learn.data.bean2.DuplicateBean duplicate2;
	
	@RequestMapping("1")
	public ResponseEntity<DuplicateBean> getFirstBean() {
		
		return new ResponseEntity<DuplicateBean>(duplicate1, HttpStatus.OK);
	}
	
	@RequestMapping("2")
	public ResponseEntity<com.learn.data.bean2.DuplicateBean> getSecondBean() {
		
		return new ResponseEntity<com.learn.data.bean2.DuplicateBean>(duplicate2, HttpStatus.OK);
	}
}

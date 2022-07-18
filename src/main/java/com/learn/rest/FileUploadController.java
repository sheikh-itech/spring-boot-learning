package com.learn.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/file")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
			
	@RequestMapping(value = "/upload", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addProduct(@RequestParam("file") MultipartFile file) {
		
		File newFile = new File("files"+File.separator+file.getOriginalFilename());
		try {
			newFile.createNewFile();
			FileOutputStream out = new FileOutputStream(newFile);
			out.write(file.getBytes());
			out.close();
			
		} catch (IOException e) {
			logger.info("Not able to read file");
			return "Not able to read file";
		}
		logger.info("File is upload successfully");
		return "File is upload successfully";
	}
}

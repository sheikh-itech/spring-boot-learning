package com.learn.excp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *	This class is meant for handling request url not found errors 
 */

@RestController
public class PathErrorController implements ErrorController {

	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Path does not exist";
    }

    public String getErrorPath() {
        return PATH;
    }
}

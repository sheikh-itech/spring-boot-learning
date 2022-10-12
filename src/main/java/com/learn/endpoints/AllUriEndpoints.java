package com.learn.endpoints;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class AllUriEndpoints {

	private static final Logger LOGGER = LoggerFactory.getLogger(AllUriEndpoints.class);

	/**
Field requestHandlerMapping in com.learn.endpoints.AllUriEndpoints required a single bean, but 3 were found:
	- requestMappingHandlerMapping: defined by method 'requestMappingHandlerMapping' in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]
	- adminHandlerMapping: defined by method 'adminHandlerMapping' in class path resource [de/codecentric/boot/admin/server/config/AdminServerWebConfiguration$ServletRestApiConfirguation.class]
	- controllerEndpointHandlerMapping: defined by method 'controllerEndpointHandlerMapping' in class path resource [org/springframework/boot/actuate/autoconfigure/endpoint/web/servlet/WebMvcEndpointManagementContextConfiguration.class]

	 */
	@Autowired
	@Qualifier(value="requestMappingHandlerMapping")
	@Resource(name = "")
	private RequestMappingHandlerMapping requestHandlerMapping;
	
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
    	/*
    	ApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.getBean(RequestMappingHandlerMapping.class)
            .getHandlerMethods().forEach((key, value) -> LOGGER.info("{} {}", key, value));
            */
        requestHandlerMapping.getHandlerMethods().forEach((key, value) -> {
        	LOGGER.info("{} {}", key, value);
        });
    }
}

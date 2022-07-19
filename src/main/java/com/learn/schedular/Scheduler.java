package com.learn.schedular;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**	Scheduling is a process of executing the tasks for the specific time period
 * 	
 * 	Java Cron expressions are used to configure the instances of CronTrigger,
 * 	A subclass of org.quartz.Trigger
 *
 */
@Component
public class Scheduler {

	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	/** Time based scheduler */
	@Scheduled(cron = "0 * 17 * * ?")
	public void cronJobScheduler() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      logger.info("Schedular Time: "+strDate);
	}
	
	/** Fixed rate scheduler same as setInterval, time in mili */
	/*@Scheduled(fixedRate = 1000)
	public void fixedRateSch() {
		
	}*/
	
	/** Fixed rate scheduler with initial delay, time in mili */
	/*@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void fixedDelaySch() {
		
	}*/
}

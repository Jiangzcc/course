package com.jiangzhichao.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 
 * @author BornToWin
 *
 */
@Component
public class TimedTask {

	@Scheduled(cron="0/10 * *  * * ? ")  
    public void testTask(){
		// System.out.println(System.currentTimeMillis());
    } 
}

package com.jiangzhichao.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimedTask {

	@Scheduled(cron="0/10 * *  * * ? ")   //每10秒执行一次      
    public void testTask(){
		System.out.println(System.currentTimeMillis());
    } 
}

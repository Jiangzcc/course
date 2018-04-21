package com.jiangzhichao.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimedTask {

	@Scheduled(cron="0/10 * *  * * ? ")   //ÿ10��ִ��һ��      
    public void testTask(){
		System.out.println(System.currentTimeMillis());
    } 
}

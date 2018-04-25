package com.jiangzhichao.admin.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiangzhichao.dao.StatusMapper;
import com.jiangzhichao.entity.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestStatus {
	
	@Autowired
	private StatusMapper statusMapper;
	
	@Test
	public void test1() {
		Status status = statusMapper.select();
		System.out.println(status);
	}

}

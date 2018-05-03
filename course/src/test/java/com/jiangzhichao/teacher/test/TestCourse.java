package com.jiangzhichao.teacher.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.entity.CourseVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCourse {

	@Autowired
	private CourseMapper courseMapper;
	
	@Test
	public void test1() {
		List<CourseVO> list = courseMapper.selectByTnoAndTerm("100000", "201803");
		for (CourseVO courseVO : list) {
			System.out.println(courseVO);
		}
	}
}

package com.jiangzhichao.student.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.entity.CourseVO;
import com.jiangzhichao.entity.StudentDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestStudent {

	@Autowired
	private CourseMapper courseMapper;

	@Test
	public void test1() {
		List<CourseVO> list = courseMapper.selectByDnoAndTerm("1234", "201803");
		for (CourseVO courseVO : list) {
			System.out.println(courseVO);
		}
	}
	
	@Test
	public void test2() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setDno("1234");
		studentDTO.setSno("1234");
		studentDTO.setSterm("201803");
		List<CourseVO> list = courseMapper.selectBySno(studentDTO);
		for (CourseVO courseVO : list) {
			System.out.println(courseVO);
		}
	}
	
	@Test
	public void test3() {
		List<CourseVO> list = courseMapper.selectedBySno("1234");
		for (CourseVO courseVO : list) {
			System.out.println(courseVO);
		}
	}
	
	@Test
	public void test4() {
		List<CourseVO> list = courseMapper.selectedOldBySno("1234");
		for (CourseVO courseVO : list) {
			System.out.println(courseVO);
		}
	}
}

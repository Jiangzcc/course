package com.jiangzhichao.dao;

import java.util.List;

import com.jiangzhichao.entity.StuCourseDTO;

public interface StuCourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stucourse
     *
     * @mbg.generated Wed Apr 18 20:00:37 CST 2018
     */
    int insert(StuCourseDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stucourse
     *
     * @mbg.generated Wed Apr 18 20:00:37 CST 2018
     */
    int insertSelective(StuCourseDTO record);
    
    /**
     * 查询某学生所有课程
     * @param sno
     * @return
     */
    List<StuCourseDTO> selectBySno(String sno);
    
    /**
     * 查询某课程下所有学生
     * @param cno
     * @return
     */
    List<StuCourseDTO> selectByCno(String cno);
    
    /**
     * 删除某学生所有课程
     * @param sno
     * @return
     */
    int deleteBySno(String sno);
    
    /**
     * 修改某个学生成绩
     * @param record
     * @return
     */
    int updateScore(StuCourseDTO record);
    
    /**
     * 退课
     * @param record
     * @return
     */
    int deleteBySnoAndCno(StuCourseDTO record);
}
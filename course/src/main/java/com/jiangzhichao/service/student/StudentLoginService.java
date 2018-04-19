package com.jiangzhichao.service.student;

import com.jiangzhichao.entity.StudentDO;

public interface StudentLoginService {

	StudentDO queryStudent(String sno,String spassword);
}

package com.jiangzhichao.dao;

import com.jiangzhichao.entity.AdminDO;

public interface AdminLoginMapper {

	AdminDO selectByUsername(String ausername);
	
}

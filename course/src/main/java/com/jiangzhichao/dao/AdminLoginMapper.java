package com.jiangzhichao.dao;

import com.jiangzhichao.entity.AdminDO;

/**
 * 管理员登陆Mapper
 * 
 * @author BornToWin
 *
 */
public interface AdminLoginMapper {

	/**
	 * 通过username查询管理员信息
	 * @param ausername
	 * @return
	 */
	AdminDO selectByUsername(String ausername);
	
}

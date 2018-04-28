package com.jiangzhichao.dao;

import com.jiangzhichao.entity.AdminDTO;

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
	AdminDTO selectByUsername(String ausername);
	
}

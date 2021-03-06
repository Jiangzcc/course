package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.AdminDTO;

/**
 * 管理员登陆Service
 * 
 * @author BornToWin
 *
 */
public interface AdminLoginService {
	
	/**
	 * 通过用户名和密码查询管理员信息
	 * @param username
	 * @param password
	 * @return
	 */
	AdminDTO queryAdmin(String username,String password);

}

package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.AdminDO;

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
	AdminDO queryAdmin(String username,String password);

}

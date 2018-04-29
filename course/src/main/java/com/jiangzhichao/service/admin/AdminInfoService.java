package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.AdminDTO;

/**
 * 管理员信息管理Service
 * 
 * @author BornToWin
 *
 */
public interface AdminInfoService {

	/**
	 * 修改密码
	 * @param admin
	 * @return
	 */
	int updatePassword(AdminDTO admin);
	
}

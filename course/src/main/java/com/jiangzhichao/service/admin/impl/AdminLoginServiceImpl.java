package com.jiangzhichao.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.AdminLoginMapper;
import com.jiangzhichao.entity.AdminDTO;
import com.jiangzhichao.service.admin.AdminLoginService;

/**
 * 管理员登陆Service
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	@Override
	public AdminDTO queryAdmin(String ausername, String apassword) {
		AdminDTO adminDTO = adminLoginMapper.selectByUsername(ausername);
		//管理员用户名存在
		if(null != adminDTO) {
			//密码正确
			if(adminDTO.getApassword().equals(apassword)) {
				return adminDTO;
			}
		}
		return null;
	}

}

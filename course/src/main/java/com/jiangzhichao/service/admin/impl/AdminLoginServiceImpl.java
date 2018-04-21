package com.jiangzhichao.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.AdminLoginMapper;
import com.jiangzhichao.entity.AdminDO;
import com.jiangzhichao.service.admin.AdminLoginService;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	@Override
	public AdminDO queryAdmin(String ausername, String apassword) {
		AdminDO adminDO = adminLoginMapper.selectByUsername(ausername);
		//管理员用户名存在
		if(null != adminDO) {
			//密码正确
			if(adminDO.getApassword().equals(apassword)) {
				return adminDO;
			}
		}
		return null;
	}

}

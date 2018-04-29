package com.jiangzhichao.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.AdminMapper;
import com.jiangzhichao.entity.AdminDTO;
import com.jiangzhichao.service.admin.AdminInfoService;

@Service
@Transactional
public class AdminInfoServiceImpl implements AdminInfoService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public int updatePassword(AdminDTO admin) {
		return adminMapper.updateByPrimaryKeySelective(admin);
	}

}

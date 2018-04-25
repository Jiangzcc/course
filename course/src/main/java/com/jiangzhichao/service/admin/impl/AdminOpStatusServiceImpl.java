package com.jiangzhichao.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.StatusMapper;
import com.jiangzhichao.entity.Status;
import com.jiangzhichao.service.admin.AdminOpStatusService;

@Service
@Transactional
public class AdminOpStatusServiceImpl implements AdminOpStatusService {
	
	@Autowired
	private StatusMapper statusMapper;

	@Override
	public Status select() {
		return statusMapper.select();
	}

	@Override
	public int update(Status status) {
		return statusMapper.update(status);
	}

}

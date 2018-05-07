package com.jiangzhichao.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.StatusMapper;
import com.jiangzhichao.entity.Status;
import com.jiangzhichao.service.admin.AdminOpStatusService;

/**
 * 选课开启关闭-录入成绩开启关闭状态查询、修改Service
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
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

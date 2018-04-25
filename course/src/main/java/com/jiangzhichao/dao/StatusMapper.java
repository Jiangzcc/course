package com.jiangzhichao.dao;

import com.jiangzhichao.entity.Status;

/**
 * 选课开启关闭-录入成绩开启关闭状态查询、修改
 * 
 * @author BornToWin
 *
 */
public interface StatusMapper {

	/**
	 * 查询状态
	 * @return
	 */
	Status select();
	
	/**
	 * 修改状态
	 * @return
	 */
	int update(Status status);
}

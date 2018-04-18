package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.AdminDO;

public interface AdminLoginService {
	
	AdminDO queryAdmin(String username,String password);

}

package com.jiangzhichao.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.Status;
import com.jiangzhichao.service.admin.AdminOpStatusService;

/**
 * 管理员管理状态相关Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpStatusController extends BaseController {

	@Autowired
	private AdminOpStatusService adminOpStatusService;
	
	@RequestMapping("/queryStatus")
	@ResponseBody
	public Status queryStatus(){
		return adminOpStatusService.select();
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public Map<String,Object> updateStatus(Status status){
		int i = adminOpStatusService.update(status);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
}

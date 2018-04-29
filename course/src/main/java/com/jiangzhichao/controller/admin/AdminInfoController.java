package com.jiangzhichao.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.AdminDTO;
import com.jiangzhichao.service.admin.AdminInfoService;

/**
 * 管理员信息Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminInfoController extends BaseController {
	
	@Autowired
	private AdminInfoService adminInfoService;

	@RequestMapping("/confirmPassword")
	@ResponseBody
	public Map<String,Object> confirmPassword(HttpSession session,String password) {
		AdminDTO admin = (AdminDTO) session.getAttribute("admin");
		Map<String,Object> map = new HashMap<>();
		if(admin.getApassword().equals(password)) {
			map.put("result", true);
			return map;
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public Map<String,Object> changePassword(String oldPassword,String newPassword,HttpSession session) {
		AdminDTO admin = (AdminDTO) session.getAttribute("admin");
		Map<String,Object> map = new HashMap<>();
		//防止用户直接通过url访问、跳过输入旧密码验证，再次验证一遍
		if(admin.getApassword().equals(oldPassword)) {
			admin.setApassword(newPassword);
			int i = adminInfoService.updatePassword(admin);
			if(i!=0) {
				map.put("result", true);
				return map;
			}
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/adminInfo")
	@ResponseBody
	public AdminDTO adminInfo(HttpSession session) {
		return (AdminDTO) session.getAttribute("admin");
	}
}

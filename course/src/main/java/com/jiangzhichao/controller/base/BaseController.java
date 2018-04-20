package com.jiangzhichao.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {
	
	/*
	 * ȫ���쳣����
	 */
	@ExceptionHandler
	@ResponseBody
	public Map<String,Object> exp(HttpServletRequest request, Exception ex) {
		Map<String,Object> map = new HashMap<>();
		ex.printStackTrace();
		map.put("result", false);
		return map;
	}
	
}
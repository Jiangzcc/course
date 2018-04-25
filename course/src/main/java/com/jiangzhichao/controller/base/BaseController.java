package com.jiangzhichao.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基类Controller，用于系统异常时返回错误信息
 * 
 * @author BornToWin
 *
 */
@Controller
public class BaseController {
	
	/*
	 * 全局异常处理
	 */
	@ExceptionHandler
	@ResponseBody
	public Map<String,Object> exp(HttpServletRequest request, Exception ex) {
		Map<String,Object> map = new HashMap<>();
		//要不要打印呢？
		ex.printStackTrace();
		map.put("result", false);
		return map;
	}
	
}
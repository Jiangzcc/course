package com.jiangzhichao.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ����Controller������ϵͳ�쳣ʱ���ش�����Ϣ
 * 
 * @author BornToWin
 *
 */
@Controller
public class BaseController {
	
	/*
	 * ȫ���쳣����
	 */
	@ExceptionHandler
	@ResponseBody
	public Map<String,Object> exp(HttpServletRequest request, Exception ex) {
		Map<String,Object> map = new HashMap<>();
		//Ҫ��Ҫ��ӡ�أ�
		ex.printStackTrace();
		map.put("result", false);
		return map;
	}
	
}
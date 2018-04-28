package com.jiangzhichao.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.DepartmentDTO;
import com.jiangzhichao.service.admin.AdminOpDepartmentService;

/**
 * 管理员操作专业信息Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminOpDepartmentController extends BaseController{

	@Autowired
	private AdminOpDepartmentService adminOpDepartmentService;
	
	@RequestMapping("queryAllDepartment")
	@ResponseBody
	public Map<String,List<DepartmentDTO>> queryAllDepartment(){
		List<DepartmentDTO> list = adminOpDepartmentService.selectAllDepartment();
		Map<String,List<DepartmentDTO>> map = new HashMap<String,List<DepartmentDTO>>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("deleteDepartment")
	@ResponseBody
	public Map<String,Object> deleteDepartment(String dno){
		int i = adminOpDepartmentService.deleteDepartment(dno);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("queryDepartment")
	@ResponseBody
	public DepartmentDTO queryDepartment(String dno){
		return adminOpDepartmentService.selectDepartmentByDno(dno);
	}
	
	@RequestMapping("editDepartment")
	@ResponseBody
	public Map<String,Object> editDepartment(DepartmentDTO departmentDTO){
		int i = adminOpDepartmentService.updateDepartment(departmentDTO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("addDepartment")
	@ResponseBody
	public Map<String,Object> addDepartment(DepartmentDTO departmentDTO) {
		int i = adminOpDepartmentService.insertDepartment(departmentDTO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	
}

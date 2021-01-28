package com.lun.c03.mapper.dao;

import com.lun.c01.helloworld.bean.Department;

public interface DepartmentMapper {
	
	public Department getDeptByIdStep(Integer id);
	
	public Department getDeptByIdPlus(Integer id);
	
	public Department getDeptById(Integer id);
	
	
	
}

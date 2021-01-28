package com.lun.c06.spring;

import java.util.List;

import com.lun.c01.helloworld.bean.Employee;

public interface EmployeeMapper {

	public Employee getEmpById(Integer id);

	public List<Employee> getEmps();
	
}

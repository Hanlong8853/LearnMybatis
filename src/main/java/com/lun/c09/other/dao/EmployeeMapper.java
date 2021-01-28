package com.lun.c09.other.dao;

import java.util.List;

import com.lun.c01.helloworld.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getEmps();
	
	public void addEmp(Employee em);
	
	public void addEmp2(com.lun.c09.other.bean.Employee em);

	public com.lun.c09.other.bean.Employee getEmpById2(Integer id);
	
}

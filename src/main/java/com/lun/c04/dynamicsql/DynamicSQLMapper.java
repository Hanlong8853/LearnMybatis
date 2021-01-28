package com.lun.c04.dynamicsql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lun.c01.helloworld.bean.Employee;

public interface DynamicSQLMapper {
	
	public void addEmps(@Param("emps")List<Employee> emps);
	
	public void addEmps2(@Param("emps")List<Employee> emps);
	
	public List<Employee> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);
	
	public void updateEmp(Employee employee);
	
	public List<Employee> getEmpsByConditionChoose(Employee employee);
	
	public List<Employee> getEmpsByConditionTrim(Employee employee);
	
	public List<Employee> getEmpsByConditionIfWithWhere(Employee employee);
	//携带了哪个字段查询条件就带上这个字段的值
	public List<Employee> getEmpsByConditionIf(Employee employee);
	
}

package com.lun.c03.mapper.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.lun.c01.helloworld.bean.Employee;


public interface EmployeeMapper {

	//带有鉴别器的
	public List<Employee> getEmpsWithDiscriminator();
	
	//用于department的分布查询
	public Employee getEmpByDeptId(Integer id);
	
	//association分步查询
	public Employee getEmpByIdStep(Integer id);
	
	//联合查询：级联属性封装结果集
	public Employee getEmpAndDept2(Integer id);
	public Employee getEmpAndDept(Integer id);
	
	//自定义结果映射规则
	public Employee getEmpByIdWithResultMap(Integer id);
	
//// return Map
	//多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
	//@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
	@MapKey("lastName")
	public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);
	
	//返回一条记录的map；key就是列名，值就是对应的值
	public Map<String, Object> getEmpByIdReturnMap(Integer id);
	
	
//// return Collection
	public List<Employee> getEmpsByLastNameLike(String str);
	
	
//// Parameters settings	
	
	public Employee getEmpByMap(Map<String, Object> map);
		
	public Employee getEmpByIdAndLastName(Integer id, String name);
	public Employee getEmpByIdAndLastName2(Integer id, String name);
	public Employee getEmpByIdAndLastName3(Integer id, String name);
	public Employee getEmpByIdAndLastName4(@Param("id")Integer id,// 
										@Param("lastName")String name);
	
	
	public Employee getEmpById2(Integer id);

////////////////////////////////////////////////	
////// Basic CRUD, Basic CRUD, Basic CRUD //////
////////////////////////////////////////////////

	public Employee getEmpById(Integer id);

	public Long addEmp(Employee employee);

	public boolean updateEmp(Employee employee);

	public void deleteEmpById(Integer id);
	
}

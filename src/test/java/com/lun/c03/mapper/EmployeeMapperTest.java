package com.lun.c03.mapper;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c01.helloworld.bean.Employee;
import com.lun.c03.mapper.dao.EmployeeMapper;
import com.lun.util.Tools;

public class EmployeeMapperTest {

	@Test
	public void testCrud() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
			Employee newEmployee = new Employee(null,"kuang","kuang@163.com","1");
			
			//增
			Long count = mapper.addEmp(newEmployee);
			System.out.println(count);
			
			//查
			System.out.println("After creating : " + mapper.getEmpById(newEmployee.getId()));
			
			//改
			newEmployee.setGender("0");
			mapper.updateEmp(newEmployee);
			
			//查
			System.out.println("After updating : " + mapper.getEmpById(newEmployee.getId()));
			
			//删
			mapper.deleteEmpById(newEmployee.getId());
			System.out.println("After deleting : " + mapper.getEmpById(newEmployee.getId()));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testParameters() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
			//1. 
			//单个参数：mybatis不会做特殊处理
			System.out.println(mapper.getEmpById2(1));
			
			//2.
			//多个参数，未作处理 ，mapper直用#{id},#{lastName}会抛异常 
			try {				
				System.out.println(mapper.getEmpByIdAndLastName(1, "jallen"));
				//org.apache.ibatis.exceptions.PersistenceException: 
				//### Error querying database.  Cause: org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [0, 1, param1, param2]
				//### Cause: org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [0, 1, param1, param2]
			}catch(PersistenceException ex) {
				System.err.println(ex);
			}
			
			//多个参数会被封装成 一个map
			//key：param1...paramN,或者参数的索引0, 1..也可以(这种方法的可读性较差)
			//value：传入的参数值
			System.out.println("1. " + mapper.getEmpByIdAndLastName2(1, "jallen"));
			System.out.println("2. " + mapper.getEmpByIdAndLastName3(1, "jallen"));
			
			//3. 
			//【命名参数】：明确指定封装参数时map的key；@Param("id")
			System.out.println("3. " + mapper.getEmpByIdAndLastName4(1, "jallen"));
			
			//4.
			//传入map
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("lastName", "jallen");
			System.out.println("4. " + mapper.getEmpByMap(map));
			
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testList() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			List<Employee> result = em.getEmpsByLastNameLike("%a%");
			System.out.println(result);

			session.commit();
		} finally {
			session.close();
		}
	}
	
	
	@Test
	public void testMap() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			Map<String, Object> result = em.getEmpByIdReturnMap(1);
			System.out.println(result);
			
			System.out.println("---");
			Map<String, Employee> result2 = em.getEmpByLastNameLikeReturnMap("%a%");
			System.out.println(result2);
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMap() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			System.out.println(em.getEmpByIdWithResultMap(1));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapAssociation() throws IOException {
		
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			System.out.println(em.getEmpAndDept(1));
			System.out.println(em.getEmpAndDept(1).getDepartment());
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapAssociation2() throws IOException {
		
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			System.out.println(em.getEmpAndDept2(1));
			System.out.println(em.getEmpAndDept2(1).getDepartment());
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testResultMapAssociation3() throws IOException {
		
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			System.out.println(em.getEmpByIdStep(1));
			System.out.println(em.getEmpByIdStep(1).getDepartment());
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsWithDiscriminator() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			System.out.println(em.getEmpsWithDiscriminator());
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
}

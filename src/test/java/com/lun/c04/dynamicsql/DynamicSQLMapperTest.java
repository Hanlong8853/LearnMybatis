package com.lun.c04.dynamicsql;


import java.io.IOException;
import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c01.helloworld.bean.Employee;
import com.lun.util.Tools;

public class DynamicSQLMapperTest {
	
	@Test
	public void testAddEmps2() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			
			dsm.addEmps2(Arrays.asList(new Employee(null, "abc", null, null),
					new Employee(null, "cba",null, null)));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testAddEmps() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			dsm.addEmps(Arrays.asList(new Employee(null, "abc", null, null),
					new Employee(null, "cba",null, null)));
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionForeach() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			
			System.out.println(dsm.getEmpsByConditionForeach(Arrays.asList(1,2,3)));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testUpdateEmp() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			
			Employee employee = new Employee(1, "jallen2", null, null);
			dsm.updateEmp(employee);
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionChoose() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			Employee employee = new Employee(1, "jallen", null, null);
			System.out.println(dsm.getEmpsByConditionChoose(employee));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionTrim() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			Employee employee = new Employee(1, "jallen", null, null);
			System.out.println(dsm.getEmpsByConditionTrim(employee));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionIfWithWhere() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			Employee employee = new Employee(null, "jallen", null, null);
			System.out.println(dsm.getEmpsByConditionIfWithWhere(employee));
			
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionIf() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c04/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DynamicSQLMapper dsm = session.getMapper(DynamicSQLMapper.class);
			Employee employee = new Employee(1, "jallen", null, null);
			System.out.println(dsm.getEmpsByConditionIf(employee));
			
			session.commit();
		} finally {
			session.close();
		}
	}

}

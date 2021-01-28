package com.lun.c05.cache;


import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c01.helloworld.bean.Department;
import com.lun.c01.helloworld.bean.Employee;
import com.lun.c03.mapper.dao.DepartmentMapper;
import com.lun.c03.mapper.dao.EmployeeMapper;
import com.lun.util.Tools;

public class CacheTest {

	@Test
	public void testEhcache() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c05/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		SqlSession session2 = ssf.openSession();
		
		try {
			DepartmentMapper dm = session.getMapper(DepartmentMapper.class);
			Department dp = dm.getDeptById(1);
			System.out.println(dp);
			session.close();
			
			DepartmentMapper dm2 = session2.getMapper(DepartmentMapper.class);
			Department dp2 = dm2.getDeptById(1);
			System.out.println(dp2);
			
		} finally {
			session2.close();
		}
	}
	
	
	@Test
	public void testSecondCache() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c05/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		SqlSession session2 = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			Employee e1 = em.getEmpById(1);
			System.out.println(e1);
			session.close();
			
			EmployeeMapper em2 = session2.getMapper(EmployeeMapper.class);
			Employee e2 = em2.getEmpById(1);
			System.out.println(e2);
			
			System.out.println("e1 == e2 : " + (e1 == e2));
			
		} finally {
			session2.close();
		}
	}
	
	@Test
	public void testFirstCache() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			EmployeeMapper em = session.getMapper(EmployeeMapper.class);
			
			Employee e1 = em.getEmpById(1);
			System.out.println(e1);
			
			Employee e2 = em.getEmpById(1);
			System.out.println(e2);
			
			System.out.println("e1 == e2 : " + (e1 == e2));
			
			session.commit();
		} finally {
			session.close();
		}
	}

}

package com.lun.c03.mapper;


import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c01.helloworld.bean.Department;
import com.lun.c03.mapper.dao.DepartmentMapper;
import com.lun.util.Tools;

public class DepartmentMapperTest {

	@Test
	public void testGetDeptByIdStep() throws IOException {
		
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DepartmentMapper dm = session.getMapper(DepartmentMapper.class);
			Department department = dm.getDeptByIdStep(1);
			
			System.out.println(department);
			System.out.println(department.getEmps());
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetDeptByIdPlus() throws IOException {
		SqlSessionFactory ssf = Tools.getSqlSessionFactory("c03/mybatis-config.xml");
		SqlSession session = ssf.openSession();
		
		try {
			DepartmentMapper dm = session.getMapper(DepartmentMapper.class);
			
			Department department = dm.getDeptByIdPlus(1);
			
			System.out.println(department);
			System.out.println(department.getEmps());
			
			session.commit();
		} finally {
			session.close();
		}
	}

}

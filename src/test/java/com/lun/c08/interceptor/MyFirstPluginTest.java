package com.lun.c08.interceptor;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c01.helloworld.bean.Employee;
import com.lun.c01.helloworld.dao.EmployeeMapper;
import com.lun.util.Tools;

public class MyFirstPluginTest {

	@Test
	public void test() throws IOException {
		
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = Tools.getSqlSessionFactory("c08/mybatis-config.xml");
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(1);
			
			//System.out.println(mapper.getClass());
			System.out.println(employee);
		} finally {
			openSession.close();
		}
		
	}

}

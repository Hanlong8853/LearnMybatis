package com.lun.c09.other;


import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.lun.c09.other.bean.EmpStatus;
import com.lun.c09.other.bean.Employee;
import com.lun.c09.other.dao.EmployeeMapper;
import com.lun.util.Tools;

public class EnumTest {

	@Test
	public void testEnumUse(){
		EmpStatus login = EmpStatus.LOGIN;
		System.out.println("枚举的索引：" + login.ordinal());
		System.out.println("枚举的名字：" + login.name());
		
		System.out.println("枚举的状态码：" + login.getCode());
		System.out.println("枚举的提示消息：" + login.getMsg());
	}

	
	/**
	 * 默认mybatis在处理枚举对象的时候保存的是枚举的名字：EnumTypeHandler
	 * 改变使用：EnumOrdinalTypeHandler：
	 * @throws IOException
	 */
	@Test
	public void testEnum() throws IOException{
		SqlSessionFactory sqlSessionFactory = Tools.getSqlSessionFactory("c09/mybatis-config.xml");
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee("test_enum", "enum@abc.com","1");
			mapper.addEmp2(employee);
			//System.out.println("保存成功"+employee.getId());
			openSession.commit();
			Employee empById = mapper.getEmpById2(employee.getId());
			System.out.println(empById.getEmpStatus());
		}finally{
			openSession.close();
		}
	}

}

/*
result:
枚举的索引：0
枚举的名字：LOGIN
*/

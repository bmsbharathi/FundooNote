package com.bridgeit.note.mybatisutility;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * An Utility class for MyBatis it creates an SqlSession and returns it
 */

public class MyBatisUtil {

	private static SqlSessionFactory factory;

	private MyBatisUtil() {

	}

	// Reading the Mybatis configurations set in XML
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	// returns  SqlsessionFactory to create a Sql session
	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}

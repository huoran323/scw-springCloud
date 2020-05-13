package com.hr.scw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test01() throws SQLException {
		
		Connection conn = dataSource.getConnection();
		
		//com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@1d91fa02
		System.out.println(conn); //代理对象
		
		conn.close(); //不是销毁对象，而是归还到连接池。
	}
	
	@Test
	public void test02() {
		stringRedisTemplate.opsForValue().set("key1", "value1");
	}

	@Test
	public void test03() {
		String string = stringRedisTemplate.opsForValue().get("key1");
		System.out.println(string);
	}
}

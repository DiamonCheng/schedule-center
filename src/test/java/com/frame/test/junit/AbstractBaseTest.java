package com.frame.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/config/spring.xml", 
		"file:src/main/resources/config/spring-mvc.xml",
		"file:src/main/resources/config/spring-hibernate.xml" })
public abstract class AbstractBaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected final static boolean isrollback = true;
	static {
		System.out.println("准备测试初始化.如果没有下一步日志打印说明spring环境初始化失败。");
		System.out.println("Use rollback : "+isrollback);
	}
	public abstract void dotest() throws Exception;

	@Test
	@Rollback(isrollback)
	public void test() throws Exception {
		try {
			System.out.println("测试开始.");
			dotest();
			System.out.println("测试结束.");
		} catch (Exception e) {
			System.out.println("出现异常！测试失败");
			e.printStackTrace();
			throw e;
		}
	}

}

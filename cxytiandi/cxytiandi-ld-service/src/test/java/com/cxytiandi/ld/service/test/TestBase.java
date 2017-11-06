package com.cxytiandi.ld.service.test;

import org.cxytiandi.conf.client.init.SmconfInit;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cxytiandi.ld.service.LdApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LdApplication.class)
public class TestBase {
	
	@BeforeClass
	public static void before() {
		SmconfInit.init("com.cxytiandi.ld.service.config");
	}
	
}

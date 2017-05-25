package com.springboot;

import com.springboot.web.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}

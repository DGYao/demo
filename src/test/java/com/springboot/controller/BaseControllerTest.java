package com.springboot.controller;

import com.springboot.web.DemoApplication;
import com.springboot.web.model.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)
public class BaseControllerTest {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    //这个方法在每个方法执行之前都会执行一遍
    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        assertNotNull(mockMvc);
    }

    @Test
    public void acitvePageTest() throws Exception{
        City city = new City();
        city.setPage(1);
        city.setRows(10);
        mockMvc.perform(
                get("/city/list")
        )
                .andDo(print())
                .andExpect(status().isOk());

    }
}

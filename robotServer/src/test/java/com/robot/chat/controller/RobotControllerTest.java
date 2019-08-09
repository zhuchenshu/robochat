package com.robot.chat.controller;

import com.alibaba.fastjson.JSON;
import com.robot.chat.ChatApplicationTests;
import com.robot.chat.dto.Query;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RobotControllerTest extends ChatApplicationTests {
    final static Logger logger = LoggerFactory.getLogger(RobotControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    public void chat() throws Exception{
        String baseUrl = "/chat";
        Query query = new Query();
        query.setQuery("我要听刘德华的笨小孩");

        logger.info("测试闲聊接口");
        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(query))).andExpect(status().isOk());
    }
}
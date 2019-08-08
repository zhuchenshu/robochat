package com.robot.chat.controller;

import com.robot.chat.ChatApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        logger.info("测试闲聊接口");
        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"query\":\"我要听刘德华的笨小孩\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}
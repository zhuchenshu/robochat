package com.robot.chat.service;

import com.robot.chat.ChatApplicationTests;
import com.robot.chat.dto.ChatResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RobotServiceImplTest extends ChatApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(RobotServiceImplTest.class);

    @Autowired private RobotService robotService;
    @Test
    public void getResponse() {
        logger.info("测试输入歌手歌曲信息时返回歌曲信息");
        String queryMusic = "我想听五月天的倔强";
        ChatResponse back = robotService.getResponse(queryMusic);
        // Assertions.assertThat().isEqualTo();

        logger.info("测试输入闲聊接口时输出闲聊类型回复");
        String queryChat = "你好呀";
    }
}
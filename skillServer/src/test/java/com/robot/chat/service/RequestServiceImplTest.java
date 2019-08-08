package com.robot.chat.service;

import com.robot.chat.ChatApplicationTests;
import com.robot.chat.dto.Music;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestServiceImplTest {

    final static Logger logger = LoggerFactory.getLogger(ChatApplicationTests.class);

    @Autowired
    RequestService requestService;

    @Test
    public void getResponse() {
        logger.info("测试输入关键字的搜索结果");
        String[] info = new String[]{
                "出山",
                "花粥"
        } ;

        Music musicBack = requestService.getResponse(info[0],info[1]);

        Assertions.assertThat(musicBack.getCode()).isEqualTo(200);
        Assertions.assertThat(musicBack.getPlayUrl()).isNotNull();
    }
}
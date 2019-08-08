package com.robot.chat.controller;

import com.alibaba.fastjson.JSON;
import com.robot.chat.ChatApplicationTests;
import com.robot.chat.dto.Music;
import com.robot.chat.dto.MusicRequest;
import com.robot.chat.dto.Nlu;
import com.robot.chat.dto.Slot;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RequestControllerTest extends ChatApplicationTests {

    final static Logger logger = LoggerFactory.getLogger(ChatApplicationTests.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRequest() throws Exception{
        //构建发送的对象
        MusicRequest musicRequest = new MusicRequest();
        Nlu nlu = new Nlu();
        List<Slot> slotList = new ArrayList<>();
        Slot firstSlot = new Slot();
        Slot secondSlot = new Slot();

        firstSlot.setName("Person");
        firstSlot.setValue("花粥");
        slotList.add(firstSlot);

        secondSlot.setName("MusicName");
        secondSlot.setValue("出山");
        slotList.add(secondSlot);

        nlu.setSlots(slotList);
        nlu.setIntent("Music.search");
        nlu.setDomain("Music");

        musicRequest.setNlu(nlu);
        musicRequest.setQuery("我想听花粥的出山");


        logger.info("测试getRequest");
        mockMvc.perform(post("/music")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(musicRequest)))
                .andExpect(status().isOk());
    }
}
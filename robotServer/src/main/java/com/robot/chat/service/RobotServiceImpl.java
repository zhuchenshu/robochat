package com.robot.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    RestTemplate restTemplate;
    @Override
    public void getResponse(String query) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.tianapi.com/txapi/robot/?key=9d45fb6c42449577890a9606f1cb2168&question=你好\n", String.class);
        System.out.println(responseEntity);
    }
}

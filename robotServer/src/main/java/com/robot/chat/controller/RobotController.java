package com.robot.chat.controller;

import com.robot.chat.dto.ChatResponse;
import com.robot.chat.dto.Query;
import com.robot.chat.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 机器人服务api
 * @author chenshu.zhu
 * @version 2019/8/6
 */
@RequestMapping("chat")
@RestController
public class RobotController {
    @Autowired
    private RobotService robotService;

    @RequestMapping()
    public String test() {
        return "hello world";
    }

    /**
     * 请求机器人聊天接口
     * @param query 聊天内容
     * @return 返回信息
     */
    @PostMapping()
    public ChatResponse chat(@RequestBody Query query) {
        return robotService.getResponse(query.getQuery());
    }
}


package com.robot.chat.controller;

import com.robot.chat.dto.ChetResponse;
import com.robot.chat.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 机器人服务api
 * @author chenshu.zhu
 * @version 2019/8/6
 */
@RequestMapping("test")
@RestController
public class RobotController {
    @Autowired
    private RobotService robotService;

    @RequestMapping()
    public String test() {
        return "hello world";
    }

    @PostMapping()
    public ChetResponse chat(@RequestParam String query) {
        System.out.println(query);
        return robotService.getResponse(query);
    }
}

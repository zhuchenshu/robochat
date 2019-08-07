package com.robot.chat.service;

import com.robot.chat.dto.ChatResponse;

/**
 * @Description 机器人服务接口
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public interface RobotService {
    ChatResponse getResponse(String query);
}

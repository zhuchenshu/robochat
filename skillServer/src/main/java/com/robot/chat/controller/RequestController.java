package com.robot.chat.controller;

import com.robot.chat.dto.MusicRequest;
import com.robot.chat.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Description json解析
 * @author chongting.li
 * @version 2019/8/6
 *
 */
@RestController
public class RequestController {
    @Autowired
    RequestServiceImpl requestService;

    @PostMapping
    public MusicRequest getRequest(@RequestBody MusicRequest musicRequest){
        String[] inputMsg = new String[2];
        inputMsg = musicRequest.getSlot();
        if(inputMsg != null){
            requestService.getResponse(inputMsg[0],inputMsg[1]);
        }
        return musicRequest;
    }

}

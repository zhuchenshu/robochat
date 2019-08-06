package com.robot.chat.controller;

import com.robot.chat.dto.MusicRequest;
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
    @RequestMapping(value = "/request-skill",method = RequestMethod.POST)
    public MusicRequest getRequest(@RequestBody MusicRequest musicRequest){
        return musicRequest;
    }
}

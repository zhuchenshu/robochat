package com.robot.chat.controller;

import com.robot.chat.dto.Music;
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
@RequestMapping("music")
@RestController
public class RequestController {
    @Autowired
    RequestServiceImpl requestService;

    @PostMapping
    public Music getRequest(@RequestBody MusicRequest musicRequest){
        /**
         * 返回音乐类
         */
        Music music = new Music();
        /**
         * 获取歌手名和歌名
         */
        String[] inputMsg = new String[2];
        
        inputMsg = musicRequest.getSlot();
        music = requestService.getResponse(inputMsg[0],inputMsg[1]);

        return music;
    }

}

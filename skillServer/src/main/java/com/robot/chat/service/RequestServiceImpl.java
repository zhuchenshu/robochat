package com.robot.chat.service;

import com.robot.chat.dto.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * @Description 根据歌手名和歌名调用第三方接口搜索歌曲
     * @param singer 歌手名
     * @param songName 歌名
     */
    @Override
    public Music getResponse(String singer,String songName){
        /**
         * 音乐搜索接口
         */
        String askMusicUrl = "https://v1.itooi.cn/netease/search?keyword={1} {2}5&type=song&pageSize=1&page=0";


        /**
         * 音乐实体类
         */
        Music music = new Music();

        //调用第三方接口
        ResponseEntity<Music> responseEntity = restTemplate.getForEntity(askMusicUrl, Music.class,singer,songName);
        System.out.println(responseEntity);

        //处理返回json
        if(responseEntity.getStatusCodeValue() == 200){
            music = responseEntity.getBody();
            //请求播放地址接口需要音乐id
            music.setPlayUrl(music.getData().getSongs().get(0).getId());
            System.out.println(music);
        }
        return music;
    }
}
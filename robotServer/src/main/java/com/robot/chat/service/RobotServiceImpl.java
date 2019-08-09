package com.robot.chat.service;

import com.robot.chat.datainit.MusicSkillDataInit;
import com.robot.chat.dto.*;
import com.robot.chat.dto.dtoSkill.SkillMusic;
import com.robot.chat.target.ChetBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class RobotServiceImpl implements RobotService {
    private static final Logger logger = LoggerFactory.getLogger(RobotServiceImpl.class.getName());
    @Autowired
    private RestTemplate restTemplate;
    @Value(value = "${robot.skillUrl}")
    private String skillUrl;
    @Value(value = "${robot.robotName}")
    private String robotName;
    @Value(value = "${robot.appellation}")
    private String appellation;
    @Autowired
    private MusicSkillDataInit musicSkillDataInit;

    /**
     * 处理对话，并回复
     *
     * @param query 请求语句
     * @return 回复对象
     */
    @Override
    public ChatResponse getResponse(String query) {
        ChatResponse chatResponse;

        // 处理对话，输出nlu
        Nlu nlu = analysisQuery(query);

        if (nlu.getDomain() != null && nlu.getDomain().equals(Nlu.MUSIC)) {
            // 如果是音乐请求技能接口
            chatResponse = this.getSkillResponse(query, nlu);
        } else {
            // 如果不是音乐请求闲聊接口
            chatResponse = this.getChatResponse(query, nlu);
        }
        return chatResponse;
    }

    /**
     * 请求技能服务
     *
     * @param query 请求内容
     * @param nlu   内容含义
     * @return 接口回复对象
     */
    public ChatResponse getSkillResponse(String query, Nlu nlu) {
        ChatResponse chatResponse = new ChatResponse();
        Header header = new Header();
        Payload payload = new Payload();
        SkillPostBody skillPostBody = new SkillPostBody();
        ResponseEntity<SkillMusic> responseEntity = null;

        skillPostBody.setQuery(query);
        skillPostBody.setNlu(nlu);
        header.setSkillId(1);
        header.setSkillName("music");
        try {
            responseEntity = restTemplate.postForEntity("http://" + this.skillUrl + "/music", skillPostBody, SkillMusic.class);
            SkillMusic skillMusic = responseEntity.getBody();
            if (skillMusic.getCode().equals(200)) {
                String singer = skillMusic.getData().getSongs().get(0).getAr().get(0).getName();
                payload.setText("主人，我已经为找到" + singer + "的" + nlu.getSlots()[0].getValue() + "啦");
                payload.setMusic(skillMusic);
                payload.getMusic().setMusicName(nlu.getSlots()[0].getValue());
                payload.getMusic().setSinger(singer);
                chatResponse.setPayload(payload);
            } else {
                header.setCode(2);
                header.setMessage("你想听，我太高兴了");
            }
        } catch (Exception e) {
            header.setCode(1);
            header.setMessage("音乐正忙，请稍后再试");
        }
        chatResponse.setHeader(header);
        chatResponse.setNlu(nlu);
        return chatResponse;
    }

    /**
     * 请求闲聊服务
     *
     * @param query 请求内容
     * @param nlu   内容含义
     * @return 接口回复对象
     */
    public ChatResponse getChatResponse(String query, Nlu nlu) {
        ChatResponse chetResponse = new ChatResponse();
        Header header = new Header();
        Payload payload = new Payload();
        ResponseEntity<ChetBack> responseChetEntity = null;
        // 如果不是音乐请求闲聊接口
        try {
            responseChetEntity = restTemplate.getForEntity("https://api.tianapi.com/txapi/robot/?key=9d45fb6c42449577890a9606f1cb2168&question=" + query, ChetBack.class);
            if (responseChetEntity.getBody().getCode().equals(200)) {
                header.setSkillId(0);
                header.setSkillName("chat");
                payload.setText(responseChetEntity.getBody().getNewslist()[0].getReply()
                        .replace("{robotname}", this.robotName)
                        .replace("{appellation}", this.appellation));
                chetResponse.setPayload(payload);
                chetResponse.setNlu(nlu);
            }
        } catch (Exception e) {
            header.setCode(3);
            header.setMessage("闲聊正忙，请稍后再试");
        }
        chetResponse.setHeader(header);
        return chetResponse;
    }

    /**
     * Pattern算法
     *
     * @return 语言理解信息
     */
    private Nlu analysisQuery(String words) {
        int jud = 0;//找到匹配字符串与否的标志
        int j = 0;
        String temp = null;//初始化临时字符串
        String intent_str = "chat";   //初始化意图
        String info_match[] = new String[3];   //存储意图、歌名与人名
        info_match[0] = "chat";

        String file_music = "music";
        String file_person = "person";
        String file_intent = "intent";

        Nlu nlu = new Nlu();        //实例化Nlu对象
        Slots term[] = new Slots[2];    //创建词槽数组
        term[0] = new Slots();           //实例化每个词槽对象
        term[1] = new Slots();
        for (; words.length() > 0; ) {
            for (int i = 0; i < words.length(); i++) {
                temp = words.substring(i); //截取字符串，得到最后一个字符
                if (info_match[2] == null && hashMap_find(temp, file_music)) {
                    //与歌名库匹配
                    info_match[2] = temp;   //给歌名赋值
                    jud = 1;
                    int number = temp.length();    //判断匹配字符串长度
                    words = words.substring(0, words.length() - number);  //截去已匹配字符串
                    term[0].setName("MusicName");        //nlu词槽对象的赋值
                    term[0].setValue(info_match[2]);

                } else if (info_match[1] == null && hashMap_find(temp, file_person)) {
                    //与人名库匹配
                    info_match[1] = temp;
                    jud = 1;
                    int number = temp.length();
                    words = words.substring(0, words.length() - number);
                    term[1].setName("Person");     //nlu词槽对象的赋值
                    term[1].setValue(info_match[1]);
                } else if (info_match[0] == "chat" && hashMap_find(temp, file_intent)) {
                    //与意图库匹配
                    info_match[0] = "music"; //nlu域属性赋值
                    intent_str = "music.search";  //nlu意图属性赋值
                    jud = 1;
                    int number = temp.length();
                    words = words.substring(0, words.length() - number);
                }
            }
            //当前字符串最后一个字符，与前面字符串中字符的结合，无法匹配到库中信息
            //如：“我想听薛之谦的丑八怪”：
            //“怪”
            //“八怪”
            //“丑八怪”....
            if (jud == 0) {
                words = words.substring(0, words.length() - 1);//截掉最后一个元素继续循环。
            }
            jud = 0;
            j++;
        }
        //info_match[1]!=null&&info_match[2]!=null,若出现歌名，意图定为听音乐
        if (info_match[2] != null && info_match[1] != null) {
            info_match[0] = "music";
            intent_str = "music.search";
        }

        //nlu对象赋值
        nlu.setDomain(info_match[0]);
        nlu.setIntent(intent_str);
        nlu.setSlots(term);
        return nlu;
    }

    public boolean hashMap_find(String key, String path) {
        HashMap<String, String> music_map = new HashMap<>();
        boolean jud = false;
        switch (path) {
            case "music" :
                music_map = musicSkillDataInit.getMusicMap();
                jud = music_map.containsKey(key);
                break;
            case "person" :
                music_map = musicSkillDataInit.getPersonMap();
                jud = music_map.containsKey(key);
                break;
            case "intent" :
                music_map = musicSkillDataInit.getIntentMap();
                jud = music_map.containsKey(key);
                break;
        }
        return jud;
    }

    public static <T> Optional resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}



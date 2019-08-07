package com.robot.chat.service;

import com.robot.chat.dto.*;
import com.robot.chat.dto.dtoSkill.SkillMusic;
import com.robot.chat.target.ChetBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RobotServiceImpl implements RobotService {
    @Autowired
    private RestTemplate restTemplate;
    @Value(value = "${robot.skillUrl}")
    private String skillUrl;

    /**
     * 处理对话，并回复
     * @param query 请求语句
     * @return 回复对象
     */
    @Override
    public ChatResponse getResponse(String query) {
        ChatResponse chetResponse = new ChatResponse();
        Header header = new Header();
        Payload payload = new Payload();
        SkillPostBody skillPostBody = new SkillPostBody();
        ResponseEntity<SkillMusic> responseEntity = null;
        ResponseEntity<ChetBack> responseChetEntity = null;
                // 处理对话，输出nlu
        Nlu nlu = analysisQuery(query);

//        nlu.setDomain("music");
//        nlu.setIntent("Music.search");
//        Slots slot = new Slots();
//        Slots slot1 = new Slots();
//        slot.setName("Person");
//        slot.setValue("刘德华");
//        slot1.setValue("忘情水");
//        slot1.setName("MusicName");
//        Slots[] slots = new Slots[2];
//        slots[0] = slot; slots[1] = slot1;
//        nlu.setSlots(slots);
        if (nlu.getDomain().equals(Nlu.MUSIC)) {
            skillPostBody.setQuery(query);
            skillPostBody.setNlu(nlu);
            // 如果是音乐请求技能接口
            header.setSkillId(1);
            header.setSkillName("music");
            try {
                responseEntity = restTemplate.postForEntity("http://192.168.43.80:8080/music", skillPostBody, SkillMusic.class);
            } catch (Exception e) {
                header.setCode(1);
                header.setMessage("音乐接口正忙，请稍后再试");
            }
            if (responseEntity.getBody().getCode().equals(200)) {
                chetResponse.setHeader(header);
                payload.setText("主人，我已经为找到" + nlu.getSlots()[0].getValue() + "的"+ nlu.getSlots()[0].getValue() + "啦");
                payload.setMusic(responseEntity.getBody());
                chetResponse.setPayload(payload);
            } else {
                header.setCode(2);
                header.setMessage("你想听，我太高兴了");
            }
            chetResponse.setNlu(nlu);
        } else {
            // 如果不是音乐请求闲聊接口
            try {
                responseChetEntity = restTemplate.getForEntity("https://api.tianapi.com/txapi/robot/?key=9d45fb6c42449577890a9606f1cb2168&question=" + query, ChetBack.class);
            } catch (Exception e) {
                header.setCode(3);
                header.setMessage("闲聊接口正忙，请稍后再试");
            }
            if (responseChetEntity.getBody().getCode().equals(200)) {
                header.setSkillId(0);
                header.setSkillName("chat");
                chetResponse.setHeader(header);
                payload.setText(responseChetEntity.getBody().getNewslist()[0].getReply()
                        .replace("{robotname}", "小T")
                        .replace("{appellation}", "大白熊"));
                chetResponse.setPayload(payload);
                chetResponse.setNlu(nlu);
            }
        }

        return chetResponse;
    }

    /**
     * Pattern算法
     * @param query 输入的对话字符串
     * @return 语言理解信息
     * 示例：
     * input ： 我要听刘德华的忘情水
     * output：
     * {
     * "domain": "music",
     * "intent": "SkillMusic.search",
     * 	"slots": [{
     * 		"value": "刘德华",
     * 		"name": "Person"
     *        }, {
     * 		"value": "忘情水",
     * 		"name": "MusicName"
     *    }]
     * }
     */
    private Nlu analysisQuery(String query) {
        return new Nlu();
    }
}

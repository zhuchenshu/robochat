package com.robot.chat.service;

import com.robot.chat.dto.ChetResponse;
import com.robot.chat.dto.Header;
import com.robot.chat.dto.Nlu;
import com.robot.chat.dto.Payload;
import com.robot.chat.target.ChetBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RobotServiceImpl implements RobotService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 处理对话，并回复
     * @param query 请求语句
     * @return 回复对象
     */
    @Override
    public ChetResponse getResponse(String query) {
        ChetResponse chetResponse = new ChetResponse();
        Header header = new Header();
        Payload payload = new Payload();
        // 处理对话，输出nlu
        Nlu nlu = handleText(query);
        if (nlu.getDomain().equals(Nlu.MUSIC)) {
            // 如果是音乐请求技能接口
        } else {
            // 如果不是音乐请求闲聊接口
            ResponseEntity<ChetBack> responseEntity = restTemplate.getForEntity("https://v1.itooi.cn/netease/url?id=37239038&quality=flac", ChetBack.class);
            if (responseEntity.getBody().getCode().equals(200)) {
                header.setSkillId(1);
                header.setSkillName("chat");
                chetResponse.setHeader(header);
                payload.setText(responseEntity.getBody().getNewslist()[0].getReply()
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
     * "intent": "Music.search",
     * 	"slots": [{
     * 		"value": "刘德华",
     * 		"name": "Person"
     *        }, {
     * 		"value": "忘情水",
     * 		"name": "MusicName"
     *    }]
     * }
     */
    private Nlu handleText(String query) {
        return new Nlu();
    }
}

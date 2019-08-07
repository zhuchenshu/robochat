package com.robot.chat.service;

import com.robot.chat.dto.*;
import com.robot.chat.dto.dtoSkill.SkillMusic;
import com.robot.chat.service.analysis_support.JiebaSegmenter;
import com.robot.chat.service.analysis_support.WordDictionary;
import com.robot.chat.target.ChetBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.List;

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

    public static void main(String[] args) {
    //	analysisQuery("我想听冰雨");
	}
    private Nlu analysisQuery(String query) {
		Nlu nlu = new Nlu();
		String intent_str = null;
		Slots[] term = new Slots[2]; //词槽（包含歌手名与歌名）
		// TODO Auto-generated method stub
		JiebaSegmenter segmenter = new JiebaSegmenter(); //调
        String info_match[]=new String[3]; //存储分词与歌名与歌手名库匹配后的结果
		
		List<String> participle_res = segmenter.sentenceProcess(query); //列表participle_res用于存储分词后的结果
		
		for(int i=0;i<participle_res.size();i++){
			String words=participle_res.get(i);
			
		    if(words.equals("播放")||words.equals("听"))    //初步判断意图
			{
		    	info_match[0]="music";
				intent_str = "music.search";
			}
		   // else if(hashMap_find(words,"src/music.txt"))
			 
		    else if(hashMap_find(words,"C:\\Users\\chenshu.zhu\\Desktop\\聊天机器人\\project\\robochat\\robotServer\\src\\main\\java\\com\\robot\\chat\\service\\file\\music.txt")) //判断分词能否与音乐库中的歌名匹配
			{
		    	info_match[1]=words;
				term[0].setName("MusicName");
				term[0].setValue(info_match[1]);
			}
		    else if(hashMap_find(words,"C:\\Users\\chenshu.zhu\\Desktop\\聊天机器人\\project\\robochat\\robotServer\\src\\main\\java\\com\\robot\\chat\\service\\file\\person.txt"))//判断分词能否与库中的歌手名匹配
			{
		    	info_match[2]=words;
				term[1].setName("Person");
				term[1].setValue(info_match[2]);
			}
		}
		
		if(info_match[1]!=null&&info_match[2]!=null) //如果没有“听、播放”等关键词，进一步通过歌名与歌手名的匹配结果来判断意图是否为听音乐
		{
			info_match[0]="music";
			intent_str = "music.search";
		}
		
		//将域、意图、词槽赋值给nlu对象
		nlu.setDomain(info_match[0]);
		nlu.setIntent(intent_str);
		nlu.setSlots(term);
		
		return nlu;
    }
	
	public static boolean hashMap_find(String key, String path) {
		HashMap<String, String> music_map = new HashMap<>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			// fis = new FileInputStream("C:\\Users\\86182\\Desktop\\聊天机器人\\music.txt");//
			fis = new FileInputStream(path);// FileInputStream
			
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis, "UTF-8");// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
			
			while ((str = br.readLine()) != null) {
				music_map.put(str.trim(), "1");// 存放键值对

			}
			
			//判断hash_map键值里是否有该key
			if (music_map.containsKey(key)) {
				return true;
			}

		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}

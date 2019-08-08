package com.robot.chat.service;

import com.robot.chat.dto.*;
import com.robot.chat.dto.dtoSkill.Data;
import com.robot.chat.dto.dtoSkill.SkillMusic;
import com.robot.chat.target.ChetBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
        if (nlu.getDomain() != null && nlu.getDomain().equals(Nlu.MUSIC)) {
            skillPostBody.setQuery(query);
            skillPostBody.setNlu(nlu);
            // 如果是音乐请求技能接口
            header.setSkillId(1);
            header.setSkillName("music");
            try {
                responseEntity = restTemplate.postForEntity("http://127.0.0.1:8082/music", skillPostBody, SkillMusic.class);
				SkillMusic skillMusic = responseEntity.getBody();
                if (skillMusic.getCode().equals(200)) {
                	String singer = skillMusic.getData().getSongs().get(0).getAr().get(0).getName();
					payload.setText("主人，我已经为找到" + singer + "的"+ nlu.getSlots()[0].getValue() + "啦");
					payload.setMusic(skillMusic);
					payload.getMusic().setMusicName(nlu.getSlots()[0].getValue());
					payload.getMusic().setSinger(singer);
					chetResponse.setPayload(payload);
				} else {
					header.setCode(2);
					header.setMessage("你想听，我太高兴了");
				}
            } catch (Exception e) {
                header.setCode(1);
                header.setMessage("音乐正忙，请稍后再试");
            }
			chetResponse.setHeader(header);
            chetResponse.setNlu(nlu);
        } else {
            // 如果不是音乐请求闲聊接口
            try {
                responseChetEntity = restTemplate.getForEntity("https://api.tianapi.com/txapi/robot/?key=9d45fb6c42449577890a9606f1cb2168&question=" + query, ChetBack.class);
				if (responseChetEntity.getBody().getCode().equals(200)) {
					header.setSkillId(0);
					header.setSkillName("chat");
					payload.setText(responseChetEntity.getBody().getNewslist()[0].getReply()
							.replace("{robotname}", "小T")
							.replace("{appellation}", "大白熊"));
					chetResponse.setPayload(payload);
					chetResponse.setNlu(nlu);
				}
            } catch (Exception e) {
                header.setCode(3);
                header.setMessage("闲聊正忙，请稍后再试");
            }

        }
		chetResponse.setHeader(header);
        return chetResponse;
    }

    /**
     * Pattern算法
     * @return 语言理解信息
     */
    private Nlu analysisQuery(String words) {
		int jud=0;//找到匹配字符串与否的标志
		int j=0;
		String temp=null;//初始化临时字符串
		String intent_str = "chat";   //初始化意图
		String info_match[]=new String[3];   //存储意图、歌名与人名
		info_match[0] = "chat";

		String file_music="C:\\Users\\chenshu.zhu\\Desktop\\聊天机器人\\project\\robochat\\robotServer\\src\\main\\java\\com\\robot\\chat\\service\\file\\music.txt";
		String file_person="C:\\Users\\chenshu.zhu\\Desktop\\聊天机器人\\project\\robochat\\robotServer\\src\\main\\java\\com\\robot\\chat\\service\\file\\person.txt";
		String file_intent="C:\\Users\\chenshu.zhu\\Desktop\\聊天机器人\\project\\robochat\\robotServer\\src\\main\\java\\com\\robot\\chat\\service\\file\\intent.txt";

		Nlu nlu = new Nlu();        //实例化Nlu对象
		Slots term[] = new Slots[2];    //创建词槽数组
		term[0]=new Slots();           //实例化每个词槽对象
		term[1]=new Slots();
		for(;words.length()>0;){
			for(int i = 0;i<words.length();i++){
				temp = words.substring(i); //截取字符串，得到最后一个字符
				if(info_match[2]==null&&hashMap_find(temp,file_music)){
					//与歌名库匹配
					info_match[2]=temp;   //给歌名赋值
					jud = 1;
					int number = temp.length();    //判断匹配字符串长度
					words = words.substring(0,words.length()-number);  //截去已匹配字符串
					term[0].setName("MusicName");        //nlu词槽对象的赋值
					term[0].setValue(info_match[2]);

				}else if(info_match[1]==null&&hashMap_find(temp,file_person)){
					//与人名库匹配
			    	info_match[1]=temp;
			    	jud = 1;
					int number = temp.length();
					words = words.substring(0,words.length()-number);
					term[1].setName("Person");     //nlu词槽对象的赋值
					term[1].setValue(info_match[1]);
				}else if(info_match[0] == "chat" && hashMap_find(temp, file_intent)){
					//与意图库匹配
			    	info_match[0]="music"; //nlu域属性赋值
			    	intent_str = "music.search";  //nlu意图属性赋值
			    	jud = 1;
					int number = temp.length();
					words = words.substring(0,words.length()-number);
				}
			}
			//当前字符串最后一个字符，与前面字符串中字符的结合，无法匹配到库中信息
			//如：“我想听薛之谦的丑八怪”：
			//“怪”
			//“八怪”
			//“丑八怪”....
			if(jud == 0){
				words = words.substring(0, words.length()-1);//截掉最后一个元素继续循环。
			}
			jud = 0;
			j++;
		}
		//info_match[1]!=null&&info_match[2]!=null,若出现歌名，意图定为听音乐
		if(info_match[2]!=null && info_match[1] != null){
			info_match[0]="music";
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

    public static <T> Optional resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}



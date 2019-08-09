package com.robot.chat.datainit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;

@Component
@Order(1)
public class MusicSkillDataInit implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MusicSkillDataInit.class.getName());
    private HashMap<String, String> musicMap;
    private HashMap<String, String> personMap;
    private HashMap<String, String> intentMap;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        String file_music = "classpath:music.txt";
        musicMap = hashMap_find(file_music);
        String file_person = "classpath:person.txt";
        personMap = hashMap_find(file_person);
        String file_intent = "classpath:intent.txt";
        intentMap = hashMap_find(file_intent);
    }

    public HashMap<String, String> hashMap_find(String path) {
        HashMap<String, String> music_map = new HashMap<>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            File file = ResourceUtils.getFile(path);
            fis = new FileInputStream(file);// FileInputStream

            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis, "UTF-8");// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象

            while ((str = br.readLine()) != null) {
                music_map.put(str.trim(), "1");// 存放键值对
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
        return music_map;
    }

    public HashMap<String, String> getMusicMap() {
        return musicMap;
    }

    public void setMusicMap(HashMap<String, String> musicMap) {
        this.musicMap = musicMap;
    }

    public HashMap<String, String> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(HashMap<String, String> personMap) {
        this.personMap = personMap;
    }

    public HashMap<String, String> getIntentMap() {
        return intentMap;
    }

    public void setIntentMap(HashMap<String, String> intentMap) {
        this.intentMap = intentMap;
    }
}

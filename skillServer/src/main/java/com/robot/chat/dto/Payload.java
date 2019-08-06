package com.robot.chat.dto;

/**
 *
 * @Description 返回主题类，记录返回消息和音乐信息
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Payload {
    /**
     * 文本消息
     */
    public String text;
    /**
     * 音乐类实体
     */
    public Music music;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}

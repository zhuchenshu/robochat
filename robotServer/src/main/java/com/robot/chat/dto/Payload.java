package com.robot.chat.dto;

/**
 * @Description 聊天回复 信息
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class Payload {
    /**
     * 内容
     */
    private String text;
    /**
     * 音乐
     */
    private Music music;

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

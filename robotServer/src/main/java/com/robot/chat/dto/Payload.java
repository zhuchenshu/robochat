package com.robot.chat.dto;

import com.robot.chat.dto.dtoSkill.SkillMusic;

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
    private SkillMusic music;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SkillMusic getMusic() {
        return music;
    }

    public void setMusic(SkillMusic music) {
        this.music = music;
    }
}

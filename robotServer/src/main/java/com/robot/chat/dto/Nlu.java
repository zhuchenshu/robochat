package com.robot.chat.dto;

/**
 * @Description 聊天回复 语言理解信息
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class Nlu {
    public static String MUSIC = "music";
    public static String CHET = "chet";
    /**
     * 域
     */
    private String domain = Nlu.CHET;
    /**
     * 意图
     */
    private String intent;
    /**
     * 词槽
     */
    private Slots[] slots;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Slots[] getSlots() {
        return slots;
    }

    public void setSlots(Slots[] slots) {
        this.slots = slots;
    }
}

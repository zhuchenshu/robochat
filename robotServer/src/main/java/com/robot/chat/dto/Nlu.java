package com.robot.chat.dto;

/**
 * @Description 聊天回复 语言理解信息
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class Nlu {
    /**
     * 域
     */
    private String domain;
    /**
     * 意图
     */
    private String intent;
    /**
     * 词槽
     */
    private ChetNluSlots chetNluSlots;

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

    public ChetNluSlots getChetNluSlots() {
        return chetNluSlots;
    }

    public void setChetNluSlots(ChetNluSlots chetNluSlots) {
        this.chetNluSlots = chetNluSlots;
    }
}

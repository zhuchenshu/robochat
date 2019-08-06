package com.robot.chat.dto;

/**
 * @Description 聊天回复 词槽
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class ChetNluSlots {
    /**
     * 词槽名称
     */
    private String name;
    /**
     * 词槽值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

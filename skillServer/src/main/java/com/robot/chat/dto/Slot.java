package com.robot.chat.dto;

/**
 *
 * @Description 词槽类，记录关键字名和值
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Slot {
    /**
     * 关键字值
     */
    public String value;
    /**
     * 关键字名
     */
    public String name;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.robot.chat.dto;

import java.util.List;

/**
 *
 * @Description NLU结果类，记录域，意图和词槽
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Nlu {
    /**
     * NLU域值
     */
    public String domain;
    /**
     * NLU意图值
     */
    public String intent;
    /**
     * 词槽链表
     */
    public List<Slot> slots;

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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}

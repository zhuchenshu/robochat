package com.robot.chat.dto;

/**
 *
 * @Description 返回信息头部类，记录技能名和id
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Header {
    /**
     * 技能名
     */
    private String skillName;
    /**
     * 技能ID
     */
    private String skillId;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }
}

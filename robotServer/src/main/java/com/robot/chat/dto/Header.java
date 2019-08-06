package com.robot.chat.dto;

/**
 * @Description 聊天回复头信息
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class Header {
    /**
     * 技能id
     */
    private Integer skillId;
    /**
     * 技能名称
     */
    private String skillName;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}

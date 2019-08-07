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

    /**
     * 自定义状态码
     */
    private Integer code = 0;

    /**
     * 状态码所对应的信息说明
     */
    private String message = "正常";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

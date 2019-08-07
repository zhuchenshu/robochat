package com.robot.chat.dto.dtoSkill;

public class SkillMusic {
        /**
         * 状态码
         */
        private Integer code;

        /**
         * 歌曲数据类型
         */
        private Data data;

        private String playUrl;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getPlayUrl() {
            return playUrl;
        }
}

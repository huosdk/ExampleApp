package com.huosdk.exampleapp.main.vo;

import java.util.List;

/**
 * Created by liuhongliang on 2017/10/31.
 */

public class AppInitVO {

    /**
     * ip : 119.131.106.170
     * user_token : b1mh0bw0csjdNf2aa4z5UM1tbrmdkdzea62FplpMGNncHRkMTQ0dTUO0O0O
     * agentgame :
     * timestamp : 1509518163
     * up_info : {"up_status":0,"url":"","content":""}
     * help : {"app_id":0,"tel":"","qq":["2852506293"],"email":"","qqgroup":[""],"qqgroupkey":[""],"wx":null,"service_time":"9:00～18:00（周一到周日）"}
     * newmsg : 1
     */

    private String ip;
    private String user_token;
    private String agentgame;
    private int timestamp;
    private UpInfoBean up_info;
    private HelpBean help;
    private int newmsg;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getAgentgame() {
        return agentgame;
    }

    public void setAgentgame(String agentgame) {
        this.agentgame = agentgame;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public UpInfoBean getUp_info() {
        return up_info;
    }

    public void setUp_info(UpInfoBean up_info) {
        this.up_info = up_info;
    }

    public HelpBean getHelp() {
        return help;
    }

    public void setHelp(HelpBean help) {
        this.help = help;
    }

    public int getNewmsg() {
        return newmsg;
    }

    public void setNewmsg(int newmsg) {
        this.newmsg = newmsg;
    }

    public static class UpInfoBean {
        /**
         * up_status : 0
         * url :
         * content :
         */

        private int up_status;
        private String url;
        private String content;

        public int getUp_status() {
            return up_status;
        }

        public void setUp_status(int up_status) {
            this.up_status = up_status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class HelpBean {
        /**
         * app_id : 0
         * tel :
         * qq : ["2852506293"]
         * email :
         * qqgroup : [""]
         * qqgroupkey : [""]
         * wx : null
         * service_time : 9:00～18:00（周一到周日）
         */

        private int app_id;
        private String tel;
        private String email;
        private Object wx;
        private String service_time;
        private List<String> qq;
        private List<String> qqgroup;
        private List<String> qqgroupkey;

        public int getApp_id() {
            return app_id;
        }

        public void setApp_id(int app_id) {
            this.app_id = app_id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getWx() {
            return wx;
        }

        public void setWx(Object wx) {
            this.wx = wx;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public List<String> getQq() {
            return qq;
        }

        public void setQq(List<String> qq) {
            this.qq = qq;
        }

        public List<String> getQqgroup() {
            return qqgroup;
        }

        public void setQqgroup(List<String> qqgroup) {
            this.qqgroup = qqgroup;
        }

        public List<String> getQqgroupkey() {
            return qqgroupkey;
        }

        public void setQqgroupkey(List<String> qqgroupkey) {
            this.qqgroupkey = qqgroupkey;
        }
    }

    @Override
    public String toString() {
        return "AppInitVO{" +
                "ip='" + ip + '\'' +
                ", user_token='" + user_token + '\'' +
                ", agentgame='" + agentgame + '\'' +
                ", timestamp=" + timestamp +
                ", up_info=" + up_info +
                ", help=" + help +
                ", newmsg=" + newmsg +
                '}';
    }
}

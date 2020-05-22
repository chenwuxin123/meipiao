package com.meipiao.chat;

import lombok.Data;

/**
 * @author sainentl
 * @create 2020-04-03 14:04
 */
@Data
public class WeChatData {
    //发送微信消息的URLString sendMsgUrl="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

    @Override
    public String toString() {
        return "WeChatData{" +
                "touser='" + touser + '\'' +
                ", toparty='" + toparty + '\'' +
                ", totag='" + totag + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", agentid='" + agentid + '\'' +
                ", text=" + text +
                '}';
    }

    /**
     * 成员账号
     */
    private String touser;
    /**
     * 部门id
     */
    private String toparty;
    /**
     * 标签id
     */
    private String totag;
    /**
     * 消息类型
     */
    private String msgtype;
    /**
     * 企业应用的agentID
     */
    private String agentid;
    /**
     * 实际接收Map类型数据
     */
    private Object text;
}

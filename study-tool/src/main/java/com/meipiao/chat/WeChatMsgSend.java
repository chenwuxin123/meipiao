package com.meipiao.chat;

import lombok.extern.slf4j.Slf4j;
import org.anyline.entity.DataRow;
import org.anyline.net.HttpUtil;
import org.anyline.util.BeanUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sainentl
 * @create 2020-04-03 14:02
 */
@Slf4j
public class WeChatMsgSend {
    private CloseableHttpClient httpClient;
    /**
     * 用于提交登陆数据
     */
    private HttpPost httpPost;
    /**
     * 用于获得登录后的页面
     */
    private HttpGet httpGet;

    public WeChatMsgSend() {
    }

    /**
     * corpid应用组织编号   corpsecret应用秘钥
     * 获取toAuth(String Get_Token_Url)返回结果中键值对中access_token键的值
     *
     * @param
     */
    public String getToken(String corpid, String corpsecret) {
        Map<String, Object> params = new HashMap<>();
        params.put("corpid", corpid);
        params.put("corpsecret", corpsecret);
        String resp = HttpUtil.get(WeChatUrlData.GET_TOKEN_URL, params).getText(); //发送Http请求
        log.info("resp=====:" + resp);
        DataRow row = DataRow.parseJson(resp);  //可将值存入HashMap中
        return row.getString("access_token");
    }

    /**
     * @return String
     * @Title:创建微信发送请求post数据 touser发送消息接收者    ，msgtype消息类型（文本/图片等），
     * application_id应用编号。
     * 本方法适用于text型微信消息，contentKey和contentValue只能组一对
     */
    public String createPostData(String toUser,String toParty,String toTag, String msgType,
                                 String applicationId, String contentKey, String contentValue) {
        WeChatData wcd = new WeChatData();
        wcd.setTouser(toUser);
        wcd.setToparty(toParty);
        wcd.setTotag(toTag);
        wcd.setAgentid(applicationId);
        wcd.setMsgtype(msgType);
        Map<Object, Object> content = new HashMap<>(1);
        content.put(contentKey, contentValue);
        wcd.setText(content);
        return BeanUtil.object2json(wcd); //会报错，可使用FastJson
    }

    /**
     * @return String
     * @Title 创建微信发送请求post实体
     * charset消息编码    ，contentType消息体内容类型，
     * url微信消息发送请求地址，data为post数据，token鉴权token
     */
    public String post(String data, String token) throws IOException {
        return post("UTF-8", WeChatUrlData.SEND_MESSAGE_URL, data, token);
    }

    public String post(String charset, String url, String data, String token) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpPost = new HttpPost(url + "?access_token=" + token);
        httpPost.setEntity(new StringEntity(data, charset));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        log.info("call [{}], param:{}, resp:{}", url, data, resp);
        return resp;
    }
}

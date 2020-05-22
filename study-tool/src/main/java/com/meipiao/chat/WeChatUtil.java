package com.meipiao.chat;

import org.anyline.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sainentl
 * @create 2020-04-24 17:08
 */
public class WeChatUtil {
    private final static String CORP_ID = "wwff4a855bc27dbd66";
    private final static String CORP_SECRET = "bq1sodwxg1cFl4f5sUIsG_0nSFcbk1p6klIBlWdlHLk";
    public final static String AGENT_ID = "1000002";

    public static String getToken() {
        WeChatMsgSend swx = new WeChatMsgSend();
        return swx.getToken(CORP_ID, CORP_SECRET);
    }

    public static String getDeptList(String token) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("access_token", token);
        return HttpUtil.get(WeChatUrlData.GET_DEPT_URL, params).getText(); //发送Http请求
    }

    public static String getDeptUserList(String token, int deptId, int fetchChild) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("access_token", token);
        params.put("department_id", deptId);
        params.put("fetch_child", fetchChild);
        return HttpUtil.get(WeChatUrlData.GET_USER_URL, params).getText(); //发送Http请求
    }

    public static void sendMsg(String toUser,String toParty,String toTag, String applicationId, String contentValue) throws IOException {
        WeChatMsgSend swx = new WeChatMsgSend();
        String token = getToken();
        String postData = swx.createPostData(toUser,toParty,toTag, "text", applicationId, "content", contentValue);
        swx.post(postData, token);
    }
}

package zone.wwwww.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zone.wwwww.service.WeChatService;
import zone.wwwww.utils.HttpUtil;
import zone.wwwww.vo.wechat.WeChatToken;
import zone.wwwww.vo.wechat.WeChatUserinfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 14:36:48
 */
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

    @Value("${wechat.sns.oauth2.access_token.url}")
    private String snsOauth2AccessTokenUrl;
    @Value("${wechat.sns.oauth2.access_token.appid}")
    private String snsOauth2AccessTokenAppId;
    @Value("${wechat.sns.oauth2.access_token.secret}")
    private String snsOauth2AccessTokenSecret;
    @Value("${wechat.sns.oauth2.access_token.grant_type}")
    private String snsOauth2AccessTokenGrantType;

    @Value("${wechat.sns.userinfo.url}")
    private String userinfoUrl;


    @Override
    public WeChatToken getToken(String code) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> params = new HashMap<>();
        params.put("appid", snsOauth2AccessTokenAppId);
        params.put("secret", snsOauth2AccessTokenSecret);
        params.put("code", code);
        params.put("grant_type", snsOauth2AccessTokenGrantType);

        String wxTokenStr = HttpUtil.getRequest(snsOauth2AccessTokenUrl, params);
        WeChatToken weChatToken = null;
        try {
            weChatToken = mapper.readValue(wxTokenStr, WeChatToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weChatToken;
    }

    @Override
    public WeChatUserinfo getUserinfo(WeChatToken weChatToken) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", weChatToken.getAccessToken());
        params.put("openid", weChatToken.getOpenId());

        String wxUserinfoStr = HttpUtil.getRequest(userinfoUrl, params);
        WeChatUserinfo weChatUserinfo = null;
        try {
            weChatUserinfo = mapper.readValue(wxUserinfoStr, WeChatUserinfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weChatUserinfo;
    }
}

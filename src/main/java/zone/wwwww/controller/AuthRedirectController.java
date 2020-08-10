package zone.wwwww.controller;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zone.wwwww.service.AlipayService;
import zone.wwwww.service.WeChatService;
import zone.wwwww.vo.wechat.WeChatToken;
import zone.wwwww.vo.wechat.WeChatUserinfo;

import java.util.Objects;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 10:50:33
 */
@RestController
@RequestMapping("/")
public class AuthRedirectController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private WeChatService weChatService;

    /**
     * 用户扫码登陆成功后回调该方法
     *
     * @param appId
     * @param scope
     * @param authCode
     * @return
     */
    @RequestMapping("authRedirect/alipay")
    public String alipay(@RequestParam("app_id") String appId, @RequestParam("scope") String scope, @RequestParam("auth_code") String authCode) {

        ObjectMapper mapper = new ObjectMapper();

        AlipaySystemOauthTokenResponse token = alipayService.getAlipaySystemOauthTokenResponse(authCode);
        if (token.isSuccess()) {
            AlipayUserInfoShareResponse userinfoShare = alipayService.getAlipayUserInfoShareResponse(token);
            if (userinfoShare.isSuccess()) {
                try {
                    return "Alipay用户信息获取成功, token = " + mapper.writeValueAsString(token) + ", userinfoShare = " + mapper.writeValueAsString(userinfoShare);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Alipay用户信息获取失败";
    }

    /**
     * 用户扫码登陆成功后回调该方法
     *
     * @param code
     * @param state 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
     */
    //    @RequestMapping("authRedirect/wechat")
    @RequestMapping("loginServlet")
    public String wechat(String code, String state) {
        if (Objects.isNull(code)) {
            // 返回登陆页面
            return "WeChat用户取消登陆, code = " + code + ", state = " + state;
        }

        WeChatToken token = weChatService.getToken(code);

        if (Objects.isNull(token)) {
            return "WeChat Token获取失败";
        }

        WeChatUserinfo userinfo = weChatService.getUserinfo(token);

        if (Objects.isNull(userinfo)) {
            return "WeChat获取用户公开信息失败";
        }
        return "WeChat 信息获取成功, token = " + token.toString() + ", userinfo = " + userinfo.toString();
    }
}

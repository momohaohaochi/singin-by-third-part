package zone.wwwww.service;

import zone.wwwww.vo.wechat.WeChatToken;
import zone.wwwww.vo.wechat.WeChatUserinfo;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 14:36:39
 */
public interface WeChatService {

    /**
     * 用户扫码授权登陆成功后, 根据返回的code获取用户令牌
     *
     * @param code
     * @return
     */
    WeChatToken getToken(String code);

    /**
     * 使用用户令牌可以获取用户的公共信息
     *
     * @param weChatToken
     * @return
     */
    WeChatUserinfo getUserinfo(WeChatToken weChatToken);
}
